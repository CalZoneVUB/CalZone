#!/usr/bin/env python
import mechanize, platform
import lxml.html, os
import sys
import csv
import urllib
import itertools

def fetchTrajects(url):
	agent = mechanize.Browser()
	agent.open(url)
	rawResponse = agent.response().read()
	html = lxml.html.fromstring(rawResponse)
	options = html.xpath('//select[@name="identifier"]/option/text()')
	return options

def fetchCourses(url):
	agent = mechanize.Browser()
	agent.open(url)
	rawResponse = agent.response().read()
	html = lxml.html.fromstring(rawResponse)
	options = html.xpath('//td[@class="object-cell-border"]/*[1]/tr/td/text()')
	return options

def scrapeTrajects(toFile, url):
	print 'Start scraping trajects.'
	# Initialize csv file
	writer = csv.writer(toFile)
	writer.writerow(['Faculty', 'Program', 'Career', 'Year', 'Traject', 'Course'])

	res = fetchTrajects(url)
	resEntries = list()
	for s in res:
		resEntries = resEntries + parseTrajectInfo(s, toFile)

	# Remove duplicates from resEntries
	resEntries.sort()
	resEntries = list(resEntries for resEntries,_ in itertools.groupby(resEntries))

	#Output to CSV + console
	writer = csv.writer(toFile)
	writer.writerows(resEntries)

	print 'Scraping done.'
	print 'Total number of relations: ' + str(len(resEntries))

# Input: name of a traject, output file name
# Returns a list of pairs of trajects - courses for this traject name
def parseTrajectInfo(trajectName, toFile):
	trajectName = trajectName.strip()
	print 'Fecthing courses of 1st semester for traject: ' + trajectName
	urlTraject = 'http://locus.vub.ac.be/reporting/individual?idtype=name&days=1-6&template=Student+Set+Individual&objectclass=Student+Set&width=100&'
	urlPart = { 'identifier' : trajectName}
	urlTraject = urlTraject + urllib.urlencode(urlPart)
	urlTraject = urlTraject + '&weeks=1-14&periods=3-23&submit2=bekijk+het+lesrooster'
	res = fetchCourses(urlTraject)
	

	#Courses for 2nd semester
	print 'Fecthing courses of 2nd semester for traject: ' + trajectName
	urlTraject = 'http://locus.vub.ac.be/reporting/individual?idtype=name&days=1-6&template=Student+Set+Individual&objectclass=Student+Set&width=100&'
	urlPart = { 'identifier' : trajectName}
	urlTraject = urlTraject + urllib.urlencode(urlPart)
	urlTaject = urlTraject + 'weeks=22-29%3B32-36&periods=3-23&submit2=bekijk+het+lesrooster'
	res = res + fetchCourses(urlTraject)
	
	#Format entries
	res = processList(trajectName, res);
	map(printCourseOfTraject, res)

	print 'Traject done.'
	return res

# Returns a list of pairs of trajects - courses for this traject name.
def processList(trajectName, col):
	# Search for Acameic Year entries
	col[:] = [course for course in col if not ((course == 'Academische openingszitting / Academic Opening 2013-2014') 
		or (course == 'Onthaal door decaan WE')
		or (course == 'Onthaal nieuwe generatiestudenten in aanwezigheid van de Rector')
		or (course == 'Campusrondleiding door studentenkringen')
		or (course == 'Feedback Pretoetsen')
		or (course == 'Feedback Pretoets Wiskunde')
		or (course == 'Begeleiding Registratie')
		or (course.startswith('Pretoets'))
		or (course.startswith('Tutoring'))
		or (course.startswith('Onthaal'))
		or (course.startswith('Contact vakgroep')))]

	# Search for HOC/WPO endings of line
	newCol = list()
	patterns = ['HOC', 'WPO', 'HOC + WPO', 'WEEK', 'WK', 'REFLECTIESESSIE', '(EX', 'PGO', 'EXTRA', 'INTROLES', '(TE)', '(nm)', '(vm)', 'BP', 'DAG']
	for course in col:
		for pattern in patterns:
			index = course.upper().rfind(pattern)
			if index != -1:
				course = course[:index]
				break
		course = course.strip().rstrip('-').rstrip('(').strip()
		newCol.append(course)

	# Search for courses which start with "TE: ", "VIP " or "EVS "
	newCol2 = list()
	for course in newCol:
		if not course.startswith('TE: ') and not course.startswith('VIP ') and not course.startswith('EVS '):
			newCol2.append(course)

	# Every course to lower course
	# newCol2[:] = [course.lower() for course in newCol2 if True]

	# Process traject name: in rare cases there are groups. Delete the GRP suffix
	indexGRP = trajectName.rfind('GRP')
	if indexGRP != -1:
		trajectName = trajectName[:indexGRP]
	trajectName = trajectName.strip()
	# Process traject name: in rare cases there is a BP suffix.
	indexBP = trajectName.rfind('BP')
	if indexBP != -1:
		trajectName = trajectName[:indexBP]
	trajectName = trajectName.strip()

	# remove duplicates of collection: convert to set
	result = set(newCol2)

	# Make pairs of traject - course.
	colPair = list()
	for course in result:
		resultEntry = list()
		resultEntry.append('Faculteit Wetenschappen')
		resultEntry.append(parseProgramOfTraject(trajectName))
		resultEntry.append(parseCareerOfTraject(trajectName))
		resultEntry.append(parseYearOfTraject(trajectName))
		resultEntry.append(trajectName)
		resultEntry.append(course)
		colPair.append(resultEntry)

	return colPair

def parseProgramOfTraject(trajectName):
	programName = trajectName
	# Remove leading numbers
	if (programName.startswith('1') or programName.startswith('2') or programName.startswith('3')):
		programName = programName[2:]
		programName = programName.strip()

	# Remove career prefix
	if (programName.startswith('B') or programName.startswith('M ')):
		programName = programName[2:]
		programName = programName.strip()

	# Remove traling "(Keuze)" text
	if (programName.upper().endswith('(KEUZE)')):
		programName = programName[:(len(programName)-len('(KEUZE)'))]
		programName = programName.strip()

	return programName

def parseCareerOfTraject(trajectName):
	if (trajectName.find('B ') != -1):
		return 'BA'
	elif (trajectName.find('MM') != -1):
		return 'MNM'
	elif (trajectName.find('Master') != -1):
		return 'MA'
	elif (trajectName.find('M ') != -1):
		return 'MA'
	else:
		return 0

def parseYearOfTraject(trajectName):
	split = trajectName.split()
	if (is_number(split[0])):
		return int(float(split[0]))
	else:
		return 1

def is_number(s):
	try:
		float(s)
		return True
	except ValueError:
		return False

# Input: A course of a traject.
def printCourseOfTraject(s):
	print '>>>> ' + str(s)

result_file = open('resultTrajects.csv', 'r+b')
result_file.truncate()
scrapeTrajects(result_file, 'http://locus.vub.ac.be/1onevenjr/studsetWE_onevenjr.html')
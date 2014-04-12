#!/usr/bin/env python
import mechanize, platform
import lxml.html, os
import sys
import csv
import urllib

intRelations = 0 # Het totaal aantal verwerkte relaties.

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
	writer = csv.writer(toFile)
	writer.writerow(['Traject', 'Course'])

	res = fetchTrajects(url)
	for s in res:
		parseTrajectInfo(s, toFile)

	print 'Scraping done.'
	print 'Total number of relations: ' + str(intRelations)

# Input: name of a traject, output file name
# Writes the data of the courses to the csv file.
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

	#Output to CSV
	writer = csv.writer(toFile)
	writer.writerows(res)

	print 'Traject done.'

def processList(trajectName, col):
	# Search for Acameic Year entries
	col[:] = [course for course in col if not ((course == 'Academische openingszitting / Academic Opening 2013-2014') 
		or (course == 'Onthaal door decaan WE')
		or (course == 'Onthaal nieuwe generatiestudenten in aanwezigheid van de Rector')
		or (course == 'Campusrondleiding door studentenkringen')
		or (course == 'Feedback Pretoetsen')
		or (course == 'Feedback Pretoets Wiskunde')
		or (course == 'Pretoets Wiskunde')
		or (course == 'Pretoets Fysica')
		or (course == 'Begeleiding Registratie')
		or (course == 'Pretoets Fysica en Chemie')
		or (course.startswith('Contact vakgroep')))]

	# Search for HOC/WPO endings of line
	newCol = list()
	patterns = [' (HOC', ' (WPO', '(HOC + WPO)']
	for course in col:
		for pattern in patterns:
			index = course.rfind(pattern)
			if index != -1:
				course = course[:index]
				break
		course.strip()
		newCol.append(course)

	# Process traject name: in rare cases there are groups. Delete the GRP suffix
	indexGRP = trajectName.rfind('GRP')
	if indexGRP != -1:
		trajectName = trajectName[:indexGRP]
	trajectName.strip()

	# remove duplicates of collection: convert to set
	result = set(newCol)

	# Make pairs of traject - course.
	colPair = list()
	for course in result:
		resultEntry = list()
		resultEntry.append(trajectName)
		resultEntry.append(course)
		colPair.append(resultEntry)



	return colPair

# Input: A course of a traject.
def printCourseOfTraject(s):
	global intRelations
	intRelations = intRelations + 1
	print '>>>> ' + str(s)

result_file = open('resultTrajects.csv', 'r+b')
scrapeTrajects(result_file, 'http://locus.vub.ac.be/1onevenjr/studsetWE_onevenjr.html')
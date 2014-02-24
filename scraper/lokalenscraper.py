#!/usr/bin/env python
import mechanize, platform
import lxml.html, os
import sys
import csv

def fetchInfo(url):
	agent = mechanize.Browser()
	agent.open(url)
	rawResponse = agent.response().read()
	html = lxml.html.fromstring(rawResponse)
	options = html.xpath('//select[@name="identifier"]/option/text()')
	return options

def scrapeRooms(toFile, url):
	print 'Start scraping class rooms.'
	res = fetchInfo(url)
	res = map(parseRoomInfo, res)
	writer = csv.writer(toFile)
	writer.writerow(['Type','Building','Floor','Room','Details'])
	writer.writerows(res)
	print 'Scraping class rooms done'

def scrapePCRooms(toFile, url):
	print 'Start scraping computer rooms.'
	res = fetchInfo(url)
	res = map(parsePCRoomInfo, res)
	writer = csv.writer(toFile)
	writer.writerows(res)
	print 'Scraping computer rooms done'
    
def parseRoomInfo(s):
		try:
			# split on building, floor, room+info
			info = s.split('.',2)
			# split last element to get room and info separated
			room_and_info = info[-1].split('(',1)
			# clean up the last split
			room_and_info[0] = room_and_info[0].strip()
			room_and_info[-1] = room_and_info[-1].strip(')')
			# make 3-ary list into -ary now that the last element is splitted
			info[-1] = room_and_info[0] 
			info.append(room_and_info[1])
			# insert type tag
			info.insert(0, 'Class room')
			return info
		except:
			return [s]
	
def parsePCRoomInfo(s):
		try:
			# split on building, floor, room+info
			info = s.split('.',2)
			# insert type tag
			info.insert(0, 'Computer room')
			return info
		except:
			return [s]
					
result_file = open('result.csv', 'r+b')
scrapeRooms(result_file, 'http://locus.vub.ac.be/1onevenjr/location_leslokalen_onevenjr.html')
scrapePCRooms(result_file, 'http://locus.vub.ac.be/1onevenjr/location_computerlokaal_onevenjr.html')

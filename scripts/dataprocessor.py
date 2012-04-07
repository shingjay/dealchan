import simplejson
import urllib2
import pymysql
import sys
from nlp import trainer, featx

sys.path.append('./nlp')

db_host = '127.0.0.1'
db_port = 3306
db_username = 'root'
db_password = 'password'
db_dbname = 'sd'
raw_table = 'GrouponDeal'
filtered_table = 'GrouponDealLive'

def processDatabase():
	try:
		classifier = trainer.trainCorpus()
	except:
		print "Error occurred: ", sys.exc_info()[0]
		return

	conn = pymysql.connect(host=db_host, port=db_port, user=db_username, passwd=db_password, db=db_dbname)
	cur = conn.cursor()
	cur.execute('SELECT * FROM ' + raw_table)

	'''
	Index	Field name		Data type
	0	id			bigint(20)
	1	active			tinyint(1)
	2	bought			int(11)
	3	city			varchar(255)
	4	currentPrice		double
	5	description		longtext
	6	discount		double
	7	extraInformation	varchar(255)
	8	image			varchar(255)
	9	link			varchar(255)
	10	originalPrice		double
	11	pubDate			datetime
	12	saving			double
	13	timeEnds		datetime
	14	title			varchar(255)
	15	address			varchar(255)
	*16	latitude		double
	*17	longitude		double
	*18	category		varchar(255)

	* - only in live/filtered DB
	'''
	for c in cur:
		cur2 = conn.cursor()
		cat_set = classifier.classify(featx.bag_of_words(c[14].split()))
		if len(cat_set) == 0:
			category = 'Miscellaneous'
		else:
			category = cat_set.pop()
			if category == 'activitiesevents':
				category = 'Activities & Events'
			elif category == 'fooddrinks':
				category = 'Food & Drinks'
			elif category == 'healthbeauty':
				category = 'Health & Beauty'
			elif category == 'shoppingservices':
				category = 'Shopping & Services'
			elif category == 'travel':
				category = 'Travel'
			else:
				category = ''
		cat_set.clear()
		json_file = googlemaps_json(c[15])
		json_data = simplejson.loads(json_file)
		if json_data['status'] == 'OK':
			latitude = json_data['results'][0]['geometry']['location']['lat']
			longitude = json_data['results'][0]['geometry']['location']['lng']
		else:
			latitude = 360.0
			longitude = 360.0
		db_entry = [c[1], c[2], c[3], c[4], c[5], c[6], c[7], c[8],c[9], c[10], c[11], c[12], c[13], c[14], c[15], latitude, longitude, category]
		cur2.execute("INSERT INTO " + filtered_table + " (active, bought, city, currentPrice, description, discount, extraInformation, image, link, originalPrice, pubDate, saving, timeEnds, title, address, latitude, longitude, category) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", db_entry)
		cur2.close()
	cur.close()

	''' cur.execute("INSERT INTO GrouponDealLive (active, bought, city, currentPrice,
	 description, discount, extraInfo, image, link, origPrice, pubDate,
	 saving, timeEnds, title, address, latitude, longitude, category)
	 VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s,
	 %s, %s, %s, %s)", dict)
	'''

	'''Categories mapping:
	'activitiesevents' : 'Activities & Events'
	'fooddrinks': 'Food & Drinks'
	'healthbeauty': 'Health & Beauty'
	'shoppingservices': 'Shopping & Services'
	'travel': 'Travel'
	everything else: 'Miscellaneous'
	'''
	conn.close()

def googlemaps_json(address):
	""" returns json data
	"""
	url = 'http://maps.google.com/maps/api/geocode/json?address=' + address.replace(" ", "+") + ',+Malaysia' + '&sensor=false'
	return urllib2.urlopen(url).read()

if __name__ == "__main__":
	processDatabase()

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
filtered_table = 'deals'

def processDatabase():
	try:
		classifier = trainer.trainCorpus()
	except:
		print "Error occurred: ", sys.exc_info()[0]
		return

	conn = pymysql.connect(host=db_host, port=db_port, user=db_username, passwd=db_password, db=db_dbname)
	conn2 = pymysql.connect(host=db_host, port=db_port, user=db_username, passwd=db_password, db=db_dbname)

	# clear filtered db
	cur = conn.cursor()
	cur.execute("DELETE FROM " + filtered_table)
	cur.close()

	# categories id mapping
	categories_dict = dict()
	cur = conn.cursor()
	cur.execute("SELECT id, title FROM categories")
	for c in cur:
		categories_dict[c[1]] = c[0]
	cur.close()
	print categories_dict

	# cities id mapping
	cities_dict = dict()
	cur = conn.cursor()
	cur.execute("SELECT id, name, country FROM cities")
	for c in cur:
		cities_dict[c[1].strip() + '#' + c[2].strip()] = c[0]
	cur.close()
	print cities_dict

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
	16	country			varchar(255)

	*17	latitude		double
	*18	longitude		double
	*19	category_id		int
	*29	city_id			int

	* - only in live/filtered DB
	'''
	for c in cur:
		# assign category id
		cat_set = classifier.classify(featx.bag_of_words(c[14].split()))
		if len(cat_set) == 0:
			category_id = categories_dict['Miscellaneous']
		else:
			category = cat_set.pop()
			if category == 'activitiesevents':
				category_id = categories_dict['Activities & Events']
			elif category == 'fooddrinks':
				category_id = categories_dict['Food & Drinks']
			elif category == 'healthbeauty':
				category_id = categories_dict['Health & Beauty']
			elif category == 'shoppingservices':
				category_id = categories_dict['Shopping & Services']
			elif category == 'travel':
				category_id = categories_dict['Travel']
			else:
				category_id = categories_dict['Miscellaneous']
		cat_set.clear()

		# assign city id
		try:
			if category_id == categories_dict['Travel']:
				city_id = cities_dict['Travel#' + c[16].strip()]
			else:
				city_id = cities_dict[c[3].strip() + '#' + c[16].strip()]
			#something
		except KeyError:
			print 'City and country not found: ' + c[3].strip() + '#' + c[16].strip()
			continue

		# find latitude and longitude of address
		json_file = googlemaps_json(c[15])
		json_data = simplejson.loads(json_file)
		if json_data['status'] == 'OK':
			latitude = json_data['results'][0]['geometry']['location']['lat']
			longitude = json_data['results'][0]['geometry']['location']['lng']
		else:
			latitude = 360.0
			longitude = 360.0

		# insert into filtered db
		db_entry = [c[1], c[2], c[4], c[5], c[6], c[7], c[8],c[9], c[10], c[11], c[12], c[13], c[14], c[15], latitude, longitude, category_id, city_id]
		cur2 = conn2.cursor()
		cur2.execute("INSERT INTO " + filtered_table + " (active, bought, currentPrice, description, discount, extraInformation, image, link, originalPrice, pubDate, saving, timeEnds, title, address, latitude, longitude, category_id, city_id) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)", db_entry)
		cur2.close()
	cur.close()

	'''Categories mapping:
	'activitiesevents' : 'Activities & Events'
	'fooddrinks': 'Food & Drinks'
	'healthbeauty': 'Health & Beauty'
	'shoppingservices': 'Shopping & Services'
	'travel': 'Travel'
	everything else: 'Miscellaneous'
	'''
	conn.close()
	conn2.close()

def googlemaps_json(address):
	""" returns json data
	"""
	url = 'http://maps.google.com/maps/api/geocode/json?address=' + address.replace(" ", "+") + '&sensor=false'
	return urllib2.urlopen(url).read()

if __name__ == "__main__":
	processDatabase()

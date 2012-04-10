import pymysql
import os
import re
import nltk
from corpus import getStopwordsCorpus

text_path = './datatext'
db_host = '127.0.0.1'
db_port = 3306
db_username = 'root'
db_password = 'password'
db_dbname = 'sd'
table_name = 'asia_deals_clean'

def processTitle(title):
	stopwords = getStopwordsCorpus()
	pattern = re.compile('\w+')
	postext = nltk.pos_tag(nltk.word_tokenize(title))
	wordslist = []
	for w, t in postext:
		if pattern.match(w) is not None and (t == 'NN' or t == 'NNS' or t == 'NNP' or t == 'NNPS') and w not in set(stopwords.words()):
			wordslist.append(w)
	return ' '.join(wordslist)

if __name__ == "__main__":
	categories_names = []
	conn = pymysql.connect(host=db_host, port=db_port, user=db_username, passwd=db_password, db=db_dbname)
	cur = conn.cursor()
	cur.execute("SELECT category_custom FROM " + table_name +  " GROUP BY category_custom")

	for r in cur:
		categories_names.append(r[0])
	cur.close()

	for c in categories_names:

		cur = conn.cursor()
		cat_name = c.lower().replace(' ', '').replace('&', '')

		file_count = 0
		cur.execute("SELECT title FROM " + table_name + " WHERE category_custom = '" + c + "'")

		#Create directories for categories if they don't exist
		if not os.path.exists(text_path + '/' + cat_name):
			os.makedirs(text_path + '/' + cat_name)

		for t in cur:
			deal_file = open(text_path + '/' + cat_name + '/' + str(file_count) + '.txt', 'w')
			title = processTitle(t[0])
			deal_file.write(title + '\n')
			file_count = file_count + 1
			deal_file.close()

		cur.close()

	#close MySQL connection
	conn.close()


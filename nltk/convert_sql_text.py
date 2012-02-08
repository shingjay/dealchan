import pymysql
import os

text_path = '/Users/tmlee/TMWorkspace/SeniorDesignDeals/nltk/datatext2'

if __name__ == "__main__":
	categories_names = []
	conn = pymysql.connect(host='127.0.0.1', port=3306, user='root', passwd='', db='mysql')
	cur = conn.cursor()
	cur.execute("USE deal_db")
	cur.execute("SELECT category_custom FROM dealery GROUP BY category_custom")

	for r in cur:
		categories_names.append(r[0])
	cur.close()

	for c in categories_names:

		cur = conn.cursor()
		cat_name = c.lower().replace(' ', '').replace('&', '')

		file_count = 0
		cur.execute("SELECT title FROM dealery WHERE category_custom = '" + c + "'")
		for t in cur:
			deal_file = open(text_path + '/' + cat_name + '-' + str(file_count) + '.txt', 'a')
			deal_file.write(t[0] + '\n')
			file_count = file_count + 1

		cur.close()

	#close MySQL connection
	conn.close()


import pymysql

text_path = '/home/yingzhe/Desktop/seniordesign/datatext'

if __name__ == "__main__":
	categories_names = []
	conn = pymysql.connect(host='127.0.0.1', port=3306, user='root', passwd='password', db='mysql')
	cur = conn.cursor()
	cur.execute("USE seniordesign")
	cur.execute("SELECT category_custom FROM dealery GROUP BY category_custom")

	for r in cur:
		categories_names.append(r[0])
	cur.close()

	for c in categories_names:
		cur = conn.cursor()
		category_file = open(text_path + '/' + c.lower().replace(' ', '').replace('&', '') + '.txt', 'a')
		cur.execute("SELECT title FROM dealery WHERE category_custom = '" + c + "'")
		for t in cur:
			category_file.write(t[0] + '\n')
		cur.close()

	#close MySQL connection
	conn.close()


from nltk.corpus.reader import CategorizedPlaintextCorpusReader
from nltk.corpus.reader import WordListCorpusReader

def getDealsCorpus():
	return CategorizedPlaintextCorpusReader('.', r'datatext/.*/.*\.txt', cat_pattern=r'datatext/(\w+)')

def getStopwordsCorpus():
	return WordListCorpusReader('stopwords/', ['words'])


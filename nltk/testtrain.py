from corpus import getDealsCorpus
from featx import *
import scipy
import numpy
from nltk.classify import MaxentClassifier
from nltk.classify.util import accuracy
import pickle
import nltk
import os
from classification import *

nltk.classify.megam.config_megam('/home/yingzhe/Desktop/seniordesign/megam_i686.opt')
# Save Classifier
def SaveClassifier( classifier):
	fModel = open('classifier.pkl',"wb")
	pickle.dump(classifier, fModel,1)
	fModel.close()
	os.system("rm classifier.pkl.gz")
	os.system("gzip classifier.pkl")

# Load Classifier    
def LoadClassifier():
	os.system("gunzip classifier.pkl.gz")
	fModel = open('classifier.pkl',"rb")
	classifier = pickle.load(fModel)
	fModel.close()
	os.system("gzip classifier.pkl")
	return classifier

def corpus_high_info_words(corpus, score_fn=BigramAssocMeasures.chi_sq):
	labelled_words = []
	for label in corpus.categories():
		print label
		labelled_words.append((label, corpus.words(categories=[label])))
	return high_information_words(labelled_words, score_fn=score_fn)

def corpus_train_test_feats(corpus, feature_detector=bag_of_words):
	train_feats = []
	test_feats = []
	for fileid in corpus.fileids():
		if fileid.endswith('1.txt') or fileid.endswith('5.txt'):
			featlist = test_feats
		else:
			featlist = train_feats
		feats = feature_detector(corpus.words(fileid))
		labels = corpus.categories(fileid)
		featlist.append((feats, labels))
	return train_feats, test_feats

if __name__ == "__main__":
	c = getDealsCorpus()
	hiwords = corpus_high_info_words(c)
	featdet = lambda words: bag_of_words_in_set(words, hiwords)
	train_feats, test_feats = corpus_train_test_feats(c, featdet)
	trainf = lambda train_feats: MaxentClassifier.train(train_feats, algorithm='megam', trace=0, max_iter=10)
	labelset = set(c.categories())
	classifiers = train_binary_classifiers(trainf, train_feats, labelset)
	multi_classifier = MultiBinaryClassifier(*classifiers.items())
	multi_p, multi_r, avg_md = multi_metrics(multi_classifier, test_feats)
	print multi_p['activitiesevents'], multi_r['activitiesevents'], avg_md


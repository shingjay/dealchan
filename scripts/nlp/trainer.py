from corpus import getDealsCorpus
from featx import *
import scipy
import numpy
from nltk.classify import MaxentClassifier
from nltk.classify.util import accuracy
import pickle
import nltk
import os
#from classification import *
#from featx import *
from . import classification, featx

abs_path = '/home/yingzhe/Projects/seniordesign/dealchan/scripts/nlp/'
nltk.classify.megam.config_megam(abs_path + 'megam_i686.opt')
classifier_fname = abs_path + 'classifier.pkl'

# Save Classifier
def SaveClassifier(classifier):
	fModel = open(classifier_fname,"wb")
	pickle.dump(classifier, fModel,1)
	fModel.close()
	#if os.path.exists(classifier_zname):
	#	os.system("rm " + classifier_zname)
	#os.system("gzip " + classifier_fname)

# Load Classifier    
def LoadClassifier():
	#if not os.path.exists(classifier_zname):
	#	print 'Classifier does not exist'
	#	return None
	#os.system("gunzip " + classifier_zname)
	fModel = open(classifier_fname,"rb")
	classifier = pickle.load(fModel)
	fModel.close()
	#os.system("gzip " + classifier_fname)
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

def trainCorpus():
	if os.path.exists(classifier_fname):
		return LoadClassifier()
	else:
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
		SaveClassifier(multi_classifier)
		return multi_classifier


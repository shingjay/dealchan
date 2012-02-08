from nltk.corpus import movie_reviews
from nltk import *
from featx2 import *
from scipy import *
from nltk.classify import MaxentClassifier
from nltk.classify.util import accuracy
import pickle

deals = CategorizedPlaintextCorpusReader( '/Users/tmlee/nltk_data/corpora/deals', r'.*\.txt' , cat_pattern=r'(\w+).*')

deals.categories()
lfeats = label_feats_from_corpus(deals)
lfeats.keys()
train_feats, test_feats = split_label_feats(lfeats)
trainf = MaxentClassifier.train(train_feats, algorithm='megam', trace=0, max_iter=10)
print(accuracy(trainf, test_feats))
burgerdeal = "[Up to 56% Off] Jerk BBQ Quarter Chicken OR Beef Burger. Includes Golden Strip Salad Appetiser at BBQ Chicken, 1 Utama / Wangsa Walk Mall. For 1 (RM12) OR 2 People (RM21). Halal".split()
trainf.classify(bag_of_words(bag_of_words(burgerdeal))
trainf.show_most_informative_features(100)

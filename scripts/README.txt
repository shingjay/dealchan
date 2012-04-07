Using NLTK component to categorize deals:
-----------------------------------------

Requirements:
1. Python 2.6+ (http://www.python.org)
2. PyYAML (http://pyyaml.org/wiki/PyYAML)
3. NLTK (including the corpus data, only "book" is needed - http://www.nltk.org/data, http://www.nltk.org/download)
4. NumPy and SciPy (http://www.scipy.org/Download)
5. MegaM (including in the directory)

Structure:
nlp/classification.py - contains Classifier class definition and some useful statistical methods
nlp/corpus.py - reads a corpus from textfiles
nlp/featx.py - definitions of features for NLTK
nlp/dealcat.py - test drive for NLTK
***nlp/convert_sql_text.py - converts data from MySQL database to text files.
***nlp/trainer.py - trains a classifier and saves in a zipped package
***dataprocessor.py - converts data from raw DB to live/production/filtered DB

Running NLTK components:
------------------------

Converting SQL to text files:
1. Change all the configuration variables in convert_sql_text.py and trainer.py.
2. Run "python convert_sql_text.py". This can take more than 30 minutes and create thousands of text files.

Training:
1. Run "python dataprocessor.py". To run the trainer separately, run "python". In python shell, run "from nlp import trainer", then "classifier = trainer.trainCorpus()"
2. Classify a text by using the line "classifier.classify(featx.bag_of_words("your text here".split()))". Refer to usage in dataprocessor.py. Certain modules such as featx needs to be imported before you can classify anything.


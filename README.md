MMTSquery
=========

MMTS query processing and dialog system.

pre-requisite:
1. ubuntu 32-bit system.
2. shallow parser and full parser installed
3. eclipse installed preferable


To run the tool at its best:

1. open the project in eclipse
2. enter these arguments (all absolute paths preferred)
	a. ontological resource folder (/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/)
	b. linguistic resource folder (/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/)
	c. Nlp tools folder (/home/rishabh/workspace/MmtsProcessing/NLP/)
	d. rdf ontology (/home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/MMTS_test.rdf)

To run the tool from command line:
export MMTSQA=/path/to/MMTSQA/folder

java -cp "$MMTSQA/pellet-2.3.1/lib/*:$MMTSQA/NLP/ILMT/Sanchay/dist/Sanchay.jar:$MMTSQA/MMTSProcessing" com.processquery.ProcessQuery <ontological resource folder> <linguistic resource folder> <Nlp tools folder> <rdf ontology>

eg.
java -cp "$MMTSQA/pellet-2.3.1/lib/*:$MMTSQA/NLP/ILMT/Sanchay/dist/Sanchay.jar:$MMTSQA/MMTSProcessing" com.processquery.ProcessQuery /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/ /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/ /home/rishabh/workspace/MmtsProcessing/NLP/ /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/MMTS_test.rdf

You can find the related documentation at http://rish321.github.io/MMTSquery/doc/

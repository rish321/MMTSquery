#!/bin/bash

while read line
do
	echo "$line" > tempin.txt
	shallow_parser_hin tempin.txt tempout.txt 
	cat $2/shallow-parser-hin.3.0.fc8/OUTPUT.tmp/postagger.tmp > $2/corpus.train.pos.hn
done < $1	

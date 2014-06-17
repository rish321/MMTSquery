#!/bin/bash

VAANEE_DIR=/home/hduser/sampark


while read line
do
	
	echo "$line" > $2/.tmp/tempin.txt
	shallow_parser_hin $2/.tmp/tempin.txt $2/.tmp/tempout.txt
	cat $PWD/OUTPUT$2/.tmp/postagger$2/.tmp > $2/.tmp/corpus.train.pos.hn
	perl $VAANEE_DIR/hindiLWG/ssf2lwg.pl < $2/.tmp/corpus.train.pos.hn > $2/.tmp/corpus.train.lwgI.hn
	perl $VAANEE_DIR/hindiLWG/hnd_lwg.pl  $2/.tmp/corpus.train.lwgI.hn > $2/.tmp/corpus.train.lwgI$2/.tmp.hn
	perl $VAANEE_DIR/hindiLWG/hindi_feature.pl $2/.tmp/corpus.train.lwgI$2/.tmp.hn >> $2/.tmp/temporaryfile1

done < $1

path=$HOME/sampark/shallow_parser_hin/bin/sys/convertor/hin

cat $2/.tmp/temporaryfile1 > $2/.tmp/temp
sed -i 's/^$/::/g' $2/.tmp/temp
sed -i 's/\t/\n/g' $2/.tmp/temp
tr '\n' ' ' < $2/.tmp/temp > $2/.tmp/temp2
sed -i 's/ :: :: /\n/g' $2/.tmp/temp2
sed -i 's/PREP://g' $2/.tmp/temp2
sed -i 's/AUX:VM_//g' $2/.tmp/temp2
sed -i 's/AUX:VM//g' $2/.tmp/temp2
sed -i 's/[^ ]*_[A-Z]*_//g' $2/.tmp/temp2
sed -i 's/jAwI_hE/jA hE/g' $2/.tmp/temp2
sed -i 's/AwI_hE/A hE/g' $2/.tmp/temp2
$path/bin/hin/wx_to_i8.exe <  $2/.tmp/temp2 > $2/.tmp/temp3
perl $path/bin/hin/iscii2utf8.pl < $2/.tmp/temp3

rm $2/.tmp/temporaryfile1

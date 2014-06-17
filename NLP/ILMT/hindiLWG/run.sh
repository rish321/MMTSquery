#!/bin/bash

VAANEE_DIR=/home/hduser/sampark

while read line
do
	
	echo "$line" > .tmp/tempin.txt
	shallow_parser_hin .tmp/tempin.txt .tmp/tempout.txt
	cat $PWD/OUTPUT.tmp/postagger.tmp > .tmp/corpus.train.pos.hn
	perl $VAANEE_DIR/hindiLWG/ssf2lwg.pl < .tmp/corpus.train.pos.hn > .tmp/corpus.train.lwgI.hn
	perl $VAANEE_DIR/hindiLWG/hnd_lwg.pl  .tmp/corpus.train.lwgI.hn > .tmp/corpus.train.lwgI.tmp.hn
	perl $VAANEE_DIR/hindiLWG/hindi_feature.pl .tmp/corpus.train.lwgI.tmp.hn >> .tmp/temporaryfile1

done < $1

path=$HOME/sampark/shallow_parser_hin/bin/sys/convertor/hin

cat .tmp/temporaryfile1 > .tmp/temp
sed -i 's/^$/::/g' .tmp/temp
sed -i 's/\t/\n/g' .tmp/temp
tr '\n' ' ' < .tmp/temp > .tmp/temp2
sed -i 's/ :: :: /\n/g' .tmp/temp2
sed -i 's/PREP://g' .tmp/temp2
sed -i 's/AUX:VM_//g' .tmp/temp2
sed -i 's/AUX:VM//g' .tmp/temp2
sed -i 's/[^ ]*_[A-Z]*_//g' .tmp/temp2
sed -i 's/jAwI_hE/jA hE/g' .tmp/temp2
sed -i 's/AwI_hE/A hE/g' .tmp/temp2
$path/bin/hin/wx_to_i8.exe <  .tmp/temp2 > .tmp/temp3
perl $path/bin/hin/iscii2utf8.pl < .tmp/temp3

rm .tmp/temporaryfile1

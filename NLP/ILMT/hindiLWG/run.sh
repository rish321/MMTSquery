#!/bin/bash

VAANEE_DIR=$3/ILMT/
export SHALLOW_PARSER_HIN=$4/shallow_parser_hin
	
#echo $1 > $2/.tmp/tempin.txt
$4/shallow_parser_hin/bin/sys/hin/shallow_parser_hin $1 $2/.tmp/tempout.txt
cat $PWD/OUTPUT.tmp/postagger.tmp > $2/.tmp/corpus.train.pos.hn
perl $VAANEE_DIR/hindiLWG/ssf2lwg.pl < $2/.tmp/corpus.train.pos.hn > $2/.tmp/corpus.train.lwgI.hn
perl $VAANEE_DIR/hindiLWG/hnd_lwg.pl  $2/.tmp/corpus.train.lwgI.hn > $2/.tmp/corpus.train.lwgI.tmp.hn
perl $VAANEE_DIR/hindiLWG/hindi_feature.pl $2/.tmp/corpus.train.lwgI.tmp.hn > $2/.tmp/temp

path=$HOME/sampark/shallow_parser_hin/bin/sys/convertor/hin

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

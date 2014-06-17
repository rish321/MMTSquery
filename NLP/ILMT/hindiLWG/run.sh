#perl $VAANEE_DIR/hindiLWG/text2SSF.pl < $1 > corpus.train.ssf.hn
#$setu/bin/sl/morph/hin/morph_run.sh corpus.train.ssf.hn > corpus.train.morphPre.hn
#perl $VAANEE_DIR/hindiLWG/post.pl < corpus.train.morphPre.hn > corpus.train.morph.hn
#sh $setu/bin/sl/postagger/postagger_run.sh corpus.train.morph.hn > corpus.train.pos.hn

#!/bin/bash

touch temporaryfile1
rm temporaryfile1

while read line
do
	
	echo "$line" > tempin.txt
	shallow_parser_hin tempin.txt tempout.txt 
	
	cat $PWD/OUTPUT.tmp/postagger.tmp > corpus.train.pos.hn

	perl $VAANEE_DIR/hindiLWG/ssf2lwg.pl < corpus.train.pos.hn > corpus.train.lwgI.hn
	perl $VAANEE_DIR/hindiLWG/hnd_lwg.pl  corpus.train.lwgI.hn > corpus.train.lwgI.tmp.hn
	perl $VAANEE_DIR/hindiLWG/hindi_feature.pl corpus.train.lwgI.tmp.hn >> temporaryfile1

done < $1

path=$HOME/sampark/shallow_parser_hin/bin/sys/convertor/hin

cat temporaryfile1 > temp
sed -i 's/^$/::/g' temp
sed -i 's/\t/\n/g' temp
tr '\n' ' ' < temp > temp2
sed -i 's/ :: :: /\n/g' temp2
sed -i 's/PREP://g' temp2
sed -i 's/AUX:VM_//g' temp2
sed -i 's/AUX:VM//g' temp2
sed -i 's/[^ ]*_[A-Z]*_//g' temp2
sed -i 's/jAwI_hE/jA hE/g' temp2
sed -i 's/AwI_hE/A hE/g' temp2
$path/bin/hin/wx_to_i8.exe <  temp2 > /tmp/tmp.is
perl $path/bin/hin/iscii2utf8.pl < /tmp/tmp.is
rm -f /tmp/tmp.is
rm temp
rm temp2
rm temporaryfile1

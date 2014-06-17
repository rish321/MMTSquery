#!/bin/bash

path=$HOME/sampark/shallow_parser_hin/bin/sys/convertor/hin

cat $1 > temp
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

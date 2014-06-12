#!/bin/bash

folder="/home/rishabh/tools/pellet-2.3.1/unprocessed"
mkdir $6

for file in `ls $folder`
do
a=`echo $file | cut -d '.' -f2`
b="sh"
echo $a
echo $b
if [[ $a == $b ]]; then
	cat $folder/$file | sed -e "s,folder,$6,g" > $6/$file
	chmod +x $6/$file
else
	cat $folder/$file | sed -e "s,rdfsprefix,$1,g" | sed -e "s,rdfprefix,$2,g" | sed -e "s,owlprefix,$3,g" | sed -e "s,xsdprefix,$4,g"  | sed -e "s,baseprefix,$5,g" > $6/$file
fi

done

#rm $6/*~

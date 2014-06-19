#!/bin/bash

cat folderkab | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/set/$6/g" > folderkabtmp

if [[ "$7" == "dep" ]]; then
	cat folderkabtmp | sed -e "s/^SELECT (/#/g"
else	
	cat folderkabtmp | sed -e "s/^SELECT ?/#/g"
fi

rm folderkabtmp

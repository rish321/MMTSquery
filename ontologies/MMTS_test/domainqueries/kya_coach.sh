#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kitne | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/set/$6/g" | sed -e "s/property/$8/g" | sed -e "s/couach_type/$9/g"


#> /home/rishabh/tools/pellet-2.3.1/kitnetmp

#if [[ "$7" == "dep" ]]; then
#	cat /home/rishabh/tools/pellet-2.3.1/kabtmp | sed -e "s/^SELECT (/#/g"
#else	
#	cat /home/rishabh/tools/pellet-2.3.1/kabtmp | sed -e "s/^SELECT ?/#/g"
#fi
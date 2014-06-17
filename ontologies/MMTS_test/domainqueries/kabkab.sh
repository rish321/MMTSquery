#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kabkab | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/set/$6/g" > /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kabkabtmp

if [[ "$7" == "dep" ]]; then
	cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kabkabtmp | sed -e "s/^SELECT (/#/g"
else	
	cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kabkabtmp | sed -e "s/^SELECT ?/#/g"
fi

rm /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kabkabtmp

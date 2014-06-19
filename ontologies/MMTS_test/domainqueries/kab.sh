#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kab | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/set/$6/g" > /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kabtmp

if [[ "$7" == "dep" ]]; then
	cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kabtmp | sed -e "s/^SELECT (/#/g"
else	
	cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kabtmp | sed -e "s/^SELECT ?/#/g"
fi

rm /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kabtmp

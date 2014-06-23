#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kab | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/set/$6/g" > /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kabtmp

if [[ "$7" == "dep" ]]; then
	cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kabtmp | sed -e "s/^SELECT (/#/g"
else	
	cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kabtmp | sed -e "s/^SELECT ?/#/g"
fi

rm /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kabtmp

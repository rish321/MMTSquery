#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kitnidersimple | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/begtimearr/$3/g" | sed -e "s/endtimearr/$4/g" | sed -e "s/depstation/$5/g" | sed -e "s/arrstation/$6/g" | sed -e "s/set/$7/g"

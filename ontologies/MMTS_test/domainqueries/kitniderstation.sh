#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/kitniderstation | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g" | sed -e "s/currtime/$6/g" | sed -e "s/set/$7/g"

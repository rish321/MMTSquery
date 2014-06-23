#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/kahankahan | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/set/$5/g"


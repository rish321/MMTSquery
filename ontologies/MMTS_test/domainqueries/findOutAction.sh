#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/findOutAction | sed -e "s/condition/$1/g"

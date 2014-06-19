#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/findOutAction | sed -e "s/condition/$1/g"

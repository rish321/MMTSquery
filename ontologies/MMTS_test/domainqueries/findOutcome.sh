#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/findOutcome | sed -e "s/action/$1/g"

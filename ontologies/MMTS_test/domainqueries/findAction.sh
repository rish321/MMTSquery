#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/findAction | sed -e "s/superaction/$1/g" | sed -e "s/supertheme/$2/g"

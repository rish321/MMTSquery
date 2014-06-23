#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/findSubaction | sed -e "s/actions/$1/g"

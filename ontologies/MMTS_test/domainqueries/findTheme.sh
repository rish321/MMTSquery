#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/findTheme | sed -e "s/actions/$1/g"

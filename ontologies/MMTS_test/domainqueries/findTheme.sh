#!/bin/bash

cat /home/shreshtha/git/MMTSquery1/ontologies/MMTS_test/domainqueries/findTheme | sed -e "s/actions/$1/g"

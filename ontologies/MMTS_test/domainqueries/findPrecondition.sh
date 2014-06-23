#!/bin/bash

cat /home/pramesh/git/MMTSquery/ontologies/MMTS_test/domainqueries/findPrecondition | sed -e "s/action/$1/g"

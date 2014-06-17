#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/findPreAction | sed -e "s/condition/$1/g"

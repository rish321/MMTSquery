#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/findOutcome | sed -e "s/action/$1/g"

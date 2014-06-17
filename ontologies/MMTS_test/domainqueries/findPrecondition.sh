#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/findPrecondition | sed -e "s/action/$1/g"

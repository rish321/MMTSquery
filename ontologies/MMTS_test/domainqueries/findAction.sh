#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/findAction | sed -e "s/superaction/$1/g" | sed -e "s/supertheme/$2/g"

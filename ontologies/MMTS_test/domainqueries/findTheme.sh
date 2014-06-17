#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/findTheme | sed -e "s/actions/$1/g"

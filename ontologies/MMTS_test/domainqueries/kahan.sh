#!/bin/bash

cat /home/rishabh/workspace/MmtsProcessing/ontologies/MMTS_test/domainqueries/kahan | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/set/$5/g"


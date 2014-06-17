#!/bin/bash

cat /home/rishabh/tools/pellet-2.3.1/findOutcome | sed -e "s/action/$1/g"

#!/bin/bash

cat /home/rishabh/tools/pellet-2.3.1/kahan | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/set/$5/g" > /home/rishabh/tools/pellet-2.3.1/kahantmp


#!/bin/bash

cat /home/rishabh/tools/pellet-2.3.1/kitnikitniderstation | sed -e "s/begtimedep/$1/g" | sed -e "s/endtimedep/$2/g" | sed -e "s/depstation/$3/g" | sed -e "s/arrstation/$4/g" | sed -e "s/atstation/$5/g"

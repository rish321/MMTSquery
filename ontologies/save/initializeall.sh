#!/bin/bash

mkdir $1

cp /home/rishabh/tools/pellet-2.3.1/* $1/

cat /home/rishabh/tools/pellet-2.3.1/findPrecondition | sed -e "s/prefix/$1/g" > $1/Precondition

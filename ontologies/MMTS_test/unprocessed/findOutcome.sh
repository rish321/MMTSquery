#!/bin/bash

cat folderfindOutcome | sed -e "s/action/$1/g"

#!/bin/bash

cat folderfindAction | sed -e "s/superaction/$1/g" | sed -e "s/supertheme/$2/g"

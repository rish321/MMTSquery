
perl $SHALLOW_PARSER_HIN/bin/sl/headcomputation/printinput.pl $1 > /tmp/headcomputationinput$$.tmp

perl $SHALLOW_PARSER_HIN/bin/sl/headcomputation/headcomputation.pl --path=$SHALLOW_PARSER_HIN/bin/sl/headcomputation --input=/tmp/headcomputationinput$$.tmp

rm -fr /tmp/headcomputationinput$$.tmp


perl $SHALLOW_PARSER_HIN/bin/sl/pruning/printinput.pl $1 > /tmp/pruneinput$$.tmp
perl $SHALLOW_PARSER_HIN/bin/sl/pruning/pruning.pl --path=$SHALLOW_PARSER_HIN/bin/sl/pruning --resource=$SHALLOW_PARSER_HIN/data_bin/sl/pruning/mapping.db --input=/tmp/pruneinput$$.tmp

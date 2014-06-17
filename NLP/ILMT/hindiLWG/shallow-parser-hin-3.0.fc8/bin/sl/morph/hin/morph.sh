
perl $SHALLOW_PARSER_HIN/bin/sl/morph/hin/deletesentencetag.pl $1 > /tmp/slput$$.tmp

$SHALLOW_PARSER_HIN/bin/sl/morph/hin/morph_hin.exe --logfilepath /tmp/sl$$.log --pdgmfilepath $SHALLOW_PARSER_HIN/data_bin/sl/morph/hin --uwordpath $SHALLOW_PARSER_HIN/data_bin/sl/morph/hin/dict_final --dictfilepath $SHALLOW_PARSER_HIN/data_bin/sl/morph/hin/dict/  -ULDWH --inputfile /tmp/slput$$.tmp --outputfile /tmp/sl$$.out

perl $SHALLOW_PARSER_HIN/bin/sl/morph/hin/addsentencetag.pl /tmp/sl$$.out

rm -fr /tmp/sl$$.log /tmp/slput$$.tmp /tmp/sl$$.out

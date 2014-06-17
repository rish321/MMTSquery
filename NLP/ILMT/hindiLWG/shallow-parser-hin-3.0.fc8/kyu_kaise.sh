#!bin/bash


 while read line
 do
         echo "$line" > tempin.txt
         shallow_parser_hin tempin.txt tempout.txt| sh $setu/bin/sl/fullparser/fullparser_hin_run.sh tempout.txt > parse_output.txt
 done < $1
                                                   
         

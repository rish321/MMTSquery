#!/bin/bash


#while read line
# do
#         echo "$line" > tempin.txt
#	 echo "shallow_parser_hin tempin.txt tempout.txt| sh $2/bin/sl/fullparser/fullparser_hin_run.sh tempout.txt > /tmp/tempout.txt"
#echo "shallow_parser_hin $1 | sh $2/bin/sl/fullparser/fullparser_hin_run.sh $3"
         $2/shallow_parser_hin/bin/sys/hin/shallow_parser_hin $1 /tmp/tempmid.txt
	 sh $2/bin/sl/fullparser/fullparser_hin_run.sh /tmp/tempmid.txt $2 > $3
# done < $1
                                                   
         

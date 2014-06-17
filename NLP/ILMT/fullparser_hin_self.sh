#exec 1>$setu/bin/sl/fullparser/logfile
#exec 2>&1
foldername=$(date +%Y%m%d%H%M%S)
mkdir -p  /tmp/"$foldername"
python $2/bin/sl/fullparser/add_dummy_dependencies.py --input $1 --output /tmp/"$foldername"/dummy_dep_out.txt

python $2/bin/sl/fullparser/InterChunkConllConvertor-v2/ssf2conll.py --input /tmp/"$foldername"/dummy_dep_out.txt --output /tmp/"$foldername"/ssf2conll_out.txt

java -Xms2g -Xmx2G -jar $2/bin/sl/fullparser/maltparser/maltparser-1.7.jar -c train -m parse -pp head -pcr ignore -gds T.TRANS#A.DEPREL -nr true -grl main -F $2/bin/sl/fullparser/maltparser/hindi_best.xml -lo -s_0_-t_1_-d_2_-g_0.12_-c_0.7_-r_0.3_-e_0.5 -v error -w $2/bin/sl/fullparser/maltparser/ -i /tmp/"$foldername"/ssf2conll_out.txt -o /tmp/"$foldername"/maltparser_out1.txt

java -Xms2g -Xmx2G -jar $2/bin/sl/fullparser/maltparser/maltparser-1.7.jar -c proj -m deproj -pp head -pcr ignore -gds T.TRANS#A.DEPREL -nr true -grl main -F $2/bin/sl/fullparser/maltparser/hindi_best.xml -lo -s_0_-t_1_-d_2_-g_0.12_-c_0.7_-r_0.3_-e_0.5 -v error -w $2/bin/sl/fullparser/maltparser/ -i /tmp/"$foldername"/maltparser_out1.txt -o /tmp/"$foldername"/maltparser_out2.txt

java -jar $2/bin/sl/fullparser/conll2ssf_converter.jar --input1 /tmp/"$foldername"/dummy_dep_out.txt --input2 /tmp/"$foldername"/maltparser_out2.txt




sub wx2utf
{	
	$path=@_[0];
	$file=@_[1];
	$output=@_[2];

	system("$path/bin/hin/wx_to_i8.exe <  $file > /tmp/tmp.is");
	system("perl $path/bin/hin/iscii2utf8.pl < /tmp/tmp.is > $output");
	system("rm -f /tmp/tmp.is");
}
1;

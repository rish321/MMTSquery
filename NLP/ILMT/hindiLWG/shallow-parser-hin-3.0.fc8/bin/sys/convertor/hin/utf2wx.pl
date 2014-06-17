
sub utf2wx
{	
	$path=@_[0];
	$file=@_[1];
	$output=@_[2];
	system("perl $path/unicode-wx.pl $file > /tmp/tmp.num");
	system("perl $path/utf82iscii.pl < /tmp/tmp.num > /tmp/tmp.iscii");
	system("$path/i8_wx/d8_ra_wp_r.exe < /tmp/tmp.iscii > $output");
	system("rm -f /tmp/tmp.iscii /tmp/tmp.num");
}
1;

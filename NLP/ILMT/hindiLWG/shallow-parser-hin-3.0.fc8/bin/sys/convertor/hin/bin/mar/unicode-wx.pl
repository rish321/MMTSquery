
while(<>)
{

	$_=~s/०/0/g;
    	$_=~s/१/1/g;
	$_=~s/२/2/g;
	$_=~s/३/3/g;
	$_=~s/४/4/g;
	$_=~s/५/5/g;
	$_=~s/६/6/g;
	$_=~s/७/7/g;
	$_=~s/८/8/g;
	$_=~s/९/9/g;
	$_=~s/।/\./g;

	print STDOUT $_;
}

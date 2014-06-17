#!/usr/bin/perl

$tmp=$ARGV[0];
chomp($tmp);
open(ENG,$tmp);

@src = <ENG>;

$no_lines = @src;


for ($i=0; $i<$no_lines; $i++)
{
	@words = split(/\s+/,$src[$i]);
	$no_of_words = @words;

	if($no_of_words > 1)
	{
		$prep = "";
		$vaux = "";

		($word,$pos) = split(/_/,$words[0]);
		$vaux = $pos."_";
		$vaux_bkp = $vaux;

		for($j=1; $j < $no_of_words; $j++)
		{
			if($words[$j] =~ /PSP/)
			{
				$words[$j] =~ /(.*)\_PSP\_.*/;
				$prep = $prep.$1."_";
			}
			else
			{
				($word) = split(/_/,$words[$j]);
				$vaux = $vaux.$word."_";
			}
		}
		print $words[0];
		if($prep !~ /^\s*$/)
		{
			chop($prep);
			print "\tPREP:",$prep;
		}
		if($vaux ne $vaux_bkp)
		{
			chop($vaux);
			print "\tAUX:",$vaux;
		}
		print "\n";
	}
	elsif($src[$i] =~ /_VM_/)
	{
		chomp($src[$i]);
		print $src[$i];
		print "\tAUX:","VM\n";
	}
	else
	{
		print $src[$i];
	}

}

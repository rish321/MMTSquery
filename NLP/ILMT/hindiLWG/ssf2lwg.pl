#!/usr/bin/perl
@lines = <STDIN>;
chomp(@lines);

for $line (@lines)
{
	@words = split(/\t/,$line);
	if($words[0] eq "</Sentence>")
	{
		print "\n";
	}
	elsif($words[1] eq "")
	{
	}
	else
	{
		@array = split(/,/,$words[3]);
		$array[0] =~ s/<fs af='//g;
		if($array[0] eq "")
		{
			$array[0] = $words[1];
		}

		print $words[1]," ",$array[0]," ",$words[2],"\n";
	}
}

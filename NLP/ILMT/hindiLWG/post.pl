@lines = <STDIN>;
chomp(@lines);

for $line (@lines)
{
	@words = split(/\t/,$line);
	if($words[1] eq "<Sentence")
	{
		print "</Sentence>\n\n";
		print "<Sentence id=\"1\">\n";
	}
	elsif($words[1] eq "id=\"1\">")
	{
		print "<Sentence id=\"1\">\n";
	}
	elsif($words[1] eq "")
	{
		print "</Sentence>\n\n";
	}
	else
	{
		print $line,"\n";
	}
}

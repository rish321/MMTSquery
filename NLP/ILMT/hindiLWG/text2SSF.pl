@lines = <STDIN>;
chomp(@lines);

for $line (@lines)
{
	print "<Sentence id=\"1\">\n";
	@words = split(/ /,$line);
	for $word (@words)
	{
		print "1\t$word\t<unk>\n";
	}
	print "</Sentence>\n\n";
}

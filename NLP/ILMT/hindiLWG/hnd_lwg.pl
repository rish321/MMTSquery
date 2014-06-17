#!/usr/local/bin/perl

$tmp=$ARGV[0];
chomp($tmp);
open(FILE1,$tmp);

$read1 = <FILE1>;

$flag=0;
$i=0;

@sentences;

while($read1 ne "")
{
	if($read1 =~ /^\s*$/)
	{
		$words_no = @sentences;
		for($i=0; $i < $words_no; $i++)
		{
			$flag = 0;
			for($k = $i+1; $k < $words_no; $k++)
			{
				if($sentences[$k] =~ /PSP/)
				{
					$flag = 1;
				}
				else
				{
					last;
				}
			}
			if($flag == 1)
			{
				print $sentences[$i];
			}
			for($j = $i+1; $j < $k; $j++)
			{
				if($sentences[$i] =~ /^\s*$/)
				{
					print $sentences[$j];
				}
				else
				{
					print "\t",$sentences[$j];
				}
				$i++;
			}
			if($flag == 1)
			{
				print "\n";
			}
			elsif ($sentences[$i] =~ /.*\_VM\_.*/ or $sentences[$i] =~ /.*\_VJJ\_.*/ or $sentences[$i] =~ /VNN/ or $sentences[$i] =~ /.*\_VRB\_.*/)
			{
				print $sentences[$i];
				$bkp = $i;
				for($j=$i+1;$j<$words_no;$j++)
				{
					if ($sentences[$j] =~ /NEG/ or $sentences[$j] =~ /.*\_VJJ\_.*/ or $sentences[$j] =~ /.*\_VAUX\_.*/ or $sentences[$j] =~ /.*\_VNN\_.*/ or $sentences[$j] =~ /.*\_VRB\_.*/ or $sentences[$j] =~ /_cAha$/ or $sentences[$j] =~ /_jAna$/ or $sentences[$j] =~ /_ho$/ or $sentences[$j] =~ /_xe$/ or $sentences[$j] =~ /_laga$/ or $sentences[$j] =~ /_pada$/)
					{
						if($sentences[$j] =~ /NEG/)
						{
						}
						else
						{
							print "\t",$sentences[$j];
							$sentences[$j] = "";
						}
					}
					else
					{
						last;
					}
				}
				print "\n";
			}
			else
			{
				if($sentences[$i] !~ /^\s*$/)
				{
					print $sentences[$i],"\n";
				}
			}
		}
		print "\n";
		@sentences=();
		$i=0;
	}
	else
	{
		chomp($read1);
		@array=split(/\s+/,$read1);
		$sentences[$i] = $array[0]."_".$array[2]."_".$array[1];
		$i++;
	}
	$read1 = <FILE1>;
}
print "\n";

close (FILE1);

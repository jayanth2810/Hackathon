<?php 
$f=fopen("user1.php","w");
$f1=fopen("sample1.php","a+");
$f2=fopen("merchant1.php","a+");

$set2=file_get_contents("sample1.php");
$set3=explode("<br>", $set2);
$set4= explode("_",$set3[sizeof($set3)-2]);

$set=$_GET['val'];
$set1=explode("_",$set);
$set5=$set4[4]-$set1[4];
if($set5<0) echo "negative";
else
	echo "positive";
$mydate=getdate(date("U"));
fwrite($f1, $set1[4]."_".$set1[6]."_"."$mydate[weekday], $mydate[month] $mydate[mday], $mydate[year]"."_".$set4[4]."_".$set5.'<br>');
fwrite($f2, $set1[6]."_"."98765"."_"."$mydate[weekday], $mydate[month] $mydate[mday], $mydate[year]"."_"."PersonA"."_".$set1[4]."\n");

//echo " ".$set;
fwrite($f,$set);
fclose($f);
fclose($f1);
fclose($f2);

?>
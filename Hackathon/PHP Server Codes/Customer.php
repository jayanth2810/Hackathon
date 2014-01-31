
<html>
<head>
<link rel="stylesheet" type="text/css" href="merchant.css">
</head>
<body>
<div id="table">Customer Transactions</div>
<table id="t1" border="1">
<th>Merchant id</th><th>Current Amount</th><th>Date of Trans</th><th>Total</th><th>Remaining</th>
<?php 

	$f=fopen("sample1.php","r");
    $set=fgets($f) ;
     
	$set1=explode("<br>", $set);
	$count=sizeof($set1);
	$i=0;
	while($count)
	{
 	    $set2=explode("_",$set1[$i]);
		echo '<tr><td>'.$set2[1].'</td><td>'.$set2[0].'</td><td>'.$set2[2].'</td><td>'.$set2[3].'</td><td>'.$set2[4].'</td>';
		
		echo '</tr>';
		$count=$count-1;
		$i=$i+1;
	}
?>
</table>

</body>
</html>
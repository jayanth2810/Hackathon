
<html>
<head>
<link rel="stylesheet" type="text/css" href="merchant.css">
</head>
<body>
<div id="table">Merchant Transactions</div>
<table id="t1" border="1">
<th>Merchant id</th><th>CustomerId</th><th>Date</th><th>Name</th><th>Debited</th><th>Delayed</th><th>Address</th>
<?php 

	$f=fopen("merchant1.php","r");
    while ($set=fgets($f)) 
     {
	$set1=explode("_", $set);
 	
		echo '<tr><td>'.$set1[0].'</td><td>'.$set1[1].'</td><td>'.$set1[2].'</td><td>'.$set1[3].'</td><td>'.$set1[4].'</td>';
		if(is_null($set1[5])==false)
		{
			echo '<td>'.$set1[5].'</td>'.'<td>'.$set1[6].'</td>';
		}
		echo '</tr>';
	}
?>
</table>

</body>
</html>
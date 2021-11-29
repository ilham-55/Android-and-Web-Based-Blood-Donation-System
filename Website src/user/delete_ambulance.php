<?php

include ('db_connect.php');

$id=$_GET['id'];

$delete=mysqli_query($con,"DELETE FROM ambulance_list  WHERE id='$id'");

if($delete)
{
	echo "<script>alert('Ambulance Deleted!')</script>";
	echo "<script>window.open('ambulance.php','_self')</script>";
}

else
{
	echo "<script>alert('Failed!')</script>";
	echo "<script>window.open('ambulance.php','_self')</script>";
}


?>
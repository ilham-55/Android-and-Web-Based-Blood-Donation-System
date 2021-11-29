<?php

include ('db_connect.php');

$id=$_GET['id'];

$delete=mysqli_query($con,"DELETE FROM doctor_list  WHERE id='$id'");

if($delete)
{
	echo "<script>alert('Doctor Deleted!')</script>";
	echo "<script>window.open('doctors.php','_self')</script>";
}

else
{
	echo "<script>alert('Failed!')</script>";
	echo "<script>window.open('doctors.php','_self')</script>";
}


?>
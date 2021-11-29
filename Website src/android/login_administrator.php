<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
 $userCell = $_POST['cell'];

 $password1 = $_POST['password'];
 
 $password=md5($password1);
 
 
 //Creating sql query
 

 $sql = "SELECT * FROM administrator WHERE mobile='$userCell' AND password='$password'";

 //importing dbConnect.php script 
 require_once('db_connect.php');
 
 //executing query
 $result = mysqli_query($con,$sql);
 
 //fetching result
 $check = mysqli_fetch_array($result);
 
 //if we got some result 
 if(isset($check)){
 //displaying success 
 echo "success";
 }
 
 else{
 //displaying failure
 
 echo "failure";
 }
 
 //close db connect
 mysqli_close($con);
 }

?>
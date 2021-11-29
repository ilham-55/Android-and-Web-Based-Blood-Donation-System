<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 



 $getgender =  $_POST['gender'];
 $getname =  $_POST['name'];
 $getcell =  $_POST['cell'];
 $getlocation =$_POST['location'];
  $getdonatedate =$_POST['donate_date'];
 
  $password1 = $_POST['password']; //normal password
  
  $password=md5($password1);       //encrypted password for security
 
 
 //importing dbConnect.php script 
 require_once('db_connect.php');
 
  
   $sql="UPDATE users SET name ='$getname',gender='$getgender',division='$getlocation',password='$password',last_donate_date='$getdonatedate' WHERE mobile ='$getcell' ";
 
  
     

if(mysqli_query($con,$sql))

{
 
     echo "success";
     
} 
 
 else
 {
     echo "failure";
 }
 
 mysqli_close($con);
 
 
}


?>
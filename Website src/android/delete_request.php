<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 



 $getid =  $_POST['id'];
 
 //importing dbConnect.php script 
 require_once('db_connect.php');
 

  $sql= "DELETE FROM blood_request  WHERE id='$getid'";
  
     

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
<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 


  $id = $_POST['id'];
  $status = intval($_POST['status']);
  $address = $_POST['address'];
  $division = $_POST['division'];
  $contact = $_POST['contact'];
  $blood_group = $_POST['blood_group'];
  $type=$_POST['type'];
  $bag = $_POST['bag'];
  $user_cell = $_POST['user_cell'];
  
 //importing dbConnect.php script 
 require_once('db_connect.php');
 
  
  
  // $sql="UPDATE blood_request SET blood_group ='$blood_group',division='$division',address='$address',bag='$bag',contact='$contact',type='$type',status=$status WHERE id ='$id' ";
 
   $sql="UPDATE blood_request SET blood_group ='$blood_group',division='$division',address='$address',bag='$bag',contact='$contact',type='$type',status=$status WHERE id ='$id' ";
 
     

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
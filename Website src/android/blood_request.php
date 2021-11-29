<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
  $address = $_POST['address'];
  $division = $_POST['division'];
  $contact = $_POST['contact'];
  $blood_group = $_POST['blood_group'];
  $type=$_POST['type'];
  $bag = $_POST['bag'];
  $user_cell = $_POST['user_cell'];
  
   $sub_area = $_POST['sub_area'];
   $latitude = $_POST['latitude'];
   $longitude = $_POST['longitude'];
   
   $status=0;
  

 //set default time zone
  date_default_timezone_set("Asia/Dhaka");
  
  //get current time
  $current_time=date("h:i A");
  
  //get current date
  $current_date=date("d/m/Y");
  
  

 //importing dbConnect.php script 
 require_once('db_connect.php');
 

 //Creating sql query
  $sql = "Insert into blood_request (blood_group,address,division,contact,type,bag,user_cell,date,time,sub_area,latitude,longitude,status) values('$blood_group','$address','$division','$contact','$type','$bag','$user_cell','$current_date','$current_time','$sub_area','$latitude','$longitude',$status)";
  
 
  
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
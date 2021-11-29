<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
  
   $name = $_POST['doctor_name'];
   $details = $_POST['doctor_details'];
   $chamber = $_POST['chamber'];
   $address = $_POST['address'];
   $division = $_POST['division'];
   $contact = $_POST['contact']; 
   $specialist = $_POST['specialist']; 
   $hospital_id = $_POST['hospital_id'];
   

  

 //importing dbConnect.php script 
 require_once('db_connect.php');
 

 //Creating sql query
 // $sql = "UPDATE doctor_list SET hospital_id='$hospital_id' WHERE id='$dr_id'";
  
  $sql = "INSERT INTO doctor_list  (name,specialist,mobile,chamber,address,division,details,hospital_id) VALUES ('$name','$specialist','$contact','$chamber','$address','$division','$details','$hospital_id')";
 
  
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
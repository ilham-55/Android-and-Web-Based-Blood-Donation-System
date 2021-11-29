<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 
  $name = $_POST['name'];
  $gender = $_POST['gender'];
  $cell = $_POST['cell'];
  $blood_group = $_POST['blood_group'];
 // $sub_area=$_POST['sub_area'];
  $location = $_POST['location'];
  $password1 = $_POST['password']; //normal password
  
  $password=md5($password1);       //encrypted password for security
  

  

 //importing dbConnect.php script 
 require_once('db_connect.php');
 

 //Creating sql query
  $sql = "Insert into administrator (name,password,mobile,division,blood_group,gender) values('$name','$password','$cell','$location','$blood_group','$gender')";
  
  $result =  mysqli_query($con,"SELECT mobile FROM administrator where mobile='$cell'");
  
  $num_rows =mysqli_num_rows($result);


  if($num_rows>0)
	
{
echo  "exists";	
}
  
  else
  
  {
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
 
     
 
}

?>
<?php

//get values

if($_SERVER['REQUEST_METHOD']=='POST'){
 //Getting values 

 $review =  $_POST['review'];
 $rating =  $_POST['rating'];
 $id =  $_POST['id'];
 $dr_cell =  $_POST['dr_cell'];
 $user_cell =  $_POST['cell'];
 
  //set default time zone
  date_default_timezone_set("Asia/Dhaka");
  
  //get current time
  $current_time=date("h:i A");
  
  //get current date
  $current_date=date("d/m/Y");
  
 
 //importing dbConnect.php script 
 require_once('db_connect.php');
 
 
 
 
   $result =  mysqli_query($con,"SELECT * FROM review where dr_id='$id' AND user_cell='$user_cell'");
  $num_rows =mysqli_num_rows($result);
  
 if($num_rows>0)
	
{
  
  $sql="UPDATE review SET review ='$review', rating='$rating' where dr_id='$id' AND user_cell='$user_cell'";

}

else
{
      //Creating sql query
  $sql = "INSERT INTO review (review,rating,dr_id,dr_cell,user_cell) values ('$review','$rating','$id','$dr_cell','$user_cell')";
  

    
}
  


  


     

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
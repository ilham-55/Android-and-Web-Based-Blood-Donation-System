<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 $get_city= $_GET['city'];
 
 
  //set default time zone
  date_default_timezone_set("Asia/Dhaka");
  
  //get current time
  $current_time=date("h:i A");
  
  //get current date
  $current_date=date("d/m/Y");
  
 
 require_once('db_connect.php');
 
  $sql = "SELECT * FROM blood_request ORDER BY id DESC";
 
 if($get_text=='aplus')
 {
     
      $sql = "SELECT * FROM blood_request WHERE blood_group='A+' ORDER BY id DESC";
     if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='A+' AND division='$get_city' ORDER BY id DESC"; 
     }
     
    
     
 }
 
 if($get_text=='aminus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='A-' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='A-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bplus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='B+' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='B+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bminus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='B-' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='b-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abplus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='AB+' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='AB+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abminus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='AB-' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='AB-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 
 if($get_text=='oplus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='O+' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='O+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='ominus')
 {
     $sql = "SELECT * FROM blood_request WHERE blood_group='O-' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM blood_request WHERE blood_group='O-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 
 

 
 
 
//   if(strlen($get_text)>0)
//  {

//  //$sql = "SELECT * FROM blood_donor WHERE name LIKE  '%$get_text%' OR blood_group='".$get_text."'  OR location='".$get_text."'  ORDER BY id DESC";
 
//  $sql = "SELECT * FROM blood_donor WHERE blood_group LIKE  '%".$get_text."%' OR gender='".$get_text."'  OR location='".$get_text."'  ORDER BY id DESC";
 
//  }


 $r = mysqli_query($con,$sql);
 
// $res = mysqli_fetch_array($r);
 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		    
		                "id"=>$res['id'],
		                "blood_group"=>$res['blood_group'],
		            	"division"=>$res['division'],
                        "address"=>$res['address'],
                        "bag"=>$res['bag'],
                        "contact"=>$res['contact'],
                        "type"=>$res['type'],
                        "date"=>$res['date'],
                        "time"=>$res['time'],
                         "status"=>$res['status']
                
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
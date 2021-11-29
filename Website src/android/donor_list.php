<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 $get_city= $_GET['city'];
 
 require_once('db_connect.php');
 
  $sql = "SELECT * FROM users ORDER BY id DESC";
 
 if($get_text=='aplus')
 {
     
      $sql = "SELECT * FROM users WHERE blood_group='A+' ORDER BY id DESC";
     if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='A+' AND division='$get_city' ORDER BY id DESC"; 
     }
     
    
     
 }
 
 if($get_text=='aminus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='A-' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='A-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bplus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='B+' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='B+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bminus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='B-' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='b-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abplus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='AB+' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='AB+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abminus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='AB-' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='AB-' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 
 if($get_text=='oplus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='O+' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='O+' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='ominus')
 {
     $sql = "SELECT * FROM users WHERE blood_group='O-' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM users WHERE blood_group='O-' AND division='$get_city' ORDER BY id DESC"; 
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
		                "name"=>$res['name'],
		            	"gender"=>$res['gender'],
                        "location"=>$res['division'],
                        "cell"=>$res['mobile'],
                        "blood_group"=>$res['blood_group'],
                        "donate_date"=>$res['last_donate_date'],
                        "image"=>$res['image']
                
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
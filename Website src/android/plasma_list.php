<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['blood'];
 $get_city= $_GET['city'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
 
 
 
 
  if($get_text=='aplus')
 {
     
     $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='A+' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
     
    
     
 }
 
 if($get_text=='aminus')
 {
    $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='A-' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bplus')
 {
      $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='B+' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='bminus')
 {
     $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='b-' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abplus')
 {
     $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='AB+' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='abminus')
 {
    $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='AB-' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 
 if($get_text=='oplus')
 {
    $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='O+' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='ominus')
 {
    $sql = "SELECT * FROM plasma_donor ORDER BY id ASC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM plasma_donor WHERE blood_group='O-' AND address Like '%$get_city%' ORDER BY id DESC"; 
     }
 }
 
 
 
 
 

 $r = mysqli_query($con,$sql);
 
 $result = array();

 while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		                "name"=>$res['name'],
		                "address"=>$res['address'],
                    	"cell"=>$res['cell'],
                    	"blood_group"=>$res['blood_group']
                       
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
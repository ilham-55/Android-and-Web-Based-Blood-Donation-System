<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 $get_city= $_GET['city'];
 
/* 
  //set default time zone
  date_default_timezone_set("Asia/Dhaka");
  
  //get current time
  $current_time=date("h:i A");
  
  //get current date
  $current_date=date("d/m/Y");
  
 */
 require_once('db_connect.php');
 
  $sql = "SELECT * FROM hospital_list ORDER BY id";
 
 if($get_text=='bb')
 {
     
      $sql = "SELECT * FROM hospital_list WHERE category='Blood Bank' ORDER BY id DESC";
     if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Blood Bank' AND division='$get_city' ORDER BY id DESC"; 
     }
     
    
     
 }
 
 if($get_text=='bps')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Burn & Plastic Surgery' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Burn & Plastic Surgery' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='ch')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Cancer Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Cancer Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='cgh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Cardiac & General Hospital' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Cardiac & General Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='chmch')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Child Health & Mother Care Hospital' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Child Health & Mother Care Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='c')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Clinic' ORDER BY id DESC"; 
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Clinic' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 
 if($get_text=='dh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Dental Hospital' ORDER BY id DESC";
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Dental Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 
 if($get_text=='dc')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Diagnostic Centre' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Diagnostic Centre' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='dpcc')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Diagnostic & Patient Consultation Center' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Diagnostic & Patient Consultation Center' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='enth')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='ENT Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='ENT Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='eh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Eye Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Eye Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='glh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Gastro Liver Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Gastro Liver Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='gh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='General Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='General Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='gdh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='General & Dental Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='General & Dental Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='kh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Kidney Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Kidney Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='kdh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Kidney & Dialysis Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Kidney & Dialysis Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='lscsh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Laser Skin & Cosmetic Surgery Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Laser Skin & Cosmetic Surgery Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='mhcc')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Mental Health Care Center' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Mental Health Care Center' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='mdath')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Mental & Drug Abuse Treatment Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Mental & Drug Abuse Treatment Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
  if($get_text=='oph')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Orthopedic ( Pangu ) Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Orthopedic ( Pangu ) Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 if($get_text=='ph')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Paralysis Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Paralysis Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 if($get_text=='pcc')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Patient Consultation Center' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Patient Consultation Center' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 if($get_text=='pnh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Pediatric & Neonatal Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Pediatric & Neonatal Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 if($get_text=='pyh')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Physiotherapy Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Physiotherapy Hospital' AND division='$get_city' ORDER BY id DESC"; 
     }
 }
 if($get_text=='th')
 {
     $sql = "SELECT * FROM hospital_list WHERE category='Tuberculosis Hospital' ORDER BY id DESC";
     
      if(strlen($get_city)>1)
     {
         $sql = "SELECT * FROM hospital_list WHERE category='Tuberculosis Hospital' AND division='$get_city' ORDER BY id DESC"; 
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
		                "name"=>$res['name'],
		            	"mobile"=>$res['mobile'],
		            	"address"=>$res['address'],
                        "division"=>$res['division'],
                        "website"=>$res['website'],
                        "category"=>$res['category']
                
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
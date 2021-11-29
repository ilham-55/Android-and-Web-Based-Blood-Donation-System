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
 
  $sql = "SELECT * FROM doctor_list ORDER BY id ASC";
 
 
 
 if($get_text=='as')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Anesthesiology Specialist' ORDER BY id ASC";
     
 }
 
 if($get_text=='bs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Burn Specialist' ORDER BY id ASC";
 }
 
 if($get_text=='bss')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Breast Surgeon Specialist' ORDER BY id ASC";
     
 }
 
 if($get_text=='chs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Cardiology Heart Specialist' ORDER BY id ASC"; 
     
 }
 
 if($get_text=='cs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Cancer Specialist' ORDER BY id ASC"; 
 }
 
 
 if($get_text=='cts')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Cardiovascular Thoracic Surgeon' ORDER BY id ASC";
 }
 
 if($get_text=='cas')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Chest Asthma Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='cpc')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Child Pediatric Cardiology' ORDER BY id ASC";
     
 }
  if($get_text=='css')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Colorectal Surgery Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='ds')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Dental Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='des')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Dermatology Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='dis')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Diabetes Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='es')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Eye Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='ents')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='ENT Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='gos')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Gynaecology Obstetrics Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='gs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Gastroenterology Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='mhcc')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Mental Health Care Center' ORDER BY id ASC";
     
 }
  if($get_text=='hs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Haematology Specialist' ORDER BY id ASC";
     
 }
  if($get_text=='is')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Infertility Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='ks')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Kidney Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='ls')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Liver Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='ms')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Medicine Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='ns')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Neuromedicine Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='nss')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Neuro Surgeons Specialist' ORDER BY id ASC";
     
 }
 if($get_text=='nds')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Nutritionist & Diet Specialist' ORDER BY id ASC";
     
     
 }if($get_text=='os')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Orthopaedics Specialist' ORDER BY id ASC";
     
     
 }if($get_text=='pms')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Pain Management Specialist' ORDER BY id ASC";
     
     
 }if($get_text=='ps')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Pediatric Specialist' ORDER BY id ASC";
     
     
 }if($get_text=='pys')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Psychologist Specialist' ORDER BY id ASC";
     
      
 }
if($get_text=='rs')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Rheumatology Specialist' ORDER BY id ASC";
     
     
 }if($get_text=='us')
 {
     $sql = "SELECT * FROM doctor_list WHERE specialist='Urology Specialist' ORDER BY id ASC";
     
      
 } 

 
 
 
 /* if(strlen($get_text)>0)
 {


 
 $sql = "SELECT * FROM doctor_list WHERE name LIKE  '%".$get_text."%'";
 
 }*/


 $r = mysqli_query($con,$sql);
 
// $res = mysqli_fetch_array($r);
 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		    
		                "id"=>$res['id'],
		                "name"=>$res['name'],
		            	"specialist"=>$res['specialist'],
                        "mobile"=>$res['mobile'],
                        "chamber"=>$res['chamber'],
                        "address"=>$res['address'],
                        "division"=>$res['division'],
                        "details"=>$res['details']
                
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
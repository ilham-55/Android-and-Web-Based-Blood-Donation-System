<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM ambulance_list ORDER BY id ASC";
 
 
  if(strlen($get_text)>0)
 {

 $sql = "SELECT * FROM ambulance_list WHERE name LIKE  '%$get_text%' OR  address LIKE  '$get_text'   ORDER BY id ASC";
 
 
 }
 
 $r = mysqli_query($con,$sql);
 
 $result = array();

 while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		                "name"=>$res['name'],
		                "address"=>$res['address'],
		            	"phones"=>$res['phones'],
                    	"mobile"=>$res['mobile']
                       
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
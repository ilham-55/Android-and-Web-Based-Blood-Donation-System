<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM doctor_list ORDER BY id DESC";
 
 
  if(strlen($get_text)>0)
 {

 $sql = "SELECT * FROM doctor_list WHERE name LIKE  '%$get_text%' ORDER BY id ASC";
 
 }
 
 $r = mysqli_query($con,$sql);
 
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
<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM hospital_list ORDER BY name ASC";
 
 

 $r = mysqli_query($con,$sql);
 
// $res = mysqli_fetch_array($r);
 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		                "id"=>$res['id'],
		                "name"=>$res['name']
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
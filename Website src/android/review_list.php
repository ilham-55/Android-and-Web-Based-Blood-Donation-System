<?php 

//get values
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $dr_id = $_GET['dr_id'];
 
 
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM review WHERE dr_id='".$dr_id."' ORDER BY review_id DESC";
 
 $r = mysqli_query($con,$sql);
 
// $res = mysqli_fetch_array($r);
 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		    
		                "id"=>$res['review_id'],
		                "review"=>$res['review'],
		                "rating"=>$res['rating']
                       
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
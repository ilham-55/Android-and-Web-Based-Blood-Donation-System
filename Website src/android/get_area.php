<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $getdivision = $_GET['division'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT sub_area FROM area WHERE division_name='".$getdivision."'  ORDER BY sub_area ASC";
 
 

 $r = mysqli_query($con,$sql);
 
// $res = mysqli_fetch_array($r);
 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		                "sub_area"=>$res['sub_area']
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
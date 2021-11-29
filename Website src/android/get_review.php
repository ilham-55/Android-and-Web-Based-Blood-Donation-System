<?php 
 
 //get values
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $id = $_GET['id'];
 $user_cell = $_GET['user_cell'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM review WHERE dr_id='".$id."' AND user_cell='$user_cell'";
 
 
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
                        "id"=>$res['review_id'],
		                "review"=>$res['review'],
		                "rating"=>$res['rating']
                       
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
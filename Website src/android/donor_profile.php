<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 
 $usercell = $_GET['cell'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM users WHERE mobile='".$usercell."'";
 

 
 
 $r = mysqli_query($con,$sql);
 
 $res = mysqli_fetch_array($r);
 
 $result = array();
 
 array_push($result,array(
 "name"=>$res['name'],
 "gender"=>$res['gender'],
 "cell"=>$res['mobile'],
 "location"=>$res['division'],
  "image"=>$res['image'],
  "donate_date"=>$res['last_donate_date']
  
 )
 );
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
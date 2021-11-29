<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_text= $_GET['text'];
 
 require_once('db_connect.php');
 
 $sql = "SELECT * FROM bloodbank_list ORDER BY id ASC";
 
 
  if(strlen($get_text)>0)
 {

 $sql = "SELECT * FROM bloodbank_list WHERE name LIKE  '%$get_text%' OR  address LIKE  '%$get_text%'   ORDER BY id ASC";
 
 
 }
 
 $r = mysqli_query($con,$sql);
 
 $result = array();

 while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		                "name"=>$res['name'],
		                "category"=>$res['category'],
		                "address"=>$res['address'],
                    	"mobile"=>$res['mobile']
                       
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
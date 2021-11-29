<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_cell= $_GET['cell'];

  require_once('db_connect.php');
 
 $sql = "SELECT * FROM blood_request WHERE user_cell='$get_cell' ORDER BY id DESC"; 
 
 

 $r = mysqli_query($con,$sql);
 

 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		    
		                "id"=>$res['id'],
		                "blood_group"=>$res['blood_group'],
		            	"division"=>$res['division'],
                        "address"=>$res['address'],
                        "bag"=>$res['bag'],
                        "contact"=>$res['contact'],
                        "type"=>$res['type'],
                        "date"=>$res['date'],
                        "time"=>$res['time'],
                        "sub_area"=>$res['sub_area'],
                        "status"=>$res['status']
                
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
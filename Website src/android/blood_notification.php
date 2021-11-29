<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $get_cell= $_GET['cell'];

  require_once('db_connect.php');
  
   $get_info =mysqli_query($con,"SELECT division,sub_area FROM users where mobile ='$get_cell'");
    
   while($r = mysqli_fetch_array($get_info))
  {
  
    $div=$r['division'];
    $area=$r['sub_area'];

  }
  
 
 
 $sql = "SELECT * FROM blood_request WHERE sub_area='$area' AND division='$div'"; 
 
 

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
 
 ?>
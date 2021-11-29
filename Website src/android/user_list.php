<?php 
 
 if($_SERVER['REQUEST_METHOD']=='GET'){
 

  $get_text= $_GET['text'];
 require_once('db_connect.php');
 
  $sql = "SELECT * FROM users ORDER BY id DESC";
 
 if(strlen($get_text)>0)
 {

 $sql = "SELECT * FROM users WHERE name LIKE  '%$get_text%' OR mobile LIKE  '%$get_text%' OR division LIKE  '%$get_text%' ";

 }

 
 $r = mysqli_query($con,$sql);
 

 
 $result = array();


while($res = mysqli_fetch_array($r))
        {
		
		//Pushing msg and date in the blank array created 
		array_push($result,array(
		              
		              	"id"=>$res['id'],
		            	"name"=>$res['name'],
		            	"cell"=>$res['mobile'],
		            	"location"=>$res['division']
		            
                    
                        
                    
                        
		));
	}
 
 echo json_encode(array("result"=>$result));
 
 mysqli_close($con);
 
 }
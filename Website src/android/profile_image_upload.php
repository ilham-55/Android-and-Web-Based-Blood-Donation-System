<?php
$target_dir = "profile_image/";
$temp = explode(".", $_FILES["file"]["name"]);
$newfilename =round(microtime(true)) . '.' . end($temp);
 
$target_file_name = $target_dir .$newfilename;

 
    
$response = array();

 $cell =$_POST['cell']; //get user cell
 $image =$newfilename;
 
 
 

 //importing db_connect.php script 
 require_once('db_connect.php');
 
  $sql = "UPDATE users SET image='$image' WHERE mobile='$cell'";
 
 
// Check if image file is a actual image or fake image
if (isset($_FILES["file"])) 
{
   if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_file_name)) 
   {
       
     if(mysqli_query($con,$sql))
     {
     $success = true;
     $message = "Successfully Uploaded";
     }
     else
     {
         $success = false;
         $message = "Error";  
     }
   }
   else 
   {
      $success = false;
      $message = "Error while uploading";
   }
}
else 
{
      $success = false;
      $message = "Required Field Missing";
}
$response["success"] = $success;
$response["message"] = $message;
echo json_encode($response);

?>
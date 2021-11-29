<?php
session_start();
if (isset($_SESSION['email'])) {
    header("location:admin/dashboard.php");

} else {
    echo "";
}


$submit = "";

$status = "OK";
$msg = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $email = $_POST['email'];
    $password =md5( $_POST['password']);


    if (empty($email)) {
        $msg .= "<center><font  size='4px' face='Verdana' size='1' color='red'>Please Enter Your Email. </font></center>";


        $status = "NOTOK";

    }


    if (empty($password)) {
        $msg .= "<center><font  size='4px' face='Verdana' size='1' color='red'>Please Enter Your password.";

        $status = "NOTOK";
    }

    if ($status == "OK") {

        include('db_connect.php');


//   include('db_connect.php');

        $result = mysqli_query($con, "SELECT * FROM admin WHERE email='$email' and password='$password'");

        $result2 = mysqli_query($con, "SELECT * FROM users WHERE mobile='$email' and password='$password'"); //for users


        $count = mysqli_num_rows($result);
        $count2 = mysqli_num_rows($result2);

        if ($count == 1) {

            $row = mysqli_fetch_array($result);

            $_SESSION['email'] = $row['email'];
          

            header("location:admin/dashboard.php");
        } else if ($count2 == 1) {

            $row = mysqli_fetch_array($result2);

            $_SESSION['email'] = $row['mobile'];
           


            header("location:user/dashboard.php");
        } else {


            $msg = "<center><font  size='4px' face='Verdana' size='1' color='red'>Wrong ID or Password !!!.
";

            echo "<div class='alert-danger' align='center'>" . $msg . "</div";

        }
    } else {
        echo "<div class='alert-danger' align='center'>" . $msg . "</div";
    }

}


?>




<!DOCTYPE html>
<html lang="en">
<head>
	<title>Blood Donation</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body style="background-color: #666666;">
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<form class="login100-form validate-form"  action="index.php" method="post">
					<span class="login100-form-title p-b-43">
						Blood Donation</br>Login
						
					</span>
					
					
					<div class="wrap-input100" >
						<input class="input100" type="text" name="email">
						<span class="focus-input100"></span>
						<span class="label-input100">Email or Phone</span>
					</div>
					
					
					<div class="wrap-input100 validate-input" data-validate="Password is required">
						<input class="input100" type="password" name="password">
						<span class="focus-input100"></span>
						<span class="label-input100">Password</span>
					</div>

					
			

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Login
						</button>
					</div>
					
					

				</form>

				<div class="login100-more" style="background-image: url('images/bg-01.jpg');">
				</div>
			</div>
		</div>
	</div>
	
	

	
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>

</body>
</html>
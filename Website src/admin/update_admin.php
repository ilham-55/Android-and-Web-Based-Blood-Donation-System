<?php
session_start();
if (isset($_SESSION['email']))
    echo " ";
else
    header("location:../index.php");


if ($_SERVER["REQUEST_METHOD"] == "GET") {
    $getid = $_SESSION['email'];
    include('db_connect.php');

    if (!empty($getid)) {
        $query = mysqli_query($con, "SELECT * FROM admin WHERE email='$getid'");
        $row = mysqli_fetch_assoc($query);
    }
}


$id = $name = $email = $password = '';
$idErr = $nameErr = $emailErr = $passwordErr = '';

if ($_SERVER["REQUEST_METHOD"] == "POST") {


    $id = $_POST['id'];
    $name = $_POST['name'];
    $email = $_POST['email'];
    $password = $_POST['password'];


    $row['id'] = $id;
    $row['name'] = $name;
    $row['email'] = $email;

    $row['password'] = $password;


    $status = "OK";
    $msg = "";

    if (empty($id)) { // checking your name
        $idErr = "* Please Enter Student ID.<BR>";
        $status = "NOTOK";
    }


    if (empty($name)) { // checking your name
        $nameErr = "* Please Enter Name.<BR>";
        $status = "NOTOK";
    }

    if (!stristr($email, "@") or !stristr($email, ".")) {
        $emailErr = "* Wrong Email Address!<BR>";
        $status = "NOTOK";
    }


    if (empty($password)) { // checking your name
        $passwordErr = "* Please Enter Password.<BR>";
        $status = "NOTOK";
    }


    if (strlen($password) < 4) { // checking your name
        $passwordErr = "* Password Length Minimum 4 digit !.<BR>";
        $status = "NOTOK";
    }


    if ($status == "OK") {

        include('db_connect.php');


        if (mysqli_query($con, "UPDATE  admin SET name='$name',email='$email',password='$password' WHERE id='$id'")) {
            echo '<script type="text/javascript">';
            echo 'setTimeout(function () { swal("SUCCESS!","Updated Sucessfully!","success");';
            echo '}, 500);</script>';


            echo '<script type="text/javascript">';
            echo "setTimeout(function () { window.open('dashboard.php','_self')";
            echo '}, 1500);</script>';


        } else {// display the error message
            echo '<script type="text/javascript">';
            echo 'setTimeout(function () { swal("ERROR!","Something Wrong!","error");';
            echo '}, 500);</script>';

//  echo "<script>window.open('view_student.php','_self')</script>";
        }

    }
}

?>


<!doctype html>
<html lang="en">
<head>

    <style>
        .error {
            font-family: sans-serif;
            font-style: italic;
            color: #FF0000;
        }


        /* Paste this css to your style sheet file or under head tag */
        /* This only works with JavaScript,
        if it's not present, don't show loader */
        .no-js #loader {
            display: none;
        }

        .js #loader {
            display: block;
            position: absolute;
            left: 100px;
            top: 0;
        }

        .se-pre-con {
            position: fixed;
            left: 0px;
            top: 0px;
            width: 100%;
            height: 100%;
            z-index: 9999;
            background: url(images/loader-64x/Preloader_3.gif) center no-repeat #fff;
        }

        #my_button {
            display: inline-block;
            width: 150px;
            height: 50px;
            margin: 2px;
        }

    </style>


    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>
    <script>
        //paste this code under head tag or in a seperate js file.
        // Wait for window load
        $(window).load(function () {
            // Animate loader off screen
            $(".se-pre-con").fadeOut("slow");
            ;
        });
    </script>

    <link rel="stylesheet" href="assets/swal2/sweetalert2.min.css" type="text/css"/>

    <meta charset="utf-8"/>
    <link rel="icon" type="image/png" href="assets/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

    <title>Update</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <meta name="viewport" content="width=device-width"/>


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Light Bootstrap Table core CSS    -->
    <link href="assets/css/light-bootstrap-dashboard.css" rel="stylesheet"/>


    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet"/>


    <!--     Fonts and icons     -->
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300' rel='stylesheet' type='text/css'>
    <link href="assets/css/pe-icon-7-stroke.css" rel="stylesheet"/>

</head>
<body>

<div class="se-pre-con"></div> <!--For preloader-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="assets/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/swal2/sweetalert2.min.js"></script>


<div class="wrapper">
    <div class="sidebar" data-color="black" data-image="assets/img/sidebar-5.jpg">

        <!--

            Tip 1: you can change the color of the sidebar using: data-color="blue | azure | green | orange | red | purple"
            Tip 2: you can also add an image using data-image tag

        -->

        <div class="sidebar-wrapper">
            <div class="logo">
                <a href="#" class="simple-text">
                    Admin Panel
                </a>
            </div>

            <ul class="nav">
                <li class="active">
                    <a href="dashboard.php">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>

                <li>
                    <a href="blood_donor.php">
                        <i class="pe-7s-plus"></i>
                        <p>Blood Donor</p>
                    </a>
                </li>


                <li>
                    <a href="hospital.php">
                        <i class="pe-7s-plus"></i>
                        <p>Hospital</p>
                    </a>
                </li>


                <li>
                    <a href="doctors.php">
                        <i class="pe-7s-plus"></i>
                        <p>Doctor List</p>
                    </a>
                </li>

                <li>
                    <a href="ambulance.php">
                        <i class="pe-7s-plus"></i>
                        <p>Ambulance List</p>
                    </a>
                </li>

                <li>
                    <a href="plasma_donor.php">
                        <i class="pe-7s-plus"></i>
                        <p>Plasma Donor</p>
                    </a>
                </li>

                <li>
                    <a href="logout.php">
                        <i class="pe-7s-power"></i>
                        <p>Logout</p>
                    </a>
                </li>

            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-default navbar-fixed">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#navigation-example-2">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Dashboard</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">

                    </ul>
                </div>
            </div>
        </nav>
        <br>

        <form role="form" id="student_info_form" name="form3" method="post" action="update_admin.php">


            <div class="form-group">
                <div class="col-xs-10">
                    <br>
                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-key"></i></span>
                        <input type="text" class="form-control" name="id" value="<?php echo $row['id']; ?>"
                               id="student_id" readonly>


                    </div>
                    <span class="error"> <?php echo $idErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="name" id="name"
                               value="<?php echo $row['name']; ?>" placeholder="Enter Name">

                    </div>

                    <span class="error"> <?php echo $nameErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                        <input type="text" name="email" class="form-control" id="email"
                               value="<?php echo $row['email']; ?>" placeholder="Enter Email">

                    </div>
                    <span class="error"> <?php echo $emailErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        <input type="password" name="password" class="form-control" id="latitude"
                               value="<?php echo $row['password']; ?>" placeholder="Enter Password">

                    </div>


                    <span class="error"> <?php echo $passwordErr; ?></span>


                    <br>
                    <button type="submit" id="btn_submit" class="btn btn-info btn-fill pull-right">Update</button>
                    <div class="clearfix"></div>
                </div>
            </div>

        </form>


        <script type="text/javascript">

            $(document).on('click', '#btn_submit', function (e) {
                e.preventDefault();
                swal({
                    title: "ARE YOU SURE ?",
                    text: "Want to update information ?",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "YES",
                    cancelButtonText: "NO",
                    closeOnConfirm: false,
                    closeOnCancel: false

                }).then(function (e) {
                    $('#student_info_form').submit();
                });
            });


        </script>


    </div>
</div>


</body>

<!--   Core JS Files   -->
<script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

<!--  Checkbox, Radio & Switch Plugins -->
<script src="assets/js/bootstrap-checkbox-radio-switch.js"></script>

<!--  Charts Plugin -->
<script src="assets/js/chartist.min.js"></script>

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js"></script>

<!-- Light Bootstrap Table DEMO methods, don't include it in your project! -->
<script src="assets/js/demo.js"></script>


</html>

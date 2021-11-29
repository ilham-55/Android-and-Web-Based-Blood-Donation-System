<?php
session_start();
if (isset($_SESSION['email']))
    echo " ";
else
    header("location:../index.php");

$name = $division = $address = $cell = $latitude = $longitude = '';
$nameErr = $divisionErr = $addressErr = $districtErr = $cellErr = $latitudeErr = $longitudeErr = '';
if ((isset($_POST['name'])) != "" && (isset($_POST['division'])) != "0" && (isset($_POST['address'])) != "" && (isset($_POST['cell'])) != "" && $_SERVER["REQUEST_METHOD"] == "POST") {


    $name = $_POST['name'];
    $address = $_POST['address'];
    $cell = $_POST['cell'];
    $division = $_POST['division'];
    $website = $_POST['website'];
    $category = $_POST['category'];


    $status = "OK";
    $msg = "";

    if (empty($name)) { // checking your name
        $nameErr = "* Please Enter Hospital Name!.<BR>";
        $status = "NOTOK";
    } else if (empty($division)) { // checking your name
        $divisionErr = "* Please Select Division!<BR>";
        $status = "NOTOK";
    } else if (empty($cell)) { // checking your name
        $cellErr = "* Please Enter Correct Cell!<BR>";
        $status = "NOTOK";
    } else if (empty($address)) { // checking your name
        $addressErr = "* Please Select Division!<BR>";
        $status = "NOTOK";
    }


    if ($status == "OK") {

        include('db_connect.php');


        $result = mysqli_query($con, "SELECT * FROM hospital_list where name='$name'");
        $num_rows = mysqli_num_rows($result);


        if ($num_rows > 0) {
            echo '<script type="text/javascript">';
            echo 'setTimeout(function () { swal("ERROR!","Hospital already exists!","error");';
            echo '}, 500);</script>';
        } else {
            if (mysqli_query($con, "INSERT INTO hospital_list (`name`,`mobile`,`address`,`division`,`website`,`category`) VALUE ('$name','$cell','$address','$division','$website','$category')")) {
                echo '<script type="text/javascript">';
                echo 'setTimeout(function () { swal("Hospital Successfully Added!","Done!","success");';
                echo '}, 500);</script>';
            } else {// display the error message
                echo '<script type="text/javascript">';
                echo 'setTimeout(function () { swal("ERROR!","Something Wrong!","error");';
                echo '}, 500);</script>';
            }

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

    <title>Add Hospital</title>

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

<!-- <div class="se-pre-con"></div> <!--For preloader-->

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
                <li>
                    <a href="dashboard.php">
                        <i class="pe-7s-graph"></i>
                        <p>Dashboard</p>
                    </a>
                </li>

                <li>


                <li>
                    <a href="blood_donor.php">
                        <i class="pe-7s-plus"></i>
                        <p>Blood Donor</p>
                    </a>
                </li>


                <li class="active">
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
                    <a class="navbar-brand" href="#">Add Hospital</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-left">

                    </ul>
                </div>
            </div>
        </nav>
        <br>

        <form role="form" id="student_info_form" name="form3" method="post" action="add_hospital.php">

            <div class="col-xs-10">

                <br><br>
            </div>

            <div class="form-group">
                <div class="col-xs-10">
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="name" placeholder="Enter Hospital Name">

                    </div>

                    <span class="error"> <?php echo $nameErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <select class="form-control" name="division">


                            <option value="0" Selected Disabled>Select Division</option>
                            <option value="Dhaka"> Dhaka</option>
                            <option value="Chittagong"> Chittagong</option>
                            <option value="Rajshahi"> Rajshahi</option>
                            <option value="Sylhet"> Sylhet</option>
                            <option value="Khulna">Khulna</option>
                            <option value="Mymensingh"> Mymensingh</option>
                            <option value="Barishal"> Barishal</option>
                            <option value="Rangpur"> Rangpur</option>


                        </select>

                    </div>

                    <span class="error"> <?php echo $divisionErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="address" id="name"
                               placeholder="Enter Full Address">

                    </div>

                    <span class="error"> <?php echo $addressErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="number" class="form-control" name="cell" id="name" placeholder="Enter Contact Number">

                    </div>

                    <span class="error"> <?php echo $cellErr; ?></span>
                    <br>



                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="website" id="name" placeholder="Enter Website">

                    </div>

                    <span class="error"> <?php echo $latitudeErr; ?></span>
                    <br>


                    <div class="input-group ">
                        <span class="input-group-addon"><i class="fa fa-user"></i></span>
                        <input type="text" class="form-control" name="category" id="name"
                               placeholder="Enter Category">

                    </div>

                    <span class="error"> <?php echo $longitudeErr; ?></span>
                    <br>


                    <br>
                    <button type="submit" id="btn_submit" class="btn btn-info btn-fill pull-right">Submit</button>
                    <div class="clearfix"></div>
                </div>
            </div>

        </form>


        <script type="text/javascript">

            $(document).on('click', '#btn_submit', function (e) {
                e.preventDefault();
                swal({
                    title: "ARE YOU SURE ?",
                    text: "Want to add new hospital ?",
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


<!--  Checkbox, Radio & Switch Plugins -->

<!--  Notifications Plugin    -->
<script src="assets/js/bootstrap-notify.js"></script>

<!--  Google Maps Plugin    -->

<!-- Light Bootstrap Table Core javascript and methods for Demo purpose -->
<script src="assets/js/light-bootstrap-dashboard.js"></script>


</html>

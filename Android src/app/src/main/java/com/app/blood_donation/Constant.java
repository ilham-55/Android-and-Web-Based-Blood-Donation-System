package com.app.blood_donation;

public class Constant {


    public static final String MAIN_URL="http://myproject24.cf/blood";
    public static final String BASE_URL="http://myproject24.cf/blood";


    public static final String DONOR_PROFILE_URL = MAIN_URL+"/android/donor_profile.php?cell=";


    public static final String DELETE_USER_URL = MAIN_URL+"/android/delete_user.php";

    public static final String ALL_USER_LIST_URL = MAIN_URL+"/android/user_list.php";

    public static final String DONOR_PROFILE_UPDATE_URL = MAIN_URL+"/android/update_donor_profile.php";

    public static final String REQUEST_UPDATE_URL = MAIN_URL+"/android/request_update.php";

    public static final String LOAD_PROFILE_IMAGE_URL = MAIN_URL+"/android/profile_image/";

    public static final String REVIEW_LIST_URL = MAIN_URL+"/android/review_list.php?dr_id=";
    public static final String GET_REVIEW_URL = MAIN_URL+"/android/get_review.php?id=";
    public static final String ADD_REVIEW_URL = MAIN_URL+"/android/add_review.php";
    //access db from device
    public static final String LOGIN_URL = MAIN_URL+"/android/login.php";

    //access db from device
    public static final String DELETE_NEWS_URL = MAIN_URL+"/android/delete_news.php";

    public static final String GET_AREA_URL = MAIN_URL+"/android/get_area.php?division=";

    public static final String GET_SPECIALITY_URL = MAIN_URL+"/android/get_speciality.php";
    public static final String GET_HOSPITAL_LIST_URL = MAIN_URL+"/android/get_hospital_list.php";

    public static final String SHOW_DONOR_URL = MAIN_URL+"/android/donor_list.php";
    public static final String DELETE_REQUEST_URL = MAIN_URL+"/android/delete_request.php";

    public static final String SHOW_NEWS_URL = MAIN_URL+"/android/news_list.php";
    public static final String SHOW_HOSPITALS_URL = MAIN_URL+"/android/hospitals_list.php";
    public static final String SHOW_DOCTORSDETAILS_URL = MAIN_URL+"/android/doctors_details_list.php";

    public static final String HOSPITAL_DOCTOR_URL = MAIN_URL+"/android/hospital_doctor.php";

    public static final String DOCTOR_LIST_URL = MAIN_URL+"/android/doctor_list.php";
    public static final String AMBULANCE_LIST_URL = MAIN_URL + "/android/ambulance_list.php";
    public static final String BLOODBANK_LIST_URL = MAIN_URL + "/android/bloodbank_list.php";

    public static final String PLASMA_LIST_URL = MAIN_URL + "/android/plasma_list.php";


    public static final String SHOW_MY_REQUEST_URL = MAIN_URL+"/android/my_request.php";

    public static final String BLOOD_NOTIFICATION_URL = MAIN_URL+"/android/blood_notification.php";

    public static final String SIGNUP_URL = MAIN_URL+"/android/signup.php";

    public static final String BLOOD_REQUEST_URL = MAIN_URL+"/android/blood_request.php";

    public static final String ADD_DR_TOHOSPITAL_URL = MAIN_URL+"/android/add_dr_to_hospital.php";


    public static final String KEY_SUB_AREA = "sub_area";

    //If server response is equal to this that means login is successful
    public static final String SIGNUP_SUCCESS = "success";
    public static final String USER_EXISTS = "exists";


    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_TEST_ID = "test_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_CHAMBER = "chamber";
    public static final String KEY_PRICE = "price";
    public static final String KEY_CELL = "cell";
    public static final String KEY_GENDER = "gender";
    public static final String KEY_CABIN = "cabin";
    public static final String KEY_LOCATION = "location";

    public static final String KEY_LATITUDE = "latitude";

    public static final String KEY_LONGITUDE = "longitude";

    public static final String KEY_REVIEW = "review";
    public static final String KEY_RATING = "rating";


    public static final String KEY_DONATE_DATE = "donate_date";
    public static final String KEY_STATUS = "status";
    public static final String KEY_PROFILE_NOT_COMPLETED = "0";
    public static final String KEY_PROFILE_IMAGE = "image";

    public static final String KEY_AGE = "age";
    public static final String KEY_TYPE= "type";
    public static final String KEY_FILE= "file";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_BLOOD_GROUP = "blood_group";
    public static final String KEY_DATE = "date";

    public static final String KEY_DR_ADDRESS = "dr_address";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_DIVISION = "division";
    public static final String KEY_BAG= "bag";
    public static final String KEY_CONTACT = "contact";
    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_PHONE = "phones";

    public static final String KEY_CATEGORY = "category";
    public static final String KEY_SPECIALIST_DOCTOR= "specialist";
    public static final String KEY_WEBSITE = "website";
    public static final String KEY_DETAILS_DOCTOR = "details";

    public static final String KEY_USER_CELL= "user_cell";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_SPECIALIST = "specialist";
    public static final String KEY_DR_MODE = "dr_mode";
    public static final String JSON_ARRAY = "result";


    public static final String KEY_TIME = "time";
    public static final String KEY_ID = "id";
    public static final String KEY_DR_CELL = "dr_cell";

    public static final String KEY_DOCTOR_NAME = "doctor_name";
    public static final String KEY_DOCTOR_DETAILS = "doctor_details";
    public static final String KEY_DOCTOR_ID = "doctor_id";
    public static final String KEY_HOSPITAL_ID ="hospital_id";

    //We will use this to store the user cell number into shared preference
    public static final String SHARED_PREF_NAME = "com.app.android.userlogin"; //pcakage name+ id

    //This would be used to store the cell of current logged in user
    public static final String CELL_SHARED_PREF = "cell";
    public static final String PASSWORD_SHARED_PREF = "password";
    public static final String AC_TYPE_SHARED_PREF = "ac_type";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

}

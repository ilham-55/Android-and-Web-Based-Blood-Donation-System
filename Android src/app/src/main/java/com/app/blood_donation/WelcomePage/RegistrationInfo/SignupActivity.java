package com.app.blood_donation.WelcomePage.RegistrationInfo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class SignupActivity extends AppCompatActivity {
    private ImageView imageView;
    private Animation smalltobig, sinup_btta, sinup_btta2;
    private TextView textView, subtitle_header,buttongotologin,txtregistration;
    private Button btnSignUp;
    private EditText etxtName, etxtPassword, etxtMobile, etxtDivision, etxtBloodGroup;
    ProgressDialog loading;
    private RadioGroup radioGroup;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    MaterialSpinner txtSubArea;
    LinearLayout layout;
    String division;

    List<String> list = new ArrayList<>();
    //for double back press to exit
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().hide();
        // load this animation
        smalltobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        sinup_btta = AnimationUtils.loadAnimation(this, R.anim.btta);
        sinup_btta2 = AnimationUtils.loadAnimation(this, R.anim.btta2);

        imageView = findViewById(R.id.sign_imageView);
        txtSubArea =findViewById(R.id.sinup_txt_area_signup2);

        //textView = findViewById(R.id.sinup_textView);
        subtitle_header = findViewById(R.id.sinup_subtitle_header);
        txtregistration = findViewById(R.id.sinup_textView2);
        buttongotologin = findViewById(R.id.txtgotologin);

        btnSignUp = findViewById(R.id.sinup_button);

        etxtName = findViewById(R.id.sinup_txt_fullname_signup);
        etxtPassword = findViewById(R.id.sinup_txt_password_signup);
        etxtMobile = findViewById(R.id.sinup_txt_mobile_signup);
        //editText4 = findViewById(R.id.txt_area_signup);
        radioGroup = findViewById(R.id.sinup_radiogroup);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        layout = (LinearLayout) findViewById(R.id.sinup_radiogrplayout);

        // sCountry = findViewById(R.id.txt_area_signup);
        etxtDivision = findViewById(R.id.sinup_txt_area_signup);
        etxtBloodGroup = findViewById(R.id.sinup_txt_bloodgroup_signup);
        Typeface tf = Typeface.createFromAsset(getAssets(), "Milkshake.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "Quicksand-Regular.otf");

        //txtTitle.setTypeface(tf);
        txtregistration.setTypeface(tf);
        btnSignUp.setTypeface(tf1);



        // passing animation and start it
     /*   imageView.startAnimation(smalltobig);

        textView.startAnimation(sinup_btta);
        subtitle_header.startAnimation(sinup_btta);

        btnSignUp.startAnimation(sinup_btta2);

        etxtName.startAnimation(sinup_btta2);
        etxtPassword.startAnimation(sinup_btta2);
        etxtMobile.startAnimation(sinup_btta2);
        etxtDivision.startAnimation(sinup_btta2);
        etxtBloodGroup.startAnimation(sinup_btta2);
        layout.startAnimation(sinup_btta2);
*/

        etxtDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 1:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 2:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];

                                break;

                            case 3:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 4:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 5:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 6:

                               etxtDivision.setText(cityList[position]);
                                division =cityList[position];
                                break;

                            case 7:

                               etxtDivision.setText(cityList[position]);
                               division =cityList[position];
                                break;
                        }



                        String div=etxtDivision.getText().toString();
                        txtSubArea.setText("Select Sub Area");
                        //call method for district
                        list.clear();
                        getDistrict(div);

                        //for better ui response
                        //sleep(500);
                        Log.d("CALL","GET District");
                       // layoutDistrict.setVisibility(View.VISIBLE);

                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog locationTypeDialog = builder.create();

                locationTypeDialog.show();



            }

        });



        txtSubArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtSubArea.setItems(list);


                txtSubArea.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {


                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        Toast.makeText(SignupActivity.this, "Clicked "+item, Toast.LENGTH_SHORT).show();



                    }
                });




            }
        });

        // blood group
        etxtBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] bloodList = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

                AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT BLOOD GROUP");
                builder.setIcon(R.drawable.ic_blood);


                builder.setCancelable(false);
                builder.setItems(bloodList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtBloodGroup.setText("A+");
                                break;

                            case 1:

                                etxtBloodGroup.setText("A-");
                                break;

                            case 2:

                                etxtBloodGroup.setText("B+");
                                break;

                            case 3:

                                etxtBloodGroup.setText("B-");
                                break;

                            case 4:

                                etxtBloodGroup.setText("AB+");
                                break;

                            case 5:

                                etxtBloodGroup.setText("AB-");
                                break;

                            case 6:

                                etxtBloodGroup.setText("O+");
                                break;

                            case 7:

                                etxtBloodGroup.setText("O-");
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                });


                AlertDialog locationTypeDialog = builder.create();

                locationTypeDialog.show();
            }

        });

        buttongotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signup();
            }
        });
    }



    /*public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();

            finishAffinity();

        } else {
            Toasty.warning(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();

        }
        back_pressed = System.currentTimeMillis();
    }*/




    private void signup() {

        String getGender = "";
        if (rbMale.isChecked())
        {
            getGender="Male";
        }

        else if (rbFemale.isChecked())
        {
            getGender="Female";

        }
        //Getting values from edit texts
        //String gender="";
        final String gender = getGender;
        final String name = etxtName.getText().toString().trim();
        final String mobile = etxtMobile.getText().toString().trim();
        final String division = etxtDivision.getText().toString().trim();
        final String password = etxtPassword.getText().toString().trim();
        final String blood_group=etxtBloodGroup.getText().toString().trim();
        final String sub_area=txtSubArea.getText().toString().trim();


        //Checking  field/validation
        if (name.isEmpty()) {
            etxtName.setError("Please enter your name !");
            etxtName.requestFocus();
        }


        //Checking username field/validation
        else if (mobile.length()!=11 || mobile.contains(" ") || mobile.charAt(0)!='0'
                ||mobile.charAt(1)!='1' ) {
            etxtMobile.setError("Please enter correct cell !");
            etxtMobile.requestFocus();
        }

        //Checking username field/validation
        else  if (division.isEmpty()) {
            etxtDivision.setError("Please enter your location!");
            etxtDivision.requestFocus();
        }

        else  if (sub_area.isEmpty()) {
            etxtDivision.setError("Please enter your sub area!");
            etxtDivision.requestFocus();
        }


        //Checking password field/validation
        else if (password.length() < 4) {

            etxtPassword.setError("Password at least 4 character long !");
            etxtPassword.requestFocus();

        }

        else if (!rbMale.isChecked() && !rbFemale.isChecked())
        {
            Toast.makeText(this, "Select Gender", Toast.LENGTH_SHORT).show();

        }






        else {
            //showing progress dialog

            loading = new ProgressDialog(this);
            loading.setMessage("Please wait....");
            loading.show();

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.SIGNUP_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for logcat
                            Log.d("RESPONSE", response);


                            //If we are getting success from server
                            if (response.equalsIgnoreCase(Constant.SIGNUP_SUCCESS)) {


                                loading.dismiss();
                                //Starting profile activity
                                Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
                                Toasty.success(SignupActivity.this, "Sign up successful", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            } else if (response.equalsIgnoreCase(Constant.USER_EXISTS)) {

                                Toasty.error(SignupActivity.this, "User Already exists!", Toast.LENGTH_SHORT).show();
                                loading.dismiss();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(SignupActivity.this, "Error in connection!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_CELL, mobile);
                    params.put(Constant.KEY_GENDER, gender);
                    params.put(Constant.KEY_LOCATION, division);
                    params.put(Constant.KEY_SUB_AREA, sub_area);
                    params.put(Constant.KEY_PASSWORD, password);
                    params.put(Constant.KEY_BLOOD_GROUP, blood_group);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }



    private void getDistrict(String district) {

        final ProgressDialog loading;
        loading=new ProgressDialog(this);
        loading.setMessage("Loading...Please wait...");
        loading.show();

        String url = Constant.GET_AREA_URL+ district;  // url for connecting php file

        Log.d("URL",url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //showJSON(response);
                Log.d("response",response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


                    for (int i = 0; i < result.length(); i++) {

                        JSONObject jo = result.getJSONObject(i);
                        String sub_area = jo.getString(Constant.KEY_SUB_AREA);
                        list.add(sub_area);

                    }

                    loading.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Volley Error","Error:"+error);

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(SignupActivity.this);
        requestQueue.add(stringRequest);

    }
}


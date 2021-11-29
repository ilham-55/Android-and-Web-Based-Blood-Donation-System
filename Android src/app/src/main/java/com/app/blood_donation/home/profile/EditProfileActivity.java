package com.app.blood_donation.home.profile;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.MainPage.MainActivity;
import com.app.blood_donation.R;
import com.app.blood_donation.home.model.ImageUpload;
import com.app.blood_donation.home.remote.ApiClient;
import com.app.blood_donation.home.remote.ApiInterface;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;


public class EditProfileActivity extends AppCompatActivity {

    EditText txtName,txtDonateDate,txtCell,txtLocation,txtGender,txtPassword;


    ProgressDialog loading;
    TextView txtName2,txtUpdate;
    ImageView profileImage;

    ProgressDialog progressDialog;



    String imagePath="",getImage;

    //for date
    public Calendar myCalendar = Calendar.getInstance();
    public DatePickerDialog.OnDateSetListener date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        txtName=findViewById(R.id.txt_name);
        txtCell=findViewById(R.id.txt_cell);
        txtGender=findViewById(R.id.txt_gender);
        txtLocation=findViewById(R.id.txt_location);
        txtPassword=findViewById(R.id.txt_password);
        txtName2=findViewById(R.id.txt_name2);
        txtUpdate=findViewById(R.id.txt_update);
        profileImage=findViewById(R.id.profile_image);
        txtDonateDate=findViewById(R.id.txt_donate_date);



        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Update Profile");

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading...");


        String getName=getIntent().getExtras().getString("name");
        String getCell=getIntent().getExtras().getString("cell");
        String getGender=getIntent().getExtras().getString("gender");
        String getLocation=getIntent().getExtras().getString("location");
        String getDonateDate=getIntent().getExtras().getString("donate_date");
        getImage=getIntent().getExtras().getString("image");


        txtName.setText(getName);
        txtName2.setText(getName);
        txtCell.setText(getCell);
        txtCell.setEnabled(false);

        txtDonateDate.setText(getDonateDate);


        txtLocation.setText(getLocation);
        txtGender.setText(getGender);
        //txtPassword.setText("*******");


        if(getGender.equals("Female"))
        {
           profileImage.setImageResource(R.drawable.girl);
        }


        if (!getImage.isEmpty())
        {
            Picasso.with(this)

                    .load(Constant.LOAD_PROFILE_IMAGE_URL+getImage)
                    .placeholder(R.drawable.donor)
                    .error(R.drawable.donor)
                    .into(profileImage);


        }

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                txtLocation.setText("Dhaka");
                                break;

                            case 1:

                                txtLocation.setText("Chittagong");
                                break;

                            case 2:

                                txtLocation.setText("Sylhet");
                                break;

                            case 3:

                                txtLocation.setText("Rajshahi");
                                break;

                            case 4:

                                txtLocation.setText("Barishal");
                                break;

                            case 5:

                                txtLocation.setText("Khulna");
                                break;

                            case 6:

                                txtLocation.setText("Rangpur");
                                break;

                            case 7:

                                txtLocation.setText("Mymensingh");
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



        //for input date
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }





        };

//        txtDonateDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new DatePickerDialog(EditProfileActivity.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });



        txtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] genderList = {"Male", "Female"};

                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT GENDER");
                builder.setIcon(R.drawable.ic_gender);


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                txtGender.setText(genderList[position]);
                                break;

                            case 1:
                                txtGender.setText(genderList[position]);
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


                AlertDialog accountTypeDialog = builder.create();

                accountTypeDialog.show();
            }
        });


        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);
                builder.setMessage("Want to Update Profile?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {



                                Log.d("ImagePath",imagePath);

                                if (!imagePath.isEmpty())
                                {

                                    uploadFile();
                                }

                                // Perform Your Task Here--When Yes Is Pressed.
                                UpdateProfile();
                                dialog.cancel();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();
            }
        });



        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(EditProfileActivity.this, ImageSelectActivity.class);
//                intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
//                intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
//                intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
//                startActivityForResult(intent, 1213);
            }
        });

    }




    //update contact method
    public void UpdateProfile() {

        final String name = txtName.getText().toString();
        final String cell = txtCell.getText().toString();
        final String location = txtLocation.getText().toString();
        final String gender = txtGender.getText().toString();
        final String password = txtPassword.getText().toString();
        final String donate_date= txtDonateDate.getText().toString();


        if (name.isEmpty()) {
            txtName.setError("Name Can't Empty");
            txtName.requestFocus();
        } else if (cell.length()!=11) {
            txtCell.setError("Invalid Cell");
            txtCell.requestFocus();

        } else if (password.length()<4) {
            txtPassword.setError("Password too short! or Invalid Password");
            txtPassword.requestFocus();
        } else {
            loading = new ProgressDialog(this);
            // loading.setIcon(R.drawable.wait_icon);
            // loading.setTitle("Update");
            loading.setMessage("Update...Please wait...");
            loading.show();

            String URL = Constant.DONOR_PROFILE_UPDATE_URL;




            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for track response in logcat
                            Log.d("RESPONSE", response);
                            // Log.d("RESPONSE", userCell);


                            //If we are getting success from server
                            if (response.equals("success")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                                Toasty.success(EditProfileActivity.this, " Profile Successfully Updated!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(EditProfileActivity.this,MainActivity.class);
                                Toasty.error(EditProfileActivity.this, " Profile Update fail!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(EditProfileActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(EditProfileActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    // params.put(Constant.KEY_ID, getID);
                    params.put(Constant.KEY_NAME, name);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_GENDER, gender);
                    params.put(Constant.KEY_LOCATION, location);
                    params.put(Constant.KEY_PASSWORD, password);
                    params.put(Constant.KEY_DONATE_DATE, donate_date);


                    //Log.d("ID", getID);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(EditProfileActivity.this);
            requestQueue.add(stringRequest);
        }

    }




    //for date input
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtDonateDate.setText(sdf.format(myCalendar.getTime()));
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            // When an Image is picked
            if (requestCode == 1213 && resultCode == RESULT_OK && null != data) {



                imagePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
                Bitmap selectedImage = BitmapFactory.decodeFile(imagePath);
                profileImage.setImageBitmap(selectedImage);


            }


        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


    // Uploading Image
    private void uploadFile() {
        progressDialog.show();

        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(imagePath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

       RequestBody cell = RequestBody.create(MediaType.parse("text/plain"),txtCell.getText().toString());


        ApiInterface getResponse = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ImageUpload> call = getResponse.uploadFile(fileToUpload, filename,cell);
        call.enqueue(new Callback<ImageUpload>() {

            @Override
            public void onResponse(Call<ImageUpload> call, retrofit2.Response<ImageUpload> response) {
                ImageUpload serverResponse = response.body();
                if (serverResponse != null) {
                    if (serverResponse.getSuccess()) {
//                        Toasty.success(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(EditProfileActivity.this,HomeActivity.class));

                    } else {
                        Toasty.error(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Log.v("Response", serverResponse.toString());
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ImageUpload> call, Throwable t) {

            }
        });
    }

    //for back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

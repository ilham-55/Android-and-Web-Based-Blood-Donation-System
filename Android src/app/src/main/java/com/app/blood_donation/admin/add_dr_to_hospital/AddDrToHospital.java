package com.app.blood_donation.admin.add_dr_to_hospital;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.app.blood_donation.admin.AdminHome;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AddDrToHospital extends AppCompatActivity {


    MaterialSpinner etSpeciality, etHospital;
    List<String> drList = new ArrayList<>();
    List<String> drID = new ArrayList<>();
    List<String> hospitalID = new ArrayList<>();
    List<String> hospitalList = new ArrayList<>();

    EditText etDrName,etDrDetails,etChamber,etAddress,etDivision,etContact;

    ProgressDialog loading;
    TextView txtSubmit;
    String dr_id = "", hospital_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dr_to_hospital);

        etSpeciality = findViewById(R.id.et_doctor);
        etHospital = findViewById(R.id.et_hospital);
        txtSubmit = findViewById(R.id.txt_submit);

        etDrName=findViewById(R.id.et_dr_name);
        etDrDetails=findViewById(R.id.et_dr_details);
        etChamber=findViewById(R.id.et_chamber);
        etAddress=findViewById(R.id.et_address);
        etDivision=findViewById(R.id.et_division);
        etContact=findViewById(R.id.et_contact);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Add Doctor");



        getSpeciality();
        getHospitalList();


        etDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(AddDrToHospital.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etDivision.setText(cityList[position]);
                                break;

                            case 1:

                                etDivision.setText(cityList[position]);
                                break;

                            case 2:

                                etDivision.setText(cityList[position]);

                                break;

                            case 3:

                                etDivision.setText(cityList[position]);
                                break;

                            case 4:

                                etDivision.setText(cityList[position]);
                                break;

                            case 5:

                                etDivision.setText(cityList[position]);
                                break;

                            case 6:

                                etDivision.setText(cityList[position]);
                                break;

                            case 7:

                                etDivision.setText(cityList[position]);
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



        etSpeciality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etSpeciality.setItems(drList);


                etSpeciality.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {


                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        //   Toast.makeText(AddDrToHospital.this, "Clicked "+drID.get(position), Toast.LENGTH_SHORT).show();




                    }
                });


            }
        });


        etHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                etHospital.setItems(hospitalList);


                etHospital.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {


                    @Override
                    public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        //Toast.makeText(AddDrToHospital.this, "Clicked "+hospitalID.get(position), Toast.LENGTH_SHORT).show();

                        hospital_id = hospitalID.get(position);

                    }
                });


            }
        });


        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addDoctorToHospital( hospital_id);
            }
        });
    }


    private void getSpeciality() {

        final ProgressDialog loading;
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...Please wait...");
        loading.show();

        String url = Constant.GET_SPECIALITY_URL;  // url for connecting php file

        Log.d("URL", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //showJSON(response);
                Log.d("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


                    for (int i = 0; i < result.length(); i++) {

                        JSONObject jo = result.getJSONObject(i);
                        String id = jo.getString(Constant.KEY_ID);
                        String dr_name = jo.getString(Constant.KEY_NAME);
                        drList.add(dr_name);
                        drID.add(id);

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

                        Log.d("Volley Error", "Error:" + error);

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void getHospitalList() {

        final ProgressDialog loading;
        loading = new ProgressDialog(this);
        loading.setMessage("Loading...Please wait...");
        loading.show();

        String url = Constant.GET_HOSPITAL_LIST_URL;  // url for connecting php file

        Log.d("URL", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //showJSON(response);
                Log.d("response", response);

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


                    for (int i = 0; i < result.length(); i++) {

                        JSONObject jo = result.getJSONObject(i);
                        String id = jo.getString(Constant.KEY_ID);
                        String dr_name = jo.getString(Constant.KEY_NAME);
                        hospitalList.add(dr_name);
                        hospitalID.add(id);

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

                        Log.d("Volley Error", "Error:" + error);

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


    private void addDoctorToHospital(String hospitalId) {
        //Getting values from edit texts
        final String hospital_id = hospitalId;

        final String dr_name=etDrName.getText().toString();
        final String dr_details=etDrDetails.getText().toString();
        final String dr_chamber=etChamber.getText().toString();
        final String dr_address=etAddress.getText().toString();
        final String dr_contact=etContact.getText().toString();
        final String dr_speciality=etSpeciality.getText().toString();
        final String dr_division=etDivision.getText().toString();


        if (dr_name.isEmpty()) {

            etDrName.setError("Enter Doctor Name");
            etDrName.requestFocus();
        }

      else   if (dr_details.isEmpty()) {

            etDrDetails.setError("Enter Doctor Details");
            etDrDetails.requestFocus();
        }

    else if (hospital_id.isEmpty()) {

            Toast.makeText(this, "Select Hospital", Toast.LENGTH_SHORT).show();
        } else {

            //showing progress dialog
            loading = new ProgressDialog(AddDrToHospital.this);
            loading.setMessage("Please wait....");
            loading.show();

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.ADD_DR_TOHOSPITAL_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for logcat
                            Log.d("RESPONSE", response);


                            //If we are getting success from server
                            if (response.equals("success")) {


                                loading.dismiss();
                                //Starting profile activity
                                Intent intent = new Intent(AddDrToHospital.this, AdminHome.class);
                                Toasty.success(AddDrToHospital.this, "Doctor Added to Hospital", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }

                            //If we are getting success from server
                            else if (response.equals("failure")) {


                                loading.dismiss();
                                //Starting profile activity
                                // Intent intent = new Intent(getActivity(), HomeActivity.class);
                                Toasty.error(AddDrToHospital.this, "Failed!", Toast.LENGTH_SHORT).show();
                                //  startActivity(intent);

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(AddDrToHospital.this, "Error in connection!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_DOCTOR_NAME, dr_name);
                    params.put(Constant.KEY_DOCTOR_DETAILS, dr_details);
                    params.put(Constant.KEY_CHAMBER, dr_chamber);
                    params.put(Constant.KEY_CONTACT, dr_contact);
                    params.put(Constant.KEY_ADDRESS, dr_address);
                    params.put(Constant.KEY_DIVISION, dr_division);
                    params.put(Constant.KEY_SPECIALIST, dr_speciality);
                    params.put(Constant.KEY_HOSPITAL_ID, hospital_id);

                    Log.d("Data",dr_name+" "+dr_details+ " "+dr_chamber+" "+dr_contact+" "+dr_address+" "+dr_division+" "+dr_speciality);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(AddDrToHospital.this);
            requestQueue.add(stringRequest);


        }
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

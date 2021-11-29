package com.app.blood_donation.home.blood_donor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.app.blood_donation.MainPage.MainActivity;
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

public class RequestDetailsActivity extends AppCompatActivity {

    MaterialSpinner txtSubArea;
    List<String> list = new ArrayList<>();

    ProgressDialog loading;
    TextView txtDelete,txtUpdate;
    String  UserCell,bags,id,type,blood_group,address,status,division,contact,sub_area;
    EditText etxtBags,etxtType,etxtBloodGroup,etxtAddress,etxtDivision,etxtStatus,etxtContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Details");

        etxtType=findViewById(R.id.type);
        txtSubArea =findViewById(R.id.etxt_sub_area);
        etxtAddress=findViewById(R.id.address);
        etxtBloodGroup=findViewById(R.id.blood_group);
        etxtBags=findViewById(R.id.bag);
        etxtDivision=findViewById(R.id.division);
        etxtStatus=findViewById(R.id.status);
        etxtContact=findViewById(R.id.contact_cell);

        txtDelete=findViewById(R.id.txt_delete);
        txtUpdate=findViewById(R.id.txt_update);

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;



        id=getIntent().getExtras().getString("id");
        type=getIntent().getExtras().getString("type");
        blood_group=getIntent().getExtras().getString("blood_group");
        address=getIntent().getExtras().getString("address");
        status=getIntent().getExtras().getString("status");
        division=getIntent().getExtras().getString("division");
        contact=getIntent().getExtras().getString("contact");
        bags=getIntent().getExtras().getString("bags");
        sub_area=getIntent().getExtras().getString("sub_area");


        etxtType.setText(type);
        etxtAddress.setText(address);
        etxtBloodGroup.setText(blood_group);
        etxtBags.setText(bags);
        etxtDivision.setText(division);
        etxtStatus.setText(status);
        etxtContact.setText(contact);
        txtSubArea.setText(sub_area);



        txtUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this);
                builder.setMessage("Want to update ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                               UpdateData();

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

        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this);
                builder.setMessage("Want to delete blood request ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                DeleteFromServer();//call method

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



        etxtBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] blood_group={"A+","A-","B+","B-","AB+","AB-","O+","O-"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this, R.style.MyDialogTheme);
                builder.setTitle("Select Blood Group");

                builder.setCancelable(false);
                builder.setItems(blood_group, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 1:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 2:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 3:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 4:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 5:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 6:

                                etxtBloodGroup.setText(blood_group[position]);
                                break;

                            case 7:

                                etxtBloodGroup.setText(blood_group[position]);
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



        etxtDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this, R.style.MyDialogTheme);
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
                        Toast.makeText(RequestDetailsActivity.this, "Clicked "+item, Toast.LENGTH_SHORT).show();



                    }
                });




            }
        });

        //For choosing account type and open alert dialog
        etxtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] typeList = {"Emergency", "Normal"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT TYPE");
                builder.setIcon(R.drawable.ic_blood);


                builder.setCancelable(false);
                builder.setItems(typeList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                etxtType.setText(typeList[position]);
                                break;

                            case 1:
                                etxtType.setText(typeList[position]);
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


        etxtStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Received","Not Received"};

                AlertDialog.Builder builder = new AlertDialog.Builder(RequestDetailsActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT STATUS");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtStatus.setText(cityList[position]);
                                status="1";

                                break;

                            case 1:

                                etxtStatus.setText(cityList[position]);
                                status="0";
                                break;


                        }


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                    }
                }).show();





            }

        });






    }




    //update contact method
    public void UpdateData() {

        final String type = etxtType.getText().toString();
        final String cell = etxtContact.getText().toString();
        final String bags = etxtBags.getText().toString();
        final String address = etxtAddress.getText().toString();
        final String division = etxtDivision.getText().toString();

        final String blood_group = etxtBloodGroup.getText().toString();
        final String sub_area=txtSubArea.getText().toString().trim();


        if (type.isEmpty()) {
            etxtType.setError("Type Can't Empty");
            etxtType.requestFocus();
        } else if (cell.length()!=11) {
          etxtContact.setError("Invalid Cell");
            etxtContact.requestFocus();

        }
        else {
            loading = new ProgressDialog(this);
            loading.setMessage("Update...Please wait...");
            loading.show();

            String URL = Constant.REQUEST_UPDATE_URL;




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

                                Intent intent = new Intent(RequestDetailsActivity.this, MainActivity.class);
                                Toasty.success(RequestDetailsActivity.this, " Successfully Updated!", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();
                                //Starting profile activity

                                Intent intent = new Intent(RequestDetailsActivity.this,MainActivity.class);
                                Toasty.error(RequestDetailsActivity.this, " Update fail!", Toast.LENGTH_SHORT).show();
                                //startActivity(intent);

                            } else {

                                loading.dismiss();
                                Toasty.error(RequestDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(RequestDetailsActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_ID, id);
                    params.put(Constant.KEY_STATUS, status);
                    params.put(Constant.KEY_CONTACT, cell);
                    params.put(Constant.KEY_BLOOD_GROUP, blood_group);
                    params.put(Constant.KEY_ADDRESS, address);
                    params.put(Constant.KEY_TYPE, type);
                    params.put(Constant.KEY_DIVISION, division);
                    params.put(Constant.KEY_SUB_AREA, sub_area);
                    params.put(Constant.KEY_BAG, bags);
                    params.put(Constant.KEY_USER_CELL, UserCell);


                    Log.d("Data", id+" "+status+" "+cell+ " "+type);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(RequestDetailsActivity.this);
            requestQueue.add(stringRequest);
        }

    }


    //Delete method for deleting contacts
    public void DeleteFromServer() {
        loading = new ProgressDialog(this);

        loading.setMessage("Delete.Please wait....");
        loading.show();

        String URL = Constant.DELETE_REQUEST_URL;


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

                            Intent intent = new Intent(RequestDetailsActivity.this, MainActivity.class);
                            Toasty.success(RequestDetailsActivity.this, " Successfully Deleted!", Toast.LENGTH_SHORT).show();
                            startActivity(intent);

                        }


                        //If we are getting success from server
                        else if (response.equals("failure")) {

                            loading.dismiss();
                            //Starting profile activity


                            Toasty.error(RequestDetailsActivity.this, " Delete fail!", Toast.LENGTH_SHORT).show();

                        } else {

                            loading.dismiss();
                            Toasty.error(RequestDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want

                        Toast.makeText(RequestDetailsActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                params.put(Constant.KEY_ID, id);

                Log.d("ID", id);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(RequestDetailsActivity.this);
        requestQueue.add(stringRequest);

    }


    private void getDistrict(String district) {

        final ProgressDialog loading;
        loading=new ProgressDialog(RequestDetailsActivity.this);
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

        RequestQueue requestQueue = Volley.newRequestQueue(RequestDetailsActivity.this);
        requestQueue.add(stringRequest);

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

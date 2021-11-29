package com.app.blood_donation.home.blood_donor;


import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodRequestFragment extends Fragment {


    private EditText etxtLatLon,etxtBloodGroup,etxtAddress,etxtDivision,etxtBag,etxtContact,etxtType;
    TextView txtSubmit;
    private ProgressDialog loading;
    String UserCell;
    SharedPreferences sharedPreferences;
    MaterialSpinner txtSubArea;


    public RequestQueue requestQueue;
    public LocationManager locationManager;
    public LocationListener listener;

    double latitude;
    double longitude;

    List<String> list = new ArrayList<>();
    public BloodRequestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_blood_request, container, false);


        getActivity().setTitle("Blood Request");

        etxtBloodGroup=v.findViewById(R.id.blood_group);
        etxtAddress=v.findViewById(R.id.address);
        etxtDivision=v.findViewById(R.id.division);
        etxtBag=v.findViewById(R.id.bag);
        etxtContact=v.findViewById(R.id.contact_cell);
        etxtType=v.findViewById(R.id.type);
        txtSubmit=v.findViewById(R.id.txt_submit);
        txtSubArea =v.findViewById(R.id.etxt_sub_area);
        etxtLatLon=v.findViewById(R.id.etxt_lat_lon);


        //Fetching cell from shared preferences
        sharedPreferences =getActivity().getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;




        //For choosing gender and open alert dialog
        etxtDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList={"Dhaka","Chittagong","Sylhet","Rajshahi","Barishal","Khulna","Rangpur","Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                etxtDivision.setText("Dhaka");
                                break;

                            case 1:

                                etxtDivision.setText("Chittagong");
                                break;

                            case 2:

                                etxtDivision.setText("Sylhet");
                                break;

                            case 3:

                                etxtDivision.setText("Rajshahi");
                                break;

                            case 4:

                                etxtDivision.setText("Barishal");
                                break;

                            case 5:

                                etxtDivision.setText("Khulna");
                                break;

                            case 6:

                                etxtDivision.setText("Rangpur");
                                break;

                            case 7:

                                etxtDivision.setText("Mymensingh");
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



        //For choosing account type and open alert dialog
        etxtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] genderList = {"Emergency", "Normal"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                builder.setTitle("SELECT TYPE");
                builder.setIcon(R.drawable.ic_blood);


                builder.setCancelable(false);
                builder.setItems(genderList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                                etxtType.setText(genderList[position]);
                                break;

                            case 1:
                               etxtType.setText(genderList[position]);
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



        etxtBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] blood_group={"A+","A-","B+","B-","AB+","AB-","O+","O-"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
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



        txtSubArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtSubArea.setItems(list);


                txtSubArea.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {


                    @Override public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                        Toast.makeText(getActivity(), "Clicked "+item, Toast.LENGTH_SHORT).show();



                    }
                });




            }
        });

        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Want to submit blood request ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                blood_request();

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




        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        requestQueue = Volley.newRequestQueue(getActivity());

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {


                latitude = location.getLatitude();
                longitude = location.getLongitude();

                Log.d("Lat", "" + latitude);
                Log.d("Lng", "" + latitude);

                //   txtCoordinates.setText("Lat="+latitude+" Lng="+longitude);

                etxtLatLon.setText("Lat:"+latitude+" Lon:"+longitude);
                etxtLatLon.setEnabled(false);
               // getCurrentPlace();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };


        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return v;
        }


        locationManager.requestLocationUpdates("gps", 10000, 10, listener);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 10, listener);



        return v;

    }



    private void blood_request(){
        //Getting values from edit texts
        final String blood_group = etxtBloodGroup.getText().toString().trim();
        final String address = etxtAddress.getText().toString().trim();
        final String division = etxtDivision.getText().toString().trim();
        final String bag = etxtBag.getText().toString().trim();
        final String type=etxtType.getText().toString().trim();
        final String contact=etxtContact.getText().toString().trim();
        final String sub_area=txtSubArea.getText().toString().trim();


        //Checking  field/validation
        if (blood_group.isEmpty()) {
            etxtBloodGroup.setError("Please select blood group!");
            etxtBloodGroup.requestFocus();
        }


        //Checking username field/validation
        else if (contact.length()!=11 || contact.contains(" ") || contact.charAt(0)!='0' ||contact.charAt(1)!='1' ) {
            etxtContact.setError("Please enter correct cell !");
            etxtContact.requestFocus();
        }

        //Checking username field/validation
        else  if (address.isEmpty()) {
            etxtAddress.setError("Please enter your address!");
            etxtAddress.requestFocus();
        }

        //Checking username field/validation
        else  if (bag.isEmpty()) {
            etxtBag.setError("Please enter how many bags!");
            etxtBag.requestFocus();
        }




        else {
            //showing progress dialog

            loading = new ProgressDialog(getActivity());
            loading.setMessage("Please wait....");
            loading.show();

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constant.BLOOD_REQUEST_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for logcat
                            Log.d("RESPONSE", response);


                            //If we are getting success from server
                            if (response.equalsIgnoreCase(Constant.SIGNUP_SUCCESS)) {


                                loading.dismiss();
                                //Starting profile activity
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                Toasty.success(getActivity(), "Blood Request Submitted", Toast.LENGTH_SHORT).show();
                                startActivity(intent);

                            }

                            //If we are getting success from server
                           else if (response.equalsIgnoreCase("failure")) {


                                loading.dismiss();
                                //Starting profile activity
                               // Intent intent = new Intent(getActivity(), HomeActivity.class);
                                Toasty.error(getActivity(), "Blood Request Failed!", Toast.LENGTH_SHORT).show();
                              //  startActivity(intent);

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toasty.error(getActivity(), "Error in connection!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_BLOOD_GROUP, blood_group);
                    params.put(Constant.KEY_ADDRESS, address);
                    params.put(Constant.KEY_DIVISION, division);
                    params.put(Constant.KEY_BAG, bag);
                    params.put(Constant.KEY_CONTACT, contact);
                    params.put(Constant.KEY_TYPE, type);
                    params.put(Constant.KEY_USER_CELL, UserCell);

                    params.put(Constant.KEY_SUB_AREA, sub_area);
                    params.put(Constant.KEY_LATITUDE, ""+latitude);
                    params.put(Constant.KEY_LONGITUDE, ""+longitude);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);


        }
    }



    private void getDistrict(String district) {

        final ProgressDialog loading;
        loading=new ProgressDialog(getActivity());
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

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);

    }
}

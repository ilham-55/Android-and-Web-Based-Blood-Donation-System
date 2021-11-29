package com.app.blood_donation.home.doctor_info;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class DoctorsListActivity extends AppCompatActivity {


    private ListView CustomList;

    private ProgressDialog loading;

    private Button btnDoctorSpecialist, btnDoctorSearch, btnSearchHospitalinMap;


    private int MAX_SIZE = 999;
    private String[] drCell = new String[MAX_SIZE];
    private String[] drID = new String[MAX_SIZE];
    private String mCategory = "", mCity = "";
    private ImageView imgNoData;
    String UserCell;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        getSupportActionBar().setTitle("Doctors Information");


        CustomList = findViewById(R.id.listView_doctorslist);


        btnDoctorSpecialist = findViewById(R.id.btn_blood_specialist_doctorslist);
        btnDoctorSearch = findViewById(R.id.btn_submit_doctorslist);

        //Fetching cell from shared preferences
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;

        imgNoData = findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);


        //call function
        getData("");


        btnDoctorSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] specialist = {"Anesthesiology Specialist", "Burn Specialist",
                        "Breast Surgeon Specialist", "Cardiology Heart Specialist",
                        "Cancer Specialist", "Cardiovascular Thoracic Surgeon", "Chest Asthma Specialist",
                        "Child Pediatric Cardiology", "Colorectal Surgery Specialist",
                        "Dental Specialist", "Diabetes Specialist", "Eye Specialist",
                        "ENT Specialist", "Haematology Specialist", "Kidney Specialist", "Liver Specialist",
                        "Medicine Specialist", "Neuromedicine Specialist", "Orthopaedics Specialist",
                        "Pain Management Specialist", "Pediatric Specialist",
                        "Psychologist Specialist",
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorsListActivity.this, R.style.MyDialogTheme);
                builder.setTitle("Select Specialist");

                builder.setCancelable(false);
                builder.setItems(specialist, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {


                            case 0:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "as";
                                break;

                            case 1:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "bs";
                                break;

                            case 2:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "bss";
                                break;

                            case 3:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "chs";
                                break;

                            case 4:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "cs";
                                break;

                            case 5:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "cts";
                                break;

                            case 6:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "cas";
                                break;
                            case 7:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "cpc";
                                break;
                            case 8:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "css";
                                break;
                            case 9:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ds";
                                break;

                            case 10:
                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "dis";
                                break;
                            case 11:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "es";
                                break;
                            case 12:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ents";
                                break;
                            case 13:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "hs";
                                break;
                            case 14:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ks";
                                break;
                            case 15:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ls";
                                break;
                            case 16:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ms";
                                break;
                            case 17:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ns";
                                break;
                            case 18:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "os";
                                break;
                            case 19:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "pms";
                                break;
                            case 20:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "ps";
                                break;
                            case 21:

                                btnDoctorSpecialist.setText(specialist[position]);
                                mCategory = "pys";
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


                AlertDialog categoryDialog = builder.create();

                categoryDialog.show();
            }

        });


        btnDoctorSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /*if (mCity.isEmpty()) {
                    Toasty.warning(DoctorsListActivity.this, "Please select city", Toast.LENGTH_SHORT).show();
                } else*/
                if (mCategory.isEmpty()) {
                    Toasty.warning(DoctorsListActivity.this, "Please select Specialist", Toast.LENGTH_SHORT).show();
                } else {
                    //getData(mCategory, mCity);
                    getData(mCategory);
                }


            }
        });

    }


    private void getData(String specialist) {

        loading = new ProgressDialog(this);
        loading.setMessage("Please wait....");
        loading.show();

        String url = Constant.SHOW_DOCTORSDETAILS_URL + "?text=" + specialist;
        Log.d("url", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("response", response);
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toasty.error(DoctorsListActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DoctorsListActivity.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length() == 0) {
                Toasty.error(DoctorsListActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String id = jo.getString(Constant.KEY_ID);
                    String specialist = jo.getString(Constant.KEY_SPECIALIST_DOCTOR);
                    String address = jo.getString(Constant.KEY_ADDRESS);
                    String division = jo.getString(Constant.KEY_DIVISION);
                    String details = jo.getString(Constant.KEY_DETAILS_DOCTOR);
                    String mobile = jo.getString(Constant.KEY_MOBILE);
                    String name = jo.getString(Constant.KEY_NAME);
                    String chamber = jo.getString(Constant.KEY_CHAMBER);

                    drID[i]=id;
                    drCell[i]=mobile;



                    drCell[i] = mobile;
                    if (chamber.equals("null")) {
                        chamber = "N/A";
                    }
                    if (address.equals("null")) {
                        address = "N/A";
                    }
                    if (details.equals("null")) {
                        details = "N/A";
                    }

                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME,  name);
                    user_msg.put(Constant.KEY_SPECIALIST_DOCTOR, "Specialist: " + specialist);
                    user_msg.put(Constant.KEY_CHAMBER, "Chamber: " + chamber);
                    user_msg.put(Constant.KEY_ADDRESS, "Address: " + address);

                    //user_msg.put(Constant.KEY_DIVISION, "Division: "  + division);
                    user_msg.put(Constant.KEY_DETAILS_DOCTOR, "Qualification: " + details);
                    // user_msg.put(Constant.KEY_MOBILE, mobile);


                    list.add(user_msg);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.doctors_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_SPECIALIST_DOCTOR, Constant.KEY_CHAMBER, Constant.KEY_ADDRESS, Constant.KEY_DETAILS_DOCTOR},
                new int[]{R.id.doctors_name, R.id.doctors_specialist, R.id.doctors_chamber, R.id.doctors_address, R.id.doctors_details});
        CustomList.setAdapter(adapter);


        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                new AlertDialog.Builder(DoctorsListActivity.this)
                        .setMessage("CALL OR REVIEW")
                        .setCancelable(true)
                        .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + drCell[position]));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("View Review", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed

                                Intent intent=new Intent(DoctorsListActivity.this,ViewReviewActivity.class);
                                intent.putExtra("dr_id",drID[position]);
                                intent.putExtra("dr_cell",drCell[position]);
                                intent.putExtra("user_cell",UserCell);
                                startActivity(intent);
                                dialog.cancel();
                            }
                        }).show();


            }
        });


    }

}

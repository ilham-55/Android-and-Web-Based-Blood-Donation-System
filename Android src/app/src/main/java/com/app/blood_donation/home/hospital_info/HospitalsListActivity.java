package com.app.blood_donation.home.hospital_info;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
import com.app.blood_donation.home.Maps.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import es.dmoral.toasty.Toasty;

public class HospitalsListActivity extends AppCompatActivity {
    private ListView CustomList;

    private ProgressDialog loading;

    private Button btnHospitalDivision, btnHospitalCategory, btnHospitalSearch, btnSearchHospitalinMap;
    SwipeRefreshLayout mSwipeRefreshLayout;

    private int MAX_SIZE = 999;
    private String[] hospitalCell = new String[MAX_SIZE];
    private String[] hospitalId = new String[MAX_SIZE];
    private String mCategory = "", mCity = "";
    private ImageView imgNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hospitals_list);
        CustomList = (ListView) findViewById(R.id.listView_hospitalslist);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Hospital Information");

        mSwipeRefreshLayout = findViewById(R.id.swipeToRefreshHospital);

        btnHospitalCategory = findViewById(R.id.btn_blood_category_hospitalslist);
        btnHospitalDivision = findViewById(R.id.btn_division_hospitalslist);
        btnHospitalSearch = findViewById(R.id.btn_submit_hospitalslist);
        btnSearchHospitalinMap = findViewById(R.id.button_search_hospitalinmap);

        imgNoData = findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);

        btnSearchHospitalinMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalsListActivity.this, MapsActivity.class);
                intent.putExtra("type", "hospital");
                startActivity(intent);

            }
        });

        //call function
        getData("", "");
        //set color of swipe refresh
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        //swipe refresh listeners
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                //call function
                getData("", "");

                //after shuffle id done then swife refresh is off
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        //For choosing gender and open alert dialog
        btnHospitalDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Barishal", "Khulna", "Rangpur", "Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(HospitalsListActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT CITY");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                btnHospitalDivision.setText("Dhaka");
                                mCity = cityList[position];
                                break;

                            case 1:

                                btnHospitalDivision.setText("Chittagong");
                                mCity = cityList[position];
                                break;

                            case 2:

                                btnHospitalDivision.setText("Sylhet");
                                mCity = cityList[position];
                                break;

                            case 3:

                                btnHospitalDivision.setText("Rajshahi");
                                mCity = cityList[position];
                                break;

                            case 4:

                                btnHospitalDivision.setText("Barishal");
                                mCity = cityList[position];
                                break;

                            case 5:

                                btnHospitalDivision.setText("Khulna");
                                mCity = cityList[position];
                                break;

                            case 6:

                                btnHospitalDivision.setText("Rangpur");
                                mCity = cityList[position];
                                break;

                            case 7:

                                btnHospitalDivision.setText("Mymensingh");
                                mCity = cityList[position];
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


        btnHospitalCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] category = {"Blood Bank", "Burn & Plastic Surgery", "Cancer Hospital",
                        "Cardiac & General Hospital", "Child Health & Mother Care Hospital",
                        "Clinic", "Dental Hospital", "Diagnostic Centre", "Diagnostic & Patient Consultation Center",
                        "ENT Hospital", "Eye Hospital", "Gastro Liver Hospital", "General Hospital",
                        "General & Dental Hospital", "Kidney Hospital", "Kidney & Dialysis Hospital",
                        "Laser Skin & Cosmetic Surgery Hospital", "Mental Health Care Center",
                        "Mental & Drug Abuse Treatment Hospital", "Orthopedic ( Pangu ) Hospital",
                        "Paralysis Hospital", "Patient Consultation Center", "Pediatric & Neonatal Hospital",
                        "Physiotherapy Hospital", "Tuberculosis Hospital"};

                AlertDialog.Builder builder = new AlertDialog.Builder(HospitalsListActivity.this, R.style.MyDialogTheme);
                builder.setTitle("Select Category");

                builder.setCancelable(false);
                builder.setItems(category, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "bb";
                                break;

                            case 1:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "bps";
                                break;

                            case 2:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "ch";
                                break;

                            case 3:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "cgh";
                                break;

                            case 4:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "chmch";
                                break;

                            case 5:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "c";
                                break;

                            case 6:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "dh";
                                break;

                            case 7:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "dc";
                                break;
                            case 8:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "dpcc";
                                break;
                            case 9:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "enth";
                                break;
                            case 10:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "eh";
                                break;
                            case 11:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "glh";
                                break;
                            case 12:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "gh";
                                break;
                            case 13:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "gdh";
                                break;
                            case 14:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "kh";
                                break;
                            case 15:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "kdh";
                                break;
                            case 16:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "lscsh";
                                break;
                            case 17:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "mhcc";
                                break;
                            case 18:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "mdath";
                                break;
                            case 19:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "oph";
                                break;
                            case 20:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "ph";
                                break;
                            case 21:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "pcc";
                                break;
                            case 22:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "pnh";
                                break;
                            case 23:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "pyh";
                                break;
                            case 24:

                                btnHospitalCategory.setText(category[position]);
                                mCategory = "th";
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


        btnHospitalSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mCity.isEmpty()) {
                    Toasty.warning(HospitalsListActivity.this, "Please select city", Toast.LENGTH_SHORT).show();
                } else if (mCategory.isEmpty()) {
                    Toasty.warning(HospitalsListActivity.this, "Please select category", Toast.LENGTH_SHORT).show();
                } else {
                    getData(mCategory, mCity);
                }


            }
        });

    }


    private void getData(String category, String city) {

        loading = new ProgressDialog(this);
        loading.setMessage("Please wait....");
        loading.show();


//        if (!get_blood.isEmpty() && !getCity.isEmpty()) {
//            get_blood = blood;
//            getCity=city;
//        }

        String url = Constant.SHOW_HOSPITALS_URL + "?text=" + category + "&city=" + city;
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
                        Toasty.error(HospitalsListActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(HospitalsListActivity.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length() == 0) {
                Toasty.error(HospitalsListActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);


                    String id = jo.getString(Constant.KEY_ID);

                    String category = jo.getString(Constant.KEY_CATEGORY);
                    String address = jo.getString(Constant.KEY_ADDRESS);
                    String website = jo.getString(Constant.KEY_WEBSITE);
                    String mobile = jo.getString(Constant.KEY_MOBILE);
                    String name = jo.getString(Constant.KEY_NAME);


                    hospitalCell[i] = mobile;
                    hospitalId[i] = id;
                    if (website.equals("null")) {
                        website = "N/A";
                    }

                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME,  name);
                    user_msg.put(Constant.KEY_CATEGORY, "Category: " + category);
                    user_msg.put(Constant.KEY_ADDRESS, "Address: " + address);
                    //user_msg.put(Constant.KEY_DIVISION, "Division: "  + division);
                    user_msg.put(Constant.KEY_WEBSITE, "Website: " + website);
                    // user_msg.put(Constant.KEY_MOBILE, mobile);


                    list.add(user_msg);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                this, list, R.layout.hospitals_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_CATEGORY, Constant.KEY_ADDRESS, Constant.KEY_WEBSITE},
                new int[]{R.id.hospitals_name, R.id.hospitals_category, R.id.hospitals_location, R.id.hospitals_website});
        CustomList.setAdapter(adapter);


        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                Intent intent=new Intent(HospitalsListActivity.this,DoctorList.class);
                intent.putExtra("id", hospitalId[position]);
                intent.putExtra("cell", hospitalCell[position]);
                startActivity(intent);

//                new AlertDialog.Builder(HospitalsListActivity.this)
//                        .setMessage("Want to Call Now?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
////                                Intent intent = new Intent(Intent.ACTION_DIAL);
////                                intent.setData(Uri.parse("tel:" + hospitalCell[position]));
////                                startActivity(intent);
//
//
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();


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

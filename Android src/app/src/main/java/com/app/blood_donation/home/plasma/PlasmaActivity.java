package com.app.blood_donation.home.plasma;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class PlasmaActivity extends AppCompatActivity {


    ListView CustomList;
    Button btnCity,btnBloodGroup,btnSubmit;
    private String mBlood = "", mCity = "", status = "";
    private ProgressDialog loading;
    int MAX_SIZE = 999;
    public String bloodbankCell[] = new String[MAX_SIZE];
    private ImageView imgNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plasma);


        CustomList = findViewById(R.id.listView_bloodbank);


        imgNoData = findViewById(R.id.img_no_data);

        btnBloodGroup = findViewById(R.id.btn_blood_group);
        btnCity = findViewById(R.id.btn_city);
        btnSubmit = findViewById(R.id.btn_submit);

        imgNoData.setVisibility(View.INVISIBLE);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Plasma Donor List");


        //call function
        getData("","");



        //For choosing gender and open alert dialog
        btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Barishal", "Khulna", "Rangpur", "Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(PlasmaActivity.this, R.style.MyDialogTheme);
                builder.setTitle("SELECT DIVISION");
                builder.setIcon(R.drawable.ic_location);


                builder.setCancelable(false);
                builder.setItems(cityList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:

                                btnCity.setText("Dhaka");
                                mCity = cityList[position];
                                break;

                            case 1:

                                btnCity.setText("Chittagong");
                                mCity = cityList[position];
                                break;

                            case 2:

                                btnCity.setText("Sylhet");
                                mCity = cityList[position];
                                break;

                            case 3:

                                btnCity.setText("Rajshahi");
                                mCity = cityList[position];
                                break;

                            case 4:

                                btnCity.setText("Barishal");
                                mCity = cityList[position];
                                break;

                            case 5:

                                btnCity.setText("Khulna");
                                mCity = cityList[position];
                                break;

                            case 6:

                                btnCity.setText("Rangpur");
                                mCity = cityList[position];
                                break;

                            case 7:

                                btnCity.setText("Mymensingh");
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


        btnBloodGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] blood_group = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};

                AlertDialog.Builder builder = new AlertDialog.Builder(PlasmaActivity.this, R.style.MyDialogTheme);
                builder.setTitle("Select Blood Group");

                builder.setCancelable(false);
                builder.setItems(blood_group, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        switch (position) {
                            case 0:
                            btnBloodGroup.setText(blood_group[position]);
                            mBlood = "aplus";
                            break;

                            case 1:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "aminus";
                                break;

                            case 2:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "bplus";
                                break;

                            case 3:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "bminus";
                                break;

                            case 4:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "abplus";
                                break;

                            case 5:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "abminus";
                                break;

                            case 6:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "oplus";
                                break;

                            case 7:

                                btnBloodGroup.setText(blood_group[position]);
                                mBlood = "ominus";
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


                AlertDialog blooGroupDialog = builder.create();

                blooGroupDialog.show();
            }

        });


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCity.isEmpty()) {
                    Toasty.warning(PlasmaActivity.this, "Please select city", Toast.LENGTH_SHORT).show();
                } else if (mBlood.isEmpty()) {
                    Toasty.warning(PlasmaActivity.this, "Please select blood group", Toast.LENGTH_SHORT).show();
                } else {
                    getData(mBlood, mCity);
                }
            }
        });

    }


    private void getData(String blood,String city) {


        //showing progress dialog
        loading = new ProgressDialog(this);
        loading.setMessage("Please wait....");
        loading.show();



        String url = Constant.PLASMA_LIST_URL + "?city=" + city+"&blood="+blood;

        Log.d("URL", url);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response", response);
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toasty.error(PlasmaActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {


        Log.d("Response 2", response);

        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length() == 0) {
                Toasty.error(PlasmaActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String bloodbank_name = jo.getString(Constant.KEY_NAME);

                    String plasma_donor_address = jo.getString(Constant.KEY_ADDRESS);
                    String plasma_donor_phone = jo.getString(Constant.KEY_CELL);

                    String blood_group = jo.getString(Constant.KEY_BLOOD_GROUP);

                    bloodbankCell[i] = plasma_donor_phone;
                    if (plasma_donor_address.equals("null")) {
                        plasma_donor_address = "N/A";
                    }

                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME, bloodbank_name);
                    user_msg.put(Constant.KEY_ADDRESS, "Address : " + plasma_donor_address);
                    user_msg.put(Constant.KEY_PHONE, "Mobile : " + plasma_donor_phone);
                    user_msg.put(Constant.KEY_BLOOD_GROUP, blood_group);


                    list.add(user_msg);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                PlasmaActivity.this, list, R.layout.plasma_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_ADDRESS, Constant.KEY_PHONE,Constant.KEY_BLOOD_GROUP},
                new int[]{R.id.bloodbank_name, R.id.bloodbank_address, R.id.bloodbank_mobile,R.id.txt_blood_group2});
        CustomList.setAdapter(adapter);

        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                new AlertDialog.Builder(PlasmaActivity.this)
                        .setMessage("Want to Call Now?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + bloodbankCell[position]));
                                startActivity(intent);
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




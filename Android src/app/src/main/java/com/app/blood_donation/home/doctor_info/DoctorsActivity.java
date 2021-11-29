package com.app.blood_donation.home.doctor_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorsActivity extends AppCompatActivity {

    private ListView CustomList;

    private ProgressDialog loading;

    ListAdapter adapter;



    private int MAX_SIZE = 999;
    // private String[] donorCell = new String[MAX_SIZE];
    //private String mSpecialist = "", mCity = "";
    private String[] drID = new String[MAX_SIZE];
    private String[] drCell = new String[MAX_SIZE];
    private ImageView imgNoData;
    String UserCell;
    EditText txtSearch;

    public String Name[] = new String[MAX_SIZE];
    public String Specialist[] = new String[MAX_SIZE];
    public String Mobile[] = new String[MAX_SIZE];
    public String Chamber[] = new String[MAX_SIZE];
    public String Address[] = new String[MAX_SIZE];
    public String Division[] = new String[MAX_SIZE];
    public String Qualification[] = new String[MAX_SIZE];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Doctors List");

        CustomList = (ListView) findViewById(R.id.listView_doctorslist_details);



        txtSearch = findViewById(R.id.search_input_doctordlist);
        //btnSearchHospitalinMap = findViewById(R.id.button_search_hospitalinmap);

        imgNoData = findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);

        //call function to get data
        getData("");

        txtSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                searchData(s.toString());



            }

            @Override
            public void afterTextChanged(Editable s) {

            }


        });


    }


    private void getData(String text) {

        //for showing progress dialog
        loading = new ProgressDialog(DoctorsActivity.this);
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.SHOW_DOCTORSDETAILS_URL + "?text=" + text;

        Log.d("url", URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toast.makeText(DoctorsActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DoctorsActivity.this);
        requestQueue.add(stringRequest);
    }




    private void searchData(String text) {


        String URL = Constant.DOCTOR_LIST_URL + "?text=" + text;

       Log.d("url", URL);

        StringRequest stringRequest = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(DoctorsActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DoctorsActivity.this);
        requestQueue.add(stringRequest);

    }


    private void showJSON(String response) {

        //Create json object for receiving jason data
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);


            if (result.length() == 0) {
                Toast.makeText(DoctorsActivity.this, "No Data Available!", Toast.LENGTH_SHORT).show();


            } else {

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


                    //donorCell[i] = mobile;
                    if (chamber.equals("null")) {
                        chamber = "N/A";
                    }
                    if (address.equals("null")) {
                        address = "N/A";
                    }
                    if (details.equals("null")) {
                        details = "N/A";
                    }
                    if (mobile.equals("null")) {
                        details = "N/A";
                    }

                    //insert data into array for put extra
                    Name[i] = name;
                    Specialist[i] = specialist;
                    Mobile[i] = mobile;
                    Chamber[i] = chamber;
                    Address[i] = address;
                    Division[i] = division;
                    Qualification[i] = details;


                    //put value into Hashmap
                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME,  name);
                    user_msg.put(Constant.KEY_SPECIALIST_DOCTOR, specialist);
                    user_msg.put(Constant.KEY_CHAMBER, "Chamber: " + chamber);
                    user_msg.put(Constant.KEY_ADDRESS, "Address: " + address);

                    //user_msg.put(Constant.KEY_DIVISION, "Division: "  + division);
                    user_msg.put(Constant.KEY_DETAILS_DOCTOR,  details);
                    // user_msg.put(Constant.KEY_MOBILE, mobile);


                    list.add(user_msg);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new SimpleAdapter(
                DoctorsActivity.this, list, R.layout.doctors_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_SPECIALIST_DOCTOR, Constant.KEY_CHAMBER, Constant.KEY_ADDRESS, Constant.KEY_DETAILS_DOCTOR},
                new int[]{R.id.doctors_name, R.id.doctors_specialist, R.id.doctors_chamber, R.id.doctors_address, R.id.doctors_details});
        CustomList.setAdapter(adapter);



        // to see every item in details
        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                new AlertDialog.Builder(DoctorsActivity.this)
                        .setMessage("CALL OR REVIEW?")
                        .setCancelable(true)
                        .setPositiveButton("Call", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + Mobile[position]));
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("View Review", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed

                                Intent intent=new Intent(DoctorsActivity.this,ViewReviewActivity.class);
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
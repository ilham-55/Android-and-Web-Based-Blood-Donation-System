package com.app.blood_donation.home.blood_donor;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodNewsFragment extends Fragment {


    private ListView CustomList;

    private ProgressDialog loading;

    private Button  btnCity, btnBloodGroup, btnSubmit;


    private int MAX_SIZE = 999;
    private String donorCell[] = new String[MAX_SIZE];
    private String mBlood = "", mCity = "";
    private ImageView imgNoData;


    public BloodNewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blood_news, container, false);

        getActivity().setTitle("Blood News");
        CustomList = (ListView) v.findViewById(R.id.listView);


        btnBloodGroup = v.findViewById(R.id.btn_blood_group);
        btnCity = v.findViewById(R.id.btn_city);
        btnSubmit = v.findViewById(R.id.btn_submit);

        imgNoData = v.findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);



        //call function
        getData("", "");


        //For choosing gender and open alert dialog
        btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Barishal", "Khulna", "Rangpur", "Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
                builder.setTitle("SELECT CITY");
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

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
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
                    Toasty.warning(getActivity(), "Please select city", Toast.LENGTH_SHORT).show();
                } else if (mBlood.isEmpty()) {
                    Toasty.warning(getActivity(), "Please select blood group", Toast.LENGTH_SHORT).show();
                } else {
                    getData(mBlood, mCity);
                }


            }
        });




        return v;

    }


    private void getData(String blood, String city) {

        loading = new ProgressDialog(getActivity());
        loading.setMessage("Please wait....");
        loading.show();


//        if (!get_blood.isEmpty() && !getCity.isEmpty()) {
//            get_blood = blood;
//            getCity=city;
//        }

        String url = Constant.SHOW_NEWS_URL + "?text=" + blood + "&city=" + city;
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
                        Toasty.error(getActivity(), "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length() == 0) {
                Toasty.error(getActivity(), "No News Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String bag = jo.getString(Constant.KEY_BAG);
                    String address = jo.getString(Constant.KEY_ADDRESS);
                    String division = jo.getString(Constant.KEY_DIVISION);
                    String blood_group = jo.getString(Constant.KEY_BLOOD_GROUP);
                    String contact = jo.getString(Constant.KEY_CONTACT);
                    String type = jo.getString(Constant.KEY_TYPE);
                    String date = jo.getString(Constant.KEY_DATE);
                    String time = jo.getString(Constant.KEY_TIME);


                    String status = jo.getString(Constant.KEY_STATUS);


                    donorCell[i] = contact;

                    if (status.equals("0"))
                    {
                        status="Not Received";

                    }

                    else  if (status.equals("1"))
                    {
                        status="Received";
                    }


                    donorCell[i] = contact;


                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_TYPE, "Blood Needed: " + type);
                    user_msg.put(Constant.KEY_BAG, "Bags: " + bag);
                    user_msg.put(Constant.KEY_ADDRESS, "Hospital: " + address + "," + division);
                    user_msg.put(Constant.KEY_BLOOD_GROUP, blood_group);
                    user_msg.put(Constant.KEY_TIME, time + " " + date);

                    user_msg.put(Constant.KEY_STATUS, status);



                    list.add(user_msg);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.news_list_item,
                new String[]{Constant.KEY_TYPE, Constant.KEY_BAG, Constant.KEY_ADDRESS, Constant.KEY_BLOOD_GROUP, Constant.KEY_TIME,Constant.KEY_STATUS},
                new int[]{R.id.txt_type, R.id.txt_bag, R.id.txt_donor_location, R.id.txt_blood_group, R.id.txt_time_date,R.id.txt_status});
        CustomList.setAdapter(adapter);


        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                new AlertDialog.Builder(getActivity())
                        .setMessage("Want to Call Now?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(Intent.ACTION_DIAL);
                                intent.setData(Uri.parse("tel:" + donorCell[position]));
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

}

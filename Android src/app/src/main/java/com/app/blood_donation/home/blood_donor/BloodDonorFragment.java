package com.app.blood_donation.home.blood_donor;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodDonorFragment extends Fragment {

    private ListView CustomList;

    private ProgressDialog loading;

    private Button btnCity, btnBloodGroup, btnSubmit;

    SwipeRefreshLayout mSwipeRefreshLayout;

    private int MAX_SIZE = 999;
    private String[] donorCell = new String[MAX_SIZE];
    private String mBlood = "", mCity = "", status = "";
    private ImageView imgNoData;


    public BloodDonorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blood_donor, container, false);

        getActivity().setTitle("Blood Donor");
        CustomList = (ListView) v.findViewById(R.id.listView);


        btnBloodGroup = v.findViewById(R.id.btn_blood_group);
        btnCity = v.findViewById(R.id.btn_city);
        btnSubmit = v.findViewById(R.id.btn_submit);
        mSwipeRefreshLayout = v.findViewById(R.id.swipeToRefresh);


        imgNoData = v.findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);


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
        btnCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String[] cityList = {"Dhaka", "Chittagong", "Sylhet", "Rajshahi", "Barishal", "Khulna", "Rangpur", "Mymensingh"};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);
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


        String url = Constant.SHOW_DONOR_URL + "?text=" + blood + "&city=" + city;
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
        final ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length() == 0) {
                Toasty.error(getActivity(), "No Donors Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String name = jo.getString(Constant.KEY_NAME);
                    String gender = jo.getString(Constant.KEY_GENDER);
                    String location = jo.getString(Constant.KEY_LOCATION);
                    String blood_group = jo.getString(Constant.KEY_BLOOD_GROUP);
                    String cell = jo.getString(Constant.KEY_CELL);
                    String donate_date = jo.getString(Constant.KEY_DONATE_DATE);
                    String image = jo.getString(Constant.KEY_PROFILE_IMAGE);

                    Log.d("image", image);


                    donorCell[i] = cell;
//                    PatientGender[i] = gender;

                    if (donate_date.equals("null")) {
                        donate_date = "N/A";

                        status = "Available";


                    } else {
                        String getDate = donate_date;


                        Calendar cal1 = new GregorianCalendar();
                        Calendar cal2 = new GregorianCalendar();

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        Date date2 = new Date();
                        String current_date = sdf.format(date2);

                        try {
                            Date date = sdf.parse(current_date);
                            cal1.setTime(date);
                            date = sdf.parse(getDate);
                            cal2.setTime(date);


                            int days = daysBetween(cal1.getTime(), cal2.getTime());
                            Log.d("NoDay:", "" + days);

                            if (days < 120) {
                                status = "Not Available";
                            } else {
                                status = "Available";
                            }

                        } catch (Exception e) {
                            //error
                        }


                    }
//


                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME, "Donor Name: " + name);
                    user_msg.put(Constant.KEY_LOCATION, "Location: " + location);
                    user_msg.put(Constant.KEY_GENDER, "Gender: " + gender);
                    user_msg.put(Constant.KEY_BLOOD_GROUP, blood_group);
                    user_msg.put(Constant.KEY_DONATE_DATE, "Last Donate: " + donate_date);
                    user_msg.put(Constant.KEY_PROFILE_IMAGE, image);
                    user_msg.put(Constant.KEY_STATUS, status);

                    list.add(user_msg);
                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        final ListAdapter adapter = new SimpleAdapter(
                getActivity(), list, R.layout.donor_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_GENDER, Constant.KEY_LOCATION, Constant.KEY_BLOOD_GROUP, Constant.KEY_DONATE_DATE, Constant.KEY_STATUS},
                new int[]{R.id.donor_name, R.id.txt_txt_donor_gender, R.id.txt_donor_location, R.id.txt_blood_group, R.id.txt_last_donate, R.id.txt_status}) {


            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;


                for (int i = 0; i < list.size(); i++) {


                    if (v == null) {
                        LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        v = vi.inflate(R.layout.donor_list_item, null);

                    }


                }


                TextView txtName = v.findViewById(R.id.donor_name);
                TextView txtGender = v.findViewById(R.id.txt_txt_donor_gender);
                TextView txtLocation = v.findViewById(R.id.txt_donor_location);
                TextView txtBloodGroup = v.findViewById(R.id.txt_blood_group);
                TextView txtDonateDate = v.findViewById(R.id.txt_last_donate);
                TextView txtStatus = v.findViewById(R.id.txt_status);
                ImageView profile_img = v.findViewById(R.id.list_donor_image);

                String image = list.get(position).get(Constant.KEY_PROFILE_IMAGE);
                // Log.d("image",image);


                if (image != null && image.length() > 4) {


                    String url = Constant.BASE_URL + "android/profile_image/" + image;
                    Glide.
                            with(getActivity())
                            .load(url)
                            .centerCrop()
                            .placeholder(R.drawable.loading)
                            .into(profile_img);


                } else {
                    profile_img.setImageResource(R.drawable.donor);
                }


                txtName.setText(list.get(position).get(Constant.KEY_NAME));
                txtGender.setText(list.get(position).get(Constant.KEY_GENDER));
                txtBloodGroup.setText(list.get(position).get(Constant.KEY_BLOOD_GROUP));
                txtLocation.setText(list.get(position).get(Constant.KEY_LOCATION));
                txtDonateDate.setText(list.get(position).get(Constant.KEY_DONATE_DATE));

                String getStatus = list.get(position).get(Constant.KEY_STATUS);

                if (getStatus.equals("Available")) {
                    txtStatus.setText("Status: " + list.get(position).get(Constant.KEY_STATUS));
                    txtStatus.setTextColor(Color.GREEN);

                } else {
                    txtStatus.setText("Status: " + list.get(position).get(Constant.KEY_STATUS));
                    txtStatus.setTextColor(Color.RED);

                }

                return v;


            }


        };
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

    private int daysBetween(Date d1, Date d2) {
        return (int) ((d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
    }

}

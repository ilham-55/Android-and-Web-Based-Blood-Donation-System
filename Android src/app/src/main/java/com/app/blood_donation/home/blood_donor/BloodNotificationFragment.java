package com.app.blood_donation.home.blood_donor;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import androidx.fragment.app.Fragment;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class BloodNotificationFragment extends Fragment {

    private ListView CustomList;

    private ProgressDialog loading;


    private int MAX_SIZE = 999;
    private String subArea[] = new String[MAX_SIZE];
    private String donorStatus[] = new String[MAX_SIZE];
    private String donorID[] = new String[MAX_SIZE];
    private String donorBloodGroup[] = new String[MAX_SIZE];
    private String donorAddress[] = new String[MAX_SIZE];
    private String donorDivision[] = new String[MAX_SIZE];
    private String donorBags[] = new String[MAX_SIZE];
    private String donorType[] = new String[MAX_SIZE];
    private String donorContact[] = new String[MAX_SIZE];

    private ImageView imgNoData;
    String UserCell;

    public BloodNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blood_notification, container, false);
        CustomList = (ListView) v.findViewById(R.id.listView);


        getActivity().setTitle("Blood Notification");
        imgNoData = v.findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);

        //Fetching cell from shared preferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;


        //call function
        getData();


        return v;
    }


    private void getData() {

        loading = new ProgressDialog(getActivity());
        loading.setMessage("Please wait....");
        loading.show();


        String url = Constant.BLOOD_NOTIFICATION_URL + "?cell=" + UserCell;
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
                Toasty.error(getActivity(), "No Data Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String bag = jo.getString(Constant.KEY_BAG);
                    String id = jo.getString(Constant.KEY_ID);
                    String address = jo.getString(Constant.KEY_ADDRESS);
                    String division = jo.getString(Constant.KEY_DIVISION);
                    String blood_group = jo.getString(Constant.KEY_BLOOD_GROUP);
                    String contact = jo.getString(Constant.KEY_CONTACT);
                    String type = jo.getString(Constant.KEY_TYPE);
                    String date = jo.getString(Constant.KEY_DATE);
                    String time = jo.getString(Constant.KEY_TIME);
                    String status = jo.getString(Constant.KEY_STATUS);
                    String sub_area = jo.getString(Constant.KEY_SUB_AREA);


                    if (status.equals("0")) {
                        status = "Not Received";

                    } else if (status.equals("1")) {
                        status = "Received";
                    }

                    donorID[i] = id;
                    donorStatus[i] = status;
                    donorBloodGroup[i] = blood_group;
                    donorAddress[i] = address;
                    donorBags[i] = bag;
                    donorContact[i] = contact;
                    donorType[i] = type;
                    donorDivision[i] = division;
                    subArea[i] = sub_area;


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
                getActivity(), list, R.layout.my_request_list_item,
                new String[]{Constant.KEY_TYPE, Constant.KEY_BAG, Constant.KEY_ADDRESS, Constant.KEY_BLOOD_GROUP, Constant.KEY_TIME, Constant.KEY_STATUS},
                new int[]{R.id.txt_type, R.id.txt_bag, R.id.txt_donor_location, R.id.txt_blood_group, R.id.txt_time_date, R.id.txt_status});
        CustomList.setAdapter(adapter);

    }
}
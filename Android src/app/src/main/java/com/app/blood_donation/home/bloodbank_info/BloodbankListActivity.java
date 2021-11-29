package com.app.blood_donation.home.bloodbank_info;

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

import androidx.appcompat.app.AppCompatActivity;
import es.dmoral.toasty.Toasty;

public class BloodbankListActivity extends AppCompatActivity {


    ListView CustomList;
    Button btnSearch;
    EditText etxtSearch;

    private ProgressDialog loading;
    int MAX_SIZE = 999;
    public String bloodbankCell[] = new String[MAX_SIZE];
    private ImageView imgNoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodbank_list);

        CustomList = findViewById(R.id.listView_bloodbank);
        btnSearch = findViewById(R.id.btn_submit_bloodbank);
        etxtSearch = findViewById(R.id.search_input_bloodbank);
        imgNoData = findViewById(R.id.img_no_data);

        imgNoData.setVisibility(View.INVISIBLE);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Blood bank List");


        //call function
        getData("");


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText = etxtSearch.getText().toString().trim();

                if (searchText.isEmpty()) {
                    Toasty.error(BloodbankListActivity.this, "Please input blood bank name or address!", Toast.LENGTH_SHORT).show();
                } else {
                    getData(searchText);
                }
            }
        });
    }


    private void getData(String s) {

        String getSearchText = s;
        //showing progress dialog
        loading = new ProgressDialog(this);
        loading.setMessage("Please wait....");
        loading.show();

        if (!s.isEmpty()) {
            getSearchText = s;
        }


        String url = Constant.BLOODBANK_LIST_URL + "?text=" + getSearchText;

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
                        Toasty.error(BloodbankListActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
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
                Toasty.error(BloodbankListActivity.this, "No Data Found!", Toast.LENGTH_SHORT).show();

                imgNoData.setImageResource(R.drawable.no_data);
                imgNoData.setVisibility(View.VISIBLE);


            } else {

                imgNoData.setVisibility(View.GONE);

                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);
                    String bloodbank_name = jo.getString(Constant.KEY_NAME);
                    String bloodbank_category = jo.getString(Constant.KEY_CATEGORY);
                    String bloodbank_address = jo.getString(Constant.KEY_ADDRESS);
                    String bloodbank_mobile = jo.getString(Constant.KEY_MOBILE);

                    bloodbankCell[i] = bloodbank_mobile;
                    if (bloodbank_address.equals("null")) {
                        bloodbank_address = "N/A";
                    }

                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME, bloodbank_name);
                    user_msg.put(Constant.KEY_CATEGORY, "Category : " + bloodbank_category);
                    user_msg.put(Constant.KEY_ADDRESS, "Address : " + bloodbank_address);
                    user_msg.put(Constant.KEY_PHONE, "Mobile : " + bloodbank_mobile);


                    list.add(user_msg);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                BloodbankListActivity.this, list, R.layout.bloodbank_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_CATEGORY, Constant.KEY_ADDRESS, Constant.KEY_PHONE},
                new int[]{R.id.bloodbank_name, R.id.bloodbank_category, R.id.bloodbank_address, R.id.bloodbank_mobile});
        CustomList.setAdapter(adapter);

        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                new AlertDialog.Builder(BloodbankListActivity.this)
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




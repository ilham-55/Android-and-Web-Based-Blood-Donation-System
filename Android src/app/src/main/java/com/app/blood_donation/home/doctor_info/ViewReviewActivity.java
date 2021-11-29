package com.app.blood_donation.home.doctor_info;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class ViewReviewActivity extends AppCompatActivity {


    ListView CustomList;
    private ProgressDialog loading;
    String cell, drCell,drId;
    SharedPreferences sharedPreferences;
    FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_review);

        CustomList = findViewById(R.id.listView);
        actionButton=findViewById(R.id.floatingActionButton);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Reviews");

        drCell = getIntent().getExtras().getString("dr_cell");
        drId = getIntent().getExtras().getString("dr_id");


        //Fetching cell from shared preferences
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");


        //call function
        getData("");

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ViewReviewActivity.this,ReviewActivity.class);
                intent.putExtra("dr_cell",drCell);
                intent.putExtra("dr_id",drId);
                startActivity(intent);
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


        String url = Constant.REVIEW_LIST_URL + drId;

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
                        Toasty.error(ViewReviewActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
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
                Toasty.info(ViewReviewActivity.this, "No Review Found!", Toast.LENGTH_SHORT).show();



            } else {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);


                    String rating = jo.getString(Constant.KEY_RATING);
                    String review = jo.getString(Constant.KEY_REVIEW);


                    HashMap<String, String> user_msg = new HashMap<>();

                    if (rating.isEmpty() || rating.equals("null")) {
                        //nothing
                    } else {
                        user_msg.put(Constant.KEY_REVIEW, review);
                        user_msg.put(Constant.KEY_RATING, "Rating: " + rating + " | 5.0");

                    }

                    list.add(user_msg);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter = new SimpleAdapter(
                ViewReviewActivity.this, list, R.layout.review_list_items,
                new String[]{Constant.KEY_REVIEW, Constant.KEY_RATING},
                new int[]{R.id.txt_review, R.id.txt_rating});
        CustomList.setAdapter(adapter);


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

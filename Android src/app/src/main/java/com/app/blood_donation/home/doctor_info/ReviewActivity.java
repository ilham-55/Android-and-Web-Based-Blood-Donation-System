package com.app.blood_donation.home.doctor_info;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
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
import com.app.blood_donation.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ReviewActivity extends AppCompatActivity {


    RatingBar ratingBar;
    EditText txtReview;
    TextView txtSubmit, txtRating;
    float getRating;
    String id, drCell,cell;
    SharedPreferences sharedPreferences;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Reviews");


        ratingBar = findViewById(R.id.rating);
        txtReview = findViewById(R.id.txt_review);

        txtSubmit = findViewById(R.id.txt_submit);
        txtRating = findViewById(R.id.txt_rating);

        //Fetching cell from shared preferences
        sharedPreferences = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");


        id = getIntent().getExtras().getString("dr_id");
        drCell = getIntent().getExtras().getString("dr_cell");

        getData();

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                Toast.makeText(ReviewActivity.this, "" + rating, Toast.LENGTH_SHORT).show();
                txtRating.setText("Rating: " + rating + " | 5.0");
                getRating = rating;
            }
        });

        txtSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reviewSubmit(getRating);

            }
        });

    }


    //for submit review
    public void reviewSubmit(float getRating) {

        final String review = txtReview.getText().toString();
        final String rating = "" + getRating;


        if (review.isEmpty()) {
            txtReview.setError("Please write review");
            txtReview.requestFocus();
        } else {

            loading = new ProgressDialog(this);
            loading.setMessage("Review Submit...Please wait...");
            loading.show();

            String URL = Constant.ADD_REVIEW_URL;

            //Creating a string request
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {


                            //for track response in logcat
                            Log.d("RESPONSE", response);


                            //If we are getting success from server
                            if (response.equals("success")) {

                                loading.dismiss();
                                //Starting profile activity

                                Toasty.success(ReviewActivity.this, "Review Successfully Submitted!", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(ReviewActivity.this,DoctorsListActivity.class);
                                startActivity(intent);
                                finish();

                            }


                            //If we are getting success from server
                            else if (response.equals("failure")) {

                                loading.dismiss();

                                Toasty.error(ReviewActivity.this, "Fail!", Toast.LENGTH_SHORT).show();


                            } else {

                                loading.dismiss();
                                Toast.makeText(ReviewActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                            }

                        }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //You can handle error here if you want

                            Toast.makeText(ReviewActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                            loading.dismiss();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    //Adding parameters to request

                    params.put(Constant.KEY_REVIEW, review);
                    params.put(Constant.KEY_RATING, rating);
                    params.put(Constant.KEY_ID, id);
                    params.put(Constant.KEY_CELL, cell);
                    params.put(Constant.KEY_DR_CELL, drCell);


                    Log.d("ID", id + " " + rating + " " + review);

                    //returning parameter
                    return params;
                }
            };


            //Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(ReviewActivity.this);
            requestQueue.add(stringRequest);
        }

    }


    private void getData() {

        //showing progress dialog
        loading = new ProgressDialog(ReviewActivity.this);
        loading.setMessage("Please wait....");
        loading.show();


        String url = Constant.GET_REVIEW_URL + id+"&&user_cell="+cell;;  // url for connecting php file
        Log.d("Review", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("Volley Error", "Error:" + error);
                        Toasty.error(ReviewActivity.this, "No Internet Connection!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ReviewActivity.this);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        Log.d("RESPONSE", response);

        String review = "";
        String rating = "";


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);
            JSONObject ProfileData = result.getJSONObject(0);

            review = ProfileData.getString(Constant.KEY_REVIEW);
            rating = ProfileData.getString(Constant.KEY_RATING);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //textViewResult.setText("Name:\t"+first_name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);


        if (review.isEmpty() || review.equals("null")) {
            txtReview.setText("");
        } else {
            txtReview.setText(review);
        }


        if (rating.isEmpty() || rating.equals("null")) {
            txtRating.setText("");
        } else {
            txtRating.setText("Rating: " + rating + " | 5.0");
            ratingBar.setRating(Float.parseFloat(rating));

        }


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
package com.app.blood_donation.home.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import es.dmoral.toasty.Toasty;

public class ProfileActivity extends AppCompatActivity {

    TextView txtEdit,txtDonateDate,txtName,txtName2,txtGender,txtProfession,txtCell,txtEmail,txtLocation,txtsubarea,txtPassword;

    String UserCell;
    String profileImage="";

    private ProgressDialog loading;

    SharedPreferences sharedPreferences;

    ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Fetching cell from shared preferences
        sharedPreferences =getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String cell = sharedPreferences.getString(Constant.CELL_SHARED_PREF, "Not Available");
        UserCell = cell;



        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Profile");


        txtName=findViewById(R.id.txtName);
        txtName2=findViewById(R.id.txtName2);
        txtProfession=findViewById(R.id.txt_profession);
        txtCell=findViewById(R.id.txtCell);
        txtGender=findViewById(R.id.txtGender);

        txtLocation=findViewById(R.id.txtLocation);
        //txtsubarea=findViewById(R.id.txtLocation_subarea);
        txtDonateDate=findViewById(R.id.txt_donate_date1);


        profilePic=findViewById(R.id.profile_image);
        txtEdit=findViewById(R.id.txtEdit);

        Typeface tf = Typeface.createFromAsset(getAssets(), "AsapCondensed-Regular.ttf");
        //txtTitle.setTypeface(tf);
        txtName.setTypeface(tf);
        txtName2.setTypeface(tf);
        txtProfession.setTypeface(tf);
        txtCell.setTypeface(tf);
        txtGender.setTypeface(tf);
        txtDonateDate.setTypeface(tf);


        txtEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("name",txtName.getText());
                intent.putExtra("cell",txtCell.getText());
                intent.putExtra("location",txtLocation.getText());
                intent.putExtra("gender",txtGender.getText());
                intent.putExtra("donate_date",txtDonateDate.getText());
                intent.putExtra("image",profileImage);
                startActivity(intent);
            }
        });

        //call function
        getData();

    }



    private void getData() {

        //loading = ProgressDialog.show(this,"Please wait...","Fetching...",false,false);

        //showing progress dialog
        loading = new ProgressDialog(ProfileActivity.this);
        loading.setMessage("Please wait....");
        loading.show();


        String url = Constant.DONOR_PROFILE_URL+ UserCell;  // url for connecting php file

        Log.d("URL",url);
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

                        Log.d("Volley Error","Error:"+error);
                        Toasty.error(ProfileActivity.this, "No Internet Connection!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(ProfileActivity.this);
        requestQueue.add(stringRequest);
    }



    private void showJSON(String response) {


        Log.d("RESPONSE",response);

        String name = "";
        String gender = "";
        String cell = "";
        String location="";
        String donate_date="";


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);
            JSONObject ProfileData = result.getJSONObject(0);

            name = ProfileData.getString(Constant.KEY_NAME);
            gender = ProfileData.getString(Constant.KEY_GENDER);
            cell = ProfileData.getString(Constant.KEY_CELL);
            location = ProfileData.getString(Constant.KEY_LOCATION);
            donate_date = ProfileData.getString(Constant.KEY_DONATE_DATE);
            profileImage= ProfileData.getString(Constant.KEY_PROFILE_IMAGE);




        } catch (JSONException e) {
            e.printStackTrace();
        }

        //textViewResult.setText("Name:\t"+name+"\nAddress:\t" +address+ "\nVice Chancellor:\t"+ vc);

        txtName.setText(name);
        txtName2.setText(name);
        txtGender.setText(gender);

        if (gender.equals("Female"))
        {
            profilePic.setImageResource(R.drawable.girl);
        }


        if (donate_date.equals("null"))
        {
            txtDonateDate.setText("N/A");
        }
        else
        {
            txtDonateDate.setText(donate_date);
        }


        txtCell.setText(cell);
        txtLocation.setText(location);

        if (!profileImage.isEmpty())
        {
            Picasso.with(this)
                    .load(Constant.LOAD_PROFILE_IMAGE_URL+profileImage)
                    .placeholder(R.drawable.donor)
                    .error(R.drawable.donor)
                    .into(profilePic);
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


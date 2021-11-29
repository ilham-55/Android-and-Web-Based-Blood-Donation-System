package com.app.blood_donation.admin.users;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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
import com.app.blood_donation.admin.AdminHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class UserActivity extends AppCompatActivity {



    ListView CustomList;
    Button btnSearch;
    EditText etxtSearch;

    private ProgressDialog loading;
    int MAX_SIZE=999;
    public String userID[]=new String[MAX_SIZE];




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        CustomList=findViewById(R.id.listView);
        btnSearch=findViewById(R.id.btnSearch);
        etxtSearch=findViewById(R.id.etxt_search);


        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("All User List");



        //call function
        getData("");



        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText=etxtSearch.getText().toString().trim();

                if (searchText.isEmpty())
                {
                    Toasty.error(UserActivity.this, "Please input text!", Toast.LENGTH_SHORT).show();
                }

                else {
                    getData(searchText);
                }
            }
        });
    }



    private void getData(String s) {

        String getSearchText=s;
        //showing progress dialog
        loading = new ProgressDialog(this);
        loading.setMessage("Please wait....");
        loading.show();

        if (!s.isEmpty()) {
            getSearchText = s;
        }


        String url = Constant.ALL_USER_LIST_URL+"?text="+getSearchText;

        Log.d("URL",url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Response",response);
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        loading.dismiss();
                        Toasty.error(UserActivity.this, "Network Error!", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String response) {


        Log.d("Response 2",response);

        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray(Constant.JSON_ARRAY);

            if (result.length()==0)
            {
                Toasty.error(UserActivity.this, "No User Found!", Toast.LENGTH_SHORT).show();
                finish();

            }

            else {
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jo = result.getJSONObject(i);

                    String id = jo.getString(Constant.KEY_ID);
                    String name = jo.getString(Constant.KEY_NAME);
                    String cell = jo.getString(Constant.KEY_CELL);
                    String location = jo.getString(Constant.KEY_LOCATION);



                    userID[i]=id;

                    HashMap<String, String> user_msg = new HashMap<>();
                    user_msg.put(Constant.KEY_NAME, name);
                    user_msg.put(Constant.KEY_CELL, "Contact: "+cell);
                    user_msg.put(Constant.KEY_LOCATION, "Location: "+location);





                    list.add(user_msg);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                UserActivity.this, list, R.layout.user_list_item,
                new String[]{Constant.KEY_NAME, Constant.KEY_CELL,Constant.KEY_LOCATION},
                new int[]{R.id.txt_name, R.id.txt_cell,R.id.et_location});
        CustomList.setAdapter(adapter);


        CustomList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);

                new AlertDialog.Builder(UserActivity.this)
                        .setMessage("Want to delete user  ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {


                                // Perform Your Task Here--When Yes Is Pressed.
                                deleteUser(userID[position]);
                                dialog.cancel();


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





    //update contact method
    public void deleteUser(final String id) {




        loading = new ProgressDialog(this);
        // loading.setIcon(R.drawable.wait_icon);
        loading.setTitle("Update");
        loading.setMessage("Please wait....");
        loading.show();

        String URL = Constant.DELETE_USER_URL;


        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //for track response in logcat
                        Log.d("RESPONSE", response);
                        // Log.d("RESPONSE", userCell);

                        String getResponse=response.trim();

                        //If we are getting success from server
                        if (getResponse.equals("success")) {

                            loading.dismiss();
                            //Starting profile activity

                            Intent intent = new Intent(UserActivity.this, AdminHome.class);
                            Toasty.success(UserActivity.this, " Deleted !", Toast.LENGTH_SHORT).show();


                            startActivity(intent);

                        }


                        //If we are getting success from server
                        else if (getResponse.equals("failure")) {

                            loading.dismiss();
                            //Starting profile activity

                            //  Intent intent = new Intent(UserActivity.this, FarmerOrderActivity.class);
                            Toast.makeText(UserActivity.this, "Delete fail!", Toast.LENGTH_SHORT).show();
                            //startActivity(intent);

                        } else {

                            loading.dismiss();
                            Toast.makeText(UserActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want

                        Toast.makeText(UserActivity.this, "No Internet Connection or \nThere is an error !!!", Toast.LENGTH_LONG).show();
                        loading.dismiss();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request

                params.put(Constant.KEY_ID, id);
                params.put(Constant.KEY_STATUS, "1"); //1 for approved


                //returning parameter
                return params;
            }
        };


        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(UserActivity.this);
        requestQueue.add(stringRequest);


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



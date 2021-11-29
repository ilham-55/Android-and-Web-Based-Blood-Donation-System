package com.app.blood_donation.MainPage;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.blood_donation.home.plasma.PlasmaActivity;
import com.google.android.material.navigation.NavigationView;
import com.app.blood_donation.AppInfo.AboutUsActivity;
import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
import com.app.blood_donation.WelcomePage.RegistrationInfo.LoginActivity;
import com.app.blood_donation.home.Maps.MapsActivity;
import com.app.blood_donation.home.ambulance_info.AmbulanceListActivity;
import com.app.blood_donation.home.blood_donor.DonorHomeActivity;
import com.app.blood_donation.home.blood_info.BloodInfoActivity;
import com.app.blood_donation.home.bloodbank_info.BloodbankListActivity;
import com.app.blood_donation.home.doctor_info.DoctorsMainActivity;
import com.app.blood_donation.home.hospital_info.HospitalsListActivity;
import com.app.blood_donation.home.profile.ProfileActivity;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;
import es.dmoral.toasty.Toasty;
import spencerstudios.com.bungeelib.Bungee;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    CardView cardPlasma, cardAmbulance, cardBloodBank, cardBloodDonor, cardProfile, cardHospital, cardDoctor, cardBloodInfo;

    TextView txtProfile, txtNewsfeed, txtHospital, txtDoctor, txtBloodbank, txtAmbulance, txtBloodinfo, txtLogout;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    private AppBarConfiguration mAppBarConfiguration;
    //for double back press to exit
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getSupportActionBar().setTitle("Home Page");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtProfile = findViewById(R.id.txt_mainProfile);
        txtNewsfeed = findViewById(R.id.txt_mainNewsfeed);
        txtHospital = findViewById(R.id.txt_mainHospital);
        txtDoctor = findViewById(R.id.txt_mainDoctor);
        txtBloodbank = findViewById(R.id.txt_mainBloodbank);
        txtAmbulance = findViewById(R.id.txt_mainAmbulance);
        txtBloodinfo = findViewById(R.id.txt_mainBloodinfo);
        txtLogout = findViewById(R.id.txt_mainLogout);
        cardPlasma=findViewById(R.id.card_plasma);


        Typeface tf = Typeface.createFromAsset(getAssets(), "AsapCondensed-Regular.ttf");
        Typeface tf1 = Typeface.createFromAsset(getAssets(), "CaviarDreams.ttf");
        //txtTitle.setTypeface(tf);
        txtProfile.setTypeface(tf1);
        txtNewsfeed.setTypeface(tf1);
        txtHospital.setTypeface(tf1);
        txtDoctor.setTypeface(tf1);
        txtBloodbank.setTypeface(tf1);
        txtAmbulance.setTypeface(tf1);
        txtBloodinfo.setTypeface(tf1);
        txtLogout.setTypeface(tf1);


        cardProfile = findViewById(R.id.card_mainProfile);
        cardBloodDonor = findViewById(R.id.card_mainNewsfeed);
        cardHospital = findViewById(R.id.card_mainHospital);
        cardDoctor = findViewById(R.id.card_mainDoctorsInfo);
        cardBloodInfo = findViewById(R.id.card_mainBloodinformation);
        cardBloodBank = findViewById(R.id.card_mainBloodbank);
        cardAmbulance = findViewById(R.id.card_mainAmbulance);


        sp = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();


        if (Build.VERSION.SDK_INT >= 23) //Android MarshMellow Version or above
        {
            requestAllPermission();
        }


        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                Bungee.diagonal(MainActivity.this);
            }
        });
        cardHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HospitalsListActivity.class);
                startActivity(intent);
                Bungee.card(MainActivity.this);

            }
        });


        cardBloodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BloodInfoActivity.class);
                startActivity(intent);
                Bungee.fade(MainActivity.this);

            }
        });

        cardBloodDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DonorHomeActivity.class);
                startActivity(intent);
                Bungee.inAndOut(MainActivity.this);

            }
        });
        cardDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DoctorsMainActivity.class);
                startActivity(intent);
                Bungee.shrink(MainActivity.this);

            }
        });
        cardAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AmbulanceListActivity.class);
                startActivity(intent);
                Bungee.zoom(MainActivity.this);

            }
        });
        cardBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BloodbankListActivity.class);
                startActivity(intent);
                Bungee.swipeLeft(MainActivity.this);

            }
        });


        cardPlasma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlasmaActivity.class);
                startActivity(intent);
                Bungee.swipeLeft(MainActivity.this);

            }
        });

//        cardLogout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setMessage("Logout from your account ?")
//                        .setCancelable(false)
//                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//
//
//                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                                startActivity(intent);
//
//                                dialog.cancel();
//
//                            }
//                        })
//                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Perform Your Task Here--When No is pressed
//                                dialog.cancel();
//                            }
//                        }).show();
//
//            }
//        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //for showing default fragment
        if (savedInstanceState == null) {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_exit) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Exit from App ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {


                            // Perform Your Task Here--When Yes Is Pressed.
                            editor.clear(); //for clear shared preference file
                            editor.apply();
                            finishAffinity(); //For API 16+, use
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
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);


        } else if (id == R.id.nav_newsfeed) {
            Intent intent = new Intent(MainActivity.this, DonorHomeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_findhospital) {
            Intent intent = new Intent(MainActivity.this, HospitalsListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_neerbyhospital) {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("type", "hospital");
            startActivity(intent);

        } else if (id == R.id.nav_finddoctor) {

            Intent intent = new Intent(MainActivity.this, DoctorsMainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_findambulance) {
            Intent intent = new Intent(MainActivity.this, AmbulanceListActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_donationtips) {
            Intent intent = new Intent(MainActivity.this, BloodInfoActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_aboutus) {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String subject = "Blood Donation Online Blood Sharing Platform";
            String body = "This app very useful for blood donation, request for blood, manage own request, search Hospital, search specialist doctors, ambulance, blood banks ect.";
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intent, "Shared with"));

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void requestAllPermission() {
        Dexter.withActivity(this)
                .withPermissions(

                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // Toast.makeText(getApplicationContext(), "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        //Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    //double backpress to exit
    @Override
    public void onBackPressed() {


        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {

            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            finish();

        } else {
            Toasty.info(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}


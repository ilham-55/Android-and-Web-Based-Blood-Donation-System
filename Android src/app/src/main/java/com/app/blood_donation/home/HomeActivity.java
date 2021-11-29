package com.app.blood_donation.home;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.app.blood_donation.Constant;
import com.app.blood_donation.R;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import es.dmoral.toasty.Toasty;

public class HomeActivity extends AppCompatActivity {

    CardView cardLogout, cardAmbulance, cardBloodBank, cardBloodDonor, cardProfile, cardHospital, cardHospitalAR, cardBloodInfo;

    SharedPreferences sp;
    SharedPreferences.Editor editor;


    //for double back press to exit
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Admin Panel");

        cardProfile = findViewById(R.id.card_profile);
        cardBloodDonor = findViewById(R.id.card_blood_donor);
        cardHospital = findViewById(R.id.card_hospital);
        cardHospitalAR = findViewById(R.id.card_hospital_ar);
        cardBloodInfo = findViewById(R.id.card_blood_info);
        cardBloodBank = findViewById(R.id.card_blood_bank);
        cardAmbulance = findViewById(R.id.card_ambulance);
        cardLogout = findViewById(R.id.card_logout);

        sp = getSharedPreferences(Constant.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sp.edit();


        if (Build.VERSION.SDK_INT >= 23) //Android MarshMellow Version or above
        {
            requestAllPermission();
        }


        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        cardHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HospitalsListActivity.class);
                startActivity(intent);
            }
        });


        cardBloodInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BloodInfoActivity.class);
                startActivity(intent);
            }
        });

        cardBloodDonor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DonorHomeActivity.class);
                startActivity(intent);
            }
        });
        cardHospitalAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DoctorsMainActivity.class);
                startActivity(intent);
            }
        });
        cardAmbulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AmbulanceListActivity.class);
                startActivity(intent);
            }
        });
        cardBloodBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, BloodbankListActivity.class);
                startActivity(intent);
            }
        });

        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                builder.setMessage("Logout from app ?")
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
        });


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
            super.onBackPressed();

            finishAffinity();//to use for exit from whole app and finish all activity

        } else {
            Toasty.warning(this, "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();

        }
        back_pressed = System.currentTimeMillis();
    }
}

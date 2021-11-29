package com.app.blood_donation.home.blood_donor;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.app.blood_donation.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class DonorHomeActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //declare fragment
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //handle operation or what you want to do
                    fragment = new BloodDonorFragment();
                    break;

                case R.id.navigation_dashboard:
                    //handle operation or what you want to do
                    fragment = new BloodNewsFragment();
                    break;

                case R.id.navigation_notifications:

                    fragment = new BloodRequestFragment();
                    break;

                case R.id.navigation_my_request:

                    fragment = new MyRequestFragment();
                    break;

                case R.id.navigation_blood_notification:

                    fragment = new BloodNotificationFragment();
                    break;




            }

            return loadFragment(fragment); //call load fragment function
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);

        //loading the default fragment
        loadFragment(new BloodDonorFragment());


        BottomNavigationView navigation = findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    //loadFragment function
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

package com.app.blood_donation.AppInfo;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.app.blood_donation.R;


public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("About Us");

//        simulateDayNight(/* DAY */ 0);
//        Element adsElement = new Element();
//        adsElement.setTitle("Advertise with us");
//
//        View aboutPage = new AboutPage(this)
//                .isRTL(false)
//                .setImage(R.drawable.ic_teleblood_squre_icon)
//                .addItem(new Element().setTitle("Version 1.0.0"))
//                .setDescription("This app very useful for blood donation, request for blood, manage own request, search Hospital, search specialist doctors, ambulance, blood banks ect.")
//                .addGroup("Connect with us")
//                .addEmail("habibcse009@gmail.com")
//                .addWebsite("http://bloodbank24.cf")
//                .addFacebook("habibcse009")
//                .addTwitter("habibcse009")
//                .addYoutube("UC8mYj05k_tUd7fzKweQ41EQ")
//                .addInstagram("habibcse009")
//                .addGitHub("habibcse009")
//                .addItem(getCopyRightsElement())
//                .create();
//
//        setContentView(aboutPage);
//    }
//
//
//    Element getCopyRightsElement() {
//        Element copyRightsElement = new Element();
//        final String copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR));
//        copyRightsElement.setTitle(copyrights);
//        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right);
//        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color);
//        copyRightsElement.setIconNightTint(android.R.color.white);
//        copyRightsElement.setGravity(Gravity.CENTER);
//        copyRightsElement.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(AboutUsActivity.this, copyrights, Toast.LENGTH_SHORT).show();
//            }
//        });
//        return copyRightsElement;
//    }
//
//    void simulateDayNight(int currentSetting) {
//        final int DAY = 0;
//        final int NIGHT = 1;
//        final int FOLLOW_SYSTEM = 3;
//
//        int currentNightMode = getResources().getConfiguration().uiMode
//                & Configuration.UI_MODE_NIGHT_MASK;
//        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_NO);
//        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_YES);
//        } else if (currentSetting == FOLLOW_SYSTEM) {
//            AppCompatDelegate.setDefaultNightMode(
//                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//        }
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

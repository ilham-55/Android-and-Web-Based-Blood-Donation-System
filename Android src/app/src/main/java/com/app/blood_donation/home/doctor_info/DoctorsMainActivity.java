package com.app.blood_donation.home.doctor_info;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.app.blood_donation.R;

public class DoctorsMainActivity extends AppCompatActivity {
    CardView cardSearchManually,cardSearchSpecialist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_main);

        getSupportActionBar().setHomeButtonEnabled(true); //for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//for back button
        getSupportActionBar().setTitle("Doctor's Search List");

        cardSearchManually= findViewById(R.id.card_search_manually);
        cardSearchSpecialist=findViewById(R.id.card_search_specialist);

        cardSearchManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorsMainActivity.this, DoctorsActivity.class);
                startActivity(intent);
            }
        });
        cardSearchSpecialist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DoctorsMainActivity.this, DoctorsListActivity.class);
                startActivity(intent);
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

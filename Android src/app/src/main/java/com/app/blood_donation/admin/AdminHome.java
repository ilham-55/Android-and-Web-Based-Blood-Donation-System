package com.app.blood_donation.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.blood_donation.R;
import com.app.blood_donation.admin.add_dr_to_hospital.AddDrToHospital;
import com.app.blood_donation.admin.blood_news.BloodNewsActivity;
import com.app.blood_donation.admin.users.UserActivity;

public class AdminHome extends AppCompatActivity {

    CardView cardBloodNews,cardDrToHospital,cardUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        cardBloodNews=findViewById(R.id.card_blood_news);
        cardDrToHospital=findViewById(R.id.card_add_dr_to_hospital);
        cardUsers=findViewById(R.id.card_users);


        cardBloodNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHome.this, BloodNewsActivity.class);
                startActivity(intent);

            }
        });



        cardDrToHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHome.this, AddDrToHospital.class);
                startActivity(intent);

            }
        });


        cardUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AdminHome.this, UserActivity.class);
                startActivity(intent);

            }
        });
    }
}

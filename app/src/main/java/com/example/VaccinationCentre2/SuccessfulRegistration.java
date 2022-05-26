package com.example.VaccinationCentre2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class SuccessfulRegistration extends AppCompatActivity {
    TextView textView;
    Button button3;
    DrawerLayout my_drawer_layout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_registration);
        textView= findViewById(R.id.textView);
        button3= findViewById(R.id.button3);
        my_drawer_layout= findViewById(R.id.my_drawer_layout);
        nav_view= findViewById(R.id.nav_view);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SuccessfulRegistration.this, WelcomePage.class);
                startActivity(intent);
            }
        });
        //NAVIGATION DRAWER
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, my_drawer_layout, R.string.nav_open, R.string.nav_close);
        my_drawer_layout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id == R.id.dashboard){
                    Toast.makeText(SuccessfulRegistration.this, "Home", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(SuccessfulRegistration.this,Dashboard.class);
                    startActivity(intent1);
                }
                if (id == R.id.vaccinationCentres){
                    Toast.makeText(SuccessfulRegistration.this, "Vaccination Centres", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(SuccessfulRegistration.this, VaccinationCentres.class);
                    startActivity(intent1);
                }
                if(id==R.id.newsroom){
                    Toast.makeText(SuccessfulRegistration.this, "News Room", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(SuccessfulRegistration.this, NewsRoom.class);
                    startActivity(intent2);
                }
                if(id==R.id.vaccinationRegistration){
                    Toast.makeText(SuccessfulRegistration.this, "Vaccination Registration", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(SuccessfulRegistration.this, SuccessfulRegistration.class);
                    startActivity(intent3);
                }
                if(id==R.id.logout){
                    Toast.makeText(SuccessfulRegistration.this, "Log Out", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(SuccessfulRegistration.this, WelcomePage.class);
                    startActivity(intent3);
                }

                return true;
            }
        });

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;

        }
        return super.onOptionsItemSelected(item);

    }
}
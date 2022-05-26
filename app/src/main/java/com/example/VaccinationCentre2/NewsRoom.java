package com.example.VaccinationCentre2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NewsRoom extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_room);


        //NAVIGATION DRAWER
        drawerLayout = findViewById(R.id.my_drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
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
                    Toast.makeText(NewsRoom.this, "Home", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(NewsRoom.this,Dashboard.class);
                    startActivity(intent1);
                }
                if (id == R.id.vaccinationCentres){
                    Toast.makeText(NewsRoom.this, "Vaccination Centres", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(NewsRoom.this, VaccinationCentres.class);
                    startActivity(intent1);
                }
                if(id==R.id.newsroom){
                    Toast.makeText(NewsRoom.this, "News Room", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(NewsRoom.this, NewsRoom.class);
                    startActivity(intent2);
                }
                if(id==R.id.vaccinationRegistration){
                    Toast.makeText(NewsRoom.this, "Vaccination Registration", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(NewsRoom.this, VaccineRegistration.class);
                    startActivity(intent3);
                }
                if(id==R.id.logout){
                    Toast.makeText(NewsRoom.this, "Log Out", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(NewsRoom.this, WelcomePage.class);
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
package com.example.VaccinationCentre2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public NavigationView nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout);
        //me
        nav_view=findViewById(R.id.nav_view);
        AppBarConfiguration mAppBarConfiguration= new AppBarConfiguration.Builder(
                R.id.vaccinationCentres, R.id.newsroom, R.id.vaccinationRegistration).setDrawerLayout(drawerLayout).build();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        // to make the Navigation drawer icon always appear on the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id == R.id.dashboard){
                    Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Dashboard.this,Dashboard.class);
                    startActivity(intent1);
                }
                if (id == R.id.vaccinationCentres){
                    Toast.makeText(Dashboard.this, "Vaccination Centres", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Dashboard.this, VaccinationCentres.class);
                    startActivity(intent1);
                }
                if(id==R.id.newsroom){
                    Toast.makeText(Dashboard.this, "News Room", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(Dashboard.this, NewsRoom.class);
                    startActivity(intent2);
                }
                if(id==R.id.vaccinationRegistration){
                    Toast.makeText(Dashboard.this, "Vaccination Registration", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(Dashboard.this, VaccineRegistration.class);
                    startActivity(intent3);
                }
                if(id==R.id.logout){
                    Toast.makeText(Dashboard.this, "Log Out", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(Dashboard.this, WelcomePage.class);
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
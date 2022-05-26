package com.example.VaccinationCentre2;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class VaccineRegistration extends AppCompatActivity {
    TextView personalDetails,contactInfo, nextofKin, residence, condition, selectVaccine, selectDose;;
    EditText firstName, middleName, lastName, dateofBirth, idNumber, occupation, email, phone, nextofKinName, nextofKinPhone, relationship, nextofKinEmail, county, subcounty, preexistingCondition, allergy, disability;
    Spinner spinner, idType,spinner3, spinner4, religion;
    String[] gender= new String[] {"Gender","Female", "Male"};
    String[] vaccines= new String[] {"Vaccine Types","Johnson and Johnson", "Pfizer", "Astrazeneca","Moderna"};
    String[] dose= new String[] {"SelectDose","1","2"};
    String[] religionArray= new String [] {"Religion","Christianity", "Islam", "Hinduism" ,"Buddhism"};
    Button button;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    String[] idtype= new String[] {"ID Type","National ID", "Passport ID", "Alien ID"};
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView nav_view;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    RegisteredUserHelper registeredUserHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_registration);
        personalDetails=findViewById(R.id.personalDetails);
        firstName=findViewById(R.id.firstName);
        middleName=findViewById(R.id.middleName);
        lastName=findViewById(R.id.lastName);
        spinner=findViewById(R.id.spinner);
        dateofBirth=findViewById(R.id.dateofBirth);
        button=findViewById(R.id.button);
        idType=findViewById(R.id.idType);
        idNumber=findViewById(R.id.idNumber);
        occupation=findViewById(R.id.occupation);
        religion=findViewById(R.id.religion);

        contactInfo=findViewById(R.id.contactInfo);
        nextofKin=findViewById(R.id.contactInfo);
        residence=findViewById(R.id.contactInfo);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        nextofKinName=findViewById(R.id.nextofKinName);
        nextofKinPhone=findViewById(R.id.nextofKinPhone);
        relationship=findViewById(R.id.relationship);
        nextofKinEmail=findViewById(R.id.nextofKinEmail);
        county=findViewById(R.id.county);
        subcounty=findViewById(R.id.subcounty);

        condition=findViewById(R.id.condition);
        selectVaccine=findViewById(R.id.selectVaccine);
        selectDose=findViewById(R.id.selectDose);
        preexistingCondition=findViewById(R.id.preexistingCondition);
        allergy=findViewById(R.id.allergy);
        disability=findViewById(R.id.disability);
        spinner3= findViewById(R.id.spinner3);
        spinner4= findViewById(R.id.spinner4);
        //Gender drop down list
        ArrayAdapter<String> myAdapter=new ArrayAdapter<>(VaccineRegistration.this,android.R.layout.simple_list_item_1,gender);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        //set the Vaccines Spinner
        ArrayAdapter<String> vaccineAdapter=new ArrayAdapter<>(VaccineRegistration.this,android.R.layout.simple_list_item_1,vaccines);
        vaccineAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(vaccineAdapter);
        //set the Dose Spinner
        ArrayAdapter<String> doseAdapter=new ArrayAdapter<>(VaccineRegistration.this,android.R.layout.simple_list_item_1,dose);
        doseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(doseAdapter);
        //set ID Type Spinner
        ArrayAdapter<String> idAdapter=new ArrayAdapter<>(VaccineRegistration.this,android.R.layout.simple_list_item_1,idtype);
        idAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idType.setAdapter(idAdapter);
        //set Religion Spinner
        ArrayAdapter<String> religionAdapter=new ArrayAdapter<>(VaccineRegistration.this,android.R.layout.simple_list_item_1,religionArray);
        religionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        religion.setAdapter(religionAdapter);

        dateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //declares the pop up dialog box
                DatePickerDialog dialog = new DatePickerDialog(
                        VaccineRegistration.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                dateofBirth.setText(date);
            }
        };
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Registered Citizens");
        registeredUserHelper= new RegisteredUserHelper();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get all the values
                String fNameString= firstName.getEditableText().toString().trim();
                String mNameString= middleName.getEditableText().toString().trim();
                String lNameString= lastName.getEditableText().toString().trim();
                String dobString= dateofBirth.getEditableText().toString().trim();
                String genderString= spinner.getSelectedItem().toString().trim();
                String idtypeString= idType.getSelectedItem().toString().trim();
                String idnumberString= idNumber.getEditableText().toString().trim();
                String OccupationString= occupation.getEditableText().toString().trim();
                String ReligionString= religion.getSelectedItem().toString().trim();
                String emailString= email.getEditableText().toString().trim();
                String phoneString= phone.getEditableText().toString().trim();
                String nextofKinNameString= nextofKinName.getEditableText().toString().trim();
                String nextofKinPhoneString= nextofKinPhone.getEditableText().toString().trim();
                String relationshipString= relationship.getEditableText().toString().trim();
                String nextofKinEmailString= nextofKinEmail.getEditableText().toString().trim();
                String countyString= county.getEditableText().toString().trim();
                String subCountyString= subcounty.getEditableText().toString().trim();
                String conditionString= preexistingCondition.getEditableText().toString().trim();
                String allergyString= allergy.getEditableText().toString().trim();
                String disabilityString= disability.getEditableText().toString().trim();
                String vaccineString= spinner3.getSelectedItem().toString().trim();
                String doseString= spinner4.getSelectedItem().toString().trim();

                if(TextUtils.isEmpty(fNameString)&&TextUtils.isEmpty(mNameString)&&TextUtils.isEmpty(lNameString)&&TextUtils.isEmpty(dobString)&&TextUtils.isEmpty(genderString)&&TextUtils.isEmpty(idtypeString)&&TextUtils.isEmpty(idnumberString)&&TextUtils.isEmpty(OccupationString)&&TextUtils.isEmpty(ReligionString)&&TextUtils.isEmpty(emailString)&&TextUtils.isEmpty(phoneString)&&TextUtils.isEmpty(nextofKinNameString)&&TextUtils.isEmpty(nextofKinPhoneString)&&TextUtils.isEmpty(relationshipString)&&TextUtils.isEmpty(nextofKinEmailString)&&TextUtils.isEmpty(countyString)&&TextUtils.isEmpty(subCountyString)&&TextUtils.isEmpty(conditionString)&&TextUtils.isEmpty(allergyString)&&TextUtils.isEmpty(disabilityString)&&TextUtils.isEmpty(vaccineString)&&TextUtils.isEmpty(doseString)) {
                    Toast.makeText(VaccineRegistration.this, "Please fill all fields.", Toast.LENGTH_SHORT).show();
                } else{
                    addDatatoFirebase(fNameString,lNameString,mNameString,dobString, genderString, idtypeString, idnumberString, OccupationString, ReligionString,emailString,phoneString,nextofKinNameString,nextofKinPhoneString, relationshipString, nextofKinEmailString, countyString, subCountyString, conditionString, allergyString, disabilityString, vaccineString, doseString);
                    Intent intent3= new Intent(getApplicationContext(),SuccessfulRegistration.class);
                    startActivity(intent3);
                    finish();

                }

                firstName.getText().clear();
                middleName.getText().clear();
                lastName.getText().clear();
                dateofBirth.getText().clear();
                idNumber.getText().clear();
                occupation.getText().clear();
                email.getText().clear();
                phone.getText().clear();
                nextofKinName.getText().clear();
                nextofKinPhone.getText().clear();
                relationship.getText().clear();
                nextofKinEmail.getText().clear();
                county.getText().clear();
                subcounty.getText().clear();
                preexistingCondition.getText().clear();
                allergy.getText().clear();
                disability.getText().clear();
                spinner.setAdapter(null);
                idType.setAdapter(null);
                spinner3.setAdapter(null);
                spinner4.setAdapter(null);
                religion.setAdapter(null);

            }
        });

        //NAVIGATION DRAWER
        drawerLayout = findViewById(R.id.my_drawer_layout);
        nav_view=findViewById(R.id.nav_view);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void addDatatoFirebase(String fNameString, String lNameString,String mNameString,String dobString, String genderString,String idtypeString, String idnumberString,String OccupationString, String ReligionString, String emailString, String phoneString, String nextofKinNameString,String nextofKinPhoneString, String relationshipString, String nextofKinEmailString, String countyString, String subCountyString,String conditionString, String allergyString, String disabilityString, String vaccineString, String doseString){
        registeredUserHelper.setFname(fNameString);
        registeredUserHelper.setmName(mNameString);
        registeredUserHelper.setlName(lNameString);
        registeredUserHelper.setDob(dobString);
        registeredUserHelper.setGender(genderString);
        registeredUserHelper.setIdtype(idtypeString);
        registeredUserHelper.setIdnumber(idnumberString);
        registeredUserHelper.setOccupation(OccupationString);
        registeredUserHelper.setReligion(ReligionString);
        registeredUserHelper.setEmail(emailString);
        registeredUserHelper.setPhoneNumber(phoneString);
        registeredUserHelper.setNextOfKin(nextofKinNameString);
        registeredUserHelper.setPhoneNumberKin(nextofKinPhoneString);
        registeredUserHelper.setRelationship(relationshipString);
        registeredUserHelper.setEmailKin(nextofKinEmailString);
        registeredUserHelper.setCounty(countyString);
        registeredUserHelper.setSubcounty(subCountyString);
        registeredUserHelper.setCondition(conditionString);
        registeredUserHelper.setAllergy(allergyString);
        registeredUserHelper.setDisability(disabilityString);
        registeredUserHelper.setVaccineType(vaccineString);
        registeredUserHelper.setDose(doseString);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.child(idnumberString).setValue(registeredUserHelper);
                Toast.makeText(VaccineRegistration.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VaccineRegistration.this, "Registration Failed " + error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id=menuItem.getItemId();
                if (id == R.id.dashboard){
                    Toast.makeText(VaccineRegistration.this, "Home", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(VaccineRegistration.this,Dashboard.class);
                    startActivity(intent1);
                }
                if (id == R.id.vaccinationCentres){
                    Toast.makeText(VaccineRegistration.this, "Vaccination Centres", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(VaccineRegistration.this, VaccinationCentres.class);
                    startActivity(intent1);
                }
                if(id==R.id.newsroom){
                    Toast.makeText(VaccineRegistration.this, "News Room", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(VaccineRegistration.this, NewsRoom.class);
                    startActivity(intent2);
                }
                if(id==R.id.vaccinationRegistration){
                    Toast.makeText(VaccineRegistration.this, "Vaccination Registration", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(VaccineRegistration.this, VaccineRegistration.class);
                    startActivity(intent3);
                }
                if(id==R.id.logout){
                    Toast.makeText(VaccineRegistration.this, "Log Out", Toast.LENGTH_LONG).show();
                    Intent intent3 = new Intent(VaccineRegistration.this, WelcomePage.class);
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
package com.example.VaccinationCentre2;

public class RegisteredUserHelper {
    String fname, mName, lName,dob, gender, idtype, idnumber, occupation,religion,email, phoneNumber, nextOfKin, phoneNumberKin, relationship, emailKin, county, subcounty, condition, allergy, disability, vaccineType, dose;
    // an empty constructor is required when using Firebase Realtime Database.
    public RegisteredUserHelper() {
    }

    public RegisteredUserHelper(String fname, String mName, String lName, String dob, String gender, String idtype, String idnumber, String occupation, String religion, String email, String phoneNumber, String nextOfKin, String phoneNumberKin, String relationship, String emailKin, String county, String subcounty, String condition, String allergy, String disability, String vaccineType, String dose) {
        this.fname = fname;
        this.mName = mName;
        this.lName = lName;
        this.dob = dob;
        this.gender = gender;
        this.idtype = idtype;
        this.idnumber = idnumber;
        this.occupation = occupation;
        this.religion = religion;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nextOfKin = nextOfKin;
        this.phoneNumberKin = phoneNumberKin;
        this.relationship = relationship;
        this.emailKin = emailKin;
        this.county = county;
        this.subcounty = subcounty;
        this.condition = condition;
        this.allergy = allergy;
        this.disability = disability;
        this.vaccineType = vaccineType;
        this.dose = dose;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdtype() {
        return idtype;
    }

    public void setIdtype(String idtype) {
        this.idtype = idtype;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    public String getPhoneNumberKin() {
        return phoneNumberKin;
    }

    public void setPhoneNumberKin(String phoneNumberKin) {
        this.phoneNumberKin = phoneNumberKin;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmailKin() {
        return emailKin;
    }

    public void setEmailKin(String emailKin) {
        this.emailKin = emailKin;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSubcounty() {
        return subcounty;
    }

    public void setSubcounty(String subcounty) {
        this.subcounty = subcounty;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getDisability() {
        return disability;
    }

    public void setDisability(String disability) {
        this.disability = disability;
    }

    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }
}

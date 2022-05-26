package com.example.VaccinationCentre2;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class VaccinationCentres extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng TrinityBaptistChurch = new LatLng(-1.2943480045114264, 36.893045284719726);
    LatLng PefaDonholmChurch = new LatLng(-1.297308405917464, 36.89008412587558);
    LatLng WordofGraceChurch = new LatLng(-1.2990245790585957, 36.89411816797273);
    LatLng ChristisLordChurch = new LatLng(-1.3013843152240339, 36.89330277648501);
    LatLng DonholmBaptistChurch= new LatLng(-1.3028430601132117, 36.89261613102167);
    LatLng KingOutreachChurch= new LatLng(-1.305760547356627, 36.886865475266156);
    LatLng RevivalandRestorationChurch= new LatLng(-1.3022424005551947, 36.9032591357035);
    LatLng ChristWorshipChurch= new LatLng(-1.3042588999331763, 36.90094170726472);
    LatLng EmbakasiChurchofGod= new LatLng(-1.302714347362831, 36.912013865361146);
    LatLng EmbakasiMethodistChurch= new LatLng(-1.3075196189599672, 36.918966151591874);
    LatLng BethsedaFaithChurch= new LatLng(-1.2931895856804152, 36.9100397605684);
    LatLng RedeemedGospelChurch= new LatLng(-1.301118413229879, 36.90908335584377);
    LatLng TassiaCentralSDAChurch= new LatLng(-1.301286739772838, 36.90563177181947);
    LatLng TheInternationalLifehouseChurch= new LatLng(-1.299708677991741, 36.9114194889334);
    LatLng AllNationsFellowshipChurch= new LatLng(-1.2889566681681568, 36.90771972677594);
    LatLng StGabrielCatholicChurch= new LatLng(-1.214954987083883, 36.86378180923152);
    LatLng CalvaryGardensSDAChurch= new LatLng(-1.2206131698262204, 36.86177819910575);
    LatLng ThomeBaptistChurch= new LatLng(-1.2125469039694576, 36.87156289695841);
    LatLng PCEAGateawayChurch= new LatLng(-1.2118604121461247, 36.87370866403136);
    LatLng ChristInternationalChurch= new LatLng(-1.2082706281981412, 36.87448114030546);
    private ArrayList<LatLng> locationArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_centres);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationArrayList = new ArrayList<>();
        locationArrayList.add(TrinityBaptistChurch);
        locationArrayList.add(PefaDonholmChurch);
        locationArrayList.add(WordofGraceChurch);
        locationArrayList.add(ChristisLordChurch);
        locationArrayList.add(DonholmBaptistChurch);
        locationArrayList.add(KingOutreachChurch);
        locationArrayList.add(RevivalandRestorationChurch);
        locationArrayList.add(ChristWorshipChurch);
        locationArrayList.add(EmbakasiChurchofGod);
        locationArrayList.add(EmbakasiMethodistChurch);
        locationArrayList.add(BethsedaFaithChurch);
        locationArrayList.add(RedeemedGospelChurch);
        locationArrayList.add(TassiaCentralSDAChurch);
        locationArrayList.add(TheInternationalLifehouseChurch);
        locationArrayList.add(AllNationsFellowshipChurch);
        locationArrayList.add(StGabrielCatholicChurch);
        locationArrayList.add(CalvaryGardensSDAChurch);
        locationArrayList.add(ThomeBaptistChurch);
        locationArrayList.add(PCEAGateawayChurch);
        locationArrayList.add(ChristInternationalChurch);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for (int i = 0; i < locationArrayList.size(); i++) {

            // below line is use to add marker to each location of our array list.
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("marker"));

            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));

            //open registration activity
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    Intent intent = new Intent(VaccinationCentres.this, VaccineRegistration.class);
                    startActivity(intent);
                    return true;

                }
            });
        }


    }
}
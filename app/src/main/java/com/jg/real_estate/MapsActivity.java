package com.jg.real_estate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jg.real_estate.DB.DatabaseHelper;
import com.jg.real_estate.Model.House;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
GoogleMap map;
DatabaseHelper databaseHelper;
ArrayList<House> houses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        databaseHelper = new DatabaseHelper(this);
        houses= databaseHelper.getAllData();

        if(houses.isEmpty()){
           Toast.makeText(this, "No data Found! Add some home Please.", Toast.LENGTH_SHORT).show();
        }


        for (int i=0; i<houses.size();i++ ) {
//getting longitude latitude
        String location = houses.get(i).getGoogle_location();
        String a[]= location.split(":");
        String b[]=a[1].split("L");

           LatLng position = new LatLng(Double.parseDouble(b[0]) ,Double.parseDouble(a[2]));
           //set masker point and estate name on title
           googleMap.addMarker(new MarkerOptions().position(position).title(houses.get(i).getEstate_name()));
           googleMap.animateCamera(CameraUpdateFactory.zoomBy(15.f));
           googleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }

    }
}
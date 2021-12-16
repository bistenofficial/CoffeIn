package com.example.coffein;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.coffein.model.CoffeeInLocate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<CoffeeInLocate> locates;
    private GoogleMap map;
    private LatLng spb = new LatLng(59.938074 , 30.311365 );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        locates = CoffeeInLocate.createList();
        map = googleMap;
        findSpb();
        int element = locates.size();
        for(int i = 0;i<element;i++)
        {
            LatLng spbMarker = new LatLng(locates.get(i).getLatitude(),locates.get(i).getLongitude());
            map.addMarker(new MarkerOptions().position(spbMarker).title("Кофейня").snippet("CoffeeIN"));
        }
    }
    public void findSpb() {
        if (map != null) {
            map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(spb, 11)
            );
        }
    }
}
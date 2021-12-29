package com.example.coffein;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffein.model.CoffeeInLocate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    ArrayList<CoffeeInLocate> locates = new ArrayList<CoffeeInLocate>();
    ArrayList<String> Street = new ArrayList<String>();
    private GoogleMap map;
    DBHelper dbHelper;

    private final LatLng spb = new LatLng(59.938074, 30.311365);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_maps);
        dbHelper = new DBHelper(this);
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("select coordinate, name_street from coffein", new String[]{});
        c.moveToFirst();
        map = googleMap;
        findSpb();
        for (int i = 0; i < c.getCount(); i++) {
            Street.add(c.getString(c.getColumnIndex("name_street")));
            String[] arrSplit = c.getString(c.getColumnIndex("coordinate")).split(",");
            locates.add(new CoffeeInLocate(Double.parseDouble(arrSplit[0]), Double.parseDouble(arrSplit[1])));
            c.moveToNext();
        }
        for (int i = 0; i < c.getCount(); i++) {
            LatLng spbMarker = new LatLng(locates.get(i).getLatitude(), locates.get(i).getLongitude());
            map.addMarker(new MarkerOptions().position(spbMarker).title("CoffeeIN").snippet(Street.get(i)));
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
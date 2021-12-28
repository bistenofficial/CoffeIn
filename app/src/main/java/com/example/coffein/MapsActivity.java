package com.example.coffein;

import android.content.ContentValues;
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
    ArrayList<CoffeeInLocate> locates= new ArrayList<CoffeeInLocate>();
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
        if (c.getCount() == 0) {
            createList();
            c = db.rawQuery("select coordinate, name_street from coffein", new String[]{});
        }
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

    public void createList() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase dw = dbHelper.getWritableDatabase();
        cv.put("coordinate", "59.926334,30.297289");
        cv.put("name_street", "Театральная площадь, 3");
        dw.insert("coffein", null, cv);

        cv.put("coordinate", "59.978283 ,30.349302");
        cv.put("name_street", "Литовская улица, 6");
        dw.insert("coffein", null, cv);

        cv.put("coordinate", "59.833926 ,30.404899");
        cv.put("name_street", "Будапештская улица, 104к1");
        dw.insert("coffein", null, cv);

        //states.add(new CoffeeInLocate(59.926334,30.297289));
        //states.add(new CoffeeInLocate(59.978283 ,30.349302));
        //states.add(new CoffeeInLocate(59.833926 ,30.404899));
    }
}
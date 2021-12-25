package com.example.coffein;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

public class Registration extends AppCompatActivity {
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new DBHelper(this);
    }
    public void Register(View V)
    {
        EditText editTextPhone = (EditText)findViewById(R.id.editTextTextPersonNamePhone);
        EditText editTextMail = (EditText)findViewById(R.id.editTextTextPersonNameEmail);
        EditText editTextDate = (EditText)findViewById(R.id.editTextTextPersonNameDATE);
        EditText editTextName = (EditText)findViewById(R.id.editTextTextPersonName);
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cv.put("name", editTextName.getText().toString());
        cv.put("phone", editTextPhone.getText().toString());
        cv.put("email", editTextMail.getText().toString());
        cv.put("date_of_birth", editTextDate.getText().toString());
        db.insert("users", null, cv);
    }
}
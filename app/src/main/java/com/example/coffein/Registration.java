package com.example.coffein;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {
    DBHelper dbHelper;

    private Pattern regexPattern;
    private Matcher regMatcher;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelper = new DBHelper(this);
    }

    public void Register(View V) {
        EditText editTextPhone = (EditText) findViewById(R.id.editTextTextPersonNamePhone);
        EditText editTextMail = (EditText) findViewById(R.id.editTextTextPersonNameEmail);
        EditText editTextDate = (EditText) findViewById(R.id.editTextTextPersonNameDATE);
        EditText editTextName = (EditText) findViewById(R.id.editTextTextPersonName);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if ((editTextPhone.length() == 12) && validateEmailAddress(editTextMail.toString())) {
            Cursor c = db.rawQuery("select * from users where phone = ? ", new String[]{editTextPhone.getText().toString()});
            if (c.getCount() == 0) {
                ContentValues cv = new ContentValues();
                SQLiteDatabase dw = dbHelper.getWritableDatabase();
                cv.put("name", editTextName.getText().toString());
                cv.put("phone", editTextPhone.getText().toString());
                cv.put("email", editTextMail.getText().toString());
                cv.put("date_of_birth", editTextDate.getText().toString());
                dw.insert("users", null, cv);

                toast = Toast.makeText(Registration.this, "Добро пожаловать в клуб CoffeeIN", Toast.LENGTH_LONG);
                toast.show();

                Intent intent = new Intent(this, AuthActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                toast = Toast.makeText(Registration.this, "Пользователь уже зарегестрирован", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(Registration.this, "Проверте введеные данные", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);

        if (regMatcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
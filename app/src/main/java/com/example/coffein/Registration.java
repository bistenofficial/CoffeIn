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
    Toast toast;
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
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

        if ((editTextPhone.length() == 12) && validateEmailAddress(editTextMail.getText().toString())) {
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
                c.close();
            }
        } else {
            Toast toast = Toast.makeText(Registration.this, "Проверте введеные данные", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public boolean validateEmailAddress(String emailAddress) {
        Matcher regMatcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailAddress);
        return regMatcher.matches();
    }
}
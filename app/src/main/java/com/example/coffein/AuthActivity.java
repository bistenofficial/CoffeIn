package com.example.coffein;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class AuthActivity extends AppCompatActivity {
    private NotificationManagerCompat manager;
    public static final String NORMAL_CHANNEL = "Normal_Channel";
    public static final String IMPORTANT_CHANNEL = "IMPORTANT_CHANNEL";
    public int code;
    EditText editCode;
    DBHelper dbHelper;
    String Phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        dbHelper = new DBHelper(this);
    }

    public void GoToMain(View v) {

        editCode = (EditText) findViewById(R.id.editTextCode);
        SharedPreferences mSettings = getSharedPreferences("my_storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();

        if (editCode.length() == 0) {
            Toast toast = Toast.makeText(AuthActivity.this, "Вы ничего не ввели", Toast.LENGTH_LONG);
            toast.show();
        } else {
            if (Integer.parseInt(editCode.getText().toString()) == code) {
                editor.putBoolean("is_logged", true).apply();
                editor.putString("Phone", Phone).apply();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast toast = Toast.makeText(AuthActivity.this, "Ошибка ввода кода", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    public void VisibleInvisible(View v) {
        manager = NotificationManagerCompat.from(this);
        EditText textInputEdittext = (EditText) findViewById(R.id.textInputEditText);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String name = getResources().getString(R.string.NOT_IMPORTANT_CHANNEL_NAME);
            NotificationChannel channel = new NotificationChannel(
                    NORMAL_CHANNEL,
                    name,
                    NotificationManager.IMPORTANCE_LOW);
            String description = getResources().getString(R.string.NOT_IMPORTANT_CHANNEL_DESCRIPTION);
            channel.setDescription(description);
            channel.enableVibration(false);
            manager.createNotificationChannel(channel);
        }

        int min = 100000;
        int max = 999999;
        int diff = max - min;
        Random random = new Random();
        int i = random.nextInt(diff + 1);
        i += min;
        code = i;

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        if (textInputEdittext.length() == 12) {
            Cursor c = db.rawQuery("select * from users where phone = ? ", new String[]{textInputEdittext.getText().toString()});
            if (c.getCount() > 0) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NORMAL_CHANNEL);
                builder.setSmallIcon(android.R.drawable.sym_def_app_icon);
                builder.setContentTitle("Код для авторизации");
                builder.setContentText(Integer.toString(i));
                builder.setAutoCancel(true);
                manager.notify(R.id.CODE_NOTIFICATION, builder.build());

                TextView TextClick = (TextView) findViewById(R.id.textViewCodClickb);
                TextView textViewCod = (TextView) findViewById(R.id.textViewCod);
                EditText editCode = (EditText) findViewById(R.id.editTextCode);
                Button enter = (Button) findViewById(R.id.btnEnter);
                Phone = textInputEdittext.getText().toString();
                textInputEdittext.setFocusable(false);
                textInputEdittext.setFocusableInTouchMode(false);
                textInputEdittext.setClickable(false);

                textViewCod.setVisibility(View.VISIBLE);
                TextClick.setText("Отправить код повторно");
                editCode.setVisibility(View.VISIBLE);
                enter.setVisibility(View.VISIBLE);
            } else {
                Toast toast = Toast.makeText(AuthActivity.this, "Пользователь не зарегестрирован", Toast.LENGTH_LONG);
                toast.show();
            }
        } else {
            Toast toast = Toast.makeText(AuthActivity.this, "Проверьте правильность введеного номера", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void GoToReg(View view)
    {
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
        finish();
    }
}
package com.huseyinkadioglu.storingdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;

    // aktivite destroy edildiğinde çağırılıyor.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextNumber);
        textView = findViewById(R.id.textView);

        sharedPreferences =
                this.getSharedPreferences("com.huseyinkadioglu.storingdata", Context.MODE_PRIVATE);
        // bu db'ye sadece benim uygulamamdan ulaşılsın anlamına geliyor.

        int storedAge = sharedPreferences.getInt("storedAge", 0);

        textView.setText("Your age: " + storedAge);

    }
    // Küçük veriler için veritabanı oluşturmaya gerek yok.
    // Kullanıcı adını ve yaşını saklamak istiyorum.

    public void save(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Saved");
        alert.setMessage("Are you sure?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!editText.getText().toString().matches("")) {
                    // kullanıcı girdi olarak bir şey verdiyse
                    int userAge = Integer.parseInt(editText.getText().toString());
                    textView.setText("Your age : " + userAge);

                    // küçük bir database'de tutuluyor.App silinmediği sürece tutabiliriz.
                    sharedPreferences.edit().putInt("storedAge", userAge).apply();


                } else {
                }
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Not happened", Toast.LENGTH_LONG).show();
            }
        });

        alert.show();

    }

    public void delete(View view) {
        int storedData = sharedPreferences.getInt("storedAge", 0);

        if (storedData != 0) {
            sharedPreferences.edit().remove("storedAge").apply();
        }
    }


}
package com.example.mbahr.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mbahr.myapplication.Models.Sonuc;
import com.example.mbahr.myapplication.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    AutoCompleteTextView email,name, surname,sifre;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.backg);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tanimla();
        add();

        Toast.makeText(this, "asdasdasd", Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "yeni eklendi burasıııı", Toast.LENGTH_SHORT).show();
    }

    public void tanimla() {
        email = findViewById(R.id.reg_userEmail);
        name = findViewById(R.id.reg_userName);
        surname = findViewById(R.id.reg_userSurname);
        sifre = findViewById(R.id.reg_userPassword);
        btn = findViewById(R.id.reg_button);

    }

    public void add() {

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emaill = email.getText().toString();
                String isim = name.getText().toString();
                String soyisim = surname.getText().toString();
                String sifree = sifre.getText().toString();

                if (!emaill.equals("") && !isim.equals("") && !soyisim.equals("") && !sifree.equals("")) {
                    istek(emaill,isim,soyisim,sifree);
                    deleteFromEdittext();
                } else {
                    Toast.makeText(MainActivity.this, "Lütfen Tüm Alanları Doldurunuz...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void deleteFromEdittext() {

        email.setText("");
        name.setText("");
        surname.setText("");
        sifre.setText("");
    }


    public void istek(String email,String ad, String soyad,String sifre) {
        Call<Sonuc> x = ManagerAll.getInstance().ekle(email,ad,soyad,sifre);
        x.enqueue(new Callback<Sonuc>() {
            @Override
            public void onResponse(Call<Sonuc> call, Response<Sonuc> response) {
                Toast.makeText(MainActivity.this, "" + response.body().getResult(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().equals("Tebrikler Basariyla Kayit Oldunuz ...")) {
                    Intent ıntent = new Intent(getApplicationContext(), Main2Activity.class);
                    startActivity(ıntent);
                }
            }
            @Override
            public void onFailure(Call<Sonuc> call, Throwable t) {

                Toast.makeText(MainActivity.this, "hata olustu", Toast.LENGTH_SHORT).show();

            }
        });

    }
}

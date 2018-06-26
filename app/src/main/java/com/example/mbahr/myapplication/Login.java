package com.example.mbahr.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Login extends Activity {

    private Button login, register_button;
    private AutoCompleteTextView login_email, login_sifre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);
        getWindow().setBackgroundDrawableResource(R.drawable.backg);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tanimla();
        login_control();
        RegisterActivity();



    }

    public void tanimla() {

        login = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_Email);
        login_sifre = findViewById(R.id.userPassword);
        register_button = findViewById(R.id.register_btn);

    }

    public void RegisterActivity() {

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }

        });

    }


    public void login_control() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String login_email_control = login_email.getText().toString();
                String login_sifre_control = login_sifre.getText().toString();

                if (!login_email_control.equals("") && !login_sifre_control.equals("")) {

                    login_istek(login_email_control, login_sifre_control);
                    deleteFromEdittext();

                } else {
                    Toast.makeText(Login.this, "Lütfen Tüm Alanları Doldurunuz...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void deleteFromEdittext() {

        login_email.setText("");
        login_sifre.setText("");

    }

    public void login_istek(String login_email, String login_sifre) {

        Call<Sonuc> login_process = ManagerAll.getInstance().login(login_email, login_sifre);
        login_process.enqueue(new Callback<Sonuc>() {
            @Override
            public void onResponse(Call<Sonuc> call, Response<Sonuc> response) {

                Toast.makeText(Login.this, "" + response.body().getResult(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().equals("Tebrikler Basariyla Giris Yaptiniz")) {
                    Intent ıntent = new Intent(getApplicationContext(), Home.class);
                    startActivity(ıntent);
                }

            }

            @Override
            public void onFailure(Call<Sonuc> call, Throwable t) {
                Toast.makeText(Login.this, "" + call, Toast.LENGTH_LONG).show();
                Log.d("errorr", "" + call);
            }
        });


    }

}

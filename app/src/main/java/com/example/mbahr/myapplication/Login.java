package com.example.mbahr.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.mbahr.myapplication.Fragments.GlobalClass;
import com.example.mbahr.myapplication.Models.Sonuc;
import com.example.mbahr.myapplication.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity {

    private Button login, register_button;
    private AutoCompleteTextView login_email, login_sifre;
    public GlobalClass globalClass;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

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

        globalClass = ((GlobalClass) getApplicationContext());
        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences objesi
        editor = preferences.edit();

        String login_email = preferences.getString("email", "");
        String login_sifre = preferences.getString("sifre", "");

        if(preferences.getBoolean("login", false)){
            Intent i = new Intent(getApplicationContext(),Home.class);
            startActivity(i);
            finish();
        }
        Log.i("valueeee",login_email+login_sifre);
}

    public void tanimla() {

        login = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_Email);
        login_sifre = findViewById(R.id.login_passwordd);
        register_button = findViewById(R.id.register_btn);

    }

    public void RegisterActivity() {

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                GlobalClass.setLogin_email(null);
                GlobalClass.setLogin_password(null);
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

                    String log_email =txtMesaj_TextChanged_3(login_email_control);
                    String log_password = txtMesaj_TextChanged_3(login_sifre_control);
                    login_istek(log_email, log_password);
                    deleteFromEdittext();

                } else {
                    Toast.makeText(Login.this, "Lütfen Tüm Alanları Doldurunuz...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private String txtMesaj_TextChanged_3(String s)
    {

        String text = s;
        text = text.replace("ü", "u");
        text = text.replace("ı", "i");
        text = text.replace("ö", "o");
        text = text.replace("ü", "u");
        text = text.replace("ş", "s");
        text = text.replace("ğ", "g");
        text = text.replace("ç", "c");
        text = text.replace("Ü", "U");
        text = text.replace("İ", "I");
        text = text.replace("Ö", "O");
        text = text.replace("Ü", "U");
        text = text.replace("Ş", "S");
        text = text.replace("Ğ", "G");
        text = text.replace("Ç", "C");


        return text;

    }

    public void deleteFromEdittext() {

        login_email.setText("");
        login_sifre.setText("");

    }

    public void login_istek(final String login_email, final String login_sifre) {

        GlobalClass.setLogin_email(login_email);
        GlobalClass.setLogin_password(login_sifre);



        Call<Sonuc> login_process = ManagerAll.getInstance().login(login_email, login_sifre);
        login_process.enqueue(new Callback<Sonuc>() {
            @Override
            public void onResponse(Call<Sonuc> call, Response<Sonuc> response) {

                Toast.makeText(Login.this, "" + response.body().getResult(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().equals("Tebrikler Basariyla Giris Yaptiniz")) {


                    editor.putString("email", login_email);
                    editor.putString("sifre", login_sifre);
                    editor.putBoolean("login", true);
                    editor.commit();
                    Intent ıntent = new Intent(getApplicationContext(), Home.class);
                    startActivity(ıntent);
                }

            }

            @Override
            public void onFailure(Call<Sonuc> call, Throwable t) {
                Toast.makeText(Login.this, "Lütfen Tekrar Giriş Yapmayı Deneyiniz" , Toast.LENGTH_LONG).show();
                Log.d("errorr", "" + call);
            }
        });


    }

}

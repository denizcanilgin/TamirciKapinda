package com.example.Inc.tamircikapinda;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.Inc.tamircikapinda.Fragments.GlobalClass;
import com.example.Inc.tamircikapinda.Models.Sonuc;
import com.example.Inc.tamircikapinda.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Activity {

    AutoCompleteTextView email, name, surname, sifre;
    Button btn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.register);
        getWindow().setBackgroundDrawableResource(R.drawable.backg);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        tanimla();
        add();

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());//preferences objesi
        editor = preferences.edit();

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


                GlobalClass.setRegister_email(emaill);
                GlobalClass.setRegister_password(sifree);

                if (!emaill.equals("") && !isim.equals("") && !soyisim.equals("") && !sifree.equals("")) {
                    istek(emaill, isim, soyisim, sifree);
                    deleteFromEdittext();
                } else {
                    Toast.makeText(Register.this, "Lütfen Tüm Alanları Doldurunuz...", Toast.LENGTH_SHORT).show();
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


    public void istek(String email, String ad, String soyad, String sifre) {

        String m_email = txtMesaj_TextChanged_3(email);
        final String m_ad = txtMesaj_TextChanged_3(ad);
        final String m_soyad = txtMesaj_TextChanged_3(soyad);
        String m_sifre = txtMesaj_TextChanged_3(sifre);


        Call<Sonuc> x = ManagerAll.getInstance().ekle(m_email, m_ad, m_soyad, m_sifre);
        x.enqueue(new Callback<Sonuc>() {
            @Override
            public void onResponse(Call<Sonuc> call, Response<Sonuc> response) {
                Toast.makeText(Register.this, "" + response.body().getResult(), Toast.LENGTH_SHORT).show();
                if (response.body().getResult().equals("Tebrikler Basariyla Kayit Oldunuz ...")) {

                    editor.putString("name", m_ad);
                    editor.putString("surname", m_soyad);

                    editor.commit();

                    Intent ıntent = new Intent(getApplicationContext(), Login.class);
                    startActivity(ıntent);
                }
            }

            @Override
            public void onFailure(Call<Sonuc> call, Throwable t) {

                Toast.makeText(Register.this, "hata olustu", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private String txtMesaj_TextChanged_3(String s) {

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
}

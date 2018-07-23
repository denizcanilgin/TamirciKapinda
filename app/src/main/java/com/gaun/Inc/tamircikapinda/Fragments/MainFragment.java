package com.gaun.Inc.tamircikapinda.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gaun.Inc.tamircikapinda.Home;
import com.gaun.Inc.tamircikapinda.Models.Sonuc;
import com.gaun.Inc.tamircikapinda.R;
import com.gaun.Inc.tamircikapinda.RestApi.ManagerAll;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends AppCompatActivity {

    int CountOfPush = 0;
    private Button back_btn;
    public Button forward_btn;
    private Button back_toHome;
    int problem_lenght = 3;
    int lenght;
    public GlobalClass globalVariable;

    public String text;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_infos_fragment);
        DisplayFirstFragment(null);
        defining();
        ForwardClick();
        BackClick();
        BackToHomeClick();

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();
        globalVariable = (GlobalClass) getApplicationContext();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit_app) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void defining() {
        back_btn = findViewById(R.id.BackButton);
        forward_btn = findViewById(R.id.ForwardButton);
        back_btn.setVisibility(View.INVISIBLE);
        back_toHome = findViewById(R.id.back_toHome);
        back_toHome.setVisibility(View.INVISIBLE);
    }


    public void ForwardClick() {
        forward_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (0 <= CountOfPush && CountOfPush < 3) {
                    CountOfPush++;

                    if (CountOfPush == 0) {
                        DisplayFirstFragment(null);
                        forward_btn.setText("İLERİ");

                    } else if (CountOfPush == 1) {

                        String text = GlobalClass.getProblem();

                        if (text == null || text == "") {
                            CountOfPush = 0;
                            Toast.makeText(getApplicationContext(), "Lütfen Daha Detaylı Bilgi Giriniz !!!", Toast.LENGTH_SHORT).show();
                        } else if (text.length() < 5) {
                            CountOfPush = 0;
                            Toast.makeText(getApplicationContext(), "Lütfen Daha Detaylı Bilgi Giriniz !!!", Toast.LENGTH_SHORT).show();
                        } else {
                            DisplayPhotoFragment();
                            back_btn.setVisibility(View.VISIBLE);
                            forward_btn.setText("İLERLE");
                            GlobalClass.setImage(null);
                        }

                    } else if (CountOfPush == 2) {
                        String image = GlobalClass.getImage();

                        if(image == null || image == "") {
                            CountOfPush = 1;
                            Toast.makeText(MainFragment.this, "Lütfen Arızanızın Fotoğrafını Ekleyiniz.", Toast.LENGTH_SHORT).show();
                        }else{
                            forward_btn.setText("TAMAMLA");
                        DisplaySecondFragment(null);}
                    } else if (CountOfPush == 3) {

                        String adres = GlobalClass.getAdress();
                        String phone = GlobalClass.getPhoneNumber();
                        String problem = GlobalClass.getProblem();
                        String emaill = GlobalClass.getLogin_email();
                        String passw = GlobalClass.getLogin_password();

                        if (emaill == null && passw == null) {
                            emaill = GlobalClass.getRegister_email();
                            passw = GlobalClass.getRegister_password();
                        }
                        if (adres == null || adres.equals("") || phone == null || phone.equals("")) {
                            CountOfPush = 2;
                            Toast.makeText(MainFragment.this, "Lütfen Tüm Alanları Doldurunuz ", Toast.LENGTH_SHORT).show();
                        } else {
                            String fotograf = "burak.png";
                            istek(emaill, passw, problem, fotograf, adres, phone);
                            txtMesaj_TextChanged_3(adres);

                        }
                    }

                }

            }

        });

    }

    public void istek(String mail, String passwords, String problemm, String fotograf, String adres, String phoneNumber) {


        Log.i("gelenAdres", mail + " " + passwords + " " + problemm + " " + adres + " " + phoneNumber);

        String login_email = preferences.getString("email", "");
        String login_sifre = preferences.getString("sifre", "");

        String m_email = txtMesaj_TextChanged_3(login_email);
        String m_password = txtMesaj_TextChanged_3(login_sifre);
        String m_problem = txtMesaj_TextChanged_3(problemm);
        String m_fotograf = txtMesaj_TextChanged_3(fotograf);
        String m_adres = txtMesaj_TextChanged_3(adres);
        String m_telefon = txtMesaj_TextChanged_3(phoneNumber);
        String m_image = GlobalClass.getImage();
        String m_image_path = GlobalClass.getImage_path();

        Call<Sonuc> s = ManagerAll.getInstance().addProblem(m_email, m_password, m_problem, m_image, m_image_path,m_adres, m_telefon);
        s.enqueue(new Callback<Sonuc>() {
            @Override
            public void onResponse(Call<Sonuc> call, Response<Sonuc> response) {

                if (response.body().getResult().equals("success")) {
                    Toast.makeText(MainFragment.this, "Talebiniz Başarılı Bir Şekilde Alınmıstır", Toast.LENGTH_SHORT).show();
                    DisplayThirdFragment(null);
                    back_btn.setVisibility(View.INVISIBLE);
                    forward_btn.setVisibility(View.INVISIBLE);
                    back_toHome.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainFragment.this, "" + response.body().getResult(), Toast.LENGTH_SHORT).show();
                    CountOfPush = 1;
                }

            }

            @Override
            public void onFailure(Call<Sonuc> call, Throwable t) {

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


    public void BackClick() {

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 < CountOfPush && CountOfPush <= 3) {
                    CountOfPush--;

                    if (CountOfPush == 0) {
                        GlobalClass.setProblem("");
                        DisplayFirstBackFragment(null);
                        back_btn.setVisibility(View.INVISIBLE);
                        forward_btn.setText("İLERİ");
                    } else if (CountOfPush == 1) {

                        GlobalClass.setImage(null);
                        DisplayPhotoFragmentBack();
                        back_btn.setVisibility(View.VISIBLE);
                        forward_btn.setText("İLERİ");
                    }
                    else if (CountOfPush == 2) {

                        DisplaySeoondBackFragment(null);
                        back_btn.setVisibility(View.VISIBLE);
                        forward_btn.setText("TAMAMLA");
                    }else if (CountOfPush == 3) {
                        DisplayThirdBackFragment(null);
                        back_btn.setVisibility(View.INVISIBLE);
                        forward_btn.setVisibility(View.INVISIBLE);
                        back_toHome.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    public void BackToHomeClick() {

        back_toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalClass.setProblem(null);
                GlobalClass.setAdress(null);
                GlobalClass.setPhoneNumber(null);
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void DisplayFirstFragment(View view) {
        Fragment fragment;
        fragment = new Fragment1();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }

    public void DisplayFirstBackFragment(View view) {
        Fragment fragment;
        fragment = new Fragment1();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }

    public void DisplayPhotoFragment() {
        Fragment fragment;
        fragment = new FragmentPhotoRequest();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }

    public void DisplayPhotoFragmentBack() {
        Fragment fragment;
        fragment = new FragmentPhotoRequest();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }


    public void DisplaySecondFragment(View view) {
        Fragment fragment;
        fragment = new Fragment2();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();


    }

    public void DisplaySeoondBackFragment(View view) {
        Fragment fragment;
        fragment = new Fragment2();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }

    public void DisplayThirdFragment(View view) {
        Fragment fragment;
        fragment = new Fragment3();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }

    public void DisplayThirdBackFragment(View view) {
        Fragment fragment;
        fragment = new Fragment3();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.exit_to_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.fragmentview, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        GlobalClass.setProblem(null);
        GlobalClass.setAdress(null);
        GlobalClass.setPhoneNumber(null);


    }
}



package com.example.mbahr.myapplication.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mbahr.myapplication.Home;
import com.example.mbahr.myapplication.R;


public class MainFragment extends AppCompatActivity implements Fragment1.onSomeEventListener {

    int CountOfPush = 0;
    private Button back_btn;
    public Button forward_btn;
    private Button back_toHome;
    int problem_lenght = 3;
    int lenght;

    public String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_infos_fragment);
        DisplayFirstFragment(null);
        defining();
        ForwardClick();
        BackClick();
        BackToHomeClick();

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
                if (0 <= CountOfPush && CountOfPush < 2) {
                    CountOfPush++;

                    if (CountOfPush == 0) {
                        DisplayFirstFragment(null);
                        forward_btn.setText("İLERİ");
                    } else if (CountOfPush == 1) {


                        DisplaySecondFragment(null);
                        back_btn.setVisibility(View.VISIBLE);
                        forward_btn.setText("TAMAMLA");

                    } else if (CountOfPush == 2) {
                        DisplayThirdFragment(null);
                        back_btn.setVisibility(View.INVISIBLE);
                        forward_btn.setVisibility(View.INVISIBLE);
                        back_toHome.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

    }

    @Override
    public String someEvent(String s) {
        String text = s;

        if (text.length() <5)
        {
            Intent intent = new Intent(getApplicationContext(), MainFragment.class);
            startActivity(intent);
            Toast.makeText(this, "Lütfen Daha Detaylı Bilgi Giriniz !!!", Toast.LENGTH_SHORT).show();
        }
        return text;
    }


    public void BackClick() {

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (0 < CountOfPush && CountOfPush <= 2) {
                    CountOfPush--;

                    if (CountOfPush == 0) {
                        DisplayFirstBackFragment(null);
                        back_btn.setVisibility(View.INVISIBLE);
                        forward_btn.setText("İLERİ");
                    } else if (CountOfPush == 1) {

                        DisplaySeoondBackFragment(null);
                        back_btn.setVisibility(View.VISIBLE);
                        forward_btn.setText("TAMAMLA");
                    } else if (CountOfPush == 2) {
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


}



package com.example.mbahr.myapplication.menu_activities;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


import com.example.mbahr.myapplication.Fragments.MainFragment;
import com.example.mbahr.myapplication.R;

public class find_us_on_socialmedia extends AppCompatActivity {
//
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_us_on_socialmedia);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        mDrawerLayout = findViewById(R.id.nav_menu_drawer_layout5);
        mToggle = new ActionBarDrawerToggle(this,mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        int id = menuItem.getItemId();

                        switch (id){

                            case R.id.text0:
                                Intent intent_HomePage = new Intent(getApplicationContext(),com.example.mbahr.myapplication.Home.class);
                                startActivity(intent_HomePage);
                                break;
                            case R.id.text1:
                                Intent intent_howToUse = new Intent(getApplicationContext(),com.example.mbahr.myapplication.menu_activities.how_to_use.class);
                                startActivity(intent_howToUse);
                                // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text2:
                                Intent intent_aboutUs = new Intent(getApplicationContext(),com.example.mbahr.myapplication.menu_activities.about_us.class);
                                startActivity(intent_aboutUs);
                                //Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text3:
                                mDrawerLayout.closeDrawers();
                                // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text4:
                                Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text5:
                                // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;

                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(mToggle.onOptionsItemSelected(item)){return true;}
        return super.onOptionsItemSelected(item);
    }



    public void OpenRequestInfoPage(View view) {
        Intent intent = new Intent(getApplicationContext(), MainFragment.class);
        startActivity(intent);
    }

    public void openInstagram(){

        Uri uri = Uri.parse("http://instagram.com/_u/denizcanilgin");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/denizcanilgin")));
        }

    }



    }


package com.example.mbahr.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.mbahr.myapplication.Fragments.MainFragment;

public class Home extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = preferences.edit();

        String login_email = preferences.getString("email", "");

        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Hoşgeldin"+" "+login_email,Snackbar.LENGTH_LONG);
        snackbar.show();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mDrawerLayout = findViewById(R.id.nav_menu_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_menu);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        int id = menuItem.getItemId();

                        switch (id) {

                            case R.id.text0:

                                //Do nothing when click on current page

                                break;

                            case R.id.text1:
                                Intent intent_howToUse = new Intent(getApplicationContext(), com.example.mbahr.myapplication.menu_activities.how_to_use.class);
                                startActivity(intent_howToUse);
                                // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text2:
                                Intent intent_aboutUs = new Intent(getApplicationContext(), com.example.mbahr.myapplication.menu_activities.about_us.class);
                                startActivity(intent_aboutUs);
                                //Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text3:
                                Intent intent_contactUs = new Intent(getApplicationContext(), com.example.mbahr.myapplication.menu_activities.contact_us.class);
                                startActivity(intent_contactUs);
                                //Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text4:
                                // Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.text5:
                                Toast.makeText(getApplicationContext(), "" + id, Toast.LENGTH_SHORT).show();
                                Intent intent_find_us_on_socialmedia = new Intent(getApplicationContext(), com.example.mbahr.myapplication.menu_activities.find_us_on_socialmedia.class);
                                startActivity(intent_find_us_on_socialmedia);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.exit_app) {


            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
            builder.setTitle(getResources().getString(R.string.app_name));
            builder.setMessage("Çıkış Yapmak İstiyor musunuz ? ");

            builder.setPositiveButton("TAMAM", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    editor.putBoolean("login", false);//uygulamaya tekrar girdiğinde kontrol için kullanılcak
                    editor.commit();
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();

                }
            });
            builder.setNegativeButton("İPTAL", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int id) {



                }
            });





            builder.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void OpenRequestInfoPage(View view) {
        Intent intent = new Intent(getApplicationContext(), MainFragment.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() { }


}

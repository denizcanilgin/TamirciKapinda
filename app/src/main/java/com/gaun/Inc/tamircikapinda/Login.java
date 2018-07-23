package com.gaun.Inc.tamircikapinda;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gaun.Inc.tamircikapinda.Fragments.GlobalClass;
import com.gaun.Inc.tamircikapinda.Models.Sonuc;
import com.gaun.Inc.tamircikapinda.RestApi.ManagerAll;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends Activity implements
        View.OnClickListener,
        GoogleApiClient.OnConnectionFailedListener{

    private Button login, register_button;
    private AutoCompleteTextView login_email, login_sifre;
    public GlobalClass globalClass;

    private static final String TAG = Login.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnSignIn;
    private LinearLayout gmail_access,facebook_access;


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
        gmail_access();
        facebook_access();


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

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        FragmentActivity a = new FragmentActivity();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(a,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());


}

    public void tanimla() {

        login = findViewById(R.id.login_button);
        login_email = findViewById(R.id.login_Email);
        login_sifre = findViewById(R.id.login_passwordd);
        register_button = findViewById(R.id.register_btn);
        btnSignIn = findViewById(R.id.btn_sign_in);
        gmail_access = findViewById(R.id.gmail_access);
        facebook_access = findViewById(R.id.facebook_access);

    }

    private void gmail_access()
    {
        gmail_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Gmail ile hızlı giriş özelliği önümüzdeki güncellemede aktif olacaktır.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void facebook_access()
    {
        facebook_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Facebook ile hızlı giriş özelliği önümüzdeki güncellemede aktif olacaktır.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent ıntent = new Intent(getApplicationContext(), Home.class);
        startActivity(ıntent);
    }

    public void signOut(GoogleApiClient mGoogleApiClientt) {
        Auth.GoogleSignInApi.signOut(mGoogleApiClientt).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        Toast.makeText(getApplicationContext(), ""+status, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = "";//acct.getPhotoUrl().toString();
            String email = acct.getEmail();

            Toast.makeText(this, ""+personName +"\n"+email, Toast.LENGTH_SHORT).show();

        } else {
            // Signed out, show unauthenticated UI.
        }
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

                Log.i("ssssss",response.body().getResult());
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

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id) {
            case R.id.btn_sign_in:


                Toast.makeText(this, "Bu özellik önümüzdeki versiyonlarda aktif olacaktır", Toast.LENGTH_SHORT).show();

//                signIn();
//
//                OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
//                if (opr.isDone()) {
//
//                    Log.d(TAG, "Got cached sign-in");
//                    GoogleSignInResult result = opr.get();
//                    handleSignInResult(result);
//                } else {
//
//                    opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
//                        @Override
//                        public void onResult(GoogleSignInResult googleSignInResult) {
//                            //hideProgressDialog();
//                            handleSignInResult(googleSignInResult);
//                        }
//                    });
//                }
                break;

        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        mGoogleApiClient.connect();
    }

}

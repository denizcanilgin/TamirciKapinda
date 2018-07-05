package com.example.mbahr.myapplication.RestApi;

import com.example.mbahr.myapplication.Models.Sonuc;

import retrofit2.Call;

public class ManagerAll extends BaseManager {

    private static ManagerAll ourInstance = new ManagerAll();

    public static synchronized ManagerAll getInstance() {

        return ourInstance;
    }

    public Call<Sonuc> ekle(String email, String ad, String soyad, String sifre) {

        Call<Sonuc> x = getRestApi().addUser(email, ad, soyad, sifre);
        return x;
    }

    public Call<Sonuc> login(String login_email,String login_sifre)
    {

        Call<Sonuc> login = getRestApi().authenticate(login_email,login_sifre);
        return login;

    }

    public Call<Sonuc> addProblem(String email, String sifre, String problem, String fotograf, String adres, String telefon)

    {

        Call<Sonuc> addproblemm = getRestApi().problemrecord(email,sifre,problem,fotograf,adres,telefon);
        return addproblemm;

    }

}

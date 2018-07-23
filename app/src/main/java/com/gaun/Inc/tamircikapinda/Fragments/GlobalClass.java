package com.gaun.Inc.tamircikapinda.Fragments;

import android.app.Application;

public class GlobalClass extends Application {

    public  static  String problem;
    public  static  String adress;
    public  static  String phoneNumber;
    public  static  String login_email;
    public  static  String login_password;
    public  static  String register_email;
    public  static  String register_password;
    public  static  String image;
    public  static  String image_path;

    public static String getImage_path() {
        return image_path;
    }

    public static void setImage_path(String image_path) {
        GlobalClass.image_path = image_path;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        GlobalClass.image = image;
    }

    public static String getRegister_email() {
        return register_email;
    }

    public static void setRegister_email(String register_email) {
        GlobalClass.register_email = register_email;
    }

    public static String getRegister_password() {
        return register_password;
    }

    public static void setRegister_password(String register_password) {
        GlobalClass.register_password = register_password;
    }

    public static String getLogin_email() {
        return login_email;
    }

    public static void setLogin_email(String login_email) {
        GlobalClass.login_email = login_email;
    }

    public static String getLogin_password() {
        return login_password;
    }

    public static void setLogin_password(String login_password) {
        GlobalClass.login_password = login_password;
    }

    public static String getProblem() {
        return problem;
    }

    public static void setProblem(String problem) {
        GlobalClass.problem = problem;
    }

    public static String getAdress() {
        return adress;
    }

    public static void setAdress(String adress) {
        GlobalClass.adress = adress;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        GlobalClass.phoneNumber = phoneNumber;
    }
}

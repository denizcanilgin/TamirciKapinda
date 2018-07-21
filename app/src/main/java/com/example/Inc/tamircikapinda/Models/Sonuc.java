package com.example.Inc.tamircikapinda.Models;

public class Sonuc {

    private String Result;

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    private String soyad;
    private String id;
    private String isim;

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getIsim() {
        return isim;
    }

    @Override
    public String toString() {
        return
                "Login{" +
                        "soyad = '" + soyad + '\'' +
                        ",id = '" + id + '\'' +
                        ",isim = '" + isim + '\'' +
                        "}";
    }
}

package com.example.mbahr.myapplication.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.mbahr.myapplication.R;


public class Fragment2 extends android.support.v4.app.Fragment {

    private View view;
    private AutoCompleteTextView usersAdress, usersPhone;
    public GlobalClass globalClass;
    private String setAdress, setPhoneNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment2, container, false);

        usersAdress = view.findViewById(R.id.usersAdress);
        usersPhone = view.findViewById(R.id.usersPhone);

        globalClass = ((GlobalClass) getActivity().getApplicationContext());

        usersAdress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                setAdress = usersAdress.getText().toString();
                GlobalClass.setAdress(setAdress);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        usersPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                setPhoneNumber = usersPhone.getText().toString();
                GlobalClass.setPhoneNumber(setPhoneNumber);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return view;


    }
}
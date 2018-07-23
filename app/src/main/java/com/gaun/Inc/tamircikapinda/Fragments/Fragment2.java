package com.gaun.Inc.tamircikapinda.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gaun.Inc.tamircikapinda.R;


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

        final Spinner disrict_spinner1 = (Spinner) view.findViewById(R.id.spinner_town1);
        Spinner disrict_spinner2 = (Spinner) view.findViewById(R.id.spinner_town2);
        final String[] disricts1 = new String[]{"İlçe seçiniz","Şahinbey İlçesi"};
        String[] disricts2 = new String[]{"Mahalle seçiniz","Yeditepe Mahallesi","Güneykent Mahallesi","Binevler Mahallesi"};


        ArrayAdapter disrictAdapter1 = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,disricts1){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override

            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv1 = (TextView) view;
                if(position == 0){

                    tv1.setTextColor(Color.GRAY);
                }
                else {
                    tv1.setTextColor(Color.BLACK);
                }
                return view;
            }
        };


        ArrayAdapter disrictAdapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,disricts2){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv2 = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv2.setTextColor(Color.GRAY);
                }
                else {
                    tv2.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        //Set Adapter For Both Array to Spinner
        disrict_spinner1.setAdapter(disrictAdapter1);
        disrict_spinner2.setAdapter(disrictAdapter2);

        disrict_spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position > 0)
//                   // Toast.makeText(getActivity(),"Seçiminiz : " + position,0).show();
//                else
//                    Toast.makeText(getActivity(),"Lütfen seçim yapınız! ",0).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        disrict_spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(getActivity(),"asdasdsad",0).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
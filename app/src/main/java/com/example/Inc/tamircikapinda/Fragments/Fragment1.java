package com.example.Inc.tamircikapinda.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.Inc.tamircikapinda.R;


public class Fragment1 extends android.support.v4.app.Fragment {

    private View view;
    public AutoCompleteTextView Entered_Problem;
    private String getTextProb;
    public GlobalClass globalClass;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fragment1, container, false);
        Entered_Problem = view.findViewById(R.id.EnterYourProblem);


        globalClass = ((GlobalClass) getActivity().getApplicationContext());


        Entered_Problem.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getTextProb = Entered_Problem.getText().toString();
                GlobalClass.setProblem(getTextProb);
                Log.i("gelendegerrr", "focus : " + getTextProb);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }




}

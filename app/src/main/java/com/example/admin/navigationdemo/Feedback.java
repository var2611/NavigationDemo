package com.example.admin.navigationdemo;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Feedback extends Fragment {

    EditText editText;
    RadioGroup radioGroup;
    RadioButton good, verygood, bad, average;
    //    RadioButton radioButton;
    String result;
    ImageButton imageButton;


    public Feedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_feedback, container, false);


        editText = (EditText) view.findViewById(R.id.message);
        radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        radioGroup.clearCheck();
        imageButton = (ImageButton) view.findViewById(R.id.ib);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                good = (RadioButton) radioGroup.findViewById(R.id.good);
                verygood = (RadioButton) radioGroup.findViewById(R.id.verygood);
                bad = (RadioButton) radioGroup.findViewById(R.id.bad);
                average = (RadioButton) radioGroup.findViewById(R.id.average);
                if (i == R.id.good) {

                    result = good.getText().toString();
                } else if (i == R.id.verygood) {
                    result = verygood.getText().toString();
                } else if (i == R.id.bad) {
                    result = bad.getText().toString();
                } else if (i == R.id.average) {
                    result = average.getText().toString();
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editText.getText().toString().isEmpty()) {
                    editText.setError("Feedback is empty");

                }
            else {
                    sendEmail();
                }

            }

        });


        return view;
    }


    private void sendEmail() {

        String message = editText.getText().toString();
        String[] to = {"140120116044@git.org.in"};


        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback Status");
        emailIntent.putExtra(Intent.EXTRA_TEXT,  message +"/n" + result);


        try {
            startActivity(Intent.createChooser(emailIntent, "send mail..."));
            finish();
            Log.i("Finished sending email", "");

        } catch (ActivityNotFoundException ex) {


        }
    }

    private void finish() {
    }

}

package com.weather.weatherzp202;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class NewEntryActivity extends Activity {

    private CheckBox chkTmp;
    private Button btnCheck;
    private Button btnRadio;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        setTitle("New Entry");
        addListenerOnButton();

    }


    public void addListenerOnButton() {

        chkTmp = (CheckBox) findViewById(R.id.chkIos);
        btnCheck = (Button) findViewById(R.id.btnCheck);

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnRadio = (Button) findViewById(R.id.btnRadio);

        //Check

        btnCheck.setOnClickListener(new OnClickListener() {

            //Run when button is clicked
            @Override
            public void onClick(View v) {

                StringBuffer result = new StringBuffer();
                result.append("It may be cold outside").append(chkTmp.isChecked());

                Toast.makeText(NewEntryActivity.this, result.toString(),
                        Toast.LENGTH_LONG).show();

            }
        });

//        Radio

        btnRadio.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(NewEntryActivity.this,
                        radioSexButton.getText(), Toast.LENGTH_SHORT).show();

            }

        });
        //spinner
    }
}
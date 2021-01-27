package com.weather.weatherzp202;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setTitle("Registration");

        Button register=findViewById(R.id.register);
        final EditText username=findViewById(R.id.username);
        final EditText email=findViewById(R.id.email);
        final EditText password=findViewById(R.id.password);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (Validation.isValidUsername(username.getText().toString()) &&
                        Validation.isValidEmail(email.getText().toString()) &&
                        Validation.isValidPassword(password.getText().toString()))  {

                    Intent goToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(goToLoginActivity);


                } else {
                    username.setError(getResources().getString(R.string.register_invalid_validation));
                    username.requestFocus();
                }


            }
        });
    }
}
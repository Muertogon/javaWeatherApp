package com.weather.weatherzp202;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//atidaromas langas (tuscias)
        setContentView(R.layout.activity_login);//pridek prie to lango, vaizda
        setTitle("Login");
        //register activity
            Button registryButt = findViewById(R.id.regbutton);
            registryButt.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(myIntent);
                }
            });


        //kodas rasomas nuo cia
        Button login=findViewById(R.id.loginbatonas);//susiejamas vaizde esantis elementa su kodu
        final EditText username=findViewById(R.id.usernametext);
        final EditText password=findViewById(R.id.passwordtext);

        final CheckBox rememberme = (CheckBox) findViewById(R.id.rememberMe);
        //bus konstruojamas vartotojo objektas perduodant context'a (langa kuriame esame)
        final User user=new User(LoginActivity.this);
        //patikriname, ar paskutini karta buvo pazymetas checkbox remember me
        rememberme.setChecked(user.isRememberedForLogin());

        //Aprasoma prisiminti mane checkbox logika
        if (rememberme.isChecked()){//jeigu checkbox buvo pazymetas-vartotojas pageidavo, kad informacija buvo issaugota
            username.setText(user.getUsernameForLogin(),TextView.BufferType.EDITABLE);//setText-uzpildysime user informacija, editable - suteiksim galimybe paredaguoti duomenis
            password.setText(user.getPasswordForLogin(),TextView.BufferType.EDITABLE);
        } else {//jeigu checkbox buvo nepazymetas-vartotojas nenorejo, kad informacija buvo issaugota
            username.setText("",TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // cia rasomas kodas kuris vykdomas paspaudus mygtuka
                if (Validation.isValidUsername(username.getText().toString())
                        && Validation.isValidPassword(password.getText().toString())){
                    user.setUsernameForLogin(username.getText().toString());
                    user.setPasswordForLogin(password.getText().toString());
                    if (rememberme.isChecked()){
                        user.setRemembermeKeyForLogin(true);
                    } else {
                        user.setRemembermeKeyForLogin(false);
                    }
                    //ketinimas pereiti i paieskos langa                is kur            į kur
                    Intent goToSeachActivity=new Intent(LoginActivity.this, SearchActivity.class);
                    startActivity(goToSeachActivity);
                }
                else{//Jeigu vartotojas ivede bloga prisijungimo varda
                    username.setError(getResources().getString(R.string.login_invalid_username));
                    username.requestFocus();
                }

            }
        });

    }

}

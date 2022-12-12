package com.danielle.myapplicationnogeenkeer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Inloggen(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void MaakAccount(View v){
        Intent intent = new Intent(this, MaakAccount.class);
        startActivity(intent);
    }
}

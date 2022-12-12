package com.danielle.myapplicationnogeenkeer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MaakAccount extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_account_aanmaken);
        }

        public void Inloggen(View v){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
}

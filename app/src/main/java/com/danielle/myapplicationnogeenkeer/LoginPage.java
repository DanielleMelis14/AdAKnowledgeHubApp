package com.danielle.myapplicationnogeenkeer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        Button btn_login = this.findViewById(R.id.button_login);
        btn_login.setOnClickListener(e->{
            Login();
        });
    }

    public void MaakAccount(View v){
        Intent intent = new Intent(this, MaakAccount.class);
        startActivity(intent);
    }
    
    public void Login() {
        String inputEmail = ((EditText) this.findViewById(R.id.inputEmailInlog)).getText().toString();
        String inputWachtwoord = ((EditText) this.findViewById(R.id.inputWachtwoordInlog)).getText().toString();

        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/student/login/";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(LoginPage.super.getBaseContext(), "Data added to API", Toast.LENGTH_SHORT).show();
                Log.i("Response", response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    System.out.println(jsonResponse);
                    System.out.println(jsonResponse.get("data"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(LoginPage.super.getBaseContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Email", inputEmail);
                params.put("Wachtwoord", inputWachtwoord);
                return params;
            }
        };

        queue.add(request);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

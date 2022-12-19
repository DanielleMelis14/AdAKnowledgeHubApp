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

public class MaakAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_aanmaken);

        Button btnCreateAccount = this.findViewById(R.id.button_maak_account);
        btnCreateAccount.setOnClickListener(e->{
            CreateAccount();
        });
    }

    public void Inloggen(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void CreateAccount() {
        String inputNaam = ((EditText) this.findViewById(R.id.input_acc_naam)).getText().toString();
        String inputEmail = ((EditText) this.findViewById(R.id.input_acc_email)).getText().toString();
        String inputStudentNummer = ((EditText) this.findViewById(R.id.input_acc_studentnr)).getText().toString();
        String inputWachtwoord = ((EditText) this.findViewById(R.id.input_acc_wachtwoord)).getText().toString();

        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/student/register/";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(MaakAccount.super.getBaseContext(), "Data added to API", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(MaakAccount.super.getBaseContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Student_id", inputStudentNummer);
                params.put("Naam", inputNaam);
                params.put("Email", inputEmail);
                params.put("Wachtwoord", inputWachtwoord);
                return params;
            }
        };

        queue.add(request);
    }
}

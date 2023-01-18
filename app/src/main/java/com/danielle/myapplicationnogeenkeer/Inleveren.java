package com.danielle.myapplicationnogeenkeer;

import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danielle.myapplicationnogeenkeer.models.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Inleveren extends AppCompatActivity {
    //Intialize attributes
    NfcAdapter nfcAdapter;
    PendingIntent pendingIntent;
    final static String TAG = "nfc_test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_inleveren);

        Button button_terug = this.findViewById(R.id.btn_back4);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.PreviousFragment();
            }
        });

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        //If no NfcAdapter, display that the device has no NFC
        if (nfcAdapter == null){
            Toast.makeText(this,"NO NFC Capabilities",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        //Create a PendingIntent object so the Android system can
        //populate it with the details of the tag when it is scanned.
        //PendingIntent.getActivity(Context,requestcode(identifier for
        //                           intent),intent,int)
        pendingIntent = PendingIntent.getActivity(this,1337,new Intent(this,this.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_MUTABLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        assert nfcAdapter != null;
        //nfcAdapter.enableForegroundDispatch(context,pendingIntent,
        //                                    intentFilterArray,
        //                                    techListsArray)
        nfcAdapter.enableForegroundDispatch(this,pendingIntent,null,null);
    }

    protected void onPause() {
        super.onPause();
        //Onpause stop listening
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        resolveIntent(intent);
    }

    private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Tag tag = (Tag) intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Parcelable[] messages = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

            Ndef ndef = Ndef.get(tag);

            if(ndef == null) {
                Toast.makeText(this, "Scan een boek!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                ndef.connect();
                if (messages != null) {
                    NdefMessage[] ndefMessages = new NdefMessage[messages.length];
                    for (int i = 0; i < messages.length; i++) {
                        ndefMessages[i] = (NdefMessage) messages[i];
                    }
                    NdefRecord record = ndefMessages[0].getRecords()[0];

                    // Foreach door elke record om te kijken of het een ID is
//                    NdefRecord[] records = ndefMessages[0].getRecords();

                    String NFCData = new String(record.getPayload()).substring(3);
                    Log.i("testerino", NFCData);

                    Toast.makeText(this, NFCData, Toast.LENGTH_SHORT).show();
                    loan(NFCData);

                    ndef.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loan(String id) {
        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/loan/return/";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);
                Log.i("Response", "dqwqwd");

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(jsonResponse.getBoolean("success"))
                        Toast.makeText(Inleveren.this, "Lening ingeleverd", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Inleveren.this, "Er is iets misgegaan met het inleveren van de lening", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            Log.e("error", error.getMessage());
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                Student student = MainActivity.getLoggedInStudent();

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                String currentDateandTime = sdf.format(new Date());

                params.put("Student_id", String.valueOf(student.getId()));
                params.put("item_id", id);
                params.put("InleverDatum", currentDateandTime);

                return params;
            }
        };
        queue.add(request);
    }
}
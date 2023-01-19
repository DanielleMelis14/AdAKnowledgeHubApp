package com.danielle.myapplicationnogeenkeer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.SimpleDateFormat;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danielle.myapplicationnogeenkeer.models.Student;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BoekRegistreren extends AppCompatActivity {
    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] intentFiltersArray;
    private String[][] techList;
    private int newBoekId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boek_registreren);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        if (nfcAdapter == null)
            Log.e("error", "No NFC capabilities");

        if (!nfcAdapter.isEnabled())
            Log.e("error", "NFC is disabled");

        Button btnRegisterBook = findViewById(R.id.btn_registreer_boek);
        btnRegisterBook.setOnClickListener(e -> {
            EditText etTitel = findViewById(R.id.input_boek_titel);
            EditText etISBN = findViewById(R.id.input_boek_isbn);
            EditText etSchrijver = findViewById(R.id.input_boek_schrijver);
            EditText etSamenvatting = findViewById(R.id.input_boek_samenvatting);
            EditText etTaal = findViewById(R.id.input_boek_taal);
            EditText etDruk = findViewById(R.id.input_boek_druk);
            EditText etJaar = findViewById(R.id.input_boek_jaar);
            EditText etFoto = findViewById(R.id.input_boek_foto);

            register(etTitel.getText().toString(), etISBN.getText().toString(), etSchrijver.getText().toString(), etSamenvatting.getText().toString(), etTaal.getText().toString(), etDruk.getText().toString(), etJaar.getText().toString(), etFoto.getText().toString());
        });
    }

    protected void onPause() {
        super.onPause();
        //Onpause stop listening
        if (nfcAdapter != null) {
            nfcAdapter.disableForegroundDispatch(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent nfcIntent = new Intent(this, getClass());
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        pendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, PendingIntent.FLAG_MUTABLE);
        intentFiltersArray = new IntentFilter[] {};
        techList = new String[][] {
                { android.nfc.tech.Ndef.class.getName() },
                { android.nfc.tech.NdefFormatable.class.getName() }
        };
        nfcAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        writeTag(tag, createTextMessage(String.valueOf(newBoekId)));
    }

    public void writeTag(Tag tag, NdefMessage message)  {
        if (tag != null) {
            try {
                Ndef ndefTag = Ndef.get(tag);
                if (ndefTag == null) {
                    // Let's try to format the Tag in NDEF
                    NdefFormatable nForm = NdefFormatable.get(tag);
                    if (nForm != null) {
                        nForm.connect();
                        nForm.format(message);
                        nForm.close();
                    }
                }
                else {
                    ndefTag.connect();
                    ndefTag.writeNdefMessage(message);
                    ndefTag.close();
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public NdefMessage createTextMessage(String content) {
        try {
            // Get UTF-8 byte
            byte[] lang = Locale.getDefault().getLanguage().getBytes("UTF-8");
            byte[] text = content.getBytes("UTF-8"); // Content in UTF-8

            int langSize = lang.length;
            int textLength = text.length;

            ByteArrayOutputStream payload = new ByteArrayOutputStream(1 + langSize + textLength);
            payload.write((byte) (langSize & 0x1F));
            payload.write(lang, 0, langSize);
            payload.write(text, 0, textLength);
            NdefRecord record = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,
                    NdefRecord.RTD_TEXT, new byte[0],
                    payload.toByteArray());

            NdefMessage t = new NdefMessage(new NdefRecord[]{record});

            return t;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void register(String titel, String ISBN, String schrijver, String samenvatting, String taal, String druk, String jaar, String foto) {
        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/book/create/";

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    if(jsonResponse.getBoolean("success")) {
                        newBoekId = jsonResponse.getInt("data");

                        Toast.makeText(BoekRegistreren.this, "Boek geregistreerd", Toast.LENGTH_SHORT).show();

                        nfcAdapter.enableForegroundDispatch(BoekRegistreren.this, pendingIntent, intentFiltersArray, techList);

                        Toast.makeText(BoekRegistreren.this, "Hou nu de NFC tag tegen de telefoon", Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(BoekRegistreren.this, "Er is iets misgegaan met het regis", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            // method to handle errors.
            Toast.makeText(this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            Log.e("erorr", error.toString());
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("Titel", titel);
                params.put("ISBN", ISBN);
                params.put("Schrijver", schrijver);
                params.put("Samenvatting", samenvatting);
                params.put("Taal", taal);
                params.put("Druk", druk);
                params.put("Jaar", jaar);
                params.put("Foto", foto);

                return params;
            }
        };
        queue.add(request);
    }
}
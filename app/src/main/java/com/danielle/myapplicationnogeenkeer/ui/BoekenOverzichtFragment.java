package com.danielle.myapplicationnogeenkeer.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danielle.myapplicationnogeenkeer.BookAdapter;
import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentBoekenOverzichtBinding;
import com.danielle.myapplicationnogeenkeer.models.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BoekenOverzichtFragment extends Fragment {

    private FragmentBoekenOverzichtBinding binding;
    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoekenOverzichtBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_terug = root.findViewById(R.id.btn_back);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ReplaceFragment(new BronnenOverzichtFragment());
            }
        });

        gv = root.findViewById(R.id.Gridview_boeken);
        getBooks();

        return root;
    }

    public void getBooks() {
        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/book/list";

        RequestQueue queue = Volley.newRequestQueue(BoekenOverzichtFragment.super.getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    System.out.println(jsonResponse);
                    JSONArray jBoeken = (JSONArray) jsonResponse.get("data");

                    ArrayList<Book> boeken = new ArrayList<>();

                    for (int i = 0; i < jBoeken.length(); i++){
                        JSONObject boek = jBoeken.getJSONObject(i);
                        boeken.add(new Book(boek.getInt("Boek_id"), boek.getString("Titel"), boek.getString("Foto"), boek.getString("Samenvatting")));
                    }

                    gv.setAdapter(new BookAdapter(boeken));

                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Book book = (Book) gv.getItemAtPosition(i);
                            MainActivity.ReplaceFragment(new BoekFragment(book));
                        }
                    });

                    System.out.println(jsonResponse.get("data"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            Toast.makeText(BoekenOverzichtFragment.super.getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        });

        queue.add(request);
    }
}
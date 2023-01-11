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
import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.ResourceAdapter;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentOnlineBronnenOverzichtBinding;
import com.danielle.myapplicationnogeenkeer.models.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OnlineBronnenFragment extends Fragment {

    private FragmentOnlineBronnenOverzichtBinding binding;
    private GridView gv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnlineBronnenOverzichtBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_terug = root.findViewById(R.id.btn_back6);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ReplaceFragment(new BronnenOverzichtFragment());
            }
        });

        gv = root.findViewById(R.id.gridview_onlinebronnen);
        getResourcess();

        return root;
    }

    public void getResourcess() {
        String url = "https://projects.adainforma.tk/adaknowledgehub/api/v1/source/list";

        RequestQueue queue = Volley.newRequestQueue(OnlineBronnenFragment.super.getContext());

        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Response", response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    System.out.println(jsonResponse);
                    JSONArray jResource = (JSONArray) jsonResponse.get("data");

                    ArrayList<Resource> bronnen = new ArrayList<>();

                    for (int i = 0; i < jResource.length(); i++){
                        JSONObject bron = jResource.getJSONObject(i);
                        bronnen.add(new Resource(bron.getInt("Bron_id"), bron.getString("Titel"), bron.getString("Samenvatting"), bron.getString("Link")));
                    }

                    gv.setAdapter(new ResourceAdapter(bronnen));

                    gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Resource resource = (Resource) gv.getItemAtPosition(i);
                            MainActivity.ReplaceFragment(new BronFragment(resource));
                        }
                    });

                    System.out.println(jsonResponse.get("data"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, error -> {
            Toast.makeText(OnlineBronnenFragment.super.getContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            error.printStackTrace();
        });

        queue.add(request);
    }

}
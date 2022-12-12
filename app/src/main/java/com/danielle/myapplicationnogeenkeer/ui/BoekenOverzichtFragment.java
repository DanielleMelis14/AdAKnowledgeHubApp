package com.danielle.myapplicationnogeenkeer.ui;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentBoekenOverzichtBinding;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentHomeBinding;

public class BoekenOverzichtFragment extends Fragment {

    private FragmentBoekenOverzichtBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoekenOverzichtBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_terug = root.findViewById(R.id.btn_back);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                BronnenFragment fr = new BronnenFragment();
                fragmentTransaction.replace(android.R.id.content, fr);
                fragmentTransaction.commit();
            }
        });
        return root;
    }


}
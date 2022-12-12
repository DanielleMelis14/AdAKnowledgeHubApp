package com.danielle.myapplicationnogeenkeer.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentBoekenOverzichtBinding;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentOnlineBronnenBinding;

public class OnlineBronnenFragment extends Fragment {

    private FragmentOnlineBronnenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnlineBronnenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_terug = root.findViewById(R.id.btn_back6);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.PreviousFragment();
            }
        });
        return root;

    }
}
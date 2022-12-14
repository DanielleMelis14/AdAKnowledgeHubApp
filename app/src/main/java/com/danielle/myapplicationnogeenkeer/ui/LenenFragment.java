package com.danielle.myapplicationnogeenkeer.ui;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentLenenBinding;

public class LenenFragment extends Fragment {

    private FragmentLenenBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLenenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_terug = root.findViewById(R.id.btn_back5);

        button_terug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.PreviousFragment();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
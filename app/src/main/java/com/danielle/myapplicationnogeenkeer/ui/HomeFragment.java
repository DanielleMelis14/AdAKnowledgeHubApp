package com.danielle.myapplicationnogeenkeer.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.danielle.myapplicationnogeenkeer.Lenen;
import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_lenen = root.findViewById(R.id.button_boeken);
        Button button_inleveren = root.findViewById(R.id.button_online_bronnen);
        Button button_huidigeleningen = root.findViewById(R.id.button_opgeslagen);

        button_lenen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Jeremy
//                MainActivity.ReplaceFragment(new Lenen());
            }
        });

        button_inleveren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ReplaceFragment(new InleverenFragment());
            }
        });

        button_huidigeleningen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ReplaceFragment(new HuidigeLeningenFragment());
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
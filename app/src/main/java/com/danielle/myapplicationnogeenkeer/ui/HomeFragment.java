package com.danielle.myapplicationnogeenkeer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.danielle.myapplicationnogeenkeer.BoekRegistreren;
import com.danielle.myapplicationnogeenkeer.Inleveren;
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
        Button button_boekregistreren = root.findViewById(R.id.button_registreer_boek);

        button_lenen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Jeremy
//                MainActivity.ReplaceFragment(new Lenen());
                Intent intent = new Intent(getContext(), Lenen.class);
                startActivity(intent);
            }
        });

        button_inleveren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Inleveren.class);
                startActivity(intent);
            }
        });

        button_huidigeleningen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.ReplaceFragment(new HuidigeLeningenFragment());
            }
        });

        button_boekregistreren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), BoekRegistreren.class);
                startActivity(intent);
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
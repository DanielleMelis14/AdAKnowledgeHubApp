package com.danielle.myapplicationnogeenkeer.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentBronnenBinding;

public class BronnenFragment extends Fragment {

    private FragmentBronnenBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentBronnenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_boeken = root.findViewById(R.id.button_boeken);

        button_boeken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("isabel", "dqwdwqdwqwdqwq");
                MainActivity.ReplaceFragment(new BoekenOverzichtFragment());
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
package com.danielle.myapplicationnogeenkeer.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.danielle.myapplicationnogeenkeer.LoginPage;
import com.danielle.myapplicationnogeenkeer.MaakAccount;
import com.danielle.myapplicationnogeenkeer.MainActivity;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.databinding.FragmentInstellingenBinding;

public class InstellingenFragment extends Fragment {

    private FragmentInstellingenBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInstellingenBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button button_uitloggen = root.findViewById(R.id.button_loguit);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
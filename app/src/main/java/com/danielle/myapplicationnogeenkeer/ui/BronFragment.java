package com.danielle.myapplicationnogeenkeer.ui;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.models.Resource;

public class BronFragment extends Fragment {
    private Resource resource;

    public BronFragment(Resource resource){this.resource = resource;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bron, container, false);

        TextView tv_title = v.findViewById(R.id.title_bron);
        tv_title.setText(resource.getTitel());

        TextView tv_samenvatting = v.findViewById(R.id.txtBron);
        tv_samenvatting.setText(resource.getSamenvatting());

        TextView tv_link = v.findViewById(R.id.BronLink);
        tv_link.setText(resource.getLink());

        return v;
    }


}
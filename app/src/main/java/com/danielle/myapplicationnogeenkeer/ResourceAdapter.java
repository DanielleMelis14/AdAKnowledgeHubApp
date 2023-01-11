package com.danielle.myapplicationnogeenkeer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danielle.myapplicationnogeenkeer.models.Resource;

import java.util.List;

public class ResourceAdapter extends BaseAdapter {
    List<Resource> resources;

    public ResourceAdapter(List<Resource> resources){this.resources = resources;}

    @Override
    public int getCount(){return resources.size();}

    @Override
    public Object getItem(int i) {
        return resources.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Resource resource = (Resource) getItem(i);

        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.cardview_online_bronnen, null);
        }

        TextView tvResourceName = view.findViewById(R.id.textview_resource);
        tvResourceName.setText(resource.getTitel());

        return view;
    }

}

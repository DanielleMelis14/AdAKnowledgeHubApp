package com.danielle.myapplicationnogeenkeer.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.danielle.myapplicationnogeenkeer.R;
import com.danielle.myapplicationnogeenkeer.models.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BoekFragment extends Fragment {

    private Book book;

    public BoekFragment(Book book){
        this.book = book;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_boek, container, false);

        TextView tv_title = v.findViewById(R.id.title_boek);
        tv_title.setText(book.getTitel());

        TextView tv_samenvatting = v.findViewById(R.id.txtBoek2);
        tv_samenvatting.setText(book.getSamenvatting());

        ImageView iv_image = v.findViewById(R.id.image_books);
        String imgURL = book.getFoto();

        Glide
                .with(v)
                .load(imgURL)
                .centerCrop()
                .placeholder(v.getContext().getDrawable(R.drawable.logo_ada))
                .into(iv_image);

        return v;
    }
}
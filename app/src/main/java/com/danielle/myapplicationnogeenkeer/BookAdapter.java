package com.danielle.myapplicationnogeenkeer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import com.bumptech.glide.Glide;
import com.danielle.myapplicationnogeenkeer.models.Book;

public class BookAdapter extends BaseAdapter {
    List<Book> books;

    public BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int i) {
        return books.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Book book = (Book) getItem(i);

        if(view == null){
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.cardview_boeken, null);
        }

        TextView tvBookName = view.findViewById(R.id.textview_book);
        tvBookName.setText(book.getTitel());

        ImageView ivBook = view.findViewById(R.id.image_books);

        String imgURL = book.getFoto();

        Glide
                .with(view)
                .load(imgURL)
                .centerCrop()
                .placeholder(view.getContext().getDrawable(R.drawable.logo_ada))
                .into(ivBook);

        return view;
    }


}
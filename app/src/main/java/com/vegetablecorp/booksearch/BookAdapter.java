package com.vegetablecorp.booksearch;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    // Create a constant tag for so we know log messages came from this class
    private static final String LOG_TAG = "BookAdapter";

    /**
     * Constructs a new {@link BookAdapter}.
     *
     * @param context of the app
     * @param books   is the list of books, which is the data source of the adapter
     */
    public BookAdapter(Context context, ArrayList<Book> books) {
        super(context, 0, books);
    }

    static class ViewHolder {
        private TextView authorView;
        private TextView titleView;
    }

    /**
     * Returns a list item view that displays information about the book at the given position
     * in the list.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        ViewHolder holder;

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.authorView = listItemView.findViewById(R.id.lbl_txt_authors);
            holder.titleView = listItemView.findViewById(R.id.lbl_txt_title);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        // Find the item at the given position in the list
        Book currentBook = getItem(position);

        String a = currentBook.getmAuthors();
        // removes the extra formatting from the parse information
        a = a.replace("[\"", "");
        a = a.replace("\"]", "");
        a = a.replace("\",\"", ", ");
        holder.authorView.setText(a);
        Log.i(LOG_TAG, a);

        // Title
        holder.titleView.setText(currentBook.getmTitle());

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }
}
package com.vegetablecorp.booksearch;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {

    // Create a constant tag for so we know log messages came from this class
    private static final String LOG_TAG = BookActivity.class.getName();
    // int value for the book loader ID
    private static final int BOOK_LOADER_ID = 1;
    // Book Adapter
    private BookAdapter mAdapter;
    // base URL for the search API request
    private String baseApiUrl;
    // String from MainActivity
    private String QUERY_KEY;
    // search URL used for the API request (baseApiUrl + QUERY_KEY
    private String searchURL;
    // TextView that is displayed when the list is empty
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        QUERY_KEY = getIntent().getStringExtra("key").trim();
        Log.i(LOG_TAG, "QUERY_KEY passed = " + QUERY_KEY);

        baseApiUrl = getString(R.string.GOOGLE_API_SEARCH);
        searchURL = String.format(baseApiUrl + QUERY_KEY);
        Log.i(LOG_TAG, "Search URL = " + searchURL);

        ListView bookListView = (ListView) findViewById(R.id.list);

        // Add the empty state TextView to a variable
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        // Set the empty view on the list
        bookListView.setEmptyView(mEmptyStateTextView);

        mAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListView.setAdapter(mAdapter);

        // Create new ConnectivityManager to check state of the network
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection, fetch the data
        if (networkInfo != null && networkInfo.isConnected()) {

            // Create new LoaderManager to manage our loaders
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);

        } else {
            // Else hide loading indicator
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Display error message in the empty state TextView
            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }

        bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current book that was clicked on
                Book currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri bookUri = Uri.parse(String.valueOf(currentBook.getmInfoLink()));

                // Create a new intent to view the book URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {


        return new BookLoader(this, searchURL);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);


        // Set empty state text to display "No books found."
        mEmptyStateTextView.setText(R.string.no_books);

        // Clear the adapter of previous book data
        mAdapter.clear();

        // If there is a valid list of {@link Book}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            mAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}

package com.vegetablecorp.booksearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Create a constant tag for so we know log messages came from this class
    private static final String LOG_TAG = MainActivity.class.getName();

    private EditText searchString;
    private ImageView button;
    private String QUERY_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchString = (EditText) findViewById(R.id.searchString);
        button = (ImageView) findViewById(R.id.lbl_btn_search);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QUERY_KEY = String.valueOf(searchString.getText());
                if (QUERY_KEY.trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), R.string.no_search_item, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, BookActivity.class);
                    intent.putExtra("key", QUERY_KEY);
                    Log.i(LOG_TAG, "QUERY_KEY = " + QUERY_KEY);
                    startActivity(intent);
                    searchString.setText("");
                }
            }
        });
    }
}

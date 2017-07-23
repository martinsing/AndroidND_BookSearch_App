package com.vegetablecorp.booksearch;

/**
 * Created by Megatron on 2017-07-19.
 */

public class Book {

    private String mTitle;
    private String mAuthors;
    private String mInfoLink;

    public String getmTitle() {
        return mTitle;
    }

    public String getmAuthors() {
        return mAuthors;
    }

    public String getmInfoLink() {
        return mInfoLink;
    }

    public Book(String mTitle, String mAuthors, String mInfoLink) {
        this.mTitle = mTitle;
        this.mAuthors = mAuthors;
        this.mInfoLink = mInfoLink;
    }
}

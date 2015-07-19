package com.hoodbluck.authum.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.hoodbluck.authum.data.prefs.Prefs_;

import org.androidannotations.annotations.EProvider;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created on 7/19/15.
 *
 * @author Skye Schneider
 */
@EProvider
public class EmailProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.hoodbluck.authum.provider";
    static final String URL = "content://" + PROVIDER_NAME + "/email";
    static final Uri CONTENT_URI = Uri.parse(URL);

    public EmailProvider() {
        super();
    }

    @Pref
    Prefs_ mPrefs;


    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        MatrixCursor cursor = new MatrixCursor(new String[]{"user"});
        cursor.addRow(new String[]{mPrefs.email().get()});
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}

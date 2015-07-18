package com.hoodbluck.authum.activities;

import android.app.Activity;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity
public class BaseActivity extends Activity {

    @UiThread
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

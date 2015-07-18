package com.hoodbluck.authum.activities;

import android.app.Activity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity
public class BaseActivity extends Activity {

    @AfterViews
    public void afterViews() {
    }
}

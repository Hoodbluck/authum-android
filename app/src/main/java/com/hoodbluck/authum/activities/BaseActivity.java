package com.hoodbluck.authum.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.hoodbluck.authum.R;

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

    @UiThread
    public void showAlert(String message, DialogInterface.OnClickListener onClickListener) {
        if (onClickListener == null) {
            onClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setMessage(message)
                .setTitle(getString(R.string.aw_shucks))
                .setPositiveButton(getString(R.string.ok), onClickListener)
                .create();
        dialog.show();
    }

    public void showAlert(String message) {
        showAlert(message, null);
    }
}

package com.hoodbluck.authum.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.hoodbluck.authum.R;
import com.hoodbluck.authum.data.prefs.Prefs_;
import com.hoodbluck.authum.managers.UserManager;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.apache.commons.lang.StringUtils;

import java.util.List;

import me.zhanghai.patternlock.ConfirmPatternActivity;
import me.zhanghai.patternlock.PatternUtils;
import me.zhanghai.patternlock.PatternView;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity
public class ConfirmAuthumPatterActivity extends ConfirmPatternActivity {

    @Pref
    Prefs_ mPrefs;

    @Bean
    UserManager mUserManager;

    @Extra
    String mClientId;

    @Override
    protected boolean isStealthModeEnabled() {
        // TODO: Return the value from SharedPreferences.
        return false;
    }

    @Override
    protected boolean isPatternCorrect(List<PatternView.Cell> pattern) {
        // TODO: Get saved pattern sha1.
        String patternSha = mPrefs.patterSha().get();
        boolean isCorrect = StringUtils.equals(PatternUtils.patternToSha1String(pattern), patternSha);
        authenticate(isCorrect);
        return isCorrect;
    }

    @Background
    protected void authenticate(boolean isCorrect) {
        mUserManager.authenticate(mPrefs.email().get(), mClientId, isCorrect);
        finishThis();
    }

    @UiThread
    protected void finishThis() {
        finish();
    }

    @Override
    protected void onForgotPassword() {
        mPrefs.patterSha().put("");
        AlertDialog dialog = new AlertDialog.Builder(ConfirmAuthumPatterActivity.this)
                .setTitle(getString(R.string.aw_shucks))
                .setMessage(getString(R.string.reset_pattern))
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LoginActivity_.intent(ConfirmAuthumPatterActivity.this).start();

                        // Finish with RESULT_FORGOT_PASSWORD.
                        ConfirmAuthumPatterActivity.super.onForgotPassword();
                    }
                })
                .create();
        dialog.show();


    }
}

package com.hoodbluck.authum.activities;

import android.util.Log;
import android.widget.Toast;

import com.hoodbluck.authum.data.prefs.Prefs_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.List;

import me.zhanghai.patternlock.PatternUtils;
import me.zhanghai.patternlock.PatternView;
import me.zhanghai.patternlock.SetPatternActivity;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity
public class PatternRegistrationActivity extends SetPatternActivity {

    @Pref
    Prefs_ mPrefs;

    public static final String TAG = PatternRegistrationActivity.class.getSimpleName();
    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha = PatternUtils.patternToSha1String(pattern);
        Log.d(TAG, "patterSha = " + patternSha);
        mPrefs.patterSha().put(patternSha);
        Toast.makeText(this, "Authum is ready to use", Toast.LENGTH_LONG).show();
    }
}

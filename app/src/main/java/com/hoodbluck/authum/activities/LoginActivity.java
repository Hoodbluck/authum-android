package com.hoodbluck.authum.activities;

import android.content.DialogInterface;
import android.widget.EditText;

import com.hoodbluck.authum.R;
import com.hoodbluck.authum.data.prefs.Prefs_;
import com.hoodbluck.authum.managers.UserManager;
import com.hoodbluck.authum.utils.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.util.ArrayList;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity{

    @ViewById(R.id.email_input)
    EditText mEmailInput;
    @ViewById(R.id.password_input)
    EditText mPasswordInput;

    @Bean
    UserManager mUserManager;

    @Pref
    Prefs_ mPrefs;

    private DialogInterface.OnClickListener mOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            PatternRegistrationActivity_.intent(LoginActivity.this).start();
            finish();
        }
    };

    @AfterViews
    public void afterViews() {

    }

    @Click(R.id.register_button)
    public void onRegistrationButtonClick() {
        RegisterActivity_.intent(this)
                .start();
        finish();
    }

    @Click(R.id.login_button)
    public void onLoginButtonClick() {
        ArrayList<EditText> editTextList = new ArrayList<>();
        editTextList.add(mEmailInput);
        editTextList.add(mPasswordInput);
        if (ViewUtil.isFormValid(this, editTextList)) {
           logUserIn();
        }
    }

    @Background
    protected void logUserIn() {
        mUserManager.login(mEmailInput.getText().toString().trim(), mPasswordInput.getText().toString().trim(), new UserManager.UserLoginCallback() {
            @Override
            public void loginSuccess() {
                showToast("Login a success");
                mPrefs.email().put(mEmailInput.getText().toString().trim());
                showAlert(getString(R.string.no_pattern), mOnClickListener);
            }

            @Override
            public void loginFailUnknown() {
                showToast("Login failed for an unknown reason");
            }

            @Override
            public void loginFailInvalidCredential() {
                showAlert(getString(R.string.invalid_credentials));
            }
        });

    }

}

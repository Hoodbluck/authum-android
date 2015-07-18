package com.hoodbluck.authum.activities;

import android.widget.EditText;

import com.hoodbluck.authum.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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

    @AfterViews
    public void afterViews() {

    }

    @Click(R.id.register_button)
    public void onRegistrationButtonClick() {
        RegisterActivity_.intent(this)
                .start();
    }
}

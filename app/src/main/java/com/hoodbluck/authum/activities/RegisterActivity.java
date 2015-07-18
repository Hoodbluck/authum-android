package com.hoodbluck.authum.activities;

import android.util.Log;
import android.widget.EditText;

import com.hoodbluck.authum.R;
import com.hoodbluck.authum.managers.UserManager;
import com.hoodbluck.authum.models.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    @ViewById(R.id.first_name_input)
    EditText mFirstNameInput;
    @ViewById(R.id.last_name_input)
    EditText mLastNameInput;
    @ViewById(R.id.email_input)
    EditText mEmailInput;
    @ViewById(R.id.password_input)
    EditText mPasswordInput;
    @ViewById(R.id.re_enter_password_input)
    EditText mReEnterPasswordInput;

    @Bean
    UserManager mUserManager;

    @AfterViews
    public void afterViews() {
    }

    @Click(R.id.register_button)
    public void onRegisterButtonClick() {
        Log.d(TAG, "Please log this");
        if(isFormValid()) {
            User user =  new User(
                    mFirstNameInput.getText().toString(),
                    mLastNameInput.getText().toString(),
                    mEmailInput.getText().toString(),
                    mPasswordInput.getText().toString()
            );
            registerUser(user);
        }
    }

    private boolean isFormValid() {
        boolean valid = true;
        ArrayList<EditText> inValidForms = new ArrayList<>();
        if (StringUtils.isEmpty(mFirstNameInput.getText().toString())) {
            mFirstNameInput.setHint(getString(R.string.first_name_required));
            inValidForms.add(mFirstNameInput);
            valid = false;
        }
        if (StringUtils.isEmpty(mLastNameInput.getText().toString())) {
            mLastNameInput.setHint(getString(R.string.last_name_required));
            inValidForms.add(mLastNameInput);
            valid = false;
        }
        if (StringUtils.isEmpty(mEmailInput.getText().toString())) {
            mEmailInput.setHint(getString(R.string.email_required));
            inValidForms.add(mEmailInput);
            valid = false;
        }
        if (StringUtils.isEmpty(mPasswordInput.getText().toString())) {
            mPasswordInput.setHint(getString(R.string.password_required));
            inValidForms.add(mPasswordInput);
            valid = false;
        }
        if (StringUtils.isEmpty(mReEnterPasswordInput.getText().toString())) {
            mReEnterPasswordInput.setHint(getString(R.string.password_required));
            inValidForms.add(mReEnterPasswordInput);
            valid = false;
        }

        if (!StringUtils.equals(mPasswordInput.getText().toString(), mReEnterPasswordInput.getText().toString())) {
            mReEnterPasswordInput.setHint(getString(R.string.password_do_not_equal));
            mReEnterPasswordInput.setText("");
            valid = false;
            inValidForms.add(mReEnterPasswordInput);
        }

        if (!valid) {
            setHintColorToRed(inValidForms);
        }


        return valid;
    }

    private void setHintColorToRed(ArrayList<EditText> inValidForms) {
        for (EditText editText : inValidForms) {
            editText.setHintTextColor(getResources().getColor(R.color.red));
        }
    }

    @Background
    protected void registerUser(User user) {
        mUserManager.register(user, new UserManager.UserRegistrationCallback() {
            @Override
            public void registrationSuccess() {
                showToast("registration a success");
            }

            @Override
            public void registrationFailureUnknown() {
                showToast("registration failed unknown");
            }

            @Override
            public void registrationFailureUserInvalid() {
                showToast("registration failed invalid");
            }

            @Override
            public void registgrationFailureUserDupilcated() {
                showToast("registration failed duplicate");
            }
        });
    }
}

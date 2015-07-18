package com.hoodbluck.authum.activities;

import android.util.Log;
import android.widget.EditText;

import com.hoodbluck.authum.R;
import com.hoodbluck.authum.managers.UserManager;
import com.hoodbluck.authum.models.User;
import com.hoodbluck.authum.utils.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

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
        ArrayList<EditText> editTexts = new ArrayList<>();
        editTexts.add(mFirstNameInput);
        editTexts.add(mLastNameInput);
        editTexts.add(mEmailInput);
        editTexts.add(mPasswordInput);
        editTexts.add(mReEnterPasswordInput);
        if(ViewUtil.isFormValid(this, editTexts)) {
            User user =  new User(
                    mFirstNameInput.getText().toString().trim(),
                    mLastNameInput.getText().toString().trim(),
                    mEmailInput.getText().toString().trim(),
                    mPasswordInput.getText().toString().trim()
            );
            registerUser(user);
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

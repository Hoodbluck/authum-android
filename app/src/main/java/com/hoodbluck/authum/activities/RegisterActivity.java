package com.hoodbluck.authum.activities;

import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.hoodbluck.authum.R;
import com.hoodbluck.authum.data.prefs.Prefs_;
import com.hoodbluck.authum.managers.UserManager;
import com.hoodbluck.authum.models.User;
import com.hoodbluck.authum.utils.ViewUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends BaseActivity {

    public static final String TAG = RegisterActivity.class.getSimpleName();
    private static final String SENDER_ID = "303628330526";

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

    @Pref
    Prefs_ mPrefs;

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
    protected void registerUser(final User user) {

        String token = requestGcmToken();
        if (token != null) {
            user.setDeviceToken(token);
            mUserManager.register(user, new UserManager.UserRegistrationCallback() {
                @Override
                public void registrationSuccess() {
                    mPrefs.email().put(user.getEmail());
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

        else {
            showAlert("Something went wrong with the registration.");
        }
    }

    private String requestGcmToken() {
        try {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
            return gcm.register(SENDER_ID);

        } catch (IOException ex) {
            return null;
        }
    }
}

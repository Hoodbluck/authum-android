package com.hoodbluck.authum.managers;

import com.hoodbluck.authum.data.http.AuthumHttpClient;
import com.hoodbluck.authum.models.AuthumResponse;
import com.hoodbluck.authum.models.User;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@EBean(scope = EBean.Scope.Singleton)
public class UserManager {

    private User mUser;

    @Bean
    AuthumHttpClient mAuthumHttpClient;

    public void register(User user, UserRegistrationCallback callback) {
        AuthumResponse response = mAuthumHttpClient.register(user);
        if (response == null) {
            callback.registrationFailureUnknown();
            return;
        }

        if (response.getCode() >= 0 ) {
            callback.registrationSuccess();
        }

        else if (response.getCode() < 0) {
            AuthumResponse.AuthumResponseStatus status = AuthumResponse.AuthumResponseStatus.getAuthumResponse(response.getStatus());
            switch (status) {
                case USER_INVALID:
                    callback.registrationFailureUserInvalid();
                    break;
                case USER_ALREADY_REGISTERED:
                    callback.registgrationFailureUserDupilcated();
                    break;
                default:
                    callback.registrationFailureUnknown();
                    break;
            }
        }
    }

    public void login(String username, String password, UserLoginCallback callback) {
        AuthumResponse response = mAuthumHttpClient.login(username, password);
        if (response == null) {
            callback.loginFailUnknown();
            return;
        }

        if (response.getCode() >= 0) {
            callback.loginSuccess();
        }
        else if (response.getCode() < 0) {
            AuthumResponse.AuthumResponseStatus status = AuthumResponse.AuthumResponseStatus.getAuthumResponse(response.getStatus());
            switch (status) {
                case LOGIN_INVALID:
                    callback.loginFailInvalidCredential();
                    break;
                default:
                    callback.loginFailUnknown();
                    break;
            }
        }

    }

    public static abstract class UserRegistrationCallback {
        public abstract void registrationSuccess();
        public abstract void registrationFailureUnknown();
        public abstract void registrationFailureUserInvalid();
        public abstract void registgrationFailureUserDupilcated();
    }

    public static abstract class UserLoginCallback {
        public abstract void loginSuccess();
        public abstract void loginFailUnknown();
        public abstract void loginFailInvalidCredential();

    }
}

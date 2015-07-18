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
            }
        }

    }

    public static abstract class UserRegistrationCallback {
        public abstract void registrationSuccess();
        public abstract void registrationFailureUnknown();
        public abstract void registrationFailureUserInvalid();
        public abstract void registgrationFailureUserDupilcated();
    }
}

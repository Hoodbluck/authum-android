package com.hoodbluck.authum.data.http;

import com.hoodbluck.authum.models.AuthumResponse;
import com.hoodbluck.authum.models.User;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */

@EBean
public class AuthumHttpClient {

    @RestService
    AuthumHttpInterface mService;

    public AuthumResponse register(User user) {
        return mService.register(user);
    }

    public AuthumResponse login(String username, String password) {
        return mService.login(username, password);
    }

    public AuthumResponse authenticate(String email, String appId, String isCorrect) {
        return mService.authenticate(email, appId, isCorrect);
    }
}

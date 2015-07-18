package com.hoodbluck.authum.data.http;

import com.hoodbluck.authum.models.AuthumResponse;
import com.hoodbluck.authum.models.User;

import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.androidannotations.api.rest.RestClientSupport;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@Rest(rootUrl = "http://authum.hoodbluck.com",
        converters = { StringHttpMessageConverter.class, GsonHttpMessageConverter.class},
        interceptors = RestHeaderInterceptor.class)
public interface AuthumHttpInterface extends RestClientRootUrl, RestClientSupport, RestClientHeaders {

        @Post("/user")
        AuthumResponse register(User user);
}


package com.hoodbluck.authum.data.http;

import com.hoodbluck.authum.models.AuthumResponse;
import com.hoodbluck.authum.models.User;

import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.androidannotations.api.rest.RestClientRootUrl;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Created on 7/18/15.
 *
 * @author Skye Schneider
 */
@Rest(converters = { StringHttpMessageConverter.class, GsonHttpMessageConverter.class})
public interface AuthumHttpInterface extends RestClientRootUrl, RestClientHeaders {

        AuthumResponse register();
}


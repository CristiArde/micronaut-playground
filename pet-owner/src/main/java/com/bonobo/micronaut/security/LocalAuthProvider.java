package com.bonobo.micronaut.security;

import io.micronaut.security.authentication.*;
import io.reactivex.rxjava3.core.Flowable;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;


/**
 * LocalAuthProvider implements the standard AuthenticationProvider
 * interface by concretely defining the authenticate() method. In the
 * authenticate() method, we simply check whether the identity and secret
 * specified in the user request match any username and password in the identity
 * store. If we find a match, then we return the UserDetails object, else we return
 * AuthenticatonFailed
 */

@Singleton
public class LocalAuthProvider implements AuthenticationProvider {
    @Inject
    IdentityStore store;

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        String username =
                authenticationRequest.getIdentity().toString();
        String password =
                authenticationRequest.getSecret().toString();
        if (password.equals(store.getUserPassword
                (username))) {
            UserDetails details = new UserDetails
                    (username, Collections.singletonList
                            (store.getUserRole(username)));
            return Flowable.just(details);
        } else {
            return Flowable.just(new
                    AuthenticationFailed());
        }
    }
}

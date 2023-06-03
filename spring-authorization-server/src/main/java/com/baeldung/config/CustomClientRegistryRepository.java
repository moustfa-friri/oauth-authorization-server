package com.baeldung.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Set;


//@Service("registeredClientRepository")
public class CustomClientRegistryRepository implements RegisteredClientRepository {

    public void save(RegisteredClient registeredClient) {

    }


    public RegisteredClient findById(String id) {

        return   mapToRegisteredClient();
    }


    public RegisteredClient findByClientId(String clientId) {

        return   mapToRegisteredClient();
    }
@Autowired
    PasswordEncoder passwordEncoder;
    private RegisteredClient mapToRegisteredClient() {

         RegisteredClient.Builder serviceClient = RegisteredClient.withId("1")
                .clientId("client-id")
                .clientSecret(passwordEncoder.encode("secret"))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC);

               /* .clientAuthenticationMethods((authenticationMethods) -> {
                    clientAuthenticationMethods.forEach((authenticationMethod) -> {
                        authenticationMethods.add(resolveClientAuthenticationMethod(authenticationMethod));
                    });
                });*/
        serviceClient
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .authorizationGrantType(AuthorizationGrantType.JWT_BEARER)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/articles-client-oidc")
                .redirectUri("http://127.0.0.1:8080/authorized")
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .redirectUri("https://oidcdebugger.com/debug")
                .scope(OidcScopes.OPENID)
                .scope("articles.read")
                .tokenSettings(TokenSettings.builder().setting("hi","hiii").build())
                 .build();

        return serviceClient.build();

    }

    private static ClientAuthenticationMethod resolveClientAuthenticationMethod(String clientAuthenticationMethod) {
        if (ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue().equalsIgnoreCase(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_BASIC;
        } else if (ClientAuthenticationMethod.CLIENT_SECRET_POST.getValue().equalsIgnoreCase(clientAuthenticationMethod)) {
            return ClientAuthenticationMethod.CLIENT_SECRET_POST;
        } else {
            return ClientAuthenticationMethod.NONE.getValue().equalsIgnoreCase(clientAuthenticationMethod) ? ClientAuthenticationMethod.NONE : new ClientAuthenticationMethod(clientAuthenticationMethod);
        }
    }

    private static AuthorizationGrantType resolveAuthorizationGrantType(String authorizationGrantType) {
        if (AuthorizationGrantType.AUTHORIZATION_CODE.getValue().equalsIgnoreCase(authorizationGrantType)) {
            return AuthorizationGrantType.AUTHORIZATION_CODE;
        } else if (AuthorizationGrantType.CLIENT_CREDENTIALS.getValue().equalsIgnoreCase(authorizationGrantType)) {
            return AuthorizationGrantType.CLIENT_CREDENTIALS;
        } else {
            return AuthorizationGrantType.REFRESH_TOKEN.getValue().equalsIgnoreCase(authorizationGrantType) ? AuthorizationGrantType.REFRESH_TOKEN : new AuthorizationGrantType(authorizationGrantType);
        }
    }
}

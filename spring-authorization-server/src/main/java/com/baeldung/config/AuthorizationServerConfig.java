package com.baeldung.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.util.UUID;

@Configuration
 public class AuthorizationServerConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
     @Bean
     @Order(1)
     public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http)
             throws Exception {
         OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

         http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                 .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
         http
                 // Redirect to the login page when not authenticated from the
                 // authorization endpoint
                 .exceptionHandling((exceptions) -> exceptions
                         .authenticationEntryPoint(
                                 new LoginUrlAuthenticationEntryPoint("/login"))
                 )
                 // Accept access tokens for User Info and/or Client Registration
                 //    .authenticationManager(OidcUserInfoAuthenticationToken::new)
                 .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
         // ;
         ;
         return http.build();
     }


     @Bean
    public RegisteredClientRepository registeredClientRepository() {
        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
          .clientId("client-id")
          .clientSecret(passwordEncoder.encode("secret"))
          .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
          .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
          .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/articles-client-oidc")
                .redirectUri("http://127.0.0.1:8080/authorized")
                .redirectUri("https://oauth.pstmn.io/v1/callback")
                .redirectUri("https://oidcdebugger.com/debug")
          .scope(OidcScopes.OPENID)
          .scope("read")
          .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }


    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }



}
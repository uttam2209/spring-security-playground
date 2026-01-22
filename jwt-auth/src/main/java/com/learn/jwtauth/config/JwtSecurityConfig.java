//package com.learn.jwtauth.config;
//
//import com.learn.jwtauth.service.JwtUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.*;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static com.learn.jwtauth.util.JWTUtil.SECRET_KEY;
//
//@Configuration
//@EnableWebSecurity
//public class JwtSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(reqs ->
//                        reqs.requestMatchers("/v1/jwt-auth/authenticate", "/h2-console/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .csrf(AbstractHttpConfigurer::disable)
//                .oauth2ResourceServer(oauth2 ->
//                        oauth2.jwt(jwt -> jwt.decoder(customJwtDecoder())))
//                .sessionManagement(sessionManagement ->
//                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//
//    /*
//    This bean is needed because (explicit configuration):
//    - Spring Security cannot assume how you want to validate JWTs because there are multiple approaches:
//        1. Symmetric Key (HMAC) (my case, You sign/verify with a shared secret)
//        2. Asymmetric Key (RSA/ECDSA) public key
//        3. JWK Set URI (External Auth Server)
//        Spring can't guess which one you need, so it requires you to provide the JwtDecoder bean.
//
//        Spring boot will auto configure in below cases:
//        Option 1: Using JWK Set URI (External OAuth2 Server)
//        Option 2: Using Issuer URI
//     */
//    @Bean
//    public JwtDecoder customJwtDecoder() {
//        NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(SECRET_KEY).build();
//
//        OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(
//                new JwtTimestampValidator(),
//                new JwtClaimValidator<String>("scope", s -> s.equals("test"))
//        );
//
//        decoder.setJwtValidator(validator);
//        return decoder;
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, JwtUserDetailsService jwtUserDetailsService) {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
//        daoAuthenticationProvider.setUserDetailsService(jwtUserDetailsService);
//        return new ProviderManager(daoAuthenticationProvider);
//    }
//}

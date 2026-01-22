Some basic notes:
- Whenever you add spring security dependency to your project, spring automatically adds a default form based authentication (UsernameAndPasswordAuthenticationFilter)
- The default auth will have user as `user` and `password` is auto generated when the app starts up.
- By adding a simple SecurityFilterChain config with .httpBasic(withDefaults()), we can add explicit user and password in app.yml
- spring.security.user.name & spring.security.user.password
- SecurityFilterChain --> In this, the Authentication always runs (meaning various authentication filter always runs). If authentication successful, it populates SecurityContextHolder.
- Authorization comes after Authentication. permitAll(), anyRequest().authenticated() checks happen after authentication
- For permitAll(), it lets the configured endpoint even if they are authenticated or unauthenticated.

Basic auth module notes:
- This module accepts username and password in the request payload.
- Checks it against the H2 db table.
- If exists and if the info is correct, request succeeds. Else 401
- Working mechanism:
  - Controller endpoint is hit
  - Triggers SecurityFilterChain block. Any non-authorization url are accepted without any auth. Others require auth.
  - .httpBasic(Customizer.withDefaults()) will trigger BasicAuthenticationFilter authentication
  - 

Jwt auth module notes:
- This module accepts username and password as a request from /authenticate endpoint.
- It will use BasicUserNameAndPassword Authentication mechanism to check if requested user/password is correct as per H2 db data.
- If correct, it is going to create a JWT token that can be used by subsequent controller endpoints.
- JWT token passed by subsequent controller endpoint is validated in 2 different implementation.
  - Imp 1:
    - Using OncePerRequestFilter which decodes the JWT token, extracts the username and checks in DB. Also checks the token expiration.
    - This filter is always executed (/authenticate or /health endpoint)
    - For /authenticate, since there is no Bearer token in the request, this filter is skipped. Its validated using the logic present in controller method.
    - For /health, this filter runs and validated the token.
    - In SecurityFilterChain config, addFilterBefore(jwtFilter, UserPasswordFilter) is what making this filter to run always.
  - Imp 2:
    - .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(customJwtDecoder())))
    - JwtDecoder is validating claims and expiration. Much of it is handled by Spring. No extra code (like extracting token, checking username etc)
    - 


!["Spring security auth flow"](auth_flow.png)
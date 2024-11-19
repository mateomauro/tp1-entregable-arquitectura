package com.uncledavecode.api_gateway.Config;

import com.uncledavecode.api_gateway.Security.AuthorityConstant;
import com.uncledavecode.api_gateway.Security.jwt.JwtFilter;
import com.uncledavecode.api_gateway.Security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private final TokenProvider tokenProvider;

  public SecurityConfig( TokenProvider tokenProvider ) {
    this.tokenProvider = tokenProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

  @Bean
  public SecurityFilterChain filterChain(final HttpSecurity http ) throws Exception {
    http
        .csrf( AbstractHttpConfigurer::disable );
    http
        .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
    http
        .securityMatcher("/api/**" )
        .authorizeHttpRequests( authz -> authz
                .requestMatchers( HttpMethod.POST, "/api/authenticate").permitAll()
                .requestMatchers( HttpMethod.POST, "/api/usuarios").permitAll()
                .requestMatchers( HttpMethod.POST,"/api/admins").hasAuthority( AuthorityConstant._ADMIN )
                .requestMatchers( HttpMethod.POST, "/api/users/**").hasAuthority( AuthorityConstant._USER )
                .requestMatchers( HttpMethod.POST, "/api/maintenances").hasAuthority( AuthorityConstant._MANAGER )
                .requestMatchers( HttpMethod.PUT,"/api/admins").hasAuthority( AuthorityConstant._ADMIN )
                .requestMatchers( HttpMethod.PUT,"/api/maintenances").hasAuthority( AuthorityConstant._MANAGER )
                .requestMatchers( HttpMethod.DELETE,"/api/admins").hasAuthority( AuthorityConstant._ADMIN )
                .requestMatchers( HttpMethod.DELETE,"/api/maintenances").hasAuthority( AuthorityConstant._MANAGER )
                .requestMatchers( HttpMethod.GET, "/api/admins/**").hasAuthority( AuthorityConstant._ADMIN )
                .requestMatchers( HttpMethod.GET, "/api/maintenances/**").hasAuthority( AuthorityConstant._ADMIN )
                .requestMatchers( HttpMethod.GET, "/api/maintenances/**").hasAuthority( AuthorityConstant._MANAGER )
                .requestMatchers( HttpMethod.GET, "/api/users/scootersNearby/**").hasAuthority( AuthorityConstant._USER )
                .anyRequest().authenticated()
        )
        .httpBasic( Customizer.withDefaults() )
        .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
    return http.build();
  }
  /*
  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll() // Permitir las rutas públicas
            .antMatchers("/api/secure/**").authenticated() // Proteger las rutas seguras
            .anyRequest().denyAll() // Denegar el resto
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Filtro JW*/
}

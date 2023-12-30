package com.lcwd.electronic_store.electronic_store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    UserDetailsService userDetailsService;
    private String[] swaggerArr={
            "/swagger-ui/**"
            ,"/webjars/**",
            "/swagger-resources/**"
            ,"/v3/api-docs"
    };
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user= User.builder().username("inderjeet")
//                .password(passwordEncoder()
//                .encode("sagar"))
//                .roles("admin")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(crsf->crsf.disable())
                .authorizeHttpRequests(auth->auth.
                        requestMatchers("/product/getAll","/product/create").permitAll()
                        .requestMatchers(swaggerArr).permitAll()
                        .anyRequest().authenticated());
        return httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

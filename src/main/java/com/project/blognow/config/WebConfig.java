package com.project.blognow.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class WebConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(auth->auth.disable()).authorizeHttpRequests((auth)->{auth.requestMatchers(antMatcher("/css/**")).permitAll();
        auth.requestMatchers(antMatcher("/js/**")).permitAll();
        auth.requestMatchers(antMatcher("/images/**")).permitAll();
        auth.requestMatchers(antMatcher("/fonts/**")).permitAll();
        auth.requestMatchers(antMatcher("/webjars/**")).permitAll();
        auth.requestMatchers(antMatcher("/")).permitAll();
        auth.requestMatchers(antMatcher("/rss/**")).permitAll();
        auth.requestMatchers(antMatcher("/register/**")).permitAll();
        auth.requestMatchers(antMatcher("/posts/**")).permitAll();
        auth.anyRequest().authenticated();
        }).
                formLogin(form->form.loginPage("/login").loginProcessingUrl("/login").
                usernameParameter("email").passwordParameter("password").defaultSuccessUrl("/posts",true).
                failureUrl("/login?error").permitAll());
        return http.build();

    }

}

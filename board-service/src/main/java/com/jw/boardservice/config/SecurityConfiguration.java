package com.jw.boardservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        httpSecurity.headers().frameOptions().disable();
    }
}

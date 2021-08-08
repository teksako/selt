package com.selt.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.username, u.password,1 FROM user u WHERE u.username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name, 1 " +
                        "FROM user u " +
                        "INNER JOIN user_role ur ON ur.user_id = u.user_id " +
                        "INNER JOIN role r ON r.role_id = ur.role_id " +
                        "WHERE u.username=?")
                .dataSource(dataSource);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/css/**", "/static/css/**", "/js/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                .antMatchers("/index").authenticated()
                .antMatchers("/showLaptops").authenticated()
                .antMatchers("/addEmployee").authenticated()
                .antMatchers("/addLaptop").hasAuthority("ADMIN")
                .antMatchers("/addWindowsLicense").hasAuthority("ADMIN")
                .antMatchers("/addOfficeLicense").hasAuthority("ADMIN")
                .antMatchers("/addDepartment").hasAuthority("ADMIN")
                .antMatchers("/addUser").hasAuthority("ADMIN")
                .antMatchers("/addLocation").hasAuthority("ADMIN")
                .antMatchers("/addComputer").hasAuthority("ADMIN")
                .antMatchers("/showUserHardware").hasAuthority("ADMIN")
                .antMatchers("/addToner").hasAuthority("ADMIN")
                .antMatchers("/addPhone").hasAuthority("ADMIN")
                .antMatchers("/addPhoneNumber").hasAuthority("ADMIN")
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/index", true);




        httpSecurity.csrf().disable()
                .headers().frameOptions().disable();


    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

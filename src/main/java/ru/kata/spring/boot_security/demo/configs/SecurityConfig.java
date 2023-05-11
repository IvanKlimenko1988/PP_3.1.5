package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kata.spring.boot_security.demo.services.UsersDetailsService;




@EnableWebSecurity()
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UsersDetailsService usersDetailsService;

    private final SuccessUserHandler successUserHandler;

    @Autowired
    public SecurityConfig(UsersDetailsService usersDetailsService, SuccessUserHandler successUserHandler) {
        this.usersDetailsService = usersDetailsService;
        this.successUserHandler = successUserHandler;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailsService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/auth/login", "/auth/registration", "/error").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successUserHandler)
                .loginPage("/auth/login")
                .usernameParameter("email")
                .loginProcessingUrl("/process_login")
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/auth/login");
    }

}

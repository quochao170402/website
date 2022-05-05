package com.quochao.website.security;

import com.quochao.website.repository.UserRepository;
import com.quochao.website.service.UserService;
import com.quochao.website.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/v1/products/**", "/api/v1/orders", "/login",
                        "/logout", "/api/v1/register", "/", "/api/v1/feedbacks/**","/api/v1/feedbacks").permitAll()

                .antMatchers("/admin/**").hasAuthority("ADMIN")

                .antMatchers("/api/v1/orders/checkouts", "/api/v1/orders/histories/**",
                        "/api/v1/accounts/**", "/api/v1/reviews/**").hasAuthority("USER")

                .anyRequest().authenticated()

                .and()
                .formLogin().loginPage("/login").permitAll()

                .and()
                .logout().deleteCookies("JSESSIONID").permitAll()

                .and()
                .httpBasic();
    }
}

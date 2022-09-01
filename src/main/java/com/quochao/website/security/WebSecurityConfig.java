package com.quochao.website.security;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig {
//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//
//        http.authorizeRequests()
////                .antMatchers("/api/v1/products/**", "/api/v1/orders", "/login",
////                        "/logout", "/api/v1/register", "/", "/api/v1/feedbacks/**", "/api/v1/feedbacks", "/api/v1/auth/**",
////                        "/api/v1/orders/checkouts", "/api/v1/accounts/**", "/api/v1/reviews/**","/admin/**").permitAll()
//
////                .antMatchers("/admin/**").hasAuthority("ADMIN")
//                .anyRequest().permitAll()
//                .anyRequest().authenticated()
//
////                .and()
////                .formLogin().loginPage("/login").successForwardUrl("/home").permitAll()
////                .and().formLogin().permitAll()
//
//                .and()
//                .logout().deleteCookies("JSESSIONID").permitAll()
//
//                .and()
//                .httpBasic();
//    }
//
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
}

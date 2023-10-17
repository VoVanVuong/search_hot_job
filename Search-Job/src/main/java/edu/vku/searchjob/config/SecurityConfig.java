package edu.vku.searchjob.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        {
                            try {
                                authorizeRequests
                                        .requestMatchers("/home/**","/css/**","/img/**","/js/**","/scss/**","/vendor/**","/.idea/**").permitAll() // Cho phép truy cập /public/** mà không cần xác thực
                                        .requestMatchers("/admin/**").hasRole("ADMIN") // Đối với các URL /admin/**
                                        .requestMatchers("/user/**").hasRole("USER") // Đối với các URL /user/**
                                        .anyRequest().authenticated().and() // Yêu cầu xác thực cho các URL khác
                                        .formLogin(form -> form
                                                .loginPage("/signIn")
                                                .permitAll());


                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(accountService).passwordEncoder(passwordEncoder());
//    }
}
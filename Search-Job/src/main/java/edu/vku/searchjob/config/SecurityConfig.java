package edu.vku.searchjob.config;

// import edu.vku.searchjob.security.CustomUserDetailsService;
import edu.vku.searchjob.repository.IAccountRepository;
//import edu.vku.searchjob.security.AccountDetailService;
import edu.vku.searchjob.security.CustomUserDetailService;
import edu.vku.searchjob.security.LoginFailureHandlerextends;
import edu.vku.searchjob.security.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandlerextends loginFailureHandlerextends;
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        {
                            try {
                                authorizeRequests
                                    .requestMatchers("/","/css/**","/img/**","/js/**","/scss/**","/vendor/**","/.idea/**").permitAll() // Cho phép truy cập /public/** mà không cần xác thực
                                    .requestMatchers("/signUp").permitAll()
                                        .requestMatchers("/employRegister").permitAll()
                                        .requestMatchers("/forgotPass").permitAll()

                                        .requestMatchers("/homes").permitAll()
                                        .requestMatchers("/listCompany").permitAll()
                                        .requestMatchers("/listJob").permitAll()

                                        .requestMatchers("/home").permitAll()
                                        .requestMatchers("/home/details").permitAll()
                                        .requestMatchers("/signIn/**").permitAll()
                                            .requestMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                                        .requestMatchers("/user/**").access("hasRole('ROLE_USER')")// Đối với các URL /admin/**
                                        .requestMatchers("employer/**").access("hasRole('ROLE_EMPLOYER')")
                                        .anyRequest().authenticated()
                                        .and() // Yêu cầu xác thực cho các URL khác
                                        .formLogin(form -> form
                                                .loginPage("/signIn")
                                                .usernameParameter("username")
                                                .passwordParameter("password")
                                                .loginProcessingUrl("/login")
                                             //   .defaultSuccessUrl("/admin/ListCategory")
                                             .successHandler(loginSuccessHandler)
                                            //      .failureHandler(loginFailureHandlerextends)
                                             //   .failureUrl()
                                                .permitAll() )
                                              .logout(logout -> logout
                                                .logoutUrl("/logout") // URL để thực hiện đăng xuất
                                                .logoutSuccessUrl("/signIn") // URL sau khi đăng xuất thành công
//                                                      .logoutSuccessHandler(logoutSuccessHandler())
                                                      .permitAll()
                                        );
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    // authentication
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    }

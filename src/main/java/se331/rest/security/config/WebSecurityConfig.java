package se331.rest.security.config;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import se331.rest.security.controller.JwtAuthenticationEntryPoint;
import se331.rest.security.controller.JwtAuthenticationTokenFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class WebSecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    
    @Autowired
    private final JwtAuthenticationTokenFilter tokenFilter;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .authorizeRequests()
                .antMatchers("/auth/**",  "/refresh", "/register","/changeRoleToUser").permitAll()
                .antMatchers(HttpMethod.GET,"/people/**").hasAnyRole("PATIENT","USER","ADMIN","DOCTOR")
                .antMatchers(HttpMethod.GET,"/user").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/doctor").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/changeRole").hasRole("ADMIN")
                //.antMatchers(HttpMethod.POST,"/setDoctorToUser/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/setDoctorToUser").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/changeRoleToDoctor").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/user/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/doctors").hasAnyRole("ADMIN","DOCTOR")
                .antMatchers(HttpMethod.GET,"/doctor/{id}/patient").permitAll()
                .antMatchers(HttpMethod.POST,"/vaccine/people/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/register").permitAll()
                .antMatchers(HttpMethod.POST,"/register").permitAll()
                .antMatchers(HttpMethod.POST,"/comment/people/{id}").hasRole("DOCTOR")
                .antMatchers(HttpMethod.POST,"/uploadFile").permitAll()

                .anyRequest()
                .authenticated();
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
        log.info("security filter chain set");
        return http.build();
    }



//    @Bean
//    ServerHttpSecurity serverHttpSecurity() {
//
//        return ServerHttpSecurity.http();
//    }



    @SneakyThrows
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)  {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }



    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        DefaultHttpFirewall firewall = new DefaultHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}
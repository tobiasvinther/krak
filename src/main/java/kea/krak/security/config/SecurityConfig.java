package kea.krak.security.config;

import kea.krak.repositories.PersonRepository;
import kea.krak.security.UserWithPassword;
import kea.krak.security.UserDetailsServiceImp;
import kea.krak.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;


@EnableWebSecurity(debug = false)
//jsr250Enabled = true enables @RolesAllowed annotation.
@EnableGlobalMethodSecurity(
        //securedEnabled = true,
        jsr250Enabled = true
        //prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig( PersonRepository personRepository) {
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return UserWithPassword.getPasswordEncoder();
    }

    @Autowired
    private UserDetailsServiceImp userDetailsService;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;
//    @Bean
//    public JwtTokenFilter authenticationJwtTokenFilter() {
//        return new JwtTokenFilter();
//    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http.cors().and().csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                )
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/persons/{email}").permitAll()
                .antMatchers(HttpMethod.PATCH, "/api/persons/{email}/add-hobby").permitAll()
                .antMatchers(HttpMethod.GET, "/api/persons").permitAll()
                .antMatchers(HttpMethod.GET, "/api/persons/{username}").permitAll()
                .antMatchers(HttpMethod.GET, "/api/hobby").permitAll()
                // All other endpoints are private
                //.anyRequest().authenticated();
                .anyRequest().permitAll();  //Disable Security
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}

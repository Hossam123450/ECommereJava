package ma.emsi.javaproject.auth;

import ma.emsi.javaproject.services.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder)
            throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(("/login**"), ("/logout**"), ("/register")).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.loginPage("/login"))
                .httpBasic(withDefaults())
                .build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                );
//        return http.build();
//    }
//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    return http
//            .authorizeHttpRequests((authz) -> authz
//                            .requestMatchers(new AntPathRequestMatcher("/login**"), new AntPathRequestMatcher("/logout**"), new AntPathRequestMatcher("/register")).permitAll()
////                      .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN")
//                            .anyRequest().authenticated()
//            )
//            .formLogin(form -> form.loginPage("/login")) // Configuration de la page de connexion
////                .formLogin(withDefaults()) // Utilisation de la configuration par défaut pour la page de connexion
//            .httpBasic(withDefaults()) // Configuration HTTP Basic avec les options par défaut
//            .build();
//}

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

}


//@Configuration
//@EnableWebSecurity
//public class SecurityConfig
//{
//    private final CustomUserDetailsService userDetailsService;
//    private final JwtAuthorizationFilter jwtAuthorizationFilter;
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtAuthorizationFilter jwtAuthorizationFilter)
//    {
//        this.userDetailsService = customUserDetailsService;
//        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, NoOpPasswordEncoder noOpPasswordEncoder) throws Exception
//    {
//        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(noOpPasswordEncoder);
//        return authenticationManagerBuilder.build();
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
//    {        http.csrf(csrf -> csrf.disable())
//            .authorizeRequests()
//            .requestMatchers("/rest/auth/**")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);;
//        return http.build();
//    }
//    @Bean
//    public NoOpPasswordEncoder passwordEncoder()
//    {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
//}



//http
//        .authorizeHttpRequests((authz) -> authz
//        .antMatchers("/public/**").permitAll()
//                .anyRequest().authenticated()
//            )
//                    .formLogin((form) -> form
//        .loginPage("/login")
//                .permitAll()
//            )
//                    .logout((logout) -> logout
//        .permitAll()
//            );


//@Bean
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    return http
//            .authorizeHttpRequests((auth)->auth.requestMatchers("/login**","/logout**", "/register").permitAll()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .anyRequest().authenticated())
//            .formLogin((form)->form.loginPage("/login"))
//            .formLogin(withDefaults())
//            .httpBasic(withDefaults()).build();
//}
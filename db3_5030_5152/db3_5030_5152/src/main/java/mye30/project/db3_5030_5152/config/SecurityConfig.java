package mye30.project.db3_5030_5152.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow everyone to see your homeMenu and charts
                )
                .csrf(csrf -> csrf.disable()) // Disable CSRF tokens for easier local testing
                .formLogin(form -> form.disable()); // Turn off the automatic redirect to the sign-in page

        return http.build();
    }
}
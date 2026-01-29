package vendredi.soir.posu.endpoint.rest.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vendredi.soir.posu.repository.UserRepository;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
  private final UserRepository userRepository;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            auth ->
                auth.requestMatchers(HttpMethod.POST, "/login", "/register")
                    .permitAll()
                    .requestMatchers(HttpMethod.GET, "/ping", "/health/email")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .addFilterBefore(
            new ApiKeyAuthenticationFilter(userRepository),
            UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}

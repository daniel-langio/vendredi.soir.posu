package vendredi.soir.posu.endpoint.rest.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import vendredi.soir.posu.repository.UserRepository;

@AllArgsConstructor
public class ApiKeyAuthenticationFilter extends OncePerRequestFilter {
  private static final String API_KEY_HEADER = "x-api-key";
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String apiKey = request.getHeader(API_KEY_HEADER);
    if (apiKey != null) {
      userRepository
          .findByApiKey(apiKey)
          .ifPresent(
              user -> {
                UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
              });
    }
    filterChain.doFilter(request, response);
  }
}

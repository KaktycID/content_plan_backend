package content.auth;

import content.users.repository.UsersRepository;
import content.users.service.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
@Component
public class JwtRequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserServiceImpl userService;
    private final UsersRepository repository;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        final String authorizationHeader = httpRequest.getHeader("Authorization");
        // Проверка, является ли запрос к /auth
        String requestURI = httpRequest.getRequestURI();
        if (requestURI.equals("/auth")) {
            String jwtToken = userService.auth(authorizationHeader); // Получаем JWT-токен

            // Устанавливаем статус ответа и заголовки
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");

            // Формируем JSON-ответ с токеном
            PrintWriter out = httpResponse.getWriter();
            out.print("{\"token\": \"" + jwtToken + "\"}");
            out.flush();
            return;

        }

        logger.info("Processing request: {}", httpRequest.getRequestURI());


        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtService.extractUsername(jwt); // Предполагается, что метод extractUsername возвращает email
            logger.info("Extracted email: {}", email);
        }

        if (email != null) {

            // Проверяем, если аутентификация еще не установлена
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                    // Проверяем валидность токена
                    if (jwtService.validateToken(jwt, userDetails)) {
                        if (jwtService.validateToken(jwt, userDetails)) {
                            // Устанавливаем аутентификацию в SecurityContextHolder
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                        logger.info("User {} authenticated successfully", email);
                    }
                } catch (Exception e) {
                    logger.error("Authentication failed for user {}: {}", email, e.getMessage());
                }
            }
        }

        try {
            chain.doFilter(request, response); // Продолжаем цепочку фильтров
        } catch (Exception e) {
            logger.error("Error during filter chain: {}", e.getMessage());
            throw e; // Пробрасываем исключение дальше
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Инициализация фильтра, если необходимо
    }

    @Override
    public void destroy() {
        // Освобождение ресурсов, если необходимо
    }
}

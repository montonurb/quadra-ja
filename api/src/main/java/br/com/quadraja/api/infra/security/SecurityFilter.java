package br.com.quadraja.api.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.quadraja.api.models.User;
import br.com.quadraja.api.services.TokenService;
import br.com.quadraja.api.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recoverTokenJWT(request);

        if (token != null) {
            String subject = tokenService.getSubject(token);
            User user = userService.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        
        filterChain.doFilter(request, response);
    }

    private String recoverTokenJWT(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");

        if (authorization != null) {
            return authorization.replace("Bearer ", "");
        }

        return null;
    }
}

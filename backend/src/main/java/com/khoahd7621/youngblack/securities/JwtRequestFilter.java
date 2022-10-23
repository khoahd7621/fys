package com.khoahd7621.youngblack.securities;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.models.TokenPayload;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/v1/login")
                || (request.getRequestURI().equals("/api/v1/user") && request.getMethod().equals(HttpMethod.POST.toString()))) {
            filterChain.doFilter(request, response);
        } else {
            final String requestTokenHeader = request.getHeader("Authorization");
            String accessToken = null;
            TokenPayload tokenPayload = null;
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                accessToken = requestTokenHeader.substring(7).trim();
                try {
                    tokenPayload = jwtTokenUtil.getTokenPayload(accessToken);
                } catch (SignatureException se) {
                    System.out.println("Invalid JWT signature");
                } catch (IllegalArgumentException iae) {
                    System.out.println("Unable to get JWT");
                } catch (ExpiredJwtException eje) {
                    System.out.println("Token has expired");
                }
            } else {
                System.out.println("JWT Access Token does not start with 'Bearer ");
            }
            if (tokenPayload != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                Optional<User> userOptional = userRepository.findById(tokenPayload.getId());
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    if (jwtTokenUtil.validateToken(accessToken, user)) {
                        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        UserDetails userDetails =
                                new org.springframework.security.core.userdetails.User(
                                        user.getEmail(), user.getPassword(), authorities);
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}

package com.khoahd7621.youngblack.securities;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.repositories.UserRepository;
import com.khoahd7621.youngblack.utils.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    private Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

    private void setSecurityContext(User user) {
        UserDetails userDetails = new CustomUserDetails(user);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/api/v1/login")
                || (request.getRequestURI().equals("/api/v1/user") && request.getMethod().equals(HttpMethod.POST.toString()))) {
            filterChain.doFilter(request, response);
        } else {
            final Optional<String> requestTokenHeaderOpt = getJwtFromRequest(request);
            if (requestTokenHeaderOpt.isPresent()) {
                try {
                    String accessToken = requestTokenHeaderOpt.get();
                    Optional<User> userOptional = userRepository.findById(jwtTokenUtil.getUserIdFromToken(accessToken));
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        if (jwtTokenUtil.validateToken(accessToken, user)) {
                            setSecurityContext(user);
                        }
                    }
                } catch (SignatureException se) {
                    System.out.println("Here");
                    System.out.println("Invalid JWT signature");
                } catch (IllegalArgumentException iae) {
                    System.out.println("Unable to get JWT");
                } catch (ExpiredJwtException eje) {
                    System.out.println("Token has expired");
                }
            } else {
                System.out.println("JWT Access Token does not start with 'Bearer ");
            }
            filterChain.doFilter(request, response);
        }
    }
}

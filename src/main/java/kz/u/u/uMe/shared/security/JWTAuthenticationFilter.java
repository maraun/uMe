package kz.u.u.uMe.shared.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kz.u.u.uMe.models.entities.Role;
import kz.u.u.uMe.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            kz.u.u.uMe.models.entities.User creds = new ObjectMapper()
                    .readValue(req.getInputStream(), kz.u.u.uMe.models.entities.User.class);
            kz.u.u.uMe.models.entities.User user = userService.findByLogin(creds.getLogin());
            Set authorities = new HashSet<>();
            try {
                for (Role role : user.getRoles()){
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
                return authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                creds.getLogin(),
                                creds.getPassword(),
                                authorities)/*TODO*/
                );
            } catch (Exception e){
                throw new UsernameNotFoundException("Invalid username or password.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        claims.put("scopes", auth.getAuthorities().iterator().next());
        String token = Jwts.builder()
                .setSubject(((User) auth.getPrincipal()).getUsername())
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET)
                .compact();
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);

        String responseToClient = token;
        res.setStatus(HttpServletResponse.SC_OK);
        res.setHeader(HttpHeaders.CONTENT_TYPE, "text");
        res.getWriter().print(responseToClient);
        res.getWriter().flush();
        res.getWriter().close();
    }
}
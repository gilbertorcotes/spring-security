package mx.darthill.api.springsecurity.config.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.darthill.api.springsecurity.exception.ObjectNotFoundException;
import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;
import mx.darthill.api.springsecurity.service.UserService;
import mx.darthill.api.springsecurity.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Entro en el authentication section filter");

        //Encabezado Http Authorization (Barer)
        String authorizationHeader = request.getHeader("Authorization");
        if(!StringUtils.hasText(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        //Obtener el Token desde el encabezado
        String jwt = authorizationHeader.split(" ")[1];

        //Obtener el subject/username desde el Token

        String username = jwtService.extractusername(jwt);

        //Setear objeto Authentication (usuario logueado) dentro de securityContextHolder

        Usuario usuario = userService.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("User Not Found : " + username ));
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            username, null, usuario.getAuthorities()
        );
        authToken.setDetails(new WebAuthenticationDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        System.out.println("Se acaba de setear el authentication");

        //Ejecutar el resto de los filtros

        filterChain.doFilter(request, response);
    }
}

package mx.darthill.api.springsecurity.config.security;

import mx.darthill.api.springsecurity.config.security.filter.JwtAuthenticationFilter;
import mx.darthill.api.springsecurity.persistence.util.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.web.bind.annotation.PostMapping;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .csrf( csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(authReqConfig -> {
//                    buildRequestMatchersV2(authReqConfig); //Se desvincula por completo el matchers y los permisos se manejan de manera personalizada
//                })
                .build();

        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
    /*
    Autorización de Endpoints de Veterinarios basado en coincidencias HTTP
     */
        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_VETERINARIOS.name());

//        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios/{veterinariosId}") //Se cambia por una expresión regular
        authReqConfig.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "/veterinarios/[0-9]*"))
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios")
                .hasRole(Rol.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/veterinarios")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.CREATE_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/veterinarios/{veterinariosId}")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.UPDATE_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/veterinarios/{veterinariosId}/disable")
                .hasRole(Rol.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_VETERINARIOS.name());

                    /*
                    Autorización de Endpoints de Especialidad basado en coincidencias HTTP
                     */
        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad/{especialidadId}")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad")
                .hasRole(Rol.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/especialidad")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.CREATE_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/especialidad/{especialidadId}")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name());
//                .hasAuthority(RolPermission.UPDATE_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/especialidad/{especialidadId}/disable")
                .hasRole(Rol.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_ESPECIALIDAD.name());




        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/profile")
                .hasAnyRole(Rol.ADMINISTRATOR.name(), Rol.OPERATOR.name(), Rol.CUSTOMER.name());
//                .hasAuthority(RolPermission.READ_PROFILE.name());

                    /*
                    Autorizaciópn endpoints públicos
                     */
        authReqConfig.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate").permitAll();
        authReqConfig.anyRequest().authenticated();
    }

    private static void buildRequestMatchersV2(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

        /*
        Autorización de endpoints públicos
         */
        authReqConfig.requestMatchers(HttpMethod.POST, "/usuarios").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST, "/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/validate").permitAll();
        authReqConfig.anyRequest().authenticated();

    }
}

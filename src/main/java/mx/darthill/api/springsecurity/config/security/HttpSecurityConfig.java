package mx.darthill.api.springsecurity.config.security;

import mx.darthill.api.springsecurity.config.security.filter.JwtAuthenticationFilter;
import mx.darthill.api.springsecurity.persistence.util.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity(prePostEnabled = true)
/*
Cuando la seguridad esta absada en métodos esta anotación debe estar habilitada
y la sección authorizeHttpRequests de SecurityFilterChain habilitada
 */
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationEntryPoint AuthenticationEntryPoint;
    /*
    Solo se utiliza cuando esta la versión 1 del filtro en donde se realizan las validaciones por coincidencia de rutas http
     */

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        SecurityFilterChain filterChain = http
                .csrf( csrfConfig -> csrfConfig.disable())
                .sessionManagement(sessMagConfig -> sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig -> {
                    authReqConfig.anyRequest().access(authorizationManager);
//                    buildRequestMatchers(authReqConfig); //Se desvincula por completo el matchers y los permisos se manejan de manera personalizada
                })
                /*
                Cuando esta deshabilkitadoa la anotación de seguridad basada en métodos
                authorizeHttpRequests debe estar habilitado
                V2 solo tiene configurados los endpoints publicos
                V1 maneja la autorización por roles y coincidencia de peticiones http
                 */
                .exceptionHandling( exceptionConfig -> {
                    exceptionConfig.authenticationEntryPoint(AuthenticationEntryPoint);
                    exceptionConfig.accessDeniedHandler(accessDeniedHandler);
                })
                /*
                Solo se utiliza cuando esta la versión 1 del filtro en donde se realizan las validaciones por coincidencia de rutas http
                y con la anotación del  @Autowired private AuthenticationEntryPoint AuthenticationEntryPoint;
                 */
                .build();

        return filterChain;
    }

    private static void buildRequestMatchers(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
    /*
    Autorización de Endpoints de Veterinarios basado en coincidencias HTTP
     */
        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_VETERINARIOS.name());

//        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios/{veterinariosId}") //Se cambia por una expresión regular
        authReqConfig.requestMatchers(RegexRequestMatcher.regexMatcher(HttpMethod.GET, "/veterinarios/[0-9]*"))
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/veterinarios")
                .hasRole(RolEnum.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/veterinarios")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.CREATE_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/veterinarios/{veterinariosId}")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.UPDATE_ONE_VETERINARIOS.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/veterinarios/{veterinariosId}/disable")
                .hasRole(RolEnum.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_VETERINARIOS.name());

                    /*
                    Autorización de Endpoints de Especialidad basado en coincidencias HTTP
                     */
        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad/{especialidadId}")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.GET, "/especialidad")
                .hasRole(RolEnum.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ALL_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.POST, "/especialidad")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.CREATE_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/especialidad/{especialidadId}")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name());
//                .hasAuthority(RolPermission.UPDATE_ONE_ESPECIALIDAD.name());

        authReqConfig.requestMatchers(HttpMethod.PUT, "/especialidad/{especialidadId}/disable")
                .hasRole(RolEnum.ADMINISTRATOR.name());
//                .hasAuthority(RolPermission.READ_ONE_ESPECIALIDAD.name());




        authReqConfig.requestMatchers(HttpMethod.GET, "/auth/profile")
                .hasAnyRole(RolEnum.ADMINISTRATOR.name(), RolEnum.OPERATOR.name(), RolEnum.CUSTOMER.name());
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

package mx.darthill.api.springsecurity.config.security.authorization;

import jakarta.servlet.http.HttpServletRequest;
import mx.darthill.api.springsecurity.exception.ObjectNotFoundException;
import mx.darthill.api.springsecurity.persistence.entity.security.Operation;
import mx.darthill.api.springsecurity.persistence.entity.security.Usuario;
import mx.darthill.api.springsecurity.persistence.repository.security.OperationRepository;
import mx.darthill.api.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;

import javax.security.auth.login.CredentialNotFoundException;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class CustomAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private UserService userService;

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext RequestContext) {

        HttpServletRequest request = RequestContext.getRequest();
        System.out.println(request.getRequestURL());
        System.out.println(request.getRequestURI());

        String url = extractUrl(request);
        String httpMethod = request.getMethod();

        boolean isPublic = isPublic(url, httpMethod);

        if(isPublic){
            return new AuthorizationDecision(true);
        }
        boolean isGranted = false;
        try {  //Ojo con este Try debe haber manera de no usarlo
            isGranted = isGranted(url, httpMethod, authentication.get());
        } catch (CredentialNotFoundException e) {
            throw new RuntimeException(e);
        }

        return new AuthorizationDecision(isGranted);
    }

    private boolean isGranted(String url, String httpMethod, Authentication authentication) throws CredentialNotFoundException {

        if(authentication == null || !(authentication instanceof UsernamePasswordAuthenticationToken)){
            throw new CredentialNotFoundException("Usuario no a accedido");
        }
        List<Operation> operations =  obtainOperations(authentication);

        boolean isGranted = operations.stream().anyMatch(getOperationPredicate(url, httpMethod));

        System.out.println("Es Autorizada.... : " + isGranted);

        return isGranted;

    }

    private static Predicate<Operation> getOperationPredicate(String url, String httpMethod) {
        return operation -> {

            String basePath = operation.getModule().getBasePath();
            Pattern pattern = Pattern.compile(basePath.concat(operation.getPath()));
            Matcher matcher = pattern.matcher(url);
            return matcher.matches() && operation.getHttpMethod().equals(httpMethod);
        };
    }

    private List<Operation> obtainOperations(Authentication authentication) {

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = (String) authToken.getPrincipal();
        Usuario usuario = userService.findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("Usuario no encontrado : " + username));
        return usuario.getRole().getPermissions().stream()
                .map(grantedPermission -> grantedPermission.getOperation())
                .collect(Collectors.toList());

    }

    private boolean isPublic(String url, String httpMethod) {

        List<Operation> publicAccesEndPoints = operationRepository
                .findByPublicAccess();

        boolean isPublic = publicAccesEndPoints.stream().anyMatch(getOperationPredicate(url, httpMethod));

        System.out.println("Es publica.... : " + isPublic);

        return isPublic;

    }

    private String extractUrl(HttpServletRequest request) {

        String contextPath = request.getContextPath();
        // /api/v1
        String url = request.getRequestURI();
        url = url.replace(contextPath, "");
        System.out.println("proceso extractUrl.... : " + url);

        return url;
    }
}

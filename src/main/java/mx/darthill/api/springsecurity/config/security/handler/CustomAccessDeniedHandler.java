package mx.darthill.api.springsecurity.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mx.darthill.api.springsecurity.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(accessDeniedException.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Acceso denegado, No tiene los permisos necesarios para acceder a esta función");

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());

        // Crea una instancia de ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        // Registra el módulo JavaTimeModule para manejar los tipos de datos de tiempo
        objectMapper.registerModule(new JavaTimeModule());
        // Convierte el objeto `apiError` a una cadena JSON
        String apiErrorJson = objectMapper.writeValueAsString(apiError);
        response.getWriter().write(apiErrorJson);

    }
}

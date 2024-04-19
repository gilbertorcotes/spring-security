package mx.darthill.api.springsecurity.exeption;

import jakarta.servlet.http.HttpServletRequest;
import mx.darthill.api.springsecurity.dto.ApiError;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request){

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Mensaje de error personalizado");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiError);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerArgumentNotValidException(MethodArgumentNotValidException exception,
                                                              HttpServletRequest request){

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Error en la petición enviada");

        System.out.println(exception.getAllErrors().stream().map(each -> each.getDefaultMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(AccessDeniedException exception, HttpServletRequest request){

        ApiError apiError = new ApiError();
        apiError.setBackendMessage(exception.getLocalizedMessage());
        apiError.setUrl(request.getRequestURL().toString());
        apiError.setMessage(request.getMethod());
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setMessage("Acceso denegado, No tiene los permisos necesarios para acceder a esta función");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiError);

    }


}

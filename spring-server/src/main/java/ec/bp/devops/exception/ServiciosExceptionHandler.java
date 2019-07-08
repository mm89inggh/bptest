package ec.bp.devops.exception;

import ec.bp.devops.model.ModelApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ServiciosExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServiciosExceptionHandler.class);

    public ServiciosExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        ModelApiResponse error = new ModelApiResponse().message("ERROR: " + errorMessage);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorMessage = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        ModelApiResponse error = new ModelApiResponse().message("ERROR: " + errorMessage);
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleServiciosError(final Exception ex, final WebRequest request) {
        String errorMessage = ex.getMessage() == null ? ex.toString() : ex.getMessage();
        ModelApiResponse error = new ModelApiResponse();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

}

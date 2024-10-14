package exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalDNAException {

    @ExceptionHandler(NullDnaException.class)
    public ResponseEntity<?> handleNullDnaException(NullDnaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}

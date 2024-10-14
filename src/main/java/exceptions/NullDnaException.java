package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class NullDnaException extends RuntimeException{
    public NullDnaException(String message) {
        super(message);
    }
}

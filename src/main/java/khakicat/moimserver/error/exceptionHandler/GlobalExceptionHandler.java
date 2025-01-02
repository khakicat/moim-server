package khakicat.moimserver.error.exceptionHandler;

import khakicat.moimserver.error.response.ErrorResponse;
import khakicat.moimserver.error.exception.MultipleErrorsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipleErrorsException.class)
    public ResponseEntity<ErrorResponse> handleMultipleErrorsException(MultipleErrorsException ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }
}

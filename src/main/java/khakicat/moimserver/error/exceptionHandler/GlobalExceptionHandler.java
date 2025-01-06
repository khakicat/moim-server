package khakicat.moimserver.error.exceptionHandler;

import khakicat.moimserver.error.response.ErrorResponse;
import khakicat.moimserver.error.response.ErrorResponses;
import khakicat.moimserver.error.exception.MultipleErrorsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipleErrorsException.class)
    public ResponseEntity<ErrorResponses> handleMultipleErrorsException(MultipleErrorsException ex) {
        return new ResponseEntity<>(new ErrorResponses(ex.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }
}

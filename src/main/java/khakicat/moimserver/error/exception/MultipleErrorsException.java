package khakicat.moimserver.error.exception;

import java.util.List;
import lombok.Getter;

@Getter
public class MultipleErrorsException extends RuntimeException{
    private List<String> errorMessages;

    public MultipleErrorsException(List<String> errorMessages) {
        super("여러 오류가 발생했습니다.");
        this.errorMessages = errorMessages;
    }
}

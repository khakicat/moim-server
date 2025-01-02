package khakicat.moimserver.signUp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PasswordPolicyCheck {

    // 비밀번호는 최소 8자리 이상이고 영문, 숫자, 특수 기호를 조합하여야한다.

    private final int passwordMinimumLength = 8;
    private Pattern specialChar = Pattern.compile("[^a-zA-Z0-9가-힣ㄱ-ㅎㅏ-ㅣ]");
    private Pattern number = Pattern.compile("[0-9]");
    private Pattern hanGueal = Pattern.compile("[^가-힣ㄱ-ㅎㅏ-ㅣ]");
    private Pattern alphabet = Pattern.compile("[a-zA-Z]");




    public String checkPasswordPolicy(String password) {
        List<String> errorMessages = new ArrayList<>();

        if (password.length() < passwordMinimumLength
                || !specialChar.matcher(password).matches()
                || !number.matcher(password).matches()
                || !hanGueal.matcher(password).matches()
                || !alphabet.matcher(password).matches())
            return "비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요.";
        return "";
    };

}

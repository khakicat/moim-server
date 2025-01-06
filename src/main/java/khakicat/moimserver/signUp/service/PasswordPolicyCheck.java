package khakicat.moimserver.signUp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

@Service
public class PasswordPolicyCheck {

    // 비밀번호는 최소 8자리 이상이고 영문, 숫자, 특수 기호를 조합하여야한다.

    private final int passwordMinimumLength = 8;
    private Pattern specialChar = Pattern.compile(".*[^a-zA-Z0-9].*");
    private Pattern number = Pattern.compile(".*[0-9].*");
    private Pattern hanGueal = Pattern.compile(".*[^가-힣ㄱ-ㅎㅏ-ㅣ].*");
    private Pattern capitalLetter = Pattern.compile(".*[A-Z].*");
    private Pattern smallLetter = Pattern.compile(".*[a-z].*");

    public String checkPasswordPolicy(String password) {
        if (password.length() >= passwordMinimumLength
        && specialChar.matcher(password).matches()
        && number.matcher(password).matches()
        && hanGueal.matcher(password).matches()
        && capitalLetter.matcher(password).matches()
        && smallLetter.matcher(password).matches())
            return "";
        return "비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요.";
    };
}

package khakicat.moimserver.signUp.service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PasswordPolicyCheckTest {

    @Autowired
    private PasswordPolicyCheck passwordPolicyCheck;

    @Test
    void passwordPolicyCheck_success() {
        String password = "TESTtest123!@#$";

        String result = passwordPolicyCheck.checkPasswordPolicy(password);

        Assertions.assertThat(result).isEqualTo("");
    }



    @Test
    @DisplayName("8자 미만 비밀번호 입력 케이스")
    void setPasswordPolicyCheck_fail1() {
        String password = "Tt1!";

        String result = passwordPolicyCheck.checkPasswordPolicy(password);

        Assertions.assertThat(result).isEqualTo("비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요.");
    }

    @Test
    @DisplayName("영문 소문자만 입력한 케이스")
    void setPasswordPolicyCheck_fail2() {
        String password = "tttttttt";

        String result = passwordPolicyCheck.checkPasswordPolicy(password);

        Assertions.assertThat(result).isEqualTo("비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요.");
    }

    @Test
    @DisplayName("특수문자가 포함되지 않은 케이스")
    void setPasswordPolicyCheck_fail3(){
        String password = "Testtest1234";

        String result = passwordPolicyCheck.checkPasswordPolicy(password);

        Assertions.assertThat(result).isEqualTo("비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요.");
    }
}
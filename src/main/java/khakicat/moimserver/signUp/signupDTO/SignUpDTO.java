package khakicat.moimserver.signUp.signupDTO;

import khakicat.moimserver.member.dto.MemberDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignUpDTO extends MemberDTO {
    private String email;
    private String nickname;
    private String password;
}

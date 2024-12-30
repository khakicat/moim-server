package khakicat.moimserver.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberDTO {
    private String email;
    private String nickname;
    private String password;
}

package khakicat.moimserver.signUp.service;

import java.util.ArrayList;
import java.util.List;
import khakicat.moimserver.error.exception.MultipleErrorsException;
import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.service.MemberService;
import khakicat.moimserver.signUp.signupDTO.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpService {
    private final MemberService memberService;
    private final PasswordPolicyCheck passwordPolicyCheck;

    public Member signUp(SignUpDTO signUpDTO) {
        List<String> errorMessages = new ArrayList<>();
        if (memberService.isEmailAlreadyExist(signUpDTO.getEmail()))
            errorMessages.add("이미 존재하는 이메일 입니다.");

        String checkResult = passwordPolicyCheck.checkPasswordPolicy(signUpDTO.getPassword());
        if (!checkResult.isEmpty())
            errorMessages.add(checkResult);

        if (!errorMessages.isEmpty())
            throw new MultipleErrorsException(errorMessages);

        return memberService.addMember(signUpDTO);
    }
}

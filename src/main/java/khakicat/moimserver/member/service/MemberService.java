package khakicat.moimserver.member.service;

import khakicat.moimserver.member.dto.MemberDTO;
import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final IdentifierService identifierService;

    public Member addMember(MemberDTO memberDTO) {
        return memberRepository.save(new Member(
                memberDTO.getEmail(),
                memberDTO.getNickname(),
                identifierService.getUniqueIdentifier(),
                memberDTO.getPassword()
        ));
    }

    public boolean isEmailUnique(String email) {
        return memberRepository.findByEmail(email).isEmpty();
    }
}

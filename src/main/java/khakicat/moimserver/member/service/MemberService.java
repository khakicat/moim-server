package khakicat.moimserver.member.service;

import khakicat.moimserver.member.dto.MemberDTO;
import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final IdentifierService identifierService;
    private final PasswordEncoder passwordEncoder;

    public Member addMember(MemberDTO memberDTO) {
        return memberRepository.save(new Member(
                memberDTO.getEmail(),
                memberDTO.getNickname(),
                identifierService.getUniqueIdentifier(),
                passwordEncoder.encode(memberDTO.getPassword()))
        );
    }

    public boolean isEmailAlreadyExist(String email) {
        return !memberRepository.findByEmail(email).isEmpty();
    }

    public Member findMemberByEmail(String email) throws UsernameNotFoundException {
        return memberRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    public Member findMemberByIdentifier(String identifier) throws UsernameNotFoundException {
        return memberRepository
                .findByIdentifier(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}

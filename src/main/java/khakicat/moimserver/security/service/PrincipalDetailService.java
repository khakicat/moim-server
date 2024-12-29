package khakicat.moimserver.security.service;

import khakicat.moimserver.member.Member;
import khakicat.moimserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")) {
            return userBuilder(findMemberByEmail(username));
        }
        return userBuilder(findMemberByIdentifier(username));
    }

    public Member findMemberByEmail(String email) throws UsernameNotFoundException {
        return memberRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    public Member findMemberByIdentifier(String identifier) throws UsernameNotFoundException {
        return memberRepository
                .findByIdentifier(identifier)
                .orElseThrow(() -> new UsernameNotFoundException("유저를 찾을 수 없습니다."));
    }

    public UserDetails userBuilder(Member member) {
        return User.builder()
                .username(member.getIdentifier())
                .password(member.getPassword())
                .build();
    }
}

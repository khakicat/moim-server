package khakicat.moimserver.security.service;

import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final MemberService memberService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.contains("@")) {
            return userBuilder(memberService.findMemberByEmail(username));
        }
        return userBuilder(memberService.findMemberByIdentifier(username));
    }

    public UserDetails userBuilder(Member member) {
        return User.builder()
                .username(member.getIdentifier())
                .password(member.getPassword())
                .build();
    }
}

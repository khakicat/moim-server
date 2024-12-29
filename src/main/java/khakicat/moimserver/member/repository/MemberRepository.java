package khakicat.moimserver.member.repository;

import java.util.Optional;
import khakicat.moimserver.member.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
    Optional<Member> findByIdentifier(String identifier);
}

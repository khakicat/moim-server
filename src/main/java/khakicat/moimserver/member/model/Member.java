package khakicat.moimserver.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String identifier;

    @Column
    private String password;

    public Member(String email, String nickname, String identifier, String password) {
        this.email = email;
        this.nickname = nickname;
        this.identifier = identifier;
        this.password = password;
    }
}

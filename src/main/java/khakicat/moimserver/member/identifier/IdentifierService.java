package khakicat.moimserver.member.identifier;

import java.util.Random;
import java.util.stream.IntStream;
import khakicat.moimserver.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdentifierService {

    private final MemberRepository memberRepository;
    private final String stringPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final int length = 8;

    public String getUniqueIdentifier() {
        Random random = new Random();
        StringBuilder identifier = new StringBuilder();
        do {
            identifier.delete(0, identifier.length());
            IntStream.range(0, length)
                    .forEach(num -> identifier.append(stringPool.charAt(num)));
        } while (memberRepository.findByIdentifier(identifier.toString()).isPresent());
        return identifier.toString();
    }
}

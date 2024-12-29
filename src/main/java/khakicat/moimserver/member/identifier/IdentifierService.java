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
        String identifier = "";
        do {
            identifier = generateRandomString();
        } while (memberRepository.findByIdentifier(identifier).isPresent());
        return identifier;
    }

    public String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        IntStream.range(0, length)
                .forEach(num -> sb.append(stringPool.charAt(num)));
        return sb.toString();
    }
}

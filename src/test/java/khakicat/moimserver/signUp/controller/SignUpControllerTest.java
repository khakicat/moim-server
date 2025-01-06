package khakicat.moimserver.signUp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입 성공 케이스")
    void testSignUpUser_success() throws Exception {
        String requestBody = """
                {
                    "email": "test@gmail.com",
                    "nickname": "test",
                    "password": "TESTtest123!@#$"
                }
                """;

        mockMvc.perform(post("/api/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated());

        Optional<Member> newMember = memberRepository.findByEmail("test@gmail.com");
        Assertions.assertThat(newMember).isNotNull();
    }

    @Test
    @DisplayName("회원가입 실패 - 중복 이메일 사용")
    void testSignUpUser_fail1() throws Exception {
        String requestBody = """
                {
                    "email": "test@gmail.com",
                    "nickname": "test",
                    "password": "TESTtest123!@#$"
                }
                """;

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody));

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("이미 존재하는 이메일 입니다."));
    }

    @Test
    @DisplayName("회원가입 실패 - 비밀번호 정책 미충족")
    void testSignUpUser_fail2() throws Exception {
        String requestBody = """
                {
                    "email": "test@gmail.com",
                    "nickname": "test",
                    "password": "1234"
                }
                """;

        mockMvc.perform(post("/api/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorMessage").value("비밀번호는 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해주세요."));
    }
}
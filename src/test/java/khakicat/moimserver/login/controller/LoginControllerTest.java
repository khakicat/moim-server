package khakicat.moimserver.login.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import khakicat.moimserver.member.model.Member;
import khakicat.moimserver.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member(
                "test@gmail.com",
                "test",
                "111111",
                new BCryptPasswordEncoder().encode("TESTtest123!@#$")
        );

        memberRepository.save(member);
    }

    @Test
    @DisplayName("이메일 로그인 테스트")
    void login_withValidEmailAndPassword_returnSessionId() throws Exception {

        String requestBody = """
                {
                    "username": "test@gmail.com",
                    "password": "TESTtest123!@#$"
                }
                """;

        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String cookie = response.getHeader("Set-Cookie");

        Assertions.assertThat(cookie).isNotNull();
        Assertions.assertThat(cookie).contains("JSESSIONID");
    }

    @Test
    @DisplayName("identifier 로그인 테스트")
    void login_withValidIdentifierAndPassword_returnSessionId() throws Exception {

        String requestBody = """
                {
                    "username": "111111",
                    "password": "TESTtest123!@#$"
                }
                """;

        MvcResult mvcResult = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String cookie = response.getHeader("Set-Cookie");

        Assertions.assertThat(cookie).isNotNull();
        Assertions.assertThat(cookie).contains("JSESSIONID");
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    void login_withInvalidCredential_return401() throws Exception {

        String requestBody = """
                {
                    "username": "test1234@gmail.com",
                    "password": "TESTtest123!@#$"
                }
                """;

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isUnauthorized());
    }
}
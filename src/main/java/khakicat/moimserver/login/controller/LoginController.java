package khakicat.moimserver.login.controller;

import jakarta.servlet.http.HttpSession;
import khakicat.moimserver.login.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession httpSession) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        httpSession.setAttribute("SPRING_SECURITY_CONTEXT", authentication);

        String sessionId = httpSession.getId();

        return ResponseEntity.ok()
                .header("Set-Cookie", "JSESSIONID=" + sessionId + "; Path=/; HttpOnly; Secure; SameSite=Strict")
                .body("Login successful");
    }
}

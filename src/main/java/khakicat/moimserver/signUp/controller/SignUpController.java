package khakicat.moimserver.signUp.controller;

import khakicat.moimserver.signUp.service.SignUpService;
import khakicat.moimserver.signUp.signupDTO.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/signup")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        signUpService.signUp(signUpDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };
}

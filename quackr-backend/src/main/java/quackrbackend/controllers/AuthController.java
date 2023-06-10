package quackrbackend.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SignUpRequest;
import quackrbackend.payloads.SuccessResponse;
import quackrbackend.services.UserService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<String>> login(@RequestBody SignInRequest signInRequest) throws JOSEException {
        SuccessResponse<String> response = SuccessResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Sign in successfully")
                .data(userService.signIn(signInRequest))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/signup",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<String>> signUp(@RequestBody SignUpRequest signUpRequest) throws JOSEException {
        SuccessResponse<String> response = SuccessResponse.<String>builder()
                .status(HttpStatus.OK.value())
                .message("Sign up successfully")
                .data(userService.signUp(signUpRequest))
                .build();
        return ResponseEntity.ok(response);
    }
}

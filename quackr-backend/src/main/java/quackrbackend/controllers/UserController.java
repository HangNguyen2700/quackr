package quackrbackend.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SignUpRequest;
import quackrbackend.payloads.SuccessResponse;
import quackrbackend.payloads.UserResponse;
import quackrbackend.services.UserService;

@RestController
@RequestMapping(path = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<UserResponse>> login(@RequestBody SignInRequest signInRequest) throws JOSEException {
        SuccessResponse<UserResponse> response = SuccessResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Sign in successfully")
                .data(userService.signIn(signInRequest))
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/auth/signup",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<UserResponse>> signUp(@RequestBody SignUpRequest signUpRequest) throws JOSEException {
        SuccessResponse<UserResponse> response = SuccessResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Sign up successfully")
                .data(userService.signUp(signUpRequest))
                .build();
        return ResponseEntity.ok(response);
    }
}

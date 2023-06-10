package quackrbackend.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SuccessResponse;
import quackrbackend.payloads.UserResponse;
import quackrbackend.services.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/me",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<UserResponse>> getCurrentUser() {
        SuccessResponse<UserResponse> response = SuccessResponse.<UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Get current user successfully")
                .data(userService.getCurrentUser())
                .build();
        return ResponseEntity.ok(response);
    }
}

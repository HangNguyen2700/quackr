package quackrbackend.controllers;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quackrbackend.payloads.PostResponse;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SuccessResponse;
import quackrbackend.payloads.UserResponse;
import quackrbackend.services.PostService;
import quackrbackend.services.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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

    @GetMapping(path = "/me/posts",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<List<PostResponse>>> getCurrentUserPosts() {
        SuccessResponse<List<PostResponse>> response = SuccessResponse.<List<PostResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Get all current user posts successfully")
                .data(postService.getCurrentUserPosts())
                .build();
        return ResponseEntity.ok(response);
    }
}

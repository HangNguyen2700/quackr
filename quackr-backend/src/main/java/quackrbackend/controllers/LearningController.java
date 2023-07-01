package quackrbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quackrbackend.services.PostService;
import quackrbackend.services.UserService;

@RestController
@RequestMapping(path = "/api/reset")
public class LearningController {
    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @DeleteMapping()
    public ResponseEntity<Void> reset(){
        postService.resetAllPosts();
        userService.resetAllUsers();
        return ResponseEntity.noContent().build();
    }

}

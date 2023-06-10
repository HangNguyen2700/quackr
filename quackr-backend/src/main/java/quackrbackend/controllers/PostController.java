package quackrbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;
import quackrbackend.payloads.SuccessResponse;
import quackrbackend.services.PostService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "/newest",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<PostResponse>> getNewestPost() {
        SuccessResponse<PostResponse> response = SuccessResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Get newest post successfully")
                .data(postService.getNewestPost())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<List<PostResponse>>> getAllPosts() {
        SuccessResponse<List<PostResponse>> response = SuccessResponse.<List<PostResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Get all posts successfully")
                .data(postService.getAllPosts())
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable long id) {
        postService.deletePostById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<PostResponse>> createPost(@RequestBody PostRequest postRequest) {
        SuccessResponse<PostResponse> response = SuccessResponse.<PostResponse>builder()
                .status(HttpStatus.CREATED.value())
                .message("Create post successfully")
                .data(postService.createPost(postRequest))
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(path = "/{postId}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<PostResponse>> updatePost(@PathVariable long postId, @RequestBody PostRequest postRequest) {
        SuccessResponse<PostResponse> response = SuccessResponse.<PostResponse>builder()
                .status(HttpStatus.OK.value())
                .message("Update post successfully")
                .data(postService.updatePostById(postId, postRequest))
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/3newest",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SuccessResponse<List<PostResponse>>> get3NewestPosts() {
        SuccessResponse<List<PostResponse>> response = SuccessResponse.<List<PostResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("Get 3 newest posts successfully")
                .data(postService.get3NewestPosts())
                .build();
        return ResponseEntity.ok(response);
    }
}

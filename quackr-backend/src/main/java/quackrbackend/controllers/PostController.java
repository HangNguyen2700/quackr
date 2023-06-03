package quackrbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;
import quackrbackend.services.PostService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(path = "newest",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> getNewestPost() {
        PostResponse response = postService.getNewestPost();
        return ResponseEntity.ok(response);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> response = postService.getAllPosts();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity.HeadersBuilder<?> deletePostById(@PathVariable long id) {
        postService.deleteByPostId(id);
        return ResponseEntity.noContent();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest) {
        PostResponse response = postService.createPost(postRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> createPost(@PathVariable long id, @RequestBody PostRequest postRequest) {
        PostResponse response = postService.updatePost(id, postRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "3newest",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PostResponse>> get3NewestPosts() {
        List<PostResponse> response = postService.get3NewestPosts();
        return ResponseEntity.ok(response);
    }
}

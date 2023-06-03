package quackrbackend.services;

import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getNewestPost();
    List<PostResponse> getAllPosts();
    void deleteByPostId(long postId);
    PostResponse createPost(PostRequest postRequest);
    PostResponse updatePost(long postId, PostRequest postRequest);
}

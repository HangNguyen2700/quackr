package quackrbackend.services;

import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostResponse getNewestPost();
    List<PostResponse> getAllPosts();
    List<PostResponse> getCurrentUserPosts();
    void deletePostById(long postId);
    PostResponse createPost(PostRequest postRequest);
    PostResponse updatePostById(long postId, PostRequest postRequest);
    List<PostResponse> get3NewestPosts();

}

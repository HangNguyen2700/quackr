package quackrbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quackrbackend.entities.DBPost;
import quackrbackend.entities.DBUser;
import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;
import quackrbackend.repositories.PostRepository;
import quackrbackend.repositories.UserRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PostResponse getNewestPost() {
        Sort sort = Sort.by(Sort.Direction.DESC, "publishedOn");
        DBPost post = postRepository.findAll(sort).get(0);
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .publishedOn(post.getPublishedOn())
                .publishedBy(post.getUser().getDisplayName())
                .build();
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<DBPost> posts = postRepository.findAll();
        return posts
                .stream()
                .map((post) -> PostResponse.builder()
                    .id(post.getId())
                    .content(post.getContent())
                    .publishedOn(post.getPublishedOn())
                    .publishedBy(post.getUser().getDisplayName())
                    .build())
                .toList();
    }

    @Override
    public void deleteByPostId(long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        DBUser user = userRepository.findById(postRequest.getUserId()).get();
        DBPost post = DBPost.builder()
                .content(postRequest.getContent())
                .publishedOn(new Date())
                .user(user)
                .build();
        postRepository.save(post);
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .publishedOn(post.getPublishedOn())
                .publishedBy(post.getUser().getDisplayName())
                .build();
    }

    @Override
    public PostResponse updatePost(long postId, PostRequest postRequest) {
        DBUser user = userRepository.findById(postRequest.getUserId()).get();
        DBPost post = DBPost.builder()
                .id(postId)
                .content(postRequest.getContent())
                .publishedOn(new Date())
                .user(user)
                .build();
        postRepository.save(post);
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .publishedOn(post.getPublishedOn())
                .publishedBy(post.getUser().getDisplayName())
                .build();
    }

    @Override
    public List<PostResponse> get3NewestPosts(){
        Sort sort = Sort.by(Sort.Direction.DESC, "publishOn");
        List<DBPost> posts = null;
        for (int i = 0; i < 3; i++) {
           posts.add(postRepository.findAll(sort).get(i));
        }
        return posts.stream().map((post) -> PostResponse.builder()
                        .id(post.getId())
                        .content(post.getContent())
                        .publishedOn(post.getPublishedOn())
                        .publishedBy(post.getUser().getDisplayName())
                        .build())
                .toList();
    }
}

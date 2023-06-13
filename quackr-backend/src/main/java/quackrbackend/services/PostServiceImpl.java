package quackrbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quackrbackend.entities.DBPost;
import quackrbackend.entities.DBUser;
import quackrbackend.entities.Role;
import quackrbackend.exceptions.ForbiddenException;
import quackrbackend.exceptions.NotFoundException;
import quackrbackend.payloads.PostRequest;
import quackrbackend.payloads.PostResponse;
import quackrbackend.repositories.PostRepository;
import quackrbackend.repositories.UserRepository;
import quackrbackend.utils.UserUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        return convertEntityToPayload(post);
    }

    @Override
    public List<PostResponse> getAllPosts() {
        List<DBPost> posts = postRepository.findAll();

        return posts
                .stream()
                .map(this::convertEntityToPayload)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getCurrentUserPosts() {
        DBUser currentUser = UserUtil.getCurrentUser(userRepository);
        List<DBPost> posts = postRepository.findAllByPublishedBy(currentUser);

        return posts
                .stream()
                .map(this::convertEntityToPayload)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePostById(long postId) {
        DBUser currentUser = UserUtil.getCurrentUser(userRepository);

        if (Role.USER == currentUser.getRole() && !isPostBelongsToUser(postId, currentUser)) {
            throw new ForbiddenException("Do not have access to delete post with postId = " + postId);
        }
        postRepository.deleteById(postId);
    }

    private boolean isPostBelongsToUser(long postId, DBUser user) {
        DBPost post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Post not found with postId: " + postId));

        return post.getPublishedBy().getId() == user.getId();
    }

    @Override
    public PostResponse createPost(PostRequest postRequest) {
        DBUser currentUser = UserUtil.getCurrentUser(userRepository);
        DBPost post = DBPost.builder()
                .content(postRequest.getContent())
                .publishedOn(new Date())
                .publishedBy(currentUser)
                .build();
        postRepository.save(post);

        return convertEntityToPayload(post);
    }

    @Override
    public List<PostResponse> get3NewestPosts(){
        Sort sort = Sort.by(Sort.Direction.DESC, "publishedOn");
        Pageable pageable = PageRequest.of(0, 3, sort);
        List<DBPost> posts = postRepository.findAll(pageable).toList();

        return posts
                .stream()
                .map(this::convertEntityToPayload)
                .collect(Collectors.toList());
    }

    @Override
    public PostResponse updatePostById(long postId, PostRequest postRequest) {
        DBUser currentUser = UserUtil.getCurrentUser(userRepository);
        DBPost post = DBPost.builder()
                .id(postId)
                .content(postRequest.getContent())
                .publishedOn(new Date())
                .publishedBy(currentUser)
                .build();
        postRepository.save(post);

        return convertEntityToPayload(post);
    }

    private PostResponse convertEntityToPayload(DBPost post) {
        return PostResponse.builder()
                .id(post.getId())
                .content(post.getContent())
                .publishedOn(post.getPublishedOn())
                .authorUsername(post.getPublishedBy().getUsername())
                .authorName(post.getPublishedBy().getDisplayName())
                .build();
    }
}

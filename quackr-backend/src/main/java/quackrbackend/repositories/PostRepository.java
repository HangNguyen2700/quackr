package quackrbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quackrbackend.entities.DBPost;
import quackrbackend.entities.DBUser;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<DBPost, Long> {
    List<DBPost> findAllByPublishedByOrderByPublishedOnDesc(DBUser publishedBy);
}

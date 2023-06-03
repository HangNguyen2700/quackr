package quackrbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quackrbackend.entities.DBPost;

@Repository
public interface PostRepository extends JpaRepository<DBPost, Long> {
}

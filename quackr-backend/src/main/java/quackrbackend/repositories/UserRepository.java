package quackrbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quackrbackend.entities.DBUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<DBUser, Long> {
    Optional<DBUser> findDBUserByUsername(String username);
}

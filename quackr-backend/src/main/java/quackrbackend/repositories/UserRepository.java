package quackrbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quackrbackend.entities.DBUser;

@Repository
public interface UserRepository extends JpaRepository<DBUser, Long> {
}

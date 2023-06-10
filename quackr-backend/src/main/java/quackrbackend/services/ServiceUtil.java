package quackrbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import quackrbackend.entities.DBUser;
import quackrbackend.exceptions.NotFoundException;
import quackrbackend.repositories.UserRepository;
import quackrbackend.security.jwt.JWTUtil;

public class ServiceUtil {
    public static DBUser getCurrentUser(UserRepository userRepository) {
        String currentUsername = JWTUtil.getCurrentUsernameFromSubject();

        return userRepository.findDBUserByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + currentUsername));
    }
}

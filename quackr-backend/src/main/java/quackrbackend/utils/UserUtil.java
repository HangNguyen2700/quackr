package quackrbackend.utils;

import quackrbackend.entities.DBUser;
import quackrbackend.exceptions.NotFoundException;
import quackrbackend.repositories.UserRepository;

public class UserUtil {
    public static DBUser getCurrentUser(UserRepository userRepository) {
        String currentUsername = JWTUtil.getCurrentUsernameFromSubject();

        return userRepository.findDBUserByUsername(currentUsername)
                .orElseThrow(() -> new NotFoundException("User not found with username: " + currentUsername));
    }
}

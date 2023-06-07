package quackrbackend.services;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import quackrbackend.entities.DBUser;
import quackrbackend.entities.Role;
import quackrbackend.exceptions.DuplicatedException;
import quackrbackend.exceptions.UnauthorizedException;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SignUpRequest;
import quackrbackend.payloads.UserResponse;
import quackrbackend.repositories.UserRepository;
import quackrbackend.security.jwt.JWTLoginData;
import quackrbackend.security.jwt.JWTUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse signIn(SignInRequest signInRequest) throws JOSEException {
        String username = signInRequest.getUsername();
        String password = signInRequest.getPassword();

        DBUser user = userRepository.findDBUserByUsername(username)
                .orElseThrow(() -> new UnauthorizedException("Invalid credentials"));

        if (!user.getPassword().equals(password)) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return convertEntityToPayload(user);
    }

    @Override
    public UserResponse signUp(SignUpRequest signUpRequest) throws JOSEException {
        userRepository.findDBUserByUsername(signUpRequest.getUsername()).ifPresent((user) -> {
            throw new DuplicatedException(String.format("%s already exists!", signUpRequest.getUsername()));
        });

        DBUser user = DBUser.builder()
                .username(signUpRequest.getUsername())
                .password(signUpRequest.getPassword())
                .displayName(signUpRequest.getDisplayName())
                .role(Role.USER)
                .build();
        userRepository.save(user);

        return convertEntityToPayload(user);
    }

    private UserResponse convertEntityToPayload(DBUser user) throws JOSEException {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .displayName(user.getDisplayName())
                .role(user.getRole().name())
                .token(JWTUtil.createJWToken(JWTLoginData.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build()))
                .build();
    }
}
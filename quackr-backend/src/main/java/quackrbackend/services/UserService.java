package quackrbackend.services;

import com.nimbusds.jose.JOSEException;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SignUpRequest;
import quackrbackend.payloads.UserResponse;

public interface UserService {
    String signIn(SignInRequest signInRequest) throws JOSEException;
    String signUp(SignUpRequest signUpRequest) throws JOSEException;
    UserResponse getCurrentUser();
}

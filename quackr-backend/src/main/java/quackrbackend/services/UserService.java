package quackrbackend.services;

import com.nimbusds.jose.JOSEException;
import quackrbackend.payloads.SignInRequest;
import quackrbackend.payloads.SignUpRequest;
import quackrbackend.payloads.UserResponse;

public interface UserService {
    UserResponse signIn(SignInRequest signInRequest) throws JOSEException;
    UserResponse signUp(SignUpRequest signUpRequest) throws JOSEException;
}

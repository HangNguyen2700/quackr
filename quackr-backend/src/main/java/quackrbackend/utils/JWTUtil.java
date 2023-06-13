package quackrbackend.utils;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import quackrbackend.entities.Role;
import quackrbackend.exceptions.UnauthorizedException;
import quackrbackend.security.jwt.JWTLoginData;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

public class JWTUtil {

    public final static String ROLES_CLAIM = "role";

    public static String createJWToken(JWTLoginData credentials, Role role) throws JOSEException {

        final String user = credentials.getUsername();

        final JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

        // set additional fields if wanted
        builder.subject(user);
        builder.issueTime(new Date());
        builder.jwtID(UUID.randomUUID().toString());

        builder.claim(ROLES_CLAIM, role.name());

        final JWTClaimsSet claimsSet = builder.build();

        final JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        final Payload payload = new Payload(claimsSet.toJSONObject());
        final JWSObject jwsObject = new JWSObject(header, payload);

        final JWSSigner signer = new MACSigner(getSharedKey());
        jwsObject.sign(signer);

        return jwsObject.serialize();
    }

    public static boolean validateToken(String token) {
        try {
            final SignedJWT signed = SignedJWT.parse(token);
            final JWSVerifier verifier = new MACVerifier(getSharedKey());

            return signed.verify(verifier);
        } catch (ParseException | JOSEException ex) {
            return false;
        }
    }

    public static String getCurrentUsernameFromSubject() {
        Subject subject = SecurityUtils.getSubject();

        if (!subject.isAuthenticated()) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return (String) subject.getPrincipal();
    }

    private static byte[] getSharedKey() {
        return "mySuperDuperSecure256BitLongSecret".getBytes();
    }

}

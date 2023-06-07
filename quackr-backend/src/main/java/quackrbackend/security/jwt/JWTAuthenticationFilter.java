package quackrbackend.security.jwt;

import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.shaded.gson.Gson;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import quackrbackend.payloads.ErrorResponse;

import java.text.ParseException;
import java.util.Map;
import java.util.Objects;

public class JWTAuthenticationFilter extends AuthenticatingFilter {

    private final static String AUTHORIZATION = "Authorization";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        final HttpServletRequest httpRequest = WebUtils.toHttp(request);
        final String authzHeader = httpRequest.getHeader(AUTHORIZATION);

        if (authzHeader != null && authzHeader.startsWith("Bearer ")) {
            return buildShiroToken(authzHeader.split(" ")[1]);
        }

        return new UsernamePasswordToken();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        final boolean loggedIn = executeLogin(request, response);

        if (!loggedIn) {
            ErrorResponse errorResponse = new ErrorResponse(HttpServletResponse.SC_UNAUTHORIZED, "This endpoint requires authentication.");
            HttpServletResponse httpResponse = WebUtils.toHttp(response);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.getWriter().write(new Gson().toJson(errorResponse));

            return false;
        }

        return true;
    }

    private JWTShiroToken buildShiroToken(String token) {
        try {
            final JWSObject jwsObject = JWSObject.parse(token);
            final Map<String, Object> payload = jwsObject.getPayload().toJSONObject();

            final String username = Objects.toString(payload.get("sub"));

            return new JWTShiroToken(username, token);
        } catch (ParseException ex) {
            throw new AuthenticationException(ex);
        }

    }
}

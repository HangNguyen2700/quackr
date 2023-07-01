package quackrbackend.security;

import jakarta.servlet.Filter;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import quackrbackend.security.jwt.JWTAuthenticationFilter;
import quackrbackend.security.jwt.JWTWT2Realm;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public Realm jwtRealm() {
        return new JWTWT2Realm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(Realm jwtRealm) {
        return new DefaultWebSecurityManager(Collections.singletonList(jwtRealm));
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("jwtFilter", new JWTAuthenticationFilter());

        final Map<String, String> chainDefinition = new LinkedHashMap<>();

        chainDefinition.put("/api/auth/**", "anon");
        chainDefinition.put("/api/public/**", "anon");
        chainDefinition.put("/api/reset/**", "anon");
        chainDefinition.put("/api/**", "noSessionCreation, jwtFilter");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinition);

        return shiroFilterFactoryBean;
    }

}

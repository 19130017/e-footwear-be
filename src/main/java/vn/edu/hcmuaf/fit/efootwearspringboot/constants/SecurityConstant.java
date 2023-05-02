package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class SecurityConstant {
    public final static String[] PUBLIC_URLS = {
            "/api/v1/accounts/register", "/api/v1/accounts/login",
            "/swagger-ui/**", "/webjars/**", "/v3/**", "/swagger-resources/**",
    };
    public final static String[] REQUIRE_ADMIN_ROLE_URLS = {
            "/accounts/update-status",
            "/accounts/admin/**",
            "/app-status/**",
    };
}

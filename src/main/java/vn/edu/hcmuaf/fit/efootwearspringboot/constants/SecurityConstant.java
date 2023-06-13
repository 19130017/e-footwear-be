package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class SecurityConstant {

    public final static String[] METHOD_GET_URLS = {
            "/products",
            "/categories",
            "/colors",
            "/sizes",
            "/coupons"
    };

    public final static String[] PUBLIC_URLS = {
            "/accounts/register",
            "/accounts/login/**",
            "/accounts/verify/{token}",
            "/accounts/forgot-password/**",
            "/accounts/reset-password",

            "/products/slug/**",
            "/products/{id}",
            "/products/category/**",

            "/categories/parent",
            "/categories/slug/**",
            "/categories/{id}",

            "/customer/profile",

            "/details/**",

            "/galleries/{type}",
            "/v2/api-docs",
    };
    public final static String[] REQUIRE_ADMIN_ROLE_URLS = {
//            "/sizes",
    };
}

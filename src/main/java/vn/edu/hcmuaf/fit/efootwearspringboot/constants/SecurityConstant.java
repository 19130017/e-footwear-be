package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class SecurityConstant {

    public final static String[] METHOD_GET_URLS = {
            "/api/v1/products",
            "/api/v1/categories",
            "/api/v1/colors",
            "/api/v1/sizes",
    };

    public final static String[] PUBLIC_URLS = {
            "/api/v1/accounts/register",
            "/api/v1/accounts/login",

            "/api/v1/products/slug/**",
            "/api/v1/products/{id}",
            "/api/v1/products/category/**",

            "/api/v1/categories/parent",
            "/api/v1/categories/slug/**",
            "/api/v1/categories/{id}",

            "/api/v1/customer/profile",

            "/api/v1/details/**",

            "/api/v1/galleries/type/**",

    };
    public final static String[] REQUIRE_ADMIN_ROLE_URLS = {
//            "/api/v1/sizes",
    };
}

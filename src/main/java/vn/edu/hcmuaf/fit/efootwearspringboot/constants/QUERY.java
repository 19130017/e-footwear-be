package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class QUERY {
    public static class CATEGORY {
        public static final String FIND_ALL = "select * from categories where state = 'ACTIVE'";
        public static final String FIND_CATEGORY = "select * from categories where state = 'ACTIVE' and id = ? ";
        public static final String FIND_CATEGORY_BY_SLUG = "select * from categories where state = 'ACTIVE' and slug = ? ";

    }

    //product
    public static class PRODUCT {
        public static final String FIND_PRODUCTS = "select * from products where state = 'ACTIVE'";
        public static final String FIND_PRODUCT_BY_ID = "select * from products p " +
                "join products_sizes_colors psc on p.id = psc.product_id " +
                "where p.state = 'ACTIVE' and p.id = ? ";
        public static final String FIND_PRODUCT_BY_SLUG = "select * from products where state = 'ACTIVE' and slug = ? ";

    }
}

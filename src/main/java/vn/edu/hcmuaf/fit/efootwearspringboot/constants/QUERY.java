package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class QUERY {

    public static class CATEGORY {
        public static final String FIND_ALL = "select * from categories where state = 'ACTIVE'";
        public static final String FIND_CATEGORY = "select * from categories where state = 'ACTIVE' and id = ? ";
        public static final String FIND_CATEGORY_BY_SLUG = "select * from categories where state = 'ACTIVE' and slug = ? ";

        public static final String FIND_PARENT_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id is null";
        public static final String FIND_CHILDREN_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id = ?";
    }

    public static class COLOR {
        public static final String FIND_ALL = "select * from colors where state = 'ACTIVE'";
        public static final String FIND_COLOR = "select * from colors where state = 'ACTIVE' and id = ? ";
    }

    public static class SIZE {
        public static final String FIND_ALL = "select * from sizes where state = 'ACTIVE'";
        public static final String FIND_SIZE = "select * from sizes where state = 'ACTIVE' and id = ? ";
    }

    public static class PRODUCT {
        public static final String FIND_PRODUCTS = "select * from products where state = 'ACTIVE'";
        public static final String FIND_PRODUCT_BY_ID = "select * from products p " +
                "join products_sizes_colors psc on p.id = psc.product_id " +
                "where p.state = 'ACTIVE' and p.id = ? ";
        public static final String FIND_PRODUCT_BY_SLUG = "select * from products where state = 'ACTIVE' and slug = ? ";

        public static final String FIND_PRODUCT_BY_CATE_SLUG = "select p.* from products p join categories c on c.id = p.category_id where p.state = 'ACTIVE' and c.slug = ? ";

        public static final String COUNT_PRODUCT_BY_SLUG = "SELECT COUNT(*) from products where slug = ?";
    }
}

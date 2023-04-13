package vn.edu.hcmuaf.fit.efootwearspringboot.constants;

public class QUERY {

    public static class CATEGORY {
        public static final String FIND_ALL = "select * from categories where state = 'ACTIVE'";
        public static final String FIND_CATEGORY = "select * from categories where state = 'ACTIVE' and id = ? ";
        public static final String FIND_CATEGORY_BY_SLUG = "select * from categories where state = 'ACTIVE' and slug = ? ";

        public static final String FIND_PARENT_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id is null";
        public static final String FIND_CHILDREN_CATEGORY = "select * from categories where state = 'ACTIVE' and parent_id = ?";
    }

    public static class COLLECTION {
        public static final String FIND_ALL = "select * from collections where state = 'ACTIVE'";
        public static final String FIND_ALL_WITH_SLUG = "select c.id, c.name, c.state, c.category_id, c.create_at, c.update_at " +
                "from collections c " +
                "join categories cate on c.category_id = cate.id " +
                "where c.state = 'ACTIVE' and cate.slug = ?";
        public static final String FIND_COLLECTION = "select * from collections where state = 'ACTIVE' and id = ? ";
    }

    public static class PRODUCT {
        public static final String FIND_PRODUCTS = "select * from products where state = 'ACTIVE'";
        public static final String FIND_PRODUCT_BY_ID = "select * from products p " +
                "join products_sizes_colors psc on p.id = psc.product_id " +
                "where p.state = 'ACTIVE' and p.id = ? ";
        public static final String FIND_PRODUCT_BY_SLUG = "select * from products where state = 'ACTIVE' and slug = ? ";

    }
}

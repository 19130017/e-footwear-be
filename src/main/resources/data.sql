-- categories
insert into categories(parent_id, name, slug, state)
values (null, "Nam", "nam", "ACTIVE");
insert into categories(parent_id, name, slug, state)
values (null, "Nữ", "nu", "ACTIVE");
insert into categories(parent_id, name, slug, state)
values (null, "Trẻ em", "tre-em", "ACTIVE");
insert into categories(parent_id, name, slug, state)
values (1, "Giày thể thao", "giay-the-thao-nam", "ACTIVE");
insert into categories(parent_id, name, slug, state)
values (2, "Giày thể thao", "giay-the-thao-nu", "ACTIVE");
insert into categories(parent_id, name, slug, state)
values (2, "Giày búp bê", "giay-bup-be-nu", "ACTIVE");
--  collection
insert into collections(name, state, category_id)
values ("Test1", "ACTIVE", 1);
insert into collections(name, state, category_id)
values ("Test2", "ACTIVE", 2);
insert into collections(name, state, category_id)
values ("Hunter a", "ACTIVE", 1);
insert into collections(name, state, category_id)
values ("hunter b", "ACTIVE", 1);

-- color
insert into colors(code_color, name, state)
values ("yellow", "Vàng", "ACTIVE");
insert into colors(code_color, name, state)
values ("red", "Đỏ", "ACTIVE");
insert into colors(code_color, name, state)
values ("blue", "Xanh", "ACTIVE");

-- -- sizes
insert into sizes(value, state)
values ("38", "ACTIVE");
insert into sizes(value, state)
values ("39", "ACTIVE");
insert into sizes(value, state)
values ("40", "ACTIVE");
insert into sizes(value, state)
values ("41", "ACTIVE");

-- products
insert into products(name, slug, description, origin_price, discount_rate, stock_quantity, state, collection_id,
                     color_id, size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 2, 125, "ACTIVE",
        1, 2,
        1);
insert into products(name, slug, description, origin_price, discount_rate, stock_quantity, state, collection_id,
                     color_id, size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 2, 125, "ACTIVE",
        3, 2,
        1);
insert into products(name, slug, description, origin_price, discount_rate, stock_quantity, state, collection_id,
                     color_id, size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 2, 125, "ACTIVE",
        2, 2,
        1);
insert into products(name, slug, description, origin_price, discount_rate, stock_quantity, state, collection_id,
                     color_id, size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 2, 125, "ACTIVE",
        1, 2,
        1);


-- product images
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",
        1, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/00100vag_2dd5ae5608ad483e8ae78d00a89128c1_grande.jpg",
        1, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",
        4, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/00100vag_2dd5ae5608ad483e8ae78d00a89128c1_grande.jpg",
        4, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/dsbh00300xam__3__03ca735d421042b0849810ada927032d_grande.jpg",
        3, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",
        3, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",
        2, "ACTIVE");
insert into product_images(image_url, product_id, state)
values ("https://product.hstatic.net/1000230642/product/00100vag_2dd5ae5608ad483e8ae78d00a89128c1_grande.jpg",
        2, "ACTIVE");
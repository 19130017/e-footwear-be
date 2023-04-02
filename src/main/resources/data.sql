-- categories
insert into categories(parent_id, name)
values (null, "Giày nam");
insert into categories(parent_id, name)
values (null, "Giày nữ");
insert into categories(parent_id, name)
values (null, "Giày trẻ em");
insert into categories(parent_id, name)
values (1, "Giày thể thao");
insert into categories(parent_id, name)
values (2, "Giày thể thao");
insert into categories(parent_id, name)
values (2, "Giày búp bê");

-- brands
insert into brands(name)
values ("Bitis");
insert into brands(name)
values ("Adidas");
insert into brands(name)
values ("Nike");

-- color
insert into colors(code_color, name)
values ("yellow", "Vàng");
insert into colors(code_color, name)
values ("red", "Đỏ");

-- sizes
insert into sizes(value)
values ("38");
insert into sizes(value)
values ("39");
insert into sizes(value)
values ("40");
insert into sizes(value)
values ("41");

-- products
insert into products(name, slug, description, origin_price, discount_rate, quantity, brand_id, category_id, color_id,
                     size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 0, 125, 1, 4, 1,
        2);
insert into products(name, slug, description, origin_price, discount_rate, quantity, brand_id, category_id, color_id,
                     size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 0, 125, 1, 4, 1,
        1);
insert into products(name, slug, description, origin_price, discount_rate, quantity, brand_id, category_id, color_id,
                     size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 0, 125, 1, 4, 2,
        1);
insert into products(name, slug, description, origin_price, discount_rate, quantity, brand_id, category_id, color_id,
                     size_id)
values ("Giày Thể Thao Nam Biti's Hunter X Wavy Collection HSM001400DEN",
        "giay-the-thao-nam-biti-s-hunter-x-wavy-collection-hsm001400den-den", "description", 1069000, 0, 125, 1, 4, 2,
        4);

-- product images
insert into product_images(image_url, product_id) values("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",1);
insert into product_images(image_url, product_id) values("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",2);
insert into product_images(image_url, product_id) values("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",3);
insert into product_images(image_url, product_id) values("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",4);
insert into product_images(image_url, product_id) values("https://product.hstatic.net/1000230642/product/z4228019514433_4e24e88fd2a72c1c3fe36582ef218ac9_a6c478e1be634fff9d5507822a2e98f2.jpg",1);
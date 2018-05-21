insert into product_categories(id, name) values (1, 'frontend');
insert into product_categories(id, name) values (2, 'backend');
insert into product_categories(id, name) values (3, 'linux');
insert into product_categories(id, name) values (4, 'tool');
insert into product_categories(id, name) values (5, 'free');

insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (1, 'Spring', 1500, '40 x 40 mm', 1, '/images/spring.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (2, 'Python', 1200, '40 x 40 mm', 90, '/images/python.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (3, 'Django', 0, '40 x 40 mm', 8, '/images/django.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (4, 'Groovy', 0, '40 x 40 mm', 70, '/images/groovy.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (5, 'Docker', 2000, '40 x 40 mm', 9, '/images/docker.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (6, 'IntelliJ', 1300, '40 x 40 mm', 80, '/images/intelliJ.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (7, 'Android', 10000, '40 x 40 mm', 7, '/images/android.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (8, 'Tensorflow', 20000, '40 x 40 mm', 2, '/images/tensorflow.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (9, 'Git', 30000, '40 x 40 mm', 6, '/images/git.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (10, 'Rails', 3000, '40 x 40 mm', 5, '/images/radis.png', 2, now(), now());

insert into product_files(id, file_name, save_file_name, content_type, length, regtime, product_id) VALUES (1, 'python2.jpg', '/images/python2.jpg', 'image/jpg', 19600, now(), 2);
insert into product_files(id, file_name, save_file_name, content_type, length, regtime, product_id) VALUES (2, 'python3.jpg', '/images/python3.jpg', 'image/jpg', 19600, now(), 2);

insert into users(id, name, email, password, regtime) VALUES (1, '테스트', 'test', '{bcrypt}$2a$10$qS48/8nM2fSagy1di.whF.tutE/VZ9/wwOkGBcm.Ty8mOKLfwpv/G', now());
insert into user_roles(id, role_name, user_id) VALUES (1, 'USER', 1);
insert into user_roles(id, role_name, user_id) VALUES (2, 'ADMIN', 1);

insert into users(id, name, email, password, regtime) VALUES (2, '타노스', 'test2', '{bcrypt}$2a$10$qS48/8nM2fSagy1di.whF.tutE/VZ9/wwOkGBcm.Ty8mOKLfwpv/G', now());
insert into user_roles(id, role_name, user_id) VALUES (3, 'USER', 2);
insert into user_roles(id, role_name, user_id) VALUES (4, 'ADMIN', 2);

insert into orders(id, payment, order_no, phone1, phone2, phone3, receiver, regtime, status, zipcode, addr1, addr2, user_id, depositor) values (1, 2, 'S2018052056412', '010', '111', '222', '타조', now(), '3','111-111','지구','한국','1','김세화');
insert into orders(id, payment, order_no, phone1, phone2, phone3, receiver, regtime, status, zipcode, addr1, addr2, depositor) values (2, 2, 'S2018052078945', '010', '8757', '9087', '라쿤', now(), '3','222-234','지구','한국','김세화');
insert into orders(id, payment, order_no, phone1, phone2, phone3, receiver, regtime, status, zipcode, addr1, addr2, depositor) values (3, 2, 'S2018052045632', '010', '8757', '9087', '라쿤', now(), '3','222-234','지구','한국','김세화');

insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (1, 3, 5, 1200, now(), 1);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (2, 5, 4, 1200, now(), 1);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (3, 7, 3, 1200, now(), 1);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (4, 4, 5, 1200, now(), 2);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (5, 6, 8, 1200, now(), 2);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (6, 8, 2, 1200, now(), 2);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (7, 2, 6, 1200, now(), 3);
insert into order_products(id, product_id, quantity, purchase_price, regtime, order_id) VALUES (8, 9, 1, 1200, now(), 3);
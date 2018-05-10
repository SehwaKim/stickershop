insert into product_categories(id, name) values (1, 'frontend');
insert into product_categories(id, name) values (2, 'backend');
insert into product_categories(id, name) values (3, 'linux');
insert into product_categories(id, name) values (4, 'tool');
insert into product_categories(id, name) values (5, 'free');

insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (1, 'Spring', 1500, '40 x 40 mm', 1, '/static/spring.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (2, 'Python', 1200, '40 x 40 mm', 90, '/static/python.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (3, 'Django', 0, '40 x 40 mm', 80, '/static/django.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (4, 'Groovy', 0, '40 x 40 mm', 70, '/static/groovy.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (5, 'Docker', 2000, '40 x 40 mm', 9, '/static/docker.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (6, 'IntelliJ', 1300, '40 x 40 mm', 8, '/static/intellij.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (7, 'Android', 10000, '40 x 40 mm', 7, '/static/android.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (8, 'Tensorflow', 20000, '40 x 40 mm', 2, '/static/tensorflow.png', 2, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (9, 'Git', 30000, '40 x 40 mm', 6, '/static/git.png', 4, now(), now());
insert into products(id, name, price, size, sales, image_path, category_id, regtime, edittime) VALUES (10, 'Radis', 3000, '40 x 40 mm', 5, '/static/radis.png', 2, now(), now());

insert into product_files(id, file_name, save_file_name, content_type, length, regtime, product_id) VALUES (1, 'python2.jpg', '/static/python2.jpg', 'image/jpg', 19600, now(), 2);
insert into product_files(id, file_name, save_file_name, content_type, length, regtime, product_id) VALUES (2, 'python3.jpg', '/static/python3.jpg', 'image/jpg', 19600, now(), 2);
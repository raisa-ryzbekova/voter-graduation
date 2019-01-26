DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM restaurants;
DELETE
FROM dishes;
DELETE
FROM menu_items;
DELETE
FROM votes;

ALTER SEQUENCE global_seq
  RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100001),
       ('ROLE_USER', 100001);

INSERT INTO restaurants (name, phone, address)
VALUES ('Ресторан 1', 222222, 'Улица, д.1'),
       ('Ресторан 2', 222223, 'Улица, д.2'),
       ('Ресторан 3', 222224, 'Улица, д.3');

INSERT INTO dishes (name, price, restaurant_id)
VALUES ('Блюдо 1', 500, 100002),
       ('Блюдо 2', 500, 100002),
       ('Блюдо 3', 500, 100003),
       ('Блюдо 4', 500, 100003),
       ('Блюдо 5', 500, 100004),
       ('Блюдо 6', 500, 100004);

INSERT INTO menu_items (date, time, dish_id, restaurant_id)
VALUES ('2018-11-07', '07:00:00', 100005, 100002),
       ('2018-11-07', '07:00:00', 100006, 100002),
       ('2018-11-07', '07:00:00', 100007, 100003),
       ('2018-11-07', '07:00:00', 100008, 100003),
       ('2018-11-07', '07:00:00', 100009, 100004),
       ('2018-11-06', '07:00:00', 100010, 100004);

INSERT INTO votes (date, time, user_id, restaurant_id)
VALUES ('2018-11-07', '10:30:00', 100000, 100003);
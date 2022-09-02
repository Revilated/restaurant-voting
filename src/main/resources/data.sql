INSERT INTO USERS (NAME, EMAIL, PASSWORD)
VALUES ('User1', 'user1@yandex.ru', '{noop}user1'),
       ('User2', 'user2@yandex.ru', '{noop}user2'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO USER_ROLE (ROLE, USER_ID)
VALUES ('USER', 1),
       ('USER', 2),
       ('ADMIN', 3),
       ('USER', 3);

INSERT INTO RESTAURANT (NAME)
VALUES ('Макдональдс'),
       ('Бургер Кинг'),
       ('KFC');

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID, CREATED)
VALUES ('Двойной чизбургер', 15000, 1, '2022-08-31'),
       ('Нагетсы', 13000, 1, '2022-08-31'),
       ('Чай', 4000, 1, '2022-08-31');

INSERT INTO DISH (NAME, PRICE, RESTAURANT_ID)
VALUES ('Бит тейсти', 20000, 1),
       ('Картофель фри', 5000, 1),
       ('Кола', 6000, 1),
       ('Двойной воппер', 30000, 2),
       ('Пепси', 7000, 2),
       ('Твистер', 10000, 3),
       ('Куриные крылья', 15000, 3),
       ('Картофель по-деревенски', 7000, 3),
       ('Спрайт', 6000, 3);

INSERT INTO VOTE (USER_ID, RESTAURANT_ID, CREATED)
VALUES (1, 1, '2022-08-31'),
       (2, 2, '2022-08-31');

INSERT INTO VOTE (USER_ID, RESTAURANT_ID)
VALUES (1, 1),
       (2, 1),
       (3, 3);
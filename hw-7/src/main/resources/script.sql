create table product(
                        id serial primary key,
                        name varchar(128),
                        price bigint
)

insert into product (name, price) values ('Kiwi', 10);
insert into product (name, price) values ('Orange', 20);
insert into product (name, price) values ('Potatoes', 30);
insert into product (name, price) values ('Tomato', 40);
insert into product (name, price) values ('Banana', 50);
insert into product (name, price) values ('Pineapple', 60);
insert into product (name, price) values ('Apple', 70);
insert into product (name, price) values ('Bread', 80);
insert into product (name, price) values ('iPhone', 90);
insert into product (name, price) values ('Book', 100);
insert into product (name, price) values ('Car', 1000);
insert into product (name, price) values ('Bike', 550);
insert into product (name, price) values ('Lamp', 5);
insert into product (name, price) values ('Computer', 300);
insert into product (name, price) values ('Box', 3);
insert into product (name, price) values ('Boll', 7);
insert into product (name, price) values ('Jacket', 107);
insert into product (name, price) values ('Shoes', 70);
insert into product (name, price) values ('Sneakers', 35);
insert into product (name, price) values ('T-shirt', 15);
insert into product (name, price) values ('Shirt', 25);

select * from product;
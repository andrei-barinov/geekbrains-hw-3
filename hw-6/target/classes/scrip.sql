create table person(
id serial primary key,
name varchar(128)
)

insert into product(name, price) values ('Book', 40);
insert into product(name, price) values ('Computer', 100);
insert into product(name, price) values ('Cake', 5);
insert into product(name, price) values ('boll', 10);

select * from product;

insert into person (name) values ('Dmitri');
insert into person (name) values ('Denis');
insert into person (name) values ('Anton');
insert into person (name) values ('Vladimir');

create table product_person(
                               id serial primary key,
                               product_id int references product(id),
                               person_id bigint references person(id)
)

    insert into product_person(product_id, person_id) values (1, 1);
insert into product_person(product_id, person_id) values (9, 2);
insert into product_person(product_id, person_id) values (9, 1);
insert into product_person(product_id, person_id) values (11, 1);
insert into product_person(product_id, person_id) values (11, 3);
insert into product_person(product_id, person_id) values (11, 2);
insert into product_person(product_id, person_id) values (11, 3);

select * from product_person left join product on product_person.product_id = product.id left join person on product_person.person_id = person.id;
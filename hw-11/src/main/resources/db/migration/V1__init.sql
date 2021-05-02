create table products (
                          id                      bigserial primary key,
                          title                   varchar(255),
                          price                   int,
                          created_at              timestamp default current_timestamp,
                          updated_at              timestamp default current_timestamp
);

create table role (
                      id                      uuid primary key,
                      name                    varchar(50) not null unique,
                      created_at              timestamp default current_timestamp,
                      updated_at              timestamp default current_timestamp
);

create table person (
                       id                      bigserial primary key,
                       name                    varchar(30) not null unique,
                       role_id                 uuid not null references role(id),
                       login                   varchar(50) unique,
                       password                varchar(80) not null,
                       created_at              timestamp default current_timestamp,
                       updated_at              timestamp default current_timestamp
);

create table orders (
                        id                      bigserial primary key,
                        owner_id                bigint references person (id),
                        price                   int,
                        created_at              timestamp default current_timestamp,
                        updated_at              timestamp default current_timestamp
);

create table order_items (
                             id                      bigserial primary key,
                             order_id                bigint references orders (id),
                             product_id              bigint references products (id),
                             quantity                int,
                             price_per_product       int,
                             price                   int,
                             created_at              timestamp default current_timestamp,
                             updated_at              timestamp default current_timestamp
);

INSERT INTO role values('18c2f73d-e893-4c98-a871-e3928152ea00', 'ROLE_USER');
INSERT INTO role values ('19b05a72-2c4d-4f0c-8eb8-c734db9ff542', 'ROLE_MANAGER');
INSERT INTO role values ('cc839990-4997-4212-8709-e5eda3324994', 'ROLE_ADMIN');
INSERT INTO role values ('02f2904f-2847-4118-8eed-907f20b1ba56', 'ROLE_SUPERADMIN');

INSERT INTO products values(1, 'Bread', 10);
INSERT INTO products values(2, 'Milk', 15);
INSERT INTO products values(3, 'Computer', 1000);
INSERT INTO products values(4, 'Book', 100);
INSERT INTO products values(5, 'Cheese', 40);
INSERT INTO products values(6, 'iPhone', 200);
INSERT INTO products values(7, 'T-shirt', 80);
INSERT INTO products values(8, 'Watch', 75);
INSERT INTO products values(9, 'TV', 90);
INSERT INTO products values(10, 'Lamp', 3);

INSERT INTO person values(1, 'Mark', 'cc839990-4997-4212-8709-e5eda3324994', 'admin', '$2a$10$8iHKrbGKC0r2/Heplvr.Aed2hF74iRmye1eBjLW1fE7QsImcflfwC');
INSERT INTO person values(2, 'Denis', '18c2f73d-e893-4c98-a871-e3928152ea00', 'user1', '$2a$10$0SJDvhZPaGaIovyO/O.QHOuYFozr6t3pNWNoSFkZ61avuPcXfOywK');
INSERT INTO person values(3, 'Anton', '18c2f73d-e893-4c98-a871-e3928152ea00', 'user2', '$2a$10$IhbKLerrbb0TTAzvCuRNlOnnN9tj/7oEq7bNojsd/tR56Vy4z96vO');
INSERT INTO person values(4, 'Vladimir', '18c2f73d-e893-4c98-a871-e3928152ea00', 'user3', '$2a$10$C.VpbMhK5uQRy6AomGAedu7SBFdNirjgr1N7dfU/RyasI5dU9cUAW');
INSERT INTO person values(5, 'Alex', '19b05a72-2c4d-4f0c-8eb8-c734db9ff542', 'manager', '$2a$10$OrBVY8ul6ZcbcZ.n13Ayd.Cq8s5P.odekHK1yx04kj/GXZdh0Gb6S');
INSERT INTO person values(6, 'Andrei', '02f2904f-2847-4118-8eed-907f20b1ba56', 'superadmin', '$2a$10$ANXHqbwrie2XoktCLIuAuepShMI5.cEW/xT7rsRAkd4yFO8/N67mW');
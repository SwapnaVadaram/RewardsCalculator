create table customer ( id bigint not null, first_name varchar(255) not null, last_name varchar(255) not null);

insert into customer (id, first_name, last_name) values(1, 'Swapna', 'Berger');
insert into customer (id, first_name, last_name) values(2, 'Swapna1', 'Berger1');
insert into customer (id, first_name, last_name) values(3, 'Swapna3', 'Berger3');

create table purchase ( id bigint not null, amount decimal not null, time timestamp not null, customer_id bigint not null, FOREIGN KEY (customer_id) REFERENCES customer(id));
insert into purchase (id, amount, time, customer_id) values(1, 172.43, TO_TIMESTAMP('2022-03-01','YYYY-MM-DD'), 1);
insert into purchase (id, amount, time, customer_id) values(2, 76.43, TO_TIMESTAMP('2022-04-01','YYYY-MM-DD'), 1);
insert into purchase (id, amount, time, customer_id) values(3, 100.43, TO_TIMESTAMP('2022-04-01','YYYY-MM-DD'), 2);
insert into purchase (id, amount, time, customer_id) values(4, 200.56, TO_TIMESTAMP('2022-04-01','YYYY-MM-DD'), 3);



create table points(id bigint not null, min_value integer not null, max_value integer, points integer not null);

insert into points (id, min_value, max_value, points) values(1, 50, 100, 1);

insert into points (id, min_value, points) values(2, 100,2);




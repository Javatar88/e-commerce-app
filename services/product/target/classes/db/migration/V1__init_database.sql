create table if not exists CATEGORY
(
id integer not null primary key,
description varchar(255),
name varchar(255)
);

create table if not exists PRODUCT
(
id integer not null primary key,ProductService
description varchar(255),
name varchar(255),
available_quantity double precision not null,
price numeric(38,2),
category_id integer
constraint categoryfk references CATEGORY
);

create sequence if not exists category_seq increment by 1;
create sequence if not exists product_seq increment by 1;

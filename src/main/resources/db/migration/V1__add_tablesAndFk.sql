create table _order
(
    id           bigint not null auto_increment,
    date_created datetime(6),
    payment      varchar(255),
    user_id      bigint,
    primary key (id)
) engine = InnoDB;
create table drink_has_order
(
    id                       bigint  not null auto_increment,
    quantity                 integer not null,
    ingredients_has_drink_id bigint,
    order_id                 bigint,
    primary key (id)
) engine = InnoDB;
create table drinks
(
    id          bigint not null auto_increment,
    description varchar(255),
    image       varchar(255),
    name        varchar(255),
    price       decimal(38, 2),
    primary key (id)
) engine = InnoDB;
create table ingredients
(
    id          bigint not null auto_increment,
    description varchar(255),
    name        varchar(255),
    price       decimal(38, 2),
    primary key (id)
) engine = InnoDB;
create table ingredients_has_drink
(
    id            bigint not null auto_increment,
    drink_id      bigint,
    ingredient_id bigint,
    size_id       bigint,
    primary key (id)
) engine = InnoDB;
create table roles
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
) engine = InnoDB;
create table sizes
(
    id    bigint not null auto_increment,
    name  varchar(255),
    price_multiplier double precision,
    primary key (id)
) engine = InnoDB;
create table users
(
    id         bigint not null auto_increment,
    username     varchar(255),
    password     varchar(255),
    first_name varchar(255),
    last_name  varchar(255),
    payment    varchar(255),
    primary key (id)
) engine = InnoDB;
create table users_rolesset
(
    users_id    bigint not null,
    rolesset_id bigint not null,
    primary key (users_id, rolesset_id)
) engine = InnoDB;
alter table _order
    add constraint FK_user_to_order foreign key (user_id) references users (id);
alter table drink_has_order
    add constraint FK_drink_to_order foreign key (ingredients_has_drink_id) references ingredients_has_drink (id);
alter table drink_has_order
    add constraint FK_for_order foreign key (order_id) references _order (id);
alter table ingredients_has_drink
    add constraint FK_drinks_to_drink foreign key (drink_id) references drinks (id);
alter table ingredients_has_drink
    add constraint FK_ingredients_to_drink foreign key (ingredient_id) references ingredients (id);
alter table ingredients_has_drink
    add constraint FK_sizes_to_drink foreign key (size_id) references sizes (id);
alter table users_rolesset
    add constraint FK_roles_to_users foreign key (rolesset_id) references roles (id);
alter table users_rolesset
    add constraint FK_users_to_roles foreign key (users_id) references users (id);
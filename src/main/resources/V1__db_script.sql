
/* **************************************************************************
 *  
 */
create table tb_user_roles (
    id_ bigserial not null primary key,
    name_ varchar(32),
    is_admin_ bool default false not null 
);

insert into tb_user_roles (name_) values ('Клиент');
insert into tb_user_roles (name_) values ('Мастер');
insert into tb_user_roles (name_) values ('Администратор');
commit;


/* **************************************************************************
 *  
 */
create table tb_users (
    id_ bigserial not null primary key,
    user_role_id_ int not null,
    login_ varchar(64) not null unique,
    password_ varchar(255) not null,
    enabled_ boolean default true not null,
    foreign key (user_role_id_) references tb_user_roles(id_)
);

/* **************************************************************************
 *  
 */
create table tb_clients (
    id_ bigserial not null primary key,
    user_id_ int not null unique,
    name_ varchar(255) not null,
    phone_ varchar(12) not null,
    email_ varchar(128),
    address_ varchar(255),
    foreign key (user_id_) references tb_users(id_)
);

/* **************************************************************************
 *  
 */
create table tb_client_adresses (
    id_ bigserial not null primary key,
    client_id_ integer not null,
    address_ varchar(255),
    foreign key (client_id_) references tb_clients(id_)
);


/* **************************************************************************
 *  
 */
create table tb_pet_kind_ref (
    id_ bigserial not null primary key,
    name_ varchar(128)
);

insert into tb_pet_kind_ref (name_) values ('Кошка');
insert into tb_pet_kind_ref (name_) values ('Собака');
commit;

/* **************************************************************************
 *  
 */
create table tb_pets (
    id_ bigserial not null primary key,
    name_ varchar(255),
    client_id_ int not null,
    pet_kind_id_ int not null
);

alter table tb_pets add foreign key (client_id_) references tb_clients(id_);
alter table tb_pets add foreign key(pet_kind_id_) references tb_pet_kind_ref(id_);

/* **************************************************************************
 *  
 */
create table tb_services (
    id_ bigserial not null primary key,
    pet_kind_id_ int not null,
    name_ varchar(255),
    timing_minutes_ int not null,
    price_ money not null
);

alter table tb_services add foreign key (pet_kind_id_) references tb_pet_kind_ref(id_);
alter table tb_services add constraint un_services_pet_kind_id_name unique (pet_kind_id_, name_);

/* **************************************************************************
 *  
 */
create table tb_masters (
    id_ bigserial not null primary key,
    user_id_ integer not null unique,
    name_ varchar(255) not null,
    phone_ varchar(12) not null,
    email_ varchar(128),
    foreign key (user_id_) references tb_users(id_)
);


/* **************************************************************************
 *  
 */
create table tb_administrators (
    id_ bigserial not null primary key,
    user_id_ integer not null unique,
    name_ varchar(255) not null,
    phone_ varchar(12) not null,
    email_ varchar(128),
    foreign key (user_id_) references tb_users(id_)
);

/* **************************************************************************
 *  
 */
create table tb_schedules (
    id_ bigserial not null primary key,
    date_ date not null default current_date,
    master_id_ integer not null,
    start_time_ time not null,
    end_time_ time not null,
    foreign key (master_id_) references tb_masters(id_)
);

alter table tb_schedules
    add constraint un_schedules_master_id_start_time_end_time unique(master_id_, start_time_, end_time_);
    
/* **************************************************************************
 *  
 */
create table tb_schedule_items (
    id_ bigserial not null primary key,
    schedule_id_ integer not null,
    start_time_ time not null,
    timing_minutes_ integer,
    foreign key (schedule_id_) references tb_schedules(id_)
);

alter table tb_schedule_items
    add constraint un_schedule_items_schedule_id_start_time unique(schedule_id_, start_time_);
    
/* **************************************************************************
 *  
 */
create table tb_orders (
    id_ bigserial not null primary key,
    date_ date not null default current_date,
    schedule_item_id_ integer not null,
    pet_id_ integer not null,
    total_timing_minutes_ integer not null,
    foreign key (schedule_item_id_) references tb_schedule_items(id_),
    foreign key (pet_id_) references tb_pets(id_)
);

/* **************************************************************************
 *  
 */
create table tb_ordered_services (
    id_ bigserial not null primary key,
    order_id_ integer not null,
    service_id_ integer not null,
    foreign key (order_id_) references tb_orders(id_),
    foreign key (service_id_) references tb_services(id_)
);

/* **************************************************************************
 *  
 */
create table tb_client_feedbacks (
    id_ bigserial not null primary key,
    client_id_ integer not null,
    date_ date not null default current_date,
    feedback_ text,
    rate_ int default 5 not null
);

alter table tb_client_feedbacks add foreign key (client_id_) references tb_clients(id_);
alter table tb_client_feedbacks add check (rate_ between 1 and 5);

/* **************************************************************************
 *  
 */
create table tb_master_feedbacks (
    id_ bigserial not null primary key,
    master_id_ integer not null,
    date_ date not null default current_date,
    feedback_ text,
    rate_ int default 5 not null
);

alter table tb_master_feedbacks add foreign key (master_id_) references tb_masters(id_);
alter table tb_master_feedbacks add check (rate_ between 1 and 5);

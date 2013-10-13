# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table brand (
  id                        bigint not null,
  name_id                   varchar(255),
  name                      varchar(255),
  constraint uq_brand_name_id unique (name_id),
  constraint uq_brand_name unique (name),
  constraint pk_brand primary key (id))
;

create table category (
  id                        bigint not null,
  name_id                   varchar(255),
  name                      varchar(255),
  constraint uq_category_name_id unique (name_id),
  constraint uq_category_name unique (name),
  constraint pk_category primary key (id))
;

create table equipment (
  used                      boolean)
;

create table event (
  id                        bigint not null,
  start_date                timestamp,
  end_date                  timestamp,
  name                      varchar(255),
  name_id                   varchar(255),
  constraint pk_event primary key (id))
;

create table product (
  id                        bigint not null,
  type_of_equipment_id      bigint,
  category_id               bigint,
  brand_id                  bigint,
  name                      varchar(255) not null,
  name_id                   varchar(255),
  description               varchar(255),
  quantity                  integer,
  remaining_quantity        integer,
  owner                     varchar(255),
  room                      varchar(255),
  location                  varchar(255),
  state                     varchar(255),
  date_of_purchase          timestamp,
  euro_price                double,
  usd_price                 double,
  constraint pk_product primary key (id))
;

create table type_of_equipment (
  id                        bigint not null,
  name_id                   varchar(255),
  name                      varchar(255),
  constraint uq_type_of_equipment_name_id unique (name_id),
  constraint uq_type_of_equipment_name unique (name),
  constraint pk_type_of_equipment primary key (id))
;

create sequence brand_seq;

create sequence category_seq;

create sequence event_seq;

create sequence product_seq;

create sequence type_of_equipment_seq;

alter table product add constraint fk_product_typeOfEquipment_1 foreign key (type_of_equipment_id) references type_of_equipment (id) on delete restrict on update restrict;
create index ix_product_typeOfEquipment_1 on product (type_of_equipment_id);
alter table product add constraint fk_product_category_2 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_product_category_2 on product (category_id);
alter table product add constraint fk_product_brand_3 foreign key (brand_id) references brand (id) on delete restrict on update restrict;
create index ix_product_brand_3 on product (brand_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists brand;

drop table if exists category;

drop table if exists equipment;

drop table if exists event;

drop table if exists product;

drop table if exists type_of_equipment;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists brand_seq;

drop sequence if exists category_seq;

drop sequence if exists event_seq;

drop sequence if exists product_seq;

drop sequence if exists type_of_equipment_seq;


# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table brand (
  name                      varchar(255) not null,
  constraint pk_brand primary key (name))
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

create table product (
  id                        bigint not null,
  type_of_equipment_name    varchar(255),
  category_id               bigint,
  brand_name                varchar(255),
  name                      varchar(255) not null,
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
  name                      varchar(255) not null,
  constraint pk_type_of_equipment primary key (name))
;

create sequence brand_seq;

create sequence category_seq;

create sequence product_seq;

create sequence type_of_equipment_seq;

alter table product add constraint fk_product_typeOfEquipment_1 foreign key (type_of_equipment_name) references type_of_equipment (name) on delete restrict on update restrict;
create index ix_product_typeOfEquipment_1 on product (type_of_equipment_name);
alter table product add constraint fk_product_category_2 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_product_category_2 on product (category_id);
alter table product add constraint fk_product_brand_3 foreign key (brand_name) references brand (name) on delete restrict on update restrict;
create index ix_product_brand_3 on product (brand_name);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists brand;

drop table if exists category;

drop table if exists equipment;

drop table if exists product;

drop table if exists type_of_equipment;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists brand_seq;

drop sequence if exists category_seq;

drop sequence if exists product_seq;

drop sequence if exists type_of_equipment_seq;


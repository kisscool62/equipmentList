# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table brand (
  name                      varchar(255) not null,
  constraint pk_brand primary key (name))
;

create table category (
  name                      varchar(255) not null,
  constraint pk_category primary key (name))
;

create table equipment (
  used                      boolean)
;

create table equipment_type (
  name                      varchar(255) not null,
  constraint pk_equipment_type primary key (name))
;

create table product (
  id                        bigint not null,
  equipment_type_name       varchar(255),
  category_name             varchar(255),
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

create sequence brand_seq;

create sequence category_seq;

create sequence equipment_type_seq;

create sequence product_seq;

alter table product add constraint fk_product_equipmentType_1 foreign key (equipment_type_name) references equipment_type (name) on delete restrict on update restrict;
create index ix_product_equipmentType_1 on product (equipment_type_name);
alter table product add constraint fk_product_category_2 foreign key (category_name) references category (name) on delete restrict on update restrict;
create index ix_product_category_2 on product (category_name);
alter table product add constraint fk_product_brand_3 foreign key (brand_name) references brand (name) on delete restrict on update restrict;
create index ix_product_brand_3 on product (brand_name);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists brand;

drop table if exists category;

drop table if exists equipment;

drop table if exists equipment_type;

drop table if exists product;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists brand_seq;

drop sequence if exists category_seq;

drop sequence if exists equipment_type_seq;

drop sequence if exists product_seq;


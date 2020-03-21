alter table comment alter column id bigint auto_increment;
alter table comment alter column parent_id bigint not null;
alter table comment alter column commentator bigint not null;

alter table question alter column id bigint auto_increment;
alter table question alter column creator bigint not null;

alter table `user` alter column id bigint auto_increment;
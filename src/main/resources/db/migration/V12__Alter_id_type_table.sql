alter table comment modify id bigint auto_increment;
alter table comment modify parent_id bigint not null;
alter table comment modify commentator bigint not null;

alter table question modify id bigint auto_increment;
alter table question modify creator bigint not null;

alter table `user` modify id bigint auto_increment;
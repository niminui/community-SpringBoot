alter table COMMENT alter column ID bigint auto_increment;
alter table COMMENT alter column PARENT_ID bigint not null;
alter table COMMENT alter column COMMENTATOR bigint not null;

alter table QUESTION alter column ID bigint auto_increment;
alter table QUESTION alter column CREATOR bigint not null;

alter table USER alter column ID bigint auto_increment;
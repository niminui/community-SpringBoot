create table user(
    id int AUTO_INCREMENT primary key not null,
    account_id varchar(100),
    name varchar(52),
    token varchar(36),
    gmt_create BIGINT,
    gmt_modified BIGINT
);
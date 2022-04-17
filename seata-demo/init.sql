create table order_tbl
(
    id             int auto_increment
        primary key,
    user_id        varchar(255)  null,
    commodity_code varchar(255)  null,
    count          int default 0 null,
    money          int default 0 null
)
charset = utf8;

create table undo_log
(
    id            bigint auto_increment
        primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   datetime     not null,
    log_modified  datetime     not null,
    constraint ux_undo_log
        unique (xid, branch_id)
)
    charset = utf8;


create table stock_tbl
(
    id             int auto_increment
        primary key,
    commodity_code varchar(255)  null,
    count          int default 0 null,
    constraint commodity_code
        unique (commodity_code)
)
    charset = utf8;

create table undo_log
(
    id            bigint auto_increment
        primary key,
    branch_id     bigint       not null,
    xid           varchar(100) not null,
    context       varchar(128) not null,
    rollback_info longblob     not null,
    log_status    int          not null,
    log_created   datetime     not null,
    log_modified  datetime     not null,
    constraint ux_undo_log
        unique (xid, branch_id)
)
    charset = utf8;

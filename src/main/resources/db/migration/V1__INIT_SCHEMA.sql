create sequence users_seq;
create sequence tasks_seq;

create table if not exists Users (
    id bigint primary key generated always as identity,
    name varchar(255) not null,
    login varchar(255) not null,
    password varchar(255) not null
);

create table if not exists Tasks (
    id bigint primary key generated always as identity,
    title varchar(255) not null,
    content varchar(1023)
)

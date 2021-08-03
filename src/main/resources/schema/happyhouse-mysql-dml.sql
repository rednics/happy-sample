create table TBL_USER(
    id int not null AUTO_INCREMENT,
    email varchar(20) not null,
    password varchar(20) not null,
    roles varchar(20) not null,
    primary key (ID)
);
create table cliente(
	id integer not null auto_increment,
    nome varchar(255) not null,
    email varchar(255) null,
    telefone varchar(255) null,
    primary key(id)
);
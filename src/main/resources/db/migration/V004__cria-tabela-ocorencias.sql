create table ocorrencia(
	id bigint not null,
    descricao varchar(1000) not null,
    data_ocorrencia datetime,
    entrega_id bigint not null
);

alter table ocorrencia
modify id bigint not null auto_increment,
add constraint pk_ocorrencia_id primary key (id),
add constraint fk_entrega_ocorrencia foreign key (entrega_id) references entrega(id);
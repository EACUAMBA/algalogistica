#Para o entrega
create table entrega (
	id bigint not null,
    cliente_id bigint not null,
    taxa decimal(10, 2) not null,
    `status` varchar(20) not null,
    data_pedido datetime not null,
    data_finalizacao datetime null,
    
    destinatario_nome varchar(60) not null,
    destinatario_logradouro varchar(255),
    destinatario_complemento varchar(255),
    destinatario_numero varchar(255),
    destinatario_bairro varchar(255)
    
);

alter table entrega add constraint fk_entrega_cliente foreign key (cliente_id) references cliente (id),
add constraint pk_entrega primary key (id),
modify id bigint not null auto_increment
;
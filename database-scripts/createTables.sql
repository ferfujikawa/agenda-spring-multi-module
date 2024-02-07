CREATE TABLE tarefas (
	id uuid NOT NULL,
	data_cadastro timestamp(6) NULL,
	prazo timestamp(6) NULL,
	situacao varchar(255) NULL,
	titulo varchar(255) NULL,
	visualizada bool NULL,
	CONSTRAINT tarefas_pkey PRIMARY KEY (id)
);

CREATE TABLE historicos_tarefa (
	id uuid NOT NULL,
	anotacao varchar(255) NULL,
	data_historico timestamp(6) NULL,
	evento varchar(255) NULL,
	tarefa_id uuid NULL,
	CONSTRAINT historicos_tarefa_pkey PRIMARY KEY (id),
	CONSTRAINT historicos_tarefa_tarefa_fk FOREIGN KEY (tarefa_id) REFERENCES tarefas(id)
);
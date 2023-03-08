DROP TABLE IF EXISTS usuario CASCADE;
DROP TABLE IF EXISTS permissao CASCADE;

CREATE TABLE usuario (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	nome VARCHAR(100) NOT NULL,
	telefone VARCHAR(14) NOT NULL,
	email VARCHAR(100) NOT NULL,
	senha VARCHAR(100) NOT NULL,
	foto VARCHAR(255),
	criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	atualizado_em TIMESTAMP,
	is_ativo 		BOOLEAN NOT NULL DEFAULT FALSE
);
ALTER TABLE usuario ADD CONSTRAINT pk_usuario PRIMARY KEY (id);
ALTER TABLE usuario ADD CONSTRAINT uk_usuario_email UNIQUE (email);

CREATE TABLE permissao (
	id 				BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
	funcao			VARCHAR(100) NOT NULL,
	usuario_id 		BIGINT NOT NULL
);
ALTER TABLE permissao ADD CONSTRAINT pk_permissao PRIMARY KEY (id);
ALTER TABLE permissao ADD CONSTRAINT uk_permissao UNIQUE (funcao, usuario_id);
ALTER TABLE permissao ADD CONSTRAINT fk_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario;

-- Cria a tabela pessoa
CREATE TABLE IF NOT EXISTS pessoa (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	endereco VARCHAR(150) NOT NULL,
	fone VARCHAR(20) NOT NULL
);

-- Cria a tabela pessoa_fisica
CREATE TABLE IF NOT EXISTS pessoa_fisica (
	id BIGINT NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	rg VARCHAR(20) NOT NULL,
	FOREIGN KEY(id) REFERENCES pessoa(id),
	PRIMARY KEY(id)
);

-- Cria a tabela fone
--
-- A relação entre as tabelas 'pessoa' e 'fone' é de 1:N (uma pessoa possui
-- vários fones)
CREATE TABLE IF NOT EXISTS fone (
	id BIGINT NOT NULL,
	numero VARCHAR(20) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	operadora VARCHAR(20) NOT NULL,
	FOREIGN KEY(id) REFERENCES pessoa(id),
	PRIMARY KEY(id, numero)
);


-- Cria a tabela pessoa
CREATE TABLE IF NOT EXISTS pessoa (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	tratamento VARCHAR(10) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	rg VARCHAR(20) NOT NULL,
	sexo VARCHAR(1) NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero VARCHAR(6) NOT NULL,
	complemento VARCHAR(100) NOT NULL,
	bairro VARCHAR(20) NOT NULL,
	cidade VARCHAR(20) NOT NULL,
	cep VARCHAR(8) NOT NULL,
	email VARCHAR(30) NOT NULL,
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


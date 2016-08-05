-- Cria a tabela pessoa
CREATE TABLE IF NOT EXISTS pessoa (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(100) NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	rg VARCHAR(20) NOT NULL,
	sexo VARCHAR(9) NOT NULL,
	tratamento VARCHAR(10) NOT NULL,
	logradouro VARCHAR(100) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	complemento VARCHAR(100) NOT NULL,
	bairro VARCHAR(50) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	cep VARCHAR(8) NOT NULL
);
-- Cria a tabela fone
--
-- A relação entre as tabelas 'fone' e 'pessoa' é de N:1 (uma pessoa possui
-- vários fones)
CREATE TABLE IF NOT EXISTS fone (
	id BIGINT NOT NULL,
	numero VARCHAR(20) NOT NULL,
	tipo VARCHAR(20) NOT NULL,
	operadora VARCHAR(20) NOT NULL,
	FOREIGN KEY(id) REFERENCES pessoa(id),
	PRIMARY KEY(id, numero)
);


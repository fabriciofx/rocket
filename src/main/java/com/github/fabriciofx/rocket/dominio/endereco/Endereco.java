package com.github.fabriciofx.rocket.dominio.endereco;

import com.github.fabriciofx.rocket.dominio.documento.Documento;

public interface Endereco extends Documento {
	Logradouro logradouro();

	void logradouro(Logradouro logradouro);

	Numero numero();

	void numero(Numero numero);

	Complemento complemento();

	void complemento(Complemento complemento);

	Bairro bairro();

	void bairro(Bairro bairro);

	Cidade cidade();

	void cidade(Cidade cidade);

	Cep cep();

	void cep(Cep cep);
}

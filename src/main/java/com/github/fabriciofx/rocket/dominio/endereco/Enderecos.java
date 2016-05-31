package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

public interface Enderecos {
	Endereco endereco(Logradouro logradouro, Numero numero,
			Complemento complemento, Bairro bairro, Cidade cidade, Cep cep)
			throws IOException;

	Iterable<Endereco> iterate() throws IOException;
}

package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;
import java.util.Iterator;

public interface Enderecos {
	Endereco endereco(Logradouro logradouro, Numero numero,
			Complemento complemento, Bairro bairro, Cidade cidade, Cep cep)
			throws IOException;

	Iterator<Endereco> iterate() throws IOException;
}

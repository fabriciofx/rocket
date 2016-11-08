package com.github.fabriciofx.rocket.doc.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.doc.Documento;

public interface Endereco extends Documento {
	void atualize(String logradouro, String numero, String complemento,
		String bairro, Cidade cidade, Cep cep) throws IOException;

	void apague() throws IOException;
}

package com.github.fabriciofx.rocket.dominio.doc.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.doc.Documento;

public interface Endereco extends Documento {
	Logradouro logradouro() throws IOException;
	
	Numero numero() throws IOException;
	
	Complemento complemento() throws IOException;
	
	Bairro bairro() throws IOException;
	
	Cidade cidade() throws IOException;
	
	Cep cep() throws IOException;
}

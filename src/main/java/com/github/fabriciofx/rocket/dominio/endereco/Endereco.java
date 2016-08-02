package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Documento;

public interface Endereco extends Documento {
	Logradouro logradouro() throws IOException;
	
	Numero numero() throws IOException;
	
	Complemento complemento() throws IOException;
	
	Bairro bairro() throws IOException;
	
	Cidade cidade() throws IOException;
	
	Cep cep() throws IOException;
}

package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.H2Database;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.endereco.MeEndereco;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Bairro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cep;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Cidade;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Complemento;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Estado;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Logradouro;
import com.github.fabriciofx.rocket.dominio.endereco.doc.Numero;
import com.github.fabriciofx.rocket.dominio.fone.MeFone;
import com.github.fabriciofx.rocket.dominio.fone.MeFones;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Rg;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.pessoa.doc.Tratamento;
import com.github.fabriciofx.rocket.id.UuidId;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class TestePessoa {
	@Test
	public void pessoa() throws IOException {
		final TestePessoaDatabase bd = new TestePessoaDatabase(
			new H2Database("testebd")
		).init();
		final Pessoa pessoa = new BdPessoa(
			bd.dataSource(),
			new UuidId()
		).salva(
			new MePessoa(
				new Nome("Jason Bourne"),
				new MeDocumentos(
					new Cpf("57381117533"),
					new Rg("62527362"),
					Sexo.MASCULINO,
					Tratamento.SENHOR,
					new MeEndereco(
						new Logradouro("Av Gov Torquato Nepomuceno Neves"),
						new Numero("123"),
						new Complemento("AP 101"),
						new Bairro("Vila Madalena"),
						new Cidade("São Paulo", Estado.SP),
						new Cep("48035120")
					),
					new MeFones(
						new MeFone("81988144321"),
						new MeFone("83999231234")
					)
				)
			)
		);
		System.out.println(new XmlFormat(
			pessoa.print(
				new XmlMedia("pessoa")).toString()
			).toString()
		);
		bd.finaliza();
	}
}

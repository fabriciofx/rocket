package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.IOException;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.H2Database;
import com.github.fabriciofx.rocket.dominio.Nome;
import com.github.fabriciofx.rocket.dominio.Pessoa;
import com.github.fabriciofx.rocket.dominio.Pessoas;
import com.github.fabriciofx.rocket.dominio.bd.BdPessoa;
import com.github.fabriciofx.rocket.dominio.bd.BdPessoas;
import com.github.fabriciofx.rocket.dominio.doc.Cpf;
import com.github.fabriciofx.rocket.dominio.doc.Rg;
import com.github.fabriciofx.rocket.dominio.doc.Sexo;
import com.github.fabriciofx.rocket.dominio.doc.Tratamento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Bairro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cep;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Cidade;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Complemento;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Estado;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Logradouro;
import com.github.fabriciofx.rocket.dominio.doc.endereco.Numero;
import com.github.fabriciofx.rocket.dominio.me.MeDocumentos;
import com.github.fabriciofx.rocket.dominio.me.MeEndereco;
import com.github.fabriciofx.rocket.dominio.me.MeFone;
import com.github.fabriciofx.rocket.dominio.me.MeFones;
import com.github.fabriciofx.rocket.dominio.me.MePessoa;
import com.github.fabriciofx.rocket.id.UuidId;
import com.github.fabriciofx.rocket.media.XmlFormat;
import com.github.fabriciofx.rocket.media.XmlMedia;

public final class TestePessoas {
	@Test
	public void pessoas() throws IOException {
		final TestePessoaDatabase bd = new TestePessoaDatabase(
				new H2Database("testebd")
			).init();
		final Pessoas pessoas = new BdPessoas(bd.dataSource());
		new BdPessoa(
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
		for (final Pessoa p : pessoas.todas()) {
			System.out.println(new XmlFormat(
				p.print(
					new XmlMedia("pessoa")).toString()
				).toString()
			);			
		}
	}
}

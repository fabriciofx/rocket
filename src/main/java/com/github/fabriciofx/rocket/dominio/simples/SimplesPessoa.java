package com.github.fabriciofx.rocket.dominio.simples;

import java.io.IOException;

import com.github.fabriciofx.rocket.dominio.documento.Cpf;
import com.github.fabriciofx.rocket.dominio.documento.Documento;
import com.github.fabriciofx.rocket.dominio.documento.Fone;
import com.github.fabriciofx.rocket.dominio.documento.Nome;
import com.github.fabriciofx.rocket.dominio.documento.Rg;
import com.github.fabriciofx.rocket.dominio.documento.Sexo;
import com.github.fabriciofx.rocket.dominio.documento.Tratamento;
import com.github.fabriciofx.rocket.dominio.endereco.Endereco;
import com.github.fabriciofx.rocket.dominio.pessoa.Pessoa;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.immutable.Array;

public final class SimplesPessoa implements Pessoa {
	private final transient Array<Documento> documentos;

	public SimplesPessoa(final Documento... documentos) {
		this.documentos = new Array<>(documentos);
	}

	@Override
	public Media print(Media media) throws IOException {
		for (final Documento d : documentos) {
			media = d.print(media);
		}
		return media;
	}

	@Override
	public Nome nome() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void nome(Nome nome) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Sexo sexo() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sexo(Sexo sexo) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tratamento tratamento() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tratamento(Tratamento tratamento) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cpf cpf() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cpf(Cpf cpf) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rg rg() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void rg(Rg rg) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Endereco endereco() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void endereco(Endereco endereco) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Fone> fones() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fones(Iterable<Fone> fones) throws IOException {
		// TODO Auto-generated method stub
		
	}
}

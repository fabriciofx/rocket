package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.dominio.repositorio.Identificavel;
import com.github.fabriciofx.rocket.dominio.simples.SimplesFone;
import com.github.fabriciofx.rocket.infra.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlFone implements Fone, Identificavel<Id> {
	private final transient DataSource ds;
	private final transient Id id;

	public SqlFone(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}

	@Override
	public Id id() {
		return id;
	}
	
	@Override
	public Media print(Media media) throws IOException { 
		return fone(id).print(media);
	}
	
	private Fone fone(final Id id) throws IOException {
		try {
			final List<Fone> fones = new JdbcSession(ds)
				.sql("SELECT * FROM fone WHERE pessoa = ?")
				.set(id)
				.select(new ListOutcome<Fone>(
					new ListOutcome.Mapping<Fone>() {
						@Override
						public Fone map(final ResultSet rs) 
							throws SQLException {
								return new SimplesFone(
										rs.getString(2),
										Tipo.valueOf(rs.getString(3)),
										Operadora.valueOf(rs.getString(4))
								);
							}
						}
					)
				);
			return fones.get(0);
		} catch (final SQLException e) {
			throw new IOException(e);
		}			
	}

	@Override
	public String numero() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void numero(String numero) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tipo tipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tipo(Tipo tipo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Operadora operadora() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void operadora(Operadora operadora) {
		// TODO Auto-generated method stub
		
	}
}

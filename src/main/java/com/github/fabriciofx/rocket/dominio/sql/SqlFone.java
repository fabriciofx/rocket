package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.doc.Documento;
import com.github.fabriciofx.rocket.dominio.doc.Fone;
import com.github.fabriciofx.rocket.dominio.repositorio.Id;
import com.github.fabriciofx.rocket.media.InsertSqlMedia;
import com.github.fabriciofx.rocket.media.Media;
import com.github.fabriciofx.rocket.repository.Repository;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlFone implements Repository<Fone>, Documento {
	private final transient Fone origem;
	private final transient Id id;

	public SqlFone(final Fone origem, final Id id) {
		this.origem = origem;
		this.id = id;
	}
	
	@Override
	public Media print(final Media media) throws IOException { 
		return origem.print(media.with("id", id.toString()));
	}
	
	@Override
	public void save(final DataSource ds) throws IOException {
		final String sql = print(new InsertSqlMedia("fone")).toString();
		try {
			new JdbcSession(ds)
				.sql(sql)
				.insert(new SingleOutcome<Long>(Long.class, true));
		} catch (final SQLException e) {
			throw new IOException(e);
		}		
	}
	
	@Override
	public Fone find(final DataSource ds, final Id id) throws IOException {
		try {
			final List<Fone> fones = new JdbcSession(ds)
				.sql("SELECT * FROM fone WHERE pessoa = ?")
				.set(id)
				.select(new ListOutcome<Fone>(
					new ListOutcome.Mapping<Fone>() {
						@Override
						public Fone map(final ResultSet rs) 
							throws SQLException {
								return new Fone(
										rs.getString(2),
										Fone.Tipo.valueOf(rs.getString(3)),
										Fone.Operadora.valueOf(rs.getString(4))
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
}

package com.github.fabriciofx.rocket.dominio.sql;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.dominio.Fone;
import com.github.fabriciofx.rocket.dominio.Fones;
import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;

public final class SqlFones implements Fones {
	private final transient DataSource ds;
	private final transient Id id;
	
	public SqlFones(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}

	@Override
	public Fone fone(final String numero) throws IOException {
		return new SqlFone(ds, id, numero);
	}

	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final String numero : numeros()) {
			m = m.with("fone", numero);
		}
		return m;
	}
	
	private List<String> numeros() throws IOException {
		try {
			return new JdbcSession(ds)
				.sql("SELECT numero FROM fone WHERE pessoa = ?")
				.set(id)
				.select(
					new ListOutcome<String>(
						new ListOutcome.Mapping<String>() {
							@Override
							public String map(final ResultSet rs)
								throws SQLException {
								return rs.getString(1);
							}
						}
					)
				);
		} catch (final SQLException e) {
			throw new IOException(e);
		}				
	}
}

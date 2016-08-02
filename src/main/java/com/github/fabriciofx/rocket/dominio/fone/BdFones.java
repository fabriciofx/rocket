package com.github.fabriciofx.rocket.dominio.fone;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.github.fabriciofx.rocket.id.Id;
import com.github.fabriciofx.rocket.media.Media;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.ListOutcome;
import com.jcabi.jdbc.SingleOutcome;

public final class BdFones implements Fones {
	private final transient DataSource ds;
	private final transient Id id;
	
	public BdFones(final DataSource ds, final Id id) {
		this.ds = ds;
		this.id = id;
	}

	@Override
	public Fone fone(final String numero) throws IOException {
		return new BdFone(ds, id, numero);
	}

	@Override
	public Media print(final Media media) throws IOException {
		Media m = media;
		for (final String numero : numeros()) {
			m = m.with("fone", numero);
		}
		return m;
	}

	@Override
	public void salva(final Fones origem) throws IOException {
		final JdbcSession session = new JdbcSession(ds);
		try {
			for (final Fone f : origem.todos()) {
				session.sql("INSERT INTO fone (pessoa, numero) VALUES (?, ?)")
					.set(id)
					.set(f.numero())
					.insert(SingleOutcome.VOID);
			}
		} catch (final SQLException e) {
			throw new IOException(e);
		}
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

	@Override
	public List<Fone> todos() throws IOException {
		final List<Fone> todos = new ArrayList<>();
		for (final String numero : numeros()) {
			todos.add(new BdFone(ds, id, numero));
		}
		return todos;
	}
}

package com.github.fabriciofx.rocket.doc.fone;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.fabriciofx.rocket.doc.fone.Fone.Operadora;
import com.github.fabriciofx.rocket.doc.fone.Fone.Tipo;
import com.jcabi.jdbc.ListOutcome;

public final class SqlFoneMapping implements ListOutcome.Mapping<Fone> {
	@Override
	public Fone map(final ResultSet rs) throws SQLException {
		return new MemFone(
			rs.getString(2),
			Tipo.valueOf(rs.getString(3)),
			Operadora.valueOf(rs.getString(4))
		);
	}
}

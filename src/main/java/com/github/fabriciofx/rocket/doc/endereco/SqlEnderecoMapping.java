package com.github.fabriciofx.rocket.doc.endereco;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jcabi.jdbc.ListOutcome;

public final class SqlEnderecoMapping implements ListOutcome.Mapping<Endereco> {
	@Override
	public Endereco map(final ResultSet rs) throws SQLException {
		return new MemEndereco(
			rs.getString(1),
			rs.getString(2),
			rs.getString(3),
			rs.getString(4),
			new Cidade(rs.getString(5)),
			new Cep(rs.getString(6))
		);
	}
}

package com.github.fabriciofx.rocket.pessoa;

import java.io.IOException;
import java.util.concurrent.Callable;

import com.github.fabriciofx.rocket.db.Database;
import com.github.fabriciofx.rocket.db.Transaction;
import com.github.fabriciofx.rocket.doc.Nome;
import com.github.fabriciofx.rocket.id.Id;
import com.jcabi.jdbc.JdbcSession;
import com.jcabi.jdbc.SingleOutcome;

public final class SqlPessoasFisicas implements PessoasFisicas {
	private final Database db;

	public SqlPessoasFisicas(final Database db) {
		this.db = db;
	}
	
	@Override
	public PessoaFisica pessoaFisica(final Nome nome, final String endereco,
		final String fone, final String cpf, final String rg)
		throws IOException {
		return new Transaction(db).call(
			new Callable<PessoaFisica>() {
				@Override
				public PessoaFisica call() throws Exception {
					final Pessoa pessoa = new SqlPessoas(db).pessoa(
						nome,
						endereco,
						fone
					);
					new JdbcSession(db.source())
						.sql("INSERT INTO pessoa_fisica (id, cpf, rg) " +
							 "VALUES (?, ?, ?)")
						.set(pessoa.id())
						.set(cpf)
						.set(rg)
						.insert(SingleOutcome.VOID);
					return new SqlPessoaFisica(pessoa, db);
				}
			}
		);
	}

	@Override
	public PessoaFisica pessoaFisica(final Id id) {
		return new SqlPessoaFisica(
			new SqlPessoa(id, db),
			db
		);
	}
}

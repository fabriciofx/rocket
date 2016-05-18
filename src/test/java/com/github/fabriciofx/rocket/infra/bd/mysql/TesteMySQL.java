package com.github.fabriciofx.rocket.infra.bd.mysql;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.infra.bd.Conexao;
import com.github.fabriciofx.rocket.infra.bd.Dados;
import com.github.fabriciofx.rocket.infra.bd.Insert;
import com.github.fabriciofx.rocket.infra.bd.MySQL;
import com.github.fabriciofx.rocket.infra.bd.Select;
import com.github.fabriciofx.rocket.infra.bd.Sgbd;
import com.github.fabriciofx.rocket.infra.bd.Update;
import com.github.fabriciofx.rocket.infra.bd.Usuario;

public final class TesteMySQL {
	private final static transient String NOME_BD = "testebd";

	@Test
	public void url() {
		final Sgbd mysql = new MySQL();
		assertEquals("jdbc:mysql://localhost:3306/testebd", mysql.url(NOME_BD));
	}

	@Test
	public void servidor() throws IOException, InterruptedException {
		final Sgbd mysql = new MySQL();
		criaBD(mysql);
		final Conexao conexao = new Conexao(mysql, NOME_BD,
				new Usuario("root", "admin"));
		final long id = new Date().getTime();
		final String msg = "Uma mensagem de log qualquer";
		new Update("CREATE TABLE IF NOT EXISTS"
				+ " log(id BIGINT PRIMARY KEY, info VARCHAR(255))")
						.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id, msg)
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 1, msg + "1")
				.execute(conexao);
		new Insert("INSERT INTO log (id, info) VALUES(?, ?)", id + 2, msg + "2")
				.execute(conexao);
		final Select select = new Select("SELECT * FROM log");
		final Dados logs = select.execute(conexao);
		assertEquals(logs.item(0, "id"), id);
		assertEquals(logs.item(0, "info"), msg);
		assertEquals(logs.item(1, "id"), id + 1);
		assertEquals(logs.item(1, "info"), msg + "1");
		assertEquals(logs.item(2, "id"), id + 2);
		assertEquals(logs.item(2, "info"), msg + "2");
		apagaBD(mysql);
		conexao.fecha();
	}

	private void apagaBD(final Sgbd mysql) throws IOException {
		final Conexao conexao = new Conexao(mysql, NOME_BD,
				new Usuario("root", "admin"));
		new Update("DROP DATABASE IF EXISTS testebd").execute(conexao);
		conexao.fecha();
	}

	private void criaBD(final Sgbd mysql) throws IOException {
		final Conexao conexao = new Conexao(mysql, "",
				new Usuario("root", "admin"));
		new Update("CREATE DATABASE IF NOT EXISTS testebd").execute(conexao);
		conexao.fecha();
	}
}

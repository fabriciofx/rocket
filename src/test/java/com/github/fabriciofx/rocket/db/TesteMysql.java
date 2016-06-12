package com.github.fabriciofx.rocket.db;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.github.fabriciofx.rocket.db.Conexao;
import com.github.fabriciofx.rocket.db.Dados;
import com.github.fabriciofx.rocket.db.Insert;
import com.github.fabriciofx.rocket.db.Mysql;
import com.github.fabriciofx.rocket.db.Select;
import com.github.fabriciofx.rocket.db.Sgbd;
import com.github.fabriciofx.rocket.db.Update;
import com.github.fabriciofx.rocket.db.Usuario;

public final class TesteMysql {
	private final static transient String NOME_BD = "testebd";

	@Test
	public void url() {
		final Sgbd mysql = new Mysql(NOME_BD);
		assertEquals("jdbc:mysql://localhost:3306/testebd", mysql.url());
	}

	@Test
	public void servidor() throws IOException, InterruptedException {
		final Sgbd mysql = new Mysql(NOME_BD);
		criaBD();
		final Conexao conexao = new Conexao(mysql,
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
		final Conexao conexao = new Conexao(mysql,
				new Usuario("root", "admin"));
		new Update("DROP DATABASE IF EXISTS testebd").execute(conexao);
		conexao.fecha();
	}

	private void criaBD() throws IOException {
		final Sgbd mysql = new Mysql("");
		final Conexao conexao = new Conexao(mysql,
				new Usuario("root", "admin"));
		new Update("CREATE DATABASE IF NOT EXISTS testebd").execute(conexao);
		conexao.fecha();
	}
}

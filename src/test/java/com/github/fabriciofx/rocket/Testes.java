package com.github.fabriciofx.rocket;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.github.fabriciofx.rocket.dominio.TesteCnpj;
import com.github.fabriciofx.rocket.dominio.TesteNome;
import com.github.fabriciofx.rocket.dominio.TestePeriodo;
import com.github.fabriciofx.rocket.dominio.TestePessoa;
import com.github.fabriciofx.rocket.dominio.TestePlaca;
import com.github.fabriciofx.rocket.dominio.endereco.TesteCep;
import com.github.fabriciofx.rocket.dominio.endereco.TesteEndereco;
import com.github.fabriciofx.rocket.dominio.financeiro.TesteConta;
import com.github.fabriciofx.rocket.dominio.financeiro.TesteDinheiro;
import com.github.fabriciofx.rocket.dominio.intervalo.TesteIntervalo;
import com.github.fabriciofx.rocket.dominio.repositorio.TesteDataId;
import com.github.fabriciofx.rocket.dominio.repositorio.TesteHashId;
import com.github.fabriciofx.rocket.infraestrutura.crypto.TesteHash;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TesteCnpj.class,
	TesteNome.class,
	TestePeriodo.class,
	TestePessoa.class,
	TestePlaca.class,
	TesteCep.class,
	TesteEndereco.class,
	TesteConta.class,
	TesteDinheiro.class,
	TesteIntervalo.class,
	TesteDataId.class,
	TesteHashId.class,
	TesteHash.class
})
public final class Testes {
}

package com.github.fabriciofx.rocket.dominio.financeiro;

import java.util.Currency;
import java.util.Objects;

import com.github.fabriciofx.rocket.dominio.Periodo;
import com.github.fabriciofx.rocket.dominio.financeiro.lancamento.Credito;
import com.github.fabriciofx.rocket.dominio.financeiro.lancamento.Debito;
import com.github.fabriciofx.rocket.dominio.financeiro.lancamento.Lancamento;
import com.jcabi.immutable.Array;

public final class Conta {
	private final Array<Lancamento> lancamentos;
	private final Currency moeda;

	public Conta(final Dinheiro saldo) {
		this(new Lancamento(Credito.class, saldo));
	}

	public Conta(final Lancamento... lancamentos) {
		this(Currency.getInstance("BRL"), new Array<Lancamento>(lancamentos));
	}

	public Conta(final Currency moeda, final Array<Lancamento> lancamentos) {
		this.moeda = Objects.requireNonNull(moeda,
				"tipo de moeda da conta não pode ser NULL");
		this.lancamentos = Objects.requireNonNull(lancamentos,
				"lançamentos da conta não podem ser NULL");
	}

	public Currency moeda() {
		return moeda;
	}

	public Conta adiciona(final Lancamento lancamento) {
		return new Conta(moeda, lancamentos.with(lancamento));
	}

	public Conta saque(final Dinheiro quantia) {
		return adiciona(new Lancamento(Debito.class, quantia.inverte()));
	}

	public Conta deposito(final Dinheiro quantia) {
		return adiciona(new Lancamento(Credito.class, quantia));
	}

	public Dinheiro saldo() {
		Dinheiro resultado = new Dinheiro();

		for (final Lancamento lancamento : lancamentos) {
			resultado = resultado.soma(lancamento.valor());
		}

		return resultado;
	}

	public Dinheiro saques(final Periodo periodo) {
		Dinheiro resultado = new Dinheiro();

		for (final Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor negativo
			if (periodo.contem(lancamento.data())
					&& lancamento.valor().compareTo(new Dinheiro()) < 0) {
				resultado = resultado.soma(lancamento.valor());
			}
		}

		return resultado;
	}

	public Dinheiro depositos(final Periodo periodo) {
		Dinheiro resultado = new Dinheiro();

		for (final Lancamento lancamento : lancamentos) {
			// veririca também se o lancamento possui um valor positivo
			if (periodo.contem(lancamento.data())
					&& lancamento.valor().compareTo(new Dinheiro()) > 0) {
				resultado = resultado.soma(lancamento.valor());
			}
		}

		return resultado;
	}
}
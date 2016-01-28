package com.github.fabriciofx.rocket.dominio.financeiro;

import java.time.LocalDateTime;

import com.github.fabriciofx.rocket.dominio.financeiro.lancamento.Lancamento;

// A transação é composta de dois lançamentos, cada um de valor
// oposto ao outro, para que as somas destes lançamentos sejam iguais
// a zero.
public final class Transacao {
	public void executa(final LocalDateTime dataHora, final Conta de,
			final Conta para, final Dinheiro quantia) {
		final Lancamento lancamentoDe = new Lancamento(Transacao.class,
				dataHora, quantia.multiplica(-1));
		final Conta novoDe = de.adiciona(lancamentoDe);

		final Lancamento lancamentoPara = new Lancamento(Transacao.class,
				dataHora, quantia);
		final Conta novoPara = para.adiciona(lancamentoPara);
	}
}
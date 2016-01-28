package com.github.fabriciofx.rocket.dominio.financeiro.lancamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.github.fabriciofx.rocket.dominio.financeiro.Dinheiro;

public final class Lancamento {
	private final Class<?> tipo;
	private final LocalDateTime dataHora;
	private final Dinheiro valor;

	public Lancamento(final Class<?> tipo, final Dinheiro valor) {
		this(tipo, LocalDateTime.now(), valor);
	}

	public Lancamento(final Class<?> tipo, final LocalDateTime dataHora,
			final Dinheiro valor) {
		this.tipo = Objects.requireNonNull(tipo,
				"tipo de lançamento inválido (null)");
		this.dataHora = Objects.requireNonNull(dataHora,
				"data/hora do lançamento inválido (null)");
		this.valor = Objects.requireNonNull(valor,
				"valor do lançamento inválido (null)");
	}

	public Class<?> tipo() {
		return tipo;
	}

	public Dinheiro valor() {
		return valor;
	}

	public LocalDateTime dataHora() {
		return dataHora;
	}
	
	public LocalDate data() {
		return dataHora.toLocalDate();
	}
}
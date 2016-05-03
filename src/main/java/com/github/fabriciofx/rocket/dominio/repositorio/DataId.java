package com.github.fabriciofx.rocket.dominio.repositorio;

import java.time.LocalDateTime;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.misc.Aleatorio;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class DataId implements Id, Elemento, Comparable<DataId> {
	private final transient long numero;

	public DataId() {
		this(LocalDateTime.now());
	}

	public DataId(final LocalDateTime dataHora) {
		this(dataHora, new Aleatorio().numero(10, 99));
	}

	public DataId(final LocalDateTime dataHora, final int sequencial) {
		this(localDateTimeParaLong(dataHora) + sequencial);
	}

	public DataId(final long numero) {
		this.numero = numero;
	}

	public LocalDateTime dataHora() {
		final int ano = (int) (numero / 1000000000000000L);
		final int mes = (int) ((numero / 10000000000000L)
				- ((numero / 1000000000000000L) * 100L));
		final int dia = (int) ((numero / 100000000000L)
				- ((numero / 10000000000000L) * 100L));
		final int hora = (int) ((numero / 1000000000L)
				- ((numero / 100000000000L) * 100L));
		final int minuto = (int) ((numero / 10000000L)
				- ((numero / 1000000000L) * 100L));
		final int segundo = (int) ((numero / 100000L)
				- ((numero / 10000000L) * 100L));
		final int nanosegundo = (int) ((numero / 100L)
				- ((numero / 100000L) * 1000L));

		return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo,
				(nanosegundo * 1000000));
	}

	public int sequencial(final long n) {
		return (int) (numero - ((numero / 100L) * 100L));
	}

	// Ano.Mes.Dia.Hora.Minuto.Segundo.Nanosegundo.(RANDOM entre 0 e 99)
	// Exemplo: 2015.12.28.22.52.13.123.45
	@Override
	public long toLong() {
		return numero;
	}

	@Override
	public String toString() {
		return numero + "";
	}

	@Override
	public int compareTo(final DataId dataId) {
		return (int) (numero - dataId.numero);
	}

	@Override
	public int hashCode() {
		return (int) (31 * numero);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof DataId
				&& numero == DataId.class.cast(o).numero;
	}

	private static long localDateTimeParaLong(final LocalDateTime dataHora) {
		final LocalDateTime dh = new RestNaoNulo<LocalDateTime>()
				.valido(dataHora);
		return dh.getYear() * 1000000000000000L
				+ dh.getMonthValue() * 10000000000000L
				+ dh.getDayOfMonth() * 100000000000L
				+ dh.getHour() * 1000000000L
				+ dh.getMinute() * 10000000L
				+ dh.getSecond() * 100000L
				+ (dh.getNano() / 10000L);
	}
}

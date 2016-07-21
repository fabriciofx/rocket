package com.github.fabriciofx.rocket.id;

import java.time.LocalDateTime;

import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class DataId implements Id, Comparable<DataId> {
	private final transient long milisegundos;

	public DataId() {
		this(LocalDateTime.now());
	}

	public DataId(final LocalDateTime dataHora) {
		this(dataHora, new Aleatorio().numero(10, 99));
	}

	public DataId(final LocalDateTime dataHora, final int sequencial) {
		this(localDateTimeParaLong(dataHora) + sequencial);
	}

	public DataId(final long milisegundos) {
		this.milisegundos = milisegundos;
	}

	public LocalDateTime dataHora() {
		final int ano = (int) (milisegundos / 1000000000000000L);
		final int mes = (int) ((milisegundos / 10000000000000L)
				- ((milisegundos / 1000000000000000L) * 100L));
		final int dia = (int) ((milisegundos / 100000000000L)
				- ((milisegundos / 10000000000000L) * 100L));
		final int hora = (int) ((milisegundos / 1000000000L)
				- ((milisegundos / 100000000000L) * 100L));
		final int minuto = (int) ((milisegundos / 10000000L)
				- ((milisegundos / 1000000000L) * 100L));
		final int segundo = (int) ((milisegundos / 100000L)
				- ((milisegundos / 10000000L) * 100L));
		final int nanosegundo = (int) ((milisegundos / 100L)
				- ((milisegundos / 100000L) * 1000L));

		return LocalDateTime.of(ano, mes, dia, hora, minuto, segundo,
				(nanosegundo * 1000000));
	}

	public int sequencial(final long n) {
		return (int) (milisegundos - ((milisegundos / 100L) * 100L));
	}

	// Ano.Mes.Dia.Hora.Minuto.Segundo.Nanosegundo.(RANDOM entre 0 e 99)
	// Exemplo: 2015.12.28.22.52.13.123.45
	@Override
	public long toLong() {
		return milisegundos;
	}

	@Override
	public String toString() {
		return milisegundos + "";
	}

	@Override
	public int compareTo(final DataId dataId) {
		return (int) (milisegundos - dataId.milisegundos);
	}

	@Override
	public int hashCode() {
		return (int) (31 * milisegundos);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof DataId
				&& milisegundos == DataId.class.cast(o).milisegundos;
	}

	private static long localDateTimeParaLong(final LocalDateTime dataHora) {
		final LocalDateTime dh = new NotNull<LocalDateTime>()
				.valid(dataHora);
		return dh.getYear() * 1000000000000000L
				+ dh.getMonthValue() * 10000000000000L
				+ dh.getDayOfMonth() * 100000000000L
				+ dh.getHour() * 1000000000L
				+ dh.getMinute() * 10000000L
				+ dh.getSecond() * 100000L
				+ (dh.getNano() / 10000L);
	}
}

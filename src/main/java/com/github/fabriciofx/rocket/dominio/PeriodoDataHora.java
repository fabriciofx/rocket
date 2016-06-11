package com.github.fabriciofx.rocket.dominio;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class PeriodoDataHora implements Periodo<ChronoLocalDateTime<?>> {
	private final transient LocalDateTime inicio;
	private final transient LocalDateTime termino;

	public PeriodoDataHora(final String dataInicio, final String horaInicio,
			final String dataTermino, final String horaTermino) {
		this(toLocalDateTime(dataInicio, horaInicio),
				toLocalDateTime(dataTermino, horaTermino));
	}

	public PeriodoDataHora(final LocalDateTime inicio,
			final LocalDateTime termino) {
		this.inicio = new RestNaoNulo<LocalDateTime>().valido(inicio);
		this.termino = new RestNaoNulo<LocalDateTime>().valido(termino);
		if (this.inicio.isAfter(this.termino)) {
			throw new IllegalArgumentException(
					"a data/hora de início deve ser anterior a de término");
		}
	}

	public ChronoLocalDateTime<?> inicio() {
		return inicio;
	}

	public ChronoLocalDateTime<?> termino() {
		return termino;
	}

	@Override
	public boolean contem(final ChronoLocalDateTime<?> dataHora) {
		return inicio.compareTo(dataHora) <= 0
				&& termino.compareTo(dataHora) >= 0;
	}

	@Override
	public String toString() {
		final DateTimeFormatter formato = DateTimeFormatter
				.ofLocalizedDateTime(FormatStyle.SHORT);
		return String.format("%s a %s", inicio.format(formato),
				termino.format(formato));
	}

	@Override
	public Media print(Media media) {
		final DateTimeFormatter formato = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
		return media.with("inicio", inicio.format(formato))
				.with("termino", termino.format(formato));
	}

	private static LocalDateTime toLocalDateTime(final String data,
			final String hora) {
		return LocalDateTime.parse(String.format("%s %s", data, hora),
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	}
}

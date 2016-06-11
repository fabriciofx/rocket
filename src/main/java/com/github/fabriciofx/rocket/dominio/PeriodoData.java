package com.github.fabriciofx.rocket.dominio;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public final class PeriodoData implements Periodo<ChronoLocalDate> {
	private final transient LocalDate inicio;
	private final transient LocalDate termino;

	public PeriodoData(final String dataInicio, final String dataTermino) {
		this(toLocalDate(dataInicio), toLocalDate(dataTermino));
	}

	public PeriodoData(final LocalDate inicio, final LocalDate termino) {
		this.inicio = new RestNaoNulo<LocalDate>().valido(inicio);
		this.termino = new RestNaoNulo<LocalDate>().valido(termino);
		if (this.inicio.isAfter(this.termino)) {
			throw new IllegalArgumentException(
					"a data de início deve ser anterior a de término");
		}
	}

	public ChronoLocalDate inicio() {
		return inicio;
	}

	public ChronoLocalDate termino() {
		return termino;
	}

	@Override
	public boolean contem(final ChronoLocalDate data) {
		return inicio.compareTo(data) <= 0 && termino.compareTo(data) >= 0;
	}

	@Override
	public String toString() {
		final DateTimeFormatter formato = DateTimeFormatter
				.ofLocalizedDate(FormatStyle.SHORT);
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

	private static LocalDate toLocalDate(final String data) {
		return LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}

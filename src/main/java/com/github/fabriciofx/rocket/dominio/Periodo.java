package com.github.fabriciofx.rocket.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;

public interface Periodo<T extends Comparable<T>> extends Intervalo<T> {
	T inicio();

	T termino();

	final public class DataHora implements Periodo<ChronoLocalDateTime<?>> {
		private final LocalDateTime inicio;
		private final LocalDateTime termino;

		public DataHora(final String dataInicio, final String horaInicio,
				final String dataTermino, final String horaTermino) {
			this(toLocalDateTime(dataInicio, horaInicio),
					toLocalDateTime(dataTermino, horaTermino));
		}

		public DataHora(final LocalDateTime inicio,
				final LocalDateTime termino) {
			this.inicio = new RestNaoNulo<>(inicio).objeto();
			this.termino = new RestNaoNulo<>(termino).objeto();

			if (this.inicio.isAfter(this.termino)) {
				throw new IllegalArgumentException(
			"a data/hora de início deve ser anterior a data/hora de término");
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

		private static LocalDateTime toLocalDateTime(final String data,
				final String hora) {
			return LocalDateTime.parse(String.format("%s %s", data, hora),
					DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
		}
	}

	final public class Data implements Periodo<ChronoLocalDate> {
		private final LocalDate inicio;
		private final LocalDate termino;

		public Data(final String dataInicio, final String dataTermino) {
			this(toLocalDate(dataInicio), toLocalDate(dataTermino));
		}

		public Data(final LocalDate inicio, final LocalDate termino) {
			this.inicio = new RestNaoNulo<>(inicio).objeto();
			this.termino = new RestNaoNulo<>(termino).objeto();

			if (this.inicio.isAfter(this.termino)) {
				throw new IllegalArgumentException(
			"a data/hora de início deve ser anterior a data/hora de término");
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

		private static LocalDate toLocalDate(final String data) {
			return LocalDate.parse(data,
					DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
	}
}

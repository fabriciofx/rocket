package com.github.fabriciofx.rocket.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.github.fabriciofx.rocket.dominio.intervalo.Intervalo;

public final class Periodo implements Intervalo<ChronoLocalDateTime<?>> {
	private final LocalDateTime inicio;
	private final LocalDateTime termino;

	public Periodo(final String dataInicio, final String horaInicio,
			final String dataTermino, final String horaTermino) {
		this(LocalDateTime.parse(dataInicio + " " + horaInicio,
				DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
				LocalDateTime.parse(dataTermino + " " + horaTermino,
						DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	}

	public Periodo(final String dataInicio, final String dataTermino) {
		this(LocalDate.parse(dataInicio,
				DateTimeFormatter.ofPattern("dd/MM/yyyy")),
				LocalDate.parse(dataTermino,
						DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}

	public Periodo(final LocalDate inicio, final LocalDate termino) {
		this(LocalDateTime.of(inicio, LocalTime.of(0, 0, 0)),
				LocalDateTime.of(termino, LocalTime.of(23, 59, 59)));
	}

	public Periodo(final LocalDate dataInicio, final LocalTime horaInicio,
			final LocalDate dataTermino, final LocalTime horaTermino) {
		this(LocalDateTime.of(dataInicio, horaInicio),
				LocalDateTime.of(dataTermino, horaTermino));
	}

	public Periodo(final LocalDateTime inicio, final LocalDateTime termino) {
		this.inicio = Objects.requireNonNull(inicio,
				"data/hora de início não pode ser NULL");
		this.termino = Objects.requireNonNull(termino,
				"data/hora de término não pode ser NULL");

		if (this.inicio.isAfter(this.termino)) {
			throw new IllegalArgumentException(
					"a data/hora de início deve ser anterior a data/hora de término");
		}
	}

	public LocalDateTime inicio() {
		return inicio;
	}

	public LocalDateTime termino() {
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
				.ofPattern("dd/MM/yyyy HH:mm");

		return inicio.format(formato) + " a " + termino.format(formato);
	}
}

package com.github.fabriciofx.rocket.dominio.gerador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.github.fabriciofx.rocket.misc.Aleatorio;

public final class GeradorDataHora {
	private final Aleatorio aleatorio;

	public GeradorDataHora() {
		aleatorio = new Aleatorio();
	}

	private Date deStringParaDate(final String data) {
		try {
			final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			final Date d = df.parse(data);

			return d;
		} catch (final Exception e) {
			throw new IllegalArgumentException("data inválida!");
		}
	}

	public int getAno(final int min, final int max) {
		return aleatorio.numero(min, max);
	}

	public int getMes(final int min, final int max) {
		return aleatorio.numero(min, max);
	}

	public int getDia(final int min, final int max) {
		return aleatorio.numero(min, max);
	}

	public Date getData(final String min, final String max) {
		final Date dataMin = deStringParaDate(min);
		final Date dataMax = deStringParaDate(max);

		final long data = aleatorio.numero(dataMin.getTime(),
				dataMax.getTime());

		return new Date(data);
	}
}

package com.github.fabriciofx.rocket.dominio.endereco;

import java.io.IOException;

import com.github.fabriciofx.rocket.infra.media.Media;
import com.github.fabriciofx.rocket.infra.media.Printer;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.jcabi.immutable.Array;

public final class SimplesEndereco implements Endereco {
	private final Array<Printer> printers;

	public SimplesEndereco(final Logradouro logradouro, final Numero numero,
			final Complemento complemento, final Bairro bairro,
			final Cidade cidade, final Cep cep) {
		this.printers = new Array<>(
			new RestNaoNulo<Printer>().valido(logradouro),
			new RestNaoNulo<Printer>().valido(numero),
			new RestNaoNulo<Printer>().valido(complemento),
			new RestNaoNulo<Printer>().valido(bairro),
			new RestNaoNulo<Printer>().valido(cidade),
			new RestNaoNulo<Printer>().valido(cep)
		);
	}

	@Override
	public Media print(Media media) throws IOException {
		for (final Printer p : printers) {
			media = p.print(media);
		}
		return media;
	}
}

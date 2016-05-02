package com.github.fabriciofx.rocket.dominio.financeiro;

import java.time.LocalDateTime;

public interface Evento<Origem, Destino> {
	// Quando o evento ocorreu
	LocalDateTime ocorrido();

	// Quando o evento foi observado
	LocalDateTime observado();

	// Quem originou o evento
	Origem origem();

	// Quem recebeu o evento
	Destino destino();
}

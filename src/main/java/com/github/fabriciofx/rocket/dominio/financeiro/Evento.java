package com.github.fabriciofx.rocket.dominio.financeiro;

import java.time.LocalDateTime;

public interface Evento<Origem, Destino> {
	// Quando o evento ocorreu
	public LocalDateTime ocorrido();

	// Quando o evento foi observado
	public LocalDateTime observado();

	// Quem originou o evento
	public Origem origem();

	// Quem recebeu o evento
	public Destino destino();
}

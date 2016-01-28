package com.github.fabriciofx.rocket.dominio.financeiro;

import java.time.LocalDateTime;

public interface Evento<Originario, Alvo> {
	// Quando o evento ocorreu
	public LocalDateTime ocorrido();

	// Quando o evento foi observado
	public LocalDateTime observado();

	// Quem originou o evento
	public Originario originario();

	// Quem recebeu o evento
	public Alvo alvo();
}

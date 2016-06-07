package com.github.fabriciofx.rocket.dominio;

public interface Fone extends Documento {
	enum Operadora {
		TIM, OI, CLARO, AEIOU, GVT, EMBRATEL, TELEFONICA, NEXTEL, VIVO,
		DESCONHECIDO;
	}

	enum Tipo {
		CELULAR, FIXO, RADIO, DESCONHECIDO;
	}
	
	String numero();
	
	void numero(String numero);
	
	Tipo tipo();
	
	void tipo(Tipo tipo);
	
	Operadora operadora();
	
	void operadora(Operadora operadora);
}

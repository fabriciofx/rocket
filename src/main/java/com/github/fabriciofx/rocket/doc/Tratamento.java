package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.media.Media;

public enum Tratamento implements Documento {
	// http://www.academia.org.br/nossa-lingua/reducoes
	VOCE("V."),
	VOCES("V.V."),
	SENHOR("Sr."),
	SENHORES("Srs."),
	SENHORA("Sr.a."),
	SENHORAS("Sr.as."),
	SENHORIA("V.Sa."),
	SENHORIAS("V.Sas."),
	SENHORITA("S.ta"),
	EXCELENCIA("V.Exa."),
	EXCELENCIAS("V.Exas."),
	EMINENCIA("V.Ema."),
	EMINENCIAS("V.Emas."),
	ALTEZA("V.A."),
	ALTEZAS("VV.AA."),
	SANTIDADE("V.S."),
	REVERENDISSIMA("Rev.ma."),
	REVERENDISSIMAS("Rev.mas."),
	PATERNINADE("V.P."),
	PATERNINADES("VV.PP."),
	MAGNIFICENCIA("V.Maga."),
	MAGNIFICENCIAS("V.Magas."),
	MAJESTADE("V.M."),
	MAJESTADES("VV.MM."),
	MAJESTADE_IMPERIAL("V.M.I."),
	MAJESTADES_IMPERIAIS("VV.MM.II."),
	ONIPOTECIA("V.O.");

	private final String abreviatura;

	Tratamento(final String abreviatura) {
		this.abreviatura = abreviatura;
	}

	@Override
	public String toString() {
		return new NotEmpty<String>(
			new NotNull<>()
		).valid(abreviatura);
	}

	@Override
	public Media print(final Media media) {
		return media.with("tratamento", toString());
	}	
}

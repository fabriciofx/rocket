package com.github.fabriciofx.rocket.doc;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.doc.endereco.Estado;
import com.github.fabriciofx.rocket.media.Media;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class Rg implements Documento {
	public enum Emissor {
		ABNC, CGPI, CGPMAF, CNIG, CNT, COREN, CORECON, CRA, CRAS, CRB, CRC, CRE,
		CREA, CRECI, CREFIT, CRF, CRM, CRMV, CRN, CRO, CRP, CRPRE, CRQ, CRRC,
		CSC, CTPS, DETRAN, DIC, DIREX, DPF, DPMAF, DPT, DST, DUREX, EST, FGTS,
		FIPE, FLS, GOV_GO, I, IFP, IGP, IICCECF_RO, IIMG, IML, IPC, IPF, ITEP,
		I_CLA, MAE, MEX, MMA, OAB, OMB, PCMG, PMMG, POF, POM, SDS, SECC, SEJUSP,
		SES, SESP, SJS, SJTC, SJTS, SNJ, SPTC, SSP, OUTRO;
	}

	private final String numero;
	private final Emissor emissor;
	private final Estado estado;
	private final int via;

	public Rg(final String numero) {
		this(numero.split(" ")[0], Emissor.SSP, Estado.PB, 1);
	}

	public Rg(final String numero, final Emissor emissor, final Estado estado,
		final int via) {
		this.numero = numero;
		this.emissor = emissor;
		this.estado = estado;
		this.via = via;
	}

	@Override
	public String toString() {
		final String v = via > 1 ? " - " + via + "ª via" : "";
		return String.format(
			"%s %s-%s %s",
			new Pattern<String>(
				new NotEmpty<>(
					new NotNull<>()
				),
				"[0-9]+"
			).valid(numero).toUpperCase(),
			new NotNull<Emissor>().valid(emissor),
			new NotNull<Estado>().valid(estado),
			v
		).trim();
	}

	@Override
	public Media print(final Media media) {
		return media.with("rg", toString());
	}
}

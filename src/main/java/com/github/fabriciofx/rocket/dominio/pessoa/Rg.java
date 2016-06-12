package com.github.fabriciofx.rocket.dominio.pessoa;

import java.time.LocalDate;

import com.github.fabriciofx.rocket.constraint.NotEmpty;
import com.github.fabriciofx.rocket.constraint.NotNull;
import com.github.fabriciofx.rocket.constraint.Pattern;
import com.github.fabriciofx.rocket.dominio.Documento;
import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.media.Media;

public final class Rg implements Documento {
	public enum Emissor {
		ABNC, CGPI, CGPMAF, CNIG, CNT, COREN, CORECON, CRA, CRAS, CRB, CRC, CRE,
		CREA, CRECI, CREFIT, CRF, CRM, CRMV, CRN, CRO, CRP, CRPRE, CRQ, CRRC,
		CSC, CTPS, DETRAN, DIC, DIREX, DPF, DPMAF, DPT, DST, DUREX, EST, FGTS,
		FIPE, FLS, GOV_GO, I, IFP, IGP, IICCECF_RO, IIMG, IML, IPC, IPF, ITEP,
		I_CLA, MAE, MEX, MMA, OAB, OMB, PCMG, PMMG, POF, POM, SDS, SECC, SEJUSP,
		SES, SESP, SJS, SJTC, SJTS, SNJ, SPTC, SSP, OUTRO;
	}

	private final transient String numero;
	private final transient Emissor emissor;
	private final transient Estado estado;
	private final transient int via;
	private final transient LocalDate expedicao;

	public Rg(final String numero) {
		this(numero.split(" ")[0], Emissor.SSP, Estado.PB, 1, LocalDate.now());
	}

	public Rg(final String numero, final Emissor emissor, final Estado estado,
			final int via, final LocalDate expedicao) {
		this.numero = new Pattern<String>(
				new NotEmpty<>(new NotNull<>()), "[0-9]+")
						.valid(numero).toUpperCase();
		this.emissor = new NotNull<Emissor>().valid(emissor);
		this.estado = new NotNull<Estado>().valid(estado);
		this.via = via;
		this.expedicao = new NotNull<LocalDate>().valid(expedicao);
	}

	@Override
	public String toString() {
		final String v = via > 1 ? " - " + via + "ª via" : "";
		return String.format("%s %s-%s %s", numero, emissor, estado, v);
	}

	@Override
	public Media print(Media media) {
		final String rg;
		if (via == 1) {
			rg = String.format("%s %s-%s", numero, emissor, estado);
		} else {
			rg = String.format("%s %s-%s %dª via", numero, emissor, estado,
				via);
		}
		return media.with("rg", rg);
	}
	
	private Media xml(Media media) {
		media = media.with("rg-numero", numero)
			.with("rg-emissor", emissor.toString())
			.with("rg-estado", estado.toString())
			.with("rg-expedicao", expedicao.toString());
		if (via > 1) {
			media = media.with("rg-via", Integer.toString(via));
		}
		return media;
	}
}

package com.github.fabriciofx.rocket.dominio;

import java.time.LocalDate;

import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Rg {
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
	private final String estado;
	private final int via;
	private final LocalDate expedicao;

	public Rg(final String numero) {
		this(numero, Emissor.SSP, "PB", 1, null);
	}

	public Rg(final String numero, final Emissor emissor, final String estado,
			final int via, final LocalDate expedicao) {
		this.numero = new RestPadrao<String>(
				new RestNaoVazia<>(new RestNaoNulo<>(numero)), "[0-9]+")
						.objeto().toUpperCase();
		this.emissor = new RestNaoNulo<Emissor>(emissor).objeto();
		this.estado = new RestNaoVazia<String>(new RestNaoNulo<>(estado))
				.objeto().toUpperCase();
		this.via = via;
		this.expedicao = new RestNaoNulo<LocalDate>(expedicao).objeto();
	}

	public String numero() {
		return numero;
	}

	public Emissor emissor() {
		return emissor;
	}

	public String estado() {
		return estado;
	}

	public int via() {
		return via;
	}

	public LocalDate expedicao() {
		return expedicao;
	}

	@Override
	public String toString() {
		return numero + " " + emissor.toString() + "/" + estado
				+ (via > 1 ? " - " + via + "ª via" : "");
	}
}

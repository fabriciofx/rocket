package com.github.fabriciofx.rocket.dominio.pessoa;

import java.io.Serializable;
import java.time.LocalDate;

import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Rg implements Serializable {
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
	private final LocalDate expedicao;

	public Rg(final String numero) {
		this(numero, Emissor.SSP, Estado.PB, 1, LocalDate.now());
	}

	public Rg(final String numero, final Emissor emissor, final Estado estado,
			final int via, final LocalDate expedicao) {
		this.numero = new RestPadrao<>(
				new RestNaoVazia<>(new RestNaoNulo<>(numero)), "[0-9]+")
						.objeto().toUpperCase();
		this.emissor = new RestNaoNulo<>(emissor).objeto();
		this.estado = new RestNaoNulo<>(estado).objeto();
		this.via = via;
		this.expedicao = new RestNaoNulo<>(expedicao).objeto();
	}

	public String numero() {
		return numero;
	}

	public Emissor emissor() {
		return emissor;
	}

	public Estado estado() {
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
		final String v = via > 1 ? " - " + via + "ª via" : "";
		return String.format("%s %s/%s %s", numero, emissor, estado, v).trim();
	}
}

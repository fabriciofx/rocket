package com.github.fabriciofx.rocket.dominio.pessoa;

import java.time.LocalDate;

import com.github.fabriciofx.rocket.dominio.Elemento;
import com.github.fabriciofx.rocket.dominio.endereco.Estado;
import com.github.fabriciofx.rocket.restricao.RestNaoNulo;
import com.github.fabriciofx.rocket.restricao.RestNaoVazia;
import com.github.fabriciofx.rocket.restricao.RestPadrao;

public final class Rg implements Elemento {
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
		this(numero, Emissor.SSP, Estado.PB, 1, LocalDate.now());
	}

	public Rg(final String numero, final Emissor emissor, final Estado estado,
			final int via, final LocalDate expedicao) {
		this.numero = new RestPadrao<String>(
				new RestNaoVazia<>(new RestNaoNulo<>()), "[0-9]+")
						.valido(numero).toUpperCase();
		this.emissor = new RestNaoNulo<Emissor>().valido(emissor);
		this.estado = new RestNaoNulo<Estado>().valido(estado);
		this.via = via;
		this.expedicao = new RestNaoNulo<LocalDate>().valido(expedicao);
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

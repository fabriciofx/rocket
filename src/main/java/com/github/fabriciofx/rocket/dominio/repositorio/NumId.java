package com.github.fabriciofx.rocket.dominio.repositorio;

public final class NumId implements Id, Comparable<NumId> {
	private final transient long numero;

	public NumId(final long numero) {
		this.numero = numero;
	}

	@Override
	public int compareTo(final NumId numId) {
		return (int) (numero - numId.numero);
	}

	@Override
	public int hashCode() {
		return (int) (31 * numero);
	}

	@Override
	public boolean equals(final Object o) {
		return o != null && o instanceof NumId
				&& numero == NumId.class.cast(o).numero;
	}

	@Override
	public long toLong() {
		return numero;
	}
	
	@Override
	public String toString() {
		return Long.toString(numero);
	}
}

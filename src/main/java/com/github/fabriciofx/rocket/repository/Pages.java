package com.github.fabriciofx.rocket.repository;

public final class Pages<T> {
	private final transient Paginable<T> paginable;
	private final transient long quantity;

	public Pages(final Paginable<T> paginable, final long quantity) {
		this.paginable = paginable;
		this.quantity = quantity;
	}

	public long total() {
		final long total = paginable.total();
		return total / quantity + (total % quantity == 0L ? 0L : 1L);
	}

	public Page<T> first() {
		return new Page<T>(paginable.itens(0L, quantity), 1L);
	}

	public Page<T> last() {
		final long total = total();
		final long from = total <= 0L ? 0L : (total - 1L) * quantity;
		return new Page<T>(paginable.itens(from, quantity), total);
	}

	public Page<T> page(final long num) {
		final Page<T> page;
		if (num < 1L) {
			page = first();
		}
		else if (num > total()) {
			page = last();
		}
		else {
			final long from = (num - 1L) * quantity;
			page = new Page<T>(paginable.itens(from, quantity), num);
		}
		return page;
	}
}

package com.github.fabriciofx.rocket.constraint;

public interface Constraint<T> {
	T valid(T object);
}

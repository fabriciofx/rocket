package com.github.fabriciofx.rocket.number;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class NumbersFromMem implements Numbers {
	private List<Number> nums;
	
	public NumbersFromMem(final Number... nums) {
		this(Arrays.asList(nums));
	}
	
	public NumbersFromMem(final List<Number> nums) {
		this.nums = nums;
	}
	
	@Override
	public List<Number> all() {
		return Collections.unmodifiableList(this.nums);
	}
}

package com.hx.designpatterns.behavor.strategy;

public class Genaral extends Common {
	public Genaral(String name, Double salary) {
		super(name, salary);
	}

	@Override
	public double bonus(double salary) {
		return salary * 0.1;
	}

}

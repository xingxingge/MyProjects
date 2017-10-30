package com.hx.designpatterns.behavor.strategy;

public class Excellent extends Common {
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Excellent(String name, Double salary) {
		super(name, salary);
	}

	@Override
	public double bonus(double salary) {
		return salary * 0.5;
	}

}

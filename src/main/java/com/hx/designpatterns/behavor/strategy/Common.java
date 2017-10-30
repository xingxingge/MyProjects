package com.hx.designpatterns.behavor.strategy;

public abstract class Common implements Strategy {
	public String name;
	public Double salary;

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

	public Common(String name,Double salary) {
		this.name= name;
		this.salary=salary;
	}

	@Override
	public abstract double bonus(double salary);

}

package com.hx.designpatterns.behavor.strategy;

//奖金类
public class AllBonus {
	
	private Strategy strategy;

	public AllBonus(Strategy strategy) {
		this.strategy=strategy;
	}
	
	public Double myBonus(Double salary){
		return this.strategy.bonus(salary);
	}

}

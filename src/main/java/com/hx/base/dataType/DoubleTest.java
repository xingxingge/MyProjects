package com.hx.base.dataType;
import java.text.DecimalFormat;


public class DoubleTest {
	public static void main(String[] args) {
		String str = "+18.002";
		System.out.println(parsePoints1(str));
		
	}
	
	public static  String parsePoints(String points) {

		double point = Double.parseDouble(points);
		DecimalFormat r = new DecimalFormat();
		r.applyPattern("#00000000");// 保留小数位数，不足会补零
		return r.format(point*100);
	}
	
	public static String parsePoints1(String points){

		double point = Double.parseDouble(points);
		double result = Math.floor(point)
				* 10000
				+ Math.floor((point - Math.floor(point)) * 60)
				* 100
				+ ((point - Math.floor(point)) * 60 - Math.floor((point - Math
						.floor(point)) * 60)) * 60;
		DecimalFormat r = new DecimalFormat();
		r.applyPattern("#000000000");// 保留小数位数，不足会补零
		return r.format(result*100);
	}


}

package com.hx.db;

import java.util.ArrayList;

public class JdbcDaoTest {

	public static void queryByIDTest() {
		String id = "OuT9R22_T7-gSt9aCZB5gw";
		FusionAlertDaoImpl fusionAlertDaoImpl = new FusionAlertDaoImpl();
		FusionAlert fusionAlert = fusionAlertDaoImpl.findById(id);
		System.out.println(fusionAlert);
	}

	public static void queryAllTest() {
		FusionAlertDaoImpl fusionAlertDaoImpl = new FusionAlertDaoImpl();
		ArrayList<FusionAlert> list = fusionAlertDaoImpl.findAll();
		for (FusionAlert fusionAlert : list) {
			System.out.printf("%-30s", fusionAlert.getRes_id());
			System.out.printf("%-30s", fusionAlert.getReceive_time());
			System.out.printf("%-120s", fusionAlert.getAlert_info());
			System.out.printf("%-30s", fusionAlert.getAlert_source());
			System.out.println("");
		}
	}

	public static void main(String[] args) {
		queryByIDTest();
		queryAllTest();
	}
}

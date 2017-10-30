package com.hx.db;

public class FusionAlert {
	private String res_id;
	private String receive_time;
	private String alert_info;
	private String alert_source;

	public String getRes_id() {
		return res_id;
	}

	public void setRes_id(String res_id) {
		this.res_id = res_id;
	}

	public String getReceive_time() {
		return receive_time;
	}

	public void setReceive_time(String receive_time) {
		this.receive_time = receive_time;
	}

	public String getAlert_info() {
		return alert_info;
	}

	public void setAlert_info(String alert_info) {
		this.alert_info = alert_info;
	}

	public String getAlert_source() {
		return alert_source;
	}

	public void setAlert_source(String alert_source) {
		this.alert_source = alert_source;
	}

	@Override
	public String toString() {
		return "FusionAlert [res_id=" + res_id + ", receive_time="
				+ receive_time + ", alert_info=" + alert_info
				+ ", alert_source=" + alert_source + "]";
	}

}

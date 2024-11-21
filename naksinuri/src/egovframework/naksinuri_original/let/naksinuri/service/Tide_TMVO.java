package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class Tide_TMVO {
	private String solar_date;
	private String moon_date;
	private String tide_time1;
	private String tide_time2;
	private String tide_time3;
	
	
	public String getSolar_date() {
		return solar_date;
	}
	public void setSolar_date(String solar_date) {
		this.solar_date = solar_date;
	}
	public String getMoon_date() {
		return moon_date;
	}
	public void setMoon_date(String moon_date) {
		this.moon_date = moon_date;
	}
	public String getTide_time1() {
		return tide_time1;
	}
	public void setTide_time1(String tide_time1) {
		this.tide_time1 = tide_time1;
	}
	public String getTide_time2() {
		return tide_time2;
	}
	public void setTide_time2(String tide_time2) {
		this.tide_time2 = tide_time2;
	}
	public String getTide_time3() {
		return tide_time3;
	}
	public void setTide_time3(String tide_time3) {
		this.tide_time3 = tide_time3;
	}
	
	
}

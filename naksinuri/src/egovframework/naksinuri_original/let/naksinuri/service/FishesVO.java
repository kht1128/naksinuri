package egovframework.naksinuri_original.let.naksinuri.service;

import org.springframework.stereotype.Component;

@Component
public class FishesVO {

	private int fish_no;
	private String fishing_type;
	private String fish_name;
	private String fish_img_name;
	
	public int getFish_no() {
		return fish_no;
	}
	public void setFish_no(int fish_no) {
		this.fish_no = fish_no;
	}
	public String getFishing_type() {
		return fishing_type;
	}
	public void setFishing_type(String fishing_type) {
		this.fishing_type = fishing_type;
	}
	public String getFish_name() {
		return fish_name;
	}
	public void setFish_name(String fish_name) {
		this.fish_name = fish_name;
	}
	public String getFish_img_name() {
		return fish_img_name;
	}
	public void setFish_img_name(String fish_img_name) {
		this.fish_img_name = fish_img_name;
	}
}

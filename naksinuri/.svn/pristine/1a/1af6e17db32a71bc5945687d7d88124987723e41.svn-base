package egovframework.naksinuri_original.let.naksinuri.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class NaksinuriSmsVO extends CommonVO {
	
	//문자발송번호 세팅
	public String getSmsSendNumber() {
		return "18337139";
	}
	
	//rstkey 생성
	public String getUniqRstKey() {
		Date today = new Date();
	    SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
	    return date.format(today) + getRandomString(4);
	}
	private static String getRandomString(int length) {
	    StringBuffer buffer = new StringBuffer();
	    Random random = new Random();
	    random.setSeed(System.currentTimeMillis());
	    String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
	    for (int i = 0; i < length; i++) {
	      buffer.append(chars[random.nextInt(chars.length)]);
	    }
	    return buffer.toString();
	}
	
	
	public String tablename;//문자모듈의 테이블명 지정
	
	//SMS_QUEUE table
	public int mid;
	public int is_incron;//스케줄러에 진입 여부 : 0,1
	public String apikey;//발송자고유키값
	public String rstkey;//문자검증고유키값
	public String rst_code;//발송상태값
	public int rst;//0:대기,1:성공,2:실패,4:실패(잔액부족)
	public int stat;//0:대기,1:발송완료,2:발송실패,3:결과리턴,4:발송실패(잔액부족)
	public String send_date;//발송시간
	public String upd_date;//변경날짜
	public String reg_date;//생성날짜
	public String msg_type;//SMS,LMS,MMS
	public String r_phone;//받는사람 번호
	public String s_phone;//보내는사람 번호
	public String ip;//문자 발송 시도 IP
	public String submsg;//문자 - 제목
	public String msg;//문자 - 내용
	public int img_cnt;//MMS 전송시 이미지 파일 갯수
	public String img_path;//이미지 파일 경로 ( 다수인 경우 구분자는 ';' )
	public int m_point;//발송시 차감될 문자 포인트(SMS,LMS,MMS 별로 다름)
	public int c_point;//나의 현재 보유 포인트
	
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getIs_incron() {
		return is_incron;
	}
	public void setIs_incron(int is_incron) {
		this.is_incron = is_incron;
	}
	public String getApikey() {
		return apikey;
	}
	public void setApikey(String apikey) {
		this.apikey = apikey;
	}
	public String getRstkey() {
		return rstkey;
	}
	public void setRstkey(String rstkey) {
		this.rstkey = rstkey;
	}
	public String getRst_code() {
		return rst_code;
	}
	public void setRst_code(String rst_code) {
		this.rst_code = rst_code;
	}
	public int getRst() {
		return rst;
	}
	public void setRst(int rst) {
		this.rst = rst;
	}
	public int getStat() {
		return stat;
	}
	public void setStat(int stat) {
		this.stat = stat;
	}
	public String getSend_date() {
		return send_date;
	}
	public void setSend_date(String send_date) {
		this.send_date = send_date;
	}
	public String getUpd_date() {
		return upd_date;
	}
	public void setUpd_date(String upd_date) {
		this.upd_date = upd_date;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(String msg_type) {
		this.msg_type = msg_type;
	}
	public String getR_phone() {
		return r_phone;
	}
	public void setR_phone(String r_phone) {
		this.r_phone = r_phone;
	}
	public String getS_phone() {
		return s_phone;
	}
	public void setS_phone(String s_phone) {
		this.s_phone = s_phone;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getSubmsg() {
		return submsg;
	}
	public void setSubmsg(String submsg) {
		this.submsg = submsg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getImg_cnt() {
		return img_cnt;
	}
	public void setImg_cnt(int img_cnt) {
		this.img_cnt = img_cnt;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	public int getM_point() {
		return m_point;
	}
	public void setM_point(int m_point) {
		this.m_point = m_point;
	}
	public int getC_point() {
		return c_point;
	}
	public void setC_point(int c_point) {
		this.c_point = c_point;
	}
	
	
	
	
}

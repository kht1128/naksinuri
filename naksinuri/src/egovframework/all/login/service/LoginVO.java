package egovframework.all.login.service;

import java.io.Serializable;

public class LoginVO implements Serializable{
	
	private String MBR_SN;//bigint(20) NOT NULL일련번호
	private String MBR_CD;//varchar(50) NULL회원코드
	private String MBR_ID;//varchar(50) NULL아이디
	private String MBR_PWD;//varchar(250) NULLㅂㅁ
	private String MBR_NM;//varchar(50) NULL이름
	private String MBR_NCNM;//varchar(50) NULL닉네임
	private String MBR_TEL;//varchar(20) NULL전화번호
	private String MBR_HP;//varchar(14) NULL휴대폰
	private String MBR_EMAIL;//varchar(40) NULL이메일
	private String MBR_ADDR1;//varchar(100) NULL주소1
	private String MBR_ADDR2;//varchar(100) NULL주소2
	private String MBR_ZIPCD;//varchar(7) NULL우편번호
	private String MBR_LAST_CON_TM;//datetime NULL접속 시간
	private String MBR_LAST_CON_IPADDR;//varchar(30) NULL접속 아이피
	private String MBR_REG_DT;//datetime NULL가입일
	private String MBR_MOD_DT;//datetime NULL수정일
	private String MBR_TYPE_CD;//varchar(5) NULL유형코드
	private String MBR_BIRTH;//varchar(20) NULL생년월일
	private String MBR_SEX;//tinyint(4) NULL1:남, 2:여
	private String MBR_HOPE_ZONE;//varchar(256) NULL희망지역
	private String MBR_HOPE_BUSINESS;//varchar(256) NULL희망업종
	private String MBR_ST;//enum('Y','N') NULLY:사용, N:삭제
	private String MBR_LV_ID;//int(11) NULL권한레벨
	private String MBR_POSITION_CD;//varchar(10) NULL직급코드[CID00003]=>REG_TYPE_CD
	private String MBR_GRP_1_ST;//enum('Y','N') NULL낚시누리 권한허용여부
	private String MBR_GRP_2_ST;//enum('Y','N') NULL낚시전문교육 권한허용여부
	private String MBR_GRP_3_ST;//enum('Y','N') NULL빈 권한허용여부
	private String MBR_GRP_4_ST;//enum('Y','N') NULL빈 권한허용여부
	private String MBR_JOIN;//varchar(20) NULL회원가입타입
	private String MBR_HMBR_ONLINE_SN;//varchar(50) NULL온라인교육이수코드
	private String MBR_HMBR_ONLINE_ST;//enum('Y','N') NULL온라인교육이수여부
	private String MBR_RETRY_LOGIN_DT;//datetime NULL로그인시도일자
	private String MBR_RETRY_LOGIN_CNT;//int(11) NULL로그인시도횟수
	private String REG_MBR_ID;//varchar(50) NULL작성자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String MBR_PSITN_NM;//varchar(250) NULL(관리자)소속명 또는 노출명
	private String MBR_SIDO_CD;//varchar(20) NULL(관리자)시도 지역 제한코드
	private String MBR_SIGNGU_CD;//varchar(20) NULL(관리자)시군구 지역 제한코드
	private String MBR_EDU_INS_CD;//varchar(20) NULL(관리자)교육기관 제한코드
	private String MBR_TRGT_CD;//varchar(20) NULL(관리자)교육대상 제한코드
	private String MBR_PWD_ST;//char(1) NULL(관리자)ㅂㅁ상태값(0:변경완료,1:최초)
	private String MBR_PWD_DT;//datetime NULLㅂㅁ 변경일자
	private String MBR_DSCRP;//text NULL설명
	private String MBR_PLEDGE_ST;//관리자 서약서 동의여부
	
	// DB 외
	private String MBR_GRP_ID;//그룹구분자
	private String MBR_ADDR;//주소 조합
	private String MBR_SOCIAL_TOKEN;//소셜로그인 토큰키
	private String MBR_USG_DT;//사용기간
	private String MBR_PWD_CHK;//비밀번호확인
		
	
	
	public String getMBR_PLEDGE_ST() {
		return MBR_PLEDGE_ST;
	}
	public void setMBR_PLEDGE_ST(String mBR_PLEDGE_ST) {
		MBR_PLEDGE_ST = mBR_PLEDGE_ST;
	}
	public String getMBR_USG_DT() {
		return MBR_USG_DT;
	}
	public void setMBR_USG_DT(String mBR_USG_DT) {
		MBR_USG_DT = mBR_USG_DT;
	}
	public String getMBR_DSCRP() {
		return MBR_DSCRP;
	}
	public void setMBR_DSCRP(String mBR_DSCRP) {
		MBR_DSCRP = mBR_DSCRP;
	}
	public String getMBR_PWD_DT() {
		return MBR_PWD_DT;
	}
	public void setMBR_PWD_DT(String mBR_PWD_DT) {
		MBR_PWD_DT = mBR_PWD_DT;
	}
	public String getMBR_PWD_ST() {
		return MBR_PWD_ST;
	}
	public void setMBR_PWD_ST(String mBR_PWD_ST) {
		MBR_PWD_ST = mBR_PWD_ST;
	}
	public String getMBR_CD() {
		return MBR_CD;
	}
	public void setMBR_CD(String mBR_CD) {
		MBR_CD = mBR_CD;
	}
	public String getREG_MBR_ID() {
		return REG_MBR_ID;
	}
	public void setREG_MBR_ID(String rEG_MBR_ID) {
		REG_MBR_ID = rEG_MBR_ID;
	}
	public String getUPD_MBR_ID() {
		return UPD_MBR_ID;
	}
	public void setUPD_MBR_ID(String uPD_MBR_ID) {
		UPD_MBR_ID = uPD_MBR_ID;
	}
	public String getMBR_PSITN_NM() {
		return MBR_PSITN_NM;
	}
	public void setMBR_PSITN_NM(String mBR_PSITN_NM) {
		MBR_PSITN_NM = mBR_PSITN_NM;
	}
	public String getMBR_SIDO_CD() {
		return MBR_SIDO_CD;
	}
	public void setMBR_SIDO_CD(String mBR_SIDO_CD) {
		MBR_SIDO_CD = mBR_SIDO_CD;
	}
	public String getMBR_SIGNGU_CD() {
		return MBR_SIGNGU_CD;
	}
	public void setMBR_SIGNGU_CD(String mBR_SIGNGU_CD) {
		MBR_SIGNGU_CD = mBR_SIGNGU_CD;
	}
	public String getMBR_EDU_INS_CD() {
		return MBR_EDU_INS_CD;
	}
	public void setMBR_EDU_INS_CD(String mBR_EDU_INS_CD) {
		MBR_EDU_INS_CD = mBR_EDU_INS_CD;
	}
	public String getMBR_TRGT_CD() {
		return MBR_TRGT_CD;
	}
	public void setMBR_TRGT_CD(String mBR_TRGT_CD) {
		MBR_TRGT_CD = mBR_TRGT_CD;
	}
	public String getMBR_NCNM() {
		return MBR_NCNM;
	}
	public void setMBR_NCNM(String mBR_NCNM) {
		MBR_NCNM = mBR_NCNM;
	}
	public String getMBR_ADDR() {
		return MBR_ADDR;
	}
	public void setMBR_ADDR(String mBR_ADDR) {
		MBR_ADDR = mBR_ADDR;
	}
	public String getMBR_SOCIAL_TOKEN() {
		return MBR_SOCIAL_TOKEN;
	}
	public void setMBR_SOCIAL_TOKEN(String mBR_SOCIAL_TOKEN) {
		MBR_SOCIAL_TOKEN = mBR_SOCIAL_TOKEN;
	}
	public String getMBR_SN() {
		return MBR_SN;
	}
	public void setMBR_SN(String mBR_SN) {
		MBR_SN = mBR_SN;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getMBR_PWD() {
		return MBR_PWD;
	}
	public void setMBR_PWD(String mBR_PWD) {
		MBR_PWD = mBR_PWD;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_TEL() {
		return MBR_TEL;
	}
	public void setMBR_TEL(String mBR_TEL) {
		MBR_TEL = mBR_TEL;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_EMAIL() {
		return MBR_EMAIL;
	}
	public void setMBR_EMAIL(String mBR_EMAIL) {
		MBR_EMAIL = mBR_EMAIL;
	}
	public String getMBR_ADDR1() {
		return MBR_ADDR1;
	}
	public void setMBR_ADDR1(String mBR_ADDR1) {
		MBR_ADDR1 = mBR_ADDR1;
	}
	public String getMBR_ADDR2() {
		return MBR_ADDR2;
	}
	public void setMBR_ADDR2(String mBR_ADDR2) {
		MBR_ADDR2 = mBR_ADDR2;
	}
	public String getMBR_ZIPCD() {
		return MBR_ZIPCD;
	}
	public void setMBR_ZIPCD(String mBR_ZIPCD) {
		MBR_ZIPCD = mBR_ZIPCD;
	}
	public String getMBR_LAST_CON_TM() {
		return MBR_LAST_CON_TM;
	}
	public void setMBR_LAST_CON_TM(String mBR_LAST_CON_TM) {
		MBR_LAST_CON_TM = mBR_LAST_CON_TM;
	}
	public String getMBR_LAST_CON_IPADDR() {
		return MBR_LAST_CON_IPADDR;
	}
	public void setMBR_LAST_CON_IPADDR(String mBR_LAST_CON_IPADDR) {
		MBR_LAST_CON_IPADDR = mBR_LAST_CON_IPADDR;
	}
	public String getMBR_REG_DT() {
		return MBR_REG_DT;
	}
	public void setMBR_REG_DT(String mBR_REG_DT) {
		MBR_REG_DT = mBR_REG_DT;
	}
	public String getMBR_MOD_DT() {
		return MBR_MOD_DT;
	}
	public void setMBR_MOD_DT(String mBR_MOD_DT) {
		MBR_MOD_DT = mBR_MOD_DT;
	}
	public String getMBR_POSITION_CD() {
		return MBR_POSITION_CD;
	}
	public void setMBR_POSITION_CD(String mBR_POSITION_CD) {
		MBR_POSITION_CD = mBR_POSITION_CD;
	}
	public String getMBR_TYPE_CD() {
		return MBR_TYPE_CD;
	}
	public void setMBR_TYPE_CD(String mBR_TYPE_CD) {
		MBR_TYPE_CD = mBR_TYPE_CD;
	}
	public String getMBR_GRP_ID() {
		return MBR_GRP_ID;
	}
	public void setMBR_GRP_ID(String mBR_GRP_ID) {
		MBR_GRP_ID = mBR_GRP_ID;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getMBR_SEX() {
		return MBR_SEX;
	}
	public void setMBR_SEX(String mBR_SEX) {
		MBR_SEX = mBR_SEX;
	}
	public String getMBR_HOPE_ZONE() {
		return MBR_HOPE_ZONE;
	}
	public void setMBR_HOPE_ZONE(String mBR_HOPE_ZONE) {
		MBR_HOPE_ZONE = mBR_HOPE_ZONE;
	}
	public String getMBR_HOPE_BUSINESS() {
		return MBR_HOPE_BUSINESS;
	}
	public void setMBR_HOPE_BUSINESS(String mBR_HOPE_BUSINESS) {
		MBR_HOPE_BUSINESS = mBR_HOPE_BUSINESS;
	}
	public String getMBR_ST() {
		return MBR_ST;
	}
	public void setMBR_ST(String mBR_ST) {
		MBR_ST = mBR_ST;
	}
	public String getMBR_LV_ID() {
		return MBR_LV_ID;
	}
	public void setMBR_LV_ID(String mBR_LV_ID) {
		MBR_LV_ID = mBR_LV_ID;
	}
	public String getMBR_GRP_1_ST() {
		return MBR_GRP_1_ST;
	}
	public void setMBR_GRP_1_ST(String mBR_GRP_1_ST) {
		MBR_GRP_1_ST = mBR_GRP_1_ST;
	}
	public String getMBR_GRP_2_ST() {
		return MBR_GRP_2_ST;
	}
	public void setMBR_GRP_2_ST(String mBR_GRP_2_ST) {
		MBR_GRP_2_ST = mBR_GRP_2_ST;
	}
	public String getMBR_GRP_3_ST() {
		return MBR_GRP_3_ST;
	}
	public void setMBR_GRP_3_ST(String mBR_GRP_3_ST) {
		MBR_GRP_3_ST = mBR_GRP_3_ST;
	}
	public String getMBR_GRP_4_ST() {
		return MBR_GRP_4_ST;
	}
	public void setMBR_GRP_4_ST(String mBR_GRP_4_ST) {
		MBR_GRP_4_ST = mBR_GRP_4_ST;
	}
	public String getMBR_JOIN() {
		return MBR_JOIN;
	}
	public void setMBR_JOIN(String mBR_JOIN) {
		MBR_JOIN = mBR_JOIN;
	}
	public String getMBR_HMBR_ONLINE_SN() {
		return MBR_HMBR_ONLINE_SN;
	}
	public void setMBR_HMBR_ONLINE_SN(String mBR_HMBR_ONLINE_SN) {
		MBR_HMBR_ONLINE_SN = mBR_HMBR_ONLINE_SN;
	}
	public String getMBR_HMBR_ONLINE_ST() {
		return MBR_HMBR_ONLINE_ST;
	}
	public void setMBR_HMBR_ONLINE_ST(String mBR_HMBR_ONLINE_ST) {
		MBR_HMBR_ONLINE_ST = mBR_HMBR_ONLINE_ST;
	}
	public String getMBR_RETRY_LOGIN_DT() {
		return MBR_RETRY_LOGIN_DT;
	}
	public void setMBR_RETRY_LOGIN_DT(String mBR_RETRY_LOGIN_DT) {
		MBR_RETRY_LOGIN_DT = mBR_RETRY_LOGIN_DT;
	}
	public String getMBR_RETRY_LOGIN_CNT() {
		return MBR_RETRY_LOGIN_CNT;
	}
	public void setMBR_RETRY_LOGIN_CNT(String mBR_RETRY_LOGIN_CNT) {
		MBR_RETRY_LOGIN_CNT = mBR_RETRY_LOGIN_CNT;
	}
	public String getMBR_PWD_CHK() {
		return MBR_PWD_CHK;
	}
	public void setMBR_PWD_CHK(String mBR_PWD_CHK) {
		MBR_PWD_CHK = mBR_PWD_CHK;
	}
	
	
}

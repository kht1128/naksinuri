package egovframework.adm.sms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

import egovframework.adm.main.service.AdmDefaultVO;

@Component
public class SmsSendVO extends AdmDefaultVO {
	
	//rstkey 생성
	public String getUniqRstKey() {
		Date today = new Date();
	    SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
	    return date.format(today) + getRandomString(4);
	}
	private String getRandomString(int length) {
	    StringBuffer buffer = new StringBuffer();
	    Random random = new Random();
	    random.setSeed(System.currentTimeMillis());
	    String[] chars = "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
	    for (int i = 0; i < length; i++) {
	      buffer.append(chars[random.nextInt(chars.length)]);
	    }
	    return buffer.toString();
	}
	
	//SMS_QUEUE table
	private String MID;
	private int IS_INCRON;//스케줄러에 진입 여부 : 0,1
	private String APIKEY;//발송자고유키값
	private String RSTKEY;//문자검증고유키값
	private String RST_CODE;//발송상태값
	private int RST;//0:대기,1:성공,2:실패,4:실패(잔액부족)
	private int STAT;//0:대기,1:발송완료,2:발송실패,3:결과리턴,4:발송실패(잔액부족),5:예약발송
	private String SEND_DATE;//발송시간
	private String UPD_DATE;//변경날짜
	private String REG_DATE;//생성날짜
	private String MSG_TYPE;//SMS,LMS,MMS
	private String R_PHONE;//받는사람 번호
	private String S_PHONE;//보내는사람 번호
	private String IP;//문자 발송 시도 IP
	private String SUBMSG;//문자 - 제목
	private String MSG;//문자 - 내용
	private int IMG_CNT;//MMS 전송시 이미지 파일 갯수
	private String IMG_PATH;//이미지 파일 경로 ( 다수인 경우 구분자는 ';' )
	private String REG_MBR_ID;//varchar(50) NULL등록자아이디
	private String UPD_MBR_ID;//varchar(50) NULL수정자아이디
	private String DEL_ST;//int(3) NULL0:정상,1:삭제됨
	private String MBR_ID;//varchar(50) NULL대상자아이디
	
	//디비외
	private String SIDO_CD;
	private String SIGNGU_CD;
	private String MBR_NM;
	private String MBR_HP;
	private String MBR_BIRTH;
	private String DTL_NM;
	private String SMS_MENT_DTL_CD;	// 메세지전송대상구분코드
	private String SEND_DATE_STR;	// 발송시간
	private String SEND_DATE_END;	// 발송시간
	private String MBR_SCRTY_KEY;	// 웹취약점 대응 변수
	private String TR_NUM;			// sms 문자발송 번호
	private String TR_MSG;			// sms 문자발송 내용
	private String TR_SENDDATE;		// sms 문자발송 일시
	private String MSGKEY;			// mms 문자발송 번호
	private String REQDATE;			// mms 문자발송 일시 
	
	
	public String getMSGKEY() {
		return MSGKEY;
	}
	public void setMSGKEY(String mSGKEY) {
		MSGKEY = mSGKEY;
	}
	public String getREQDATE() {
		return REQDATE;
	}
	public void setREQDATE(String rEQDATE) {
		REQDATE = rEQDATE;
	}
	public String getTR_MSG() {
		return TR_MSG;
	}
	public void setTR_MSG(String tR_MSG) {
		TR_MSG = tR_MSG;
	}
	public String getTR_SENDDATE() {
		return TR_SENDDATE;
	}
	public void setTR_SENDDATE(String tR_SENDDATE) {
		TR_SENDDATE = tR_SENDDATE;
	}
	public String getTR_NUM() {
		return TR_NUM;
	}
	public void setTR_NUM(String tR_NUM) {
		TR_NUM = tR_NUM;
	}
	public String getMBR_SCRTY_KEY() {
		return MBR_SCRTY_KEY;
	}
	public void setMBR_SCRTY_KEY(String mBR_SCRTY_KEY) {
		MBR_SCRTY_KEY = mBR_SCRTY_KEY;
	}
	public String getSEND_DATE_STR() {
		return SEND_DATE_STR;
	}
	public void setSEND_DATE_STR(String sEND_DATE_STR) {
		SEND_DATE_STR = sEND_DATE_STR;
	}
	public String getSEND_DATE_END() {
		return SEND_DATE_END;
	}
	public void setSEND_DATE_END(String sEND_DATE_END) {
		SEND_DATE_END = sEND_DATE_END;
	}
	public String getSMS_MENT_DTL_CD() {
		return SMS_MENT_DTL_CD;
	}
	public void setSMS_MENT_DTL_CD(String sMS_MENT_DTL_CD) {
		SMS_MENT_DTL_CD = sMS_MENT_DTL_CD;
	}
	public String getMBR_ID() {
		return MBR_ID;
	}
	public void setMBR_ID(String mBR_ID) {
		MBR_ID = mBR_ID;
	}
	public String getDEL_ST() {
		return DEL_ST;
	}
	public void setDEL_ST(String dEL_ST) {
		DEL_ST = dEL_ST;
	}
	public String getSIDO_CD() {
		return SIDO_CD;
	}
	public void setSIDO_CD(String sIDO_CD) {
		SIDO_CD = sIDO_CD;
	}
	public String getSIGNGU_CD() {
		return SIGNGU_CD;
	}
	public void setSIGNGU_CD(String sIGNGU_CD) {
		SIGNGU_CD = sIGNGU_CD;
	}
	public String getMBR_NM() {
		return MBR_NM;
	}
	public void setMBR_NM(String mBR_NM) {
		MBR_NM = mBR_NM;
	}
	public String getMBR_HP() {
		return MBR_HP;
	}
	public void setMBR_HP(String mBR_HP) {
		MBR_HP = mBR_HP;
	}
	public String getMBR_BIRTH() {
		return MBR_BIRTH;
	}
	public void setMBR_BIRTH(String mBR_BIRTH) {
		MBR_BIRTH = mBR_BIRTH;
	}
	public String getDTL_NM() {
		return DTL_NM;
	}
	public void setDTL_NM(String dTL_NM) {
		DTL_NM = dTL_NM;
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
	public String getMID() {
		return MID;
	}
	public void setMID(String mID) {
		MID = mID;
	}
	public int getIS_INCRON() {
		return IS_INCRON;
	}
	public void setIS_INCRON(int iS_INCRON) {
		IS_INCRON = iS_INCRON;
	}
	public String getAPIKEY() {
		return APIKEY;
	}
	public void setAPIKEY(String aPIKEY) {
		APIKEY = aPIKEY;
	}
	public String getRSTKEY() {
		return RSTKEY;
	}
	public void setRSTKEY(String rSTKEY) {
		RSTKEY = rSTKEY;
	}
	public String getRST_CODE() {
		return RST_CODE;
	}
	public void setRST_CODE(String rST_CODE) {
		RST_CODE = rST_CODE;
	}
	public int getRST() {
		return RST;
	}
	public void setRST(int rST) {
		RST = rST;
	}
	public int getSTAT() {
		return STAT;
	}
	public void setSTAT(int sTAT) {
		STAT = sTAT;
	}
	public String getSEND_DATE() {
		return SEND_DATE;
	}
	public void setSEND_DATE(String sEND_DATE) {
		SEND_DATE = sEND_DATE;
	}
	public String getUPD_DATE() {
		return UPD_DATE;
	}
	public void setUPD_DATE(String uPD_DATE) {
		UPD_DATE = uPD_DATE;
	}
	public String getREG_DATE() {
		return REG_DATE;
	}
	public void setREG_DATE(String rEG_DATE) {
		REG_DATE = rEG_DATE;
	}
	public String getMSG_TYPE() {
		return MSG_TYPE;
	}
	public void setMSG_TYPE(String mSG_TYPE) {
		MSG_TYPE = mSG_TYPE;
	}
	public String getR_PHONE() {
		return R_PHONE;
	}
	public void setR_PHONE(String r_PHONE) {
		R_PHONE = r_PHONE;
	}
	public String getS_PHONE() {
		return S_PHONE;
	}
	public void setS_PHONE(String s_PHONE) {
		S_PHONE = s_PHONE;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getSUBMSG() {
		return SUBMSG;
	}
	public void setSUBMSG(String sUBMSG) {
		SUBMSG = sUBMSG;
	}
	public String getMSG() {
		return MSG;
	}
	public void setMSG(String mSG) {
		MSG = mSG;
	}
	public int getIMG_CNT() {
		return IMG_CNT;
	}
	public void setIMG_CNT(int iMG_CNT) {
		IMG_CNT = iMG_CNT;
	}
	public String getIMG_PATH() {
		return IMG_PATH;
	}
	public void setIMG_PATH(String iMG_PATH) {
		IMG_PATH = iMG_PATH;
	}
	
}

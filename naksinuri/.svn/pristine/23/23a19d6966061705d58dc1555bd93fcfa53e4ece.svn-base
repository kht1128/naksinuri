package egovframework.all.excel;

public class AllExcelDownLoadBundle {
	/**
	 * 엑셀다운로드 등록 방법
	 * 
	 * 1. MERGE 변수에 키 등록
	 * 2. 출력 할 타이틀포맷을 FORMAT_TITLE 변수에 등록 [
	 * 								표시할출력값,[[LABEL]]를 대체할 키값
	 * 							 ]
	 * 3. 출력 할 포맷을 FORMAT 변수에 등록 [ 
	 * 								표시할출력값 , 
	 * 								VO 변수값(','로 구분시 두개 이상 병합하여 처리) , 
	 * 								ALL_CODE_SET_TB 의 대응되는 코드값으로 출력 값 교체시 , 
	 * 								두개 이상의 List Map 사용시 번호 , 
	 * 								에러 또는 빈값 인 경우 기본값으로 출력 할 내용 ( ',' 및 ':' 로 추가 시 파싱하여 처리  ) 
	 * 								드롭다운항목추가( 구분자 ',' 로 셀렉트항목을 구분 예:낚시터,낚시어선,기타 )
	 * 							 ]
	 * 3-1. 출력 할 포맷을  FORMAT 변수에 등록 [
	 * 								구분할필드명(확인용),
	 * 								메모내용에 입력 될 내용 ( [[BR]] 을 입력하면 줄바꿈 처리 됨 ) 
	 * 									>> MERGE 키를  callTitle() 함수에는 추가할 필요 없고 call() 함수는 필히 추가
	 * 							 ]
	 * 4. call() 함수에 FORMAT 리턴값 매칭
	 * 5. AllMainController 파일 내 listExcelDownload() 함수 내용 추가 및 수정 필수 
	 * 6. AllExcelDownLoad 파일 처리 부 확인 필요시 수정 (List Map 단일 건 인 경우 별도 수정 필요 없음, 특수한 경우에만 추가 및 변경 )
	 * 
	 * */
	public static enum MERGE {
		EDUADM_MEMBER_EDULIST,
		EDUADM_MEMBER_EDULIST_SHIP,
		EDUADM_MEMBER_EDULIST_STORE,
		EDUADM_MEMBER_EDULIST_MERGE,
		EDUADM_MEMBER_EDULIST_SHIP_MERGE,
		EDUADM_MEMBER_EDULIST_STORE_MERGE,
		EDUADM_MBRHSTRY_LIST,
		EDUADM_MBRHSTRY_LIST_NONE_MERGE,
		EDUADM_CERTIFICATE_LIST_STORE,
		EDUADM_CERTIFICATE_LIST_SHIP,
		EDUADM_MBRHSTRY_AREA_RESULT_STORE,
		EDUADM_MBRHSTRY_AREA_RESULT_SHIP,
		LOG_REC_LIST,
		LOG_SYS_LIST,
		LOG_MBR_MOD_LIST,
		EDUADM_BOARD_ESREQUEST_LIST,
		EDUADM_MEMBER_AUTHOR_LOG,
		EDUADM_MEMBER_EDULIST_LOCGOV,
		EDUADM_MEMBER_EDULIST_LOCGOV_SUMMARY
	}
	private final static String[][][] FORMAT_TITLE = {//타이틀,대체값( [[LABEL]] 에 대한 )
		{//0 교육대상자 - 전체
			
		},
		{//1 교육대상자 - 낚시어선업자
			
		},
		{//2 교육대상자 - 낚시터업자
			
		},
		{//3 수강자목록
			
		},
		{//4 이수증발급대장 - 낚시터업자
			{"낚시전문교육 낚시터 이수증 발급대장",""},
		},
		{//5 이수증발급대장 - 낚시어선업자
			{"낚시전문교육 낚시어선 이수증 발급대장",""},			
		},
		{//6 결과보고서 - 낚시터업자
			{"낚시전문교육 낚시터업자 결과보고서",""},
			{"□ 교 육 기 간 : [[LABEL]]년","searchYear"}
		},
		{//7 결과보고서 - 낚시어선업자
			{"낚시전문교육 낚시어선업자 결과보고서",""},
			{"□ 교 육 기 간 : [[LABEL]]년","searchYear"}
		},
		{//8 로그기록 - 접속자 로그 기록
			{"접속자 로그 기록",""},
		},
		{//9 로그기록 - 시스템 로그 기록
			{"시스템 로그 기록",""},
		},
		{//10 로그기록 - 회원정보수정 로그 기록
			{"회원정보수정 로그 기록",""},
		},
		{//11 교육대상자관리 - 문자신청리스트
			{"교육대상자 문자신청리스트",""},
		},
		{//12 관리자계정 - 리스트
			{"관리자권한 리스트",""},
		},
		{//13 지자체현행화 - 전체
			{"지자체명단현행화 리스트",""},
		},
	};
	private final static String[][][] FORMAT = {//라벨,필드값(내부에,구분시 두 필드를 병합),코드값변환,서브쿼리,대체값,포맷타입,드롭다운항목값
		{//0 교육대상자 - 전체
			//교육신청 = 0:신청,1:참석,2:불참,:미신청
			//교육이수 = 0:미이수,1:이수완료,2:이수취소,:미이수
			{"번호","ROW_NUM_INNER","","","","",""},
			{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},{"읍면동","YMD_NM","","1","","",""},
			{"교육대상구분","DTL_CD","Y","1","","",""},
			
			{"시작일자","SHIP_LICENSE_STR_DT","","1","","",""},{"종료일자","SHIP_LICENSE_END_DT","","1","","",""},{"허가(등록)번호/신고번호","REG_NUM_CD","","1","","",""},{"낚시어선 소재지/낚시터위치","DTL_ADDR","","1","","",""},
			
			{"낚시터 업구분","FSHLC_WORK_CD","Y","1","","",""},{"낚시터 적용수면","FSHLC_APPLC","","1","","",""},

			{"낚시어선 어선번호","SHIP_CD","","1","","",""},{"낚시터명/어선명","DTL_NM","","1","","",""},{"낚시어선 해기사자격증","SHIP_LICENSE","","1","","",""},{"낚시어선 총톤수","SHIP_GRTG","","1","","",""},
			{"낚시어선 선적항","SHIP_PRLOAD","","1","","",""},{"낚시어선 최대승객","SHIP_MAX_PSNGER","","1","","",""},{"낚시어선 최대선원","SHIP_MAX_CREW","","1","","",""},
			
			{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"주소","MBR_ADDR1,MBR_ADDR2","","","","",""},{"전화번호","MBR_TEL","","","","TEL",""},
			{"휴대전화","MBR_HP","","","","HP",""},{"교육신청","MBR_LRNNG_ST","","2","0:신청,1:참석,2:불참,:미신청","",""},{"교육이수","MBR_LRNNG_CMPLT_ST","","2","0:미이수,1:이수완료,2:이수취소,:미이수","",""},
			{"등록방법","REG_TYPE_CD","Y","1","공단","",""},
			
			{"회원가입일자","MBR_REG_DT","","","","",""},{"교육신청일자","REG_DT","","2","","",""},{"신규/재개자/기존 구분","HMBR_FSHRBT_TYPE","","","legacy:기존,new:신규,resmpt:재개자","",""},
			{"법인사업장 교육책임자 구분","MBR_EDU_RSPNBER_TYPE","","","CEO:대표자,EDU_RSPNBER:교육책임자","",""}
		},
		{//1 교육대상자 - 낚시어선업자
			{"번호","ROW_NUM_INNER","","","","",""},
			{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},{"읍면동","YMD_NM","","1","","",""},{"신고번호","REG_NUM_CD","","1","","",""},{"어선번호","SHIP_CD","","1","","",""},{"어선명","DTL_NM","","1","","",""},
			{"시작일자","SHIP_LICENSE_STR_DT","","1","","",""},{"종료일자","SHIP_LICENSE_END_DT","","1","","",""},{"총톤수","SHIP_GRTG","","1","","",""},{"선적항","SHIP_PRLOAD","","1","","",""},{"최대승객","SHIP_MAX_PSNGER","","1","","",""},
			{"최대선원","SHIP_MAX_CREW","","1","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"주소","MBR_ADDR1,MBR_ADDR2","","","","",""},{"전화번호","MBR_TEL","","","","TEL",""},
			{"휴대전화","MBR_HP","","","","HP",""},{"해기사자격증","SHIP_LICENSE","","1","","",""},{"교육신청","MBR_LRNNG_ST","","2","0:신청,1:참석,2:불참,:미신청","",""},{"교육이수","MBR_LRNNG_CMPLT_ST","","2","0:미이수,1:이수완료,2:이수취소,:미이수","",""},
			{"등록방법","REG_TYPE_CD","Y","1","공단","",""},{"신규/재개자/기존 구분","HMBR_FSHRBT_TYPE","","","legacy:기존,new:신규,resmpt:재개자","",""},{"법인사업장 교육책임자 구분","MBR_EDU_RSPNBER_TYPE","","","CEO:대표자,EDU_RSPNBER:교육책임자","",""}
		},
		{//2 교육대상자 - 낚시터업자 (20열)
			{"번호","ROW_NUM_INNER","","","","",""},
			{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},{"읍면동","YMD_NM","","1","","",""},{"업구분","FSHLC_WORK_CD","Y","1","","",""},{"허가(등록)번호","REG_NUM_CD","","1","","",""},{"낚시터명","DTL_NM","","1","","",""},
			{"낚시터위치","DTL_ADDR","","1","","",""},{"적용수면","FSHLC_APPLC","","1","","",""},{"시작일자","SHIP_LICENSE_STR_DT","","1","","",""},{"종료일자","SHIP_LICENSE_END_DT","","1","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},
			{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"주소","MBR_ADDR1,MBR_ADDR2","","","","",""},{"전화번호","MBR_TEL","","","","TEL",""},{"휴대전화","MBR_HP","","","","HP",""},
			{"교육신청","MBR_LRNNG_ST","","2","0:신청,1:참석,2:불참,:미신청","",""},{"교육이수","MBR_LRNNG_CMPLT_ST","","2","0:미이수,1:이수완료,2:이수취소,:미이수","",""},{"법인사업장 교육책임자 구분","MBR_EDU_RSPNBER_TYPE","","","CEO:대표자,EDU_RSPNBER:교육책임자","",""}
		},	
		{//3 수강자목록 (15열)
			//신청상태 = 0:대기,1:승인,2:취소,3:강제취소,4:보류 
			//이수상태 = 0:이수전,1:이수완료,2:이수취소
			{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},{"낚시터명/어선명","DTL_NM","","1","","",""},
			{"이름","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"주소","MBR_ADDR1","","","","",""}, {"상세주소","MBR_ADDR2","","","","",""},{"연락처","MBR_HP","","","","HP",""},{"신청상태","LRNNG_ST","","","0:대기,1:승인,2:취소,3:강제취소,4:보류","",""},{"이수상태","LRNNG_CMPLT_ST","","","0:이수전,1:이수완료,2:이수취소","",""},{"이수일자","LRNNG_CMPLT_DT","","","","",""},
			{"이수처리담당자","LRNNG_CMPLT_MBR_ID","","","","",""},{"교과목","CRS_NM","","","","",""},{"교육일시","CRS_STR_DT,CRS_END_DT","","","","",""},{"교육기관","CAT_INS_NM","","","","",""},{"교육장소","CRS_PLACE","","","","",""},{"메모","MBR_DSCRP","","","","",""}
		},	
		{//4 이수증발급대장 - 낚시터업자 (16열)
			{"번호","ROW_NUM","","","","",""},{"일련번호(증서번호)","CRTF_CD","","","","",""},{"교육실시일","CRS_STR_DT","","","","",""},{"증서발급(취소)일시","UPD_DT","","","","",""},{"교육기관명","CRS_NM","","","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},
			{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"연락처","MBR_HP","","","","HP",""},{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},
			{"신고번호","REG_NUM_CD","","1","","",""},{"낚시터명","DTL_NM","","1","","",""},{"업구분","FSHLC_WORK_CD","Y","1","","",""},{"등록방법","REG_TYPE_CD","Y","1","공단","",""},{"발급여부","USE_ST","","","0:미발급,1:발급","",""}
			/*
			{"번호","ROW_NUM","","","","",""},{"일련번호(증서번호)","CRTF_CD","","","","",""},{"교육실시일","CRS_STR_DT","","","","",""},{"증서발급(취소)일시","UPD_DT","","","","",""},{"교육기관명","CRS_NM","","","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},
			{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","",""},{"연락처","MBR_HP","","","","",""},{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},
			{"신고번호","REG_NUM_CD","","1","","",""},{"낚시터명","DTL_NM","","1","","",""},{"업구분","FSHLC_WORK_CD","Y","1","","",""},{"등록방법","REG_TYPE_CD","Y","1","공단",""},{"발급여부","USE_ST","","","0:미발급,1:발급",""}
			*/
		},
		{//5 이수증발급대장 - 낚시어선업자 (17열)
			{"번호","ROW_NUM","","","","",""},{"일련번호(증서번호)","CRTF_CD","","","","",""},{"교육실시일","CRS_STR_DT","","","","",""},{"증서발급(취소)일시","UPD_DT","","","","",""},{"교육기관명","CRS_NM","","","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},
			{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","BIRTH",""},{"연락처","MBR_HP","","","","HP",""},{"해기사자격증","SHIP_LICENSE","","1","","",""},{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},
			{"신고번호","REG_NUM_CD","","1","","",""},{"어선번호","SHIP_CD","","1","","",""},{"어선명","DTL_NM","","1","","",""},{"등록방법","REG_TYPE_CD","Y","1","공단","",""},{"발급여부","USE_ST","","","0:미발급,1:발급","",""}
			/*
			{"번호","ROW_NUM","","","","",""},{"일련번호(증서번호)","CRTF_CD","","","","",""},{"교육실시일","CRS_STR_DT","","","","",""},{"증서발급(취소)일시","UPD_DT","","","","",""},{"교육기관명","CRS_NM","","","","",""},{"대상구분","DTL_LICENSE_CD_NM","","1","","",""},
			{"성명","MBR_NM","","","","",""},{"생년월일","MBR_BIRTH","","","","",""},{"연락처","MBR_HP","","","","",""},{"해기사자격증","SHIP_LICENSE","","1","","",""},{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},
			{"신고번호","REG_NUM_CD","","1","","",""},{"어선번호","SHIP_CD","","1","","",""},{"어선명","DTL_NM","","1","","",""},{"등록방법","REG_TYPE_CD","Y","1","공단",""},{"발급여부","USE_ST","","","0:미발급,1:발급",""}
			 */
		},
		{//6 결과보고서 - 낚시터업자
			{"시도","sidoCdNm","","","","",""},{"교육대상","mbrTotCnt","","","","",""},{"교육인원","mbrCmpltCnt","","","","",""},{"이수율","mbrPerVal","","","","",""},
		},
		{//7 결과보고서 - 낚시어선업자
			{"시도","sidoCdNm","","","","",""},{"교육대상","mbrTotCnt","","","","",""},{"교육인원","mbrCmpltCnt","","","","",""},{"이수율","mbrPerVal","","","","",""},
		},
		{//8 로그기록 - 접속자 로그 기록
			{"번호","ROW_NUM_INNER","","","","",""},{"로그일자","REG_DT","","","","",""},{"요청자:호칭","MBR_NCNM","","","","",""},{"요청자:이름","MBR_NM","","","","",""},{"요청자:아이디","MBR_ID","","","","",""},{"요청자:IP","MBR_IP","","","","",""},
			{"로그내용","LOG_DSCRP","","","","",""},{"비고(사유)","MBR_MSG","","","","",""},
		},
		{//9 로그기록 - 시스템 로그 기록
			{"번호","ROW_NUM_INNER","","","","",""},{"발생일자","ERR_DT","","","","",""},{"요청자:호칭","MBR_NCNM","","","","",""},{"요청자:이름","MBR_NM","","","","",""},{"요청자:아이디","MBR_ID","","",":비회원","",""},{"요청자:IP","ERR_IP","","","","",""},
			{"요청","ERR_REQ_URI","","","","",""},{"에러코드","ERR_STAT_CD","","","","",""},{"응답결과","ERR_EXP_MSG","","","","",""}
		},
		{//10 로그기록 - 회원정보수정 로그 기록
			{"번호","ROW_NUM_INNER","","","","",""},{"발생일자","REG_DT","","","","",""},{"요청자:호칭","MASTER_MBR_NCNM","","","","",""},{"요청자:이름","MASTER_MBR_NM","","","","",""},{"요청자:아이디","REG_MBR_ID","","","","",""},{"요청자:IP","LOG_INFO_IP","","","","",""},
			{"로그구분","LOG_TYPE","","","MBR_TB:회원기본정보,MBR_DTL_TB:회원추가상세정보 ,EDU_MBR_HSTRY_TB:교육수강생","",""},{"로그이력","LOG_DSCRP","","","","",""},{"사용자변경이력","LOG_UPD_MSG","","","","",""},
			{"대상자:이름","TMP_MBR_NM","","","","",""},{"대상자:아이디","MBR_ID","","","","",""},{"대상자:변경 전 ROW데이터","OLD_DATA","","","","",""},{"대상자:변경 후 ROW데이터","NEW_DATA","","","","",""},
		},
		{//11 교육대상자관리 - 문자신청리스트
			/*
			{"번호","ROW_NUM_INNER","","","",""},{"이름","ESR_MBR_NM","","","",""},{"아이디","ESR_MBR_ID","","","",""},{"연락처","ESR_MBR_HP","","","",""},
			{"교육대상구분","ESR_DTL_CD","Y","","",""},{"선택교육과정","RMNDR_CRS_NM","","","미지정",""},
			{"수강인구분","ESR_DTL_LICENSE_CD","Y","","",""},{"신청일자","ESR_REG_DT","","","",""},
			*/
			{"번호","ROW_NUM_INNER","","","","",""},{"이름","ESR_MBR_NM","","","","",""},{"연락처","ESR_MBR_HP","","","","HP",""},
			{"교육대상구분","ESR_DTL_CD","Y","","","",""},{"수강인구분","ESR_DTL_LICENSE_CD","Y","","","",""},{"신청일자","ESR_REG_DT","","","","",""},
		},
		{//12 관리자계정 - 리스트
			{"번호","ROW_NUM_INNER","","","","",""},
			{"신청자","REQST_NM","","","","",""},{"신청일시","REQST_DT","","","","",""},{"신청목적","REQST_CN","","","","",""},
			{"승인자","CONFM_NM","","","","",""},{"사유","CONFM_CN","","","","",""},{"구분","CONFM_TYPE","","","","",""},{"승인일시","CONFM_DT","","","","",""},{"메모","MBR_MSG","","","","",""},
			{"권한레벨","MBR_LV","","","","",""},{"권한명","AUTHOR_NM","","","","",""},{"권한내역","AUTHOR_CN","","","","",""},{"사용기간","MBR_USG_DT","","","","",""}
		},
		{//13 지자체명단 현행화 교육대상자 - 전체
			{"번호","ROW_NUM_INNER","","","","",""},
			{"낚시터/어선 구분","DTL_CD","","","낚시터업자:낚시터,낚시어선업자:낚시어선","","낚시터,낚시어선"},{"시도","SIDO_CD_NM","","1","","",""},{"시군구","SIGNGU_CD_NM","","1","","",""},{"허가(등록)/신고번호","REG_NUM_CD","","1","","",""},{"어선번호","SHIP_CD","","1","","",""},{"낚시터명/어선명","DTL_NM","","1","","",""},
			{"대상구분(개인업자,법인업자,선원)","DTL_LICENSE_CD_NM","","1","","","개인업자,법인업자,해기사면허 소지 선장,해기사면허 소지 선원,해기사면허 미소지 선원"},{"법인업자구분(대표자/교육책임자)","MBR_EDU_RSPNBER_TYPE","","","CEO:대표자,EDU_RSPNBER:교육책임자","","대표자,교육책임자"},{"해기사자격증","SHIP_LICENSE","","1","","","무,유"},
			{"성명(법인명)","MBR_NM","","","","",""},{"생년월일(법인등록번호)","MBR_BIRTH","","","","BIRTH",""},{"휴대전화번호","MBR_HP","","","","HP",""},{"우편번호","MBR_ZIPCD","","","","",""},{"주소","MBR_ADDR1","","","","",""},{"전화번호","MBR_TEL","","","","TEL",""}
		},
		{//14 지자체명단 현행화 교육대상자 -주석라벨(구분을위한필드명,메모입력)
			{"번호",""},
			{"낚시터/어선 구분","1)낚시터[[BR]]2)낚시어선[[BR]]"},{"시도",""},{"시군구",""},{"허가(등록)/신고번호","1)0000-000[[BR]]2)0000000"},{"어선번호","* 낚시어선만 입력하세요[[BR]]1)0000000-0000000[[BR]]2)00000000000000"},{"낚시터명/어선명",""},
			{"대상구분","1)개인업자[[BR]]2)법인업자[[BR]]3)해기사면허 소지 선장[[BR]]4)해기사면허 소지 선원[[BR]]5)해기사면허 미소지 선원"},{"법인업자구분(대표자/교육책임자)","* 법인업자만 입력하세요[[BR]]1)대표자[[BR]]2)교육책임자"},{"해기사자격증","* 낚시어선만 입력하세요[[BR]]1)있을 경우: 유[[BR]]2)없을 경우: 무"},
			{"성명(법인명)",""},{"생년월일(법인등록번호)","1)생년월일: 0000-00-00[[BR]]2)사업자등록번호[[BR]]  (1)000-00-00000[[BR]]  (2)000000-0000000"},{"휴대전화번호","1)000-0000-0000[[BR]]2)000-000-0000[[BR]]* 교육 안내를 위해 정확한 휴대전화 번호 입력 필수"},
			{"우편번호",""},{"주소","* 교육 안내를 위한 정확한 주소 입력 필수"},{"전화번호",""}
		}
	};
	public final static Object[] callTitle(MERGE merge) {
		if(merge == MERGE.EDUADM_MEMBER_EDULIST || merge == MERGE.EDUADM_MEMBER_EDULIST_MERGE) {
			return new Object[]{"0",FORMAT_TITLE[0],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_SHIP || merge == MERGE.EDUADM_MEMBER_EDULIST_SHIP_MERGE) {
			return new Object[]{"1",FORMAT_TITLE[1],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_STORE || merge == MERGE.EDUADM_MEMBER_EDULIST_STORE_MERGE) {
			return new Object[]{"2",FORMAT_TITLE[2],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_LIST || merge == MERGE.EDUADM_MBRHSTRY_LIST_NONE_MERGE) {
			return new Object[]{"3",FORMAT_TITLE[3],""};
		} else if(merge == MERGE.EDUADM_CERTIFICATE_LIST_STORE) {
			return new Object[]{"4",FORMAT_TITLE[4],""};
		} else if(merge == MERGE.EDUADM_CERTIFICATE_LIST_SHIP) {
			return new Object[]{"5",FORMAT_TITLE[5],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_AREA_RESULT_STORE) {
			return new Object[]{"6",FORMAT_TITLE[6],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_AREA_RESULT_SHIP) {
			return new Object[]{"7",FORMAT_TITLE[7],""};
		} else if(merge == MERGE.LOG_REC_LIST) {
			return new Object[]{"8",FORMAT_TITLE[8],""};
		} else if(merge == MERGE.LOG_SYS_LIST) {
			return new Object[]{"9",FORMAT_TITLE[9],""};
		} else if(merge == MERGE.LOG_MBR_MOD_LIST) {
			return new Object[]{"10",FORMAT_TITLE[10],""};
		} else if(merge == MERGE.EDUADM_BOARD_ESREQUEST_LIST) {
			return new Object[]{"11",FORMAT_TITLE[11],""};
		} else if(merge == MERGE.EDUADM_MEMBER_AUTHOR_LOG) {
			return new Object[]{"12",FORMAT_TITLE[12],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_LOCGOV) {
			return new Object[]{"13",FORMAT_TITLE[13],""}; 
		} else {
			return null;
		}
	}
	public final static Object[] call(MERGE merge) {
		if(merge == MERGE.EDUADM_MEMBER_EDULIST || merge == MERGE.EDUADM_MEMBER_EDULIST_MERGE) {
			return new Object[]{"0",FORMAT[0],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_SHIP || merge == MERGE.EDUADM_MEMBER_EDULIST_SHIP_MERGE) {
			return new Object[]{"1",FORMAT[1],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_STORE || merge == MERGE.EDUADM_MEMBER_EDULIST_STORE_MERGE) {
			return new Object[]{"2",FORMAT[2],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_LIST || merge == MERGE.EDUADM_MBRHSTRY_LIST_NONE_MERGE) {
			return new Object[]{"3",FORMAT[3],""};
		} else if(merge == MERGE.EDUADM_CERTIFICATE_LIST_STORE) {
			return new Object[]{"4",FORMAT[4],""};
		} else if(merge == MERGE.EDUADM_CERTIFICATE_LIST_SHIP) {
			return new Object[]{"5",FORMAT[5],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_AREA_RESULT_STORE) {
			return new Object[]{"6",FORMAT[6],""};
		} else if(merge == MERGE.EDUADM_MBRHSTRY_AREA_RESULT_SHIP) {
			return new Object[]{"7",FORMAT[7],""};
		} else if(merge == MERGE.LOG_REC_LIST) {
			return new Object[]{"8",FORMAT[8],""};
		} else if(merge == MERGE.LOG_SYS_LIST) {
			return new Object[]{"9",FORMAT[9],""};
		} else if(merge == MERGE.LOG_MBR_MOD_LIST) {
			return new Object[]{"10",FORMAT[10],""};
		} else if(merge == MERGE.EDUADM_BOARD_ESREQUEST_LIST) {
			return new Object[]{"11",FORMAT[11],""};
		} else if(merge == MERGE.EDUADM_MEMBER_AUTHOR_LOG) {
			return new Object[]{"12",FORMAT[12],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_LOCGOV) {
			return new Object[]{"13",FORMAT[13],""};
		} else if(merge == MERGE.EDUADM_MEMBER_EDULIST_LOCGOV_SUMMARY) {
			return new Object[]{"14",FORMAT[14],""};
		} else {
			return null;
		}
	}
}

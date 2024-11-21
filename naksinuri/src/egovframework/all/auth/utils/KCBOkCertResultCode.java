package egovframework.all.auth.utils;

public class KCBOkCertResultCode {
	//KCB 오류 코드 모음
	public static String parseMessage(final String code) {
		switch (code) {
			case "B000" : return "정상 처리"; 
			case "B006" : return "잔여건수 사용 초과, 충전제 사용시 잔액부족"; 
			case "B008" : return "제휴가맹점 코드 오류 – 회원사코드가 등록 안 된 경우"; 
			case "B010" : return "계약되지 않은 서비스 타입"; 
			case "B017" : return "입력값 오류"; 
			case "B030" : return "네트워크 오류 (타임아웃, 웹서비스 연결 지연 등)"; 
			case "B043" : return "잘못된 형식의 전문"; 
			case "B081" : return "이통사 DB 오류"; 
			case "B082" : return "이통사 SCI 통신 오류"; 
			case "B083" : return "이통사 DB 암호화 서버 연결 실패"; 
			case "B084" : return "이통사 CI/DI 연동 오류"; 
			case "B085" : return "이통사 CP(회원사) 코드 없음"; 
			case "B086" : return "이통사 기타 오류"; 
			case "B091" : return "인증 시도 횟수 추가"; 
			case "B092" : return "이통사 연동 오류"; 
			case "B097" : return "서비스거래번호 없음"; 
			case "B098" : return "서비스거래번호 오류(길이/형식)"; 
			case "B099" : return "기타오류 – 서버에러"; 
			case "B100" : return "본인인증 처리 실패"; 
			case "B101" : return "기요청 서비스 거래번호"; 
			case "B102" : return "선행 요청정보 없음"; 
			case "B103" : return "서비스 사용 가능일이 아닙니다"; 
			case "B104" : return "서비스 사용 중지 상태입니다"; 
			case "B105" : return "서비스 기간이 만료 되었습니다"; 
			case "B106" : return "요청 사이트 도메인 없음"; 
			case "B107" : return "본인인증수단 없음"; 
			case "B108" : return "본인인증 요청사유 없음"; 
			case "B109" : return "타겟ID 없음"; 
			case "B110" : return "리턴 URL 없음"; 
			case "B111" : return "파라미터체크(본인인증수단)"; 
			case "B112" : return "파라미터체크(주민번호형식)"; 
			case "B113" : return "파라미터체크(휴대폰 통신사 정보)"; 
			case "B114" : return "파라미터체크(휴대폰 번호 앞자리)"; 
			case "B115" : return "파라미터체크(휴대폰 번호)"; 
			case "B120" : return "인증번호 재전송 건수 초과"; 
			case "B121" : return "서비스 오류"; 
			case "B122" : return "DB 오류"; 
			case "B123" : return "본인인증 취소"; 
			case "B124" : return "회원사 허용대역 오류"; 
			case "B125" : return "인증번호 문자길이 오류 (80Byte 초과 시) – 회원사명 조정 필요"; 
			case "B128" : return "이미 정상인증 확인되었습니다 – 인증 완료 건에 인증번호 요청한 경우"; 
			case "B129" : return "인증번호 입력시간 초과"; 
			case "B130" : return "인증번호 오류입력건수 초과"; 
			case "B131" : return "인증번호 불일치"; 
			case "B132" : return "해당건 미존재"; 
			case "B133" : return "잘못된 접근 (페이지 리로딩 포함)"; 
			case "B135" : return "등록되지 않은 서비스 구분"; 
			case "B136" : return "인증번호 재전송 요청시간이 초과되었습니다"; 
			case "B137" : return "SMS 발송에 실패했습니다"; 
			case "B138" : return "잘못된 DI 정보가 수신되었습니다"; 
			case "B139" : return "잘못된 CI 정보가 수신되었습니다"; 
			case "B140" : return "CI 검증 실패"; 
			case "B141" : return "파라미터체크(성명)"; 
			case "B142" : return "파라미터체크(생년월일)"; 
			case "B143" : return "파라미터체크(성별)"; 
			case "B144" : return "파라미터체크(내외국인구분)"; 
			case "B145" : return "파라미터체크(개인정보동의여부)"; 
			case "B146" : return "파라미터체크(식별정보동의여부)"; 
			case "B147" : return "파라미터체크(통신약관동의여부)"; 
			case "B148" : return "파라미터체크(SMS/LMS구분)"; 
			case "B149" : return "파라미터체크(SMS 인증번호)"; 
			case "B150" : return "파라미터체크(원거래 주민번호 상이)"; 
			case "B151" : return "파라미터체크(원거래 휴대폰번호 상이)"; 
			case "B154" : return "휴대폰인증보호서비스앱 미설치"; 
			case "B155" : return "차단된 회원사"; 
			case "B156" : return "SMS발송 차단 회원사"; 
			case "B157" : return "사용자 입력 항목 체크 오류 – 개인정보수집/이용/취급위탁 동의 여부"; 
			case "B158" : return "SMS메시지를 확인해주세요"; 
			case "B159" : return "콜백번호를 확인해주세요"; 
			case "B161" : return "비정상적인 요청으로 서비스 제공이 중지되었습니다"; 
			case "B162" : return "법인폰 차단회원사"; 
			case "T990" : return "모듈토큰 미존재"; 
		}
		return "(미확인된 코드값)";
	}
}

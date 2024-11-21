package egovframework.adm.main.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.all.login.service.LoginVO;

public class AdmLoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdmLoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("*******************************************************");
		
		/** 
		 * MBR_LV_ID 		: 회원레벨			( 1:통합관리자,2:총관리자,3:센터운영자,4:외부운영자,10:일반사용자 )   
		 * MBR_GRP_1_ST 	: 종합센터 권한허용여부 	('Y','N') 
		 * MBR_GRP_2_ST 	: 교육센터 권한허용여부 	('Y','N') 
		 * MBR_GRP_3_ST 	: 박랍회 권한허용여부 	('Y','N') 
		 * MBR_GRP_4_ST 	: CTI 권한허용여부	('Y','N')
		 * MBR_POSITION_CD 	: 직급코드			(0:기본,1:홈스테이운영자,2:귀어닥터운영자) 
		 */
		
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			try { 
				String urlStr = request.getRequestURI().toString(); 
				if(urlStr.equals("/adm/member/join_adm_act.do")) { //예외처리
					LOGGER.debug("관리자페이지 예외 구간!!");
				} else {
					LOGGER.debug("관리자페이지는 무조건 로그인이 필요한 서비스!!");
					response.sendRedirect("/adm/member/login.do");
					return false;
				}
			} catch(Exception e) {
				LOGGER.debug("관리자페이지는 무조건 로그인이 필요한 서비스!!");
				response.sendRedirect("/adm/member/login.do");
				return false;
			}
			
		} else {
			LOGGER.debug("아이디 : " + loginVO.getMBR_ID());
			LOGGER.debug("권한레벨 : " + loginVO.getMBR_LV_ID());
			LOGGER.debug("그룹 : " + loginVO.getMBR_GRP_ID());
			LOGGER.debug("종합센터 권한허용여부 : " + loginVO.getMBR_GRP_1_ST());
			LOGGER.debug("교육센터 권한허용여부 : " + loginVO.getMBR_GRP_2_ST());
			LOGGER.debug("박람회 권한허용여부 : " + loginVO.getMBR_GRP_3_ST());
			LOGGER.debug("CTI 권한허용여부 : " + loginVO.getMBR_GRP_4_ST());
			//권한 및 그룹체크
			if(loginVO.getMBR_LV_ID().equals("1") //최상위등급,그룹제한없음 = 통합관리자
				|| ( loginVO.getMBR_LV_ID().equals("2") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //1등급 = 총관리자
				|| ( loginVO.getMBR_LV_ID().equals("3") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //2등급 = 센터운영자
				|| ( loginVO.getMBR_LV_ID().equals("4") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //3등급 = 타기관운영자
				// *** 아래는 허용되지 않는 사용자 ***
				//|| ( loginVO.getUserlevel().equals("10") && loginVO.getUsergroup1().equals("1") ) //최하위등급 = 일반사용자
			) {
				LOGGER.debug("관리자페이지 - 접근 허용!!");	
				if(loginVO.getMBR_POSITION_CD().equals("PCD0004") || loginVO.getMBR_POSITION_CD().equals("PCD0003") || loginVO.getMBR_POSITION_CD().equals("PCD0002")){
					//PCD0004=낚시전문교육 교육기관 담당자, PCD0003=낚시전문교육 지자체 담당자, PCD0002=낚시전문교육 해양경찰서 담당자
					String[] uriArr = request.getRequestURI().split("/");
					
					String uri = "";
					for(int i = 0; i < uriArr.length; i++){
						if(!uri.equals("")) uri += "/";
						uri += uriArr[i];
					}
					
					boolean page = true;
					switch(uri){
						case "eduadm/member/listAdm.do": page = false; break;//관리자계정관리
						case "eduadm/member/author_log.do": page = false; break;//관리자권한기록
						case "eduadm/member/access_log.do": page = false; break;//관리자접속기록
						
						case "eduadm/board/notice/list.do": page = false; break;//게시판관리 - 공지사항
						case "eduadm/board/file/list.do": page = false; break;//게시판관리 - 자료실
						case "eduadm/board/faq/list.do": page = false; break;//게시판관리 - FAQ
						case "eduadm/board/admnotice/list.do": page = false; break;//게시판관리 - 안내사항(관리자)
						
						case "eduadm/analytics/summary/sitesummary.do": page = false; break;//통계관리 - 사이트 현황
						case "eduadm/analytics/visit/uv.do": page = false; break;//통계관리 - 방문현황(UV)
						case "eduadm/analytics/visit/pv.do": page = false; break;//통계관리 - 페이지뷰(PV)
						case "eduadm/analytics/inflow/searchdashboard.do": page = false; break;//통계관리 - 검색유입현황
						case "eduadm/analytics/environment/osdashboard.do": page = false; break;//통계관리 - 운영체제(OS)분석
						case "eduadm/analytics/inflow/urls.do": page = false; break;//통계관리 - 페이지분석
						
						case "adm/sms/resveEduadm/list.do": page = false; break;//SMS관리 - 메세지 예약관리
						case "adm/sms/mentEduadm/list.do": page = false; break;//SMS관리 - 메세지 탬플릿
						case "adm/sms/logEduadm/list.do": page = false; break;//SMS관리 - 메세지 이력
						
						case "adm/log/listRecEduadm.do": page = false; break;//로그관리 - 접속자 로그 기록
						case "adm/log/listSysEduadm.do": page = false; break;//로그관리 - 시스템 로그 기록

						case "adm/log/listRecSeadm.do": page = false; break;//로그관리 - 접속자 로그 기록
						case "adm/log/listSysSeadm.do": page = false; break;//로그관리 - 시스템 로그 기록
						
						case "adm/log/listMbrModEduadm.do": page = false; break;//로그관리 - 회원정보수정 로그 기록
					}
					
					if(!page){
						LOGGER.debug("교육센터 관리자페이지 -" + uri + "에 접근할 수 있는 권한이 없음!!");
						response.sendRedirect("/eduadm/error/unauth.do");
						return false;
					}
				}
			} else {
				LOGGER.debug("관리자페이지 - 접근할 수 있는 권한이 없음!!");
				//request.getSession().setAttribute("LoginVO", null);
				response.sendRedirect("/adm/error/unauth.do");
				return false;
			}
		}
		LOGGER.debug("*******************************************************");
		return super.preHandle(request, response, handler);
	}
	
}

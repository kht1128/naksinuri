package egovframework.eduadm.main.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.log.service.LogRecordVO;
import egovframework.all.login.service.LoginVO;
import egovframework.com.cmm.service.EgovFileScrty;
import egovframework.eduadm.board.service.EduBoardService;
import egovframework.eduadm.board.service.EduBoardVO;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.utils.PublicUtils;
import egovframework.all.log.service.LogRecordService;



public class EduCenterAdmLoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(EduCenterAdmLoginInterceptor.class);
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	/** EduBoardService */
	@Resource(name = "eduBoardService")
	private EduBoardService eduBoardService;
	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** LogRecordService */
	@Resource(name = "logRecordService")
	private LogRecordService logRecordService;	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.debug("*******************************************************");
		LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
		if(loginVO==null || loginVO.getMBR_ID()==null) {
			LOGGER.debug("교육센터 관리자페이지는 무조건 로그인이 필요한 서비스!!");
			response.sendRedirect("/eduadm/member/login.do");
			return false;
		} else {
			LOGGER.debug("아이디 : " + loginVO.getMBR_ID());
			LOGGER.debug("권한레벨 : " + loginVO.getMBR_LV_ID());
			LOGGER.debug("그룹 : " + loginVO.getMBR_GRP_ID());
			LOGGER.debug("직급코드 : " + loginVO.getMBR_POSITION_CD());
			LOGGER.debug("낚시누리 권한허용여부 : " + loginVO.getMBR_GRP_1_ST());
			LOGGER.debug("낚시전문교육 권한허용여부 : " + loginVO.getMBR_GRP_2_ST());
			//권한 및 그룹체크
			if(loginVO.getMBR_LV_ID().equals("1") //최상위등급,그룹제한없음 = 통합관리자
				|| ( loginVO.getMBR_LV_ID().equals("2") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //1등급 = 총관리자
				|| ( loginVO.getMBR_LV_ID().equals("3") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //2등급 = 센터운영자
				|| ( loginVO.getMBR_LV_ID().equals("4") && loginVO.getMBR_GRP_2_ST().equals("Y") ) //3등급 = 타기관운영자
			) {
				LOGGER.debug("교육센터 관리자페이지 - 접근 허용!!");
				
				//지자체 허용 메뉴 (교육대상자관리,출력관리), 해경 허용 메뉴 (교육대상자관리 - 전체보기)
				if(loginVO.getMBR_POSITION_CD().equals("PCD0003") || loginVO.getMBR_POSITION_CD().equals("PCD0002")){
					String[] uri = request.getRequestURI().split("/");
					boolean page = true;
					switch(uri[2]){//접근불가목록
						case "board": page = false; break;//게시판관리
						case "category": page = false; break;//교육카테고리
						case "sms": page = false; break;//SMS관리
						case "tdata": page = false; break;//교육(콘텐츠)자료
						case "analytics": page = false; break;//통계관리
						case "curriculum": page = false;break;//교육목록
					}
					
					if(!page){
						LOGGER.debug("교육센터 관리자페이지 -" + uri[2] + "에 접근할 수 있는 권한이 없음!!");
						response.sendRedirect("/eduadm/error/unauth.do");
						return false;
					}
					
					
					
					//김현태 지자체 공휴일 차단 테스트 start
			  		
			  			//System.out.println("지자체 로그인 체크 시작");
			  			
			  			
			  			Calendar now = Calendar.getInstance();
			  	        int currentYear = now.get(Calendar.YEAR);
			  	        int currentMonth = now.get(Calendar.MONTH) + 1;
			  	        int currentDay = now.get(Calendar.DAY_OF_MONTH);
			  	        
			  	        String Year = Integer.toString(currentYear);
			  	        String Month = Integer.toString(currentMonth);
			  	        String Day = Integer.toString(currentDay);
			  	    	
			  	        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"); /*URL*/
			  	        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=%2BXYGAVzjF7SyzCmlBHh%2FMbkrQT%2FFwRKSwc%2B9zNpSK%2BoMasrnlmzaYdULhj%2BwZz0UqExf6HMuW1COlF9ntbwF8w%3D%3D"); /*Service Key*/
			  	        urlBuilder.append("&" + URLEncoder.encode("solYear","UTF-8") + "=" + URLEncoder.encode(Year, "UTF-8")); /*연*/
			  	        urlBuilder.append("&" + URLEncoder.encode("solMonth","UTF-8") + "=" + URLEncoder.encode(Month, "UTF-8")); /*월*/
			  	        URL url = new URL(urlBuilder.toString());
			  	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			  	        conn.setRequestMethod("GET");
			  	        conn.setRequestProperty("Content-type", "application/json");
			  	        System.out.println("Response code: " + conn.getResponseCode());
			  	        BufferedReader rd;
			  	        
			  	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			  	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			  	        } else {
			  	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			  	        }
			  	        
			  	        StringBuilder sb = new StringBuilder();
			  	        String line;
			  	        
			  	        while ((line = rd.readLine()) != null) {
			  	            sb.append(line);
			  	        }
			  	        rd.close();
			  	        conn.disconnect();
			  	        
			  	        //System.out.println(sb.toString()); api 반환값 프린트
			  	        
			  	        
			  	        // API 응답 데이터를 XML로 파싱
			  	        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			  	        DocumentBuilder builder = factory.newDocumentBuilder();
			  	        Document document = builder.parse(new InputSource(new StringReader(sb.toString())));

			  	        // <locdate> 요소 추출
			  	        NodeList locdateElements = document.getElementsByTagName("locdate");
			  	        List<String> locdateList = new ArrayList<>();
			  	        
			  	        for (int i = 0; i < locdateElements.getLength(); i++) {
			  	            Element locdateElement = (Element) locdateElements.item(i);
			  	            String locdateValue = locdateElement.getTextContent(); // locdate 값을 문자열로 가져옴
			  	            locdateList.add(locdateValue);
			  	        }
			  	       
			  	        // 현재 날짜와 비교
			  	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			  	        String currentDate = Year + Month + Day;
			  	       
			  	        //로컬 시간으로 주중인지 주말인지 체크
				  	    LocalDate currentDate2 = LocalDate.now();
				        DayOfWeek dayOfWeek = currentDate2.getDayOfWeek();
				          
			  	        String result = "weekday";
			  	        

			  	        for (String locdateValue : locdateList) {
			  	            if (locdateValue.equals(currentDate)||(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY)) {
			  	            	
			  	                result = "holiday"; // 하나라도 일치하면 holiday로 변경
			  	                break; // 일치하는 것을 찾았으므로 루프를 빠져나감
			  	            }
			  	        }
			  	        
			  	        //System.out.println("오늘은: " + result);
			  			
			  			if (result.equals("holiday")) {
			  				
			  				StringBuilder log_dscrp = new StringBuilder();
			  		    	StringBuilder tbl_inf = new StringBuilder();
			  		    	StringBuilder tbl_sn = new StringBuilder();	  				
			  				
			  		    	log_dscrp.append("[지자체,해경 관리자-공휴일 로그인시도]");
			  		    	LogRecordVO mEduLogRecordVO = new LogRecordVO();
			  		    	
			  				mEduLogRecordVO.setLOG_MSG(mEduLogRecordVO.encodingFromObjectToJson(loginVO));
			  				mEduLogRecordVO.setLOG_DSCRP(log_dscrp.toString());
			  				mEduLogRecordVO.setTBL_INF(tbl_inf.toString());
			  				mEduLogRecordVO.setTBL_SN(tbl_sn.toString());
			  				mEduLogRecordVO.setMBR_ID(loginVO.getMBR_ID());
			  				mEduLogRecordVO.setMBR_LV(loginVO.getMBR_LV_ID());
			  				mEduLogRecordVO.setMBR_IP(PublicUtils.getClientIpAddr(request));
			  				logRecordService.set_log_data(mEduLogRecordVO,request);		  				
			  	        
			  				//System.out.println("휴일일경우 못지나감");
			  				LOGGER.debug("교육센터 관리자페이지 - 지자체,해경 관리자 주말/공휴일 로그인 차단!!");
			  				
			  				response.setContentType("text/html; charset=utf-8");
			  		        PrintWriter out = response.getWriter();		
			  				
			  				out.println("<script>alert('주말과 공휴일은 로그인 할 수 없습니다.');history.go(-1);</script>");
			  		        out.flush();
			  				out.close();
							
							//response.sendRedirect("/eduadm/error/unauth.do");
							return false;
						
			  			}
			  			
			  			//System.out.println("아닐경우 빠져나옴");
					
			  		//지자체 공휴일 차단 테스트 end
					
					
					
				}else if(loginVO.getMBR_POSITION_CD().equals("PCD0004")){
					// 교육기관 허용 메뉴 ( 교육목록 )
					String[] uri = request.getRequestURI().split("/");
					boolean page = true;
					switch(uri[2]){//접근불가목록
						case "member": {//계정관리
							String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
							boolean isKoreafcaMember = false;
							if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
								for (String mbrid : koreafcaMbrIds) {
									if(loginVO.getMBR_ID().equals(mbrid)) {
										isKoreafcaMember = true;
										break;
									}
								}
							}
							if(isKoreafcaMember) {
								//특정 계정은 예외처리 
								//낚시터 한정 관리
								if(!loginVO.getMBR_TRGT_CD().equals("CIDN010200")) {
									loginVO.setMBR_TRGT_CD("CIDN010200");	
									request.getSession().setAttribute("LoginVO", loginVO);
								}
							} else {
								page = false;
							}
						} break;
						case "board": {//게시판관리
							if(uri[3].equals("rmndr")) {
								String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
								boolean isKoreafcaMember = false;
								if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
									for (String mbrid : koreafcaMbrIds) {
										if(loginVO.getMBR_ID().equals(mbrid)) {
											isKoreafcaMember = true;
											break;
										}
									}
								}
								if(isKoreafcaMember) {
									//특정 계정은 예외처리
								} else {
									page = false;
								}
							} else {
								page = false;
							}
						}
						break;
						case "category": page = false; break;//교육카테고리
						case "sms": page = false; break;//SMS관리
						case "tdata": page = false; break;//교육(콘텐츠)자료
						case "analytics": page = false; break;//통계관리
						case "certificate": { //이수증출력관리
							String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
							boolean isKoreafcaMember = false;
							if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
								for (String mbrid : koreafcaMbrIds) {
									if(loginVO.getMBR_ID().equals(mbrid)) {
										isKoreafcaMember = true;
										break;
									}
								}
							}
							if(isKoreafcaMember) {
								//특정 계정은 예외처리
							} else {
								page = false;
							}
						} break;
					}
					
					if(!page){
						LOGGER.debug("교육센터 관리자페이지 -" + uri[2] + "에 접근할 수 있는 권한이 없음!!");
						response.sendRedirect("/eduadm/error/unauth.do");
						return false;
					}
				}
				
				//안내사항 정보 호출
				EduBoardVO eduBoardVO = new EduBoardVO();
				eduBoardVO.setBD_ID("board018");
				eduBoardVO.setBD_LOCK_ST("Y");
				eduBoardVO.setBD_ST("Y");
				eduBoardVO.setNotUsedPagination(true);
				eduBoardVO.setBD_TRGT_CD(loginVO.getMBR_POSITION_CD());
				List<EduBoardVO> list = eduBoardService.boardAdmList(eduBoardVO);
				request.getSession().setAttribute("admNoticeList",list);
				//End of 안내사항 정보 호출				
				
				//보안
				String MBR_SCRTY_KEY = EgovFileScrty.security(loginVO.getMBR_ID(), loginVO.getMBR_ID());
				request.getSession().setAttribute("MBR_SCRTY_KEY",MBR_SCRTY_KEY);
				
			} else {
				
				if(loginVO.getMBR_GRP_1_ST().equals("Y")) {
					response.sendRedirect("/adm/index.do");
					return false;
				} 
				
				LOGGER.debug("교육센터 관리자페이지 - 접근할 수 있는 권한이 없음!!");
				response.sendRedirect("/eduadm/error/unauth.do");
				return false;
			}
		}
		LOGGER.debug("*******************************************************");
		return super.preHandle(request, response, handler);
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
		if(modelAndView!=null) {
			LOGGER.debug("*******************************************************");
			boolean isKoreafcaMember = false;
			LoginVO loginVO = (LoginVO)request.getSession().getAttribute("LoginVO");
			if(loginVO==null || loginVO.getMBR_ID()==null) {
			
			} else {
				if(loginVO.getMBR_POSITION_CD().equals("PCD0004")) {
					String[] koreafcaMbrIds = propertiesService.getString("koreafca.mbrIds").trim().split(",");
					if(koreafcaMbrIds!=null && koreafcaMbrIds.length!=0) {
						for (String mbrid : koreafcaMbrIds) {
							if(mbrid!=null && loginVO.getMBR_ID().equals(mbrid)) {
								isKoreafcaMember = true;
								break;
							}
						}
					}
				}
			}
			modelAndView.addObject("isKoreafcaMember", isKoreafcaMember);
			LOGGER.debug("*******************************************************");
		}		
	}
	
}

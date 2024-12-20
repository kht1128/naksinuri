package egovframework.all.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import egovframework.all.codeset.service.CodeSetService;
import egovframework.all.codeset.service.CodeSetVO;
import egovframework.eduadm.category.service.EduCategoryInsInfVO;
import egovframework.eduadm.category.service.EduCategoryService;
import egovframework.eduadm.certificate.service.EduCertificateService;
import egovframework.eduadm.certificate.service.EduCertificateVO;
import egovframework.eduadm.certificate.web.CreateCertificateToHtmlData;
import egovframework.eduadm.curriculum.service.EduCurriculumService;
import egovframework.eduadm.curriculum.service.EduCurriculumVO;
import egovframework.eduadm.member.service.EduMemberService;
import egovframework.eduadm.member.service.EduMemberVO;
import egovframework.eduadm.myhistory.service.EduMyHistoryService;
import egovframework.eduadm.myhistory.service.EduMyHistoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.seadm.member.service.MemberService;
import egovframework.utils.EgovDateUtil;
import egovframework.utils.EgovDateUtil.RETURN_MIN_TYPE;
import egovframework.utils.EgovStringUtil;
import egovframework.utils.PublicUtils;

/**
 * @Class Name : AllMainController.java
 * @Description : AllMainController Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2019.12.17  김정하         최초생성
 *
 * @author 개발팀
 * @since 2019.12.17
 * @version 1.0
 * @see
 *
 *  Copyright (C) by jhkim All right reserved.
 */

@RestController
@Controller
public class AllApiController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AllApiController.class);
	
	/** EduCertificateService */
	@Resource(name = "eduCertificateService")
	private  EduCertificateService eduCertificateService;
	
	/** EduMemberService */
	@Resource(name = "eduMemberService")
	private  EduMemberService eduMemberService;
	
	/** EduCurriculumService */
	@Resource(name = "eduCurriculumService")
	private EduCurriculumService eduCurriculumService;
	
	/** EduMyHistoryService */
	@Resource(name = "eduMyHistoryService")
	private EduMyHistoryService eduMyHistoryService;
	
	@Resource(name = "eduCategoryService")
	private EduCategoryService eduCategoryService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** memberService */
	@Resource(name = "MemberService")
	private MemberService memberService;
	
	@Resource(name = "codeSetService")
	private CodeSetService codeSetService;
	
	// API 생성
	@ResponseBody
	@RequestMapping(value = "/all/api/apiCrt.do",method = {RequestMethod.GET, RequestMethod.POST})
	public void apiCrt(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		
		EgovStringUtil egovStringUtil = new EgovStringUtil();
		EgovDateUtil egovDateUtil = new EgovDateUtil();
		PublicUtils publicUtils = new PublicUtils();
		
		
		egovStringUtil.AES128Util();
		
		LOGGER.debug("현재시간 기준 서비스키 : " + egovStringUtil.makeScrtyKey(publicUtils.currentTime("yyyyMMddHH")+"_낚시전문교육API"));
		
		JSONObject returnData = new JSONObject(); // return 할 JSON 데이터
		JSONArray jsonData = new JSONArray(); //data
		
		LOGGER.debug(" : " + request);
		
		
		
		

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
 
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
 
        body = stringBuilder.toString();
        
        System.out.println("body:"+body);
		
		
		
		
		
		
		try {
			String apiKey = request.getHeader("svcVldKey"); 
			String strPage = request.getHeader("pageNo"); 
			String reqMbrId = request.getHeader("mberId");
			String reqMbrNm = request.getHeader("mberNm");
			String reqMbrHp = request.getHeader("mberCttpc");
			String reqMbrBirth = request.getHeader("mberBrdt");
			
			LOGGER.debug("[ScrtyKey] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.debug("[ScrtyKey] svcVldKey : "  + request.getHeader("svcVldKey"));
			LOGGER.debug("[ScrtyKey] reqMbrId : "  + request.getHeader("mberId"));
			LOGGER.debug("[ScrtyKey] reqMbrNm : "  + request.getHeader("mberNm"));
			LOGGER.debug("[ScrtyKey] reqMbrHp : "  + request.getHeader("mberCttpc"));
			LOGGER.debug("[ScrtyKey] mberBrdt : "  + request.getHeader("mberBrdt"));
			
			LOGGER.debug("[ScrtyKey] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.debug("[ScrtyKey] svcVldKey : "  + apiKey);
			LOGGER.debug("[ScrtyKey] reqMbrId : "  + reqMbrNm);
			LOGGER.debug("[ScrtyKey] reqMbrNm : "  + reqMbrNm);
			LOGGER.debug("[ScrtyKey] reqMbrHp : "  + reqMbrHp);
			LOGGER.debug("[ScrtyKey] mberBrdt : "  + reqMbrBirth);
			
			LOGGER.debug("[ScrtyKey] @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.debug("[ScrtyKey] svcVldKey : "  + request.getParameter("svcVldKey"));
			LOGGER.debug("[ScrtyKey] reqMbrId : "  + request.getParameter("mberId"));
			LOGGER.debug("[ScrtyKey] reqMbrNm : "  + request.getParameter("mberNm"));
			LOGGER.debug("[ScrtyKey] reqMbrHp : "  + request.getParameter("mberCttpc"));
			LOGGER.debug("[ScrtyKey] mberBrdt : "  + request.getParameter("mberBrdt"));
			
			
		
			
			int page = 0;
			if(EgovStringUtil.isEmpty(strPage)) {
				strPage = "";
			} else {
				page = Integer.parseInt(strPage);
			}
			
			if(EgovStringUtil.isEmpty(reqMbrId)) {
				reqMbrId = "";
			} else {
				LOGGER.debug("[makeScrtyKey] mberId : "  + egovStringUtil.makeScrtyKey(reqMbrId));
				reqMbrId = egovStringUtil.removeScrtyKey(reqMbrId);
			}
			
			if(EgovStringUtil.isEmpty(reqMbrNm)) {
				reqMbrNm = "";
			} else {
				LOGGER.debug("[makeScrtyKey] mberNm : "  + egovStringUtil.makeScrtyKey(reqMbrNm));
				reqMbrNm = egovStringUtil.removeScrtyKey(reqMbrNm);
			}
			
			if(EgovStringUtil.isEmpty(reqMbrHp)) {
				reqMbrHp = "";
			} else {
				LOGGER.debug("[makeScrtyKey] mberCttpc : "  + egovStringUtil.makeScrtyKey(reqMbrHp));
				reqMbrHp = egovStringUtil.removeScrtyKey(reqMbrHp);
			}
			
			if(EgovStringUtil.isEmpty(reqMbrBirth)) {
				reqMbrBirth = "";
			} else {
				LOGGER.debug("[makeScrtyKey] mberBrdt : "  + egovStringUtil.makeScrtyKey(reqMbrBirth));
				reqMbrBirth = egovStringUtil.removeScrtyKey(reqMbrBirth);
			}
			
			LOGGER.debug("[removeScrtyKey] pageNo : " + page);
			LOGGER.debug("[removeScrtyKey] mberId : " + reqMbrId);
			LOGGER.debug("[removeScrtyKey] mberNm : " + reqMbrNm);
			LOGGER.debug("[removeScrtyKey] mberCttpc : " + reqMbrHp);
			LOGGER.debug("[removeScrtyKey] mberBrdt : " + reqMbrBirth);			
			
			apiKey = egovStringUtil.removeScrtyKey(apiKey);
			String parseApiKeyTime = apiKey.substring(0, apiKey.indexOf("_"));
			
			// 현재 날짜/시간
			String beforeDateTime = egovDateUtil.getTimeStringBeforeOrAfterMinute(publicUtils.currentTime("yyyyMMddHHmmss"), "yyyyMMddHHmmss", 3, "yyyyMMddHH", RETURN_MIN_TYPE.before);
			String currentDateTime = publicUtils.currentTime("yyyyMMddHH");
			
			LOGGER.debug("[parseApiKeyTime] " + parseApiKeyTime);
			LOGGER.debug("[beforeDateTime] " + beforeDateTime);
			LOGGER.debug("[currentDateTime] " + currentDateTime);
			
			if(parseApiKeyTime.equals(currentDateTime) || parseApiKeyTime.equals(beforeDateTime)) {
				LOGGER.debug("[서비스키 검증] ok ");
				try {
					EduMemberVO eduMemberInfo = new EduMemberVO();
					eduMemberInfo.setMBR_LV_ID("10");
					eduMemberInfo.setMBR_ST("Y");
					
					if(EgovStringUtil.isEmpty(reqMbrId) && EgovStringUtil.isEmpty(reqMbrNm) && EgovStringUtil.isEmpty(reqMbrHp) && EgovStringUtil.isEmpty(reqMbrBirth)) {
						eduMemberInfo.setRecordCountPerPage(100);
						eduMemberInfo.setFirstIndex(page);
					} else {
						//eduMemberInfo.setNotUsedPagination(true);
						//page = 1;
						eduMemberInfo.setRecordCountPerPage(100);
						eduMemberInfo.setFirstIndex(page);
					}
					
					eduMemberInfo.setMBR_ID(reqMbrId);
					eduMemberInfo.setMBR_NM(reqMbrNm);
					eduMemberInfo.setMBR_HP(reqMbrHp);
					eduMemberInfo.setMBR_BIRTH(reqMbrBirth);
					
					List<EduMemberVO> eduMemberList = eduMemberService.get_api_edu_member_list(eduMemberInfo);
					int member_totcnt = eduMemberService.get_api_edu_member_list_totcnt(eduMemberInfo);
					int totPage = (member_totcnt % 100) == 0 ? member_totcnt / 100 : member_totcnt / 100 + 1;
					
					if (eduMemberList.size() <= 0) {
						returnData.put("errCd", 1);
						returnData.put("mssage", "검색된 데이터가 없습니다.");
						returnData.put("data", jsonData);

					} else {
						
						String html_header = "";
						String html_footer = "";
						
						try {
							html_header = publicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/header.html").toString();
						} catch(Exception e) {
							html_header = "";
						}
						try {
							html_footer = publicUtils.readFile(propertiesService.getString("Globals.fileCommonPath").toString()+"/certificate/fipa/footer.html").toString();
						} catch(Exception e) {
							html_footer = "";
						}
						
						for (EduMemberVO mbrData : eduMemberList) {
							JSONObject infoData = new JSONObject(); 
							String mbrId = mbrData.getMBR_ID();
							String addr = mbrData.getMBR_ADDR1() + mbrData.getMBR_ADDR2();
							
							if(StringUtils.isEmpty(mbrId)) {
								infoData.put("mberId", "");
							} else {
								infoData.put("mberId", egovStringUtil.makeScrtyKey(mbrId));
							}
							if(StringUtils.isEmpty(mbrData.getMBR_NM())) {
								infoData.put("mberNm", "");
							} else {
								infoData.put("mberNm", egovStringUtil.makeScrtyKey(mbrData.getMBR_NM()));
							}
							if(StringUtils.isEmpty(mbrData.getMBR_BIRTH())) {
								infoData.put("mberBrdt", "");
							} else {
								infoData.put("mberBrdt", egovStringUtil.makeScrtyKey(mbrData.getMBR_BIRTH()));
							}
							if(StringUtils.isEmpty(mbrData.getMBR_HP())) {
								infoData.put("mberCttpc", "");
							} else {
								infoData.put("mberCttpc", egovStringUtil.makeScrtyKey(mbrData.getMBR_HP()));	
							}
							if(StringUtils.isEmpty(mbrData.getMBR_ZIPCD())) {
								infoData.put("zip", "");
							} else {
								infoData.put("zip", egovStringUtil.makeScrtyKey(mbrData.getMBR_ZIPCD()));	
							}
							if(StringUtils.isEmpty(addr)) {
								infoData.put("adres", "");
							} else {
								infoData.put("adres", egovStringUtil.makeScrtyKey(addr));	
							}
							
							
							EduCertificateVO eduCertificateVO = new EduCertificateVO();
							eduCertificateVO.setMBR_ID(mbrId);
							
							// ======================== dtl ======================================
							//회원부가상세정보
							EduMemberVO eduMemberDtlVO = new EduMemberVO();
							eduMemberDtlVO.setMBR_ID(mbrId);
							eduMemberDtlVO.setUSE_AT("Y");
							eduMemberDtlVO.setHIDE_AT("N");
							List<EduMemberVO> listMbrDtl = eduMemberService.get_edu_member_dtl_list(eduMemberDtlVO);
							
							List<Map<String, Object>> dtlList = new ArrayList<>(); // dtl
							for (EduMemberVO dtlVO : listMbrDtl) {
								Map<String, Object> dtlData = new HashMap<String, Object>();
								
								CodeSetVO mCodeSetVO = new CodeSetVO();
								
								
								//코드 조회 - 업종구분
								mCodeSetVO.setCD_MASTER_ID("CID00002");
								List<CodeSetVO> list_dtl_cd = codeSetService.get_codeset_list(mCodeSetVO);
								
								if(egovStringUtil.isEmpty(dtlVO.getDTL_CD())) {
									dtlData.put("fshInfoCl", "");
								} else {
									for (CodeSetVO codeVO : list_dtl_cd) {
										if(codeVO.getCD_ID().equals(dtlVO.getDTL_CD())){
											dtlData.put("fshInfoCl", egovStringUtil.makeScrtyKey(codeVO.getCD_NM()));
											break;
										}
									}
								}
								
								//코드 조회 - 시도 구분
								mCodeSetVO.setCD_MASTER_ID("CID00004");
								List<CodeSetVO> list_sido_cd = codeSetService.get_codeset_list(mCodeSetVO);
								
								if(egovStringUtil.isEmpty(dtlVO.getSIDO_CD())) {
									dtlData.put("ctprvnNm", "");
									dtlData.put("ctprvnCd", "");
								} else {
									for (CodeSetVO codeVO : list_sido_cd) {
										if(codeVO.getCD_ID().equals(dtlVO.getSIDO_CD())){
											dtlData.put("ctprvnNm", egovStringUtil.makeScrtyKey(codeVO.getCD_NM()));
											dtlData.put("ctprvnCd", egovStringUtil.makeScrtyKey(codeVO.getCD_ID()));
											break;
										}
									}
								}
								
								//코드 조회 - 시군구 구분
								if(egovStringUtil.isEmpty(dtlVO.getSIDO_CD())) {
									dtlData.put("signguNm", "");
									dtlData.put("signguCd", "");
								} else {
									mCodeSetVO.setCD_MASTER_ID(dtlVO.getSIDO_CD());
									List<CodeSetVO> list_signgu_cd = codeSetService.get_codeset_list(mCodeSetVO);
									
									if(egovStringUtil.isEmpty(dtlVO.getSIGNGU_CD())) {
										dtlData.put("signguNm", "");
										dtlData.put("signguCd", "");
									} else {
										for (CodeSetVO codeVO : list_signgu_cd) {
											if(codeVO.getCD_ID().equals(dtlVO.getSIGNGU_CD())){
												dtlData.put("signguNm", egovStringUtil.makeScrtyKey(codeVO.getCD_NM()));
												dtlData.put("signguCd", egovStringUtil.makeScrtyKey(codeVO.getCD_ID()));
												
												break;
											}
										}
									}
								}
								
								//코드 조회 - 대상구분
								mCodeSetVO.setCD_MASTER_ID("CID00006");
								List<CodeSetVO> list_dtl_license_cd = codeSetService.get_codeset_list(mCodeSetVO);
								
								if(egovStringUtil.isEmpty(dtlVO.getDTL_LICENSE_CD())) {
									dtlData.put("cprClNm", "");
								} else {
									for (CodeSetVO codeVO : list_dtl_license_cd) {
										if(codeVO.getCD_ID().equals(dtlVO.getDTL_LICENSE_CD())){
											dtlData.put("cprClNm", egovStringUtil.makeScrtyKey(codeVO.getCD_NM()));
											break;
										}
									}
								}								
								
								
								String regNumCd = dtlVO.getREG_NUM_CD();
								if (!egovStringUtil.isEmpty(regNumCd) && regNumCd.length() <= 5) {
									regNumCd = regNumCd.substring(0, 4).concat("-").concat(regNumCd.substring(4));
								}
								
								String shipCd = dtlVO.getSHIP_CD();
								if (!egovStringUtil.isEmpty(shipCd) && regNumCd.length() <= 7) {
									shipCd = shipCd.substring(0, 7).concat("-").concat(shipCd.substring(7));
								}
								
								String mbrFshrbtType = dtlVO.getMBR_FSHRBT_TYPE();
								if ("legacy".equals(mbrFshrbtType)) {
									mbrFshrbtType = "기존";
								} else if ("new".equals(mbrFshrbtType)) {
									mbrFshrbtType = "신규";
								} else if ("resmpt".equals(mbrFshrbtType)) {
									mbrFshrbtType = "재개자";
								} else {
									mbrFshrbtType = "";
								}
								
								String mbrEduRspnberType = dtlVO.getMBR_EDU_RSPNBER_TYPE();
								if ("CEO".equals(mbrEduRspnberType)) {
									mbrEduRspnberType = "대표자";
								} else if ("EDU_RSPNBER".equals(mbrEduRspnberType)) {
									mbrEduRspnberType = "교육책임자";
								} else {
									mbrEduRspnberType = "";
								}
								
								String dtlNm = egovStringUtil.isEmpty(dtlVO.getDTL_NM()) ? "" : dtlVO.getDTL_NM();
								String shipLicense = egovStringUtil.isEmpty(dtlVO.getSHIP_LICENSE()) ? "" : dtlVO.getSHIP_LICENSE();
								
								if(StringUtils.isEmpty(regNumCd)) {
									dtlData.put("prmsnDclrNo", ""); // 허가/신고번호
								} else {
									dtlData.put("prmsnDclrNo", egovStringUtil.makeScrtyKey(regNumCd)); // 허가/신고번호	
								}
								if(StringUtils.isEmpty(shipCd)) {
									dtlData.put("fshrbtNo", "");
								} else {
									dtlData.put("fshrbtNo", egovStringUtil.makeScrtyKey(shipCd)); // 어선번호
								}
								if(StringUtils.isEmpty(mbrFshrbtType)) {
									dtlData.put("mberEduTrgtClNm", "");
								} else {
									dtlData.put("mberEduTrgtClNm", egovStringUtil.makeScrtyKey(mbrFshrbtType)); // 신규/기존/재개자
								}
								if(StringUtils.isEmpty(dtlNm)) {
									dtlData.put("holdInfoNm", "");
								} else {
									dtlData.put("holdInfoNm", egovStringUtil.makeScrtyKey(dtlNm)); // 낚시터명/어선명
								}							
								if(StringUtils.isEmpty(mbrEduRspnberType)) {
									dtlData.put("trgtClNm", "");
								} else {
									dtlData.put("trgtClNm", egovStringUtil.makeScrtyKey(mbrEduRspnberType)); // 법인업자구분
								}
								if(StringUtils.isEmpty(shipLicense)) {
									dtlData.put("lcnse", "");
								} else {
									dtlData.put("lcnse", egovStringUtil.makeScrtyKey(shipLicense)); // 해기사자격증
								}
								
								dtlList.add(dtlData);
							}
							
							infoData.put("mberHoldDtls", dtlList);
							// ===================================================================
							
							
							// ======================== edu ======================================
							// 교육이수내역
							EduMyHistoryVO eduMyHistoryVO = new EduMyHistoryVO();
							eduMyHistoryVO.setNotUsedPagination(true);
							eduMyHistoryVO.setMBR_ID(mbrId);
							List<EduMyHistoryVO> eduHistoryList = eduMyHistoryService.get_edu_mbrhstry_list(eduMyHistoryVO);
							
							List<Map<String, Object>> eduList = new ArrayList<>(); // edu
							for (EduMyHistoryVO eduVO : eduHistoryList) {
								Map<String, Object> eduData = new HashMap<String, Object>();
								
								String cmplt_st = eduVO.getLRNNG_CMPLT_ST();
								
								if(egovStringUtil.isEmpty(cmplt_st)) {
									cmplt_st = "N";
								} else {
									if (cmplt_st.equals("0")) {
										cmplt_st = "N";
									} else if (cmplt_st.equals("1")) {
										cmplt_st = "Y";
									} else if (cmplt_st.equals("2")) {
										cmplt_st = "N";
									}
								}
//								eduData.put("lrnngCmpltSt", cmplt_st);
								String htmlCode = get_html_code(request, mbrData, listMbrDtl, eduVO);
								
								String crsYear = egovStringUtil.isEmpty(eduVO.getCRS_STR_DT()) ? "" : eduVO.getCRS_STR_DT().substring(0,4);
								String crsNm = egovStringUtil.isEmpty(eduVO.getCRS_NM()) ? "" : eduVO.getCRS_NM();
								String cmpltDt = egovStringUtil.isEmpty(eduVO.getLRNNG_CMPLT_DT()) ? "" : eduVO.getLRNNG_CMPLT_DT();
								
								if(StringUtils.isEmpty(crsYear)) {
									eduData.put("eduYear", "");
								} else {
									eduData.put("eduYear", egovStringUtil.makeScrtyKey(crsYear));
								}
								if(StringUtils.isEmpty(crsNm)) {
									eduData.put("eduNm", "");								
								} else {
									eduData.put("eduNm", egovStringUtil.makeScrtyKey(crsNm));
								}
								if(StringUtils.isEmpty(cmplt_st)) {
									eduData.put("eduComplYn", "");
								} else {
									eduData.put("eduComplYn", egovStringUtil.makeScrtyKey(cmplt_st));
								}
								if(StringUtils.isEmpty(cmpltDt)) {
									eduData.put("eduComplDt", "");
								} else {
									eduData.put("eduComplDt", egovStringUtil.makeScrtyKey(cmpltDt));
								}								
								if(StringUtils.isEmpty(html_header+htmlCode+html_footer)) {
									eduData.put("crtfHtmlData", "");
								} else {
									eduData.put("crtfHtmlData", egovStringUtil.makeScrtyKey(html_header+htmlCode+html_footer));
								}
								
								eduList.add(eduData);
							}
							infoData.put("eduDtls", eduList);
							// ===================================================================
							
							jsonData.add(infoData);
							
						}
					
						returnData.put("errCd", 0);
						returnData.put("mssage", "데이터 처리 정상");
						returnData.put("totCnt", member_totcnt);
						returnData.put("nowPage", page);
						returnData.put("totPage", totPage);
						returnData.put("data", jsonData);
						
					}
					
				} catch(Exception e) {
					
					returnData.put("errCd", 1);
					returnData.put("mssage", e.toString());
					returnData.put("totCnt", 0);
					returnData.put("nowPage", 0);
					returnData.put("totPage", 0);
					returnData.put("data", jsonData);

				}
			} else {
				LOGGER.debug("[서비스키 검증] fail ");
				
				returnData.put("errCd", 1);
				returnData.put("mssage", "apiKey Error");
				returnData.put("totCnt", 0);
				returnData.put("nowPage", 0);
				returnData.put("totPage", 0);
				returnData.put("data", jsonData);
				
				LOGGER.debug(returnData.toString());

			}
		} catch (StringIndexOutOfBoundsException strException) {
						
			returnData.put("errCd", 1);
			returnData.put("mssage", "올바르지 않은 api키 입니다.");
			returnData.put("totCnt", 0);
			returnData.put("nowPage", 0);
			returnData.put("totPage", 0);
			returnData.put("data", jsonData);
						
			LOGGER.debug(returnData.toString());
			
		} catch (Exception e) {
			
			returnData.put("errCd", 1);
			returnData.put("mssage", "잘못된 접근입니다.");
			returnData.put("totCnt", 0);
			returnData.put("nowPage", 0);
			returnData.put("totPage", 0);
			returnData.put("data", jsonData);
			
			LOGGER.debug(returnData.toString());

		}
				
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(returnData);
		
		return;
	}
	
	public String get_html_code(HttpServletRequest request, EduMemberVO mbrData, 
			List<EduMemberVO> listMbrDtl, EduMyHistoryVO chkEduMyHistoryVO) throws Exception {
		
		EduCertificateVO eduCertificateVO = new EduCertificateVO();
		eduCertificateVO.setMBR_ID(mbrData.getMBR_ID());
		eduCertificateVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
		eduCertificateVO.setHMBR_SN(chkEduMyHistoryVO.getHMBR_SN());
		
		EduCertificateVO info = eduCertificateService.get_edu_certificate_info(eduCertificateVO);
//		
		if (!"1".equals(chkEduMyHistoryVO.getLRNNG_CMPLT_ST())) {
			return "";
		}
		
		if(info == null || info.getCRTF_SN() == null || info.getCRTF_SN().length() == 0) {
			eduCertificateVO.setCRTF_CD(eduCertificateVO.getUniqKey());
		} else {
			eduCertificateVO.setCRTF_CD(info.getCRTF_CD());
		}
		
		try {
			//교육과정정보
			EduCurriculumVO eduCurriculumVO = new EduCurriculumVO();
			eduCurriculumVO.setCRS_SN(chkEduMyHistoryVO.getCRS_SN());
			eduCurriculumVO = eduCurriculumService.get_edu_curriculum_info(eduCurriculumVO);
			//점수 재계산
			chkEduMyHistoryVO.setHMBR_INPUT_TIME(eduCurriculumVO.getCRS_TOT_TIME());
			chkEduMyHistoryVO.setHMBR_INPUT_POINT(eduCurriculumVO.getCRS_TOT_POINT());
			int LRNNG_TOT_TIME = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_TIME());//교과목이수종합시간
			int LRNNG_TOT_POINT = Integer.parseInt(chkEduMyHistoryVO.getLRNNG_TOT_POINT());//교과목이수종합점수
			int HMBR_INPUT_TIME = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_TIME());//최대인정교육시간
			int HMBR_INPUT_POINT = Integer.parseInt(chkEduMyHistoryVO.getHMBR_INPUT_POINT());//최대인정교육점수
			int HMBR_RCGNT_TIME = HMBR_INPUT_TIME+LRNNG_TOT_TIME;//인정된 교육최종시간
			int HMBR_RCGNT_POINT = HMBR_INPUT_POINT+LRNNG_TOT_POINT;//인정된 교육최종점수
			chkEduMyHistoryVO.setHMBR_RCGNT_TIME(String.valueOf(HMBR_RCGNT_TIME));
			chkEduMyHistoryVO.setHMBR_RCGNT_POINT(String.valueOf(HMBR_RCGNT_POINT));
			//발급기관정보
			EduCategoryInsInfVO eduCategoryInsInfVO = new EduCategoryInsInfVO();
			eduCategoryInsInfVO.setCAT_INS_SN(eduCurriculumVO.getCAT_INS_SN());
			eduCategoryInsInfVO = eduCategoryService.get_edu_category_ins_inf_info(eduCategoryInsInfVO);
			//새로 발급할 이수증
			
			eduCertificateVO.setREG_MBR_ID(mbrData.getMBR_ID());
			eduCertificateVO.setUPD_MBR_ID(mbrData.getMBR_ID());			
			
			String html_data = new CreateCertificateToHtmlData(
									request,
									propertiesService,
									mbrData,
									listMbrDtl,
									eduCertificateVO,
									chkEduMyHistoryVO,
									eduCurriculumVO,
									eduCategoryInsInfVO).toDocument();
			return html_data;
			
		} catch(Exception e) {
			return "";
		}
		
		
	}
	
}



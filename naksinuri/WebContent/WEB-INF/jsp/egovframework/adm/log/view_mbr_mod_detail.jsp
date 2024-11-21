<%@page import="kcb.org.json.JSONArray"%>
<%@page import="kcb.org.json.JSONObject"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pfpu"   uri="customtaglib/pfpu.tld"%>

<%
String logTableCellMbrData = "["+
                       	"{label:'회원일련번호',id:'mbr_sn',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'아이디',id:'mbr_id',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'이름',id:'mbr_nm',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'닉네임(호칭)',id:'mbr_ncnm',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'연락처(일반)',id:'mbr_tel',code:'n',adm:'n',inputtype:'',gubun:'',pattern:'t'},"+
                       	"{label:'연락처(휴대폰)',id:'mbr_hp',code:'n',adm:'n',inputtype:'',gubun:'',pattern:'t'},"+
                       	"{label:'이메일',id:'mbr_email',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'주소',id:'mbr_addr1',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'상세주소',id:'mbr_addr2',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'우편번호',id:'mbr_zipcd',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'마지막접속시간',id:'mbr_last_con_tm',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'유형코드',id:'mbr_type_cd',code:'y',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'생년월일',id:'mbr_birth',code:'n',adm:'n',inputtype:'',gubun:'',pattern:'b'},"+
                       	"{label:'직급코드',id:'mbr_position_cd',code:'y',adm:'n',inputtype:'',gubun:'',pattern:''},"+ 	
                       	"{label:'사용여부',id:'mbr_st',code:'n',adm:'n',inputtype:'',gubun:'N:비활성,Y:사용중,R:승인대기,F:승인거부',pattern:''},"+
                       	"{label:'등록일자',id:'mbr_reg_dt',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'변경일자',id:'mbr_mod_dt',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'작성자아이디',id:'reg_mbr_id',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'수정자아이디',id:'upd_mbr_id',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'관리주체',id:'mbr_reg_type_cd',code:'y',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'메모기록',id:'mbr_dscrp',code:'n',adm:'n',inputtype:'textarea',gubun:'',pattern:''},"+
                       	//관리자용
                       	"{label:'소속명',id:'mbr_psitn_nm',code:'n',adm:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'지역제한(시도)',id:'mbr_sido_cd',code:'y',adm:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'지역제한(시군구)',id:'mbr_signgu_cd',code:'y',adm:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'교육기관제한',id:'mbr_edu_ins_cd',code:'n',adm:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'교육대상제한',id:'mbr_trgt_cd',code:'y',adm:'y',inputtype:'',gubun:'',pattern:''},"+
                       	//14세미만 보호자 동의 정보
                       	"{label:'14세 미만 여부',id:'under_age_14_st',code:'n',adm:'n',inputtype:'',gubun:'N:해당안함,Y:해당',pattern:''},"+
                       	"{label:'보호자 이름',id:'parnts_mbr_nm',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'보호자 연락처',id:'parnts_mbr_hp',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'보호자 생년월일',id:'parnts_mbr_birth',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'보호자 관계',id:'parnts_mbr_relationship',code:'n',adm:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"]";
String logTableCellMbrDtlData = "["+
                       	"{label:'상세정보일련번호',id:'dtl_sn',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'이름',id:'mbr_nm',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'대상구분',id:'dtl_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'어선명<br>/낚시터명',id:'dtl_nm',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'관리주체',id:'reg_type_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'대상구분',id:'dtl_license_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'어선(혹은 낚시터)소재지 시도',id:'sido_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'어선(혹은 낚시터)소재지 시군구',id:'signgu_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'어선(혹은 낚시터)소재지 읍면동명',id:'ymd_nm',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'주소',id:'dtl_addr',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'신고번호(어선)<br>/허가(등록)번호(낚시터)',id:'reg_num_cd',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시터 업구분',id:'fshlc_work_cd',code:'y',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시터 적용수면',id:'fshlc_applc',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 어선번호',id:'ship_cd',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 총톤수',id:'ship_grtg',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 선적항',id:'ship_prload',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 최대승객',id:'ship_max_psnger',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 최대선원',id:'ship_max_crew',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'낚시어선 해기사면허',id:'ship_license',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'유효시작일자',id:'ship_license_str_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'유효만료일자',id:'ship_license_end_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'사용여부',id:'use_at',code:'n',inputtype:'',gubun:'0:사용안함,1:사용중',pattern:''},"+
                       	"{label:'삭제여부',id:'del_at',code:'n',inputtype:'',gubun:'0:정상,1:삭제됨',pattern:''},"+
                       	"{label:'등록일자',id:'reg_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'변경일자',id:'upd_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'작성자아이디',id:'reg_mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'수정자아이디',id:'upd_mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"]";
String logTableCellMbrHstryData = "["+
                       	"{label:'이수내역번호',id:'hmbr_sn',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'회원아이디',id:'mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'회원이름',id:'mbr_nm',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'교육정보1단계번호',id:'crs_sn',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'신청상태',id:'lrnng_st',code:'n',inputtype:'',gubun:'0:대기,1:승인(이수증외부출력가능),2:취소,3:강제취소,4:보류',pattern:''},"+
                       	"{label:'학습진행률(%)',id:'lrnng_prgrs',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'이수상태',id:'lrnng_cmplt_st',code:'n',inputtype:'',gubun:'0:이수전,1:이수완료(이수증발급완료),2:이수취소',pattern:''},"+
                       	"{label:'이수완료-일자',id:'lrnng_cmplt_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'이수완료-담당자아이디',id:'lrnng_cmplt_mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'생성일자',id:'reg_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'변경일자',id:'upd_dt',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'작성자아이디',id:'reg_mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'수정자아이디',id:'upd_mbr_id',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"{label:'사용여부',id:'ues_st',code:'n',inputtype:'',gubun:'0:사용안함,1:사용중',pattern:''},"+
                       	"{label:'삭제여부',id:'del_st',code:'n',inputtype:'',gubun:'0:정상,1:삭제됨',pattern:''},"+
                       	"{label:'총교육시간',id:'hmbr_rcgnt_time',code:'n',inputtype:'',gubun:'',pattern:''},"+
                       	"]";
request.getSession().setAttribute("logTableCellMbrData", new JSONArray(logTableCellMbrData));
request.getSession().setAttribute("logTableCellMbrDtlData", new JSONArray(logTableCellMbrDtlData));
request.getSession().setAttribute("logTableCellMbrHstryData", new JSONArray(logTableCellMbrHstryData));
%>
	<p class="pb-20">
		<span class="font-weight-800 cyan-700">${info.MBR_NM }(${info.MBR_NCNM })</span>
		<span class="ml-5 mr-5">
			<c:choose>
			<c:when test="${info.LOG_TYPE eq 'MBR_TB'}">회원기본정보</c:when>
			<c:when test="${info.LOG_TYPE eq 'MBR_DTL_TB'}">회원추가상세정보</c:when>
			<c:when test="${info.LOG_TYPE eq 'EDU_MBR_HSTRY_TB'}">교육수강생</c:when>
			</c:choose>
		</span>
		<span class="font-weight-800 orange-700">${info.LOG_DSCRP}</span>
	</p>
	<div class="alertify-ajax-scroll-body scroll-y">
	<table class="w-p100">
		<colgroup>
			<col width="24%"/>
			<col width="38%"/>
			<col width="38%"/>
		</colgroup>
		<thead>
			<tr>
				<td class="text-center border border-default bg-grey-200">항목</td>
				<td class="text-center border border-default bg-grey-200">변경 전</td>
				<td class="text-center border border-default bg-grey-200">변경 후</td>
			</tr>
		</thead>
		<tbody>	
		<c:choose>
		 	<c:when test="${info.LOG_TYPE eq 'MBR_DTL_TB'}"><%-- 회원추가상세정보 --%>
		 		<c:forEach var="i" begin="0" end="${logTableCellMbrDtlData.length()-1}">
		 			<c:set var="item" value="${logTableCellMbrDtlData.getJSONObject(i)}"/>
		 			<c:set var="_label" value="${item.getString('label')}"/>
		 			<c:set var="_id" value="${item.getString('id')}"/>
		 			<c:set var="_code" value="${item.getString('code')}"/>
		 			<c:set var="_inputtype" value="${item.getString('inputtype')}"/>
		 			<c:set var="_gubun" value="${item.getString('gubun')}"/>
		 			<c:set var="_pattern" value="${item.getString('pattern')}"/>
		 			<c:set var="item_old" value="${fn:trim(info_old[_id])}"/>
		 			<c:set var="item_new" value="${fn:trim(info_new[_id])}"/>
				 	<tr>
				 		<td class="text-center border border-default">${_label}</td>
				 		<td class="text-center border border-default">
				 			<c:if test="${not empty info_old}">
					 			<c:set var="item_old_str" value="${item_old}"/>		
					 			<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_old}"><c:set var="item_old_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
					 				<c:otherwise><c:set var="item_old_str" value="${item_old}"/></c:otherwise>
					 			</c:choose>	
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_old_str}"><c:set var="item_old_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>			
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_old_str" value="${pfpu:birthHypen(item_old_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_old_str" value="${pfpu:phoneHypen(item_old_str)}"/></c:if>
					 			</c:if>	 			
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white border-0 " disabled>${item_old_str}</textarea></c:when>
						 			<c:otherwise>${item_old_str}</c:otherwise>
					 			</c:choose>	
					 		</c:if>			 			
				 		</td>
						<td class="text-center border border-default">
							<c:if test="${not empty info_new}">
								<c:set var="item_new_str" value="${item_new}"/>	
								<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_new}"><c:set var="item_new_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
						 			<c:otherwise><c:set var="item_new_str" value="${item_new}"/></c:otherwise>
					 			</c:choose>
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_new_str}"><c:set var="item_new_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_new_str" value="${pfpu:birthHypen(item_new_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_new_str" value="${pfpu:phoneHypen(item_new_str)}"/></c:if>
					 			</c:if>	 
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white <c:if test="${item_old eq item_new}">border-0</c:if> <c:if test="${item_old ne item_new}">border-danger</c:if>" disabled>${item_new_str}</textarea></c:when>
						 			<c:otherwise><span class="<c:if test="${item_old ne item_new}">red-600</c:if>">${item_new_str}</span></c:otherwise>
					 			</c:choose>	
				 			</c:if>
						</td>
					</tr>
		 		</c:forEach>
		 	</c:when>
		 	<c:when test="${info.LOG_TYPE eq 'MBR_TB'}"><%-- 회원기본정보 --%>
		 		<c:forEach var="i" begin="0" end="${logTableCellMbrData.length()-1}">
		 			<c:set var="item" value="${logTableCellMbrData.getJSONObject(i)}"/>
		 			<c:set var="_label" value="${item.getString('label')}"/>
		 			<c:set var="_id" value="${item.getString('id')}"/>
		 			<c:set var="_code" value="${item.getString('code')}"/>
		 			<c:set var="_inputtype" value="${item.getString('inputtype')}"/>
		 			<c:set var="_gubun" value="${item.getString('gubun')}"/>
		 			<c:set var="_adm" value="${item.getString('adm')}"/>
		 			<c:set var="_pattern" value="${item.getString('pattern')}"/>
		 			<c:set var="item_old" value="${fn:trim(info_old[_id])}"/>
		 			<c:set var="item_new" value="${fn:trim(info_new[_id])}"/>
		 			<c:set var="isPass" value="false"/>
		 			<c:if test="${_adm eq 'y' and info_old['mbr_lv_id'] eq '10'}"><c:set var="isPass" value="true"/></c:if>
		 			<c:if test="${isPass eq false}">
		 			<tr>
				 		<td class="text-center border border-default">${_label}</td>
				 		<td class="text-center border border-default">
				 			<c:if test="${not empty info_old}">
					 			<c:set var="item_old_str" value="${item_old}"/>		
					 			<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_old}"><c:set var="item_old_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
					 				<c:otherwise><c:set var="item_old_str" value="${item_old}"/></c:otherwise>
					 			</c:choose>
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_old_str}"><c:set var="item_old_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>		
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_old_str" value="${pfpu:birthHypen(item_old_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_old_str" value="${pfpu:phoneHypen(item_old_str)}"/></c:if>
					 			</c:if>	 			 			
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white border-0 " disabled>${item_old_str}</textarea></c:when>
						 			<c:otherwise>${item_old_str}</c:otherwise>
					 			</c:choose>		
				 			</c:if>		 			
				 		</td>
						<td class="text-center border border-default">
							<c:if test="${not empty info_new}">
								<c:set var="item_new_str" value="${item_new}"/>	
								<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_new}"><c:set var="item_new_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
						 			<c:otherwise><c:set var="item_new_str" value="${item_new}"/></c:otherwise>
					 			</c:choose>
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_new_str}"><c:set var="item_new_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_new_str" value="${pfpu:birthHypen(item_new_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_new_str" value="${pfpu:phoneHypen(item_new_str)}"/></c:if>
					 			</c:if>	 
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white <c:if test="${item_old eq item_new}">border-0</c:if> <c:if test="${item_old ne item_new}">border-danger</c:if>" disabled>${item_new_str}</textarea></c:when>
						 			<c:otherwise><span class="<c:if test="${item_old ne item_new}">red-600</c:if>">${item_new_str}</span></c:otherwise>
					 			</c:choose>
					 		</c:if>	
						</td>
					</tr>
					</c:if>
		 		</c:forEach>
		 	</c:when>
		 	<c:when test="${info.LOG_TYPE eq 'EDU_MBR_HSTRY_TB'}"><%-- 교육수강생 --%>
		 		<c:forEach var="i" begin="0" end="${logTableCellMbrHstryData.length()-1}">
		 			<c:set var="item" value="${logTableCellMbrHstryData.getJSONObject(i)}"/>
		 			<c:set var="_label" value="${item.getString('label')}"/>
		 			<c:set var="_id" value="${item.getString('id')}"/>
		 			<c:set var="_code" value="${item.getString('code')}"/>
		 			<c:set var="_inputtype" value="${item.getString('inputtype')}"/>
		 			<c:set var="_gubun" value="${item.getString('gubun')}"/>
		 			<c:set var="_pattern" value="${item.getString('pattern')}"/>
		 			<c:set var="item_old" value="${fn:trim(info_old[_id])}"/>
		 			<c:set var="item_new" value="${fn:trim(info_new[_id])}"/>
				 	<tr>
				 		<td class="text-center border border-default">${_label}</td>
				 		<td class="text-center border border-default">
				 			<c:if test="${not empty info_old}">
					 			<c:set var="item_old_str" value="${item_old}"/>		
					 			<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_old}"><c:set var="item_old_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
					 				<c:otherwise><c:set var="item_old_str" value="${item_old}"/></c:otherwise>
					 			</c:choose>	
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_old_str}"><c:set var="item_old_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>		
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_old_str" value="${pfpu:birthHypen(item_old_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_old_str" value="${pfpu:phoneHypen(item_old_str)}"/></c:if>
					 			</c:if>	 	 			
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white border-0 " disabled>${item_old_str}</textarea></c:when>
						 			<c:otherwise>${item_old_str}</c:otherwise>
					 			</c:choose>
					 		</c:if>				 			
				 		</td>
						<td class="text-center border border-default">
							<c:if test="${not empty info_new}">
								<c:set var="item_new_str" value="${item_new}"/>	
								<c:choose>
						 			<c:when test="${_code eq 'y'}">
						 				<c:forEach var="item_category" items="${list_all_cd}">
						 					<c:if test="${item_category.CD_ID eq item_new}"><c:set var="item_new_str" value="${item_category.CD_NM}"/></c:if>
						 				</c:forEach>
						 			</c:when>
						 			<c:otherwise><c:set var="item_new_str" value="${item_new}"/></c:otherwise>
					 			</c:choose>
					 			<c:if test="${not empty _gubun}">
					 				<c:forEach var="item_gubun" items="${fn:split(_gubun,',')}">
					 					<c:set var="item_gubun_key" value="${fn:split(item_gubun,':')[0]}"/>
					 					<c:set var="item_gubun_val" value="${fn:split(item_gubun,':')[1]}"/>
					 					<c:if test="${item_gubun_key eq item_new_str}"><c:set var="item_new_str" value="${item_gubun_val}"/></c:if>
					 				</c:forEach>
					 			</c:if>
					 			<c:if test="${not empty _pattern}">
					 				<c:if test="${_pattern eq 'b'}"><c:set var="item_new_str" value="${pfpu:birthHypen(item_new_str)}"/></c:if>
					 				<c:if test="${_pattern eq 't'}"><c:set var="item_new_str" value="${pfpu:phoneHypen(item_new_str)}"/></c:if>
					 			</c:if>	 
					 			<c:choose>
						 			<c:when test="${_inputtype eq 'textarea'}"><textarea class="form-control bg-white <c:if test="${item_old eq item_new}">border-0</c:if> <c:if test="${item_old ne item_new}">border-danger</c:if>" disabled>${item_new_str}</textarea></c:when>
						 			<c:otherwise><span class="<c:if test="${item_old ne item_new}">red-600</c:if>">${item_new_str}</span></c:otherwise>
					 			</c:choose>	
					 		</c:if>
						</td>
					</tr>
		 		</c:forEach>
		 	</c:when>
		 	<c:otherwise>
		 		<tr class=""><td class="text-center border border-default" colspan="3">해당되는 정보가 없습니다.</td></tr>
		 	</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	</div>
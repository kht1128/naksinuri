<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@include file="../../naksinuri_original/naksinuri/layout/newHead.jsp"%>

<style>
	.bg-emphasis {background-color:#f9f2f4; padding: 25px; border-radius: 4px;}
	.emphasis {color: #B75552;font-size: 1.7rem;}
	.emphasis2 {color: #fff;font-size: 1.8rem;background-color: #C00000;line-height: 4.3rem;padding: 6px;}
	.line-h18 {line-height: 1.8rem;}
	.emphasis3 {color: #fff; background-color: #000;display: inline-block;padding: 3px 5px; font-weight: bold;}
	#writeForm ul li {list-style: disc;}
	.light-red {color: #B75552;}
</style>

<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet> 

<form:form commandName="CodeSetVO" id="modalMemberViewFormSido" name="modalMemberViewFormSido" method="post">
<input type="hidden" name="CD_MASTER_ID" value=""/>
</form:form>

<script>
function auto_view_sel_area_sido(target_id,sido_cd,signgu_cd) {
	var target = $('#'+target_id);
	var val = sido_cd;
	var form = document.getElementById('modalMemberViewFormSido');
	form.CD_MASTER_ID.value = val;
	form.action = '';
	if(val=='') {
		target.html('');
	} else {
		$.ajax({
			type:"POST",
			url :"/all/code.do",
			data:$('#modalMemberViewFormSido').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				if(data.error == '1') {
					//alertify.alert(data.msg);
				} else {
					var json = JSON.parse(data.rawdata);
					var htmlString = '';
					for (i=0; i<json.length; i++) {	
						var item = json[i];
						if(item.cd_id == signgu_cd) {
							htmlString += item.cd_nm;
						}
					}
					target.html(htmlString);
				}
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	}
}
</script>


<jsp:useBean id="toDay" class="java.util.Date" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>


<fmt:parseDate value="${fn:replace(info_crs.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info_crs.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info_crs.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:parseDate value="${fn:replace(info_crs.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd (E) HH시 부터" var="CRS_STR_DT"/>
<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd (E) HH시 까지" var="CRS_END_DT"/>
<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy-MM-dd (E HH:mm)" var="RECRUIT_STR_DT"/>
<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy-MM-dd (E HH:mm)" var="RECRUIT_END_DT"/>
<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy-MM-dd" var="STR_DT"/>
<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy-MM-dd" var="END_DT"/>
          		 		
<!-- 교육분류에 따른 표기 처리 : start -->
<c:choose>
	<c:when test="${info_crs.CRS_TYPE eq 'fshsk_trnng'}">
		<c:set var="label_crs_time" value="개월" />
		<c:set var="is_allow_online" value="0" />
	</c:when>
	<c:otherwise>
		<c:set var="label_crs_time" value="시간" />
		<c:set var="is_allow_online" value="1" />
	</c:otherwise>											
</c:choose>
<!-- 교육분류에 따른 표기 처리 : end -->

<%-- 기본 데이터 처리 : start --%>
<c:set var="CRS_MBR_NM" value=""/>
<c:if test="${not empty info_crs.CRS_MBR_CD}">
	<c:forEach var="item" items="${list_mbr_cd}">
		<c:if test="${info_crs.CRS_MBR_CD eq item.CD_ID}">
			<c:set var="CRS_MBR_NM" value="${item.CD_DES}"/>
		</c:if>
	</c:forEach>
</c:if>
<%-- 기본 데이터 처리 : end --%>

<form:form commandName="myHistoryVO" id="listForm" name="listForm" method="post">
	<input type="hidden" id="CRS_SN" name="CRS_SN" value="${info_crs.CRS_SN}"/>
	<input type="hidden" id="STR_DT" name="STR_DT" value="${STR_DT}"/>
	<input type="hidden" id="END_DT" name="END_DT" value="${END_DT}"/>
	<%--[보안점검수정][START]#################################################### --%>
	<input type="hidden" id="MBR_ID" name="MBR_ID" value="${info_mbr.MBR_ID}"/>
	<%--[보안점검수정][END]#################################################### --%>
	<div class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>교육정보</h2>
			<div class="agree_box">
				<%-- <c:if test="${info_crs.CRS_MBR_CD eq 'CIDN010200'}">
					<p class="dottedbox"><b>수강신청문의</b> (전화) 031-227-0745 | (팩스) 031-675-0744 | (이메일) kfcal1993@naver.com</p>
				</c:if>
				<c:if test="${info_crs.CRS_MBR_CD eq 'CIDN010300'}"> --%>
					<p class="dottedbox"><b>수강신청문의</b> (전화) 1833-7139 | (팩스) 0505-742-1004 | (이메일) naksinuri@fipa.or.kr</p>
				<%-- </c:if> --%>
				<table class="basic_tbl listTypeA mt-10">
					<caption>교육정보</caption>
					<colgroup>
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>
						<tr>
							<th>교육지역</th>
							<td>
								<c:forEach var="item" items="${list_edu_grp_cd}">
			        				<c:if test="${info_crs.CRS_GRP_CD eq item.CD_ID}">${item.CD_NM}</c:if>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<th>교육대상</th>
							<td>
								<!--<c:forEach var="item" items="${list_mbr_cd}">
			        				<c:if test="${info_crs.CRS_MBR_CD eq item.CD_ID}">${item.CD_NM}</c:if>
								</c:forEach>-->
								
								<c:forEach var="item" items="${list_mbr_cd}">
								  <c:choose>
									<c:when test="${info_crs.CRS_MBR_CD eq item.CD_ID}">
									  <c:choose>
										<c:when test="${item.CD_NM eq '낚시터업자'}">
										  ${item.CD_NM}
										</c:when>
										<c:when test="${item.CD_NM eq '낚시어선업자' and info2.TYPE_GB eq 'offline' and info2.CRS_LAW_TYPE ne 'default'}">
												${item.CD_NM}&nbsp &nbsp &nbsp※「낚시관리법」 제25조(낚시어선업의 신고)에 따른 낚시어선업을 하려는 자, 제38조(영업의 폐쇄 등)제1항제5호(안전사고 발생)에 해당하여 영업정지명령을 받은 후 영업을 재개하려는 자
										</c:when>
										<c:otherwise>
												${item.CD_NM}
												<!-- 다른 조건이 있을 경우 처리 -->
										</c:otherwise>
									  </c:choose>
									</c:when>
								  </c:choose>
								</c:forEach>
							</td>
						</tr>
						<%--
						<tr>
							<th>교육분류</th>
							<td>
								<c:choose>
									<c:when test="${info_crs.CRS_TYPE eq 'fshsk_trnng'}"><c:set var="crs_type_str" value="귀어창업기술교육"/></c:when>
									<c:when test="${info_crs.CRS_TYPE eq 'wknd_trnng'}"><c:set var="crs_type_str" value="주말교육"/></c:when>
									<c:when test="${info_crs.CRS_TYPE eq 'default'}"><c:set var="crs_type_str" value="종합교육"/></c:when>
									<c:when test="${info_crs.CRS_TYPE eq 'default_online'}"><c:set var="crs_type_str" value="온라인강좌"/></c:when>
									<c:otherwise><c:set var="crs_type_str" value="기타"/></c:otherwise>									
								</c:choose>
								<input type="text" class="basic_input w100 readonly" value="${crs_type_str}" disabled>
							</td>
						</tr>
						 --%>
						 <tr>
							<th>교육과정명</th>
							<td>${info_crs.CRS_NM}</td>
						</tr>
						 <c:choose>
							<c:when test="${info_crs.CRS_GRP_CD eq 'CIDE00000000000'}">
								<tr>
									<th>신청기간</th>
									<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
									<%-- <td>${RECRUIT_STR_DT} ~ 집합교육 재개 시까지</td> --%>
								</tr>
								<tr>
									<th>교육일시</th>
									<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
									<!-- <td>상시(집합교육 재개 시까지 한시 운영)</td> -->
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<th>신청기간</th>
									<td>${RECRUIT_STR_DT} ~ ${RECRUIT_END_DT}</td>
								</tr>
								<tr>
									<th>교육일시</th>
									<td>${CRS_STR_DT} ~ ${CRS_END_DT}</td>
								</tr>
								<tr>
									<th>교육기관</th>
									<td>${info_crs.CAT_INS_NM}</td>
								</tr>
								<tr>
									<th>교육장소</th>
									<td>${info_crs.CRS_PLACE}</td>
								</tr>
								<tr>
									<th>교육장상세주소</th>
									<td>${info_crs.CRS_ADDR}</td>
								</tr>
								<tr>
									<th>모집최대인원</th>
									<td>${info_crs.MBR_MAX_CNT} 명</td>
								</tr>
							</c:otherwise>
						</c:choose>
						<c:if test="${not empty info_crs.CRS_DSCRP}">
						<tr>
							<th>안내사항</th>
							<td>
								${fn:replace(info_crs.CRS_DSCRP, lf, "<br/>")}
							</td>
						</tr>
						</c:if>
						<c:if test="${not empty info_crs.CRS_SCHDL_FILE_SN}">
						<tr>
							<th>교육시간표</th>
							<td>
								<img src="/cmm/fms/getImage.do?atchFileId=${info_crs.CRS_SCHDL_FILE_SN}&fileSn=0" alt="교육시간표"/>
							</td>
						</tr>
						</c:if>		
						<c:if test="${info_crs.CRS_LOCK_AREA_ST eq '1'}">
						<tr>
							<th>신청지역제한</th>
							<td class="green-600 font-weight-bold">
								<c:forEach var="item" items="${list_lock_area_ship_grp_cd}">
									<c:if test="${info_crs.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
								</c:forEach>
								<c:forEach var="item" items="${list_lock_area_house_grp_cd}">
									<c:if test="${info_crs.CRS_LOCK_AREA_CD eq item.CD_ID}">${item.CD_NM}</c:if>
								</c:forEach>	
								<p class="red-600 mt-10">(신고지에 따라 신청지역제한으로 수강신청이 불가능 할 수 있습니다.)</p>	
							</td>
						</tr>					
						</c:if>			
					</tbody>
				</table>
			</div>
		</section>
		<section id="writeForm" class="write_box mt-30">
			<h2>${CRS_MBR_NM } 전문교육 수강신청서</h2>
			<div class="agree_box">
				<h3 class="">기본회원정보</h3>
				<table class="basic_tbl listTypeA mt-10">
					<caption>수강신청서 양식</caption>
					<colgroup>
						<col width="140" />
						<col />
						<col width="140" />
						<col />
					</colgroup>
					<thead><tr class="table-cell-blind"><th></th></tr></thead>
					<tbody>		
						<tr>
							<th class="text-center">성 명</th>
							<td><input type="text" class="basic_input w100 readonly" name="MBR_NM" value="${info_mbr.MBR_NM}" disabled title="성명"></td>
							<th class="text-center">생년월일</th>
							<td><input type="text" class="basic_input w100 readonly" value="${info_mbr.MBR_BIRTH}" disabled title="생년월일"></td>
						</tr>
						<tr>
							<th class="text-center">주 소</th>
							<td colspan="3"><input type="text" class="basic_input w100 readonly" value="${info_mbr.MBR_ADDR1} ${info_mbr.MBR_ADDR2}" disabled title="주소"></td>
						</tr>
						<tr>
							<th class="text-center">연 락 처</th>
							<td colspan="3"><input type="text" name="MBR_HP" class="basic_input w100 readonly" value="${info_mbr.MBR_HP}" disabled title="연락처"></td>
						</tr>						
					</tbody>
				</table>
				<h3 class="mt-30">추가상세정보</h3>
				<c:choose>
					<c:when test="${info_crs.CRS_MBR_CD eq 'CIDN010200'}">
						
						<c:if test="${empty list_mbr_dtl}">
							<p class="dottedbox text-center pt-30 pb-30">등록 된 낚시터 추가 상세정보가 없습니다.<br><br>낚시터 정보가 없을 경우 낚시터 전문교육 신청이 불가능합니다.<br><br>낚시터 정보 추가를 희망하실 경우 ☎1833-7139로 연락주시기 바랍니다.</p>
						</c:if>
						<c:forEach var="row" items="${list_mbr_dtl}">
						<table class="basic_tbl mt-10">
							<caption>${item.CD_NM} 상세정보</caption>
							<colgroup>
								<col width="140" />
								<col />
							</colgroup>
							<thead><tr class="table-cell-blind"><th></th></tr></thead>
							<tbody>
								<tr>
									<th>상세정보구분</th>
									<td>
										<c:forEach var="item" items="${list_license_se_cd}">
											<c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">${item.CD_NM}</c:if>
										</c:forEach>
										<c:if test="${row.DTL_LICENSE_CD eq 'CIDL00002'}"><span class="ml-20"><b>*교육책임자 지정 확인서 제출 필수(대표자 본인이 수강 시에도 제출)</b></span></c:if>
									</td>
								</tr>
								<tr>
									<th>낚시터명칭</th>
									<td>${row.DTL_NM}</td>
								</tr>
								<tr>
									<th>허가(등록)번호</th>
									<td>${row.REG_NUM_CD}</td>
								</tr>
								<tr>
									<th>허가(등록)신고지</th>
									<td>
										<c:forEach var="item" items="${list_address_cd}">
											<c:if test="${row.SIDO_CD eq item.CD_ID}">${item.CD_NM}</c:if>
										</c:forEach>
										<span id="view_SIDO_CD_${row.DTL_SN}"></span>
										<script>auto_view_sel_area_sido('view_SIDO_CD_${row.DTL_SN}','${row.SIDO_CD}','${row.SIGNGU_CD}')</script>
									</td>
								</tr>
							</tbody>
						</table>
						</c:forEach>
						
					</c:when>
					<c:when test="${info_crs.CRS_MBR_CD eq 'CIDN010300'}">
						
						<c:if test="${empty list_mbr_dtl}">
							<p class="dottedbox text-center pt-30 pb-30">등록 된 낚시어선 추가 상세정보가 없습니다.<br><br>낚시어선 정보가 없을 경우 낚시어선 전문교육 신청이 불가능합니다.<br><br>낚시어선 정보 추가를 희망하실 경우 ☎1833-7139로 연락주시기 바랍니다.</p>
							<input type="hidden" name="REQ_YN" value="N" >
						</c:if>
						<c:forEach var="row" items="${list_mbr_dtl}" varStatus="status">
						<table class="basic_tbl mt-10">
							<caption>${item.CD_NM} 상세정보</caption>
							<colgroup>
								<col width="140" />
								<col />
							</colgroup>
							<thead><tr class="table-cell-blind"><th></th></tr></thead>
							<tbody>
								<tr>
									<th>상세정보구분</th>
									<td>
										<c:forEach var="item" items="${list_license_se_cd}">
											<c:if test="${row.DTL_LICENSE_CD eq item.CD_ID}">${item.CD_NM}</c:if>
										</c:forEach>
										<c:if test="${status.count eq '1'}">
											<select class="ml-10" title="교육수강 구분선택" id="MBR_FSHRBT_TYPE" name="MBR_FSHRBT_TYPE" 
											data-crs-law-type="${info_crs.CRS_LAW_TYPE }" title="교육수강구분 옵션선택(기존,신규,재개자)">
												<option value="">교육수강 구분</option>
												<option value="legacy">기존</option>			
												<option value="new">신규</option>			
												<option value="resmpt">재개자</option>	
											</select>
										</c:if>	
									</td>
								</tr>
								<tr>
									<th>낚시어선명칭</th>
									<td>${row.DTL_NM}</td>
								</tr>
								<tr>
									<th>신고확인증번호</th>
									<td>
										<fmt:parseDate var="parse_ship_license_str_dt" value="${row.SHIP_LICENSE_STR_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
	           							<fmt:formatDate var="ship_license_str_dt" value="${parse_ship_license_str_dt}" pattern="yyyy-MM-dd" />
								 		<fmt:parseDate var="parse_ship_license_end_dt" value="${row.SHIP_LICENSE_END_DT}" pattern="yyyy-MM-dd HH:mm:ss" />
	           							<fmt:formatDate var="ship_license_end_dt" value="${parse_ship_license_end_dt}" pattern="yyyy-MM-dd" />
										${row.REG_NUM_CD} ( 유효기간 : ${ship_license_str_dt} ~ ${ship_license_end_dt} )
									</td>
								</tr>
								<tr>
									<th>어선번호</th>
									<td>${row.SHIP_CD}</td>
								</tr>
								<tr>
									<th>낚시어선 신고지</th>
									<td>
										<c:forEach var="item" items="${list_address_cd}">
											<c:if test="${row.SIDO_CD eq item.CD_ID}">${item.CD_NM}</c:if>
										</c:forEach>
										<span id="view_SIDO_CD_${row.DTL_SN}"></span>
										<script>auto_view_sel_area_sido('view_SIDO_CD_${row.DTL_SN}','${row.SIDO_CD}','${row.SIGNGU_CD}')</script>
									</td>
								</tr>
							</tbody>
						</table>
						</c:forEach>
						
						<%-- // start 신규, 재개자 교육 // --%>
						<c:if test="${info_crs.CRS_LAW_TYPE eq 'CIDLAW002'}">
							<div class="hide" id="mbr_fshrbt_type_check">
								<h3 class="mt-30"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;"></i> 알림사항</h3>
								<div class="mt-10 bg-emphasis">
									<p class="font-weight-bold emphasis">
										귀하는 「신규·재개자 전문교육」 대상이 아닙니다. 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 「낚시어선 전문교육」을 수강하여 주시기 바랍니다.<br>
										20.2.21. 이후 최초 신고(신규) 및 영업정지명령을 받은 후 재개하려는 낚시어선업자(재개자)의 경우
									</p>
									<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계없이 「신규·재개자 전문교육(현장교육, 연간 21시간 이상)」을 수강하여야 합니다.</span>
									<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 기존 선택 및「낚시어선 전문교육」을 신청하여 주시기 바랍니다.</p>
								</div>
							</div>
						</c:if>
						<%-- // end 신규, 재개자 교육 // --%>
						
						<%-- // start 기존교육 // --%>
						<c:if test="${info_crs.CRS_LAW_TYPE eq 'default'}">
							<c:forEach var="mbrDtl" items="${list_mbr_dtl}" end="0">
							<c:if test="${mbrDtl.DTL_LICENSE_CD eq 'CIDL00001' or mbrDtl.DTL_LICENSE_CD eq 'CIDL00002' 
										  or mbrDtl.DTL_LICENSE_CD eq 'CIDL00003' or mbrDtl.DTL_LICENSE_CD eq 'CIDL00004' or mbrDtl.DTL_LICENSE_CD eq 'CIDL00005'}">   <%--해기사면서 소지 선장, 해기사면허 소지 선원, 해기사면허 미소지 선원  --%>
								<input type="hidden" name="DTL_LICENSE_CD" value="${mbrDtl.DTL_LICENSE_CD}"/>
								<div class="hide" id="defalut_check">
									<h3 class="mt-30"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;"></i> 알림사항</h3>
									<div class="mt-10 bg-emphasis">
										<p class="font-weight-bold emphasis">
											귀하는 「낚시어선 전문교육」 대상이 아닙니다. 「신규·재개자 전문교육」을 수강하여 주시기 바랍니다.<br>
											20.2.21. 이후 최초 신고 및 영업정지명령을 받은 후 재개하려는 낚시어선업자의 경우 
										</p>
										<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계 없이 연간 21시간 이상 현장 집합교육을 받아야 합니다.</span>
										<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 종전과 동일하게 매년 4시간의 낚시전문교육만 수강하시면 됩니다.</p>
									</div>
									<table class="basic_tbl mt-10">
									<caption>알림사항</caption>
									<colgroup>
										<col width="140" />
										<col />
									</colgroup>
									<thead><tr class="table-cell-blind"><th></th></tr></thead>
									<tbody>	
										<tr>
											<th>대상 선택</th>
											<td>
												<input type="radio" name="REQ_YN" id="sendSmsN" value="N" ><label class="mt-7" for="sendSmsN"><span></span>기존 낚시어선업 신고자 ( 기간만료 후 재신고자 포함 )</label><br>
												<input type="radio" name="REQ_YN" id="sendSmsY" value="Y" >
												<label class="mt-7" for="sendSmsY"><span></span>20.2.21 이후 <p class="emphasis3">최초</p>로 낚시어선업을 신고한 자</label><br>
												<input type="radio" name="REQ_YN" id="sendSmsR" value="R">
												<label class="mt-7" for="sendSmsR"><span></span>20.2.21 이후 낚시 관리 및 육성법 제38조제1항제5호에 따라 영업정지명령을 받은 후 재개하려는 자</label>
											</td>						
										</tr>
									</tbody>
									</table>
								</div>
								<c:set var="isEmptyDtlLicense" value="N"/>
							</c:if>
							</c:forEach>
						</c:if>
						<%-- // end 기존교육 // --%>
						
						<%-- <c:if test="${info_crs.CRS_GRP_CD ne 'CIDE16119012309'}">// 전국교육이 아니면 //
							<c:set var="isEmptyDtlLicense" value="Y"/>
							<c:forEach var="mbrDtl" items="${list_mbr_dtl}" end="0">
							<c:if test="${mbrDtl.DTL_LICENSE_CD eq 'CIDL00001' or mbrDtl.DTL_LICENSE_CD eq 'CIDL00002'}">
								<input type="hidden" name="DTL_LICENSE_CD" value="${mbrDtl.DTL_LICENSE_CD}"/>
								<div id="smsCheck">
									<h3 class="mt-30"><i class="fa fa-info-circle" aria-hidden="true" style="color: crimson;"></i> 알림사항</h3>
									<div class="mt-10 bg-emphasis">
										<p class="font-weight-bold emphasis">20.2.21. 이후 최초 신고 및 영업정지명령을 받은 후 재개하려는 낚시어선업자의 경우 </p>
										<span class="font-weight-bold emphasis2">온라인교육 수강 여부와 관계 없이 연간 21시간 이상 현장 집합교육을 받아야 합니다.</span>
										<p class="font-weight-bold light-red mt-10">* 기존 낚시어선업 신고자(기간만료 후 재신고자 포함)는 종전과 동일하게 매년 4시간의 낚시전문교육만 수강하시면 됩니다.</p>
									</div>
									<table class="basic_tbl mt-10">
									<caption>알림사항</caption>
									<colgroup>
										<col width="140" />
										<col />
									</colgroup>
									<thead><tr class="table-cell-blind"><th></th></tr></thead>
									<tbody>	
										<tr>
											<th>대상 선택</th>
											<td>
												<input type="radio" name="REQ_YN" id="sendSmsN" value="N" ><label class="mt-7" for="sendSmsN"><span></span>기존 낚시어선업 신고자 ( 기간만료 후 재신고자 포함 )</label><br>
												<input type="radio" name="REQ_YN" id="sendSmsY" value="Y" >
												<label class="mt-7" for="sendSmsY"><span></span>20.2.21 이후 <p class="emphasis3">최초</p>로 낚시어선업을 신고한 자</label><br>
												<input type="radio" name="REQ_YN" id="sendSmsR" value="R">
												<label class="mt-7" for="sendSmsR"><span></span>20.2.21 이후 낚시 관리 및 육성법 제38조제1항제5호에 따라 영업정지명령을 받은 후 재개하려는 자</label>
											</td>						
										</tr>
									</tbody>
									</table>
								</div>
								<c:set var="isEmptyDtlLicense" value="N"/>
							</c:if>
							</c:forEach>	
						</c:if> --%>
					</c:when>
					<c:otherwise>
						<p class="dottedbox text-center pt-30 pb-30">등록 된 낚시터 또는 낚시어선 추가상세정보가 없습니다.<br><br>해당 정보가 없을 경우 낚시터 또는 낚시어선 전문교육 신청이 불가능합니다.
						<br><br>낚시터 또는 낚시어선 정보 추가를 희망하실 경우 ☎1833-7139로 연락주시기 바랍니다.</p>
					</c:otherwise>
				</c:choose>
				<!-- <p class="dottedbox"></p> -->
				<!-- <div class="agree_text mgt10px"></div> -->
			</div>
			<div class="mt-10" id="more_msg"></div>
			
			<c:if test="${not empty list_mbr_dtl}">
				<p class="dottedbox mt-30 font-weight-bold text-center">『낚시전문교육 및 교육기관 지정에 관한 고시』 제8조에 따라 위와 같이 신청합니다.</p>
				<div id="btnArea">
					<button type="button" class="btn_blue h60px w50 trg_btn_submit" id="btn_submit"><b>신청하기</b></button>
				</div>
			</c:if>
		</section>
	</div>
	
</form:form>

<script>
$("body").tooltip({
    selector: '[data-toggle="tooltip"]'
});
$("#btn_submit").click(function() {

var form = document.getElementById('listForm');
	
	var mbr_fshrbt_type = $("#MBR_FSHRBT_TYPE").val();
	var crs_law_type = $("#MBR_FSHRBT_TYPE").attr("data-crs-law-type");
	var crs_mbr_cd = '${info_crs.CRS_MBR_CD}';
	if(!mbr_fshrbt_type) {
		if(crs_mbr_cd == 'CIDN010300') {
			allPublicModalMessage("교육수강 구분 선택은 필수 선택정보입니다.");
			//alert("교육수강 구분 선택은 필수 선택정보입니다.");
			return;
		}
	} else {
		
		if(crs_law_type == 'default') {
			if(mbr_fshrbt_type == '신규' || mbr_fshrbt_type == '재개자') {
				var REQ_YN = document.getElementsByName('REQ_YN');
				var checkCnt = 0;
				
				for(var i = 0; i < REQ_YN.length; i++){
					if(REQ_YN[i].checked == true) checkCnt++;
				}
				if(checkCnt < 1) {
					$(form.REQ_YN).attr('data-fail-message', '교육 알림 선택은 필수입니다');
					//allPublicModalMessage($(form.REQ_YN).attr('data-fail-message'));
					alert($(form.REQ_YN).attr('data-fail-message'));
					return;
				}
			}
		}
	}
/* 	var tempDate = $('.STR_DT').getTime() - $('.END_DT').getTime(); */
	var BF_END_DT = $('#END_DT').val();
	var END_DT = new Date(BF_END_DT);
	
	var formattedDate = new Date(); 
	var d = formattedDate.getDate(); 
	var m = formattedDate.getMonth(); m += 1;
	var y = formattedDate.getFullYear();
	//var BF_STR_DT = y + "-" + m + "-" + d;
	var BF_STR_DT = "";
	if(m < 10)
		BF_STR_DT = y + "-0" + m + "-" + d;
	else 
		BF_STR_DT = y + "-" + m + "-" + d;
	var STR_DT = new Date(BF_STR_DT);
	
	var DIFF_DATE = END_DT.getTime() - STR_DT.getTime();
	var DATE = Math.abs(DIFF_DATE / (1000 * 3600 * 24));
	
	var confirmValue = confirm("신청하신 교육은 " + Math.floor(DATE) + "일 후 수강이 종료됩니다.\n(~" + BF_END_DT + "까지 이수 필수)\n기간 내 교육이수를 하지 않았을 경우, 재수강을 해야합니다.\n\n해당 교육을 신청하시겠습니까?");
	if(confirmValue){
		$.ajax({
			type:"POST",
			url :"/educenter/trnng/write_act.do",
			data:$('#listForm').serialize(),
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data.errCnt > 0) {
					var pass = true;
					for(var key in data.errField) {
						
						$('#'+data.errField[key]).addClass('is-invalid');
						pass = false;
					}
					if(pass) {
						alert(data.msg);
					}
				} else {
					if(data.error == '1') {
						alert(data.msg);
					} else if(data.error == '2') {
						alert(data.msg);
						location.href = '/educenter/trnng/list.do';
					} else if(data.error == '3') {
						alert(data.msg);
						location.href = '/educenter/index.do';
					} else {
						location.href = '/educenter/mbrhstry/list.do';	
					}
				}
			},
			beforeSend : function(xhr, opts) {
				//console.log('before!');
				if(isClickRequestLocked()) {
					xhr.abort();
					return;
				}
				$('.trg_btn_submit').addClass('disabled');
			},
			complete : function() {
				//console.log('complete!');
				$('.trg_btn_submit').removeClass('disabled');
				clickRequestLockStop();
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
				//console.log(jqXHR);
				//console.log(textStatus);
				//console.log(errorThrown);
				clickRequestLockStop();
			}
		});
		
	}
	
}); 

/* $("input[name='REQ_YN']").on("click", function(){
	if($(this).val() == 'Y' || $(this).val() == 'R'){
		var html = '<ul class="ml-20">';
			html += '<li class="line-h18">현재 코로나19로 교육 개설이 되지 않아 추후 집합교육 개설 시, 입력해 주신 연락처로 교육 안내문자를 발송해드리겠습니다.</li>';
			html += '<li class="line-h18">온라인교육 수강을 원치 않으실 경우 교육 개설 안내를 위하여 1833-7139로 연락 주시기 바랍니다.</li>';
			html += '</ul>';
		$("#more_msg").html(html);
	} else {
		$("#more_msg").text("");
	}
}); */

$("#MBR_FSHRBT_TYPE").on("change", function(){
	
	var crs_law_type = $(this).attr('data-crs-law-type');
	var val = $(this).val();
	
	if(crs_law_type == 'default'){//기존교육
		
		if(val == 'legacy'){
			$("#defalut_check").addClass("hide");
		} else {
			$("#defalut_check").removeClass("hide");
		}
		
	} else {//신규, 재개자교육
		
		if(val == 'legacy'){
			$("#mbr_fshrbt_type_check").removeClass("hide");
			$("#btn_submit").addClass("hide");
		} else {
			$("#mbr_fshrbt_type_check").addClass("hide");
			$("#btn_submit").removeClass("hide");
		}
	}
	
});
</script>

<%@include file="../../naksinuri_original/naksinuri/layout/tail.jsp"%>

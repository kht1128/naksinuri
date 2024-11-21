<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>

<c:set var="pageMode" value="admin" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="boatfishing" />

	<%@include file="header_admin.jsp"%>
	<%@include file="login_header.jsp"%>
<style>
	.fish_list li{padding:4px 0;width:200px;}
</style>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="admin_leftTab.jsp"%>
<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="fileListCnt">
	<input type="hidden" name="atchFileId">
	<input type="hidden" name="fileSn">
	<input type="hidden" name="returnUrl" value="<c:url value='/info/admin/findCorp.do'/>"/>
	<input type="hidden" name="nak_id" id="nak_id" value="${info.nak_id}">
	<input type="hidden" name="atch_file_id" value="${info.atch_file_id }">
	<input type="hidden" name="co_mimgsrc" value="${info.co_mimgsrc }">
	<input type="hidden" name="fishing_type" value="boatfishing">
	<input type="hidden" name="co_addr"/>
	<input type="hidden" name="co_addr2"/>
	<input type="hidden" name="adres_la" value=""/>
	<input type="hidden" name="adres_lo" value=""/>
	
	<div id="container">

		<div id="content">			
			
			<section id="table-write" class="table-box">
				<h3>기본정보</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>	
							<tr>
								<th>승인 상태</th>
								<td>
									<select class="frm_select" name="co_status">
										<option <c:if test="${info.co_status eq 'w' }">selected</c:if> value="w">대기중</option>
										<option <c:if test="${info.co_status eq 'y' }">selected</c:if> value="y">승인</option>
										<option <c:if test="${info.co_status eq 'n' }">selected</c:if> value="n">미승인</option>
									</select>
								</td>
							</tr>						
							<tr>
								<th>구분</th>
								<td>
									<select class="frm_select" name="mod_fishing_type">
										<option <c:if test="${info.fishing_type eq 'boatfishing' }">selected</c:if> value="boatfishing">선상낚시</option>
										<option <c:if test="${info.fishing_type eq 'riverfishing' }">selected</c:if> value="riverfishing">민물낚시</option>
										<option <c:if test="${info.fishing_type eq 'seafishing' }">selected</c:if> value="seafishing">바다낚시</option>
									</select>
								</td>
							</tr>						
							<tr>
								<th>상호(선박)명</th>
								<td><input type="text" name="co_nm" id="co_nm" class="frm_input" value="${info.co_nm}"  /></td>
							</tr>
							<tr>
								<th>어선번호</th>
								<td>
									<input type="text" name="co_ship_num1" id="co_ship_num1" class="frm_input" value="${info.co_ship_num1}"  />
									- 
									<input type="text" name="co_ship_num2" id="co_ship_num2" class="frm_input" value="${info.co_ship_num2}"  />
								</td>
							</tr>
							
							<tr>
								<th>대표자</th>
								<td><input type="text" name="ceo_nm" id="ceo_nm" class="frm_input" value="${info.ceo_nm}"/></td>
							</tr>
							<tr>
								<th>일반전화</th>
								<td>
								<select class="frm_select" name="co_phone1">
									<option value=''>없음</option>
									<option <c:if test="${info.co_phone1 eq '02' }">selected</c:if>>02</option>
									<option <c:if test="${info.co_phone1 eq '031' }">selected</c:if>>031</option>
									<option <c:if test="${info.co_phone1 eq '032' }">selected</c:if>>032</option>
									<option <c:if test="${info.co_phone1 eq '033' }">selected</c:if>>033</option>
									<option <c:if test="${info.co_phone1 eq '041' }">selected</c:if>>041</option>
									<option <c:if test="${info.co_phone1 eq '042' }">selected</c:if>>042</option>
									<option <c:if test="${info.co_phone1 eq '043' }">selected</c:if>>043</option>
									<option <c:if test="${info.co_phone1 eq '051' }">selected</c:if>>051</option>
									<option <c:if test="${info.co_phone1 eq '052' }">selected</c:if>>052</option>
									<option <c:if test="${info.co_phone1 eq '053' }">selected</c:if>>053</option>
									<option <c:if test="${info.co_phone1 eq '054' }">selected</c:if>>054</option>
									<option <c:if test="${info.co_phone1 eq '055' }">selected</c:if>>055</option>
									<option <c:if test="${info.co_phone1 eq '061' }">selected</c:if>>061</option>
									<option <c:if test="${info.co_phone1 eq '062' }">selected</c:if>>062</option>
									<option <c:if test="${info.co_phone1 eq '063' }">selected</c:if>>063</option>
									<option <c:if test="${info.co_phone1 eq '064' }">selected</c:if>>064</option>
									<option <c:if test="${info.co_phone1 eq '070' }">selected</c:if>>070</option>
								</select> - 
								<input type="text" class="frm_input" name="co_phone2" id="co_phone2" maxlength="4" value="${info.co_phone2}" size="7" /> - 
								<input type="text" class="frm_input" name="co_phone3" id="co_phone3" maxlength="4" value="${info.co_phone3}" size="7" />
								<input type="hidden" name="co_phone">
								</td>
							</tr>
							<tr>
								<th>휴대전화</th>
								<td>
								<select class="frm_select"  name="co_hphone1">
									<option <c:if test="${info.co_hphone1 eq '010' }">selected</c:if>>010</option>
									<option <c:if test="${info.co_hphone1 eq '011' }">selected</c:if>>011</option>
									<option <c:if test="${info.co_hphone1 eq '016' }">selected</c:if>>016</option>
									<option <c:if test="${info.co_hphone1 eq '017' }">selected</c:if>>017</option>
									<option <c:if test="${info.co_hphone1 eq '018' }">selected</c:if>>018</option>
									<option <c:if test="${info.co_hphone1 eq '019' }">selected</c:if>>019</option>
								</select> - 
								<input type="text" class="frm_input" name="co_hphone2" value="${info.co_hphone2}" maxlength="4" size="7" /> - 
								<input type="text" class="frm_input" name="co_hphone3" value="${info.co_hphone3}" maxlength="4" size="7" />
								<input type="hidden" name="co_hphone">
								</td>
							</tr>
							<tr>
								<th>휴대전화2</th>
								<td>
								<select class="frm_select"  name="co_2hphone1">
									<option <c:if test="${info.co_2hphone1 eq '010' }">selected</c:if>>010</option>
									<option <c:if test="${info.co_2hphone1 eq '011' }">selected</c:if>>011</option>
									<option <c:if test="${info.co_2hphone1 eq '016' }">selected</c:if>>016</option>
									<option <c:if test="${info.co_2hphone1 eq '017' }">selected</c:if>>017</option>
									<option <c:if test="${info.co_2hphone1 eq '018' }">selected</c:if>>018</option>
									<option <c:if test="${info.co_2hphone1 eq '019' }">selected</c:if>>019</option>
								</select> - 
								<input type="text" class="frm_input" name="co_2hphone2" value="${info.co_2hphone2}" maxlength="4" size="7" /> - 
								<input type="text" class="frm_input" name="co_2hphone3" value="${info.co_2hphone3}" maxlength="4" size="7" />
								<input type="hidden" name="co_2hphone">
								</td>
							</tr>
							<tr>
								<th>어선규모(톤)</th>
								<td><input type="text" class="frm_input" name="bo_size" id="bo_size" value="${info.bo_size}" /></td>
							</tr>
							<tr>
								<th>어선속도(knot)</th>
								<td><input type="text" name="bo_spd" id="bo_spd" class="frm_input" value="${info.bo_spd}" /> </td>
							</tr>
							<tr>
								<th>승선인원</th>
								<td><input type="text" name="bo_psg" id="bo_psg" class="frm_input" value="${info.bo_psg}" /> 명</td>
							</tr>
							<tr>
								<th>홈페이지</th>
								<td><input type="text" name="co_web" class="frm_input" size=100 value=<c:choose><c:when test="${info.co_web eq null }">"http://"</c:when><c:otherwise>"${info.co_web}"</c:otherwise></c:choose> />
							</tr>	
							<tr>
								<th>주소</th>
								<td>
									<dl>
										<dd class="multi_input">
											<input type="text" name="co_addr_1" class="frm_input readonly" id="newpostcode"  value="${info.co_addr_1}" readonly size="7" /><a href="#;" class="btn_size1 btn_violet" onclick="newaddress()">주소검색</a><br />
											<div id="wrap2" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
											<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode2()" alt="접기 버튼">
											</div>
		
											<input type="text" id="newaddress1" name="co_addr_2" readonly class="frm_input" value="${info.co_addr_2}" size="100" /><br />
											<input type="text" id="newaddress2" name="co_addr_3" class="frm_input" placeholder="상세주소"  value="${info.co_addr_3}" size="100"/>
											<input type="hidden" id="oldaddress1"/>
										</dd>
									</dl>
								</td>
							</tr>							
													
						</tbody>
					</table>
				</div>
			</section>
			<section id="table-write" class="table-box m20">
				<h3>주요정보</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>주요어종</th>
								<input type="hidden" name="getco_fish" value="${info.co_fish}"/>
								<td>
									<ul class="fish_list floats">
										<c:set var="sub" value="${2}"></c:set>
										<c:forEach var="fishes" items="${fishlist}" varStatus="status">
											<c:set var="subname" value="${sub}${fishes.fish_name}${sub}"></c:set>
											<li><input type="checkbox" name="co_fish" id="fish${status.count}" value="${subname}" <c:if test ="${fn:contains(info.co_fish, subname)}">checked</c:if>><label for="fish${status.count}"><span></span>${fishes.fish_name}</label></li>
										</c:forEach>
									</ul>
								</td>
							</tr>

							<tr>
								<th>오시는 길</th>
								<td>
									<dl>
										<dd class="multi_input">
											<input type="text" class="frm_input readonly" name="co_addr2_1" id="busipostcode" readonly size="7" value="${info.co_addr2_1}"/><a href="#;" class="btn_size1 btn_violet" onclick="busiaddress()">주소검색</a>
											<input type="checkbox" id="sameaddr" onclick = "checksameaddr()"/><label for="sameaddr"><span></span>사업장 주소와 동일</label><br />
											<div id="wrap" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
											<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
											</div>
		
											<input type="text" id="busiaddress1" name="co_addr2_2" value="${info.co_addr2_2}" readonly class="frm_input" size="100" /><br />
											<input type="text" id="busiaddress2" name="co_addr2_3" value="${info.co_addr2_3}" class="frm_input" size="100" placeholder="상세주소"/>
										</dd>									
									</dl>
								</td>
							</tr>
							
							<tr>
								<th>이용시간</th>
								<td>
									<input type="text" name="co_stm" id="co_stm" class="frm_input" size="10" value="${info.co_stm}" placeholder="예) 오전 7시" /> ~ 
									<input type="text" name="co_etm" id="co_etm" class="frm_input" size="10" value="${info.co_etm}" placeholder="오후 4시" />
								</td>
							</tr>
							<tr>
								<th>이용료</th>
								<td>
									<input type="hidden" name="getco_prc" value="${info.co_prc}">
									<input type="hidden" name="getco_prctp" value="${info.co_prctp }">
									<input type="hidden" name="co_prc" id="co_prc" >
									<input type="hidden" name="co_prctp" id="co_prctp">
									<ul id="charul">
										<li id="charli_0" style="padding:5px 0">
											<input type="checkbox" id="chart_0" name="chart[]"/>
											<label for="chart_0"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="frm_input price_input" size="10" name="reco_prctp" id="reco_prctp_0" placeholder="예) 오전낚시"/>&nbsp;&nbsp;
											<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="frm_input price_input" size="10" name="reco_prc" id="reco_prc_0" placeholder="예) 4만원"/>
										</li>
									</ul>
									<br>
									<a href="#;" class="btn_size1 btn_violet" onclick="insert()">이용료 추가</a>
									<a href="#;" class="btn_size1 btn_gray" onclick="del()">이용료 삭제</a>
									
									<span>카드결제 가능</span>
									<input type="checkbox" id="credit_p" name="credit_p" <c:if test="${info.co_credit eq 1}">checked</c:if>/>
									<input type="hidden" name="co_credit" id="co_credit" <c:if test="${info.co_credit ne null }">value="${info.co_credit}"</c:if> <c:if test="${info.co_credit eq null }">value="0"</c:if> >
								</td>
								
							</tr>
							<tr>
								<th>시설안내</th>
								<td>								
									<ul class="fish_list floats">
										<li><input type="checkbox" id="aircon" name="co_fct" value="에어컨" 		<c:if test ="${fn:contains(info.co_fct,'에어컨')}">checked</c:if> /><label for="aircon"><span></span>에어컨</label></li>
										<li><input type="checkbox" id="toilet" name="co_fct" value="화장실" 		<c:if test ="${fn:contains(info.co_fct,'화장실')}">checked</c:if> /><label for="toilet"><span></span>화장실</label></li>
										<li><input type="checkbox" id="lifejacket" name="co_fct" value="구명조끼" <c:if test ="${fn:contains(info.co_fct,'구명조끼')}">checked</c:if> /><label for="lifejacket"><span></span>구명조끼</label></li>
										<li><input type="checkbox" id="liferope" name="co_fct" value="구명밧줄" 	<c:if test ="${fn:contains(info.co_fct,'구명밧줄')}">checked</c:if> /><label for="liferope"><span></span>구명밧줄</label></li>
										<li><input type="checkbox" id="insurance" name="co_fct" value="보험" 		<c:if test ="${fn:contains(info.co_fct,'보험')}">checked</c:if> /><label for="insurance"><span></span>보험</label></li>
										<li><input type="checkbox" id="heating" name="co_fct" value="난방" 		<c:if test ="${fn:contains(info.co_fct,'난방')}">checked</c:if> /><label for="heating"><span></span>난방</label></li>
										<li><input type="checkbox" id="fireex" name="co_fct" value="소화기"		<c:if test ="${fn:contains(info.co_fct,'소화기')}">checked</c:if> /><label for="fireex"><span></span>소화기</label></li>
										<li><input type="checkbox" id="cctv" name="co_fct" value="CCTV" 		<c:if test ="${fn:contains(info.co_fct,'CCTV')}">checked</c:if> /><label for="cctv"><span></span>CCTV</label></li>
										<li><input type="checkbox" id="shade" name="co_fct" value="그늘막" 		<c:if test ="${fn:contains(info.co_fct,'그늘막')}">checked</c:if> /><label for="shade"><span></span>그늘막</label></li>
										<li><input type="checkbox" id="table" name="co_fct" value="식탁" 			<c:if test ="${fn:contains(info.co_fct,'식탁')}">checked</c:if> /><label for="table"><span></span>식탁</label></li>
										<li><input type="checkbox" id="chair" name="co_fct" value="의자" 			<c:if test ="${fn:contains(info.co_fct,'의자')}">checked</c:if> /><label for="chair"><span></span>의자</label></li>
										<li><input type="checkbox" id="elecreel" name="co_fct" value="전동릴" 	<c:if test ="${fn:contains(info.co_fct,'전동릴')}">checked</c:if> /><label for="elecreel"><span></span>전동릴</label></li>
										<li><input type="checkbox" id="guidance" name="co_fct" value="안내방송" 	<c:if test ="${fn:contains(info.co_fct,'안내방송')}">checked</c:if> /><label for="guidance"><span></span>안내방송</label></li>
										<li><input type="checkbox" id="radio" name="co_fct" value="무전기" 		<c:if test ="${fn:contains(info.co_fct,'무전기')}">checked</c:if> /><label for="radio"><span></span>무전기</label></li>
										<li><input type="checkbox" id="finder" name="co_fct" value="어군탐지기" 	<c:if test ="${fn:contains(info.co_fct,'어군탐지기')}">checked</c:if> /><label for="finder"><span></span>어군탐지기</label></li>
										<li><input type="checkbox" id="radar" name="co_fct" value="레이더" 		<c:if test ="${fn:contains(info.co_fct,'레이더')}">checked</c:if> /><label for="radar"><span></span>레이더</label></li>
										<li><input type="checkbox" id="plotter" name="co_fct" value="플로터" 		<c:if test ="${fn:contains(info.co_fct,'플로터')}">checked</c:if> /><label for="plotter"><span></span>플로터</label></li>
										<li><input type="checkbox" id="sonar" name="co_fct" value="쏘나" 			<c:if test ="${fn:contains(info.co_fct,'쏘나')}">checked</c:if> /><label for="sonar"><span></span>쏘나</label></li>
										<li><input type="checkbox" id="cooler" name="co_fct" value="쿨러" 		<c:if test ="${fn:contains(info.co_fct,'쿨러')}">checked</c:if> /><label for="cooler"><span></span>쿨러</label></li>
										<li><input type="checkbox" id="sasimi" name="co_fct" value="회떠드림" 		<c:if test ="${fn:contains(info.co_fct,'회떠드림')}">checked</c:if> /><label for="sasimi"><span></span>회떠드림</label></li>				
									</ul>
								</td>
							</tr>
							<tr>
								<th>사업장 소개</th>
								<td>
									<textarea placeholder="예) 근해낚시, 먼바다낚시, 회와 매운탕제공, 낚시대여(유료)" class="frm_textarea" name="co_intro" id="co_intro" style="width:100%;height:300px" >${info.co_intro}</textarea>								
								</td>
							</tr>
							<tr>
								<th>대표 이미지</th>
								<c:choose>
									<c:when test="${mimg.orignl_file_nm ne null }">
										<td>
											<c:out value="${mimg.orignl_file_nm }"/>&nbsp;<c:out value="[${mimg.file_size} Byte]"/>
											<input type="hidden" id="mimg" value="${info.co_mimgsrc }"/>
												<img class="cursor" alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" 
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${mimg.co_mimgsrc}"/>','<c:out value="${mimg.file_sn}"/>');" />
								       			<img class="cursor" alt="파일 보기" src="<c:url value='/naksinuri_original/images/egovframework/com/ency.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_getImage('<c:out value="${mimg.co_mimgsrc}"/>','<c:out value="${mimg.file_sn}"/>');" style="margin-left:10px"/>
								       			<img class="cursor" alt="파일 다운" src="<c:url value='/naksinuri_original/common_main/img/icon_file.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_downFile('<c:out value="${mimg.co_mimgsrc}"/>','<c:out value="${mimg.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" id="mimg" name="mimg" accept="image/*" id="mascortimg" style="width:290px" />
										</td>
									</c:otherwise>
								</c:choose>							
							</tr>
							<tr>
								<th>소개이미지</th>
								<td>
									<ul class="imgul" id="imgul" style="margin-bottom:10px">
										  <c:forEach var="naksinurifileVO" items= "${filelist}" varStatus="status">
											<li><c:out value="${naksinurifileVO.orignl_file_nm }"/>&nbsp;<c:out value="[${naksinurifileVO.file_size} Byte]"/>							
												<img class="cursor" alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" 
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${naksinurifileVO.atch_file_id}"/>','<c:out value="${naksinurifileVO.file_sn}"/>');" />
								       			<img class="cursor" alt="파일 보기" src="<c:url value='/naksinuri_original/images/egovframework/com/ency.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_getImage('<c:out value="${naksinurifileVO.atch_file_id}"/>','<c:out value="${naksinurifileVO.file_sn}"/>');" style="margin-left:10px"/>
								       			<img class="cursor" alt="파일 다운" src="<c:url value='/naksinuri_original/common_main/img/icon_file.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_downFile('<c:out value="${naksinurifileVO.atch_file_id}"/>','<c:out value="${naksinurifileVO.file_sn}"/>');" />
							       			</li>
										  </c:forEach>			
										<li id="imgli_0">
											<em><input type="checkbox" id="introimage_0"  />

											<label for="introimage_0"><span></span></label></em><span><input type="file" size="30" id="egovComFileUploader" accept="image/*" name="file_1" value="${info.atch_file_id }"  /></span>
										</li>
									</ul>
									<a href="#;" class="btn_size1 btn_violet" onclick="insertimg()">이미지 추가</a><a href="#;" class="btn_size1 btn_gray" onclick="delrow()">이미지 삭제</a>									
								</td>							
							</tr>
							
							<!-- <tr>
								<th>반출대상2</th>
								<td>
									<input type="text" class="frm_input" size="20" placeholder="회사명" /> 
									<input type="text" class="frm_input" size="20" placeholder="성명" /> 
									<input type="text" class="frm_input" size="20" placeholder="직급(직책)" /> 
									<input type="text" class="frm_input" size="20" placeholder="메일주소" /> 
									<a href="#;" class="btn_size1 btn_violet">추가</a><a href="#;" class="btn_size1 btn_gray">삭제</a>
								</td>
							</tr>
							<tr>
								<th>결제자지정</th>
								<td>
									<input type="text" class="frm_input" size="30" placeholder="1차 결제자" />
									&nbsp;&nbsp;&nbsp;
									<input type="text" class="frm_input" size="30" placeholder="1차 결제자" />
									<div class="plusminus">
										<a href="#;">+</a>
										<a href="#;">-</a>
									</div>
								</td>
							</tr> -->
						</tbody>
					</table>
				</div>
			</section>
			<div class="btn_area textcenter">
				
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.co_trash eq '1' }">
					<a href="/admin/info/boatfishing/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.co_trash eq '0' && info.nak_id ne null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/info/admin/boatfishing.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.nak_id eq null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/info/admin/boatfishing.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>	
			</div>
		</div>
</form:form>

		<div id="content">			
			<section id="table-write" class="table-box m20">
				<h3>수정기록 - 완료된 기록만 나타납니다.</h3>
				<div class="padding_box">
					<table class="t_write">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
							<c:forEach var="plist" items="${previewlist}" varStatus="status">
							<tr>
								<th>수정완료일</th>
								<td>${plist.preg_date}</td>
								<th>수정요청 IP</th>
								<td>${plist.preg_sess}</td>
							</tr>
							</c:forEach>
						
						</tbody>
					</table>
				</div>
			</section>
		</div>
	</div>

		


	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>

</body>
</html>

<script>

//전역변수선언
var oEditors = [];
var j=0;

nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "co_intro",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["co_intro"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});


$("#credit_p").change(function() {
	 if(this.checked) {
		  $('#co_credit').val(1);
	  }else{
		  $('#co_credit').val(0);
	  }
	});

function imgupload(i){

	$("input:file").click();
	var fileNm = $("#file").val();
	 
	 //이미지파일 체크
	if (fileNm != "") {
	 
		var ext = fileNm.slice(fileNm.lastIndexOf(".") + 1).toLowerCase();
	 
		if (!(ext == "gif" || ext == "jpg" || ext == "png")) {
			alert("이미지파일 (.jpg, .png, .gif ) 만 업로드 가능합니다.");
			return false;
		}
	 
	}
}


//이용료 행 추가 삭제


function insert(){

	if($("ul#charul").children().size()==5){
		alert("이용료는 5개까지 추가 가능합니다.");
		return false;
	}else{
		$('#charul').append('<li id="charli_'+(j+1)+'"style="padding:5px 0">\
			<input type="checkbox" id="chart_'+(j+1)+'" name="chart[]"/>\
			<label for="chart_'+(j+1)+'"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="frm_input price_input" size="10" id="reco_prctp_'+(j+1)+'" name="reco_prctp"\
			placeholder="예) 오전낚시"/>&nbsp;&nbsp;\
			<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="frm_input price_input" size="10" id="reco_prc_'+(j+1)+'" name="reco_prc" placeholder="예) 4만원"/>\
		</li>');
		j++;
	}
		
}
function del(){
	
	for(var k=0;k<=j;k++){
			if($("#chart_"+k).prop("checked")) {
			$("#charli_"+k).remove();
			
			}
		}
}



// 행 추가 삭제
	var i = 1;
	function insertimg(){		
		if($("ul#imgul").children().size()==10){
			alert("이미지는 10개까지 추가 가능합니다.");
			return false;
		}else{
			$('#imgul').append('<li id="imgli_'+i+'" name="imgli[]"><em><input type="checkbox" id="introimage_'+i+'" name="introimage[]" /><label for="introimage_'+i+'"><span>&nbsp;</span></label></em><span><input type="file" size="30" id="file"  name="file_'+(i+1)+'" accept="image/*" /></span></li>');	
			i++;
		}
	}
	function delrow(){		
		for(var j=0;j<=i;j++){
			if($("#introimage_"+j).prop("checked")) {
			$("#imgli_"+j).remove();
			}
		}
	}

 // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
	var element_wrap2 = document.getElementById('wrap2');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

	function foldDaumPostcode2() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap2.style.display = 'none';
    }



    function newaddress() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('newpostcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('newaddress1').value = fullAddr;
				document.getElementById('oldaddress1').value = data.jibunAddress;
				

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap2.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap2.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap2);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap2.style.display = 'block';
    }

	function busiaddress() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('busipostcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('busiaddress1').value = fullAddr;			

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }

		function checksameaddr(){
			if($("#sameaddr").prop("checked")){
				if($("#newaddress1").val()){
					$shapeadd0 = document.getElementById('newpostcode').value;
					$shapeadd1 = document.getElementById('newaddress1').value;
					$shapeadd2 = document.getElementById('newaddress2').value;

					document.getElementById('busipostcode').value = $shapeadd0;
					document.getElementById('busiaddress1').value = $shapeadd1;
					document.getElementById('busiaddress2').value = $shapeadd2;
//				}else if($("#oldaddress1").val()==''){
				}else{
					$("#sameaddr").prop("checked",false);
					alert("사업장 주소를 입력해주세요");
					
				}
			}

		}
		$(document).ready(function(){	
			var getfish2=$('input[name=getco_fish]').val();
			
			
			var getfish = getfish2.split(',');
			for(var i=0;i<getfish.length;i++){
				if(getfish[i]=='광어'){
					$('input:checkbox[id="fish01"]').attr("checked", true);
				}else if(getfish[i]=='우럭'){
					$('input:checkbox[id="fish02"]').attr("checked", true);
				}else if(getfish[i]=='농어'){
					$('input:checkbox[id="fish03"]').attr("checked", true);
				}else if(getfish[i]=='민어'){
					$('input:checkbox[id="fish04"]').attr("checked", true);
				}else if(getfish[i]=='참돔'){
					$('input:checkbox[id="fish05"]').attr("checked", true);
				}else if(getfish[i]=='도미'){
					$('input:checkbox[id="fish06"]').attr("checked", true);
				}else if(getfish[i]=='돔'){
					$('input:checkbox[id="fish07"]').attr("checked", true);
				}else if(getfish[i]=='쭈꾸미'){
					$('input:checkbox[id="fish08"]').attr("checked", true);
				}else if(getfish[i]=='갑오징어'){
					$('input:checkbox[id="fish09"]').attr("checked", true);
				}else if(getfish[i]=='호래기'){
					$('input:checkbox[id="fish10"]').attr("checked", true);
				}else if(getfish[i]=='갈치'){
					$('input:checkbox[id="fish11"]').attr("checked", true);
				}else if(getfish[i]=='백조기'){
					$('input:checkbox[id="fish12"]').attr("checked", true);
				}else if(getfish[i]=='조기'){
					$('input:checkbox[id="fish13"]').attr("checked", true);
				}else if(getfish[i]=='가자미'){
					$('input:checkbox[id="fish14"]').attr("checked", true);
				}else if(getfish[i]=='참가자미'){
					$('input:checkbox[id="fish15"]').attr("checked", true);
				}else if(getfish[i]=='대구'){
					$('input:checkbox[id="fish16"]').attr("checked", true);
				}else if(getfish[i]=='삼치'){
					$('input:checkbox[id="fish17"]').attr("checked", true);
				}else if(getfish[i]=='숭어'){
					$('input:checkbox[id="fish18"]').attr("checked", true);
				}else if(getfish[i]=='문어'){
					$('input:checkbox[id="fish19"]').attr("checked", true);
				}else if(getfish[i]=='낙지'){
					$('input:checkbox[id="fish20"]').attr("checked", true);
				}else if(getfish[i]=='열기'){
					$('input:checkbox[id="fish21"]').attr("checked", true);
				}else if(getfish[i]=='볼락'){
					$('input:checkbox[id="fish22"]').attr("checked", true);
				}else if(getfish[i]=='쏨뱅이'){
					$('input:checkbox[id="fish23"]').attr("checked", true);
				}else if(getfish[i]=='참우럭'){
					$('input:checkbox[id="fish24"]').attr("checked", true);
				}else if(getfish[i]=='홍감펭'){
					$('input:checkbox[id="fish25"]').attr("checked", true);
				}else if(getfish[i]=='고등어'){
					$('input:checkbox[id="fish26"]').attr("checked", true);
				}else if(getfish[i]=='한치'){
					$('input:checkbox[id="fish27"]').attr("checked", true);
				}else if(getfish[i]=='오징어'){
					$('input:checkbox[id="fish28"]').attr("checked", true);
				}else if(getfish[i]=='부시리'){
					$('input:checkbox[id="fish29"]').attr("checked", true);
				}else if(getfish[i]=='방어'){
					$('input:checkbox[id="fish30"]').attr("checked", true);
				}else if(getfish[i]=='참치'){
					$('input:checkbox[id="fish31"]').attr("checked", true);
				}else if(getfish[i]=='다금바리'){
					$('input:checkbox[id="fish32"]').attr("checked", true);
				}else if(getfish[i]=='능성어'){
					$('input:checkbox[id="fish33"]').attr("checked", true);
				}else if(getfish[i]=='돗돔'){
					$('input:checkbox[id="fish34"]').attr("checked", true);
				}else if(getfish[i]=='도다리'){
					$('input:checkbox[id="fish35"]').attr("checked", true);
				}else if(getfish[i]=='놀래미'){
					$('input:checkbox[id="fish36"]').attr("checked", true);
				}else if(getfish[i]=='감성돔'){
					$('input:checkbox[id="fish37"]').attr("checked", true);
				}else if(getfish[i]=='전어'){
					$('input:checkbox[id="fish38"]').attr("checked", true);
				}else if(getfish[i]=='쥐치'){
					$('input:checkbox[id="fish39"]').attr("checked", true);
				}else if(getfish[i]=='전갱이'){
					$('input:checkbox[id="fish40"]').attr("checked", true);
				}else if(getfish[i]=='돌돔'){
					$('input:checkbox[id="fish41"]').attr("checked", true);
				}else if(getfish[i]=='벵에돔'){
					$('input:checkbox[id="fish42"]').attr("checked", true);
				}else if(getfish[i]=='학꽁치'){
					$('input:checkbox[id="fish43"]').attr("checked", true);
				}
			}
			var getprc= $('input[name=getco_prc]').val();
			var getprctp= $('input[name=getco_prctp]').val();
			
			if(getprc!='') {		
				$("#charli_0").remove();
				$('input[name=getco_prc]').val()
				
				
				var prc = getprc.split('|');
				var prctp= getprctp.split('|');		
				//배열 빈값제거
				for(var i=0;i<prc.length;i++){
					if(prc[i]!=''){
					$('#charul').append('<li id="charli_'+(j+1)+'"style="padding:5px 0">\
							<input type="checkbox" id="chart_'+(j+1)+'" name="chart[]"/>\
							<label for="chart_'+(j+1)+'"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="frm_input price_input" value = "'+prctp[i]+'" name="reco_prctp" size="10" id="reco_prctp_'+(j+1)+'"\
							/>&nbsp;&nbsp;\
							<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="frm_input price_input" value ="'+prc[i]+'" name="reco_prc" size="10" id="reco_prc_'+(j+1)+'" />\
						</li>');
						j++;
					}
				}
			}
			
			//이용료 5개 다 채웟을때 이용료 행 삭제
			if($("ul#charul").children().size()>=5){
				$("#charli_0").remove();
			}
			// 이미지 10개다 채웠을때 파일첨부 행 없애기
			if($("ul#imgul").children().size()>=10){
				$("#imgli_0").remove();
			}
			
		});
		
		function fn_egov_downFile(atchFileId, fileSn){
			window.open("<c:url value='/naksinuri_original/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
		
		function fn_egov_getImage(atchFileId, fileSn){
			window.open("<c:url value='/naksinuri_original/cmm/fms/getImage.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}	
		
		function fn_egov_deleteFile(atchFileId, fileSn) {
			if(!confirm('파일을 삭제 하시겠습니까?')) return;
			forms = document.getElementsByTagName("form");

			for (var i = 0; i < forms.length; i++) {
				if (typeof(forms[i].atchFileId) != "undefined" &&
						typeof(forms[i].fileSn) != "undefined"  &&
						typeof(forms[i].fileListCnt) != "undefined") {
					form = forms[i];
				}
			}
			//form = document.forms[0];
			form.atchFileId.value = atchFileId;
			form.fileSn.value = fileSn;
			form.action = "<c:url value='/naksinuri_original/cmm/fms/deleteFileInfs.do'/>";
			form.submit();
		}
		
		$("#cancel").click(function(){
			
			$(location).attr("href","/naksinuri/login.do");
			
		});
		
		$('#updateInfo').click(function(){
			
			//주소-좌표 변환 객체를 생성합니다
			var address = $("#newaddress1").val();
			var form = document.getElementById('supform');
			var geocoder = new kakao.maps.services.Geocoder();
			geocoder.addressSearch(address, function(result, status) {
				if (status === kakao.maps.services.Status.OK) {
					var position = new kakao.maps.LatLng(result[0].y, result[0].x);
					
					form.adres_la.value = result[0].y;
					form.adres_lo.value = result[0].x;
					
					
				}
				
			});
			
			var co_addr = $('input[name=co_addr_1]').val() +
						  $('input[name=co_addr_2]').val() +
						  $('input[name=co_addr_3]').val();			

			var co_addr2 = $('input[name=co_addr2_1]').val() +
						   $('input[name=co_addr2_2]').val() +
						   $('input[name=co_addr2_3]').val();	
			
			var co_phone = $('select[name=co_phone1]').val() +
						   $('input[name=co_phone2]').val() + 
						   $('input[name=co_phone3]').val();
			
			var co_hphone = $('select[name=co_hphone1]').val() + 
							$('input[name=co_hphone2]').val() +
							$('input[name=co_hphone3]').val();
			
			var co_2hphone = $('select[name=co_2hphone1]').val() + 
							$('input[name=co_2hphone2]').val() +
							$('input[name=co_2hphone3]').val();
			
			$('input[name=co_addr]').val(co_addr);
			$('input[name=co_addr2]').val(co_addr2);
			$('input[name=co_phone]').val(co_phone);			
			$('input[name=co_hphone]').val(co_hphone);
			$('input[name=co_2hphone]').val(co_2hphone);	
			
			if(!$('#co_nm').val() || $('#co_nm').val().trim()==''){
				alert('상호명을 입력해주세요');
				return false;
			}
			if(!$('#ceo_nm').val() || $('#ceo_nm').val().trim()==''){
				alert('대표자를 입력해주세요');
				return false;
			}
			
			if(!$('#mimg').val()){
				alert('대표 이미지를 등록해주세요.');
				return false;
			}
			
			var co_prctp='';
			for(var i=0;i<=j;i++){
					if($('#reco_prctp_'+i).val()){
						if(co_prctp){
							co_prctp+='|';
						}
							co_prctp += $('#reco_prctp_'+i).val();
						}
					}
				var co_prc='';
				for(var i=0;i<=j;i++){
					if($('#reco_prc_'+i+'').val()){
						if(co_prc){
							co_prc+='|';
						}
							co_prc += $('#reco_prc_'+i).val();
					}
				}
			$('#co_prctp').val(co_prctp);
			$('#co_prc').val(co_prc);
			
			// 에디터의 내용이 textarea에 적용된다.
		    oEditors.getById["co_intro"].exec("UPDATE_CONTENTS_FIELD", [ ]);
			
			if($('#nak_id').val()!=''){
				$("#supform").attr("action","/info/admin/updateInfo.do");
				$("#supform").submit(); 
			}else{
				$("#supform").attr("action","/info/admin/insert.do");
				$("#supform").submit();				
			}
		});	
		
		// textArea에 이미지 첨부
		function pasteHTML(filepath){
		    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
		    oEditors.getById["co_intro"].exec("PASTE_HTML", [sHTML]);
		   
		}
</script>
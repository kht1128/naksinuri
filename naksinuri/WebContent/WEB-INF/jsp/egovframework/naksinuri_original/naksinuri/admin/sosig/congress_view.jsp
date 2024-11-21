<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<html lang="ko">
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="bo_type" value="congress" />
<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/sosig/congress/board_findCorp.do'/>"/>
<input type="hidden" name="bo_sub_img" value="${info.bo_sub_img}">
<input type="hidden" name="bo_main_img" value="${info.bo_main_img}">
<input type="hidden" name="bo_atch_file" value="${info.bo_atch_file}">
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
	
	<div id="container">
		
		<div id="content">			
			<section id="table-write" class="table-box">
				<h3>행사(대회/박람회) 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>	
							<tr>
								<th>공지 상단에 표시</th>
								<td><input type="checkbox" name="gongzi" id="gongzi" <c:if test="${info.bo_notice_y eq 1}">checked</c:if> class="frm_input" />
									<label for="gongzi">게시판 상단에 공지사항 마크로 표기됩니다.</label>								
								</td>
								<input type="hidden" name="bo_notice_y" id="bo_notice_y" value="0"/>
							</tr>	
							<tr>
								<th>문자자동발송</th>
								<td><input type="checkbox" name="is_autosend_sms" id="is_autosend_sms" <c:if test="${info.is_autosend_sms_y eq 1}">checked</c:if> class="frm_input" />
									<label for="is_autosend_sms">체크할 경우 참가자 신청시 안내 문자를 자동으로 발송합니다.</label>								
								</td>
								<input type="hidden" name="is_autosend_sms_y" id="is_autosend_sms_y" value="${info.is_autosend_sms_y}"/>
							</tr>												
							<c:if test="${info.bo_notice_y eq '0'}">
								<tr>
									<th>승인여부</th>
									<td>
										<select class="frm_select" name="bo_trash" id="bo_trash">
											<option value="0"<c:if test="${info.bo_trash eq '0'}">selected</c:if>>승인</option>
											<option value="3"<c:if test="${info.bo_trash eq '3'}">selected</c:if>>미승인</option>								
										</select> 
										<span> ${info.regit_num}</span>
									</td>
								</tr>
							</c:if>							
							<!-- <tr>
								<th>패스워드</th>
								<td><input type="password" name="bo_pass" id="bo_pass" class="frm_input" value="${info.bo_pass}"/></td>
							</tr>
							 -->
							<tr>
								<th>제목</th>
								<td><input type="text" name="bo_subject" id="bo_subject" class="frm_input" style="width:100%;" value="<c:out value="${info.bo_subject}"/>"/></td>
							</tr>
							<tr>
								<th>주최</th>
								<td><input type="text" name="organizer" id="organizer" class="frm_input" value="<c:out value="${info.organizer}"/>"  /></td>
							</tr>
							<tr>
								<th>주관</th>
								<td><input type="text" name="host" id="host" class="frm_input" value="<c:out value="${info.host}"/>"  /></td>
							</tr>
							<tr>
								<th>담당자</th>
								<td><input type="text" name="bo_name" id="bo_name" class="frm_input" value="<c:out value="${info.bo_name}"/>"  /></td>
							</tr>
							
							<tr>
								<th>구분</th>
								<td>
									<select class="frm_select" name="bo_cate" id="bo_cate">
										<option value="">구분을 선택하세요.</option>
										<option value="대회"<c:if test="${info.bo_cate eq '대회'}">selected</c:if>>대회</option>
										<option value="박람회"<c:if test="${info.bo_cate eq '박람회'}">selected</c:if>>박람회</option>								
									</select> 
								</td>
							</tr>
													
							<tr>
								<th>진행일정</th>
								<td><input type="text" name="bo_start_dt" id="bo_start_dt" class="frm_input" value="${info.bo_start_dt}" autocomplete="off"/> ~ <input type="text" name="bo_end_dt" id="bo_end_dt" class="frm_input" value="${info.bo_end_dt}" autocomplete="off"/> </td>
							</tr>
							<tr>
								<th>포스터</th>
								<c:choose>
									<c:when test="${bmimg ne null }">
										<td>
										<c:out value="${bmimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bmimg.file_size} Byte]"/>
											<input type="hidden" id="mimg" value="${bmimg.bo_main_img}"/>
											<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bmimg.bo_main_img}"/>','<c:out value="${bmimg.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" name="mimg" accept="image/*" id="mimg" style="width:290px" /></td>
									</c:otherwise>			
								</c:choose>
							</tr>
							<tr>
								<th>대회장소</th>
								<td>
								<input type="text" style="display:none" id="sample4_postcode" placeholder="우편번호">
								<input type="text" id="sample4_roadAddress" name="cg_location" class="frm_input" width="70%" placeholder="대회장소를 입력해주세요." value="<c:out value="${info.cg_location}"/>">
								<input type="text" id="sample4_jibunAddress" style="display:none" placeholder="지번주소">
								<span id="guide" style="color:#999"></span>								
								</td>
							</tr>
							<tr>
								<th>이메일</th>
								<td>
									<input type="text" size="30" class="frm_input" placeholder="이메일을 입력하세요" name="bo_email1" id="bo_email1" value="${info.bo_email1 }">@
									<select id="email_addr" name="bo_email2" class="frm_select" style="width:200px;" onchange="chgaddr();">					
										<option value="">선택하세요</option>
										<option value="naver.com" <c:if test="${info.bo_email2 eq 'naver.com' }">selected</c:if>>naver.com</option>
										<option value="daum.net" <c:if test="${info.bo_email2 eq 'daum.net' }">selected</c:if>>daum.net</option>
										<option value="nate.com" <c:if test="${info.bo_email2 eq 'nate.com' }">selected</c:if>>nate.com</option>
										<option value="yahoo.com" <c:if test="${info.bo_email2 eq 'yahoo.com' }">selected</c:if>>yahoo.com</option>
										<option value="gmail.com" <c:if test="${info.bo_email2 eq 'gmail.com' }">selected</c:if>>gmail.com</option>	
										<option value="paran.com" <c:if test="${info.bo_email2 eq 'paran.com' }">selected</c:if>>paran.com</option>
										<option value="hanmail.net" <c:if test="${info.bo_email2 eq 'hanmail.net' }">selected</c:if>>hanmail.net</option>
										<option id="self" value="1">직접 입력하기</option>
									</select>
								
									<input type="text" style="display:none" size="30" class="frm_input" placeholder="이메일을 입력하세요" id="bo_email2" value="${info.bo_email2 }">
								</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td>
									<select class="frm_select" id="bo_phone1" name="bo_phone1" value="${info.bo_phone1 }">
										<option <c:if test="${info.bo_phone1 eq '010' }">selected</c:if>>010</option>
										<option <c:if test="${info.bo_phone1 eq '011' }">selected</c:if>>011</option>
										<option <c:if test="${info.bo_phone1 eq '012' }">selected</c:if>>012</option>
										<option <c:if test="${info.bo_phone1 eq '014' }">selected</c:if>>014</option>
										<option <c:if test="${info.bo_phone1 eq '017' }">selected</c:if>>017</option>
										<option <c:if test="${info.bo_phone1 eq '018' }">selected</c:if>>018</option>
										<option <c:if test="${info.bo_phone1 eq '019' }">selected</c:if>>019</option>
										<option <c:if test="${info.bo_phone1 eq '070' }">selected</c:if>>070</option>
									</select> - 
									<input type="text" class="frm_input" size="7" name="bo_phone2" maxlength="4"  id="bo_phone2" value="${info.bo_phone2 }"/> - 
									<input type="text" class="frm_input" size="7" name="bo_phone3" maxlength="4"  id="bo_phone3" value="${info.bo_phone3 }"/>
								</td>
							</tr>
							
							
							<tr>
								<th>내용</th>
								<td><textarea name="bo_content" id="bo_content"  class="frm_input"  style="WIDTH: 100%; HEIGHT:500px;">${info.bo_content}</textarea></td>
							</tr>
										
							<%--
							<tr>
								<td></td>
								<td><a href="#;" class="btn_size1 btn_violet" onclick="insertinput()">첨부파일 추가 </a><a href="#;" class="btn_size1 btn_bordergray" onclick="deleteinput()">파일 삭제</a></td>
							</tr>
							<c:forEach items="${filelist}" var="item">
								<tr>
									<th>첨부파일</th>
									<td>
										<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
										<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.bo_atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
									</td>
								</tr>
							</c:forEach>
							<tr id="file_0">
								<th><label for="chart_0">첨부파일</label></th>
								<td style="padding-top:10px;">
									<input type="checkbox" id="chart_0" name="atch_file_0"/>
									<input type="file" size="30" name="bo_file_0"  style="width:290px" class="file" />
								</td>									
							</tr>
							 --%>
							<tr>
								<th>참가자 설정</th>
								<td>
									<input type="checkbox" name="is_entry" id="is_entry" class="frm_input" <c:if test="${info.is_entry_y eq 1}">checked</c:if>/>
									<label for="is_entry"><span></span>참가자 설정을 활성화 하려면 필히 체크하세요.</label>
									<input type="hidden" name="is_entry_y" id="is_entry_y" value="<c:if test="${info.is_entry_y eq 1}">1</c:if>" />
								</td>
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th>참여(제한)인원</th>
								<td>
									최대 <input type="text" name="entry_cnt" id="entry_cnt" class="frm_input " value="<c:out value="${info.entry_cnt}"/>" autocomplete="off"/> 명
									<br><label for="bo_entry">(인원제한이 없는 경우에는 빈값이거나 0 명을 입력하세요.)</label>
								</td>
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th>모집기간</th>
								<td>
									<input type="text" name="entry_start_dt" id="entry_start_dt" readonly class="frm_input" value="${info.entry_start_dt}" autocomplete="off"/> ~ <input type="text" readonly name="entry_end_dt" id="entry_end_dt" class="frm_input" value="${info.entry_end_dt}" autocomplete="off"/> 
								</td>					
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th>참가신청 안내문구</th>
								<td>
									<textarea name="entry_notice" id="entry_notice"  class="frm_input"  style="width:100%;height:80px;border:1px solid #cdcdcd" autocomplete="off"><c:out value="${info.entry_notice}"/></textarea>
								</td>
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th>선택 옵션</th>
								<td>
									<div class="choicebox">
										<input type="checkbox" name="is_jacket_offer" id="is_jacket_offer" class="frm_input" <c:if test="${info.is_jacket_offer_y eq 1}">checked</c:if>/>
										<label for="is_jacket_offer"><span></span>참가자 신청서란에 <b>구명조끼 지참여부</b> 체크란을 활성화 하려면 체크하세요.</label>
										<input type="hidden" name="is_jacket_offer_y" id="is_jacket_offer_y" value="<c:if test="${info.is_jacket_offer_y eq 1}">1</c:if>" />
									</div>
									<div class="choicebox">
										<input type="checkbox" name="is_inflow_path" id="is_inflow_path" class="frm_input" <c:if test="${info.is_inflow_path_y eq 1}">checked</c:if>/>
										<label for="is_inflow_path"><span></span>참가자 신청서란에 <b>유입경로</b> 체크 옵션을 활성화 하려면 체크하세요.</label>
										<input type="hidden" name="is_inflow_path_y" id="is_inflow_path_y" value="<c:if test="${info.is_inflow_path_y eq 1}">1</c:if>" />
									</div>
									<div class="choicebox">
										<input type="checkbox" name="is_attend_cause" id="is_attend_cause" class="frm_input" <c:if test="${info.is_attend_cause_y eq 1}">checked</c:if>/>
										<label for="is_attend_cause"><span></span>참가자 신청서란에 <b>참가이유</b>란을 활성화 하려면 체크하세요.</label>
										<input type="hidden" name="is_attend_cause_y" id="is_attend_cause_y" value="<c:if test="${info.is_attend_cause_y eq 1}">1</c:if>" />
									</div>
									<div class="choicebox">
										<input type="checkbox" name="is_naksi_career" id="is_naksi_career" class="frm_input" <c:if test="${info.is_naksi_career_y eq 1}">checked</c:if>/>
										<label for="is_naksi_career"><span></span>참가자 신청서란에 <b>낚시경력</b>란을 활성화 하려면 체크하세요.</label>
										<input type="hidden" name="is_naksi_career_y" id="is_naksi_career_y" value="<c:if test="${info.is_naksi_career_y eq 1}">1</c:if>" />
									</div>
								</td>
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th style="vertical-align:top;padding-top:20px;">요금 옵션&nbsp;&nbsp;<input type="button" class="btn grey" onclick="add_price()" value="추가"></th>
								<td>
									<ul id="price_item_list" class="price_item_box">
										<c:choose>
											<c:when test="${info.price_item_name eq null}">
												<li>모든 대상에 참가비가 없는 무료인 경우 항목을 추가하지 않으시면 됩니다.</li>
											</c:when>
											<c:otherwise>
												<li>모든 대상에 참가비가 없는 무료인 경우 항목을 추가하지 않으시면 됩니다.</li>
												<c:set var="price_list_name" value="${fn:split(info.price_item_name,',')}" />
												<c:set var="price_list_cash" value="${fn:split(info.price_item_cash,',')}" />
												<c:forEach var="item_value" items="${price_list_name}" varStatus="i">
													<li id="price_item_${i.count}" class="price_item">
													<input type="text" name="price_item_name" class="frm_input" value="${item_value}" autocomplete="off" placeholder="요금명칭"/>&nbsp;
													<input type="number" name="price_item_cash" class="frm_input" value="${price_list_cash[i.index]}" autocomplete="off" placeholder="요금금액(숫자)"/>&nbsp;
													<input type="button" class="btn lgrey" onclick="del_price('${i.count}')" value="항목삭제">
													</li>
												</c:forEach>
											</c:otherwise>
										</c:choose>							
									</ul>
								</td>
							</tr>	
							<tr class="is_entry_check" style="display:none">
								<th>참가비</th>
								<td><input type="text" name="entry_cash" id="entry_cash" class="frm_input" value="<c:out value="${info.entry_cash}"/>" placeholder="1인 3만원 (중학생 이하는 50% 할인)" size="80" /></td>
							</tr>
							<tr class="is_entry_check" style="display:none">
								<th>입금계좌</th>
								<td>
									<select id="cg_account_name" name="cg_account_name" class="frm_input">
										   <option value=''>-선택-</option>
									       <option value='SC제일은행' <c:if test="${info.cg_account_name eq 'SC제일은행' }">selected</c:if>>SC제일은행</option>
									       <option value='경남은행' <c:if test="${info.cg_account_name eq '경남은행' }">selected</c:if>>경남은행</option>
									       <option value='광주은행' <c:if test="${info.cg_account_name eq '광주은행' }">selected</c:if>>광주은행</option>
									       <option value='국민은행' <c:if test="${info.cg_account_name eq '국민은행' }">selected</c:if>>국민은행</option>
									       <option value='굿모닝신한증권' <c:if test="${info.cg_account_name eq '굿모닝신한증권' }">selected</c:if>>굿모닝신한증권</option>
									       <option value='기업은행' <c:if test="${info.cg_account_name eq '기업은행' }">selected</c:if>>기업은행</option>
									       <option value='농협중앙회' <c:if test="${info.cg_account_name eq '농협중앙회' }">selected</c:if>>농협중앙회</option>
									       <option value='농협회원조합' <c:if test="${info.cg_account_name eq '농협회원조합' }">selected</c:if>>농협회원조합</option>
									       <option value='대구은행' <c:if test="${info.cg_account_name eq '대구은행' }">selected</c:if>>대구은행</option>
									       <option value='대신증권' <c:if test="${info.cg_account_name eq '대신증권' }">selected</c:if>>대신증권</option>
									       <option value='대우증권' <c:if test="${info.cg_account_name eq '대우증권' }">selected</c:if>>대우증권</option>
									       <option value='동부증권' <c:if test="${info.cg_account_name eq '동부증권' }">selected</c:if>>동부증권</option>
									       <option value='동양종합금융증권' <c:if test="${info.cg_account_name eq '동양종합금융증권' }">selected</c:if>>동양종합금융증권</option>
									       <option value='메리츠증권' <c:if test="${info.cg_account_name eq '메리츠증권' }">selected</c:if>>메리츠증권</option>
									       <option value='미래에셋증권' <c:if test="${info.cg_account_name eq '미래에셋증권' }">selected</c:if>>미래에셋증권</option>
									       <option value='뱅크오브아메리카(BOA)' <c:if test="${info.cg_account_name eq '뱅크오브아메리카(BOA)' }">selected</c:if>>뱅크오브아메리카(BOA)</option>
									       <option value='부국증권' <c:if test="${info.cg_account_name eq '부국증권' }">selected</c:if>>부국증권</option>
									       <option value='부산은행' <c:if test="${info.cg_account_name eq '부산은행' }">selected</c:if>>부산은행</option>
									       <option value='산림조합중앙회' <c:if test="${info.cg_account_name eq '산림조합중앙회' }">selected</c:if>>산림조합중앙회</option>
									       <option value='산업은행' <c:if test="${info.cg_account_name eq '산업은행' }">selected</c:if>>산업은행</option>
									       <option value='삼성증권' <c:if test="${info.cg_account_name eq '삼성증권' }">selected</c:if>>삼성증권</option>
									       <option value='상호신용금고' <c:if test="${info.cg_account_name eq '상호신용금고' }">selected</c:if>>상호신용금고</option>
									       <option value='새마을금고' <c:if test="${info.cg_account_name eq '새마을금고' }">selected</c:if>>새마을금고</option>
									       <option value='수출입은행' <c:if test="${info.cg_account_name eq '수출입은행' }">selected</c:if>>수출입은행</option>
									       <option value='수협중앙회' <c:if test="${info.cg_account_name eq '수협중앙회' }">selected</c:if>>수협중앙회</option>
									       <option value='신영증권' <c:if test="${info.cg_account_name eq '신영증권' }">selected</c:if>>신영증권</option>
									       <option value='신한은행' <c:if test="${info.cg_account_name eq '신한은행' }">selected</c:if>>신한은행</option>
									       <option value='신협중앙회' <c:if test="${info.cg_account_name eq '신협중앙회' }">selected</c:if>>신협중앙회</option>
									       <option value='에스케이증권' <c:if test="${info.cg_account_name eq '에스케이증권' }">selected</c:if>>에스케이증권</option>
									       <option value='에이치엠씨투자증권' <c:if test="${info.cg_account_name eq '에이치엠씨투자증권' }">selected</c:if>>에이치엠씨투자증권</option>
									       <option value='엔에이치투자증권' <c:if test="${info.cg_account_name eq '엔에이치투자증권' }">selected</c:if>>엔에이치투자증권</option>
									       <option value='엘아이지투자증권' <c:if test="${info.cg_account_name eq '엘아이지투자증권' }">selected</c:if>>엘아이지투자증권</option>
									       <option value='외환은행' <c:if test="${info.cg_account_name eq '외환은행' }">selected</c:if>>외환은행</option>
									       <option value='우리은행' <c:if test="${info.cg_account_name eq '우리은행' }">selected</c:if>>우리은행</option>
									       <option value='우리투자증권' <c:if test="${info.cg_account_name eq '우리투자증권' }">selected</c:if>>우리투자증권</option>
									       <option value='우체국' <c:if test="${info.cg_account_name eq '우체국' }">selected</c:if>>우체국</option>
									       <option value='유진투자증권' <c:if test="${info.cg_account_name eq '유진투자증권' }">selected</c:if>>유진투자증권</option>
									       <option value='전북은행' <c:if test="${info.cg_account_name eq '전북은행' }">selected</c:if>>전북은행</option>
									       <option value='제주은행' <c:if test="${info.cg_account_name eq '제주은행' }">selected</c:if>>제주은행</option>
									       <option value='키움증권' <c:if test="${info.cg_account_name eq '키움증권' }">selected</c:if>>키움증권</option>
									       <option value='하나대투증권' <c:if test="${info.cg_account_name eq '하나대투증권' }">selected</c:if>>하나대투증권</option>
									       <option value='하나은행' <c:if test="${info.cg_account_name eq '하나은행' }">selected</c:if>>하나은행</option>
									       <option value='하이투자증권' <c:if test="${info.cg_account_name eq '하이투자증권' }">selected</c:if>>하이투자증권</option>
									       <option value='한국씨티은행' <c:if test="${info.cg_account_name eq '한국씨티은행' }">selected</c:if>>한국씨티은행</option>
									       <option value='한국투자증권' <c:if test="${info.cg_account_name eq '한국투자증권' }">selected</c:if>>한국투자증권</option>
									       <option value='한화증권' <c:if test="${info.cg_account_name eq '한화증권' }">selected</c:if>>한화증권</option>
									       <option value='현대증권' <c:if test="${info.cg_account_name eq '현대증권' }">selected</c:if>>현대증권</option>
									       <option value='홍콩상하이은행' <c:if test="${info.cg_account_name eq '홍콩상하이은행' }">selected</c:if>>홍콩상하이은행</option>
									</select>
									<input type="text" id="cg_account" name="cg_account" class="frm_input" size="50" value="<c:out value="${info.cg_account}"/>">
								</td>
							</tr>						
							<tr>
								<th>암호설정</th>
								<td>
									<input type="password" id="bo_pass" name="bo_pass" class="frm_input" placeholder="암호를 입력해주세요." size="50" autocomplete="off" value="${info.bo_pass}"/>
									<br><label for="bo_pass" class="txt_red">#주의# 비밀번호 변경이 필요한 경우에 한해서만 입력하세요. 바뀐 비밀번호는 복구되지 않습니다.</label>								
								</td>
							</tr>
												
						</tbody>
					</table>
				</div>
			</section>
			<%--
			<section id="table-write" class="table-box" >
				<h3>참가자 설정</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>		
					
							<tr>
								<th>사용여부</th>
								<td><input type="checkbox" name="is_entry" id="is_entry" <c:if test="${info.is_entry_y eq 1}">checked</c:if> class="frm_input" />
									<label for="is_entry">아래 참가자 설정을 활성화 하려면 필히 체크하세요.</label>		
								</td>
								<input type="hidden" name="is_entry_y" id="is_entry_y" value="${info.is_entry_y}"/>
							</tr>
							
							<tr>
								<th>참여(제한)인원</th>
								<td>
									최대 <input type="text" name="entry_cnt" id="entry_cnt" class="frm_input " value="${info.entry_cnt}" autocomplete="off"/> 명
									<br><label for="bo_entry">(인원제한이 없는 경우에는 빈값이거나 0 명을 입력하세요.)</label>						
								</td>
							</tr>
							
							<tr>
								<th>모집기간</th>
								<td><input type="text" name="entry_start_dt" id="entry_start_dt" class="frm_input" value="${info.entry_start_dt}" autocomplete="off"/> ~ <input type="text" name="entry_end_dt" id="entry_end_dt" class="frm_input" value="${info.entry_end_dt}" autocomplete="off"/> </td>
							</tr>
							<tr>
								<th>참가신청 안내문구</th>
								<td>
									<textarea name="entry_notice" id="entry_notice"  class="frm_input font_set"  style="width:100%;height:80px;" autocomplete="off">${info.entry_notice}</textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>				
			</section>
			 --%>

			<div class="btn_area textcenter">
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.bo_trash eq '1' }">
					<a href="/admin/${depthName}/${pageName}/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${(info.bo_trash eq '0' or info.bo_trash eq '3') && info.bo_sn ne null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<%--
					<input type="button" value="페이스북에 포스팅하기 " id="facebookposting" onclick="submitFBPosting()" class="btn_size2 btn_orange" />
					--%>
					<%--
					<fb:login-button 
					  scope="public_profile,email,publish_pages"
					  onlogin="checkLoginState();">
					</fb:login-button>
					 --%>
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.bo_sn eq null}">
					<a href="/admin/${depthName}/${pageName}/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>		
				
								
			</div>
		</div>
	</div>
</form:form>
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
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script type="text/javaScript">

$(document).ready(function(){
	
	//구명조끼 옵션
	$("#is_jacket_offer").change(function(){
		if($('#is_jacket_offer').is(":checked")){
			$('#is_jacket_offer_y').val(1);
		}else{
			$('#is_jacket_offer_y').val(0);
		}
	});	
	//유입경로 옵션
	$("#is_inflow_path").change(function(){
		if($('#is_inflow_path').is(":checked")){
			$('#is_inflow_path_y').val(1);
		}else{
			$('#is_inflow_path_y').val(0);
		}
	});	
	//참가이유 옵션
	$("#is_attend_cause").change(function(){
		if($('#is_attend_cause').is(":checked")){
			$('#is_attend_cause_y').val(1);
		}else{
			$('#is_attend_cause_y').val(0);
		}
	});	
	//낚시경력 옵션
	$("#is_naksi_career").change(function(){
		if($('#is_naksi_career').is(":checked")){
			$('#is_naksi_career_y').val(1);
		}else{
			$('#is_naksi_career_y').val(0);
		}
	});	
	//--
	
	
	$("#bo_start_dt").datepicker({
		language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	$("#bo_end_dt").datepicker({
		 language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	//참여자 추가
	$("#entry_start_dt").datepicker({
		language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	$("#entry_end_dt").datepicker({
		 language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	if($('#is_entry_y').val()==1){
		$('.is_entry_check').css("display","");
	}
	$("#is_entry").change(function(){
		if($('#is_entry').is(":checked")){
			$('#is_entry_y').val(1);
		}else{
			$('#is_entry_y').val(0);
		}
	});	
		
	
	//자신의 글 수정시 이메일 주소값을 직접입력하기로 입력한 경우 id="bo_email2"에 나타납니다.
	var x1 = document.getElementById("bo_email2");
	for(var i=0;i<imform.email_addr.length;i++){
		if(imform.email_addr.value == '' && $('#bo_sn').val()!=''){
			$('#self').attr("selected",true);
			document.getElementById("bo_email2").style.display = "inline";
		}
	}
	
	
	$("#gongzi").change(function(){
		if($('#gongzi').is(":checked")){
			$('#bo_notice_y').val(1);
		}else{
			$('#bo_notice_y').val(0);
		}
	});
	
	$("#is_autosend_sms").change(function(){
		if($('#is_autosend_sms').is(":checked")){
			$('#is_autosend_sms_y').val(1);
		}else{
			$('#is_autosend_sms_y').val(0);
		}
	});
	

});



	
    var depthName = "${depthName}";
    var pageName = "${pageName}";
	
    //전역변수선언
    var oEditors = [];

   
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "bo_content",
        sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
        
        fOnAppLoad : function(){
        	oEditors.getById["bo_content"].exec("PASTE_HTML", [" "]);
        },
        fCreator: "createSEditor2"
    });
    
    
    var x = document.getElementById("bo_email2");

    function chgaddr(){
    	
    	if(imform.email_addr.value=='1'){ // 직접 입력
    		x.style.display = "inline";
    	
    	}else{ // 직접입력아닌경우
    		x.style.display="none";
    	
    	}
    }

    //전송버튼 클릭이벤트
	function submitContents() {
	

    	
		if($("#bo_subject").val()== ""){
			alert("제목을 입력하세요.");
			return false;
		}

	
		if($("#bo_start_dt").val() == ""){
			alert("시작일을 입력하세요.");
			return false;
		}

		if($("#bo_end_dt").val() == ""){
			alert("종료일을 입력하세요.");
			return false;
		}
	
    // 에디터의 내용이 textarea에 적용된다.
    oEditors.getById["bo_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("bo_content").value==' <p>&nbsp;'){
    	alert('내용을 입력해주세요.');
    	return false;   	
    }
    
    if($('#bo_cate').val()==''){
    	alert('구분을 선택해주세요.');
    	return false;
    }
    
    if($('#bo_start_dt').val()==''){
    	alert('기간을 확인하세요.');
    	return false;
    }
    
    var xy = document.getElementById("bo_email2");
	if($('#email_addr').val()=="1"){
		addr_value = $('#self').val();
		addr_value ="";
		addr_value=xy.value;
		$('#self').val(addr_value);
	}
	
    
//     if($('#bo_start_dt').val()==$('#bo_end_dt').val()){
//     	$('#bo_end_dt').val(null);
//     }
    
//     var startDate = $( "input[name='bo_start_dt']" ).val();
//     var startDateArr = startDate.split('-');
     
//     var endDate = $( "input[name='bo_end_dt']" ).val();
//     var endDateArr = endDate.split('-');
             
//     var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
//     var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
     
//     if(startDateCompare.getTime() > endDateCompare.getTime()) {
//         alert("기간의 시작날짜와 종료날짜를 확인해 주세요.");
//         return false;;
//     }
    
    if(!$("#mimg").val()){
    	//포스터 필수 제외
    	//alert("포스터를 등록해주세요.");
    	//return false;
    }
    
     try {
    	 if($('#bo_sn').val()!=''){
    		 $("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/update_data.do");
				$("#imform").submit(); 
			}else{
				$("#imform").attr("action","/admin/"+depthName+"/"+pageName+"/insert_data.do");
				document.getElementById("imform").submit();			
			}
    	 
    } catch(e) {
     
    }
   
    
}
    

// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["bo_content"].exec("PASTE_HTML", [sHTML]);
   
}

function fn_egov_deleteFile(atchFileId, fileSn) {
	forms = document.getElementsByTagName("form");

	for (var i = 0; i < forms.length; i++) {
		if (typeof(forms[i].atchFileId) != "undefined" &&
				typeof(forms[i].fileSn) != "undefined" &&
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

//첨부파일 행 추가 삭제
var j=0;
function insertinput(){
		

	if($("#ftable tr").length>=18){
		alert("파일 첨부는 10개까지 가능합니다.");
		return false;
	}else{
	$(".t_write > tbody:last").append('<tr id="file_'+(j+1)+'">\
					<th><label for="chart_'+(j+1)+'">첨부파일</label></th>\
					<td style="padding-top:10px;">\
						<input type="checkbox" id="chart_'+(j+1)+'" name="atch_file_'+(j+1)+'"/>\
						<input type="file" size="30" name="bo_file_'+(j+1)+'"  style="width:290px" class="file" /></td></tr>');
	j++;
	}
}

function deleteinput(){
	for(var k=0;k<=j;k++){
		if($("#chart_"+k).prop("checked")){
			$("#file_"+k).remove();
		}
	}

}


function submitFBPosting() {
	var frmPop= document.frmPopup;
	var specs = "left=10,top=10,width=800,height=480,toolbar=no,menubar=no,status=no,scrollbars=no,resizable=no";
    window.open('','popupViewFacebookPosting',specs);
    frmPosting.target = 'popupViewFacebookPosting'; 
    frmPosting.submit();   	
}


$('#bo_start_dt').change(function(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+mm+dd;
	
	var choice_val = $('#bo_start_dt').val().replace(/-/gi,"");
	if(parseInt(choice_val)<parseInt(today)){
		alert("오늘날짜보다 이전날짜를 선택할 수 없습니다.");
		$('#bo_start_dt').val('');
	}
})

$('#bo_end_dt').change(function(){
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+mm+dd;
	
	
	if(!$.trim($('#bo_start_dt').val())){
		alert("시작날짜부터 선택해주세요.");
		$('#bo_end_dt').val('');
	}
	
	var start_dt = $('#bo_start_dt').val().replace(/-/gi,"");
	var end_dt = $('#bo_end_dt').val().replace(/-/gi,"");
	if(parseInt(end_dt)<parseInt(start_dt)){
		alert("시작날짜보다 이전날짜를 선택할 수 없습니다.");
		$('#bo_end_dt').val('');
	}
	
	if(parseInt(end_dt)<parseInt(today)){
		alert("오늘날짜보다 이전날짜를 선택할 수 없습니다.");
		$('#bo_end_dt').val('');
	}
})


function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample4_postcode').value = data.zonecode; //5자리 새우편번호 사용
            document.getElementById('sample4_roadAddress').value = fullRoadAddr;
            document.getElementById('sample4_jibunAddress').value = data.jibunAddress;

            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
            if(data.autoRoadAddress) {
                //예상되는 도로명 주소에 조합형 주소를 추가한다.
                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

            } else if(data.autoJibunAddress) {
                var expJibunAddr = data.autoJibunAddress;
                document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

            } else {
                document.getElementById('guide').innerHTML = '';
            }
        }
    }).open();
}



$('#is_entry').click(function(){
	if($(this).is(':checked')){
		$('.is_entry_check').css("display","");    		
	}else{
		$('.is_entry_check').css("display","none");
	}
})


function add_price() {
	var cnt = $('.price_item').length;//전체 카운트
	var idx = cnt+1;
	var html_item = '';
	html_item += '<li id="price_item_'+idx+'" class="price_item">';
	html_item += '<input type="text" name="price_item_name" class="frm_input" value="" autocomplete="off" placeholder="요금명칭"/> ';
	html_item += '<input type="number" name="price_item_cash" class="frm_input" value="" autocomplete="off" placeholder="요금금액(숫자)"/> ';
	html_item += '<input type="button" class="btn lgrey" onclick="del_price('+idx+')" value="항목삭제">';
	html_item += '</li>';
	$('#price_item_list').append(html_item);
}

function del_price(idx) {
	$('#price_item_'+idx).remove();	
}

</script>
<form name="frmPosting" action="https://m.tirebank.com/facebook_posting.php" method="post">
<input type="hidden" name="title" value="${info.bo_subject}">
<input type="hidden" name="content" value="<c:out value="${info.bo_content}" />">
</form>





<%--
<script>
//페이스북 포스팅을 사용하려면 https 로 전환해야 한다.

  window.fbAsyncInit = function() {
    FB.init({
      appId      : '2450207618538872',
      cookie     : true,
      xfbml      : true,
      version    : 'v3.0'
    });
      
    FB.AppEvents.logPageView();   
      
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "https://connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
  
  function checkLoginState() {
	  FB.getLoginStatus(function(response) {
	    statusChangeCallback(response);
	  });
	}
  
  
  function statusChangeCallback(response) {
      console.log('statusChangeCallback');
      console.log(response);
      // The response object is returned with a status field that lets the
      // app know the current login status of the person.
      // Full docs on the response object can be found in the documentation
      // for FB.getLoginStatus().
      if (response.status === 'connected') {
          // Logged into your app and Facebook.
          console.log('Welcome!  Fetching your information.... ');
          FB.api('/me', function (response) {
              console.log('Successful login for: ' + response.name);
              createFBPost();
          });
      } else {
          // The person is not logged into your app or we are unable to tell.
          alert('페이스북 로그인이 필요합니다.');
      }
  }
  
  
  
  
  function submitFBPosting() {
	  checkLoginState();
  }
  
  function createFBPost() {
	  if(confirm('페이스북에 포스팅 하시겠습니까?')) {
		  https://graph.facebook.com/467073916685768
		  
		  
		  FB.api(
			    "/365152433860144/feed",
			    "POST",
			    {
			        "message": "This is a test message"
			    },
			    function (response) {
			      if (response && !response.error) {
			       	console.log(response);
			      }
			    }
			);
	  }
  }
     
</script>
 --%>



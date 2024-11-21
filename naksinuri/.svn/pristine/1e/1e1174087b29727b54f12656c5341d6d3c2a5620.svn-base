<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="4"/>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />

<%@include file="../../layout/m/head.jsp"%>

<div>
	<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}"/>
		<input type="hidden" name="returnUrl" value="<c:url value='/sosig/congress/m/write2.do'/>"/>
		<input type="hidden" name="bo_atch_file" id="bo_atch_file" value="${info.bo_atch_file}">
		<input type="hidden" name="bo_email" id="bo_email">
		<input type="hidden" name="bo_phone" id="bo_phone">
		<input type="hidden" name="fileSn" id="fileSn">
		<input type="hidden" name="atchFileId" id="atchFileId">
		<input type="hidden" name="bo_main_img" value="${info.bo_main_img}">
		<input type="hidden" name="bo_sub_img" value="${info.bo_sub_img}">
		<input type="hidden" name="fileListCnt">
		<input type="hidden" name="entry_cnt" id="frm_entry_cnt"  value="${info.entry_cnt}" />
	
	<div id="customerSound" class="content respon3">
		<section id="writeForm" class="write_box">
			<h2>낚시대회</h2>
				
			<div class="agree_box">
				<h3>개인정보 수집 및 활용에 관한 동의</h3>
				<div class="agree_text">
					1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 사이트 회원관리 등<br />
					2. 수집항목 : 이름, 휴대폰번호, 전자우편<br />
					3. 개인정보 보유·이용기간 : 동의일로부터 동의철회(직권해지)시 까지<br />
					4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br /><br />
					동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
				</div><br/>
				<div class="agree_yesorno">
					개인정보 수집 및 활용에 대하여 <br/><br/>
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>                                                                                                                       
				</div>
			</div>
			
			<dl>
				<dt><span class="colorRed">*</span> 제목</dt>
				<dd><input type="text" class="write_input w100" placeholder="제목을 입력해주세요." id="bo_subject" name="bo_subject" value="<c:out value="${info.bo_subject }"/>"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 주최</dt>
				<dd><input type="text"  class="write_input w100" placeholder="주최를 입력해주세요." id="organizer" name="organizer" value="<c:out value="${info.organizer}"/>"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 주관</dt>
				<dd><input type="text"  class="write_input w100" placeholder="주관를 입력해주세요." id="host" name="host" value="<c:out value="${info.host}"/>"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 담당자</dt>
				<dd><input type="text"  class="write_input w100" placeholder="담당자를 입력해주세요." id="bo_name" name="bo_name" value="<c:out value="${info.bo_name }"/>"/></dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 구분</dt>
				<dd>
					<select name="bo_cate" id="bo_cate">
						<option value="">구분을 선택하세요.</option>
						<option value="대회"<c:if test="${info.bo_cate eq '대회'}">selected</c:if>>대회</option>
						<option value="박람회"<c:if test="${info.bo_cate eq '박람회'}">selected</c:if>>박람회</option>								
					</select> 
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 시작날짜</dt>
				<dd>
					<input type="text" id="bo_start_dt" name="bo_start_dt" readonly value="${info.bo_start_dt}">
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 종료날짜</dt>
				<dd>
					<input type="text" id="bo_end_dt" name="bo_end_dt" readonly value="${info.bo_end_dt}">
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 포스터</dt>
				<c:choose>
					<c:when test="${bmimg ne null }">
						<dd>
							<c:out value="${bmimg.orignl_file_nm}"/>&nbsp;<c:out value="[${bmimg.file_size} Byte]"/>
							<input type="hidden" id="mimg" value="${bmimg.bo_main_img}">
							<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							 width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${bmimg.bo_main_img}"/>','<c:out value="${bmimg.file_sn}"/>');" />
						</dd>
					</c:when>
					
					<c:otherwise>
					<dd><input type="file" id="mimg" name="mimg" ></dd>
					</c:otherwise>
					
				</c:choose>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 대회장소</dt>
				<dd>
				<input type="text" style="display:none" id="sample4_postcode" placeholder="우편번호">
				<input type="text" class="w100" id="sample4_roadAddress" name="cg_location" placeholder="대회장소를 입력해주세요." value="<c:out value="${info.cg_location}"/>">
				<!-- <input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"> -->
				<input type="text" id="sample4_jibunAddress" style="display:none" placeholder="지번주소">
				<span id="guide" style="color:#999"></span>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 이메일</dt>
				<dd>
				<input type="text" class="write_input w30" placeholder="이메일을 입력하세요" name="bo_email1" id="bo_email1" value="${info.bo_email1 }">@
				<select id="email_addr" name="bo_email2" class="write_input" style="width:200px;" onchange="chgaddr();">					
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
			
				<input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요" id="bo_email2" value="<c:out value="${info.bo_email2 }"/>">
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 연락처</dt>
				<dd>
					<select class="naksi_select" id="bo_phone1" name="bo_phone1" value="${info.bo_phone1 }">
						<option <c:if test="${info.bo_phone1 eq '010' }">selected</c:if>>010</option>
						<option <c:if test="${info.bo_phone1 eq '011' }">selected</c:if>>011</option>
						<option <c:if test="${info.bo_phone1 eq '012' }">selected</c:if>>012</option>
						<option <c:if test="${info.bo_phone1 eq '014' }">selected</c:if>>014</option>
						<option <c:if test="${info.bo_phone1 eq '017' }">selected</c:if>>017</option>
						<option <c:if test="${info.bo_phone1 eq '018' }">selected</c:if>>018</option>
						<option <c:if test="${info.bo_phone1 eq '019' }">selected</c:if>>019</option>
						<option <c:if test="${info.bo_phone1 eq '070' }">selected</c:if>>070</option>
					</select> - 
					<input type="text" class="write_input" size="7" name="bo_phone2" maxlength="4"  id="bo_phone2" value="${info.bo_phone2 }"/> - 
					<input type="text" class="write_input" size="7" name="bo_phone3" maxlength="4"  id="bo_phone3" value="${info.bo_phone3 }"/>
				</dd>
			</dl>	
			<dl>
				<dt><span class="colorRed">*</span> 내용</dt>
				<dd><textarea class="write_textarea" rows="10" id="bo_content" name="bo_content" style="width:98%">${info.bo_content}</textarea></dd>
			</dl>
			<dl>
				<dt>참가자 설정</dt>
				<dd>
					<dl>
						<dt>
							<input type="checkbox" name="is_entry" id="is_entry" class="frm_input" <c:if test="${info.is_entry_y eq 1}">checked</c:if>/>
							<label for="is_entry"><span></span>참가자 설정을 활성화 하려면 필히 체크하세요.</label>
							<input type="hidden" name="is_entry_y" id="is_entry_y" value="<c:if test="${info.is_entry_y eq 1}">1</c:if>" />
						</dt>
					</dl>
					<div id="is_entry_check" style="display:none">
					<dl>
						<dt>참여(제한)인원</dt>
						<dd>
							최대 <input type="text" id="entry_cnt" class="frm_input " value="<c:out value="${info.entry_cnt}"/>" autocomplete="off"/> 명
							<br><label for="bo_entry">(인원제한이 없는 경우에는 빈값이거나 0 명을 입력하세요.)</label>
						</dd>
					</dl>
					<dl>
						<dt>모집기간</dt>
						<dd>
							<input type="text" name="entry_start_dt" id="entry_start_dt" readonly class="frm_input w40" value="${info.entry_start_dt}" autocomplete="off"/> ~ <input type="text" readonly name="entry_end_dt" id="entry_end_dt" class="frm_input w40" value="${info.entry_end_dt}" autocomplete="off"/> 
						</dd>					
					</dl>
					<dl>
						<dt>참가신청 안내문구</dt>
						<dd>
							<textarea name="entry_notice" id="entry_notice"  class="frm_input"  style="width:100%;height:80px;border:1px solid #cdcdcd" autocomplete="off"><c:out value="${info.entry_notice}"/></textarea>
						</dd>
					</dl>
					<dl>
					<dt>선택 옵션</dt>
						<dd>
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
						</dd>
					</dl>
					<dl>
					<dt>요금 옵션&nbsp;&nbsp;<input type="button" class="btn grey" onclick="add_price()" value="추가"></dt>
						<dd>
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
						</dd>
					</dl>	
					<dl>
						<dt>참가비</dt>
						<dd><input type="text" name="entry_cash" id="entry_cash" class="frm_input w100" value="<c:out value="${info.entry_cash}"/>" placeholder="1인 3만원 (중학생 이하는 50% 할인)"  /></dd>
					</dl>
					<dl>
						<dt>입금계좌</dt>
						<dd>
							<select id="cg_account_name" name="cg_account_name">
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
							<input type="text" id="cg_account" name="cg_account" size="50" value="<c:out value="${info.cg_account}"/>">
						</dd>
					</dl>
					</div>
				</dd>
			</dl>
			<dl>
				<dt><span class="colorRed">*</span> 암호설정</dt>
				<dd>
					<input type="password" id="bo_pass" name="bo_pass" class="write_input w100" placeholder="암호를 입력해주세요." autocomplete="off"/>				
				</dd>
			</dl>

			<div id="btnArea">
				<ul class="floats">
					<a href="#;" class="btn_request btn_blue" onclick="submitContents()">정보등록신청</a>
					<a href="#;" onclick="cancel()"  class="btn_prev btn_white">취소</a></li>
				</ul>
			</div>
		</section>
	</div>

	
</form:form>
</div>



<script type="text/javascript">


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
	
	
	if($('#is_entry_y').val()==1){
		$('#is_entry_check').css("display","block");
	}
	
	$('#bo_start_dt').datepicker({ language: "kr", dateFormat : 'yy-mm-dd', minDate: 0 });
	$('#bo_end_dt').datepicker({language: "kr", dateFormat: 'yy-mm-dd', minDate: 0 });
	
	//참여자 추가
	$("#entry_start_dt").datepicker({ language: "kr", dateFormat : 'yy-mm-dd', minDate: 0 });
	$("#entry_end_dt").datepicker({ language: "kr", dateFormat : 'yy-mm-dd', minDate: 0 });
	
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

	

	
		
		if(imform.email_addr.value=='1'){ // 직접 입력
			x1.style.display = "inline";
		
		}else{ // 직접입력아닌경우
			x1.style.display="none";
		
		}
	
})



function deleteinput(){
	for(var k=0;k<=j;k++){
		if($("#chart_"+k).prop("checked")){
			$("#file_"+k).remove();
		}
	}

}





function fn_egov_deleteFile(atchFileId, fileSn) {
	forms = document.getElementsByTagName("form");

	
	if(confirm("정말 삭제하시겠습니까??") == true){
		
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
	
	}else{
		return false;
	}
}


$('#is_entry').click(function(){
	if($(this).is(':checked')){
		$('#is_entry_check').css("display","block");
	}else{
		$('#is_entry_check').css("display","none");
	}
});


function cancel(){
	location.href="./list.do";
}
var x = document.getElementById("bo_email2");

function chgaddr(){
	
	if(imform.email_addr.value=='1'){ // 직접 입력
		x.style.display = "inline";
		imform.bo_email2.value="";
		//imform.bo_email2.focus();
	}else{ // 직접입력아닌경우
		x.style.display="none";
		imform.bo_email2.value=imform.email_addr.value;
	}
}


//전송버튼 클릭이벤트
function submitContents() {

	var ischk1 = false;
	var chk1 = document.getElementsByName("agreeing");
	
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	
	
	
	if(!$.trim($("#bo_subject").val())){
		alert("제목을 입력하세요.");
		return false;
	}
	
	if(!$.trim($("#bo_cate").val())){
		alert("구분을 선택해주세요.");
		return false;
	}
	
	if(!$.trim($('#bo_start_dt').val())){
		alert("시작날짜를 입력하세요.");
		return false;
	}
	
	if(!$.trim($('#bo_end_dt').val())){
		alert("종료날자를 입력하세요.");
		return false;
	}

	
	if(!$.trim($("#bo_name").val())){
		alert("담당자를 입력하세요.");
		return false;
	}
	
	
	var xy = document.getElementById("bo_email2");
	if($('#email_addr').val()=="1"){
		addr_value = $('#self').val();
		addr_value ="";
		addr_value=xy.value;
		$('#self').val(addr_value);
	}
	

	var email1 =  jQuery("#bo_email1").val();

	var regex = /^([\w-]+(?:\.[\w-]+)*)/;

	if(regex.test(email1) === false){
		alert("잘못된 이메일 형식입니다.")
		return false;
	}

	var email2 =  jQuery("#email_addr").val();
	var regex2 = /((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/; 
	
	if(regex2.test(email2) === false){
		alert("잘못된 이메일 형식입니다.")
		return false;
	}

	var phone_check = jQuery("#bo_phone2").val();
	var phone_check1 = jQuery("#bo_phone3").val();

	var regex3 = /\d{3,4}/;
	var regex4 = /\d{4}$/;

	if(regex3.test(phone_check) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}

	if(regex4.test(phone_check1) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}

	
	
	var phone1 = $("#bo_phone1").val();
	var phone2 = $("#bo_phone2").val();
	var phone3 = $("#bo_phone3").val();
	var phone = phone1 + "-" + phone2 + "-" + phone3;
	$("#bo_phone").val(phone);

	var email = email1 + "@" + imform.email_addr.value;
	$("#bo_email").val(email);
	
	// 에디터의 내용이 textarea에 적용된다.
	//oEditors.getById["bo_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);
	
	//에디터의 내용에 대한 값 검증은 이곳에서
	if ($("#bo_content").val() == " <p>&nbsp;</p>") {
		alert('내용을 입력해주세요.');
		return false;
	
	}
	

	
	
	/*
	entry_start=entry_start.replace(/-/gi, ""); 
	entry_end=entry_end.replace(/-/gi, "");
	bo_end_dt=bo_end_dt.replace(/-/gi, "");
	
	if(parseInt(entry_start)>parseInt(bo_end_dt)||parseInt(entry_end)>parseInt(bo_end_dt)){
		alert("모집기간을 다시 확인해주세요.");
		return false;
	}
	*/
	
	
	//참가자 신청을 안할 수도 있기 때문에
	var entry_cnt = $('#entry_cnt').val();
	if( entry_cnt == '') {
		$('#frm_entry_cnt').val(0);
	} else {
		$('#frm_entry_cnt').val(entry_cnt);
	}
	
	if($('#is_entry_y').val() == '1') {
		var entry_start,entry_end,bo_end_dt;
		entry_start = $('#entry_start_dt').val();
		entry_end = $('#entry_end_dt').val();
		
		if(!$.trim(entry_start)){
			alert("모집기간을 다시 확인해주세요.");
			return false;
		}
		
		if(!$.trim(entry_end)){
			alert("모집기간을 다시 확인해주세요.");
			return false;
		}
	}
	
	
	
	if($("#bo_pass").val()== ""){
		alert("암호를 설정해 주세요.");
		return false;
	}
	
	if(!ischk1){
		alert("약관에 동의해주세요.")
		return false;
	}
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}
	
	
	try {

		if ($('#bo_sn').val() != '') {
				$("#imform")
						.attr("action", "/sosig/congress/m/update_data.do");
				$("#imform").submit();
			} else {
				alert("입력하신 이메일로 접수번호를 발송해드렸습니다.");
				$("#imform")
						.attr("action", "/sosig/congress/m/insert_data.do");
				document.getElementById("imform").submit();
			}

	} catch (e) {

	}

	}
	
	
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
/* 	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+mm+dd; */
	
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
	
	/* if(parseInt(end_dt)<parseInt(today)){
		alert("오늘날짜보다 이전날짜를 선택할 수 없습니다.");
		$('#bo_end_dt').val('');
	} */
})

$('#entry_end_dt').change(function(){
	
	if(!$.trim($('#entry_start_dt').val())){
		alert("시작날짜부터 선택해주세요.");
		$('#entry_end_dt').val('');
	}
	
	var start_dt = $('#entry_start_dt').val().replace(/-/gi,"");
	var end_dt = $('#entry_end_dt').val().replace(/-/gi,"");
	if(parseInt(end_dt)<parseInt(start_dt)){
		alert("시작날짜보다 이전날짜를 선택할 수 없습니다.");
		$('#entry_end_dt').val('');
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
<%@include file="../../layout/m/tail.jsp"%>

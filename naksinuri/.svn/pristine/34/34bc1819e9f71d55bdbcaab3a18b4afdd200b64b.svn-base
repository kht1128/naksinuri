<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="sosig"/>
<c:set var="pageName" value="congressmbrwrite" />


<%@include file="../layout/head.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>

<style>
	.mgt50px{margin-top:50px}
	
	.write_box h2.championshopTit{height:auto;line-height:30px}
	.write_box h2.championshopTit span{display:block;font-size:16px;color:#999;margin-top:5px;}
	.write_box h2.championshopTit span b{color:#666}
	
	.write_box dl dt{width:200px}
	.write_box dl dd{}
		
	.write_box .zipcodeBox{
		display:inline-block;height:40px;line-height:40px;padding:0 10px;margin:0 0 4px 5px;border:none;background-color:#626262;color:#fff;vertical-align:top;
		-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}
	.board_list .list_tbl tr th, .board_list .list_tbl tr td {
		padding:10px;
	}		
		
</style>
	
	<div id="customerSound" class="content respon3">
	
		<input type="hidden" id="is_jacket_offer_y" value="${info.is_jacket_offer_y}" />
		<input type="hidden" id="is_inflow_path_y" value="${info.is_inflow_path_y}" />
		<input type="hidden" id="is_attend_cause_y" value="${info.is_attend_cause_y}" />
		<input type="hidden" id="is_naksi_career_y" value="${info.is_naksi_career_y}" />
		
		<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}"/>
		
		<section id="writeForm" class="write_box">
			<h2 class="championshopTit">
				<em><c:out value="${info.bo_subject}"/></em>
				<span>기간 : <b>${info.bo_start_dt} ~ ${info.bo_end_dt}</b><br/>주최 : <b><c:out value="${info.organizer}"/></b><br/>주관 : <b><c:out value="${info.host}"/></b></span>
			</h2>
			<div class="agree_box" style="line-height:22px;">
				<h3>개인정보 수집 및 이용에 대한 고지사항</h3>
				<div class="agree_text" >
					* 개인 정보 수집 및 이용목적 : 낚시대회 운영 및 홍보 등<br/>
					* 수집항목 : 성명, 성별, 생년월일, e-mail, 휴대전화번호, 주소<br/>
					* 보유 및 이용기간 : 신청일로부터 24개월<br/>
					* 동의 거부 권리안내 : 본 개인정보 수집에 대한 동의를 거부하실 수 있으며, 이 경우 참가신청이 일부 제한 될 수 있습니다.
				</div><br/>
				<div class="agree_yesorno">
					개인정보 수집 및 활용에 대하여 
					<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){ $('#agreeingy').click();}"></span>동의합니다.</label>
					<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span tabindex="0" onkeyPress="if (event.keyCode==13 || event.keyCode==32){ $('#agreeingn').click();}"></span>동의하지 않습니다.</label>                                                                                                                  
				</div>
			</div>
			<h2>신청자 정보 입력</h2>
			<p class="txt_red">어린이 민물낚시는 보호자 동반이 필수입니다. 어린이 낚시대회 보호자는 민물낚시 대회 참여가 불가능합니다.</p><p>&nbsp;</p>
			<div class="owner_box">
				<div id="owner_box">
				<dl>
					<dt><span class="colorRed">*</span> 신청자(보호자)</dt>
					<dd>
						<input type="text" size="50" id="own_name" name="own_name" class="write_input" placeholder="신청자(보호자) 이름을 입력해주세요." title="신청자 이름"/>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 성별</dt> 
					<dd>
						<div class="choicebox">
							<input type="radio" id="own_gender_m" name="own_gender" value="남자"/><label for="own_gender_m"><span></span>남자</label>
							<input type="radio" id="own_gender_w" name="own_gender" value="여자"/><label for="own_gender_w"><span></span>여자</label>
						</div>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 생년월일</dt>
					<dd>
					<!--  작업중 멈춤 2019년 고도화 때 -->
					<!-- -
						<select id="own_birth1" name="own_birth1" class="naksi_select own_birth1" >
							<option value='' >년도</option>			
							<%
							for(int i=1950 ; i < 2018; i++ ) {
							%>
								<option value='<%=i%>' ><%=i%></option>
							<%
							}
							%>
						</select>
						
						<select id="own_birth2" name="own_birth2" class="naksi_select own_birth2" >
							<option value='' >월</option>			
							<%
							for(int i=1 ; i < 13; i++ ) {
								String day = String.format("%02d", i);
							%>
								<option value='<%=day%>' ><%=day%></option>
							<%
							}
							%>
						</select>
						
						<select id="own_birth3" name="own_birth3" class="naksi_select own_birth3" >
							<option value='' >일</option>		
							
						</select>
						-->
						
						
						<input type="text" id="own_birth" name="own_birth" class="write_input" placeholder="19990101" maxlength="8" title="생년월일"/>				
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 핸드폰번호</dt> 
					<dd>	
						<select class="naksi_select" id="own_phone1" name="own_phone1" title="핸드폰앞자리선택옵션(010,011,012,014,017,018,019,070)">
							<option>010</option>
							<option>011</option>
							<option>012</option>
							<option>014</option>
							<option>017</option>
							<option>018</option>
							<option>019</option>
							<option>070</option>
						</select> - 
						<input type="text" class="write_input" size="7" name="own_phone2" maxlength="4"  id="own_phone2" title="휴대폰 가운데 자리번호"/> - 
						<input type="text" class="write_input" size="7" name="own_phone3" maxlength="4"  id="own_phone3" title="휴대폰 끝 자리번호"/>
						<input type="hidden" id="own_hp" name="own_hp" value="" />
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 주소</dt>
					<!--  작업중 멈춤 2019년 고도화 때 -->
					<!-- -
					<dd class="multi_input">
						<input type="text" class="naksi_input readonly" id="busipostcode" readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="busiaddress()">우편번호검색</a>
						
						<div id="wrap" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>

						<input type="text" id="busiaddress1" readonly class="naksi_input w100" /><br />
						<input type="text" id="busiaddress2" class="naksi_input w100" placeholder="상세주소"/>
						<input type="text" id="own_address" name="own_address" size="50" class="write_input" placeholder="00(시) 00(시군구)">
						</dd>
					 -->	
					  
					<dd><input type="text" id="own_address" name="own_address" size="50" class="write_input" placeholder="00(시) 00(시군구)" title="시,군구"></dd>
								
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd>
					<input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" name="own_email1" id="own_email1" value="" title="이메일"> @
					<select id="own_email_addr" class="write_input" style="width:200px;" onchange="chgaddr_own();" title="이메일주소선택옵션(naver.com,daum.net,nate.com,yahoo.com,gmail.com,paran.com,hanmail.net,직접 입력하기)">					
						<option value="">선택하세요</option>
						<option value="naver.com" >naver.com</option>
						<option value="daum.net" >daum.net</option>
						<option value="nate.com" >nate.com</option>
						<option value="yahoo.com" >yahoo.com</option>
						<option value="gmail.com" >gmail.com</option>	
						<option value="paran.com" >paran.com</option>
						<option value="hanmail.net" >hanmail.net</option>
						<option id="self" value="1">직접 입력하기</option>
					</select>
					<input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요" id="own_email2" value="">
					<input type="hidden" id="own_email" name="own_email" value="" />
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 총 참여인원</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="총 참여인원을 입력해주세요." id="mbr_tot_count" / maxlength="5" title="총 참여인원">
					</dd>
				</dl>
				<%--
				<c:if test="${info.is_jacket_offer_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 구명조끼 지참여부</dt> 
						<dd>
							<div class="choicebox">
								<input type="radio" id="own_jacket_offer_y" name="own_jacket_offer" value="지참"/><label for="own_jacket_offer_y"><span></span>지참</label>
								<input type="radio" id="own_jacket_offer_n" name="own_jacket_offer" value="현장대여"/><label for="own_jacket_offer_n"><span></span>현장대여</label>
							</div>
						</dd>                                                                                                                           
					</dl>
				</c:if>
				 --%>	
				<c:if test="${info.is_naksi_career_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 낚시경력</dt> 
						<dd>
							<input type="text" size="25" class="write_input" placeholder="낚시경력을 입력해주세요.(년)" id="own_naksi_career" name="own_naksi_career" maxlength="5" title="낚시경력"> 년
						</dd>                                                                                                                           
					</dl>
				</c:if>
				<c:if test="${info.is_inflow_path_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 대회(행사)를 알게 된 경로</dt> 
						<dd>
							<div class="choicebox">
								<input type="radio" id="own_inflow_path_1" name="own_inflow_path" value="TV"/><label for="own_inflow_path_1"><span></span>TV</label>
								<input type="radio" id="own_inflow_path_2" name="own_inflow_path" value="잡지"/><label for="own_inflow_path_2"><span></span>잡지</label>
								<input type="radio" id="own_inflow_path_3" name="own_inflow_path" value="포털검색"/><label for="own_inflow_path_3"><span></span>포털검색</label>
								<input type="radio" id="own_inflow_path_4" name="own_inflow_path" value="인터넷카페"/><label for="own_inflow_path_4"><span></span>인터넷카페</label>
								<input type="radio" id="own_inflow_path_5" name="own_inflow_path" value="블로그"/><label for="own_inflow_path_5"><span></span>블로그</label>
								<input type="radio" id="own_inflow_path_6" name="own_inflow_path" value="기사"/><label for="own_inflow_path_6"><span></span>기사</label>
								<input type="radio" id="own_inflow_path_7" name="own_inflow_path" value="지인"/><label for="own_inflow_path_7"><span></span>지인</label>
								<input type="radio" id="own_inflow_path_8" name="own_inflow_path" value="기타"/><label for="own_inflow_path_8"><span></span>기타</label>
							</div>
						</dd>                                                                                                                           
					</dl>
				</c:if>
				<c:if test="${info.is_attend_cause_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 참가동기</dt> 
						<dd>
							<label for="own_attend_cause" class="blind">참가동기</label>
							<textarea id="own_attend_cause" name="own_attend_cause" rows="4" class="write_textarea"></textarea>
						</dd>                                                                                                                           
					</dl>
				</c:if>
				<dl>
					<dt><span class="colorRed">*</span> 입금자명</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="입금자명을 입력해주세요." id="own_repre_name" name="own_repre_name" title="입금자명"/>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 입금예정일</dt>
					<dd>
						<input type="text" id="own_depo_date" name="own_depo_date" size="50" class="write_input" placeholder="입금가능한 날짜를 선택해주세요.(년도-월-일)" title="입금가능 날짜"/>				
					</dd>
				</dl>
				<dl>
					<dt>요청사항 및 의견</dt> 
					<dd>
						<label for="own_etc" class="blind">요청사항 및 의견</label>
						<textarea id="own_etc" name="own_etc" rows="4" class="write_textarea"></textarea>
					</dd>                                                                                                                           
				</dl>
				<dl></dl>
				</div>
				<div id="owner_box_opener">신청자 입력정보 접기</div>
			</div>	
			
			<h2>참가자 추가 및 변경</h2>
			<!-- -->
			<div class="client_excel_box">
				<div id="client_excel_box">				
				<dl>
					<dt>엑셀양식 다운로드</dt>
					<dd class="pt20">
						<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
		                    <c:param name="param_atchFileId" value="FILE_000000000018228" />
		                </c:import>
					</dd>
				</dl>
				<dl>
					<dt>엑셀양식 업로드</dt>
					<dd class="pt20">
						<span class="txt_red"><b>참가자 양식에 맞춰 엑셀파일을 작성하면 한번에 추가할 수 있습니다.</b></span>
						
						<input type="file" id="client_excel_f" name="client_excel_f" title="엑셀양식 업로드">
					</dd>
				</dl>
				<div id="btnArea" class="noupline">
					<a href="#;" class="btn_request btn_blue" onclick="add_excel_member()" title="참가자 엑셀파일 실행">참가자 엑셀파일 실행하기</a>
				</div>
				<br><br>
				<dl></dl>
				</div>
				<div id="client_excel_box_opener">참가자 일괄등록 접기</div>
			</div>
			
			<div class="client_box">
				<div id="client_box">
				<dl>
					<dt>참가자 정보 자동입력</dt>
					<dd>
						<div class="choicebox">
							<input type="checkbox" id="is_enter" class="frm_input" />
							<label for="is_enter"><span></span>체크하시면 위 신청자 정보의 내용을 가져옵니다.<br>(신청자도 참가하는 경우에는 참가자 리스트에 필히 추가해주셔야 합니다.)</label>
						</div>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 참여유형 선택</dt>
					<dd>
						<select class="naksi_select" id="frm_ci_type" name="frm_ci_type" onchange="chg_citype();" title="참여유형선택옵션(대회참가자,동행자)">
						<c:choose>
							<c:when test="${info.bo_sn eq '1172'}">
								<option value="보호자">보호자</option>
								<option value="어린이 참가자">어린이 참가자</option>
							</c:when>
							<c:otherwise>
								<option value="대회참가자">대회참가자</option>
								<option value="동행자">동행자</option>
							</c:otherwise>
						</c:choose>
						</select>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 참가자명</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="참가자명을 입력해주세요." id="frm_mbr_name" name="frm_mbr_name"  title="참가자명"/>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 성별</dt> 
					<dd>
						<div class="choicebox">
							<input type="radio" id="frm_mbr_gender_m" name="frm_mbr_gender" value="남자"/><label for="frm_mbr_gender_m"><span></span>남자</label>
							<input type="radio" id="frm_mbr_gender_w" name="frm_mbr_gender" value="여자"/><label for="frm_mbr_gender_w"><span></span>여자</label>
						</div>
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 생년월일</dt>
					<dd>
						<input type="text" id="frm_mbr_birth" name="frm_mbr_birth" class="write_input" placeholder="19990101" maxlength="8" title="생년월일"/>				
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 핸드폰번호</dt> 
					<dd>	
						<select class="naksi_select" id="frm_bo_phone1" name="frm_bo_phone1" title="핸드폰번호 앞자리선택옵션(010,011,012,014,017,018,019,070)">
							<option>010</option>
							<option>011</option>
							<option>012</option>
							<option>014</option>
							<option>017</option>
							<option>018</option>
							<option>019</option>
							<option>070</option>
						</select> - 
						<input type="text" class="write_input" size="7" name="frm_bo_phone2" maxlength="4"  id="frm_bo_phone2" title="핸드폰번호 가운데자리 번호"/> - 
						<input type="text" class="write_input" size="7" name="frm_bo_phone3" maxlength="4"  id="frm_bo_phone3" title="핸드폰번호 끝자리 번호"/>
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 주소</dt>
					<dd><input type="text" id="frm_mbr_address" size="50" class="write_input" placeholder="00(시) 00(시군구)" title="시,군구"><span class="txt_red mgr10px" style="display:none" id="frm_mbr_address_label">&nbsp;어린이 참가자의 경우 주소를 입력하지 않아도 됩니다.</span></dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 신청자(보호자)와의 관계</dt> 
					<dd>	
						<input type="text" name="frm_mbr_relation" id="frm_mbr_relation" placeholder="본인 또는 가족" title="신청자와의 관계">
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt>소속</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="(예시) 00학교 00학년 , 그룹(모임) 등" id="frm_mbr_belongto" title="소속"/>
					</dd>
				</dl>
				<c:if test="${info.is_jacket_offer_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 구명조끼 지참여부</dt> 
						<dd>
							<div class="choicebox">
								<input type="radio" id="frm_jacket_offer_y" name="frm_jacket_offer" value="지참"/><label for="frm_jacket_offer_y"><span></span>지참</label><span class="txt_red mgr10px">(구명조끼 지참시 기념품증정)</span>
								<input type="radio" id="frm_jacket_offer_n" name="frm_jacket_offer" value="현장대여"/><label for="frm_jacket_offer_n"><span></span>현장대여</label>
							</div>
						</dd>                                                                                                                           
					</dl>
				</c:if>	
				<c:if test="${info.is_naksi_career_y eq '1'}">
					<dl>
						<dt><span class="colorRed">*</span> 낚시경력</dt> 
						<dd>
							<input type="text" size="25" class="write_input" placeholder="낚시경력을 입력해주세요.(년)" id="frm_naksi_career" title="낚시경력"/> 년
						</dd>                                                                                                                           
					</dl>
				</c:if>
				<dl>
					<dt>입금금액</dt>
					<dd>
						<div class="choicebox">
						<c:choose>
							<c:when test="${info.price_item_name eq null}">
								<!-- <input type="text" size="50" class="write_input" id="frm_deposit_amount" name="frm_deposit_amount" value="0" readonly /> -->
								<input type="radio" class="price_item" id="price_item_1" name="frm_deposit_amount" value="0" checked /><label for="price_item_1"><span></span><b>참가비 없음</b></label>
							</c:when>
							<c:otherwise>
								<c:set var="price_list_name" value="${fn:split(info.price_item_name,',')}" />
								<c:set var="price_list_cash" value="${fn:split(info.price_item_cash,',')}" />
								<c:forEach var="item_value" items="${price_list_name}" varStatus="i">
									<input type="radio" class="price_item" id="price_item_${i.count}" name="frm_deposit_amount" value="${price_list_cash[i.index]}"/><label for="price_item_${i.count}"><span></span><b>${item_value}</b>&nbsp;<fmt:formatNumber value="${price_list_cash[i.index]}" groupingUsed="true"></fmt:formatNumber>&nbsp;원</label>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						</div>	
					</dd>
				</dl>
				<dl>
					<dt>요청사항 및 의견</dt> 
					<dd>
						<label for="frm_etc" class="blind">요청사항 및 의견</label>
						<textarea name="frm_etc" id="frm_etc" rows="4" class="write_textarea"></textarea>
					</dd>                                                                                                                           
				</dl>
					
				<div id="btnArea" class="noupline">
					<a href="#;" class="btn_request btn_blue" onclick="add_member()" title="참가 목록 추가">참가 목록에 추가하기</a>
				</div>
				<br><br>
				<dl></dl>
				</div>
				<div id="client_box_opener">참가자 입력정보 접기</div>
			</div>
			
			
			<!-- //참가자 목록 : start// -->
			<h2 class="mgt50px">참가자 목록</h2>
			<div class="/*apndList mgt50px*/ board_list ">
				<table class="/*apnd_tbl basic_tbl*/ list_tbl">
					<caption>참가자목록 리스트</caption>
					<colgroup>
						<col width="130"/>
						<col width="130"/>
						<col width="80"/>
						<col width="100"/>
						<col />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>이름</th>
							<th>성별</th>
							<th>생년월일</th>
							<th>관계</th>
							<th>소속</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody id="apnd">
						<tr>
							<td colspan="6">참가자를 목록에 추가하면 표시됩니다.</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- //참가자 목록 : end// -->
			<div id="btnArea" class="noupline">
				<a href="#;" class="btn_request btn_blue" onclick="submitContents()" title="참가신청완료">참가신청완료</a>
				<a href="#;" onclick="cancel()"  class="btn_prev btn_white" title="취소">취소</a>
			</div>
		</section>
		</form:form>
	</div>

<%@include file="../layout/tail.jsp"%>


<script type="text/javascript">
var is_unlock_require_address = false;//주소 필수 해제

var is_jacket_offer_y = $('#is_jacket_offer_y').val();
var is_inflow_path_y = $('#is_inflow_path_y').val();
var is_attend_cause_y = $('#is_attend_cause_y').val();
var is_naksi_career_y = $('#is_naksi_career_y').val();

$(document).ready(function () {

	$( "#own_birth2" ).change(function() {
		var birth1 = $('#own_birth1').val();
		var birth2 = $('#own_birth2').val();

		var lastDay = ( new Date( birth1, birth2, 0) ).getDate();

		//console.log('lastDay         > ' + lastDay);

		$("#own_birth3 option").remove();

		for(i = 1 ; i <= lastDay; i++ ) {
			var ipad = pad(i,2);
		//console.log('lastDay         > ' + ipad);
			$("#own_birth3").append("<option value='"+ipad+"'>"+ipad+"</option>");
		}
	});
});

$("#frm_mbr_birth, #own_birth, #mbr_tot_count").on("keyup", function() {
    $(this).val($(this).val().replace(/[^0-9]/g,""));
});

//자리수 채우기
function pad(n, width) {
  n = n + '';
  return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}

$("#own_depo_date").datepicker({
	 language: "kr",
	dateFormat : 'yy-mm-dd'
});


var is_show_owner_box = true;
$('#owner_box_opener').click(function(){
	if(is_show_owner_box){
		$('#owner_box')
		.fadeIn('slow') 
        .slideUp('slow',function(){
    		$('#owner_box_opener').html('신청자 입력정보 펼치기');	
        }); 
		is_show_owner_box = false;		
	}else{
		$('#owner_box')
		.fadeOut('slow') 
        .slideDown('slow',function(){
    		$('#owner_box_opener').html('신청자 입력정보 접기');	
        }); 
		is_show_owner_box = true;
	}
});
function showOwnerInfo() {
	$('#owner_box')
	.fadeOut('slow') 
    .slideDown('slow',function(){
		$('#owner_box_opener').html('신청자 입력정보 접기');	
    }); 
	is_show_owner_box = true;
}
function hideOwnerInfo() {
	$('#owner_box')
	.fadeIn('slow') 
    .slideUp('slow',function(){
		$('#owner_box_opener').html('신청자 입력정보 펼치기');	
    }); 
	is_show_owner_box = false;
}

var is_show_client_box = true;
$('#client_box_opener').click(function(){
	if(is_show_client_box){
		$('#client_box')
		.fadeIn('slow') 
        .slideUp('slow',function(){
    		$('#client_box_opener').html('참가자 입력정보 펼치기');	
        }); 
		is_show_client_box = false;		
	}else{
		$('#client_box')
		.fadeOut('slow') 
        .slideDown('slow',function(){
    		$('#client_box_opener').html('참가자 입력정보 접기');	
        }); 
		is_show_client_box = true;
	}
});
var is_show_client_excel_box = true;
$('#client_excel_box_opener').click(function(){
	if(is_show_client_excel_box){
		$('#client_excel_box')
		.fadeIn('slow') 
        .slideUp('slow',function(){
    		$('#client_excel_box_opener').html('참가자 일괄등록 펼치기');	
        }); 
		is_show_client_excel_box = false;		
	}else{
		$('#client_excel_box')
		.fadeOut('slow') 
        .slideDown('slow',function(){
    		$('#client_excel_box_opener').html('참가자 일괄등록 접기');	
        }); 
		is_show_client_excel_box = true;
	}
});
function showClentInfo() {
	$('#client_box')
	.fadeOut('slow') 
    .slideDown('slow',function(){
		$('#client_box_opener').html('참가자 입력정보 접기');	
    }); 
	is_show_client_box = true;
}
function hideClentInfo() {
	$('#client_box')
	.fadeIn('slow') 
    .slideUp('slow',function(){
		$('#client_box_opener').html('참가자 입력정보 펼치기');	
    }); 
	is_show_client_box = false;
}


$("#is_enter").change(function(){
	if($('#is_enter').is(":checked")){
		if(!checkOwnerInfo()) {
			$('#is_enter').prop("checked", false);
			return;
		} 
		putOwnerInfo();
	}else{
		clearClientForm();
	}
});


//참여유형 선택변경
function chg_citype() {
	var frm_ci_type = $('#frm_ci_type').val();
	if(frm_ci_type=='어린이 참가자'){ 
		is_unlock_require_address = true;
		$('#frm_mbr_address_label').show();
	}else{ 
		is_unlock_require_address = false;
		$('#frm_mbr_address_label').hide();
	}
}

function chgaddr_own(){
	var own_email_addr = $('#own_email_addr').val();
	if(own_email_addr=='1'){ // 직접 입력
		$('#own_email2').css('display',"inline").val('').focus();
	}else{ // 직접입력아닌경우
		$('#own_email2').val(own_email_addr).css('display',"none");
	}
}


function putOwnerInfo() {
	clearClientForm();
	//이름 가져오기
	$('#frm_mbr_name').val($('#own_name').val());
	//성별 가져오기
	if($("input:radio[name=own_gender]:checked").val()=="남자"){
		$('#frm_mbr_gender_m').prop("checked","checked");
	}else{
		$('#frm_mbr_gender_w').prop("checked","checked");
	}
	//생년월일 가져오기
	$('#frm_mbr_birth').val($('#own_birth').val());
	//핸드폰번호 가져오기
	var full_hp = $('#own_hp').val();
	var strArray = full_hp.split("-");
	$('#frm_bo_phone1').val(strArray[0]);
	$('#frm_bo_phone2').val(strArray[1]);
	$('#frm_bo_phone3').val(strArray[2]);
	//주소
	$('#frm_mbr_address').val($('#own_address').val());
	/*
	//구명조끼 가져오기
	if(is_jacket_offer_y == '1') {
		if($("input:radio[name=own_jacket_offer]:checked").val()=="지참"){
			$('#frm_jacket_offer_y').prop("checked","checked");
		}else{
			$('#frm_jacket_offer_n').prop("checked","checked");
		}
	}
	*/
	//낚시경력 가져오기
	if(is_naksi_career_y == '1') {
		$('#frm_naksi_career').val($('#own_naksi_career').val());
	}	 
	//비고
	var own_etc = $('#own_etc').val();
	if(own_etc == '') {
		own_etc = ' ';
	}
	$('#frm_etc').val(own_etc);
}

function del_member(idx){
	$('#num'+idx).remove();
	var cnt = $('.add_mbr').length;
	if(cnt==0) {
		$('#apnd').html('<tr><td colspan="6">참가자를 목록에 추가하면 표시됩니다.</td></tr>');
	} 
}

function add_member(){	
	hideOwnerInfo();//신청자 정보 숨기기
	var cnt = $('.add_mbr').length;//전체 카운트
	var idx = cnt+1;
	var html_list = '';
	html_list+='<tr id="num'+idx+'" class="add_mbr">';
	//----- 검증
	//이름
	if($.trim($('#frm_mbr_name').val())=='') {
		alert('참가자 정보에 이름을 작성해주세요.');
		$('#frm_mbr_name').focus();
		return false;
	}
	//성별
	if($("input:radio[name=frm_mbr_gender]:checked").length < 1) {
		alert('참가자 정보에 성별을 선택해주세요.');
		return false;
	}
	//생년월일
	if($.trim($('#frm_mbr_birth').val())=='') {
		alert('참가자 정보에 생년월일을 작성해주세요.');
		$('#frm_mbr_birth').focus();
		return false;
	}
	//연락처
	var frm_bo_phone1 = $.trim($("#frm_bo_phone1").val());
	var frm_bo_phone2 = $.trim($("#frm_bo_phone2").val());
	var frm_bo_phone3 = $.trim($("#frm_bo_phone3").val());
	var regex3 = /\d{3,4}/;
	if(regex3.test(frm_bo_phone2) == false){
		alert("참가자 정보에 연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	var regex4 = /\d{4}$/;
	if(regex4.test(frm_bo_phone3) == false){
		alert("참가자 정보에 연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	//주소
	if(is_unlock_require_address) {
		
	} else {
		if($.trim($('#frm_mbr_address').val())=='') {
			alert('참가자 정보에 주소를 작성해주세요.');
			$('#frm_mbr_address').focus();
			return false;
		}
	}
	//신청자와의 관계
	if($.trim($('#frm_mbr_relation').val())=='') {
		alert('참가자 정보에 신청자(보호자)와의 관계를 작성해주세요.');
		$('#frm_mbr_relation').focus();
		return false;
	}
	//소속 - 필수항목에서 제외
	if($.trim($('#frm_mbr_belongto').val())=='') {
		//alert('참가자 정보에 소속을 작성해주세요.');
		//$('#frm_mbr_belongto').focus();
		//return false;
		$('#frm_mbr_belongto').val(' ');
	}
	//구명조끼
	if(is_jacket_offer_y == '1') {
		if($("input:radio[name=frm_jacket_offer]:checked").length < 1) {
			alert('참가자 정보에 구명조끼 지참여부를 선택해주세요.');
			return false;
		}
	}
	//낚시경력
	if(is_naksi_career_y == '1') {
		if($.trim($('#frm_naksi_career').val())=='') {
			alert('참가자 정보에 낚시경력을 작성해주세요.');
			$('#frm_naksi_career').focus();
			return false;
		}	
	}
	//입금금액
	if($("input:radio[name=frm_deposit_amount]:checked").length < 1) {
		alert('입금금액을 선택해주세요.');
		$('#frm_deposit_amount').focus();
		return false;
	}
	//----- 배치
	//참여유형 선택
	var frm_ci_type = $('#frm_ci_type').val();
	html_list+='<td style="display:none" id="ci_type'+idx+'" name="ci_type">'+frm_ci_type+'</td>';
	html_list+='<input type="hidden" name="ci_type" value="'+frm_ci_type+'"/>';
	//이름
	var frm_mbr_name = replaceTag($('#frm_mbr_name').val());
	html_list+='<td id="mbr_name'+idx+'">'+frm_mbr_name+'</td>';
	html_list+='<input type="hidden" name="mbr_name" value="'+frm_mbr_name+'"/>';
	//성별
	var frm_mbr_gender = $("input:radio[name=frm_mbr_gender]:checked").val();
	html_list+='<td id="mbr_gender'+idx+'" name="mbr_gender">'+frm_mbr_gender+'</td>';
	html_list+='<input type="hidden" name="mbr_gender" value="'+frm_mbr_gender+'"/>';
	//생년월일
	var frm_mbr_birth = $('#frm_mbr_birth').val();
	html_list+='<td id="mbr_birth'+idx+'">'+frm_mbr_birth+'</td>';
	html_list+='<input type="hidden" name="mbr_birth" value="'+frm_mbr_birth+'"/>';
	//연락처
	var frm_mbr_hp = frm_bo_phone1+"-"+frm_bo_phone2+"-"+frm_bo_phone3;
	html_list+='<td style="display:none" id="mbr_hp'+idx+'" name="mbr_hp">'+frm_mbr_hp+'</td>';
	html_list+='<input type="hidden" name="mbr_hp" value="'+frm_mbr_hp+'"/>';
	//주소
	var frm_mbr_address = replaceTag($('#frm_mbr_address').val());
	html_list+='<td style="display:none" id="mbr_address'+idx+'" name="mbr_address">'+frm_mbr_address+'</td>';
	html_list+='<input type="hidden" name="mbr_address" value="'+frm_mbr_address+'"/>';
	//신청자와의 관계
	var frm_mbr_relation = replaceTag($('#frm_mbr_relation').val());
	html_list+='<td id="mbr_relation'+idx+'" name="mbr_relation">'+frm_mbr_relation+'</td>';
	html_list+='<input type="hidden" name="mbr_relation" value="'+frm_mbr_relation+'"/>';
	//소속
	var frm_mbr_belongto = replaceTag($('#frm_mbr_belongto').val());
	html_list+='<td id="mbr_belongto'+idx+'" name="mbr_belongto">'+frm_mbr_belongto+'</td>';
	html_list+='<input type="hidden" name="mbr_belongto" value="'+frm_mbr_belongto+'"/>';
	//구명조끼
	if(is_jacket_offer_y == '1') {
		var frm_jacket_offer = $("input:radio[name=frm_jacket_offer]:checked").val();
		html_list+='<td style="display:none" id="jacket_offer'+idx+'" name="jacket_offer">'+frm_jacket_offer+'</td>';
		html_list+='<input type="hidden" name="jacket_offer" value="'+frm_jacket_offer+'"/>';
	}
	//낚시경력
	if(is_naksi_career_y == '1') {
		var frm_naksi_career = replaceTag($('#frm_naksi_career').val());
		html_list+='<td style="display:none" id="naksi_career'+idx+'" name="naksi_career">'+frm_naksi_career+'</td>';
		html_list+='<input type="hidden" name="naksi_career" value="'+frm_naksi_career+'"/>';
	}
	//입금금액
	var frm_deposit_amount = $("input:radio[name=frm_deposit_amount]:checked").val();
	//var frm_deposit_amount = $('#frm_deposit_amount').val();
	html_list+='<td style="display:none" id="deposit_amount'+idx+'" name="deposit_amount">'+frm_deposit_amount+'</td>';
	html_list+='<input type="hidden" name="deposit_amount" value="'+frm_deposit_amount+'"/>';
	//비고
	var frm_etc = replaceTag($('#frm_etc').val());
	if(frm_etc == '') {
		frm_etc = ' ';
	}
	html_list+='<td style="display:none" id="etc'+idx+'" name="etc">'+frm_etc+'</td>';
	html_list+='<input type="hidden" name="etc" value="'+frm_etc+'"/>';
	//
	html_list+='<td>';
	//삭제하기
	html_list+='<input type="button" class="btn grey" onclick="del_member('+idx+')" value="삭제" />';
	//수정하기
	html_list+='<input type="button" class="btn lgrey" onclick="select_member('+idx+')" value="수정하기" />';
	html_list+='</td>';
	//
	html_list+='</tr>';
	
	if(cnt==0) {
		$('#apnd').html(html_list);
	} else {
		$('#apnd').append(html_list);
	}
		
	//참가자 정보 초기화
	clearClientForm();
	
	//신청자 자동 입력 체크 박스 해제 
	$('#is_enter').prop("checked", false);
	
	alert(frm_mbr_name+"님의 정보가 추가되었습니다.");
	
}

function select_member(idx){
	showClentInfo();
	//참여유형선택 가져오기
	$('#frm_ci_type').val($('#ci_type'+idx).text());
	chg_citype();
	//이름 가져오기
	$('#frm_mbr_name').val(replaceTag($('#mbr_name'+idx).text()));
	//성별 가져오기
	if($('#mbr_gender'+idx).text()=="남자"){
		$('#frm_mbr_gender_m').prop("checked","checked");
	}else{
		$('#frm_mbr_gender_w').prop("checked","checked");
	}
	//생년월일 가져오기
	$('#frm_mbr_birth').val($('#mbr_birth'+idx).html());
	//핸드폰번호 가져오기
	var full_hp = $('#mbr_hp'+idx).html();
	var strArray = full_hp.split("-");
	$('#frm_bo_phone1').val(strArray[0]);
	$('#frm_bo_phone2').val(strArray[1]);
	$('#frm_bo_phone3').val(strArray[2]);
	//주소
	$('#frm_mbr_address').val(replaceTag($('#mbr_address'+idx).text()));
	//신청자와의 관계 가져오기
	$('#frm_mbr_relation').val(replaceTag($('#mbr_relation'+idx).text()));
	//소속 가져오기
	$('#frm_mbr_belongto').val(replaceTag($.trim($('#mbr_belongto'+idx).text())));
	//구명조끼 가져오기
	if(is_jacket_offer_y == '1') {
		if($('#jacket_offer'+idx).html()=="지참"){
			$('#frm_jacket_offer_y').prop("checked","checked");
		}else{
			$('#frm_jacket_offer_n').prop("checked","checked");
		}
	}
	//낚시경력 가져오기
	if(is_naksi_career_y == '1') {
		$('#frm_naksi_career').val(replaceTag($('#naksi_career'+idx).html()));
	}	 
	//입금금액 가져오기
	//$('#frm_deposit_amount').val($('#deposit_amount'+idx).html());
	var price = $('#deposit_amount'+idx).html();
	var cnt = $('.price_item').length;//전체 카운트
	for(var i=1; i<=cnt; i++) {
		if($('#price_item_'+i).val() == price) {
			$('#price_item_'+i).prop("checked","checked");
		}
	}		
	//비고
	$('#frm_etc').val(replaceTag($('#etc'+idx).html()));
	//목록에서 삭제
	del_member(idx);
}
function cancel(){
	$("#imform").attr("action", "./view.do");
	document.getElementById("imform").submit();
}
function clearClientForm() {
	//참여유형선택
	if('${info.bo_sn}' == '1172') {
		$('#frm_ci_type').val('보호자');	
	} else {
		$('#frm_ci_type').val('대회참가자');
	}
	chg_citype();
	//이름
	$('#frm_mbr_name').val('');
	//성별
	$("input[name=frm_mbr_gender]").attr("checked",false);
	//생년월일
	$('#frm_mbr_birth').val('');
	//연락처
	$('#frm_bo_phone1').val('010');
	$("#frm_bo_phone2").val('');
	$("#frm_bo_phone3").val('');
	//주소
	$('#frm_mbr_address').val('');
	//신청자와의 관계
	$('#frm_mbr_relation').val('');
	//소속
	$('#frm_mbr_belongto').val('');
	//구명조끼
	if(is_jacket_offer_y == '1') {
		$("input[name=frm_jacket_offer]").attr("checked",false);
	}
	//낚시경력
	$('#frm_naksi_career').val('');
	//입금금액
	$("input[name=frm_deposit_amount]").attr("checked",false);
	//비고
	$('#frm_etc').val(' ');
}

function checkOwnerInfo() {
	showOwnerInfo();
	//이름
	if($.trim($('#own_name').val())=='') {
		alert('신청자 정보에 신청자(보호자)이름을 작성해주세요.');
		$('#own_name').focus();
		return false;
	}
	//성별
	if($("input:radio[name=own_gender]:checked").length < 1) {
		alert('신청자 정보에 성별을 선택해주세요.');
		return false;
	}
	//생년월일
	if($.trim($('#own_birth').val())=='') {
		alert('신청자 정보에 생년월일을 작성해주세요.');
		$('#own_birth').focus();
		return false;
	}
	//연락처
	var own_phone1 = $.trim($("#own_phone1").val());
	var own_phone2 = $.trim($("#own_phone2").val());
	var own_phone3 = $.trim($("#own_phone3").val());
	var regex3 = /\d{3,4}/;
	if(regex3.test(own_phone2) == false){
		alert("신청자 정보에 연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	var regex4 = /\d{4}$/;
	if(regex4.test(own_phone3) == false){
		alert("신청자 정보에 연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	$('#own_hp').val(own_phone1+"-"+own_phone2+"-"+own_phone3);
	//이메일	
	var own_email1 = $.trim($("#own_email1").val());
	var own_email2 = $.trim($("#own_email2").val());
	if($('#own_email_addr').val()=="1"){		
		$('#self').val(own_email2);
	}
	if(own_email1.length!=0) { //이메일은 필수 처리 안함
		var regex = /^([\w-]+(?:\.[\w-]+)*)/;
		if(regex.test(own_email1) === false){
			alert("신청자 정보에 잘못된 이메일 형식입니다.")
			return false;
		}
	}
	var own_email_addr = $("#own_email_addr").val();
	if(own_email_addr) { //이메일은 필수 처리 안함
		var regex2 = /((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		if(regex2.test(own_email_addr) === false){
			alert("신청자 정보에 잘못된 이메일 형식입니다.")
			return false;
		}
	}
	$("#own_email").val(own_email1+"@"+own_email_addr);
	//주소
	if($.trim($('#own_address').val())=='') {
		alert('신청자 정보에 주소를 작성해주세요.');
		$('#own_address').focus();
		return false;
	}
	/*
	//구명조끼
	if(is_jacket_offer_y == '1') {
		if($("input:radio[name=own_jacket_offer]:checked").length < 1) {
			alert('신청자 정보에 구명조끼 지참여부를 선택해주세요.');
			return false;
		}
	}
	*/
	//낚시경력
	if(is_naksi_career_y == '1') {
		if($.trim($('#own_naksi_career').val())=='') {
			alert('신청자 정보에 낚시경력을 작성해주세요.');
			$('#own_naksi_career').focus();
			return false;
		}	
	}
	//유입경로
	if(is_inflow_path_y == '1') {
		if($("input:radio[name=own_inflow_path]:checked").length < 1) {
			alert('신청자 정보에 대회(행사)를 알게 된 경로를 선택해주세요.');
			return false;
		}
	}
	//참가동기
	if(is_attend_cause_y == '1') {
		if($.trim($('#own_attend_cause').val())=='') {
			alert('신청자 정보에 참가동기를 작성해주세요.');
			$('#own_attend_cause').focus();
			return false;
		}	
	}
	//입금자명
	if($.trim($('#own_repre_name').val())=='') {
		alert('신청자 정보에 입금자명을 작성해주세요.');
		$('#own_repre_name').focus();
		return false;
	}	
	//입금예정일
	if($.trim($('#own_depo_date').val())=='') {
		alert('신청자 정보에 입금예정일을 선택해주세요.');
		$('#own_depo_date').focus();
		return false;
	}	
	return true;
}


//전송버튼 클릭이벤트
function submitContents() {
	
	/* 신청자 정보 검증 */	
	if(!checkOwnerInfo()) {
		return false;	
	}
	
	//총 참여인원확인
	var cnt = $('.add_mbr').length;//전체 카운트
	var mbr_tot_count = $('#mbr_tot_count').val();
	if(mbr_tot_count==null || mbr_tot_count.length==0 || mbr_tot_count <= 0) {
		alert('신청자 정보의 총 참여인원이 설정이 올바르지 않습니다.');
		return false;
	} else {
		if(mbr_tot_count != cnt) {
			alert('신청자 정보에 입력한 총 참여인원과 참가자 목록 인원이 일치해야 합니다.');
			return false;
		}
	}

	var ischk1 = false;
	var ischk2 = false;
	var chk1 = document.getElementsByName("agreeing");
	var chk2 = document.getElementsByName("mbr_gender");
	for(var i=0;i<chk1.length;i++){
		if(chk1[i].checked == true){
			ischk1 = true;
			break;	
		}
	}
	
	for(var i=0;i<chk2.length;i++){
		if(chk2[i].checked == true){
			ischk2 = true;
			break;	
		}
	}
	
	
	if(!ischk1){
		alert("약관에 동의해주세요.")
		return false;
	}
	
	
	if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
		alert("개인정보 수집 및 활동 동의가 필요합니다.");
		return false;
	}
	
	
	$("#imform").attr("action", "/sosig/congress/mbrinsert_data.do");
	$("#imform").submit();

}
	
	
	function add_excel_member() {
		 var form = $('#imform')[0];
		 var formData = new FormData(form);
		 $.ajax({
	        type: "POST",
	        enctype: 'multipart/form-data',
	        url: "/sosig/congress/ajax_upload_excel.do",
	        data: formData,
	        processData: false,
	        contentType: false,
	        cache: false,
	        timeout: 600000,
	        success: function (data) {
	            //console.log("SUCCESS : ", data);
	            
	            var error_lines = "";
	            var error_field = "";
	            
	            var line_num = 1;
	            
	            error_code = data.error;
				msg = data.msg;
				
				if ( error_code != 0) {
					alert(msg);
					return false;
				}
				
				lists = data.lists;
				
				var cnt = $('.add_mbr').length;//전체 카운트
				var idx = cnt+1;
				var html_list = '';
				
				//console.log("cnt = " + cnt + " , idx = " + idx);
				
	
				
				var becontinue = 0;
				
				$.each(lists, function(i, n) {	
					becontinue = 1;
					
					$('#apnd tr').each(function(k, v) {
						var thisname = $(this).find("td").eq(0).html();	
						var thisbirth = $(this).find("td").eq(2).html();
						/*
						$(this).find('td').each (function(column, td) {
							thisname = 
							 console.log('td  == ' + column + " , " + td);
						});
						*/
						if ( thisname == n.A && thisbirth == n.C) {
							becontinue = 0;
						}
					});
					
					if (becontinue == 1 ) {
				
					//console.log(n.A + " ," + n.E);
					idx = idx+i;
					html_list = '';
					html_list+='<tr id="num'+idx+'" class="add_mbr">';
					
					//----- 검증
					//이름
					if($.trim(n.A)=='') {
						error_lines += line_num + i + " ,";
						error_field += "이름 ,";
					}
					//성별
					if($.trim(n.B)=='') {
						error_lines += line_num + i + " ,";
						error_field += "성별 ,";
					}
					//생년월일
					if($.trim(n.C)=='') {		
						error_lines += line_num + i + " ,";
						error_field += "생년월일 ,";
					}
					//연락처
					if($.trim(n.D)=='') {		
						error_lines += line_num + i + " ,";
						error_field += "연락처 ,";
					}					
					//주소
					if($.trim(n.E)=='') {						
						error_lines += line_num + i + " ,";
						error_field += "주소 ,";
					}
					//신청자와의 관계
					if($.trim(n.F)=='') {
						
						error_lines += line_num + i + " ,";
						error_field += "신청자(보호자)와의 관계 ,";
					}
					//구명조끼
					if($.trim(n.H)=='') {
						
						error_lines += line_num + i + " ,";
						error_field += "구명조끼 ,";
					}
					//경력
					if($.trim(n.I)=='') {
						
						error_lines += line_num + i + " ,";
						error_field += "경력 ,";
					}
					
					// 화면 그리기
					//이름
					var frm_mbr_name = n.A;
					html_list+='<td id="mbr_name'+idx+'">'+frm_mbr_name+'</td>';
					html_list+='<input type="hidden" name="mbr_name" value="'+frm_mbr_name+'"/>';
					//성별
					var frm_mbr_gender = n.B;
					html_list+='<td id="mbr_gender'+idx+'" name="mbr_gender">'+frm_mbr_gender+'</td>';
					html_list+='<input type="hidden" name="mbr_gender" value="'+frm_mbr_gender+'"/>';
					//생년월일
					var frm_mbr_birth = n.C;
					html_list+='<td id="mbr_birth'+idx+'">'+frm_mbr_birth+'</td>';
					html_list+='<input type="hidden" name="mbr_birth" value="'+frm_mbr_birth+'"/>';
					//연락처
					var frm_mbr_hp = n.D;
					html_list+='<td style="display:none" id="mbr_hp'+idx+'" name="mbr_hp">'+frm_mbr_hp+'</td>';
					html_list+='<input type="hidden" name="mbr_hp" value="'+frm_mbr_hp+'"/>';
					//주소
					var frm_mbr_address = n.E;
					html_list+='<td style="display:none" id="mbr_address'+idx+'" name="mbr_address">'+frm_mbr_address+'</td>';
					html_list+='<input type="hidden" name="mbr_address" value="'+frm_mbr_address+'"/>';
					//신청자와의 관계
					var frm_mbr_relation = n.F;
					html_list+='<td id="mbr_relation'+idx+'" name="mbr_relation">'+frm_mbr_relation+'</td>';
					html_list+='<input type="hidden" name="mbr_relation" value="'+frm_mbr_relation+'"/>';
					//소속
					var frm_mbr_belongto = n.G;
					html_list+='<td id="mbr_belongto'+idx+'" name="mbr_belongto">'+frm_mbr_belongto+'</td>';
					html_list+='<input type="hidden" name="mbr_belongto" value="'+frm_mbr_belongto+'"/>';
					//구명조끼					
					var frm_jacket_offer = n.H;
					html_list+='<td style="display:none" id="jacket_offer'+idx+'" name="jacket_offer">'+frm_jacket_offer+'</td>';
					html_list+='<input type="hidden" name="jacket_offer" value="'+frm_jacket_offer+'"/>';					
					//낚시경력
					var frm_naksi_career = n.I;
					html_list+='<td style="display:none" id="naksi_career'+idx+'" name="naksi_career">'+frm_naksi_career+'</td>';
					html_list+='<input type="hidden" name="naksi_career" value="'+frm_naksi_career+'"/>';
					
					//입금금액
					var frm_deposit_amount = n.J;
					//var frm_deposit_amount = $('#frm_deposit_amount').val();
					html_list+='<td style="display:none" id="deposit_amount'+idx+'" name="deposit_amount">'+frm_deposit_amount+'</td>';
					html_list+='<input type="hidden" name="deposit_amount" value="'+frm_deposit_amount+'"/>';
					//비고
					var frm_etc = n.K;
					if(frm_etc == '') {
						frm_etc = ' ';
					}
					html_list+='<td style="display:none" id="etc'+idx+'" name="etc">'+frm_etc+'</td>';
					html_list+='<input type="hidden" name="etc" value="'+frm_etc+'"/>';
					//
					html_list+='<td>';
					
					//참여유형선택
					var frm_ci_type = n.L;
					html_list+='<td style="display:none" id="ci_type'+idx+'" name="ci_type">'+frm_ci_type+'</td>';
					html_list+='<input type="hidden" name="ci_type" value="'+frm_ci_type+'"/>';
					
					//삭제하기
					html_list+='<input type="button" class="btn grey" onclick="del_member('+idx+')" value="삭제" />';
					//수정하기
					//html_list+='<input type="button" class="btn lgrey" onclick="select_member('+idx+')" value="수정하기" />';
					html_list+='</td>';
					//
					html_list+='</tr>';		
					
					
					if( idx == 1 ) {
						//console.log("최초 삽입줄이네");
						$('#apnd').html(html_list);
					} else {
						$('#apnd').append(html_list);
					}
					}
					
				});
				
				//console.log(error_lines);
				//console.log(error_field);
				
				if ( error_lines.length > 0) {
					alert("필수정보 입력에러 " + error_lines+"번째 ("+error_field+")");
					$('#apnd').html('');
					return false;
				}
				
				alert("참가자 엑셀파일 실행 성공");
	            
	        },
	        error: function (e) {
	            console.log("ERROR : ", e);
	        }
	    });
	}
	
	// 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
	
    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
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
	
	function replaceTag(test_str) {
		
		test_str = test_str.toLowerCase();
		test_str = test_str.replace('<script>', '');
		test_str = test_str.replace('<\/script>', '');
		test_str = test_str.replace('<img>', '');
		test_str = test_str.replace('<\/img>', '');
		test_str = test_str.replace('<body>', '');
		test_str = test_str.replace('<\/body>', '');
		test_str = test_str.replace('<iframe>', '');
		test_str = test_str.replace('<\/iframe>', '');
		
	    test_str = test_str.replace(">", "&gt;");
		test_str = test_str.replace("<", "&lt;");
		
		return test_str;
	}
	
</script>
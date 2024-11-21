<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="gongmo"/>
<c:set var="pageName" value="mbrwrite" />
<%@include file="../layout/head.jsp"%>

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
		
		<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn" value="${info.bo_sn}"/>
		<input type="hidden" name="idx" id="idx" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.idx}">${info_gongmo.idx}</c:if>"/>
		<input type="hidden" name="bo_atch_file" id="bo_atch_file" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.bo_atch_file}">${info_gongmo.bo_atch_file}</c:if>"/>
		<input type="hidden" name="ci_cate" value="gongmo" />
		<input type="hidden" name="bo_atch_file" id="bo_atch_file" />
		<input type="hidden" name="mbr_email" id="mbr_email" />
		<input type="hidden" name="mbr_hp" id="mbr_hp" />
		
		<section id="writeForm" class="write_box">
			<h2 class="championshopTit">
				<em>${info.bo_subject}</em>
				<span>공모기간 : <b>${info.bo_start_dt} ~ ${info.bo_end_dt}</b><br/>모집기간 : <b>${info.entry_start_dt} ~ ${info.entry_end_dt}</b><br/>주최 : <b>${info.organizer}</b><br/>주관 : <b>${info.host}</b></span>
			</h2>
			
			<!-- //업로드 파일 규격 안내// -->
			<div class="agree_box">
			${info.bo_content}
			</div>
			<%--
			<div class="agree_box">
				<h3>작품규격 & 제출방법 ( 포스터분야, UCC분야, 광고디자인분야 )</h3>
				<div class="agree_text" >
					<div class="board_list ">
					<table class="list_tbl">
					<thead>
						<tr>
							<th>공모부문</th>
							<th>작품규격</th>
							<th>응모방법</th>
							<th>비고</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>포스터</td>
							<td>4절지(394*545mm)<br>*손 그림,컴퓨터 작품 모두 인정<br>*가로,세로 상관없음<br>*1인당 3작품 이내 접수가능</td>
							<td>포스터사진촬영(스마트폰,DSLR,카메라)후 JPG파일 홈페이지에 등록 접수(고해상도)</td>
							<td>5인 이상<br>대포자 단체접수 가능</td>
						</tr>
						<tr>
							<td>UCC</td>
							<td>UCC,동영상,V-LOG 등 5분 내외<br>표현기법자유<br>해상도 1280*720(HD급)이상,500MB이내<br>*wmv,mp4,avi,mov 동영상파일<br>*1인당 작품 수 제한없음</td>
							<td>동영상 파일로 홈페이지에 등록 접수</td>
							<td></td>
						</tr>
						<tr>
							<td>광고디자인</td>
							<td>A3 Size(가로,세로 상관없음/자유롭게표현),30MB이내<br>출력용 jpg 파일 필수 제출(300dpi이상)<br>*수상작은 추후 원본(ai,psd,eps등)파일제출<br>1인당 3작품 이내 접수가능</td>
							<td>jpg파일로 홈페이지에 등록 접수</td>
							<td></td>
						</tr>
					</tbody>
					</table>
					</div><br>					
					* 포스터 분야는 개인참여만 가능합니다.<br>
					* UCC,광고디자인은 개인 및 팀 자격으로 참가 가능하며, 팀인원은 제한없습니다.<br>
					* UCC 응모작품 수는 제한 없으며, 포스터 및 광고디자인은 1인(팀)당 3작품까지 작품 제출 가능합니다.
				</div>
			</div>
			--%>
			
			<h2>접수정보</h2>
			<div class="owner_box">
				<dl>
					<dt><span class="colorRed">*</span> 응모부문</dt> 
					<dd>
						<div class="choicebox">
							<!--
							<input type="radio" id="mbr_cate_1" name="mbr_cate" value="포스터 부문" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_cate and info_gongmo.mbr_cate eq '포스터 부문'}">checked</c:if>/><label for="mbr_cate_1"><span></span>포스터 부문</label>
							<input type="radio" id="mbr_cate_2" name="mbr_cate" value="UCC 부문" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_cate and info_gongmo.mbr_cate eq 'UCC 부문'}">checked</c:if>/><label for="mbr_cate_2"><span></span>UCC 부문</label>
							<input type="radio" id="mbr_cate_3" name="mbr_cate" value="광고디자인 부문" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_cate and info_gongmo.mbr_cate eq '광고디자인 부문'}">checked</c:if>/><label for="mbr_cate_3"><span></span>광고디자인 부문</label>
							-->
							<input type="radio" id="mbr_cate_3" name="mbr_cate" value="광고디자인 부문" checked/><label for="mbr_cate_3"><span></span>광고디자인 부문</label>
						</div>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 암호설정</dt>
					<dd>
						<input type="password" id="mbr_pwd" name="mbr_pwd" class="write_input" placeholder="암호를 입력해주세요." size="50" autocomplete="off" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_pwd}">${info_gongmo.mbr_pwd}</c:if>" />
						<span class="txt_red">
						<br>* 암호는 접수확인시 사용됩니다. 비밀번호 분실 시 공모전 사무국(010-7578-2297)로 연락주시기 바랍니다.
						</span>				
					</dd>
				</dl>
			</div>	
			
			<h2>접수자 정보</h2>			
			<div class="owner_box">
				<dl>
					<dt><span class="colorRed">*</span> 성명</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="성명을 입력해주세요." id="mbr_name" name="mbr_name" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_name}"><c:out value="${info_gongmo.mbr_name}"/></c:if>"/>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 응모자구분</dt> 
					<dd>
						<div class="choicebox">
							<input type="radio" id="mbr_class_1" name="mbr_class" value="초등학생" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_class and info_gongmo.mbr_class eq '초등학생'}">checked</c:if>/><label for="mbr_class_1"><span></span>초등학생</label>
							<input type="radio" id="mbr_class_2" name="mbr_class" value="중학생" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_class and info_gongmo.mbr_class eq '중학생'}">checked</c:if>/><label for="mbr_class_2"><span></span>중학생</label>
							<input type="radio" id="mbr_class_3" name="mbr_class" value="고등학생" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_class and info_gongmo.mbr_class eq '고등학생'}">checked</c:if>/><label for="mbr_class_3"><span></span>고등학생</label>
							<input type="radio" id="mbr_class_4" name="mbr_class" value="대학생" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_class and info_gongmo.mbr_class eq '대학생'}">checked</c:if>/><label for="mbr_class_4"><span></span>대학생</label>
							<input type="radio" id="mbr_class_5" name="mbr_class" value="일반인" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_class and info_gongmo.mbr_class eq '일반인'}">checked</c:if>/><label for="mbr_class_5"><span></span>일반인</label>
						</div>
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 주소</dt>
					<dd>
						<input type="text" style="display:none" id="sample4_postcode" placeholder="우편번호">
						<input type="text" id="mbr_address"  name="mbr_address" style="width:70%;" placeholder="주소를 입력해주세요." value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_address}"><c:out value="${info_gongmo.mbr_address}"/></c:if>">
						<input type="button" onclick="sample4_execDaumPostcode()"  class="zipcodeBox" value="우편번호 찾기">
						<input type="text" id="mbr_address_jibun"  name="mbr_address_jibun" style="display:none" placeholder="지번주소" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_address_jibun}"><c:out value="${info_gongmo.mbr_address_jibun}"/></c:if>">
						<span id="guide" style="color:#999"></span>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 연락처</dt> 
					<dd>	
						<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp}">
							<c:set var="mbr_hp_arr" value="${fn:split(info_gongmo.mbr_hp, '-')}" />
						</c:if>
						<select class="naksi_select" id="frm_phone1" name="frm_phone1" >
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '010'}">selected</c:if> >010</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '011'}">selected</c:if> >011</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '012'}">selected</c:if> >012</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '014'}">selected</c:if> >014</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '017'}">selected</c:if> >017</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '018'}">selected</c:if> >018</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '019'}">selected</c:if> >019</option>
							<option <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp and mbr_hp_arr[0] eq '070'}">selected</c:if> >070</option>
						</select> - 
						<input type="text" class="write_input" size="7" name="frm_phone2" maxlength="4"  id="frm_phone2" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp}">${mbr_hp_arr[1]}</c:if>"/> - 
						<input type="text" class="write_input" size="7" name="frm_phone3" maxlength="4"  id="frm_phone3" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_hp}">${mbr_hp_arr[2]}</c:if>"/>
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd>
						<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email}">
							<c:set var="mbr_email_arr" value="${fn:split(info_gongmo.mbr_email, '@')}" />
						</c:if>
						<input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" name="frm_email1" id="frm_email1" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email}">${mbr_email_arr[0]}</c:if>"> @
						<select id="frm_email_addr" class="write_input" style="width:200px;" onchange="chgaddr_email();">					
							<option value="">선택하세요</option>
							<option value="naver.com"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'naver.com'}">selected</c:if> >naver.com</option>
							<option value="daum.net"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'daum.net'}">selected</c:if> >daum.net</option>
							<option value="nate.com"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'nate.com'}">selected</c:if> >nate.com</option>
							<option value="yahoo.com"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'yahoo.com'}">selected</c:if> >yahoo.com</option>
							<option value="gmail.com"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'email.com'}">selected</c:if> >gmail.com</option>	
							<option value="paran.com"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'paran.com'}">selected</c:if> >paran.com</option>
							<option value="hanmail.net"  <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email and mbr_hp_arr[1] eq 'hanmail.net'}">selected</c:if> >hanmail.net</option>
							<option id="self" value="1" <c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email}">selected<c:set var="mbr_email_self" value="1"/></c:if> >직접 입력하기</option>
						</select>
						<input type="text" style=" <c:if test="${info_gongmo eq null and empty info_gongmo.mbr_email and mbr_email_self eq null}">display:none</c:if>" size="30" class="write_input" placeholder="이메일을 입력하세요" id="frm_email2" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.mbr_email}">${mbr_email_arr[1]}</c:if>">
					</dd>
				</dl>
			</div>			
			
			<h2>출품작</h2>		
			<div class="owner_box">			
				<dl>
					<dt><span class="colorRed">*</span> 작품 제목</dt>
					<dd>
						<input type="text" size="50" class="write_input" placeholder="작품제목을 입력해주세요." id="work_subject" name="work_subject" value="<c:if test="${info_gongmo ne null and not empty info_gongmo.work_subject}"><c:out value="${info_gongmo.work_subject}"/></c:if>"/>
					</dd>
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 작품 설명<br>(500자 이하)</dt> 
					<dd>
						<textarea name="work_summary" id="work_summary" rows="4" class="write_textarea" ><c:if test="${info_gongmo ne null and not empty info_gongmo.work_summary}"><c:out value="${info_gongmo.work_summary}"/></c:if></textarea>
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt><span class="colorRed">*</span> 작품 업로드</dt>
					<dd class="pt20">
						<input type="file" id="frm_file" name="frm_file">
						<c:if test="${info_gongmo ne null and not empty info_gongmo.bo_atch_file}">
							현재 등록된 파일 : 
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${info_gongmo.bo_atch_file}" />
					        </c:import>
							<p class="txt_red">* 파일을 교체하는 경우에 사용하시면 됩니다.</p>
						</c:if>
						<span class="txt_red">
						- JPEG,JPG 파일 / 3,000픽셀 이상 / 100MB 이내<br>
						- 비율 : 가로,세로 상관없음(4:6 또는 6:4)<br>
						- 1회 접수 시 1개 작품만 접수 가능합니다. 다작 접수의 경우, 별도로 접수하여 주시기 바랍니다.
						</span>
					</dd>
				</dl>
			</div>
			
			<div id="btnArea" class="noupline">
				<c:if test="${info_gongmo eq null}">
					<a href="#;" class="btn_request btn_blue" id="btn_excute" onclick="submitContents()">작성완료</a>
					<a href="#;" onclick="cancel()"  class="btn_prev btn_white">취소</a>
				</c:if>
				<c:if test="${info_gongmo ne null}">
					<a href="#;" class="btn_request btn_blue" id="btn_excute" onclick="submitContents_modify()">수정완료</a>
					<a href="#;" onclick="cancel_modify()"  class="btn_prev btn_white">취소</a>
				</c:if>
			</div>
			
		</section>
		</form:form>
	</div>

<%@include file="../layout/tail.jsp"%>


<script type="text/javascript">

function chgaddr_email(){
	var frm_email_addr = $('#frm_email_addr').val();
	if(frm_email_addr=='1'){ // 직접 입력
		$('#frm_email2').css('display',"inline").val('').focus();
	}else{ // 직접입력아닌경우
		$('#frm_email2').val(frm_email_addr).css('display',"none");
	}
}

function cancel(){
	if(!confirm("공모전 신청서 작성을 취소하시겠습니까?\n현재 작성 된 내용은 초기화 됩니다.")) {
		return;
	}
	$("#imform").attr("action", "/gongmo/gongmo/check.do");
	$("#bo_sn").val("");
	document.getElementById("imform").submit();
}
function cancel_modify(){
	if(!confirm("공모전 내용 수정을 취소하시겠습니까?\n현재 작성 된 내용은 저장되지 않습니다.")) {
		return;
	}
	$("#imform").attr("action", "/gongmo/gongmo/search.do");
	$("#bo_sn").val("");
	document.getElementById("imform").submit();
}

function checkInput(num) {
	//응모부분
	if($("input:radio[name=mbr_cate]:checked").length < 1) {
		alert('응모부문을 선택해주세요.');
		return false;
	}	
	//암호설정
	if($.trim($('#mbr_pwd').val())=='') {
		alert('암호를 설정해주세요.');
		$('#mbr_pwd').focus();
		return false;
	}
	//성명
	if($.trim($('#mbr_name').val())=='') {
		alert('성명을 입력해주세요.');
		$('#mbr_name').focus();
		return false;
	}
	//응모자구분
	if($("input:radio[name=mbr_class]:checked").length < 1) {
		alert('응모자구문을 선택해주세요.');
		return false;
	}
	//주소
	if($.trim($('#mbr_address').val())=='') {
		alert('주소를 입력해주세요.');
		$('#mbr_address').focus();
		return false;
	}
	//연락처
	var frm_phone1 = $.trim($("#frm_phone1").val());
	var frm_phone2 = $.trim($("#frm_phone2").val());
	var frm_phone3 = $.trim($("#frm_phone3").val());
	var regex3 = /\d{3,4}/;
	if(regex3.test(frm_phone2) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	var regex4 = /\d{4}$/;
	if(regex4.test(frm_phone3) == false){
		alert("연락처는 숫자만 입력할 수 있습니다.")
		return false;
	}
	$('#mbr_hp').val(frm_phone1+"-"+frm_phone2+"-"+frm_phone3);
	//이메일 -  필수 처리 안함 
	var frm_email1 = $.trim($("#frm_email1").val());
	var frm_email2 = $.trim($("#frm_email2").val());
	var frm_email_addr = "";
	if($('#frm_email_addr').val()=="1"){		
		$('#self').val(frm_email2);
		frm_email_addr = frm_email2;
	} else {
		frm_email_addr = $("#frm_email_addr").val();
	}
	if(frm_email1.length!=0) { //이메일은 필수 처리 안함
		var regex = /^([\w-]+(?:\.[\w-]+)*)/;
		if(regex.test(frm_email1) === false){
			alert("잘못된 이메일 형식입니다.")
			return false;
		}
	}
	if(frm_email1.length!=0) {	
		var regex2 = /((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
		if(regex2.test(frm_email_addr) === false){
			alert("잘못된 이메일 형식입니다.")
			return false;
		}
	}
	$("#mbr_email").val(frm_email1+"@"+frm_email_addr);
	//작품제목
	if($.trim($('#work_subject').val())=='') {
		alert('작품제목을 입력해주세요.');
		$('#work_subject').focus();
		return false;
	}
	//작품설명
	if($.trim($('#work_summary').val())=='') {
		alert('작품설명을 작성해주세요.');
		$('#work_summary').focus();
		return false;
	}
	if(num == '1') {
		//첨부파일 확인
		if(!$("#frm_file").val()){
	    	alert("업로드 할 작품을 선택해주세요.");
	    	return false;
	    }
	}
	return true;
}

//전송버튼 클릭이벤트
function submitContents() {	
	if(!checkInput(1)) {
		return false;
	}	
	$("#btn_excute").prop("disabled", true).html("처리중...");
	var form = $('#imform')[0];
	var formData = new FormData(form);
	$.ajax({
	    type: "POST",
	    enctype: 'multipart/form-data',
	    url: "/gongmo/gongmo/ajax_check_upload_file.do",
	    data: formData,
	    processData: false,
	    contentType: false,
	    cache: false,
	    timeout: 600000,
	    success: function (data) {
	    	if(data.error == '1') {
	    		alert(data.msg);
	    		$("#btn_excute").prop("disabled", false).html("작성완료");
	    	} else {
	    		$("#imform").attr("action", "/gongmo/gongmo/mbrinsert_data.do");
	    		$("#imform").submit();
	    	}
	    },
	    error: function (e) {
	    	alert('서버와의 통신이 고르지 못합니다./n잠시 후 다시 시도해주세요.');
	    	$("#btn_excute").prop("disabled", false).html("작성완료");
	    }
	});	
}
function submitContents_modify() {	
	if(!checkInput(2)) {
		return false;
	}	
	$("#btn_excute").prop("disabled", true).html("처리중...");
	var form = $('#imform')[0];
	var formData = new FormData(form);
	$.ajax({
	    type: "POST",
	    enctype: 'multipart/form-data',
	    url: "/gongmo/gongmo/ajax_check_upload_file.do",
	    data: formData,
	    processData: false,
	    contentType: false,
	    cache: false,
	    timeout: 600000,
	    success: function (data) {
	    	if(data.error == '1') {
	    		alert(data.msg);
	    		$("#btn_excute").prop("disabled", false).html("수정완료");
	    	} else {
	    		$("#imform").attr("action", "/gongmo/gongmo/mbrupdate_data.do");
		    	$("#imform").submit();
	    	}
	    },
	    error: function (e) {
	    	alert('서버와의 통신이 고르지 못합니다./n잠시 후 다시 시도해주세요.');
	    	$("#btn_excute").prop("disabled", false).html("수정완료");
	    }
	});	
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
            document.getElementById('mbr_address').value = fullRoadAddr;
            document.getElementById('mbr_address_jibun').value = data.jibunAddress;

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

</script>
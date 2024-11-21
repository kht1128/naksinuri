<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="depthName" value="mainimg" />
<c:set var="pageName" value="mainimg" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<style>
#image_preview {
    display:none;
}
</style>
<body oncontextmenu="return false;">

<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post"  enctype="multipart/form-data" >
<input type="hidden" name="img_no" id="img_no" value="${info.img_no}" />
<input type="hidden" name="returnUrl" value="<c:url value='/admin/info/mainimg/board_findCorp.do'/>"/>
<input type="hidden" name="img_cont" value="${info.img_cont}">
<input type="hidden" name="m_img_cont" value="${info.m_img_cont}">
<input type="hidden" name="atch_file" value="${info.atch_file}">
<input type="hidden" name="file_list_size" id="file_list_size" value="${fn:length(filelist)}">
<input type="hidden" name="fileSn">
<input type="hidden" name="atchFileId">
<input type="hidden" name="fileListCnt">
<input type="hidden" name="mobile_link" id="mobile_link" value="${info.mobile_link}">



	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시누리 메인페이지 이미지 등록</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>							
						
							<tr>
								<th>제목</th>
								<td><input type="text" name="img_subject" id="img_subject" class="frm_input" value="${info.img_subject}" style="WIDTH: 100%;"/></td>
							</tr>
							
						  <tr>
								<th>카테고리</th>
								<td>
									<select class="frm_select" id="menu_category" name="menu_category" value="${info.menu_category }">
										<option <c:if test="${info.menu_category eq '메인스크롤'}">selected</c:if>>메인스크롤</option>
										<option <c:if test="${info.menu_category eq '팝업'}">selected</c:if>>팝업</option>
										<option <c:if test="${info.menu_category eq '이미지상단'}">selected</c:if>>이미지상단</option>
										<option <c:if test="${info.menu_category eq '이미지중간1'}">selected</c:if>>이미지중간1</option>
										<option <c:if test="${info.menu_category eq '이미지중간2'}">selected</c:if>>이미지중간2</option>
										<option <c:if test="${info.menu_category eq '이미지중간3'}">selected</c:if>>이미지중간3</option>
										<option <c:if test="${info.menu_category eq '이미지중간4'}">selected</c:if>>이미지중간4</option>
										<option <c:if test="${info.menu_category eq '우측배너1'}">selected</c:if>>우측배너1</option>
										<option <c:if test="${info.menu_category eq '우측배너2'}">selected</c:if>>우측배너2</option>
										<option <c:if test="${info.menu_category eq '우측배너3'}">selected</c:if>>우측배너3</option>
										<option <c:if test="${info.menu_category eq '우측이미지'}">selected</c:if>>우측이미지</option>
								  </select>
								</td>
								
							</tr>
							<tr>
								<th>이미지 표시 여부</th>
								<td><input type="checkbox" name="gongzi" id="gongzi" <c:if test="${info.img_sta eq 1}">checked</c:if> class="frm_input" />
									<label for="gongzi">해당 이미지가 사용자 메인메뉴에 나타납니다.</label>								
								</td>
								<input type="hidden" name="img_sta" id="img_sta" <c:if test="${info.img_sta ne null }">value="${info.img_sta}"</c:if> <c:if test="${info.img_sta eq null }">value="0"</c:if> >
							</tr>
								
								<tr>
									<th>링크</th>
									<td>
									
										<select id="email_addr" class="frm_input" style="width:200px;" onchange="chgaddr();" name="img_link">					
											<option value="">선택하세요</option>
											<option value="/lesson/junior/list.do" <c:if test ="${info.img_link eq '/lesson/junior/list.do'}">selected</c:if>>초보탈출하기</option>
											<option value="/lesson/gosu/list.do"  <c:if test ="${info.img_link eq '/lesson/gosu/list.do'}">selected</c:if>>낚시고수되기</option>
											<option value="/lesson/sense/list.do"  <c:if test ="${info.img_link eq '/lesson/sense/list.do'}">selected</c:if>>낚시상식</option>
											<option value="/lesson/binding/list.do" <c:if test ="${info.img_link eq '/lesson/binding/list.do'}">selected</c:if>>채비필수묶음법</option>
											<option value="/lesson/class/list.do" <c:if test ="${info.img_link eq '/lesson/class/list.do'}">selected</c:if>>어종별낚시교실</option>	
											<option value="/info/fishjob/list.do" <c:if test ="${info.img_link eq '/info/fishjob/list.do'}">selected</c:if>>낚시터정보</option>
											<option value="/info/industry/list.do" <c:if test ="${info.img_link eq '/info/industry/list.do'}">selected</c:if>>낚시산업정보</option>
											<option value="/info/angling/list.do" <c:if test ="${info.img_link eq '/info/angling/list.do'}">selected</c:if>>조황정보</option>
											<option value="/sosig/lab/list.do" <c:if test ="${info.img_link eq '/sosig/lab/list.do'}">selected</c:if>>낚시연구소</option>
											<option value="/share/travel/list.do" <c:if test ="${info.img_link eq '/share/travel/list.do'}">selected</c:if>>낚시여행기</option>
											<option value="/share/zisik/list.do" <c:if test ="${info.img_link eq '/share/zisik/list.do'}">selected</c:if>>누리지식인</option>
											<option value="/share/nuri/list.do" <c:if test ="${info.img_link eq '/share/nuri/list.do'}">selected</c:if>>자주묻는질문</option>
											<option value="/promotion/qna/list.do" <c:if test ="${info.img_link eq '/promotion/qna/list.do'}">selected</c:if>>낚시법 및 유어장 관련 질의회신사례</option>
											<option value="/share/usage/list.do" <c:if test ="${info.img_link eq '/share/usage/list.do'}">selected</c:if>>낚시용품 사용기</option>
											<option value="/share/column/list.do" <c:if test ="${info.img_link eq '/share/column/list.do'}">selected</c:if>>낚시칼럼</option>
<%-- 											<option value="/info/safe/list.do" <c:if test ="${info.img_link eq '/share/column/list.do'}">selected</c:if>>낚시안전</option> --%>
											<option value="/sosig/gosi/list.do" <c:if test ="${info.img_link eq '/sosig/gosi/list.do'}">selected</c:if>>지역별 낚시준수사항</option>
											<option value="/sosig/news/list.do" <c:if test ="${info.img_link eq '/sosig/news/list.do'}">selected</c:if>>낚시뉴스</option>
											<option value="/sosig/notice/list.do" <c:if test ="${info.img_link eq '/sosig/notice/list.do'}">selected</c:if>>공지사항</option>											
											<option value="/sosig/congress/list.do" <c:if test ="${info.img_link eq '/sosig/congress/list.do'}">selected</c:if>>낚시대회</option>
											<option value="/sosig/event/list.do" <c:if test ="${info.img_link eq '/sosig/event/list.do'}">selected</c:if>>이벤트</option>
											<option value="/promotion/campaign/list.do" <c:if test ="${info.img_link eq '/promotion/campaign/list.do'}">selected</c:if>>낚시캠페인</option>
											<option value="/promotion/plocation/list.do" <c:if test ="${info.img_link eq '/promotion/plocation/list.do'}">selected</c:if>>낚시금지구역</option>
											<option value="/promotion/policy/list.do" <c:if test ="${info.img_link eq '/promotion/policy/list.do'}">selected</c:if>>낚시정책안내</option>
											<option value="/promotion/auditor/list.do" <c:if test ="${info.img_link eq '/promotion/auditor/list.do'}">selected</c:if>>낚시명예감시원</option>
											<option value="/survey/survey/list.do" <c:if test ="${info.img_link eq '/survey/survey/list.do'}">selected</c:if>>설문조사</option>
											<option id="self" value="text">직접 입력하기</option>
										</select>
										<input type="text" style="display:none" size="30" class="frm_input" placeholder="링크 주소를 입력하세요" name="img_link2" id="img_link" value="${info.img_link }">
									</td>
									
								</tr>								
								<tr class="toggle_visible">
									<th>PC이미지<br>(1920*120px)</th>
									<c:choose>
										<c:when test="${mimg ne null }">
											<td>
											<c:out value="${mimg.orignl_file_nm}"/>&nbsp;<c:out value="[${mimg.file_size} Byte]"/>
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${mimg.img_cont}"/>','<c:out value="${mimg.file_sn}"/>');" />
											</td>
										</c:when>
										<c:otherwise>
											<td style="padding-top:10px;"><input type="file" size="30" name="mimg" accept="image/*" id="mimg" style="width:290px" /></td>
										</c:otherwise>			
									</c:choose>
								</tr>
								<tr class="toggle_visible">
									<th>모바일 이미지<br>(360*120px)</th>
									<c:choose>
										<c:when test="${mobile_mimg ne null }">
											<td>
											<c:out value="${mobile_mimg.orignl_file_nm}"/>&nbsp;<c:out value="[${mobile_mimg.file_size} Byte]"/>
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
								       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${mobile_mimg.m_img_cont}"/>','<c:out value="${mobile_mimg.file_sn}"/>');" />
											</td>
										</c:when>
										<c:otherwise>
											<td style="padding-top:10px;"><input type="file" size="30" name="mobile_mimg" accept="image/*" id="mobile_mimg" style="width:290px" /></td>
										</c:otherwise>			
									</c:choose>
								</tr>
								
							<tr>
								<td></td>
								<td><a href="#;" class="btn_size1 btn_violet" onclick="insertinput()">첨부파일 추가 </a><a href="#;" class="btn_size1 btn_bordergray" onclick="deleteinput()">파일 삭제</a></td>
							</tr>
								<c:forEach items="${filelist}" var="item">
									<tr>
										<th>첨부파일</th>
										<td>
											<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
												<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.atch_file}"/>','<c:out value="${item.file_sn}"/>');" />
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
								
								
						</tbody>
					</table>
				</div>
			</section>

			<div class="btn_area textcenter">
				
				<!-- 휴지통에서 글 취소버튼 -->
				<c:if test="${info.img_trash eq '1' }">
					<a href="/admin/info/mainimg/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.img_trash eq '0' && info.img_no ne null}">
					<a href="/admin/info/mainimg/list.do" class="btn_size2 btn_bordergray">취소</a>
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.img_no eq null}">
					<a href="/admin/info/mainimg/list.do" class="btn_size2 btn_bordergray">취소</a>
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
<script type="text/javaScript" language="javascript">

	var j=0;

// $(document).ready(function(){
//  mobile_link=document.getElementById("email_addr");
// 	mobile_link="";
// 	if("#email_addr option:eq(1) selected"){
// 		$('#mobile_link').val('/lesson/junior/m/list.do');
// 		alert('1');
// 	}else if('#email_addr option:eq(2) selected'){
// 		$('#mobile_link').val('/lesson/gosu/m/list.do');
// 		alert('2');
// 	}
	
// 	alert($('#mobile_link').val());
// })

//직접 입력하기 입력값
	var x1 = document.getElementById("img_link");
	for(var i=0; i<imform.email_addr.length;i++){
		//셀렉트박스 전체중에서 아무것도 선택이 안되어있고 , img_no가 있을때
		if(imform.email_addr.value == '' && $('#img_no').val()!=''){
			//
			$('#self').attr("selected",true);
			x1.style.display = "inline";
		}
	}
	
	
	



$("#gongzi").change(function() {
	 if(this.checked) {
		  $('#img_sta').val(1);
	  }else{
		  $('#img_sta').val(0);
	  }
	});
   
   
var x = document.getElementById("img_link");
var imglink = document.getElementsByName("img_link");
function chgaddr(){	
	var mob="";
	if($('#email_addr option:selected').text()=="초보탈출하기"){
		mob="/lesson/junior/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시고수되기"){
		mob="/lesson/gosu/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시상식"){
		mob="/lesson/sense/m/list.do";
	}else if($('#email_addr option:selected').text()=="채비필수묶음법"){
		mob="/lesson/binding/m/list.do";
	}else if($('#email_addr option:selected').text()=="어종별낚시교실"){
		mob="/lesson/class/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시터정보"){
		mob="/info/fishjob/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시산업정보"){
		mob="/info/industry/m/list.do";
	}else if($('#email_addr option:selected').text()=="조황정보"){
		mob="/info/angling/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시연구소"){
		mob="/sosig/lab/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시여행기"){
		mob="/share/travel/m/list.do";
	}else if($('#email_addr option:selected').text()=="누리지식인"){
		mob="/share/zisik/m/list.do";
	}else if($('#email_addr option:selected').text()=="자주묻는 낚시질문"){
		mob="/share/nuri/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시용품 사용기"){
		mob="/share/usage/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시칼럼"){
		mob="/share/column/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시뉴스"){
		mob="/sosig/news/m/list.do";
	}else if($('#email_addr option:selected').text()=="공지사항"){
		mob="/sosig/notice/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시대회"){
		mob="/sosig/congress/m/list.do";
	}else if($('#email_addr option:selected').text()=="이벤트"){
		mob="/sosig/event/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시캠페인"){
		mob="/promotion/campaign/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시금지구역"){
		mob="/promotion/plocation/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시정책안내"){
		mob="/promotion/policy/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시법 및 유어장 관련 질의회신사례"){
		mob="/promotion/qna/m/list.do";
	}else if($('#email_addr option:selected').text()=="지역별 낚시준수사항"){
		mob="/sosig/gosi/m/list.do";
	}else if($('#email_addr option:selected').text()=="낚시명예감시원"){
		mob="/promotion/auditor/m/list.do";
	}else if($('#email_addr option:selected').text()=="설문조사"){
		mob="/survey/survey/m/list.do";
	}
	
	$('#mobile_link').val(mob);
	
	
 if(imform.email_addr.value=='text'){ // 직접 입력
	x.style.display = "inline";
	x.value="";
}else{ // 직접입력아닌경우
	x.style.display="none";

	
}
	
}

    //전송버튼 클릭이벤트
	function submitContents() {
    	
    	
		//직접 입력하기 입력값
		var yx = document.getElementById("img_link");
		//셀렉트박스 옵션값
		if($('#email_addr').val()=='text'){
			//옵션마지막아이디
		var addr_value = $('#self').val() ;
		addr_value="";
		addr_value = yx.value; 	
		$('#self').val(addr_value);

		}
     
     if($('#img_no').val()!=''){
		$("#imform").attr("action","/admin/info/mainimg/update_data.do");
		$("#imform").submit(); 
	}else{
		$("#imform").attr("action","/admin/info/mainimg/insert_data.do");				
		document.getElementById("imform").submit();					
	}
    	 
  
   
    
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

</script>

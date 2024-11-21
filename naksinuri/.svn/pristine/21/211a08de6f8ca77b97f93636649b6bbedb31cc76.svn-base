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
<c:set var="depthName" value="info" />
<c:set var="pageName" value="industry" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="fileListCnt">
	<input type="hidden" name="atchFileId">
	<input type="hidden" name="fileSn">
	<input type="hidden" name="returnUrl" value="<c:url value='/admin/info/industry/ind_find.do'/>"/>
	<input type="hidden" name="san_sn" id="san_sn" value="${info.san_sn}" />
	<input type="hidden" name="san_img" value="${info.san_img }"/>
	<input type="hidden" name="san_simg" value="${info.san_simg }"/>
	<input type="hidden" name="file_list_size" id="file_list_size" value="${fn:length(simglist)}">
	<input type="hidden" name="adres_la" value=""/>
	<input type="hidden" name="adres_lo" value=""/>
	
	<div id="container">

		<div id="content">			

			<section id="table-write" class="table-box">
				<h3>낚시 산업정보 글쓰기</h3>
				<div class="padding_box">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="150" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>승인 상태</th>
								<td>
									<select class="frm_select" name="san_status">
										<option <c:if test="${info.san_status eq 'w' }">selected</c:if> value="w">대기중</option>
										<option <c:if test="${info.san_status eq 'y' }">selected</c:if> value="y">승인</option>
										<option <c:if test="${info.san_status eq 'n' }">selected</c:if> value="n">미승인</option>
									</select>
								</td>
							</tr>		
							<tr>
								<th>구분</th>
								<td>									
									<input type="checkbox" id="san_aag" name="san_aag" value="협회/기관/단체" <c:if test ="${info.san_aag eq'협회/기관/단체'}">checked</c:if>/><label for="san_aag"><span></span>협회/기관/단체&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_zogu" name="san_zogu" value="조구업체" <c:if test ="${info.san_zogu eq'조구업체'}">checked</c:if>/><label for="san_zogu"><span></span>조구업체&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_media" name="san_media" value="미디어"   <c:if test ="${info.san_media eq '미디어'}">checked</c:if>/><label for="san_media"><span></span>미디어&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_sell" name="san_sell" value="판매점"   <c:if test ="${info.san_sell eq'판매점'}">checked</c:if>/><label for="san_sell"><span></span>판매점&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_chool" name="san_chool" value="출조점"   <c:if test ="${info.san_chool eq '출조점'}">checked</c:if>/><label for="san_chool"><span></span>출조점&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_inprov" name="san_inprov" value="낚시정보제공" <c:if test ="${info.san_inprov eq'낚시정보제공'}">checked</c:if>/><label for="san_inprov" ><span></span>낚시정보제공</label>
								</td>
							</tr>					
							<tr>
								<th>상호명</th>
								<td><input type="text" name="san_name" id="san_name" class="frm_input" value="${info.san_name}"  /></td>
							</tr>
							<tr>
								<th>대표자</th>
								<td><input type="text" name="san_buisnessman" id="san_buisnessman" class="frm_input" value="${info.san_buisnessman}"/></td>
							</tr>
							<tr>
								<th>일반전화</th>
								<td>
									<select class="frm_select" name="san_tel1" id="san_tel1">
										<option value=''>없음</option>
										<option <c:if test="${info.san_tel1 eq '02' }">selected</c:if>>02</option>
										<option <c:if test="${info.san_tel1 eq '031' }">selected</c:if>>031</option>
										<option <c:if test="${info.san_tel1 eq '032' }">selected</c:if>>032</option>
										<option <c:if test="${info.san_tel1 eq '033' }">selected</c:if>>033</option>
										<option <c:if test="${info.san_tel1 eq '041' }">selected</c:if>>041</option>
										<option <c:if test="${info.san_tel1 eq '042' }">selected</c:if>>042</option>
										<option <c:if test="${info.san_tel1 eq '043' }">selected</c:if>>043</option>
										<option <c:if test="${info.san_tel1 eq '051' }">selected</c:if>>051</option>
										<option <c:if test="${info.san_tel1 eq '052' }">selected</c:if>>052</option>
										<option <c:if test="${info.san_tel1 eq '053' }">selected</c:if>>053</option>
										<option <c:if test="${info.san_tel1 eq '054' }">selected</c:if>>054</option>
										<option <c:if test="${info.san_tel1 eq '055' }">selected</c:if>>055</option>
										<option <c:if test="${info.san_tel1 eq '061' }">selected</c:if>>061</option>
										<option <c:if test="${info.san_tel1 eq '062' }">selected</c:if>>062</option>
										<option <c:if test="${info.san_tel1 eq '063' }">selected</c:if>>063</option>
										<option <c:if test="${info.san_tel1 eq '064' }">selected</c:if>>064</option>
										<option <c:if test="${info.san_tel1 eq '070' }">selected</c:if>>070</option>
									</select> - 
									<input type="text" class="frm_input" name="san_tel2" id="san_tel2" maxlength="4" value="${info.san_tel2}" size="7" /> - 
									<input type="text" class="frm_input" name="san_tel3" id="san_tel3" maxlength="4" value="${info.san_tel3}" size="7" />
									<input type="hidden" name="san_tel" id="san_tel">
								</td>
							</tr>
							<tr>
								<th>휴대전화</th>
								<td>
									<select class="frm_select" name="san_phone1" id="san_phone1">
										<option <c:if test="${info.san_phone1 eq '010' }">selected</c:if>>010</option>
										<option <c:if test="${info.san_phone1 eq '011' }">selected</c:if>>011</option>
										<option <c:if test="${info.san_phone1 eq '016' }">selected</c:if>>016</option>
										<option <c:if test="${info.san_phone1 eq '017' }">selected</c:if>>017</option>
										<option <c:if test="${info.san_phone1 eq '018' }">selected</c:if>>018</option>
										<option <c:if test="${info.san_phone1 eq '019' }">selected</c:if>>019</option>
									</select> - 
									<input type="text" class="frm_input" name="san_phone2"  id="san_phone2" value="${info.san_phone2}"  maxlength="4" size="7"  /> - 
									<input type="text" class="frm_input"  name="san_phone3" id="san_phone3" value="${info.san_phone3}"  maxlength="4" size="7" />
									<input type="hidden" name="san_phone" id="san_phone">
								</td>
							</tr>
							<tr>
								<th>사업영역</th>
								<td><input type="text" name="san_item" id="san_item"  class="frm_input" value="${info.san_item}"></td>
							</tr>
							<tr>
								<th>홈페이지</th>
								<td><input type="text" name="san_homepage" id="san_homepage"  class="frm_input" value=<c:choose><c:when test="${info.san_homepage eq null }">"http://"</c:when><c:otherwise>"${info.san_homepage}"</c:otherwise></c:choose> /></td>
							</tr>
							<tr>
								<th>회사 소개글</th>
								<td><textarea name="san_content" id="san_content"  class="frm_input"  style="WIDTH: 100%">${info.san_content}</textarea></td>
							</tr>
							<tr>
								<th>사업장주소</th>
								<td><input type="text" name="san_address1" class="frm_input readonly" id="newpostcode"  value="${info.san_address1}" readonly size="7" /><a href="#;" class="btn_size1 btn_violet" onclick="newaddress()">우편번호검색</a><br />
									<div id="wrap2" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
										<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode2()" alt="접기 버튼">
									</div>
									<input type="text" id="newaddress1" name="san_address2" readonly class="frm_input" value="${info.san_address2}" size="100" /><br />
									<input type="text" id="newaddress2" name="san_address3" class="frm_input" placeholder="상세주소"  value="${info.san_address3}" size="100"/>
									<input type="hidden" name="san_address" />
								</td>
							</tr>		
							<tr>
								<th>회사로고</th>
								<c:choose>
									<c:when test="${info.file_sn ne null }">
										<td>
											<c:out value="${info.orignl_file_nm }"/>&nbsp;<c:out value="[${info.file_size} Byte]"/>
											<img class="cursor" alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
							       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${info.san_img}"/>','<c:out value="${info.file_sn}"/>');" />
							       			<img class="cursor" alt="파일 보기" src="<c:url value='/naksinuri_original/images/egovframework/com/ency.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_getImage('<c:out value="${info.san_img}"/>','<c:out value="${info.file_sn}"/>');" style="margin-left:10px"/>
								       			<img class="cursor" alt="파일 다운" src="<c:url value='/naksinuri_original/common_main/img/icon_file.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_downFile('<c:out value="${info.san_img}"/>','<c:out value="${info.file_sn}"/>');" />
										</td>
									</c:when>
									<c:otherwise>
										<td style="padding-top:10px;"><input type="file" size="30" id="mimg" name="mimg" accept="image/*" id="mimg" style="width:290px" /></td>
									</c:otherwise>
								</c:choose>	
							</tr>
							<tr>
								<td></td>
								<td><a href="#;" class="btn_size1 btn_violet" onclick="insertinput()">소개이미지 추가 </a><a href="#;" class="btn_size1 btn_bordergray" onclick="deleteinput()">파일 삭제</a></td>
							</tr>
							<c:forEach items="${simglist}" var="item">
									<tr>
										<th>소개이미지</th>
										<td>
											<c:out value="${item.orignl_file_nm}"/>&nbsp;<c:out value="[${item.file_size} Byte]"/>
											<img class="cursor" alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${item.san_simg}"/>','<c:out value="${item.file_sn}"/>');" />
											<img class="cursor" alt="파일 보기" src="<c:url value='/naksinuri_original/images/egovframework/com/ency.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_getImage('<c:out value="${item.san_simg}"/>','<c:out value="${item.file_sn}"/>');" style="margin-left:10px"/>
								       			<img class="cursor" alt="파일 다운" src="<c:url value='/naksinuri_original/common_main/img/icon_file.gif'/>" 
								       			width="19" height="19" onClick="fn_egov_downFile('<c:out value="${item.san_simg}"/>','<c:out value="${item.file_sn}"/>');" />
										</td>
									</tr>
							</c:forEach>
							<tr id="file_0">
								<th><label for="chart_0">소개이미지</label></th>
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
				<c:if test="${info.san_trash eq '1' }">
					<a href="/admin/info/industry/trash.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				
				<!-- 일반게시판에서 글 조회,수정 -->
				<c:if test="${info.san_trash eq '0' && info.san_sn ne null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/info/industry/list.do" class="btn_size2 btn_bordergray">취소</a>
				</c:if>
				
				<!-- 글생성할 때  -->
				<c:if test="${info.san_sn eq null}">
					<input type="button" value="저장 " id="updateInfo" onclick="submitContents()" class="btn_size2 btn_violet" />
					<a href="/admin/info/industry/list.do" class="btn_size2 btn_bordergray">취소</a>
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

$(document).ready(function(){
	if($("#file_list_size").val()>=4){
			$("#file_0").remove();
	}
});

//첨부파일 행 추가 삭제
var j=0;
function insertinput(){
		
	
		if($("#ftable tr").length>=15){
			alert("소개이미지 첨부는 4개까지 가능합니다.");
			return false;
		}else{
		$(".t_write > tbody:last").append('<tr id="file_'+(j+1)+'">\
						<th><label for="chart_'+(j+1)+'">소개이미지</label></th>\
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
	
    //전역변수선언
    var oEditors = [];

   
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: oEditors,
        elPlaceHolder: "san_content",
        sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
        
        fOnAppLoad : function(){
        	oEditors.getById["san_content"].exec("PASTE_HTML", [" "]);
        },
        fCreator: "createSEditor2"
    });

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

    
    
    //전송버튼 클릭이벤트
	function submitContents() {
    	
		//주소-좌표 변환 객체를 생성합니다
		var address = $("#newaddress1").val();
		var form = document.getElementById('imform');
		var geocoder = new kakao.maps.services.Geocoder();
		geocoder.addressSearch(address, function(result, status) {
			if (status === kakao.maps.services.Status.OK) {
				var position = new kakao.maps.LatLng(result[0].y, result[0].x);
				
				form.adres_la.value = result[0].y;
				form.adres_lo.value = result[0].x;
				
				
			}
			
		});
    
    	var phone1 = $('#san_phone1').val();
    	var phone2 = $('#san_phone2').val();
    	var phone3 = $('#san_phone3').val();
    	
    	if(phone2!=null && phone3!=null){
	    	$('#san_phone').val(phone1+'-'+phone2+'-'+phone3);
    	}
    	
    	var tel1=$('#san_tel1').val();
	   	var tel2=$('#san_tel2').val();
	   	var tel3=$('#san_tel3').val();
	   	if(tel1=='' && (tel2!='' && tel3!='')){
	   		$('#san_tel').val(tel2+'-'+tel3);
	   	}else if(tel2!='' && tel3==''){
	   		 alert('올바른 전화번호를 입력해주세요.');
	   		 return false;
	   	}else if(tel2=='' && tel3!=''){
	   		 alert('올바른 전화번호를 입력해주세요.');
	   		 return false;
	   	}else if(tel1!='' && tel2!='' && tel3!=''){
	   		 $('#san_tel').val(tel1+'-'+tel2+'-'+tel3);
	   	}
    		
    	
	   	if($('#mimg').val()==""){
	   		alert("회사로고를 등록해주세요.");
	   		return false;
	   	}
	   	
    oEditors.getById["san_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

    var san_addr =	$('input[name=san_address2]').val() + 
    				$('input[name=san_address3]').val();
	$('input[name=san_address]').val(san_addr);
   			
    // 에디터의 내용에 대한 값 검증은 이곳에서
    if(document.getElementById("san_content").value==''){
    	alert('내용을 입력해주세요.');
    	return false;   	
    } 
     try {
    	 if($('#san_sn').val()!=''){
				$("#imform").attr("action","/admin/info/industry/ind_update.do");
			}else{
				$("#imform").attr("action","/admin/info/industry/ind_insert.do"); 
			}
				$("#imform").submit(); 		
    	 
    } catch(e) {
     
    }
   
    
}
    
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

// textArea에 이미지 첨부
function pasteHTML(filepath){
    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
    oEditors.getById["san_content"].exec("PASTE_HTML", [sHTML]);
   
}



</script>

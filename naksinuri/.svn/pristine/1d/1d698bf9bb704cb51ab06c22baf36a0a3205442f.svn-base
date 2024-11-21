<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
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
<c:set var="pageName" value="fishingAM" />

<%@include file="header_admin.jsp"%>
<%@include file="login_header.jsp"%>

<body oncontextmenu="return false;">

<form:form commandName="seaform" id="seaform" name="listform" method="post" enctype="multipart/form-data" >
<input type="hidden" name="nak_id" id="nak_id"/>
<input type="hidden" name="san_sn" id="san_sn"/>
</form:form>

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="fishing_type" id="fishing_type" value="boatfishing" />

<div id="wrapper">
<%@include file="admin_leftTab.jsp"%>
	<div id="container">
	
		<div id="content">			
			
			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
					</ul>
				</div>
				<!-- } 탭 영역 -->


			<input type="hidden" name="bo_sns" id="bo_sns"/>
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div style="color: #ff5200;">
						* 등록된 최근순으로 나타나고, 적용 및 삭제는 미리보기창에서 가능합니다.
						<br>
						* 같은 코드의 최신 요청을 처리하면, 하위의 같은 코드는 자동삭제됩니다. (수정기준)
					</div>
					<br>

					<table class="t_list">
						<colgroup>
							<col width="50" />
							<col width="100" />
							<col width="150" />
							<col width="150" />
							<col />
							<col width="150" />
							<col width="150" />
							<col width="130" />
							<col width="100" />
						</colgroup>
						<thead>
							<tr>
								<%-- 
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								--%>
								<th scope="col">구분</th>
								<th scope="col">타입</th>
								<th scope="col">상호(선박)명</th>
								<th scope="col">대표자</th>
								<th scope="col">주소</th>
								<th scope="col">휴대전화</th>
								<th scope="col">등록날짜</th>
								<th scope="col">등록아이피</th>
								<th scope="col">원본 글번호</th>
								
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${admin_list}">
							<c:choose>
								<c:when test="${fn:length(item.nak_id) > 8}">
									<c:set var="fdata" value="<span style='color:#0000ff'>신규</span>"></c:set>
								</c:when>					
								<c:otherwise>					
									<c:set var="fdata" value="<span style='color:#ff0000'>수정</span>"></c:set>
								</c:otherwise>
							</c:choose>
							<c:choose>
								<c:when test="${item.fishing_type eq 'boatfishing'  }">
									<c:set var="ftype" value="선상낚시"></c:set>
								</c:when>					
								<c:when test="${item.fishing_type eq 'riverfishing'  }">
									<c:set var="ftype" value="민물낚시"></c:set>
								</c:when>
								<c:otherwise>					
									<c:set var="ftype" value="바다낚시"></c:set>
								</c:otherwise>
							</c:choose>
							
							<tr>
								<%--
								<td><input type="checkbox" name="delYn" class="check2" value="${item.nak_id}"></td>
								 --%>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${fdata}</a></td>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${ftype}</a></td>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${item.co_nm}</a></td>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${item.ceo_nm}</a></td>
								<td><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${item.co_addr_2}</a></td>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${item.co_hphone}</a></td>
								<td class="textcenter"><a href="#;" onclick="preview_admin('${item.nak_id}','${item.preg_pid}')">${fn:substring(item.reg_date,0,16)}</a></td>
								<td class="textcenter">${item.preg_sess}</td>
								
								<c:choose>
							 		<c:when test="${fn:length(item.preg_pid) >= 4 and fn:substring(item.preg_pid,0,4) eq '9999'}">
										<td class="textcenter">신규</td>
							 		</c:when>
							 		<c:otherwise>
							 			<td class="textcenter"><a href="#;" onclick="original_view('${item.preg_pid}')">${item.preg_pid}</a></td>
							 		</c:otherwise>
						 		</c:choose>
								
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->
	
				<%--
				<div align="right"><a href="#;" onclick="PreviewDelete()" class="btn_size1 btn_violet">삭제</a></div>
				 --%>
				
				<form:form commandName="supform2" id="supform2" method="post"  >
					<input type="hidden" name="nak_id" id="nak_id" value="">
				</form:form>
				
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


<script>
function original_view(pid){	
	var form = document.getElementById('seaform');
	$('#nak_id').val(pid);	
	form.action="/info/admin/findCorp.do";
	var gsWin = window.open("about:blank", "winName");
	form.target = "winName";
	form.submit();	
};

function preview_admin(nak_id, pid){
	$('#nak_id').val(nak_id);
	window.name = "parent_page";
	/*
    var openwin = window.open('','admin_preview_page', 'width=1400, height=800, scrollbars=yes, resizable=yes');
    $("#supform2").attr("target","admin_preview_page");
    $("#supform2").attr("action","/info/admin/fishPreview.do");	
    $("#supform2").submit();
	openwin.focus();
	*/
    var openwin = window.open('/info/admin/fishPreview.do?nak_id='+nak_id+'&pid='+pid,'admin_preview', 'width=1400, height=800, scrollbars=yes, resizable=yes');
    openwin.focus();
};

function fncCheckAll() {
    var checkField = document.boatform.delYn;
    if(document.boatform.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}


function fncGroupListDelete() {
	if(fncManageChecked()) {
	    if(confirm("삭제하시겠습니까?")) {
            document.boatform.action = "/info/admin/gotrash_fishinglist.do";
            document.boatform.submit();
	    }
	}
}



function fncManageChecked() {

    var checkField = document.boatform.delYn;
    var checkId = document.boatform.delYn;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;

                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
                
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  그룹이 없습니다.");
                returnBoolean = false;
            }
        } else {
        	 if(document.boatform.delYn.checked == false) {
                alert("선택된 그룹이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    }

    document.boatform.bo_sns.value = returnValue;

    return returnBoolean;
}

</script>
</body>

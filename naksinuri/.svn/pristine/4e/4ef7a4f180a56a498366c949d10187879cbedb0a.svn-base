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
<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="logs" />
<c:set var="pageName" value="logslist" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">			

			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
					</ul>
				</div>
				<!-- } 탭 영역 -->

				<!-- 검색 영역 { -->
				<div id="search_form">
					<table class="t_search">
						<colgroup>
							<col width="80" />
							<col />
							<col width="80" />
							<col />
							<col width="80" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th>검색</th>
								<td colspan="5">
									<select class="frm_select" style="width:100px" name="searchType">
										<option <c:if test="${searchType eq 'bo_subject'}">selected</c:if> value="bo_subject">제목</option>
										<option <c:if test="${searchType eq 'bo_name'}">selected</c:if> value="bo_name">글쓴이</option>
									</select>	
									<input type="text" class="frm_input" size="70" name="searchText" value="${searchText}"/><br/>
									<input type="text" class="frm_input" size="20" id="date_from" name="date_from" placeholder="시작날짜를 입력해주세요." value="${date_from}"/> ~
									<input type="text" class="frm_input" size="20" id="date_to" name="date_to" placeholder="종료날짜를 입력해주세요." value="${date_to}"/>									 
									<button class="btn_size1 btn_violet" type="button" onclick="fnSelectInfs(1);">검색</button>
								</td>
							</tr>						</tbody>
					</table>
				</div>
				</form>
				<!-- } 검색 영역 -->

		<form:form commandName="listform" id="listform" name="listform" method="post" >
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>${select_total}</b>건</div>
						<div class="listnum">
							출력수 
							<select class="frm_select" id="s_pageUnit" name="s_pageUnit" onchange="fnSelectInfs(1);">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
							</select>
						</div>
							
					</div>
					<table class="t_list">
						<colgroup>
							<col width="30" />
							<col width="50" />
							<col width="180" />
							<col width="130" />
							<col width="130" />
							<col  />
							

						</colgroup>
						<thead>
							<tr>					
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								<th scope="col">번호</th>
								<th scope="col">접속일시</th>	
								<th scope="col">아이디</th>	
								<th scope="col">로그IP</th>
								<th scope="col">행위</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${select_list}">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.idx}"></td>
								<td class="textcenter">${item.idx}</td>
								<td class="textcenter">${item.reg_date}</td>
								<td class="textcenter">${item.user_id}</td>
								<td class="textcenter">${item.conn_ip}</td>		
								<td class="textcenter">${item.actions}</td>		
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->
		</form:form>
				<!-- 페이지 { -->
				<!-- 
				<div align="right"><a href="#;" onclick="fncGroupListDelete()" class="btn_size1 btn_violet">삭제</a><a href="./view.do" class="btn_size1 btn_violet">글쓰기</a></div>
				 --><div id="pagenation">
					<ui:pagination paginationInfo="${paginationInfo }" type="text" jsFunction="fnSelectInfs" />
				</div>
				<!-- } 페이지 -->
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
<script>
$(document).ready(function(){
	$("#date_from").datepicker({
		language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	$("#date_to").datepicker({
		 language: "kr",
		dateFormat : 'yy-mm-dd'
	});
	
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

	today = yyyy+"-"+mm+"-"+dd;
	
	//$('#date_from').val(today);
	//$('#date_to').val(today);
	
		

	$('#date_to').change(function(){
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
		
		
		
		if(!$.trim($('#date_from').val())){
			alert("시작날짜부터 선택해주세요.");
			$('#date_to').val('');
		}
		
		var start_dt = $('#date_from').val().replace(/-/gi,"");
		var end_dt = $('#date_to').val().replace(/-/gi,"");
		if(parseInt(end_dt)<parseInt(start_dt)){
			alert("시작날짜보다 이전날짜를 선택할 수 없습니다.");
			$('#date_to').val('');
		}
	})
});

function view2(img_no){
	var form = document.getElementById('listform');
	$('#img_no').val(img_no);
	
	form.action="/admin/info/mainimg/board_findCorp.do";
	form.submit();
};

function fncCheckAll() {
    var checkField = document.listform.delYn;
    if(document.listform.checkAll.checked) {
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
            document.listform.action = "./gotrash_list.do";
            document.listform.submit();
	    }
	}
}



function fncManageChecked() {

    var checkField = document.listform.delYn;
    var checkId = document.listform.delYn;
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
        	 if(document.listform.delYn.checked == false) {
                alert("선택된 그룹이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    }

    document.listform.bo_sns.value = returnValue;

    return returnBoolean;
}

function fnSelectInfs(pageIndex) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val(idx);
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "./list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});
</script>
</body>
</html>
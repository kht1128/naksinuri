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
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />
<body oncontextmenu="return false;">
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="fishing_type" id="fishing_type" value="boatfishing" />
<input type="hidden" name="bo_datet" id="bo_datet"/>
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
	<div id="container">

		<div id="content">			

			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
						<li><a href="${pageContext.request.contextPath}/admin/sosig/congress/list.do" class="cateing">진행중인 행사(대회/박람회)</a></li>
						<li><a href="#;" class="cateend on">종료된 행사(대회/박람회)</a></li>
<!-- 						<li><a href="#;">상세내용</a></li> -->
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
									<input type="text" class="frm_input" size="20" id="start_d" name="bo_start_dt" placeholder="시작날짜를 입력해주세요." value="${bo_start_dt}"/> ~
									<input type="text" class="frm_input" size="20" id="end_d" name="bo_end_dt" placeholder="종료날짜를 입력해주세요." value="${bo_end_dt}"/>
									<button class="btn_size1 btn_violet" type="button" onclick="fnSelectInfs(1);">검색</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</form>
				<!-- } 검색 영역 -->

<form:form commandName="listform" id="listform" name="listform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="bo_sns" id="bo_sns"/>
	<input type="hidden" name="bo_sn" id="bo_sn"/>
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
							<col width="110" />
							<col />
							<col width="130" />
							<col width="130" />
							<col width="130" />
							<col width="200" />
							<col width="80" />
							<col width="80" />
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								<th scope="col">번호</th>
								<th scope="col">글쓴이</th>
								<th scope="col">제목</th>	
								<th scope="col">구분</th>	
								<th scope="col">시작날짜</th>	
								<th scope="col">종료날짜</th>									
								<th scope="col">등록날짜</th>
								<th scope="col">승인여부</th>
								<th scope="col">참가자</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item2" items="${noticlist }">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.bo_sn}"></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">공지</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">${item2.bo_name}</a></td>
								<td><a href="#;" onclick="view2('${item2.bo_sn}')">${item2.bo_subject}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">${item2.bo_cate}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">${item2.bo_start_dt}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">${item2.bo_end_dt}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item2.bo_sn}')">
									<fmt:parseDate value="${item.bo_insert_dt}" var="f_data" pattern="yyyy-MM-dd HH:mm:ss.SSS" scope="page"/>
									<fmt:formatDate value="${f_data}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</a></td>		
								<td class="textcenter">승인</td>
								<td class="textcenter">
								
									 	<a href="#;" onclick="show_applicant('${item2.bo_sn}')" class="txt_red">참가자</a>								 
									    
																	
								</td>
							</tr>
						</c:forEach>
						<c:forEach var="item" items="${select_list}">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.bo_sn}"></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')"><c:out value="${select_total-item.rn + 1}"></c:out></a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_name}</a></td>
								<td><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_subject}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_cate}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_start_dt}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')">${item.bo_end_dt}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.bo_sn}')">
									<fmt:parseDate value="${item.bo_insert_dt}" var="f_data" pattern="yyyy-MM-dd HH:mm:ss.SSS" scope="page"/>
									<fmt:formatDate value="${f_data}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</a></td>		
								<td class="textcenter">
									<a href="#;" onclick="view2('${item.bo_sn}')">
										<c:if test="${item.bo_trash eq '0'}">승인</c:if>
										<c:if test="${item.bo_trash eq '3'}">미승인</c:if>
									</a>
								</td>	
								<td class="textcenter">
								
									 	<a href="#;" onclick="show_applicant('${item.bo_sn}')" class="txt_red">참가자</a>								 
									    
																	
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->

</form:form>
				<div align="right"><a href="#;" onclick="fncGroupListDelete()" class="btn_size1 btn_violet">삭제</a><a href="./view.do" class="btn_size1 btn_violet">글쓰기</a></div>
				<!-- 페이지 { -->
				<div id="pagenation">
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
function view2(bo_sn){
	var form = document.getElementById('listform');
	$('#bo_sn').val(bo_sn);
	
	form.action="./board_findCorp.do";
	form.submit();
};

function show_applicant(bo_sn){
	if(!$.trim(bo_sn)){
		alert("해당 대회를 찾을 수 없습니다.");
		return false;
	}
	var form = document.getElementById('listform');
	var url = "/sosig/congress/list_show.do";
	window.open("about:blank","popwin","width=1400,height=800,scrollbars=yes");
	$('#bo_sn').val(bo_sn);
	form.action = url;
	form.method = "post";
	form.target="popwin";
	form.bo_sn = bo_sn;
	form.submit();
	
}
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
            document.listform.action = "./gotrash_endlist.do";
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
	$("#frm").attr("action", "./endlist.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}



$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});

$(document).ready(function(){
	$('#start_d').datepicker();
	$('#end_d').datepicker();
})
</script>
</body>
</html>
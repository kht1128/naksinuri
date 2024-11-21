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
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="plocation" />
<body oncontextmenu="return false;">
<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="fishing_type" id="fishing_type" value="boatfishing" />
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
							
										<option value="x_name" <c:if test="${pageinfo.searchType eq 'x_name' }"> selected</c:if> >명 칭</option>
										<option value="x_address2" <c:if test="${pageinfo.searchType eq 'x_address2' }"> selected</c:if>>지역명</option>
<!-- 										<option value="bo_name">글쓴이</option> -->
		
									</select>
									<input type="text" class="frm_input" size="70" name="searchText" value="${pageinfo.searchText }" /> 
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
			<input type="hidden" name="x_sn" id="x_sn" value="${item.x_sn }"/>
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
							<col width="200" />
							<col width="150" />							
							<col width="200" />
							<col width="150" />
							<col width="150" />
							<col width="150" />
							<col width="150" />
							
							

						</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								<th scope="col">번호</th>
								<th scope="col">명 칭</th>
								<th scope="col">지정기간</th>
								<th scope="col">지정범위</th>
								<th scope="col">지정면적</th>
								<th scope="col">지정권자</th>
								<th scope="col">이수목적</th>
								<th scope="col">관련법령</th>

							</tr>
						</thead>
						<tbody>
						<c:if test="${fn:length(select_list) eq 0 }">
							<tr>
								<td class="textcenter" colspan="9"> 데이터가 없습니다. </td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${select_list}" varStatus="status">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.x_sn}"></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.tot_cnt - ((pageinfo.pageIndex - 1) * pageinfo.pageUnit +status.index)}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_name}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_term} ~ ${item.x_end}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">
									<c:choose>
										<c:when test="${fn:length(item.x_rang) > 10}">${fn:substring(item.x_rang,0,9)}...</c:when>
										<c:otherwise>${item.x_rang}</c:otherwise>
									</c:choose> 
									</a>
								</td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_area}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_person}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_purpose}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.x_sn}')">${item.x_related}</a></td>			
								
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
function view2(x_sn){
	var form = document.getElementById('listform');
	$('#x_sn').val(x_sn);
	
	form.action="./promotion_findCorp.do";
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
            document.listform.action = "./gotrash_list_p.do";
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
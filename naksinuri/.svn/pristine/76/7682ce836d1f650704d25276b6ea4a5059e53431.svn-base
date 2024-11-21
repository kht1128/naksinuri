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
<c:set var="depthName" value="info" />
<c:set var="pageName" value="industry" />
<body oncontextmenu="return false;">

<form action="" id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden"  id="req_sido" value="${searchSido }" />
<input type="hidden" id="req_gugun" value="${searchGugun}" />
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">			

			<h1 class="bigTit"><i class="fa fa-trash" aria-hidden="true"></i> 낚시산업정보 휴지통 <div class="trash_box"><%@include file="../trash_selectBox.jsp" %></div></h1>
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
									<input type="checkbox" id="san_aag" name="san_aag" value="협회/기관/단체" <c:if test ="${san_aag eq'협회/기관/단체'}">checked</c:if>/><label for="san_aag"><span></span>협회/기관/단체&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_zogu" name="san_zogu" value="조구업체" <c:if test ="${san_zogu eq'조구업체'}">checked</c:if>/><label for="san_zogu"><span></span>조구업체&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_media" name="san_media" value="미디어"   <c:if test ="${san_media eq '미디어'}">checked</c:if>/><label for="san_media"><span></span>미디어&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_sell" name="san_sell" value="판매점"   <c:if test ="${san_sell eq'판매점'}">checked</c:if>/><label for="san_sell"><span></span>판매점&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_chool" name="san_chool" value="출조점"   <c:if test ="${san_chool eq '출조점'}">checked</c:if>/><label for="san_chool"><span></span>출조점&nbsp;&nbsp;</label>
									<input type="checkbox" id="san_inprov" name="san_inprov" value="낚시정보제공" <c:if test ="${san_inprov eq'낚시정보제공'}">checked</c:if>/><label for="san_inprov" ><span></span>낚시정보제공</label>
									<br><br>
									<select class="frm_select" style="width:100px" name="searchType">
										<option value="san_name"<c:if test ="${searchType eq'san_name'}">selected</c:if>>상호명</option>
										<option value="san_buisnessman" <c:if test ="${searchType eq'san_buisnessman'}">selected</c:if>>대표자</option>
										<option value="san_addr" <c:if test ="${searchType eq'san_addr'}">selected</c:if>>주소</option>
									</select>
									<input type="text" class="frm_input" size="70" name="searchText" value="${searchText }" /> 
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
	<input type="hidden" name="san_sn" id="san_sn" value="${item.san_sn }"/>
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>${ind_total}</b>건 <br><span>전체선택</span><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></div>
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
							<col width="110" />
							<col />
							<col width="200" />
							
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">번호</th>
								<th scope="col">대표자</th>
								<th scope="col">상호명</th>
								<th scope="col">사업장주소</th>	
								<th scope="col">등록날짜</th>
								
							</tr>
						</thead>
						<tbody>
						<c:forEach var="item" items="${ind_list}">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.san_sn}"></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.san_sn}')">${ind_total-item.rn+1}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.san_sn}')">${fn:substring(item.san_buisnessman,0,10)}</a></td>
								<td><a href="#;" onclick="view2('${item.san_sn}')">${fn:substring(item.san_name,0,10)}</a></td>
								<td><a href="#;" onclick="view2('${item.san_sn}')">${fn:substring(item.san_address,0,40)}</a></td>
								<td class="textcenter"><a href="#;" onclick="view2('${item.san_sn}')">
								<fmt:parseDate value="${item.san_date}" var="f_data" pattern="yyyy-MM-dd HH:mm:ss.SSS" scope="page"/>
								<fmt:formatDate value="${f_data}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</a></td>		
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->
	
</form:form>
				<!-- 페이지 { -->
				<div align="right"><a href="#;" onclick="fncGroupListDelete()" class="btn_size1 btn_violet">삭제</a><a href="#;" onclick="fncGroupListRestore()" class="btn_size1 btn_violet">글쓰기</a></div>
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
function view2(san_sn){
	var form = document.getElementById('listform');
	$('#san_sn').val(san_sn);
	
	form.action="./ind_find.do";
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
            document.listform.action = "./ind_del.do";
            document.listform.submit();
	    }
	}
}


function fncGroupListRestore() {
	if(fncManageChecked()) {
	    if(confirm("복구하시겠습니까?")) {
            document.listform.action = "./ind_restore.do";
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
	$("#frm").attr("action", "./trash.do");
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
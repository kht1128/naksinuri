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
<c:set var="pageName" value="boatfishing" />

<%@include file="header_admin.jsp"%>
<%@include file="login_header.jsp"%>
<script type="text/javascript">
function fnSelectInfs(pageIndex) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val(idx);
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "${pageContext.request.contextPath}/info/admin/boatfishing.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});
</script>
<body oncontextmenu="return false;">
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
								<th>등록날짜</th>
								<td colspan="5"><input type="text" readonly class="frm_input date_input" size="10" name="searchDate1" value="${searchDate1}"/> ~ <input type="text" readonly class="frm_input date_input" size="10" name="searchDate2" value="${searchDate2}"/></td>
								<!-- <th>주소</th>
								<td>
									<select class="frm_select">
										<option>선택</option>
										<option>서울</option>
										<option>부산</option>
										<option>대구</option>
										<option>서울</option>
										<option>서울</option>
										<option>경기도</option>
										<option>강원도</option>
										<option>충청남도</option>
										<option>충청북도</option>
										<option>경상북도</option>
										<option>경상남도</option>
										<option>강원도</option>
										<option>강원도</option>
										<option>강원도</option>
										<option>강원도</option>
									</select>
								</td> -->
								<!-- <th>진행상태</th>
								<td>
									<select class="frm_select">
										<option>선택</option>
										<option>진행상태을 골라주세요</option>
										<option>진행상태을 골라주세요</option>
										<option>진행상태을 골라주세요</option>
									</select>
								</td> -->
							</tr>
							<tr>
								<th>검색</th>
								<td colspan="5">
									<select class="frm_select" style="width:100px" name="searchType">
										<option>선택</option>
										<option value="co_nm" <c:if test="${searchType eq 'co_nm' }">selected</c:if>>상호명</option>
										<option value="ceo_nm" <c:if test="${searchType eq 'ceo_nm' }">selected</c:if>>대표자</option>
										<option value="co_addr"<c:if test="${searchType eq 'co_addr' }">selected</c:if>>주소</option>
									</select>
									
									
									
									<input type="text" class="frm_input" size="70" name="searchText" value="${searchText}"/> 
									<button class="btn_size1 btn_violet" type="button" onclick="fnSelectInfs(1);">검색</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
				<!-- } 검색 영역 -->
		<form:form commandName="boatform" id="boatform" name="boatform" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="nak_id" id="nak_id" value="${item.nak_id }"/>
			<input type="hidden" name="bo_sns" id="bo_sns"/>
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>${admin_total}</b>건</div>
						<div class="listnum" align="right">
							출력수 
							<select class="frm_select" id="s_pageUnit" name="s_pageUnit" onchange="fnSelectInfs(1);">
								<option value="10">10</option>
								<option value="20">20</option>
								<option value="30">30</option>
								<option value="40">40</option>
								<option value="50">50</option>
							</select>
							<br>
							<a href="/info/admin/boatfishing.do" class="btn_size1 btn_violet">최신순</a>
							<a href="/info/admin/boatlatest.do" class="btn_size1 btn_violet">등록순</a>
							<a href="/info/admin/boatKor.do" class="btn_size1 btn_violet">한글순</a>
						</div>
					</div>
					<table class="t_list">
						<colgroup>
							<col width="30" />
							<col width="80" />
							<col width="300" />
							<col />
							<col width="110" />
							<col width="130" />
							<col width="200" />
							<col width="110" />
							
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								<th scope="col">번호<br>(고유번호)</th>
								<th scope="col">상호(선박)명</th>
								<th scope="col">주소</th>
								<th scope="col">승인상태</th>
								<th scope="col">휴대전화</th>
								<th scope="col">등록날짜</th>
								<th scope="col">대표자</th>
								
								
							</tr>
						</thead>
						<tbody>
						<c:if test="${admin_list ne ''}">
							<c:forEach var="item" items="${admin_list}">
								<c:choose>
									<c:when test="${item.cate_mod_st eq 'Y'}">
										<tr>
											<td><input type="checkbox" name="delYn" class="check2" value="${item.nak_id}"></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${admin_total-item.rn+1}</a><br>(${item.nak_id})</td>
											<td class="textcenter red-600">(카테고리 변경건) <a href="#;" onclick="view2('${item.nak_id}')">${item.co_nm}</a></td>
											<td><a href="#;" onclick="view2('${item.nak_id}')">${item.co_addr_2}</a></td>
											<td class="textcenter">
												<a href="#;" onclick="view2('${item.nak_id}')">
													<c:if test="${item.co_status eq 'n'}">미승인</c:if>
													<c:if test="${item.co_status eq 'y'}">승인</c:if>
													<c:if test="${item.co_status eq 'w'}">대기</c:if>
												</a>
											</td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${item.co_hphone}</a></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${fn:substring(item.reg_date,0,16)}</a></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${item.ceo_nm}</a></td>
										</tr>
									</c:when>
									<c:otherwise>
										<tr>
											<td><input type="checkbox" name="delYn" class="check2" value="${item.nak_id}"></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${admin_total-item.rn+1}</a><br>(${item.nak_id})</td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${item.co_nm}</a></td>
											<td><a href="#;" onclick="view2('${item.nak_id}')">${item.co_addr_2}</a></td>
											<td class="textcenter">
												<a href="#;" onclick="view2('${item.nak_id}')">
													<c:if test="${item.co_status eq 'n'}">미승인</c:if>
													<c:if test="${item.co_status eq 'y'}">승인</c:if>
													<c:if test="${item.co_status eq 'w'}">대기</c:if>
												</a>
											</td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${item.co_hphone}</a></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${fn:substring(item.reg_date,0,16)}</a></td>
											<td class="textcenter"><a href="#;" onclick="view2('${item.nak_id}')">${item.ceo_nm}</a></td>
										</tr>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${admin_list eq ''}">
							<tr>
								<td colspan="6">데이터가 없습니다.</td>
							</tr>
						</c:if>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->
	
</form:form>

				<div align="right"><a href="#;" onclick="fncGroupListDelete()" class="btn_size1 btn_violet">삭제</a><a href="/info/admin/write_boat.do" class="btn_size1 btn_violet">글쓰기</a></div>
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

<script>
function view2(nak_id){
	var form = document.getElementById('boatform');
	$('#nak_id').val(nak_id);
	
	form.action="/info/admin/findCorp.do";
	form.submit();
};
function serch_submit(){
	$('#serch_submit').submit();
	
	
}

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


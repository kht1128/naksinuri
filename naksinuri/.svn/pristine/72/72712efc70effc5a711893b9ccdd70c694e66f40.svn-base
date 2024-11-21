<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="nuri" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">

<form action="" id="frm" name="frm" method="post">
<div id="wrapper">
	<%@include file="../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">			

			<h1 class="bigTit"><i class="fa fa-trash" aria-hidden="true"></i> 자주묻는낚시질문 휴지통 <div class="trash_box"><%@include file="../trash_selectBox.jsp" %></div></h1>

			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
					</ul>
				</div>
				</form>
				<!-- } 검색 영역 -->
	<form:form commandName="listform" id="listform" name="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sns" id="bo_sns"/>
		<input type="hidden" name="zazu_num" id="zazu_num" value="${item.zazu_num }"/>
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>${zazu_total}</b>건</div>
					</div>
					<table class="t_list">
						<colgroup>
							<col width="30" />
							<col width="50" />
							<col />
							<col width="200" />
							
						</colgroup>
						<thead>
							<tr>
								<th scope="col"><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></th>
								<th scope="col">번호</th>
								<th scope="col">질문</th>
								<th scope="col">등록날짜</th>
							

							</tr>
						</thead>
						<tbody>
						<c:forEach var="item"  items="${zazu_list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.zazu_num}"></td>
								<td class="textcenter"><a href="#;">${zazu_total-item.rn+1}</a></td>
								<td class="subject align_left"><a href="#;" onclick="view2('${item.zazu_num}')">${item.zazu_ques}</a></td>
								<td class="textcenter"><a href="#;">
									<fmt:parseDate value="${item.zazu_insert_date}" var="f_data" pattern="yyyy-MM-dd HH:mm:ss.SSS" scope="page"/>
									<fmt:formatDate value="${f_data}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</a></td>		
						
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- } 리스트 영역 -->
	
	</form:form>
				<div align="right"><a href="#;" onclick="fncGroupListDelete()" class="btn_size1 btn_violet">삭제</a><a href="#;" onclick="fncGroupListRestore()" class="btn_size1 btn_violet">복구</a></div>
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
		
	</footer>
	<!-- } 하단 푸터 -->
</div>
<script>
function view2(zazu_num){
	var form = document.getElementById('listform');
	$('#zazu_num').val(zazu_num);
	
	form.action="/admin/share/nuri/find.do";
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
            document.listform.action = "./deletezazu.do";
            document.listform.submit();
	    }
	}
}

function fncGroupListRestore() {
	if(fncManageChecked()) {
	    if(confirm("복구하시겠습니까?")) {
            document.listform.action = "./restorezazu.do";
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
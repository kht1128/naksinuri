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
<c:set var="depthName" value="promotion" />
<c:set var="pageName" value="qna" />
<%@include file="../header_admin.jsp"%>
<%@include file="../login_header.jsp"%>
<body oncontextmenu="return false;">
<form id="frm" name="frm" method="post">
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="qna_type" id="qna_type" value="${qna_type}"/>
<div id="wrapper">
<%@include file="../admin_leftTab.jsp"%>
	<div id="container">

		<div id="content">			

			<h1 class="bigTit"><i class="fa fa-trash" aria-hidden="true"></i> 낚시법 및 유어장 관련 질의회신 사례 휴지통 <div class="trash_box"><%@include file="../trash_selectBox.jsp" %></div></h1>

			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="tab6 floats">
						<li onclick="fnSelectInfs2('낚시관리및제도일반')"><a href="#;" class="tab1 on">낚시관리및제도일반</a></li>
	 					<li onclick="fnSelectInfs2('낚시터')"><a href="#;" class="tab2">낚시터</a></li>
	 					<li onclick="fnSelectInfs2('낚시어선')"><a href="#;" class="tab3">낚시어선</a></li>
	 					<li onclick="fnSelectInfs2('낚시도구및미끼')"><a href="#;" class="tab4">낚시도구및미끼</a></li>
	 					<li onclick="fnSelectInfs2('유어장')"><a href="#;" class="tab5">유어장</a></li>
	 					<li onclick="fnSelectInfs2('참고자료')"><a href="#;" class="tab6">참고자료</a></li>
					</ul>
				</div>
				</form>
				<!-- } 검색 영역 -->
		<form:form commandName="listform" id="listform" name="listform" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="bo_sns" id="bo_sns"/>
			<input type="hidden" name="qna_no" id="qna_no" value="${item.qna_no }"/>
			<input type="hidden" name="qna_type" value="${qna_type}"/>
				<!-- 리스트 영역 { -->
				<div class="t_list_area">
					<div class="listtop floats">
						<div class="totalnum">총 <b>${select_total}</b>건 <br><span>전체선택</span><input type="checkbox" name="checkAll" class="check2" onclick="javascript:fncCheckAll()" title="전체선택"></div>
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
							<col width="130" />
							<col />
							<col width="130" />
							<col width="130" />
							
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">번호</th>
								<th scope="col">종류</th>
								<th scope="col">질문<span style="font-size:12px">(이 회신집에 수록된 사항은 참고용입니다.)</span></th>
								<th scope="col">등록날짜</th>
								<th scope="col">수정날짜</th>
							

							</tr>
						</thead>
						<tbody>
						<c:forEach var="item"  items="${select_list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="delYn" class="check2" value="${item.qna_no}"></td>
								<td class="textcenter"><a href="#;">${select_total-item.rn+1}</a></td>
								<td class="textcenter"><a href="#;">${item.qna_type}</a></td>
								<td class="subject align_left"><a href="#;" onclick="view2('${item.qna_no}')">${item.qna_ques}</a></td>
								<td class="textcenter"><a href="#;">${item.qna_insert_dt}</a></td>		
								<td class="textcenter"><a href="#;">${item.qna_update_dt}</a></td>		
							
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
	var qna_type = $('#qna_type').val()

		if(qna_type=='낚시관리및제도일반'){
			$('.tab1').addClass("on");
			$('.taball').removeClass("on");
			$('.tab2').removeClass("on");
			$('.tab3').removeClass("on");
			$('.tab4').removeClass("on");
			$('.tab5').removeClass("on");
			$('.tab6').removeClass("on");
			
		}else if(qna_type=='낚시터'){
			$('.tab2').addClass("on");
			$('.taball').removeClass("on");
			$('.tab1').removeClass("on");
			$('.tab3').removeClass("on");
			$('.tab4').removeClass("on");
			$('.tab5').removeClass("on");
			$('.tab6').removeClass("on");
			
		}else if(qna_type=='낚시어선'){
			$('.tab3').addClass("on");
			$('.taball').removeClass("on");
			$('.tab1').removeClass("on");
			$('.tab2').removeClass("on");
			$('.tab4').removeClass("on");
			$('.tab5').removeClass("on");
			$('.tab6').removeClass("on");
			
		}else if(qna_type=='낚시도구및미끼'){
			$('.tab4').addClass("on");
			$('.taball').removeClass("on");
			$('.tab1').removeClass("on");
			$('.tab2').removeClass("on");
			$('.tab3').removeClass("on");
			$('.tab5').removeClass("on");
			$('.tab6').removeClass("on");
		}else if(qna_type=='유어장'){
			$('.tab5').addClass("on");
			$('.taball').removeClass("on");
			$('.tab1').removeClass("on");
			$('.tab2').removeClass("on");
			$('.tab3').removeClass("on");
			$('.tab4').removeClass("on");
			$('.tab6').removeClass("on");
		}else if(qna_type=='참고자료'){
			$('.tab6').addClass("on");
			$('.taball').removeClass("on");
			$('.tab1').removeClass("on");
			$('.tab2').removeClass("on");
			$('.tab3').removeClass("on");
			$('.tab4').removeClass("on");
			$('.tab5').removeClass("on");
		}
})



function view2(qna_no){
	var form = document.getElementById('listform');
	$('#qna_no').val(qna_no);
	
	form.action="/admin/promotion/qna/find.do";
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
            document.listform.action = "./delete_list.do";
            document.listform.submit();
	    }
	}
}

function fncGroupListRestore() {
	if(fncManageChecked()) {
	    if(confirm("복구하시겠습니까?")) {
            document.listform.action = "./restore.do";
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

function fnSelectInfs2(qna_type) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val();
	$("#pageIndex").val(1);	
	$("#qna_type").val(qna_type);
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
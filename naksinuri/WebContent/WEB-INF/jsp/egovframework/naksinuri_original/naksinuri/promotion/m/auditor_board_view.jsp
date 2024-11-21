<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="promotion"/>
<c:set var="depthNum" value="5" />
<c:set var="pageName" value="auditor" />

<%@include file="../../layout/m/head.jsp"%>

	<script src="/naksinuri_original/common_main/js/jquery.sticky.js"></script>

	<script>
	var youtube_link = '${info.bo_youtubelink}';
		$(document).ready(function(){
			
			//$('#youtubelink_div').html('<iframe width="400px" height="240px" src="'+youtube_link+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>');
			
			/* 낚시산업정보 상세페이지 기본정보 스크롤 (Feat.sticky-kit) */
			$("#boardView #viewLatest").stick_in_parent()
			.on("sticky_kit:stick", function(e) {
				$('#viewLatest').addClass("scrollOn");
			})
			.on("sticky_kit:unstick", function(e) {
				$('#viewLatest').removeClass("scrollOn");
			});
		});
	</script>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn"/>
		<input type="hidden" name="co_sn" id="co_sn"/>
		<input type="hidden" id="spbo_sn" value="${info.bo_sn}" />
		<input type="hidden" name="co_pass4" id="co_pass4" />
		<input type="hidden" name="bo_pass" id="bo_pass" />
	</form:form>
	
	<form:form commandName="boardVO" id="viewForm" name="viewForm" method="post">
		<input type="hidden" name="bo_sn" id="bo_sn">
	</form:form>
	
	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
				<div  id="printthis">
					<div class="viewTit">
						<h1 class="tit">${info.bo_subject}</h1>
						<div class="name_date">
							<span class="name">${info.bo_name}</span>	
							<span class="date">${fn:substring(info.bo_insert_dt,0,10)}</span>
						</div>
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em><span class="blind">조회수</span> ${info.bo_view}</li>
						</ul><br>
						<%-- <c:if test="${column ne 'share' and table ne 'travel' and not empty info.bo_atch_file}">
							첨부파일 목록 : 					
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${info.bo_atch_file}" />
			                </c:import>					              
		                </c:if> --%>
					</div>
						
					<div class="content">${info.bo_content}</div>
				</div>
				
				<div id="btnArea">
					<ul class="floats">
					
						<li>
							<a href="#" onclick="prev(${prev.bo_sn})" class="btn_prev btn_white" title="이전글"><i class="fa fa-angle-left" aria-hidden="true" title="이전글"></i> 이전글</a>
							<a href="#" onclick="next(${next.bo_sn})" class="btn_next btn_white" title="다음글">다음글 <i class="fa fa-angle-right" aria-hidden="true" title="다음글"></i></a>
						</li>
						<c:if test="${info.bo_member_id eq MBR_ID}">
							<li class="">
								<a href="javascript:void(0)" onClick="fnModify(${info.bo_sn})" class="btn_list btn_blue" title="수정하기" >수정하기</a>
							</li>
						</c:if>
						<li class="fr">
							<a href="javascript:history.go(-1);" class="btn_list btn_gray" title="목록가기" >목록</a>
						</li>
					</ul>
				</div>
			</section>
			
		</article>
	</div>
	
			
	<div class="password_check modal fade" id="updateCheckPassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<p class="comment">게시글을 수정합니다. 비밀번호를 입력해주세요.</p>
				<input type="password" class="del_input" id="udt_bo_pass" placeholder="비밀번호를 입력해주세요." />
				<div class="btn_box">
					<button class="cp_btn del_btn" onclick="update_travel_info(${info.bo_sn})" >확인</button>
					<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
				</div>
			</div>
		</div>
	</div>

	<div class="password_check modal fade" id="deleteCheckPassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<p class="comment">게시글을 삭제합니다. 비밀번호를 입력해주세요.</p>
				<input type="password" class="del_input" id="del_bo_pass" placeholder="비밀번호를 입력해주세요." />
				<div class="btn_box">
					<button class="cp_btn del_btn" onclick="delete_travel_info(${info.bo_sn})" >확인</button>
					<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
				</div>
			</div>
		</div>
	</div>
	
<%@include file="../../layout/m/tail.jsp"%>

<script type="text/javascript">

	$('#changePassword').on('show.bs.modal',function(e){
	var aa = $(e.relatedTarget).data('rev');
	$('#co_sn').val(aa);
	})
	
	function fnView(bdsn) {
		document.viewForm.bo_sn.value = bdsn;
	    document.viewForm.action = "/promotion/m/auditor/board_view.do";
	    document.viewForm.submit();
	}
	function fnModify(bdsn) {
		document.viewForm.bo_sn.value = bdsn;
	    document.viewForm.action = "/promotion/m/auditor/board_modify.do";
	    document.viewForm.submit();
	}
	
	function next(bo_sn){
		if(bo_sn==null){
			alert('다음글이 없습니다');
			return false;
				
			}
		
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./board_view.do";
		form.submit();
	};
	function prev(bo_sn){
		if(bo_sn==null){
		alert('이전글이 없습니다');
		return false;
			
		}
		
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./board_view.do";
		form.submit();
	};

	function co_del(){
		var form = document.getElementById('listform');
		var bo_sn = $("#spbo_sn").val();
		var co_pass4 = $("#co_passr").val();
		$('#bo_sn').val(bo_sn);
		$('#co_pass4').val(co_pass4);
		
		form.action = "./co_delete.do";
		form.submit();
	}
	function replsubmit(){
		
		var form = document.getElementById('listform');
		var co_name=$('#co_name').val();
		var co_pass=$('#co_pass').val();
		var co_comment=$('#co_comment').val();
		/*
		if(co_name==''){
			alert("이름을 입력하세요");
			return false;
		}else if(co_pass==''){
			alert("비밀번호를 입력하세요.");
			return false;
		}else if(co_comment==''){
			alert("댓글내용을 입력하세요.");
		}else{
			form.action="./view.do";
			form.submit();
		}
		
		return true;
		*/
		if(co_name==''){
			alert("이름을 입력하세요");
			return false;
		}else if(co_pass==''){
			alert("비밀번호를 입력하세요.");
			return false;
		}else if( !/^[a-zA-Z0-9]{10,}$/.test(co_pass) || co_pass.search(/[0-9]/g)<0 || co_pass.search(/[a-z]/ig)<0 ){
			alert("비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.");
			return false;
		}else{
			form.action="./board_view.do";
			form.submit();
		}		
		
	}
		
	
</script>
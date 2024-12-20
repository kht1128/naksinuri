<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="event" />
<c:if test="${pageName eq 'event'}">
	<c:set var="bo_cate" value="ancevent" />
</c:if>

<%@include file="../layout/newHead.jsp"%>

	<script src="/naksinuri_original/common_main/js/jquery.sticky.js"></script>

	<script>
		$(document).ready(function(){
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
		<input type="hidden" name="evn_no" id="evn_no"/>
		<input type="hidden" name="eco_pass4" id="eco_pass4" />
		<input type="hidden" id="spevn_no" value="${info.evn_no}" />
		<input type="hidden" name="eco_no" id="eco_no"/>
		<input type="hidden" name="eco_content_add" id="eco_content_add"/>
	
	</form:form>
	

	
	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
				<div class="viewTit">
					<h3 class="tit">${info.evn_subject}</h3>
					<div class="name_date"><span class="name">${info.evn_writer}</span><span class="date">${fn:substring(info.evn_insert_dt,0,10)}</span></div>
					<ul class="eyes_heart floats">
						<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> ${info.evn_viewhit}</li>
						<li><a href="#;" onclick="like_up(${info.evn_no})"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${info.evn_like }</a></li>
						<!-- 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> -->
						
					</ul><br>
					<c:if test="${not empty info.evn_atch_file}">
						첨부파일 목록 : 					
						<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
				                    <c:param name="param_atchFileId" value="${info.evn_atch_file}" />
				                </c:import>
	                </c:if>
				</div>
				<div class="content">
					${info.evn_content}	
						<div class="writer_box">
							<div class="pic">	
								<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_writer.png">					
							</div>
							<div class="name"><span>작성자</span> ${info.evn_writer}</div>
							<p>${info.evn_info}</p>
							<div class="warning">
								본 콘텐츠의 저작권은 저자 또는 제공처에 있음을 알려드립니다.<br />
								또한 무단으로 콘텐츠를 이용하는 경우 저작권법 등에 따라 법적 책임을 질 수 있으니 유의하시기 바랍니다.
							</div>
						</div>
								<%@include file="../layout/evnprintmenu.jsp"%>
				</div>
				<div class="reply_box">
					<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') }">
						<form method="post">
							<input type="hidden" name="evn_no" id="evn_no" value="${info.evn_no}"/>
							<div class="reply_write">
								<div class="name_pw">
									<input type="text" class="reply_input" name="eco_name" id="eco_name" placeholder="이름" /><input type="password" id="eco_pass" name="eco_pass" class="reply_input" placeholder="비밀번호" />
								</div>
								<div class="reply_textbox">
									<textarea class="reply_textarea" name="eco_content" id="eco_content" placeholder="댓글을 입력해주세요"></textarea>
									<button class="submitBtn">댓글등록</button>
								</div>
							</div>
						</form>	
						<div class="reply_list">
						<c:forEach var="item" items="${comment_list}">
							<div class="reply_con">
								<span class="name">${item.eco_name}</span>
								<span class="date">${fn:substring(item.eco_insert_dt,0,20)}</span>
								
								<a href="#;" data-toggle="modal" data-target="#changePassword${item.eco_no}" data-rev="${item.eco_no}" class="replyBtn">[삭제]</a>
								<a href="#;" data-toggle="modal" data-target="#changeReply${item.eco_no}" data-rev="${item.eco_no}" class="replyBtn">[본인인증정보등록]</a>
								<input type="hidden" id="eco_no4" value="${item.eco_no }"/>
								<c:if test="${not empty item.eco_gongmo_hp}">
									<p style="font-weight:bold;color:#f00;">본인인증정보등록완료</p>
								</c:if>
								<p>${item.eco_content}</p>
							</div>
							
							<div class="password_check modal fade" id="changePassword${item.eco_no}" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<p class="comment">댓글을 삭제합니다. 비밀번호를 입력해주세요.</p>
										<input type="password" class="del_input" id="eco_passr${item.eco_no}" placeholder="비밀번호를 입력해주세요." />
										<div class="btn_box">
											<button class="cp_btn del_btn" onclick="eco_del(${item.eco_no})" >확인</button>
											<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
										</div>
									</div>
								</div>
							</div>
							
							<div class="password_check modal fade" id="changeReply${item.eco_no}" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<p class="comment">연락 가능한 연락처를 입력해주세요.<br><span class="txt_red" style="font-size:11px;">해당 정보는 외부에 노출되지 않으며 이벤트 종료 후 폐기됩니다.</span></p>
										<input type="password" class="del_input" id="eco_mod_passr${item.eco_no}" placeholder="비밀번호를 입력해주세요." />
										<textarea id="eco_content_add${item.eco_no}" placeholder="내용을 입력해주세요" style="width:100%;height:100px;padding:10px;border:1px solid #cdcdcd;"></textarea>
										<div class="btn_box">
											<button class="cp_btn del_btn" onclick="eco_modify(${item.eco_no})" >확인</button>
											<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
										</div>
									</div>
								</div>
							</div>
							
						</c:forEach>
						</div>
					</c:if>
				</div>
				<div id="btnArea">
					<ul class="floats">
					
						<li>
							<a href="#" onclick="prev(${prev.evn_no})" class="btn_prev btn_white" title="이전글"><i class="fa fa-angle-left" aria-hidden="true" title="이전글"></i> 이전글</a>
							<a href="#" onclick="next(${next.evn_no})" class="btn_next btn_white" title="다음글">다음글 <i class="fa fa-angle-right" aria-hidden="true" title="다음글"></i></a>
						</li>
						<li class="fr">
							<a href="./list.do" class="btn_list btn_gray" title="목록가기" >목록</a>
						</li>
					</ul>
				</div>
			</section>
			
			
		<section id="viewLatest" class="gallery_list">
				<ul>
					<c:if test="${pageName eq 'event'}">
						<c:forEach var="item" items="${post_list}">
							<li>
								<a href="#;" onclick="view2('${item.evn_no}')"class="pic" title="${item.evn_subject} 상세보기"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.evn_main_img}"/>&fileSn=<c:out value="0"/>'  width="303" height="250" alt="${item.evn_subject} 상세보기"/></a>
								<a href="#;" onclick="view2('${item.evn_no}')" class="subject" title="${item.evn_subject} 상세보기">
									<c:choose>
										<c:when test="${fn:length(item.evn_subject)>50 }">
											<em class="list_subject">${fn:substring(item.evn_subject, 0, 50)}...</em>
										</c:when>
										<c:otherwise>
											<em class="list_subject">${item.evn_subject}</em>
										</c:otherwise>
									</c:choose> 
										<span class="date">이벤트 기간: ${fn:substring(item.evn_startdate,0,10)}~ ${fn:substring(item.evn_enddate,0,10)}</span>
								</a>
								<a href="#;" onclick="view2('${item.evn_no}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.evn_subject} 상세보기"></i></a>
							</li>
						</c:forEach>
					</c:if>
						
				
					<c:if test="${pageName eq 'endevent'}">
						<c:forEach var="item" items="${end_post_list}">
							<li>
								<a href="#;" onclick="view2('${item.evn_no}')"class="pic" title="${item.evn_subject} 상세보기"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.evn_main_img}"/>&fileSn=<c:out value="0"/>'  width="303" height="250"  alt="${item.evn_subject}"/></a>
								<a href="#;" onclick="view2('${item.evn_no}')" class="subject" title="${item.evn_subject} 상세보기">
									<c:choose>
										<c:when test="${fn:length(item.evn_subject)>50 }">
											<em class="list_subject">${fn:substring(item.evn_subject, 0, 50)}...</em>
										</c:when>
										<c:otherwise>
											<em class="list_subject">${item.evn_subject}</em>
										</c:otherwise>
									</c:choose> 
									<span class="date">이벤트 기간: ${fn:substring(item.evn_startdate,0,10)}~ ${fn:substring(item.evn_enddate,0,10)}</span>
									
								</a>
								<a href="#;" onclick="view2('${item.evn_no}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.evn_subject} 상세보기"></i></a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</section>
			
		</article>
	</div>
<%@include file="../layout/tail.jsp"%>
<script type="text/javascript">
	$('#changePassword').on('show.bs.modal',function(e){
	$('#co_sn').val("");
	var aa = $(e.relatedTarget).data('rev');
	$('#co_sn').val(aa);
	})
	
	function eco_modify(t){
		var form = document.getElementById('listform');
		var evn_no = $("#spevn_no").val();
		var eco_pass4 = $("#eco_mod_passr"+t).val();
		var eco_content_add = $("#eco_content_add"+t).val();
		$('#evn_no').val(evn_no);
		$('#eco_no').val(t);
		$('#eco_pass4').val(eco_pass4);
		$('#eco_content_add').val(eco_content_add);
		
		form.action = "./eco_update.do";
		form.submit();
						
	}
	
	function eco_del(t){
		var form = document.getElementById('listform');
		var evn_no = $("#spevn_no").val();
		var eco_pass4 = $("#eco_passr"+t).val();
		
		$('#evn_no').val(evn_no);
		$('#eco_no').val(t);
		$('#eco_pass4').val(eco_pass4);
		
		form.action = "./eco_delete.do";
		form.submit();
	}
	
	function view2(evn_no){
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		
		form.action="./view.do";
		form.submit();
	};
	function next(evn_no){
		if(evn_no==null){
			alert('다음글이 없습니다');
			return false;
				
			}
		
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		
		form.action="./view.do";
		form.submit();
	};
	function prev(evn_no){
		if(evn_no==null){
		alert('이전글이 없습니다');
		return false;
			
		}
		
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		
		form.action="./view.do";
		form.submit();
	};
	
	
	function like_up(evn_no){
		var form = document.getElementById('listform');
		$('#evn_no').val(evn_no);
		form.action="./like.do";
		form.submit();
		
			
	}

</script>


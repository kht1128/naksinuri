<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthNum" value="8" />
<c:set var="pageMode" value="gongmo"/>
<c:set var="depthName" value="gongmo" />
<c:set var="pageName" value="event" />

<%@include file="../../layout/m/head.jsp"%>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="evn_no" id="evn_no"/>
		<input type="hidden" name="eco_pass4" id="eco_pass4" />
		<input type="hidden" id="spevn_no" value="${info.evn_no}" />
		<input type="hidden" name="eco_no" id="eco_no"/>
	
	
	</form:form>
	
	
	<jsp:useBean id="now" class="java.util.Date" />
	<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="nowDate" /> 
	<c:if test="${info.evn_enddate eq null}">
		<c:set var = "evn_enddate" value = "20180101235959" />
	</c:if>	
	<c:if test="${info.evn_enddate ne null}">
		<c:set var = "evn_enddate" value = "${fn:replace(info.evn_enddate, '-', '')}235959" />
	</c:if>
	
	

	
	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
				<div class="viewTit">
					<h1 class="tit">${info.evn_subject}</h1>
					<div class="name_date"><span class="name">${info.evn_writer}</span><span class="date">${fn:substring(info.evn_insert_dt,0,10)}</span></div>
					<ul class="eyes_heart floats">
						<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> ${info.evn_viewhit}</li>
						<li><a href="#;" onclick="like_up(${info.evn_no})"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${info.evn_like }</a></li>
						<!-- 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> -->
						
					</ul>
				</div>
				<div class="content">
					${info.evn_content}
					<%--	
					<div class="writer_box">
												
						<div class="name"><span>작성자</span> ${info.evn_writer}</div>
						<p>${info.evn_info}</p>
						<div class="warning">
							본 콘텐츠의 저작권은 저자 또는 제공처에 있음을 알려드립니다.<br />
							또한 무단으로 콘텐츠를 이용하는 경우 저작권법 등에 따라 법적 책임을 질 수 있으니 유의하시기 바랍니다.
						</div>
					</div>
					<%@include file="../../layout/evnprintmenu.jsp"%>
					--%>
					<%--
					<div class="set_btn_center mgt30">
						<c:if test="${not empty info.evn_atch_file}">
							공모전 포스터 다운로드 : 					
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${info.evn_atch_file}" />
			                </c:import>
		                </c:if>
	                </div>
					<div id="btnArea" class="noupline">
						<a href="#;" class="btn_request btn_orange" onclick="clipboard('http://www.naksinuri.kr/gongmo/summary/intro.do');">공모전요강복사</a>
					</div>
					 --%>
				</div>
				<c:if test="${evn_enddate > nowDate}">
				<div class="reply_box">
					<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') }">
						<form method="post" name="replform" id="replform" onsubmit="return replsubmit();">
							<input type="hidden" name="evn_no" id="evn_no" value="${info.evn_no}"/>
							<input type="hidden" name="eco_cate" id="eco_cate" value="gongmo"/>
							<div class="reply_write">
								<div class="name_pw">
									<input type="text" class="reply_input" name="eco_name" id="eco_name" placeholder="이름" /><input type="password" id="eco_pass" name="eco_pass" class="reply_input" placeholder="비밀번호" />
								</div>
								<div class="reply_input">
									<input type="number" class="reply_input" name="eco_gongmo_hp" id="eco_gongmo_hp" placeholder="휴대폰번호" /> <span class="ft12">(숫자만 입력해주세요)</span>
								</div>
								<div class="reply_input">
									<input type="text" class="reply_input w100" name="eco_gongmo_url" id="eco_gongmo_url" placeholder="업로드한 URL" />
								</div>
								<div class="reply_textbox">
									<textarea class="reply_textarea" name="eco_content" id="eco_content" placeholder="비고"></textarea>
									<input type="submit" class="submitBtn" value="참여하기"/>
								</div>
							</div>
						</form>	
						<div class="reply_list">
						<c:forEach var="item" items="${comment_list}">
							<div class="reply_con">
								<span class="name">${item.eco_name}</span>
								<span class="date">${fn:substring(item.eco_insert_dt,0,20)}</span>
								
								<a href="#;" data-toggle="modal" data-target="#changePassword${item.eco_no}" data-rev="${item.eco_no}" class="replyBtn">[삭제]</a>
								<input type="hidden" id="eco_no4" value="${item.eco_no }"/>
								<p>${item.eco_gongmo_url}</p>
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
						</c:forEach>
						</div>
					</c:if>
				</div>
				</c:if>
				<div id="btnArea">
					<ul class="floats">
					
						<li>
							<a href="#;" onclick="prev(${prev.evn_no})" class="btn_prev btn_white"><i class="fa fa-angle-left" aria-hidden="true"></i> 이전글</a>
							<a href="#;" onclick="next(${next.evn_no})"class="btn_next btn_white">다음글 <i class="fa fa-angle-right" aria-hidden="true"></i></a>
						</li>
						<li class="fr">
							<a href="./list.do" class="btn_list btn_gray">목록</a>
						</li>
					</ul>
				</div>
			</section>
			<section id="viewLatest" class="gallery_list">
				<ul>
				<c:forEach var="item" items="${post_list}">
					<li>
						<a href="#;" onclick="view2('${item.evn_no}')"class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.evn_main_img}"/>&fileSn=<c:out value="0"/>'  width="303" height="250"  /></a>
						<a href="#;" onclick="view2('${item.evn_no}')" class="subject">
					

							<em class="list_subject">${fn:substring(item.evn_subject.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""), 0, 50)}...</em>
						
							<span class="date">${fn:substring(item.evn_insert_dt,0,10)}</span>
						</a>
						<a href="#;" onclick="view2('${item.evn_no}')" class="search_square"><i class="fa fa-search" aria-hidden="true" title="${item.evn_subject} 상세보기"></i></a>
					</li>
				</c:forEach>
				</ul>
			</section>
		</article>
	</div>
	
<script type="text/javascript">

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
	
	
	function replsubmit(){
		
		var form = document.getElementById('replform');
		var co_name=$('#eco_name').val();
		var co_pass=$('#eco_pass').val();
		var eco_gongmo_hp=$('#eco_gongmo_hp').val();
		var eco_gongmo_url=$('#eco_gongmo_url').val();

		if(co_name==''){
			alert("이름을 입력하세요");
			$('#eco_name').focus();
			return false;
		}else if(co_pass==''){
			alert("비밀번호를 입력하세요.");
			return false;
		}else if(eco_gongmo_hp==''){
			alert("연락처를 입력하세요.");
			$('#eco_gongmo_hp').focus();
			return false;
		}else if(eco_gongmo_url==''){
			alert("업로드한 URL을 입력하세요.");
			$('#eco_gongmo_url').focus();
			return false;
		}else{
			alert("참여해주셔서 감사합니다.");
			form.action="./view.do";
			form.submit();
		}		
		
	}
	
	//클립보드 복사 
	function clipboard(str){
	    $('#clip_target').val(str); 
	    $('#clip_target').select(); 
	    try { 
	        var successful = document.execCommand('copy');  
	        alert('클립보드에 주소가 복사되었습니다. Ctrl + V 로 붙여넣기 하세요.'); 
	    } catch (err) { 
	        alert('이 브라우저는 지원하지 않습니다.'); 
	    }
	}


</script>
<input id="clip_target" type="text" value="" style="position:absolute;top:-9999em;"/>

<%@include file="../../layout/m/tail.jsp"%>

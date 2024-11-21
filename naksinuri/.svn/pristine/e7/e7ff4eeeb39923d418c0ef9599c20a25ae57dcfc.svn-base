<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="share"/>
<c:set var="depthName" value="share" />
<c:set var="pageName" value="zisik" />



<%@include file="../layout/head.jsp"%>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="nuri_q_num" id="nuri_q_num"/>
	</form:form>

	<div id="qnaView" class="content respon2">
	<div class="tabArea tab2"> <!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
			<ul class="floats">
				<li class="on"><a href="/share/zisik/list.do">누리지식인</a></li>
				<li><a href="/share/nuri/list.do">자주묻는낚시질문</a></li>
			</ul>
		</div>
		<div class="floats">
			<div class="lc">
				<section id="qnaViewQuestion" class="qnabox">
					<div class="ico"><img src="/naksinuri_original/common_main/img/ico_qna_q.png" alt="" /></div>
					<div class="tit">
						<h2>${info.nuri_q_subject }</h2>
						<span class="name"><i class="fa fa-user" aria-hidden="true"></i> ${info.nuri_q_writer }</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> ${info.nuri_q_date }</span>
					</div>
					<div class="comment">
						${info.nuri_q_content }
					</div>
					<div id="btnArea" class="noupline">
						<ul class="floats">
							<li class="fr">
								<a href="#;" onclick="update(${info.nuri_q_num})" class="btn_list btn_gray">수정</a>
							</li>
						</ul>
					</div>
				</section>
			</div>
			
			<div class="rc">
				<c:forEach var="answer" items="${answer_list}">
					<section id="qnaViewAnswer" class="qnabox">
						<div class="ico"><img src="/naksinuri_original/common_main/img/ico_qna_a.png" alt="" /></div>
						<div class="tit">
							<h2>${answer.nuri_a_subject }</h2>
							<span class="name"><i class="fa fa-user" aria-hidden="true"></i> ${answer.nuri_a_writer}</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i>${answer.nuri_a_date}</span>
						</div>
						<div class="comment">
							${answer.nuri_a_content}
						</div>
					</section>
				</c:forEach>
				<c:if test="${answer_list eq null }">
					<section id="qnaViewWait" class="qnabox">
						<div class="icos"><img src="/naksinuri_original/common_main/img/ico_qna_wait.png" alt="" /></div>
						<div class="tit">
							<h2>현재 답변을 등록중입니다.<br />조금만 기다려주세요.</h2>
						</div>
					</section>
				</c:if>
			</div>
		</div>

		<div id="btnArea">
			<ul class="floats">
				<li>
					<a href="#" onclick="prev(${prev.nuri_q_num})" class="btn_prev btn_white" title="이전글"><i class="fa fa-angle-left" aria-hidden="true" title="이전글"></i> 이전글</a>
					<a href="#" onclick="next(${next.nuri_q_num})" class="btn_next btn_white" title="다음글">다음글 <i class="fa fa-angle-right" aria-hidden="true" title="다음글"></i></a>
					
				</li>
				<li class="fr">
					<a href="/share/zisik/write.do" class="btn_request btn_blue" title="질문하기 이동" >질문하기</a>
					<a href="/share/zisik/list.do" class="btn_list btn_gray" title="목록가기" >목록</a>
				</li>
			</ul>
		</div>
	</div>
<script type="text/javascript">
	function view2(nuri_q_num){
		var form = document.getElementById('listform');
		$('#nuri_q_num').val(nuri_q_num);
		
		form.action="./view.do";
		form.submit();
	};
	function next(nuri_q_num){
		if(nuri_q_num==null){
			alert('다음글이 없습니다');
			return false;
				
			}
		
		var form = document.getElementById('listform');
		$('#nuri_q_num').val(nuri_q_num);
		
		form.action="./view.do";
		form.submit();
	};
	function prev(nuri_q_num){
		if(nuri_q_num==null){
		alert('이전글이 없습니다');
		return false;
		}
		var form = document.getElementById('listform');
		$('#nuri_q_num').val(nuri_q_num);
		
		form.action="./view.do";
		form.submit();
	};
	
	function update(nuri_q_num){
		var form = document.getElementById('listform');
		$('#nuri_q_num').val(nuri_q_num);
		
		form.action="./modify.do";
		form.submit();
	}


</script>

<%@include file="../layout/tail.jsp"%>
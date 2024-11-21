<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="info" />
<c:set var="pageName" value="angling" />

<%@include file="../layout/head.jsp"%>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="mid" id="mid"/>

	
	</form:form>
	

	
	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
				<div class="viewTit">
					<h1 class="tit">${info.title}</h1>
					<div class="name_date"><span class="name">${info.writer_name}</span><span class="date">${fn:substring(info.regdate,0,10)}</span></div>
					<ul class="eyes_heart floats">
						<li><em><i class="fa fa-eye" aria-hidden="true"></i></em> ${info.views}</li>
						<li><em>${info.category }</em></li>		
					</ul><br>

				</div>
				<div class="content">
					${info.body}	
						<div class="writer_box">
							<div class="pic">	
								<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_writer.png">					
							</div>
							<div class="name"><span>작성자</span> ${info.writer_name}</div>
							<div class="warning">
								본 콘텐츠의 저작권은 저자 또는 제공처에 있음을 알려드립니다.<br />
								또한 무단으로 콘텐츠를 이용하는 경우 저작권법 등에 따라 법적 책임을 질 수 있으니 유의하시기 바랍니다.
							</div>
						</div>
						    <%@include file="../layout/angling_printmenu.jsp"%>
				</div>

				<div id="btnArea">
					<ul class="floats">
					
						<li>
							<a href="#" onclick="prev(${prev.mid})" class="btn_prev btn_white" title="이전글"><i class="fa fa-angle-left" aria-hidden="true" title="이전글"></i> 이전글</a>
							<a href="#" onclick="next(${next.mid})" class="btn_next btn_white" title="다음글">다음글 <i class="fa fa-angle-right" aria-hidden="true" title="다음글"></i></a>
							
						</li>
						<li class="fr">
							<a href="./list.do" class="btn_list btn_gray" title="목록가기">목록</a>
						</li>
					</ul>
				</div>
			</section>
			
			
			<section id="viewLatest" class="board_list">
				<table class="list_tbl">
					<colgroup>
						<col width="80" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>No</th>
							<th>제목</th>
						</tr>
					</thead>
					<tbody>			
						<c:forEach var="item" items="${bobo_list}" begin="0" end="10" >
							<tr class="now_view">
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left">
									<a href="#;" onclick="view2('${item.mid}')" title="${item.evn_subject} 상세보기">
										<c:choose>
											<c:when test="${fn:length(item.title)>50 }">
												<em class="list_subject">${fn:substring(item.title, 0, 50)}...</em>
											</c:when>
											<c:otherwise>
												<em class="list_subject">${item.title}</em>
											</c:otherwise>
										</c:choose> 
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</article>
	</div>
	

<%@include file="../layout/tail.jsp"%>
<script type="text/javascript">


	function view2(mid){
		var form = document.getElementById('listform');
		$('#mid').val(mid);
		
		form.action="./view.do";
		form.submit();
	};
	function next(mid){
		if(mid==null){
			alert('다음글이 없습니다');
			return false;
				
			}
		
		var form = document.getElementById('listform');
		$('#mid').val(mid);
		
		form.action="./view.do";
		form.submit();
	};
	function prev(mid){
		if(mid==null){
		alert('이전글이 없습니다');
		return false;
			
		}
		
		var form = document.getElementById('listform');
		$('#mid').val(mid);
		
		form.action="./view.do";
		form.submit();
	};
	
	


</script>


<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<%-- 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>
<script>
location.href = '/index.do';
</script>
<%-- End of 메뉴 숨김처리에 의한 접근 차단 요청 2023.04.11 --%>

<c:set var="pageMode" value="conference" />
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="congress" />

<%@include file="../layout/head.jsp"%>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn"/>
		<input type="hidden" name="bo_writer" id="bo_writer" value="${info.bo_name}"/>
		<!-- 
		<input type="hidden" name="bo_subject" id="bo_subject" value="${info.bo_subject}"/>
		<input type="hidden" name="bo_writer" id="bo_writer" value="${info.bo_name}"/>
		<input type="hidden" name="organizer" id="organizer" value="${info.organizer}"/>
		<input type="hidden" name="host" id="host" value="${info.host}"/>
		<input type="hidden" name="start_date" id="start_date" value="${info.bo_start_dt}"/>
		<input type="hidden" name="end_date" id="end_date" value="${info.bo_end_dt}"/>
		<input type="hidden" name="cnt" id="cnt" value="${info.entry_cnt}"/>
		<input type="hidden" name="bo_phone" id="bo_phone" value="${info.bo_phone}"/>
		<input type="hidden" name="cg_account" id="cg_account" value="${info.cg_account}"/>
		<input type="hidden" name="cg_account_name" id="cg_account_name" value="${info.cg_account_name}"/>
		 -->
	</form:form>
	
	<form:form commandName="replyform" id="replyform" method="post" enctype="multipart/form-data">
	<input type="hidden" name="co_sn" id="co_sn"/>
	</form:form>
	
	<jsp:useBean id="now" class="java.util.Date" />	 
	<fmt:formatDate value="${now}" pattern="yyyyMMddHHmmss" var="nowDate1" /> 
	 <c:set var = "bostart" value = "${fn:replace(info.bo_start_dt, '-', '')}000000" />
	 <c:set var = "boend" value = "${fn:replace(info.bo_end_dt, '-', '')}235959" />
	 
	 <c:choose>
		<c:when test="${info.entry_start_dt ne null }">
			<c:set var = "entrystart" value = "${fn:replace(info.entry_start_dt, '-', '')}000000" />
			<c:set var = "entryend" value = "${fn:replace(info.entry_end_dt, '-', '')}235959" />
		</c:when>
		<c:otherwise>
			<c:set var = "entrystart" value = "20180101000000" />
			<c:set var = "entryend" value = "20180101235959" />
		</c:otherwise>
	</c:choose>
	 
	 
	 
	
	<fmt:parseDate value="${nowDate1}" var="nowDate" pattern="yyyyMMddHHmmss"/>
	<fmt:parseDate value="${bostart}" var="bo_start" pattern="yyyyMMddHHmmss"/>
	<fmt:parseDate value="${boend}" var="bo_end" pattern="yyyyMMddHHmmss"/>
	<fmt:parseDate value="${entrystart}" var="entry_start" pattern="yyyyMMddHHmmss"/>
	<fmt:parseDate value="${entryend}" var="entry_end" pattern="yyyyMMddHHmmss"/>
	
	
	<fmt:parseNumber value="${bo_start.time / (1000*60*60*24)}" integerOnly="true" var="calcStrDate"></fmt:parseNumber>
	<fmt:parseNumber value="${bo_end.time / (1000*60*60*24)}" integerOnly="true" var="calcEndDate"></fmt:parseNumber>
	<fmt:parseNumber value="${entry_start.time / (1000*60*60*24)}" integerOnly="true" var="calcEntStrDate"></fmt:parseNumber>
	<fmt:parseNumber value="${entry_end.time / (1000*60*60*24)}" integerOnly="true" var="calcEntEndDate"></fmt:parseNumber>
	
		

	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
			   <div id="printthis">
					<div class="viewTit">
						<h1 class="tit"><c:out value="${info.bo_subject}"/></h1>
						<c:choose>
							<c:when test="${info.bo_end_dt ne null }">
								<div class="name_date"><span class="name">기간</span><span class="date">${info.bo_start_dt} ~ ${info.bo_end_dt}</span></div>
							</c:when>
							<c:when test="${(info.bo_start_dt eq null) and (info.bo_end_dt eq null) }">
								<div class="name_date"></div>
							</c:when>
							<c:otherwise>
								<div class="name_date"><span class="name">일시</span><span class="date">${info.bo_start_dt}</span></div>
							</c:otherwise>
						</c:choose>
						<div class="name_date"><span class="name">주최</span><span class="date"><c:out value="${info.organizer}"/></span></div>
						
						<ul class="eyes_heart floats">
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${info.bo_view}</li>
							<li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em> ${info.bo_like }</a></li>
							<!-- 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> -->
						</ul><br>
						<c:if test="${not empty info.bo_atch_file}">
							첨부파일 목록 : 					
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
					                    <c:param name="param_atchFileId" value="${info.bo_atch_file}" />
					                </c:import>
		                </c:if>
					</div>
					<!-- <div style="width:100%;height:300px;display:inline-block;float:left;margin:5px;">
						<div style="width:50%;height:100%;">
							<c:choose>
								<c:when test="${info.bo_main_img ne null }">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'
										width="100%" height="100%" />
								</c:when>
								<c:otherwise>
									<img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="no_image"/>' />
								</c:otherwise>
							</c:choose>
						</div>
						<div style="width:50%;height:100%;">
							
						</div>
					</div>
					-->
				<c:if test="${info.is_entry_y == '1'}">
				<div class="wrp_step">	
					<ul>
						<li class="<c:if test="${entry_start > nowDate}">on</c:if>">1. 접수예정</li>
						<span class="arrow"></span>
						<li class="<c:if test="${entry_start < nowDate && entry_end > nowDate}">on</c:if>">2. 접수중</li>
						<span class="arrow"></span>
						<li class="<c:if test="${entry_end < nowDate && nowDate < bo_end}">on</c:if>">3. 신청마감</li>
						<span class="arrow"></span>
						<li class="<c:if test="${bo_end < nowDate}">on</c:if>">4. 대회종료</li>
					</ul>
				</div>
				</c:if>
				<div class="left_contents" style="top: 0px; bottom: auto; left: 0px; right: auto;">
					<div class="header_coninfo">
						<div class="con_box">
							<div class="poster">
								<c:choose>
								<c:when test="${(info.bo_main_img ne null) and not empty fn:trim(info.bo_main_img) }">
									<div class="thumbs crop imgLiquid_bgSize imgLiquid_ready" style="background-image: url('<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'); background-size: cover; background-position: center center; background-repeat: no-repeat;">
									<img src="<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>" alt="" style="display: none;">
									<button type="button" onclick="open_porster('<c:url value='/show/image.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>')" title="크게보기" class="openpop focustop"><span class="sp_btn">크게보기</span></button>
								</c:when>
								<c:otherwise>
									<div class="thumbs crop imgLiquid_bgSize imgLiquid_ready" style="background-image: url('/naksinuri_original/common_main/img/noImage_big.png'); background-size: cover; background-position: center center; background-repeat: no-repeat;">
									<img src="/naksinuri_original/common_main/img/noImage_big.png" alt="" style="display: none;">
								</c:otherwise>
								</c:choose>
							</div>
						</div>
					
							<div class="info">
							
								<c:if test="${entry_start < nowDate && entry_end > nowDate}">
									<div id="btnArea" class="noupline mgb30 pdt0" >
							 			<a href="#;" class="btn_blue" onclick="write_form(${info.bo_sn})" title="참가신청 이동"><i class="fa fa-user-plus" aria-hidden="true"></i> 참가신청</a>
							 			<a href="/sosig/congress/mbrcheck.do" class="btn_gray" title="접수내역확인 이동"><i class="fa fa-user-plus" aria-hidden="true"></i> 접수내역확인</a>
							 		</div>
								</c:if>
							
								<div class="wrp_lst">
									<table class="lst_board type8">
										<caption>낚시대회 정보 확인 테이블입니다.</caption>
										<colgroup>
											<col style="width:100px">
											<col>
										</colgroup>
										<tbody>
											<tr>
												<th scope="col">주최</th>
												<td><c:out value="${info.organizer}"/></td>
											</tr>
											<tr>
												<th scope="col">주관</th>
												<td><c:out value="${info.host}"/></td>
											</tr>
											<%-- 
												//2018.09.20 본서버를 위한 적용  
												- 제7회 해양수산부 어린이 낚시안전 체험교실
												- 제9회 해양수산부장관배 전국민물낚시대회
												두건만 전용으로 처리된 부분.. 추후 개선 방안을 강구해야함.
											--%>
											<c:choose>
												<c:when test="${ (info.bo_sn eq '1056') or (info.bo_sn eq '1057') }">
													<tr>
														<th scope="col" style="background:#ffe4e4;">문의전화</th>
														<td style="color:#0054FF;">031-564-0733</td>
													</tr>
												</c:when>
												<c:otherwise>
													<tr>
														<th scope="col">담당자</th>
														<td><c:out value="${info.bo_name}"/><%--(${info.bo_phone}) --%></td>
													</tr>
												</c:otherwise>
											</c:choose>
											<tr>
												<th scope="col">행사일</th>
												<td><fmt:formatDate value="${bo_start}" pattern="yyyy-MM-dd (E)"/> ~ <fmt:formatDate value="${bo_end}" pattern="yyyy-MM-dd (E)"/> (총 ${calcEndDate-calcStrDate}일)</td>
											</tr>
											<c:if test="${info.is_entry_y == '1'}">
											<tr>
												<th scope="col">접수기간</th>
												<td><fmt:formatDate value="${entry_start}" pattern="yyyy-MM-dd (E)"/> ~ <fmt:formatDate value="${entry_end}" pattern="yyyy-MM-dd (E)"/> (총 ${calcEntEndDate-calcEntStrDate}일 )</td>
											</tr>
											<tr>
												<th scope="col">모집인원</th>
												<td><c:out value="${info.entry_cnt}"/> 명<%--(${info.cnt}명 신청) --%> </td>
											</tr>
											<tr>
												<th scope="col">참가비</th>
												<td><c:out value="${info.entry_cash}"/></td>
											</tr>
											<c:if test="${ (info.bo_sn eq '1165') or (info.bo_sn eq '1172') }">
												<tr>
													<th scope="col">입금계좌</th>
													<td style="color:#0054FF;"><span style="font-size:16px;">국민은행 008-25-0014-047</span> (예금주 : 사)한국낚시협회)</td>
												</tr>
											</c:if>
											<tr>
												<th scope="col">유의사항</th>
												<td><c:out value="${info.entry_notice}"/></td>
											</tr>
											<c:if test="${ (info.bo_sn eq '1165') or (info.bo_sn eq '1172') }">
												<tr>
													<th scope="col">문의사항</th>
													<td style="color:#0054FF;"><span style="font-size:16px;">031-564-0733</span> ((사)한국낚시연합))</td>
												</tr>
											</c:if>
											</c:if>
										</tbody>
									</table>
								</div>
								
							</div>
						</div>
					</div>
				</div>
				<div class="content">
					<%--
					<c:if test="${info.is_entry_y != '1'}">
						<c:choose>
							<c:when test="${info.bo_main_img ne null }">
							<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${item.file_sn }"/>'
									width="100%" height="100%" />
							</c:when>
							<c:otherwise>
								<img src="/naksinuri_original/common_main/img/noImage_big.png" alt='<c:out value="no_image"/>' />
							</c:otherwise>
						</c:choose>
					</c:if>
					 --%>
						${info.bo_content}	
						
						<c:if test="${entry_start < nowDate && entry_end > nowDate}">
							<div id="btnArea" class="noupline mgb30 pdt0" >
								<a href="#;" class="btn_blue" onclick="write_form(${info.bo_sn})" title="참가신청 이동"><i class="fa fa-user-plus" aria-hidden="true" ></i> 참가신청</a>
							</div>
						</c:if>	
						
						<!-- //참가자// -->	
						<%@include file="../layout/printmenu.jsp"%>
									
				</div>
				
				
			</div>
				<div class="reply_box">
				</div>
				
				<div id="btnArea">
					<ul class="floats">
						<li>
							<a href="#" onclick="prev(${prev.bo_sn})" class="btn_prev btn_white" title="이전글"><i class="fa fa-angle-left" aria-hidden="true" title="이전글"></i> 이전글</a>
							<a href="#" onclick="next(${next.bo_sn})" class="btn_next btn_white" title="다음글">다음글 <i class="fa fa-angle-right" aria-hidden="true" title="다음글"></i></a>
							
						</li>
						<li class="fr">
							<a href="./list.do" class="btn_list btn_gray" title="목록가기" >목록</a>
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
						<c:forEach var="item2" items="${noticlist}">
							<tr>
								<td><span class="notice">공지</span></td>
								<td class="subject align_left">
									<a href="#;" onclick="view2('${item2.bo_sn}')"class="pic" title="${item2.bo_subject} 상세보기">
										<c:choose>
											<c:when test="${fn:length(item2.bo_subject)>50 }">
												<em class="list_subject">${fn:substring(item2.bo_subject, 0, 50)}...</em>
											</c:when>
											<c:otherwise>
												<em class="list_subject">${item2.bo_subject}</em>
											</c:otherwise>
										</c:choose>										
										<c:if test="${item.bo_start_dt ne null }">
											<span class="date">${item.bo_start_dt} ~ ${item.bo_end_dt}</span>
										</c:if>										
									</a>
								</td>
							</tr>
						</c:forEach>
						<c:forEach var="item" items="${bobo_list}" begin="0" end="${9-fn:length(noticlist)}" >
							<tr class="now_view">
								<td>${select_total-item.rn+1}</td>
								<td class="subject align_left">
									<a href="#;" onclick="view2('${item.bo_sn}')" title="${item.bo_subject} 상세보기">
										<c:choose>
											<c:when test="${fn:length(item.bo_subject)>50 }">
												<em class="list_subject">${fn:substring(item.bo_subject, 0, 50)}...</em>
											</c:when>
											<c:otherwise>
												<em class="list_subject">${item.bo_subject}</em>
											</c:otherwise>
										</c:choose>
										<c:if test="${item.bo_start_dt ne null }">
											<span class="date">${item.bo_start_dt} ~ ${item.bo_end_dt}</span>
										</c:if>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
			
		</article>
	</div>

<script>
	// 게시판 리스트 or 갤러리 토글버튼
	$('.change_type.list').click(function(){
		$(this).addClass("on");
		$('.change_type.gallery').removeClass("on");
		$('.board_list').removeClass("off");
		$('.gallery_list').addClass("off");
	});

	$('.change_type.gallery').click(function(){
		$(this).addClass("on");
		$('.change_type.list').removeClass("on");
		$('.board_list').addClass("off");
		$('.gallery_list').removeClass("off");
	});
	
	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};
	
	function write_form(bo_sn){
		
		if(!bo_sn){
			alert("게시글을 찾을 수 없습니다.");
			return false;
		}
		
		
		
		var form = document.getElementById("listform");
		$('#bo_sn').val(bo_sn);
		form.action="./mbrwrite.do";
		form.submit();
		
		
	
	}
	function next(bo_sn){
		if(bo_sn==null){
			alert('다음글이 없습니다');
			return false;
				
			}
		
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};
	function prev(bo_sn){
		if(bo_sn==null){
		alert('이전글이 없습니다');
		return false;
			
		}
		
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};

	function co_del(co_sn){
		var form = document.getElementById('replyform');
		$('#co_sn').val(co_sn);
		
		form.action = "./co_delete.do";
		form.submit();
	}
	
	
	function open_porster(url) {
		window.open(url);
	}
</script>

<%@include file="../layout/tail.jsp"%>
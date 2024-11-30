<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info"/>
<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />

<%@include file="../layout/newHead.jsp"%>

	<script src="/naksinuri_original/common_main/js/jquery.sticky.js"></script>

	<script>
	var youtube_link = '${info.bo_youtubelink}';
		$(document).ready(function(){
			
			//$('#youtubelink_div').html('<iframe width="400px" height="240px" src="'+youtube_link+'" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>');
			
			/* 낚시산업정보 상세페이지 기본정보 스크롤 (Feat.sticky-kit) */
			/* $("#boardView #viewLatest").stick_in_parent()
			.on("sticky_kit:stick", function(e) {
				$('#viewLatest').addClass("scrollOn");
			})
			.on("sticky_kit:unstick", function(e) {
				$('#viewLatest').removeClass("scrollOn");
			}); */
		});
	</script>
	
<style>
/* 웹 접근성 글자색 명도대비 수정사항 */
	.modal-content input::placeholder {
	    color: #636363;
	}
    .reply_write input::placeholder {
	    color: #636363;
	}
	.reply_textbox textarea::placeholder {
    color: #636363;
	}
</style>

	<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
		<input type="hidden" name="bo_sn" id="bo_sn"/>
		<input type="hidden" name="co_sn" id="co_sn"/>
		<input type="hidden" id="spbo_sn" value="${info.bo_sn}" />
		<input type="hidden" name="co_pass4" id="co_pass4" />
		<input type="hidden" name="bo_pass" id="bo_pass" />
		
	</form:form>
	
	
	
	<div id="conferenceView" class="content respon2">
		<article id="boardView" class="floats">
			<section id="viewContent">
				<div  id="printthis">
					<div class="viewTit">
						<h3 class="tit">${info.bo_subject}</h3>
						<div class="name_date"><span class="name">
							<c:choose>
								<c:when test="${depthName eq 'share' and ( pageName eq 'usage' or pageName eq 'travel' or pageName eq 'column' )}">
									<c:choose>
										<c:when test="${fn:length(info.bo_name) < 2}">
											*${info.bo_name}*
										</c:when>
										<c:when test="${fn:length(info.bo_name) < 3}">
											${fn:substring(info.bo_name,0,1)}*
										</c:when>
										<c:otherwise>
											${fn:substring(info.bo_name,0,1)}<c:forEach begin="2" end="${fn:length(info.bo_name)-1}" step="1">*</c:forEach>${fn:substring(info.bo_name,fn:length(info.bo_name)-1,fn:length(info.bo_name))}
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									${info.bo_name}
								</c:otherwise>
							</c:choose></span>
							<c:choose>
								<c:when test="${depthName eq 'share' and ( pageName eq 'column' )
											 or depthName eq 'lesson' and ( pageName eq 'junior' or pageName eq 'gosu' or pageName eq 'sense' or pageName eq 'binding' or pageName eq 'class' )
											 or depthName eq 'promotion' and ( pageName eq 'policy')}">
									<!-- 김현태 추가 -->
								</c:when>
								<c:otherwise>
									<span class="date">${fn:substring(info.bo_insert_dt,0,10)}</span>
								</c:otherwise>
							</c:choose>
							<!-- <span class="date">${fn:substring(info.bo_insert_dt,0,10)}</span> --></div>
						<c:if test="${pageName eq 'campaign'}">
							<c:if test="${info.bo_start_dt ne null }">기간 : ${info.bo_start_dt }</c:if><c:if test="${info.bo_end_dt ne null }"> ~ ${info.bo_end_dt }</c:if>
						</c:if>
						<c:if test="${pageName eq 'gosi' }">
							<span>${info.bo_sido } ${info.bo_gugun }</span>
						</c:if>
						<ul class="eyes_heart floats eyes_heart_link" >
							<li><em><i class="fa fa-eye" aria-hidden="true" title="조회수"></i></em><span class="blind">조회수</span> ${info.bo_view}</li>
							<li><a href="#;" onclick="like_up(${info.bo_sn})"><em><i class="fa fa-heart" aria-hidden="true" title="좋아요 수"></i></em><span class="blind">좋아요 수</span> ${info.bo_like }</a></li>
							<!-- 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> -->
							
<%-- 							<c:if test="${depthName eq 'promotion' and pageName eq 'info'}"> --%>
							<li>
								<a href="#;" class="tooltipCustom btnSharelink" title="게시물 공유 URL주소"
								data-pl1="${depthName}"
								data-pl2="${pageName}"
								data-pl3="view"
								data-pl4=""
								data-sid="${info.bo_sn}">
									<i class="fa fa-link"></i>
								</a>&nbsp;<span id="sharelinkurlprint" class="red-600"></span>	
							</li>
<%-- 							</c:if> --%>
						</ul><br>
						<c:if test="${column ne 'share' and table ne 'travel' and not empty info.bo_atch_file}">
							첨부파일 목록 : 					
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${info.bo_atch_file}" />
			                </c:import>
			                <c:if test="${ (movfile.orignl_file_nm ne null) and (not empty fn:trim(movfile.orignl_file_nm)) }">
			                <c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${movfile.bo_mov_file}" />
			                </c:import>
			                </c:if>
		                </c:if>
					</div>
						
					
					<div class="content">					
										
					  	<!-- 동영상 영역 -->
					  	<c:if test="${ (movfile.orignl_file_nm ne null) and (not empty fn:trim(movfile.orignl_file_nm)) }">
					  	<%-- <div align="center">
							<div id="myElement" style="margin:auto;" ></div>
						       
						        <script type="text/javascript">
						        	var filen = $('#filen').val();
						            jwplayer("myElement").setup({
						            	//"file": "/movupload/${movfile.stre_file_nm}",
						            	"playlist" : [{
						            		//"file" : "/movupload/${movfile.stre_file_nm}"
						            		"file" : "/cmwebfiles/${movfile.orignl_file_nm}"
						            	}],
						                "width":"800",
						                "height":"450",
						                "align":"center",
						                "autostart": true,
						                "mute": false
						            });							
						        </script>							        
						</div> --%>
										              
							
	  						<c:import url="/cmm/fms/selectFilePrmov.do" >
			    				<c:param name="atchFileId" value="${movfile.bo_mov_file}" />
			    			</c:import>
						
						</c:if>					
					    <!-- //동영상 영역 -->
					    
					    <!-- //PDF슬라이드 영역:start // -->
					    <%--
					    <style>
					    #pdfimgslider .outarrow span a {
					        display: block;
						    width: 60px;
						    height: 70px;
						    line-height: 68px;
						    background-color: rgba(0,0,0,0.5);
						    color: #fff;
						    font-size: 50px;
						    text-align: center;
					    }
					    #pdfimgslider .outarrow span {/*position:absolute;top:240px;*/}
					    #pdfimgslider .outarrow span#slider-prev{left:1px;}
					    #pdfimgslider .outarrow span#slider-next{right:1px;}
					    </style>
					    <c:if test="${not empty pdffilelist}">
					    <div class="fish_slide" id="pdfimgslider">
							<ul>
								<c:forEach var="pdfimgfile" items= "${pdffilelist}" varStatus="status">
									<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${pdfimgfile.bo_pdf_img_file}"/>&fileSn=<c:out value="${pdfimgfile.file_sn}"/>' style="margin-left:auto; margin-right: auto;"  height="510" alt=<c:out value="${pdfimgfile.orignl_file_nm}" /> />
								</c:forEach>
							</ul>
							<div class="outarrow">
								<p><span id="slider-next"></span><span id="slider-prev"></span></p>
							</div>
							<script>
								$('#pdfimgslider ul').bxSlider({
									auto: false,
									nextSelector: '#slider-next',
									prevSelector: '#slider-prev',
									nextText: '<i class="fa fa-angle-right"></i>',
									prevText: '<i class="fa fa-angle-left"></i>',
									pause: 5000
								});
							</script>
						</div>						
					    </c:if>	
					     --%>				    
					    <!-- //PDF슬라이드 영역:end // -->
					    <c:if test="${not empty info.bo_youtubelink}">
					    <div class="youtube-embed-wrapper" style="position:relative;padding-bottom:56.25%;padding-top:30px;height:0;overflow:hidden" id="youtubelink_div">
					    <iframe width="600px" height="480px" src="${info.bo_youtubelink}" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
					    
					 </div>
					     </c:if>	
					    				    
						${fn:replace(info.bo_content,"&quot;","")}
						
						<c:if test="${depthName eq 'share' and pageName eq 'usage'}">
							<br>
							<%-- <c:if test="${not empty info.bo_main_img}">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' alt="${info.bo_name}" />
							</c:if> --%>
							<c:if test="${not empty filelist}">
								<c:forEach var="imgf" items= "${filelist}" varStatus="status">
									<br><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${imgf.bo_atch_file}"/>&fileSn=<c:out value="${imgf.file_sn}"/>' alt=<c:out value="${imgf.orignl_file_nm}" /> />
								</c:forEach>
							</c:if>
					    </c:if>	
						
						<c:if test="${column eq 'share' and table eq 'travel' and not empty filelist}">
			                <c:forEach var="imgf" items= "${filelist}" varStatus="status">
			                	${info.bo_subject}
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${imgf.bo_atch_file}"/>&fileSn=<c:out value="${imgf.file_sn}"/>' alt=<c:out value="${info.bo_subject}" /> />
							</c:forEach>			              
		                </c:if>
		                
		                
						<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') and(pageName ne 'campaign') and (pageName ne 'info')}">				
							<div class="writer_box">
								<div class="pic">
									<c:choose>
										<c:when test="${empty info.bo_sub_img}">
											<img alt="낚시누리 Naksi Nuri" src="/naksinuri_original/common_main/img/noImage_writer.png">
										</c:when>
										<c:when test="${info.bo_sub_img ne null}">
											<img alt="낚시누리 Naksi Nuri" src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_sub_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' width="100%" height="100%" />
										</c:when>
										<c:when test="${!empty info.bo_sub_img}">
											<img alt="낚시누리 Naksi Nuri" src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_sub_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' width="100%" height="100%" />
										</c:when>										
										<c:otherwise>
											<img alt="낚시누리 Naksi Nuri" src="/naksinuri_original/common_main/img/noImage_writer.png">
										</c:otherwise>
									</c:choose>
								</div>
								<div class="name"><span>글/사진</span> 
									<c:choose>
										<c:when test="${depthName eq 'share' and ( pageName eq 'usage' or pageName eq 'travel' or pageName eq 'column' )}">
											<c:choose>
												<c:when test="${fn:length(info.bo_name) < 2}">
													*${info.bo_name}*
												</c:when>
												<c:when test="${fn:length(info.bo_name) < 3}">
													${fn:substring(info.bo_name,0,1)}*
												</c:when>
												<c:otherwise>
													${fn:substring(info.bo_name,0,1)}<c:forEach begin="2" end="${fn:length(info.bo_name)-1}" step="1">*</c:forEach>${fn:substring(info.bo_name,fn:length(info.bo_name)-1,fn:length(info.bo_name))}
												</c:otherwise>
											</c:choose>
										</c:when>
										<c:otherwise>
											${info.bo_name}
										</c:otherwise>
									</c:choose>
								</div>
								<p>${info.bo_info}</p>
								<div class="warning">
									본 콘텐츠의 저작권은 저자 또는 제공처에 있음을 알려드립니다.<br />
									또한 무단으로 콘텐츠를 이용하는 경우 저작권법 등에 따라 법적 책임을 질 수 있으니 유의하시기 바랍니다.
								</div>
							</div>
						</c:if> 
						<%@include file="../layout/printmenu.jsp"%>
					</div>
				</div>
				<div class="reply_box">
					<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') and (pageName ne 'info')}">	
						<form method="post" name="replform" id="replform" onsubmit="return replsubmit();">
						<!--<form method="post" name="replform" id="replform" onsubmit="return replsubmit();">-->
							<input type="hidden" name="bo_sn" value="${info.bo_sn}"/>
							<div class="reply_write">
								<div class="name_pw">
									<input type="text" class="reply_input" name="co_name" id="co_name" placeholder="이름" title="이름"/><input type="password" id="co_pass" name="co_pass" class="reply_input" placeholder="비밀번호" title="비밀번호"/>
								</div>
								<div class="reply_textbox">
									<textarea class="reply_textarea" name="co_comment" id="co_comment" placeholder="댓글을 입력해주세요" title="댓글내용입력"></textarea>
									<!-- <button class="submitBtn" onclick="replsubmit()">댓글등록</button>  -->
									<input type="submit" class="submitBtn" value="댓글등록" title="댓글등록"/>
								</div>
							</div>
						</form>
						<div class="reply_list">
						<c:forEach var="item" items="${comment_list}">
							<div class="reply_con">
								<span class="name">${item.co_name}</span>
								<span class="date">${fn:substring(item.co_insert_dt,0,20)}</span>
								<a href="#;" data-toggle="modal" data-target="#changePassword" data-rev="${item.co_sn}" class="replyBtn">[삭제]</a>
								<input type="hidden" id="co_sn4" value="${item.co_sn }"/>
								<p>${item.co_comment}</p>
							</div>
						</c:forEach>
						</div>
					</c:if>
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
			<c:choose>
				<c:when test="${(pageName eq 'notice') or (pageName eq 'policy') or (pageName eq 'info') }">
					<section id="viewLatest" class="board_list">
						<table class="list_tbl">
							<c:if test="${pageName eq 'notice'}"><caption>공지사항 리스트</caption></c:if>
							<c:if test="${pageName eq 'policy'}"><caption>낚시정책 리스트</caption></c:if>
							<c:if test="${pageName eq 'info'}"><caption>알림마당 리스트</caption></c:if>
							
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
											</a>
										</td>
									</tr>
								</c:forEach>
								<%--
									<c:forEach var="item" items="${bobo_list}" begin="0" end="${9-fn:length(noticlist)}" > 
									9- 부분이 무언가 이상함.
								--%>
								<c:forEach var="item" items="${bobo_list}" begin="0" end="${fn:length(noticlist)}" > 
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
											</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</section>
				</c:when>
				<c:when test="${pageName eq 'news'}">
					<section id="viewLatest">
				
					</section>
				</c:when>
				
				<c:otherwise>
					<section id="viewLatest" class="gallery_list">
						<ul>
					<!-- 공지사항에서 자꾸 얘때문에 페이지가 깨짐 -->
							<c:forEach var="item" items="${post_list}">
								<li>
									<c:choose>
										<c:when test="${info.bo_main_img eq '' }">
											<%-- <a href="#;" onclick="view2('${item.bo_sn}')"class="pic" title="${item.bo_subject} 상세보기"><img src='/naksinuri_original/common_main/img/noImage_gen.jpg'  width="303" height="250"  alt="이미지가 없습니다"/></a> --%>
											<a href="#;" onclick="view2('${item.bo_sn}')"class="pic" title="${item.bo_subject} 상세보기"><img src='/naksinuri_original/common_main/img/noImage_writer.png'  width="303" height="250"  alt="낚시누리 Naksi Nuri"/></a>
										</c:when>
										<c:otherwise>
											<a href="#;" onclick="view2('${item.bo_sn}')"class="pic" title="${item.bo_subject} 상세보기"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>'  width="303" height="250"  alt="${item.bo_subject}"/></a>
										</c:otherwise>
									</c:choose>
									<a href="#;" onclick="view2('${item.bo_sn}')" class="subject" tabindex="-1">
										<c:choose>
											<c:when test="${fn:length(item.bo_subject)>50 }">
												<em class="list_subject">${fn:substring(item.bo_subject, 0, 50)}...</em>
											</c:when>
											<c:otherwise>
												<em class="list_subject">${item.bo_subject}</em>
											</c:otherwise>
										</c:choose> 
										<%-- <span class="date">${fn:substring(item.bo_insert_dt,0,10)}</span> 김현태 주석 처리--%>
									</a>
									<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square" tabindex="-1"><i class="fa fa-search" aria-hidden="true" title="${item.bo_subject} 상세보기"></i></a>
								</li>
							</c:forEach>
						</ul>
					</section>
				</c:otherwise>
			</c:choose>
			
		</article>
	</div>
	
			
	<div class="password_check modal fade" id="updateCheckPassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<p class="comment">게시글을 수정합니다. 비밀번호를 입력해주세요.</p>
				<input type="password" class="del_input" id="udt_bo_pass" title="비밀번호 입력" placeholder="비밀번호를 입력해주세요." />
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
	
	
	<div class="password_check modal fade" id="changePassword" tabindex="0" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<p class="comment">댓글을 삭제합니다. 비밀번호를 입력해주세요.</p>
				<input style="font-color:black" type="password" class="del_input" tabindex="0" id="co_passr" title="비밀번호 입력" placeholder="비밀번호를 입력해주세요." />
				<div class="btn_box">
					<button class="cp_btn del_btn" tabindex="0" onclick="co_del()" >확인</button>
					<a href="#;" class="cp_btn remote_btn" tabindex="0" data-dismiss="modal" aria-label="Close">취소</a>
				</div>
			</div>
		</div>
	</div>
	
	
<%@include file="../layout/tail.jsp"%>



<script type="text/javascript">

	$('#changePassword').on('show.bs.modal',function(e){
	var aa = $(e.relatedTarget).data('rev');
	$('#co_sn').val(aa);
	})
	
	function view2(bo_sn){
		var form = document.getElementById('listform');
		$('#bo_sn').val(bo_sn);
		
		form.action="./view.do";
		form.submit();
	};
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
		}else if(co_comment==''){
			alert("댓글내용을 입력하세요.");
			return false;
		}else if( !/^[a-zA-Z0-9]{10,}$/.test(co_pass) || co_pass.search(/[0-9]/g)<0 || co_pass.search(/[a-z]/ig)<0 ){
			alert("비밀번호는 숫자와 영문자 조합으로 10자리 이상 사용해야 합니다.");
			return false;
		}else{
			form.action="./view.do";
			form.submit();
		}		
		
	}
		
	$('.btnSharelink').click(function() {
		var sid = $(this).attr('data-sid');
		var pl1 = $(this).attr('data-pl1');
		var pl2 = $(this).attr('data-pl2');
		var pl3 = $(this).attr('data-pl3');
		var pl4 = $(this).attr('data-pl4');
		$.ajax({
			type:"POST",
			url :"/share/link/copy.do",
			data:{
				LINK_PL1:pl1,
				LINK_PL2:pl2,
				LINK_PL3:pl3,
				LINK_PL4:pl4,
				LINK_SID:sid,
			},
			dataType: "json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {	
				if(data.error == '1') {
					alert(data.msg);
				} else {
					//$('#sharelinkurlprint').html(data.sharelink);
					try { 
						var str = data.sharelink;
						if( window.clipboardData && clipboardData.setData ) {
						    clipboardData.setData("Text", str);
						    alert("복사되었습니다.");
						} else {
							prompt("Ctrl+C를 눌러 복사하세요.", str);
						}
					} catch (err) { 
						alert('이 브라우저는 지원하지 않습니다.'); 
					}
				}			
			},
			beforeSend : function() {
				//console.log('before!');
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
				//console.log(jqXHR);
				//console.log(textStatus);
				//console.log(errorThrown);			
			}
		});
	});
</script>


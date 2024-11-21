<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="juniorout"/>
<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />

<c:choose>
	<c:when test="${depthName eq 'lesson'}">
		<c:set var="depthNum" value="1"/>
		<c:set var="depthNameMobile" value="lesson"/>
	</c:when>
	<c:when test="${depthName eq 'info'}">
		<c:set var="depthNum" value="2"/>
		<c:set var="depthNameMobile" value="info"/>
	</c:when>
	<c:when test="${depthName eq 'share'}">
		<c:set var="depthNum" value="3"/>
		<c:set var="depthNameMobile" value="share"/>
	</c:when>
	<c:when test="${depthName eq 'sosig'}">
		<c:set var="depthNum" value="4"/>
		<c:set var="depthNameMobile" value="sosig"/>
	</c:when>
	<c:when test="${depthName eq 'promotion'}">
		<c:set var="depthNum" value="5"/>
		<c:set var="depthNameMobile" value="promotion"/>
	</c:when>	
</c:choose>



<%@include file="../../layout/m/head.jsp"%>



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
						<h1 class="tit">${info.bo_subject}</h1>
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
							<span class="date">${fn:substring(info.bo_insert_dt,0,10)}</span></div>
						<c:if test="${pageName eq 'campaign'}">
							<c:if test="${info.bo_start_dt ne null }">기간 : ${info.bo_start_dt }</c:if><c:if test="${info.bo_end_dt ne null }"> ~ ${info.bo_end_dt }</c:if>
						</c:if>
						<ul class="eyes_heart floats eyes_heart_link" >
							<li><em><i class="fa fa-eye" aria-hidden="true"></i></em>${info.bo_view}</li>
							<li><a href="#;" onclick="like_up(${info.bo_sn})"><em><i class="fa fa-heart" aria-hidden="true"></i></em> ${info.bo_like }</a></li>
							<!-- 내가 찜 했을 시 <li><a href="#;" class="on"><em><i class="fa fa-heart" aria-hidden="true"></i></em> 34</a></li> -->
							
							<%-- <c:if test="${depthNameMobile eq 'promotion' and pageName eq 'info'}"> --%>
								<li>
									<a href="#;" class="tooltipCustom btnSharelink" title="게시물 공유 URL주소"
									data-pl1="${depthNameMobile}"
									data-pl2="${pageName}"
									data-pl3="view"
									data-pl4=""
									data-sid="${info.bo_sn}">
										<i class="fa fa-link"></i>
									</a>&nbsp;<span id="sharelinkurlprint" class="red-600"></span>	
								</li>
							<%-- </c:if> --%>
						</ul><br>
						<c:if test="${column ne 'share' and table ne 'travel' and not empty info.bo_atch_file}">
							첨부파일 목록 : 					
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
			                    <c:param name="param_atchFileId" value="${info.bo_atch_file}" />
			                </c:import>
		                </c:if>
					</div>
					<div class="content">
					
						<!-- 동영상 영역 -->
						<c:if test="${movfile.orignl_file_nm ne null }">
						<%-- <div id="myElement" style="margin:0 auto;"></div>
					        <script type="text/javascript">
					        	var filen = $('#filen').val();
					            jwplayer("myElement").setup({					
					                file: "/movupload/${movfile.stre_file_nm}",
					                width:"100%",
				                	align:"center",
					                autostart: false,
					                mute: false,
					            });					
					        </script> --%>
					        
					        <c:import url="/cmm/fms/selectFilePrmov.do" >
			    				<c:param name="atchFileId" value="${movfile.bo_mov_file}" />
			    			</c:import>
					        
						</c:if>						
					    <!-- //동영상 영역 -->
						${fn:replace(info.bo_content,"&quot;","")}
						
						<c:if test="${column eq 'share' and pageName eq 'usage'}">
							<br>
							<c:if test="${not empty info.bo_main_img}">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_main_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' alt="${info.bo_subject}_대표이미지" />
							</c:if>
							<c:if test="${not empty filelist}">
								<c:forEach var="imgf" items= "${filelist}" varStatus="status">
									<br><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${imgf.bo_atch_file}"/>&fileSn=<c:out value="${imgf.file_sn}"/>' alt=<c:out value="${imgf.orignl_file_nm}" /> />
								</c:forEach>
							</c:if>
					    </c:if>	
						
						<c:if test="${column eq 'share' and table eq 'travel' and not empty filelist}">
			                <c:forEach var="imgf" items= "${filelist}" varStatus="status">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${imgf.bo_atch_file}"/>&fileSn=<c:out value="${imgf.file_sn}"/>' alt=<c:out value="${imgf.orignl_file_nm}" /> />
							</c:forEach>			              
		                </c:if>
							
						<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') and(pageName ne 'campaign') and(pageName ne 'info')}">				
							<div class="writer_box">
								<!-- 
								<c:choose>
									<c:when test="${empty info.bo_sub_img}">
										<img alt="noImage" src="/naksinuri_original/common_main/img/noImage_writer.png">
									</c:when>
									<c:when test="${info.bo_sub_img ne null}">
										<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_sub_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' width="100%" height="100%" />
									</c:when>
									<c:when test="${!empty info.bo_sub_img}">
										<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${info.bo_sub_img}"/>&fileSn=<c:out value="${info.file_sn}"/>' width="100%" height="100%" />
									</c:when>										
									<c:otherwise>
										<img alt="noImage" src="/naksinuri_original/common_main/img/noImage_writer.png">
									</c:otherwise>
								</c:choose>
								-->
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
						<%@include file="../../layout/m/printmenu.jsp"%>
					</div>
				</div>
				<div class="reply_box">
					<c:if test="${(pageName ne 'notice') and (pageName ne 'policy') and(pageName ne 'info')}">	
						<form method="post" name="replform" id="replform" onsubmit="return replsubmit();">
							<input type="hidden" name="bo_sn" value="${info.bo_sn}"/>
							<div class="reply_write">
								<div class="name_pw">
									<input type="text" class="reply_input" name="co_name" id="co_name" placeholder="이름" /><input type="password" id="co_pass" name="co_pass" class="reply_input" placeholder="비밀번호" />
								</div>
								<div class="reply_textbox">
									<textarea class="reply_textarea" name="co_comment" id="co_comment" placeholder="댓글을 입력해주세요"></textarea>
									<!-- <button class="submitBtn" onclick="replsubmit()">댓글등록</button>-->
									<input type="submit" class="submitBtn" value="댓글등록"/>
								</div>
							</div>
						</form>
						<div class="reply_list">
						<c:forEach var="item" items="${comment_list}">
							<div class="reply_con">
								<span class="name">${item.co_name}</span>
								<span class="date">${fn:substring(item.co_insert_dt,0,20)}</span>
								<a href="#;" data-toggle="modal" data-target="#changePassword" class="replyBtn">[삭제]</a>
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
							<a href="#;" onclick="prev(${prev.bo_sn})" class="btn_prev btn_white"><i class="fa fa-angle-left" aria-hidden="true"></i> 이전글</a>
							<a href="#;" onclick="next(${next.bo_sn})"class="btn_next btn_white">다음글 <i class="fa fa-angle-right" aria-hidden="true"></i></a>
						</li>
						<li class="fr">
							<a href="./list.do" class="btn_list btn_gray">목록</a>
						</li>
					</ul>
				</div>
			</section>
			<c:choose>
				<c:when test="${(pageName eq 'notice') or (pageName eq 'policy') or(pageName eq 'info') }">
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
											<a href="#;" onclick="view2('${item2.bo_sn}')"class="pic">
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
											<a href="#;" onclick="view2('${item.bo_sn}')">
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
											<a href="#;" onclick="view2('${item.bo_sn}')"class="pic"><img src='/naksinuri_original/common_main/img/noImage_gen.jpg'  width="303" height="250"  /></a>
										</c:when>
										<c:otherwise>
											<a href="#;" onclick="view2('${item.bo_sn}')"class="pic"><img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item.bo_main_img}"/>&fileSn=<c:out value="0"/>'  width="303" height="250"  /></a>
										</c:otherwise>
									</c:choose>
									<a href="#;" onclick="view2('${item.bo_sn}')" class="subject">
										<c:choose>
											<c:when test="${fn:length(item.bo_subject)>50 }">
												<em class="list_subject">${fn:substring(item.bo_subject, 0, 50)}...</em>
											</c:when>
											<c:otherwise>
												<em class="list_subject">${item.bo_subject}</em>
											</c:otherwise>
										</c:choose> 
										<span class="date">${fn:substring(item.bo_insert_dt,0,10)}</span>
									</a>
									<a href="#;" onclick="view2('${item.bo_sn}')" class="search_square"><i class="fa fa-search" aria-hidden="true"></i></a>
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
	
	
	<div class="modal fade password_check" id="changePassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<p class="comment">댓글을 삭제합니다. 비밀번호를 입력해주세요.</p>
				<input type="password" class="del_input" id="co_passr" placeholder="비밀번호를 입력해주세요." />
				<div class="btn_box">
					<button class="cp_btn del_btn" onclick="co_del()" >확인</button>
					<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
				</div>
			</div>
		</div>
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
		var co_sn4	=$("#co_sn4").val();
		$('#bo_sn').val(bo_sn);
		$('#co_sn').val(co_sn4);
		$('#co_pass4').val(co_pass4);
		
		form.action = "./co_delete.do";
		form.submit();
	}
	function replsubmit(){
		
		var form = document.getElementById('listform');
		var co_name=$('#co_name').val();
		var co_pass=$('#co_pass').val();
		var co_comment=$('#co_name').val();
		
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

<%@include file="../../layout/m/tail.jsp"%>
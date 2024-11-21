<%@ page import="kcb.org.json.*" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import ="java.util.*,java.text.SimpleDateFormat"%>
<%@ page import ="egovframework.all.login.service.AuthipinsmsVO"%>
<%@ page import="egovframework.all.login.service.LoginVO"%>

<%@ include file="../../naksinuri_original/naksinuri/layout/m/head.jsp" %>

<c:set var="toDay" value="<%=new java.util.Date()%>" />
<fmt:formatDate var="year" pattern="yyyy" value="${toDay}"/>
<fmt:formatDate var="month" pattern="MM" value="${toDay}"/>
<fmt:formatDate var="day" pattern="dd" value="${toDay}"/>

<form:form commandName="eduCenterMainVO" id="trnngForm" name="trnngForm" method="post">
<input type="hidden" id="trnng_year" name="trnng_year" value="${year}"/>
<input type="hidden" id="trnng_month" name="trnng_month" value="${month}"/>
<input type="hidden" id="CRS_GRP_CD" name="CRS_GRP_CD" value=""/>
</form:form>

<form:form commandName="eduCenterBoardVO" id="noticeForm" name="noticeForm" method="post">
<input type="hidden" id="BD_SN" name="BD_SN" value=""/>
</form:form>
		
		
		
<style>
.grpedu_ul div.bx-wrapper{min-height:210px;}
</style>
		
<c:if test="${empty list_banner_center}">
<p><img src="/naksinuri_original/edu/edu_mvis.png" alt="" /></p>
</c:if>
<c:if test="${not empty list_banner_center}">
<style>
#mainNaksiBtnBox{
	padding-top: 10px;
	background-color: #d8e7fa;
	/* height:220px; */
	position: relative;
}

#mainNaksiBtnBox .bx-wrapper{
	/* height: 210px; */
}
#mainNaksiBtnBox .bx-wrapper .bx-viewport{
	height: 165px;
}
#mainNaksiBtnBox .bx-wrapper img{
	/* height: 180px; */
	display: inline;
	width: 100%;
}

#mainNaksiBtnBox .bx-wrapper .bx-pager, .bx-wrapper .bx-controls-auto{
	bottom: 3px;
}

.maintop_slider_control > * {
    position: absolute;
    width: 41px;
    background: #000;
    background: rgba(0,0,0,0.5);
    display: block;
    height: 43px;
    line-height: 36px;
    text-align: center;
    font-size: 20px;
}
.maintop_slider_control a.prev {
    top: 38%;
    left: 0;
}
.maintop_slider_control a.next {
    top: 38%;
    right: 0;
}
.maintop_slider .maintop_slider_control > a.play, .maintop_slider .maintop_slider_control > a.pause {
    width: 40px;
    height: 40px;
    line-height: 35px;
}
.maintop_slider .maintop_slider_control a.play {
    top: 0;
    right: 0;
}
.maintop_slider .maintop_slider_control a.pause { 
    top: 0;
    right: 0;
}
</style>
<section id="mainNaksiBtnBox">
	<ul>
		<c:forEach var="item" items="${list_banner_center}">
			<li style="text-align:center;">
				<a <c:if test="${not empty item.PP_LINK_URL}">
							href="${item.PP_LINK_URL}" target="${item.PP_LINK_TARGET}"
						</c:if> 
						<c:if test="${empty item.PP_LINK_URL}">
							href="#;"
						</c:if>>
					<img src='/cmm/fms/getImage.do?atchFileId=${item.PP_FILE}&fileSn=1' alt="${item.PP_CONT}"/>
				</a>
			</li>
		</c:forEach>
	</ul>
	
	<div class="maintop_slider_control">
	                     
	  <c:set var="scroll_nav_bar_style" value="display:none;"/>
	  <c:if test="${fn:length(list_banner_center)>1}"><c:set var="scroll_nav_bar_style" value=""/></c:if>                                   
	                                     
         <!-- <div class="slide_number">0/0</div> -->
         
         <a href="#;" data-function="prev" class="prev" style="${scroll_nav_bar_style}" tabindex="0" title="이전">
            <img src="/common/img/roll_left.png" alt="이전">
     	  </a>
         <a href="#;" data-function="next" class="next" style="${scroll_nav_bar_style}" tabindex="0" title="다음">
            <img src="/common/img/roll_right.png" alt="다음">
         </a>
         <!-- <a href="#;" data-function="play" class="play" style="display: none;" tabindex="0" title="재생">
            <img src="/common/img/edu/roll_play.png" alt="재생">
         </a>
         <a href="#;" data-function="pause" class="pause" tabindex="0" title="정지">
            <img src="/common/img/edu/roll_pause.png" alt="정지">
         </a> -->
        </div>
	                        
	<script>
		var notice_slider = $('#mainNaksiBtnBox ul').bxSlider({
			auto: true,
			controls: false,
			pause: 5000
		});
		
		$(".maintop_slider_control a").click(function(event) {
        	event.preventDefault();

        	var func = $(this).attr('data-function');
            if( func == 'next' ) {
               notice_slider.goToNextSlide();
            } else if( func == 'prev' ) {
               notice_slider.goToPrevSlide();
            } else if( func == 'play' ) {
               notice_slider.startAuto();
               $(".maintop_slider_control a[data-function='play']").hide();
               $(".maintop_slider_control a[data-function='pause']").show();
            } else if( func == 'pause' ) {
               notice_slider.stopAuto();
               $(".maintop_slider_control a[data-function='play']").show();
               $(".maintop_slider_control a[data-function='pause']").hide();
            }
     	});
	</script>
</section>
<p style="padding-top:18px;"></p>
</c:if>


<div class="eduVis">
		<!-- ##start## 집합교육 / 로그인 ##### -->
		<div class="grp_edu_Bx Grp">

			<div class="calendar_bx">
				<div class="one_calendar" id="schedule">
					<!-- 아작스 로딩 -->
					<!-- <div id="ajax_loading">
						<img src="/common/img/ajax_loader_gray_64.gif" alt="loading">
					</div> -->
					<!-- // 아작스 로딩 -->
					<div class="header">
						<h3 class="f20"><span id="date_label_year">${year}</span> 집합교육</h3>
	
						<span id="" class="change_month prv prev">이전 월</span>
						<span id="" class="change_month nxt next">다음 월</span>
	
						<div class="month"><span id="date_label_month">${month}</span>월</div>
	
						<p class="blk" style="height:10px;"></p>
	
						<select id="sel_crs_grp_cd" class="local_seletor">
							<option value="">교육그룹선택(전체보기)</option>
							<c:forEach var="item" items="${list_edu_grp_cd}">
								<option value="${item.CD_ID}">${item.CD_NM}</option>
							</c:forEach>
						</select>
					</div>
				</div><!--//one_calendar//-->
			</div><!--//calendar_bx//-->

			<div class="login_Bx">
			<c:choose>
				<c:when test="${LoginVO.MBR_ID ne null}">
					<h3>환영합니다. <%-- <span>(${LoginVO.MBR_SN})</span> --%></h3>
					<p><input type="text" placeholder="" value="${LoginVO.MBR_NM} 님" disabled/></p>
					<p class="blk" style="height:8px;"></p>
					<!-- <p><a href="/member/mypage_modify.do?page_type=mobile">내정보수정</a></p> -->
					<p class="blk" style="height:8px;"></p>
					<p>${LoginVO.MBR_LAST_CON_TM}</p>
					<input type="button" class="login_submit" value="로그아웃" onclick="location.href='/educenter/member/logout_act.do?page_type=mobile'" />
				</c:when>
				<c:otherwise>

					<h3>로그인 <span>LOGIN</span></h3>					
					 <div>
						<input type="button" class="login_submit mt-0" value="로그인" onclick="cmmnLoginPopup()" style="height:50px;font-size:16px;font-weight:800;"/>
					</div>
				</c:otherwise>
			</c:choose>
			</div><!--//login_Bx//-->

			<p class="blk" style="height:10px;"></p>

			<div class="eduinfo_Bx" id="table_wrap">
			<!-- 
				<ul class="grpedu_ul">
					<li>
						<div class="grpedu_Pn">
							<span class="tag">낚시어선업자 및 선원</span>
							<a href="#">
								<p class="dates">2019-08-01~2019-09-18</p>
								<p class="sbj">영흥동 늘푸른 센터 3층 강당</p>
								<div class="cont">
									경기 용인시 처인구 원삼면 농촌파크로 80        
								</div>
							</a>
						</div>
					</li>
					<li>
						<div class="grpedu_Pn">
							<span class="tag">낚시어선업자 및 선원</span>
							<a href="#">
								<p class="dates">2019-08-01~2019-09-18</p>
								<p class="sbj">영흥동 늘푸른 센터 3층 강당</p>
								<div class="cont">
									경기 용인시 처인구 원삼면 농촌파크로 80        
								</div>
							</a>
						</div>
					</li>
				</ul>
				<p class="clr"></p>
			 -->
			</div>
		


			<p class="clr"></p>
		</div><!--//grp_edu_Bx//-->
		<!-- ##end## 집합교육 / 로그인 ## -->
	</div><!--//eduVis//-->
	
	<p class="blk mt-50 mb-30"></p>
	<%--
	<div class="edu_cs_Bx">
		<p>
			
			<a href="/educenter/m/rmndr/agreeShip.do" class="wp50 display-inline-block float-left"><img src="/naksinuri_original/edu/education_img1.png" alt="" /></a>
			<a href="/educenter/m/rmndr/agreeHouse.do" class="wp50 display-inline-block"><img src="/naksinuri_original/edu/education_img2.png" alt="" /></a>
			
			<a href="javascript:void(0)" class="btn-act-chk-login-to wp50 display-inline-block float-left" data-linkurl-success="/educenter/m/trnng/list.do" data-linkurl-fail="/educenter/m/rmndr/agreeShip.do">
				<img src="/naksinuri_original/edu/education_img1.png" alt="" />
			</a>
			<a href="javascript:void(0)" class="btn-act-chk-login-to wp50 display-inline-block" data-linkurl-success="/educenter/m/trnng/list.do" data-linkurl-fail="/educenter/m/rmndr/agreeHouse.do">
				<img src="/naksinuri_original/edu/education_img2.png" alt="" />
			</a>
		</p>
	</div><!--//edu_cs_Bx//-->
 --%>

	<!-- ##start## 아이콘 ##### -->
	<div class="edu_icons Grp">
		<ul class="eduicon_ul">
			<li>
				<a href="/educenter/m/trnng/list.do">
					<img src="/naksinuri_original/edu/edcon_01.png" alt="" />
					<p>교육일정</p>
				</a>
			</li>
			<li>
				<a href="/educenter/m/main/curriculumGuide.do">
					<img src="/naksinuri_original/edu/edcon_02.png" alt="" />
					<p>교육과정안내</p>
				</a>
			</li>
			<li>
				<a href="/educenter/m/board/faq/list.do">
					<img src="/naksinuri_original/edu/edcon_03.png" alt="" />
					<p>수강 FAQ</p>
				</a>
			</li>
			<li>
				<a href="/educenter/m/main/serviceGuide.do">
					<img src="/naksinuri_original/edu/edcon_04.png" alt="" />
					<p>처음이용자 안내</p>
				</a>
			</li>
			<!-- 
			<li>
				<a href="#">
					<img src="/naksinuri_original/edu/edcon_05.png" alt="" />
					<p>낚시전문교육과정<br/>오프라인교육</p>
				</a>
			</li>
			 -->
		</ul>
		<p class="clr"></p>
	</div><!--//edu_icons//-->
	<!-- ##end## 아이콘 ## -->


	<p class="blk" style="height:20px;"></p>

	<!--### 커뮤니티 ######-->
	<div class="educomm_set Grp">
		<div class="edu_notice_Bx">
			<h3>
				공지사항
				<a href="/educenter/m/board/notice/list.do"><img src="/naksinuri_original/edu/more.png" alt="더보기" /></a>
			</h3>
			<ul class="edu_latest">
				<c:if test="${empty list_notice}">
					<li class="text-center pt-60">등록 된 공지사항이 없습니다.</li>
				</c:if>
				<c:forEach var="item" varStatus="status" items="${list_notice }">
					<fmt:parseDate value="${item.BD_REG_DT}" var="parse_bd_reg_dt" pattern="yyyy-MM-dd HH:mm:ss" scope="page"/>
					<fmt:formatDate value="${parse_bd_reg_dt }" pattern="yyyy.MM.dd" var="str_date"/>
					<fmt:formatDate value="${parse_bd_reg_dt }" pattern="yyyy.MM." var="str_date_ym"/>
					<fmt:formatDate value="${parse_bd_reg_dt }" pattern="dd" var="str_date_d"/>
					<c:choose>
						<c:when test="${status.index == 0}">
						<li>
							<a href="#;" class="fst" onclick="noticeView('${item.BD_SN}')">
								<div class="fst_date">
									<img src="/naksinuri_original/edu/new.png" alt="새로운글" class="new_icon"/>
									<span>${str_date_ym }</span>
									<div class="day">${str_date_d }</div>
								</div>
								<p>${fn:substring(item.BD_TITLE.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "), 0, 22)}</p>
								<div style="height:60px;overflow:hidden;">
								<%-- ${fn:substring(item.BD_CONT.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "), 0, 50)}... --%>
								${item.BD_CONT.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")}
								</div>
							</a>
						</li>
						</c:when>
						<c:otherwise>
							<li class="line"><a href="#;" onclick="noticeView('${item.BD_SN}')" class="sbj">${fn:substring(item.BD_TITLE.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "").replaceAll("&nbsp;"," "), 0, 22)}..</a> <div class="date">${str_date}</div></li>
						</c:otherwise>
					</c:choose>
					</li>
				</c:forEach>
			</ul>
		</div><!--//edu_notice_Bx//-->

		<p class="blk" style="height:20px;"></p>

		<div class="edu_apply_Bx">
			<ul>
				<li>
					<!-- <a href="#">
						<p>낚시누리 바로가기</p>
						다양한 낚시정보와 낚시대회는 낚시누리에서 확인하세요.
					</a> -->
					<img src="/naksinuri_original/edu/educational_counseling.png" width="100%"/>
				</li>
				<%--
				<li>
					<a href="javascript:fn_egov_all_document_fileDownload('FILE_000000000000000','1')">
						<p>낚시어선전문교육 수강신청서</p>
						<span style="font-size:12px;">온라인 교육 신청이 안 될 경우만 수강신청서를 다운 받아 작성 후 팩스 또는 메일로 제출해주세요.</span>
					</a>
				</li>
				<li>
					<a href="javascript:fn_egov_all_document_fileDownload('FILE_000000000000000','2')">
						<p>낚시터전문교육 수강신청서</p>
						<span style="font-size:12px;">온라인 교육 신청이 안 될 경우만 수강신청서를 다운 받아 작성 후 팩스 또는 메일로 제출해주세요.</span>
					</a>
				</li>
				 --%>
			</ul>
		</div><!--//edu_apply_Bx//-->
		
		<p class="clr"></p>
	</div><!--//educomm_set//-->
	<!--end.### 커뮤니티 ###-->

	<article id="mainGoLink" class="respon floats Grp">
		<ul>
			<li><a href="http://www.mof.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink01.png" alt="해양수산부" /></a></li>
			<li><a href="https://www.fipa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink02.png" alt="KFPA 한국어촌어항공단" /></a></li>
			<li><a href="https://www.facebook.com/FIPAkor" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink10.png" alt="한국어촌어항공단 공식 페이스북" /></a></li>
			<li><a href="http://www.sealife.go.kr" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink09.png" alt="귀어귀촌" /></a></li>
			<li><a href="http://www.kff.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink03.png" alt="KFF 한국낚시연합" /></a></li>
			<li><a href="http://www.nifs.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink04.png" alt="국립수산과학원" /></a></li>
			<li><a href="http://www.fira.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink05.png" alt="FIRA 한국수산자원관리공단" /></a></li>
			<!-- <li><a href="http://www.kst.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink06.png" alt="선박안전기술공단" /></a></li> -->
			<li><a href="https://www.komsa.or.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink11.png" alt="한국해양교통안전공단" /></a></li>
			<li><a href="http://www.khoa.go.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink07.png" alt="국립해양조사원 KHOA" /></a></li>
			<li><a href="http://www.koreafca.kr/" target="_blank"><img src="/naksinuri_original/common_main/img/ico_mainGoLink08.png" alt="한국낚시업중앙회" /></a></li>
		</ul>
		<div class="outside">
			<span id="slider-prev"></span>
			<span id="slider-next"></span>
		</div>
		<script>
			$('#mainGoLink ul').bxSlider({
				auto: true,
				pager: false,
				minSlides: 1,
				maxSlides: 1,
				moveSlides: 1,
				slideWidth: 250,
				slideMargin: 10,
				nextSelector: '#mainGoLink #slider-next',
				prevSelector: '#mainGoLink #slider-prev',
				nextText: '<i class="fa fa-angle-right" aria-hidden="true"></i>',
				prevText: '<i class="fa fa-angle-left" aria-hidden="true"></i>',
				pause: 3000
			});
		</script>
	</article>

	<p class="clr"></p>
	
<script>
function noticeView(bdsn) {
	document.noticeForm.BD_SN.value = bdsn;
    document.noticeForm.action = "<c:url value='/educenter/m/board/notice/view.do'/>";
    document.noticeForm.submit();
}
/** 메인화면 교육과정 스케줄표  */
$(document).ready(function() {
	getAjaxCalendarTableView(null);
});
$("#schedule .change_month").click(function(event) {
	event.preventDefault();
	getAjaxCalendarTableView($(this).hasClass('next'));
});
$("#sel_crs_grp_cd").change(function(){
	event.preventDefault();
	getAjaxCalendarTableView(null);
});
function getAjaxCalendarTableView(moveit) {
	var form = document.getElementById('trnngForm');
	var y = form.trnng_year.value;
	var m = form.trnng_month.value;
	form.CRS_GRP_CD.value = $('#sel_crs_grp_cd').val();
	if(moveit!=null) {
		var y = parseInt(form.trnng_year.value);
		var m = parseInt(form.trnng_month.value);
		if(moveit == true) {
			if(++m > 12) {
				m = 1;
				y++;
			}
		} else {
			if(--m < 1) {
				m = 12;
				y--;
			}
		}		
		y = form.trnng_year.value = pad(y,4);
		m = form.trnng_month.value = pad(m,2);
	}
	$.ajax({
		type:"POST",
		url :"/educenter/m/trnng/ajax_calendar_table.do",
		data:$('#trnngForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$("#table_wrap").html(data);
		},
		beforeSend : function() {
			$("#schedule #ajax_loading").show();
		},
		complete : function() {
			$("#schedule #date_label_year").html(y);
			$("#schedule #date_label_month").html(m);
			$("#schedule #ajax_loading").hide();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
		}
	});	
}
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}
</script>
	

<%-- 공통 로그인 팝업 --%>
<script>
function cmmnLoginPopup() {
	$.ajax({
		type:"POST",
		url :"/educenter/member/loginPopup.do",
		data:null,
		dataType: "html",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			$("#allPublicModalMessage .modal-body").html(data);
			$("#allPublicModalMessage").modal({
				backdrop: 'static',
			    keyboard: false
			});
		},
		beforeSend : function(xhr, opts) {
			//console.log('before!');
			if(isClickRequestLocked()) {
				xhr.abort();
				return;
			}
		},
		complete : function() {
			//console.log('complete!');
			clickRequestLockStop();
	    },
		error: function(jqXHR, textStatus, errorThrown) {
			//console.log('error!');
			clickRequestLockStop();
		}
	});
}
</script>
<%-- //End of 공통 로그인 팝업 --%>	

<%@ include file="../../naksinuri_original/naksinuri/layout/m/tail.jsp" %>

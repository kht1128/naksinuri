<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Enumeration" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.sub.jsp"%>

<%-- <% 
	try {
		Enumeration sessionList = request.getSession().getAttributeNames();
		while(sessionList.hasMoreElements()) {
			String keyName = sessionList.nextElement().toString();
			if(keyName.contains("isInitExternalPlay") || keyName.contains("dateInitExternalPlay")) {
				request.getSession().removeAttribute(keyName);
				//System.out.println("세션 삭제 : " + keyName);
			}
		}
	} catch(Exception e) {
		System.out.println("초기화 가능 중복키 없음 , error : " + e.toString());
	}
	String key = (String)request.getAttribute("key");
	String PLAY_LOCK_CD = (String)request.getAttribute("PLAY_LOCK_CD");
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	session.setAttribute("isInitExternalPlay"+key, true);
	session.setAttribute("dateInitExternalPlay"+key, sf.format(nowTime));
	session.setAttribute("keyInitExternalPlay"+key, PLAY_LOCK_CD);
%>	 --%>

<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
pageContext.setAttribute("sp", " ");
</jsp:scriptlet> 


<div class="modal" id="allPublicModal" role="dialog" style="display:block;position:relative;">
<!--//modal -->

<script src="/common/js/jquery.slimscroll.min.js"></script>
<style>

/* 모달팝업 */
#allPublicModal{z-index:99999;}
#allPublicModalMessage{z-index:999999;}
.modal-backdrop{z-index:99998;}
.modal-title {
	font-family: "Roboto", sans-serif;
	font-weight: bold;
    font-size: 1.286rem;
}
.close {
	font-family: "Roboto", sans-serif;
    float: right;
    font-size: 21px;
    font-weight: bold;
    line-height: 1;
    color: #000;
    text-shadow: none;
    opacity: .5;
}
.modal-float-right {
	text-align:center;
}
.modal-title-sub {
	margin-top:2px;
	color:#f96c29;
	font-size:12px;
	font-weight:bold;
}
.modal-view-table {
	padding-left: 30px;
    padding-right: 30px;
}

.video_block_view {
	display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 10;    
}
.video_block_view ._bg {
	position: absolute;
    top: 0px;
    cursor: pointer;
    background: #000000;
    opacity: 0.8;
}
.video_block_view ._label {
    color: #ffffff;
    padding-top: 10px;
    padding-bottom: 10px;
    background: #f74584;
    position: relative;
    top: 130px;
    cursor: pointer;
    text-align: center;
    font-size: 12px;
    line-height: 16px;
}
.video_ended_view {
	display: none;
    position: absolute;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 10;
    background: rgba(0,0,0,0.5);
}
.video_ended_view ._bg {
	background:#000000;opacity:0.8;
}
.video_ended_view ._label {
    color: #ffffff;
    padding-top: 10px;
    padding-bottom: 10px;
    background: #087dd2;
    position: relative;
    top: 130px;
    cursor: pointer;
    text-align: center;
    font-size: 12px;
    line-height: 16px;
}	
	

.video_wrap {
	position: relative;
	overflow: hidden;

	padding-bottom: 30px;
	/*.vjs-control-bar 높이 이상 띄우기*/
	margin-bottom: 20px;
}


/* video-js 커스텀 */
	.vjs-has-started .vjs-control-bar {
		display: flex;
		bottom: -30px;
		visibility: visible;
	    opacity: 100;
	    transition: visibility 0s, opacity 0s;
	    background: #222222;
	}
	.vjs-has-started.vjs-user-inactive.vjs-playing .vjs-control-bar {
	  visibility: visible;
	  opacity: 100;
	  transition: visibility 0s, opacity 0s; }

	.vjs-controls-disabled .vjs-control-bar,
	.vjs-using-native-controls .vjs-control-bar,
	.vjs-error .vjs-control-bar {
	  display: flex;
	}
	.video-js .vjs-big-play-button {
	    top: 44%;
	    left: 44%;
    }
    .video-js .vjs-progress-control {
    	display:none;
    }
/* 모달 커스텀 */
	#allPublicModal .modal-dialog{
		max-height: 95%;
		overflow-y: auto;
	}

	#allPublicModal .modal-title-sub {
		margin-top:10px;
	}
	#allPublicModal .title_wrap {
		color: #ffffff;;
		overflow: hidden;
		padding-bottom: 10px;
	}

	#allPublicModal .modal-title {
		font-size: 1.5rem;
	}

	#allPublicModal .modal-content {
		background-color:#2d2d2d;
	}

/* 탭 커스텀 */
	#allPublicModal .menu_wrap {
		background: #1a1a1c;
		border: 1px solid #000;
	}

	#allPublicModal .menu_wrap ul.nav {
		border-bottom: 0 none;
	}
	#allPublicModal .menu_wrap ul.nav li {
		border-bottom: 0 none;
		/* width: 50%; */
		width: 100%;
	}

	#allPublicModal .menu_wrap ul.nav li a {
		border-radius: 0;
		text-align: center;
		margin: 0;
		font-weight: bold;

		background: url(/common/img/edu/menu_ul_bg.png);
		border: 0 none;
	    color: #ccc;
	}

	#allPublicModal .menu_wrap ul.nav li:first-child a {
		border-right: 1px solid #000;
	}

	#allPublicModal .menu_wrap ul.nav li.active a {
	    background: url(/common/img/edu/menu_ul_bg_active.png);
	    color: #337ab7;
	}

/* 탭 컨텐츠 */
	#allPublicModal .menu_wrap .tab-content {
		padding: 10px 10px 0 10px;
		color: #fff;
		overflow-y:scroll;
		margin-top:2px;
	}

	#allPublicModal .menu_wrap .tab-content .image_wrap {
	    float: left;
	    margin-right: 10px;
	    position: relative;
	    text-align: right;
	}

	#allPublicModal .menu_wrap .tab-content .image_wrap p {
		display: inline-block;
		vertical-align: top;
		zoom: 1; /* Fix for IE7 */
		*display: inline; /* Fix for IE7 */

		position: absolute;
		right: 0;
		bottom: 0;

		padding: 5px;
		background: #000;
		background: rgba(0,0,0,0.5);
		color: #fff;
	}

	#allPublicModal .menu_wrap .tab-content .ul_lecture_list {
		margin-bottom: 0;
	}

	#allPublicModal .menu_wrap .tab-content .ul_lecture_list li {
		overflow: hidden;
	    background: #2d2d32;
	    padding: 8px;
	    margin-bottom: 10px;
	    cursor: pointer;
	}

	#allPublicModal .menu_wrap .tab-content .ul_lecture_list li.active {
		border: 1px solid #0099e0;
	}

	#allPublicModal .menu_wrap .tab-content .info p {
		text-overflow: ellipsis;
		overflow: hidden;
		word-break: break-all;
		white-space: nowrap;

		margin: 10px 0;
	}

	#allPublicModal .menu_wrap .tab-content .info .label {
		border-radius: 10px;
		line-height: 2;
	}

	#allPublicModal .menu_wrap .tab-content hr {
		border-color: #4b4b59;
		margin: 10px 0;
	}

	#allPublicModal .menu_wrap .tab-content .lecture_detail ul,
	#allPublicModal .menu_wrap .tab-content .lecture_detail ol {
		padding-left: 20px;
	}
	.tab-content>.tab-pane.custom {
		height:300px;
	}
	.text-pre-detail {
		display: block;
	    word-break: break-all;
    	word-wrap: break-word;
    	line-height: 1.5;
    	/*margin-left: -15px;
    	margin-right: 10px;*/
    	overflow-x: scroll;
    	padding: 5px;
	}
/* 기타 */
	.filedownload {
		padding-left:10px;
		padding-bottom:20px;
	}
	.filedownload a {
		color:#ffffff;
	}	
	.filedownload a:focus, a:hover {
		color:#ffcd17;
	}
	.subtitle_area{
		width: 100%;
	    height: 100px;
	    padding: 5px;
		background: #fff;
	    border: 1px #ffa500 solid;
	    margin-bottom: 10px;
	}
	
	.subtitle_area textarea{
		margin: 0px;
	    width: 100%;
	    height: 100%;
	    border: 0px;
	}	
</style>

<div class="modal-dialog" role="document">

<form:form commandName="eduMyHistoryVO" id="listForm2" name="listForm2" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo.HMBR_SN}"/>
<input type="hidden" id="HMBR_DTL_SN" name="HMBR_DTL_SN" value=""/>
<input type="hidden" id="focusindex" name="focusindex" value=""/>
</form:form>
<form:form commandName="myHistoryVO" id="listForm3" name="listForm3" method="post">
<input type="hidden" id="CRS_SN" name="CRS_SN" value="${parentInfo.CRS_SN}"/>
<input type="hidden" id="HMBR_SN" name="HMBR_SN" value="${parentInfo.HMBR_SN}"/>
<input type="hidden" id="HMBR_DTL_SN" name="HMBR_DTL_SN" value="${info.HMBR_DTL_SN}"/>
<input type="hidden" id="TRN_CUR_TIME" name="TRN_CUR_TIME" value=""/>
<input type="hidden" name="PLAY_LOCK_CD" value="${PLAY_LOCK_CD}"/>
<input type="hidden" name="key" value="${key}"/>
<%-- <input type="hidden" name="MBR_ID" value="${parentInfo.MBR_ID}"/> --%>
<input type="hidden" name="LOG_DTLS">
<input type="hidden" id="PLAY_LOCK_YN" name="PLAY_LOCK_YN" value="${PLAY_LOCK_YN}"/>
<input type="hidden" name="VLD_VRFC_KEY">
</form:form>	
	
<form id="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off" 
	action="">
	
	<input type="hidden" id="typeStr" name="typeStr" value="dtl">
	
	<div class="modal-body">
		<div class="row">
			<div class="col-md-12">
				<div class="title_wrap">
	 				<h4 class="modal-title">
	 					낚시전문교육
	 				</h4>
	 				<h4 class="modal-title">
	 					강좌명 : ${info.CRS_DTL_NM}
	 				</h4>
				</div>
				<div class="video_wrap">
					<c:set var="vfile" value="/cmwebfiles/edu/sample_prmov5"/>
					<div id="videoplayerBody">
						<video id="my-player" class="video-js" style="width:100%;" playsinline>
		  					<%--
		  					<source src="${info.FILE_ORG_NM}.mp4" type="video/mp4"></source>
		  					<source src="${info.FILE_ORG_NM}.webm" type="video/webm"></source>
		  					<source src="${info.FILE_ORG_NM}.ogv" type="video/ogg"></source>
		  					--%>
		  					<c:import url="/cmm/fms/selectFileSourceUrlForVideojs.do" >
			    				<c:param name="atchFileId" value="${info.TRN_FILE_SN}" />
			    				<c:param name="fileSn" value="0" />
			    			</c:import>
		  					<p class="vjs-no-js">
		    				To view this video please enable JavaScript, and consider upgrading to a web browser that
		    				<a href="http://videojs.com/html5-video-support/" target="_blank">
		      				supports HTML5 video
		    				</a>
		  				</p>
						</video>
					</div>
					<div class="video_block_view" id="video_block_view">
						<div class="_bg"></div>
						<div class="_label">화면을 터치해주세요.</div>
					</div>
					<div class="video_ended_view" id="video_ended_view">
						<div class="_bg"></div>
						<div class="_label">동영상 강의가 종료되었으며 해당 강의가 이수완료 되었습니다.<br>처음부터 다시 보시려면 화면을 클릭하세요.</div>
					</div>
				</div>
		  	</div>
		</div>
		<%--  
		<div class="subtitle_area" tabindex="-1">
			<textarea tabindex="0" readonly="readonly">${info.TRN_CONT}</textarea>
		</div>
		--%>
		<div class="row">
			<div class="col-md-12">
		  		<div class="menu_wrap">
					<ul class="nav nav-tabs" role="tablist">
						<!-- <li><a href="#lecture_list">강의목차</a></li> -->
						<li><a href="#lecture_info">강의정보</a></li>
					</ul>
					<div class="tab-content">
						<%-- <div role="tabpanel" class="tab-pane custom" id="lecture_list">
							<ul class="ul_lecture_list list-unstyled">
							<c:forEach var="item" varStatus="status" items="${list_dtl}">
								<c:if test="${item.CRS_DTL_SN eq info.CRS_DTL_SN}">
								<li class="<c:if test="${item.HMBR_DTL_SN eq info.HMBR_DTL_SN}">active</c:if>">
									<div class="image_wrap">
										
										<fmt:parseNumber var="calc_hour" value="${item.TRN_MAX_TIME/3600}" integerOnly="true"/>
										<fmt:parseNumber var="calc_min" value="${(item.TRN_MAX_TIME%3600)/60}" integerOnly="true"/>
										<fmt:parseNumber var="calc_sec" value="${item.TRN_MAX_TIME%60}" integerOnly="true"/>	
									
										<img src="<c:url value="/cmm/fms/getImage.do?atchFileId=${item.TRN_FILE_SN}&fileSn=1"/>" alt="강의 이미지" style="width:110px;height:70px;">
										<p class="time">
											[ <fmt:formatNumber minIntegerDigits="2" value="${calc_hour}" type="number"/>:<fmt:formatNumber minIntegerDigits="2" value="${calc_min}" type="number"/>:<fmt:formatNumber minIntegerDigits="2" value="${calc_sec}" type="number"/> ]
										</p>
									</div>
									<div class="info">
										<p class="title">
											${item.TRN_NM} (${item.CRS_DTL_NM})
										</p>
										<p class="">
											<span class="text-muted">
												학습률
											</span>
											<span class="label label-warning">
												<fmt:formatNumber type="percent" value="${item.LRNNG_PRGRS}"  pattern="0.00%"/>
											</span>
										</p>
									</div>
									<a href="#" data-hmbr-dtl-sn="${item.HMBR_DTL_SN}" data-linkurl="/educenter/m/mbrhstry/videoplayer.do"></a>
								</li>
								</c:if>
							</c:forEach>
							</ul>
						</div> --%>
						
						<div role="tabpanel" class="tab-pane custom" id="lecture_info">
						
							<fmt:parseDate value="${fn:replace(parentInfo.CRS_STR_DT, '.0', '')}" var="parse_crs_str_dt" pattern="yyyy-MM-dd" scope="page"/>
							<fmt:parseDate value="${fn:replace(parentInfo.CRS_END_DT, '.0', '')}" var="parse_crs_end_dt" pattern="yyyy-MM-dd" scope="page"/>
							<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_STR_DT, '.0', '')}" var="parse_recruit_str_dt" pattern="yyyy-MM-dd" scope="page"/>
							<fmt:parseDate value="${fn:replace(parentInfo.RECRUIT_END_DT, '.0', '')}" var="parse_recruit_end_dt" pattern="yyyy-MM-dd" scope="page"/>
							<fmt:formatDate value="${parse_crs_str_dt}" pattern="yyyy.MM.dd(E)" var="CRS_STR_DT"/>
							<fmt:formatDate value="${parse_crs_end_dt}" pattern="yyyy.MM.dd(E)" var="CRS_END_DT"/>
							<fmt:formatDate value="${parse_recruit_str_dt}" pattern="yyyy.MM.dd(E)" var="RECRUIT_STR_DT"/>
							<fmt:formatDate value="${parse_recruit_end_dt}" pattern="yyyy.MM.dd(E)" var="RECRUIT_END_DT"/>
							<br>
							<ol class="lecture_detail">
								<li>교육명 : ${parentInfo.CRS_NM}</li><br>
								<li>교육과정 : ${info.CAT_NM}</li><br>
								<li>교과목 : ${info.CAT_DTL_NM}</li><br>							
								<li>수강기간 : ${CRS_STR_DT} ~ ${CRS_END_DT}</li><br>
								<li>총 교육시간 : ${parentInfo.SUM_TOT_TIME}시간</li>
							</ol>
							<br>
							<hr>
							<p class="text-primary">강좌명</p>
							<hr>
							
							<ol class="lecture_detail">
								<li>강의시간 : ${info.LRNNG_MAX_TIME}시간	</li><br>
								<pre class="text-pre-detail">${info.CRS_DTL_DSCRP}</pre>
							</ol>						
							
							<hr>
							<p class="text-primary">다운로드 자료</p>
							<hr>
							<p class="filedownload">
							<c:import url="/cmm/fms/selectFileInfs.do" >
						    	<c:param name="param_atchFileId" value="${info.CRS_DTL_FILE}" />
						    	<c:param name="param_isHideFileSize" value="true" />
						    </c:import>
						    </p>		
							<br>
							 
						</div>
					</div> <!-- tab-content -->
				</div> <!-- menu_wrap -->
		  	</div>
		</div>
	  	<div class="form-group row">
			<div class="col-md-12">
				<p class="modal-title-sub mt-10" id="modal_title_sub">3분마다 현재 플레이시간이 자동 저장됩니다.</p>
		  	</div>
		</div>
		<div class="modal-float-right">
            <button type="button" class="btn btn-default btn-outline clk_modal_dismiss">종료하기</button>
        </div>
  	</div>
</form>

<script>
//var isPlatformAndroid = false;
//var isPlatformiOS = false;
var isError = false;
var isRefreshList = false;
var isCompleted = ${info.LRNNG_CMPLT_DTL_ST};
var lockScreen = new Array();
var lockTimeSec = 30;//스크린락 만들기 위한 기준값
var trnContinueTime = ${info.TRN_CUR_TIME};
var trnMaxTime = ${info.TRN_MAX_TIME};
var isFirstSet = true;
var videoSaveTimeSec = 1;
var videoCurTimeSec = 0;
var videoNowSeeTime = 0;
var videoRemainTime = 0;
var videoMaxTime = 0;
var options = {
	"controls": true,
	"autoplay": true,
    "preload": "auto",
    "poster": '<c:url value="/cmm/fms/getImage.do?atchFileId=${info.TRN_FILE_SN}&fileSn=1"/>',
    //"loop": "true",//주석을 풀면 무조건 반복한다?!
    /*"width": 768,*/
    "height": 320
};
var player = null;
var savePointTimeArr = [];

var currentDt = new Date(+new Date() + 3240 * 10000).toISOString().replace("T", " ").replace(/\..*/, '');
var eduStrDt = '${parentInfo.CRS_STR_DT}'.replace(".0", "");
var eduEndDt = '${parentInfo.CRS_END_DT}'.replace(".0", "");

if(!((currentDt < eduEndDt) && (currentDt > eduStrDt))){
	alert("교육기간이 지난 교육입니다.");
	location.href = '/educenter/index.do';
}

var form = document.getElementById('listForm2');

//중복실행했을 경우
/* if($('#error').val() == "1"){
	if(!confirm('다른 화면 또는 브라우저에서 이미 동영상을 시청중인 경우 취소버튼을 눌러 닫으시기 바랍니다.\n(하나 이상의 플레이어가 실행 되면 중복실행으로 감지되어 종료 될 수 있습니다.)\n계속 하시겠습니까?')) {
		form.CRS_SN.value = $('#CRS_SN').val();
		form.HMBR_SN.value = $('#HMBR_SN').val();
		form.action = "/educenter/mbrhstry/listDtl.do";
		form.submit();
	} 
} */

/*******************************************************/
setInterval(function(){
	
	var logDtls = '[videoNowSeeTime : ' + videoNowSeeTime + ', videoCurTimeSec(교육시간) : ' + videoCurTimeSec;
   	logDtls += ', trnContinueTime(이어보기시간) : ' + trnContinueTime + ', trnMaxTime(총시간) : ' + trnMaxTime;
   	logDtls += ', isError : ' + isError + ', isRefreshList : ' + isRefreshList + ', isCompleted(완료여부) : ' + isCompleted + ', isFirstSet : ' + isFirstSet;
	logDtls += ', lockScreen : ' + lockScreen + ', lockTimeSec : ' + lockTimeSec;
	logDtls += ', videoSaveTimeSec : ' + videoSaveTimeSec + ', videoRemainTime : ' + videoRemainTime + ', videoMaxTime : ' + videoMaxTime;
	
	for(var i = 0; i < savePointTimeArr.length; i++){
		if(i == 0) logDtls += ', savePointTimeArr : ';
		if(i > 0) logDtls += ', ';
		logDtls += savePointTimeArr[i];
	}
	logDtls += ']';
	
   	var frm = document.getElementById('listForm3');
   	frm.TRN_CUR_TIME.value = videoCurTimeSec;
   	frm.LOG_DTLS.value = logDtls;
   	frm.action = '';
	
	ajaxConnectionCheck();
	
	frm.LOG_DTLS.value = "";
   		
}, 60000);
/*******************************************************/

$(document).ready(function(){
	/*
	var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
	if (mobile) {
		// 유저에이전트를 불러와서 OS를 구분합니다.
		var userAgent = navigator.userAgent.toLowerCase();
		if (userAgent.search("android") > -1) {
			isPlatformAndroid = true;
		} else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1) || (userAgent.search("ipad") > -1)) {
			isPlatformiOS = true;
		} else {
			//etc
		}
	} else {
		//not mobile
	}
	*/
	//frstSaveUdtDate();
	var initcanplaythrough = false;
	player = videojs('my-player',options);
	player.ready(function() {
		isError = false;
	});
	player.on('loadedmetadata', function() {
		
	});
    player.on("canplaythrough", function(){
    	if(!initcanplaythrough) {
    		var timeValue = 0;
    		if(trnContinueTime > 0 && trnContinueTime <= trnMaxTime) {
    			if(!isError) {
    				player.currentTime(0);
    				player.pause();
    				if(!isCompleted) {
    					var timeValue = 0;
    					if(confirm('저장 된 시점이 있습니다.\n이어보기 하시겠습니까?')) {
    						/*
    						videoNowSeeTime = trnContinueTime;
    						videoCurTimeSec = trnContinueTime;
    						player.currentTime(videoCurTimeSec);
    						player.play();
    						timeValue = trnContinueTime;
    						*/
    						frstSaveUdtDate();
    						checkContinueTime();
    					} else {
    						frstSaveUdtDate();
    						timeValue = trnContinueTime;
    						player.currentTime(0);
    						player.play();
    					}
    				} else {
    					$('#modal_title_sub').html('해당 교육은 이수가 완료 되었습니다.');
    					player.play();
    				}
    				//$('#modal_title_sub').html('3분마다 현재 플레이시간이 자동 저장됩니다.<br/>( 마지막 저장 시점 : '+parseTimeLabel(timeValue)+' )');
    				$('#modal_title_sub').html('3분마다 현재 플레이시간이 자동 저장됩니다.');
    			} 
    		} else {
    			frstSaveUdtDate();
    		}		
    		initcanplaythrough = true;
		}
    });
	player.on('ended', function() {
		$('#video_ended_view').css('display','block');
		$('#modal_title_sub').html('해당 교육은 이수가 완료 되었습니다.');
		if(!isCompleted) savePoint();	
	});
	player.on('pause', function() {
		if (player.isFullscreen()){
			player.exitFullscreen();
			player.exitFullWindow();	 
		}
	});
	player.on('play', function() {
		//console.log('play~');
		videoMaxTime = player.duration();
	});
	player.on('timeupdate', function(){
		var curTime = Math.floor(player.currentTime());
		var videoRemainTime = player.remainingTime();//남은시간
		if(player.currentTime() == player.duration()){
	   		//ended
		}
	   	if(videoCurTimeSec != curTime && !player.seeking()) {
	   		videoCurTimeSec = curTime;
	   		//console.log(videoCurTimeSec + ' / ' + videoSaveTimeSec);
	   		initSet();
	   		initSetVjsProgressbar();
	   		// 이어보기 저장 시점 설정
	   	   	if(videoSaveTimeSec == 180) {
	   	   		videoSaveTimeSec = 0;
	   	   		if(!isCompleted) savePoint();	
	   	   	}
	   	   	if(!isCompleted) showLockScreen();
	   	 	videoSaveTimeSec++;
	   		// End 이어보기 저장 시점 설정
	   	}
	});
	player.on("seeking", function(){ 
		if(player.currentTime() > videoNowSeeTime && !isCompleted) {
			player.currentTime(videoNowSeeTime);
		}
	});
	player.on('error', function(e) {
		//console.log(e);
		//videojs.log('error:'+e);
		//isError = true;
		var playError = player.error();
		isError = playError.code + " - " + playError.message;
		// https://docs.videojs.com/mediaerror
		var movurl = player.src();
		player.dispose();
		$('#videoplayerBody').html('<video id="my-player" class="video-js" ><source src="'+movurl+'" type="video/mp4"></source><source src="'+movurl+'" type="video/webm"></source><source src="'+movurl+'" type="video/ogg"></source></video>');
		player = videojs('my-player',options);
		player.reset();
		player.load();
		//alert('플레이어에 일시적인 오류가 발생되어 자동 종료 됩니다.\n종료 후 다시 실행해 주세요.');
		//$("#allPublicModal").modal('hide');			
	});
	var myVideo = document.getElementById("my-player");
	if (myVideo.addEventListener) {
		myVideo.addEventListener('contextmenu', function(e) {
	        e.preventDefault();
	    }, false);
	} else {
		myVideo.attachEvent('oncontextmenu', function() {
	        window.event.returnValue = false;
	    });
	}
	var form2 = document.getElementById('listForm2');

	if($('#PLAY_LOCK_YN').val() == "Y") {
		if(!confirm('다른 화면 또는 브라우저에서 이미 동영상을 시청중인 경우 취소버튼을 눌러 닫으시기 바랍니다.\n(하나 이상의 플레이어가 실행 되면 중복실행으로 감지되어 종료 될 수 있습니다.)\n계속 하시겠습니까?')) {
			form2.setAttribute('action', '/educenter/mbrhstry/listDtl.do');
	 		form2.submit();	
		} else {
			$.ajax({
				type:"POST",
				url :"/educenter/mbrhstry/ajaxUpdateEduCheck.do",
				data:$('#listForm3').serialize(),
				/* data:null, */
				dataType: "json",
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
				async: false
			})
			.done(function(data, status, xhr) {
				
				
			})
			.fail(function(jqXHR, textStatus, errorThrown) {
				//console.log(jqXHR);
				//console.log(textStatus);
				//console.log(errorThrown);
		  	})
			.always(function(){
				//console.log('complete!');
			});
		}
	}
});
//setTimeout(function() { player.play(); }, 800);
/*
setInterval(function() {
	if (!player.paused()) {
    	currentTime = Math.floor(player.currentTime());
	}
}, 1000);
*/
//var currentTime = 0;
/*
$(function(){
	setTimeout(function() {
		if(isPlatformAndroid && trnContinueTime > 0 && trnContinueTime <= trnMaxTime) {
			//videojs.log('trnContinueTime : ' + trnContinueTime);
			//videojs.log('trnMaxTime : ' + trnMaxTime);
			if(!isError) {
				player.pause();
				if(!isCompleted) {
					var timeValue = 0;
					if(confirm('저장 된 시점이 있습니다.\n이어보기 하시겠습니까?')) {
						videoNowSeeTime = trnContinueTime;
						videoCurTimeSec = trnContinueTime;
						player.currentTime(videoCurTimeSec);
						player.play();
						timeValue = trnContinueTime;
					} else {
						timeValue = trnContinueTime;
						player.currentTime(0);
						player.play();
					}
				} else {
					$('#modal_title_sub').html('해당 교육은 이수가 완료 되었습니다.');
					player.play();
				}
				$('#modal_title_sub').html('3분마다 현재 플레이시간이 자동 저장됩니다.( 마지막 저장 시점 : '+parseTimeLabel(timeValue)+' )');
			} 
		}		
	}, 800);
});
*/
function frstSaveUdtDate(){
	var form = document.getElementById('listForm3');
	form.TRN_CUR_TIME.value = videoCurTimeSec;
	form.LOG_DTLS.value = '[처음실행 저장]';
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/ajaxUpdateUdtDate.do",
		data:$('#listForm3').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		success: function(data, status, xhr) {
			console.log(data);
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
			alert('현재 동영상을 시청할 수 없는 환경입니다.\n인터넷 환경 및 로그인 여부를 재확인 후 시청해주세요.');
			location.href = '/educenter/index.do';
		}
	});
}
//동영상 플레이어 이어보기 시간 검증
function checkContinueTime() {
	
	var form = document.getElementById('listForm3');
	form.TRN_CUR_TIME.value = videoCurTimeSec;
	form.action = '';
	
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/ajaxCheckEduContinueTime.do",
		data:$('#listForm3').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false
	})
	.done(function(data, status, xhr) {
		var trn_cur_time = data.TRN_CUR_TIME;
		var error_msg = data.error;
		
		if(error_msg != null) alert(error_msg)
	
		trnContinueTime = trn_cur_time;
		videoNowSeeTime = trnContinueTime;
		videoCurTimeSec = trnContinueTime;
		player.currentTime(videoCurTimeSec);
		player.play();
		timeValue = trnContinueTime;
		
		$('#modal_title_sub').html('3분마다 현재 플레이시간이 자동 저장됩니다.<br/>( 마지막 저장 시점 : '+parseTimeLabel(timeValue)+' )');
		
	})
	.fail(function(jqXHR, textStatus, errorThrown) {
		//console.log(jqXHR);
		//console.log(textStatus);
		//console.log(errorThrown);
  	})
	.always(function(){
		//console.log('complete!');
	});
	
}
//스크린락 세팅
function initSet() {
	if(isFirstSet) {
		var maxTime = videoMaxTime - lockTimeSec;
		var lockCount = 6;//Math.floor(maxTime/lockTimeSec);
		for(var i=0; i<lockCount; i++) {
			var rst = Math.floor(Math.random() *  Math.floor(maxTime)) + lockTimeSec;
			lockScreen.push(rst);
		}	
		isFirstSet = false;
	}
}
//스킵바 처리
function initSetVjsProgressbar() {
	if(!isCompleted) {
		$('.video-js .vjs-progress-control').css('display','none');
	} else {
		$('.video-js .vjs-progress-control').css('display','flex');
	}
}
//플레이시간 저장
function showLockScreen() {
	for(var i=0; i<lockScreen.length; i++) {
		if(lockScreen[i] == videoCurTimeSec) {
			lockScreen.splice(i,1);
			$('#video_block_view').css('display','block');
			player.pause();
		}
	}	
}
var VLD_VRFC_KEY;
//플레이시간 저장
function savePoint() {
	if(videoNowSeeTime < videoCurTimeSec) //스킵 가능 구간 설정
		videoNowSeeTime = videoCurTimeSec;
	if(trnContinueTime > videoCurTimeSec || videoNowSeeTime > videoCurTimeSec) {
		//기존 이어보기 시간보다 작으면 저장하지 않는다.
		return;
	}
	
	/***************************************/
	savePointTimeArr.push(nowTime());

	var logDtls = '[savePoint저장]';
		logDtls += '[videoNowSeeTime : ' + videoNowSeeTime + ', videoCurTimeSec(교육시간) : ' + videoCurTimeSec;
	   	logDtls += ', trnContinueTime(이어보기시간) : ' + trnContinueTime + ', trnMaxTime(총시간) : ' + trnMaxTime;
	   	logDtls += ', isError : ' + isError + ', isRefreshList : ' + isRefreshList + ', isCompleted(완료여부) : ' + isCompleted + ', isFirstSet : ' + isFirstSet;
		logDtls += ', lockScreen : ' + lockScreen + ', lockTimeSec : ' + lockTimeSec;
		logDtls += ', videoSaveTimeSec : ' + videoSaveTimeSec + ', videoRemainTime : ' + videoRemainTime + ', videoMaxTime : ' + videoMaxTime;

	for(var i = 0; i < savePointTimeArr.length; i++){
		if(i == 0) logDtls += ', savePointTimeArr : ';
		if(i > 0) logDtls += ', ';
		logDtls += savePointTimeArr[i];
	}
	logDtls += ']';
	/***************************************/
	
	$('#modal_title_sub').html('3분마다 현재 플레이시간이 자동 저장됩니다.<br/>( 마지막 저장 시점 : '+parseTimeLabel(videoCurTimeSec)+' )');
	var form = document.getElementById('listForm3');
	form.TRN_CUR_TIME.value = videoCurTimeSec;
	form.LOG_DTLS.value = logDtls;
	form.VLD_VRFC_KEY.value = VLD_VRFC_KEY;
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/ajaxUpdateEduContinueTime.do",
		data:$('#listForm3').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		success: function(data, status, xhr) {
			//console.log(data);
			if(data.error == '1') {
				//alert(data.msg);
				alert('현재 동영상을 시청할 수 없는 환경입니다.\n인터넷 환경 및 로그인 여부를 재확인 후 시청해주세요.');
				location.href = '/educenter/m/index.do';
			} else if(data.error == '3') {
				player.pause();
				player.dispose();
				alert(data.msg);
				location.href = '/educenter/m/index.do';
			} else {
				if(data.refresh == '1') {
					isRefreshList = true;
				}
				if(data.lrnng_cmplt_st == '1') {
					alert("수고하셨습니다. 설문조사를 완료 하여야 최종 이수가 완료되며, 이수증 출력이 가능합니다. 설문조사 페이지로 이동합니다.");
					//var form2 = document.getElementById('listForm3');
					//form2.setAttribute('action', '/educenter/mbrhstry/external/survey.do');
					//form2.submit();
					var surveyUrl = "${surveyUrl}";
					location.href=surveyUrl;
				}
			}
			VLD_VRFC_KEY = data.VLD_VRFC_KEY;
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
			alert('현재 동영상을 시청할 수 없는 환경입니다.\n인터넷 환경 및 로그인 여부를 재확인 후 시청해주세요.');
			location.href = '/educenter/m/index.do';
		}
	});
}
//스크린락뷰
$('#video_block_view').click(function() {
	$(this).css('display','none');
	player.play();
});
//종료뷰
$('#video_ended_view').click(function() {
	$(this).css('display','none');
	player.currentTime(0);
	player.play();
});
//모달창 닫을때
$('.clk_modal_dismiss').click(function() {
	player.pause();
	player.dispose();
	location.href = '/educenter/m/index.do';
});



//메뉴 탭
$(function(){
	$("#allPublicModal .menu_wrap .nav a").click(function(e){
		e.preventDefault();
		$(this).tab('show');
	});

	$("#allPublicModal .menu_wrap .nav a:first").click();
});

//강의목차 내용 클릭
$(function(){
	$("#allPublicModal .menu_wrap .tab-content .ul_lecture_list li").click(function(e){
		player.pause();
		$('#videoplayerBody').html('');
		player.dispose();
		var form = document.getElementById('listForm2');
		form.HMBR_DTL_SN.value = $(this).find("a").attr('data-hmbr-dtl-sn');
		form.action = '';
		var data_linkurl = $(this).find("a").attr('data-linkurl');
		$.ajax({
			type:"POST",
			url :data_linkurl,
			data:$('#listForm2').serialize(),
			dataType: 'html',//"json",
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				//console.log('success!');
				$("#allPublicModal").html(data);
				$("#allPublicModal").modal({backdrop: 'static', keyboard: false});
			},
			complete : function() {
				//console.log('complete!');
		    },
			error: function(jqXHR, textStatus, errorThrown) {
				//console.log('error!');
			}
		});
	});
});

//메뉴영역 스크롤
/* 모바일에서 제거
$('#allPublicModal .menu_wrap .tab-content').slimscroll({
	height: '100%',
	size: '5px',
	alwaysVisible: true,
	color: '#2D2D33',
	opacity: 1
});
*/
//유틸리티 - 시분초 표기
function parseTimeLabel(seconds) {
	var hour = parseInt(seconds/3600);
	var min = parseInt((seconds%3600)/60);
	var sec = seconds%60;
	return pad(hour,2)+":"+pad(min,2)+":"+pad(sec,2);
}
//유틸리티 - 앞에0채우기
function pad(n, width) {
	n = n + '';
	return n.length >= width ? n : new Array(width - n.length + 1).join('0') + n;
}

function nowTime() {
	var today = new Date();
	var year = today.getFullYear(); // 년도
	var month = ('0' + (today.getMonth() + 1)).slice(-2);  // 월
	var date = ('0' + today.getDate()).slice(-2);  // 날짜
	//var day = today.getDay();  // 요일
	
	var hours = ('0' + today.getHours()).slice(-2); 
	var minutes = ('0' + today.getMinutes()).slice(-2);
	var seconds = ('0' + today.getSeconds()).slice(-2); 
	
	return year + "-" + month + "-" + date + " " + hours + ':' + minutes  + ':' + seconds;
}

function ajaxConnectionCheck(){
	
	$.ajax({
		type:"POST",
		url :"/educenter/mbrhstry/ajaxConnectionCheck.do",
		data:$('#listForm3').serialize(),
		dataType: "json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: false,
		success: function(data, status, xhr) {	
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
			//alert('현재 동영상을 시청할 수 없는 환경입니다.\n인터넷 환경 및 로그인 여부를 재확인 후 시청해주세요.');
			//location.href = '/index.do';
		/* alert("관리자에게 문의하시길 바랍니다. " + errorThrown);
			if(!navigator.onLine){
				alert("현재 네트워크에 연결되어 있지 않습니다.");
			} */
		}
	});
}

</script>
	
</div><!-- /.modal-dialog -->

<%-- PIP(picture-in-picture) 기능 - 2023.04.18 추가  --%>
<script src="/common/js/alertify/mobile_browser.js"></script>
<link rel="stylesheet" type="text/css" href="/common/js/alertify/mobile_browser.css" />
<div id="pipNoticeCmmnAlert" style="display:none;">
	<label style="color:red">동영상 교육 이수시 주의사항을 안내해드립니다.</label><br><br>
	<label style="">해당 아이콘 &nbsp;<img src="/common/img/educenter/pip_icon.png" style="width:50%;"/>&nbsp; 등의 기능(<label style="color:gray">별도의 동영상 플레이어 띄우기 등</label>)을 사용하시면 <label style="color:blue">실제 이수율 반영이 되지 않으므로</label> 현재페이지 내에서의 재생버튼만을 사용하여 이수 하시는 것을 권장합니다.</label>
</div>
<script>
$(function(){
	alertify.alert($('#pipNoticeCmmnAlert').html());
	$('.alertify .ok').html('확인');
	$('.alertify').css('z-index',9999999);
});
</script>
<%-- End of PIP(picture-in-picture) 기능  --%>

<link href="/common/js/video-js/video.js_7.3.0.css" rel="stylesheet">
<script src="/common/js/video-js/video.js_7.3.0.js"></script>

<!--//End of modal -->
</div>

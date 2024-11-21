<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="depthName" value="${column}" />
<c:set var="pageName" value="${table}" />
<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<video width="100%" autoplay muted controls>
	<c:choose>
		<c:when test="${depthName eq 'promotion' and pageName eq 'info'}">
			<source src="/moveupload/${fileVO.streFileNm}" type="video/mp4"></source>
			<source src="/moveupload/${fileVO.streFileNm}" type="video/webm"></source>
			<source src="/moveupload/${fileVO.streFileNm}" type="video/ogg"></source>
		</c:when>
		<c:otherwise>
			<source src="/cmwebfiles/${fileVO.streFileNm}" type="video/mp4"></source>
			<source src="/cmwebfiles/${fileVO.streFileNm}" type="video/webm"></source>
			<source src="/cmwebfiles/${fileVO.streFileNm}" type="video/ogg"></source>
		</c:otherwise>
	</c:choose>
	<p class="vjs-no-js">
		To view this video please enable JavaScript, and consider upgrading to a web browser that
		<a href="http://videojs.com/html5-video-support/" target="_blank">
				supports HTML5 video
		</a>
	</p>
	</video>
</c:forEach>

<%--
<link href="/common/js/video-js/video.js_7.3.0.css" rel="stylesheet">
<script src="/common/js/video-js/video.js_7.3.0.js"></script>   

<script src="/common/js/jquery.slimscroll.min.js"></script>
<style>
/* video-js 커스텀 */
	.vjs-has-started .vjs-control-bar {
		/*display: flex;
		top: 432px;*/
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
/* 모달 커스텀 */
	#eduCenterPublicModal .modal-dialog{
	    position: relative;
	    display: table;
	    overflow-y: auto;
	    overflow-x: auto;
	    width: auto;
	    min-width: 1200px;
	}

	#eduCenterPublicModal .modal-title-sub {
		margin-top:10px;
	}
	#eduCenterPublicModal .title_wrap {
		color: #ffffff;;
		overflow: hidden;
		padding-bottom: 10px;
	}

	#eduCenterPublicModal .modal-title {
		line-height: 40px;
		height: 40px;
		font-size: 1.5rem;
	}

	#eduCenterPublicModal .modal-content {
		background-color:#2d2d2d;
	}

/* 탭 커스텀 */
	#eduCenterPublicModal .menu_wrap {
		background: #1a1a1c;
		border: 1px solid #000;
	}

	#eduCenterPublicModal .menu_wrap ul.nav {
		border-bottom: 0 none;
	}
	#eduCenterPublicModal .menu_wrap ul.nav li {
		border-bottom: 0 none;
		width: 50%;
	}

	#eduCenterPublicModal .menu_wrap ul.nav li a {
		border-radius: 0;
		text-align: center;
		margin: 0;
		font-weight: bold;

		background: url(/common/img/edu/menu_ul_bg.png);
		border: 0 none;
	    color: #ccc;
	}

	#eduCenterPublicModal .menu_wrap ul.nav li:first-child a {
		border-right: 1px solid #000;
	}

	#eduCenterPublicModal .menu_wrap ul.nav li.active a {
	    background: url(/common/img/edu/menu_ul_bg_active.png);
	    color: #337ab7;
	}

/* 탭 컨텐츠 */
	#eduCenterPublicModal .menu_wrap .tab-content {
		padding: 10px 10px 0 10px;
		color: #fff;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .image_wrap {
	    float: left;
	    margin-right: 10px;
	    position: relative;
	    text-align: right;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .image_wrap p {
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

	#eduCenterPublicModal .menu_wrap .tab-content .ul_lecture_list {
		margin-bottom: 0;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .ul_lecture_list li {
		overflow: hidden;
	    background: #2d2d32;
	    padding: 8px;
	    margin-bottom: 10px;
	    cursor: pointer;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .ul_lecture_list li.active {
		border: 1px solid #0099e0;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .info p {
		text-overflow: ellipsis;
		overflow: hidden;
		word-break: break-all;
		white-space: nowrap;

		margin: 10px 0;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .info .label {
		border-radius: 10px;
		line-height: 2;
	}

	#eduCenterPublicModal .menu_wrap .tab-content hr {
		border-color: #4b4b59;
		margin: 10px 0;
	}

	#eduCenterPublicModal .menu_wrap .tab-content .lecture_detail ul,
	#eduCenterPublicModal .menu_wrap .tab-content .lecture_detail ol {
		padding-left: 20px;
	}
	
	.text-pre-detail {
	    width: 300px;
	}
</style>
 
<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	<video id="my-player" class="video-js" >
	<source src="<c:url value='/cmm/fms/getMovie.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=<c:out value="${fileVO.fileSn}"/>" type="video/mp4"></source>
	<source src="<c:url value='/cmm/fms/getMovie.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=<c:out value="${fileVO.fileSn}"/>" type="video/webm"></source>
	<source src="<c:url value='/cmm/fms/getMovie.do'/>?atchFileId=<c:out value="${fileVO.atchFileId}"/>&fileSn=<c:out value="${fileVO.fileSn}"/>" type="video/ogg"></source>
	<p class="vjs-no-js">
		To view this video please enable JavaScript, and consider upgrading to a web browser that
		<a href="http://videojs.com/html5-video-support/" target="_blank">
				supports HTML5 video
		</a>
	</p>
	</video>
</c:forEach>

<script>
var options = {
		"controls": true,
		"autoplay": true,
	    "preload": "auto",
	    "poster": "${info.FILE_ORG_NM}.jpg",
	    //"loop": "true",//주석을 풀면 무조건 반복한다?!
	    "width": 768,
	    "height": 432,
	    "seek": true 
	};
	var player = videojs('my-player', options, function onPlayerReady() {
		videojs.log('onPlayerReady!!!!');
		this.on('ready', function() {
			videojs.log('ready!!!!');
		});
		this.on('ended', function() {

		});
		this.on('pause', function() {

		});
		this.on('play', function() {
			
		});
		this.on('timeupdate', function(){
			
		});
		this.on("seeking", function(){ 
			
		});
		this.on('error', function(e) {
			
		});
	});

</script>
 --%>
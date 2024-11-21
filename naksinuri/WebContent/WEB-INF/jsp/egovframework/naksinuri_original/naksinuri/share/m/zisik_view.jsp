<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>



<c:set var="pageMode" value="share"/>
<c:set var="depthNum" value="3" />
<c:set var="depthName" value="share" />
<c:set var="pageName" value="zisik" />



<%@include file="../../layout/m/head.jsp"%>

<div id="qnaView" class="content respon2">
		<section id="qnaViewQuestion" class="qnabox">
			<div class="ico"><img src="/naksinuri_original/common_main/img/ico_qna_q.png" alt="" /></div>
			<div class="tit">
				<h2>초보가 접하기엔 어떤 장르가 좋을지 각각 루어, 원투 낚시의 특징</h2>
				<span class="name"><i class="fa fa-user" aria-hidden="true"></i> 함문혁</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2015-06-17</span>
			</div>
			<div class="comment">
				안녕하세요. 학생인데요 낚시가 배워보고 싶어서 이렇게 글 남깁니다. 낚시 종류에는 루어, 원투 등등이 있던데..낚시의 '낚'자도 모르는 초보가 접하기엔 어떤 장르가 좋을지 각각 루어 , 원투 낚시의 특징 혹은 장단점을 알려주세요~
			</div>
			<div id="btnArea" class="noupline">
				<ul class="floats">
					<li class="fr">
						<a href="#;" class="btn_list btn_gray">수정</a>
					</li>
				</ul>
			</div>
		</section>

		<section id="qnaViewAnswer" class="qnabox">
			<div class="ico"><img src="/naksinuri_original/common_main/img/ico_qna_a.png" alt="" /></div>
			<div class="tit">
				<h2>Re : 초보가 접하기엔 어떤 장르가 좋을지 각각 루어, 원투 낚시의 특징</h2>
				<span class="name"><i class="fa fa-user" aria-hidden="true"></i> 함문혁</span><span class="date"><i class="fa fa-clock-o" aria-hidden="true"></i> 2015-06-17</span>
			</div>
			<div class="comment">
				서울이나 경기도 근교의 낚시터는 대부분 유료낚시터화 되어 있습니다. 따라서 인터넷에서 찾아보면 각각의 낚시터 블로그나 카페가 운영되고 있으니 참고 하시되, 필히 낚시를 갈 시기에 맞춰서 가고싶은 낚시터명을 검색창에서 검색해 보세요. 그러면 최근 조행기나 조황소식을 바로 볼 수 있습니다. 더 정확한 것은 가고 싶은 지역의 낚시점에 들러서 간단한 미끼를 사면서 안내를 받는 것이 최상입니다.<br /><br />
				* 낚시상황은 수시로 변동이 심하여 미리 어느 낚시터를 추천하는 것은 자칫 거짓말이 될 수 있으니 이렇게 답글을 씁니다.
			</div>
		</section>

		<section id="qnaViewWait" class="qnabox">
			<div class="icos"><img src="/naksinuri_original/common_main/img/ico_qna_wait.png" alt="" /></div>
			<div class="tit">
				<h2>현재 답변을 등록중입니다.<br />조금만 기다려주세요.</h2>
			</div>
		</section>

		<div id="btnArea">
			<ul class="floats">
				<li><a href="#;" class="btn_prev btn_white"><i class="fa fa-angle-left" aria-hidden="true"></i> 이전글</a></li>
				<li class="fr"><a href="#;" class="btn_next btn_white">다음글 <i class="fa fa-angle-right" aria-hidden="true"></i></a></li>
			</ul>
			<a href="#;" class="btn_request btn_blue">질문하기</a>
			<a href="#;" class="btn_list btn_gray">목록</a>
		</div>
	</div>
	
<%@include file="../../layout/m/tail.jsp"%>
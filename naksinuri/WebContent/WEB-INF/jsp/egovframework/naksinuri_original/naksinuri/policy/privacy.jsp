<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="privacy"/>
<c:set var="depthNum" value="0" />
<c:set var="depthName" value="사이트이용안내" />
<c:set var="pageName" value="개인정보처리방침" />

<div class="privacy_layout">

<%@include file="../layout/newHead.jsp"%>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<style>

html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, 
p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, 
em, img, ins, kbd, q, s, samp, small, strike, strong, 
sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, 
fieldset, form, label, legend, table, 
caption, tbody, tfoot, thead, tr, th, td, 
article, aside, canvas, details, embed, figure,  
figcaption, footer, header, hgroup, 
menu, nav, output, ruby, section, summary, time, mark, audio, video {margin:0; padding:0; border:0;}

	.bold {font-weight: bold;}
	.underBold {text-decoration: underline;font-weight: bold;}
	.underBold-red {text-decoration: underline;font-weight: bold;color: #ff0000; font-size: 17px;}
	.text-center, tr th {text-align: center;}
	.word-spacing {word-spacing: -0.5px;}
	.height71 td {height: 71.2px;}
	.margin-left7 {margin-left: 7px;}

	.pvc_infoLabel {display: flex; flex-wrap: wrap; justify-content: space-between; width: 100%; margin-top: 5em; margin-bottom:2em;}
	.pvc_infoLabel .lb_title {text-align: center; padding: 10px; background-color: #black; width: 100%; margin-bottom: 10px;}
	.pvc_infoLabel .label_info {position: relative; display: flex; flex-direction: column; justify-content: flex-start; align-items: center; border: 1px solid #ccc; width: calc(100% / 3 - 11px); margin : 0 -1px -1px 0; margin-bottom:1em; text-align: center; padding: 30px 5px;}
	.pvc_infoLabel .label_info p.img {display:flex; width:100px; height:100px; background:url(/naksinuri_original/images/policy/pvc_group.png) no-repeat; background-position:0 0;}
	.pvc_infoLabel .label_info p.img.label02 {background-position:-100px 0;}
	.pvc_infoLabel .label_info p.img.label03 {background-position:-200px 0;}
	.pvc_infoLabel .label_info p.img.label04 {background-position:-300px 0;}
	.pvc_infoLabel .label_info p.img.label05 {background-position:-100px -100px;}
	.pvc_infoLabel .label_info p.img.label06 {background-position:-700px -100px;}

	@media screen and (max-width:640px) {
	.pvc_infoLabel .label_info {width: calc(100% / 2 - 11px);}
	}
	
	.pvc_infoLabel .label_info dl dt {position:relative; font-size: 17px; font-weight: 600; color: #4576da;}
	.pvc_infoLabel .label_info dl dt:after {content:''; display:block; margin:10px auto; width:1.6rem; height:1px; background: #4576da;}


	.pvc_infoList {position: relative; }
	.pvc_infoList ul {display: flex; flex-wrap: wrap; justify-content: space-between; margin: 0; width: 100%;}
	.pvc_infoList ul li {position: relative; display: flex; flex-direction: row; justify-content: flex-start; width: calc(100% / 2); text-align: left; letter-spacing: -1px;}
	.pvc_infoList ul li a > span {font-size:15px; font-weight: 600;}
	.pvc_infoList ul li a > span:hover, .guid_infoList ul li span:focus {color: #4576da;}
	.pvc_infoList ul li a > span:before {content:''; display: inline-block; vertical-align: middle; width:100px; height:100px; background:url(/naksinuri_original/images/policy/pvc_group.png) no-repeat; background-position:0 0; margin-right: 10px;}
	.pvc_infoList ul li a.icon1 > span:before {background-position:-100px 0;}
	.pvc_infoList ul li a.icon2 > span:before  {background-position:-200px 0;}
	.pvc_infoList ul li a.icon3 > span:before  {background-position:-100px -100px;}
	.pvc_infoList ul li a.icon4 > span:before  {background-position:-300px 0}
	.pvc_infoList ul li a.icon5 > span:before  {background-position:-200px -100px;}
	.pvc_infoList ul li a.icon6 > span:before  {background-position:0 -100px;}
	.pvc_infoList ul li a.icon7 > span:before  {background-position:-300px -100px;}
	.pvc_infoList ul li a.icon8 > span:before  {background-position:-400px -100px;}
	.pvc_infoList ul li a.icon9 > span:before  {background-position:-500px -100px;}
	.pvc_infoList ul li a.icon10 > span:before  {background-position:-600px -100px;}
	.pvc_infoList ul li a.icon11 > span:before  {background-position:-700px -100px;}
	.pvc_infoList ul li a.icon12 > span:before  {background-position:-800px -100px;}
	.pvc_infoList ul li a.icon13 > span:before  {background-position:-1000px -100px;}
	.pvc_infoList ul li a.icon14 > span:before  {background-position:-500px 0}
	.pvc_infoList ul li a.icon15 > span:before  {background-position:-600px 0}
	
	#step01,
	#step02,
	#step03,
	#step04,
	#step05,
	#step06,
	#step07,
	#step08,
	#step09,
	#step10,
	#step11,
	#step12,
	#step13
	#step14
	#step15
	#step16 {
	  scroll-margin-top: 120px; /* 상단 헤더 사이즈 */
	}

.privacy_layout {
            width: 100%;
        }
        .privacy_layout .contents {
            display: flex;
        }
        .privacy_layout .contents > :nth-child(1), .privacy_layout .contents > :nth-child(3) {
            flex-basis: 20%;
        }
        .privacy_layout .contents > :nth-child(2) {
            flex-basis: 60%;
        }
        
@media (max-width: 700px) { /* 원하는 화면 너비로 변경 */
      .con {
        flex-direction: column-reverse;
      }
    }
@media screen and (max-width:1000px) {
	.pvc_infoList ul li {width: calc(100%);}
	
    .privacy_layout .contents > :nth-child(1) > img {
            display: none;
     }
    .privacy_layout .contents > :nth-child(3) > img {
            display: none;
     }
     
    .privacy_layout .contents {display: block; }

}



.pvc_infoLabel {
  display: flex;
}

.label_info {
  position: relative;
}

.label_info p {
  cursor: pointer;
}

.label_info dl {
  position: absolute;
  top: -100%;
  left: 0;
  width: 100%;
  height: 100%;
  display: none;
  padding: 10px;
  background-color: rgba(255, 255, 255, 0.9);
  backdrop-filter: blur(5px);
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
  animation: slideDown 0.3s ease-in-out forwards;
}

@keyframes slideDown {
  0% { top: -100%; opacity: 0; }
  100% { top: 0; opacity: 1; }
}

.label_info:focus dl,
.label_info:hover dl {
  display: block;
}

.label_info dl {
  opacity: 0;
  transform: translateY(-10px);
  transition: opacity 0.3s ease-in-out, transform 0.3s ease-in-out;
  
}

.label_info:focus dl,
.label_info:hover dl {
  opacity: 1;
  transform: translateY(0);
}

.label_info dl dt,
.label_info p {
  font-weight: bold;
  font-size: 18px;
}

.label_info dl dd {
  display: none;
  text-align: center;
  line-height: 1.5;
}

.label_info:focus-within dl dd,
.label_info:hover dl dd {
  display: block;
}
@media (max-width: 1000px) {
  /* 특정 사이즈(예: 768px) 밑으로 내려갈 때 호버 기능 끄기 (라벨링 부분 호버) */
  .label_info:hover dl {
    display: none;
  }
@media (max-width: 1280px) {
		#section_main {
            display: block;
        }
        #section_side,#section_side2 {
            display: none;
        }
}

.basic_tbl a {
    display: inline; /* 인라인으로 표시 */
    width: auto; /* 너비 자동으로 설정 */
    height: auto; /* 높이 자동으로 설정 */
    line-height: normal; /* 라인 높이 초기화 */
    margin: 0; /* 여백 초기화 */
    background-color: transparent; /* 배경 색상 초기화 */
    text-align: inherit; /* 텍스트 정렬 초기화 */
    color: inherit; /* 텍스트 색상 초기화 */
    font-weight: inherit; /* 폰트 굵기 초기화 */
    border-radius: 0; /* 테두리 반경 초기화 */
}
</style>

<article class="contents">
<section id="section_side"><img src="/naksinuri_original/images/policy/side_L.png" alt="" /></section>

<section id="section_main">

	<div id="privacy" class="content respon3">
		<section id="privacyCon">
			<div class="con" style="border: 1px solid; background: white; padding: 10px; height: auto; width: auto; display: flex; align-items: center;">
			  <img style="width: 150px; height: 92px;" src="/naksinuri_original/images/policy/ico_fiparang05.png" alt="" />
			  <div>
			  	<br>
			    <h3 class="">낚시누리(www.naksinuri.kr) 개인정보 처리방침</h3>
			    <p class="">한국어촌어항공단이 해양수산부로부터 위탁받아 운영하는 낚시누리(www.naksinuri.kr)(이하 “낚시누리”)는<br>개인정보 보호법 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리방침을 수립․공개합니다.</p>
			    <br>
			  </div>
			</div>
			
			<div  class="con">
				
					<h3 style="font-size: 20px; color: #023c90; line-height: 24px; font-weight: bold; margin-bottom: 20px; text-align: center; display: flex; align-items: center; justify-content: center;">
    				주요 개인정보 처리 표시(라벨링)
    				<img style="width: 112px; height: 143px; margin-left: 5px;" src="/naksinuri_original/images/policy/adong1.png" alt="" />
					</h3>
					<div class="pvc_infoLabel">
					  <div class="label_info" tabindex="0">
						<p class="img label01" title="개인정보수집"></p>
						<dt>일반 개인정보 수집</dt>
						<dl>
						  <dd style="font-weight: bold;">-필수항목-</dd>
						  <dd>성명,생년월일,주소</dd>
						  <dd>휴대전화번호,허가(등록·신고 시·군·구명 등</dd>
						  <dd style="font-weight: bold;">-선택항목-</dd>
						  <dd>전화번호,낚시터·낚시어선 명칭</dd>
						  <dd>허가(등록)·신고증 번호 등</dd>
						</dl>
					  </div>
					  <div class="label_info" tabindex="0">
						<p class="img label02" title="개인정보 처리목적"></p>
						<dt>개인정보의 처리 목적</dt>
						<dl>
						 <br>
						  <dd>낚시전문교육 교육대상자의 신원 확인</dd>
						  <dd>교육 이력 관리</dd>
						  <dd>교육 및 관련 정책 안내 등</dd>
						</dl>
					  </div>
					  <div class="label_info" tabindex="0">
						<p class="img label03" title="개인정보의 보유기간"></p>
						<dt>개인정보의 보유기간</dt>
						<dl>
							<br>
						  <dd style="font-weight: bold;">2년</dd>
						  <dd>“낚시누리”는 개인정보 보유기간의 경과,처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체 없이 해당 개인정보를 파기합니다.</dd>
						</dl>
					  </div>
					  <div class="label_info" tabindex="0">
						<p class="img label04" title="개인정보 처리위탁"></p>
						<dt>개인정보 처리위탁</dt>
						<dl>
							<br>
							<br>
						  <dd>에스엠유㈜</dd>
						  <dd>한국해양수산연수원</dd>
						  <dd>(사)한국낚시업중앙회</dd>
						</dl>
					  </div>
					  <div class="label_info" tabindex="0">
						<p class="img label05" title="개인정보 제공"></p>
						<dt>개인정보 제공</dt>
						<dl>
							<br>
							<br>
						  <dd>(아래 제5조 조항 참고)</dd>
						</dl>
					  </div>
					  <div class="label_info" tabindex="0">
						<p class="img label06" title="개인정보 열람 청구"></p>
						<dt>개인정보 열람 청구</dt>
						<dl>
							<br>
							<br>
						  <dd>개인정보 열람청구 책임자</dd>
						  <dd>교육운영팀</dd>
						  <dd>02-6098-0853</dd>
						</dl>
					  </div>
					</div>
					<h3 style="font-size: 20px;color:#023c90;line-height:24px;font-weight:bold;margin-bottom:20px; text-align:center;">[ 자세한 내용은 아래의 개인정보 처리방침을 확인하시기 바랍니다. ]</h3>
					<br>
					<br>
			</div>
			
			<div class="pvc_infoList">
			    <ul>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step01" class="icon1"><span>개인정보의 처리목적</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step02" class="icon2"><span>개인정보의 처리 및 보유기간</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step03" class="icon6"><span>처리하는 개인정보 항목</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step04" class="icon14"><span>개인정보 영향평가 수행 결과</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step05" class="icon3"><span>개인정보의 제3자 제공</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step06" class="icon4"><span>개인정보처리의 위탁</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step07" class="icon7"><span>개인정보의 파기</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step08" class="icon5"><span>정보주체와 법정대리인의 권리&middot;의무 및 행사방법</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step09" class="icon8"><span>개인정보의 안전성 확보조치</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step10" class="icon9"><span>개인정보 자동수집장치의 설치&middot;운영 및 거부에 관한 사항</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step11" class="icon15"><span>가명정보 처리에 관한 사항</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step12" class="icon10"><span>개인정보 보호책임자 및 담당자</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step13" class="icon11"><span>개인정보를 열람청구</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step14" class="icon12"><span>권익침해 구제방법</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step15" class="icon14"><span>개인정보 관리수준 진단 결과</span></a></li>
			        <li><a style="width: auto; height: auto; background-color: initial; color: initial; text-align:left; margin:0; margin-bottom:20px;" href="#step16" class="icon13"><span>개인정보 처리방침 변경</span></a></li>
			    </ul>
			</div>
			
			
			<div id="step01" class="con">
			
			<div>
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_01.png" alt="" />제1조(개인정보의 처리목적)</h3>
				
			</div>
				<p class="dottedbox">
					① “낚시누리”는 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행할 예정입니다.
				</p>
				<!-- <p class="dottedbox">1. 낚시전문교육 민원사무 처리 및 서비스 제공 <br>
					<span class="underBold"> 낚시전문교육 교육 대상자의 신원 확인, 교육사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보 등의 민원사무 처리와 교육 콘텐츠 제공, 본인인증, 교육 이수증 발급 등 서비스 제공에 관련한 목적</span>으로 개인정보를 처리합니다. 
				</p> -->
				<p class="dottedbox">
					②“낚시누리”가 개인정보보호법에 따라 등록․공개하는 개인정보파일의 처리목적은 다음과 같습니다.
				</p>
				<table class="basic_tbl mgt10px">
					<caption>개인정보의 처리목적 리스트</caption>
						<colgroup>
							<col class="wp10" />
							<col class="wp20"/>
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>개인정보파일명칭</th>
								<th>운영근거</th>
								<th>처리목적</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">1</td>
								<td class="text-center">낚시전문교육<br>교육대상자 명단</td>
								<td class="word-spacing">낚시 관리 및 육성법 제47조, 시행령 제23조, 낚시전문교육 및 교육기관 지정에 관한 고시, 정보주체 동의</td>
								<td class="underBold">낚시전문교육 교육대상자의 신원 확인, 교육 이력 관리, 교육 및 관련 정책 안내, 처리결과 통보, 교육 이수증 발급</td>
							</tr>
 							<!-- <tr>
	                            <td class="text-center">2</td>
	                            <td class="text-center">수산공익직불제<br>교육 대상자 명단</td>
	                            <td class="word-spacing">수산업·어촌 공익기능 증진을 위한 직접지불제도 운영에 관한 법률 제19조, 시행령 제12조, 시행규칙 제17조</td>
	                            <td class="underBold">수산공익직불제교육 교육대상자의 신원 확인, 교육 이력 관리, 교육 및 관련 정책 안내, 처리결과 통보, 교육 이수증 발급</td>
	                        </tr> -->							
							<!-- <tr>
								<td class="text-center bold">2</td>
								<td class="text-center bold">낚시명예감시원<br>위촉대상자 명단</td>
								<td class="word-spacing bold">낚시 관리 및 육성법 제46조, 낚시명예감시원 위촉 등에 관한 고시, 정보주체 동의</td>
								<td class="underBold">낚시명예감시원 위촉대상자의 신원 확인, 활동 이력 관리, 활동 및 관련 정책 안내, 결과 통보, 활동수당 지급</td>
							</tr> -->
						</tbody>
					</table>
				<p class="dottedbox mgt10px"> ※ 보다 상세한 “낚시누리”의 개인정보파일 등록사항 공개는 행정안전부 개인정보보호 종합지원 포털(www.privacy.go.kr) → 민원마당 → 개인정보열람등 요구 → 개인정보파일 목록검색 메뉴를 활용해주시기 바랍니다.</p>
				
			</div>
			
			<div id="step02" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_02.png" alt="" />제2조(개인정보의 처리 및 보유기간)</h3>
				<p class="dottedbox">
					① “낚시누리”는 법령에 따른 개인정보 보유·이용기간 또는 정보주체로부터 개인정보를 수집 시에 동의 받은 개인정보 보유·이용기간 내에서 개인정보를 처리·보유합니다. 
				</p>
				<p class="dottedbox">
					② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.  
				</p>
				<table class="basic_tbl mgt10px text-center">
					<caption>개인정보의 처리 및 보유기간 리스트</caption>
						<colgroup>
							<col class="wp10" />
							<col class="wp20"/>
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>개인정보파일명칭</th>
								<th>처리</th>
								<th>보유기간<br>(목적 달성시)</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>낚시전문교육<br>교육대상자 명단</td>
								<td>서비스 제공 및 민원 사무 처리</td>
								<td><span class="underBold-red">2년</span></td>
							</tr>
	                        <!-- <tr>
	                            <td>2</td>
	                            <td>수산공익직불제<br>교육대상자 명단</td>
	                            <td>서비스 제공 및 민원 사무 처리</td>
	                            <td><span class="underBold-red">2년</span></td>
	                        </tr> -->							
							<!-- <tr>
								<td>2</td>
								<td>낚시명예감시원<br>위촉대상자 명단</td>
								<td><span class="underBold-red">낚시명예감시원<br>활동기간</span></td>
							</tr> -->
						</tbody>
					</table>
			</div>
			
			<div id="step03" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_06.png" alt="" />제3조(처리하는 개인정보 항목)</h3>
				<p>“낚시누리”는 다음의 개인정보 항목을 처리하고 있습니다.</p>
				<table class="basic_tbl mgt10px">
					<caption>처리하는 개인정보 항목 리스트</caption>
						<colgroup>
							<col class="wp10" />
							<col class="wp20"/>
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>개인정보파일명칭</th>
								<th>필수항목</th>
								<th>선택항목</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="text-center">1</td>
								<td class="text-center">낚시전문교육<br>교육대상자 명단</td>
								<td class="underBold word-spacing">수강인구분, 성명, 생년월일, 주소, 휴대전화번호, 허가(등록)·신고 시·군·구명</td>
								<td class="underBold">전화번호, 낚시터·낚시어선 명칭, 허가(등록)·신고증 번호, 어선번호(낚시어선에 한함)</td>
							</tr>
	                        <!-- <tr>
	                            <td class="text-center">2</td>
	                            <td class="text-center">수산공익직불제<br>교육대상자 명단</td>
	                            <td class="underBold word-spacing">직불제구분, 성명, 생년월일, 휴대전화번호</td>
	                            <td class="underBold">전화번호, 주소, 시·군·구명, 법인또는단체명</td>
	                        </tr> -->							
							<!-- <tr>
								<td class="text-center bold">2</td>
								<td class="text-center bold">낚시명예감시원<br>위촉대상자 명단</td>
								<td class="underBold word-spacing bold">성명(한글, 한자, 영문), 생년월일, 사진, 주소, 이메일, 연락처(전화번호, 핸드폰), 전공, 경력, 자격 사항(면허, 자격증)</td>
								<td class="underBold bold">해당없음</td>
							</tr> -->
						</tbody>
					</table>
			</div>
			
			<div id="step04" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_14.png" alt="" />제4조(개인정보 영향평가 수행 결과)</h3>
				
				<p class="dottedbox">① 한국어촌어항공단이 운영하고 있는 개인정보 처리시스템 "낚시누리"는 「개인정보 보호법」 제33조에 따라 “개인정보 영향평가”의 대상에 해당되지 않습니다.</p>
				<p class="dottedbox">② 한국어촌어항공단은 개인정보파일에 대해 영향평가를 수행할 시 결과를 기재하겠습니다.</p>
				
			</div>
			
			<div id="step05" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_03.png" alt="" />제5조(개인정보의 제3자 제공)<img style="width: 112px; height: 143px; margin-left:5px" src="/naksinuri_original/images/policy/adong3.png" alt="" /></h3>
				
				<p class="dottedbox">① “낚시누리”는 원칙적으로 정보주체의 개인정보를 수집 · 이용 목적으로 명시한 범위 내에서 처리하며, 다음의 경우를 제외하고는 정보주체의 사전 동의 없이는 본래의 목적 범위를 초과하여 처리하거나 제3자에게 제공하지 않습니다.</p>
				<p class="dottedbox">
				<ol>
					<li>
						<p class="margin-left7">ㆍ 정보주체로부터 별도의 동의를 받은 경우</p>
						<p class="margin-left7">ㆍ 다른 법률에 특별한 규정이 있는 경우</p>
						<p class="margin-left7">ㆍ 정보주체 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 정보주체 또는 제3자의 급박한 생명, 신체, 재산</p>
						<p class="margin-left7">&emsp; 의 이익을 위하여 필요하다고 인정되는 경우</p>
						<p class="margin-left7">ㆍ 개인정보를 목적 외의 용도로 이용하거나 이를 제3자에게 제공하지 아니하면 다른 법률에서 정하는 소관 업무를 수행할 수 없는 경우로서 보호위원회의 심의ㆍ의결을 거친 경우</p>
						<p class="margin-left7">ㆍ 조약, 그 밖의 국제협정의 이행을 위하여 외국정부 또는 국제기구에 제공하기 위하여 필요한 경우</p>
						<p class="margin-left7">ㆍ 범죄의 수사와 공소의 제기 및 유지를 위하여 필요한 경우</p>
						<p class="margin-left7">ㆍ 법원의 재판업무 수행을 위하여 필요한 경우</p>
						<p class="margin-left7">ㆍ 형(刑) 및 감호, 보호처분의 집행을 위하여 필요한 경우</p>
					</li>
				</ol>
				</p> 
			</div>
			
			
			
			<div id="step06" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_04.png" alt="" />제6조(개인정보처리의 위탁)</h3>
				<p class="dottedbox">① “낚시누리”는 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다.</p>
				<table class="basic_tbl mgt10px text-center height71">
					<caption>개인정보처리의 위탁 리스트</caption>
						<colgroup>
							<col class="wp10" />
							<col />
							<col class="wp20"/>
							<col />
							<col class="wp10"/>
						</colgroup>
						<thead>
							<tr>
								<th>순번</th>
								<th>위탁받는 자(수탁자)</th>
								<th>수탁자 연락처</th>
								<th>위탁하는 업무의 내용</th>
								<th>실태점검 현황</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="bold">1</td>
								<td class="bold">에스엠유㈜</td>
								<td class="bold">032-209-1330</td>
								<td class="bold">낚시정보종합포털 내부시스템 운영<br>지원 및 시스템 유지보수</td>
								<td class="bold">O</td>
							</tr>
							<tr>
								<td>2</td>
								<td>한국해양수산연수원</td>
								<td>051-624-4521</td>
								<td>낚시어선 신규재개자 교육 운영</span></td>
								<td>O</td>
							</tr>
							<tr>
								<td>3</td>
								<td>(사)한국낚시업중앙회</td>
								<td>031-227-0744</td>
								<td>낚시터 전문 현장교육 운영</span></td>
								<td>O</td>
							</tr>
							<tr>
								<td>4</td>
								<td>군산대학교산학협력단</td>
								<td>063-469-7553</td>
								<td>낚시어선 전문 현장교육 운영</span></td>
								<td>O</td>
							</tr>
							<!-- <tr>
								<td>5</td>
								<td>한국낚시단체총연합회</td>
								<td>02-494-2733</td>
								<td>낚시명예감시원 업무 수행을 위한<br>위촉대상자 정보 처리</td>
								<td>O</td>
							</tr> -->
						</tbody>
					</table>
				<p class="dottedbox mgt10px">②“낚시누리”는 개인정보 보호법 제26조에 따라 개인정보 처리업무 위탁계약 체결 시 아래의 내용이 포함된 문서에 의하여 처리하고 있습니다.</p>
				<p class="depth2">1) 위탁업무 수행목적 외 개인정보 처리 금지에 관한 사항</p>
				<p class="depth2">2) 개인정보의 기술적·관리적 보호조치에 관한 사항</p>
				<p class="depth2">3) 개인정보의 안전한 관리에 관한 사항: 위탁업무의 목적 및 범위, 재위탁 제한에 관한 사항, 개인정보 안전성 확보 조치에 관한 사항,
					 위탁업무와 관련하여 보유하고 있는 개인<br>정보의 관리현황 점검 등 감독에 관한 사항, 수탁자가 준수하여야 할 의무를 위반한 경우의 손해배상 책임에 관한 사항
				</p>
				<p class="dottedbox">③ 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체 없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.</p>
			</div>
			
			<div id="step07" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_07.png" alt="" />제7조(개인정보의 파기)</h3>
				
				<p class="dottedbox">① “낚시누리”는 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체 없이 해당 개인정보를 파기합니다.</p>
				<p class="dottedbox">② 정보주체로부터 동의 받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는, 해당 개인정보<br>(또는 개인정보파일)을 별도의 데이터베이스(DB)로 옮기거나 보관 장소를 달리하여 보존합니다.</p>
				<p class="dottedbox">③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.</p>
				<p class="dottedbox">
				1. 파기절차
				<ol>
					<li>“낚시누리”는 개인정보 보호책임자의 책임 하에 개인정보 파기계획에 따라 다음과 같이 처리하고 있습니다.
						<p class="margin-left7">ㆍ개인정보의 파기보유기간이 경과한 개인정보는 종료일로부터 지체 없이 파기합니다.</p>
						<p class="margin-left7">ㆍ개인정보파일의 처리 목적 달성 등 그 개인정보파일이 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 지체 없이 그 개인정보파일을 파기합니다.</p>
					</li>
				</ol>
				</p> 
				<p class="dottedbox">
				2. 파기방법 
				<ol>
					<li> “낚시누리”는 전자적 파일 형태로 기록·저장된 개인정보는 기록을 재생할 수 없도록 로우레벨포멧(Low Level Format) 등의 방법을 이용하여 파기하며, 종이 문서에 기록·저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다.</li>
				</ol>
				</p>
			</div>
			
			<div id="step08" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_05.png" alt="" />제8조(정보주체와 법정대리인의 권리·의무 및 행사방법)</h3>
				<p class="dottedbox">① 정보주체와 법정대리인은 “낚시누리”에 대해 언제든지 개인정보 열람·정정·삭제·처리정지 요구 등의 권리를 행사할 수 있습니다.   
				</p>
				<p class="dottedbox">② 제1항에 따른 권리 행사는 “낚시누리”에 대해 개인정보 보호법 시행령 제41조제1항에 따라 서면, 전자우편, 모사전송(FAX) 등을 통하여 하실 수 있으며, “낚시누리”는 이에 대해 지체 없이 조치하겠습니다.</p>
				<p class="dottedbox">③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 <span class="bold">개인정보보호위원회 개인정보보호지침 별지 제2호 서식</span>에 따른 위임장 및 신분을 확인할 수 있는 증명서(주민등록증 등)를 제출하셔야 합니다.</p>
				<p class="dottedbox">④ 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조제4항, 제37조제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.</p>
				<p class="dottedbox">⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</p>
				<p class="dottedbox">⑥ “낚시누리”는 정보주체 권리에 따른 열람·정정·삭제·처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.</p>			
				<div class="downloadbox mgt30px text-center">
					<a href="#;" onclick="fn_egov_downFile('FILE_000000000000000','4')" class="bold">[개인정보보호위원회 개인정보보호지침 별지 제1호]<br>개인정보(열람, 정정·삭제, 처리정지) 요구서</a>
					<a href="#;" onclick="fn_egov_downFile('FILE_000000000000000','3')" class="bold">[개인정보보호위원회 개인정보보호지침 별지 제2호]<br>위임장</a>
					
				</div>
			</div>
			
			
			<div id="step09" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_08.png" alt="" />제9조(개인정보의 안전성 확보조치)<img style="width: 103px; height: 130px; margin-left:5px" src="/naksinuri_original/images/policy/ico_fiparang02.png" alt="" /></h3>
				<p class="dottedbox">① “낚시누리”는 개인정보의 안전성 확보를 위해 개인정보보호법 제29조에 따라 다음과 같은 조치를 취하고 있습니다.</p>
				<p>1. 관리적 조치: 내부관리계획 수립·시행, 개인정보 취급직원의 최소화 및 교육, 접속기록 보관 등</p>
				<p>2. 기술적 조치: 접근로그 생성, 개인정보처리시스템 등의 접근권한 관리, 접근통제시스템 설치, 개인정보 등의 암호화, 보안프로그램 설치 및 주기적 점검·갱신</p>
				<p>3. 물리적 조치: 전산실, 자료보관실 등 비인가자 접근통제</p>
			</div>
			<div id="step10" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_09.png" alt="" />제10조(개인정보 자동 수집 장치의 설치∙운영 및 거부에 관한 사항)</h3>
				<p class="dottedbox">① “낚시누리”는 이용자에게 개별적인 맞춤서비스를 제공하기 위해 이용정보를 저장하고 수시로 불러오는 ‘쿠키(cookie)’를 사용합니다.</p>
				<p class="dottedbox">② 쿠키는 웹사이트를 운영하는데 이용되는 서버(http)가 이용자의 컴퓨터 브라우저에게 보내는 소량의 정보이며 이용자의 PC 컴퓨터내의 하드디스크에 저장되기도 합니다.</p>
				<p>
					<ol class="depth2">
						<li>가. 쿠키의 사용목적: 이용자가 방문한 각 서비스와 웹 사이트들에 대한 방문 및 이용형태, 인기 검색어, 보안접속 여부 등을 파악하여 이용자에게 최적화된 정보 제공을 위해 사용됩니다.</li>
						<li>나. 쿠키의 설치∙운영 및 거부 : 웹브라우저 상단의 도구>인터넷 옵션>개인정보 메뉴의 옵션 설정을 통해 쿠키 저장을 허용/거부하거나, 쿠키가 저장될 때마다 확인을 거치도록 할 수 있습니다.</li>
						<li>다. 쿠키 저장을 거부할 경우 맞춤형 서비스 이용에 어려움이 발생할 수 있습니다.</li>
					</ol>
				</p>
   			</div>
   			
   			<div id="step11" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_15.png" alt="" />제11조(가명정보 처리에 관한 사항)</h3>
				
				<p class="dottedbox">① 개인정보를 가명처리 하는 경우, 관련사항은 "낚시누리"에 게재하여 정보주체가 확인할 수 있도록 안내하고 있습니다.</p>
				
			</div>
   			
			<div id="step12" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_10.png" alt="" />제12조(개인정보 보호책임자 및 담당자)</h3>
				<p class="dottedbox">① “낚시누리”는 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자 및 담당자를 지정하고 있습니다.</p>
				<table class="basic_tbl mgt10px text-center">
					<caption>개인정보 보호책임자 및 담당자 리스트</caption>
						<colgroup>
							<col />
							<col />
							<col />
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>구분</th>
								<th>부서명</th>
								<th>성명</th>
								<th>전화번호</th>
								<th>이메일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="bold">개인정보 보호책임자<img style="width: 15px; height: 25px; margin-left:5px" src="/naksinuri_original/images/policy/ico_fiparang04.png" alt="" /></td>
								<td class="bold">디지털정보실</td>
								<td class="bold">안경호</td>
								<td class="bold">02-6098-0751</td>
								<td class="bold">akh059@fipa.or.kr</td>
							</tr>
							<tr>
								<td class="bold">개인정보 보호담당자<img style="width: 15px; height: 25px; margin-left:5px" src="/naksinuri_original/images/policy/ico_fiparang02.png" alt="" /></td>
								<td class="bold">디지털정보실</td>
								<td class="bold">김지현</td>
								<td class="bold">02-6098-0752</td>
								<td class="bold">kjh097@fipa.or.kr</td>
							</tr>
						</tbody>
					</table>
					<p class="dottedbox mgt10px">② 정보주체는 “낚시누리”의 서비스(또는 사업)을 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자 및 담당부서로 문의하실 수 있습니다. “낚시누리”는 정보주체의 문의에 대해 지체 없이 답변 및 처리해 드릴 것입니다.</p>
			</div>
			<div id="step13" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_11.png" alt="" />제13조(개인정보 열람청구)<img style="width: 112px; height: 143px; margin-left:5px" src="/naksinuri_original/images/policy/adong3.png" alt="" /></h3>
				<p class="dottedbox">① 정보주체와 법정대리인은 개인정보 보호법 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다. “낚시누리”는 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다. <br><span class="underBold">(낚시누리 상담센터 : 1833-7139 )</span><p>
				<p class="mgt10px">▶  개인정보 열람청구 접수·처리 부서</p>
				<table class="basic_tbl mgt10px text-center">
					<caption>개인정보 열람청구 리스트</caption>
						<colgroup>
							<col />
							<col />
							<col />
							<col />
							<col />
						</colgroup>
						<thead>
							<tr>
								<th>구분</th>
								<th>부서명</th>
								<th>성명</th>
								<th>전화번호</th>
								<th>이메일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="bold">개인정보 열람청구 책임자<img style="width: 15px; height: 25px; margin-left:5px" src="/naksinuri_original/images/policy/ico_fiparang04.png" alt="" /></td>
								<td class="bold">수산어촌교육실</td>
								<td class="bold">권오열</td>
								<td class="bold">02-6098-0851</td>
								<td class="bold">koy@fipa.or.kr</td>
							</tr>
							<tr>
								<td class="bold">개인정보 열람청구 담당자<img style="width: 15px; height: 25px; margin-left:5px" src="/naksinuri_original/images/policy/ico_fiparang02.png" alt="" /></td>
								<td class="bold">수산어촌교육실</td>
								<td class="bold">강근철</td>
								<td class="bold">02-6098-0853</td>
								<td class="bold">kgc102@fipa.or.kr</td>
							</tr>
						</tbody>
					</table>
				<p class="dottedbox mgt10px">② 정보주체께서는 제1항의 열람청구 접수·처리부서 이외에, 행정안전부의 ‘개인정보보호 종합지원 포털’ 웹사이트(www.privacy.go.kr)를 통하여서도 개인정보   열람청구를 하실 수 있습니다.</p>
				<p>▶ 행정안전부 개인정보보호 종합지원 포털 → 민원마당 → 개인정보 열람등 요구(공공아이핀, 휴대폰을 통한 실명인증 필요)</p>
			</div>
			
			<div id="step14" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_12.png" alt="" />제14조(권익침해 구제방법)</h3>
				<p>정보주체는 아래의 기관에 대해 개인정보 침해에 대한 피해구제, 상담 등을 문의하실 수 있습니다. 아래의 기관은 “낚시누리”와는 
					<span class="underBold">별개의 기관</span>으로서, “낚시누리”의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이 필요하시면 문의하여 주시기 바랍니다.
				</p>
				<p>
					<ol>
						<li>▶ 개인정보 침해신고센터 (한국인터넷진흥원 운영)</li>
						<li>
							<ol class="depth2">
								<li>- 소관업무: 개인정보 침해사실 신고, 상담 신청</li>
								<li>- 홈페이지: privacy.kisa.or.kr</li>
								<li>- 전화: (국번없이) 118</li>
								<li>- 주소: (58324) 전남 나주시 진흥길 9(빛가람동 301-2) 3층 개인정보침해신고센터</li>
							</ol>
						</li>
						<li>▶ 개인정보 분쟁조정위원회</li>
						<li>
							<ol class="depth2">
								<li>- 소관업무: 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)</li>
								<li>- 홈페이지: www.kopico.go.kr</li>
								<li>- 전화: (국번없이) 1833-6972</li>
								<li>- 주소: (03171) 서울특별시 종로구 세종대로 209 정부서울청사 4층</li>
							</ol>
						</li>
						<li>▶ 대검찰청 사이버범죄수사단 : (국번없이) 1301 (www.spo.go.kr)</li>
						<li>▶ 경찰청 사이버안전국 : (국번없이) 182 (http://cyberbureau.police.go.kr)</li>
					</ol>
				</p>
				<br>
				<p>또한, 개인정보의 열람, 정정·삭제, 처리정지 등에 대한 정보주체자의 요구에 대하여 공공기관의 장이 행한 처분 또는 부작위로 인하여 권리 또는 이익을 침해 받은 자는 행정심판법이 정하는 바에 따라 행정심판을 청구할 수 있습니다.※ 중앙행정심판위원회(www.simpan.go.kr)의 전화번호 안내 참조</p>
			</div>
			
			<div id="step15" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_14.png" alt="" />제15조(개인정보 관리수준진단 결과)</h3>
				
				<p class="dottedbox">① 한국어촌어항공단은 정보주체의 개인정보를 안전하게 관리하기 위해 「개인정보 보호법」 제11조에 따라 매년 개인정보보호위원회에서 실시하는 "공공기관 개인정보 관리수준진단"을 받고 있습니다.</p>
				<p class="dottedbox">② 한국어촌어항공단은 2023년도 개인정보 관리수준진단 평가에서  <span class="bold">'A'</span> 등급을 획득하였습니다.<br>  ※ S등급: 90점 이상, A등급: 80점 이상, B등급: 70점 이상, C등급: 60점 이상, D등급: 60점 이하</p>
				
			</div>
			
			<div id="step16" class="con">
				<h3><img style="width: 80px; height: 80px;" src="/naksinuri_original/images/policy/pvc_13.png" alt="" />제16조(개인정보 처리방침 변경)</h3>
				<p class="dottedbox">① 이 개인정보 처리방침은 <span class="bold">2024. 10. 22.</span>부터   적용됩니다.</p>
				<p class="dottedbox">② 개인정보처리방침의 변경내용과 이전의 개인정보 처리방침은 아래에서 확인하실수 있습니다.</p>
			</div>
			
			<table class="basic_tbl mgt10px text-center" style="overflow-x: auto;">
					<caption>개인정보처리방침의 변경 사항</caption>
						<colgroup>
							<col style="width:12%"/>
							<col style="width:23%"/>
							<col style="width:29%" />
							<col style="width:29%"/>
							<col style="width:7%"/>
						</colgroup>
						<thead>
							<tr>
								<th>적용기간</th>
								<th>변경사항</th>
								<th>변경전</th>
								<th>변경후</th>
								<th>전문<br>보기</th>
							</tr>
						</thead>
						<tbody>
							
							<tr>
								<td class="bold">2024.10.22~<br>현재</td>
								<td>제6조(개인정보처리의 위탁)</td>
								<td>위탁하는 업무의 내용<br><br>
									한국해양수산 연수원<br>
									(사)한국낚시업중앙회<br>
									군산대학교 산학협력단<br>-<br>
									낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리</td>
								<td>위탁하는 업무의 내용<br><br>
									한국해양수산 연수원<br>-낚시어선 신규재개자 교육 운영<br><br>
									(사)한국낚시업중앙회<br>-낚시터 전문 현장교육 운영<br><br>
									군산대학교 산학협력단<br>-낚시어선 전문 현장교육 운영</td>
								<td class="bold"><span >-</span></td>
							</tr>
							
							
							<tr>
								<td rowspan="4" class="bold">2024.07.15~<br>2024.10.22</td>
								<td>제6조(개인정보처리의 위탁)</td>
								<td>-</td>
								<td>(추가) 4. 군산대학교 산학협력단 <br>063-469-7553</td>
								<td rowspan="4" class="bold"><a href="/policy/privacy20241022.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제12조<br>(개인정보 보호책임자 및 담당자)</td>
					            <td>부서명 : 정보화센터<br><br>이정환 / 02-6098-0752 / ljh028@fipa.or.kr</td>
					            <td>부서명 : 디지털정보실<br><br>김지현 / 02-6098-0752 / kjh097@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>제13조(개인정보 열람청구)</td>
					            <td>부서명 : 교육운영팀 <br><br>김민성 / 02-6098-0851 / kms@fipa.or.kr</td>
					            <td>부서명 : 수산어촌교육실 <br><br>권오열 / 02-6098-0851 / koy@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>제15조<br>(개인정보 관리수준진단 결과)</td>
					            <td>2022년 'S'등급</td>
					            <td>2023년 'A'등급</td>
					        </tr>
							
							
							<tr>
								<td class="bold">2023.12.06~<br>2023.07.15</td>
								<td>제16조(개인정보 처리방침 변경)</td>
								<td>-</td>
								<td>개인정보 처리방첨 변경관련 목차 삭제<br>개인정보 처리방침 변경 이력 테이블 추가</td>
								<td class="bold"><a href="/policy/privacy20240715.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							
							<tr>
								<td class="bold">2023.08.30~<br>2023.12.06</td>
								<td>제16조(개인정보 처리방침 변경)</td>
								<td>-</td>
								<td>개인정보 처리방첨 변경관련 목차 추가</td>
								<td class="bold"><a href="/policy/privacy20231206.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							
							<tr>
								<td rowspan="12" class="bold">2023.07.26~<br>2023.08.29</td>
								<td>개인정보의 제3자 제공<br>("제3조"에서 "제5조"로변경)</td>
								<td>-</td>
								<td>-</td>
								<td rowspan="12" class="bold"><a href="/policy/privacy20230829.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>개인정보처리의 위탁<br>("제4조"에서 "제6조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>정보주체와 법정대리인의 권리·의무 및 행사방법<br>("제5조"에서 "제8조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>처리하는 개인정보 항목<br>("제6조"에서 "제3조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보의 안전성 확보조치<br>("제8조"에서 "제9조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 자동 수집 장치의 설치,운영 및 거부에 관한 사항<br>("제9조"에서 "제10조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 보호책임자 및 담당자<br>("제10조"에서 "제12조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 열람청구<br>("제11조"에서 "제13조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>권익침해 구제방법<br>("제12조"에서 "제14조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>제4조<br>(개인정보 영향평가 수행 결과)</td>
					            <td>-</td>
					            <td>개인정보 영향평가 수행 결과 항목 추가</td>
					        </tr>
					        <tr>
					            <td>제11조<br>(가명정보 처리에 관한 사항)</td>
					            <td>-</td>
					            <td>가명정보 처리에 관한 사항 항목 추가</td>
					        </tr>
					        <tr>
					            <td>제15조<br>(개인정보 관리수준진단 결과)</td>
					            <td>-</td>
					            <td>개인정보 관리수준진단 결과 항목 추가</td>
					        </tr>
							
							<tr>
								<td rowspan="2" class="bold">2023.06.30~<br>2023.07.26</td>
								<td>제4조(개인정보처리의 위탁)</td>
								<td>(주)와토시스<br>연락처 : 042-471-7290<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수</td>
								<td>에스엠유㈜<br>연락처 : 032-209-1330<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수</td>
								<td rowspan="2" class="bold"><a href="/policy/privacy20230727.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제11조(개인정보 열람청구)</td>
					            <td>1.개인정보 열람청구 책임자	<br>- 성명: 김민성<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0851, kms@fipa.or.kr<br>2.개인정보 열람청구 담당자<br>- 성명: 최지원<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0852, cjw093@fipa.or.kr</td>
					            <td>1.개인정보 열람청구 책임자	<br>- 성명: 김민성<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0851, kms@fipa.or.kr<br>2.개인정보 열람청구 담당자<br>- 성명: 강근철<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0853, kgc102@fipa.or.kr</td>
					        </tr>
							
							<tr>
								<td rowspan="4" class="bold">2022.07.19~<br>2023.06.30</td>
								<td>제1조(개인정보의 처리목적)</td>
								<td>-</td>
								<td>낚시명예감시원<br>위촉대상자 명단 항목 삭제</td>
								<td rowspan="4" class="bold"><a href="/policy/privacy20230630.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제2조<br>(개인정보의 처리 및 보유기간)</td>
					            <td>-</td>
					            <td>낚시전문교육 교육대상자 명단<br>보유기간 :2년 항목 삭제</td>
					        </tr>
					        <tr>
					            <td>제4조(개인정보처리의 위탁)</td>
					            <td>-</td>
					            <td>군산대학교산학협력단 및 한국낚시단체총연합회 항목 삭제</td>
					        </tr>
					        <tr>
					            <td>제6조(처리하는 개인정보 항목)</td>
					            <td>-</td>
					            <td>낚시명예감시원<br>위촉대상자 명단 항목 삭제</td>
					        </tr>
							
							<tr>
								<td rowspan="5" class="bold">2022.04.22~<br>2022.07.19</td>
								<td>제1조(개인정보의 처리목적)</td>
								<td>-</td>
								<td>낚시명예감시원<br>위촉대상자 명단 항목 추가</td>
								<td rowspan="5" class="bold"><a href="/policy/privacy20220719.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제2조<br>(개인정보의 처리 및 보유기간)</td>
					            <td>낚시전문교육 교육대상자 명단<br>보유기간 :2년</td>
					            <td>1.낚시전문교육<br>교육대상자 명단<br>-보유기간 : 1년<br>2.낚시명예감시원 위촉대상자 명단<br>-보유기간 : 낚시명예감시원 활동기간</td>
					        </tr>
					        <tr>
					            <td>제4조(개인정보처리의 위탁)</td>
					            <td>1.(주)와토시스<br>연락처 : 042-471-7290<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수<br>2.한국해양수산연수원<br>연락처 : 051-624-4521<br>3.군산대학교산학협력단<br>연락처 : 063-469-7571<br>4.(사)한국낚시업중앙회<br>연락처 : 031-227-0744<br>업무 내용 : 낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리</td>
					            <td>1.시스템 운영 및<br>유지보수 업체<br>연락처 : 042-471-7290<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수<br>2.한국해양수산연수원<br>연락처 : 051-624-4521<br>3.군산대학교산학협력단<br>연락처 : 063-469-7571<br>4.(사)한국낚시업중앙회<br>연락처 : 031-227-0744<br>업무 내용 : 낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리<br>5.한국낚시단체총연합회<br>연락처 : 02-494-2733<br>업무 내용 : 낚시명예감시원 업무 수행을 위한<br>위촉대상자 정보 처리</td>
					        </tr>
					        <tr>
					            <td>제6조(처리하는 개인정보 항목)</td>
					            <td>-</td>
					            <td>낚시명예감시원<br>위촉대상자 명단 항목 추가</td>
					        </tr>
					        <tr>
					            <td>제10조(개인정보 보호책임자 및 담당자)</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 안경호<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0751, akh059@fipa.or.kr<br>2.개인정보 보호담당자<br>- 성명: 정재혁<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0754, jjh081@fipa.or.kr</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 안경호<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0751, akh059@fipa.or.kr<br>2.개인정보 보호담당자<br>- 성명: 이정환<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0752, ljh028@fipa.or.kr</td>
					        </tr>
							
							<tr>
								<td rowspan="3" class="bold">2021.08.31~<br>2022.04.22</td>
								<td>제4조(개인정보처리의 위탁)</td>
								<td>-</td>
								<td>2.한국해양수산연수원<br>연락처 : 051-624-4521<br>업무 내용 : 낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리</td>
								<td rowspan="3" class="bold"><a href="/policy/privacy20220422.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제10조(개인정보 보호책임자 및 담당자)</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 최주원<br>- 부서명: 융복합정보화팀<br>- 연락처: 02-6098-0751, cjw@fipa.or.kr<br>2.개인정보 보호담당자<br>- 성명: 임승민<br>- 부서명: 융복합정보화팀<br>- 연락처: 02-6098-0755, lsm04@fipa.or.kr</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 안경호<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0751, akh059@fipa.or.kr<br>2.개인정보 보호담당자<br>- 성명: 정재혁<br>- 부서명: 정보화전략팀<br>- 연락처: 02-6098-0754, jjh081@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>제11조(개인정보 열람청구)</td>
					            <td>1.개인정보 보호책임자 <br>- 성명: 정종민<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0842, jjm@fipa.or.kr<br>2.개인정보 담당자<br>- 성명: 조현진<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0843, chj051@fipa.or.kr<br>3.개인정보 분야별책임자  <br>- 성명: 허은희<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0844, heh124@fipa.or.kr<br><br>- FAX : 02-6098-0830</td>
					            <td>1.개인정보 열람청구 책임자<br>- 성명: 김민성<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0851, kms@fipa.or.kr<br>2.개인정보 열람청구 담당자<br>- 성명: 최지원<br>- 부서명 : 교육운영팀<br>- 연락처: 02-6098-0852, cjw093@fipa.or.kr</td>
					        </tr>
							
							<tr>
								<td class="bold">2020.08.10~<br>2021.08.30</td>
								<td>제1조(개인정보의 처리목적)</td>
								<td>-</td>
								<td>낚시전문교육 민원사무 처리 및<br>서비스 제공 항목 삭제</td>
								<td class="bold"><a href="/policy/privacy2021.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							
							<tr>
								<td rowspan="10" class="bold">2020.06.29~<br>2020.08.09</td>
								<td>제3조(개인정보의 제3자 제공)</td>
								<td>-</td>
								<td>신규 조문 추가</td>
								<td rowspan="10" class="bold"><a href="/policy/privacy20200809.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>개인정보처리의 위탁<br>("제3조"에서 "제4조"로변경)</td>
					            <td>1.(주)와토시스<br>연락처 : 042-471-7290<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수<br>2.군산대학교산학협력단<br>연락처 : 063-469-7571<br>3.전남대학교산학협력단 여수산학협력본부<br>연락처 : 061-659-6905<br>4.부경대학교산학협력단<br>연락처 : 051-629-5230<br>5.(사)한국낚시업중앙회<br>연락처 : 031-227-0744<br>업무 내용 : 낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리</td>
					            <td>전남대학교산학협력단 여수산학협력본부 및 부경대학교산학협력단 항목 삭제</td>
					        </tr>
					        <tr>
					            <td>정보주체와 법정대리인의 권리·의무 및 행사방법<br>("제4조"에서 "제5조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>처리하는 개인정보 항목<br>("제5조"에서 "제6조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보의 파기<br>("제6조"에서 "제7조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보의 안전성 확보조치<br>("제7조"에서 "제8조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 자동 수집 장치의 설치,운영 및 거부에 관한 사항<br>("제8조"에서 "제9조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 보호책임자 및 담당자<br>("제9조"에서 "제10조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 열람청구<br>("제10조"에서 "제11조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>권익침해 구제방법<br>("제11조"에서 "제12조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
						
							<tr>
								<td rowspan="12" class="bold">2020.03.19~<br>2020.06.28</td>
								<td>제1조(개인정보의 처리목적)</td>
								<td>낚시터정보 DB,낚시산업 정보DB<br>운영근거,처리목적 명시</td>
								<td>처리목적에 신원 확인, 교육사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 교육 이수증 발급 등 자세한 정보 추가</td>
								<td rowspan="12" class="bold"><a href="/policy/privacy2020.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제2조(개인정보의 처리 및 보유기간)</td>
					            <td>보유목적달성시까지로 하며, 민원 사무 처리가 종료된 후에는 처리된 일자의 해당 년 말일까지 보관하고 말일에 일괄 삭제합니다</td>
					            <td>2년</td>
					        </tr>
					        <tr>
					            <td>제3조(개인정보의 제3자 제공)</td>
					            <td>제1조의 개인정보의 처리목적에서 명시한 범위 내에서만 처리 그 외에는 제3자에게 제공하고 있지 않음</td>
					            <td>항목 삭제</td>
					        </tr>
					        <tr>
					            <td>제3조(개인정보처리의 위탁)<br>("제4조"에서 "제3조"로변경)</td>
					            <td>위탁받는자: 해당없음<br>위탁업무나 수탁자가 변경될 경우에는 공개</td>
					            <td>1.(주)와토시스<br>연락처 : 042-471-7290<br>업무 내용 : 낚시전문교육 내부시스템 운영지원 및 시스템 유지보수<br>2.군산대학교산학협력단<br>연락처 : 063-469-7571<br>3.전남대학교산학협력단 여수산학협력본부<br>연락처 : 061-659-6905<br>4.부경대학교산학협력단<br>연락처 : 051-629-5230<br>5.(사)한국낚시업중앙회<br>연락처 : 031-227-0744<br>업무 내용 : 낚시전문교육 업무 수행을 위한<br>교육대상자 정보 처리</td>
					        </tr>
					        <tr>
					            <td>정보주체와 법정대리인의 권리․ 의무 및 행사방법<br>("제5조"에서 "제4조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>처리하는 개인정보 항목<br>("제6조"에서 "제5조"로변경)</td>
					            <td>운영근거,보유기간 명시</td>
					            <td>1. 필수항목 : 수강인구분(낚시터업자, 낚시어선업자, 선원), 성명, 생년월일, 주소, 휴대전화번호, 해기사면허 유무, 허가(등록)·신고 시·군·구명<br>2.선택항목 : 전화번호, 이메일, 허가(등록)신고증 번호, 어선번호(낚시어선에 한함), 낚시터(낚시어선) 명칭, 유효기간</td>
					        </tr>
					        <tr>
					            <td>개인정보의 파기<br>("제7조"에서 "제6조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보의 안전성 확보조치<br>("제8조"에서 "제7조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 자동 수집 장치의 <br>설치,운영 및 거부에 관한 사항("제9조"에서 "제8조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>개인정보 보호책임자 및 담당자<br>("제10조"에서 "제9조"로변경)</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 최주원<br>- 직책: 정보화·융복합정보화팀장<br>- 연락처: 02-6098-0751, cjw@fipa.or.kr<br>2.개인정보 담당자<br>- 성명: 임승민<br>- 직책: 융복합정보화팀원<br>- 연락처: 02-6098-0755, lsm04@fipa.or.kr<br>3.개인정보 분야별책임자  <br>- 성명: 고정욱<br>- 직책: 어장산업팀장<br>- 연락처: 02-6098-0841, kju@fipa.or.kr<br>4.개인정보 분야별취급자<br>- 성명: 정종민<br>- 직책: 어장산업팀원<br>- 연락처: 02-6098-0842, jjm@fipa.or.kr</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 최주원<br>- 부서명: 융복합정보화팀<br>- 연락처: 02-6098-0751, cjw@fipa.or.kr<br>2.개인정보 보호담당자<br>- 성명: 임승민<br>- 부서명: 융복합정보화팀<br>- 연락처: 02-6098-0755, lsm04@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>개인정보 열람청구<br>("제11조"에서 "제10조"로변경)</td>
					            <td>1.개인정보 분야별책임자  <br>- 성명: 성용길<br>- 직책: 어촌마케팅팀장<br>- 연락처: 02-6098-0892, syg@fipa.or.kr</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 정종민<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0842, jjm@fipa.or.kr<br>2.개인정보 담당자<br>- 성명: 조현진<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0843, chj051@fipa.or.kr<br>3.개인정보 분야별책임자  <br>- 성명: 허은희<br>- 부서명 : 어장산업팀<br>- 연락처: 02-6098-0844, heh124@fipa.or.kr<br><br>- FAX : 02-6098-0830</td>
					        </tr>
					        <tr>
					            <td>권익침해 구제방법<br>("제12조"에서 "제11조"로변경)</td>
					            <td>대검찰청 사이버범죄수사단 : 02-3480-3573 (www.spo.go.kr)</td>
					            <td>대검찰청 사이버범죄수사단 : (국번없이) 1301 (www.spo.go.kr)</td>
					        </tr>
							
							
							<tr>
								<td rowspan="12" class="bold">2019.01.29~<br>2020.03.18</td>
								<td>개인정보의 처리목적, 개인정보의 처리 및 보유기간, 처리하는 개인정보의 항목<br>->개인정보의 처리목적<br>("가."에서 "제1조"로변경)</td>
								<td>조회방법 명시</td>
								<td>낚시터정보 DB,낚시산업 정보DB<br>운영근거,처리목적 명시</td>
								<td rowspan="12" class="bold"><a href="/policy/privacy2019.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>제2조(개인정보의 처리 및 보유기간)</td>
					            <td>-</td>
					            <td>제2조 신규 조문 추가(보유기간 명시)</td>
					        </tr>
					        <tr>
					            <td>개인정보의 제3자 제공<br>("나."에서 "제3조"로변경)</td>
					            <td>9가지의 특별규정에 대해 관해서 제공할 수 있다는 것을 명시</td>
					            <td>제1조의 개인정보의 처리목적에서 명시한 범위 내에서만 처리 그 외에는 제3자에게 제공하고 있지 않음</td>
					        </tr>
					        <tr>
					            <td>개인정보처리의 위탁<br>("다."에서 "제4조"로변경)</td>
					            <td>위탁받는자: ㈜와토시스</td>
					            <td>위탁받는자: 해당없음<br>위탁업무나 수탁자가 변경될 경우에는 공개</td>
					        </tr>
					        <tr>
					            <td>정보주체와 법정대리인의 권리․의무 및 행사방법<br>("라."에서 "제5조"로변경)</td>
					            <td>1. 개인정보 열람요구<br>2. 개인정보 정정·삭제 요구<br>3. 개인정보 처리정지 요구<br>각 항목마다 분류</td>
					            <td>1. 개인정보 열람,정정,삭제 요구 등의 권리 행사 가능<br>2.제1항에 따른권리 행사에 대해 지체없이 조치<br>3.제1항에 따른권리 행사는 대리인을 통해 가능<br>4.정보주체의 권리가 제한 될 수 있음<br>5.다른 법령에서 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할수없음<br>6.각 요구에대해 처리시 본인인지 대리인인지를 확인</td>
					        </tr>
					        <tr>
					            <td>제6조(처리하는 개인정보 항목)</td>
					            <td>-</td>
					            <td>제6조 신규 조문 추가</td>
					        </tr>
					        <tr>
					            <td>개인정보의 파기<br>("마."에서 "제7조"로변경)</td>
					            <td>파기 절차 및 파기기한,파기방법 명시</td>
					            <td>1.개인정보 보유기간의 경과,처리목적 당성 등 개인정보가 불필요하게 되었을 때에는 파기<br>2.개인정보를 계속 보존하여야 하는 경우에는 DB로 옮기거나 보관장소를 달리하여 보존</td>
					        </tr>
					        <tr>
					            <td>개인정보의 안전성 확보조치<br>("바."에서 "제8조"로변경)</td>
					            <td>1. 개인정보 취급직원의 최소화 및 교육  <br>2. 개인정보에 대한 접근 제한 <br>3. 접속기록의 보관 <br>4. 개인정보의 암호화 <br>5. 보안프로그램 설치 및 주기적 점검·갱신 <br>6. 비인가자에 대한 출입 통제 </td>
					            <td>1. 관리적 조치 : 내부관리계획 수립․시행, 정기적 직원 교육 등<br>2. 기술적 조치 : 개인정보처리시스템 관리, 접근통제시스템 암호화, 보안프로그램 설치<br>3. 물리적 조치 : 전산실, 자료보관실 등의 접근통제</td>
					        </tr>
					        <tr>
					            <td>개인정보 자동 수집 장치의 설치.운영 및 거부에 관한 사항<br>("사."에서 "제9조"로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>권익침해 구제방법<br>("아."에서 "제12조"로변경)</td>
					            <td>대검찰청 사이버범죄수사단<br>- 홈페이지 : http://www.spo.go.kr/minwon<br>- 전화 : 1301<br>경찰청 사이버안전국<br>- 홈페이지 : http://cyberbureau.police.go.kr<br>- 전화 : (국번없이) : 182</td>
					            <td>대검찰청 사이버범죄수사단<br>- 홈페이지 : http://www.spo.go.kr/<br>- 전화 : 02-3480-3573</td>
					        </tr>
					        <tr>
					            <td>개인정보보호 책임자 및 담당자 연락처<br>("자."에서 "제10조"로변경)</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 최주원<br>- 직책: 정보화·융복합정보화팀장<br>- 연락처: 02-6098-0751, cjw@fipa.or.kr<br>2.개인정보 담당자<br>- 성명: 임승민<br>- 직책: 융복합정보화팀원<br>- 연락처: 02-6098-0755, lsm04@fipa.or.kr</td>
					            <td>1.개인정보 분야별책임자<br>- 성명: 고정욱<br>- 직책: 어장산업팀장<br>- 연락처: 02-6098-0841, kju@fipa.or.kr<br>2.개인정보 분야별취급자<br>- 성명: 정종민<br>- 직책: 어장산업팀원<br>- 연락처: 02-6098-0842, jjm@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>제11조(개인정보 열람청구)</td>
					            <td>-</td>
					            <td>제11조 신규 조문 추가</td>
					        </tr>
							
							<tr>
								<td rowspan="9" class="bold">2017.06.20~<br>2019.01.28</td>
								<td>개인정보의 처리목적, 개인정보의 처리 및 보유기간, 처리하는 개인정보의 항목<br>("제1조"에서 "가."로변경)</td>
								<td>-</td>
								<td>-</td>
								<td rowspan="9" class="bold"><a href="/policy/privacy2017.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							<tr>
					            <td>개인정보의 제3자 제공<br>("제2조"에서 "나."로변경)</td>
					            <td>-</td>
					            <td>개인정보의 제3자 제공 항목 삭제</td>
					        </tr>
					        <tr>
					            <td>개인정보처리 위탁<br>("제3조"에서 "다."로변경)</td>
					            <td>위탁받는자: ㈜유에이브</td>
					            <td>위탁받는자: ㈜와토시스</td>
					        </tr>
					        <tr>
					            <td>정보주체 및 법정대리인의 권리·의무 및 그 행사 방법<br>("제4조"에서 "라."로변경)</td>
					            <td>1. 개인정보 열람요구<br>2. 개인정보 정정·삭제 요구<br>3. 개인정보 처리정지 요구</td>
					            <td>1. 개인정보 열람요구<br>2. 개인정보 정정·삭제 요구<br>3. 개인정보 처리정지 요구<br>각 항목마다 분류</td>
					        </tr>
					        <tr>
					            <td>개인정보 분야별 책임관->개인정보보호 책임자 및 담당자 연락처(제목변경 및 "제5조"에서 "자."로변경)</td>
					            <td>개인정보 보호책임자 : 정보화전략팀장 최주원<br>개인정보 분야별 책임관 : 바다마케팅팀장 송영택<br>개인정보 담당자 : 정보화전략팀원 임승민<br>(02-6098-0843)</td>
					            <td>1.개인정보 보호책임자  <br>- 성명: 최주원<br>- 직책: 정보화·전략팀장<br>- 연락처: 02-6098-0841, cjw@fipa.or.kr<br>2.개인정보 담당자<br>- 성명: 임승민<br>- 직책: 정보화전략팀원<br>- 연락처: 02-6098-0843, lsm04@fipa.or.kr</td>
					        </tr>
					        <tr>
					            <td>개인정보의 파기<br>("제6조"에서 "마."로변경)</td>
					            <td>-</td>
					            <td>개인정보 처리목적이 달성된<br>개인정보 파기 항목 삭제</td>
					        </tr>
					        <tr>
					            <td>개인정보의 안전성 확보 조치<br>("제7조"에서 "바."로변경)</td>
					            <td>-</td>
					            <td>-</td>
					        </tr>
					        <tr>
					            <td>권익침해 구제방법<br>("제8조"에서 "아."로변경)</td>
					            <td>대검찰청 사이버범죄수사단<br>- 홈페이지 : www.spo.go.kr<br>- 전화 : 02-3480-3571<br>- 이메일 : cybercid@spo.go.kr<br>경찰청 사이버테러대응센터<br> - 전화 : (국번없이) : 1566-0112<br>- 홈페이지 : www.netan.go.kr</td>
					            <td>대검찰청 사이버범죄수사단<br>- 홈페이지 : http://www.spo.go.kr/minwon<br>- 전화 : 1301<br>경찰청 사이버안전국<br>- 홈페이지 : http://cyberbureau.police.go.kr<br>- 전화 : (국번없이) : 182</td>
					        </tr>
					        <tr>
					            <td>사.개인정보를 자동으로 수집하는 장치의 설치·운영 및 그 거부에 관한 사항</td>
					            <td>-</td>
					            <td>(사.) 신규 조문 추가</td>
					        </tr>
					        
							<tr>
								<td class="bold">2016.05.23~<br>2017.06.19</td>
								<td class="bold">개인정보 처리방침 최초 구성</td>
								<td class="bold">-</td>
								<td class="bold">-</td>
								<td class="bold"><a href="/policy/privacy2016.do" style="display: inline; width: auto; height: auto; line-height: normal; margin: 0; background-color: transparent; text-align: inherit; color: inherit; font-weight: inherit; border-radius: 0;"><span >보기 <i class="bi bi-box-arrow-up-right"></i></span></a></td>
							</tr>
							
						</tbody>
					</table>
			
			
			
			
			<!-- 
			<a href="/policy/privacy20230829.do" class="bold">- 2023. 07. 26 ~ 2023. 08. 29 적용</a>
			<a href="/policy/privacy20230727.do" class="bold">- 2023. 06. 30 ~ 2023. 07. 26 적용</a>
			<a href="/policy/privacy20230630.do" class="bold">- 2023. 07. 19 ~ 2023. 06. 30 적용</a>
			<a href="/policy/privacy20220719.do" class="bold">- 2022. 04. 22 ~ 2022. 07. 19 적용</a>
			<a href="/policy/privacy20220422.do">- 2021. 08. 31 ~ 2022. 04. 22 적용</a>
			<a href="/policy/privacy2021.do">- 2020. 08. 10 ~ 2021. 8. 30 적용</a>
			<a href="/policy/privacy20200809.do">- 2020. 06. 29 ~ 2020. 08. 09 적용</a>
			<a href="/policy/privacy2020.do">- 2020. 03. 19 ~ 2020. 06. 28 적용</a>
			<a href="/policy/privacy2019.do">- 2019. 01. 29 ~ 2020. 03. 18 적용</a>
			<a href="/policy/privacy2017.do">- 2017. 06. 20 ~ 2019. 01. 28 적용 </a>
			<a href="/policy/privacy2016.do">- 2016. 05. 23 ~ 2017. 06. 19 적용</a>
			 -->
			<div style="text-align:center; margin-top:20px;">
				<img style="width: 178px; height: 110px; animation:bounce .7s ease infinite alternate;" src="/naksinuri_original/images/policy/ico_fiparang05.png" alt="" />
			</div>
			
		</section>
	</div>
	
<script>
function fn_egov_downFile(atchFileId, fileSn){
	window.open("/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"");
}
$(document).ready(function(){
    $('a[href^="#"]').on('click', function (e) {
        e.preventDefault();

        var target = this.hash;
        var $target = $(target);

        var offsetTop = $target.offset().top - 120; // 타겟을 100px 위로 이동시킴

        $('html, body').stop().animate({
            'scrollTop': offsetTop
        }, 900, 'swing', function () {
            window.location.hash = target;
        });
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const labelInfos = document.querySelectorAll('.label_info');

    function handleFocus(event) {
      event.target.querySelector('dl').style.display = 'block';
    }

    function handleBlur(event) {
      event.target.querySelector('dl').style.display = 'none';
    }

    labelInfos.forEach(function (labelInfo) {
      labelInfo.addEventListener('mouseover', handleFocus);
      labelInfo.addEventListener('focus', handleFocus);

      labelInfo.addEventListener('mouseleave', handleBlur);
      labelInfo.addEventListener('blur', handleBlur);
    });
  });
</script>	

 </section>
 
    <section id="section_side2"><img src="/naksinuri_original/images/policy/side_R.png" alt=""/></section>
</article>

<%@include file="../layout/tail.jsp"%>
</div>
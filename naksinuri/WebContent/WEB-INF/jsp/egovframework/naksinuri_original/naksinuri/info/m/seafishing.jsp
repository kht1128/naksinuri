<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="pageMode" value="info" />
<c:set var="depthName" value="info" />
<c:set var="pageName" value="fishjob" />

<%@include file="../../layout/m/head.jsp"%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/se2/js/HuskyEZCreator.js" charset="utf-8"></script>

<article id="fishinfoRegister" class="content">
	<section class="fishinfoRegist">
		<h1>
			<em class="colorSky">낚시터 <b>정보등록</b></em>
			<span>
				해양수산부와 한국어촌어항공단가 운영하는 낚시정보종합포털 "낚시누리"에서 귀하의 사업장 정보를 무료로 홍보해 드립니다.<br />
				선상낚시, 바다낚시터, 민물낚시터, 낚시공원, 낚시카페, 낚시체험 등
			</span>
		</h1>
		<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >
			<input type="hidden" name="save_status" id="save_status" value="r">
			<input type="hidden" name="client_ipaddr" value="<%=request.getRemoteAddr()%>">
			
			<input type="hidden" value="${RandKey}" name="pnak_id" id="pnak_id"/>
			<c:if test="${info.nak_id eq null}">
			<input type="hidden" value="w" name="save_mode" id="save_mode"/>
			</c:if>
			<c:if test="${info.nak_id ne null}">
			<input type="hidden" value="" name="save_mode" id="save_mode"/>
			</c:if>
			
			<input type="hidden" name="fileListCnt">
			<input type="hidden" name="atchFileId">
			<input type="hidden" name="fileSn">
			<input type="hidden" name="returnUrl" value="<c:url value='/info/m/findCorp.do'/>"/>
			<input type="hidden" name="nak_id" id="nak_id" value="${info.nak_id}">
			<input type="hidden" name="atch_file_id" value="${info.atch_file_id }">
			<input type="hidden" name="co_mimgsrc" value="${info.co_mimgsrc }">
			<c:if test="${info.fishing_type ne null }">
				<input type="hidden" name="fishing_type" value="${info.fishing_type }">
			</c:if>
			<section id="basicform" class="basic_formbox mgt30">
				<h2>기본정보</h2>
				<div class="writeBox">
					<c:if test="${info.nak_id eq NULL }">
						<dl>
							<dt>구 분</dt>
							<dd>
								<ul class="choicebox floats">
									<li><input type="radio" id="boatfishing" name="fishing_type" value="boatfishing" OnClick="window.location.href='/info/m/boatfishing.do';" />
									<label for="boatfishing"><span></span>선상낚시</label></li>
									<li><input type="radio" id="riverfishing" name="fishing_type" value="riverfishing" OnClick="window.location.href='/info/m/riverfishing.do';" />
									<label for="riverfishing"><span></span>민물낚시</label></li>							
									<li><input type="radio" id="seafishing" name="fishing_type" value="seafishing" OnClick="window.location.href='/info/m/seafishing.do';" checked />
									<label for="seafishing"><span></span>바다낚시</label></li>							
								</ul>
							</dd>
						</dl>
					</c:if>
					<dl>
						<dt>낚시업종</dt>
						<dd>
							<ul class="choicebox2 floats">
								<li><input type="checkbox" id="sea_out" name="co_info" value="바다낚시터(실외)" <c:if test ="${fn:contains(info.co_info,'바다낚시터(실외)')}">checked</c:if> /><label for="sea_out"><span></span>바다낚시터(실외)</label></li>
								<li><input type="checkbox" id="sea_in" name="co_info" value="바다낚시터(실내)" <c:if test ="${fn:contains(info.co_info,'바다낚시터(실내)')}">checked</c:if> /><label for="sea_in"><span></span>바다낚시터(실내)</label></li>
								<li><input type="checkbox" id="checking3" name="co_info" value="바다수상좌대" <c:if test ="${fn:contains(info.co_info,'바다수상좌대')}">checked</c:if> /><label for="checking3"><span></span>바다수상좌대</label></li>
								<li><input type="checkbox" id="onseapension" name="co_info" value="해상펜션" <c:if test ="${fn:contains(info.co_info,'해상펜션')}">checked</c:if> /><label for="onseapension"><span></span>해상펜션</label></li>
								<li><input type="checkbox" id="seafishingpark" name="co_info" value="바다낚시공원" <c:if test ="${fn:contains(info.co_info,'바다낚시공원')}">checked</c:if> /><label for="seafishingpark"><span></span>바다낚시공원</label></li>
								<li><input type="checkbox" id="seaexpfield" name="co_info" value="바다체험장"  <c:if test ="${fn:contains(info.co_info,'바다체험장')}">checked</c:if> /><label for="seaexpfield"><span></span>바다체험장</label></li>
								<li><input type="checkbox" id="foreshore" name="co_info" value="갯바위출조" <c:if test ="${fn:contains(info.co_info,'갯바위출조')}">checked</c:if> /><label for="foreshore"><span></span>갯바위출조</label></li>
								<li><input type="checkbox" id="seapoint" name="co_info" value="바다낚시출조점" <c:if test ="${fn:contains(info.co_info,'바다낚시출조점')}">checked</c:if> /><label for="seapoint"><span></span>바다낚시출조점</label></li>
	
							</ul>
						</dd>
					
					</dl>
					<dl>
						<dt>상호명</dt>
						<dd><input type="text" name="co_nm" id="co_nm" class="naksi_input" value="${info.co_nm}" /></dd>
				<!--	<dd><input type="text" class="naksi_input readonly" /></dd>-->
					</dl>
					<dl>
						<dt>대표자</dt>
						<dd><input type="text" name="ceo_nm" id="ceo_nm" class="naksi_input" value="${info.ceo_nm}" /></dd>
					</dl>
					<dl>
						<dt>일반전화</dt>
						<dd>
							<select class="naksi_select" name="co_phone1">
								<option value=''>없음</option>
								<option <c:if test="${info.co_phone1 eq '02' }">selected</c:if>>02</option>
								<option <c:if test="${info.co_phone1 eq '031' }">selected</c:if>>031</option>
								<option <c:if test="${info.co_phone1 eq '032' }">selected</c:if>>032</option>
								<option <c:if test="${info.co_phone1 eq '033' }">selected</c:if>>033</option>
								<option <c:if test="${info.co_phone1 eq '041' }">selected</c:if>>041</option>
								<option <c:if test="${info.co_phone1 eq '042' }">selected</c:if>>042</option>
								<option <c:if test="${info.co_phone1 eq '043' }">selected</c:if>>043</option>
								<option <c:if test="${info.co_phone1 eq '051' }">selected</c:if>>051</option>
								<option <c:if test="${info.co_phone1 eq '052' }">selected</c:if>>052</option>
								<option <c:if test="${info.co_phone1 eq '053' }">selected</c:if>>053</option>
								<option <c:if test="${info.co_phone1 eq '054' }">selected</c:if>>054</option>
								<option <c:if test="${info.co_phone1 eq '055' }">selected</c:if>>055</option>
								<option <c:if test="${info.co_phone1 eq '061' }">selected</c:if>>061</option>
								<option <c:if test="${info.co_phone1 eq '062' }">selected</c:if>>062</option>
								<option <c:if test="${info.co_phone1 eq '063' }">selected</c:if>>063</option>
								<option <c:if test="${info.co_phone1 eq '064' }">selected</c:if>>064</option>
								<option <c:if test="${info.co_phone1 eq '070' }">selected</c:if>>070</option>
							</select> - 
							<input type="text" class="naksi_input" name="co_phone2" value="${info.co_phone2}" maxlength="4" size="7" /> - 
							<input type="text" class="naksi_input" name="co_phone3" value="${info.co_phone3}" maxlength="4" size="7" />
							<input type="hidden" name="co_phone">
						</dd>
					</dl>
					<dl>
						<dt>휴대전화</dt>
						<dd>
							<select class="naksi_select" name="co_hphone1">
								<option <c:if test="${info.co_hphone1 eq '010' }">selected</c:if>>010</option>
								<option <c:if test="${info.co_hphone1 eq '011' }">selected</c:if>>011</option>
								<option <c:if test="${info.co_hphone1 eq '016' }">selected</c:if>>016</option>
								<option <c:if test="${info.co_hphone1 eq '017' }">selected</c:if>>017</option>
								<option <c:if test="${info.co_hphone1 eq '018' }">selected</c:if>>018</option>
								<option <c:if test="${info.co_hphone1 eq '019' }">selected</c:if>>019</option>
							</select> - 
							<input type="text" class="naksi_input" name="co_hphone2" id="co_hphone2" value="${info.co_hphone2}" maxlength="4" size="7" /> - 
							<input type="text" class="naksi_input" name="co_hphone3" id="co_hphone3" value="${info.co_hphone3}" maxlength="4" size="7" />
							<input type="hidden" name="co_hphone">
						</dd>
					</dl>
					<dl>
						<dt>휴대전화2</dt>
						<dd>
							<select class="naksi_select" name="co_2hphone1">
								<option <c:if test="${info.co_2hphone1 eq '010' }">selected</c:if>>010</option>
								<option <c:if test="${info.co_2hphone1 eq '011' }">selected</c:if>>011</option>
								<option <c:if test="${info.co_2hphone1 eq '016' }">selected</c:if>>016</option>
								<option <c:if test="${info.co_2hphone1 eq '017' }">selected</c:if>>017</option>
								<option <c:if test="${info.co_2hphone1 eq '018' }">selected</c:if>>018</option>
								<option <c:if test="${info.co_2hphone1 eq '019' }">selected</c:if>>019</option>
							</select> - 
							<input type="text" class="naksi_input" name="co_2hphone2" id="co_2hphone2" value="${info.co_2hphone2}" maxlength="4" size="7" /> - 
							<input type="text" class="naksi_input" name="co_2hphone3" id="co_2hphone3" value="${info.co_2hphone3}" maxlength="4" size="7" />
							<input type="hidden" name="co_2hphone">
						</dd>
					</dl>						
					<dl>
						<dt>사업장 주소</dt>
						<dd class="multi_input">
							<input type="text" class="naksi_input readonly" id="newpostcode" name="co_addr_1" value="${info.co_addr_1}" readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="newaddress()">주소검색</a><br />
							<div id="wrap2" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
							<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode2()" alt="접기 버튼">
							</div>
	
							<input type="text" id="newaddress1" name="co_addr_2" value="${info.co_addr_2}" readonly class="naksi_input w100" /><br />
							<input type="text" id="newaddress2" name="co_addr_3" value="${info.co_addr_3}" class="naksi_input w100" placeholder="상세주소"/>
							<input type="hidden" name="co_addr">
						</dd>
					</dl>
					<dl>
						<dt>홈페이지</dt>
						<dd class="multi_input">
							<input type="text" class="naksi_input w100" name="co_web" value=<c:choose><c:when test="${info.co_web eq null }">"http://"</c:when><c:otherwise>"${info.co_web}"</c:otherwise></c:choose> />
						</dd>
					</dl>
				</div>
			</section>
			<section id="shipform" class="basic_formbox mgt30">
				<h2>주요정보</h2>
				<div class="writeBox">
					<dl>
						<dt>주요어종</dt>
						<dd>
							<ul class="choicebox floats">
								<c:set var="sub" value="${2}"></c:set>
								<c:forEach var="fishes" items="${fishlist}" varStatus="status">
									<c:set var="subname" value="${sub}${fishes.fish_name}${sub}"></c:set>
									<li><input type="checkbox" name="co_fish" id="fish${status.count}" value="${subname}" <c:if test ="${fn:contains(info.co_fish, subname)}">checked</c:if>><label for="fish${status.count}"><span></span>${fishes.fish_name}</label></li>
								</c:forEach>
							</ul>
						</dd>
					</dl>
					<dl>
						<dt>오시는 길</dt>
						<dd class="multi_input">
							<input type="text" class="naksi_input readonly" id="busipostcode" name="co_addr2_1" value="${info.co_addr2_1}" readonly size="7" /><a href="#;" class="basicBox bgGray zipcodeBox" onclick="busiaddress()">주소검색</a>
							<input type="checkbox" id="sameaddr" onclick = "checksameaddr()"/><label for="sameaddr"><span></span>사업장 주소와 동일</label><br />
							<div id="wrap" style="display:none;border:1px solid;width:350px;height:300px;margin:5px 0;position:relative">
							<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
							</div>
	
							<input type="text" id="busiaddress1" name="co_addr2_2" value="${info.co_addr2_2}" readonly class="naksi_input w100" /><br />
							<input type="text" id="busiaddress2" name="co_addr2_3" value="${info.co_addr2_3}" class="naksi_input w100" placeholder="상세주소"/>
							<input type="hidden" name="co_addr2">
						</dd>						
					</dl>
					<dl>
						<dt>수용인원</dt>
						<dd><input type="text" class="naksi_input" name="co_psg" id="co_psg" value="${info.co_psg}"/> 명</dd>
					</dl>
					<dl>
						<dt>이용시간</dt>
						<dd>
							<input type="text" class="naksi_input" name="co_stm" id="co_stm" value="${info.co_stm}" size="10" placeholder="예) 오전 7시" /> ~ 
							<input type="text" class="naksi_input" name="co_etm" id="co_etm" value="${info.co_etm}" size="10" placeholder="오후 4시" />
						</dd>
					</dl>
					<dl>
						<dt>이용료</dt>
						<dd>
							<input type="hidden" name="getco_prctp" value="${info.co_prctp }">
							<input type="hidden" name="getco_prc"  value="${info.co_prc}">
							<input type="hidden" name="co_prc" id="co_prc" >
							<input type="hidden" name="co_prctp" id="co_prctp">
							<ul id="charul">
								<li id="charli_0" style="padding:5px 0">
									<input type="checkbox" id="chart_0" name="chart[]"/>
									<label for="chart_0"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="naksi_input price_input" size="10" name="reco_prctp" id="reco_prctp_0" placeholder="예) 오전낚시"/>&nbsp;&nbsp;
									<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="naksi_input price_input" size="10" name="reco_prc" id="reco_prc_0" placeholder="예) 4만원"/>
								</li>
							</ul>
							<a href="#;" class="basicBox bgGray" onclick="insert()">이용료 추가</a>
							<a href="#;" class="basicBox zipcodeBox" onclick="del()">이용료 삭제</a>
						</dd>
					</dl>
	
					<dl>
						<dt>시설안내</dt>
						<dd>
							<ul class="choicebox floats">
								<li><input type="checkbox" id="aircon" name="co_fct" value="에어컨" 		<c:if test ="${fn:contains(info.co_fct,'에어컨')}">checked</c:if>/><label for="aircon"><span></span>에어컨</label></li>
								<li><input type="checkbox" id="toilet" name="co_fct" value="화장실" 		<c:if test ="${fn:contains(info.co_fct,'화장실')}">checked</c:if>/><label for="toilet"><span></span>화장실</label></li>
								<li><input type="checkbox" id="lifejacket" name="co_fct" value="구명조끼" <c:if test ="${fn:contains(info.co_fct,'구명조끼')}">checked</c:if>/><label for="lifejacket"><span></span>구명조끼</label></li>
								<li><input type="checkbox" id="liferope" name="co_fct" value="구명밧줄" 	<c:if test ="${fn:contains(info.co_fct,'구명밧줄')}">checked</c:if>/><label for="liferope"><span></span>구명밧줄</label></li>
								<li><input type="checkbox" id="insurance" name="co_fct" value="보험" 		<c:if test ="${fn:contains(info.co_fct,'보험')}">checked</c:if>/><label for="insurance"><span></span>보험</label></li>
								<li><input type="checkbox" id="heating" name="co_fct" value="난방" 		<c:if test ="${fn:contains(info.co_fct,'난방')}">checked</c:if>/><label for="heating"><span></span>난방</label></li>
								<li><input type="checkbox" id="fireex" name="co_fct" value="소화기" 		<c:if test ="${fn:contains(info.co_fct,'소화기')}">checked</c:if>/><label for="fireex"><span></span>소화기</label></li>
								<li><input type="checkbox" id="shade" name="co_fct" value="그늘막" 		<c:if test ="${fn:contains(info.co_fct,'그늘막')}">checked</c:if>/><label for="shade"><span></span>그늘막</label></li>
								<li><input type="checkbox" id="diningroom" name="co_fct" value="식당" 	<c:if test ="${fn:contains(info.co_fct,'식당')}">checked</c:if>/><label for="diningroom"><span></span>식당</label></li>
								<li><input type="checkbox" id="coffee" name="co_fct" value="커피" 		<c:if test ="${fn:contains(info.co_fct,'커피')}">checked</c:if>/><label for="coffee"><span></span>커피</label></li>
								<li><input type="checkbox" id="shop" name="co_fct" value="매점" 			<c:if test ="${fn:contains(info.co_fct,'매점')}">checked</c:if>/><label for="shop"><span></span>매점</label></li>
								<li><input type="checkbox" id="parking" name="co_fct" value="주차장" 		<c:if test ="${fn:contains(info.co_fct,'주차장')}">checked</c:if>/><label for="parking"><span></span>주차장</label></li>
								<li><input type="checkbox" id="room" name="co_fct" value="숙소" 			<c:if test ="${fn:contains(info.co_fct,'숙소')}">checked</c:if>/><label for="room"><span></span>숙소</label></li>
								<li><input type="checkbox" id="restroom" name="co_fct" value="휴게실" 	<c:if test ="${fn:contains(info.co_fct,'휴게실')}">checked</c:if>/><label for="restroom"><span></span>휴게실</label></li>
								<li><input type="checkbox" id="refrg" name="co_fct" value="냉장고" 		<c:if test ="${fn:contains(info.co_fct,'냉장고')}">checked</c:if>/><label for="refrg"><span></span>냉장고</label></li>
								<li><input type="checkbox" id="tv" name="co_fct" value="TV" 			<c:if test ="${fn:contains(info.co_fct,'TV')}">checked</c:if>/><label for="tv"><span></span>TV</label></li>
								<li><input type="checkbox" id="internet" name="co_fct" value="인터넷" 	<c:if test ="${fn:contains(info.co_fct,'인터넷')}">checked</c:if>/><label for="internet"><span></span>인터넷</label></li>
								<li><input type="checkbox" id="kitchen" name="co_fct" value="취사장" 		<c:if test ="${fn:contains(info.co_fct,'취사장')}">checked</c:if>/><label for="kitchen"><span></span>취사장</label></li>
								<li><input type="checkbox" id="campsite" name="co_fct" value="야영지" 	<c:if test ="${fn:contains(info.co_fct,'야영지')}">checked</c:if>/><label for="campsite"><span></span>야영지</label></li>
								<li><input type="checkbox" id="fishingshop" name="co_fct" value="낚시점" 	<c:if test ="${fn:contains(info.co_fct,'낚시점')}">checked</c:if>/><label for="fishingshop"><span></span>낚시점</label></li>
								<li><input type="checkbox" id="showerroom" name="co_fct" value="샤워실" 	<c:if test ="${fn:contains(info.co_fct,'샤워실')}">checked</c:if>/><label for="showerroom"><span></span>샤워실</label></li>
								<li><input type="checkbox" id="fan" name="co_fct" value="선풍기" 			<c:if test ="${fn:contains(info.co_fct,'선풍기')}">checked</c:if>/><label for="fan"><span></span>선풍기</label></li>
								<li><input type="checkbox" id="burner" name="co_fct" value="버너" 		<c:if test ="${fn:contains(info.co_fct,'버너')}">checked</c:if>/><label for="burner"><span></span>버너</label></li>
								<li><input type="checkbox" id="grill" name="co_fct" value="숯불그릴" 		<c:if test ="${fn:contains(info.co_fct,'숯불그릴')}">checked</c:if>/><label for="grill"><span></span>숯불그릴</label></li>
								<li><input type="checkbox" id="beding" name="co_fct" value="침구" 		<c:if test ="${fn:contains(info.co_fct,'침구')}">checked</c:if>/><label for="beding"><span></span>침구</label></li>
								<li><input type="checkbox" id="rod" name="co_fct" value="낚시대" 			<c:if test ="${fn:contains(info.co_fct,'낚시대')}">checked</c:if>/><label for="rod"><span></span>낚시대</label></li>
								<li><input type="checkbox" id="sasimi" name="co_fct" value="회떠드림"		<c:if test ="${fn:contains(info.co_fct,'회떠드림')}">checked</c:if>/><label for="sasimi"><span></span>회떠드림</label></li>
							</ul>
						</dd>
					</dl>
					<dl>
						<dt>사업장 소개</dt>
						<dd><textarea placeholder="예) 친절하고 깨끗한 환경, 확실한 조과 보장" class="basic_textarea" name="co_intro" id="co_intro" cols="60" >${info.co_intro}</textarea></dd>
					</dl>
					<dl>
						<dt>대표 이미지</dt>
						<dd>
							<ul>
							<c:if test="${mimg.orignl_file_nm ne null }">
								<li><c:out value="${mimg.orignl_file_nm }"/>&nbsp;<c:out value="[${mimg.file_size} Byte]"/>
									<%--
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>"
					       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${mimg.co_mimgsrc}"/>','<c:out value="${mimg.file_sn}"/>');" />
					       			<input type="hidden" value="yy" id="mimg">
					       			--%>
					       			<input type="hidden" id="is_mimg" value="1" />
				       			</li>
							</c:if>
							<c:if test="${mimg.orignl_file_nm eq null }">
					       			<input type="hidden" id="is_mimg" value="2" />
							</c:if>
							
								<li>
									<input type="file" size="30" id="mimg" name="mimg" accept="image/*" style="width:290px" />
				       			</li>
				       			<c:if test="${mimg.orignl_file_nm ne null }">
								<li class="help">
									※ 파일첨부시 대표이미지는 자동으로 교체됩니다.
				       			</li>
				       			</c:if>
				       		</ul>
						</dd>
					</dl>
					<dl>
						<dt>소개 이미지</dt>
						<dd>
							<ul class="imgul" id="imgul">
					          <c:forEach var="naksinurifileVO" items= "${filelist}" varStatus="status">
								<li><c:out value="${naksinurifileVO.orignl_file_nm}"/>&nbsp;<c:out value="[${naksinurifileVO.file_size} Byte]"/>
								<%--							
									<img alt="파일 삭제" src="<c:url value='/naksinuri_original/images/btn/bu5_close.gif'/>" 
					       			width="19" height="18" onClick="fn_egov_deleteFile('<c:out value="${naksinurifileVO.atch_file_id}"/>','<c:out value="${naksinurifileVO.file_sn}"/>');" />
					       		--%>
					       			<input type="checkbox" id="delcheck_${naksinurifileVO.atch_file_id}_${naksinurifileVO.file_sn}" name="delcheck" value="${naksinurifileVO.atch_file_id}_${naksinurifileVO.file_sn}" />
					       			<label for="delcheck_${naksinurifileVO.atch_file_id}_${naksinurifileVO.file_sn}"><span></span>삭제 ${naksinurifileVO.atch_file_id}_${naksinurifileVO.file_sn}</label>
				       			</li>
							  </c:forEach>			
							<li id="imgli_0">
								<em><input type="checkbox" id="introimage_0"  />
								<label for="introimage_0"><span></span></label></em><span><input type="file" size="30" id="egovComFileUploader" accept="image/*" name="file_1" value="${info.atch_file_id }"  /></span>
							</li>
						</ul>
						<a href="#;" class="basicBox bgGray" onclick="insertimg()">이미지 추가</a><a href="#;" class="basicBox zipcodeBox" onclick="delrow()">이미지 삭제</a>
						</dd>
					</dl>
				</div>
	
				<div class="btnArea">
					<c:choose>
						<c:when test="${info.nak_id eq null }">
							<button type="button" class="btns bgBlue" id="insertCorp" >등록 신청</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="btns bgBlue" id="insertCorp" >수정하기</button>
						</c:otherwise>
					</c:choose>
					<button type="button" class="btns bgBlue" id="preview">미리보기</button>
					<button type="button" class="btns" id="cancel">취소</button>
				</div>
			</section>
		</form:form>
	</section>
</article>

<script type="text/javaScript" language="javascript">
var j=0;
$(document).ready(function(){
	var getprc= $('input[name=getco_prc]').val();
	var getprctp= $('input[name=getco_prctp]').val();
	
	if(getprc!='') {		
		$("#charli_0").remove();
		$('input[name=getco_prc]').val();
		
		var prc = getprc.split('|');
		var prctp= getprctp.split('|');
		
		//배열 빈값제거
		for(var i=0;i<prc.length;i++){
			if(prc[i]!=''){
			$('#charul').append('<li id="charli_'+i+'"style="padding:5px 0">\
					<input type="checkbox" id="chart_'+i+'" name="chart[]"/>\
					<label for="chart_'+i+'"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="naksi_input price_input" value = "'+prctp[i]+'" name="reco_prctp" size="10" id="reco_prctp_'+i+'"\
					/>&nbsp;&nbsp;\
					<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="naksi_input price_input" value ="'+prc[i]+'" name="reco_prc" size="10" id="reco_prc_'+i+'" />\
				</li>');
				//j++;
				j=i;
			}
		}
	}
	// 이미지 10개다 채웠을때 파일첨부 행 없애기
	if($("ul#imgul").children().size()>=10){
		$("#imgli_0").remove();
	}
});

//전역변수선언
var oEditors = [];


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "co_intro",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["co_intro"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});


//이용료 행 추가 삭제

function insert(){

	if($("ul#charul").children().size()==5){
		alert("이용료는 5개까지 추가 가능합니다.");
		return false;
	}else{
		$('#charul').append('<li id="charli_'+(j+1)+'"style="padding:5px 0">\
			<input type="checkbox" id="chart_'+(j+1)+'" name="chart[]"/>\
			<label for="chart_'+(j+1)+'"><span></span>조건</label>&nbsp;&nbsp;<input type="text" class="naksi_input price_input" size="10" id="reco_prctp_'+(j+1)+'" name="reco_prctp"\
			placeholder="예) 오전낚시"/>&nbsp;&nbsp;\
			<label><span style="font-weight:normal">요금</span>&nbsp;&nbsp;</label><input type="text" class="naksi_input price_input" size="10" id="reco_prc_'+(j+1)+'" name="reco_prc" placeholder="예) 4만원"/>\
		</li>');
		j++;
	}
		
}
function del(){
	
	for(var k=0;k<=j;k++){
			if($("#chart_"+k).prop("checked")) {
			$("#charli_"+k).remove();
			
			}
		}
}


// 이미지 행 추가 삭제
	var i = 1;
	function insertimg(){
		if($("ul#imgul").children().size()==10){
			alert("이미지는 10개까지 추가 가능합니다.");
			return false;
		}else{
			$('#imgul').append('<li id="imgli_'+i+'"><em><input type="checkbox" id="introimage_'+i+'" name="introimage[]" /><label for="introimage_'+i+'"><span></span></label></em><span><input type="file" size="30" id="file" name="file_'+(i+1)+'" accept="image/*" /></span></li>');	
			i++;
		}
	}
	function delrow(){
		
		for(var j=0;j<=i;j++){
			if($("#introimage_"+j).prop("checked")) {
			$("#imgli_"+j).remove();
			}
		}
	}

 // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');
	var element_wrap2 = document.getElementById('wrap2');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

	function foldDaumPostcode2() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap2.style.display = 'none';
    }



    function newaddress() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.roadAddress; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('newpostcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('newaddress1').value = fullAddr;
			
				

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap2.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap2.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap2);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap2.style.display = 'block';
    }

	function busiaddress() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.roadAddress; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('busipostcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('busiaddress1').value = fullAddr;			

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }

		function checksameaddr(){
			if($("#sameaddr").prop("checked")){
				if($("#newaddress1").val()){
					$shapeadd0 = document.getElementById('newpostcode').value;
					$shapeadd1 = document.getElementById('newaddress1').value;
					$shapeadd2 = document.getElementById('newaddress2').value;

					document.getElementById('busipostcode').value = $shapeadd0;
					document.getElementById('busiaddress1').value = $shapeadd1;
					document.getElementById('busiaddress2').value = $shapeadd2;
//				}else if($("#oldaddress1").val()==''){
				}else{
					$("#sameaddr").prop("checked",false);
					alert("사업장 주소를 입력해주세요");
					
				}
			}

		}
		
		function fn_egov_deleteFile(atchFileId, fileSn) {
			forms = document.getElementsByTagName("form");

			for (var i = 0; i < forms.length; i++) {
				if (typeof(forms[i].atchFileId) != "undefined" &&
						typeof(forms[i].fileSn) != "undefined"  &&
						typeof(forms[i].fileListCnt) != "undefined") {
					form = forms[i];
				}
			}
			//form = document.forms[0];
			form.atchFileId.value = atchFileId;
			form.fileSn.value = fileSn;
			form.action = "<c:url value='/naksinuri_original/cmm/fms/deleteFileInfs.do'/>";
			form.submit();
		}
		
	function updateCorp(){
		var co_addr = $('input[name=co_addr_1]').val() +
		  $('input[name=co_addr_2]').val() +
		  $('input[name=co_addr_3]').val();			

		var co_addr2 = $('input[name=co_addr2_1]').val() +
		   $('input[name=co_addr2_2]').val() +
		   $('input[name=co_addr2_3]').val();	

		var co_phone = $('select[name=co_phone1]').val() +
		   $('input[name=co_phone2]').val() + 
		   $('input[name=co_phone3]').val();

		var co_hphone = $('select[name=co_hphone1]').val() + 
			$('input[name=co_hphone2]').val() +
			$('input[name=co_hphone3]').val();

		var co_2hphone = $('select[name=co_2hphone1]').val() + 
			$('input[name=co_2hphone2]').val() +
			$('input[name=co_2hphone3]').val();

			$('input[name=co_addr]').val(co_addr);
			$('input[name=co_addr2]').val(co_addr2);
			$('input[name=co_phone]').val(co_phone);			
			$('input[name=co_hphone]').val(co_hphone);	
			$('input[name=co_2hphone]').val(co_2hphone);
			
		if( $('input[name=nak_id]').val()=='' && document.getElementById('seafishing').checked == false){
			alert('기본정보의 구분을 확인해주세요.');
				return false;
			}
		var info_obj = document.getElementsByName("co_info");
		var info_leng = info_obj.length;
		var checked2=0;
		for(i=0;i<info_leng;i++){
			if(info_obj[i].checked==true){
				checked2+=1;
			}
		}
		
		if(checked2==0){
			alert('낚시업종을 하나 이상 선택해주세요.');
			return false;
		}
		if(!$('#co_nm').val() || $('#co_nm').val().trim() == '' ){
			alert('상호명을 입력해주세요');
			return false;
		}
		if(!$('#ceo_nm').val()|| $('#ceo_nm').val().trim() == ''){
			alert('대표자를 입력해주세요');
			return false;
		}
		
		
		if(!$('#co_hphone2').val()||!$('#co_hphone3').val()||$('#co_hphone2').val().trim() == ''||$('#co_hphone3').val().trim() == ''){
			alert('핸드폰 번호를 입력해주세요');
			return false;
		}
		
		if(!$('#newpostcode').val()||!$('#newaddress1').val()||$('#newpostcode').val().trim() == '' ||$('#newaddress1').val().trim() == ''){
			alert('사업장 주소를 입력해주세요');
			return false;
		}
		var fish_obj = document.getElementsByName("co_fish");
		var fish_leng = fish_obj.length;
		var checked=0;
		for(i=0;i<fish_leng;i++){
			if(fish_obj[i].checked==true){
				checked+=1;
			}
		}
			
		if(checked==0){
			alert('주요어종을 하나이상 선택해주세요');
			return false;
		}
				
		if(!$('#busipostcode').val()||!$('#busiaddress1 ').val()||$('#busipostcode').val().trim() == '' ||$('#busiaddress1').val().trim() == '' ){
			alert('오시는 길을 입력해주세요');
			return false;
		}
		if(!$('#co_psg').val()||$('#co_psg').val().trim() == '' ){
			alert('수용인원을 입력해주세요');
			return false;
		}
		
		if(!$('input[name=reco_prc]').val()||!$('input[name=reco_prctp]').val()||$('input[name=reco_prc]').val().trim() == ''||$('input[name=reco_prctp]').val().trim() == ''){
			alert('이용료를 입력해주세요');
			return false;
		}
		
		var co_prctp='';
		var co_prc='';
		$("ul#charul").children().each(function(){ 
			if(co_prctp){
				co_prctp+='|';
			}
			if(co_prc){
				co_prc+='|';
			}
			co_prctp += $(this).children('input[name=reco_prctp]').val();
			co_prc += $(this).children('input[name=reco_prc]').val();
		});
		/*
		var co_prctp='';
		for(var i=0;i<=j;i++){
			if($('#reco_prctp_'+i).val()){
				if(co_prctp){
					co_prctp+='|';
				}
				co_prctp += $('#reco_prctp_'+i).val();
			}
		}
			
		var co_prc='';
		for(var i=0;i<=j;i++){
			if($('#reco_prc_'+i).val()){
				if(co_prc){
					co_prc+='|';
				}
				co_prc += $('#reco_prc_'+i).val();
			}
		}
		*/
		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		var fct_obj = document.getElementsByName("co_fct");
		var fct_leng = fct_obj.length;
		var checked2=0;
		for(i=0;i<fct_leng;i++){
			if(fct_obj[i].checked==true){
				checked2+=1;
			}
		}
			
		if(checked2==0){
			alert('시설안내를 하나 이상 선택해주세요');
			return false;
		}

		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		 // 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["co_intro"].exec("UPDATE_CONTENTS_FIELD", [ ]);

	    // 에디터의 내용에 대한 값 검증은 이곳에서
	    if(document.getElementById("co_intro").value==''){
	    	alert('사업장 소개를 입력해주세요.');
	    	return false;   
	    }
		
	    if($('#is_mimg').val() == "2" && !$('#mimg').val()){
			alert('대표 이미지를 등록하세요.');
			return false;
		}
	    
	    var nak_id = $('#nak_id').val();
	    if(nak_id){
	    	$("#supform").attr("action","/info/updateInfo.do");
	    }else {
			$("#supform").attr("action","/info/insertCorp.do");
	    }
		$("#supform").submit();
	}
	
	$("#insertCorp").click(function(){

	    var nak_id = $('#nak_id').val();

	    var co_addr = $('input[name=co_addr_1]').val() +
		  $('input[name=co_addr_2]').val() +
		  $('input[name=co_addr_3]').val();			

		var co_addr2 = $('input[name=co_addr2_1]').val() +
		   $('input[name=co_addr2_2]').val() +
		   $('input[name=co_addr2_3]').val();	

		var co_phone = $('select[name=co_phone1]').val() +
		   $('input[name=co_phone2]').val() + 
		   $('input[name=co_phone3]').val();

		var co_hphone = $('select[name=co_hphone1]').val() + 
			$('input[name=co_hphone2]').val() +
			$('input[name=co_hphone3]').val();

		var co_2hphone = $('select[name=co_2hphone1]').val() + 
			$('input[name=co_2hphone2]').val() +
			$('input[name=co_2hphone3]').val();

			$('input[name=co_addr]').val(co_addr);
			$('input[name=co_addr2]').val(co_addr2);
			$('input[name=co_phone]').val(co_phone);			
			$('input[name=co_hphone]').val(co_hphone);	
			$('input[name=co_2hphone]').val(co_2hphone);
			
		if( $('input[name=nak_id]').val()=='' && document.getElementById('seafishing').checked == false){
			alert('기본정보의 구분을 확인해주세요.');
				return false;
			}
		var info_obj = document.getElementsByName("co_info");
		var info_leng = info_obj.length;
		var checked2=0;
		for(i=0;i<info_leng;i++){
			if(info_obj[i].checked==true){
				checked2+=1;
			}
		}
		
		if(checked2==0){
			alert('낚시업종을 하나 이상 선택해주세요.');
			return false;
		}
		if(!$('#co_nm').val() || $('#co_nm').val().trim() == '' ){
			alert('상호명을 입력해주세요');
			return false;
		}
		if(!$('#ceo_nm').val()|| $('#ceo_nm').val().trim() == ''){
			alert('대표자를 입력해주세요');
			return false;
		}
		
		
		if(!$('#co_hphone2').val()||!$('#co_hphone3').val()||$('#co_hphone2').val().trim() == ''||$('#co_hphone3').val().trim() == ''){
			alert('핸드폰 번호를 입력해주세요');
			return false;
		}
		
		if(!$('#newpostcode').val()||!$('#newaddress1').val()||$('#newpostcode').val().trim() == '' ||$('#newaddress1').val().trim() == ''){
			alert('사업장 주소를 입력해주세요');
			return false;
		}
		var fish_obj = document.getElementsByName("co_fish");
		var fish_leng = fish_obj.length;
		var checked=0;
		for(i=0;i<fish_leng;i++){
			if(fish_obj[i].checked==true){
				checked+=1;
			}
		}
			
		if(checked==0){
			alert('주요어종을 하나이상 선택해주세요');
			return false;
		}
				
		if(!$('#busipostcode').val()||!$('#busiaddress1 ').val()||$('#busipostcode').val().trim() == '' ||$('#busiaddress1').val().trim() == '' ){
			alert('오시는 길을 입력해주세요');
			return false;
		}
		if(!$('#co_psg').val()||$('#co_psg').val().trim() == '' ){
			alert('수용인원을 입력해주세요');
			return false;
		}
		
		if(!$('input[name=reco_prc]').val()||!$('input[name=reco_prctp]').val()||$('input[name=reco_prc]').val().trim() == ''||$('input[name=reco_prctp]').val().trim() == ''){
			alert('이용료를 입력해주세요');
			return false;
		}
		
		var co_prctp='';
		var co_prc='';
		$("ul#charul").children().each(function(){ 
			if(co_prctp){
				co_prctp+='|';
			}
			if(co_prc){
				co_prc+='|';
			}
			co_prctp += $(this).children('input[name=reco_prctp]').val();
			co_prc += $(this).children('input[name=reco_prc]').val();
		});
		/*
		var co_prctp='';
		for(var i=0;i<=j;i++){
			if($('#reco_prctp_'+i).val()){
				if(co_prctp){
					co_prctp+='|';
				}
				co_prctp += $('#reco_prctp_'+i).val();
			}
		}
			
		var co_prc='';
		for(var i=0;i<=j;i++){
			if($('#reco_prc_'+i).val()){
				if(co_prc){
					co_prc+='|';
				}
				co_prc += $('#reco_prc_'+i).val();
			}
		}
		*/
		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		var fct_obj = document.getElementsByName("co_fct");
		var fct_leng = fct_obj.length;
		var checked2=0;
		for(i=0;i<fct_leng;i++){
			if(fct_obj[i].checked==true){
				checked2+=1;
			}
		}
			
		if(checked2==0){
			alert('시설안내를 하나 이상 선택해주세요');
			return false;
		}

		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		 // 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["co_intro"].exec("UPDATE_CONTENTS_FIELD", [ ]);

	    // 에디터의 내용에 대한 값 검증은 이곳에서
	    if(document.getElementById("co_intro").value==''){
	    	alert('사업장 소개를 입력해주세요.');
	    	return false;   
	    }
		
	    if($('#is_mimg').val() == "2" && !$('#mimg').val()){
			alert('대표 이미지를 등록하세요.');
			return false;
		}
	    
		window.name = "parent_window";
		/*
	    if(nak_id) {
			$("#supform").attr("target","parent_window");
			$("#supform").attr("action","/info/m/updateInfo.do");			
		} else {
			$("#supform").attr("target","parent_window");
			$("#supform").attr("action","/info/m/insertCorp.do");			
		}
		*/
		$('#save_status').val('r');
		$("#supform").attr("target","parent_window");
		$("#supform").attr("action","/info/m/insertCorpPreview.do");		
		$("#supform").submit();		
	});	

	
	/** kkh **/
	$("#preview").click(function(){
		var co_addr = $('input[name=co_addr_1]').val() +
		  $('input[name=co_addr_2]').val() +
		  $('input[name=co_addr_3]').val();			

		var co_addr2 = $('input[name=co_addr2_1]').val() +
		   $('input[name=co_addr2_2]').val() +
		   $('input[name=co_addr2_3]').val();	

		var co_phone = $('select[name=co_phone1]').val() +
		   $('input[name=co_phone2]').val() + 
		   $('input[name=co_phone3]').val();

		var co_hphone = $('select[name=co_hphone1]').val() + 
			$('input[name=co_hphone2]').val() +
			$('input[name=co_hphone3]').val();

		var co_2hphone = $('select[name=co_2hphone1]').val() + 
			$('input[name=co_2hphone2]').val() +
			$('input[name=co_2hphone3]').val();

			$('input[name=co_addr]').val(co_addr);
			$('input[name=co_addr2]').val(co_addr2);
			$('input[name=co_phone]').val(co_phone);			
			$('input[name=co_hphone]').val(co_hphone);	
			$('input[name=co_2hphone]').val(co_2hphone);
			
		if( $('input[name=nak_id]').val()=='' && document.getElementById('seafishing').checked == false){
			alert('기본정보의 구분을 확인해주세요.');
				return false;
			}
		var info_obj = document.getElementsByName("co_info");
		var info_leng = info_obj.length;
		var checked2=0;
		for(i=0;i<info_leng;i++){
			if(info_obj[i].checked==true){
				checked2+=1;
			}
		}
		
		if(checked2==0){
			alert('낚시업종을 하나 이상 선택해주세요.');
			return false;
		}
		if(!$('#co_nm').val() || $('#co_nm').val().trim() == '' ){
			alert('상호명을 입력해주세요');
			return false;
		}
		if(!$('#ceo_nm').val()|| $('#ceo_nm').val().trim() == ''){
			alert('대표자를 입력해주세요');
			return false;
		}
		
		
		if(!$('#co_hphone2').val()||!$('#co_hphone3').val()||$('#co_hphone2').val().trim() == ''||$('#co_hphone3').val().trim() == ''){
			alert('핸드폰 번호를 입력해주세요');
			return false;
		}
		
		if(!$('#newpostcode').val()||!$('#newaddress1').val()||$('#newpostcode').val().trim() == '' ||$('#newaddress1').val().trim() == ''){
			alert('사업장 주소를 입력해주세요');
			return false;
		}
		var fish_obj = document.getElementsByName("co_fish");
		var fish_leng = fish_obj.length;
		var checked=0;
		for(i=0;i<fish_leng;i++){
			if(fish_obj[i].checked==true){
				checked+=1;
			}
		}
			
		if(checked==0){
			alert('주요어종을 하나이상 선택해주세요');
			return false;
		}
				
		if(!$('#busipostcode').val()||!$('#busiaddress1 ').val()||$('#busipostcode').val().trim() == '' ||$('#busiaddress1').val().trim() == '' ){
			alert('오시는 길을 입력해주세요');
			return false;
		}
		if(!$('#co_psg').val()||$('#co_psg').val().trim() == '' ){
			alert('수용인원을 입력해주세요');
			return false;
		}
		
		if(!$('input[name=reco_prc]').val()||!$('input[name=reco_prctp]').val()||$('input[name=reco_prc]').val().trim() == ''||$('input[name=reco_prctp]').val().trim() == ''){
			alert('이용료를 입력해주세요');
			return false;
		}
		
		var co_prctp='';
		var co_prc='';
		$("ul#charul").children().each(function(){ 
			if(co_prctp){
				co_prctp+='|';
			}
			if(co_prc){
				co_prc+='|';
			}
			co_prctp += $(this).children('input[name=reco_prctp]').val();
			co_prc += $(this).children('input[name=reco_prc]').val();
		});
		
		/*
		var co_prctp='';
		for(var i=0;i<=j;i++){
			if($('#reco_prctp_'+i).val()){
				if(co_prctp){
					co_prctp+='|';
				}
				co_prctp += $('#reco_prctp_'+i).val();
			}
		}
			
		var co_prc='';
		for(var i=0;i<=j;i++){
			if($('#reco_prc_'+i).val()){
				if(co_prc){
					co_prc+='|';
				}
				co_prc += $('#reco_prc_'+i).val();
			}
		}
		*/
		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		var fct_obj = document.getElementsByName("co_fct");
		var fct_leng = fct_obj.length;
		var checked2=0;
		for(i=0;i<fct_leng;i++){
			if(fct_obj[i].checked==true){
				checked2+=1;
			}
		}
			
		if(checked2==0){
			alert('시설안내를 하나 이상 선택해주세요');
			return false;
		}

		$('#co_prctp').val(co_prctp);
		$('#co_prc').val(co_prc);
		
		 // 에디터의 내용이 textarea에 적용된다.
	    oEditors.getById["co_intro"].exec("UPDATE_CONTENTS_FIELD", [ ]);

	    // 에디터의 내용에 대한 값 검증은 이곳에서
	    if(document.getElementById("co_intro").value==''){
	    	alert('사업장 소개를 입력해주세요.');
	    	return false;   
	    }
		
	    if($('#is_mimg').val() == "2" && !$('#mimg').val()){
			alert('대표 이미지를 등록하세요.');
			return false;
		}
	    
	    window.name = "parent_window";
	    $('#save_status').val('w');
	    var openwin = window.open('','preview', 'width=1400, height=800, scrollbars=yes, resizable=yes');
		$("#supform").attr("target","preview");			
	    $("#supform").attr("action","/info/m/insertCorpPreview.do");
		$("#supform").submit();
		openwin.focus();
	});	

	/** kkh **/
	
	$("#cancel").click(function(){
		$(location).attr("href","/info/m/fishjob/login.do");
	});
	
	// textArea에 이미지 첨부
	function pasteHTML(filepath){
	    var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+filepath+'">';
	    oEditors.getById["co_intro"].exec("PASTE_HTML", [sHTML]);
	   
	}
		
		
</script>


<%@include file="../../layout/m/tail.jsp"%>
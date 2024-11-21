<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.Date" %>
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

<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

<style>
#map_wrap {position:relative;/*width:100%;height:500px;*/}
#menu_wrap {position:absolute;top:0;left:0;bottom:0;width:320px;margin:10px 0px 30px 10px;padding:5px;overflow-y:auto;background:rgba(255, 255, 255, 0.7);z-index: 1;font-size:12px;border-radius: 10px;}
.bg_white {background:#fff;}
#menu_wrap hr {display: block; height: 1px;border: 0; border-top: 2px solid #5F5F5F;margin:3px 0;}
#menu_wrap .option{text-align: center;}
#menu_wrap .option p {margin:10px 0;}  
#menu_wrap .option button {margin-left:5px;}
#placesList li {list-style: none;}
#placesList .item span {display: block;margin-top:4px;}
#placesList  {display: inline;margin-top:4px;}

#placesList .item .info{padding:10px 0 10px 55px;}
#placesList .info .gray {color:#8a8a8a;}
#placesList .item .markerbg {float:left;position:absolute;width:36px; height:37px;margin:10px 0 0 10px;background:url(http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;}
#placesList .item .marker_1 {background-position: 0 -10px;}
#placesList .item .marker_2 {background-position: 0 -56px;}
#placesList .item .marker_3 {background-position: 0 -102px}
#placesList .item .marker_4 {background-position: 0 -148px;}
#placesList .item .marker_5 {background-position: 0 -194px;}
#placesList .item .marker_6 {background-position: 0 -240px;}
#placesList .item .marker_7 {background-position: 0 -286px;}
#placesList .item .marker_8 {background-position: 0 -332px;}
#placesList .item .marker_9 {background-position: 0 -378px;}
#placesList .item .marker_10 {background-position: 0 -423px;}
#placesList .item .marker_11 {background-position: 0 -470px;}
#placesList .item .marker_12 {background-position: 0 -516px;}
#placesList .item .marker_13 {background-position: 0 -562px;}
#placesList .item .marker_14 {background-position: 0 -608px;}
#placesList .item .marker_15 {background-position: 0 -654px;}
</style>

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/e59ba62350.js"></script> 
<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
<script src="/naksinuri_original/common_main/js/common.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services"></script>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=kSzk_875XFgPsLskTEKN&submodules=geocoder"></script>


<form:form commandName="supform" id="supform" method="post" enctype="multipart/form-data" >
	
	<div id="fishjobView" class="content respon2">	
		<input type="hidden" name="fishing_type" value="${preview.fishing_type}">
		<input type="hidden" name="getco_fish" value="${preview.co_fish}">
		<input type="hidden" name="getaddr2_2" id="getaddr2_2"	value="${preview.co_addr2_2 }">
		<input type="hidden" name="getpublic_addr" id="getpublic_addr" value="${preview.co_addr2_2 }">
		<input type="hidden" name="atch_file_id" value="${preview.atch_file_id }">
		<input type="hidden" name="co_nm" value="${preview.co_nm}">
		<input type="hidden" id="nak_id" name="nak_id" value="${preview.nak_id}">
		<input type="hidden" name="rev_pass" id="rev_pass">
		<input type="hidden" name="rev_sn1" id="rev_sn1">
		
		
		<section id="basicInfo" class="floats">
		
		<center><div style="font-size: 48px; font-weight: bold; letter-spacing: -0.5pt; font-family: NanumSquare;">미리보기</div></center>

		<div id="date_container"></div>
		<div id="weather_container"></div>
		<div id="test4"></div>
		<div id="test3"></div>
		<div id="tide"></div>
		
			<div class="fish_slide">
				<ul>
					<li>
						<c:choose>
					 		<c:when test="${(pmimg.co_mimgsrc ne null) and (fn:length(pfilelist) ne 0)  }">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImagePreview.do'/>?atchFileId=<c:out value="${pmimg.co_mimgsrc}"/>&fileSn=<c:out value="${pmimg.file_sn}"/>' style="margin-left:auto; margin-right: auto;"  height="510" alt=<c:out value="${pmimg.orignl_file_nm}" />/>
					 		</c:when>
					 		<c:when test="${(mimg.co_mimgsrc ne null) and (fn:length(filelist) ne 0)  }">
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${mimg.co_mimgsrc}"/>&fileSn=<c:out value="${mimg.file_sn}"/>' style="margin-left:auto; margin-right: auto;"  height="510" alt=<c:out value="${mimg.orignl_file_nm}" />/>
					 		</c:when>
					 		<c:otherwise>
					 			<img alt="낚시누리" src="/naksinuri_original/common_main/img/noImage_big.png">
					 		</c:otherwise>
				 		</c:choose>
				 		<%--
						<c:choose>
					 		<c:when test="${(pmimg.co_mimgsrc eq null) and (fn:length(pfilelist) eq 0)  }">
					 			<img alt="noImage" src="/naksinuri_original/common_main/img/noImage_big.png">
					 		</c:when>
					 		<c:otherwise>
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${pmimg.co_mimgsrc}"/>&fileSn=<c:out value="${pmimg.file_sn}"/>' style="margin-left:auto; margin-right: auto;"  height="510" alt=<c:out value="${pmimg.orignl_file_nm}" />/>
					 		</c:otherwise>
				 		</c:choose>
				 		 --%>				 		
					</li>
						<c:forEach var="images" items="${newList}" varStatus="status">
							<li>
								<img src='<c:url value='/naksinuri_original/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${images.atch_file_id}"/>&fileSn=<c:out value="${images.file_sn}"/>' style="margin-left:auto; margin-right: auto;"  height="510" alt=<c:out value="${images.orignl_file_nm}" /> />
							</li>
						</c:forEach>
				</ul>
				<div class="outarrow">
					<p><span id="slider-next"></span><span id="slider-prev"></span></p>
				</div>
				<script>
					$('#basicInfo ul').bxSlider({
						auto: true,
						nextSelector: '#slider-next',
						prevSelector: '#slider-prev',
						nextText: '<i class="fa fa-angle-right"></i>',
						prevText: '<i class="fa fa-angle-left"></i>',
						pause: 5000
					});
				</script>
			</div>
			<div class="fishshop_info">
			
				<h1><span class="cate"><c:if test="${preview.co_addr2_2 ne null ||  preview.co_addr2_2 ne '' }"> [${sido[0]} ${sido[1]}]</c:if></span><em>${preview.co_nm}</em></h1>
				<div class="info">
					<c:choose>
						<c:when test="${preview.fishing_type eq 'boatfishing'  }">
							<dl><dt>선박명</dt><dd>${preview.co_nm}</dd></dl>
							<dl><dt>어선번호</dt><dd><c:if test="${preview.co_ship_num1 ne null &&  preview.co_ship_num1 ne '' }">${preview.co_ship_num1}</c:if>-<c:if test="${preview.co_ship_num2 ne null &&  preview.co_ship_num2 ne '' }">${preview.co_ship_num2}</c:if></dd></dl>
							<dl><dt>대표자</dt><dd>${preview.ceo_nm}</dd></dl>
							<c:if test="${preview.co_phone3 ne ''}">
								<dl><dt>일반전화</dt><dd><c:if test="${preview.co_phone1 ne ''}">${preview.co_phone1}-</c:if>${preview.co_phone2}-${preview.co_phone3}</dd></dl>
							</c:if>
							<dl><dt>휴대전화</dt><dd><c:if test="${(preview.co_hphone2 ne '')&& (preview.co_hphone3 ne '') }">${preview.co_hphone1}-${preview.co_hphone2}-${preview.co_hphone3}</c:if></dd></dl>
							<c:if test="${!empty preview.co_2hphone3}">
								<dl><dt>휴대전화2</dt><dd><c:if test="${preview.co_2hphone1 ne ''}">${preview.co_2hphone1}-</c:if>${preview.co_2hphone2}-${preview.co_2hphone3}</dd></dl>
							</c:if>
							<dl><dt>낚시업종</dt><dd>선상낚시</dd></dl>
							<dl><dt>어선규모</dt><dd><c:if test="${preview.bo_size ne '' }">${preview.bo_size}톤</c:if></dd></dl>
							<dl><dt>어선속도</dt><dd><c:if test="${preview.bo_spd ne ''}">${preview.bo_spd}knot</c:if></dd></dl>
							<dl><dt>승선인원</dt><dd><c:if test="${preview.bo_psg ne ''}">${preview.bo_psg}명</c:if></dd></dl>
							<dl><dt>이용시간</dt><dd><c:if test="${preview.co_stm ne ''}">${preview.co_stm} </c:if><c:if test="${preview.co_etm ne ''}"> ~ ${preview.co_etm}</c:if></dd></dl>
							<dl><dt>카드결제</dt><dd><c:if test="${preview.co_credit eq '0'}">불가</c:if><c:if test="${preview.co_credit eq '1'}">가능</c:if></dd></dl>
						    <c:choose>
							   	<c:when test="${preview.co_web eq 'http://' }"> </c:when>
							  		<c:otherwise>
										<dl><dt>홈페이지</dt><dd><a href="${preview.co_web}" target="_blank">${preview.co_web}</a></dd></dl>
									</c:otherwise>
							</c:choose>
							<dl><dt>사업장주소</dt><dd>${preview.co_addr_2} ${preview.co_addr_3}</dd></dl>
							<c:if test="${preview.upd_date ne null}">	
								<dl><dt>업데이트</dt><dd>${preview.upd_date.substring(0,10)}</dd></dl>
							</c:if>
							<c:if test="${preview.upd_date eq null }">	
								<dl><dt>업데이트</dt><dd>${preview.reg_date.substring(0,10)}</dd></dl>	
							</c:if>		
						</c:when>
						<c:when test="${preview.fishing_type eq 'riverfishing' || preview.fishing_type eq 'seafishing' }">
							<dl><dt>상호명</dt><dd>${preview.co_nm}</dd></dl>
							<dl><dt>대표자</dt><dd>${preview.ceo_nm}</dd></dl>
							<c:if test="${preview.co_phone3 ne ''}">
								<dl><dt>일반전화</dt><dd><c:if test="${preview.co_phone1 ne ''}">${preview.co_phone1}-</c:if>${preview.co_phone2}-${preview.co_phone3}</dd></dl>
							</c:if>
							<dl><dt>휴대전화</dt><dd><c:if test="${preview.co_hphone2 ne '' && preview.co_hphone3 ne '' }">${preview.co_hphone1}-${preview.co_hphone2}-${preview.co_hphone3}</c:if></dd></dl>
							<c:if test="${!empty preview.co_2hphone3}">
								<dl><dt>휴대전화2</dt><dd><c:if test="${preview.co_2hphone1 ne ''}">${preview.co_2hphone1}-</c:if>${preview.co_2hphone2}-${preview.co_2hphone3}</dd></dl>
							</c:if>
							<dl><dt>낚시업종</dt><dd><c:if test="${preview.co_info ne '' }">${preview.co_info}</c:if></dd></dl>
							<dl><dt>수용인원</dt><dd><c:if test="${preview.co_psg ne ''}">${preview.co_psg}명</c:if></dd></dl>
							<dl><dt>이용시간</dt><dd><c:if test="${preview.co_stm ne ''}">${preview.co_stm} </c:if><c:if test="${preview.co_etm ne ''}"> ~ ${preview.co_etm}</c:if></dd></dl>
							<dl><dt>카드결제</dt><dd><c:if test="${preview.co_credit eq '0'}">불가</c:if><c:if test="${preview.co_credit eq '1'}">가능</c:if></dd></dl>
							<c:choose>
								<c:when test="${preview.co_web eq 'http://' or preview.co_web eq ''}"></c:when>
								<c:otherwise>
									<dl><dt>홈페이지</dt><dd><a href="${preview.co_web}" target="_blank">${preview.co_web}</a></dd></dl>
								</c:otherwise>
							</c:choose>
							<dl><dt>사업장주소</dt><dd>${preview.co_addr_2} ${preview.co_addr_3}</dd></dl>
							<c:if test="${preview.upd_date ne null}">	
								<dl><dt>업데이트</dt><dd>${preview.upd_date.substring(0,10)}</dd></dl>
							</c:if>
							<c:if test="${preview.upd_date eq null}">	
								<dl><dt>업데이트</dt><dd>${preview.reg_date.substring(0,10)}</dd></dl>	
							</c:if>	
						</c:when>
						<c:otherwise>							
							<dl><dt>상호명</dt><dd>${preview.co_nm}</dd></dl>
							<dl><dt>대표자</dt><dd>${preview.ceo_nm}</dd></dl>
							<c:if test="${preview.co_phone3 ne ''}">
								<dl><dt>일반전화</dt><dd><c:if test="${preview.co_phone1 ne ''}">${preview.co_phone1}-</c:if>${preview.co_phone2}-${preview.co_phone3}</dd></dl>
							</c:if>
							<dl><dt>휴대전화</dt><dd><c:if test="${preview.co_hphone2 ne '' && preview.co_hphone3 ne '' }">${preview.co_hphone1}-${preview.co_hphone2}-${preview.co_hphone3}</c:if></dd></dl>
							<c:if test="${!empty preview.co_2hphone3}">
								<dl><dt>휴대전화2</dt><dd><c:if test="${preview.co_2hphone1 ne ''}">${preview.co_2hphone1}-</c:if>${preview.co_2hphone2}-${preview.co_2hphone3}</dd></dl>
							</c:if>
							<dl><dt>낚시업종</dt><dd><c:if test="${preview.co_info ne ''  }">${preview.co_info}</c:if></dd></dl>
							<dl><dt>수용인원</dt><dd><c:if test="${preview.bo_psg ne ''}">${preview.co_psg}명</c:if></dd></dl>
							<dl><dt>이용시간</dt><dd><c:if test="${preview.co_stm ne ''}">${preview.co_stm} </c:if><c:if test="${preview.co_etm ne ''}"> ~ ${preview.co_etm}</c:if></dd></dl>
							<dl><dt>카드결제</dt><dd><c:if test="${preview.co_credit eq '0'}">불가</c:if><c:if test="${preview.co_credit eq '1'}">가능</c:if></dd></dl>
							<dl><dt>홈페이지</dt><dd><a href="${preview.co_web}" target="_blank"><c:choose><c:when test="${preview.co_web eq 'http://' }"> </c:when><c:otherwise>${preview.co_web}</c:otherwise></c:choose></a></dd></dl>
							<dl><dt>사업장주소</dt><dd>${preview.co_addr_2} ${preview.co_addr_3}</dd></dl>
							<c:if test="${preview.upd_date ne null}">	
								<dl><dt>업데이트</dt><dd>${preview.upd_date.substring(0,10)}</dd></dl>
							</c:if>
							<c:if test="${preview.upd_date eq null }">	
								<dl><dt>업데이트</dt><dd>${preview.reg_date.substring(0,10)}</dd></dl>	
							</c:if>	
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</section>
		
		<div id="btnArea" class="noupline" style="padding-top:0px;" >
			<ul class="floats">
				<li class="fr">
					<a href="#;" onclick="alert('미리보기모드에서는 사용하실 수 없습니다.')" class="btn_review btn_blue">낚시터 후기</a>
					<a href="#;" onclick="alert('미리보기모드에서는 사용하실 수 없습니다.')" class="btn_report btn_red">잘못된 정보 신고</a>
					<a href="#;" onclick="alert('미리보기모드에서는 사용하실 수 없습니다.')" class="btn_list btn_gray">목록</a>
				</li>
			</ul>
		</div>

		<section id="majorFish" class="icon_box">
			<h2>주요어종</h2>
			<ul class="floats" id="fishul">
				<c:set var="sub" value="${2}"></c:set>
				<c:forEach var="fishes" items="${pfishlist}" varStatus="status">
					<c:set var="subname" value="${sub}${fishes.fish_name}${sub}"></c:set>
					<c:if test ="${fn:contains(preview.co_fish,subname)}">
						<li><span><img src="/naksinuri_original/common_main/img/ico_fish/${fishes.fish_img_name}" alt="${fishes.fish_name}" /></span><em>${fishes.fish_name}</em></li>
					</c:if>	
				</c:forEach>
			</ul>
		</section>
		
		<section id="majorFacilities" class="icon_box">
			<h2>주요시설</h2>
			<ul class="floats">
			<c:choose>
					<c:when test="${preview.fishing_type eq 'riverfishing' || preview.fishing_type eq 'seafishing'}">
						<c:if test ="${fn:contains(preview.co_fct,'에어컨')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_aircon.png" alt="에어컨" /></span><em>에어컨</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'화장실')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_toilet.png" alt="화장실" /></span><em>화장실</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'구명조끼')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_gmzo.png" alt="구명조끼" /></span><em>구명조끼</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'구명밧줄')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_gmrope.png" alt="구명밧줄" /></span><em>구명밧줄</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'보험')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_bohum.png" alt="보험" /></span><em>보험</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'난방')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_warmer.png" alt="난방" /></span><em>난방</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'소화기')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_fireext.png" alt="소화기" /></span><em>소화기</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'CCTV')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_cctv.png" alt="CCTV" /></span><em>CCTV</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'그늘막')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_shad.png" alt="그늘막" /></span><em>그늘막</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'식당')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sikdang.png" alt="식당" /></span><em>식당</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'커피')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_coffee.png" alt="커피" /></span><em>커피</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'매점')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_maejum.png" alt="매점" /></span><em>매점</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'주차장')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_parking.png" alt="주차장" /></span><em>주차장</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'숙소')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sookso.png" alt="숙소" /></span><em>숙소</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'휴게실')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_huke.png" alt="휴게실" /></span><em>휴게실</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'냉장고')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_refr.png" alt="냉장고" /></span><em>냉장고</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'TV')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_tv.png" alt="TV" /></span><em>TV</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'인터넷')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_internet.png" alt="인터넷" /></span><em>인터넷</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'취사장')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_chui.png" alt="취사장" /></span><em>취사장</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'야영지')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_yayung.png" alt="야영지" /></span><em>야영지</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'낚시점')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_naksijum.png" alt="낚시점" /></span><em>낚시점</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'샤워실')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_showerroom.png" alt="샤워실" /></span><em>샤워실</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'선풍기')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_fan.png" alt="선풍기" /></span><em>선풍기</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'버너')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_burner.png" alt="버너" /></span><em>버너</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'숯불그릴')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sootgril.png" alt="숯불그릴" /></span><em>숯불그릴</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'침구')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_chimkoo.png" alt="침구" /></span><em>침구</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'낚시대')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_roddis.png" alt="낚시대" /></span><em>낚시대</em></li>						
						</c:if>						
						<c:if test ="${fn:contains(preview.co_fct,'회떠드림')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sasimi.png" alt="회떠드림" /></span><em>회떠드림</em></li>						
						</c:if>
					</c:when>
					
					<c:when test="${preview.fishing_type eq 'boatfishing' }">
						<c:if test ="${fn:contains(preview.co_fct,'에어컨')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_aircon.png" alt="에어컨" /></span><em>에어컨</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'화장실')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_toilet.png" alt="화장실" /></span><em>화장실</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'구명조끼')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_gmzo.png" alt="구명조끼" /></span><em>구명조끼</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'구명밧줄')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_gmrope.png" alt="구명밧줄" /></span><em>구명밧줄</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'보험')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_bohum.png" alt="보험" /></span><em>보험</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'난방')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_warmer.png" alt="난방" /></span><em>난방</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'소화기')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_fireext.png" alt="소화기" /></span><em>소화기</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'CCTV')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_cctv.png" alt="CCTV" /></span><em>CCTV</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'그늘막')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_shad.png" alt="그늘막" /></span><em>그늘막</em></li>						
						</c:if>						
						<c:if test ="${fn:contains(preview.co_fct,'식탁')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_table.png" alt="식탁" /></span><em>식탁</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'의자')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_chair.png" alt="의자" /></span><em>의자</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'전동릴')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_autorill.png" alt="전동릴" /></span><em>전동릴</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'안내방송')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_annaebc.png" alt="안내방송" /></span><em>안내방송</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'무전기')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_moojun.png" alt="무전기" /></span><em>무전기</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'어군탐지기')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_tamzi.png" alt="어군탐지기" /></span><em>어군탐지기</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'레이더')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_radar.png" alt="레이더" /></span><em>레이더</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'플로터')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_floater.png" alt="플로터" /></span><em>플로터</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'쏘나')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sonar.png" alt="쏘나" /></span><em>쏘나</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'쿨러')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_cooler.png" alt="쿨러" /></span><em>쿨러</em></li>						
						</c:if>
						<c:if test ="${fn:contains(preview.co_fct,'회떠드림')}">						
						<li><span><img src="/naksinuri_original/common_main/img/ico_facilities/fac_sasimi.png" alt="회떠드림" /></span><em>회떠드림</em></li>						
						</c:if>
					</c:when>
			</c:choose>
			</ul>
		</section>
		
		<section id="majorPrice" class="icon_box">
			<h2>이용료</h2>
			<input type="hidden" name="getco_prc" value="${preview.co_prc}">
			<input type="hidden" name="getco_prctp" value="${preview.co_prctp }">
			<ul class="floats" id="prcul">
				
			</ul>
		</section>

		<section id="majorComment" class="icon_box">
			<h2>소개</h2>
			<div class="comment">
				${preview.co_intro }
			</div>
		</section>

		<section id="majorWeather" class="icon_box">
			<h2>${preview.co_addr2_2.substring(0,2)} 날씨/물때</h2>
			<ul class="floats">
				<li id="li_day1">
					<div class="weather">
							<em id="weather_date1"></em>
						<div id="weather1">
							<div class="date_ico" id="date_ico1"></div>
							<div class="weatherinfo" id="weatherinfo1">
								<p id="w"></p>
								<p id="ww"></p>
								<p id="tide_info1"></p>
							</div>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup0"></span>
							
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown0"></span>
							
						</div>
					</div>
				</li>
				<li id="li_day2">
					<div class="weather">
						<em id="weather_date2"></em>
						<div id="weather2">
							<div class="date_ico" id="date_ico2"></div>
							<div class="weatherinfo" id="weatherinfo2">
								<p id="w2"></p>
								<p id="ww2"></p>
								<p id="tide_info2"></p>
							</div>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup1"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown1"></span>	
						</div>
					</div>
				</li>
				<li id="li_day3">
					<div class="weather">
						<em id="weather_date3"></em>
						<div class="date_ico" id="date_ico3"></div>
						<div class="weatherinfo">
							<p id="weatherinfo3"></p>
							<p id="tide_info3"></p>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup2"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown2"></span>
						</div>
					</div>
				</li>
				<li id="li_day4">
					<div class="weather">
						<em id="weather_date4"></em>
						<div class="date_ico" id="date_ico4"></div>
						<div class="weatherinfo">
							<p id="weatherinfo4"></p>
							<p id="tide_info4"></p>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup3"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown3"></span>
						</div>
					</div>
				</li>
				<li id="li_day5">
					<div class="weather">
						<em id="weather_date5"></em>
						<div class="date_ico"  id="date_ico5"></div>
						<div class="weatherinfo">
							<p id="weatherinfo5"></p>
							<p id="tide_info5"></p>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup4"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown4"></span>
						</div>
					</div>
				</li>
				<li id="li_day6">
					<div class="weather">
						<em id="weather_date6"></em>
						<div class="date_ico"  id="date_ico6"></div>
						<div class="weatherinfo">
							<p id="weatherinfo6"></p>
							<p id="tide_info6"></p>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup5"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown5"></span>
						</div>
					</div>
				</li>
				<li id="li_day7">
					<div class="weather">
						<em id="weather_date7"></em>
						<div class="date_ico"  id="date_ico7"></div>
						<div class="weatherinfo">
							<p id="weatherinfo7"></p>
							<p id="tide_info7"></p>
						</div>
					</div>
					<div class="scale_of_tide">
						<div class="scale_of_tide_box scale_of_tide1">
							<span class="tideup" id="tideup6"></span>
						</div>
						<div class="scale_of_tide_box scale_of_tide2">
							<span class="tidedown" id="tidedown6"></span>
						</div>
					</div>
				</li>
			</ul>
		</section>
		<section id="mapArea">
			<div class="tabArea">
				<ul class="floats">
					<li class="on"><a href="#;" onclick="locate()" id="locate"><i class="fa fa-map-marker" aria-hidden="true"></i> 오시는길</a></li>
					<li><a href="#;" onclick="public_festival()" id="public_festival"><i class="fa fa-flag" aria-hidden="true"></i> 축제/행사</a></li>
					<li><a href="#;" onclick="public_trv()" id="public_trv"><i class="fa fa-map" aria-hidden="true"></i> 관광지</a></li>
					<li><a href="#;" onclick="public_food()" id="public_food"><i class="fa fa-cutlery" aria-hidden="true"></i> 음식점</a></li>
					<li><a href="#;" onclick="public_shop()" id="public_shop"><i class="fa fa-shopping-bag" aria-hidden="true"></i> 쇼핑</a></li>
				</ul>
			</div>
			<div id="map_wrap">
				<div id="map" style="width:1281px;height:472px;position:relative;overflow:hidden;"></div>
				 <div id="menu_wrap" class="bg_white" style="display:none">
				 <ul id="placesList">
				 </ul>
				 </div>
			</div>
		</section>

		<%--
		<div id="btnArea">
			<ul class="floats">
				<li>
					<a onclick="go_prev(${prev.nak_id});" class="btn_prev btn_white"><i class="fa fa-angle-left" aria-hidden="true"></i> 이전글</a>
					<a onclick="go_next(${next.nak_id});" class="btn_next btn_white">다음글 <i class="fa fa-angle-right" aria-hidden="true"></i></a>
				</li>
				<li class="fr">
					<a href="#;" onclick="go_review()" class="btn_review btn_blue">낚시터 후기</a>
					<a href="#;" onclick="fishjob_report()" class="btn_report btn_red">잘못된 정보 신고</a>
					<a href="/info/fishjob/list.do" class="btn_list btn_gray">목록</a>
				</li>
			</ul>
		</div>
		--%>
		<div id="btnArea">
			<a href="#;" onclick="opener_save()" class="btn_review btn_blue">등록 신청</a>
			<a href="#" onclick="self.window.close();" class="btn_review btn_blue">취소</a>
		</div>
</form:form>

		<form name="findForm" id="findForm" action ="<c:url value='/info/findCorp.do'/>" method="post">
			<input type="hidden" id="co_nm" name="co_nm" value="${preview.co_nm}" />
			<input type="hidden" id="ceo_nm" name="ceo_nm" value="${preview.ceo_nm}" />
		</form>			

		<div class="reply_box">
			<div class="reply_list">
				<c:forEach var="rev" items="${review_list}">
					<div class="reply_con">
						<span class="name">제목:${rev.rev_title}</span>
						<span class="date">작성자:${rev.rev_writer}</span>
						<span class="date">${fn:substring(rev.rev_regdate,0,16)}</span>
						<a href="#;" data-toggle="modal" data-target="#udtPassword" data-rev1="${rev.rev_sn}" class="replyBtn" onlclick="remove_pass()">[수정]</a>
						<a href="#;" data-toggle="modal" data-target="#changePassword" data-rev="${rev.rev_sn}" class="replyBtn">[삭제]</a>
							<p>${rev.rev_cont}</p>
					</div>
				</c:forEach>
			</div>
		</div>
		
	</div>


<div class="password_check modal fade" id="udtPassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<p class="comment">해당 낚시터 후기글을 수정합니다. <br>비밀번호를 입력해주세요.</p>
			<input type="password" class="del_input" id="rev_pass1" placeholder="비밀번호를 입력해주세요." />
			<div class="btn_box">
				<button class="cp_btn del_btn" onclick="rev_udt()" >확인</button>
				<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
			</div>
		</div>
	</div>
</div>

<div class="password_check modal fade" id="changePassword" tabindex="1" role="dialog" aria-labelledby="myModalLabel" style="z-index:9999">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<p class="comment">해당 낚시터 후기글을 삭제합니다. <br>비밀번호를 입력해주세요.</p>
			<input type="password" class="del_input" id="rev_pass2" placeholder="비밀번호를 입력해주세요." />
			<div class="btn_box">
				<button class="cp_btn del_btn" onclick="rev_del()" >확인</button>
				<a href="#;" class="cp_btn remote_btn" data-dismiss="modal" aria-label="Close">취소</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function go_modify() {
	$('#findForm').submit();
}

function go_prev(nak_id){
	if(nak_id==null){
		alert('이전글이 없습니다.');
		return false;
	}
	
	var form = document.getElementById("supform");
	$('#nak_id').val(nak_id);
	form.action="/info/fishjob/view.do";
	form.submit();
}

function go_next(nak_id){
	if(nak_id==null){
		alert('다음글이 없습니다.');
		return false;
	}
	
	var form = document.getElementById("supform");
	$('#nak_id').val(nak_id);
	form.action="/info/fishjob/view.do";
	form.submit();
}

$('#changePassword').on('show.bs.modal',function(e){
	var aa = $(e.relatedTarget).data('rev');
	$('#rev_sn1').val(aa);
	})
	
$('#udtPassword').on('show.bs.modal',function(e){
	var aa = $(e.relatedTarget).data('rev1');
	$('#rev_sn1').val(aa);
	})
	
function rev_udt(){
	$('#rev_pass').val($('#rev_pass1').val());
	var form = document.getElementById("supform");
	form.action = "/info/review/update_info.do";
	form.submit();
}

function rev_del(){
	$('#rev_pass').val($('#rev_pass2').val());
	var form = document.getElementById("supform");
	form.action ="/info/review/delete_data.do";
	form.submit();
}

function go_review(){
	var form = document.getElementById('supform');
	form.action="/info/fishjob/review.do";
	form.submit();
	
}

function fishjob_report(){
	var form = document.getElementById('supform');
	form.action="/info/fishjobreport/write.do";
	form.submit();
}

$(document).ready(function(){
	
	/** 날씨 1~3일 */

	var public_addr = $("#getaddr2_2").val();
	var substr_addr = public_addr.substring(0,2);

	var data =  {address : substr_addr};
	var url = "/weatherinfo.do";

	$.ajax({
		type:"GET",
		url : url,
		dataType: 'json',
		data : data,
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log(data);
			
			 var week = new Array('일요일','월요일','화요일','수요일','목요일','금요일','토요일');
			 var date = new Date();
			 var tomorrow1 = new Date();
			 var nxt_tomorrow1 = new Date();
			 var week_day4 = new Date();
			 var week_day5 = new Date();
			 var week_day6 = new Date();
			 var week_day7 = new Date();
			 
// 			 오늘부터 일주일동안의 날짜 구하기
// 			 내일 날짜 = 오늘날짜+1 설정
			 tomorrow1.setDate(tomorrow1.getDate()+1);
// 			 모레 날짜 = 오늘날짜+2 설정
			 nxt_tomorrow1.setDate(nxt_tomorrow1.getDate()+2);
			 week_day4.setDate(week_day4.getDate()+3);
			 week_day5.setDate(week_day5.getDate()+4);
			 week_day6.setDate(week_day6.getDate()+5);
			 week_day7.setDate(week_day7.getDate()+6);
			 
			 

			    var year = date.getFullYear();
			  	var year1 = tomorrow1.getFullYear();
			  	var year2 = nxt_tomorrow1.getFullYear();
			  	var year3 = week_day4.getFullYear();
			  	var year4 = week_day5.getFullYear();
			  	var year5 = week_day6.getFullYear();
			  	var year6 = week_day7.getFullYear();
			  	
			    var month = date.getMonth()+1;
			    var month1 = tomorrow1.getMonth()+1;
			    var month2 = nxt_tomorrow1.getMonth()+1;
			    var month3 = week_day4.getMonth()+1;
			    var month4 = week_day5.getMonth()+1;
			    var month5 = week_day6.getMonth()+1;
			    var month6 = week_day7.getMonth()+1;
			    
			    var day = date.getDate();
			    var day1 = tomorrow1.getDate();
			    var day2 = nxt_tomorrow1.getDate();
			    var day3 = week_day4.getDate();
			    var day4 = week_day5.getDate();
			    var day5 = week_day6.getDate();
			    var day6 = week_day7.getDate();
			    
		
// 			    날짜중 한자리인경우 ex) 01 포맷 만들기
// 			    첫날
			    if(month < 10){
			        month = "0"+month;
			    }			   
			    if(day < 10){
			        day = "0"+day;
			    }
// 			    내일
			    if(day1 < 10){
			        day1 = "0"+day1;
			    }
			    if(month1 < 10){
			        month1 = "0"+month1;
			    }
// 			    모레
			    if(day2 < 10){
			        day2 = "0"+day2;
			    }
			    if(month2 < 10){
			        month2 = "0"+month2;
			    }
//   			4일
			    if(day3 < 10){
			        day3 = "0"+day3;
			    }
			    if(month3 < 10){
			        month3 = "0"+month3;
			    }
//   			5일
			    if(day4 < 10){
			        day4 = "0"+day4;
			    }
			    if(month4 < 10){
			        month4 = "0"+month4;
			    }
//   			6일
			    if(day5 < 10){
			        day5 = "0"+day5;
			    }
			    if(month5 < 10){
			        month5 = "0"+month5;
			    }
			    
//   			7일
			    if(day6 < 10){
			        day6 = "0"+day6;
			    }
			    if(month6 < 10){
			        month6 = "0"+month6;
			    }
// 			     오늘요일 구하기 
			    var dayforc1 = year+"-"+month+"-"+day;
			    var dayforc2 = year1+"-"+month1+"-"+day1;
			    var dayforc3 = year2+"-"+month2+"-"+day2;
			    var dayforc4 = year3+"-"+month3+"-"+day3;
			    var dayforc5 = year4+"-"+month4+"-"+day4;
			    var dayforc6 = year5+"-"+month5+"-"+day5;
			    var dayforc7 = year6+"-"+month6+"-"+day6;
			    
			    
// 				동네예보 api 날짜비교하기위한 포맷설정
			    var today = year+""+month+""+day;
			    var tomorrow = year1+""+month1+""+day1;
			    var nxt_tomorrow = year2+""+month2+""+day2;
			   	
			    
			    
			    
				var getday1 = new Date(dayforc1).getDay();
				var getday2 = new Date(dayforc2).getDay();
				var getday3 = new Date(dayforc3).getDay();
				var getday4 = new Date(dayforc4).getDay();
				var getday5 = new Date(dayforc5).getDay();
				var getday6 = new Date(dayforc6).getDay();
				var getday7 = new Date(dayforc7).getDay();
				var Label1 = week[getday1];
				var Label2 = week[getday2];
				var Label3 = week[getday3];
				var Label4 = week[getday4];
				var Label5 = week[getday5];
				var Label6 = week[getday6];
				var Label7 = week[getday7];
				
				
				
				$('#weather_date1').append(Label1);
				$('#weather_date2').append(Label2);
				$('#weather_date3').append(Label3);
				$('#weather_date4').append(Label4);
				$('#weather_date5').append(Label5);
				$('#weather_date6').append(Label6);
				$('#weather_date7').append(Label7);
				
				if(Label1 == '토요일'){
					$('#li_day1').addClass('sat');
				}
			
				if(Label2 == '토요일'){
					$('#li_day2').addClass('sat');
				}
				
				if(Label3 == '토요일'){
					$('#li_day3').addClass('sat');
				}
				
				if(Label4 == '토요일'){
					$('#li_day4').addClass('sat');
				}
				
				if(Label5 == '토요일'){
					$('#li_day5').addClass('sat');
				}
				
				if(Label6 == '토요일'){
					$('#li_day6').addClass('sat');
				}
				
				if(Label7 == '토요일'){
					$('#li_day7').addClass('sat');
				}
				
				
				if(Label1 == '일요일'){
					$('#li_day1').addClass('sun');
				}
			
				if(Label2 == '일요일'){
					$('#li_day2').addClass('sun');
				}
				
				if(Label3 == '일요일'){
					$('#li_day3').addClass('sun');
				}
				
				if(Label4 == '일요일'){
					$('#li_day4').addClass('sun');
				}
				
				if(Label5 == '일요일'){
					$('#li_day5').addClass('sun');
				}
				
				if(Label6 == '일요일'){
					$('#li_day6').addClass('sun');
				}
				
				if(Label7 == '일요일'){
					$('#li_day7').addClass('sun');
				}
			for(var i=0;i<data.length;i++){
				//오늘
		
			
				if(data[i].fcstDate == today && data[i].category == 'SKY' && data[i].fcstTime =='1500'){
					if(data[i].fcstValue == '1'){
						$('#w').append('맑음');	
						$('#date_ico1').append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />');
					}else if(data[i].fcstValue == '2'){
						$('#w').append('구름조금');	
						$('#date_ico1').append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />');
					}else if(data[i].fcstValue == '3'){
						$('#w').append('구름많음');
						$('#date_ico1').append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름많음" />');
					}else if(data[i].fcstValue == '4'){
						$('#w').append('흐림');
						$('#date_ico1').append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="흐림" />');
					}
					
				}
			
				//강수형태 ex) 비 ,눈, 짓눈깨비 정보는 기상예보 오전6시 기준의 데이터를 가져옵니다.
				if(data[i].fcstDate == today && data[i].category =='PTY'&& data[i].fcstTime =='0600'){
						if(data[i].fcstValue=="1"){
							$('#w').remove();
							$('#ww').append('비');	
							$('#date_ico1').remove();
							$('#weather1').prepend('<div class="date_ico" id="date_ico11"></div>');
							$('#date_ico11').append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />');
						}else if(data[i].fsctValue=="2"){
							$('#w').remove();
							$('#ww').append('비/눈');	
							$('#date_ico1').remove();
							$('#weather1').prepend('<div class="date_ico" id="date_ico11"></div>');
							$('#date_ico11').append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비/눈" />');
						}else if(data[i].fcstValue=="3"){
							$('#w').remove();
							$('#ww').append('눈');	
							$('#date_ico1').remove();
							$('#weather1').prepend('<div class="date_ico" id="date_ico11"></div>');
							$('#date_ico11').prepend('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />');
						} 
						
					
				} 	
			
// 				if(data[i].fcstDate == today && data[i].category == 'TMN' && data[i].fcstTime=='0600' ){
// 					$("#weatherinfo1").append(data[i].fcstValue);
// 				}
// 				if(data[i].fcstDate == today && data[i].category == 'TMX' && data[i].fcstTime=='1500' ){
// 					$('#weatherinfo1').append('/'+data[i].fcstValue +'℃');
// 				}
			
			
				//내일
				if(data[i].fcstDate == tomorrow && data[i].category == 'SKY' && data[i].fcstTime=='1500'){
					if(data[i].fcstValue == '1'){
						$('#w2').append('맑음');	
						$('#date_ico2').append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />');
					}else if(data[i].fcstValue == '2'){
						$('#w2').append('구름조금');
						$('#date_ico2').append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />');
					}else if(data[i].fcstValue == '3'){
						$('#w2').append('구름많음');	
						$('#date_ico2').append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름많음" />');
					}else if(data[i].fcstValue == '4'){
						$('#w2').append('흐림');	
						$('#date_ico2').append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="흐림" />');
					}
					
				}
				
			
			 
				//강수형태 ex) 비 ,눈, 짓눈깨비 정보는 기상예보 6시 기준의 데이터를 가져옵니다.
				if(data[i].fcstDate == tomorrow && data[i].category =='PTY'&& data[i].fcstTime =='0600'){
						if(data[i].fcstValue=="1"){
							$('#w2').remove();
							$('#ww2').append('비');	
							$('#date_ico2').remove();
							$('#weather2').prepend('<div class="date_ico" id="date_ico22"></div>');
							$('#date_ico22').append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />');
						}else if(data[i].fsctValue=="2"){
							$('#w2').remove();
							$('#ww2').append('비/눈');	
							$('#date_ico2').remove();
							$('#weather2').prepend('<div class="date_ico" id="date_ico22"></div>');
							$('#date_ico22').append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비/눈" />');
						}else if(data[i].fcstValue=="3"){
							$('#w2').remove();
							$('#ww2').append('눈');	
							$('#date_ico2').remove();
							$('#weather2').prepend('<div class="date_ico" id="date_ico22"></div>');
							$('#date_ico22').prepend('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />');
						} 
						
					
				} 	
				
// 				if(data[i].fcstDate == tomorrow && data[i].category == 'TMN' && data[i].fcstTime=='0600' ){
// 					$('#weatherinfo2').append(data[i].fcstValue +'/');
// 				}
// 				if(data[i].fcstDate == tomorrow && data[i].category == 'TMX' && data[i].fcstTime=='1500' ){
// 					$('#weatherinfo2').append(data[i].fcstValue +'℃');
// 				}
			
			
		
				
			
				
			}
			
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});

	
	


	var addr2_2 = $('input[name=getaddr2_2]').val();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

//		// 지도를 생성합니다    
	var map = new daum.maps.Map(mapContainer, mapOption); 
	
//		// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();
	

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch( addr2_2 , function(result, status) {
	
		    // 정상적으로 검색이 완료됐으면 
		     if (status === daum.maps.services.Status.OK) {
	
		        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new daum.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new daum.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+addr2_2+'</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
	});

	
	
	var public_addr11 = $("#getaddr2_2").val();
	var substr_addr11 = public_addr11.substring(0,2);
	var forecastCode;
	
 
	if(substr_addr11 == "서울" || substr_addr11 == "인천"||substr_addr11 == "경남"||substr_addr11 == "경북"||substr_addr11 == "경기"){
		forecastCode = "11B00000"
	}else if(substr_addr11 == "강원"){
		forecastCode = "11D10000"
	}else if(substr_addr11 == "충남"||substr_addr11 =="세종"||substr_addr11 =="대전"){
		forecastCode = "11C20000"
	}else if(substr_addr11 =="충북"){
		forecastCode = "11C10000"
	}else if(substr_addr11 == "광주"||substr_addr11 == "전남"){
		forecastCode = "11F20000"
	}else if(substr_addr11 == "전북"){
		forecastCode = "11F10000"
	}else if(substr_addr11 == "경북"||substr_addr11 == "대구"){
		forecastCode = "11H10000"
	}else if(substr_addr11 == "부산"||substr_addr11 == "울산" || substr_addr11 == "경남"){
		forecastCode = "11H20000"
	}else if(substr_addr11 == "제주"){
		forecastCode = "11G00000"
	};
	
	$.ajax({
		type:"GET",
		url : "/mid_forecast.do",
		data:{Code : forecastCode}, 
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data,status, xhr) {
			//console.log(data);
			for(var i=0;i<=data.length;i++){
				
				/*
				//1일
				if(data[i].wf1Pm == '맑음'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름조금'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름많음'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름많고 비'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름많고 눈'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름많고 비/눈'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '구름많고 눈/비'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '흐림'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '흐리고 비'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '흐리고 눈'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '흐리고 비/눈'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}else if(data[i].wf1Pm == '흐리고 눈/비'){
					$("#date_ico1").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
					$("#weatherinfo1").append(data[i].wf1Pm);
				}
				
				//2일
				if(data[i].wf2Pm == '맑음'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름조금'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름많음'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름많고 비'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름많고 눈'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름많고 비/눈'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '구름많고 눈/비'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '흐림'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '흐리고 비'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '흐리고 눈'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '흐리고 비/눈'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}else if(data[i].wf2Pm == '흐리고 눈/비'){
					$("#date_ico2").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
					$("#weatherinfo2").append(data[i].wf2Pm);
				}
				*/
				
				//3일
				if(data[i].wf3Pm == '맑음'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름조금'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름많음'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름많고 비'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름많고 눈'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름많고 비/눈'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '구름많고 눈/비'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '흐림'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '흐리고 비'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '흐리고 눈'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '흐리고 비/눈'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}else if(data[i].wf3Pm == '흐리고 눈/비'){
					$("#date_ico3").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
					$("#weatherinfo3").append(data[i].wf3Pm);
				}
				
// 	4일
	
			if(data[i].wf4Pm == '맑음'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름조금'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름많음'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름많고 비'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름많고 눈'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름많고 비/눈'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '구름많고 눈/비'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '흐림'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '흐리고 비'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '흐리고 눈'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '흐리고 비/눈'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}else if(data[i].wf4Pm == '흐리고 눈/비'){
				$("#date_ico4").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
				$("#weatherinfo4").append(data[i].wf4Pm);
			}
			
// 			5일
			if(data[i].wf5Pm == '맑음'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름조금'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름많음'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름많고 비'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름많고 눈'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름많고 비/눈'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '구름많고 눈/비'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '흐림'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '흐리고 비'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '흐리고 눈'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '흐리고 비/눈'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}else if(data[i].wf5Pm == '흐리고 눈/비'){
				$("#date_ico5").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
				$("#weatherinfo5").append(data[i].wf5Pm);
			}
				
				
// 				6일
			if(data[i].wf6Pm == '맑음'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름조금'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름많음'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름많고 비'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름많고 눈'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름많고 비/눈'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '구름많고 눈/비'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '흐림'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '흐리고 비'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '흐리고 눈'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '흐리고 비/눈'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}else if(data[i].wf6Pm == '흐리고 눈/비'){
				$("#date_ico6").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
				$("#weatherinfo6").append(data[i].wf6Pm);
			}
			
//				7일
			if(data[i].wf7Pm == '맑음'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather1.png" alt="맑음" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름조금'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather2.png" alt="구름조금" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름많음'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름많고 비'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름많고 눈'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름많고 비/눈'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '구름많고 눈/비'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈비" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '흐림'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather3.png" alt="구름" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '흐리고 비'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '흐리고 눈'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="눈" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '흐리고 비/눈'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather4.png" alt="비눈" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}else if(data[i].wf7Pm == '흐리고 눈/비'){
				$("#date_ico7").append('<img src="/naksinuri_original/common/img/ico_weather5.png" alt="구름" />')
				$("#weatherinfo7").append(data[i].wf7Pm);
			}
				

			}

			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			 alert("실패");
		}
	});
	


	$.ajax({
		type:"GET",
		url :"/tide_forecast.do",
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log(data);
			
		
			for(var i=0;i<data.length;i++){
				
				$("#weather_date"+[i+1]).append(data[i].tide_date);
				$("#tideup"+[i]).append(data[i].tide_forecast1+'<br>'+data[i].tide_forecast3);
				
				$("#tidedown"+[i]).append( data[i].tide_forecast2+'<br>'+data[i].tide_forecast4);
			}
			
			
		
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
		 
		}
		});

	
	


	
	

	$.ajax({
		type:"GET",
		url :"/tide_time.do",
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log(data);
			

			for(var i=0;i<=data.length;i++){
				$("#tide_info"+[i+1]).append(data[i].tide_time3);
			}
			
		
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			 
		}
		});
	

	

	var getfish2=$('input[name=getco_fish]').val();
	var getfish = getfish2.split(',');
	var getfishing_type=$('input[name=fishing_type]').val();
	if(getfishing_type=='boatfishing'){

	for(var i=0;i<getfish.length;i++){
			if(getfish[i]=='광어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gwang.png" alt="광어" /></span><em>광어</em></li>');
			}else if(getfish[i]=='우럭'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_ooruk.png" alt="우럭" /></span><em>우럭</em></li>');
			}else if(getfish[i]=='농어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_nong.png" alt="농어" /></span><em>농어</em></li>');
			}else if(getfish[i]=='민어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_miner.png" alt="민어" /></span><em>민어</em></li>');;
			}else if(getfish[i]=='참돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_chamdom.png" alt="참돔" /></span><em>참돔</em></li>');
			}else if(getfish[i]=='도미'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_domi.png" alt="도미" /></span><em>도미</em></li>');
			}else if(getfish[i]=='돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_domi.png" alt="돔" /></span><em>돔</em></li>');
			}else if(getfish[i]=='쭈꾸미'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_zzukumi.png" alt="쭈꾸미" /></span><em>쭈꾸미</em></li>');
			}else if(getfish[i]=='갑오징어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gap.png" alt="갑오징어" /></span><em>갑오징어</em></li>');
			}else if(getfish[i]=='호래기'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_horaegi.png" alt="호래기" /></span><em>호래기</em></li>');
			}else if(getfish[i]=='갈치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gal.png" alt="갈치" /></span><em>갈치</em></li>');
			}else if(getfish[i]=='백조기'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_white.png" alt="백조기" /></span><em>백조기</em></li>');
			}else if(getfish[i]=='조기'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_jokie.png" alt="조기" /></span><em>조기</em></li>');
			}else if(getfish[i]=='가자미'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gaza.png" alt="가자미" /></span><em>가자미</em></li>');
			}else if(getfish[i]=='참가자미'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_chamgazami.png" alt="참가자미" /></span><em>참가자미</em></li>');
			}else if(getfish[i]=='대구'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_daegu.png" alt="대구" /></span><em>대구</em></li>');
			}else if(getfish[i]=='삼치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_samchi.png" alt="삼치" /></span><em>삼치</em></li>');
			}else if(getfish[i]=='숭어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_soonger.png" alt="숭어" /></span><em>숭어</em></li>');
			}else if(getfish[i]=='문어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_oct.png" alt="문어" /></span><em>문어</em></li>');
			}else if(getfish[i]=='낙지'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_nak.png" alt="낙지" /></span><em>낙지</em></li>');
			}else if(getfish[i]=='열기'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_yulki.png" alt="열기" /></span><em>열기</em></li>');
			}else if(getfish[i]=='볼락'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_vollac.png" alt="볼락" /></span><em>볼락</em></li>');
			}else if(getfish[i]=='쏨뱅이'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_ssombaeng.png" alt="쏨뱅이" /></span><em>쏨뱅이</em></li>');
			}else if(getfish[i]=='참우럭'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_chamooruck.png" alt="참우럭" /></span><em>참우럭</em></li>');
			}else if(getfish[i]=='홍감펭'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_hongkampeng.png" alt="홍감펭" /></span><em>홍감펭</em></li>');
			}else if(getfish[i]=='고등어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_godeung.png" alt="고등어" /></span><em>고등어</em></li>');
			}else if(getfish[i]=='한치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gwang.png" alt="한치" /></span><em>한치</em></li>');
			}else if(getfish[i]=='오징어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_ozing.png" alt="오징어" /></span><em>오징어</em></li>');
			}else if(getfish[i]=='부시리'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_busiri.png" alt="부시리" /></span><em>부시리</em></li>');
			}else if(getfish[i]=='방어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_banger.png" alt="방어" /></span><em>방어</em></li>');
			}else if(getfish[i]=='참치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_chamchi.png" alt="참치" /></span><em>참치</em></li>');
			}else if(getfish[i]=='다금바리'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_dagum.png" alt="다금바리" /></span><em>다금바리</em></li>');
			}else if(getfish[i]=='능성어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_nuung.png" alt="능성어" /></span><em>능성어</em></li>');
			}else if(getfish[i]=='돗돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_dotdom.png" alt="돗돔" /></span><em>돗돔</em></li>');
			}else if(getfish[i]=='도다리'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_dodari.png" alt="도다리" /></span><em>도다리</em></li>');
			}else if(getfish[i]=='놀래미'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_nol.png" alt="놀래미" /></span><em>놀래미</em></li>');
			}else if(getfish[i]=='감성돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_gamsung.png" alt="감성돔" /></span><em>감성돔</em></li>');
			}else if(getfish[i]=='전어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_juner.png" alt="전어" /></span><em>전어</em></li>');
			}else if(getfish[i]=='쥐치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_juichi.png" alt="쥐치" /></span><em>쥐치</em></li>');
			}else if(getfish[i]=='전갱이'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_jungaeng.png" alt="전갱이" /></span><em>전갱이</em></li>');
			}else if(getfish[i]=='돌돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_doldom.png" alt="돌돔" /></span><em>돌돔</em></li>');
			}else if(getfish[i]=='벵에돔'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_baengedom.png" alt="벵에돔" /></span><em>벵에돔</em></li>');
			}else if(getfish[i]=='학꽁치'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_hakkong.png" alt="학꽁치" /></span><em>학꽁치</em></li>');
			}else if (getfish[i]=='장어'){
				$('#fishul').append('<li><span><img src="/naksinuri_original/common_main/img/ico_fish/fish_janger.png" alt="장어" /></span><em>장어</em></li>');
			}
		}
	}
	
		var getprc= $('input[name=getco_prc]').val();
		var getprctp= $('input[name=getco_prctp]').val();
		
		if(getprc!='') {		
			
			var prc = getprc.split('|');
			var prctp= getprctp.split('|');		
			//배열 빈값제거
			for(var i=0;i<prc.length;i++){
				if(prc[i]!=''){
				$('#prcul').append('<li><em>'+prctp[i]+'</em><span>'+prc[i]+'</span></li>');
	
				}
			}
		}
		


// 	지도 탭제어	
		
		$("#locate").click(function(){
			$("#locate").parent().addClass("on");
			$("#public_trv").parent().removeClass("on");
			$("#public_shop").parent().removeClass("on");
			$("#public_food").parent().removeClass("on");
			$("#public_festival").parent().removeClass("on");
		})
		
		$("#public_trv").click(function(){
			$("#public_trv").parent().addClass("on");
			$("#locate").parent().removeClass("on");
			$("#public_festival").parent().removeClass("on");
			$("#public_food").parent().removeClass("on");
			$("#public_shop").parent().removeClass("on");

		})
		
		$("#public_food").click(function(){
			$("#public_food").parent().addClass("on");
			$("#locate").parent().removeClass("on");
			$("#public_festival").parent().removeClass("on");
			$("#public_trv").parent().removeClass("on");
			$("#public_shop").parent().removeClass("on");

		})
		
		$("#public_shop").click(function(){
			$("#public_shop").parent().addClass("on");
			$("#locate").parent().removeClass("on");
			$("#public_festival").parent().removeClass("on");
			$("#public_trv").parent().removeClass("on");
			$("#public_food").parent().removeClass("on");

		})
		
		$("#public_festival").click(function(){
			$("#public_festival").parent().addClass("on");
			$("#locate").parent().removeClass("on");
			$("#public_shop").parent().removeClass("on");
			$("#public_trv").parent().removeClass("on");
			$("#public_food").parent().removeClass("on");

		})
});

// 오시는길
function locate(){
	
	document.getElementById("menu_wrap").style.display="none"
	var addr2_2 = $('input[name=getaddr2_2]').val();
	
	$("#map").empty();
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

//		// 지도를 생성합니다    
	var map = new daum.maps.Map(mapContainer, mapOption); 
	
//		// 주소-좌표 변환 객체를 생성합니다
	var geocoder = new daum.maps.services.Geocoder();
	

		// 주소로 좌표를 검색합니다
		geocoder.addressSearch( addr2_2 , function(result, status) {
	
		    // 정상적으로 검색이 완료됐으면 
		     if (status === daum.maps.services.Status.OK) {
	
		        var coords = new daum.maps.LatLng(result[0].y, result[0].x);
	
		        // 결과값으로 받은 위치를 마커로 표시합니다
		        var marker = new daum.maps.Marker({
		            map: map,
		            position: coords
		        });
	
		        // 인포윈도우로 장소에 대한 설명을 표시합니다
		        var infowindow = new daum.maps.InfoWindow({
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+addr2_2+'</div>'
		        });
		        infowindow.open(map, marker);
	
		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
	});
}

function public_trv(){
	
	document.getElementById("menu_wrap").style.display="block"
// 	키워드로 검색해서 배열로 넘겨준 검색결과값
	
// 	var form = document.getElementById('supform');
	//DB에 있는 주소값 잘라서 AJAX 데이터에 넣기
	var public_addr = $("#getaddr2_2").val();
	var substr_addr = public_addr.substring(0,2);
	
	//ex)경남
	if(substr_addr == "서울"){
		substr_addr ="1";
	}else if(substr_addr == "인천"){
		substr_addr ="2";
	}else if(substr_addr == "대전"){
		substr_addr ="3";
	}else if(substr_addr == "대구"){
		substr_addr ="4";
	}else if(substr_addr == "광주"){
		substr_addr ="5";
	}else if(substr_addr == "부산"){
		substr_addr ="6";
	}else if(substr_addr == "울산"){
		substr_addr ="7";
	}else if(substr_addr == "세종"){
		substr_addr ="8";
	}else if(substr_addr == "경기"){
		substr_addr ="31";
	}else if(substr_addr == "강원"){
		substr_addr ="32";
	}else if(substr_addr == "충북"){
		substr_addr ="33";
	}else if(substr_addr == "충남"){
		substr_addr ="34";
	}else if(substr_addr == "경북"){
		substr_addr ="35";
	}else if(substr_addr == "경남"){
		substr_addr ="36";
	}else if(substr_addr == "전북"){
		substr_addr="37";
	}else if(substr_addr == "전남"){
		substr_addr="38";
	}else if(substr_addr == "제주"){
		substr_addr="39";
	}
	$.ajax({
		type:"GET",
		url : "/info/fishjob/Public_trvData.do" , 
		dataType : 'json',
		data : {"keyword":substr_addr},
		contentType : "application/json;charset=UTF-8",
		async:true,
		success:function(data,status,xhr){
			//console.log(data);
			$("#map").empty();
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(data[0].mapy, data[0].mapx), // 지도의 중심좌표
		        level: 12 // 지도의 확대 레벨
		    };  
			
			

			$('#placesList').empty();   
			// 지도를 생성합니다    
			var map = new daum.maps.Map(mapContainer, mapOption); 
			
			
	
			
			var positions = [];
			var infopositions=[];
			var infocontents = [];
			var itemEl = [];
			
			for(var i =0; i<data.length;i++){
				itemEl = getListItem(i, data[i]);
				positions.push({title:data[i].title,
								latlng: new daum.maps.LatLng(data[i].mapy, data[i].mapx)});					
				
				infopositions.push({latlng:new daum.maps.LatLng(data[i].mapy,data[i].mapx)});
				if(data[i].tel != null){
					infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>전화번호:'+data[i].tel+'<br>주소:'+data[i].addr1+'</div>'});
				}else{
					infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>주소:'+data[i].addr1+'</div>'});
				}
				

			    var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
		        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
		        imgOptions =  {
		            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
		            spriteOrigin : new daum.maps.Point(0, (i*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
		            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
		        }
			    
			    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions)

			
			    
			    
		
			
		    // 마커를 생성합니다
		    var marker = new daum.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng, // 마커를 표시할 위치
		     	image: markerImage
		      	
		    });
		

		
		    
		    
		    var infowindow = new daum.maps.InfoWindow({
		    	position:infopositions[i].latlng,
		    	content:infocontents[i].contents
		    	
		    });
		 
		    
		    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		    
		
	       		
	       	
	   
	        
		             
		    $('#placesList').append(itemEl);
		    
		   

  			
			}
			
		
			function getListItem(i, data) {
			    var el = document.createElement('li'),
			    itemStr = '<span class="markerbg marker_'+(i+1)+'"></span><div class="info">'+data.title+'<span>주소>'+data.addr1+'</span></div>';
			   

			    el.innerHTML = itemStr;
			    el.className = 'item';

			    return el;
			}
			
		
			
			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			function makeOverListener(map, marker, infowindow) {
			    return function() {
			        infowindow.open(map, marker);
			    };
			}


			
		

			// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
			function makeOutListener(infowindow) {
			    return function() {
			        infowindow.close();
			    };
			}
		
		
		
		},
		error:function(jqXHR,textStatus, errorThrown){
			alert(jqXHR.responseText);
		}
	});
}

function public_food(){
	
	document.getElementById("menu_wrap").style.display="block"
// 	키워드로 검색해서 배열로 넘겨준 검색결과값
	
// 	var form = document.getElementById('supform');
	//DB에 있는 주소값 잘라서 AJAX 데이터에 넣기
	var public_addr = $("#getaddr2_2").val();
	var substr_addr = public_addr.substring(0,2);
	
	
	//ex)경남
	if(substr_addr == "서울"){
		substr_addr ="1";
	}else if(substr_addr == "인천"){
		substr_addr ="2";
	}else if(substr_addr == "대전"){
		substr_addr ="3";
	}else if(substr_addr == "대구"){
		substr_addr ="4";
	}else if(substr_addr == "광주"){
		substr_addr ="5";
	}else if(substr_addr == "부산"){
		substr_addr ="6";
	}else if(substr_addr == "울산"){
		substr_addr ="7";
	}else if(substr_addr == "세종"){
		substr_addr ="8";
	}else if(substr_addr == "경기"){
		substr_addr ="31";
	}else if(substr_addr == "강원"){
		substr_addr ="32";
	}else if(substr_addr == "충북"){
		substr_addr ="33";
	}else if(substr_addr == "충남"){
		substr_addr ="34";
	}else if(substr_addr == "경북"){
		substr_addr ="35";
	}else if(substr_addr == "경남"){
		substr_addr ="36";
	}else if(substr_addr == "전북"){
		substr_addr="37";
	}else if(substr_addr == "전남"){
		substr_addr="38";
	}else if(substr_addr == "제주"){
		substr_addr="39";
	}
	$.ajax({
		type:"GET",
		url : "/info/fishjob/Public_foodData.do" , 
		dataType : 'json',
		data : {"keyword":substr_addr},
		contentType : "application/json;charset=UTF-8",
		async:true,
		success:function(data,status,xhr){
			//console.log(data);
			$("#map").empty();
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(data[0].mapy, data[0].mapx), // 지도의 중심좌표
		        level: 12 // 지도의 확대 레벨
		    };  
			
			

//			// 지도를 생성합니다    
			var map = new daum.maps.Map(mapContainer, mapOption); 
			
			
			$('#placesList').empty(); 
				var positions = [];
				var infopositions=[];
				var infocontents = [];
				var itemEl = [];
				
				
				for(var i =0; i<data.length;i++){
					itemEl = getListItem(i, data[i]);
					positions.push({title:data[i].title,
									latlng: new daum.maps.LatLng(data[i].mapy, data[i].mapx)});					
					
					infopositions.push({latlng:new daum.maps.LatLng(data[i].mapy,data[i].mapx)});
					if(data[i].tel != null){
						infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>전화번호:'+data[i].tel+'<br>주소:'+data[i].addr1+'</div>'});
					}else{
						infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>주소:'+data[i].addr1+'</div>'});
					}
					

					 var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
				        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
				        imgOptions =  {
				            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
				            spriteOrigin : new daum.maps.Point(0, (i*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
				            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
				        }
					    
					    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions)


					

					
			    // 마커를 생성합니다
			    var marker = new daum.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커를 표시할 위치
			        image: markerImage
			      	
			    });
			    
			    
			
			    
			    var infowindow = new daum.maps.InfoWindow({
			    	position:infopositions[i].latlng,
			    	content:infocontents[i].contents
			    	
			    });
			    
			    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			    
			    $('#placesList').append(itemEl);
			    
				}
				

				function getListItem(i, data) {
						    var el = document.createElement('li'),
						    itemStr = '<span class="markerbg marker_'+(i+1)+'"></span><div class="info">'+data.title+'<span>주소>'+data.addr1+'</span></div>';
						   

						    el.innerHTML = itemStr;
						    el.className = 'item';

						    return el;
						}
					
				// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
				function makeOverListener(map, marker, infowindow) {
				    return function() {
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}	
		
		},
		error:function(jqXHR,textStatus, errorThrown){
			alert(jqXHR.responseText);
		}
	});
}

function public_festival(){
	document.getElementById("menu_wrap").style.display="block"
// 	키워드로 검색해서 배열로 넘겨준 검색결과값
	
// 	var form = document.getElementById('supform');
	//DB에 있는 주소값 잘라서 AJAX 데이터에 넣기
	var public_addr = $("#getaddr2_2").val();
	var substr_addr = public_addr.substring(0,2);
	
	//ex)경남
	if(substr_addr == "서울"){
		substr_addr ="1";
	}else if(substr_addr == "인천"){
		substr_addr ="2";
	}else if(substr_addr == "대전"){
		substr_addr ="3";
	}else if(substr_addr == "대구"){
		substr_addr ="4";
	}else if(substr_addr == "광주"){
		substr_addr ="5";
	}else if(substr_addr == "부산"){
		substr_addr ="6";
	}else if(substr_addr == "울산"){
		substr_addr ="7";
	}else if(substr_addr == "세종"){
		substr_addr ="8";
	}else if(substr_addr == "경기"){
		substr_addr ="31";
	}else if(substr_addr == "강원"){
		substr_addr ="32";
	}else if(substr_addr == "충북"){
		substr_addr ="33";
	}else if(substr_addr == "충남"){
		substr_addr ="34";
	}else if(substr_addr == "경북"){
		substr_addr ="35";
	}else if(substr_addr == "경남"){
		substr_addr ="36";
	}else if(substr_addr == "전북"){
		substr_addr="37";
	}else if(substr_addr == "전남"){
		substr_addr="38";
	}else if(substr_addr == "제주"){
		substr_addr="39";
	}

	$.ajax({
		type:"GET",
		url : "/info/fishjob/Public_festivalData.do" , 
		dataType : 'json',
		data : {"keyword":substr_addr},
		contentType : "application/json;charset=UTF-8",
		async:true,
		success:function(data,status,xhr){
			//console.log(data);
			$("#map").empty();
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(data[0].mapy, data[0].mapx), // 지도의 중심좌표
		        level: 12 // 지도의 확대 레벨
		    };  
			
			


//			// 지도를 생성합니다    
			var map = new daum.maps.Map(mapContainer, mapOption); 
			
			$('#placesList').empty(); 				

				var positions = [];
				var infopositions=[];
				var infocontents = [];
				var itemEl = [];
			
				for(var i =0; i<data.length;i++){
					itemEl = getListItem(i, data[i]);
					positions.push({title:data[i].title,
									latlng: new daum.maps.LatLng(data[i].mapy, data[i].mapx)});					
					
					infopositions.push({latlng:new daum.maps.LatLng(data[i].mapy,data[i].mapx)});
					if(data[i].tel != null){
						infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>전화번호:'+data[i].tel+'<br>주소:'+data[i].addr1+'</div>'});
					}else{
						infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>주소:'+data[i].addr1+'</div>'});
					}
					
					 var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
				        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
				        imgOptions =  {
				            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
				            spriteOrigin : new daum.maps.Point(0, (i*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
				            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
				        }
					    
					    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions)

					
			    // 마커를 생성합니다
			    var marker = new daum.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커를 표시할 위치
			        image: markerImage
			      	
			    });
			    

				

			    
			    var infowindow = new daum.maps.InfoWindow({
			    	position:infopositions[i].latlng,
			    	content:infocontents[i].contents
			    	
			    });
			    
			    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
			    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
			    

				$('#placesList').append(itemEl);
				}
					
				// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
				function makeOverListener(map, marker, infowindow) {
				    return function() {
				        infowindow.open(map, marker);
				    };
				}

				// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
				function makeOutListener(infowindow) {
				    return function() {
				        infowindow.close();
				    };
				}

				function getListItem(i, data) {
				    var el = document.createElement('li'),
				    itemStr = '<span class="markerbg marker_'+(i+1)+'"></span><div class="info">'+data.title+'<span>주소>'+data.addr1+'</span></div>';
				   

				    el.innerHTML = itemStr;
				    el.className = 'item';

				    return el;
				}
				
		
		},
		error:function(jqXHR,textStatus, errorThrown){
			alert(jqXHR.responseText);
		}
	});
}


function public_shop(){
	document.getElementById("menu_wrap").style.display="block"
// 	키워드로 검색해서 배열로 넘겨준 검색결과값
	
// 	var form = document.getElementById('supform');
	//DB에 있는 주소값 잘라서 AJAX 데이터에 넣기
	var public_addr = $("#getaddr2_2").val();
	var substr_addr = public_addr.substring(0,2);
	
	//ex)경남
	if(substr_addr == "서울"){
		substr_addr ="1";
	}else if(substr_addr == "인천"){
		substr_addr ="2";
	}else if(substr_addr == "대전"){
		substr_addr ="3";
	}else if(substr_addr == "대구"){
		substr_addr ="4";
	}else if(substr_addr == "광주"){
		substr_addr ="5";
	}else if(substr_addr == "부산"){
		substr_addr ="6";
	}else if(substr_addr == "울산"){
		substr_addr ="7";
	}else if(substr_addr == "세종"){
		substr_addr ="8";
	}else if(substr_addr == "경기"){
		substr_addr ="31";
	}else if(substr_addr == "강원"){
		substr_addr ="32";
	}else if(substr_addr == "충북"){
		substr_addr ="33";
	}else if(substr_addr == "충남"){
		substr_addr ="34";
	}else if(substr_addr == "경북"){
		substr_addr ="35";
	}else if(substr_addr == "경남"){
		substr_addr ="36";
	}else if(substr_addr == "전북"){
		substr_addr="37";
	}else if(substr_addr == "전남"){
		substr_addr="38";
	}else if(substr_addr == "제주"){
		substr_addr="39";
	}

	$.ajax({
		type:"GET",
		url : "/info/fishjob/Public_shopData.do" , 
		dataType : 'json',
		data : {"keyword":substr_addr},
		contentType : "application/json;charset=UTF-8",
		async:true,
		success:function(data,status,xhr){
			//console.log(data);
			$("#map").empty();
			
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new daum.maps.LatLng(data[0].mapy, data[0].mapx), // 지도의 중심좌표
		        level: 12 // 지도의 확대 레벨
		    };  
			
			


//			//			// 지도를 생성합니다    
			var map = new daum.maps.Map(mapContainer, mapOption); 
			
			$('#placesList').empty(); 
			
			var positions = [];
			var infopositions=[];
			var infocontents = [];
			var itemEl = [];
			

			for(var i =0; i<data.length;i++){

				itemEl = getListItem(i, data[i]);
				positions.push({title:data[i].title,
								latlng: new daum.maps.LatLng(data[i].mapy, data[i].mapx)});					
				
				infopositions.push({latlng:new daum.maps.LatLng(data[i].mapy,data[i].mapx)});
				if(data[i].tel != null){
					infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>전화번호:'+data[i].tel+'<br>주소:'+data[i].addr1+'</div>'});
				}else{
					infocontents.push({contents:'<div style="padding 5px;width:300px;height:80px;">제목:'+data[i].title+'<br>주소:'+data[i].addr1+'</div>'});
				}
			
				 var imageSrc = 'http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
			        imageSize = new daum.maps.Size(36, 37),  // 마커 이미지의 크기
			        imgOptions =  {
			            spriteSize : new daum.maps.Size(36, 691), // 스프라이트 이미지의 크기
			            spriteOrigin : new daum.maps.Point(0, (i*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
			            offset: new daum.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
			        }
				    
				    var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imgOptions)

				
		
				
			    // 마커를 생성합니다
			    var marker = new daum.maps.Marker({
			        map: map, // 마커를 표시할 지도
			        position: positions[i].latlng, // 마커를 표시할 위치
			        image: markerImage
			      	
			    });
		    
				
		    var infowindow = new daum.maps.InfoWindow({
		    	position:infopositions[i].latlng,
		    	content:infocontents[i].contents
		    	
		    });
		    
		    daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		    
			$('#placesList').append(itemEl);
		    
			}
			
			

			function getListItem(i, data) {
					    var el = document.createElement('li'),
					    itemStr = '<span class="markerbg marker_'+(i+1)+'"></span><div class="info">'+data.title+'<span>주소>'+data.addr1+'</span></div>';
					   

					    el.innerHTML = itemStr;
					    el.className = 'item';

					    return el;
					}
			
			// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
			function makeOverListener(map, marker, infowindow) {
			    return function() {
			        infowindow.open(map, marker);
			    };
			}

			// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
			function makeOutListener(infowindow) {
			    return function() {
			        infowindow.close();
			    };
			}
					
		
		},
		error:function(jqXHR,textStatus, errorThrown){
			alert(jqXHR.responseText);
		}
	});
}

function opener_save() {
	if(confirm('위 정보로 데이터를 저장하시겠습니까?')) {
		var nak_id = $('#nak_id', opener.document).val();
		//alert(nak_id);
		$("#supform", opener.document).attr("target","parent_window");			
		$("#insertCorp", opener.document).click();
		self.window.close();
	}
}
</script>

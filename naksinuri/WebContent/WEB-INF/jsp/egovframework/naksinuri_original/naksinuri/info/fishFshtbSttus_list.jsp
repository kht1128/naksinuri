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
<c:set var="pageName" value="fishCompany" />


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
#map1> img {top:0 !important;left:0 !important;}
/* 일단 안 보이게 나중에 수정 */
#container {background-color: transparent !important;}
#topLoca {display: none;}
</style>

<%@include file="../layout/newHead.jsp"%>

<form:form commandName="naksinuriVO" id="viewform" name="viewform" method="post" >
<input type="hidden" id="nak_id" name="nak_id" value="" />
</form:form>
<form:form commandName="SanupVO" id="viewform2" name="viewform2" method="post" >
<input type="hidden" id="san_sn" name="san_sn" value="" />
</form:form>

<form:form commandName="supform" id="supform" method="post" >
	<input type="hidden" id="req_sido" value="${searchSido}" />
	<input type="hidden" id="req_gugun" value="${searchGugun}" />
	<input type="hidden" name="fish_list" id="fish_list" value=""/>	
	<input type="hidden" name="addWebLink" id="addWebLink" value=""/>	

	
	<div id="fishjobView" class="container">	
<%-- 		<input type="hidden" name="fishing_type" value="${info.fishing_type}">
		<input type="hidden" name="getco_fish" value="${info.co_fish}">
		<input type="hidden" name="getaddr2_2" id="getaddr2_2"	value="${info.co_addr2_2 }">
		<input type="hidden" name="getpublic_addr" id="getpublic_addr" value="${info.co_addr2_2 }">
		<input type="hidden" name="atch_file_id" value="${info.atch_file_id }">
		<input type="hidden" name="co_nm" value="${info.co_nm}">
		<input type="hidden" id="nak_id" name="nak_id" value="${info.nak_id}">
		<input type="hidden" name="rev_pass" id="rev_pass">
		<input type="hidden" name="rev_sn1" id="rev_sn1">
 --%>
		
		<section id="fishFshtbSttus">
			<div class="subTop">
				<div class="topBg"></div>
				<h3><span class="font-weight-bold">2021년 낚시어선업</span> 관련 그래프</h3>
			</div>
			
			<div class="row">
				<div class="col-md-7">
					<div class="con">
						<div class="titleArea">
							<h4>연도별 낚시어선업 신고 현황</h4>
							<span>(단위 : 척수)</span>
							<a class="more" href="javascript:void(0);" onclick="openModal('fshtbSttus')" title="연도별 낚시어선업 신고 현황 팝업 열림">자세히 보기<i class="xi-plus"></i></a>
						</div>
						
						<!-- chart -->
				        <div class="chrat" id="chrat1"></div>
						<!-- End chart -->
					</div>
					
					<div class="con">
						<div class="titleArea">
							<h4>연도별 낚시어선업 이용객 수</h4>
							<span>(단위 : 천명)</span>
							<a class="more" href="javascript:void(0);" onclick="openModal('fshtbUser')" title="연도별 낚시어선업 이용객 수 팝업 열림">자세히 보기<i class="xi-plus"></i></a>
						</div>
						
						<!-- chart -->
				        <div class="chrat" id="chrat2"></div>
						<!-- End chart -->
					</div>
				</div>
				
				<div class="col-md-5">
					<div class="icobg" id="icobg1"></div>
					<div class="icobg" id="icobg2"></div>
					<div class="icobg" id="icobg3"></div>
					<div class="icobg" id="icobg4"></div>
					
					<div class="con">
						<div class="titleArea">
							<h4>시 · 도별 낚시어선업 신고 현황</h4>
							<span>(단위 : 척)</span>
						</div>
						
						<div class="mapArea">
							<img src="/naksinuri_original/common_main/img/fishFshtbSttus/img_map.png" alt="시 · 도별 낚시어선업 신고 현황">
							<c:forEach var="item" items="${areaFshtbList}">
								<a class="circle 
									<c:if test="${item.fshtb_area eq '인천' }">position1</c:if>
									<c:if test="${item.fshtb_area eq '경기' }">position2</c:if>
									<c:if test="${item.fshtb_area eq '강원' }">position3</c:if>
									<c:if test="${item.fshtb_area eq '충남' }">position4</c:if>
									<c:if test="${item.fshtb_area eq '경북' }">position5</c:if>
									<c:if test="${item.fshtb_area eq '전북' }">position6</c:if>
									<c:if test="${item.fshtb_area eq '전남' }">position7</c:if>
									<c:if test="${item.fshtb_area eq '경남' }">position8</c:if>
									<c:if test="${item.fshtb_area eq '부산' }">position9</c:if>
									<c:if test="${item.fshtb_area eq '울산' }">position10</c:if>
									<c:if test="${item.fshtb_area eq '제주' }">position11</c:if>
									
									<c:if test="${item.fshtb_sm  >= 0 && item.fshtb_sm < 200}">small</c:if>
									<c:if test="${item.fshtb_sm  >= 100 && item.fshtb_sm < 200}">xSmall</c:if>
									<c:if test="${item.fshtb_sm  >= 200 && item.fshtb_sm < 400}">medium</c:if>
									<c:if test="${item.fshtb_sm  >= 400 && item.fshtb_sm < 1000}">large</c:if>
									<c:if test="${item.fshtb_sm  >= 1000}">xLarge</c:if>
								" href="javascript:void(0);" onclick="openModal('siDoFshtb')" title="${item.fshtb_area} 낚시어선업 신고 현황 팝업 열림">${item.fshtb_area}<span>${item.fshtb_sm}</span></a>
							</c:forEach>
							<!-- <a class="circle medium" href="" title="인천 낚시어선업 신고 현황 팝업 열림">인천<span>205</span></a>
							<a class="circle small" href="" title="경기도 낚시어선업 신고 현황 팝업 열림">경기도<span>90</span></a>
							<a class="circle medium" href="" title="강원도 낚시어선업 신고 현황 팝업 열림">강원도<span>267</span></a>
							<a class="circle large" href="" title="충남 낚시어선업 신고 현황 팝업 열림">충남<span>997</span></a>
							<a class="circle small xSmall" href="" title="경북 낚시어선업 신고 현황 팝업 열림">경북<span>125</span></a>
							<a class="circle medium" href="" title="전북 낚시어선업 신고 현황 팝업 열림">전북<span>267</span></a>
							<a class="circle large" href="" title="전남 낚시어선업 신고 현황 팝업 열림">전남<span>790</span></a>
							<a class="circle xLarge" href="" title="경남 낚시어선업 신고 현황 팝업 열림">경남<span>1210</span></a>
							<a class="circle small xSmall" href="" title="부산 낚시어선업 신고 현황 팝업 열림">부산<span>174</span></a>
							<a class="circle small" href="" title="울산 낚시어선업 신고 현황 팝업 열림">울산<span>43</span></a>
							<a class="circle medium" href="" title="제주도 낚시어선업 신고 현황 팝업 열림">제주도<span>254</span></a> -->
						</div>
					</div>
				</div>
			</div>
		</section>

	
		
</form:form>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jQuery-rwdImageMaps/1.6/jquery.rwdImageMaps.min.js"></script> 
<script src="/common/seadm/global/vendor/toastr/toastr.min.js"></script>

<script type="text/javascript">
    // rwdImageMaps로 이미지맵 동적 할당하도록 설정
    $('img[usemap]').rwdImageMaps();
</script>

<script>
$(function(){
	var yrFshtbListjson = JSON.parse('${yrFshtbListJson}');
	var labelsdata = new Array();
	var seriesdata = new Array();
	var seriesdataitem1 = new Array();
	var seriesdataitem2 = new Array();
	seriesdataitem1.push('data1');
	seriesdataitem2.push('data2');
	var maxNum = 0;
	for (i = 0 ; i < yrFshtbListjson.length ; i++) {
		var item = yrFshtbListjson[i];
		labelsdata.push(item.year + "년");
		seriesdataitem1.push(item.fshtb_sm);
		seriesdataitem2.push(item.fshtb_sm);
		maxNum = yrFshtbListjson[yrFshtbListjson.length - 1].fshtb_sm;
	}
	maxNum = (parseInt(maxNum / 1000) + 1) * 1000;
	seriesdata.push(seriesdataitem1);
	seriesdata.push(seriesdataitem2);
	// 연도별 낚시어선업 신고 현황 차트
	var chart1 = c3.generate({
		bindto: '#chrat1',
		data: {
			columns: seriesdata,
			types: {
				'data1': 'bar',
				'data2': 'line',
			},
			colors: {
				'data1': '#dbdbdb', // 차트의 color
				'data2': '#394dc0'
			}
		},
		size: {
	        width: 500
	    },
		grid: {
			/* y: {
				show: true, // 선여부
			}, */
			y: {
				lines: [{value: 0}, {value: 25}, {value: 50}, {value: 75}, {value: 100}]
			}, 
		},
		bar: {
			width: {
				ratio: 0.15
			}
		},
		axis: {
			x: {
				type: 'category', // 그룹 막대일 때 지정
				categories: labelsdata,
			},
			y: {
				max: maxNum, // 최대값
				min: 0, // 최소값
				padding: {
					top: 0,
					bottom: 0
				},
				tick: {
					count: 5
				} 
			},
		}, 
		tooltip: {
			contents: function (d, defaultTitleFormat, defaultValueFormat, color) {
	            return "<font>" + d[0].value + "</font>";
	        }
	    },
		legend: { // 범례
	        show: false
	    },
	    padding: {
			bottom: 5,
			top: 5,
		}
	});
	
	// 연도별 낚시어선업 이용객 수 차트
	var yrFshtbListjson = JSON.parse('${yrFshtbUserListJson}');
	var labelsdata = new Array();
	var seriesdata = new Array();
	var seriesdataitem1 = new Array();
	var seriesdataitem2 = new Array();
	var maxNum = 0;
	seriesdataitem1.push('data1');
	seriesdataitem2.push('data2');
	for (i = 0 ; i < yrFshtbListjson.length ; i++) {
		var item = yrFshtbListjson[i];
		labelsdata.push(item.year);
		seriesdataitem1.push(item.fshtb_user_cnt);
		seriesdataitem2.push(item.fshtb_user_cnt);
		maxNum = yrFshtbListjson[yrFshtbListjson.length - 1].fshtb_user_cnt;
	}
	maxNum = (parseInt(maxNum / 1000) + 1) * 1000;
	seriesdata.push(seriesdataitem1);
	seriesdata.push(seriesdataitem2);
	var chart2 = c3.generate({
		bindto: '#chrat2',
		data: {
			columns: seriesdata,
			types: {
				'data1': 'bar',
				'data2': 'line',
			},
			colors: {
				'data1': '#dbdbdb', //차트의 color
				'data2': '#19a3ff'
			}
		},
		size: {
	        width: 660
	    },
		grid: {
			/* y: {
				show: true, // 선여부
			}, */
			y: {
				lines: [{value: 0}, {value: 20}, {value: 40}, {value: 60}, {value: 80}, {value: 100}]
			},  
		},
		bar: {
			width: {
				ratio: 0.2
			}
		},
		axis: {
			x: {
				type: 'category', //그룹 막대일 때 지정
				categories: labelsdata,
			},
			y: {
				max: maxNum, //최대값
				min: 0, //최소값
				padding: {
					top: 0,
					bottom: 0
				},
				tick: {
					count: 6
				} 
			},
		}, 
		tooltip: {
	        contents: function (d, defaultTitleFormat, defaultValueFormat, color) {
	            return "<font>" + d[0].value + "</font>";
	        }
	    },
		legend: { // 범례
	        show: false
	    },
	    padding: {
			bottom: 5,
			top: 5
		},
	});
});

function openModal(text) {
	$.ajax({
		type:"POST",
		url :"/info/" + text + "/ajaxView.do",
		data:$('#listForm2').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
			$("#allPublicModal").html(data);
			$("#allPublicModal").modal('show');
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
}
</script>


<%@include file="../layout/tail.jsp"%>

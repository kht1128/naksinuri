<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthName" value="static" />
<c:set var="pageName" value="site" />

<%@include file="../static_header.jsp"%>
	<!-- 사이트통계 { -->
		<article id="siteCondition">
			<h1>사이트현황</h1>
			<div class="date_choice row">
			<input type="hidden" name="searchType" id="searchType">
				<div class="col-lg-6">
					<div class="btn-group btn-group-sm" role="group" aria-label="...">
						<button type="button" id="static_day" name="static_day" class="btn btn-default" onclick="statistics_day();" >최근 1일</button>
						<button type="button" id="static_week" name="static_week" class="btn btn-default" onclick="statistics_week();">7일</button>
						<button type="button" id="static_month" name="static_month" class="btn btn-default" onclick="statistics_month()">30일</button>
						<button type="button" class="btn btn-primary" onclick="type_myself()">직접입력</button>
						
					</div>
				</div>
				<form id="frm">
					<div class="col-lg-6">
						<input type="text" id="start_dt" name="start_dt" value="${starts}" class="date_input" /> ~ <input type="text" id="end_dt" value="${ends}"name="end_dt"  class="date_input" /> <button type="button" onclick="statistics_myself()" class="btn btn-default btn-sm">조회</button>
					</div>
				</form>
			</div>
	
			<section id="yoyak" class="conbox">
				<h2>방문현황 요약</h2>
				<ul class="yoyakbox floats box3">
					<li>
						<b>방문자수</b><br/>
						<em class="num">${visit_total}</em>
						<c:if test="${compare_view2 >0}"> 
							<span class="updown up"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i> ${compare_view2}</span>
						</c:if>
						<c:if test="${compare_view2 <0}"> 
							<span class="updown down"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i> ${compare_view2}</span>
						</c:if>
					</li>
					<li>
						<b>방문횟수</b><br />
						<em class="num">${visit_total}</em> 
						<c:if test="${compare_view2 >0}"> 
							<span class="updown up"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i> ${compare_view2}</span>
						</c:if>
						<c:if test="${compare_view2 <0}"> 
							<span class="updown down"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i> ${compare_view2}</span>
						</c:if>
					</li>
					<li>
						<b>페이지뷰</b><br />
						<em class="num">${page_total}</em> 
						<c:if test="${compare_view >0}"> 
							<span class="updown up"><i class="fa fa-arrow-circle-up" aria-hidden="true"></i> ${compare_view}</span>
						</c:if>
						<c:if test="${compare_view <0}"> 
							<span class="updown down"><i class="fa fa-arrow-circle-down" aria-hidden="true"></i> ${compare_view}</span>
						</c:if>
					</li>
				</ul>
			</section>
	
			<section id="daily" class="conbox">
				<h2>일별 방문분포</h2>
				
				<div class="col-lg-12" align="center">
					<div class="btn-group btn-group-sm" role="group" aria-label="...">
						<button type="button" onclick="getLessonURL();" id="f_lesson" class="btn btn-primary">낚시교실</button>
						<button type="button" onclick="getInfoURL();" id="f_info" class="btn btn-default">낚시정보</button>
						<button type="button" onclick="getShareURL()" id="f_share" class="btn btn-default">낚시공유</button>
						<button type="button" onclick="getSosigURL()" id="f_sosig" class="btn btn-default">낚시소식</button>
						<button type="button" onclick="getPolicyURL()" id="f_policy" class="btn btn-default">정책홍보</button>
						<button type="button" onclick="getSurveyURL()" id="f_survey" class="btn btn-default">설문조사</button>
					</div>
				</div>
					
				<div id="dailyhighchart"></div>
			</section>
			<section id="browserDevice" class="conbox">
				<ul class="floats">
					<li>
						<div id="browserhighchart"></div>
					</li>
					<li>
						<div id="devicehighchart"></div>
					</li>
				</ul>
			</section>
			<section id="top10page" class="conbox">
				<h2>TOP10 인기페이지 </h2>
				<table class="alaly_tbl table table-bordered">
					<colgroup>
						<col width="50" />
						<col />
						<col width="120" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th>순위</th>
							<th>페이지URL</th>
							<th>페이지뷰(PV)</th>
							<th>비율</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${board_top10}" varStatus="status">
						<tr>
							<td>${status.count }</td>
							<td>${item.bo_name}</td>
							<td>${item.cnt }</td>
							<td>
								<div class="progress">
									<div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width:${item.prcnt}%">
										<span class="sr-only">20% Complete</span>
									</div>
								</div>
							</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</article>
	<!-- } 사이트통계 -->
	</div>

	<!-- 하단 푸터 { -->
	<%@include file="../static_tail.jsp"%>
	<!-- } 하단 푸터 -->
<script>



function getLessonURL(){
	
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-primary");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-default");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-default");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-default");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-default");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-default");

	var url ="";
	url="/admin/getLessonURLself.do";
	var name="낚시교실 통계";
	chart(url,name);
};

function getInfoURL(){
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-default");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-primary");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-default");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-default");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-default");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-default");
	
	var url ="";
	url="/admin/getInfoURLself.do";
	var name="낚시정보 통계";
	chart(url,name);
};

function getShareURL(){
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-default");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-default");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-primary");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-default");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-default");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-default");
	
	var url ="";
	url="/admin/getShareURLself.do";
	var name="낚시공유 통계";
	chart(url,name);
};

function getSosigURL(){
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-default");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-default");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-default");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-primary");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-default");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-default");
	
	var url ="";
	url="/admin/getSosigURLself.do";
	var name="낚시소식 통계";
	chart(url,name);
};

function getPolicyURL(){
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-default");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-default");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-default");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-default");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-primary");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-default");
	
	var url ="";
	url="/admin/getPolicyURLself.do";
	var name="정책홍보 통계";
	chart(url,name);
};

function getSurveyURL(){
	$('#f_lesson').removeClass();
	$('#f_lesson').addClass("btn btn-default");
	$('#f_info').removeClass();
	$('#f_info').addClass("btn btn-default");
	$('#f_share').removeClass();
	$('#f_share').addClass("btn btn-default");
	$('#f_sosig').removeClass();
	$('#f_sosig').addClass("btn btn-default");
	$('#f_policy').removeClass();
	$('#f_policy').addClass("btn btn-default");
	$('#f_survey').removeClass();
	$('#f_survey').addClass("btn btn-primary");
	
	var url ="";
	url="/admin/getSurveyURLself.do";
	var name="설문조사 통계";
	chart(url,name);
};

	


function type_myself(){
	$('#start_dt').focus();
}	


function statistics_myself(){
	var form = document.getElementById("frm");
	if($('#start_dt').val()==""){
		alert('조회할 시작일을 입력해주세요.');
		return false;
	}
	if($('#end_dt').val()==""){
		alert('조회할 종료일을 입력해주세요.');
		return false;
	}
	
	
    if($('#start_dt').val()==$('#end_dt').val()){
    	$('#end_dt').val(null);
    }
    
    var startDate = $( "input[name='start_dt']" ).val();
    var startDateArr = startDate.split('-');
     
    var endDate = $( "input[name='end_dt']" ).val();
    var endDateArr = endDate.split('-');
             
    var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
    var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
     
    if(startDateCompare.getTime() > endDateCompare.getTime()) {
        alert("기간의 시작날짜와 종료날짜를 확인해 주세요.");
        return false;;
    }
    
	
	form.action="/admin/static/sitemyself/list.do";
	form.submit();

}

function statistics_day(){
	document.location.href="/admin/static/siteday/list.do";
		
	
}

function statistics_week(){
	document.location.href="/admin/static/site/list.do";
}	

function statistics_month(){
	document.location.href="/admin/static/sitemonth/list.do";
}





function fn_substr(data){
	var result = '';
	var str_data = data.toString();
		if(str_data.length==3){
			result = "0"+str_data.substr(0,1)+"/"+str_data.substr(1,2);
		}else if(str_data.length==4){
			result = str_data.substr(0,2)+"/"+str_data.substr(2,2);
		}
	
	return result;
}


function chart(url,name){
	
	var hoursData = [];
	var jsonData = [];
	
	var start_dt = $('#start_dt').val();
	var end_dt = $('#end_dt').val();
	$.ajax({
		type:"GET",
		url :url,
		dataType: 'json',
		data:{"start":start_dt,"end":end_dt},
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			
			
		for(var i=0;i<data.length;i++){
			
			db_date=fn_substr(data[i].hours);
			jsonData.push(data[i].cnt);					
			hoursData.push(db_date);
			
		Highcharts.chart('dailyhighchart', {
		
			title: {
				text: name
			}, chart: {
		        
		        type: 'line'
		    },
		    xAxis: {
		        categories: hoursData
		    },
		    yAxis: [{
		        title: {
		            text: 'Primary Axis'
		        },
		        gridLineWidth: 0
		    }, {
		        title: {
		            text: 'Secondary Axis'
		        },
		        opposite: true
		    }],
		    legend: {
		        layout: 'vertical',
		        backgroundColor: '#FFFFFF',
		        floating: true,
		        align: 'left',
		        x: 100,
		        verticalAlign: 'top',
		        y: 70
		    },
		    tooltip: {
		        formatter: function () {
		            return '<b>' + this.series.name + '</b><br/>' +
		                this.x + ': ' + this.y;
		        }
		    },
			plotOptions: {
			
			},
		
			series: [{
				name: "접속횟수",
				data:jsonData
					
			}],
		
			responsive: {
				rules: [{
					condition: {
						maxWidth: 600
					},
					chartOptions: {
						legend: {
							layout: 'horizontal',
							align: 'center',
							verticalAlign: 'bottom'
						}
					}
				}]
			}
		
		});
	}
//for문 끝 
			if(jsonData == ""){
				alert('일별 방문분포 데이터가 없습니다.');
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
}



		
	
	</script>

	<script>
	
	$(document).ready(function(){
		
	
		var start_dt = $('#start_dt').val();
		var end_dt = $('#end_dt').val();
		
		var hoursData = [];
		var jsonData = [];
		
		$.ajax({
			type:"GET",
			url :'/admin/getLessonURLself.do',
			dataType: 'json',
			data:{"start":start_dt,"end":end_dt},
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				console.log(data);
				
				
			for(var i=0;i<data.length;i++){
				
				db_date=fn_substr(data[i].hours);
				jsonData.push(data[i].cnt);					
				hoursData.push(db_date);
				
			Highcharts.chart('dailyhighchart', {
			
				title: {
					text: "낚시교실 통계"
				}, chart: {
			        
			        type: 'line'
			    },
			    xAxis: {
			        categories: hoursData
			    },
			    yAxis: [{
			        title: {
			            text: 'Primary Axis'
			        },
			        gridLineWidth: 0
			    }, {
			        title: {
			            text: 'Secondary Axis'
			        },
			        opposite: true
			    }],
			    legend: {
			        layout: 'vertical',
			        backgroundColor: '#FFFFFF',
			        floating: true,
			        align: 'left',
			        x: 100,
			        verticalAlign: 'top',
			        y: 70
			    },
			    tooltip: {
			        formatter: function () {
			            return '<b>' + this.series.name + '</b><br/>' +
			                this.x + ': ' + this.y;
			        }
			    },
				plotOptions: {
				
				},
			
				series: [{
					name: "접속횟수",
					data:jsonData
						
				}],
			
				responsive: {
					rules: [{
						condition: {
							maxWidth: 600
						},
						chartOptions: {
							legend: {
								layout: 'horizontal',
								align: 'center',
								verticalAlign: 'bottom'
							}
						}
					}]
				}
			
			});
		}
	//for문 끝 
				if(jsonData == ""){
					alert('일별 방문분포 데이터가 없습니다.');
				}
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		})
		
		
		
		$('#start_dt').datepicker({dateFormat : 'yy-mm-dd'});
		$('#end_dt').datepicker({dateFormat : 'yy-mm-dd'});
		
					

			
			var start_dt = $('#start_dt').val();
			var end_dt = $('#end_dt').val();
			$.ajax({
				type:"GET",
				url :'/admin/getUser_BrowserInfomyself.do',
				data:{"start":start_dt,"end":end_dt},
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
//						HighChart 시작 
					
					if(chart){
				        chart.destroy(); // 차트가 있을경우 제거한다.
				    }
					var chart;
					$('#browserhighchart').highcharts({
						chart: {
							plotBackgroundColor: null,
							plotBorderWidth: null,
							plotShadow: false,
							type: 'pie'
						},
						title: {
							text: '사이트 이용별 브라우저'
						},
						tooltip: {
							pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
						},
						plotOptions: {
							pie: {
								allowPointSelect: true,
								cursor: 'pointer',
								dataLabels: {
									enabled: true,
									format: '<b>{point.name}</b>: {point.percentage:.1f} %',
									style: {
										color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
									}
								}
							}
						},
						series: [{
							name: 'Brands',
							colorByPoint: true,
							data:(function(){
								var jsonData = [];
								for(var i=0;i<data.length;i++){
									jsonData.push({
												   name:data[i].browser,
												   y:data[i].prcnt});					
									}
								return jsonData;	
							})()	
							
						}]
						
					});

					
//						HighChart 끝
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText); 
				}
			})
			
		
	})
		
		
	</script>
</body>
</html>
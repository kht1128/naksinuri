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
				<div class="col-lg-6">
					<div id="tab_btns2" class="btn-group btn-group-sm" role="group" aria-label="...">
						<button type="button" id="static_1" name="static_day" class="btn btn-default" onclick="statistics_day(1);" >최근 1일</button>
						<button type="button" id="static_7" name="static_week" class="btn btn-primary" onclick="statistics_day(7);">7일</button>
						<button type="button" id="static_30" name="static_month" class="btn btn-default" onclick="statistics_day(30);">30일</button>
						<button type="button" id="static_self" class="btn btn-default" onclick="type_myself()">직접입력</button>
					</div>
				</div>
				<div class="col-lg-6">
					<input type="text" id="start_dt" name="start_dt" class="date_input" /> ~ <input type="text" id="end_dt" name="end_dt" class="date_input" />
					 <button type="button" onclick="statistics_myself()" class="btn btn-default btn-sm">조회</button>
					 <button type="button" onclick="add_time()" class="btn btn-default btn-sm">시간조회</button>
					<br/><br/>
					<span id="apnd_time">
						
					</span>
				</div>
			</div>
			
			<script>
				function add_time(){
					if(!$('#start_dt').val()||!$('#end_dt').val()){
						alert("시작날짜와 종료날짜를 먼저 입력해주세요.");
						return false;
					}
					
					if(document.getElementById("apnd_time").children.length==0){
						$('#apnd_time').append("<input type=\"time\" id=\"start_time\" name=\"start_time\" class=\"date_input\" /> ~ <input type=\"time\" id=\"end_time\" name=\"end_time\" class=\"date_input\" />");
					}else{
						$('#apnd_time').empty();
					}
				}
			
				function statistics_day(dval){
					$('#tab_btns2').children().removeClass();
					$('#tab_btns2').children().addClass('btn btn-default');
					$('#static_'+dval+'').removeClass();
					$('#static_'+dval+'').addClass('btn btn-primary');
					
					$('#day_value').val(dval);
					$('#mode').val('n');
					var board = $('#brd_name').val();
					var name = $('#brd_title').val();
					var url = "";
					url = "/admin/get"+board+"URL.do";
					chart(url,name);
					etcData();
				}
			</script>
			<input type="hidden" id="day_value" value="7"/>
			<input type="hidden" id="mode" value="n"/>
			<input type="hidden" id="brd_title" value="낚시교실"/>
			<input type="hidden" id="brd_name" value="Lesson"/>
			<section id="yoyak" class="conbox">
				<h2>방문현황 요약</h2>
				<ul class="yoyakbox floats box3">
					<li>
						<b>방문자수</b><br/>
						<em class="num" id="visit_cnt"></em>
						<span id="page_compare3"></span>
					</li>
					<li>
						<b>방문횟수</b><br />
						<em class="num" id="visit_cnt2"></em>
						<span id="page_compare2"></span> 
					</li>
					<li>
						<b>페이지뷰</b><br />
						<em class="num" id="page_total"></em>
						<span  id="page_compare"></span> 
					</li>
				</ul>
			</section>
	
			<section id="daily" class="conbox">
				<h2>일별 방문분포</h2>
					<div class="col-lg-12" align="center">
						<div id="tab_btns" class="btn-group btn-group-sm" role="group" aria-label="...">
							<button type="button" onclick="getboardURL('낚시교실','Lesson');" id="f_Lesson" class="btn btn-primary">낚시교실</button>
							<button type="button" onclick="getboardURL('낚시정보','Info');" id="f_Info" class="btn btn-default">낚시정보</button>
							<button type="button" onclick="getboardURL('낚시공유','Share');" id="f_Share" class="btn btn-default">낚시공유</button>
							<button type="button" onclick="getboardURL('낚시소식','Sosig');" id="f_Sosig" class="btn btn-default">낚시소식</button>
							<button type="button" onclick="getboardURL('정책홍보','Policy');" id="f_Policy" class="btn btn-default">정책홍보</button>
							<button type="button" onclick="getboardURL('설문조사','Survey');" id="f_Survey" class="btn btn-default">설문조사</button>
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
					<tbody id="apnd">
					
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

function etcData(){

	$.ajax({
		type:"GET",
		data:{day_type:$('#day_value').val()},
		url :'/admin/get_visitcnt.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$('#visit_cnt').empty();
			$('#visit_cnt').append(data);
			
			$('#visit_cnt2').empty();
			$('#visit_cnt2').append(data);
//for문 끝 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
	
	$.ajax({
		type:"GET",
		data:{day_type:$('#day_value').val()},
		url :'/admin/get_pagecnt.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$('#page_total').empty();
			$('#page_total').append(data);
//for문 끝 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
	
	
	var apnd_str='';
	$.ajax({
		type:"GET",
		data:{day_type:$('#day_value').val()},
		url :'/admin/get_top10.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: false,
		success: function(data, status, xhr) {
			console.log(data);
		
			for(var i=0;i<data.length;i++){
				apnd_str +="<tr>";
				apnd_str +="<td>"+(i+1)+"</td>";
				apnd_str +="<td>"+data[i].bo_name+"</td>";
				apnd_str +="<td>"+data[i].cnt+"</td>";
				apnd_str +="<td>";
				apnd_str +="<div class=\"progress\">";
				apnd_str +="<div class=\"progress-bar progress-bar-info progress-bar-striped\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+data[i].prcnt+"%\">";
				apnd_str +="<span class=\"sr-only\">20% Complete</span>";
				apnd_str +="</div>";
				apnd_str +="</div>";
				apnd_str +="</td></tr>"; 
			}
			$('#apnd').empty();
			$('#apnd').append(apnd_str);
//for문 끝 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
	
	$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/compare1.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data > 0){
					$('#page_compare').empty();
					$('#page_compare').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
				}else{
					$('#page_compare').empty();
					$('#page_compare').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
				}
				 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		});
		
		$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/compare2.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data > 0){
					$('#page_compare2').empty();
					$('#page_compare3').empty();
					$('#page_compare2').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
					$('#page_compare3').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
				}else{
					$('#page_compare2').empty();
					$('#page_compare3').empty();
					$('#page_compare2').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
					$('#page_compare3').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
				}
				 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		});
	
	
}

/*========버튼 제어=========*/

function getboardURL(name,board){
	
	$('#brd_title').val(name);
	$('#brd_name').val(board);
	
	$('#tab_btns').children().removeClass();
	$('#tab_btns').children().addClass('btn btn-default');
	$('#f_'+board+'').removeClass();
	$('#f_'+board+'').addClass('btn btn-primary');
	
	
	var url = "";
	if($('#mode').val()=="n"){
		url = "/admin/get"+board+"URL.do";
		chart(url,name);
	}else if($('#mode').val()=="s"){
		url = "/admin/get"+board+"URLself.do";
		chart2(url,name);
	}
	
	
	
	
	
};


/*========버튼 제어=========*/


function type_myself(){
	$('#start_dt').focus();
}	


function statistics_myself(){
	
	$('#mode').val('s');
	
	$('#tab_btns2').children().removeClass();
	$('#tab_btns2').children().addClass('btn btn-default');
	$('#static_self').removeClass();
	$('#static_self').addClass('btn btn-primary');
	var board = $('#brd_name').val();
	var name = $('#brd_title').val();
	var url = "";
	url = "/admin/get"+board+"URLself.do";
	
	if($('#start_dt').val()==""){
		alert('조회할 시작일을 입력해주세요.');
		return false;
	}
	if($('#end_dt').val()==""){
		alert('조회할 종료일을 입력해주세요.');
		return false;
	}

	var startv;
	var endv;
	var chart_type;
	
	
	if($("#start_time").val()){
		startv = $("#start_dt").val()+" "+$("#start_time").val();	
	}else{
		startv = $("#start_dt").val();
	}
	
	if($("#end_time").val()){
		endv = $("#end_dt").val()+" "+$("#end_time").val();	
	}else{
		endv = $("#end_dt").val();
	}
	
	if($('#end_time').val()||$('#start_time').val()){
		chart_type = 'time';
	}else{
		chart_type = 'daily';
	}
	
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv},
		url :'/admin/compare1_self.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data > 0){
				$('#page_compare').empty();
				$('#page_compare').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
			}else{
				$('#page_compare').empty();
				$('#page_compare').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
			}
			 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
	
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv},
		url :'/admin/compare2_self.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			if(data > 0){
				$('#page_compare2').empty();
				$('#page_compare3').empty();
				$('#page_compare2').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
				$('#page_compare3').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
			}else{
				$('#page_compare2').empty();
				$('#page_compare3').empty();
				$('#page_compare2').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
				$('#page_compare3').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
			}
			 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});

//=============================================================================
	
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv},
		url :'/admin/get_visitcnt_self.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$('#visit_cnt').empty();
			$('#visit_cnt').append(data);
			
			$('#visit_cnt2').empty();
			$('#visit_cnt2').append(data);

			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
//=================================================================================//
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv},
		url :'/admin/get_pagecnt_self.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			$('#page_total').empty();
			$('#page_total').append(data);
 
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	});
//=======================================================================================//		

	
   var apnd_str='';

	$.ajax({
		type:"GET",
		data:{start:startv,end:endv},
		url :'/admin/get_top10_self.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			
			for(var i=0;i<data.length;i++){
				apnd_str +="<tr>";
				apnd_str +="<td>"+(i+1)+"</td>";
				apnd_str +="<td>"+data[i].bo_name+"</td>";
				apnd_str +="<td>"+data[i].cnt+"</td>";
				apnd_str +="<td>";
				apnd_str +="<div class=\"progress\">";
				apnd_str +="<div class=\"progress-bar progress-bar-info progress-bar-striped\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+data[i].prcnt+"%\">";
				apnd_str +="<span class=\"sr-only\">20% Complete</span>";
				apnd_str +="</div>";
				apnd_str +="</div>";
				apnd_str +="</td></tr>"; 
			}
			$('#apnd').empty();
			$('#apnd').append(apnd_str);
		
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
	
	
	var hoursData = [];
	var jsonData = [];
	var db_date;
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv,type:chart_type},
		url :url,
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			
			
		for(var i=0;i<data.length;i++){
			if(chart_type =="daily"){
				db_date=fn_substr(data[i].hours);	
			}else{
				db_date=data[i].hours;
			}	
			
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
	
	
	
	$.ajax({
				type:"GET",
				url :'/admin/getUser_BrowserInfomyself.do',
				data:{start:startv,end:endv},
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
//						HighChart 시작 
					
				
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
	var db_date;
	$.ajax({
		type:"GET",
		data:{day_type:$('#day_value').val()},
		url :url,
		dataType: 'json',
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
	
	
	
	$.ajax({
				type:"GET",
				url :'/admin/getUser_BrowserInfo.do',
				data:{day_type:$('#day_value').val()},
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
//						HighChart 시작 
					
				
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
}



function chart2(url,name){
	
	var hoursData = [];
	var jsonData = [];
	var db_date;
	
	var startv;
	var endv;
	var chart_type;	
	
	
	if($("#start_time").val()){
		startv = $("#start_dt").val()+" "+$("#start_time").val();	
	}else{
		startv = $("#start_dt").val();
	}
	
	if($("#end_time").val()){
		endv = $("#end_dt").val()+" "+$("#end_time").val();	
	}else{
		endv = $("#end_dt").val();
	}
	

	if($('#start_time').val()||$('#end_time').val()){
		chart_type = 'time';
	}else{
		chart_type = 'daily';
	}
	
	$.ajax({
		type:"GET",
		data:{start:startv,end:endv,type:chart_type},
		url :url,
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
			
			
		for(var i=0;i<data.length;i++){
			if(chart_type=="daily"){
				db_date=fn_substr(data[i].hours);	
			}else{
				db_date=data[i].hours;
			}
			
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
	
	
	
	$.ajax({
				type:"GET",
				url :'/admin/getUser_BrowserInfomyself.do',
				data:{start:startv,end:endv},
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
//						HighChart 시작 
					
				
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
}

	
	</script>


	<script>

	
	$(document).ready(function(){
		
		var hoursData = [];
		var jsonData = [];
		
		$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/getLessonURL.do',
			dataType: 'json',
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
		
					

		
			var search_type = $('#searchType').val();	
			$.ajax({
				type:"GET",
				url :'/admin/getUser_BrowserInfo.do',
				data:{day_type:$('#day_value').val()},
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
//						HighChart 시작 
					
				
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
			
		
			var apnd_str = '';
			$.ajax({
				type:"GET",
				data:{day_type:$('#day_value').val()},
				url :'/admin/get_top10.do',
				dataType: 'json',
				contentType: "application/json;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					console.log(data);
					for(var i=0;i<data.length;i++){
						apnd_str +="<tr>";
						apnd_str +="<td>"+(i+1)+"</td>";
						apnd_str +="<td>"+data[i].bo_name+"</td>";
						apnd_str +="<td>"+data[i].cnt+"</td>";
						apnd_str +="<td>";
						apnd_str +="<div class=\"progress\">";
						apnd_str +="<div class=\"progress-bar progress-bar-info progress-bar-striped\" role=\"progressbar\" aria-valuenow=\"20\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width:"+data[i].prcnt+"%\">";
						apnd_str +="<span class=\"sr-only\">20% Complete</span>";
						apnd_str +="</div>";
						apnd_str +="</div>";
						apnd_str +="</td></tr>"; 
					}
					$('#apnd').empty();
					$('#apnd').append(apnd_str);
		//for문 끝 
					
				},
				error: function(jqXHR, textStatus, errorThrown) {
					alert(jqXHR.responseText); 
				}
			})
		
			$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/get_visitcnt.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				console.log(data);
				$('#visit_cnt').append(data);
				
				$('#visit_cnt2').append(data);
	//for문 끝 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		})
		
			$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/get_pagecnt.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				console.log(data);
				$('#page_total').empty();
				$('#page_total').append(data);
	//for문 끝 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		})
		
		$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/compare1.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data > 0){
					$('#page_compare').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
				}else{
					$('#page_compare').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
				}
				 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		})
		
		$.ajax({
			type:"GET",
			data:{day_type:$('#day_value').val()},
			url :'/admin/compare2.do',
			dataType: 'json',
			contentType: "application/json;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data > 0){
					$('#page_compare2').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
					$('#page_compare3').append("<span class=\"updown up\"><i class=\"fa fa-arrow-circle-up\" aria-hidden=\"true\"></i>"+data+"</span>");
				}else{
					$('#page_compare2').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
					$('#page_compare3').append("<span class=\"updown down\"><i class=\"fa fa-arrow-circle-down\" aria-hidden=\"true\"></i>"+data+"</span>");
				}
				 
				
			},
			error: function(jqXHR, textStatus, errorThrown) {
				alert(jqXHR.responseText); 
			}
		})
		
	})
		
		
	</script>
</body>
</html>
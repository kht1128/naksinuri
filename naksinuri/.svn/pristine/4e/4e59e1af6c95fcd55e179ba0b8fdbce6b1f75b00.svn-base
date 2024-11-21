<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<c:set var="depthName" value="static" />
<c:set var="pageName" value="invitos" />

<%@include file="../static_header.jsp"%>
<body>
<form:form commandName="listform" id="listform" method="post" enctype="multipart/form-data" >
</form:form>


	<!-- } 상단 헤더 -->

	
	<!-- 사이트통계 { -->
	<article id="siteCondition">
		<h1>운영체제(OS)</h1>
		<div class="date_choice row">
			<div class="col-lg-6">
				<div class="btn-group btn-group-sm" role="group" aria-label="...">
					<button type="button" class="btn btn-default" onclick="get_osday()">최근 1일</button>
					<button type="button" class="btn btn-default" onclick="get_osweek()">7일</button>
					<button type="button" class="btn btn-primary" onclick="get_osmonth()">30일</button>
					<button type="button" class="btn btn-default" onclick="type_myself()">직접입력</button>
				</div>
			</div>
			<form id="frm">
				<div class="col-lg-6">
					<input type="text" id="start_dt" name="start_dt" readonly class="date_input" value="${starts}"/> ~ <input type="text" readonly id="end_dt" name="end_dt" class="date_input" value="${ends}"/> <button type="button" class="btn btn-default btn-sm" onclick="getOS_myself()">조회</button>
				</div>
			</form>
		</div>

		<section id="yoyak" class="conbox">
			<h2>운영체제 요약</h2>
			<ul class="yoyakbox floats box4">
				<li>
					<b>PC웹 점유율</b><br />
					<em class="num">${pc_prcnt}%</em>
				</li>
				<li>
					<b>모바일 점유율</b><br />
					<em class="num">${mobile_prcnt}%</em>
				</li>
				<li>
					<b>PC웹 최다 OS</b><br />
					<strong style="font-size:22px">${most_pc}</strong>
				</li>
				<li>
					<b>모바일 최다 OS</b><br />
					<strong style="font-size:22px">${most_mobile}</strong>
				</li>
			</ul>
		</section>

		<section id="osGraph" class="conbox">
			<h2>운영체제 그래프</h2>
			<div id="highchart"></div>
		</section>

		<section id="osdetail" class="conbox">
			<h2>운영체제 그래프</h2>
			<div class="row">
				<div class="col-lg-6">
					<table class="alaly_tbl table table-bordered">
						<colgroup>
							<col width="50" />
							<col />
							<col width="100" />
							<col width="80" />
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>운영체제</th>
								<th>방문자수</th>
								<th>비율</th>
							</tr>
						</thead>
						<tbody>	
							<tr>
								<c:forEach var="item" items="${OSpc_list}" varStatus="status">
									<tr>
										<td>${status.count }</td>
										<td>${item.statistic_os}</td>
										<td>${item.cnt}</td>
										<td>${item.prcnt}%</td>
									</tr>	
								</c:forEach>
								<c:if test="${fn:length(OSpc_list) == 0 }">
									<tr>
										<td colspan="4">정보가 없습니다.</td>
									</tr>
								</c:if>
							</tr>				
						</tbody>
					</table>
				</div>
				<div class="col-lg-6">
					<table class="alaly_tbl table table-bordered">
						<colgroup>
							<col width="50" />
							<col />
							<col width="100" />
							<col width="80" />
						</colgroup>
						<thead>
							<tr>
								<th>순위</th>
								<th>운영체제</th>
								<th>방문자수</th>
								<th>비율</th>
							</tr>
						</thead>
						<tbody>						
							<tr>
								<c:forEach var="item" items="${OSmobile_list}" varStatus="status">
									<tr>
										<td>${status.count }</td>
										<td>${item.statistic_os}</td>
										<td>${item.cnt}</td>
										<td>${item.prcnt}%</td>
									</tr>
								</c:forEach>
								<c:if test="${fn:length(OSmobile_list) == 0 }">
										<tr>
											<td colspan="4">정보가 없습니다.</td>
										</tr>
								</c:if>
							</tr>			
						</tbody>
					</table>
				</div>
			</div>
		</section>
	</article>
	<!-- } 사이트통계 -->

	<!-- 하단 푸터 { -->
	<%@include file="../static_tail.jsp"%>
	<!-- } 하단 푸터 -->
<script>
$(document).ready(function(){
	
	$('#start_dt').datepicker({dateFormat : 'yy-mm-dd'});
	$('#end_dt').datepicker({dateFormat : 'yy-mm-dd'});
	
	var jsonData=[];
	var osnameData=[];
	
	$.ajax({
		type:"GET",
		url :'/admin/getUser_OSInfomonth.do',
		dataType: 'json',
		contentType: "application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			console.log(data);
	
			for(var i=0; i<data.length;i++){
				
				osnameData.push(data[i].statistic_os);
				jsonData.push(data[i].cnt);
			
			Highcharts.chart('highchart', {
				chart: {
			        type: 'bar'
			    },
			    title: {
			        text: 'Historic World Population by Region'
			    },
			    subtitle: {
			        text: 'Source: <a href="https://en.wikipedia.org/wiki/World_population">Wikipedia.org</a>'
			    },
			    xAxis: {
			        categories: osnameData,
			        title: {
			            text: null
			        }
			    },
			    yAxis: {
			        min: 0,
			        title: {
			            text: '',
			            align: 'high'
			        },
			        labels: {
			            overflow: 'justify'
			        }
			    },
			    tooltip: {
			        valueSuffix: '회'
			    },
			    plotOptions: {
			        bar: {
			            dataLabels: {
			                enabled: true
			            }
			        }
			    },
			    legend: {
			        layout: 'vertical',
			        align: 'right',
			        verticalAlign: 'top',
			        x: -40,
			        y: 80,
			        floating: true,
			        borderWidth: 1,
			        backgroundColor: ((Highcharts.theme && Highcharts.theme.legendBackgroundColor) || '#FFFFFF'),
			        shadow: true
			    },
			    credits: {
			        enabled: false
			    },
			    series: [{
			        name: 'count',
			        data:jsonData
			        	
			    }]
			});
			}
		},
		error: function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseText); 
		}
	})
	
})

function get_osday(){
	document.location.href="/admin/static/invitos/listd.do";
}

function get_osweek(){
	document.location.href="/admin/static/invitos/list.do";
}

function get_osmonth(){
	document.location.href="#;";
}


function type_myself(){
	$('#start_dt').focus();
}

function getOS_myself(){
	var form = document.getElementById("frm");
	if($('#start_dt').val()==""){
		alert("조회할 시작일을 입력해주세요");
		return false;
	}
	
	if($('#end_dt').val()==""){
		alert('조회할 종료일을 입력해주세요');
		return false;
	}
	
	  if($('#start_dt').val()==$('#end_dt').val()){
		  alert('시작날짜가 종료날짜와 같습니다.');
		  return false;
	    }
	    
    var startDate = $( "input[name='start_dt']" ).val();
    var startDateArr = startDate.split('-');
     
    var endDate = $( "input[name='end_dt']" ).val();
    var endDateArr = endDate.split('-');
             
    var startDateCompare = new Date(startDateArr[0], startDateArr[1], startDateArr[2]);
    var endDateCompare = new Date(endDateArr[0], endDateArr[1], endDateArr[2]);
     
    if(startDateCompare.getTime() > endDateCompare.getTime()) {
        alert("기간의 시작날짜와 종료날짜를 확인해 주세요.");
    return false;
    }
    form.action="/admin/static/invitos/listself.do";
    form.submit();
}

	
	</script>
</body>
</html>
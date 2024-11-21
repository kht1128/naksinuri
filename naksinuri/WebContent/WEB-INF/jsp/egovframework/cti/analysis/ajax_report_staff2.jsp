<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="h-250 p-0 m-0" id="staff_barlist"></div>

<script>
$(function(){
	$('#analysis_staff_report_title').html('( ${searchStrDate} ~ ${searchEndDate} )');
});
//--------------------------------------------------
// 차트(그래프) 영역
//--------------------------------------------------
var labelsdata = new Array();
var seriesdata = new Array();
var seriesdataitem1 = new Array();
var seriesdataitem2 = new Array();
var seriesdataitem3 = new Array();
var seriesdataitem4 = new Array();
	labelsdata.push('x');
	seriesdataitem1.push('전체');
	seriesdataitem2.push('응답건');
	seriesdataitem3.push('부재중');
	seriesdataitem4.push('취소건'); 
	<c:forEach var="item" varStatus="status" items="${list}">
		labelsdata.push('${item.STAFF_NM}');
		seriesdataitem1.push('${item.CALL_TOTAL}');
		seriesdataitem2.push('${item.CALL_RSPONS}');
		seriesdataitem3.push('${item.CALL_DRSC}');
		seriesdataitem4.push('${item.CALL_CANCEL}');
	</c:forEach>
seriesdata.push(labelsdata);
seriesdata.push(seriesdataitem1);
seriesdata.push(seriesdataitem2);
seriesdata.push(seriesdataitem3);
seriesdata.push(seriesdataitem4);
var chart = c3.generate({
	bindto: '#staff_barlist',
	data: {
		x: 'x',
		columns: seriesdata,
		type: 'bar',
	},
	bar: {
        // width: {
        //  ratio: 0.55 // this makes bar width 55% of length between ticks
        // }
        width: {
          max: 20
        }
    },
	color: {
        pattern: [Config.colors("primary", 600), Config.colors("green", 600), Config.colors("red", 500), Config.colors("blue-grey", 200)]
	},
	padding: {
        right: 40
	},
	axis: {
        x: {
          type: 'category',
          tick: {
            outer: false,
            //format: 's'
          }
		},
        y: {
          //max: 300,
          min: 0,
          tick: {
        	outer: false,
            //count: 7,
            //values: [0, 50, 100, 150, 200, 250, 300]
          }
        }
	},
	grid: {
        x: {
          show: true
        },
        y: {
          show: true
        }
	} 
});
/* 동적추가
setTimeout(function () {
  time_series_chart.load({
    columns: [['data3', 210, 180, 260, 290, 250, 240]]
  });
}, 1000);
*/
</script>

<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="p-0 m-0 " id="ivr_barlist"></div>

<script>
$(function(){
	$('#analysis_ivr_report_title').html('( ${searchStrDate} ~ ${searchEndDate} )');
});
//--------------------------------------------------
// 차트(그래프) 영역
//--------------------------------------------------

<c:forEach var="item" items="${list}">

	$('#ivr_barlist').append('<div class="float-left" id="ivr_barlist_${item.STAFF_MBR_ID}" style="width:33%;"></div>');
	
	var data_pie = new Array();
	<c:forEach var="item2" items="${item.mapItem}">
		<c:forEach var="item_cd" items="${list_mbr_cd}">
			<c:if test="${item2.key eq item_cd.CD_ID}">
				var row_item = new Array();
				row_item.push('${item_cd.CD_NM} '+'(${item2.value} 건)');
				row_item.push('${item2.value}');
				data_pie.push(row_item);
			</c:if>
		</c:forEach>
		<c:if test="${item2.key eq 'NONE'}">
			var row_item = new Array();
			row_item.push('구분없음 '+'(${item2.value} 건)');
			row_item.push('${item2.value}');
			data_pie.push(row_item);
		</c:if>
	</c:forEach>
	
	var pie_chart2 = c3.generate({
		bindto: '#ivr_barlist_${item.STAFF_MBR_ID}',
		data: {
	        columns: data_pie,
	        type: 'pie'
		},
		legend: {
	        position: 'right',
	        show: true
		},
		title: {
	        show: true,
	        text: '${item.STAFF_NM}',
	        position: 'top-center',   // top-left, top-center and top-right
	        /* padding: {
	          top: 20,
	          right: 20,
	          bottom: 40,
	          left: 50
	        } */
	    },
		pie: {
	        label: {
	          show: true
	        },
	        onclick: function onclick(d, i) {},
	        onmouseover: function onmouseover(d, i) {},
	        onmouseout: function onmouseout(d, i) {}
		}
	});	
	
</c:forEach>

</script>

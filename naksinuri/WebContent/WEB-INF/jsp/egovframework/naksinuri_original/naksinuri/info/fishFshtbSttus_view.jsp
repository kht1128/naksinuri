<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:scriptlet>
pageContext.setAttribute("cr", "\r");
pageContext.setAttribute("lf", "\n");
pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>
<style>
.basic_tbl th, .basic_tbl td{border:1px solid #000000;text-align:center;font-size:20px;}
.basic_tbl thead th{border:0px;}
.basic_tbl tfoot td{border:0px;}
.textLeft{text-align:left !important;}
.textRight{text-align:right !important;}
.pdb0px{padding-bottom:0px !important;}
.pdt0px{padding-top:0px !important;}
.bgcWhite{background-color:#fff !important; color:#000 !important;border:1px solid #000;}
.border1{border: 1px solid #000;}
.fontSize16 th{font-size:16px !important;}
</style>
<div class="modal-dialog" role="document" style="width:75%;">
	<div class="modal-content form-horizontal">
		<div class="modal-header">
			<h4 class="modal-title" tabindex="0" style="display: inline;">${title}</h4>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   				<span aria-hidden="true">×</span>
 			</button>
		</div>
		<div class="modal-body pt-0 pl-30 pr-30">
			<div class="form-group" >
				<div class="modal-body pt-30 pl-30 pr-30">
					<div class="form-group row modal-view-table scroll-y">
						<c:choose>
							<c:when test="${addWebLink eq 'fshtbSttus' }">
								<table class="basic_tbl">
									<caption>${title}</caption>
									<colgroup>
										<col width="120">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th class="textLeft pdb0px bgcWhite" colspan="${dataCnt}">□ ${title}</th>
										</tr>
										<tr>
											<th class="textRight pdt0px bgcWhite" colspan="${dataCnt}">(단위 : 척수)</th>
										</tr>
										<tr class="border1">
											<th>시도별</th>
											<c:forEach var="item" items="${yearList}">
												<th>${item.year}년</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>계</th>
											<c:forEach var="item" items="${totList}">
												<td>${item.fshtb_sm}</td>
											</c:forEach>
										</tr>
										<c:forEach var="item" items="${list}">
											<tr>
												<th>${item.fshtb_area}</th>
												<td>${item.y1}</td>
												<td>${item.y2}</td>
												<td>${item.y3}</td>
												<td>${item.y4}</td>
												<td>${item.y5}</td>
												<td>${item.y6}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
							<c:when test="${addWebLink eq 'fshtbUser'}">
								<table class="basic_tbl">
									<caption>${title}</caption>
									<colgroup>
										<col width="100">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th class="textLeft pdb0px bgcWhite" colspan="${dataCnt}">□ ${subTitle1}</th>
										</tr>
										<tr>
											<th class="textRight pdt0px bgcWhite" colspan="${dataCnt}">(단위 : 천명)</th>
										</tr>
										<tr class="border1">
											<th>구분</th>
											<c:forEach var="item" items="${totList}">
												<th>'${item.year}</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>승객 수</th>
											<c:forEach var="item" items="${totList}">
												<td>${item.fshtb_user_cnt}</td>
											</c:forEach>
										</tr>
									</tbody>
									<tfoot>
										<tr>
											<td class="textLeft pdt0px bgcWhite" colspan="${dataCnt}">* 해양경찰청 낚시어선 출항신고(승선인원) 실적</td>
										</tr>
									</tfoot>
								</table>
								<br><br>
								<table class="basic_tbl">
									<caption>${title}</caption>
									<colgroup>
										<col width="100">
										<col>
									</colgroup>
									<thead>
										<tr>
											<th class="textLeft pdb0px bgcWhite" colspan="${dataCnt}">□ ${subTitle2}</th>
										</tr>
										<tr>
											<th class="textRight pdt0px bgcWhite" colspan="${dataCnt}">(단위 : 명)</th>
										</tr>
										<tr class="border1">
											<th>구분</th>
											<c:forEach var="item" items="${totList}">
												<th>'${item.year}</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<tr>
											<th>승객 수</th>
											<c:forEach var="item" items="${yearList}">
												<td>${item.fshtb_user_cnt}</td>
											</c:forEach>
										</tr>
										<c:forEach var="item" items="${list}">
											<tr>
												<th>${item.fshtb_area}</th>
												<td>${item.y1}</td>
												<td>${item.y2}</td>
												<td>${item.y3}</td>
												<td>${item.y4}</td>
												<td>${item.y5}</td>
												<td>${item.y6}</td>
												<td>${item.y7}</td>
												<td>${item.y8}</td>
												<td>${item.y9}</td>
												<td>${item.y10}</td>
												<td>${item.y11}</td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td class="textLeft pdt0px bgcWhite" colspan="${dataCnt}">* 해양경찰청(출‧입항 신고인원)</td>
										</tr>
									</tfoot>
								</table>
							</c:when>
							<c:when test="${addWebLink eq 'siDoFshtb'}">
								<table class="basic_tbl">
									<caption>${title}</caption>
									<colgroup>
										<col width="5%"/>
										<col width="5%"/>
									</colgroup>
									<thead>
										<tr>
											<th class="textLeft pdb0px bgcWhite" colspan="${dataCnt}">□ ${title}</th>
										</tr>
										<tr>
											<th class="textRight pdt0px bgcWhite" colspan="${dataCnt}">(단위 : 척)</th>
										</tr>
										<tr class="border1 fontSize16">
											<th>구분</th>
											<th>계</th>
											<th>1톤미만</th>
											<th>1톤-2톤미만</th>
											<th>2톤-3톤미만</th>
											<th>3톤-4톤미만</th>
											<th>4톤-5톤미만</th>
											<th>5톤-6톤미만</th>
											<th>6톤-7톤미만</th>
											<th>7톤-8톤미만</th>
											<th>8톤-9톤미만</th>
											<th>9톤-10톤미만</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${yearList}">
											<tr>
												<th>${item.fshtb_area}</th>
												<td>${item.fshtb_sm}</td>
												<td>${item.ton1}</td>
												<td>${item.ton1_2}</td>
												<td>${item.ton2_3}</td>
												<td>${item.ton3_4}</td>
												<td>${item.ton4_5}</td>
												<td>${item.ton5_6}</td>
												<td>${item.ton6_7}</td>
												<td>${item.ton7_8}</td>
												<td>${item.ton8_9}</td>
												<td>${item.ton9_10}</td>
											</tr>
										</c:forEach>
										<c:forEach var="item" items="${totList}">
											<tr>
												<th>계</th>
												<td>${item.fshtb_sm}</td>
												<td>${item.ton1}</td>
												<td>${item.ton1_2}</td>
												<td>${item.ton2_3}</td>
												<td>${item.ton3_4}</td>
												<td>${item.ton4_5}</td>
												<td>${item.ton5_6}</td>
												<td>${item.ton6_7}</td>
												<td>${item.ton7_8}</td>
												<td>${item.ton8_9}</td>
												<td>${item.ton9_10}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:when>
						</c:choose>
					</div>
			  	</div>
			</div>
			<div class="text-right">
	            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
	        </div>
		</div>
	</div>
</div>


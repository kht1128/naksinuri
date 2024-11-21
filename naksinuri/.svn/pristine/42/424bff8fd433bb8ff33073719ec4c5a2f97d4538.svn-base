<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" 	   uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:scriptlet>
    pageContext.setAttribute("cr", "\r");
    pageContext.setAttribute("lf", "\n");
    pageContext.setAttribute("crlf", "\r\n");
</jsp:scriptlet>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>
<style>
th  { text-align: center; }
.alignCenter { text-align: center; } 
</style>

    <!-- Page -->
    <div class="page">
      <div class="page-header">
        <h1 class="page-title">홈스테이관리</h1>
      </div>

      <div class="page-content container-fluid">

         <div class="row">
          <div class="col-xl-12">
            <!-- Panel Editing Rows -->
            <div class="panel">
              <header class="panel-heading">
                <h3 class="panel-title">홈스테이 상세</h3>
              </header>
              <div class="panel-body">
                <table id="exampleFooEditing" class="table table-hover footable footable-paging footable-paging-center ">
                  <colgroup>
                  	<col width='15%' >
                  	<col width='35%'>
                  	<col width='15%' >
                  	<col width='35%'>
                  </colgroup>
                 
                  <tbody>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">운영자</th>
                      <td>${homestay_info.hsnm }</td>
                      
						<c:choose>
                      		<c:when test="${homestay_info.hsbirth eq null || homestay_info.hsbirth == '' }">
                      			<c:set var="hsbirth" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsbirth" value="${homestay_info.hsbirth}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsage eq null  || homestay_info.hsage == '' }">
                      			<c:set var="hsage" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsage" value="${homestay_info.hsage}" />
                      		</c:otherwise>
                      </c:choose>
                      <th style="font-weight:bold; background:#f0f4f5; ">생년월일/나이</th>
                      <td>생년월일 : ${hsbirth} <strong>/</strong> 나이 : ${hsage}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">연락처</th>
                      <td>${homestay_info.hshp}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">이메일</th>
                      <td>${homestay_info.hsemail }</td>
                    </tr>

					<c:choose>
                      		<c:when test="${homestay_info.hszipcd eq null   || homestay_info.hszipcd == '' }">
                      			<c:set var="hszipcd" value="(-)" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hszipcd" value="(${homestay_info.hszipcd})" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">주소</th>
                      <td colspan="3">${hszipcd } ${homestay_info.hsaddr1 } ${homestay_info.hsaddr2 }</td>
                    </tr>
                    
					<c:choose>
                      		<c:when test="${homestay_info.hsbankbnm eq null   || homestay_info.hsbankbnm == '' }">
                      			<c:set var="hsbankbnm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsbankbnm" value="${homestay_info.hsbankbnm}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsbanknum eq null   || homestay_info.hsbanknum == '' }">
                      			<c:set var="hsbanknum" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsbanknum" value="${homestay_info.hsbanknum}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsbankunm eq null   || homestay_info.hsbankunm == '' }">
                      			<c:set var="hsbankunm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsbankunm" value="${homestay_info.hsbankunm}" />
                      		</c:otherwise>
                      </c:choose>                    
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">통장정보</th>
                      <td colspan="3">
                      	은행 : ${hsbankbnm } 
                      	<strong>/</strong> 
                      	계좌번호 : ${hsbanknum } 
                      	<strong>/</strong>
                      	예금주: ${hsbankunm}
                      </td>
                    </tr>
                    
                    <c:choose>
                      		<c:when test="${homestay_info.hscarrerfish eq null   || homestay_info.hscarrerfish == '' }">
                      			<c:set var="hscarrerfish" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hscarrerfish" value="${homestay_info.hscarrerfish}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hscarrerback eq null   || homestay_info.hscarrerback == '' }">
                      			<c:set var="hscarrerback" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hscarrerback" value="${homestay_info.hscarrerback}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hscarrersuccess eq null   || homestay_info.hscarrersuccess == '' }">
                      			<c:set var="hscarrersuccess" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hscarrersuccess" value="${homestay_info.hscarrersuccess}" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">경력</th>
                      <td colspan="3"> 
                      	어업경력 : ${hscarrerfish} 년 
                      	<strong>/</strong> 
                      	귀어경력 : ${hscarrerback} 년 
                      	<strong>/</strong> 
                      	어업인후계자 경력 : ${hscarrersuccess} 년 
                      </td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">영어분야(품목)</th>
                      <td colspan="3">${homestay_info.hsitem}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">소개</th>
                      <td colspan="3">${fn:replace(homestay_info.hsdes , crlf, '<br>' )}</td>
                    </tr>

					<c:choose>
                      		<c:when test="${homestay_info.hshomestatnm eq null   || homestay_info.hshomestatnm == '' }">
                      			<c:set var="hshomestatnm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hshomestatnm" value="${homestay_info.hshomestatnm}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hshometypenm eq null   || homestay_info.hshometypenm == '' }">
                      			<c:set var="hshometypenm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hshometypenm" value="${homestay_info.hshometypenm}" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">주거/주거</th>
                      <td colspan="3">
	                      주거형태 : ${hshomestatnm} 
	                      <strong>/</strong> 
	                      주택형태 : ${hshometypenm}
	                   </td>
                    </tr>
                    
                    <c:choose>
                      		<c:when test="${homestay_info.hsfarmtotalcnt eq null   || homestay_info.hsfarmtotalcnt == '' }">
                      			<c:set var="hsfarmtotalcnt" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsfarmtotalcnt" value="${homestay_info.hsfarmtotalcnt}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsfarmparentcnt eq null   || homestay_info.hsfarmparentcnt == '' }">
                      			<c:set var="hsfarmparentcnt" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsfarmparentcnt" value="${homestay_info.hsfarmparentcnt}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsmetaage eq null   || homestay_info.hsmetaage == '' }">
                      			<c:set var="hsmetaage" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsmetaage" value="${homestay_info.hsmetaage}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsfarmchildcnt eq null   || homestay_info.hsfarmchildcnt == '' }">
                      			<c:set var="hsfarmchildcnt" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsfarmchildcnt" value="${homestay_info.hsfarmchildcnt}" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">가족상황</th>
                      <td colspan="3">
                      	총 ${hsfarmtotalcnt} 명 
                      	(부모 명: ${hsfarmparentcnt} 명
                      	<strong>/</strong>
                      	배우자 : ${hsmetaage} 세 
                      	<strong>/</strong> 
                      	자녀 : ${hsfarmchildcnt}명
                      	)
                      </td>
                    </tr>
                    
                    <c:choose>
                      		<c:when test="${homestay_info.hsarealiving eq null   || homestay_info.hsarealiving == '' }">
                      			<c:set var="hsarealiving" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsarealiving" value="${homestay_info.hsarealiving}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsarearoom1 eq null   || homestay_info.hsarearoom1 == '' }">
                      			<c:set var="hsarearoom1" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsarearoom1" value="${homestay_info.hsarearoom1}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsarearoom2 eq null   || homestay_info.hsarearoom2 == '' }">
                      			<c:set var="hsarearoom2" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsarearoom2" value="${homestay_info.hsarearoom2}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsarearoom3 eq null   || homestay_info.hsarearoom3 == '' }">
                      			<c:set var="hsarearoom3" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsarearoom3" value="${homestay_info.hsarearoom3}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsareakitchen eq null   || homestay_info.hsareakitchen == '' }">
                      			<c:set var="hsareakitchen" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsareakitchen" value="${homestay_info.hsareakitchen}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsbathcnt eq null   || homestay_info.hsbathcnt == '' }">
                      			<c:set var="hsbathcnt" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsbathcnt" value="${homestay_info.hsbathcnt}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsetc1nm eq null   || homestay_info.hsetc1nm == '' }">
                      			<c:set var="hsetc1nm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsetc1nm" value="${homestay_info.hsetc1nm}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsetc2nm eq null   || homestay_info.hsetc2nm == '' }">
                      			<c:set var="hsetc2nm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsetc2nm" value="${homestay_info.hsetc2nm}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsareaetc1 eq null   || homestay_info.hsareaetc1 == '' }">
                      			<c:set var="hsareaetc1" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsareaetc1" value="${homestay_info.hsareaetc1}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsareaetc2 eq null   || homestay_info.hsareaetc2 == '' }">
                      			<c:set var="hsareaetc2" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsareaetc2" value="${homestay_info.hsareaetc2}" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">주거현황</th>
                      <td colspan="3">
                      	거실 : ${hsarealiving} ㎡
                      	<strong>/</strong> 
                      	방1 : ${hsarearoom1} ㎡
                      	<strong>/</strong> 
                      	방2 : ${hsarearoom2} ㎡
                      	<strong>/</strong> 
                      	방3 : ${hsarearoom3} ㎡
                      	<strong>/</strong> 
                      	부엌 : ${hsareakitchen} ㎡
                      	<strong>/</strong> 
                      	욕실 : ${hsbathcnt} ㎡
                      	<strong>/</strong> 
                      	기타1 : [${hsetc1nm}] : ${hsareaetc1} ㎡
                      	<strong>/</strong> 
                      	기타2 : [${hsetc2nm}] : ${hsareaetc2} ㎡
                      </td>
                    </tr>
                    
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">양식장</th>
                      <td>${homestay_info.hsitemfarm}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">어선</th>
                      <td>${homestay_info.hsitemship}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">가공공장</th>
                      <td>${homestay_info.hsitemfactory}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">염전</th>
                      <td>${homestay_info.hsitemsolt}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">어촌관광</th>
                      <td>${homestay_info.hsitemtraval}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">해양수산레저</th>
                      <td>${homestay_info.hsitemleisure}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">기타</th>
                      <td colspan="3">${homestay_info.hsitemetc}</td>
                    </tr>
                    <%--
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">영어시설</th>
                      <td colspan="3">
							<c:if test="${list_img1 ne null}">	
                      		<table >
                      			<tr>
									<c:forEach var="item_img1" varStatus="status" items="${list_img1}">
                      				<td style="width: 25%; border:0; border-right:1px solid #e4eaec ;">
											<img src="/upload/homestay/${item_img1.filenm}" alt="${item_img1.filenm}" style="width: 80px;" />
											<br>
											${item_img1.filetext}			
									</td>
									</c:forEach>
								</tr>
							</table>
							</c:if>
				 	  </td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">주거시설</th>
                      <td colspan="3">
							<c:if test="${list_img2 ne null}">	
                      		<table >
                      			<tr>
									<c:forEach var="item_img2" varStatus="status" items="${list_img2}">
                      				<td style="width: 25%; border:0; border-right:1px solid #e4eaec ;">
											<img src="/upload/homestay/${item_img2.filenm}" alt="${item_img2.filenm}" style="width: 80px;" />
											<br>
											${item_img2.filetext}			
									</td>
									</c:forEach>
								</tr>
							</table>
							</c:if>
				 	  </td>
                    </tr>
                    --%>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">이미지</th>
                      <td colspan="3">
						<c:if test="${list_imgs ne null}">	
						<c:forEach var="item_img" varStatus="status" items="${list_imgs}">
							<img src="<c:url value='/cmm/fms/getImage.do'/>?atchFileId=<c:out value="${item_img.atchfileid}"/>&fileSn=<c:out value="${item_img.filesn }" />" alt="Image" style="width: 100px;"/></a>			
						</c:forEach>
						</c:if>
					  </td>
                    </tr>
                    
                    <c:choose>
                      		<c:when test="${homestay_info.hsmngnm eq null   || homestay_info.hsmngnm == '' }">
                      			<c:set var="hsmngnm" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsmngnm" value="${homestay_info.hsmngnm}" />
                      		</c:otherwise>
                      </c:choose>
                      <c:choose>
                      		<c:when test="${homestay_info.hsmngtel eq null   || homestay_info.hsmngtel == '' }">
                      			<c:set var="hsmngtel" value="-" />
                      		</c:when>
                      		<c:otherwise>
                      			<c:set var="hsmngtel" value="${homestay_info.hsmngtel}" />
                      		</c:otherwise>
                      </c:choose>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">담당처</th>
                      <td>${hsmngnm}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">담당처 연락처</th>
                      <td>${hsmngtel}</td>
                    </tr>
                    <tr>
                      <th style="font-weight:bold; background:#f0f4f5; ">등록일</th>
                      <td>${homestay_info.hsregdt}</td>
                      <th style="font-weight:bold; background:#f0f4f5; ">마지막수정일</th>
                      <td>${homestay_info.hsmoddt}</td>
                    </tr>
                  </tbody>

				  <tfoot>
				  	<tr class="footable-paging">
				  		<td colspan="4" class="alignCenter">
				  			<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="listact()" >
				        		<i class="site-menu-icon fas fa-bars" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">목록</span>
				        	</button>
				        	&nbsp;
				  			<button class="append-rows btn btn-primary btn-outline" type="button" href="#" onclick="modact(${homestay_info.hssn})" >
				        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">수정</span>
				        	</button>
				        	&nbsp;
				  			<button class="append-rows btn btn-danger btn-outline" type="button" href="#" onclick="delact(${homestay_info.hssn})" >
				        		<i class="site-menu-icon fas fa-minus-square" aria-hidden="true"></i>
				        		<span class="hidden-sm-down">삭제</span>
				        	</button>
				  		</td>
				  	</tr>
				  </tfoot>
				  
				 </table>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
    <!-- End Page -->
    
    <script>
	function listact(obj) {
		location.href="/seadm/homestay/list.do";
	}
	function modact(obj) {
		location.href="/seadm/homestay/modify.do?sn="+obj;
	}
	function delact(obj) {
		if(confirm('정말 삭제하시겠습니까?')) {
			location.href="/seadm/homestay/delAction.do?sn="+obj;		
		}
	}
	</script>

<%@ include file="../tail.jsp" %>

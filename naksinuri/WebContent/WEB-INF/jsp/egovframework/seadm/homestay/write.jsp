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

<form:form commandName="homestayVO" id="listForm" name="listForm" method="post" enctype="multipart/form-data" onsubmit="return frm_submit()" 
	action="/seadm/homestay/write_act.do"> 
	<input type="hidden" name="hshp" id="hshp" value=""/>
	<input type="hidden" name="hsemail" id="hsemail" value=""/>

<!-- Page -->
<div class="page">
	<div class="page-header">
		<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
	</div>
	<div class="page-content container-fluid">	
	<!-- page-content -->
	
		<div class="panel">
			<div class="panel-body">
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >운영자 이름 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsnm" id="hsnm" value="" placeholder="운영자명을 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >생년월일</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsbirth" id="hsbirth" value="" placeholder="생년월일을 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >나이</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsage" id="hsage" value="" placeholder="나이를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >연락처 <span class="red-600">*</span></label>
		           	<div class="col-md-10">
		           		<div class="input-group">
							<select class="form-control selectpicker_manual col-lg-1" data-style="btn-outline btn-primary" name="hs_hp1" id="hs_hp1">
								<option value="010" >010</option>
								<option value="011" >011</option>
								<option value="016" >016</option>
								<option value="018" >018</option>
								<option value="019" >019</option>
							</select>
							<div class="input-group-append">
			           			<span class="input-group-text bg-white border-0">-</span> 
			           		</div>
			           		<input type="text" class="form-control col-lg-1" name="hs_hp2" id="hs_hp2" maxlength="4" value="" placeholder="" autocomplete="off" />
							<div class="input-group-append">
			           			 <span class="input-group-text bg-white border-0">-</span>
			           		</div>
			           		<input type="text" class="form-control col-lg-1" name="hs_hp3" id="hs_hp3" maxlength="4" value="" placeholder="" autocomplete="off" />
						</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >이메일</label>
		           	<div class="col-md-10">
		           		<div class="input-group">
							<input type="text" class="form-control col-lg-2" name="hs_email1" id="hs_email1" value="" placeholder="" autocomplete="off"/>
							<div class="input-group-append">
			           			<span class="input-group-text bg-white border-0">@</span> 
			           		</div>
							<input type="text" class="form-control col-lg-3" name="hs_email2" id="hs_email2" value="" placeholder="" autocomplete="off"/>
							<select class="form-control selectpicker_manual col-lg-3 ml-10" data-style="btn-outline btn-primary" name="hs_email3" id="hs_email3" onchange="emailCheck(this.value);"  >
								<option value="direct">직접입력</option>
								<option value="naver.com" >naver.com</option>
								<option value="daum.net"  >daum.net</option>
								<option value="hanmail.net" >hanmail.net</option>
								<option value="nate.com" >nate.com</option>
								<option value="gmail.com" >gmail.com</option>
								<option value="paran.com" >paran.com</option>
								<option value="chol.com" >chol.com</option>
								<option value="dreamwiz.com" >dreamwiz.com</option>
								<option value="empal.com" >empal.com</option>
								<option value="freechal.com" >freechal.com</option>
								<option value="hanafos.com" >hanafos.com</option>
								<option value="hanmir.com" >hanmir.com</option>
								<option value="hitel.net" >hitel.net</option>
								<option value="hotmail.com" >hotmail.com</option>
								<option value="korea.com" >korea.com</option>
								<option value="lycos.co.kr" >lycos.co.kr</option>
								<option value="netian.com" >netian.com</option>
								<option value="yahoo.co.kr" >yahoo.co.kr</option>
								<option value="yahoo.com" >yahoo.com</option>
							</select>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >주소</label>
		           	<div class="col-md-10">		            	
		            	<div class="form-inline">
					  		<div class="form-group">
							<input type="text" class="form-control alignMiddle readonly" readonly name="hszipcd" id="hszipcd" value="" style="width: 100px;" />
							</div>
							<div class="form-group"> 
							<button type="button" class="btn btn-sm btn-primary" onclick="zipcode('hszipcd','hsaddr1','hsaddr2');" >우편번호 찾기</button>
							</div>
						</div>
						<div id="zipcode_layer" style="display:none;border:1px solid #005287; width:100%;height:300px;margin:5px 0;position:relative">
						<img src="//t1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
						</div>
						<input type="text" class="form-control w70 readonly" readonly  style="margin-top:4px" name="hsaddr1" id="hsaddr1" value="" />
						<input type="text" class="form-control w70" style="margin-top:4px" name="hsaddr2" id="hsaddr2" value="" placeholder="주소를 입력해주세요." autocomplete="off"/>	
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >통장정보</label>
		           	<div class="col-md-10 input-group">
		           		<div class="mr-10">
		            		<input type="text" class="form-control bg-white" name="hsbankbnm" id="hsbankbnm" value="" placeholder="은행명" autocomplete="off">
		            	</div>
		            	<div class="mr-10">
		            		<input type="text" class="form-control bg-white" name="hsbanknum" id="hsbanknum" value="" placeholder="계좌번호" autocomplete="off">
		            	</div>
		            	<div class="mr-10">
		            		<input type="text" class="form-control bg-white" name="hsbankunm" id="hsbankunm" value="" placeholder="예금주" autocomplete="off">
		            	</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >경력</label>
		           	<div class="col-md-10 input-group">
		           		<div class="col-lg-4 p-0 pr-10">
		           			<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">어업경력(년)</span> 
				           		</div>
			            		<input type="text" class="form-control bg-white" name="hscarrerfish" id="hscarrerfish" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
		            	<div class="col-lg-4 p-0 pr-10">
		            		<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">귀어경력(년)</span> 
				           		</div>
		            			<input type="text" class="form-control bg-white" name="hscarrerback" id="hscarrerback" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
		            	<div class="col-lg-4 p-0">
		            		<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">어업인후계자 경력(년)</span> 
				           		</div>
		            			<input type="text" class="form-control bg-white" name="hscarrersuccess" id="hscarrersuccess" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >영어분야(품목)</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitem" id="hsitem" value="" placeholder="영여분야(품목)을 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >소개</label>
		           	<div class="col-md-10">
		            	<textarea class="form-control" rows="10" cols="100" name="hsdes" id="hsdes" placeholder="소개서를 작성해주세요." autocomplete="off"></textarea>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >주거 및 주택형태</label>
		           	<div class="col-md-10 input-group">
		           		<div class="col-lg-4 p-0 mr-10">
		            		<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="hshomestat" id="hshomestat" >
		                      	<c:forEach var="home_stat" varStatus="status" items="${list_hstat}">
								<option value="${home_stat.cdid}" >${home_stat.cdnm}</option>
								</c:forEach>
							</select>
		            	</div>
		            	<div class="col-lg-4 p-0 mr-10">
		            		<select class="form-control selectpicker_manual" data-style="btn-outline btn-primary"  name="hshometype" id="hshometype" >
		                      	<c:forEach var="home_type" varStatus="status" items="${list_htype}" >
								<option value="${home_type.cdid}" >${home_type.cdnm}</option>
								</c:forEach>
							</select>
		            	</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >가족상황</label>
		           	<div class="col-md-10 input-group">
		           		<div class="col-lg-3 p-0 pr-10">
		           			<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">총인원(명)</span> 
				           		</div>
			            		<input type="text" class="form-control bg-white" name="hsfarmtotalcnt" id="hsfarmtotalcnt" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
		            	<div class="col-lg-3 p-0 pr-10">
		            		<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">부모(명)</span> 
				           		</div>
		            			<input type="text" class="form-control bg-white" name="hsfarmparentcnt" id="hsfarmparentcnt" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
		            	<div class="col-lg-3 p-0 pr-10">
		            		<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">배우자(명)</span> 
				           		</div>
		            			<input type="text" class="form-control bg-white" name="hsmetaage" id="hsmetaage" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
		            	<div class="col-lg-3 p-0">
		            		<div class="input-group">
			           			<div class="input-group-append">
				           			<span class="input-group-text">자녀(명)</span> 
				           		</div>
		            			<input type="text" class="form-control bg-white" name="hsfarmchildcnt" id="hsfarmchildcnt" value="" placeholder="" autocomplete="off">
		            		</div>
		            	</div>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >주거현황</label>
		           	<div class="col-md-10">
		           		<div class="input-group">
			           		<div class="col-lg-3 p-0 pr-10">
			           			<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">거실(㎡)</span> 
					           		</div>
				            		<input type="text" class="form-control bg-white" name="hsarealiving" id="hsarealiving" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            	<div class="col-lg-3 p-0 pr-10">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">방1(㎡)</span> 
					           		</div>
			            			<input type="text" class="form-control bg-white" name="hsarearoom1" id="hsarearoom1" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            	<div class="col-lg-3 p-0 pr-10">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">방2(㎡)</span> 
					           		</div>
			            			<input type="text" class="form-control bg-white" name="hsarearoom2" id="hsarearoom2" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            	<div class="col-lg-3 p-0">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">방3(㎡)</span> 
					           		</div>
			            			<input type="text" class="form-control bg-white" name="hsarearoom3" id="hsarearoom3" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
		            	</div>
		            	<div class="input-group mt-10">
			           		<div class="col-lg-3 p-0 pr-10">
			           			<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">부엌(㎡)</span> 
					           		</div>
				            		<input type="text" class="form-control bg-white" name="hsareakitchen" id="hsareakitchen" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            	<div class="col-lg-3 p-0 pr-10">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">욕실(㎡)</span> 
					           		</div>
			            			<input type="text" class="form-control bg-white" name="hsbathcnt" id="hsbathcnt" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            </div>
		            	<div class="input-group mt-10">
			            	<div class="col-lg-6 p-0 pr-10">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">기타1(명칭,㎡)</span> 
					           		</div>
					           		<input type="text" class="form-control bg-white" name="hsetc1nm" id="hsetc1nm" value="" placeholder="명칭" autocomplete="off">
			            			<input type="text" class="form-control bg-white" name="hsareaetc1" id="hsareaetc1" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
			            	<div class="col-lg-6 p-0">
			            		<div class="input-group">
				           			<div class="input-group-append">
					           			<span class="input-group-text">기타2(명칭,㎡)</span> 
					           		</div>
					           		<input type="text" class="form-control bg-white" name="hsetc2nm" id="hsetc2nm" value="" placeholder="명칭" autocomplete="off">
			            			<input type="text" class="form-control bg-white" name="hsareaetc2" id="hsareaetc2" value="" placeholder="㎡" autocomplete="off">
			            		</div>
			            	</div>
		            	</div>
					</div>
				</div>
				
			</div>
		</div>		
		<div class="panel">
			<div class="panel-body">
			
				<div class="form-group row">
					<h4>분야(품목)</h4>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >양식장</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemfarm" id="hsitemfarm" value="" placeholder="양식장 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >어선</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemship" id="hsitemship" value="" placeholder="어선 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >가공공장</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemfactory" id="hsitemfactory" value="" placeholder="가공공장 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >염전</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemsolt" id="hsitemsolt" value="" placeholder="염전 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >어촌관광</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemtraval" id="hsitemtraval" value="" placeholder="어촌관광 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >해양수산레저</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemleisure" id="hsitemleisure" value="" placeholder="해양수산레저 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >기타</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsitemetc" id="hsitemetc" value="" placeholder="기타 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				
			</div>
		</div>		
		<div class="panel">
			<div class="panel-body">
			
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >영어시설 이미지</label>
		           	<div class="col-md-10">
		           		
		           		<c:forEach begin="0" end="3" step="1" varStatus="status">
		           		
		           		<div class="input-group <c:if test="${status.index!=0}">mt-20</c:if>">
		           			<div class="col-lg-6 p-0">
			           			<input type="text" class="form-control bg-white" name="hstext1${status.count}" id="hstext1${status.count}" value="" placeholder="설명" autocomplete="off">
			           		</div>
			           		<div class="col-lg-6 p-0 pl-10 input-group input-group-file" data-plugin="inputGroupFile">
			           			<input type="text" class="form-control" readonly="" value="변경시에만 첨부하세요." placeholder="이미지 1-${status.count}"/>
				     			<span class="input-group-btn">
				       				<span class="btn btn-success btn-file">
				         				<i class="icon wb-upload" aria-hidden="true"></i>
				         				<input type="file" name="file_file1_${status.count}" id="file_file1_${status.count}" multiple="false" value="" />
				       				</span>
				     			</span>
				   			</div>	
					    </div> 
					    
					    </c:forEach>
					    
					</div> 
				</div>   
				<div class="form-group row pt-10">
					<label class="col-md-2 form-control-label" >주거시설 이미지</label>
					<div class="col-md-10">

		           		<c:forEach begin="0" end="3" step="1" varStatus="status">
		           		
		           		<div class="input-group <c:if test="${status.index!=0}">mt-20</c:if>">
		           			<div class="col-lg-6 p-0">
			           			<input type="text" class="form-control bg-white" name="hstext2${status.count}" id="hstext2${status.count}" value="" placeholder="설명" autocomplete="off">
			           		</div>
			           		<div class="col-lg-6 p-0 pl-10 input-group input-group-file" data-plugin="inputGroupFile">
			           			<input type="text" class="form-control" readonly="" value="변경시에만 첨부하세요." placeholder="이미지 2-${status.count}"/>
				     			<span class="input-group-btn">
				       				<span class="btn btn-success btn-file">
				         				<i class="icon wb-upload" aria-hidden="true"></i>
				         				<input type="file" name="file_file2_${status.count}" id="file_file2_${status.count}" multiple="false" value="" />
				       				</span>
				     			</span>
				   			</div>	
					    </div> 
					    
					    </c:forEach>					    
					    
					</div>
				</div>	
				
			</div>
		</div>		
		<div class="panel">
			<div class="panel-body">		
				
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >담당처</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsmngnm" id="hsmngnm" value="" placeholder="담당처 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>
				<div class="form-group row">
					<label class="col-md-2 form-control-label" >담당처 연락처</label>
		           	<div class="col-md-10">
		            	<input type="text" class="form-control bg-white" name="hsmngtel" id="hsmngtel" value="" placeholder="담당처 연락처 정보를 입력해주세요." autocomplete="off">
					</div>
				</div>				
				<div class="text-center">
		  			<button class="append-rows btn btn-primary btn-outline" type="submit" >
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">등록</span>
		        	</button>
		        	&nbsp;
		  			<button class="append-rows btn btn-warning btn-outline" type="button" href="#" onclick="history.back();" >
		        		<i class="site-menu-icon fas fa-plus-square" aria-hidden="true"></i>
		        		<span class="hidden-sm-down">취소</span>
		        	</button>
		        </div>
			
			</div>
		</div>
		
	<!-- End page-content -->		
	</div>
</div>

</form:form>

<script>
$(function(){
	$('.selectpicker_manual').selectpicker();
});
function emailCheck(obj) {
	if(obj == "direct") {
		$('#hs_email2').css('readony',false).removeClass('readonly').val('');
	} else {
		$('#hs_email2').css('readony',true).addClass('readonly').val(obj);
	}
}
function frm_submit() {

	if(!$.trim($('#hsnm').val())) { 
		alert("운영자 이름을 입력하여 주세요."); 	
		$('#hsnm').focus(); 
		return false; 
	}

	var hp2 = $.trim($('#hs_hp2').val());
	if(!hp2 || hp2.length < 4) {
		alert('핸드폰 국번을 제대로 입력해 주세요.');
		$('#hs_hp2').focus();
		return false;
	}
	var hp3 = $.trim($('#hs_hp3').val());
	if(!hp3 || hp3.length < 4) {
		alert('핸드폰 국번을 제대로 입력해 주세요.')
		$('#hs_hp3').focus();
		return false;
	}
	$('#hshp').val($('#hs_hp1').val()+'-'+hp2+'-'+hp3);
	
	
	var email1= $.trim($('#hs_email1').val());
	/*
	if(!email1 || email1.length < 4) {
		alert('이메일 아이디를 제대로 입력해 주세요.')
		$('#hs_email1').focus();
		return false;
	}
	*/
	var email2= $.trim($('#hs_email2').val());
	/*
	if(!email2 || email2.length < 4) {
		alert('이메일업체를 제대로 입력해 주세요.')
		$('#hs_email2').focus();
		return false;
	}
	*/
	$('#hsemail').val(email1+'@'+email2);

	$("#frm").submit();
	return true;
	
}	
</script>

<%@ include file="../tail.jsp" %>

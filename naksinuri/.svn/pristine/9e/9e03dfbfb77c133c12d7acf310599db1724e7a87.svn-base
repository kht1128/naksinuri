<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<%@include file="../../../naksinuri_original/naksinuri/layout/m/head.sub.jsp"%>

<style>
#certificateArea{width:500px;height:1100px;display:none;}
</style>

<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
	<input type="hidden" name="sv_id" id="sv_id" value="${info.sv_id}"/>
	<%-- <input type="hidden" id="strt_dt" value="${info.sv_strt_dt}"/>
	<input type="hidden" id="end_dt" value="${info.sv_end_dt}"/> --%>
	<input type="hidden" id="as_tel" name="as_tel" />
	<input type="hidden" id="as_email" name="as_email" />
	<input type="hidden" id="sv_private_info" name="sv_private_info" value="${info.sv_private_info}"/>
	<input type="hidden" name="CRS_SN" value="${myHistoryVO.CRS_SN}"/>
	<input type="hidden" name="HMBR_SN" value="${myHistoryVO.HMBR_SN}"/>
	<input type="hidden" name="key" value="${key}"/>
	<c:if test="${info.sv_private_info eq 1}">	
		<div id="customerSound" class="content respon3">
			<section id="writeForm" class="write_box">
				<div class="agree_box">
					<h3>개인정보 수집 및 활용에 관한 동의</h3>
					<div class="agree_text">
						1. 목적 : 제안(민원)인의 신원 확인, 제안(민원)사항 확인, 사실조사를 위한 연락·통지, 처리결과 통보, 사이트 회원관리 등<br />
						2. 수집항목 : 이름, 휴대폰번호, 전자우편<br />
						3. 개인정보 보유·이용기간 : 동의일로부터 동의철회(직권해지)시 까지<br />
						4. 거부권 및 불이익 : 낚시누리 사이트의 개인정보 취급방침에 대한 동의를 거부할 권리가 있습니다.<br /><br />
						동의를 거부할 경우 서비스의 이용에 제한이 있을 수 있습니다.
					</div><br/>
					<div class="agree_yesorno">
						개인정보 수집 및 활용에 대하여 
						<input type="radio" id="agreeingy" name="agreeing" value="동의"/><label for="agreeingy"><span></span>동의합니다.</label>
						<input type="radio" id="agreeingn" name="agreeing" value="비동의"/><label for="agreeingn"><span></span>동의하지 않습니다.</label>                                                                                       
					</div>
				</div>
				<dl>
					<dt>이름</dt>
					<dd><input type="text" id="as_name" name="as_name"/></dd>
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd><input type="text" size="30" class="write_input" placeholder="이메일을 입력하세요" id="as_email1">@
					<select id="email_addr" class="write_input" style="width:200px;" onchange="chgaddr();" >					
						<option value="naver.com">naver.com</option>
						<option value="daum.net">daum.net</option>
						<option value="nate.com">nate.com</option>
						<option value="yahoo.com">yahoo.com</option>
						<option value="gmail.com">gmail.com</option>	
						<option value="paran.com">paran.com</option>
						<option value="hanmail.net">hanmail.net</option>
						<option id="self" value="1">직접 입력하기</option>
					</select>
				
					<input type="text" style="display:none" size="30" class="write_input" placeholder="이메일을 입력하세요"  id="bo_email2">
					</dd>
				</dl>
				<dl>
					<dt>연락처</dt>
					<dd>
						<select class="naksi_select" id="as_tel1">
							<option>010</option>
							<option>011</option>
							<option>012</option>
							<option>014</option>
							<option>017</option>
							<option>018</option>
							<option>019</option>
							<option>070</option>
						</select> - 
						<input type="text" class="write_input" size="7"  maxlength="4"  id="as_tel2"/> - 
						<input type="text" class="write_input" size="7"  maxlength="4"  id="as_tel3"/>
					</dd>
				</dl>
			</section>
		</div>
	</c:if>
	<div id="survey" class="content respon2">
		<section id="suveyPage">
			<div class="tit">
				<h1>${info.sv_subject }</h1>
				<%-- <span class="date">설문기간 : <b>${info.sv_strt_dt } ~ ${info.sv_end_dt }</b></span> --%>
			</div>
			<div class="suveybox">
				<c:forEach var="item" items="${quest}" varStatus="status">
					<dl id="div_sv_qst${item.sq_id}" class="div_sv_qst">
						<dt><em><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span>${item.svq_subject} <c:if test="${item.svq_mxcnt gt 1 }"><span class="jungbok" style="display:inline;font-size:14px;color:#666">(${item.svq_mxcnt}개 이하)</span></c:if></span></dt>
						<dd>
							<input type="hidden" id="anscount${status.count}" name="anscount" value="${status.count}"/>
							<input type="hidden" id="chkcount" name="chkcount"/>
							<input type="hidden" name="sq_id" id="sq_id${status.count}" value="${item.sq_id}"/>
							<input type="hidden" name="svq_type" value="${item.svq_type}"/>
							<input type="hidden" name="mxcnt" class="svq_mxcnt" value="${item.svq_mxcnt }"/>
							<input type="hidden" class="svq_type" value="${item.svq_type }"/>
							<input type="hidden" name="sq_num${status.count}" value="${item.sq_id}"/>
							
							<c:if test="${item.svq_type eq 'S' }">
								<textarea class="survey_input svq_text" name="sva_txt${status.count}"></textarea>
							</c:if>
							<c:if test="${item.svq_type eq 'O' }">
								<ul>
									
									<c:forEach var="item2" items="${qitem}" varStatus="status2">
										<c:if test="${item2.sq_id eq item.sq_id }">
											<li>
												<input type="hidden" name="sqi_count" value="${status2.count }"/>
												<c:choose>
													<c:when test="${item.svq_mxcnt gt 1}">
														<c:choose>
															<c:when test="${item2.sqi_etc eq 0 }">
																<c:set var="onclickEtc" value="onclick='ViewEtc(this)'"/>
															</c:when>
															<c:otherwise>
																<c:set var="onclickEtc" value=""/>
															</c:otherwise>
														</c:choose>
														<input type="checkbox" class="svq_checkbox" id="sqi_id${status2.count }" name="sqi_id${status.count }" value="${item2.sqi_id }" ${onclickEtc}/><label for="sqi_id${status2.count }"><span></span>${item2.svq_item_txt }</label>
														<div  class="etcSection" style='display:none;padding-top:5px;'>
															<textarea class="survey_input svq_text" name="sva_etc${status.count}_${item2.svq_item_num}" cols="100" rows="4"></textarea>
														</div>
													</c:when>
													<c:otherwise>
														<c:choose>
															<c:when test="${item2.sqi_etc eq 0 }">
																<c:set var="onclickEtc" value="onclick='ViewEtc(this)'"/>
															</c:when>
															<c:otherwise>
																<c:set var="onclickEtc" value="onclick='hideEtc(this)'"/>
															</c:otherwise>
														</c:choose>												
														<input type="radio" class="svq_radio" id="sqi_id${status2.count }" name="sqi_id${status.count }" value="${item2.sqi_id }" ${onclickEtc}/><label for="sqi_id${status2.count }"><span></span>${item2.svq_item_txt }</label>
														<div  class="etcSection" style='display:none;padding-top:5px;'>
															<textarea class="survey_input svq_text" name="sva_etc${status.count}" cols="100" rows="4"></textarea>
														</div>											
													</c:otherwise>
												</c:choose>
											</li>
										</c:if>
									</c:forEach>
								</ul>
							</c:if>							
						</dd>
					</dl>
				</c:forEach>				
				<div id="btnArea">
					<a href="#;" class="btn_blue" onclick="submitContents('등록되었습니다!');">설문조사 완료</a>
				</div>
			</div>
		</section>
	</div>
</form:form>
<div id="certificateArea">
	<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
	${html_header}
		${item.CRTF_HTML_DATA}
	${html_footer}
</div>
<script type="text/javascript">
	var x = document.getElementById("bo_email2");
	$.fn.strech_text = function(){
		var elmt          = $(this),
			cont_width    = elmt.width(),
			txt           = elmt.html(),
			one_line      = $('<span class="stretch_it">' + txt + '</span>'),
			nb_char       = elmt.text().length,
			spacing       = cont_width/nb_char,
			txt_width;
	
		elmt.html(one_line);
		txt_width = one_line.width();
	
		if (txt_width < cont_width){
			var  char_width     = txt_width/nb_char,
				 ltr_spacing    = spacing - char_width + (spacing - char_width)/nb_char ;
			if(elmt.hasClass('str_name')){
				if(txt.length == 3){
					one_line.css({'letter-spacing': 105});
				}else{
					one_line.css({'letter-spacing': 20});
				}
			}else{
				if(txt.length >= 15)
					one_line.css({'letter-spacing': 3});
				else
					one_line.css({'letter-spacing': 18});
			}
		} else {
			one_line.contents().unwrap();
			elmt.addClass('justify');
		}
	};
	$(document).ready(function(){
		$('.stretch').each(function(){
			$(this).strech_text();
		});
	});
	function chgaddr(){
		
		if(imform.email_addr.value=='1'){ // 직접 입력
			x.style.display = "inline";
		
		}else{ // 직접입력아닌경우
			x.style.display="none";
		
		}
	}
	
	function submitContents() {
		if($('#sv_private_info').val()==1){
			if($("input[type=radio][name=agreeing]:checked").val() == "비동의"){
				alert("개인정보 수집 및 활동 동의가 필요합니다.");
				return false;
			}
			
			var email1 =  jQuery("#as_email1").val();
			var regex = /^([\w-]+(?:\.[\w-]+)*)/;
	
			if(regex.test(email1) === false){
				alert("잘못된 이메일 형식입니다.");
				return false;
			}
	
			var phone_check = jQuery("#as_tel2").val();
			var phone_check1 = jQuery("#as_tel3").val();
	
			var regex3 = /\d{3,4}/;
			var regex4 = /\d{4}$/;
	
			if(regex3.test(phone_check) == false){
				alert("연락처는 숫자만 입력할 수 있습니다.");
				return false;
			}
	
			if(regex4.test(phone_check1) == false){
				alert("연락처는 숫자만 입력할 수 있습니다.")
				return false;
			}
		}
		var xy = document.getElementById("bo_email2");
		if($('#email_addr').val()=="1"){
			addr_value = $('#self').val();
			addr_value ="";
			addr_value=xy.value;
			$('#self').val(addr_value);
		}
		
		var phone1 = $("#as_tel1").val();
		var phone2 = $("#as_tel2").val();
		var phone3 = $("#as_tel3").val();
		var phone = phone1 + "-" + phone2 + "-" + phone3;
		$("#as_tel").val(phone);
		var email2 =  jQuery("#email_addr").val();
		var email = email1 + "@" + email2;
		$("#as_email").val(email);
		
		
		/* var today=new Date();
		var enddt=$('#end_dt').val().split("-");
		var strtdt=$("#strt_dt").val().split("-");
		
		var dateObj = new Date(enddt[0], Number(enddt[1])-1, enddt[2]);
		var strtObj = new Date(strtdt[0], Number(strtdt[1])-1, strtdt[2]);
		
		dateObj.setDate(dateObj.getDate()+1);

		if(dateObj<today){
			alert("마감된 설문입니다.");
			return false;			
		}else if(strtObj>today){
			alert("시작되지 않은 설문입니다.");
			return false;
		} */
		
		var anscount = new Array(); 
		anscount = $('#anscount').val();
		var qSize=$(".div_sv_qst").size();
		var qDivInfo;
		var q_mxcnt=0;
		var q_type="";
		for(var qIndex=0;qIndex<qSize;qIndex++){
			qDivInfo = $(".div_sv_qst").eq(qIndex);
			q_type = qDivInfo.find(".svq_type").val();
			q_mxcnt = parseInt(qDivInfo.find(".svq_mxcnt").val());
			
			if(q_type=="S"){
				
				if($.trim(qDivInfo.find(".svq_text").val())==""){
					alert("[설문"+(qIndex+1)+"] 항목을 작성해주세요.");
					qDivInfo.find(".svq_text").focus();
					return false;
				}
				
			}else if(q_type=="O"){
				if(q_mxcnt>1){
					if(radioCk(qDivInfo.find(".svq_checkbox"))<1){						
						alert("[설문"+(qIndex+1)+"] 항목을 선택해주세요.");
						qDivInfo.find(".svq_checkbox").eq(0).focus();
						return false;					
					}
					if(radioCk(qDivInfo.find(".svq_checkbox"))>q_mxcnt){						
						alert("[설문"+(qIndex+1)+"] 항목은 최대 "+q_mxcnt+"개 까지 선택이 가능합니다.");
						qDivInfo.find(".svq_checkbox").eq(0).focus();
						return false;			
					}

				}//체크박스 체크
				else{
					if(radioCk(qDivInfo.find(".svq_radio"))<1){
						alert("[설문"+(qIndex+1)+"] 항목을 선택해주세요.");		
						qDivInfo.find(".svq_radio").eq(0).focus();
						return false;					
					}
					
				} //라디오 박스 체크
			}
			
			//초기화
			q_type="";
			q_mxcnt=0;
			qDivInfo=null;
		}
		
		var checked=($("input:checkbox:checked").length)-1;
		$('#chkcount').val(checked);		
		
		var target = $('#certificateArea');
		var mbrId = '${myHistoryVO.MBR_ID}';
		var certificateSn = '${item.CRTF_SN}';
		$("#certificateArea").css("display", "block");
		if(target != null && target.length > 0) {
			var t = target[0];
			html2canvas(t).then(function(canvas) {
				var myImg = canvas.toDataURL("image/png");
				myImg = myImg.replace("data:image/png;base64,", "");
	
				$.ajax({
					type : "POST",
					data : {
						"imgSrc" : myImg,
						"certificateSn" : certificateSn,
						"mbrId" : mbrId
					},
					dataType : "text",
					url: "/imageCreate.do",
					beforeSend: function() {
						console.log('beforeSend!');
					},
					success: function(data, status, xhr) {	
						console.log('success!');
						console.log(data);
					},
					complete : function() {
						console.log('complete!');
						$("#certificateArea").css("display", "none");
						
						$("#imform").attr("action","/survey/survey/answer.do");
						$("#imform").submit(); 
					},
			        error: function(jqXHR, textStatus, errorThrown) {
						console.log('error!');
			 			console.log(jqXHR);
						console.log(textStatus);
						console.log(errorThrown); 
					}
				});
			});
		}
	}
	
	function radioCk(obj){
		var ch=0;
		for(var rIndex=0;rIndex<obj.size();rIndex++){
			if(obj.eq(rIndex).is(':checked')){
				ch++;
			}		
		}
		return ch;	
	}//라디오 체크박스 체크 갯수

	function ViewEtc(obj){
		if($(obj).is(':checked')){
			$(obj).parent().find(".etcSection").show();
		}	else{
			$(obj).parent().find(".etcSection").hide();
			$(obj).parent().find(".svq_text").val('');
		}
	}
	function hideEtc(obj){
		$(obj).parent().parent().parent().find(".etcSection").hide();
		$(obj).parent().parent().parent().find(".svq_text").val('');
	}
</script>

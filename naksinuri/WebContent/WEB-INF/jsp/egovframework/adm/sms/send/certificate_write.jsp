<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn"     uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.modal-dialog{
/*
    position: relative;
    display: table; 
    overflow-y: auto;    
    overflow-x: auto;
    width: auto;
    min-width: 1000px;
    */   
}
.hidden {display: none;}
#certificateArea{width:500px;height:1100px;display:none;}
</style>

<script src="https://html2canvas.hertzen.com/dist/html2canvas.min.js"></script>
<div class="modal-dialog modal-simple">
<form id="modal_action_form" name="modal_action_form" class="modal-content form-horizontal" method="post" autocomplete="off">
	<div class="modal-header">
 			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
   			<span aria-hidden="true">×</span>
 			</button>
 			<h4 class="modal-title">메세지 보내기</h4>
	</div>
	<div class="modal-body">
		<div class="form-group row">
           	<div class="col-md-12">
           		<h6>발신 번호</h6>
           		<input type="text" class="form-control disabled" placeholder="" autocomplete="off" value="${SEND_NUMBER}" disabled>
			</div>
		</div>
		<div class="form-group row">
           	<div class="col-md-12">
           		<h6>메세지 내용 <span class="red-600">*</span></h6>
				${item.CRTF_HTML_DATA}
			</div>
		</div>	
		<%-- <div class="form-group row">
			<div class="col-md-12">
				<h6>받으실 분들(수신 대상자 목록) <span class="font-weight-800 cyan-600" id="label_mbr_cnt"><c:if test="${not empty list_mbr}">총 ${fn:length(list_mbr)} 명</c:if></span></h6>
     			<c:forEach var="item" items="${list_mbr}">
	     			<script>
	     			{
						var rawObj = {
							value : '${item.MBR_ID}',
							label : '${item.MBR_HP} (${item.MBR_NM})',
						}
						rawMbrdata.push(rawObj);
	     			}
					</script>
				</c:forEach>
     			<input type="text" class="form-control tokenfield2_manual" name="chkedMbrIds" placeholder="" autocomplete="off" value="" >   
     			<input type="hidden" id="receive_mbr_cnt" value="${fn:length(list_mbr)}">   
			</div>
		</div> --%>
		<div class="float-right">
        	<button type="button" class="btn btn-primary btn-outline" onclick="sreenShot($('#certificateArea'));">보내기</button>
            <button type="button" class="btn btn-default btn-outline" data-dismiss="modal">취소(닫기)</button>
        </div>
  	</div>
</form>
</div>
<div id="certificateArea">
	${html_header}
	${item.CRTF_HTML_DATA}
</div>
<script>
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
function sreenShot(target) {
	var certificateSn = '${item.CRTF_SN}';
	var mbrId = '${item.MBR_ID}';
	$("#certificateArea").css("display", "block");
	if(target != null && target.length > 0) {
		var t = target[0];
		html2canvas(t).then(function(canvas) {
			//canvas.width = 200;
			//canvas.height = 372;
			console.log(" ::::: " + canvas.width + " ::::: " + canvas.height);
			var myImg = canvas.toDataURL("image/png");
			myImg = myImg.replace("data:image/png;base64,", "");

			$.ajax({
				type : "POST",
				data : {
					"imgSrc" : myImg,
					"certificateSn" : certificateSn,
					"mbrId" : mbrId
				},
				dataType : "json",
				url: "/imageCreate.do",
				beforeSend: function() {
					console.log('beforeSend!');
				},
				success: function(data, status, xhr) {	
					console.log('success!');
					$("#certificateArea").css("display", "none");
					if(data.error == '1') {
						alertify.alert(data.msg);
					} else {
						alertify.alert(data.msg,function(){
							$("#admPublicModal").modal('hide');
							//document.listForm.submit();	
						});
					}
				},
				complete : function() {
					console.log('complete!');
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
</script>
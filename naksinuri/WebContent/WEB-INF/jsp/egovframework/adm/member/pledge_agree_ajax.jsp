<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
  
<style>
	@media (min-width: 480px){
		.modal-dialog {max-width: 1000px;}
	}
	.modal-dialog {text-align: center;}
	.modal-body {padding:20px 40px 30px 40px;}
</style>

<form commandName="eduMemberVO" id="afterPledgeAgreeForm" name="afterPledgeAgreeForm" method="post">
	<input type="hidden" name="MBR_PLEDGE_ST">
</form>  

<div class="page-content modal-dialog animation-slide-top animation-duration-1">
   	<div class="modal-content form-horizontal">
	<%-- <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title">${TITLE}</h4>
		</div> --%>
       	<div class="modal-body">
       		<div class="brand">
          		<h2 class="brand-text font-weight-800">개인정보 취급자 개인정보 보호 서약서</h2>
				<p class="font-weight-800 mgt20px">본인은 <span class="text-underline">낚시전문교육 교육대상자 명단</span>의 개인정보취급자로서 개인정보 보호를 위하여 다음사항을 준수할 것을 엄숙히 서약합니다.</p>
       		</div>
			<div class="form-group form-material floating">
              	<p class="text-left">1. 업무상 알게 된 개인정보를 허가 없이 제3자에게 제공하거나 수집 목적 외로 이용하지 않는다.</p>
              	<p class="text-left">2. 개인정보 관련 업무 중 알게 된 개인정보와 관련된 내용이 직무상 보호할 대상임을 인정한다.</p>
              	<p class="text-left">3. 개인정보를 누설함이 정보주체의 권리와 이익에 위해가 될 수 있음을 인식하여 업무수행 중 지득한 개인정보를 일체 누설하거나 공개하지 아니한다.</p>
              	<p class="text-left">4. 본인에게 할당된 사용자 ID, 패스워드, 개인정보 처리시스템을 타인과 공동 사용하거나 관련정보를 누설하지 아니한다.</p>
              	<p class="text-left">5. 명백히 허가 받지 않은 정보에 접근하지 않는다.</p>
              	<p class="text-left">6. 업무와 관련한 개인정보의 수집, 생성, 기록, 저장, 보유, 가공, 편집, 검색, 출력, 정정, 복구, 이용, 제공, 공개, 파기 및 그 밖에 이와 유사한 일체의 행위에 대하여 공단의 규정과 통제절차를 준수한다.</p>
              	<p class="text-left">7. 인터넷 홈페이지, P2P, 공유설정, 공개된 무선망 등을 통해 개인정보가 노출되거나 유출되지 않도록 PC, 모바일 기기 등에 보호조치를 취한다.</p>
              	<p class="text-left">8. 모바일 기기를 통해 개인정보를 처리하는 경우 분실·도난 등으로 개인정보가 유출되지 않도록 해당 모바일 기기에 비밀번호 설정 등의 보호조치를 취한다.</p>
              	<p class="text-left">9. 전보 및 퇴직으로 업무를 담당하지 않게 될 경우 즉시 공단에 알려 권한에 대한 삭제 요청을 할 것이며, 전보 및 퇴직 후에도 업무상 알게 된 모든 개인정보에 대하여는 일체 누설하지 아니한다.</p>
			</div>
			
			<p class="">상기 사항을 숙지하고 이를 성실히 준수할 것을 동의하며 서약서의 보안사항을 위반하였을 경우에는<br>
			<span class="font-weight-800"> “개인정보 보호법”, “정보통신망이용촉진 및 정보보호 등에 관한 법률”</span> 등 관련법령에 의한 민/형사상의 책임 이외에도,<br> 
			 공단 관련 규정에 따라 어떠한 불이익도 감수할 것이며 공단에 끼친 손해에 대해 지체 없이 변상/복구할 것을 서약합니다.</p>
			
			<div class="form-group form-material floating">
				<label class="floating-label">(필수) 개인정보 취급자 개인정보 보호 서약서에</label>
				<div class="col-lg-12 text-right">
					<div class="radio-custom radio-default radio-inline ">
						<input type="radio" class="clk-sel-position" id="approval1Y" name="pledge" value="Y" required>
	  					<label for="approval1Y">동의함</label>
					</div>
					<div class="radio-custom radio-default radio-inline ">
						<input type="radio" class="clk-sel-position" id="approval1N" name="pledge" value="N" >
	  					<label for="approval1N">동의하지않음</label>
					</div>
				</div>
			</div>
           	<button type="button" class="btn btn-primary btn-block btn-lg mt-30" id="btn_agree_submit">확인</button>
		</div>
	</div>
	
</div>
<!-- End Page -->
	
<script>
$("#btn_agree_submit").click(function() {
	var pledge = $(':radio[name="pledge"]:checked').val();
	if(!pledge || pledge == 'N'){
		alert('(필수)개인정보 취급자 개인정보 보호 서약서에 동의해야 합니다.');
		return;
	} 
	
	var form = document.getElementById('afterPledgeAgreeForm');
	form.action = "/adm/member/pledge_agree_ajax_act.do";
	form.MBR_PLEDGE_ST.value = pledge;
	
	$.ajax({
        type:"POST",
        url :"/adm/member/pledge_agree_ajax_act.do",
        data:$('#afterPledgeAgreeForm').serialize(),
        dataType: "json", //'html',
        contentType: "application/x-www-form-urlencoded;charset=UTF-8", //"application/json;charset=UTF-8",
        async: true,
        beforeSend: function( xhr ) {
            //xhr.overrideMimeType( "text/plain; charset=x-user-defined" );
        	$(this).attr('disabled', true);
          }
     })
     .done(function(data, status, xhr) {
       	if(data.error == '1'){
			alertify.alert(data.msg);
    	   	$("#admPublicModal").modal('hide');
       	} else {
			alertify.alert(data.msg);
       	}
     })
     .always(function() {
        //console.log('complete!');
      })
     .fail(function(jqXHR, textStatus, errorThrown) {
        //console.log('error!');
     });
	
	
});
</script>

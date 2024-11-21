function formatterCustomMultiple(objdata) {
	if(objdata!=null) {
		for(var r=0; r<objdata.length; r++) {
			formatterCustomProcess(objdata[r]);
		}
	}
}
function formatterCustomProcess(obj) {
	$(obj.target).keyup(function(e){
		var prevcnt = Number($(this).attr('data-pattern-cnt'));
		var prevtext = $(this).val();
		var cnt = prevtext.replace(/-/g,"").length;
		if(prevcnt == cnt) cnt+=1;
		if(obj.over_pattern!=null && obj.over_pattern.length != null && obj.over_pattern.length >= 1) {//over_pattern 이 n개 이상
			var over_default_length = prev_over_length = obj.default_pattern[1];
			var isDefaultPattern = true;
			var str_pattern = "";
			var isAddText = false;
			for(var i=0; i<obj.over_pattern.length; i++) {
				var over_max_length = obj.over_pattern[i][1];
//console.log("cnt:"+cnt+"(prevcnt:"+prevcnt+"),over_default_length:"+over_default_length+",prev_over_length"+prev_over_length+",over_max_length:"+over_max_length);
				if(cnt > over_default_length && cnt > prev_over_length && cnt <= over_max_length) {
					if(cnt > prev_over_length && (cnt-prev_over_length)<2 ) isAddText = true;
					str_pattern = obj.over_pattern[i][0];
					isDefaultPattern = false;    		
				}
				if(cnt > over_default_length && cnt >= over_max_length) {
					str_pattern = obj.over_pattern[i][0];
					isDefaultPattern = false;
				}
				prev_over_length = over_max_length;
			}
			if(isDefaultPattern) {
				obj.target.formatter({'pattern':'','persistent':false}).resetPattern(obj.default_pattern[0]);
			} else {
				if(isAddText && e.key!=null) $(this).val(prevtext+(e.key.replace(/[^0-9]/g,"")));
    			obj.target.formatter({'pattern':'','persistent':false}).resetPattern(str_pattern);
			}
		} else {
//console.log("cnt:"+cnt+"(prevcnt:"+prevcnt+")");
			/*
			if(cnt > obj.over_length[0]) { 
    			$(this).val(prevtext+(e.key.replace(/[^0-9]/g,"")));
    			obj.target.formatter({'pattern':'','persistent':false}).resetPattern(obj.over_pattern[0]);
    		} else {
    			obj.target.formatter({'pattern':'','persistent':false}).resetPattern(obj.default_pattern);
    		}
			*/
			if(cnt >= obj.default_pattern[1] || prevcnt==0) {
				obj.target.formatter({'pattern':'','persistent':false}).resetPattern(obj.default_pattern[0]);
			}
		}
		if(prevtext=='') cnt = 0;
		$(this).attr('data-pattern-cnt',cnt);
	});
	if(obj.automatch) {
		obj.target.each(function (index, item) {
    		$(this).trigger("keyup",{keyCode:32});	
    	});
	}
}
//로딩중 알림 처리
var ajaxLoadingToastNotification = null;
function ajaxLoadingToastAppend() {
	ajaxLoadingToastNotification = toastr.warning("...","",{
			icon:true,
			tapToDismiss: false,
			closeButton: false,
			debug: false,
			newestOnTop: true,
			progressBar: false,
			positionClass: "toast-bottom-center",
			preventDuplicates: false,
			onclick: null,
			timeOut: "0",
			extendedTimeOut: "0",
			showDuration: "300",
			showMethod: "slideDown",
		  	hideDuration: "300",
		  	hideMethod: "fadeOut",
			onShown:function(){
				$(this).removeClass('toast-warning').addClass('bg-orange-600 font-size-12').css('color','#fff')
					   .find('.toast-message').eq(0).html('현재 처리중 입니다. <div class="loader vertical-align-middle loader-circle font-size-12 "></div><br>화면을 닫지 마시고 잠시만 기다려주세요.')
					   .find('.loader-circle').eq(0).css('border-top-color','#eb6709 !important')
													.css('border-bottom-color','#fff !important')
													.css('border-left-color','#fff !important')
													.css('border-right-color','#fff !important');
			},
			onHidden:function(){
				
			},
			onclick:function(){
				
			},
			onCloseClick:function(){
				
			}
		});
	
}
function ajaxLoadingToastRemoved() {
	if(ajaxLoadingToastNotification!=null) {
		ajaxLoadingToastNotification = null;
		toastr.clear();
	}
}
//End of 로딩중 알림 처리
//중복 실행 방지 처리
var isClickActionLoading = false;
function isClickRequestLocked() {
	if(isClickActionLoading) {
		return true;
	}
	clickRequestLockStart();
	return false;
}
function clickRequestLockStart() {
	isClickActionLoading = true;
}
function clickRequestLockStop() {
	isClickActionLoading = false;
}
//End of 중복 실행 방지 처리
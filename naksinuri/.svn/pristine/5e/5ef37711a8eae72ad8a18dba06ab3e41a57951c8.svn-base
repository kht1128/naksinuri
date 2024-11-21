<%@ page contentType="text/html; charset=utf-8" %>
<!doctype html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>낚시누리</title>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/admincommon/css/simple-line-icons.css" />
	<link rel="stylesheet" type="text/css" href="/naksinuri_original/admincommon/css/admin_common.css" />
	
	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<script src="https://use.fontawesome.com/0973aba921.js"></script>
	<script src="/naksinuri_original/admincommon/js/common.js"></script>
	<script type="text/javascript" src="<c:url value='/naksinuri_original/js/EgovMultiFile.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/naksinuri_original/js/EgovCalPopup.js'/>" ></script>
	<script type="text/javascript" src="<c:url value='/naksinuri_original/js/datepicker_KR.js'/>" ></script>
	
	<!--다음 주소 -->
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e883057cd4b2ba36c2a3d191735a3e8&libraries=services"></script>
	<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
	<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js?autoload=false"></script>
	<!--다음 끝 -->
	<script type="text/javascript" src="/naksinuri_original/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
	<!-- //별도로 사용하는곳이 없는것으로 판단 
	<script type="text/javascript" src="/naksinuri_original/se2/photo_uploader/plugin/hp_SE2M_AttachQuickPhoto.js" charset="utf-8"></script>
 	-->
 	
 	<!-- jQuery Modal -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
 	
 	<!--부트스트랩
 	<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
 	
	<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
	-->
	
	<script>
	//관리자 일정시간 미사용시 차단 기능
	var masterOverTimeSec = 3600;//최초 설정 시간(기본 : 초) : 3600 = 1시간 
	function chkMasterOverTimeLock() {	// 1초씩 카운트
		var m = Math.floor(masterOverTimeSec / 60) + "분 " + (masterOverTimeSec % 60) + "초";	// 남은 시간 계산
		//console.log("남은시간 : " + m);
		masterOverTimeSec--;
		if(masterOverTimeSec < 0) {
			clearInterval(masterOverTimeId);
			alert("장시간 미사용으로 접근이 차단되었습니다.\n다시 로그인 하셔야 사용 할 수 있습니다.");
			location.href='/adm/lock/memberLogout.do';
		}
	}
	window.onload = function TimerStart(){ masterOverTimeId=setInterval('chkMasterOverTimeLock()',1000) };
	//
	</script>

</head>
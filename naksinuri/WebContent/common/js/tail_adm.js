//.js? 파라미터 추출
function getJsFileParameters() {
    var script = document.getElementsByTagName('script');   
    script = script[script.length-1].src  
       .replace(/^[^\?]+\?/, '')           
       .replace(/#.+$/, '')                      
       .split('&');                                   
     var queries = {}                             
       , query;
     while(script.length){                      
          query = script.shift().split('=');    
          queries[query[0]] = query[1];   
     }
     return queries;
}
//End of .js? 파라미터 추출
//관리자 일정시간 미사용시 차단 기능
var masterOverTimeSec = 36000;//최초 설정 시간(기본 : 초) : 3600 = 1시간, 2021-02-03 10시간으로 변경
var jsFileParameters = getJsFileParameters();
if(jsFileParameters==null || jsFileParameters.time == null || typeof jsFileParameters.time == "undefined") {
	
} else {
	masterOverTimeSec = jsFileParameters.time;
}
function chkMasterOverTimeLock() {	// 1초씩 카운트
	var m = Math.floor(masterOverTimeSec / 60) + "분 " + (masterOverTimeSec % 60) + "초";	// 남은 시간 계산
	//console.log("남은시간 : " + m);
	masterOverTimeSec--;
	if(masterOverTimeSec < 0) {
		clearInterval(masterOverTimeId);
		/* 새로고침이 가능한 문제가 있음
		alertify.alert("장시간 미사용으로 접근이 차단되었습니다.<br>다시 로그인 하셔야 사용 할 수 있습니다.",function(){
			location.href='/adm/lock/memberLogout.do';
		});
		*/		
		alert("장시간 미사용으로 접근이 차단되었습니다.\n다시 로그인 하셔야 사용 할 수 있습니다.");
		location.href='/adm/lock/memberLogout.do';		
	}
}
window.onload = function TimerStart(){ masterOverTimeId=setInterval('chkMasterOverTimeLock()',1000) };
//End of 관리자 일정시간 미사용시 차단 기능


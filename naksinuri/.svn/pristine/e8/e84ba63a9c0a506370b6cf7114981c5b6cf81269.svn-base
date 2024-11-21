//이중화 설정 시 bDup 를 true 로 설정할 것
var bDup = true;
var vOpen = false;
var vError = false;
var vRetry = false;
function ConnectServer() {
    if(typeof(Worker) == "undefined") {
		WriteLog("Sorry, your browser does not support Web Workers...")
		return;
	}
		
	if(typeof(w) == "undefined") {
		w = new Worker("/telper_api/sp.js");
		WriteLog("Web Workers Setting...");
	}
	PostJsonMsg('ConnectServer', cti_call_api_url);
	
	w.onmessage = function(event) {
		jsonMsg = event.data;
		cti_manager_console_log(jsonMsg);
		//set raw
		saveRawBackUp(jsonMsg);
		//End of set raw
		
		if(jsonMsg.KIND == "Event") {
			ProcJsonEventMsg(jsonMsg);
		} else if(jsonMsg.KIND == "Resp") {
			ProcJsonRespMsg(jsonMsg);
		} else if(jsonMsg.KIND == "Open") {
			ctiResConnStatus('CTI 연결시작',true);
			ctiResConnected();
			ProcJsonOpenMsg(jsonMsg);
		} else if(jsonMsg.KIND == "Close") {
			ctiResConnStatus('CTI 연결해제',true);
			ctiResDisConnected();
			ProcJsonCloseMsg(jsonMsg);
		} else if(jsonMsg.KIND == "Error") {
			ctiResConnStatus('CTI 연결에러',true);
			ProcJsonErrorMsg(jsonMsg);
		} else {
			var jsonStr = JSON.stringify(jsonMsg);
		}
			
	};
}

function ProcJsonOpenMsg(jsonMsg) {
	//console.log("프로세스확인1");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	vOpen = true;
	var reason = jsonMsg.REASON;
	WriteLog(reason);
	
	if(bDup == true && vRetry == true)
	{
		vRetry = false;
		vError = false;
		ReconnectServer();
	}
}

function ProcJsonCloseMsg(jsonMsg) {
	//console.log("프로세스확인2");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var bError = jsonMsg.ERROR_YN;
	if(bError == "Y")
	{
		vError = true;
		
		if(bDup == true && vRetry == true)
		{
			setTimeout("ReconnectServer()", 1000);
		}
		
		if(bDup == true && vOpen == true && vError == true)
		{
			// 재접속 timer
			vRetry = true;
			setTimeout("ReconnectServer()", 1000);
		}
	}
	
	vOpen = false;
	
	var reason = jsonMsg.REASON;
	WriteLog(reason);

}

function ProcJsonErrorMsg(jsonMsg) {
	//console.log("프로세스확인3");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var reason = jsonMsg.REASON;
	WriteLog(reason);
	var msg = '에러발생';
	if(jsonMsg.ID == 'WebSocket') {
		if(jsonMsg.KIND == 'Error') {
			msg = 'CTI 서버연결실패';
		}
	}
	ctiResError(msg);
}

function ReconnectServer()
{
	//console.log("프로세스확인3");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	if(vRetry == true)
	{
		ConnectServer();
	}
	else
	{
		GetMyStatus();
	}
}

function DisconnectServer() { 
	//console.log("프로세스확인4");
	PostJsonMsg('DisconnectServer');
}
function MonitorRegist(){
	//console.log("프로세스확인5");
	PostJsonMsg('MonitorRegist', CTI_TEL_NO);
}

function MonitorUnregist(){
	//console.log("프로세스확인6");
	PostJsonMsg('MonitorUnregist', CTI_TEL_NO);
}
function Login(){
	//console.log("프로세스확인7");
	PostJsonMsg('Login', CTI_TEL_NO, CTI_ID);
}

function Logout(){
	//console.log("프로세스확인8");
	PostJsonMsg('Logout', CTI_TEL_NO, CTI_ID);
}

function Avail(){
	//console.log("프로세스확인9");
	if(ctiObj.ispause || ctiObj.iswork) {
		//set ctiObj
		ctiObj.actlabel = '[대기모드로전환]';
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('Avail', CTI_TEL_NO, CTI_ID);	
	}
}

function Unavail(subState){
	//console.log("프로세스확인10");
	//console.log("jsonMsg:"+JSON.stringify(subState));
	if(!ctiObj.ispause) {
		//set ctiObj
		ctiObj.actlabel = '[휴식모드로전환]';
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('Unavail', CTI_TEL_NO, CTI_ID, subState);	
	}
}

function Work(subState){
	console.log("프로세스확인11");
	console.log("jsonMsg:"+JSON.stringify(subState));
	if(!ctiObj.iswork) {
		//set ctiObj
		ctiObj.actlabel = '[작업모드로전환]';
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('Work', CTI_TEL_NO, CTI_ID, subState);	
	}
	
}

function MakeCall(number){	
	//console.log("프로세스확인12");
	//console.log("jsonMsg:"+JSON.stringify(number));
	//set ctiObj
	ctiObj.actlabel = '[전화발신]';
	ctiObj.issendcall = true;
	ctiObj.callnum = number;
	ctiObj.ctiid = CTI_ID;
	ctiObj.ctitelno = CTI_TEL_NO;
	ctiObj.rawdata = null;
	saveCtiBackUp(deepClone(ctiObj));
	//End of set ctiObj
	if(number.length > 4) number = '9' + number;
	PostJsonMsg('MakeCall', CTI_TEL_NO, CTI_ID, number);
}

function AnswerCall(){
	//console.log("프로세스확인13");
	//console.log("jsonMsg:"+JSON.stringify(ctiObj));
	ctiObj.isbell = true;	
	
	if(ctiObj.isbell) {
		//set ctiObj
		ctiObj.actlabel = '[전화받기]';
		ctiObj.isreceivecall = true;
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('AnswerCall', CTI_TEL_NO, CTI_ID);
	}
}


function DisconnectCall(){
	//console.log("프로세스확인14");
	
	//고광훈 추가 - 전화끊기 연결안되서 임의로 추가 
	ctiObj.iscallcatch = true;
	ctiObj.isbell = true;
	
	if(ctiObj.iscallcatch || ctiObj.isbell) {
		ctiObj.actlabel = '[전화연결끊김]';
		PostJsonMsg('DisconnectCall', CTI_TEL_NO, CTI_ID);
	}	
	

	//고광훈 2차 추가 - callcatch/isbell 항목은  선언이 안되어있어  actlabel로 구분 
	/*	
	if(ctiObj.actlabel == '[전화발신성공]' || ctiObj.actlabel == '[통화중]' ||  ctiObj.actlabel == '[수신전화]' || ctiObj.actlabel == '[전화연결끊기성공]') {

		ctiObj.actlabel = '[전화연결끊김]';
		PostJsonMsg('DisconnectCall', CTI_TEL_NO, CTI_ID);
		
	}	
	*/	
	//console.log("actlabel확인:"+ctiObj.actlabel);

	
}


function HoldCall(){
	//console.log("프로세스확인15");
	//console.log("jsonMsg:"+JSON.stringify(ctiObj));
	if(ctiObj.isbell || ctiObj.iscallcatch) {
		//set ctiObj
		ctiObj.actlabel = '[보류상태전환]';
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('HoldCall', CTI_TEL_NO, CTI_ID);
	}	
}

function RetrieveCall(){
	//console.log("프로세스확인16");
	//console.log("jsonMsg:"+JSON.stringify(ctiObj));
	if(ctiObj.isbell || ctiObj.iscallcatch) {
		//set ctiObj
		ctiObj.actlabel = '[보류상태해제]';
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.rawdata = null;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		PostJsonMsg('RetrieveCall', CTI_TEL_NO, CTI_ID);
	}
}

function ConsultationCall(number){
	//console.log("프로세스확인17");
	PostJsonMsg('ConsultationCall', CTI_TEL_NO, CTI_ID, number);
}

function ReconnectCall(){
	//console.log("프로세스확인18");
	PostJsonMsg('ReconnectCall', CTI_TEL_NO, CTI_ID);
}

function TransferCall(){	
	//console.log("프로세스확인19");
	PostJsonMsg('TransferCall', CTI_TEL_NO, CTI_ID);
}

function AlternateCall(){
	//console.log("프로세스확인20");
	PostJsonMsg('AlternateCall', CTI_TEL_NO, CTI_ID);
}

function ConferenceCall(){
	//console.log("프로세스확인21");
	PostJsonMsg('ConferenceCall', CTI_TEL_NO, CTI_ID);
}

function SingleStepTransferCall(number){
	//console.log("프로세스확인22");
	//set ctiObj
	ctiObj.actlabel = '[통화중전달하기]';
	ctiObj.istransfercall = true;
	ctiObj.calltransfernum = number;
	ctiObj.rawdata = null;
	saveCtiBackUp(deepClone(ctiObj));

	
	//End of set ctiObj
	PostJsonMsg('SingleStepTransferCall', CTI_TEL_NO, CTI_ID, number);
}

function SingleStepConferenceCall(number){
	//console.log("프로세스확인23");
	PostJsonMsg('SingleStepConferenceCall', CTI_TEL_NO, CTI_ID, number);
}

function GroupPickup(){
	//console.log("프로세스확인24");
	PostJsonMsg('GroupPickup', CTI_TEL_NO, CTI_ID);
}

function DTMFTone(dtmfString){
	//console.log("프로세스확인25");
	//console.log("dtmfString:"+JSON.stringify(dtmfString));
	PostJsonMsg('SendDTMF', CTI_TEL_NO, CTI_ID, dtmfString);
}

function SetUEI(){
	//console.log("프로세스확인26");
	PostJsonMsg('SetUEI', CTI_TEL_NO, CTI_ID, ''/*$('#uei').val()*/);
}

function GetUEI(){
	//console.log("프로세스확인27");
	PostJsonMsg('GetUEI', CTI_TEL_NO, CTI_ID);
}

function GetIvrInfo(){
	//console.log("프로세스확인28");
	PostJsonMsg('GetIvrInfo', CTI_TEL_NO, CTI_ID);
}
function GetQueueInfo(){
	//console.log("프로세스확인29");
	PostJsonMsg('GetQueueInfo', CTI_TEL_NO, CTI_ID, number);
}

function GetMyStatus(){
	//console.log("프로세스확인30");
	PostJsonMsg('GetMyStatus', CTI_TEL_NO, CTI_ID);
}

function CtfStart() {
	//console.log("프로세스확인31");
	PostJsonMsg('CtfStart', CTI_TEL_NO, CTI_ID);
}

function SetFeature(featureType){
	//console.log("프로세스확인32");
	if(featureType == 'SpeakerVolume')
		PostJsonMsg(featureType, CTI_TEL_NO, $('#featureVolume').val() == 'undefined' ? '0' : $('#featureVolume').val());
	else
		PostJsonMsg(featureType, CTI_TEL_NO, $('#featureOnOff').val());
}

function QueryDevice(){
	//console.log("프로세스확인33");
	PostJsonMsg('QueryDevice', CTI_TEL_NO, $('#featureKind').val());
}

function SendPing(){
	//console.log("프로세스확인34");
	PostJsonMsg('SendPing', CTI_TEL_NO);
}


function PostJsonMsg(valID, valParam1, valParam2, valParam3)
{
	//console.log("프로세스확인35");
	console.log("valID:"+valID);
	console.log("valParam1:"+valParam1);
	console.log("valParam2:"+valParam2);
	console.log("valParam3:"+valParam3);
	
	var message = new Object;
	message.ID = valID;
	if(valID == 'ConnectServer')				{	message.PARAM = valParam1;		}
	else if(valID == 'DisconnectServer')		{	;	}	// noParam
	else if(valID == 'MonitorRegist')		{	message.TELNO = valParam1;		}
	else if(valID == 'MonitorUnregist')		{	message.TELNO = valParam1;		}
	else if(valID == 'Login')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'Logout')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'Avail')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'Unavail')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		if(valParam3 == '0')		message.ID = 'Unavail0';
																													else if(valParam3 == '1')	message.ID = 'Unavail1';
																													else if(valParam3 == '2')	message.ID = 'Unavail2';
																													else if(valParam3 == '3')	message.ID = 'Unavail3';
																													else if(valParam3 == '4')	message.ID = 'Unavail4';
																													else if(valParam3 == '5')	message.ID = 'Unavail5';
																													else if(valParam3 == '6')	message.ID = 'Unavail6';
																													else if(valParam3 == '7')	message.ID = 'Unavail7';
																													else if(valParam3 == '8')	message.ID = 'Unavail8';
																													else if(valParam3 == '9')	message.ID = 'Unavail9';		}
	else if(valID == 'Work')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		if(valParam3 == '0')		message.ID = 'Work0';
																													else if(valParam3 == '1')	message.ID = 'Work1';
																													else if(valParam3 == '2')	message.ID = 'Work2';
																													else if(valParam3 == '3')	message.ID = 'Work3';
																													else if(valParam3 == '4')	message.ID = 'Work4';
																													else if(valParam3 == '5')	message.ID = 'Work5';
																													else if(valParam3 == '6')	message.ID = 'Work6';
																													else if(valParam3 == '7')	message.ID = 'Work7';
																													else if(valParam3 == '8')	message.ID = 'Work8';
																													else if(valParam3 == '9')	message.ID = 'Work9';
																													else if(valParam3 == 'ACW')	message.ID = 'WorkACW';
																													else if(valParam3 == 'CTF')	message.ID = 'WorkCTF';			}
	else if(valID == 'MakeCall')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DESTTELNO = valParam3;		}
	else if(valID == 'AnswerCall')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'DisconnectCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'HoldCall')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'RetrieveCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'ConsultationCall')		{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DESTTELNO = valParam3;		}
	else if(valID == 'ReconnectCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'TransferCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'AlternateCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'ConferenceCall')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'SingleStepTransferCall')	{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DESTTELNO = valParam3;		}
	else if(valID == 'SingleStepConferenceCall'){	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DESTTELNO = valParam3;		}
	else if(valID == 'GroupPickup')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'SendDTMF')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DTMFSTRING = valParam3;		}
	else if(valID == 'SetUEI')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.UEI = valParam3;			}
	else if(valID == 'GetUEI')					{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'GetQueueInfo')			{	message.TELNO = valParam1;		message.CTIID = valParam2;		message.DESTTELNO = valParam3;		}																																																																				
	else if(valID == 'GetIvrInfo')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'GetMyStatus')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	else if(valID == 'MicrophoneMute')			{	message.TELNO = valParam1;		message.ONOFF = valParam2;		}
	else if(valID == 'SpeakerMute')				{	message.TELNO = valParam1;		message.ONOFF = valParam2;		}
	else if(valID == 'SpeakerVolume')			{	message.TELNO = valParam1;		message.VALUE = valParam2;		}
	else if(valID == 'QueryDevice')				{	message.TELNO = valParam1;		message.VALUE = valParam2;		}
	else if(valID == 'SendPing')				{	message.TELNO = valParam1;		}
	else if(valID == 'CtfStart')				{	message.TELNO = valParam1;		message.CTIID = valParam2;		}
	

	WriteLog("ID[" + valID + "] Param1[" + valParam1 + "] Param2[" + valParam2 + "] Param3[" + valParam3 + "]", "send");
	w.postMessage(message);
}

function ProcJsonRespMsg(jsonMsg){
	//console.log("프로세스확인36");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	if(valID == "SetUEI" || valID == "GetUEI")
		WriteLog("ID[" + jsonMsg.ID + "] RespType[" + jsonMsg.RESPTYPE + "] Reason[" + jsonMsg.REASON + "] UEI[" + jsonMsg.UEI + "]", "receive");
	else if(valID == "GetIvrInfo")
		WriteLog("ID[" + jsonMsg.ID + "] RespType[" + jsonMsg.RESPTYPE + "] Reason[" + jsonMsg.REASON + "] IVR INFO[" + jsonMsg.IVRINFO + "]", "receive");
	else  if(valID == "GetQueueInfo")
		WriteLog("ID[" + jsonMsg.ID + "] RespType[" + jsonMsg.RESPTYPE + "] Reason[" + jsonMsg.REASON + "] AVAIL USER COUNT[" + jsonMsg.AVAILUSERCOUNT + "] WAIT COUNT[" + jsonMsg.WAITCOUNT + "] WAIT_TIME[" + jsonMsg.WAITTIME + "]", "receive");
	else {
		WriteLog("ID[" + jsonMsg.ID + "] RespType[" + jsonMsg.RESPTYPE + "] Reason[" + jsonMsg.REASON + "]", "receive");
		/** res process */
		if(jsonMsg.ID == 'Login') {							//로그인
			if(jsonMsg.RESPTYPE == 'Fail') {
				if(jsonMsg.REASON == 'ERROR_PBX_CSTA2_810F') { //재로그인 시도
					ctiResConnStatus('CTI 로그인실패 <i class="fa fa-refresh cursor-pointer" onclick="ConnectServer()" aria-hidden="true">',true);
					if(cntReqReLogin >= 3) {
						ctiResConnStatus('CTI 로그인실패횟수초과 <i class="fa fa-refresh cursor-pointer" onclick="ConnectServer()" aria-hidden="true">',true);
						isReqReLogin = false;
						cntReqReLogin = 0;
					} else {
						isReqReLogin = true;
						cntReqReLogin++;
					}
					Logout();
				} else {
					ctiResConnStatus('CTI 로그인실패 <i class="fa fa-refresh cursor-pointer" onclick="ConnectServer()" aria-hidden="true">',true);
				}
			} else if(jsonMsg.RESPTYPE == 'Success') {
				ctiResConnStatus('연결됨( '+CTI_TEL_NO+'번 )',false);
				ctiResOnReady();
				/*setInterval(function(){
					if() {
						SendPing();//자동갱신을 위해 해야할까?
					}						
		    	},1000*30);*/
			}
		} else if(jsonMsg.ID == 'Logout') {					//로그아웃
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResError('로그아웃 실패');
			} else if(jsonMsg.RESPTYPE == 'Success') {
				if(isReqReLogin) {
					ctiResConnStatus('CTI 로그인재시도..',true);
					isReqReLogin = false;					
					Login();
				}
			}		
		} else if(jsonMsg.ID == 'MakeCall') {				//발신 전화
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResError('발신 실패');
				//set ctiObj
				ctiObj.actlabel = '[전화발신실패]';
				ctiObj.iserror = true;
				//ctiObj.issendcall = false;//returnCallEnd에서처리함
				ctiObj.callnum = '';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
				returnCallEnd({label_sub:'(전화발신실패)',valTime:0,jsonMsg:jsonMsg});
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[전화발신성공]';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
				ctiResCallSend(deepClone(ctiObj));
			}			
		} else if(jsonMsg.ID == 'AnswerCall') { 			//전화받기
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResError('전화받기 실패');
				//set ctiObj
				ctiObj.actlabel = '[전화받기실패]';
				ctiObj.iserror = true;
				ctiObj.isreceivecall = false;
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[전화받기성공]';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
			}			
		} else if(jsonMsg.ID == 'DisconnectCall') {			//전화연결끊기
			
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResError('전화 끊기 실패');
				//set ctiObj
				ctiObj.actlabel = '[전화연결끊기실패]';
				ctiObj.iserror = true;
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[전화연결끊기성공]';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
			}
		} else if(jsonMsg.ID == 'SingleStepTransfer') {		//통화중전달하기
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResError('통화중 전달하기 실패');
				//set ctiObj
				ctiObj.actlabel = '[통화중 전달하기실패]';
				ctiObj.iserror = true;
				//ctiObj.istransfercall = false;//returnCallEnd에서처리함
				ctiObj.calltransfernum = '';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
				returnCallEnd({label_sub:'(통화중 전달하기실패)',valTime:0,jsonMsg:jsonMsg});
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[통화중 전달하기성공]';
				ctiObj.rawdata = jsonMsg;
				saveCtiBackUp(deepClone(ctiObj));
				//End of set ctiObj
			}
		} else if(jsonMsg.ID == 'Avail') { //대기모드전환
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResCallPauseFail();
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[대기모드전환완료]';
				ctiObj.ispause = false;
				ctiObj.iswork = false;
				//End of set ctiObj
				ctiResCallResume(deepClone(ctiObj));
			}
		} else if(jsonMsg.ID == 'Unavail0') { //휴식모드전환
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResCallPauseFail();
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[휴식모드전환완료]';
				ctiObj.ispause = true;
				//End of set ctiObj
				ctiResCallPause(deepClone(ctiObj));
			}
		} else if(jsonMsg.ID == 'Work0') { //작업모드전환
			if(jsonMsg.RESPTYPE == 'Fail') {
				ctiResCallWorkFail();
			} else if(jsonMsg.RESPTYPE == 'Success') {
				//set ctiObj
				ctiObj.actlabel = '[작업모드전환완료]';
				ctiObj.iswork = true;
				//End of set ctiObj
				ctiResCallWork(deepClone(ctiObj));
			}
		}
		/** End of res process */
	}
		
}

function ProcJsonEventMsg(jsonMsg){
	//console.log("프로세스확인37");
	//console.log("ProcJsonEventMsg:"+JSON.stringify(jsonMsg));

	var valID = jsonMsg.ID;
	
	if(valID == "ButtonStatus")
		ProcJsonEventButtonMsg(jsonMsg);
	else if(
			valID == "LOGIN" || valID == "LOGOUT" ||
			valID == "AVAIL" ||
			valID == "UNAVAIL0" || valID == "UNAVAIL1" || valID == "UNAVAIL2" || valID == "UNAVAIL3" || valID == "UNAVAIL4" || valID == "UNAVAIL5" || valID == "UNAVAIL6" || valID == "UNAVAIL7" || valID == "UNAVAIL8" || valID == "UNAVAIL9" ||
			valID == "WORK0" || valID == "WORK1" || valID == "WORK2" || valID == "WORK3" || valID == "WORK4" || valID == "WORK5" || valID == "WORK6" || valID == "WORK7" || valID == "WORK8" || valID == "WORK9" || valID == "WORKACW" || valID == "WORKCTF"
			)
		ProcJsonEventStateMsg(jsonMsg);
	else if(valID == "SERVICE_INITIATED")
	{
		ProcJsonEventServiceInitMsg(jsonMsg);
	}
	else if(valID == "ORIGINATED")
	{
		ProcJsonEventOriginatedMsg(jsonMsg);
	}
	else if(valID == "NETWORK_REACHED")
	{
		ProcJsonEventNetworkReachedMsg(jsonMsg);
	}
	else if(valID == "ALERTED")
	{
		ProcJsonEventAlertedMsg(jsonMsg);
	}
	else if(valID == "CONNECTED")
	{
		ProcJsonEventConnectedMsg(jsonMsg);
	}
	else if(valID == "HOLD")
	{
		ProcJsonEventHoldMsg(jsonMsg);
	}
	else if(valID == "HELD")
	{
		ProcJsonEventHeldMsg(jsonMsg);
	}
	else if(valID == "RETRIEVED")
	{
		ProcJsonEventRetrievedMsg(jsonMsg);
	}
	else if(valID == "DISCONNECTED")
	{
		ProcJsonEventDisconnectedMsg(jsonMsg);
	}
	else if(valID == "CONNECTION_CLEARED")
	{
		ProcJsonEventConnectionClearMsg(jsonMsg);
	}
	else if(valID == "CONFERENCED")
	{
		ProcJsonEventConferencedMsg(jsonMsg);
	}
	else if(valID == "CONFERENCED_HOLD")
	{
		ProcJsonEventConferencedHoldMsg(jsonMsg);
	}
	else if(valID == "CONFERENCED_HELD")
	{
		ProcJsonEventConferencedHeldMsg(jsonMsg);
	}
	else if(valID == "DIVERTED")
	{
		ProcJsonEventDivertedMsg(jsonMsg);
	}
	else if(valID == "FAILED")
	{
		ProcJsonEventFailedMsg(jsonMsg);
	}
	else if(valID == "QUEUED")
	{
		ProcJsonEventQueuedMsg(jsonMsg);
	}
	else if(valID == "QUEUED_OUT")
	{
		ProcJsonEventQueuedOutMsg(jsonMsg);
	}
	else if(valID == "XFERRED")
	{
		ProcJsonEventXferredMsg(jsonMsg);
	}
	else if(valID == "OUT_OF_SERVICE")
	{
		ProcJsonEventOutOfServiceMsg(jsonMsg);
	}
	else if(valID == "IN_SERVICE")
	{
		ProcJsonEventInServiceMsg(jsonMsg);
	}
	else if(valID == "PONG")
	{
		ProcJsonEventPongMsg(jsonMsg);
	}
	else if(valID == "POPUP")
	{
		ProcJsonEventPopupMsg(jsonMsg);
	}
	else
	{
		var jsonStr = JSON.stringify(jsonMsg);
		WriteLog(jsonStr);
	}
}

function ProcJsonEventServiceInitMsg(jsonMsg)
{
	//console.log("프로세스확인38");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventOriginatedMsg(jsonMsg)
{
	//console.log("프로세스확인39");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	if(valStatus == 'StationOutRing') {
		//set ctiObj
		ctiObj.actlabel = '[전화벨울림:발신]';
		ctiObj.isbell = true;
		ctiObj.callkey = valCallKey;
		ctiObj.calldt = getCallDate();
		ctiObj.bellStartTime = valTime;
		ctiObj.rawdata = jsonMsg; 
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
	} 	
	
}
function ProcJsonEventNetworkReachedMsg(jsonMsg)
{
	//console.log("프로세스확인40");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventAlertedMsg(jsonMsg)
{
	//console.log("프로세스확인41");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valInOut = jsonMsg.INOUT;
	var valOppTelno = jsonMsg.OPPTELNO;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] InOut[" + valInOut + "] OppTelno[" + valOppTelno + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	if(valStatus == 'DirectAcdInRing') {//전달건 수신전화
		//set ctiObj
		ctiObj.actlabel = '[전화벨울림:전달건]';
		ctiObj.callkey = valCallKey;
		ctiObj.isbell = true;
		ctiObj.isretransfercall = true;
		ctiObj.calldt = getCallDate();
		ctiObj.bellStartTime = valTime;
		ctiObj.ctiid = CTI_ID;
		ctiObj.ctitelno = CTI_TEL_NO;
		ctiObj.callnum = valOppTelno;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		ctiResCallReceive(deepClone(ctiObj));
		console.log("ctiObj.ctiid1:"+ctiObj.ctiid);
		ctiResCallBell(deepClone(ctiObj));
	} else if(valStatus == 'AcdInRing') {//일반 수신전화
		//set ctiObj
		ctiObj.actlabel = '[전화벨울림:수신]';
		ctiObj.callkey = valCallKey;
		ctiObj.isbell = true;
		if(!ctiObj.isreceivecall) ctiObj.istransfercall = true;//전달건 수신시
		ctiObj.calldt = getCallDate();
		ctiObj.callnum = valOppTelno;
		ctiObj.bellStartTime = valTime;
		ctiObj.rawdata = jsonMsg;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		ctiResCallBell(deepClone(ctiObj));
	} else if(valStatus == 'TrunkOutRing') {//발신
		if(ctiObj.issendcall) {//MakeCall 을 통한 발신 
			//
		} else {//단말기에서 직접 발신
			//set ctiObj
			ctiObj.actlabel = '[전화벨울림:단말기발신]';
			ctiObj.issendcall = true;
			ctiObj.ctiid = CTI_ID;
			ctiObj.ctitelno = CTI_TEL_NO;
			ctiObj.callnum = valOppTelno;
			ctiObj.rawdata = jsonMsg;
			saveCtiBackUp(deepClone(ctiObj));
			//End of set ctiObj
			ctiResCallSend(deepClone(ctiObj));
		}
		
	}
		
}
function ProcJsonEventConnectedMsg(jsonMsg)
{
	//console.log("프로세스확인42");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valInOut = jsonMsg.INOUT;
	var valOppTelno = jsonMsg.OPPTELNO;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] InOut[" + valInOut + "] OppTelno[" + valOppTelno + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	//set ctiObj
	ctiObj.actlabel = '[통화중]';
	ctiObj.callkey = valCallKey;
	ctiObj.iscallcatch = true;
	ctiObj.bellEndTime = valTime;
	ctiObj.callStartTime = valTime;
	ctiObj.rawdata = jsonMsg;
	saveCtiBackUp(deepClone(ctiObj));
	//End of set ctiObj
	ctiResCalling(deepClone(ctiObj));
	if(!ctiObj.isrecidcatch) {
		fn_vlgGetRecordID();	
	}
}
function ProcJsonEventHoldMsg(jsonMsg)
{
	//console.log("프로세스확인43");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	if(valStatus == 'TrunkOutConv' || valStatus == 'DirectAcdInConv' || valStatus == 'CSFIPAAA') {
		//set ctiObj
		ctiObj.actlabel = '[보류상태전환완료]';
		ctiObj.isholdcall = true;
		ctiObj.callkey = valCallKey;
		ctiObj.holdStartTime = valTime;
		//End of set ctiObj
		ctiResCallHold(deepClone(ctiObj));
	}	
}
function ProcJsonEventHeldMsg(jsonMsg)
{
	//console.log("프로세스확인44");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
/*	
	alert("valID:"+valID);
	alert("valCallCnt:"+valCallCnt);
	alert("valStatus:"+valStatus);
	alert("valCallKey:"+valCallKey);
	alert("valTime:"+valTime);
	alert("convTime:"+convTime);
	*/
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventRetrievedMsg(jsonMsg)
{
	//console.log("프로세스확인45");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	if(valStatus == 'TrunkOutConv' || valStatus == 'DirectAcdInConv' || valStatus == 'CSFIPAAA') {
		//set ctiObj
		ctiObj.actlabel = '[보류상태해제완료]';
		ctiObj.isholdcall = false;
		ctiObj.callkey = valCallKey;
		ctiObj.holdEndTime = valTime;
		//End of set ctiObj
		ctiResCallUnHold(deepClone(ctiObj));
	}
	
}
function ProcJsonEventDisconnectedMsg(jsonMsg)
{
	//console.log("프로세스확인46");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valReleasing = jsonMsg.RELEASING;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] RELEASING[" + valReleasing + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");

	if(valStatus == 'TrunkOutConv' && !ctiObj.iscallcatch) {//수신거부
		ctiResError('수신거부');
		//set ctiObj
		ctiObj.actlabel = '[전화발신실패:수신거부]';
		ctiObj.callst = '수신거부';
		//ctiObj.issendcall = false;//returnCallEnd에서처리함
		ctiObj.callnum = valReleasing;
		ctiObj.rawdata = jsonMsg;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		returnCallEnd({label_sub:'(상대방이 수신을 거부 하였습니다.)',valTime:valTime,jsonMsg:jsonMsg});
	}

}
function ProcJsonEventConnectionClearMsg(jsonMsg)
{
	//console.log("프로세스확인47");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	returnCallEnd({label_sub:null,valTime:valTime,jsonMsg:jsonMsg});
	
}
function ProcJsonEventConferencedMsg(jsonMsg)
{
	//console.log("프로세스확인48");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventConferencedHoldMsg(jsonMsg)
{
	//console.log("프로세스확인49");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventConferencedHeldMsg(jsonMsg)
{
	//console.log("프로세스확인50");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventDivertedMsg(jsonMsg)
{
	//console.log("프로세스확인51");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventFailedMsg(jsonMsg)
{
	//console.log("프로세스확인52");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	if(valStatus == 'TrunkOutFail' || valStatus == 'StationOutFail') {//발신 실패
		ctiResError('발신 실패');
		//set ctiObj
		ctiObj.actlabel = '[전화발신실패]';
		ctiObj.iserror = true;
		//ctiObj.issendcall = false;//returnCallEnd에서처리함
		if(valStatus == 'StationOutFail') ctiObj.callnum = '';
		ctiObj.issendcall = true;	// 발신여부
		ctiObj.rawdata = jsonMsg;
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		returnCallEnd({label_sub:'(올바른 연락처가 맞는지 확인이 필요합니다.)',valTime:valTime,jsonMsg:jsonMsg});
	} 
	
}
function ProcJsonEventQueuedMsg(jsonMsg)
{
	//console.log("프로세스확인53");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
}
function ProcJsonEventXferredMsg(jsonMsg)
{
	//console.log("프로세스확인54");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var valCallCnt = jsonMsg.CALLCOUNT;
	var valStatus = jsonMsg.STATUS;
	var valCallKey = jsonMsg.CALLKEY;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("Call Key[" + valCallKey + "] ID[" + valID + "] Call Count[" + valCallCnt + "] Call Status[" + valStatus + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	returnCallEnd({valTime:valTime,jsonMsg:jsonMsg});
	
}
function ProcJsonEventOutOfServiceMsg(jsonMsg)
{
	//console.log("프로세스확인55");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	
	WriteLog("ID[" + valID + "]", "receive");
}
function ProcJsonEventInServiceMsg(jsonMsg)
{
	//console.log("프로세스확인56");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	
	WriteLog("ID[" + valID + "]", "receive");
}

function ProcJsonEventPongMsg(jsonMsg)
{
	//console.log("프로세스확인57");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	
	WriteLog("ID[" + valID + "]", "receive");
}

function ProcJsonEventPopupMsg(jsonMsg)
{
	//console.log("프로세스확인58");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));

	//console.log("CTI_IVR_INFO_DATA:"+JSON.stringify(CTI_IVR_INFO_DATA));
	//console.log("ctiObj:"+JSON.stringify(ctiObj));
	//console.log("JsonMsg4:"+JSON.stringify(JsonMsg4));
	
	
	var valID = jsonMsg.ID;
	var valQueueDur = jsonMsg.QUEUEDUR;//호 분배 후 상담원이 받기까지의 대기시간(초)
	var valCalling = jsonMsg.CALLING;
	
	var valIvrInfo = jsonMsg.IVR_INFO;//자동안내번호선택값
	var valIvrInputInfo = jsonMsg.IVR_INPUTINFO;
	var valIvrMenuPath = jsonMsg.IVR_MENUPATH;
	
	
	
	WriteLog("ID[" + valID + "] QUEUE DUR[" + valQueueDur + "] Calling[" + valCalling + "] IVR INFO[" + valIvrInfo + "]", "receive");

	//set ctiObj
	ctiObj.actlabel = '[수신전화]';
	ctiObj.isreceivecall = true;
	if(valQueueDur!=null && valQueueDur.length!=0) 
		ctiObj.delayTime = parseInt(valQueueDur);
	ctiObj.ivrinfo = valIvrInfo;
	if(valIvrInfo!=null && valIvrInfo!='' && CTI_IVR_INFO_DATA.length!=0) {
		CTI_IVR_INFO_DATA.forEach(function(item,index){ 
			if(valIvrInfo.indexOf(item['CD']) != -1) {
				ctiObj.ivrinfocd = item['CD'];
				ctiObj.ivrinfolabel = item['NM'];
			}
		});
	}
	
	
	//고광훈 추가 - IVR메뉴 생성
	var menukey =  jsonMsg.IVR_MENUCODE;
	//console.log("menukey:"+menukey);
	
	//내서번호1 
	if(menukey=='IVMN20230814003648T87G'){
		ctiObj.ivrinfocd = 'IMNUFIPA_007';
		ctiObj.ivrinfolabel = '온라인 교육신청 및 수강방법';
	}
	
	//내서번호2
	if(menukey=='IVMN20230814003656U6D4'){
		ctiObj.ivrinfocd = 'IMNUFIPA_008';
		ctiObj.ivrinfolabel = '로그인 방법 및 시스템 오류 문의';
	}
	
	//내서번호3
	if(menukey=='IVMN202308140037372G6G'){
		ctiObj.ivrinfocd = 'IMNUFIPA_009';
		ctiObj.ivrinfolabel = '교육 이수 확인 및 이수증 발급';
	}
	
	//내서번호4
	if(menukey=='IVMN20230814003749XBC8'){
		ctiObj.ivrinfocd = 'IMNUFIPA_010';
		ctiObj.ivrinfolabel = '낚시어선 신규.재개자 전문교육';
	}
	
	//내서번호5
	if(menukey=='IVMN2023081400375506CS'){
		ctiObj.ivrinfocd = 'IMNUFIPA_011';
		ctiObj.ivrinfolabel = '교육 과정 안내 및 기타 문의';
	}
	
	
	//내서번호0
	if(menukey=='IVMN20230814003642A3IO'){
		ctiObj.ivrinfocd = 'IMNUFIPA_006';
		ctiObj.ivrinfolabel = '지자체, 해양경찰 등 낚시전문교육 담당자';
	}		
	
	//console.log("ctiObj.ivrinfocd:"+ctiObj.ivrinfocd);
	//console.log("ctiObj.ivrinfolabel:"+ctiObj.ivrinfolabel);
	//console.log("IVR메뉴확인");
	
	ctiObj.ctiid = CTI_ID;
	ctiObj.ctitelno = CTI_TEL_NO;
	ctiObj.callnum = valCalling;
	GetIvrInfo();
	saveCtiBackUp(deepClone(ctiObj));
	//End of set ctiObj		
	
	//console.log("ctiObj.ctiid2:"+ctiObj.ctiid);
	ctiResCallReceive(deepClone(ctiObj));
		
}


function ProcJsonEventStateMsg(jsonMsg)
{
	//console.log("프로세스확인59");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
	var valID = jsonMsg.ID;
	var telno = jsonMsg.TELNO;
	var ctiid = jsonMsg.CTIID;
	var valTime = jsonMsg.TIME;
	var convTime = new Date(valTime * 1000);
	
	WriteLog("ID[" + valID + "] TELNO[" + telno + "] CTIID[" + ctiid + "] Time[" + convTime.yyyymmddhhmmss() + "]", "receive");
	
	/*
	if(valID == 'WORK0') {
		if(!ctiObj.iserror
		&& !ctiObj.ispause
		&& !ctiObj.isholdcall
		&& !ctiObj.isbell
		&& !ctiObj.iscallcatch
		&& !ctiObj.issendcall
		&& !ctiObj.isreceivecall
		&& !ctiObj.istransfercall
		&& !ctiObj.isretransfercall
		&& !ctiObj.isrecidcatch
		) { //모든 조건이 초기화 상태일 때
			//발신시 work0 호출 후 통화 종료 후 work0를 수신모드로 전환하기 위한 처리
			PostJsonMsg('Avail', CTI_TEL_NO, CTI_ID);
		}
	}
	*/
	
}

function ProcJsonEventButtonMsg(jsonMsg)
{
	//console.log("프로세스확인60");
	//console.log("jsonMsg:"+JSON.stringify(jsonMsg));
//	var valID = jsonMsg.ID;
//	WriteLog("ID[" + valID + "]", "receive");
	buttonMsg = jsonMsg;
	
	var onoff;
	

	onoff = jsonMsg.CONNECT;					btnstate('connect', onoff);
	onoff = jsonMsg.DISCONNECT;					btnstate('disconnect', onoff);   		
	onoff = jsonMsg.LOGIN;						btnstate('agentLogin', onoff);  		
	onoff = jsonMsg.LOGOUT;						btnstate('agentLogOut', onoff);  		
	onoff = jsonMsg.AVAIL;						btnstate('agentWorkModeAvail', onoff);  
	onoff = jsonMsg.UNAVAIL0;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL1;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL2;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL3;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL4;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL5;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL6;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL7;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL8;					btnstate('agentWorkModeUnavail', onoff);
//	onoff = jsonMsg.UNAVAIL9;					btnstate('agentWorkModeUnavail', onoff);
	onoff = jsonMsg.WORK0;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK1;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK2;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK3;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK4;						btnstate('agentWorkModeWork', onoff);  	
//	onoff = jsonMsg.WORK5;						btnstate('agentWorkModeWork', onoff);  	
//	onoff = jsonMsg.WORK6;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK7;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK8;						btnstate('agentWorkModeWork', onoff); 	
//	onoff = jsonMsg.WORK9;						btnstate('agentWorkModeWork', onoff); 	
	onoff = jsonMsg.WORKACW;					btnstate('agentWorkModeACW', onoff);  	
//	onoff = jsonMsg.WORKCTF;					btnstate('agentWorkModeWork', onoff);  	
	onoff = jsonMsg.MAKECALL;					btnstate('makeCall', onoff);  			
	onoff = jsonMsg.ANSWERCALL;					btnstate('answerCall', onoff); 			
	onoff = jsonMsg.DISCONNECTCALL;				btnstate('disconnectCall', onoff);  	
	onoff = jsonMsg.HOLDCALL;					btnstate('holdCall', onoff);   			
	onoff = jsonMsg.RETRIEVECALL;				btnstate('retrieveCall', onoff); 		
	onoff = jsonMsg.CONSULTATIONCALL; 			btnstate('consultationCall', onoff); 	
	onoff = jsonMsg.RECONNECTCALL;				btnstate('reconnectCall', onoff); 		
	onoff = jsonMsg.TRANSFERCALL;				btnstate('transferCall', onoff);
	onoff = jsonMsg.ALTERNATECALL;				btnstate('alternateCall', onoff);
	onoff = jsonMsg.CONFERENCECALL;				btnstate('conferenceCall', onoff);  	
	onoff = jsonMsg.SINGLESTEPTRANSFERCALL;		btnstate('singleStepTransferCall', onoff);
	onoff = jsonMsg.SINGLESTEPCONFERENCECALL;	btnstate('singleStepConferenceCall', onoff);
	onoff = jsonMsg.GROUPPICKUP;				btnstate('groupPickup', onoff);
	onoff = jsonMsg.CTF;						btnstate('ctfStart', onoff);
}

function WriteLog(message, type)
{
	//console.log("프로세스확인61");
	//console.log("message:"+message);
	//console.log("type:"+type);
	var dt = new Date();
	dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + " " + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
	
	var log;
	
	if(type == "send")
		log = "Send	   : " + message;
	else if(type == "receive")
		log = "Receive : " + message;
	else log = message;	
	
	cti_manager_console_log(type+' | '+message);
}

Date.prototype.yyyymmddhhmmss = function()
{
	//console.log("프로세스확인62");
	var yyyy = this.getFullYear().toString();
    var mm = (this.getMonth() + 1).toString();
    var dd = this.getDate().toString();
	var	hh = this.getHours().toString();
	var ii = this.getMinutes().toString();
	var ss = this.getSeconds().toString();
	
	return yyyy + "-" + (mm[1] ? mm : '0'+mm[0]) + "-" + (dd[1] ? dd : '0'+dd[0]) + " " + (hh[1] ? hh : '0'+ hh[0]) + ":" + (ii[1] ? ii : '0' + ii[0]) + ":" + (ss[1] ? ss : '0' + ss[0]);
}

function btnstate(id, state){
	//console.log("프로세스확인63");
	//console.log("id"+JSON.stringify(id));
	//console.log("state"+JSON.stringify(state));
}

//======================================================================
//녹취 서버 API
//======================================================================
var wvlg;
var JsonMsg4;

function vlgConnectServer() {
	//console.log("프로세스확인64");
    if(typeof(Worker) == "undefined") {
		WriteLogVlg("Sorry, your browser does not support Web Workers...")
		return;
	}
		
	if(typeof(wvlg) == "undefined") {
		wvlg = new Worker("/telper_api/vlgap.js");
		WriteLogVlg("Web Workers Setting...");
	}
	
	PostJsonMsg4('ConnectServer', cti_rec_api_url);
	
	wvlg.onmessage = function(event) {
		JsonMsg4 = event.data;
		console.log("JsonMsg4:"+JSON.stringify(JsonMsg4));
		
		if(JsonMsg4.MSGKIND == "Event")
			ProcJsonEventVlgMsg(JsonMsg4);
		else if(JsonMsg4.MSGKIND == "Resp")
			ProcJsonRespVlgMsg(JsonMsg4);
		else if(JsonMsg4.MSGKIND == "Open") {
			ProcJsonEventVlgMsg(JsonMsg4);
			ctiVlgResConnected();
		}
		else if(JsonMsg4.MSGKIND == "Error") {
			ProcJsonEventVlgMsg(JsonMsg4);
			ctiVlgResError('');
		}
		else
		{
			var jsonStr = JSON.stringify(JsonMsg4);
			cti_vlg_console_log(jsonStr);
		}
			
	};
}

function vlgDisconnectServer() { 
	//console.log("프로세스확인65");
    PostJsonMsg4('DisconnectServer');
}

//TELPER-VLG 서버 로그인
function fn_vlgLogin()
{
	//console.log("프로세스확인66");
	// 커넥션 등록
	//PostJsonMsg4('RegLink', CTI_TEL_NO, 'TDEV20230131110754L7GD');
	PostJsonMsg4('RegLink', CTI_TEL_NO, 'TDEV20230310145507PS12');
	// 로그인
	PostJsonMsg4('Login', 'U'+CTI_TEL_NO, CTI_TEL_NO);
}

//	TELPER-VLG 서버 로그아웃
function fn_vlgLogout()
{
	//console.log("프로세스확인67");
	PostJsonMsg4('Logout', 'U'+CTI_TEL_NO, CTI_TEL_NO);
}

//녹음 ID 조회
function fn_vlgGetRecordID()
{
	//console.log("프로세스확인68");
	PostJsonMsg4('GetRecordID', 'U'+CTI_TEL_NO, CTI_TEL_NO);
	//PostJsonMsg4('GetAPRecordID', 'U'+CTI_TEL_NO, CTI_TEL_NO);
}


//메세지 수신 처리(RESP)
function ProcJsonRespVlgMsg(JsonMsg4)
{
	//console.log("프로세스확인69");
	var valID = JsonMsg4.MSGID;
	var valResult = JsonMsg4.RESULT;
	var valReason = JsonMsg4.REASON;
		
	if((valID == 'ReqRecordID' || valID == 'ReqAPRecordID') && valResult == 'Success')
	{
		var strRecordIDMsg = JsonMsg4.RECORDID;
		var arrRecordID = strRecordIDMsg.split(":");		
		var recordID = arrRecordID[0];
		var strTableName = arrRecordID[2];
		
		WriteLogVlg("MSGID[" + valID + "] Result[" + valResult + "] RecordID[" + recordID + "] TableName[" + strTableName + "]", "receive");
		
		ctiObj.isrecidcatch = true;
		ctiObj.vlgRecordID = recordID;
		ctiObj.vlgTableName = strTableName;
		//console.log(JsonMsg4);
		//console.log(recordID+'/'+strTableName);
		ctiVlgResRecording(deepClone(ctiObj));
		
	}
	else if((valID == 'ReqRecordID' || valID == 'ReqAPRecordID') && valResult == 'Fail')
	{
		WriteLogVlg("MSGID[" + valID + "] Result[" + valResult + "] Reason[" + valReason + "]", "receive");
		//fn_vlgGetRecordID();		
	}
	else
	{
		WriteLogVlg("MSGID[" + valID + "] Result[" + valResult + "] Reason[" + valReason + "]", "receive");
		
		if(valID == 'Login') {
			if(valResult == 'Success') {
				ctiVlgResOnReady();
			} else {
				ctiVlgResOnReadyFail();
			}
		}
		
	}
		
}

// 메세지 수신 처리(EVENT)
function ProcJsonEventVlgMsg(JsonMsg4)
{
	//console.log("프로세스확인7");
	var valID = JsonMsg4.MSGID;

	if(valID == "ButtonStatus")
	{
		ProcEventButtonVlgMsg(JsonMsg4);
	}
	else if(valID == "WebSocket")
	{
		ProcEventWebSocketVlgMsg(JsonMsg4);
	}
	else if(valID == "ChannelEvt")
	{
		ProcEventChannelStateVlgMsg(JsonMsg4);
	}	
	else
	{
		var jsonStr = JSON.stringify(JsonMsg4);
		WriteLogVlg(jsonStr);		
	}
	
	cti_vlg_console_log(JsonMsg4);
	
}

// 메세지 수신 처리(EVENT)
function ProcEventChannelStateVlgMsg(JsonMsg4)
{
	//console.log("프로세스확인71");
	var valID = JsonMsg4.MSGID;
	var valState = JsonMsg4.STATE;
	var valRecordID = JsonMsg4.RECORDID;	
	
	if(valState == 'Recording')
	{
		WriteLogVlg("MSGID[" + valID + "] STATE[" + valState + "] RecordID[" + valRecordID + "]", "receive");
		
		fn_vlgGetRecordID();
		
	}
	else
	{
		WriteLogVlg("MSGID[" + valID + "] STATE[" + valState + "]", "receive");
	}
	
	cti_vlg_console_log(valState);
}

//메세지 전송 처리
function PostJsonMsg4(valID, valParam1, valParam2, valParam3, valParam4, valParam5, valParam6, valParam7)
{
	//console.log("프로세스확인72");
	var message = new Object;
	message.MSGID = valID;
	if(valID == 'ConnectServer')			{	message.PARAM = valParam1;	}
	else if(valID == 'DisconnectServer')			{	;	}
	else if(valID == 'Login')					{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'Logout')					{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'StartRecord')				{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'StopRecord')				{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'GetRecordID')				{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'GetAPRecordID')			{	message.AGTID = valParam1;	message.TELNO = valParam2;	}
	else if(valID == 'RegLink')					{	message.TELNO = valParam1;	message.DEVID = valParam2;	}
	else if(valID == 'SaveCstInfo')
	{
		message.AGTID = valParam1;
		message.TELNO = valParam2;
		message.CSTINFO1 = valParam3;
		message.CSTINFO2 = valParam4;
		message.CSTINFO3 = valParam5;
		message.RECORDID = valParam6;
		message.CALLTYPE = valParam7;
	}
	
	WriteLogVlg("ID[" + valID + "] Param1[" + valParam1 + "] Param2[" + valParam2 + "] Param3[" + valParam3 + "] Param4[" + valParam4 + "] Param5[" + valParam5 + "] Param6[" + valParam6 + "] Param7[" + valParam7 + "]", "send");
	wvlg.postMessage(message);
}

function ProcEventButtonVlgMsg(jsonVlgMsg)
{
	//console.log("프로세스확인73");
	var onoff;
	
	onoff = jsonVlgMsg.CONNECT;				btnstateVlg('connect', onoff);
	onoff = jsonVlgMsg.DISCONNECT;				btnstateVlg('disconnect', onoff);
	onoff = jsonVlgMsg.LOGIN;					btnstateVlg('btnLogin', onoff);
	onoff = jsonVlgMsg.LOGOUT;					btnstateVlg('btnLogout', onoff);
	onoff = jsonVlgMsg.RECSTART;				btnstateVlg('btnStartRecord', onoff);
	onoff = jsonVlgMsg.RECSTOP;				btnstateVlg('btnStopRecord', onoff);	
	onoff = jsonVlgMsg.GETRECORDID;			btnstateVlg('btnGetRecordID', onoff);
	onoff = jsonVlgMsg.GETAPRECORDID;			btnstateVlg('btnGetAPRecordID', onoff);
	onoff = jsonVlgMsg.SAVECSTINFO;			btnstateVlg('btnSaveCstInfo', onoff);
}

function btnstateVlg(id, state){
	//console.log("프로세스확인74");
	/*
	if(state == 'off')
		document.getElementById(id).className = 'button disabled';
	else
		document.getElementById(id).className = 'button';
		*/
}

function ProcEventWebSocketVlgMsg(jsonVlgMsg)
{
	//console.log("프로세스확인75");
	var reason = jsonVlgMsg.REASON;
	
	WriteLogVlg(reason);
}

function WriteLogVlg(message, type)
{
	//console.log("프로세스확인76");
	var dt = new Date();
	dt = dt.getFullYear() + "/" + (dt.getMonth()+1) + "/" + dt.getDate() + " " + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
	
	var log;
	
	if(type == "send")
		log = "Send	   : " + message;
	else if(type == "receive")
		log = "Receive : " + message;
	else log = message;
	
	cti_vlg_console_log("[" + dt + "] " + log);
	
}

//======================================================================
//End 녹취 서버 API
//======================================================================

//CTI API 최초 구동
var cntReqReLogin = 0;
var isReqReLogin = false;
$(function(){
	setTimeout(function(){
		ConnectServer();
	},3000);
});
function returnRefreshCallEnd(obj) {//새로고침에 의한 종료시
	//console.log("프로세스확인77");
	//console.log("returnRefreshCallEnd");
	//set ctiObj
	ctiObj.label = '통화종료';
	ctiObj.label_sub = '(새로고침 등에 의해 연결이 끊긴 건)';		
	ctiObj.callst = '연결해제';
	saveCtiBackUp(deepClone(ctiObj));
	//End of set ctiObj
	ctiResCallEnd(deepClone(ctiObj));
}
function returnCallEnd(data) {
	//console.log("프로세스확인78");
	//console.log("returnCallEnd:"+JSON.stringify(data));
	//console.log("Callend:"+data);
	//console.log("ctiObj_JSON:"+JSON.stringify(ctiObj));
	//console.log("ctiObj:"+ctiObj);
	
	
	ctiObj.isbell = true;
	if(ctiObj.isbell) { //벨이 울린 상태
		//set ctiObj
		ctiObj.actlabel = '[통화종료]';
		if(ctiObj.iscallcatch) { //통화 연결 됨
			ctiObj.label = '통화종료';
			ctiObj.callst = '응답';
			if(ctiObj.istransfercall) { //통화 전달
				//고광훈 추가- 안내멘트 추가
				if(ctiObj.calltransfernum == '2109'){
					ctiObj.label_sub = '( 성희롱안내 전달건 )';
				}else if(ctiObj.calltransfernum == '2110'){
					ctiObj.label_sub = '( 폭언욕설안내 전달건 )';
				}else{
					ctiObj.label_sub = '( ' + ctiObj.calltransfernum + ' 전달건 )';
				}
				
			} else {
				ctiObj.label_sub = '';					
			}
			ctiObj.callEndTime = data.valTime;
			ctiObj.bellTime = ctiObj.bellEndTime - ctiObj.bellStartTime;
			ctiObj.callTime = ctiObj.callEndTime - ctiObj.callStartTime;
			ctiObj.holdTime = ctiObj.holdEndTime - ctiObj.holdStartTime;
			ctiObj.totalCallTime = ctiObj.callEndTime - ctiObj.bellStartTime;
		} else { //통화 연결 안됨
			ctiObj.label = '통화종료';
			ctiObj.bellEndTime = data.valTime;
			ctiObj.bellTime = ctiObj.bellEndTime - ctiObj.bellStartTime;
			ctiObj.totalCallTime = ctiObj.bellEndTime - ctiObj.bellStartTime;
			if(ctiObj.bellTime <= 5) {
				ctiObj.label_sub = '(취소건)';
				if(ctiObj.callst=='') ctiObj.callst = '취소';				
			} else {
				ctiObj.label_sub = '(부재중)';
				if(ctiObj.callst=='') ctiObj.callst = '부재중';
			}
			
			//고광훈 추가  - 수신자 전화끊기
			if(ctiObj.callkey==''){
				ctiObj.label_sub = '(취소건)';
				if(ctiObj.callkey=='') ctiObj.callst = '취소';			
			}
		}
		if(data.label_sub!=null && data.label_sub.length!=0) ctiObj.label_sub = data.label_sub;
		ctiObj.rawdata = data.jsonMsg; 
		saveCtiBackUp(deepClone(ctiObj));
		//End of set ctiObj
		ctiResCallEnd(deepClone(ctiObj));
	}
}
//End of CTI API 최초 구동

//유틸리티
function numberPad(num, digit) { // 자릿수 맞춰주기
	//console.log("프로세스확인79");
	  var zero = '';
	  num = num.toString();
	  if (num.length < digit) {
	    for (i = 0; i < digit - num.length; i++) {
	      zero += '0';
	    }
	  }
	  return zero + num;
}
function deepClone(obj) {
	//console.log("프로세스확인80");
	//console.log("deepClone:"+JSON.stringify(obj));
	/*
	if(obj === null || typeof obj !== 'object') {
		return obj;
	}
	const result = Array.isArray(obj) ? [] : {};
	for(var key typeof Object.keys(obj)) {
	//for(let key of Object.keys(obj)) {
		result[key] = deepClone(obj[key])
	}
	return result;
	*/
	var clone = {};
    for(var i in obj) {
        if(typeof(obj[i])=="object" && obj[i] != null)
            clone[i] = deepClone(obj[i]);
        else
            clone[i] = obj[i];
    }
    return clone;
}
function getCallDate() {
	//console.log("프로세스확인81");
	var d = new Date();
    var currentDate = d.getFullYear() + "-" + numberPad(( d.getMonth() + 1 ),2) + "-" + numberPad(d.getDate(),2);
    var currentTime = numberPad(d.getHours(),2) + ":" + numberPad(d.getMinutes(),2) + ":" + numberPad(d.getSeconds(),2);
    return currentDate+' '+currentTime;
}
//End of 유틸리티

//cookie 정보 획득
var ctiBackupData = {
		cti				: new Array(),//ctiObj 데이터모음
		raw				: new Array(),//cti api all 데이터모음
}
var ctiObj = {
		ctistarttime	: getCallDate(),//CTI 접속시작시간
		cticleartime	: '',//CTI 정보초기화시간
		iserror			: false,//에러발생여부
		ispause			: false,//휴식모드
		iswork 			: false,//작업모드(후처리)
		isholdcall		: false,//보류(홀드)
		isbell			: false,//벨울림
		iscallcatch		: false,//통화시작
		issendcall 		: false,//발신
		isreceivecall 	: false,//수신		
		istransfercall	: false,//교환,전달(발신하기)
		isretransfercall: false,//교환,전달(수신하기)
		isrecidcatch	: false,//녹취ID수신여부
		callst			: '',//콜진행상태라벨
		ivrinfo			: '',//IVR정보
		ivrinfocd 		: '',//IVR파싱정보키값
		ivrinfolabel	: '',//IVR파싱정보
		actlabel		: '',//진행상태별분류라벨
		callkey			: '',//콜ID
		callnum			: '',//연결된전화번호
		calldt			: '',//콜등록시간
		calltransfernum	: '',//전달할내선번호
		ctiid			: '',//단말기ID
		ctitelno		: '',//단말기내선번호
		label			: '',//알림용문구
		label_sub 		: '',//알림용문구2
		strAgreeMsg		: '',//동의여부관련문구
		agreeStatus		: '',//동의여부관련정보2
		callStartTime	: 0,//통화시작시간
		callEndTime		: 0,//통화종료시간
		bellStartTime	: 0,//벨울림시작시간
		bellEndTime		: 0,//벨울림종료시간
		holdStartTime	: 0,//보류(홀드)시작시간
		holdEndTime		: 0,//보류(홀드)종료시간
		callTime		: 0,//총통화시간
		bellTime		: 0,//총벨울림시간
		totalCallTime	: 0,//총전화연결시간
		holdTime		: 0,//총보류(홀드)시간
		pauseTime		: 0,//총휴식모드시간
		delayTime		: 0,//상담원에게 벨이 울리고 받기 까지의 대기시간
		//녹취
		vlgRecordID		: '',//녹취ID
		vlgTableName	: '',//녹취파일요청테이블명
		//backup data
		saveTime		: '',//백업저장시간
		saveBackupKey	: '',//백업데이터키
		rawdata			: null,//백업시raw데이터
};
var setCtiCookie = function(name, value, expiredays) {
	//console.log("프로세스확인82");
	var date = new Date();
	date.setTime(date.getTime() + expiredays*24*60*60*1000);
    document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';	
}
var getCtiCookie = function(name) {
	//console.log("프로세스확인83");
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value && typeof value != "undefined"? value[2] : '';
}
var deleteCtiCookie = function(name) {
	//console.log("프로세스확인84");
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
/*
function setCtiCookie(name, value, expiredays) {
	var today = new Date();
    today.setDate(today.getDate() + expiredays);
    document.cookie = name + '=' + escape(value) + '; path=/; expires=' + today.toGMTString() + ';';	
}
function getCtiCookie(name) { 
	var cName = name + "="; 
	var x = 0; 
	while ( x <= document.cookie.length ) 
	{ 
	    var y = (x+cName.length); 
	    if ( document.cookie.substring( x, y ) == cName ) 
	    { 
	        if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 ) 
	            endOfCookie = document.cookie.length;
	        return unescape( document.cookie.substring( y, endOfCookie ) ); 
	    } 
	    x = document.cookie.indexOf( " ", x ) + 1;
	    
	    if ( x == 0 ) 
	        break; 
	} 
	return ""; 
}
*/
function saveRawBackUp(obj) {
	//console.log("프로세스확인85");
	//console.log("saveRawBackUp:"+JSON.stringify(obj));
	ctiBackupData['raw'].push(obj);
	//setCtiCookie(CTI_BACK_UP_KEY,JSON.stringify(ctiBackupData),1);
	//cti_manager_console_log(ctiBackupData);
}
function saveCtiBackUp(obj) {
	//console.log("프로세스확인86");
	//console.log("saveCtiBackUp:"+JSON.stringify(obj));
	
	//고광훈 추가 - cti ivr 강제로 세팅
	
/*	
	var form = document.getElementById('dialForm');	
	var menukey= obj.IVR_MENUPATH;
	var form = document.getElementById('dialForm');	
	if(menukey=='IVHI202310191542332002'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_007';
		form.HCALL_IVR.value = '온라인 교육신청 및 수강방법';
	}
	
	if(menukey=='IVHI202310191548112013'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_008';
		form.HCALL_IVR.value = '로그인 방법 및 시스템 오류 문의';
	}
	
	if(menukey=='IVHI202310191549491003'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_009';
		form.HCALL_IVR.value = '교육 이수 확인 및 이수증 발급';
	}
	
	if(menukey=='IVHI202310191550321008'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_010';
		form.HCALL_IVR.value = '낚시어선 신규.재개자 전문교육';
	}
	
	if(menukey=='IVHI202310191552321010'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_011';
		form.HCALL_IVR.value = '교육 과정 안내 및 기타 문의';
	}
	
	if(menukey=='IVHI202310191553231012'){
		form.HCALL_IVR_CD.value = 'IMNUFIPA_006';
		form.HCALL_IVR.value = '지자체, 해양경찰 등 낚시전문교육 담당자';
	}	
*/
	
	
	
	var d = new Date();
    var currentDate = d.getFullYear() + "-" + numberPad(( d.getMonth() + 1 ),2) + "-" + numberPad(d.getDate(),2);
    var currentTime = numberPad(d.getHours(),2) + ":" + numberPad(d.getMinutes(),2) + ":" + numberPad(d.getSeconds(),2);
    cti_manager_console_log('...saveCtiBackUp(' + currentDate+' '+currentTime + ')');
    obj.saveTime = currentDate+' '+currentTime;
    obj.saveBackupKey = CTI_BACK_UP_KEY;    
    cti_manager_console_log(ctiBackupData['cti']);
    cti_manager_console_log(obj);
	ctiBackupData['cti'].push(obj);
	deleteCtiCookie(CTI_BACK_UP_KEY);
	setCtiCookie(CTI_BACK_UP_KEY,JSON.stringify(ctiBackupData),1);
	cti_manager_console_log(ctiBackupData);	
}
function removeCtiBackUp() {
	//console.log("프로세스확인87");
	cti_manager_console_log('...removeCtiBackUp');
	cti_manager_console_log(getCtiCookie(CTI_BACK_UP_KEY));
	deleteCtiCookie(CTI_BACK_UP_KEY);
	cti_manager_console_log(getCtiCookie(CTI_BACK_UP_KEY));
}
function chkLoadCtiBackupData() {
	//console.log("프로세스확인88");
	cti_manager_console_log('...chkLoadCtiBackupData');
	//무언가 잘 안됨. 확인이 필요함.
	if(CTI_BACK_UP_KEY!=null && CTI_BACK_UP_KEY.length!=0 && CTI_BACK_UP_KEY!='') {
		var backdata = getCtiCookie(CTI_BACK_UP_KEY);
		cti_manager_console_log('CTI_BACK_UP_KEY : ' + CTI_BACK_UP_KEY);	
		if(backdata!=null && backdata.length!=0 && backdata!='' && typeof backdata != "undefined") {
			/*
			try {
				backdata = JSON.parse(backdata);
		    } catch (error) {
		    	backdata = ctiBackupData;
		    }	
			cti_manager_console_log(backdata);
			if(backdata['cti']!=null && backdata['cti'].length!=0 && backdata['cti']!='' && typeof backdata['cti'] != "undefined") {
				cti_manager_console_log('..cti data 확인');
				var tempCtiObj = null;
				var isCtiObjChk = false;
				if(backdata['cti'].length > 0) {
					var lastnum = backdata['cti'].length-1;
					for(var i=0; i<=backdata['cti'].length; i++) {
						if(lastnum == i) {
							cti_manager_console_log('ctiBackupData-cti : ' + lastnum);
							tempCtiObj = backdata['cti'][i];
							isCtiObjChk = true;
						}
					}
				}
				if(isCtiObjChk) {
					cti_manager_console_log('..종료되지 않은 콜상태 검증(잘 작동하지 않아서 검증이 필요함.)');
					if(tempCtiObj!=null && (tempCtiObj.isbell || tempCtiObj.iscallcatch || tempCtiObj.issendcall || tempCtiObj.isreceivecall || tempCtiObj.istransfercall || tempCtiObj.isretransfercall)) {
						cti_manager_console_log(tempCtiObj);
						cti_manager_console_log('..강제종료가 필요함');
						//returnRefreshCallEnd(deepClone(ctiObj));
						clearCtiBackup();
					}
				}
				ctiBackupData['cti'] = backdata['cti'];
			}
			if(backdata['raw']!=null && backdata['raw'].length!=0 && backdata['raw']!='' && typeof backdata['raw'] != "undefined") {
				cti_manager_console_log('..raw data 확인');
				ctiBackupData['raw'] = backdata['raw'];
			}
			*/
		} else {
			cti_manager_console_log('..백업데이터 없음');
		}
		cti_manager_console_log('cti data length : ' + ctiBackupData['cti'].length);
		cti_manager_console_log('raw data length : ' + ctiBackupData['raw'].length);
	}
}
setTimeout(function(){
	chkLoadCtiBackupData();
},1000);
function clearCtiBackup() {
	//console.log("프로세스확인89");
	//set ctiObj
	ctiObj.cticleartime = getCallDate(); 
	ctiObj.iserror = false;
	ctiObj.ispause = false;
	ctiObj.iswork = false;
	ctiObj.isholdcall = false;
	ctiObj.isbell = false;
	ctiObj.iscallcatch = false;
	ctiObj.issendcall = false;
	ctiObj.isreceivecall = false;
	ctiObj.istransfercall = false;
	ctiObj.isretransfercall = false;
	ctiObj.isrecidcatch = false;
	ctiObj.callst = '';
	ctiObj.ivrinfo = '';
	ctiObj.ivrinfocd = '';
	ctiObj.ivrinfolabel = '';
	ctiObj.actlabel = '[초기화(대기)]';
	ctiObj.callkey = '';
	ctiObj.callnum = '';
	ctiObj.calldt = '';
	ctiObj.calltransfernum = '';
	ctiObj.ctiid = '';
	ctiObj.ctitelno = '';
	ctiObj.label = '';
	ctiObj.label_sub = '';
	ctiObj.strAgreeMsg = '';
	ctiObj.callStartTime = 0;
	ctiObj.callEndTime = 0;
	ctiObj.bellStartTime = 0;
	ctiObj.bellEndTime = 0;
	ctiObj.holdStartTime = 0;
	ctiObj.holdEndTime = 0;
	ctiObj.callTime = 0;
	ctiObj.bellTime = 0;
	ctiObj.holdTime= 0;
	ctiObj.pauseTime = 0;
	ctiObj.totalCallTime = 0;	
	ctiObj.delayTime = 0;
	ctiObj.vlgRecordID = '';
	ctiObj.vlgTableName = '';
	ctiObj.rawdata = null;
	saveCtiBackUp(deepClone(ctiObj));
	//End of set ctiObj
	removeCtiBackUp();
}
function cti_manager_console_log(msg) {
	//console.log("프로세스확인90");
	//console.log(msg);
	//console.log("console.log(msg)")
}
function cti_vlg_console_log(msg) {
	//console.log("프로세스확인91");
	//console.log(msg);
}
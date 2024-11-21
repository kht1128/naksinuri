
var ws;
var jsonMsg;

onmessage = function(event){
	
	jsonMsg = event.data;
	var valID = jsonMsg.MSGID;
	
	console.log("vglap실행:"+JSON.stringify(jsonMsg));
	
	if(valID == "ConnectServer")
	{
		var valParam = jsonMsg.PARAM;
		
		ws = new WebSocket(valParam, 'vlgap');
		ws.onopen = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Event";
			message.REASON = '<li><span class="badge badge-success">websocket opened</span></li>';
			
			postMessage(message);
			
			postButtonStatus(false, true, true, false, false, false, false, false, false);
		};

		ws.onerror = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Event";
			message.REASON = '<li><span class="badge badge-important">websocket error</span></li>';
			
			postMessage(message);
		};

		ws.onmessage = function(event) {
			
			var objJsonMsg = JSON.parse(event.data);
			var strKind = objJsonMsg.HEADER.MSGKIND;			
			
			if( strKind == "Event" )
			{
				ProcEvent(objJsonMsg);
			}
			else if( strKind == "Response")
			{
				ProcResp(objJsonMsg);
				UpdateButtonStatus(objJsonMsg);
			}
		};

		ws.onclose = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Event";
			message.REASON = '<li><span class="badge badge-important">websocket closed</span></li>';
			
			postMessage(message);
			postButtonStatus(true, false, false, false, false, false, false, false, false)
		};
	}
	else if(valID == "DisconnectServer")
	{
		ws.close();
	}
	else if(valID == "Login")
	{	
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		console.log('Login' + ' AgtId: ' + '  TelNo : ' + telno);
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "AgentLogin_out";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.LOGIN_OUT = "Login";
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "Logout")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "AgentLogin_out";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.LOGIN_OUT = "Logout";
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "StartRecord")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		//head.MSGID = "RecordCommand";
		head.MSGID = "APRecordCommand";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.COMMAND = "Start";
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "StopRecord")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		//head.MSGID = "RecordCommand";
		head.MSGID = "APRecordCommand";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.COMMAND = "Stop";
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "GetRecordID")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "ReqRecordID";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "GetAPRecordID")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "ReqAPRecordID";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.AGENTID = agtid;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}	
	else if(valID == "SaveCstInfo")
	{
		var agtid = jsonMsg.AGTID;
		var telno = jsonMsg.TELNO;
		var cstfieldid1 = "10001";
		var cstfieldid2 = "10002";
		var cstfieldid3 = "10003";	
		var cstinfo1 = jsonMsg.CSTINFO1;
		var cstinfo2 = jsonMsg.CSTINFO2;
		var cstinfo3 = jsonMsg.CSTINFO3;
		var recordid = jsonMsg.RECORDID;
		var calltype = jsonMsg.CALLTYPE
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "SaveCstInfo";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.AGENTID = agtid;
		body.TELNUM = telno;		
		
		// CSTFIELDID 협의후 결정
		// ex) 고객명 = 10001, 전화번호 = 10002, 주민번호 = 10003
		var cstinfoArray = new Array();
		// 고객명
		var cstinfo = new Object();
		cstinfo.CSTFIELDID = cstfieldid1;
		cstinfo.CSTDATA = cstinfo1;
		cstinfoArray.push(cstinfo);
		// 전화번호
		cstinfo = new Object();
		cstinfo.CSTFIELDID = cstfieldid2;
		cstinfo.CSTDATA = cstinfo2;
		cstinfoArray.push(cstinfo);		
		// 주민번호
		cstinfo = new Object();
		cstinfo.CSTFIELDID = cstfieldid3;
		cstinfo.CSTDATA = cstinfo3;
		cstinfoArray.push(cstinfo);

		body.ADDINFO = cstinfoArray;
		body.FIELDCOUNT = "3";
		
		body.RECORDID = recordid;
		body.CALLTYPE = calltype;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "RegLink")
	{		
		var telno = jsonMsg.TELNO;
		var devid = jsonMsg.DEVID;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgLink";
		head.MSGID = "RegLink";
		head.MSGKIND = "Request";
		
		var body = new Object();		
		body.TELNUM = telno;
		body.DEVICEID = devid;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);		
	}	
}


function postButtonStatus(bConnect, bDisconnect, bLogin, bLogout, bStartRecord, bStopRecord, bGetRecordID, bGetAPRecordID, bSaveCstInfo)
{
	var message = new Object();
	message.MSGID = "ButtonStatus";
	message.MSGKIND = "Event";
	
	if(bConnect)			message.CONNECT = "on";			else		message.CONNECT = "off";
	if(bDisconnect)			message.DISCONNECT = "on";		else		message.DISCONNECT = "off";
	if(bLogin)				message.LOGIN = "on";			else		message.LOGIN = "off";
	if(bLogout)				message.LOGOUT = "on";			else		message.LOGOUT = "off";
	if(bStartRecord)		message.RECSTART = "on";		else		message.RECSTART = "off";
	if(bStopRecord)			message.RECSTOP = "on";			else		message.RECSTOP = "off";
	if(bGetRecordID)		message.GETRECORDID = "on";		else		message.GETRECORDID = "off";
	if(bGetAPRecordID)		message.GETAPRECORDID = "on";	else		message.GETAPRECORDID = "off";
	if(bSaveCstInfo)		message.SAVECSTINFO = "on";		else		message.SAVECSTINFO = "off";	
	
	postMessage(message);	
}


function UpdateButtonStatus(objJsonMsg)
{
	var bConnect = false;
	var bDisconnect = true;
	var bLogin = false;
	var bLogout = false;
	var bStartRecord = false;
	var bStopRecord = false;
	var bGetRecordID = false;
	var bGetAPRecordID = false;
	var bSaveCstInfo = false;
	
	var msgId = objJsonMsg.HEADER.MSGID;
	var msgKind = objJsonMsg.HEADER.MSGKIND;
	if(msgKind == "Response")
	{
		if(msgId == "AgentLogin_out")
		{
			var result = objJsonMsg.BODY.RESULT;
			var loginout = objJsonMsg.BODY.LOGIN_OUT;
			
			if(result == "Success")
			{
				if(loginout == "Login")
				{
					bLogout = true;
					bStartRecord = true;
					bStopRecord = true;
					bGetRecordID = true;
					bGetAPRecordID = true;
					bSaveCstInfo = true;
				}
				else
				{
					bLogin = true;
					bStartRecord = false;
					bStopRecord = false;
					bGetRecordID = false;
					bGetAPRecordID = false;
					bSaveCstInfo = false;
				}
			}
			else
			{
				return;
			}		
		}
		else
		{
			return;
		}
	}
	else if(msgKind == "Event")
	{
		return;		
	}

	postButtonStatus(bConnect, bDisconnect, bLogin, bLogout, bStartRecord, bStopRecord, bGetRecordID, bGetAPRecordID, bSaveCstInfo);
}


function ProcEvent(objJsonMsg)
{
	var message = new Object;
	var valID = objJsonMsg.HEADER.MSGID;
	if(valID == "ChannelEvt")
	{
		var telno = objJsonMsg.BODY.TELNUM;
		var state = objJsonMsg.BODY.STATE;
		
		message.MSGID = "ChannelEvt";
		message.MSGKIND = "Event";
		message.TELNO = telno;
		message.STATE = state;
		
		if(state == "Recording")
		{
			var recordid = objJsonMsg.BODY.RECORDID;
			message.RECORDID = recordid;
		}			
		
		postMessage(message);
	}
	else
	{
		postMessage(objJsonMsg);	
	}	
}


function ProcResp(objJsonMsg)
{
	var message = new Object;
	var valID = objJsonMsg.HEADER.MSGID;
	if(valID == "AgentLogin_out")
	{
		message.MSGID = objJsonMsg.BODY.LOGIN_OUT;
	}
	else
	{
		message.MSGID = valID;
	}
	message.MSGKIND = "Resp";
	message.RESULT = objJsonMsg.BODY.RESULT;
	message.REASON = objJsonMsg.BODY.REASON;
		
	if(valID == "ReqRecordID" || valID == "ReqAPRecordID")
	{
		message.RECORDID = objJsonMsg.BODY.RECORDID;
	}
	
	postMessage(message);
}

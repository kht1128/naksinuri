
var wsvlg;
var jsonVlgMsg;

onmessage = function(event){
	
	jsonVlgMsg = event.data;
	var valID = jsonVlgMsg.MSGID;
	
	if(valID == "ConnectServer")
	{
		var valParam = jsonVlgMsg.PARAM;

		wsvlg = new WebSocket(valParam, 'vlgap');
		wsvlg.onopen = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Open";
			message.REASON = '<li><span class="badge badge-success">websocket opened</span></li>';
			
			postMessage(message);
			
			postButtonStatusVlg(false, true, true, false, false, false, false, false, false);
		};

		wsvlg.onerror = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Error";
			message.REASON = '<li><span class="badge badge-important">websocket error</span></li>';
			
			postMessage(message);
		};

		wsvlg.onmessage = function(event) {
			
			var objjsonVlgMsg = JSON.parse(event.data);
			var strKind = objjsonVlgMsg.HEADER.MSGKIND;			
			
			if( strKind == "Event" )
			{
				ProcEventVlg(objjsonVlgMsg);
			}
			else if( strKind == "Response")
			{
				ProcRespVlg(objjsonVlgMsg);
				UpdateButtonStatusVlg(objjsonVlgMsg);
			}
		};

		wsvlg.onclose = function() {
			var message = new Object();
			message.MSGID = "WebSocket";
			message.MSGKIND = "Event";
			message.REASON = '<li><span class="badge badge-important">websocket closed</span></li>';
			
			postMessage(message);
			postButtonStatusVlg(true, false, false, false, false, false, false, false, false)
		};
	}
	else if(valID == "DisconnectServer")
	{
		wsvlg.close();
	}
	else if(valID == "Login")
	{	
		var agtid = jsonVlgMsg.AGTID;
		var agtnm = jsonVlgMsg.AGTNM;
		var telno = jsonVlgMsg.TELNO;
		
		//console.log('Login' + ' AgtId: ' + agtid + '  AgtNm : ' + agtnm + '  TelNo : ' + telno);
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "AgentLogin_out";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.LOGIN_OUT = "Login";
		body.AGENTID = agtid;
		body.AGENTNAME = agtnm;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		wsvlg.send(jsonStr);
	}
	else if(valID == "Logout")
	{
		var agtid = jsonVlgMsg.AGTID;
		var agtnm = jsonVlgMsg.AGTNM;
		var telno = jsonVlgMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgSvr";
		head.MSGID = "AgentLogin_out";
		head.MSGKIND = "Request";
		
		var body = new Object();
		body.LOGIN_OUT = "Logout";
		body.AGENTID = agtid;
		body.AGENTNAME = agtnm;
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		wsvlg.send(jsonStr);
	}
	else if(valID == "StartRecord")
	{
		var agtid = jsonVlgMsg.AGTID;
		var telno = jsonVlgMsg.TELNO;
		
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
		wsvlg.send(jsonStr);
	}
	else if(valID == "StopRecord")
	{
		var agtid = jsonVlgMsg.AGTID;
		var telno = jsonVlgMsg.TELNO;
		
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
		wsvlg.send(jsonStr);
	}
	else if(valID == "GetRecordID")
	{
		var agtid = jsonVlgMsg.AGTID;
		var telno = jsonVlgMsg.TELNO;
		
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
		wsvlg.send(jsonStr);
	}
	else if(valID == "GetAPRecordID")
	{
		var agtid = jsonVlgMsg.AGTID;
		var telno = jsonVlgMsg.TELNO;
		
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
		wsvlg.send(jsonStr);
	}	
	else if(valID == "SaveCstInfo")
	{
		var agtid = jsonVlgMsg.AGTID;
		var telno = jsonVlgMsg.TELNO;
		var cstfieldid1 = "10001";
		var cstfieldid2 = "10002";
		var cstfieldid3 = "10003";	
		var cstinfo1 = jsonVlgMsg.CSTINFO1;
		var cstinfo2 = jsonVlgMsg.CSTINFO2;
		var cstinfo3 = jsonVlgMsg.CSTINFO3;
		var recordid = jsonVlgMsg.RECORDID;
		var calltype = jsonVlgMsg.CALLTYPE
		
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
		wsvlg.send(jsonStr);
	}
	else if(valID == "RegLink")
	{		
		var telno = jsonVlgMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.MSGSRC = "TlpVlgAp";
		head.MSGDEST = "TlpVlgLink";
		head.MSGID = "RegLink";
		head.MSGKIND = "Request";
		
		var body = new Object();		
		body.TELNUM = telno;
		
		message.HEADER = head;
		message.BODY = body;

		var jsonStr = JSON.stringify(message);
		wsvlg.send(jsonStr);		
	}	
}


function postButtonStatusVlg(bConnect, bDisconnect, bLogin, bLogout, bStartRecord, bStopRecord, bGetRecordID, bGetAPRecordID, bSaveCstInfo)
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


function UpdateButtonStatusVlg(objjsonVlgMsg)
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
	
	var msgId = objjsonVlgMsg.HEADER.MSGID;
	var msgKind = objjsonVlgMsg.HEADER.MSGKIND;
	if(msgKind == "Response")
	{
		if(msgId == "AgentLogin_out")
		{
			var result = objjsonVlgMsg.BODY.RESULT;
			var loginout = objjsonVlgMsg.BODY.LOGIN_OUT;
			
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

	postButtonStatusVlg(bConnect, bDisconnect, bLogin, bLogout, bStartRecord, bStopRecord, bGetRecordID, bGetAPRecordID, bSaveCstInfo);
}


function ProcEventVlg(objjsonVlgMsg)
{
	var message = new Object;
	var valID = objjsonVlgMsg.HEADER.MSGID;
	if(valID == "ChannelEvt")
	{
		var telno = objjsonVlgMsg.BODY.TELNUM;
		var state = objjsonVlgMsg.BODY.STATE;
		
		message.MSGID = "ChannelEvt";
		message.MSGKIND = "Event";
		message.TELNO = telno;
		message.STATE = state;
		
		if(state == "Recording")
		{
			var recordid = objjsonVlgMsg.BODY.RECORDID;
			message.RECORDID = recordid;
		}			
		
		postMessage(message);
	}
	else
	{
		postMessage(objjsonVlgMsg);	
	}	
}


function ProcRespVlg(objjsonVlgMsg)
{
	var message = new Object;
	var valID = objjsonVlgMsg.HEADER.MSGID;
	if(valID == "AgentLogin_out")
	{
		message.MSGID = objjsonVlgMsg.BODY.LOGIN_OUT;
	}
	else
	{
		message.MSGID = valID;
	}
	message.MSGKIND = "Resp";
	message.RESULT = objjsonVlgMsg.BODY.RESULT;
	message.REASON = objjsonVlgMsg.BODY.REASON;
		
	if(valID == "ReqRecordID" || valID == "ReqAPRecordID")
	{
		message.RECORDID = objjsonVlgMsg.BODY.RECORDID;
	}
	
	postMessage(message);
}

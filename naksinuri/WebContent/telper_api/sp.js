
var ws;
var jsonMsg;
var bError = true;

onmessage = function(event){
	
	jsonMsg = event.data;
	
	//console.log("고광훈추가:"+JSON.stringify(jsonMsg));
	//console.log("고광훈추가:"+JSON.stringify(ws));
	
	var valID = jsonMsg.ID;
	
	if(valID == "ConnectServer")
	{
		var valParam = jsonMsg.PARAM;
		
		// ws = new WebSocket(valParam);
		ws = new WebSocket(valParam, 'softphone');
		ws.onopen = function() {
			var message = new Object();
			message.ID = "WebSocket";
			message.KIND = "Open";
			message.REASON = '<li><span class="badge badge-success">websocket opened</span></li>';
			
			postMessage(message);
			
			postButtonStatus(false, true, 
							true, false, 
							false,
							false, false, false, false, false, false, false, false, false, false,
							false, false, false, false, false, false, false, false, false, false, false, false,
							false, false, false, false, false,
							false, false, false, false, false, false, false, false, false);
		};

		ws.onerror = function() {
			var message = new Object();
			message.ID = "WebSocket";
			message.KIND = "Error";
			message.REASON = '<li><span class="badge badge-important">websocket error</span></li>';
			
			postMessage(message);
		};

		ws.onmessage = function(event) {
			
			var objJsonMsg = JSON.parse(event.data);
			var strKind = objJsonMsg.HEAD.KIND;
			
			if( strKind == "Event" )
			{
				ProcEvent(objJsonMsg);
				UpdateButtonStatus(objJsonMsg);
			}
			else if( strKind == "Response")
			{
				ProcResp(objJsonMsg);
			}
		};

		ws.onclose = function() {
			var message = new Object();
			message.ID = "WebSocket";
			message.KIND = "Close";
			message.REASON = '<li><span class="badge badge-important">websocket closed</span></li>';
			message.ERROR_YN = bError ? "Y" : "N";
			
			postMessage(message);
			postButtonStatus(true, false, 
							false, false, 
							false,
							false, false, false, false, false, false, false, false, false, false,
							false, false, false, false, false, false, false, false, false, false, false, false,
							false, false, false, false, false,
							false, false, false, false, false, false, false, false, false);
							
			bError = true;
		};
	}
	else if(valID == "DisconnectServer")
	{
		bError = false;
		ws.close();
	}
	else if(valID == "MonitorRegist")
	{
		
		console.log('MonitorRegist' + ' Telno: ' + telno);
	
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "MonitorRegist";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;				
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "MonitorUnregist")
	{
		
		console.log('MonitorUnregist' + ' Telno: ' + telno);
	
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "MonitorUnregist";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;				
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "Login")
	{
		
		console.log('Login' + ' Telno: ' + telno + '  CtiID : ' + ctiid);
	
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_LOGIN";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
	}
	else if(valID == "Logout")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_LOGOUT";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Avail")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_AVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail0")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_0";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail1")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_1";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail2")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_2";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail3")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_3";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail4")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_4";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail5")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_5";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail6")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_6";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail7")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_7";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail8")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_8";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Unavail9")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_UNAVAIL";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.SUBSTATE = "TLP_USER_STATE_UNAVAIL_9";					
		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work0")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_0";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work1")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_1";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work2")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_2";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work3")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_3";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work4")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_4";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work5")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_5";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work6")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_6";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work7")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_7";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work8")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_8";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "Work9")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_9";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "WorkACW")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_ACW";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "WorkCTF")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TLP_USER_STATE_WORK";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
		body.SUBSTATE = "TLP_USER_STATE_WORK_CTF";
												
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "MakeCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var called = jsonMsg.DESTTELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "MakeCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
		body.CALLED_TEL = called;
									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "AnswerCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "AnswerCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "DisconnectCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "DisconnectCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "HoldCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "HoldCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "RetrieveCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "RetrieveCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "ConsultationCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var called = jsonMsg.DESTTELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "ConsultationCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;						
		body.CALLED_TEL = called;
									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "ReconnectCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "ReconnectCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "TransferCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "TransferCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;					
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "AlternateCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "AlternateCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "ConferenceCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "ConferenceCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;				
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
				
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SingleStepTransferCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var called = jsonMsg.DESTTELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SSTransferCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;			
		body.CALLED_TEL = called;		
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SingleStepConferenceCall")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var called = jsonMsg.DESTTELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SSConferenceCall";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;			
		body.CALLED_TEL = called;		
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "GroupPickup")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "GroupPickup";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;	
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SendDTMF")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var dtmf = jsonMsg.DTMFSTRING;
		
		// dtmf : '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '*', '#'
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SendDTMFTones";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;		
		body.DTMF_STRING = dtmf;		
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SetUEI")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var uei = jsonMsg.UEI;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SetUEI";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;		
		body.UEI = uei;		
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "GetUEI")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "GetUEI";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;			
																									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "GetQueueInfo")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		var queuetelno = jsonMsg.QUEUETEL;
		
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "GetQueueInfo";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;
		body.QUEUE_TEL = queuetelno;
																									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "GetIvrInfo")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "GetIvrInfo";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;			
																									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "GetMyStatus")
	{
		var telno = jsonMsg.TELNO;
		var ctiid = jsonMsg.CTIID;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "GetMyStatus";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;
		body.CTI_ID = ctiid;			
																									
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "MicrophoneMute")
	{
		var telno = jsonMsg.TELNO;
		var onoff = jsonMsg.ONOFF;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "MicrophoneMute";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;	
		body.FEATURE_ONOFF = onoff;							
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SpeakerMute")
	{
		var telno = jsonMsg.TELNO;
		var onoff = jsonMsg.ONOFF;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SpeakerMute";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;	
		body.FEATURE_ONOFF = onoff;							
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SpeakerVolume")
	{
		var telno = jsonMsg.TELNO;
		var value = jsonMsg.VALUE;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "SpeakerVolume";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;		
		body.FEATURE_VOLUME = value;
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "QueryDevice")
	{
		var telno = jsonMsg.TELNO;
		var value = jsonMsg.VALUE;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "QueryDevice";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;		
		body.FEATURE_VALUE = value;								
																		
		message.HEAD = head;
		message.BODY = body;					

		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "SendPing")
	{
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "Ping";
		head.KIND = "Event";
		
		var body = new Object();
		body.TEL = telno;		
		
		message.HEAD = head;
		message.BODY = body;	
		
		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	else if(valID == "CtfStart")
	{
		var telno = jsonMsg.TELNO;
		
		var message = new Object();
		var head = new Object();
		head.SOURCE = "SoftPhone";
		head.DEST = "TlpCtiPClt";
		head.ID = "CTFStart";
		head.KIND = "Request";
		
		var body = new Object();
		body.TEL = telno;		
		
		message.HEAD = head;
		message.BODY = body;	
		
		var jsonStr = JSON.stringify(message);
		ws.send(jsonStr);
		
//		var dt = new Date();
//		dt = dt.getFullYear() + "/" + dt.getMonth() + "/" + dt.getDate() + "-" + dt.getHours() + ":" +  dt.getMinutes() + ":" + dt.getSeconds() + ":" + dt.getMilliseconds();
//		$('#log').append('<li>' + "[" + dt + "] " + 'sended: <span class="badge">' + jsonStr + '</span></li>');
	}
	
}

function postButtonStatus(bConnect, bDisconnect, 
						bLogin, bLogout, 
						bAvail, 
						bUnavail0, bUnavail1, bUnavail2, bUnavail3, bUnavail4, bUnavail5, bUnavail6, bUnavail7, bUnavail8, bUnavail9,
						bWork0, bWork1, bWork2, bWork3, bWork4, bWork5, bWork6, bWork7, bWork8, bWork9, bWorkACW, bWorkCTF,
						bMakeCall, bAnswerCall, bDisconnectCall, bHoldCall, bRetrieveCall,
						bConsultationCall, bReconnectCall, bAlternateCall, bTransferCall, bConferenceCall, bSingleStepTransferCall, bSingleStepConferenceCall, bGroupPickup, bCtf){
	
	var message = new Object();
	message.ID = "ButtonStatus";
	message.KIND = "Event";
	if(bConnect)					message.CONNECT = "on";						else		message.CONNECT = "off";
	if(bDisconnect)					message.DISCONNECT = "on";					else		message.DISCONNECT = "off";
	if(bLogin)						message.LOGIN = "on";						else		message.LOGIN = "off";
	if(bLogout)						message.LOGOUT = "on";						else		message.LOGOUT = "off";
	if(bAvail)						message.AVAIL = "on";						else		message.AVAIL = "off";
	if(bUnavail0)					message.UNAVAIL0 = "on";					else		message.UNAVAIL0 = "off";
	if(bUnavail1)					message.UNAVAIL1 = "on";					else		message.UNAVAIL1 = "off";
	if(bUnavail2)					message.UNAVAIL2 = "on";					else		message.UNAVAIL2 = "off";
	if(bUnavail3)					message.UNAVAIL3 = "on";					else		message.UNAVAIL3 = "off";
	if(bUnavail4)					message.UNAVAIL4 = "on";					else		message.UNAVAIL4 = "off";
	if(bUnavail5)					message.UNAVAIL5 = "on";					else		message.UNAVAIL5 = "off";
	if(bUnavail6)					message.UNAVAIL6 = "on";					else		message.UNAVAIL6 = "off";
	if(bUnavail7)					message.UNAVAIL7 = "on";					else		message.UNAVAIL7 = "off";
	if(bUnavail8)					message.UNAVAIL8 = "on";					else		message.UNAVAIL8 = "off";
	if(bUnavail9)					message.UNAVAIL9 = "on";					else		message.UNAVAIL9 = "off";
	if(bWork0)						message.WORK0 = "on";						else		message.WORK0 = "off";
	if(bWork1)						message.WORK1 = "on";						else		message.WORK1 = "off";
	if(bWork2)						message.WORK2 = "on";						else		message.WORK2 = "off";
	if(bWork3)						message.WORK3 = "on";						else		message.WORK3 = "off";
	if(bWork4)						message.WORK4 = "on";						else		message.WORK4 = "off";
	if(bWork5)						message.WORK5 = "on";						else		message.WORK5 = "off";
	if(bWork6)						message.WORK6 = "on";						else		message.WORK6 = "off";
	if(bWork7)						message.WORK7 = "on";						else		message.WORK7 = "off";
	if(bWork8)						message.WORK8 = "on";						else		message.WORK8 = "off";
	if(bWork9)						message.WORK9 = "on";						else		message.WORK9 = "off";
	if(bWorkACW)					message.WORKACW = "on";						else		message.WORKACW = "off";
	if(bWorkCTF)					message.WORKCTF = "on";						else		message.WORKCTF = "off";
	if(bMakeCall)					message.MAKECALL = "on";					else		message.MAKECALL = "off";
	if(bAnswerCall)					message.ANSWERCALL = "on";					else		message.ANSWERCALL = "off";
	if(bDisconnectCall)				message.DISCONNECTCALL = "on";				else		message.DISCONNECTCALL = "off";
	if(bHoldCall)					message.HOLDCALL = "on";					else		message.HOLDCALL = "off";
	if(bRetrieveCall)				message.RETRIEVECALL = "on";				else		message.RETRIEVECALL = "off";
	if(bConsultationCall)			message.CONSULTATIONCALL = "on";			else		message.CONSULTATIONCALL = "off";
	if(bReconnectCall)				message.RECONNECTCALL = "on";				else		message.RECONNECTCALL = "off";
	if(bAlternateCall)				message.ALTERNATECALL = "on";				else		message.ALTERNATECALL = "off";
	if(bTransferCall)				message.TRANSFERCALL = "on";				else		message.TRANSFERCALL = "off";
	if(bConferenceCall)				message.CONFERENCECALL = "on";				else		message.CONFERENCECALL = "off";
	if(bSingleStepTransferCall)		message.SINGLESTEPTRANSFERCALL = "on";		else		message.SINGLESTEPTRANSFERCALL = "off";
	if(bSingleStepConferenceCall)	message.SINGLESTEPCONFERENCECALL = "on";	else		message.SINGLESTEPCONFERENCECALL = "off";
	if(bGroupPickup)				message.GROUPPKUP = "on";					else		message.GROUPPKUP = "off";
	if(bCtf)						message.CTF = "on";							else		message.CTF = "off";
	
	postMessage(message);
}

	
function UpdateButtonStatus(objJsonMsg)	{
	var bConnect = false;
	var bDisconnect = true;
	var bLogin = false;
	var bLogOut = false;
	var bAvail = false;
	var bUnavail0 = false;
	var bUnavail1 = false;
	var bUnavail2 = false;
	var bUnavail3 = false;
	var bUnavail4 = false;
	var bUnavail5 = false;
	var bUnavail6 = false;
	var bUnavail7 = false;
	var bUnavail8 = false;
	var bUnavail9 = false;
	var bWork0 = false;
	var bWork1 = false;
	var bWork2 = false;
	var bWork3 = false;
	var bWork4 = false;
	var bWork5 = false;
	var bWork6 = false;
	var bWork7 = false;
	var bWork8 = false;
	var bWork9 = false;
	var bWorkACW = false;
	var bWorkCTF = false;
	var bMakeCall = false;
	var bAnswerCall = false;
	var bDisconnectCall = false;
	var bHoldCall = false;
	var bRetrieveCall = false;
	var bConsultationCall = false;
	var bReconnectCall = false;
	var bAlternateCall = false;
	var bTransferCall = false;
	var bConferenceCall = false;
	var bSingleStepTransferCall = false;
	var bSingleStepConferenceCall = false;
	var bGroupPickup = false;
	var bCtf = false;
	
	if(objJsonMsg.HEAD.ID == "CTI_EVNT_POPUP")
		return;
	
	var objStatus = objJsonMsg.BODY.STATUS;
	if( objStatus.LOGIN_YN == "N")
	{
		bLogin = true;
		postButtonStatus(bConnect, bDisconnect, 
							bLogin, bLogOut, 
							bAvail,
							bUnavail0, bUnavail1, bUnavail2, bUnavail3, bUnavail4, bUnavail5, bUnavail6, bUnavail7, bUnavail8, bUnavail9,
							bWork0, bWork1, bWork2, bWork3, bWork4, bWork5, bWork6, bWork7, bWork8, bWork9, bWorkACW, bWorkCTF,
							bMakeCall, bAnswerCall, bDisconnectCall, bHoldCall, bRetrieveCall,
							bConsultationCall, bReconnectCall, bAlternateCall, bTransferCall, bConferenceCall, bSingleStepTransferCall, bSingleStepConferenceCall, bGroupPickup, bCtf);
		return;
	}
	
	if( objStatus.USER_STATUS != "TLP_USER_STATE_AVAIL")
	{
		bLogOut = true;
	}
	
	if( objStatus.USER_STATUS == "TLP_USER_STATE_AVAIL" )
	{
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_0" )
	{
		bAvail = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_1" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_2" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_3" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_4" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_5" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_6" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_7" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_8" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_UNAVAIL_9" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_0" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_1" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_2" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_3" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_4" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_5" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_6" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_7" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_8" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork9 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_9" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWorkACW = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_ACW" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkCTF = true;
	}
	else if( objStatus.USER_STATUS == "TLP_USER_STATE_WORK_CTF" )
	{
		bAvail = true;
		bUnavail0 = true;
		bUnavail1 = true;
		bUnavail2 = true;
		bUnavail3 = true;
		bUnavail4 = true;
		bUnavail5 = true;
		bUnavail6 = true;
		bUnavail7 = true;
		bUnavail8 = true;
		bUnavail9 = true;
		bWork0 = true;
		bWork1 = true;
		bWork2 = true;
		bWork3 = true;
		bWork4 = true;
		bWork5 = true;
		bWork6 = true;
		bWork7 = true;
		bWork8 = true;
		bWork9 = true;
		bWorkACW = true;
	}
	
	var strCallCount = objStatus.CALL_COUNT;
	
	if( strCallCount == "0" )
	{
		if( objStatus.USER_STATUS != "TLP_USER_STATE_AVAIL")
		{
			bMakeCall = true;
			bGroupPickup = true;
		}
					
		postButtonStatus(bConnect, bDisconnect, 
							bLogin, bLogOut, 
							bAvail,
							bUnavail0, bUnavail1, bUnavail2, bUnavail3, bUnavail4, bUnavail5, bUnavail6, bUnavail7, bUnavail8, bUnavail9,
							bWork0, bWork1, bWork2, bWork3, bWork4, bWork5, bWork6, bWork7, bWork8, bWork9, bWorkACW, bWorkCTF,
							bMakeCall, bAnswerCall, bDisconnectCall, bHoldCall, bRetrieveCall,
							bConsultationCall, bReconnectCall, bAlternateCall, bTransferCall, bConferenceCall, bSingleStepTransferCall, bSingleStepConferenceCall, bGroupPickup, bCtf);
		return;
	}
				
	var objCallStatus = objStatus.CALL_STATUS;
	var strStatus0 = objCallStatus[0];
	
	if( strCallCount == "1")
	{
		bDisconnectCall = true;
		
		if( strStatus0 == "CTI_EVNT_RINGING" || strStatus0 == "CTI_EVNT_RINGING_FWD" || strStatus0 == "CTI_EVNT_XFERED" )
		{
			bDisconnectCall = false;
			bAnswerCall = true;	
		}
		else if( strStatus0 == "CTI_EVNT_CONNED" || strStatus0 == "CTI_EVNT_CONNED_PKUP" )
		{
			bConsultationCall = true;
			bHoldCall = true;
			bSingleStepTransferCall = true;
			bCtf = true;
		}
		else if( strStatus0 == "CTI_EVNT_HOLD" )
		{
			bDisconnectCall = false;
			bRetrieveCall = true;
		}
	}
	
	var strStatus1 = objCallStatus[1];
	
	if( strCallCount == "2")
	{		
		if(strStatus1 == "CTI_EVNT_SERV_INITED" || strStatus1 == "CTI_EVNT_NETWK_REACHED")
		{
			bDisconnectCall = true;
			bReconnectCall = true;
		}
		else if (strStatus1 == "CTI_EVNT_DIALING" || strStatus1 == "CTI_EVNT_DIALING_FWD" || strStatus1 == "CTI_EVNT_FAILED")
		{
			bTransferCall = true;
			bReconnectCall = true;
		}							
		else if( strStatus1 == "CTI_EVNT_CONNED")
		{
			bTransferCall = true;
			bReconnectCall = true;
			bConferenceCall = true;
		}
	}
	
	postButtonStatus(bConnect, bDisconnect, 
							bLogin, bLogOut, 
							bAvail,
							bUnavail0, bUnavail1, bUnavail2, bUnavail3, bUnavail4, bUnavail5, bUnavail6, bUnavail7, bUnavail8, bUnavail9,
							bWork0, bWork1, bWork2, bWork3, bWork4, bWork5, bWork6, bWork7, bWork8, bWork9, bWorkACW, bWorkCTF,
							bMakeCall, bAnswerCall, bDisconnectCall, bHoldCall, bRetrieveCall,
							bConsultationCall, bReconnectCall, bAlternateCall, bTransferCall, bConferenceCall, bSingleStepTransferCall, bSingleStepConferenceCall, bGroupPickup, bCtf);
	return;		
}
	
	 
function ProcEvent(objJsonMsg)
{
	var message = new Object;
	var valID = objJsonMsg.HEAD.ID;
	if(valID == "TLP_USER_STATE_LOGIN")
	{
		var telno = objJsonMsg.BODY.TEL;
		var ctiid = objJsonMsg.BODY.CTI_ID;
		
		message.ID = "LOGIN";
		message.KIND = "Event";
		message.TEL = telno;
		message.CTIID = ctiid;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "TLP_USER_STATE_LOGOUT")
	{
		var telno = objJsonMsg.BODY.TEL;
		var ctiid = objJsonMsg.BODY.CTI_ID;
		
		message.ID = "LOGOUT";
		message.KIND = "Event";
		message.TEL = telno;
		message.CTIID = ctiid;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "TLP_USER_STATE_AVAIL")
	{
		var telno = objJsonMsg.BODY.TEL;
		var ctiid = objJsonMsg.BODY.CTI_ID;
		
		message.ID = "AVAIL";
		message.KIND = "Event";
		message.TEL = telno;
		message.CTIID = ctiid;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
				
		postMessage(message);
	}
	else if(valID == "TLP_USER_STATE_UNAVAIL")
	{
		var substate = objJsonMsg.BODY.SUBSTATE;
		var telno = objJsonMsg.BODY.TEL;
		var ctiid = objJsonMsg.BODY.CTI_ID;
		
		if(substate == "TLP_USER_STATE_UNAVAIL_0")
			message.ID = "UNAVAIL0";
		else if(substate == "TLP_USER_STATE_UNAVAIL_1")
			message.ID = "UNAVAIL1";
		else if(substate == "TLP_USER_STATE_UNAVAIL_2")
			message.ID = "UNAVAIL2";
		else if(substate == "TLP_USER_STATE_UNAVAIL_3")
			message.ID = "UNAVAIL3";
		else if(substate == "TLP_USER_STATE_UNAVAIL_4")
			message.ID = "UNAVAIL4";
		else if(substate == "TLP_USER_STATE_UNAVAIL_5")
			message.ID = "UNAVAIL5";
		else if(substate == "TLP_USER_STATE_UNAVAIL_6")
			message.ID = "UNAVAIL6";
		else if(substate == "TLP_USER_STATE_UNAVAIL_7")
			message.ID = "UNAVAIL7";
		else if(substate == "TLP_USER_STATE_UNAVAIL_8")
			message.ID = "UNAVAIL8";
		else if(substate == "TLP_USER_STATE_UNAVAIL_9")
			message.ID = "UNAVAIL9";
		message.KIND = "Event";
		message.TEL = telno;
		message.CTIID = ctiid;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "TLP_USER_STATE_WORK")
	{
		var substate = objJsonMsg.BODY.SUBSTATE;
		var telno = objJsonMsg.BODY.TEL;
		var ctiid = objJsonMsg.BODY.CTI_ID;
		
		if(substate == "TLP_USER_STATE_WORK_0")
			message.ID = "WORK0";
		else if(substate == "TLP_USER_STATE_WORK_1")
			message.ID = "WORK1";
		else if(substate == "TLP_USER_STATE_WORK_2")
			message.ID = "WORK2";
		else if(substate == "TLP_USER_STATE_WORK_3")
			message.ID = "WORK3";
		else if(substate == "TLP_USER_STATE_WORK_4")
			message.ID = "WORK4";
		else if(substate == "TLP_USER_STATE_WORK_5")
			message.ID = "WORK5";
		else if(substate == "TLP_USER_STATE_WORK_6")
			message.ID = "WORK6";
		else if(substate == "TLP_USER_STATE_WORK_7")
			message.ID = "WORK7";
		else if(substate == "TLP_USER_STATE_WORK_8")
			message.ID = "WORK8";
		else if(substate == "TLP_USER_STATE_WORK_9")
			message.ID = "WORK9";
		else if(substate == "TLP_USER_STATE_WORK_ACW")
			message.ID = "WORKACW";
		else if(substate == "TLP_USER_STATE_WORK_CTF")
			message.ID = "WORKCTF";
		message.KIND = "Event";
		message.TEL = telno;
		message.CTIID = ctiid;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_SERV_INITED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "SERVICE_INITIATED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);		
	}
	else if(valID == "CTI_EVNT_ORIGINATED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "ORIGINATED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_NETWK_REACHED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "NETWORK_REACHED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_ALRTED" || valID == "CTI_EVNT_ALRTED_FWD")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "ALERTED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		if(message.CALLCOUNT == '1')
		{
			if(message.STATUS == "CTI_USER_DTL_CALL_TRK_OUT_DIAL" || message.STATUS == "CTI_USER_DTL_CALL_STN_OUT_DIAL")
				message.INOUT = "OUT";
			else if(message.STATUS == "CTI_USER_DTL_CALL_ACD_IN_RING" || message.STATUS == "CTI_USER_DTL_CALL_DID_RING" || message.STATUS == "CTI_USER_DTL_CALL_STN_IN_RING" || message.STATUS == "CTI_USER_DTL_CALL_NONVO_IN_RING" )
				message.INOUT = "IN";
			else 
				message.INOUT = "NONE";
			message.OPPTEL = status.OPPSITE_TEL;
		}
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_CONNED" || valID == "CTI_EVNT_CONNED_PKUP")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "CONNECTED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		if(message.CALLCOUNT == '1')
		{
			if(message.STATUS == "CTI_USER_DTL_CALL_TRK_OUT_TALK" || message.STATUS == "CTI_USER_DTL_CALL_STN_OUT_TALK")
				message.INOUT = "OUT";
			else if(status.USER_CALL_STATUS == "CTI_USER_CALL_ACD" || message.STATUS == "CTI_USER_DTL_CALL_DID_TALK" || message.STATUS == "CTI_USER_DTL_CALL_STN_IN_TALK")
				message.INOUT = "IN";
			else 
				message.INOUT = "NONE";
			message.OPPTEL = status.OPPSITE_TEL;	
			message.TIME = objJsonMsg.BODY.EVENT_TIME;
		}
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_HOLD")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "HOLD";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_HELD")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "HELD";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_RETRVED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "RETRIEVED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_DISCED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "DISCONNECTED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.RELEASING = objJsonMsg.BODY.RELEASING_TEL;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_CXN_CLRED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "CONNECTION_CLEARED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_CONFED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "CONFERENCED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_CONFED_HOLD")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "CONFERENCED_HOLD";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_CONFED_HELD")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "CONFERENCED_HELD";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_DIVED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "DIVERTED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_FAILED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "FAILED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_QED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "QUEUED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_XFERED")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "XFERRED";
		message.KIND = "Event";
		message.CALLCOUNT = status.CALL_COUNT;
		message.STATUS = status.USER_CALL_DETAIL_STATUS;
		message.CALLKEY = objJsonMsg.BODY.CALLKEY;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_OUT_OF_SERVICE")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "OUT_OF_SERVICE";
		message.KIND = "Event";
		
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_IN_SERVICE")
	{
		var status = objJsonMsg.BODY.STATUS;
		
		message.ID = "IN_SERVICE";
		message.KIND = "Event";
		
		postMessage(message);
	}
	else if(valID == "Pong")
	{
		message.ID = "PONG";
		message.KIND = "Event";
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
				
		postMessage(message);
	}
	else if(valID == "CTI_EVNT_POPUP")
	{
		message.ID = "POPUP";
		message.KIND = "Event";
		message.QUEUEDUR = objJsonMsg.BODY.QUEUE_DUR;
		message.CALLING = objJsonMsg.BODY.CALLING_TEL;
		message.IVR_MENUCODE = objJsonMsg.BODY.IVR_MENUCODE;
		message.IVR_INPUTINFO = objJsonMsg.BODY.IVR_INPUTINFO;
		message.IVR_MENUPATH = objJsonMsg.BODY.IVR_MENUPATH;
		message.TIME = objJsonMsg.BODY.EVENT_TIME;
		
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
	message.ID = GetRespID(objJsonMsg.HEAD.ID, objJsonMsg.BODY.SUBSTATE);
	message.KIND = "Resp";
	message.RESPTYPE = objJsonMsg.BODY.RESP_TYPE;
	message.REASON = objJsonMsg.BODY.REASON_CODE;
	
	if(objJsonMsg.HEAD.ID == 'GetUEI' || objJsonMsg.HEAD.ID == 'SetUEI')
		message.UEI = objJsonMsg.BODY.UEI;
	
	if(objJsonMsg.HEAD.ID == 'GetIvrInfo')
	{
		message.IVR_MENUCODE = objJsonMsg.BODY.IVR_MENUCODE;
		message.IVR_INPUTINFO = objJsonMsg.BODY.IVR_INPUTINFO;
		message.IVR_MENUPATH = objJsonMsg.BODY.IVR_MENUPATH;
	}
	
	if(objJsonMsg.HEAD.ID == 'GetQueueInfo')
	{
		message.AVAILUSERCOUNT = objJsonMsg.BODY.AVAIL_USER_COUNT;
		message.DELAYCOUNT = objJsonMsg.BODY.DELAY_COUNT;
		message.DELAYTIME = objJsonMsg.BODY.DELAY_TIME;
	}
		
	
	
		
	postMessage(message);
	
	// GetMyStatus �� ��ư ���µ� ����
	if(message.ID == 'GetMyStatus' && message.RESPTYPE == 'Success')
		UpdateButtonStatus(objJsonMsg);
}



function GetRespID(id, substate)
{
	if(id == 'TLP_USER_STATE_LOGIN')
		return 'Login';
	else if(id == 'TLP_USER_STATE_LOGOUT')
		return 'Logout';
	else if(id == 'TLP_USER_STATE_AVAIL')
		return 'Avail';
	else if(id == 'TLP_USER_STATE_UNAVAIL')
	{
		if(substate == 'TLP_USER_STATE_UNAVAIL_0')
			return 'Unavail0';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_1')
			return 'Unavail1';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_2')
			return 'Unavail2';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_3')
			return 'Unavail3';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_4')
			return 'Unavail4';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_5')
			return 'Unavail5';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_6')
			return 'Unavail6';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_7')
			return 'Unavail7';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_8')
			return 'Unavail8';
		else if(substate == 'TLP_USER_STATE_UNAVAIL_9')
			return 'Unavail9';
	}
	else if(id == 'TLP_USER_STATE_WORK')
	{
		if(substate == 'TLP_USER_STATE_WORK_0')
			return 'Work0';
		else if(substate == 'TLP_USER_STATE_WORK_1')
			return 'Work1';
		else if(substate == 'TLP_USER_STATE_WORK_2')
			return 'Work2';
		else if(substate == 'TLP_USER_STATE_WORK_3')
			return 'Work3';
		else if(substate == 'TLP_USER_STATE_WORK_4')
			return 'Work4';
		else if(substate == 'TLP_USER_STATE_WORK_5')
			return 'Work5';
		else if(substate == 'TLP_USER_STATE_WORK_6')
			return 'Work6';
		else if(substate == 'TLP_USER_STATE_WORK_7')
			return 'Work7';
		else if(substate == 'TLP_USER_STATE_WORK_8')
			return 'Work8';
		else if(substate == 'TLP_USER_STATE_WORK_9')
			return 'Work9';
		else if(substate == 'TLP_USER_STATE_WORK_ACW')
			return 'WorkACW';
		else if(substate == 'TLP_USER_STATE_WORK_CTF')
			return 'WorkCTF';		
	}
	else if(id == 'MakeCall')
		return 'MakeCall';
	else if(id == 'AnswerCall')
		return 'AnswerCall';
	else if(id == 'DisconnectCall')
		return 'DisconnectCall';
	else if(id == 'HoldCall')
		return 'HoldCall';
	else if(id == 'RetrieveCall')
		return 'RetrieveCall';
	else if(id == 'ConsultationCall')
		return 'ConsultationCall';
	else if(id == 'ReconnectCall')
		return 'ReconnectCall';
	else if(id == 'TransferCall')
		return 'TransferCall';
	else if(id == 'AlternateCall')
		return 'AlternateCall';
	else if(id == 'ConferenceCall')
		return 'ConferenceCall';
	else if(id == 'SSTransferCall')
		return 'SingleStepTransfer';
	else if(id == 'SSConferenceCall')
		return 'SingleStepConference';
	else if(id == 'SendDTMFTones')
		return 'SendDTMF';
	else if(id == 'SetUEI')
		return 'SetUEI';
	else if(id == 'GetUEI')
		return 'GetUEI';
	else if(id == 'GetQueueInfo')
		return 'GetQueueInfo';
	else if(id == 'GetIvrInfo')
		return 'GetIvrInfo';
	else if(id == 'GetMyStatus')
		return 'GetMyStatus';
	else if(id == 'MicrophoneMute')
		return 'MicrophoneMute';
	else if(id == 'SpeakerMute')
		return 'SpeakerMute';
	else if(id == 'SpeakerVolume')
		return 'SpeakerVolume';
	else if(id == 'QueryDevice')
		return 'QueryDevice';
	else if(id == 'CtfStart')
		return 'CtfStart';
	else if(id == 'MonitorRegist')
		return 'MonitorRegist';
	else if(id == 'MonitorUnregist')
		return 'MonitorUnregist';
}

//	 $('#clearLog').on('click', function() {
//		
//		var tagLog = document.getElementById("log");
//		//alert(tagLog.innerHTML);
//		tagLog.innerHTML = '';

//		return false;
//	});

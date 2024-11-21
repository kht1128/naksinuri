var ctiws;
onmessage = function(event){
		
	jsonMsg = event.data;
	//ctiws_console_log(event);
	//ctiws_console_log(jsonMsg);
	
	
	var controll = jsonMsg.controll;
	var action = jsonMsg.action;

	ctiws_console_log('ctiws request.. ' + controll + " / " + action);
	
	if(controll == "req") 
	{
		if(action == "connect") 
		{
			var local_ws_url = jsonMsg.msg;
			ctiws_console_log('local_ws_url .. ' + local_ws_url);
			ctiws = new WebSocket(local_ws_url+'/ws/cti.do');
			ctiws.onopen = function() {
				ctiws_console_log('ctiws connected..');
				var obj = {
				    	'mbr_id':'',
				    	'cti_id':'',
				    	'cti_telno':'',
				    	'sid':'',
				    	'controll':'connected',
				    	'action':'',
				    	'msg':'',
				    };
				postMessage(obj);
			};

			ctiws.onerror = function() {
				ctiws_console_log('ctiws error..');
				var obj = {
				    	'mbr_id':'',
				    	'cti_id':'',
				    	'cti_telno':'',
				    	'sid':'',
				    	'controll':'error',
				    	'action':'',
				    	'msg':'',
				    };
				postMessage(obj);
			};

			ctiws.onmessage = function(event) {				
				ctiws_console_log('ctiws response..');
				postMessage(JSON.parse(event.data));
			};

			ctiws.onclose = function() {
				ctiws_console_log('ctiws disconnected..');
				var obj = {
				    	'mbr_id':'',
				    	'cti_id':'',
				    	'cti_telno':'',
				    	'sid':'',
				    	'controll':'disconnected',
				    	'action':'',
				    	'msg':'',
				    };
				postMessage(obj);
			};
		} 
		else if(action == "disconnect") 
		{
			ctiws.close();
		}	
	}
	else if(controll == "call")
	{
		if(action == "online") {
			ctiws_console_log('콜상태:연결');			
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "offline") {
			ctiws_console_log('콜상태:연결해제');
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "lock") {
			ctiws_console_log('콜상태:자리비움');
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "work") {
			ctiws_console_log('콜상태:후처리');
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "unlock") {
			ctiws_console_log('콜상태:자리비움해제');
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "ing") {
			ctiws_console_log('콜상태:통화중');			
			ctiws.send(JSON.stringify(jsonMsg));	
		} else if(action == "end") {
			ctiws_console_log('콜상태:통화종료');
			ctiws.send(JSON.stringify(jsonMsg));	
		}
	}
}
function ctiws_console_log(msg) {
	//console.log(msg);
}

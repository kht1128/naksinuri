package egovframework.cti.main.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Controller
public class CtiWebSocketManager extends TextWebSocketHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CtiWebSocketManager.class);
	
	private Map<String,CtiWebSocketVO> sessionUserInfoList = new HashMap<String,CtiWebSocketVO>();
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	
    // 클라이언트와 연결 이후에 실행되는 메서드
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	sessionList.add(session);
    	String sessionId = session.getId();
    	LOGGER.debug("websocket connected.. " + sessionId);
    	{
			JSONObject obj = new JSONObject();
	    	obj.put("mbr_id", "");
	    	obj.put("cti_id", "");
	    	obj.put("cti_telno", "");
	    	obj.put("sid", sessionId);
	    	obj.put("controll", "status");
	    	obj.put("action", "connected");
	    	obj.put("msg", "");
	    	session.sendMessage(new TextMessage(obj.toString()));
    	}
    	for(CtiWebSocketVO info : sessionUserInfoList.values()) {
    		JSONObject obj = new JSONObject();
        	obj.put("mbr_id", info.getMBR_ID());
        	obj.put("cti_id", info.getCTI_ID());
        	obj.put("cti_telno", info.getCTI_TELNO());
        	obj.put("sid", info.getSID());
        	obj.put("controll", info.getCONTROLL());
        	obj.put("action", info.getACTION());
        	obj.put("msg", info.getMSG());
        	session.sendMessage(new TextMessage(obj.toString()));
        	LOGGER.debug("("+sessionId+") 해당 세션에게 모든 세션 정보를 전달 ( MBR_ID : " + info.getMBR_ID() + " , CTI_TELNO : " + info.getCTI_TELNO() + " , controll : "+info.getCONTROLL()+" , action : "+info.getACTION()+" )");
    	}
    	
    }
   
    // 클라이언트가 서버로 메시지를 전송했을 때 실행되는 메서드
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	String sessionId = session.getId();
    	LOGGER.debug("websocket message.. [" + sessionId + "] , " + message.getPayload());
    	//EgovFileScrty mEgovFileScrty = new EgovFileScrty();
    	    	
    	JSONParser parser = new JSONParser();
    	JSONObject obj = (JSONObject) parser.parse(message.getPayload().toString());   
    	String mbr_id = obj.get("mbr_id").toString();
    	String cti_id = obj.get("cti_id").toString();
    	String cti_telno = obj.get("cti_telno").toString();
    	String sid = obj.get("sid").toString();
    	String controll = obj.get("controll").toString();
    	String action = obj.get("action").toString();
    	String msg = obj.get("msg").toString();
    	
    	if(sessionUserInfoList.containsKey("SID"+sessionId)) {//해당 세션이 이미 있음
    		CtiWebSocketVO info = sessionUserInfoList.get("SID"+sessionId);
    		info.setCONTROLL(controll);
    		info.setACTION(action);
    		info.setMSG(msg);
    		LOGGER.debug("("+sessionId+") 해당 세션은 이미 데이터가 존재함 ( MBR_ID : " + info.getMBR_ID() + " , CTI_TELNO : " + info.getCTI_TELNO() + ")");
    	} else {//신규 세션인 경우
    		LOGGER.debug("("+sessionId+") 해당 세션은 신규 데이터 ( MBR_ID : " + mbr_id + " , CTI_TELNO : " + cti_telno + ")");
    		if(controll.equals("call")) {
	    		if(action.equals("online")) {	    			
    				CtiWebSocketVO info = new CtiWebSocketVO();
        			info.setCTI_ID(cti_id);
        			info.setMBR_ID(mbr_id);
        			info.setCTI_TELNO(cti_telno);
        			info.setSID(sessionId);
        			info.setCONTROLL(controll);
            		info.setACTION(action);
            		info.setMSG(msg);
        			sessionUserInfoList.put("SID"+sessionId,info);
        			LOGGER.debug("세션 데이터 등록 완료("+"SID"+sessionId+")");
	    		}
	    	}
    	}
    	LOGGER.debug("*** 총 접속 세션 정보 : " + sessionList.size());
    	for (WebSocketSession sess : sessionList) {    	
    		try {
    			sess.sendMessage(new TextMessage(obj.toString()));
    		} catch(Exception e) {
	    		LOGGER.debug("Cannot send message after connection closed " + sess.getId());
	    		if(sessionUserInfoList.containsKey("SID"+sessionId)) {
	    			sessionUserInfoList.remove("SID"+sessionId);
	    			LOGGER.debug("("+sessionId+") 존재하지 않는 해당 세션을 데이터에서 삭제함");
	    		}
	    	}
    	}
    }
   
    // 클라이언트와 연결을 끊었을 때 실행되는 메소드
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
    	sessionList.remove(session);
    	String sessionId = session.getId();
    	LOGGER.debug("websocket diconnected.. " + sessionId);
    	try {
    		JSONObject obj = new JSONObject();
	    	obj.put("mbr_id", "");
	    	obj.put("cti_id", "");
	    	obj.put("cti_telno", "");
	    	obj.put("sid", sessionId);
	    	obj.put("controll", "status");
	    	obj.put("action", "disconnected");
	    	obj.put("msg", "");
	    	session.sendMessage(new TextMessage(obj.toString()));
    	} catch(Exception e) {
    		LOGGER.debug("Cannot send message after connection closed " + sessionId);
    	}
    	for (WebSocketSession sess : sessionList) {
    		if(!sess.getId().equals(sessionId)) {
    			try {
    				if(sessionUserInfoList.containsKey("SID"+sessionId)) {
    					CtiWebSocketVO info = sessionUserInfoList.get("SID"+sessionId);
    					JSONObject obj = new JSONObject();
    	    			obj.put("mbr_id", info.getMBR_ID());
    	    	    	obj.put("cti_id", info.getCTI_ID());
    	    	    	obj.put("cti_telno", info.getCTI_TELNO());
    	    	    	obj.put("sid", info.getSID());
    	    	    	obj.put("controll", "call");
    	    	    	obj.put("action", "offline");
    	    	    	obj.put("msg", "");
    	    	    	sess.sendMessage(new TextMessage(obj.toString()));
    	    	    	LOGGER.debug("("+sessionId+") 해당 세션 접속종료를 모든 세션 에게 전달 ( MBR_ID : " + info.getMBR_ID() + " , CTI_TELNO : " + info.getCTI_TELNO() + " )");
    				} 
    			} catch(Exception e) {
    	    		LOGGER.debug("Cannot send message after connection closed " + sess.getId());
    	    	}
    		}    			
    	}
    	if(sessionUserInfoList.containsKey("SID"+sessionId)) {
			sessionUserInfoList.remove("SID"+sessionId);
			LOGGER.debug("("+sessionId+") 해당 세션을 데이터에서 삭제함");
		}
    }
    
}

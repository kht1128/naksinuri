package egovframework.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import egovframework.rte.fdl.property.EgovPropertyService;

public class IPBlockInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPBlockInterceptor.class);
    private static final String BLOCKED_IP = "1.243.244.92"; // 차단할 IP 주소

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        LOGGER.debug("*******************************************************");
        LOGGER.debug("preHandle ... ");

        String clientIP = request.getHeader("X-Forwarded-For");
        if (clientIP == null || clientIP.isEmpty()) {
            clientIP = request.getRemoteAddr();
        } else if (clientIP.contains(",")) {
            clientIP = clientIP.split(",")[0].trim(); // 첫 번째 IP만 추출
        }
        
        if ("0:0:0:0:0:0:0:1".equals(clientIP)) {
            clientIP = "1.243.244.92";
        }
        
        LOGGER.debug("클라이언트 IP: [{}]", clientIP);
        LOGGER.debug("차단할 IP: [{}]", BLOCKED_IP);

        if (BLOCKED_IP.trim().equals(clientIP.trim())) {
            LOGGER.debug("차단된 IP 주소로부터의 접근 시도: " + clientIP);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied");
            return false; // 요청을 중지
        }

        LOGGER.debug("*******************************************************");
        return super.preHandle(request, response, handler);
    }
}
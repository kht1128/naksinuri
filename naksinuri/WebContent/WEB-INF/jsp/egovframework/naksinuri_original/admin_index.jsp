<%--
  Class Name : index.jsp
  Description : 최초화면으로 메인화면으로 이동한다.
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2011.08.31  JJY       경량환경 버전 생성
 
    author   : 실행환경 개발팀 JJY
    since    : 2011.08.31
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
<jsp:forward page="/naksinuri_original/cmm/main/mainPage.do"/>
--%>

<script type="text/javascript">
	
</script>
<!-- <script type="text/javaScript">document.location.href="<c:url value='/cmm/main/mainPage.do'/>"</script> -->
<script type="text/javaScript">document.location.href="<c:url value= '/admin/sosig/notice/list.do'/>"</script>

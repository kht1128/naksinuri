<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<c:if test="${Type eq 'main' }">
		<div class="svq_listAtt">
			
			<input type="hidden" name="anID" class="anID" value="${anID}">
			<select name="svq_type${anID}" id="svq${anID}" onchange="typeChange(this)">
					<option value="S">주관식</option>
					<option value="O">객관식</option>
			</select>
			<input type="text" name="svq_subject${anID}" value="" class="frm_input" size="70" title="" placeholder="">				
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="removeQ(this)">항목삭제</a>
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="preQ(this)">△</a>
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="nextQ(this)">▽</a>
			<div class="svqi_list" style="display:none">
				<a href="#;" id="btn_add_svq1" class="btn_frmline btn_violet"  onclick="SubAddQ(this)">응답 추가</a>
				최대 중복 선택수 
				<select name="svqi_mxsel${anID}" class="svqi_mxsel">
						<option value=1>1개</option>					
				</select>
				<ul class="subList">
					<li>
						<input type="hidden" name="svq_svqi_id${anID}" value="" class="frm_input" size="100">
						<input type="hidden" name="svq_svqi_etc${anID}" value="9" class="svq_svqi_etc" size="100">
						<input type="text" name="svq_svqi_txt${anID}" value="" class="frm_input" size="100"><br>
	
						<label for="etck${anID}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${anID}">
						<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a>
						<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a>
						<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a><br><br>
					</li>				
				</ul>
				<br>
			</div>
		</div>	
	</c:if>
	<c:if test="${Type eq 'sub' }">
		<li>
			<input type="hidden" name="svq_svqi_id${anID}" value="" class="frm_input" size="100">
			<input type="hidden" name="svq_svqi_etc${anID}" value="9" class="svq_svqi_etc" size="100">
			<input type="text" name="svq_svqi_txt${anID}" value="" class="frm_input" size="100"><br>

			기타의견 작성	<input type="checkbox" onclick="etcCk(this)">
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet"  onclick="SubRemoveQ(this)">항목삭제</a>
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a>
			<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a><br><br>
		</li>
	</c:if>
</html>
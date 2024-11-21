<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">

	var html_fn_script = "<script>";
	
	html_fn_script += "	function fn_egov_downFile${custom_uniq_key}(atchFileId, fileSn, fileExtsn){			";
	html_fn_script += "		window.open('/cmm/fms/FileDown.do?atchFileId='+atchFileId+'&fileSn='+fileSn);	";
	html_fn_script += "	}																					";
		
	html_fn_script += "	function fn_egov_deleteFile${custom_uniq_key}(atchFileId, fileSn) {					";
	html_fn_script += "		var target_clk_id = '${target_clk_id}'; 										";
	html_fn_script += "		alertify.confirm('해당 파일을 삭제하시겠습니까?<br>(삭제시 새로고침에 의해 현재 변경한 내용을 잃을 수 있습니다. 변경내용은 저장 후 삭제를 권장합니다.<br>그래도 계속하시겠습니까?)', function(){	";    		
	html_fn_script += "			$.ajax({																	";
	html_fn_script += "				type:'POST',															";
	html_fn_script += "				url :'/cmm/fms/deleteFileInfsAjax.do',									";
	html_fn_script += "				data:{																	";
	html_fn_script += "					atchFileId : atchFileId,											";
	html_fn_script += "					fileSn : fileSn,													";
	html_fn_script += "					returnUrl : null,													";
	html_fn_script += "					targetFormId : null,												";
	html_fn_script += "				},																		";
	html_fn_script += "				dataType: 'json',														";
	html_fn_script += "				contentType: 'application/x-www-form-urlencoded;charset=UTF-8',			";
	html_fn_script += "				async: true,															";
	html_fn_script += "				success: function(data, status, xhr) {									";
	html_fn_script += "					if(data.error == '1') {												";
	html_fn_script += "						alertify.alert(data.msg);										";
	html_fn_script += "					} else {															";
	html_fn_script += "						$('#'+target_clk_id).click();									";
	html_fn_script += "					}																	";
	html_fn_script += "				},																		";
	html_fn_script += "				complete : function() {													";
	html_fn_script += "				},																		";
	html_fn_script += "				error: function(jqXHR, textStatus, errorThrown) {						";
	html_fn_script += "				}																		";
	html_fn_script += "			});																			";
	html_fn_script += "		});																				";	
	html_fn_script += "	}																					";
	html_fn_script += "<\/script>";
	
	$('#egovFileListAjaxCti${custom_uniq_key}').append(html_fn_script);

</script>
		<div id="egovFileListAjaxCti${custom_uniq_key}"></div>
      	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	       <c:choose>
		       <c:when test="${updateFlag=='Y'}">
		       <p class="mb-0">
		       	   <a href="javascript:void(0);" onclick="javascript:fn_egov_downFile${custom_uniq_key}('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${fileVO.fileExtsn}"/>')">
			       		<code class="border-0"><c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]</code>
			       </a>
			       <a href="javascript:void(0);" onClick="fn_egov_deleteFile${custom_uniq_key}('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" 
			       class="btn btn-icon btn-default border-0 bg-white p-3 red-600" data-toggle="tooltip" data-original-title="삭제">
						<i class="icon wb-trash" aria-hidden="true"></i>
			       </a>
			   </p>    
		       </c:when>
		       <c:otherwise>
		       <p class="mb-0">
			       <a href="javascript:void(0);" onclick="javascript:fn_egov_downFile${custom_uniq_key}('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${fileVO.fileExtsn}"/>')">
						<code class="border-0"><c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]</code>
			       </a>	       
			   </p>
		       </c:otherwise>
	       </c:choose>
        </c:forEach>
        
      
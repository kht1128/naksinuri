<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script type="text/javascript">
	function fn_egov_downFile(atchFileId, fileSn, fileExtsn){
		/*
		var ext = fileExtsn.toLowerCase();
		if(ext == 'jpg' || ext == 'jpge' || ext == 'png' || ext == 'gif' || ext == 'bmp') {
			window.open("<c:url value='/cmm/fms/getImage.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		} else if(ext == 'pdf') {
			window.open("<c:url value='/cmm/fms/getPdf.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		} else if(ext == 'mp4' || ext == 'avi' || ext == 'wmv') {
			window.open("<c:url value='/cmm/fms/getPdf.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		} else {
			window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
		}
		*/
		window.open("<c:url value='/cmm/fms/FileDown.do?atchFileId="+atchFileId+"&fileSn="+fileSn+"'/>");
	}
		
	function fn_egov_deleteFile(atchFileId, fileSn) {		
		alertify.confirm('해당 파일을 삭제하시겠습니까?<br>(삭제시 새로고침에 의해 현재 변경한 내용을 잃을 수 있습니다. 변경내용은 저장 후 삭제를 권장합니다.<br>그래도 계속하시겠습니까?)', function(){    		

			forms = document.getElementsByTagName("form");

			for (var i = 0; i < forms.length; i++) {
				if (typeof(forms[i].atchFileId) != "undefined" &&
						typeof(forms[i].fileSn) != "undefined" &&
						typeof(forms[i].fileListCnt) != "undefined") {
					form = forms[i];
				}
			}
			//form = document.forms[0];
			form.atchFileId.value = atchFileId;
			form.fileSn.value = fileSn;
			form.action = "<c:url value='/cmm/fms/deleteFileInfsAjax.do'/>";

			$.ajax({
				type:"POST",
				url :form.action,
				data:$(form).serialize(),
				dataType: 'json',
				contentType: "application/x-www-form-urlencoded;charset=UTF-8",
				async: true,
				success: function(data, status, xhr) {
					if(data.error == '1') {
						alertify.alert(data.msg);
					} else {
						$.ajax({
							type:"POST",
							url :data.returnUrl,
							data:$('#'+data.targetFormId).serialize(),
							dataType: 'html',
							contentType: "application/x-www-form-urlencoded;charset=UTF-8",
							async: true,
							success: function(data, status, xhr) {
								$("#seaAdmEduPublicModal").html(data);
								$("#seaAdmEduPublicModal").modal('show');
							},
							complete : function() {
								//console.log('complete!');
						    },
							error: function(jqXHR, textStatus, errorThrown) {
								//console.log('error!');
							}
						});
					}				
				},
				complete : function() {
					//console.log('complete!');
			    },
				error: function(jqXHR, textStatus, errorThrown) {
					//console.log('error!');
				}
			});
			
    	});		
	}
	
	function fn_egov_check_file(flag) {
		if (flag=="Y") {
			document.getElementById('file_upload_posbl').style.display = "block";
			document.getElementById('file_upload_imposbl').style.display = "none";			
		} else {
			document.getElementById('file_upload_posbl').style.display = "none";
			document.getElementById('file_upload_imposbl').style.display = "block";
		}
	}
</script>

<input type="hidden" name="atchFileId" value="${atchFileId}">
<input type="hidden" name="fileSn" >
<input type="hidden" name="fileListCnt" value="${fileListCnt}">
      	<c:forEach var="fileVO" items="${fileList}" varStatus="status">
	       <c:choose>
		       <c:when test="${updateFlag=='Y'}">
		       <p class="mb-0">
		       	   <a href="#LINK" onclick="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${fileVO.fileExtsn}"/>')">
			       		<code class="border-0"><c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]</code>
			       </a>
			       <a href="#;" onClick="fn_egov_deleteFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>');" 
			       class="btn btn-icon btn-default border-0 bg-white p-3 red-600" data-toggle="tooltip" data-original-title="삭제">
						<i class="icon wb-trash" aria-hidden="true"></i>
			       </a>
			   </p>    
		       </c:when>
		       <c:otherwise>
		       <p class="mb-0">
			       <a href="#LINK" onclick="javascript:fn_egov_downFile('<c:out value="${fileVO.atchFileId}"/>','<c:out value="${fileVO.fileSn}"/>','<c:out value="${fileVO.fileExtsn}"/>')">
						<code class="border-0"><c:out value="${fileVO.orignlFileNm}"/>&nbsp;[<c:out value="${fileVO.fileMg}"/>&nbsp;byte]</code>
			       </a>	       
			   </p>
		       </c:otherwise>
	       </c:choose>
        </c:forEach>
        <c:if test="${fn:length(fileList) == 0}">
	    </c:if>
      
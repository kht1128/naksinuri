<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="validator"
	uri="http://www.springmodules.org/tags/commons-validator"%>



<c:set var="pageMode" value="share" />
<c:set var="depthName" value="share" />
<c:set var="pageName" value="zisik" />



<%@include file="../layout/head.jsp"%>
<script src='https://www.google.com/recaptcha/api.js'></script>

<div id="qnaView" class="content respon2">
	<div class="tabArea tab2">
		<!-- 탭이 3개면 class="tabArea tab3" 4개면 뭐겠냐 -->
		<ul class="floats">
			<li class="on"><a href="/share/zisik/list.do">누리지식인</a></li>
			<li><a href="/share/nuri/list.do">자주묻는낚시질문</a></li>
		</ul>
	</div>
	<div id="qnaWrite" class="content respon3">
		<form:form commandName="imform" id="imform" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="nuri_q_num" id="nuri_q_num" value="${info.nuri_q_num}" />
			<section id="qnaWriteBox" class="write_box">
				<h2>누리지식인 질문하기</h2>
				<dl>
					<dt>제목</dt>
					<dd>
						<input type="text" size="60" maxlength="40" name="nuri_q_subject"
							class="write_input" value="${info.nuri_q_subject}"
							placeholder="제목은 38자 이내로 작성해주세요." />
					</dd>
				</dl>
				<dl>
					<dt>이름</dt>
					<dd>
						<input type="text" class="write_input" name="nuri_q_writer"
							value="${info.nuri_q_writer}" placeholder="이름을 입력해주세요." />
					</dd>
				</dl>
				<c:if test="${info.nuri_q_num eq null}">
					<dl>
						<dt>비밀번호</dt>
						<dd>
							<input type="password" size="50" name="nuri_q_pass"
								id="nuri_q_pass" class="write_input" value="${info.nuri_q_pass}"
								placeholder="비밀번호를 입력해주세요." />
						</dd>
					</dl>
				</c:if>
				<dl>
					<dt>비밀번호 확인</dt>
					<dd>
						<input type="password" size="50" name="nuri_q_pconfirm"
							id="nuri_q_pconfirm" class="write_input"
							placeholder="비밀번호를 다시한번 입력해주세요." />
					</dd>
				</dl>
			
				<dl>
					<dt>질문내용</dt>
					<dd>
						<textarea class="write_textarea" name="nuri_q_content" id="nuri_q_content" rows="20"
							placeholder="질문을 입력해주세요.">${info.nuri_q_content}</textarea>
					</dd>
				</dl>
				<c:if test="${info.nuri_q_num eq null}">
					<dl>
						<dt>자동등록방지</dt>
						<dd>
							<div class="g-recaptcha" data-sitekey="${siteKey}"></div>
						</dd>
						<!-- https://www.google.com/recaptcha/intro/android.html 이거보고 참고하셔 -->
						<input type="hidden" name="recaptcha" />
					</dl>
				</c:if>
				<div id="btnArea">
					<ul class="floats">
						<li><a href="/share/zisik/list.do" class="btn_prev btn_white">목록으로</a></li>
						<!-- 버튼타입으로 할라믄 이걸로 해 <li class="fr"><button class="btn_request btn_blue">질문하기</button></li> -->
						<c:choose>
							<c:when test="${info.nuri_q_num ne null}">
								<li class="fr"><a href="#;" onclick="submitContents()"
									class="btn_request btn_blue">수정하기</a></li>
							</c:when>
							<c:otherwise>
								<li class="fr"><a href="#;" onclick="submitContents()"
									class="btn_request btn_blue">질문하기</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</section>
		</form:form>
	</div>
</div>
<script type="text/javascript">

	var depthName = "${depthName}";
	var pageName = "${pageName}";

//전역변수선언
	var oEditors = [];	


nhn.husky.EZCreator.createInIFrame({
    oAppRef: oEditors,
    elPlaceHolder: "nuri_q_content",
    sSkinURI: "/naksinuri_original/se2/SmartEditor2Skin.html",
    
    fOnAppLoad : function(){
    	oEditors.getById["nuri_q_content"].exec("PASTE_HTML", [" "]);
    },
    fCreator: "createSEditor2"
});

//전송버튼 클릭이벤트
function submitContents() {
	
// 에디터의 내용이 textarea에 적용된다.
oEditors.getById["nuri_q_content"].exec("UPDATE_CONTENTS_FIELD", [ ]);

// 에디터의 내용에 대한 값 검증은 이곳에서
if(document.getElementById("nuri_q_content").value==''){
	alert('내용을 입력해주세요.');
	return false;   	
} 
	 try {
		 if($('#nuri_q_num').val()!=0){
			 	$("#imform").attr("action", "/share/zisik/update_zisik.do");
				$("#imform").submit();
			}else{
				if ($("#nuri_q_pass").val() != $("#nuri_q_pconfirm").val()) {
					alert('비밀번호를 확인해주세요.');
					return false;
				} else {

					var recaptcha = document.getElementById("g-recaptcha-response").value;

					if (recaptcha.length < 1) {
						alert('자동등록방지를 눌러주세요.');
						return false;
					} else {
						$('input[name=recaptcha]').val(recaptcha);
					}

					$("#imform").attr("action", "/share/zisik/insert_zisik.do");
					document.getElementById("imform").submit();
				}			
			}
		 
	} catch(e) {
	 
	}

}

//textArea에 이미지 첨부
function pasteHTML(filepath){
	var sHTML = '<img src="<%=request.getContextPath()%>/upload/'+ filepath + '">';
		oEditors.getById["nuri_q_content"].exec("PASTE_HTML", [ sHTML ]);

	}

	function fn_egov_deleteFile(atchFileId, fileSn) {
		forms = document.getElementsByTagName("form");

		for (var i = 0; i < forms.length; i++) {
			if (typeof (forms[i].atchFileId) != "undefined"
					&& typeof (forms[i].fileSn) != "undefined"
					&& typeof (forms[i].fileListCnt) != "undefined") {
				form = forms[i];
			}
		}
		//form = document.forms[0];
		form.atchFileId.value = atchFileId;
		form.fileSn.value = fileSn;
		form.action = "<c:url value='/naksinuri_original/cmm/fms/deleteFileInfs.do'/>";
		form.submit();
	}

	
</script>

<%@include file="../layout/tail.jsp"%>
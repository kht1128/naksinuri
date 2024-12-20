<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
<title>낚시누리</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Generic page styles -->
<link rel="stylesheet" href="/se2/photo_uploader/popup/css/style.css?v=140715">
<!-- CSS to style the file input field as button and adjust the Bootstrap progress bars -->
<link rel="stylesheet" href="/se2/photo_uploader/popup/css/jquery.fileupload.css">
</head>
<body>
<div class="container pop_container">
	<!-- header -->
    <div id="pop_header">
        <h1>사진 첨부하기</h1>
    </div>
    <!-- //header -->
    <div class="content_container">
        <div class="drag_explain">
            <p>마우스로 드래그하여 순서를 바꿀수 있습니다.</p>
            <div class="file_selet_group">
            <span class="btn btn-success fileinput-button">
                <span>파일선택</span>
                <!-- The file input field used as target for the file upload widget -->
                <input id="fileupload" type="file" name="files[]" multiple accept="image/*">
            </span>
            <button type="button" class="btn btn-danger delete" id="all_remove_btn">
                <span>전체삭제</span>
            </button>
            </div>
        </div>
        <div class="drag_area" id="drag_area">
            <ul class="sortable" id="sortable">
            </ul>
            <em class="blind">마우스로 드래그해서 이미지를 추가해주세요.</em><span id="guide_text" class="bg hidebg"></span>
        </div>
        <div class="seletion_explain">이미지는 한번에 10개까지 선택할수 있습니다.</div>
        <div class="btn_group">
            <button type="button" class="btn" id="img_upload_submit">
                <span>등록</span>
            </button>
            <button type="button" class="btn" id="close_w_btn" >
                <span>취소</span>
            </button>
        </div>
    </div>
</div>

<script src="/se2/photo_uploader/popup/js/jquery-1.8.3.min.js"></script>
<script src="/se2/photo_uploader/popup/js/jquery-ui.min.js"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script src="/se2/photo_uploader/popup/js/jquery.iframe-transport.js"></script>

<script type="text/javascript" src="/se2/photo_uploader/popup/swfupload/swfupload.js"></script>
<script type="text/javascript" src="/se2/photo_uploader/popup/swfupload/jquery.swfupload.js"></script>

<!-- The basic File Upload plugin -->
<script src="/se2/photo_uploader/popup/js/jquery.fileupload.js?v=140715"></script>

<script src="/se2/photo_uploader/popup/js/basic.js"></script>
</body> 
</html>
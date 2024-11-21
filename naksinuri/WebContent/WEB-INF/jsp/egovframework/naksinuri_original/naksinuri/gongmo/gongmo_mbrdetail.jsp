<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/jquery.bxslider.css">
<link rel="stylesheet" type="text/css" href="/naksinuri_original/common_main/css/common.css" />

<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<script src="https://use.fontawesome.com/e59ba62350.js"></script>
<script src="/naksinuri_original/common_main/js/bootstrap.min.js"></script>
<script src="/naksinuri_original/common_main/js/jquery.bxslider.js"></script>
<script src="/naksinuri_original/common_main/js/common.js"></script>
<style>
	.mgt50px{margin-top:50px}
	
	.write_box h2.championshopTit{height:auto;line-height:30px}
	.write_box h2.championshopTit span{display:block;font-size:16px;color:#999}
	.write_box h2.championshopTit span b{color:#666}
	
	.write_box dl dt{width:150px}
	.write_box dl dd{padding-top:20px;}
		
	.write_box .zipcodeBox{
		display:inline-block;height:40px;line-height:40px;padding:0 10px;margin:0 0 4px 5px;border:none;background-color:#626262;color:#fff;vertical-align:top;
		-moz-border-radius:3px;-webkit-border-radius:3px;border-radius:3px;}
</style>
<div id="customerSound" class="content respon3">
	
		<section id="writeForm" class="write_box">
			<h2>접수 정보</h2>
			<div class="client_box">
				<dl>
					<dt>공모전 정보</dt>
					<dd>
						${info.bo_subject} (${info.bo_start_dt} ~ ${info.bo_end_dt})
					</dd>
				</dl>
				<dl>
					<dt>접수일자</dt>
					<dd>${info.regdate}</dd>
				</dl>
				<dl>
					<dt>접수번호</dt>
					<dd>${info.mbr_group}</dd>
				</dl>
				<dl>
					<dt>응모부문</dt>
					<dd>${info.mbr_cate}</dd>
				</dl>
			</div>
			
			<h2 style="margin-top:30px;">접수자 정보</h2>
			<div class="client_box">
				<dl>
					<dt>성명</dt>
					<dd>
						${info.mbr_name}
					</dd>
				</dl>
				<dl>
					<dt>응모자구분</dt>
					<dd>
						${info.mbr_class}
					</dd>
				</dl>
				<dl>
					<dt>주소</dt> 
					<dd>	
						${info.mbr_address}<br>${info.mbr_address_jibun}
					</dd>                                                                                                                           
				</dl>
				<dl>
					<dt>연락처</dt>
					<dd>
						${info.mbr_hp}
					</dd>
				</dl>
				<dl>
					<dt>이메일</dt>
					<dd>
						${info.mbr_email}
					</dd>
				</dl>
			</div>	
				
			<h2 style="margin-top:30px;">출품작</h2>
			<div class="client_box">
				<dl>
					<dt>작품제목</dt>
					<dd>
						${info.work_subject}
					</dd>
				</dl>
				<dl>
					<dt>작품설명</dt>
					<dd>
						${info.work_summary}
					</dd>
				</dl>
				<dl>
					<dt>첨부파일</dt>
					<dd>
						<c:if test="${not empty info.bo_atch_file}">				
							<c:import url="/naksinuri_original/cmm/fms/selectFileInfs.do" charEncoding="utf-8">
								<c:param name="param_atchFileId" value="${info.bo_atch_file}" />
					        </c:import>
		                </c:if>
					</dd>
				</dl>
			</div>	
			
		</section>
	</div>


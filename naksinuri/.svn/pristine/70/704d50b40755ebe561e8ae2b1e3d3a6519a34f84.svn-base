<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>


<c:set var="pageMode" value="info"/>
<c:set var="depthNum" value="3" />
<c:set var="depthName" value="share" />
<c:set var="pageName" value="survey" />


<%@include file="../../layout/m/head.jsp"%>	
<div id="container">
	<div id="content">
		<section id="suveyPage" class="table-box">					
			<div class="padding_box">
					<c:if test="${info.sv_strt_dt ne null }">
						<div class="survey_view">
							<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
								<h4>${info.sv_subject} <span class="date">(${info.sv_strt_dt} ~ ${info.sv_end_dt} / 총 응답자 수 : <span style="color:red">${result.survey_anscnt}</span>명)</span></h4>
								<c:set var="survey_people" value="${result.survey_anscnt}" />
							</c:forEach>
							<div class="suveybox">
								<c:forEach var="item" items="${quest}">
									<!-- 
									<c:if test="${item.svq_type eq 'S' }">
										<dl>
											<dt><em class="num"><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span class="subject">${item.svq_subject}<span class="type">(주관식)</span></span></dt>
											<dd>
												<div class="subjective">
													<c:forEach var="answerole" items="${answerole}">
														<c:if test="${answerole.sq_id eq item.sq_id}">
															<div class="subjective_con">${answerole.sva_txt}</div>
														</c:if>
													</c:forEach>
												</div>
											</dd>
										</dl>
									</c:if>-->
									<c:if test="${item.svq_type eq 'O' }">
										<dl>
											<dt><em class="num"><fmt:formatNumber var="no" minIntegerDigits="2" value="${item.svq_num}" type="number"/>${no}.</em><span class="subject">${item.svq_subject}<span class="type">(객관식<c:if test="${item.svq_mxcnt gt 1 }"> - 중복선택 ${item.svq_mxcnt}개 이하</c:if>)</span></span></dt>
											<dd>
												<ul class="multiple">
													<c:forEach var="item2" items="${qitem}">
														<c:if test="${item2.sq_id eq item.sq_id }">
															<li>
																<div class="multiple_subject">${item2.svq_item_txt } (${item2.selector }명 선택)</div>
																<div class="progress">
																	<div class="prograss-bar" style="width:${item2.selector/survey_people*100}%"></div>
																	<span class="percent">
																		<c:if test="${survey_people eq 0}">0.0%</c:if>
																		<c:if test="${survey_people ne 0}">${item2.selector/survey_people*100}%</c:if>	
																	</span>
																</div>
																<c:if test="${item2.sqi_etc eq 0 }">
																	<div class="subjective">
																		<c:forEach var="answerole" items="${answerole}">
																			<c:if test="${answerole.sq_id eq item.sq_id}">
																				<div class="subjective_con">${answerole.sva_txt}</div>
																			</c:if>
																		</c:forEach>
																	</div>
																</c:if>
															</li>
														</c:if>
													</c:forEach>													
												</ul>
											</dd>
										</dl>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:if>
					<c:if test="${info.sv_strt_dt eq null }">
						<table class="t_write" id="ftable">
							<colgroup>
								<col width="150" />
								<col />
							</colgroup>
							<tbody>							
								<tr>
									<th>글쓴이</th>
									<td><input type="text" name="reg_mb_id" id="reg_mb_id" class="frm_input" value="${info.reg_mb_id}"  /></td>
								</tr>							
								<tr>
									<th>설문시작일</th>
									<td><input type="text" name="sv_strt_dt" id="sv_strt_dt" class="frm_input" value="${info.sv_strt_dt}"/>~
										<input type="text" name="sv_end_dt" id="sv_end_dt" class="frm_input" value="${info.sv_end_dt}"/>
									</td>
								</tr>
								<tr>
									<th>사용자 공개여부</th>
									<td>
										<select id="sv_show" name="sv_show" value="${info.sv_show}">
											<option value="0"<c:if test="${info.sv_show eq '0'}">selected</c:if>>기간내+기간만료 포함공개</option>
											<option value="1"<c:if test="${info.sv_show eq '1'}">selected</c:if>>기간만료후 공개</option>
											<option value="2"<c:if test="${info.sv_show eq '2'}">selected</c:if>>비공개</option>
										</select>
									</td>
								</tr>
								<tr>
									<th>설문제목</th>
									<td><input type="text" name="sv_subject" id="sv_subject" class="frm_input" value="${info.sv_subject}" style="WIDTH: 100%;"/></td>
								</tr>
								<tr>
									<th align="top">설문항목<br/><br/>
										<a href="#;" onclick="addQ(this)" class="btn_size btn_violet">항목추가</a>
									</th>
									<td class="svq_list">
									<c:forEach var="result" items="${aan}" varStatus="status" begin="0" end="0">
											총 응답자 수  : ${result.survey_anscnt} 명<br><br>	
									</c:forEach>
																
										<c:forEach var="item" items="${quest}">
											<div class="svq_listAtt">
												<input type="hidden" name="sq_id" value="${item.sq_id}">
												<input type="hidden" name="anID" class="anID" value="${$anID}">
												<select name="svq_type${item.sq_id}" id="svq${item.sq_id}" onchange="typeChange(this)">
													<option value="S" <c:if test="${item.svq_type eq 'S' }">selected</c:if>>주관식</option>
													<option value="O" <c:if test="${item.svq_type eq 'O' }">selected</c:if>>객관식</option>
												</select>
												<input type="text" name="svq_subject${item.sq_id}" value="${item.svq_subject }" class="frm_input" size="70" title="" placeholder="">
												<c:if test="${item.svq_mxcnt gt 1 }">최대 중복 선택수 : ${item.svq_mxcnt }개 </c:if>	
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="removeQ(this)">항목삭제</a> -->
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="preQ(this)">△</a> -->
	<!-- 											<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="nextQ(this)">▽</a><br> -->
												<!-- 주관식일때 텍스트 에리어 생성 -->
												<c:if test="${item.svq_type eq 'S' }">
													<br>
													<textarea rows="10" cols="80%"><c:forEach var="answerole" items="${answerole}"><c:if test="${answerole.sq_id eq item.sq_id}">${answerole.sva_txt}
													</c:if></c:forEach></textarea><br><br>
												</c:if>
												<c:if test="${item.svq_type eq 'O' }">
													<div class="svqi_list">
	<!-- 													<a href="#;" id="btn_add_svq1" class="btn_frmline btn_violet"  onclick="SubAddQ(this)">응답 추가</a> -->
	<!-- 													최대 중복 선택수  -->
	<%-- 													<select name="svqi_mxsel${anID}" class="svqi_mxsel"> --%>
	<!-- 															<option value=1>1개</option>					 -->
	<!-- 													</select> -->
														<ul class="subList">
															<c:forEach var="item2" items="${qitem}">
																<c:if test="${item2.sq_id eq item.sq_id }">
																	<li>
																		<input type="hidden" name="svq_svqi_id${item2.svq_item_num}" value="" class="frm_input" size="100">
																		<input type="hidden" name="svq_svqi_etc${item2.svq_item_num}" value="9" class="svq_svqi_etc" size="100">
																		<input type="text" name="svq_svqi_txt${item2.svq_item_num}" value="${item2.svq_item_txt }" class="frm_input" size="100"><br>
																		<label for="etck${item2.svq_item_num}">기타의견 작성</label>	<input type="checkbox" onclick="etcCk(this)" id="etck${item2.svq_item_num}" <c:if test="${item2.sqi_etc eq 0 }">checked</c:if>>
	<!-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin btn_violet" onclick="SubRemoveQ(this)">항목삭제</a> -->
	<!-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubPreQ(this)">△</a> -->
	<%-- 																	<a href="#;" id="btn_svq_del" class="btn_frmline curPoin" onclick="SubNextQ(this)">▽</a>--%> &nbsp;&nbsp;${item2.selector }명 선택 
																		<br><br>
																		<c:if test="${item2.sqi_etc eq 0 }">
																			<textarea rows="10" cols="80%"><c:forEach var="answerole" items="${answerole}"><c:if test="${answerole.sq_id eq item.sq_id}">${answerole.sva_txt}
																			</c:if></c:forEach></textarea><br><br>
																		</c:if>
																																	
																	</li>				
																</c:if>
															</c:forEach>
														</ul>
													</div>
												</c:if>
												<c:if test="${item.svq_type eq 'S' }">
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
													</div>
												</c:if>
											</div><br><br>
										</c:forEach>
										
									</td>
								</tr>	
							</tbody>
						</table>
					</c:if>	
				</div>
			</section>
		</div>
	</div>
<%@include file="../../layout/m/tail.jsp"%>	
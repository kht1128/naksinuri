<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

		<!-- 좌측 메뉴 { -->
		<nav id="gnb">
			<ul>
				<%-- <li <c:choose>
						<c:when test="${depthName eq 'gongmo'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe060;"></span><em>공모전 정보</em></a>
					<ul>
						<li><a href="/admin/gongmo/gongmo/list.do"<c:if test="${pageName eq 'gongmo' }"> class="active" </c:if> >공모전</a></li>
						<li><a href="/admin/gongmo/event/list.do"<c:if test="${pageName eq 'event' }"> class="active" </c:if> >커뮤니티</a></li>
					</ul>
				</li> --%>
				
				<li <c:choose>
						<c:when test="${depthName eq 'lesson'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe060;"></span><em>낚시교실</em></a>
					<ul>
						<li><a href="/admin/lesson/dignity/list.do" <c:if test="${pageName eq 'dignity' }"> class="active" </c:if> >낚시의 품격</a></li>
						<li><a href="/admin/lesson/junior/list.do" <c:if test="${pageName eq 'junior' }"> class="active" </c:if> >초보탈출하기</a></li>
						<li><a href="/admin/lesson/gosu/list.do" <c:if test="${pageName eq 'gosu' }"> class="active" </c:if> >낚시고수되기</a></li>
						<li><a href="/admin/lesson/sense/list.do" <c:if test="${pageName eq 'sense' }"> class="active" </c:if> >낚시상식</a></li>
						<li><a href="/admin/lesson/binding/list.do" <c:if test="${pageName eq 'binding' }"> class="active" </c:if> >채비필수묶음법</a></li>
						<li><a href="/admin/lesson/class/list.do" <c:if test="${pageName eq 'class' }"> class="active" </c:if> >어종별 낚시교실</a></li>
					</ul>
				</li>

				<li 
					<c:choose>
						<c:when test="${depthName eq 'info' or depthName eq 'promotion' or (depthName eq 'sosig' and pageName eq 'notice')}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe060;"></span><em>낚시정보</em></a>
					<ul>
						<li><a href="/admin/sosig/notice/list.do"<c:if test="${pageName eq 'notice' }"> class="active" </c:if> >공지사항</a></li>
						<li><a href="/admin/promotion/info/list.do"<c:if test="${pageName eq 'info' }"> class="active" </c:if>>알림마당</a></li>
						<li><a href="/admin/promotion/policy/list.do"<c:if test="${pageName eq 'policy' }"> class="active" </c:if>>낚시정책</a></li>
						<li><a href="/admin/promotion/campaign/list.do"<c:if test="${pageName eq 'campaign' }"> class="active" </c:if>>낚시캠페인</a></li>
						<li><a href="/admin/promotion/qna/list.do"<c:if test="${pageName eq 'qna' }"> class="active" </c:if> >낚시 FAQ</a></li>
						<li><a href="/admin/promotion/auditor/list.do"<c:if test="${pageName eq 'auditor' }"> class="active" </c:if> >낚시명예감시원</a></li>
						<%-- <li><a href="/info/admin/fishingAM.do" <c:if test="${pageName eq 'fishingAM' }"> class="active" </c:if> >낚시터 등록/수정</a></li>
						<li><a href="/info/admin/sanupAM.do" <c:if test="${pageName eq 'sanupAM' }"> class="active" </c:if> >낚시산업 등록/수정</a></li>
						<li><a href="/info/admin/boatfishing.do" <c:if test="${pageName eq 'boatfishing' }"> class="active" </c:if> >선상낚시</a></li>
						<li><a href="/info/admin/riverfishing.do"<c:if test="${pageName eq 'riverfishing' }"> class="active" </c:if> >민물낚시</a></li>
						<li><a href="/info/admin/seafishing.do" <c:if test="${pageName eq 'seafishing' }"> class="active" </c:if> >바다낚시</a></li>
						<li><a href="/admin/info/industry/list.do" <c:if test="${pageName eq 'industry' }"> class="active" </c:if> >낚시 산업정보</a></li>
						<li><a href="/admin/info/angling/list.do" <c:if test="${pageName eq 'angling' }"> class="active" </c:if> >조황정보</a></li>
						<li><a href="/admin/info/lab/list.do"<c:if test="${pageName eq 'lab' }"> class="active" </c:if> >낚시연구소</a></li>
						<li><a href="/admin/info/report/list.do"<c:if test="${pageName eq 'report' }"> class="active" </c:if> >낚시터 잘못된 정보 신고</a></li>
 						<li><a href="/admin/info/safe/list.do"<c:if test="${pageName eq 'safe' }"> class="active" </c:if> >낚시안전</a></li> --%>
 						 
					</ul>
				</li>
				<li <c:choose>
						<c:when test="${depthName eq 'share' or (depthName eq 'sosig' and pageName eq 'event') or depthName eq 'survey'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe077;"></span><em>커뮤니티</em></a>
					<ul>
						<li><a href="/admin/sosig/event/list.do"<c:if test="${pageName eq 'event' }"> class="active" </c:if> >이벤트</a></li>
						<li><a href="/admin/survey/survey/list.do"<c:if test="${pageName eq 'survey' }"> class="active" </c:if>>설문조사</a></li>
						<li><a href="/admin/share/nuri/list.do"<c:if test="${pageName eq 'nuri' }"> class="active" </c:if> >Q & A</a></li>
						<li><a href="/admin/share/column/list.do"<c:if test="${pageName eq 'column' }"> class="active" </c:if> >낚시칼럼(자유게시판)</a></li>
						<%-- <li><a href="/admin/share/travel/list.do"<c:if test="${pageName eq 'travel' }"> class="active" </c:if> >낚시여행기</a></li>
						<li><a href="/admin/share/zisik/list.do"<c:if test="${pageName eq 'zisik' }"> class="active" </c:if> >누리지식인</a></li>
						<li><a href="/admin/share/nuri/list.do"<c:if test="${pageName eq 'nuri' }"> class="active" </c:if> >자주묻는낚시질문</a></li>
						<li><a href="/admin/share/usage/list.do"<c:if test="${pageName eq 'usage' }"> class="active" </c:if> >낚시용품사용기</a></li>
						<li><a href="/admin/share/column/list.do"<c:if test="${pageName eq 'column' }"> class="active" </c:if> >낚시칼럼</a></li> --%>
					</ul>
				</li>
				<%-- <li <c:choose>
						<c:when test="${depthName eq 'sosig'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe013;"></span><em>낚시소식</em></a>
					<ul>
						<li><a href="/admin/sosig/news/list.do"<c:if test="${pageName eq 'news' }"> class="active" </c:if> >낚시뉴스</a></li>
						<li><a href="/admin/sosig/notice/list.do"<c:if test="${pageName eq 'notice' }"> class="active" </c:if> >공지사항</a></li>
						<li><a href="/admin/sosig/congress/list.do"<c:if test="${pageName eq 'congress' }"> class="active" </c:if> >낚시대회</a></li>
						<li><a href="/admin/sosig/event/list.do"<c:if test="${pageName eq 'event' }"> class="active" </c:if> >이벤트</a></li>
						<li><a href="/admin/sosig/gosi/list.do"<c:if test="${pageName eq 'gosi' }"> class="active" </c:if> >지역별 낚시준수사항</a></li>
					</ul>
				</li> --%>
				<%-- <li <c:choose>
						<c:when test="${depthName eq 'promotion'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>정책홍보</em></a>
					<ul>
						<li><a href="/admin/promotion/info/list.do"<c:if test="${pageName eq 'info' }"> class="active" </c:if>>알림마당</a></li>
						<li><a href="/admin/promotion/policy/list.do"<c:if test="${pageName eq 'policy' }"> class="active" </c:if>>낚시정책안내</a></li>
						<li><a href="/admin/promotion/campaign/list.do"<c:if test="${pageName eq 'campaign' }"> class="active" </c:if>>낚시캠페인</a></li>
						<li><a href="/admin/promotion/plocation/list.do"<c:if test="${pageName eq 'plocation' }"> class="active" </c:if>>낚시금지구역</a></li>
						<li><a href="/admin/promotion/qna/list.do"<c:if test="${pageName eq 'qna' }"> class="active" </c:if> ><span style="font-size:11px">낚시법 및 유어장 관련 질의회신 사례</span></a></li>
						<li><a href="/admin/promotion/auditor/list.do"<c:if test="${pageName eq 'auditor' }"> class="active" </c:if> >낚시명예감시원</a></li>
						<li><a href="/admin/policy/customersound/list.do"<c:if test="${pageName eq 'voc' }"> class="active" </c:if> >고객의 소리</a></li>
					</ul>
				</li> --%>
				<!--  -->
				<%-- <li <c:choose>
						<c:when test="${depthName eq 'survey'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>설문조사</em></a>
					<ul>
						<li><a href="/admin/survey/survey/list.do"<c:if test="${pageName eq 'survey' }"> class="active" </c:if>>설문조사</a></li>
					</ul>
				</li> --%>
				
				<li <c:choose>
						<c:when test="${depthName eq 'static'}">
								class="sub-menu active"
							</c:when> 
							<c:otherwise>
								class="sub-menu"				
							</c:otherwise>
						</c:choose> >
 				
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>통계</em></a>
					<ul>
						<li><a target="_blank" href="/admin/static/site/list.do">구버전 통계(낚시누리)</a></li>
						<li><a href="/seadm/analytics/summary/sitesummary.do">사이트 현황</a></li>
						<li><a href="/seadm/analytics/visit/uv.do">방문현황(UV)</a></li>
						<li><a href="/seadm/analytics/visit/pv.do">페이지뷰(PV)</a></li>
						<li><a href="/seadm/analytics/inflow/searchdashboard.do">검색 유입현황</a></li>
						<li><a href="/seadm/analytics/environment/osdashboard.do">운영체제(OS) 분석</a></li>
						<li><a href="/seadm/analytics/inflow/urls.do">페이지 분석</a></li>						
					</ul>
<!-- 					
					<ul>
						<li><a target="_blank" href="/admin/static/site/list.do"<c:if test="${pageName eq 'site' }"> class="active" </c:if>>사이트현황</a></li>
						
						<li><a target="_blank" href="/admin/static/invit/list.do"<c:if test="${pageName eq 'invit' }"> class="active" </c:if>>방문현황</a></li>
						<li><a target="_blank" href="/admin/static/invadindex/list.do"<c:if test="${pageName eq 'invadindex' }"> class="active" </c:if>>유입검색어</a></li>
						<li><a target="_blank" href="/admin/static/invadurl/list.do"<c:if test="${pageName eq 'invadurl' }"> class="active" </c:if>>유입상세URL</a></li>
						<li><a target="_blank" href="/admin/static/likepage/list.do"<c:if test="${pageName eq 'likepage' }"> class="active" </c:if>>인기페이지</a></li>
					
						<li><a target="_blank" href="/admin/static/invitos/list.do"<c:if test="${pageName eq 'invitos' }"> class="active" </c:if>>운영체제</a></li>
						
					</ul>
-->	
				</li>
				<li <c:choose>
						<c:when test="${depthName eq 'mainimg'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>사용자 메인화면 설정</em></a>
					<ul>
						<%-- <li><a href="/admin/info/mainimg/list.do"<c:if test="${pageName eq 'mainimg' }"> class="active" </c:if>>사용자 메인화면 설정</a></li> --%>
						<li><a href="/seadm/popup/list.do"<c:if test="${pageName eq 'mainimg' }"> class="active" </c:if>>팝업 및 배너관리</a></li>
					</ul>
				</li>
				<li <c:choose>
						<c:when test="${depthName eq 'adminset'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>관리자메일설정</em></a>
					<ul>
						<li><a href="/admin/info/adminset/view.do"<c:if test="${pageName eq 'adminset' }"> class="active" </c:if>>관리자메일설정</a></li>
					</ul>
				</li>
				<li <c:choose>
						<c:when test="${depthName eq 'smsmngr'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>문자관리</em></a>
					<ul>
						<li><a href="/admin/sms/smsmngr/contact.do"<c:if test="${pageName eq 'smsmngr' }"> class="active" </c:if>>문자관리</a></li>
					</ul>
				</li>
				<li <c:choose>
						<c:when test="${depthName eq 'logs'}">
							class="sub-menu active"
						</c:when> 
						<c:otherwise>
							class="sub-menu"				
						</c:otherwise>
					</c:choose> >
					<a href="javascript:void(0);"><span aria-hidden="true" data-icon="&#xe04c;"></span><em>로그</em></a>
					<ul>
						<li><a href="/admin/logs/list.do"<c:if test="${pageName eq 'logslist' }"> class="active" </c:if>>구버전 로그이력(낚시누리)</a></li>
						<li><a href="/adm/log/listRecSeadm.do">접속자 로그 기록</a></li>
						<li><a href="/adm/log/listSysSeadm.do">시스템 로그 기록</a></li>
					</ul>
				</li>
			</ul>
		</nav>
		<!-- } 좌측 메뉴 -->

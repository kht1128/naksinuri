/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.eduadm.main.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

/**
 * @Class Name : EgovDefaultPaginationRenderer.java
 * @Description : EgovDefaultPaginationRenderer Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 * @ 2018.11.13			  	관리자페이지를 위한 커스텀
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class EgovDefaultPaginationRenderer extends AbstractPaginationRenderer {

	public EgovDefaultPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables(PaginationInfo paginationInfo) {
		
		/*
		 * 페이징이 몇개 없을때 UI 문제를 해결해보자..
		String currentPageLabelCustom = "";
		int totalPageCount = paginationInfo.getTotalPageCount();
		if(totalPageCount < paginationInfo.getPageSize()) { //현재 페이지 갯수가 기준치보다 모자를때
			currentPageLabelCustom = "<li class=\"footable-page-nav disabled\" data-page=\"first\"><a class=\"footable-page-link\" href=\"#\">«</a></li>"+
							"<li class=\"footable-page-nav disabled\" data-page=\"prev\"><a class=\"footable-page-link\" href=\"#\">‹</a></li>"+
							"<li class=\"footable-page visible active\" data-page=\"{0}\"><a class=\"footable-page-link\" href=\"#\" >{0}</a></li>";							
		} else {
			currentPageLabelCustom = "<li class=\"footable-page visible active\" data-page=\"{0}\"><a class=\"footable-page-link\" href=\"#\" >{0}</a></li>";
		}
		*/
		firstPageLabel = "<li class=\"footable-page-nav \" data-page=\"first\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">«</a></li>";
		previousPageLabel = "<li class=\"footable-page-nav \" data-page=\"prev\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">‹</a></li>";
		currentPageLabel = "<li class=\"footable-page visible active\" data-page=\"{0}\"><a class=\"footable-page-link\" href=\"#;\" >{0}</a></li>";
		otherPageLabel = "<li class=\"footable-page visible\" data-page=\"{2}\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"footable-page-nav\" data-page=\"next\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">›</a></li>";
		lastPageLabel = "<li class=\"footable-page-nav\" data-page=\"last\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">»</a></li>";
	
		/*
		<li class="footable-page-nav disabled" data-page="first"><a class="footable-page-link" href="#">«</a></li>
		<li class="footable-page-nav disabled" data-page="prev"><a class="footable-page-link" href="#">‹</a></li>
		<li class="footable-page-nav disabled" data-page="prev-limit"><a class="footable-page-link" href="#">...</a></li>
		<li class="footable-page visible active" data-page="1"><a class="footable-page-link" href="#">1</a></li>
		<li class="footable-page visible" data-page="2"><a class="footable-page-link" href="#">2</a></li>
		<li class="footable-page visible" data-page="3"><a class="footable-page-link" href="#">3</a></li>
		<li class="footable-page visible" data-page="4"><a class="footable-page-link" href="#">4</a></li>
		<li class="footable-page visible" data-page="5"><a class="footable-page-link" href="#">5</a></li>
		<li class="footable-page" data-page="6"><a class="footable-page-link" href="#">6</a></li>
		<li class="footable-page" data-page="7"><a class="footable-page-link" href="#">7</a></li>
		<li class="footable-page" data-page="8"><a class="footable-page-link" href="#">8</a></li>
		<li class="footable-page" data-page="9"><a class="footable-page-link" href="#">9</a></li>
		<li class="footable-page-nav" data-page="next-limit"><a class="footable-page-link" href="#">...</a></li>
		<li class="footable-page-nav" data-page="next"><a class="footable-page-link" href="#">›</a></li>
		<li class="footable-page-nav" data-page="last"><a class="footable-page-link" href="#">»</a></li>
	
		firstPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/images/egovframework/cmmn/btn_page_pre10.gif' border=0/>어디민</a>&#160;";
		previousPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/images/egovframework/cmmn/btn_page_pre1.gif' border=0/></a>&#160;";
		currentPageLabel = "<strong>{0}</strong>&#160;";
		otherPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a>&#160;";
		nextPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/images/egovframework/cmmn/btn_page_next1.gif' border=0/></a>&#160;";
		lastPageLabel = "<a href=\"#\" onclick=\"{0}({1}); return false;\">" + "<image src='" + servletContext.getContextPath() + "/images/egovframework/cmmn/btn_page_next10.gif' border=0/></a>&#160;";
		*/
		
	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		initVariables(paginationInfo);
		return super.renderPagination(paginationInfo, jsFunction);
	}
}

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
package egovframework.cti.main.web;

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
public class EgovDefaultTopPaginationRenderer extends AbstractPaginationRenderer {

	public EgovDefaultTopPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables(PaginationInfo paginationInfo) {

		firstPageLabel = "<li class=\"page-item \" data-page=\"first\"><a class=\"page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">«</a></li>";
		previousPageLabel = "<li class=\"page-item \" data-page=\"prev\"><a class=\"page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">‹</a></li>";
		currentPageLabel = "<li class=\"page-item active\" data-page=\"{0}\"><a class=\"page-link\" href=\"#;\" >{0}</a></li>";
		otherPageLabel = "<li class=\"page-item \" data-page=\"{2}\"><a class=\"page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"page-item\" data-page=\"next\"><a class=\"page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">›</a></li>";
		lastPageLabel = "<li class=\"page-item\" data-page=\"last\"><a class=\"page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">»</a></li>";

	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		initVariables(paginationInfo);
		return super.renderPagination(paginationInfo, jsFunction);
	}
}

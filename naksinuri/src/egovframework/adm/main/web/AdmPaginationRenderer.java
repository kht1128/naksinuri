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
package egovframework.adm.main.web;

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
public class AdmPaginationRenderer extends AbstractPaginationRenderer {

	public AdmPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables(PaginationInfo paginationInfo) {

		firstPageLabel = "<li class=\"footable-page-nav \" data-page=\"first\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">«</a></li>";
		previousPageLabel = "<li class=\"footable-page-nav \" data-page=\"prev\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">‹</a></li>";
		currentPageLabel = "<li class=\"footable-page visible active\" data-page=\"{0}\"><a class=\"footable-page-link\" href=\"#;\" >{0}</a></li>";
		otherPageLabel = "<li class=\"footable-page visible\" data-page=\"{2}\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"footable-page-nav\" data-page=\"next\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">›</a></li>";
		lastPageLabel = "<li class=\"footable-page-nav\" data-page=\"last\"><a class=\"footable-page-link\" href=\"#;\" onclick=\"{0}({1}); return false;\">»</a></li>";
	
	}

	@Override
	public String renderPagination(PaginationInfo paginationInfo, String jsFunction) {
		initVariables(paginationInfo);
		return super.renderPagination(paginationInfo, jsFunction);
	}
}

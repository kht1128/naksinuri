package egovframework.all.main.web;

import egovframework.rte.ptl.mvc.tags.ui.pagination.AbstractPaginationRenderer;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public class EgovListPaginationRenderer extends AbstractPaginationRenderer implements ServletContextAware{

	private ServletContext servletContext;

	public EgovListPaginationRenderer() {
		// no-op
	}

	/**
	* PaginationRenderer
	*
	* @see 개발프레임웍크 실행환경 개발팀
	*/
	public void initVariables() {

		firstPageLabel = "<li class=\"footable-page-nav visible\" data-page=\"first\"><a class=\"footable-page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">«</a></li>";
		previousPageLabel = "<li class=\"footable-page-nav visible\" data-page=\"prev\"><a class=\"footable-page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">‹</a></li>";
		currentPageLabel = "<li class=\"footable-page visible active\" data-page=\"{0}\"><a class=\"footable-page-link\" title=\"{0} 페이지 선택됨\" href=\"#\">{0}</a></li>";
		otherPageLabel = "<li class=\"footable-page visible\" data-page=\"{2}\"><a class=\"footable-page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">{2}</a></li>";
		nextPageLabel = "<li class=\"footable-page-nav\" data-page=\"next\"><a class=\"footable-page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">›</a></li>";
		lastPageLabel = "<li class=\"footable-page-nav\" data-page=\"last\"><a class=\"footable-page-link\" href=\"#\" onclick=\"{0}({1}); return false;\">»</a></li>";
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		initVariables();
	}
}

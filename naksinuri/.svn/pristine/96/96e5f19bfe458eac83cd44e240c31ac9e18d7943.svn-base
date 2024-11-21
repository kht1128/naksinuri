<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ include file="../head.jsp" %> 
<%@ include file="../left_menu.jsp" %>

<form:form commandName="eduMemberVO" id="listForm" name="listForm" method="post" 
	action="/seadm/member/list_lv.do">
<input type="hidden" name="pageIndex" value="1"/>
</form:form>

<form:form commandName="eduMemberVO" id="addForm" name="addForm" method="post">
<input type="hidden" name="MBR_LV_ID" value=""/>
<input type="hidden" name="MBR_GRP_ID" value=""/>
<input type="hidden" name="MBR_POSITION_CD" value=""/>
<input type="hidden" name="modal_title" value=""/>
</form:form>

<form:form commandName="eduMemberVO" id="delForm" name="delForm" method="post">
<input type="hidden" name="MBR_ID" value=""/>
<input type="hidden" name="MBR_LV_ID" value=""/>
<input type="hidden" name="MBR_GRP_ID" value=""/>
<input type="hidden" name="MBR_POSITION_CD" value=""/>
</form:form>

<!-- Page -->
<div class="page">
	<div class="page-header">
		<c:choose>
			<c:when test="${not empty subpageNum}">
				<h1 class="page-title">${subpageName}</h1>
		        <ol class="breadcrumb">
		        	<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item">
		        		<a href="${pageUrl}">${pageName}</a>
		        	</li>
		        	<li class="breadcrumb-item active">${subpageName}</li>
		      	</ol>
			</c:when>
			<c:otherwise>
				<h1 class="page-title">${pageName}</h1>
		        <ol class="breadcrumb">
		       		<li class="breadcrumb-item">${depthName}</li>
		        	<li class="breadcrumb-item active">${pageName}</li>
		      	</ol>
			</c:otherwise>											
		</c:choose>
		<div class="page-header-actions">
		</div>	
	</div>		
	<div class="page-content container-fluid">
		
		<hr/>
      	<h4 class="blue-grey-600">1단계</h4>
      	<hr/>
      	
      	<!-- Row -->
		<h4 class="blue-600">사용자 등급 설정</h4>
		<div class="row">
			<!-- block -->
       		<div class="col-md-4">
        		<!-- panel -->
        		<div class="panel panel-line panel-danger">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>1등급 그룹 목록</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data" 
             				data-title="1등급 그룹 회원 추가"
             				data-mbrgrpid=""
             				data-mbrlvid="2"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>            		
					<c:if test="${empty list_lv2}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv2}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid=""
		             			data-mbrlvid="3"
		             			data-mbrpositioncd=""
								data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-line panel-primary">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>2등급 그룹 목록</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="2등급 그룹 회원 추가" 
             				data-mbrgrpid=""
             				data-mbrlvid="3"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
					<c:if test="${empty list_lv3}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv3}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid=""
	             				data-mbrlvid="3"
	             				data-mbrpositioncd=""
								data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>3등급 그룹 목록</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="3등급 그룹 회원 추가" 
             				data-mbrgrpid=""
             				data-mbrlvid="4"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
           			<c:if test="${empty list_lv4}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid=""
           						data-mbrlvid="4"
           						data-mbrpositioncd=""
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			</div>
		<!-- End Row -->
		
		<!-- panel -->
  		<div class="panel">
      		<div class="panel-body red-600">
      			<b>** 위 사용자 등급 설정을 먼저 하신 뒤 아래 권한설정을 진행해주세요 **</b>
      		</div>
      	</div>
      	<!-- End panel -->
      	
      	<hr/>
      	<h4 class="blue-grey-600">2단계&nbsp;<small>( 각 등급에 포함된 사용자만 아래 각 권한을 설정 하실 수 있습니다. )</small></h4>
      	<hr/>
      	
		<!-- Row -->
		<h4 class="blue-600">낚시누리 권한 목록</h4>
		<div class="row">
			<!-- block -->
       		<div class="col-md-4">
        		<!-- panel -->
        		<div class="panel panel-line panel-danger">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>총관리자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시누리 총관리자 권한부여"
             				data-mbrgrpid="1"
             				data-mbrlvid="2" 
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
           			<c:if test="${empty list_lv2_g1_center}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv2_g1_center}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="1"
           						data-mbrlvid="2" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-line panel-primary">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>공단운영자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시누리 공단운영자 권한부여" 
             				data-mbrgrpid="1"
             				data-mbrlvid="3"   
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
					<c:if test="${empty list_lv3_g1_manager}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv3_g1_manager}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="1"
           						data-mbrlvid="3" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>	
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel 
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>홈스테이운영자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="종합센터 홈스테이운영자 권한부여" 
             				data-mbrgrpid="1"							
             				data-mbrlvid="4"
             				data-mbrpositioncd="1">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv4_g1_homestay}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4_g1_homestay}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="1"
           						data-mbrlvid="4" 
           						data-mbrpositioncd="1"   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>	
				</div>
				-->
				<!-- End panel -->
			</div>
			<!-- End block -->
		</div>
		<!-- End Row -->
		<%--
		<!-- Row -->
		<h4 class="blue-600">박람회 권한 목록</h4>
		<div class="row">
			<!-- block -->
       		<div class="col-md-4">
        		<!-- panel -->
        		<div class="panel panel-line panel-danger">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>총관리자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="박람회 총관리자 권한부여" 
             				data-mbrgrpid="3"							
             				data-mbrlvid="2"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv2_g3_center}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv2_g3_center}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="3"
           						data-mbrlvid="2" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>	
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-line panel-primary">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>공단운영자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="박람회 센터운영자 권한부여" 
             				data-mbrgrpid="3"							
             				data-mbrlvid="3"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv3_g3_manager}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv3_g3_manager}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="3"
           						data-mbrlvid="3" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>외부운영자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="박람회 외부운영자 권한부여" 
             				data-mbrgrpid="3"							
             				data-mbrlvid="4"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv4_g3_outsider}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4_g3_outsider}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="3"
           						data-mbrlvid="4" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
		</div>
		<!-- End Row -->
		 --%>
		<!-- Row -->
		<h4 class="blue-600">낚시전문교육 권한 목록</h4>
		<div class="row">
			<!-- block -->
       		<div class="col-md-4">
        		<!-- panel -->
        		<div class="panel panel-line panel-danger">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>총관리자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시전문교육 총관리자 권한부여" 
             				data-mbrgrpid="2"							
             				data-mbrlvid="2"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv2_g2_center}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv2_g2_center}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="2"
           						data-mbrlvid="2" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-line panel-primary">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>공단운영자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시전문교육 공단운영자 권한부여" 
             				data-mbrgrpid="2"							
             				data-mbrlvid="3"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv3_g2_manager}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv3_g2_manager}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="2"
           						data-mbrlvid="3" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>지자체</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시전문교육 지자체 권한부여" 
             				data-mbrgrpid="2"							
             				data-mbrlvid="4"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv4_g2_outsider}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4_g2_outsider}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="2"
           						data-mbrlvid="4" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
				<!-- panel -->
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>해양경찰</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시전문교육 해양경찰 권한부여" 
             				data-mbrgrpid="1"							
             				data-mbrlvid="4"
             				data-mbrpositioncd="2">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv4_g1_doctor}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4_g1_doctor}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="1"
           						data-mbrlvid="4" 
           						data-mbrpositioncd="2"   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>	
				</div>
				<!-- End panel -->
				<!-- panel -->
         		<div class="panel panel-success panel-line">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>교육센터</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="낚시전문교육 교육센터 권한부여" 
             				data-mbrgrpid="3"							
             				data-mbrlvid="4"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv4_g3_outsider}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv4_g3_outsider}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="3"
           						data-mbrlvid="4" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>	
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
		</div>
		<!-- End Row -->
		
		<%--
		<!-- Row -->
		<h4 class="blue-600">CTI 권한 목록</h4>
		<div class="row">
			<!-- block -->
       		<div class="col-md-4">
        		<!-- panel -->
        		<div class="panel panel-line panel-danger">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>총관리자</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="CTI 총관리자 권한부여" 
             				data-mbrgrpid="4"							
             				data-mbrlvid="2"
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv2_g4_center}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv2_g4_center}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="4"
           						data-mbrlvid="2" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
			<!-- block -->
			<div class="col-md-4">
				<!-- panel -->
         		<div class="panel panel-line panel-primary">
            		<header class="panel-heading">
              			<h3 class="panel-title"><small>센터운영자(상담사)</small></h3>
              			<div class="panel-actions">
             				<button type="button" class="btn btn-warning btn-xs clk_add_data"
             				data-title="CTI 총관리자 센터운영자(상담사) 권한부여"
             				data-mbrgrpid="4"							
             				data-mbrlvid="3" 
             				data-mbrpositioncd="">회원추가</button>
						</div>
            		</header>
            		<c:if test="${empty list_lv3_g4_manager}">
						<div class="panel-body">
						등록 된 회원이 없습니다.
						</div>
					</c:if>
       				<ul class="list-group list-group-bordered">
		            	<c:forEach var="item" varStatus="status" items="${list_lv3_g4_manager}">
							<li class="list-group-item">${item.MBR_NM}(&nbsp;${item.MBR_ID}&nbsp;)
								<a href="#;" class="btn btn-sm btn-icon btn-pure btn-default on-default remove-row clk_del_data" data-toggle="tooltip" data-original-title="삭제" 
								data-mbrgrpid="4"
           						data-mbrlvid="3" 
           						data-mbrpositioncd=""   
           						data-mbr-id="${item.MBR_ID}">
			                		<i class="icon wb-trash" aria-hidden="true"></i>
			                	</a>
		                	</li>
						</c:forEach>  
		            </ul>
				</div>
				<!-- End panel -->
			</div>
			<!-- End block -->
		</div>
		<!-- End Row -->
		 --%>
		<hr/>
		
		<!-- panel 
  		<div class="panel">
      		<header class="panel-heading">
        			<h3 class="panel-title"><small>참고사항</small></h3>
        			<div class="panel-actions"></div>
      		</header>
      		<div class="panel-body">
      		<code>* 회원레벨( 1:통합관리자,2:총관리자,3:센터운영자,4:외부운영자,10:일반사용자 )</code> : MBR_LV_ID
      		<br>
			<code>* 그룹아이디( 0:전체,1:종합센터,2:교육센터,3:박람회,4:cti )</code> : MBR_GRP_ID - 이거대신 개별 권한 설정으로 변경 MBR_GRP_1_ST ...
			<br>
			<code>* 그룹아이디( 0:구분없음,1:홈스테이,2:귀어닥터 )</code> : MBR_POSITION_CD 홈스테이,귀어닥터 등 구분자
			</div>
      	</div>
      	-->
      	<!-- End panel -->
		
	</div>
</div>
<!-- End Page -->


<script>
$(".clk_add_data").click(function() {
	var form = document.getElementById('addForm');
	form.modal_title.value = $(this).attr('data-title');
	form.MBR_LV_ID.value = $(this).attr('data-mbrlvid');
	form.MBR_GRP_ID.value = $(this).attr('data-mbrgrpid');
	form.MBR_POSITION_CD.value =  $(this).attr('data-mbrpositioncd');
	form.action = '';
	$.ajax({
		type:"POST",
		url :"/seadm/member/auth/add.do",
		data:$('#addForm').serialize(),
		dataType: 'html',//"json",
		contentType: "application/x-www-form-urlencoded;charset=UTF-8",//"application/json;charset=UTF-8",
		async: true,
		success: function(data, status, xhr) {
			//console.log('success!');
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
});  
$(".clk_del_data").click(function() {	
	var MBR_LV_ID = $(this).attr('data-mbrlvid');
	var MBR_GRP_ID = $(this).attr('data-mbrgrpid');
	var MBR_POSITION_CD =  $(this).attr('data-mbrpositioncd');
	var MBR_ID = $(this).attr('data-mbr-id');	
	alertify.confirm("해당 회원의 권한을 삭제하시겠습니까?", function(){ 
		//ok
		var form = document.getElementById('delForm');
		form.MBR_LV_ID.value = MBR_LV_ID;
		form.MBR_GRP_ID.value = MBR_GRP_ID;
		form.MBR_POSITION_CD.value =  MBR_POSITION_CD;
		form.MBR_ID.value = MBR_ID;
		form.action = '';
    	$.ajax({
			type:"POST",
			url :"/seadm/member/auth/delete_act.do",
			data:$('#delForm').serialize(),
			dataType: 'json',
			contentType: "application/x-www-form-urlencoded;charset=UTF-8",
			async: true,
			success: function(data, status, xhr) {
				if(data.error == '1') {
					alertify.alert(data.msg);
				} else {
					window.location.reload();	
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
});  
</script>


<%@ include file="../tail.jsp" %>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<c:set var="depthName" value="smsmngr" />
<c:set var="pageName" value="event" />
<%@include file="../../header_admin.jsp"%>
<%@include file="../../login_header.jsp"%>
<body oncontextmenu="return false;">
<form action="" id="frm" name="frm" method="post">
</form>
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="fishing_type" id="fishing_type" value="boatfishing" />

<div id="wrapper">
<%@include file="../../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">		
		
			
			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
						<li><a href="${pageContext.request.contextPath}/admin/sms/smsmngr/contact.do" class="cateend">연락처</a></li>
						<li><a href="#;" class="cateing on">그룹관리</a></li>
					</ul>
				</div>	
			</section>
			
			
			<section class="msg-box">
					
				<div class="msg-box-left">
					<div class="padding_box">
							<%@include file="./left_wrap.jsp"%>
					</div>
				</div>	
				
				<div class="msg-box-right">	
					<div class="padding_box">
							
						<div class="msg-box-right-top">	
							
							<input type="text" class="form-control group_add_input frm_input" placeholder="그룹명" value="">
						    <span class="input-group-btn">
						    	<button class="btn btn-default btn_group_add_act" type="button">추가</button>
						   	</span>
							
						</div>	
						
						<div class="msg-box-right-bottom">
														
							<div class="t_list_area">
								<table class="t_list">									
							         <colgroup>
										<col width="*">
										<col width="200">
									</colgroup>
									<thead>
										<tr>
											<th>그룹명</th>
											<th>관리</th>
										</tr>
									</thead>
									<tbody id="sms_list">
									</tbody>
							    </table>
							    <div id="pagenation"></div>
						    </div>
						    						
						</div>	
					
				    </div>		  
				</div>
				
			</section>
		
		</div>
	</div>

	<!-- 하단 푸터 { -->
	<footer id="footer" class="floats">
		<div class="l_version">
			No Background Tasks <em>Version 4.4.0.5</em>
		</div>
		<div class="r_copyright">
			<b>Endpoint Protector 4</b> Copyright 2004-2016 CoSoSys Ltd. All rights reserved.
		</div>
	</footer>
	<!-- } 하단 푸터 -->
</div>
<script>

function getGroupList(nPage) {
	console.log('ajax call group.jsp');
	   var target = $("#sms_list");
	   url = '/admin/sms/smsmngr/grouplist.do';
	   var params = 'pageIndex='+nPage;

	   $.ajax({
		   url: url,
			type: 'post',
			dataType: 'json',
			data : params,
			async: false,
	      beforeSend : function() {
	         //$(target).html(loading);
	      },
	      error : function(request ,status, error) {
	         alert('AJAX 통신 중 에러가 발생했습니다.');
	         console.log( request.responseText );
	      },
	      success : function(response, status, request) {
	    	  //console.log( response );
	    	  var html = "";
	    	  
	    	  $(target).html(html);
	    	  
	    	  var params = {
	                  divId : "PAGE_NAVI",
	                  pageIndex : response.pageIndex,
	                  totalCount : response.totalPage,
	                  eventName : "/admin/sms/smsmngr/contactlist.do"
	              };
	              //gfn_renderPaging(params);
	              
	              paginghtml = paging(response.pageIndex, response.pageUnit, 10, response.totalPage);

	              if ( response.totalPage > 0 )
					$("#pagenation").html(paginghtml);

						
	    	  
	    	  lists = response.lists;
	    	  
	    	  $.each(lists, function(i, n) {	
	    		  //console.log( n.groupname );
	    		  html = "";
	    		  
	    		  html += '<tr data-id="' + n.idx + '">';
	    		  html += '	<td>';
	    		  //html += ' <span class="group_name">' + n.groupname + '</span>';
	    		  html += '		<div class="group_edit">';
	    		  html += '		    <div class="input-group">';
	    		  html += '		      <input type="text" class="form-control frm_input group_edit_input" placeholder="그룹명" value="' + n.groupname + '">';
	    		  html += '		      <span class="input-group-btn"> 소속그룹인원: ' + n.groupusers + ' 명</span>';
	    		  //html += '		      <span class="input-group-btn">';
	    		  //html += '		        <button class="btn btn_violet btn_group_edit_act" type="button">적용</button>';
	    		  //html += '		      </span>';
	    		  html += '			    </div><!-- /input-group -->';
	    		  html += '		</div>';
	    		  html += '	</td>';
	    		  html += '	<td class="textcenter">';
	    		  //html += '    <div class="td_mngsmall" style="margin:0 auto;">';
	    	  	  html += '			<button type="button" class="btn_violet btn_group_edit">수정</button>';
	    		  html += '			<button type="button" class="btn_orange btn_group_del">삭제</button>';
	    		 // html += '    </div>';
	    		  html += '	</td>';
	    		  html += '</tr>';
				
				if( i == 0 ) {
					//console.log("최초 삽입줄이네");
					$(target).html(html);
				} else {
					$(target).append(html);
				}
				
	    	  });
	    	  
	    	  
	        // $(target).html('');
	         //$(target).html(response);
	      }
	   });
	}
	
	$(document).ready(function() {
		getGroupList(1);
	
		$(".btn_group_add_act").click(function(event) {
			var change_name = $('.group_add_input').val();
			
			if ( change_name == '') {
				alert('추가할 그룹명을 입력해주십시요.');
				return;
			}
			
			var target = $("#sms_list");
			
			url = '/admin/sms/smsmngr/groupinsert.do';
		   		   
			var params = 'group='+change_name;
			
			$.ajax({
				url: url,
				type: 'post',
				dataType: 'json',
				data : params,
				async: false,
			   beforeSend : function() {
			      //$(target).html(loading);
			   },
			   error : function(request ,status, error) {
			      alert('AJAX 통신 중 에러가 발생했습니다.');
			      console.log( request.responseText );
			   },
			   success : function(response, status, request) {
			 	  console.log( response );
			 	  if ( response.error == 0 ) { 
			 	 	getGroupList(1);
			 	  } else {
			 		alert("동일한 그룹명이 존재합니다.");
			 	  }
			 	  var html = "";
			   }
			 	  
				});
		});
		
		$('.t_list').on('click', '.btn_group_edit', function()	{
			var tr = $(this).closest('tr');
			var group_key = $(tr).attr('data-id');
			var change_name = $(tr).find('.group_edit_input').val();
			
			if ( change_name == '') {
				alert('변경할 그룹명을 입력해주십시요.');
				return;
			}
			
	
			$(tr).find('.group_name').hide();
			$(tr).find('.group_edit').show();
			
			url = '/admin/sms/smsmngr/groupupdate.do';
			   
			var params = 'idx='+group_key+"&group="+change_name;
			
			console.log("btn_group_edit params =" + params);
			
			$.ajax({
				url: url,
				type: 'post',
				dataType: 'json',
				data : params,
				async: false,
			   beforeSend : function() {
			      //$(target).html(loading);
			   },
			   error : function(request ,status, error) {
			      alert('AJAX 통신 중 에러가 발생했습니다.');
			      console.log( request.responseText );
			   },
			   success : function(response, status, request) {
			 	  console.log( response );
			 	  
			 	 	getGroupList(1);	
			 	 	alert('그룹정보가 성공적으로 변경되었습니다.');
			   }
			 	  
				});
	
			// var change_name = $(tr).find('.group_edit_input').val();
			// console.log( change_name );
		});
	
		//$(".btn_group_del").click(function(event) {
	$('.t_list').on('click', '.btn_group_del', function()	{
		if( !confirm("해당 그룹에 지정된 연락처는 [미지정] 상태로 되돌아갑니다.\r정말 삭제하시겠습니까?") ) return false;


		var tr = $(this).closest('tr');
		var group_key = $(tr).attr('data-id');
		
		//console.log("btn_group_del " + group_key);
		
		url = '/admin/sms/smsmngr/groupdelete.do';
		   
		var params = 'idx='+group_key;
		
		$.ajax({
		   url: url,
		   dataType: 'json',
		   data : params,
			contentType: false,
			async: false,
		   beforeSend : function() {
		      //$(target).html(loading);
		   },
		   error : function(request ,status, error) {
		      alert('AJAX 통신 중 에러가 발생했습니다.');
		      console.log( request.responseText );
		   },
		   success : function(response, status, request) {
		 	  console.log( response );
		 	 getGroupList(1);
		 	alert('그룹정보가 성공적으로 삭제되었습니다.');
		   }
		 	  
			});
	});

	});

	$(function(){
	      // 페이징
	      $(".ajax_pagination li > a").each(function(index, el) {
	         page = $(this).attr('data-ci-pagination-page');
	         if( page >= 1 ) {
	            url = 'contact?'+$("#form_search_act").serialize()+'&page='+page;
	            link = "javascript:goto_sms('"+url+"')";
	            $(this).attr('href', link);
	         } else {
	            $(this).attr('href', '#');
	         }
	      });
	   });

	$(function() {
	        $( "#sstx, #setx, #datepicker" ).datepicker({

	         dateFormat: 'yy-mm-dd',
	         prevText: '이전 달',
	         nextText: '다음 달',
	         monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12'],
	         monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
	         dayNames: ['일','월','화','수','목','금','토'],
	         dayNamesShort: ['일','월','화','수','목','금','토'],
	         dayNamesMin: ['일','월','화','수','목','금','토'],
	         showMonthAfterYear: true,
	         yearSuffix: '.'
	        });

	      $('#sstx').datepicker();
	       $('#sstx').datepicker("option", "maxDate", $("#setx").val());
	       
	   	$('#sstx').datepicker("option", "onClose", function ( selectedDate ) {
	           $("#setx").datepicker( "option", "minDate", selectedDate );
	       });
	    
	       $('#setx').datepicker();
	       $('#setx').datepicker("option", "minDate", $("#sstx").val());
	       
	   $('#setx').datepicker("option", "onClose", function ( selectedDate ) {
	         $("#sstx").datepicker( "option", "maxDate", selectedDate );
	       });

	});


function view2(evn_no){
	var form = document.getElementById('listform');
	$('#evn_no').val(evn_no);
	
	form.action="./event_findCorp.do";
	form.submit();
};

function goto_sms(purl, pbtn) {
	var form = document.getElementById('frm');
	
	form.action="/admin/sms/smsmngr/"+purl+".do";
	form.submit();
}

function fncCheckAll() {
    var checkField = document.listform.delYn;
    if(document.listform.checkAll.checked) {
        if(checkField) {
            if(checkField.length > 1) {
                for(var i=0; i < checkField.length; i++) {
                    checkField[i].checked = true;
                }
            } else {
                checkField.checked = true;
            }
        }
    } else {
        if(checkField) {
            if(checkField.length > 1) {
                for(var j=0; j < checkField.length; j++) {
                    checkField[j].checked = false;
                }
            } else {
                checkField.checked = false;
            }
        }
    }
}


function fncGroupListDelete() {
	if(fncManageChecked()) {
	    if(confirm("삭제하시겠습니까?")) {
            document.listform.action = "./gotrash_list.do";
            document.listform.submit();
	    }
	}
}



function fncManageChecked() {

    var checkField = document.listform.delYn;
    var checkId = document.listform.delYn;
    var returnValue = "";
    var returnBoolean = false;
    var checkCount = 0;

    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i<checkField.length; i++) {
                if(checkField[i].checked) {
                	checkCount++;
                    checkField[i].value = checkId[i].value;

                    if(returnValue == "")
                        returnValue = checkField[i].value;
                    else
                        returnValue = returnValue + ";" + checkField[i].value;
                }
                
            }
            if(checkCount > 0)
                returnBoolean = true;
            else {
                alert("선택된  그룹이 없습니다.");
                returnBoolean = false;
            }
        } else {
        	 if(document.listform.delYn.checked == false) {
                alert("선택된 그룹이 없습니다.");
                returnBoolean = false;
            }
            else {
                returnValue = checkId.value;
                returnBoolean = true;
            }
        }
    }

    document.listform.bo_sns.value = returnValue;

    return returnBoolean;
}


function fnSelectInfs(pageIndex) {
	var idx= $('#s_pageUnit').val();
	$("#pageUnit").val(idx);
	$("#pageIndex").val(pageIndex);	
	$("#frm").attr("action", "./list.do");
	$("#frm").submit();
	//페이징 숫자 버튼 눌렀을때 이 함수가 호출되면서 서브밋으로 주소에 포스트로 값 넘긴다..
}

$(function(){
	var idx= $('#pageUnit').val();
	$("#s_pageUnit").val(idx);
});


function paging(curpage, countperpage, pageviewsu, totalcount)
{
	var paginghtml = '';

	/* 페이지 계산 */
	var start = (curpage - 1) * countperpage;					
	var pagesu=Math.ceil(totalcount / countperpage ); 	// 페이지수 계산
	var pagegroup=Math.ceil(( curpage ) / pageviewsu); //페이지 그룹결정 
	var pagestart=(pageviewsu *(pagegroup-1))+1; 
	var pageend=pagestart+ pageviewsu -1; 
	var prev = '';
	var prevpage = '';
	
	
	console.log('curpage : ' + curpage + ',  pagesu : ' + pagesu + ', curpage : ' + curpage);
	//console_log('pagestart : ' + pagestart + ',  pageend : ' + pageend + ', pagegroup : ' + pagegroup);

	if(pagegroup>1){ 
		//prev=pagestart-pageviewsu-1;//이전목록그룹의 시작페이지결정 
		prev=(pagegroup-1) * pageviewsu;//이전목록그룹의 시작페이지결정 
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+prev+"'); ><<</a></ul>";
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getGroupList('"+prev+"'); ><<</a>";
	} 
	if(curpage>1){ 
		prevpage=curpage-1; 
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+prevpage+"'); ><</a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getGroupList('"+prevpage+"'); ><</a>";
	} 

	for(i=pagestart;i<=pageend;i++) 
	{ 
		if(pagesu<i){break;} 
		j = i;

		if(j==curpage) {
			//paginghtml += "<li class='page-item active'><a href='javascript:void(0);' class='active' onclick=javascript:getContactlist('"+j+"'); >"+i+"</a></ul>";
			paginghtml += "<strong>"+i+"</strong>";
		} else {
			//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+j+"'); >"+i+"</a></ul>";
			paginghtml += "<a href='javascript:void(0);' onclick=javascript:getGroupList('"+j+"'); >"+i+"</a>";
		}
	} 

//console.log('curpage : ' + curpage + ',  pagesu : ' + pagesu + ', curpage : ' + curpage);
	if((curpage) != pagesu && pagesu != 0 ){ 
		nextpage=Number(curpage) + 1; 
		console.log('nextpage : '+nextpage);
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+nextpage+"'); >></a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getGroupList('"+nextpage+"'); >></a>";
	} 

	if(pageend<pagesu)
	{
		pageend += 1;
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+pageend+"'); >>></a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getGroupList('"+pageend+"'); >>></a>";
	}

	return paginghtml;
}

</script>
</body>

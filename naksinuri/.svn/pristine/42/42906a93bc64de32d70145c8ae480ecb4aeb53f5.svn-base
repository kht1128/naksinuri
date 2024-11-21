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
<div class="ajax-loader">
  <img src="/naksinuri_original/common_main/img/ajax-loader.gif" class="img-responsive" />
</div>

<form action="" id="frm" name="frm" method="post">
</form>
<div id="wrapper">

<style type="text/css">

.ajax-loader {
  display: none;
  background-color: rgba(255,255,255,0.7);
  position: absolute;
  z-index: +100 !important;
  top: 0px;
  left: 0px;
  width: 100%;
  /*
  height:100%;
  */
  height:auto;
}

.ajax-loader img {
  position: relative;
  top:50%;
  left:50%;
}
</style>
<%@include file="../../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">
				
			<section id="table-list">
				<!-- 탭 영역 { -->
				<div id="tabarea">
					<ul class="floats">
						<li><a href="#;" class="cateing on">연락처</a></li>
						<li><a href="${pageContext.request.contextPath}/admin/sms/smsmngr/group.do" class="cateend">그룹관리</a></li>
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
							<form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
			                	<input type="file" id="client_excel_f" name="client_excel_f" >
			                	<button type="button" type="button" class="btn btn_orange" onclick="add_excel_member();">연락처 업로드 엑셀파일 실행하기</button>
			                </form:form>
			                <button type="button" type="button" class="btn btn_blue contact_insert_modal_btn">연락처개별추가</button>
						</div>	
						
						
						
						<!-- 검색 영역 { -->
						<div id="search_form">
							<table class="t_search">
								<colgroup>
									<col width="80" />
									<col />
									<col width="80" />
									<col />
									<col width="80" />
									<col />
								</colgroup>
								<tbody>
									<tr>
										<th>검색</th>
										<td colspan="5">
												
											<input type="text" class="frm_input" size="50" id="searchText" name="searchText" value="${searchText}" placeholder="이름 또는 전화번호"/><br/>
											<select class="form-control frm_select" id="group_search">
									          <option value="">검색할 그룹선택</option>								          
									        </select>		
									        페이징없이 보기 : <input type="checkbox" id="all_read" >							 
											<button class="btn_size1 btn_violet" type="button" onclick="getContactlist(1);">검색</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						
						<div class="msg-box-right-bottom">
						<button type="button" class="btn btn-xs btn-default" onclick="sms_obj.person_multi_add()">선택추가</button>
					
					        <select class="form-control frm_select" id="group_match">
					          <option value="">선택 그룹지정</option>
					          <option value="미지정">미지정</option>
					          
					        </select>
					
					        <button type="button" class="btn btn-xs grey pull-right sms_p_delete_act">삭제</button>
							
							<div class="t_list_area">
								<table class="t_list">									
							         <thead>
							            <tr>
							               <th scope="col" id="mb_list_chk" >
							                  <input type="checkbox" id="all_checked" onclick="sms_obj.book_all_checked(this.checked)">
							               </th>
							               <!--<th scope="col">번호</th>-->
							               <th scope="col">이름</th>
							               <th scope="col">휴대폰번호</th>										              
							               <th scope="col">그룹</th>
							
							               <th scope="col">추가</th>
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

<div id="contact_insert_modal" class="contact_insert modal">
	<form id="frmadd" name="frmadd" class="" action=""  method="post"   >	
			<div class="modal-header">
				<h4 class="modal-title">연락처 추가</h4>
			</div>
			<div class="modal-body">
				<div style="margin:5px 0 0 5px;">
   				<label class="control-label name" for="name"><span class="caution">*</span>이름</label>
				<input name="name" class="name  frm_input" type="text" placeholder="이름을 입력하세요" class="" value="" >			 
				</div>
				<div style="margin:5px 0 0 5px;">
				<label class="control-label" for="hp"><span class="caution">*</span>전화번호</label>
				<input name="hp" class="hp  frm_input" type="text" placeholder="전화번호를 입력하세요" class=""  value="" >
				</div>	
				
				<div style="margin:5px 0 0 5px;">
				<label class="control-label" for="hp"><span class="caution">*</span>그룹선택</label>
				<select class="form-control frm_select" name="groupname" class="groupname" id="foraddgroupname">
		          <option value="">그룹선택</option>								          
		        </select>	
				</div>	
				
					<button type="button" class="btn btn-primary"  style="margin:15px 0 0 0;" onclick="submitFormContactInsert(document.frmadd);">확인</button>
					
		</div>	
	</form>
</div>

<div id="contact_update_modal" class="contact_update modal" >

	<form id="frmedit" name="frmedit"  action=""  method="post">		
	<input type="hidden" name="idx" class="idx" value="">
			<div class="modal-header">
				<h4 class="modal-title">연락처 변경</h4>
			</div>
			<div class="modal-body">
   				<div style="margin:5px 0 0 5px;">
   				<label class="control-label" for="name"><span class="caution">*</span>이름</label>
					<input name="name" class="name  frm_input" type="text" placeholder="이름을 입력하세요" class="" value="">			 
				</div>
				<div style="margin:5px 0 0 5px;">	
		<label class="control-label" for="hp"><span class="caution">*</span>전화번호</label>
					<input name="hp" class="hp frm_input" type="text" placeholder="전화번호를 입력하세요" class=""  value="" >
					</div>
				<div style="margin:5px 0 0 5px;">
				<label class="control-label" for="hp"><span class="caution">*</span>그룹선택</label>
				<select class="form-control frm_select" name="groupname" class="groupname" id="forupdategroupname">
		          <option value="">그룹선택</option>								          
		        </select>	
				</div>	
					
					<button type="button" class="btn btn-primary" style="margin:15px 0 0 0;" onclick="submitFormContactUpdate(document.frmedit);">확인</button>
					
		</div>	
	</form>

</div>


<script>

var isProcess = false;

function add_excel_member() {
	 var form = $('#imform')[0];
	 var formData = new FormData(form);
	 
	 //$('.ajax-loader').css("visibility", "visible");
	 $('.ajax-loader').css("display", "inline");
	 
	 $.ajax({
       type: "POST",
       enctype: 'multipart/form-data',
       url: "/admin/sms/smsmngr/ajax_upload_excel.do",
       data: formData,
       processData: false,
       contentType: false,
       cache: false,
       timeout: 600000,
       success: function (data) {
           console.log("SUCCESS : ", data);
           getContactlist(1);       
           getGrouplist();
           $('.ajax-loader').css("display", "none");
           
           if(data.error_num == 0)
           	alert("연락처 엑셀파일추가가 성공적으로 추가되었습니다.");
           else
        	alert(data.msg);
               
       },
       error: function (e) {
    	   $('.ajax-loader').css("display", "none");
       
    	   alert('AJAX 통신 중 에러가 발생했습니다.');
    	   console.log("ERROR : ", e);
       },
       complete: function(){
    	   $('.ajax-loader').css("display", "none");
    	  }
   });
}
       
function submitFormContactInsert(frmObj) {
	url = '/admin/sms/smsmngr/contactinsert.do';
	//params = $("#frmadd").serialize();	
	
	var name = frmObj.name.value;
	var hp = frmObj.hp.value;
	var groupname_val = frmObj.groupname.value;
	
	var groupname = $("#foraddgroupname :selected").text();
	
	params = "name="+name+"&hp="+hp+"&groupname="+groupname;
	
	console.log("params = " + params);
	
	if (name == '') {
		alert('이름을 입력해주십시요.');
		return false;
	}
	
	if (hp == '') {
		alert('전화번호를 입력해주십시요.');
		return false;
	}	
	
	if (groupname_val == '') {
		alert('그룹을 선택해주십시요.');
		return false;
	}

		
   $.ajax({
	   url: url,
		type: 'post',
		dataType: 'json',
		data : params,
		  async: false,
	      beforeSend : function() {
	         //$(target).html(loading);
	      },
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
    		  getContactlist(1);	    	  
    		  alert("연락처 개별추가가 성공적으로 추가되었습니다.");
        	  
        	  $(".jquery-modal").hide();
        	  
	 	  } else {
	 		alert("동일한 연락처가 존재합니다.");
	 	  }
        
    	  
    	  var html = "";
      }
   });	
}

function submitFormContactUpdate(frmObj) {
	url = '/admin/sms/smsmngr/contactupdate.do';
	//params = $("#frmedit").serialize();	
	
	var idx = frmObj.idx.value;
	var name = frmObj.name.value;
	var hp = frmObj.hp.value;
	var groupname_val = frmObj.groupname.value;
	
	
	
	var groupname = $("#forupdategroupname :selected").text();
	
	params = "idx="+idx+"&name="+name+"&hp="+hp+"&groupname="+groupname;
	
	
	if (name == '') {
		alert('이름을 입력해주십시요.');
		return false;
	}
	
	if (hp == '') {
		alert('전화번호를 입력해주십시요.');
		return false;
	}	
	
	if (groupname_val == '') {
		alert('그룹을 선택해주십시요.');
		return false;
	}
		
	
	console.log("submitFormContactUpdate " + params);
	
   $.ajax({
	   url: url,
		type: 'post',
		dataType: 'json',
		data : params,
		  async: false,
	      beforeSend : function() {
	         //$(target).html(loading);
	      },
      beforeSend : function() {
         //$(target).html(loading);
      },
      error : function(request ,status, error) {
         alert('AJAX 통신 중 에러가 발생했습니다.');
         console.log( request.responseText );
      },
      success : function(response, status, request) {
    	  console.log( response );
    	  getContactlist(1);
    	  alert("연락처 정보가 성공적으로 변경되었습니다.");
    	  $(".jquery-modal").hide();
    	  
    	  
    	  var html = "";
      }
   });	
}

function getContactlist (nPage) {
	//console.log('ajax call cantact.jsp');
	if ( isProcess == true ) {
		alert('처리중입니다.');
		return false;
	}
	
	var group_search = document.getElementById('group_search');
	
	var all_read = document.getElementById('all_read');	
	
	var searchtext = $("#searchText").val();
	var searchgroupname = group_search.options[group_search.selectedIndex].text;
	
	
   var target = $("#sms_list");
   url = '/admin/sms/smsmngr/contactlist.do';
   var params = 'pageIndex='+nPage;
   
   if (searchtext != '')
	   params += '&searchText='+searchtext;
   
   if (searchgroupname != '' && group_search.selectedIndex > 0)
	   params += '&searchgroupname='+searchgroupname;
   
   if ( all_read.checked == true)
	   params += '&isall=1';
   else
	   params += '&isall=0';
	   
 console.log('ajax call cantact.jsp params = ' + params);	
 $('.ajax-loader').css("display", "inline");
   //btn = $(this);

   $.ajax({
	   url: url,
		type: 'post',
		dataType: 'json',
		data : params,
		async: false,
	      beforeSend : function() {
	         //$(target).html(loading);
	      },
      beforeSend : function() {
         //$(target).html(loading);
         isProcess = true;
    	  $('.ajax-loader').css("display", "inline");
      },
      error : function(request ,status, error) {
    	  isProcess = false;
      
    	  $('.ajax-loader').css("display", "none");
      
         alert('AJAX 통신 중 에러가 발생했습니다.');
         console.log( request.responseText );
      },
      success : function(response, status, request) {
    	  isProcess = false;
    	  $('.ajax-loader').css("display", "none");
      
    	  //console.log( response );
    	  var html = "";
    	  
    	  $("#pagenation").html(html);
    	  
    	  //console.log(response.pageUnit);
    	  //console.log(response.totalPage);
    	  //console.log(response.searchText);
    	  //console.log(response.countPerPage);
    	  
    	  var params = {
                  divId : "PAGE_NAVI",
                  pageIndex : response.pageIndex,
                  totalCount : response.totalPage,
                  eventName : "/admin/sms/smsmngr/contactlist.do"
              };
              
              paginghtml = paging(response.pageIndex, response.pageUnit, 10, response.totalPage);

              if ( response.totalPage > 0 && all_read.checked == false )
            	  $("#pagenation").html(paginghtml);

    	  
    	  lists = response.lists;
    	  
    	  $.each(lists, function(i, n) {
    		  
    		  html = "";
    		  
    		  html += '<tr class="">';
    		  html += '	<td headers="mb_list_chk" class="td_20">';
    		  
    		  html += '    <input type="hidden" class="idx" value="' + n.idx + '" " >';
    		  html += '    <input type="hidden" name="mc_id[' + n.idx + ']" value="' + n.idx + '" id="idx' + i + '">';
	    	  html += '    <input type="checkbox" name="chk[]" value="' + n.idx + '" id="chk_' + n.idx + '" user-hp="' + n.hp + '" user-nm="' + n.name + '">';
	    	  html += ' </td>';
              
	    	  html += ' <td class="td_80 name">' + n.name + '</td>';
	    	  html += ' <td class="td_100 hp">' + n.hp + '</td>';		    	  
	    	  html += ' <td class="td_80 groupname">' + n.groupname + '</td>';
	    	  html += ' <td class="td_40 textcenter">';
	    	  html += '    <div class="td_mngsmall" style="margin:0 auto;">';
	    	  html += '       <button type="button" class="btn_violet" onclick=sms_obj.person_add('+n.idx+',\"'+n.name+'\",\"'+n.hp+'\");>추가</button>';
	    	  html += '       <button type="button" class="btn_orange contact_update_modal_btn" >수정</button>';
	    	  html += '    </div>';
			  html += ' </td>';
			  html += ' </tr>';
			
			if( i == 0 ) {
				//console.log("최초 삽입줄이네");
				$(target).html(html);
			} else {
				$(target).append(html);
			}
			
    	  });
      }
   });
}

function getGrouplist() {
	url = '/admin/sms/smsmngr/grouplist.do';
	params = 'all=1';
	btn = $(this);

   $.ajax({
	   url: url,
	   type: 'get',
	      dataType: 'json',
	      data: params,
		  contentType: false,
		  async: false,
	      beforeSend : function() {
	         //$(target).html(loading);
	      },
      beforeSend : function() {
         //$(target).html(loading);
      },
      error : function(request ,status, error) {
         alert('AJAX 통신 중 에러가 발생했습니다.');
         console.log( request.responseText );
      },
      success : function(response, status, request) {
    	  console.log( response );
    	  
    	  $("#group_match").empty();
    	  $("#group_search").empty();
    	  $("#foraddgroupname").empty();
    	  $("#forupdategroupname").empty();
    	  
    	  
    	 
    	 $("#group_match").append(new Option('선택 그룹지정', ''));
    	 $("#group_match").append(new Option('미지정', ''));
    	 
    	 $("#group_search").append(new Option('검색할 그룹선택', ''));
    	 
    	 $("#foraddgroupname").append(new Option('그룹선택', ''));
    	  
    	 
			lists = response.lists;
    	  
    	  $.each(lists, function(i, n) {
			  $("#group_match").append(new Option(n.groupname, n.idx));
			  
			  $("#group_search").append(new Option(n.groupname, n.idx));
			  
			  $("#foraddgroupname").append(new Option(n.groupname, n.idx));
			  $("#forupdategroupname").append(new Option(n.groupname, n.idx));
			  
    	  });
      }
   });		   
}

$(document).ready(function() {
	getContactlist(1);
	getGrouplist();
	   
	
});
	
	$('.contact_insert_modal_btn').click(function(){
		console.log("contact_insert_modal_btn");
		 $("#contact_insert_modal").modal({
			  //fadeDuration: 50
			});
	});
	
	$('#sms_list').on('click', '.contact_update_modal_btn', function(){	    
	    //console.log("contact_update_modal_btn");
	    
	    var idx = $(this).parent().parent().parent().children().find('.idx').val();
	    var name = $(this).parent().parent().parent().find('td.name').text();
	    var hp = $(this).parent().parent().parent().find('td.hp').text();
	    var groupname = $(this).parent().parent().parent().find('td.groupname').text();
	    
	    $('#frmedit .idx').val(idx);
	    $('#frmedit .name').val(name);
	    $('#frmedit .hp').val(hp);
	    $('#frmedit .groupname').val(groupname);
	    
	    
		$("#forupdategroupname option").each(function()
		{
			console.log($(this).val() + " ," + $(this).text() );
			if ( $(this).text() == groupname  ) {
				$(this).attr('selected', true);
			}
				    // Add $(this).val() to your list
		});
	   
	    //console.log("idx " + idx + " ," + name + " ," + hp + " ," + groupname + " , " + $( this ).parent().parent().parent().text());
	    
		$("#contact_update_modal").modal({
			  //fadeDuration: 50
			});
		
	});
		
	
	$('.contact_update_modal_btn').click(function(){
		//console.log("contact_update_modal_btn");
		$("#contact_update_modal").modal({
			  //fadeDuration: 50
			});
	});
	
	$("#group_match").change(function(event) {
	    var chks = [];
	    var group = $(this).text();
	    
	    $("#group_match option:selected" ).each(function() {
	    	group = $( this ).text();
	      });
	console.log('group_match ' + group);
	    if( group == '' ) return false;
	    
	    
	    
	    $("[name='chk[]']").each(function(index, el) {
	      if( $(el).prop('checked')==true ) chks.push( $(el).val() );
	    });
	
	    console.log("group_match.change " + chks);
	    if (typeof chks == 'undefined' || chks.length == 0 || chks == '' ) {
	        // the array is defined and has at least one element
	        alert("선택된 연락처가 없습니다.");
	        return false;
	    }
	    
	    if( !confirm('선택한 연락처를 ['+group+'] 그룹으로 변경하시겠습니까?') ) return false;
		
 
	    url = '/admin/sms/smsmngr/contactgroupupdate.do';
	    params = "chks="+chks+"&group="+group;
	    
	    //console.log("group_match.change " + params);

		   $.ajax({
			   url: url,
				type: 'post',
				dataType: 'json',
				data : params,
				  async: false,
			      beforeSend : function() {
			         //$(target).html(loading);
			      },
		      beforeSend : function() {
		         //$(target).html(loading);
		      },
		      error : function(request ,status, error) {
		         alert('AJAX 통신 중 에러가 발생했습니다.');
		         //console.log( request.responseText );
		      },
		      success : function(response, status, request) {
		    	  //console.log( response );
		    	  getContactlist(1);
		    	  alert("그룹정보가 성공적으로 변경되었습니다.");
		    	  
		    	  
		    	  
		    	  $.each(response, function(i, n) {
					  $("#group_match").append(new Option(n.groupname, n.idx));
		    	  });
		      }
		   });	
	

	
	});
	
	 $(".sms_p_delete_act").click(function(event) {
		    

		    var chks = [];
		    
		    $("[name='chk[]']").each(function(index, el) {
			      if( $(el).prop('checked')==true ) chks.push( $(el).val() );
			    });
			
			    //console.log("sms_p_delete_act.click " + chks);
			console.log("sms_p_delete_act " + chks);
	    if (typeof chks == 'undefined' || chks.length == 0 || chks == '' ) {
	        // the array is defined and has at least one element
	        alert("선택된 연락처가 없습니다.");
	        return false;
	    }    
	    if( !confirm('정말 삭제하시겠습니까?') ) return false;
			    
		    url = '/admin/sms/smsmngr/contactdelete.do';
		    params = "chks="+chks;
		    
		    //console.log("sms_p_delete_act.click " + params);
		    btn = $(this);

		    $.ajax({
		      url: url,
		      type: 'post',
		      dataType: 'json',
		      data: params,
		      beforeSend : function() {
		        //$(btn).button('loading');
		      },
		      always : function() {
		        //$(btn).button('reset');
		      },
		      error : function(request ,status, error) {
		        alert('AJAX 통신 중 에러가 발생했습니다.');
		        //console.log( request.responseText );
		      },
		      success : function(response, status, request) {
		    	  getContactlist(1);	
		    	  alert("그룹정보가 성공적으로 삭제되었습니다.");
		    	  	        
		      }
		    });
		  });



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
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getContactlist('"+prev+"'); ><<</a>";
	} 
	if(curpage>1){ 
		prevpage=curpage-1; 
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+prevpage+"'); ><</a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getContactlist('"+prevpage+"'); ><</a>";
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
			paginghtml += "<a href='javascript:void(0);' onclick=javascript:getContactlist('"+j+"'); >"+i+"</a>";
		}
	} 

//console.log('curpage : ' + curpage + ',  pagesu : ' + pagesu + ', curpage : ' + curpage);
	if((curpage) != pagesu && pagesu != 0 ){ 
		nextpage=Number(curpage) + 1; 
		console.log('nextpage : '+nextpage);
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+nextpage+"'); >></a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getContactlist('"+nextpage+"'); >></a>";
	} 

	if(pageend<pagesu)
	{
		pageend += 1;
		//paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+pageend+"'); >>></a></ul>"; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:getContactlist('"+pageend+"'); >>></a>";
	}

	return paginghtml;
}

function paging4name(curpage, funcname, countperpage, pageviewsu, totalcount)
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
	
	//console.log('curpage : ' + curpage );
	//console.log('totalcount : ' + totalcount + ',  pagesu : ' + pagesu + ', curpage : ' + curpage);
	//console_log('pagestart : ' + pagestart + ',  pageend : ' + pageend + ', pagegroup : ' + pagegroup);

	if(pagegroup>1){ 
		//prev=pagestart-pageviewsu-1;//이전목록그룹의 시작페이지결정 
		prev=(pagegroup-1) * pageviewsu;//이전목록그룹의 시작페이지결정 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:"+funcname+"('"+prev+"'); ><<</a>"; 
	} 
	if(curpage>1){ 
		prevpage=curpage-1; 
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:"+funcname+"('"+prevpage+"'); ><</a>"; 
	} 

	for(i=pagestart;i<=pageend;i++) 
	{ 
		if(pagesu<i){break;} 
		j = i;

		if(j==curpage)
			paginghtml += "<a href='javascript:void(0);' class='active' onclick=javascript:"+funcname+"('"+j+"'); >"+i+"</a>"; 
		else
			paginghtml += "<a href='javascript:void(0);' onclick=javascript:"+funcname+"('"+j+"'); >"+i+"</a>"; 
	} 

	if((curpage+1)!=pagesu){ 
		nextpage=Number(curpage) + 1; 
//		console_log('nextpage : '+nextpage);
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:"+funcname+"('"+nextpage+"'); >></a>"; 
	} 

	if(pageend<pagesu)
	{
		pageend += 1;
		paginghtml += "<a href='javascript:void(0);' onclick=javascript:"+funcname+"('"+pageend+"'); >>></a>"; 
	}

	return paginghtml;
}

</script>
</body>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<!doctype html>
<c:set var="depthName" value="sosig" />
<c:set var="pageName" value="event" />
<%@include file="../../header_admin.jsp"%>
<%@include file="../../login_header.jsp"%>
<body oncontextmenu="return false;">
<form action="" id="frm" name="frm" method="post">
</form>
<div id="wrapper">
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
				
				<!-- 검색 영역 { -->
				<div id="search_form">
					<table class="t_write" id="ftable">
						<colgroup>
							<col width="270">
							<col>
						</colgroup>
						<tbody>	
							<tr>
								<th>
									<%@include file="./left_wrap.jsp"%>								
								</th>
								<td>
									<div id="send_book" class="pull-left btn-group">
										<input type="button" class="btn btn-ms btn_violet" onclick="goto_sms('contact',this)" value="연락처">
										<input type="button" class="btn btn-ms btn-default" onclick="goto_sms('group',this)" value="그룹관리">
										<!--
					                     <button type="button" class="btn btn-ms btn_violet" onclick="goto_sms('contact',this)">연락처</button>
					                     <button type="button" class="btn btn-ms btn-default" onclick="goto_sms('group',this)">그룹관리</button>
					                     <button type="button" type="button" class="btn btn-success contact_insert_modal_btn">연락처추가</button>
					                     -->
					                     
					                     
					                     <input type="button" class="btn btn-success contact_insert_modal_btn" onclick="goto_sms('group',this)" value="연락처추가">
					                </div>								
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				
				
			</section>
			

			<section id="table-list">	
				<div class="t_list_area">
					<table class="t_list">
						<tbody>
					         <tr>
					            <td class="sendsms_area" style="vertical-align:top;" align="center" style="width:270px;">
					              <%@include file="./left_wrap.jsp"%>
					              
					            </td>
					
					
					            <td class="phone_list_area" style="vertical-align:top; border-left:1px solid #d7d7d7;">
					               <div id="send_book" class="pull-left btn-group">
					              
					                     <button
					                     type="button"
					                     class="btn btn-ms btn_violet"
					                     onclick="goto_sms('contact',this)"
					                     >
					                     연락처
					                     </button>
					                     <button
					                     type="button"
					                     class="btn btn-ms btn-default"
					                     onclick="goto_sms('group',this)"
					                     >
					                     그룹관리
					                     </button>
					
					                  </div>
					                  <div id="num_book" style="clear: both;"></div>
					                  
					                  <button type="button" type="button" class="btn btn-success contact_insert_modal_btn">연락처추가</button>
					                  
					                  <form:form commandName="imform" id="imform" method="post" enctype="multipart/form-data" >
					                  <input type="file" id="client_excel_f" name="client_excel_f" >
					                  <button type="button" type="button" class="btn btn-success" onclick="add_excel_member();">연락처 엑셀파일 실행하</button>
					                 </form:form>
					                 <div>
					                    <div id="sms_list22">
					                    
										    <input type="hidden" name="csrf" value="csrf">
										    <input type="hidden" name="aaa" value="bbb">
										      <table class="borad_tbl" style="margin-top:20px;">										
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
										      
										      
										      <div class="text-center"><ul  class="pagination"></ul></div>
										
										
										     <!-- <div class="btn_list01 btn_list"> -->
										     <div class="form-inline">
										        <button type="button" class="btn btn-xs btn-default" onclick="sms_obj.person_multi_add()">선택추가</button>
										
										
										        <select class="form-control frm_select" id="group_match">
										          <option value="">선택 그룹지정</option>
										          <option value="미지정">미지정</option>
										          
										        </select>
										
										        <button
										          type="button"
										          class="btn btn-xs btn_red pull-right sms_p_delete_act"
										        >
										        삭제
										      </button>
										
										
										        <!--<button type="button" id="excel_upload">엑셀업로드</button>-->
										     </div>
										    
										    
					                    </div>
					                  </div>
					               </div>
					            </td>
					         </tr>
					      </tbody>
					</table>
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
				<input name="name" class="name  frm_input" type="text" placeholder="이름을 입력하세요" class="" value="" onclick="this.select()">			 
				</div>
				<div style="margin:5px 0 0 5px;">
				<label class="control-label" for="hp"><span class="caution">*</span>전화번호</label>
				<input name="hp" class="hp  frm_input" type="text" placeholder="전화번호를 입력하세요" class=""  value="" >
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
					<input name="name" class="name  frm_input" type="text" placeholder="이름을 입력하세요" class="" value="" onclick="this.select()">			 
				</div>
				<div style="margin:5px 0 0 5px;">	
		<label class="control-label" for="hp"><span class="caution">*</span>전화번호</label>
					<input name="hp" class="hp frm_input" type="text" placeholder="전화번호를 입력하세요" class=""  value="" >
					</div>
					
					<button type="button" class="btn btn-primary" style="margin:15px 0 0 0;" onclick="submitFormContactUpdate(document.frmedit);">확인</button>
					
		</div>	
	</form>

</div>

<script>

function add_excel_member() {
	 var form = $('#imform')[0];
	 var formData = new FormData(form);
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
           getContactlist();
           
       },
       error: function (e) {
           console.log("ERROR : ", e);
       }
   });
}
       
function submitFormContactInsert(frmObj) {
	url = '/admin/sms/smsmngr/contactinsert.do';
	params = $("#frmadd").serialize();	
	
   $.ajax({
	   url: url,
	      dataType: 'json',
		  contentType: "application/json;charset=UTF-8",
		  data: params,
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
    	  $(".jquery-modal").hide();
    	  getContactlist();
    	  var html = "";
      }
   });	
}

function submitFormContactUpdate(frmObj) {
	url = '/admin/sms/smsmngr/contactupdate.do';
	params = $("#frmedit").serialize();	
	
	console.log("submitFormContactUpdate " + params);
	
   $.ajax({
	   url: url,
	      dataType: 'json',
		  contentType: "application/json;charset=UTF-8",
		  data: params,
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
    	  $(".jquery-modal").hide();
    	  getContactlist();
    	  
    	  var html = "";
      }
   });	
}

function getContactlist (nPage) {
	console.log('ajax call cantact.jsp');

   var target = $("#sms_list");
   url = '/admin/sms/smsmngr/contactlist.do';
   var params = 'pageIndex='+nPage;
		
   btn = $(this);

   $.ajax({
	   url: url,
	      dataType: 'json',
	      data: params,
		  contentType: "application/json;charset=UTF-8",
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
    	  var html = "";
    	  
    	  console.log(response.pageUnit);
    	  console.log(response.totalPage);
    	  console.log(response.searchText);
    	  console.log(response.countPerPage);
    	  
    	  var params = {
                  divId : "PAGE_NAVI",
                  pageIndex : response.pageIndex,
                  totalCount : response.totalPage,
                  eventName : "/admin/sms/smsmngr/contactlist.do"
              };
              //gfn_renderPaging(params);
              
              paginghtml = paging(response.pageIndex, response.pageUnit, 10, response.totalPage);

				$(".pagination").html(paginghtml);


    	  
    	  
    	  lists = response.lists;
    	  
    	  $.each(lists, function(i, n) {
    		  
    		  html = "";
    		  
    		  html += '<tr class="">';
    		  html += '	<td headers="mb_list_chk" class="td_20">';
    		  
    		  html += '    <input type="hidden" class="idx" value="' + n.idx + '">';
    		  html += '    <input type="hidden" name="mc_id[' + n.idx + ']" value="' + n.idx + '" id="idx' + i + '">';
	    	  html += '    <input type="checkbox" name="chk[]" value="' + n.idx + '" id="chk_' + n.idx + '">';
	    	  html += ' </td>';
              
	    	  html += ' <td class="td_80 name">' + n.name + '</td>';
	    	  html += ' <td class="td_100 hp">' + n.hp + '</td>';		    	  
	    	  html += ' <td class="td_80 groupname">' + n.groupname + '</td>';
	    	  html += ' <td class="td_40">';
	    	  html += '    <div class="td_mngsmall" style="margin:0 auto;">';
	    	  html += '       <button type="button" class="btn_frmline btn-sm btn_violet" onclick=sms_obj.person_add('+n.idx+',\"'+n.name+'\",\"'+n.hp+'\");>추가</button>';
	    	  html += '       <button type="button" class="btn_frmline btn-sm btn_orange contact_update_modal_btn" >수정</button>';
	    	  
	    	  html += '    </div>';
			  html += ' </td>';
			  html += ' </tr>';
			
			if( i == 0 ) {
				console.log("최초 삽입줄이네");
				$(target).html(html);
			} else {
				$(target).append(html);
			}
			
    	  });
      }
   });
}

$(document).ready(function() {
	getContactlist(1);

	
	   
	   getGrouplist();
	   
	   function getGrouplist() {
		   url = '/admin/sms/smsmngr/grouplist.do';
		   btn = $(this);

		   $.ajax({
			   url: url,
			      dataType: 'json',
				  contentType: "application/json;charset=UTF-8",
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
		    	  
		    	  $.each(response, function(i, n) {
					  $("#group_match").append(new Option(n.groupname, n.idx));
		    	  });
		      }
		   });		   
	   }
	});
	
	$('.contact_insert_modal_btn').click(function(){
		console.log("contact_insert_modal_btn");
		 $("#contact_insert_modal").modal({
			  //fadeDuration: 50
			});
	});
	
	
	
	
	
	$('#sms_list').on('click', '.contact_update_modal_btn', function(){	    
	    console.log("contact_update_modal_btn");
	    
	    var idx = $(this).parent().parent().parent().children().find('.idx').val();
	    var name = $(this).parent().parent().parent().find('td.name').text();
	    var hp = $(this).parent().parent().parent().find('td.hp').text();
	    var groupname = $(this).parent().parent().parent().find('td.groupname').text();
	    
	    $('#frmedit .idx').val(idx);
	    $('#frmedit .name').val(name);
	    $('#frmedit .hp').val(hp);
	    $('#frmedit .groupname').val(groupname);
	   
	    console.log("idx " + idx + " ," + name + " ," + hp + " ," + groupname + " , " + $( this ).parent().parent().parent().text());
	    
		$("#contact_update_modal").modal({
			  //fadeDuration: 50
			});
		
	});
		
	
	$('.contact_update_modal_btn').click(function(){
		console.log("contact_update_modal_btn");
		$("#contact_update_modal").modal({
			  //fadeDuration: 50
			});
	});
	
	$("#group_match").change(function(event) {
	    var chks = [];
	    var group = $(this).text();
	    
	    $( "select option:selected" ).each(function() {
	    	group = $( this ).text();
	      });
	
	    if( group == '' ) return false;
	    if( !confirm('선택한 연락처를 ['+group+'] 그룹으로 변경하시겠습니까?') ) return false;
	
	    $("[name='chk[]']").each(function(index, el) {
	      if( $(el).prop('checked')==true ) chks.push( $(el).val() );
	    });
	
	    console.log("group_match.change " + chks);
 
	    url = '/admin/sms/smsmngr/contactgroupupdate.do';
	    params = "chks="+chks+"&group="+group;
	    
	    console.log("group_match.change " + params);

		   $.ajax({
			   url: url,
			      dataType: 'json',
				  contentType: "application/json;charset=UTF-8",
				  async: false,
				  data: params,
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
		    	  
		    	  $.each(response, function(i, n) {
					  $("#group_match").append(new Option(n.groupname, n.idx));
		    	  });
		      }
		   });	
	

	
	});
	
	 $(".sms_p_delete_act").click(function(event) {
		    if( !confirm('정말 삭제하시겠습니까?') ) return false;

		    var chks = [];
		    
		    $("[name='chk[]']").each(function(index, el) {
			      if( $(el).prop('checked')==true ) chks.push( $(el).val() );
			    });
			
			    console.log("sms_p_delete_act.click " + chks);
			    
		    url = '/admin/sms/smsmngr/contactdelete.do';
		    params = "chks="+chks;
		    
		    console.log("sms_p_delete_act.click " + params);
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
		        console.log( request.responseText );
		      },
		      success : function(response, status, request) {
		        

		        //alert(response.msg);
		        
		        getContactlist(1);
		        
		      }
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

/*
divId : 페이징 태그가 그려질 div
pageIndx : 현재 페이지 위치가 저장될 input 태그 id
recordCount : 페이지당 레코드 수
totalCount : 전체 조회 건수
eventName : 페이징 하단의 숫자 등의 버튼이 클릭되었을 때 호출될 함수 이름
*/
var gfv_pageIndex = null;
var gfv_eventName = null;
function gfn_renderPaging(params){
    var divId = params.divId; //페이징이 그려질 div id
    gfv_pageIndex = params.pageIndex; //현재 위치가 저장될 input 태그
    var totalCount = params.totalCount; //전체 조회 건수
    var currentIndex = $("#"+params.pageIndex).val(); //현재 위치
    
    if($("#"+params.pageIndex).length == 0 ){
        currentIndex = 1;
    }
     
    var recordCount = params.recordCount; //페이지당 레코드 수
    /*
    if(gfn_isNull(recordCount) == true){
        recordCount = 20;
    }
    */
    var totalIndexCount = Math.ceil(totalCount / recordCount); // 전체 인덱스 수
    gfv_eventName = params.eventName;
     
    $("#"+divId).empty();
    var preStr = "";
    var postStr = "";
    var str = "";
     
    var first = (parseInt((currentIndex-1) / 10) * 10) + 1;
    var last = (parseInt(totalIndexCount/10) == parseInt(currentIndex/10)) ? totalIndexCount%10 : 10;
    var prev = (parseInt((currentIndex-1)/10)*10) - 9 > 0 ? (parseInt((currentIndex-1)/10)*10) - 9 : 1;
    var next = (parseInt((currentIndex-1)/10)+1) * 10 + 1 < totalIndexCount ? (parseInt((currentIndex-1)/10)+1) * 10 + 1 : totalIndexCount;
     
    if(totalIndexCount > 10){ 
    	//전체 인덱스가 10이 넘을 경우, 맨앞, 앞 태그 작성
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>" +
                "<a href='#this' class='pad_5' onclick='_movePage("+prev+")'>[<]</a>";
    } else if( (totalIndexCount <=10) && totalIndexCount > 1){ 
    	//전체 인덱스가 10보다 작을경우, 맨앞 태그 작성
        preStr += "<a href='#this' class='pad_5' onclick='_movePage(1)'>[<<]</a>";
    }
     
    if(totalIndexCount > 10){ 
    	//전체 인덱스가 10이 넘을 경우, 맨뒤, 뒤 태그 작성
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+next+")'>[>]</a>" +
                    "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    } else if((totalIndexCount <=10) && totalIndexCount > 1) { 
    	//전체 인덱스가 10보다 작을경우, 맨뒤 태그 작성
        postStr += "<a href='#this' class='pad_5' onclick='_movePage("+totalIndexCount+")'>[>>]</a>";
    }
     
    for(var i=first; i<(first+last); i++){
        if(i != currentIndex){
            str += "<a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a>";
        }
        else{
            str += "<b><a href='#this' class='pad_5' onclick='_movePage("+i+")'>"+i+"</a></b>";
        }
    }
    $("#"+divId).append(preStr + str + postStr);
}
 
function _movePage(value){
    $("#"+gfv_pageIndex).val(value);
    if(typeof(gfv_eventName) == "function"){
        gfv_eventName(value);
    }
    else {
        eval(gfv_eventName + "(value);");
    }
}



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
		paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+prev+"'); ><<</a></ul>"; 
	} 
	if(curpage>1){ 
		prevpage=curpage-1; 
		paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+prevpage+"'); ><</a></ul>"; 
	} 

	for(i=pagestart;i<=pageend;i++) 
	{ 
		if(pagesu<i){break;} 
		j = i;

		if(j==curpage)
			paginghtml += "<li class='page-item active'><a href='javascript:void(0);' class='active' onclick=javascript:getContactlist('"+j+"'); >"+i+"</a></ul>"; 
		else
			paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+j+"'); >"+i+"</a></ul>"; 
	} 

console.log('curpage : ' + curpage + ',  pagesu : ' + pagesu + ', curpage : ' + curpage);
	if((curpage) != pagesu && pagesu != 0 ){ 
		nextpage=Number(curpage) + 1; 
		console.log('nextpage : '+nextpage);
		paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+nextpage+"'); >></a></ul>"; 
	} 

	if(pageend<pagesu)
	{
		pageend += 1;
		paginghtml += "<li class='page-item'><a href='javascript:void(0);' onclick=javascript:getContactlist('"+pageend+"'); >>></a></ul>"; 
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

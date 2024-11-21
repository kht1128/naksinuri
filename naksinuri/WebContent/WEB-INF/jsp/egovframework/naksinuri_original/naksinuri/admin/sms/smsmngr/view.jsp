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
<input type="hidden" name="pageIndex" id="pageIndex" value="${pageIndex}" />
<input type="hidden" name="pageUnit" id="pageUnit" value="${pageUnit }" />
<input type="hidden" name="fishing_type" id="fishing_type" value="boatfishing" />

<div id="wrapper">
<%@include file="../../admin_leftTab.jsp"%>

	<div id="container">

		<div id="content">			

			<section id="table-list">	
				<div class="t_list_area">
					<table class="t_list">
						<tbody>
					         <tr>
					            <td class="sendsms_area" style="vertical-align:top;" align="center">
					              <%@include file="./left_wrap.jsp"%>
					              
					            </td>
					
					
					            <td class="phone_list_area" style="vertical-align:top; border-left:1px solid #d7d7d7;">
					               <div id="send_book">
					                  <div class="pull-right btn-group">
					                     <button
					                     type="button"
					                     class="btn btn-ms btn-default"
					                     onclick="goto_sms('view',this)"
					                     >
					                     예약리스트
					                     </button>
					                     
					                     <button
					                     type="button"
					                     class="btn btn-ms btn-info"
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
					                     
					
					                     
					                     <button
					                     type="button"
					                     class="btn btn-ms btn-default"
					                     onclick="goto_sms('burning',this)"
					                     >
					                     긴급문자
					                     </button>
					                     
					                     <!--
					                     <button
					                     type="button"
					                     class="btn btn-ms btn-default"
					                     onclick="goto_sms('num_group',this)"
					                     >
					                     그룹
					                     </button>
					                    -->
					
					
					                  </div>
					                  <div id="num_book" style="clear: both;"></div>
					
					                  <div>
					                    <div id="sms_list"></div>
					                  </div>
					               </div>
					            </td>
					         </tr>
					      </tbody>
					</table>
				</div>
		
</form>
				
				
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

$(document).ready(function() {

	   var target = $("#sms_list");
	   url = '<?=base_url()?>sms/sms_p_list';
	   btn = $(this);

	   $.ajax({
	      url: url,
	      type: 'post',
	      dataType: 'html',
	      data: {
	         '<?=$this->security->get_csrf_token_name()?>' : '<?=$this->security->get_csrf_hash()?>',
	         data : '<?=$ajax_data?>'
	       },
	      beforeSend : function() {
	         $(target).html(loading);
	      },
	      error : function(request ,status, error) {
	         alert('AJAX 통신 중 에러가 발생했습니다.');
	         console.log( request.responseText );
	      },
	      success : function(response, status, request) {
	         $(target).html('');
	         $(target).html(response);
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
</script>
</body>

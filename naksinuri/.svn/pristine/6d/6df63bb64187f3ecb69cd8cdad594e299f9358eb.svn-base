<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<style type="text/css">

.t_write tbody tr th, .t_write tbody tr td {
padding: 8px 10px;
}

._title {
	font-weight: bold;
    font-size: 100%;
}

/*
select[multiple], select[size] {
    height: auto;
}
#write_sc_btn {top:15px}
#write_emo_btn {top:50px}
.sms_preset_sch form {display:inline-block}
.btn_add {float:right}
.sendsms_area {width:30%;}
.write_scemo {display:none;z-index:10;position:absolute;left:10px;border:1px solid #e9e9e9;background:#f7f7f7}
.write_scemo .scemo_list {z-index:11;margin:0;padding:0;width:190px;height:150px;background:#fff;overflow-y:scroll}
.write_scemo .scemo_add {margin:0;padding:0;height:25px;border:0;background:transparent}
#write_sc .scemo_add {width:25px}
#write_emo .scemo_add {width:50px}
#write_emo .emo_long {width:80px}
.pagination a, .pagination strong {position: relative;float: left;padding: 6px 12px;margin-left: -1px;line-height: 1.42857143;color: #337ab7;text-decoration: none;background-color: #fff;border: 1px solid #ddd;}
.pagination a:hover { color: #23527c;background-color: #eee;border-color: #ddd;}

.form-control {
    background-color: #FFFFFF;
    background-image: none;
    border: 1px solid #e5e6e7;
    border-radius: 5px;
    color: inherit;
    display: inline-block;
    padding: 2px 12px;
    font-size: 13px;
    vertical-align: middle;
  
}

.write_floater_btn {
    margin: 0;
    padding: 0;
    border: 0;
    background: transparent;
    color: #ff0000;
    font-size: 0.95em;
    letter-spacing: -0.1em;
	float:right;
}

#recv_add{
    position: relative;
	margin-top:10px;
}
#recv_add label {
    display: inline-block;
}
#recv_add input {
	margin: 0 0 3px;
}
#recv_add input.frm_input_wd {
	width:120px;
}
#recv_add button {
    position: absolute;
    top: 0;
    right: 0 !important;
    right: 20px;
    margin: 0;
    padding: 0;
    width: 36px;
    height: 64px;
    border: 1px solid #ccc;
    background: #fafafa;
	border-radius: 0;
    font-size: 1em;
    font-family: dotum;
    -webkit-appearance: none;
    cursor: pointer;
    color: #000000;
}

.td_mng > a{    width: 100px;
    font-size: 0.95em;
    text-align: center;
    letter-spacing: -0.1em;
}

.tbl_frm001 > table > tbody > tr > th{
	width:20%;
}

.tbl_frm001 textarea{
	height:120px;
	width:90%;
}


.btn_confirm01 {
	margin-top:20px;
    clear: both;
    text-align: center;
}


.btn_confirm .btn_submit {
    padding: 0 15px;
    border: 0;
    height: 30px;
    color: #fff;
}

.btn_submit {
    margin: 0;
    padding: 0;
    border: 0;
    background: #ff3061;
    color: #fff;
    cursor: pointer;
}

.btn_confirm01 a {
    display: inline-block;
    padding: 0 15px;
    height: 30px;
    background: #617d46;
    color: #fff;
    text-decoration: none;
    line-height: 2.5em;
    vertical-align: middle;
}
*/
</style>

<form name="form_sms" id="form_sms" action="sms_write_send" onsubmit="return sms5_chk_send(this);" accept-charset="utf-8">
<input type="hidden" name="send_list" value="" id="send_list"/>
<input type="hidden" name="mentidx" value="" id="mentidx"/>
	<table class="t_write" id="ftable">
	<colgroup>
		<col width="80">
		<col>
	</colgroup>
	<tbody>	
		<tr>
			<th colspan="2" class="txt-left">
				<h3 class="_title">멘트선택 <button type="button" class="btn_orange" onclick="goMentManage();">멘트관리</button></h3> 
				<select class="form-control frm_select wp100" name="mentOption" id="mentOption" ></select>								
			</th>
		</tr>
		<tr>
			<th colspan="2" class="txt-left">
				<h3 class="_title">보낼내용</h3>
				<textarea class="form-control frm_input wp100" rows="5" name="wr_message" id="wr_message" onkeyup="byte_check('wr_message', 'sms_bytes');"  accesskey="m"></textarea>
				<div class="form-inline text-left">
					 <div id="sms_byte"><span id="sms_bytes">0</span> / <span id="sms_max_bytes">2000</span> byte</div>
	
				</div>								
			</th>
		</tr>
		<tr>
			<th colspan="2" class="txt-left">
				<h3 class="_title">발송번호(회신번호)</h3>
				<input type="text" name="wr_reply" value="${sendnumber}" id="wr_reply" class="form-control required frm_input wp100" maxlength="20" />								
			</th>
		</tr>
		<tr>
			<th colspan="2" class="txt-left">
				<h3 class="_title">받는사람(목록)</h3>
				<select name="hp_list" class="form-control mt5 frm_select wp100" id="hp_list" size="5" style="min-height:150px;"></select>
				<button type="button" class="btn btn_frmline btn-sm" onclick="hp_list_del()">선택삭제</button>		
				<button type="button" class="btn btn_receiveradd btn-sm" onclick="hp_receiveradd()">받는사람추가</button>						
			</th>
		</tr>
		<tr class="receiveradd_input">
			<th colspan="2" class="txt-left">
				<h3 class="_title">받는사람 추가(목록에 바로추가)</h3>
				<input type="text" name="hp_name" id="hp_name" class="frm_input frm_input_wd wp100" maxlength="20" onkeypress="if(event.keyCode==13) document.getElementById('hp_number').focus();" autocomplete="off" placeholder="이름을 입력하세요.">
				<input type="text" name="hp_number" id="hp_number" class="frm_input frm_input_wd wp100" maxlength="20" onkeypress="if(event.keyCode==13) document.getElementById('hp_number').focus();" autocomplete="off" placeholder="연락처를 입력하세요.">
				<button type="button" class="btn_size1 btn_violet" onclick="hp_add()">추가</button>
			</th>
		</tr>
		<tr>
			<th colspan="2" class="txt-center">
				<button type="button" class="btn btn_violet" id="btn_submit">전송하기</button>										
			</th>
		</tr>
		<!-- 
		<tr>
			<th colspan="2" class="txt-left">
			<h3 class="_title">고정멘트관리</h3>
				<select class="form-control frm_select wp100" id="ment_edit_select"></select>
				<input type="text" name="title" id="title" class="frm_input frm_input_wd wp100" size="20" maxlength="20" autocomplete="off" placeholder="제목을 입력하세요.">
				<textarea class="form-control frm_input wp100" rows="5" name=""  id="mentNew" placeholder="멘트를 입력하세요."></textarea>
				<button type="button" class="btn gray ment_btn" data-id="New">추가</button>
				<button type="button" class="btn gray ment_edit_btn" data-id="Edit" style="display:none;">변경</button>
				<div id="mentlist" style="display:none;"></div>
			</th>
		</tr>
		 -->
	<tr>
		
	</tr>	
	</tbody>
	</table>


</form>



<div id="mentmanage_modal" class="mentmanage_modal modal" >
	<table class="t_write" id="ftable">
	<tr>
		<th colspan="2" class="txt-left">
		<h3 class="_title">고정멘트관리</h3>
			<select class="form-control frm_select wp100" id="ment_edit_select"></select>
			<input type="text" name="title" id="title" class="frm_input frm_input_wd wp100" size="20" maxlength="20" autocomplete="off" placeholder="제목을 입력하세요.">
			<textarea class="form-control frm_input wp100" rows="5" name=""  id="mentNew" placeholder="멘트를 입력하세요."></textarea>
			<button type="button" class="btn gray ment_btn" data-id="New">추가</button>
			<button type="button" class="btn gray ment_edit_btn" data-id="Edit" style="display:none;">변경</button>
			<div id="mentlist" style="display:none;"></div>
		</th>
	</tr>
	</table>	

</div>

<script>
function goMentManage() {
	$("#mentmanage_modal").modal({
		  //fadeDuration: 50
		});
}

$(document).ready(function() {
	$('.receiveradd_input').hide();	
	$('.btn_receiveradd').text('받는사람추가');
	
	$("#ment_edit_select").change(function(event) {
		var optionvalue = $(this).val();
		
		console.log('ment_edit_select '+$(this).val());
		
		var new_message = $( "#ment"+$(this).val() ).html();
		
		$("#title").val($(this).find(":selected").text());
		$("#mentNew").val(new_message);
		
		if ( optionvalue == 'New') {
			$('.ment_btn').show();
			$('.ment_edit_btn').hide();
			
		} else {
			$('#mentidx').val(optionvalue);
			
			$('.ment_btn').hide();
			$('.ment_edit_btn').show();
			var target = "#ment_edit_wrap_"+$(this).val();
			console.log( "ment_edit_select.change " +target );
			//$(target).show().siblings('.ment_edit_wrap').hide();
		}
	});
	

	
	$("[name='mentOption']").change(function(){
		console.log( "mentOption.change " + $(this).val() );
		
		if($(this).val() == '0'){
			$("#wr_message").val('');
			byte_check('wr_message', 'sms_bytes');
		} else if($(this).val() == 'Add'){
			goMentManage();
		} else{
			var new_message = $( "#ment"+$(this).val() ).html();
			
			console.log( "mentOption.change new_message " + new_message );
			
			$("#wr_message").val(new_message);
			byte_check('wr_message', 'sms_bytes');
		}
	});
	
	getMentlist();
	 
	// 멘트 저장
	 $(".ment_btn").click(function(event) {

		 var target = $(".ment_btn");
		 var mn_id = $(this).attr('data-id');
		 url = '/admin/sms/smsmngr/mentinsert.do';
		 btn = $(this);
		 
		 var title = $('#title').val();
		 var ment = $('#mentNew').val();
		 
		 title = trim(title);
		 ment = trim(ment);
		 
		 if ( title == null || title == '' ) {
			 alert("요약타이틀을 입력해주십시요.");
			 return false;
		 }
		 
		 if ( ment == null || ment == '') {
			 alert("멘트내용을 입력해주십시요.");
			 return false;
		 }
		 
		 var params = 'title='+title+'&menttext='+ment;
		 
		 console.log( "ment_btn " + params );

		 $.ajax({
				url: url,
				type: 'post',
				dataType: 'json',
				data : params,				
				beforeSend : function() {
					 //$(btn).button('loading');
				},
				error : function(request ,status, error) {
					 alert('AJAX 통신 중 에러가 발생했습니다.');
					 console.log( request.responseText );
				},
				success : function(response, status, request) {
					//$(btn).button('reset');
					alert("멘트가 성공적으로 추가되었습니다.");
					getMentlist()
				}
		 });
	 });
	
	// 멘트 변경
	 $(".ment_edit_btn").click(function(event) {

		 var target = $(".ment_btn");
		 var mn_id = $(this).attr('data-id');
		 url = '/admin/sms/smsmngr/mentupdate.do';
		 btn = $(this);
		 
		 var title = $('#title').val();
		 var ment = $('#mentNew').val();
		 var mentidx = $('#mentidx').val();
		 
		 var params = 'idx='+mentidx+'&title='+title+'&menttext='+ment;
		 
		 console.log( "ment_btn " + params );

		 $.ajax({
				url: url,
				type: 'post',
				dataType: 'json',
				data : params,				
				beforeSend : function() {
					 //$(btn).button('loading');
				},
				error : function(request ,status, error) {
					 alert('AJAX 통신 중 에러가 발생했습니다.');
					 console.log( request.responseText );
				},
				success : function(response, status, request) {
					//$(btn).button('reset');
					alert("멘트가 성공적으로 변경되었습니다.");
					getMentlist();
					$('.ment_btn').show();
					$('.ment_edit_btn').hide();
				}
		 });
	 });

	 // 멘트 삭제
	 $(".ment_delete_btn").click(function(event) {
	 		if( !confirm('정말 삭제하시겠습니까?') ) return false;

		 var target = $(".ment_btn");
		 var mn_id = $(this).attr('data-id');
		 url = '/admin/sms/smsmngr/mentdelete.do';
		 btn = $(this);
		 var ment = $('#ment'+mn_id).val();
		 
		 var params = 'idx='+mn_id;
		 
		 console.log( "ment_delete_btn " + params );

		 $.ajax({
				url: url,
				type: 'post',
				dataType: 'json',
				data : params,
				
				beforeSend : function() {
					 //$(btn).button('loading');
				},
				error : function(request ,status, error) {
					 alert('AJAX 통신 중 에러가 발생했습니다.');
					 console.log( request.responseText );
				},
				success : function(response, status, request) {
					alert("멘트가 성공적으로 삭제되었습니다.");
					 //$(btn).button('reset');
					 
					 getMentlist()
				}
		 });
	 });
	 
	 var is_sms5_submitted = false;  //중복 submit방지
	 
	 $("#btn_submit").click(function(event) {
		 
		 console.log("btn_submit");
		 
	   if( is_sms5_submitted == false ){
	     is_sms5_submitted = true;
	     var hp_list = document.getElementById('hp_list');
	     var wr_message = document.getElementById('wr_message');
	     var hp_number = document.getElementById('hp_number');
	     var hp_name = document.getElementById('hp_name');
	     var wr_reply = document.getElementById('wr_reply');
	     var wr_reply_regExp = /^[0-9\-]+$/;
	     var list = '';

	    if (!wr_message.value) {
	      alert('메세지를 입력해주세요.');
	      wr_message.focus();
	      is_sms5_submitted = false;
	      return false;
	    }
	    if( !wr_reply_regExp.test(wr_reply.value) ){
	      alert('회신번호 형식이 잘못 되었습니다.');
	      wr_reply.focus();
	      is_sms5_submitted = false;
	      return false;
	    }
	    if (hp_list.length < 1) {
	      alert('받는 사람을 입력해주세요.');
	      hp_number.focus();
	      is_sms5_submitted = false;
	      return false;
	    }
	    
	      //url = 'sms_write_send?'+$("#form_sms").serialize();
	      url = '/admin/sms/smsmngr/sms_write_send.do';
	      btn = $(this);
	      var formData = $("#form_act").serialize();

	    
	    var json_list = new Array();

	    for (i=0; i<hp_list.length; i++) {
	      hp_num = hp_list.options[i].value;
	    
	      list += hp_list.options[i].value + '/';

	      //f.send_list.value = list;
	      $('#send_list').val(list);

	
	      var f_send_list = list;
	      var f_wr_message = wr_message.value;
	      var f_wr_reply = wr_reply.value;
	      var f_hp_name = hp_name.value;
	      var f_hp_number = hp_number.value;
	      
	      console.log("f_send_list = " + f_send_list);
	      console.log("f_wr_message = " + f_wr_message);
	      console.log("f_hp_name = " + f_hp_name);
	      
	      var myObj = {};
	      myObj["name"] = f_hp_name;
	      myObj["hp"] = f_hp_number;
	      
	      json_list.push(hp_num);
	      
	      var json = JSON.stringify(json_list);
	      //alert(json);
	    }
	    
	    var params = 'send_list='+f_send_list+'&wr_message='+f_wr_message+'&wr_reply='+f_wr_reply+'&hp_name='+f_hp_name+'&hp_number='+f_hp_number;
	    console.log("params = " + params);
	      $.ajax({
	    	  url: url,
				type: 'post',
				dataType: 'json',
				data : params,	
	       
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
	              is_sms5_submitted = false;
	              if( response.status == 'ok' ) {
	                  alert(response.msg);
	              } else {
	                  alert(response.msg);
	              }
	            },
	            complete : function(){
	                is_sms5_submitted = false;
	                //$(btn).button('reset');
	            }
	        });


	   }

	   // console.log(url);

	});
	 
	 
	 
	 function getMentlist() {
		 console.log("getMentlist");
		   url = '/admin/sms/smsmngr/mentlist.do';		  

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
		    	  
		    	  $("#mentOption").empty();
		    	  $("#ment_edit_select").empty();
		    	 
		    	 $("#mentOption").append(new Option('직접입력', 0));
		    	 //$("#mentOption").append(new Option('멘트관리', 'Add'));
		    	 $("#ment_edit_select").append(new Option('고정멘트 추가', 'New'));
		    	  
		    	  $.each(response, function(i, n) {
					  $("#mentOption").append(new Option(n.title, n.idx));
					  $("#ment_edit_select").append(new Option(n.title, n.idx));
					  
		    	  });
		    	  
		    	  var html = "";
		    	  $('#mentlist').html();
		    	  
		    	  $.each(response, function(i, n) {
		    		  html = "";		    	  
		    	  
			    	  html += ' <div class="mt10 ment_edit_wrap" id="ment_edit_wrap_'+n.idx+'">';
			    	  html += ' 	<textarea class="form-control ment" rows="5" name=""  id="ment'+n.idx+'">'+n.menttext+'</textarea>';
			    	  html += ' 	<div>';
			    	  html += ' 		<span class="btn btn-success ment_btn" data-id="'+n.idx+'">저장</span>';
			    	  html += ' 		<span class="btn btn-danger ment_delete_btn" data-id="'+n.idx+'">삭제</span>';
			    	  html += ' 	</div>';
			    	  html += ' </div>';
			    	  
			    	  $('#mentlist').append(html);
		    	  });
		      }
		   });		   
	   }
});

	function trim(s) {
	    var t = '';
	    var from_pos = to_pos = 0;
	
	    for (i = 0; i < s.length; i++) {
	        if (s.charAt(i) === ' ') {
	            continue;
	        } else {
	            from_pos = i;
	            break;
	        }
	    }
	
	    for (i = s.length; i >= 0; i--) {
	        if (s.charAt(i-1) === ' ') {
	            continue;
	        } else {
	            to_pos = i;
	            break;
	        }
	    }
	    t = s.substring(from_pos, to_pos);
	    return t;
	}

	function hp_add()
	{
	    var hp_number = document.getElementById('hp_number'),
	        hp_name = document.getElementById('hp_name'),
	        hp_list = document.getElementById('hp_list'),
	        pattern = /^01[016789][0-9]{3,4}[0-9]{4}$/,
	        pattern2 = /^01[016789]-[0-9]{3,4}-[0-9]{4}$/;
	
	    if( !hp_number.value ){
	        alert("휴대폰번호를 입력해 주세요.");
	        hp_number.select();
	        return;
	    }
	
	    if(!pattern.test(hp_number.value) && !pattern2.test(hp_number.value)) {
	        alert("휴대폰번호 형식이 올바르지 않습니다.");
	        hp_number.select();
	        return;
	    }
	
	    if (!pattern2.test(hp_number.value)) {
	        hp_number.value = hp_number.value.replace(new RegExp("(01[016789])([0-9]{3,4})([0-9]{4})"), "$1-$2-$3");
	    }
	
	    var item = '';
	    if (trim(hp_name.value))
	        item = hp_name.value + ' (' + hp_number.value + ')';
	    else
	        item = hp_number.value;
	
	    var value = 'h,' + hp_name.value + ':' + hp_number.value;
	    
	    ///////////////////////////////////////////
	    var exist_hp = "";
	    
	    var ck_hp = hp_number.value  + ',';
	    var ck_nm = hp_name.value + ',';
		        
        $("#hp_list option").each(function()
     	{
       		var addedSplit = $(this).attr('user-hp').split(',');
       		var newSplit = ck_hp.split(',');
       		var newSplitnm = ck_nm.split(',');
       	
       			
   			for ( var i in addedSplit ) {
   				for ( var j in newSplit ) {
   					console.log('['+addedSplit[i] + '] = [' +  newSplit[j] + ']');
   		     		
   					if ( addedSplit[i] == newSplit[j] && addedSplit[i] != '' && newSplitnm[j] != '') {
   						console.log('앗 ' + newSplit[j] + ' 가 이미추가되었다.');
   						//exist_hp += newSplitnm[j]+"("+newSplit[j] + "),";
   						exist_hp += newSplitnm[j] + ",";
   						
   					}
   				}
   			}
     	});
	    ///////////////////////////////////////////
	    
	    if ( exist_hp.length == 0 ) {
	    	/*
		    for (i=0; i<hp_list.length; i++) {
		        if (hp_list[i].value == value) {
		            alert('이미 같은 목록이 있습니다.');
		            return;
		        }
		    }
		
		    if( jQuery.inArray( hp_number.value , sms_obj.phone_number ) > -1 ){
		       alert('목록에 이미 같은 휴대폰 번호가 있습니다.');
		       return;
		    } else {
		        sms_obj.phone_number.push( hp_number.value );
		    }
		    */
		    
		    //hp_list.options[hp_list.length] = new Option(item, value);
		    var option = document.createElement("option");
		    option.value = value;
	        option.text = item;
	        hp_list.add(option); 
	      		        
	        var nm_att = document.createAttribute('user-nm');
	        var hp_att = document.createAttribute('user-hp');
	        nm_att.value = hp_name.value;
	        hp_att.value = hp_number.value;
	        
	        option.setAttributeNode(nm_att);
	        option.setAttributeNode(hp_att);
		
		    hp_number.value = '';
		    hp_name.value = '';
		    hp_name.select();
	    } else {
        	alert(exist_hp +"들이 이미 목록에 있습니다.");
        }
	}
	
	function hp_receiveradd() {
		var isshow = $(".receiveradd_input").is(":visible"); 
		
		if ( isshow == true) {
			$('.receiveradd_input').hide();	
			$('.btn_receiveradd').text('받는사람추가');
			 
		} else {
			$('.receiveradd_input').show('받는사람추가');
		}
		
	}
	
	function hp_list_del()
	{
	    var hp_list = document.getElementById('hp_list');
	
	    if (hp_list.selectedIndex < 0) {
	        alert('삭제할 목록을 선택해주세요.');
	        return;
	    }
	
	    var regExp = /(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?[0-9]{3,4}-?[0-9]{4}/,
	        hp_number_option = hp_list.options[hp_list.selectedIndex],
	        result = (hp_number_option.outerHTML.match(regExp));
	    if( result !== null ){
	        sms_obj.phone_number = sms_obj.array_remove( sms_obj.phone_number, result[0] );
	    }
	    hp_list.options[hp_list.selectedIndex] = null;
	}

	function byte_check(wr_message, sms_bytes)
	{
	    var conts = document.getElementById(wr_message);
	    var bytes = document.getElementById(sms_bytes);
	    var max_bytes = document.getElementById("sms_max_bytes");
	
	    var i = 0;
	    var cnt = 0;
	    var exceed = 0;
	    var ch = '';
	
	    for (i=0; i<conts.value.length; i++)
	    {
	        ch = conts.value.charAt(i);
	        if (escape(ch).length > 4) {
	            cnt += 2;
	        } else {
	            cnt += 1;
	        }
	    }
	
	    bytes.innerHTML = cnt;
	
	    if(cnt > 90)
	        max_bytes.innerHTML = 2000;
	    else
	        max_bytes.innerHTML = 2000;
	
	    if (cnt > 2000)
	    {
	        exceed = cnt - 2000;
	        alert('메시지 내용은 2000바이트를 넘을수 없습니다.\n\n작성하신 메세지 내용은 '+ exceed +'byte가 초과되었습니다.\n\n초과된 부분은 자동으로 삭제됩니다.');
	        var tcnt = 0;
	        var xcnt = 0;
	        var tmp = conts.value;
	        for (i=0; i<tmp.length; i++)
	        {
	            ch = tmp.charAt(i);
	            if (escape(ch).length > 4) {
	                tcnt += 2;
	            } else {
	                tcnt += 1;
	            }
	
	            if (tcnt > 2000) {
	                tmp = tmp.substring(0,i);
	                break;
	            } else {
	                xcnt = tcnt;
	            }
	        }
	        conts.value = tmp;
	        bytes.innerHTML = xcnt;
	        return;
	    }
	}
	
	var sms_obj={
		    phone_number : [],
		    el_box : "#num_book",
		    person_is_search : false,
		    level_add : function(lev, cnt){
		        if (cnt == '0') {
		            alert(lev + ' 레벨은 아무도 없습니다.');
		            return;
		        }

		        var hp_list = document.getElementById('hp_list');
		        var item    = "회원 권한 " + lev + " 레벨 (" + cnt + " 명)";
		        var value   = 'l,' + lev;

		        for (i=0; i<hp_list.length; i++) {
		            if (hp_list[i].value == value) {
		                alert('이미 같은 목록이 있습니다.');
		                return;
		            }
		        }

		        hp_list.options[hp_list.length] = new Option(item, value);
		    },
		    array_remove : function(arr, item){
		        for(var i = arr.length; i--;) {
		          if(arr[i] === item) {
		              arr.splice(i, 1);
		          }
		        }
		        return arr;
		    },
		    book_all_checked : function(chk){
		        var bk_no = document.getElementsByName('chk[]');

		        if (chk) {
		            for (var i=0; i<bk_no.length; i++) {
		                bk_no[i].checked = true;
		            }
		        } else {
		            for (var i=0; i<bk_no.length; i++) {
		                bk_no[i].checked = false;
		            }
		        }
		    },
		    person_add : function(bk_no, bk_name, bk_hp){
		        var hp_list = document.getElementById('hp_list');
		        var item    = bk_name + " (" + bk_hp + ")";
		        var value   = 'p,' + bk_no;

		        for (i=0; i<hp_list.length; i++) {
		            if (hp_list[i].value == value) {
		                alert('이미 같은 목록이 있습니다.');
		                return;
		            }
		        }
		        if( jQuery.inArray( bk_hp , this.phone_number ) > -1 ){
		           alert('목록에 이미 같은 휴대폰 번호가 있습니다.');
		           return;
		        } else {
		            this.phone_number.push( bk_hp );
		        }
		        hp_list.options[hp_list.length] = new Option(item, value);
		    },
		   person_g_add : function(bk_no, bk_name, bk_hp){
		        var hp_list = document.getElementById('hp_list');
		        var item    = bk_name + " (" + bk_hp + ")";
		        var value   = 'd,' + bk_no;

		        for (i=0; i<hp_list.length; i++) {
		            if (hp_list[i].value == value) {
		                alert('이미 같은 목록이 있습니다.');
		                return;
		            }
		        }
		        if( jQuery.inArray( bk_hp , this.phone_number ) > -1 ){
		           alert('목록에 이미 같은 휴대폰 번호가 있습니다.');
		           return;
		        } else {
		            this.phone_number.push( bk_hp );
		        }
		        hp_list.options[hp_list.length] = new Option(item, value);
		    },
		    person_multi_add : function(){
		    	pattern2 = /^01[016789]-[0-9]{3,4}-[0-9]{4}$/;
		        var chk = document.getElementsByName('chk[]');
		        var ck_no = '';
		        var ck_nm = '';
		        var ck_hp = '';
		        var count = 0;
		        var imsi_hp = '';

		        for (i=0; i<chk.length; i++) {
		            if (chk[i].checked==true) {
		                count++;
		                ck_no += chk[i].value + ',';

		                attr_name = 'chk_' + chk[i].value;
		                
		                imsi_hp = $(chk[i]).attr('user-hp');
		                
		                if (!pattern2.test(imsi_hp)) {
		                	imsi_hp = imsi_hp.replace(new RegExp("(01[016789])([0-9]{3,4})([0-9]{4})"), "$1-$2-$3");
		        	    }
		                
		                ck_nm += $(chk[i]).attr('user-nm') + ',';
		                ck_hp += imsi_hp + ',';
		            }
		        }

		        if (!count) {
		            alert('하나이상 선택해주세요.');
		            return;
		        }
		        
		        /*
		        console.log(ck_no);
		        console.log(ck_name);
		        console.log(ck_hp);
		        */
		        
		        var exist_hp = "";
		        
		        $("#hp_list option").each(function()
        		{
		        	var addedSplit = $(this).attr('user-hp').split(',');
		        	var newSplit = ck_hp.split(',');
		        	var newSplitnm = ck_nm.split(',');
		        	
        			console.log($(this).val() + " ," + $(this).text() + " ," + ck_hp + " ," + $(this).attr('user-hp') );
        			
        			for ( var i in addedSplit ) {
        				for ( var j in newSplit ) {
        					if ( addedSplit[i] == newSplit[j] && addedSplit[i] != '') {
        						console.log('앗 ' + newSplit[j] + ' 가 이미추가되었다.');
        						//exist_hp += newSplitnm[j]+"("+newSplit[j] + "),";
        						exist_hp += newSplitnm[j] + ",";
        						
        					}
        				}
        			}
        		});
		        
		        if ( exist_hp.length == 0 ) {
			        var hp_list = document.getElementById('hp_list');
			        var item    = "개인 (" + count + " 명)";
			        var value   = 'p,' + ck_no;
	
			        for (i=0; i<hp_list.length; i++) {
			            if (hp_list[i].value == value) {
			                alert('이미 같은 목록이 있습니다.');
			                return;
			            }
			        }
			        
			      	//hp_list.options[hp_list.length] = new Option(item, value);
			      
			        var option = document.createElement("option");
			        option.value = value;
			        option.text = item;
			        hp_list.add(option); 
			      		        
			        var nm_att = document.createAttribute('user-nm');
			        var hp_att = document.createAttribute('user-hp');
			        nm_att.value = ck_nm;
			        hp_att.value = ck_hp;
			        
			        option.setAttributeNode(nm_att);
			        option.setAttributeNode(hp_att);
		        } else {
		        	alert(exist_hp +"들이 이미 목록에 있습니다.");
		        }
		    },
		    person_g_multi_add : function(){
		        var chk = document.getElementsByName('bk_no');
		        var ck_no = '';
		        var count = 0;

		        for (i=0; i<chk.length; i++) {
		            if (chk[i].checked==true) {
		                count++;
		                ck_no += chk[i].value + ',';
		            }
		        }

		        if (!count) {
		            alert('하나이상 선택해주세요.');
		            return;
		        }

		        var hp_list = document.getElementById('hp_list');
		        var item    = "개인 (" + count + " 명)";
		        var value   = 's,' + ck_no;

		        for (i=0; i<hp_list.length; i++) {
		            if (hp_list[i].value == value) {
		                alert('이미 같은 목록이 있습니다.');
		                return;
		            }
		        }

		        hp_list.options[hp_list.length] = new Option(item, value);
		    },
		    person : function(bg_no){
		        var params = { bg_no : bg_no };
		        this.person_is_search = true;
		        this.person_select( params, "html" );
		        //book_change('book_person');
		    },
		    group_add : function(bg_no, bg_name, bg_count){
		        if (bg_count == '0') {
		            alert('그룹이 비어있습니다.');
		            return;
		        }

		        var hp_list = document.getElementById('hp_list');
		        var item    = bg_name + " 그룹 (" + bg_count + " 명)";
		        var value   = 'g,' + bg_no;

		        for (i=0; i<hp_list.length; i++) {
		            if (hp_list[i].value == value) {
		                alert('이미 같은 목록이 있습니다.');
		                return;
		            }
		        }

		        hp_list.options[hp_list.length] = new Option(item, value);
		    }
		};
</script>
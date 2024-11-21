<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
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

.form-control-custom {
    background-color: #FFFFFF;
    background-image: none;
    border: 1px solid #e5e6e7;
    border-radius: 5px;
    color: inherit;
    display: inline-block;
    padding: 2px 12px;
    font-size: 13px;
    vertical-align: middle;
    width: 100px;
    height: 34px;

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
    width: 45px;
    height: 45px;
    border: 1px solid #ccc;
    background: #fafafa;
   border-radius: 0;
    font-size: 1em;
    font-family: dotum;
    -webkit-appearance: none;
    cursor: pointer;
}

#sms_list{
   margin-top: 10px;
}

.ment{
  font-weight: bold;
  background: #ffffff;
}

.ment-title{
  padding: 6px;
  margin: 5px;
}
.px115{
  width: 115px;
}
.px120{
  width: 120px;
}
.px130{
  width: 130px;
}
.px140{
  width: 140px;
}
</style>



<div class="tbl_head02 tbl_wrap">
   <table class="table table-bordered table-hover table-striped">
      <tbody>
         <tr>
            <td class="sendsms_area" style="vertical-align:top;" align="center">
              <? include(VIEWPATH.'sms/_left_wrap.php'); ?>
            </td>


            <td class="phone_list_area" style="vertical-align:top; border-left:1px solid #d7d7d7;">
               <div id="send_book">
                  <div class="pull-right btn-group">
                     <button
                     type="button"
                     class="btn btn-ms btn-default"
                     onclick="goto_sms('smssend',this)"
                     >
                     예약리스트
                     </button>
                     <?php if($this->config_bdsr['mobile_contatct'] == 'Y') {?>
                     <button
                     type="button"
                     class="btn btn-ms btn-default"
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
                     <?php } ?>

                     <?php if($this->config_bdsr['mobile_attend'] == 'Y') {?>
                     <button
                     type="button"
                     class="btn btn-ms btn-info"
                     onclick="goto_sms('burning',this)"
                     >
                     긴급문자
                     </button>
                     <?php } ?>
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

<script>
$(document).ready(function() {

   var target = $("#sms_list");
   url = '<?=base_url()?>sms/sms_b_list';
   btn = $(this);

   $.ajax({
      url: url,
      type: 'get',
      dataType: 'html',
      data: {
         '<?=$this->security->get_csrf_token_name()?>' : '<?=$this->security->get_csrf_hash()?>',
         data : '<?=$get?>'
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



/*
function sms5_chk_send(f)
{
    if( is_sms5_submitted == false ){
        is_sms5_submitted = true;
        var hp_list = document.getElementById('hp_list');
        var wr_message = document.getElementById('wr_message');
        var hp_number = document.getElementById('hp_number');
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

        for (i=0; i<hp_list.length; i++)
            list += hp_list.options[i].value + '/';

        w = document.body.clientWidth/2 - 200;
        h = document.body.clientHeight/2 - 100;
        act = window.open('sms_ing', 'act', 'width=300, height=200, left=' + w + ', top=' + h);
        act.focus();

        f.send_list.value = list;
        return true;
    } else {
        alert("데이터 전송중입니다.");
    }
}
*/


$(function(){
      // 페이징
      $(".ajax_pagination li > a").each(function(index, el) {
         page = $(this).attr('data-ci-pagination-page');
         if( page >= 1 ) {
            url = 'burning?'+$("#form_search_act").serialize()+'&page='+page;
            link = "javascript:goto_sms('"+url+"')";
            $(this).attr('href', link);
         } else {
            $(this).attr('href', '#');
         }
      });
   });

$(function() {
        $( "#sstx, #setx, #datepicker, #rv_date" ).datepicker({

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



</script>
    
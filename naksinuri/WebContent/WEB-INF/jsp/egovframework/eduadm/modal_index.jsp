<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<!-- Modal -->
<div class="modal fade" id="exampleFormModal" aria-hidden="false" aria-labelledby="exampleFormModalLabel"
  role="dialog" tabindex="-1">
  <div class="modal-dialog modal-simple">
    <form id="modal_action_form" class="modal-content" autocomplete="off">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">×</span>
        </button>
        <h4 class="modal-title" id="exampleFormModalLabel">카테고리추가</h4>
      </div>
      <div class="modal-body">
        <div class="row">
          <div class="col-xl-4 form-group">
            <input type="text" class="form-control" name="firstName" placeholder="교육명">
          </div>
          <div class="col-md-12 float-right">
            <button type="submit" class="btn btn-primary btn-outline" >추가하기</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>
<!-- End Modal -->

<script>
	
$("#modal_action_form").bind("submit", function(event) {
	console.log('1231231');
	event.preventDefault();

	alert(1);
	
	$("#exampleFormModal").modal('hide');
	
});
	
</script>


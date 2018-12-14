<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<div class="modal fade" id="product_modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">PRODUCT 추가</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" id="pdForm" enctype="multipart/form-data">
					<input type="hidden" name="productId" id="productId"/>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">name</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name" placeholder="name">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">type</label>
						<div class="col-sm-10">
							<select class="form-control" id="type" name="type">
								<sTag:selectTag name="type"/>
							</select>
						</div>
						
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">version</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="version" name="version" placeholder="">
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">productCategory</label>
						<div class="col-sm-10">
							<select class="form-control" id="pdcNameModal" name="productCategoryId">
<!-- 								<option>없음</option> -->
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">file</label>
						<div class="col-sm-10">
							<input type="file" id="file" placeholder="" >
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">status</label>
						<div class="col-sm-10">
							<select class="form-control" id="statusModal" name="status">
								<sTag:selectTag name="status"/>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">description</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="description" name="description" placeholder="">
						</div>
					</div>
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="saveBtn">저장</button>
				<input type="hidden" id="modBtn">
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
// $(document).ready(function(){
	
// 	//id 유효성
	
	
	
	
// 	//비밀번호 check
// 	var password = $('#password').val();
// 	var password-ch = $('#password-ch').val();
// 	$().on('keyup', fuction(){
// 		if(password-ch != password){
// 			alert('비밀번호가 일치하지 않습니다');
// 		}
// 	});

	
// });
</script>

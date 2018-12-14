<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<div class="modal fade" id="productCategoryModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">카테고리 추가</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">name</label>
						<div class="col-sm-10">
							<div class="input-group">
							<input type="text" class="form-control" id="name" placeholder="name" required>
			                <div class="input-group-btn">
			                  <span class="input-group-btn"><button type="button" class="btn btn-primary" id="checkBtn">중복검사</button></span>
			                </div>
			                <!-- /btn-group -->
			                </div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">status</label>
						<div class="col-sm-10">
							<select class="form-control" id="status">
								<sTag:selectTag name="status"/>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">description</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="description" placeholder="">
						</div>
					</div>
					
					<input type="hidden" id="productCategoryId" name="productCategoryId">	

				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="saveBtn">저장</button>
				<input type="hidden" id="modBtn" >
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

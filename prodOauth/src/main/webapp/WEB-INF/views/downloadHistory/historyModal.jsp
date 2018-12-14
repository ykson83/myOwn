<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<div class="modal fade" id="historyModal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">DOWNLOAD HISTORY</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" >
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">productId</label>
						<div class="col-sm-10">
							<input type="text" readonly class="form-control" id="downProductId" name="productId">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">product-name</label>
						<div class="col-sm-10">
<!-- 							<select class="form-control" disabled id="pdName" name="pdName"> -->
<!-- 							</select> -->
							<input type="text" readonly class="form-control" id="downPdName" name="pdName">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">date</label>
						<div class="col-sm-10">
<!-- 							<select disabled class="form-control"  id="addDate" name="addDate"> -->
<!-- 							</select> -->
							<input type="text" readonly class="form-control" id="downAddDate" name="addDate">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">userId</label>
						<div class="col-sm-10">
							<input type="text" readonly class="form-control" id="downAddUser" name="addUser">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">purpose</label>
						<div class="col-sm-10">
							<input type="text" readonly class="form-control" id="downPurpose" name="purpose">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">description</label>
						<div class="col-sm-10">
<!-- 							<input type="text" class="form-control" id="downDescription" name="description" placeholder="다운로드 이유를 입력하세요"> -->
							<textarea disabled rows="5" style="resize: none;" class="form-control" id="downDescription" name="description" placeholder="다운로드 이유를 입력하세요"></textarea>
						</div>
					</div>
				
					
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-primary" id="saveBtn">확인</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->


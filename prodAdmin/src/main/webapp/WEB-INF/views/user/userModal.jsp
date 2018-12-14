<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<div class="modal fade" id="modal-default">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">사용자 추가</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">userId</label>
						<div class="col-sm-10">
							<div class="input-group">
							<input type="text" class="form-control" id="userId" placeholder="userId" required>
			                <div class="input-group-btn">
			                  <span class="input-group-btn"><button type="button" class="btn btn-primary" id="idCheckBtn">중복검사</button></span>
			                </div>
			                </div>
			                <div id="valDiv"></div>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">name</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" placeholder="name">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">password</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" placeholder="">
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">password-check</label>
						<div class="col-sm-10">
							<div class="input-group">
							<input type="password" class="form-control" id="password-ch" value="" placeholder="Password-check">
			                <div class="input-group-btn">
			                  <span class="input-group-btn"><button type="button" class="btn btn-primary" id="pwdCheckBtn">확인</button></span>
			                </div>
			                </div>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 control-label">dept</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="dept" placeholder="">
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 control-label">type</label>
						<div class="col-sm-10">
							<select class="form-control" id="type">
								<option value="0">일반</option>
								<option value="1">관리자</option>
							</select>
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
<!-- 							<input type="text" class="form-control" id="description" placeholder=""> -->
							<textarea rows="5" style="resize: none;" class="form-control" id="description"></textarea>
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



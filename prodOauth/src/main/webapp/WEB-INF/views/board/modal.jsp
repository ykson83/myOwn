<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<div class="modal fade" id="modal-default">
	<div class="modal-dialog  modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">BOARD 추가</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal">
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">title</label>
						<div class="col-sm-10">
							<input readonly type="text" class="form-control" id="title" placeholder="title" required>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">boardCategory</label>
						<div class="col-sm-10">
							<select disabled class="form-control" id="bcNameModal">
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="" class="col-sm-2 control-label">status</label>
						<div class="col-sm-10">
							<select disabled class="form-control" id="status">
								<sTag:selectTag name="status"/>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="" class="col-sm-2 control-label">description</label>
						<div class="col-sm-10">
							<!-- 							<textarea id="editor2" name="editor2" rows="15" cols="63"></textarea> -->
							<!-- <textarea readonly rows="15" cols="63" id="editor2"></textarea> -->
							<!-- 							<input id="description" readonly> -->

							<div class="box box-primary" id="content-box">
								<div class="box-body">
									<div id=editor2></div>
								</div>
							</div>


						</div>
					</div>

				</form>
			</div>
			<div class="modal-footer">
			<button type="button" class="btn btn-primary pull-right" data-dismiss="modal">close</button>
<!-- 			<button>close</button> -->
				<input type="hidden" id="modBtn" >
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

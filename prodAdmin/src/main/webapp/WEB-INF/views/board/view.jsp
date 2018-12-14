<%@page import="org.springframework.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>
<%
	String boardId = request.getParameter("x");
	boolean nullCheck = false;
	if(!StringUtils.isEmpty(boardId)) {
		nullCheck =true;
	}
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/inc/meta.jsp"%>
<!-- Google Font -->
 <!--  <link rel="stylesheet"
        href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic"> -->
<title>KSignAccess | ${pageHeaderName} View</title>
</head>
<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">

		<%-- Main Header include --%>
		<%@ include file="/WEB-INF/views/inc/main_header.jsp"%>

		<%-- SideBar include --%>
		<%@ include file="/WEB-INF/views/inc/sideBar.jsp"%>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>${pageHeaderName}</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">${pageHeaderName}</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title" id="boardTitle">${pageHeaderName}</h3>
							</div>
							<div class="box-body">
								<form class="form-horizontal">
								
<!-- 								if문 고치기 -->
								<c:if test="${x ne null}"> 
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">boardId</label>
										<div class="col-sm-10">
											<input readonly type="text" class="form-control" id="boardId" placeholder="boardId">
										</div>
									</div>
								</c:if>
								
									<div class="form-group">
										<label for="certDn" class="col-sm-2 control-label">title</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="title" placeholder="title">
										</div>
									</div>
									<div class="form-group">
										<label for="inputEmail3" class="col-sm-2 control-label">boardCategory</label>
										<div class="col-sm-10">
											<select class="form-control" id="bcNameModal">
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label for="certDn" class="col-sm-2 control-label">status</label>
										<div class="col-sm-10">
											<select class="form-control" id="status">
												<sTag:selectTag name="status"/>
											</select>
										</div>
									</div>
									
						            <div class="form-group">
										<label for="certDn" class="col-sm-2 control-label">description</label>
										<div class="col-sm-10">
											<textarea id="editor1" name="editor1" rows="10" cols="80"></textarea>
										</div>
									</div>
						            
								</form>
							</div>

							<div class="box-footer clearfix">
								<div class="pull-right btn-group">
									<button type="button" id="cancelBtn" class="btn btn-warning">cancel</button>
									<button type="button" id="submitBtn" class="btn btn-primary">submit</button>
                				</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- end row -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	</div>

	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- CK Editor -->
	<script src="${ctxPath}/resources/js/plugins/ckeditor/ckeditor.js"></script>
	<script>
		var pageSize = 20;
		var boardId = '<%= boardId %>';
		var nullCheck = <%= nullCheck %>;
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			$("#boardListMenu").addClass("active");
			
			$("#deleteBtn").click(function(){
				delModal($("#boardId").val());
			});
			
		
			CKEDITOR.replace('editor1');
			
			//category불러오기
			var req = {
					start: 0,
					length: 999,
					orderColumn: "ADDDATE",
					orderDir: "DESC"
			}
			handleGet("/boardCategory/list_with_count", req, function(result) {
				for(var i = 0; i < result.data.length; i++) {
					var boardCategoryId = result.data[i].boardCategoryId;
					var name = result.data[i].name;
					var status = result.data[i].status;
	
					if(status != 1) continue;
					var opt = "<option value='" + boardCategoryId + "'>" + name + "</option>";
					
					$('#bcNameModal').append(opt);
				}
				
				if(boardId == null || boardId == '') {
					$('#boardTitle').text('BOARD 추가');
				} else {
					search();
				}
			});
			
			$('#cancelBtn').click(function() {
				history.back();
			})
			
			$('#submitBtn').click(function() {
				console.log("현재 boardId: " + boardId);
				if(!nullCheck) {
					console.log('save');
					save();
				} else {
					console.log('modify');
					update();
				}
			})
		});

		
		function makeObj() {
			var obj = {
					boardId: boardId,
					title: $("#title").val(),
					status: $('#status').val(),
					description: CKEDITOR.instances.editor1.getData()
				};
				
				obj.boardCategory = {};
				obj.boardCategory.boardCategoryId = $('#bcNameModal').val();
				//BoardEntity에 boardCategoryId 컬럼이 없으므로 
				return obj;
		}
		
		function update() {
			var obj = makeObj();
			handlePatch("/board/update/" + obj.boardId, obj, function(result) {
				alert(result.message);
				history.back();
			});
		}
		
		function save() {
			var obj = makeObj();
			handlePost("/board/", obj, function(result) {
				console.log($('#editor1').html());
				alert(result.message);
				history.back();
			});
				
		}
		
		function search() {
			handleGet("/board/" + boardId, "", function(result) {
				var obj = result.object
				$("#boardId").val(obj.boardId);
	 			$("#title").val(obj.title);
// 	 			$("#bcNameModal").val(obj.boardCategory.boardCategoryId);
	 			$("#bcNameModal").val(obj.boardCategory.boardCategoryId).prop("selected", true); //값이 1인 option 선택

	 			$("#status").val(obj.status);
	 			$('#editor1').html(obj.description);
			});
		}
		
	</script>
</body>
</html>


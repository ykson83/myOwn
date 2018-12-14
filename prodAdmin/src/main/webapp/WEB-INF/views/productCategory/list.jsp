<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/print.tld" prefix="sTag" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/inc/meta.jsp"%>
  <!-- daterange picker -->
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/datepicker/datepicker3.css">
  
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/css/dataTables.responsive.css">
  <title>KSignAccess | ${pageHeaderName} List</title>
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
				<h1>${pageHeaderName}  List</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">${pageHeaderName} List</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="row">
				
					<div class="col-md-12">
						<div class="box box-primary">
							<div class="box-header with-border">
								<h3 class="box-title">${pageHeaderName} List</h3>
							</div>
							<div class="box-body">
							
							<form id="searchForm" role="form">
							<div class="row">
								<div class="col-md-3">
									<div class="form-group">
										<label for="">name</label> <input type="text" name="name" class="form-control" placeholder="category name">
									</div>
								</div>
<!-- 								<div class="col-md-3"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label for="">status</label>  -->
<!-- 										<select class="form-control" name="status"> -->
<!-- 											<option>선택</option> -->
<%-- 											<sTag:selectTag name="status"/> --%>
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div> -->
								<div class="col-md-3">
									<div class="form-group">
										<label for="">description</label> <input type="text" name="description" class="form-control" placeholder="description">
									</div>
								</div>
							</div>

							<div class="row">
								<div class="col-md-1 pull-right"> 
									<div class="form-group">
										<label></label>
										<button type="button" id="searchBtn" class="btn btn-block btn-success">조 회</button>
									</div>
								</div>
							</div>
							
							</form>
								<br>
								<span class="pull-right"><button type="button" class="btn btn-primary" id="addModalBtn">카테고리 추가</button></span>
								<table class="table table-bordered table-striped dataTable responsive" width="100%" id="listTable">
									<thead>
										<tr>
											<th>#</th>
											<th>NAME</th>
											
											<th>description</th>
											<th>status</th>
											<th>addDate</th>
											<th>addUser</th>
											<th>modDate</th>
											<th>modUser</th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr>
										</tr>
									</tbody>
									<tfoot>
									</tfoot>
								</table>
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
	
	<%@ include file="/WEB-INF/views/productCategory/modal.jsp"%>
	
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	
	<script>
		var sortColList = ['', 'NAME', 'DESCRIPTION', 'STATUS', 'ADDUSER', 'ADDDATE', 'MODDATE', 'MODUSER', '', ''];
		var nameCheck = false;
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			
			$("#productCategoryListMenu").addClass("active");
			
			search();
			
			$("#addModalBtn").on('click', function(e) {
				$('#name').attr('readonly', false);

				$("#productCategoryModal").on('hidden.bs.modal', function (e) { 
				    $("#productCategoryModal").find('form')[0].reset() 
				}); //모달 청소

				$("#productCategoryModal").modal();
			});
			
			$("#saveBtn").click(function() {
				if($('#name').val() == null || $('#name').val() == '') {
					alert("name을 입력하세요")
				}else {
					if($('#modBtn').val() == 'mod') {
						update();
					}else if(nameCheck == false){
						alert("name 중복검사를 하세요");
					} else {
						save();
					}
				}
			});
			
			$('#name').on('change', function() {
				nameCheck = false;
			});
			
			$('#checkBtn').on('click', function() {
				if($('#name').val() == null || $('#name').val() == '') {
					alert("name을 입력하세요")
				} else {
					nameChecking();
				}
			});
		});//document 끝
		
		
		
		function search() {
			$('#listTable').DataTable( {
				filter: false,
				//info: false,
				lengthChange: true,
		        serverSide: true,
		        bPaginate: true,
		        responsive: true,
		        lengthMenu : [10, 25, 50] ,
		        ajax: {
		            url: "${ctxPath}/api/productCategory/list_with_count",
		            
		            type: "get",
		            data: function ( d ) {
		            	/* sort field */
		            	
		            	for (var i = 0; i < d.order.length; i++) {
		        			columnIndex = d.order[i].column;
		        			d.orderDir = d.order[i].dir;
 		        			d.orderColumn = sortColList[columnIndex];
		        	    }
		            	
		            	/* search field */
		                d.name = $('#searchForm input[name="name"]').val();
		                d.status = $('#searchForm select[name="status"]').val();
		                d.description = $('#searchForm input[name="description"]').val();
		            	console.log(d);
		                
		            }
		        },
		        order: [[1, "desc"]],
		        columns: [
		        	{ "data": function(data, type, row, meta ) {
		        		return meta.row + 1;
		        	}},
		        	{ "data": "name" },
		        	{ "data": function(data, type, row, meta) {
		        		var des = data.description;
		        		if(des && des.length >30) {
		        			des = des.substring(0, 30) + "...";
		        		}
		        		return des;
		        	}},
		        	{ "data": "status" },
		        	{ "data": "addDate" },
		        	{ "data": "addUser" },
		        	{ "data": "modDate" },
		        	{ "data": "modUser" }
		        	
		        ],
		        columnDefs: [
		        	{ orderable: false, targets: [0, 8] }
		        	,{
			            "targets": 3,
			            "data": "status",
			            "render": function (data, type, row) {
			            	return toStatusStr(data);
			          	}
		        	}
	        	 ,{
			            "targets": 8,
			            "data": "productCategoryId",
			            "render": function (data, type, row) {
			            	return '<a href="#" onclick="javascript:updModal(\'' + data + '\');"> <i class="fa fa-fw fa-pencil text-yellow"></i></a> <a href="#" onclick="javascript:delModal(\'' + data + '\');"><i class="fa fa-fw fa-close text-red"></i></a>';
			          	}
		        	}
		        ]
		    });
					
			$('#searchBtn').click(function () {
				listDraw();
			});
		}
		
		function listDraw(){
			$('#listTable').DataTable().draw();
		}
		
		function makeObj() {
			var obj = {
				name: $("#name").val(),
				status: $('#status').val(),
				description: $('#description').val(),
				productCategoryId: $('#productCategoryId').val()
			};
			return obj;
		}
		
		function nameChecking() {
			var objectId = $('#name').val();
			handleGet("/productCategory/nameCheck/" + objectId, "", function(result) {
				
				if(result.code == 404) {
					nameCheck = true;
				}
				alert(result.message);
			}, function(result) {});
		}
		
		function save() {
				var obj = makeObj();
				handlePost("/productCategory/" + "", obj, function(result) {
					alert(result.message);
					location.reload();
				});
				$('#productCategoryModal').modal('hide');	
		}
		
		function updModal(objectId) {
			//업데이트 모달을 띄우고 기존 정보 입력
			$('#modBtn').val('mod');
			$('h4').text('카테고리 수정');
			
			handleGet("/productCategory/" + objectId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				if($('#modBtn').val() == 'mod') {
					$("#name").val(obj.name);
					$("#name").attr('readonly', true);
					$('#status').val(obj.status);
					$("#description").val(obj.description);
					$('#productCategoryId').val(objectId);
					
					$("#productCategoryModal").modal();
				}
			});
		}
		
		//저장 클릭시 업데이트
		function update() {
			var obj = makeObj();
			
			handlePatch("/productCategory/" + obj.productCategoryId, obj, function(result) {
				alert(result.message);
				location.reload();
				$('#modBtn').val('');
			});
		}
		
		
		function del() {
			var productCategoryId = $('#deleteIdOne').val();
			handleDelete("/productCategory/" + productCategoryId, "", function(result) {
				alert(result.message);
				location.reload();
			});
			
			$('#modalDelete').modal('hide');
		}

	</script>
</body>
</html>
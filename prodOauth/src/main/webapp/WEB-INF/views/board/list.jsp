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
										<label for="">board category</label> 
										<select class="form-control" id="bcName" name="boardCategoryId">
											<option value="0">선택</option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">title</label> <input type="text" name="title" class="form-control" placeholder="title">
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
										<label for="">addUser</label> <input type="text" name="addUser" class="form-control" placeholder="addUser">
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
								<span class="pull-right"><button type="button" class="btn btn-primary" id="addModalBtn" >BOARD 추가</button></span>
								<table class="table table-bordered table-striped dataTable responsive" width="100%" id="listTable">
									<thead>
										<tr>
											<th>boardId</th>
											<th>category</th>
											<th>title</th>
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
	
	<%@ include file="/WEB-INF/views/board/modal.jsp"%>
	
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- CK Editor -->
	<script src="${ctxPath}/resources/js/plugins/ckeditor/ckeditor.js"></script>
	
	<script>
		var sortColList = ['B.BOARDID', 'BC.NAME', 'B.TITLE', '', 'B.STATUS', 'B.ADDDATE', 'B.ADDUSER', 'B.MODDATE', 'B.MODUSER' ];
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			
			$("#boardListMenu").addClass("active");
			
			search();
			
			$("#addModalBtn").on('click', function(e) {
				location.href = "view";
			});
			
			$("#saveBtn").on('click', function(e) {
				if($('#modBtn').val() == 'mod') {
					update();
				} else {
					save();
				}
			});
			
			//modal에 category불러오기
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
					
					$("#bcName").append(opt); 
					$('#bcNameModal').append(opt);
				}
			});
			
			
			
			$('#testPageBtn').click(function() {
				location.href = "test";
			})
			
// 			CKEDITOR.replace('editor2');
			
			
		});//document 끝
		

		
		function search() {
			$('#listTable').DataTable( {
				filter: false,
				//info: false,
				lengthChange: true,
		        serverSide: true,
		        bPaginate: true,
		        
		        responsive: true,
		        pageLength: 25,
		        ajax: {
		            url: "${ctxPath}/api/board/list_with_count",
		            
		            type: "get",
		            data: function ( d ) {
		            	/* sort field */
		            	for (var i = 0; i < d.order.length; i++) {
		        			columnIndex = d.order[i].column;
		        			d.orderDir = d.order[i].dir;
		        			d.orderColumn = sortColList[columnIndex];
		        	    }
		            	
		            	/* search field */
		                d.title = $('#searchForm input[name="title"]').val();
		            	d.boardCategoryId = $('#searchForm select[name="boardCategoryId"]').val(); 
		                d.status = $('#searchForm select[name="status"]').val();
		                d.addUser = $('#searchForm input[name="addUser"]').val();
		            }
		        },
		        order: [[0, "desc"]],
		        columns: [
		        	{ "data": function(data, type, row, meta ) {
		        		return data.boardId;
		        	}},
		        	{ "data": "boardCategory.name" },
		        	{ "data": function(data, type, row, meta) {
		        		var title = data.title;
		        		if(title && title.length >20) {
		        			title = title.substring(0, 20) + "...";
		        		}
		        		return title;
		        	} },
		        	{ "data": function(data, type, row, meta) {
		        		var desc = data.description;
		        		if(desc && desc.length > 20) {
		        			desc = desc.substring(0, 20) + "...";
		        		}
		        		return  desc;
		        	} },
		        	{ "data": "status" },
		        	{ "data": "addDate" },
		        	{ "data": "addUser" },
		        	{ "data": "modDate" },
		        	{ "data": "modUser" }
		        	
		        ],
		        columnDefs: [
		        	{ orderable: false, targets: [0, 8] }
		        	 ,{
				            "targets": 4,
				            "data": "status",
				            "render": function (data, type, row) {
				            	return toStatusStr(data);
				          	}
			        	}
	        	 ,{
			            "targets": 9,
			            "data": "boardId",
			            "render": function (data, type, row) {
			            	return '<a href="view?x=' + data + '"> <i class="fa fa-fw fa-pencil text-yellow"></i></a>' + 
			            			'<a href="#" onclick="javascript:delModal(\'' + data + '\');"><i class="fa fa-fw fa-close text-red"></i></a>' +
			            			'<a href="#" onclick="javascript:detailModal(\'' + data + '\');"><i class="fa fa-fw fa-eye text-black"></i></a>';
			          		console.log("페이지 넘김 " + data);
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
				boardId: $("#boardId").val(),
				title: $("#title").val(),
				status: $('#status').val(),
				description: $('#description').val()
			};
			
			obj.boardCategory = {};
			obj.boardCategory.boardCategoryId = $('#bcNameModal').val();
			//BoardEntity에 boardCategoryId 컬럼이 없으므로 
			return obj;
		}
		
		function save() {
				var obj = makeObj();
				handlePost("/board/", obj, function(result) {
					alert(result.message);
					location.reload();	
				});
					
		}
		
		
		function del() {
			var boardId = $('#deleteIdOne').val();
			handleDelete("/board/" + boardId, "", function(result) {
				alert(result.message);
				location.reload();
			});
		}
		
		
		function detailModal(boardId) {
			$('#modBtn').val('mod');
			$('h4').text(boardId);
			
			
			handleGet("/board/" + boardId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				if($('#modBtn').val() == 'mod') {
					
					$("#title").val(obj.title);
					$("#bcNameModal").val(obj.boardCategory.boardCategoryId); //boardCategory 테이블의 name
// 					$("#description").append(obj.description);
					
					if(obj.description && obj.description.length > 0) {
						$('#editor2').html(obj.description);
// 						$('#content-box').removeClass('hidden');
					} else {
						$('#editor2').html('<center>내용이 없습니다</center>');
// 						$('#content-box').addClass('hidden');
					}
	 				
					$('#status').val(obj.status);
					$("#boardId").val(boardId);
					
					$("#modal-default").modal();
				}
			});
		}
		
	</script>
</body>
</html>
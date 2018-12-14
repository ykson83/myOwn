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
										<label for="">historyId</label> <input type="text" name="historyId" class="form-control" placeholder="historyId" >
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">product-name</label> <input type="text" name="name" class="form-control" placeholder="product-name">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">purpose</label> <input type="text" name="purpose" class="form-control" placeholder="purpose">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">date</label> 
										<input type="text" name="addDate" class="form-control" placeholder="date">
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
								<table class="table table-bordered table-striped dataTable responsive" width="100%" id="listTable">
									<thead>
										<tr>
											<th>historyId</th>
											<th>product-name</th>
											<th>purpose</th>
											<th>description</th>
											<th>date</th>
											<th>userId</th>
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
	
	<%@ include file="/WEB-INF/views/downloadHistory/historyModal.jsp"%>
	
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	
	<script>
		var sortColList = ['DH.HISTORYID','DH.PRODUCT.NAME', 'DH.PURPOSE', 'DH.DESCRIPTION', 'DH.ADDDATE', 'DH.ADDUSER', '', '', '', '', ''];
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			
			$("#downloadHistoryListMenu").addClass("active");
			
			search();
			
			

				
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
		            url: "${ctxPath}/api/product/historyList_with_count",
		            
		            type: "get",
		            data: function ( d ) {
		            	/* sort field */
		            	for (var i = 0; i < d.order.length; i++) {
		        			columnIndex = d.order[i].column;
		        			d.orderDir = d.order[i].dir;
		        			d.orderColumn = sortColList[columnIndex];
		        	    }
		            	
		            	/* search field */
		                d.historyId = $('#searchForm input[name="historyId"]').val(); 
		                d.name = $('#searchForm input[name="name"]').val();
		                d.purpose = $('#searchForm input[name="purpose"]').val();
		                d.addDate = $('#searchForm input[name="addDate"]').val(); //이거 쓸거면 date써서 기간으로 검색
// 		                d.status = $('#searchForm select[name="status"]').val();
		                
		            }
		        },
		        order: [[0, "desc"]],
		        columns: [
		        	
		        	{ "data": function(data, type, row, meta ) {
		        		return data.historyId;
		        	}},
		        	
		        	{ "data": "product.name" },
		        	{ "data": "purpose" },
		        	{ "data": function(data, type, row, meta) {
		        		var des = data.description;
		        		if(des && des.length >20) {
		        			des = des.substring(0, 20) + "...";
		        		}
		        		return des;
		        	}},
		        	{ "data": "addDate" },
		        	{ "data": "addUser" }
		        	
		        ],
		        columnDefs: [
		        	{ orderable: false, targets: [0, 6] }
// 		        	 ,{
// 				            "targets": 7,
// 				            "data": "status",
// 				            "render": function (data, type, row) {
// 				            	return toStatusStr(data);
// 				          	}
// 			        	}
	        	 ,{
			            "targets": 6,
			            "data": "historyId",
			            "render": function (data, type, row) {
			            	return '<a href="#" onclick="javascript:detailModal(\'' + data + '\');"><i class="fa fa-fw fa-eye text-black"></i></a>';
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
		
		
		//모달을 띄우고 기존 정보 입력
		function detailModal(objectId) {
			handleGet("/product/historyDetail/" + objectId, "", function(result) {
				console.log(result);
				
				var obj = result.object;
				//TODO: Son	
// 				$("#historyId").val(obj.historyId);
				$("#downProductId").val(obj.product.productId);
				//product.category도 넣자
				$("#downPdName").val(obj.product.name); //product 테이블의 name
 				$('#downAddDate').val(obj.addDate);
				$("#downAddUser").val(obj.addUser);
				$("#downPurpose").val(obj.purpose);
				$("#downDescription").val(obj.description);
				
				$("#historyModal").modal();
			});
		}
		
		

		

	</script>
</body>
</html>
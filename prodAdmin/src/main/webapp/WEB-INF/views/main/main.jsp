<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/inc/meta.jsp" %>

<!-- daterange picker -->
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.css">
  <!-- bootstrap datepicker -->
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/datepicker/datepicker3.css">
  <link rel="stylesheet" href="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/css/dataTables.responsive.css">
  <title>KSignAccess | DashBoard</title>
</head>
<style>
#div1 {
 float: left;
}
#div2 {
/*  width: fit-content; */
 margin: 0 auto;
}
#div3 {
 float: right;
}
/* table { */
/*  table-layout: fixed; */
/* } */
/* td { */
/*  text-overflow: ellipsis; */
/*  overflow: hidden; */
/*  white-space: nowrap; */
/*   line-height: 1.2em; */
/*  max-height: 1.2em;  */
/* } */
</style>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 
 <%-- Main Header include --%>
 <%@ include file="/WEB-INF/views/inc/main_header.jsp" %>
  
 <%-- SideBar include --%>
 <%@ include file="/WEB-INF/views/inc/sideBar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Dashboard <small>Version 2.0</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
				</ol>
			</section>

<section class="content">
			<div class="row">
				<div class="col-md-12" style="padding: 15px;"></div>
			</div>

			<!-- Main content -->
			<div class="row">
				<div class="col-md-12">
					<div class="box box-info" id="div1">
						<div class="box-header with-border">
							<h3 class="box-title">Latest Board</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" onclick="location.href='${ctxPath}/views/board/list'">
									view all boards
								</button>
							</div>
						</div>
						<div class="box-body chart-responsive">
							<table width='100%'	class="table" id="listTable1">
								<thead>
									<tr>
										<th>category</th>
										<th>title</th>
										<th>description</th>
										<th>addDate</th>
										<th>addUser</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-6">
					<div class="box box-info" id="div2">
						<div class="box-header with-border">
							<h3 class="box-title">Latest Product</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" onclick="location.href='${ctxPath}/views/product/list'">
									view all products
								</button>
							</div>
						</div>
						<div class="box-body chart-responsive">
							<table width='100%'	class="table" id="listTable2">
								<thead>
									<tr>
										<th>name</th>
										<th>type</th>
										<th>version</th>
										<th>description</th>
										<th>addDate</th>
										<th>addUser</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="box box-info" id="div3">
						<div class="box-header with-border">
							<h3 class="box-title">Latest Download history</h3>

							<div class="box-tools pull-right">
								<button type="button" class="btn btn-box-tool" onclick="location.href='${ctxPath}/views/downloadHistory/list'">
									view all download histories
								</button>
							</div>
						</div>
						<div class="box-body chart-responsive">
							<table width='100%'	class="table" id="listTable3">
								<thead>
									<tr>
										<th>product</th>
										<th>purpose</th>
										<th>description</th>
										<th>addDate</th>
										<th>addUser</th>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
								</tbody>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
				</div>
			</div>
			 </section>
		</div>
		
		<!-- /.content -->
 </div>

  <!-- /.content-wrapper -->
<%@ include file="/WEB-INF/views/inc/footer.jsp" %>
<%@ include file="/WEB-INF/views/inc/base_js.jsp" %>
<%@ include file="/WEB-INF/views/board/modal.jsp"%>
<%@ include file="/WEB-INF/views/product/modal.jsp"%>
<%@ include file="/WEB-INF/views/downloadHistory/historyModal.jsp"%>

<!-- DataTables -->
<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>


	<script>
		$(document).ready(function() {
			$("#dashboardMenu").addClass("active");
			setApiUrl("${ctxPath}");
			
			table1();
			table2();
			table3();
			
			//board category불러오기
			var req = {
					start: 0,
					length: 999,
					orderColumn: "ADDDATE",
					orderDir: "DESC"
			}
			handleGet("/api/boardCategory/list_with_count", req, function(result) {
				
				for(var i = 0; i < result.data.length; i++) {
					var boardCategoryId = result.data[i].boardCategoryId;
					var name = result.data[i].name;
					var status = result.data[i].status;

					if(status != 1) continue;
					var opt = "<option value='" + boardCategoryId + "'>" + name + "</option>";
					
					$('#bcNameModal').append(opt);
				}
			});	
			
			
			//product category 불러오기
			var req = {
					start: 0,
					length: 999,
					orderColumn: "ADDDATE",
					orderDir: "DESC"
			}
			handleGet("/api/productCategory/list_with_count", req, function(result) {
				
				for(var i = 0; i < result.data.length; i++) {
					var productCategoryId = result.data[i].productCategoryId;
					var name = result.data[i].name;
					var status = result.data[i].status;
	
					if(status != 1) continue;
					var opt = "<option value='" + productCategoryId + "'>" + name + "</option>";
					
					$('#pdcNameModal').append(opt);
				}
			});
			
		});//document 끝
		
		
// 		function table1() {
// 			$('#listTable1').DataTable( {
// 				filter: false,
// 				//info: false,
// 				lengthChange: false,
// 		        serverSide: true,
// 		        bPaginate: false,
		        
// 		        responsive: true,
// 		        pageLength: 10,
// 				ajax: {
// 					url: "${ctxPath}/api/board/list_with_count",
// 					type: "get"
// 				},
// 		        order: [[0, "desc"]],
// 		        columns: [
// 		        	{ "data": "boardCategory.name" },
// 		        	{ "data": function(data, type, row, meta) {
// 		        		var title = data.title;
// 		        		if(title && title.length >10) {
// 		        			title = title.substring(0, 10) + "...";
// 		        		}
// 		        		return title;
// 		        	} },
// 		        	{ "data": function(data, type, row, meta) {
// 		        		var desc = data.description;
// 		        		if(desc && desc.length > 10) {
// 		        			desc = desc.substring(0, 10) + "...";
// 		        		}
// 		        		return  desc;
// 		        	} },
// 		        	{ "data": "addDate" },
// 		        	{ "data": "addUser" }
		        	
// 		        ],
// 		        columnDefs: [
// 		        	{ orderable: false, targets: [0] }
// 		        ]
// 		    });
// 		}
		
		function table1() {
			var req = {
					start: 0,
					length: 10,
					orderColumn: "B.ADDDATE",
					orderDir: "DESC"
			}
			
			handleGet("/api/board/list_with_count", req, function(result) {
				var datas = result.data;
				var tbody = "<tbody>"
				
				for(var i = 0; i < datas.length; i++) {
					
					var desc = datas[i].description;
					console.log(desc);
					
					if(desc && desc.length > 40) desc = desc.substring(0, 40);
					
					var tr = "<tr>"
					var td = "<td>"+ datas[i].boardCategory.name + "</a></td>";
					td += '<td><a href="#" onclick="javascript:boardDetail(\'' + datas[i].boardId + '\');">' + datas[i].title + '</a></td>'
					td += "<td>" + desc + "</td>";
					td += "<td>" + datas[i].addDate.substring(0,10) + "</td>";
					td += "<td>" + datas[i].addUser + "</td>";
					tr += td;
					tr += "</tr>\n";
					tbody += tr;
				}
				
				tbody += "</tbody>";
				
				$("#listTable1 tbody").remove();
				$("#listTable1 ").append(tbody);
			});
		}
		
				
		function table2() {
			var req = {
					start: 0,
					length: 10,
					orderColumn: "P.ADDDATE",
					orderDir: "DESC"
			}
			
			handleGet("/api/product/list_with_count", req, function(result) {
				var datas = result.data;
				var tbody = "<tbody>"
				
				for(var i = 0; i < datas.length; i++) {
					var tr = "<tr>"
					var td = '<td><a href="#" onclick="javascript:productDetail(\'' + datas[i].productId + '\');">' + datas[i].name + '</a></td>';
					td += "<td>" + toPdTypeToStr(datas[i].type) + "</td>";
					td += "<td>" + datas[i].version + "</td>";
					td += "<td>" + datas[i].description + "</td>";
					td += "<td>" + datas[i].addDate.substring(0,10) + "</td>";
					td += "<td>" + datas[i].addUser + "</td>";
					tr += td;
					tr += "</tr>\n";
					tbody += tr;
				}
				
				tbody += "</tbody>";
				
				$("#listTable2 tbody").remove();
				$("#listTable2 ").append(tbody);
			});
		}
		
		
		function table3() {
			var req = {
					start: 0,
					length: 10,
					orderColumn: "DH.ADDDATE",
					orderDir: "DESC"
			}
			
			handleGet("/api/product/historyList_with_count", req, function(result) {
				var datas = result.data;

				var tbody = "<tbody>"
				
				for(var i = 0; i < datas.length; i++) {
					var tr = "<tr>"
					var td = "<td>" + datas[i].product.name + "</td>";
					td += '<td><a href="#" onclick="javascript:historyDetail(\'' + datas[i].historyId + '\');">' + datas[i].purpose + '</a></td>';
					td += "<td>" + datas[i].description + "</td>";
					td += "<td>" + datas[i].addDate.substring(0,10) + "</td>";
					td += "<td>" + datas[i].addUser + "</td>";
					tr += td;
					tr += "</tr>\n";
					tbody += tr;
				}
				
				tbody += "</tbody>";
				
				$("#listTable3 tbody").remove();
				$("#listTable3").append(tbody);
			});
		}
		
		
		
		
		
		//detail Modal 띄우기
		function boardDetail(boardId) {
			console.log(boardId);
			$('#modBtn').val('mod');
			$('h4').text(boardId);
			
			handleGet("/api/board/" + boardId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				if($('#modBtn').val() == 'mod') {
					
					$("#title").val(obj.title);
					$("#bcNameModal").val(obj.boardCategory.boardCategoryId); //boardCategory 테이블의 name
					
					if(obj.description && obj.description.length > 0) {
						$('#editor2').html(obj.description);
					} else {
						$('#editor2').html('<center>내용이 없습니다</center>');
					}
	 				
					$('#status').val(obj.status);
					$("#boardId").val(boardId);
					
					$("#modal-default").modal();
				}
			});
		}
		
		//product modal
		function productDetail(objectId) {
			$('h4').text(objectId);
			
			handleGet("/api/product/" + objectId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				$("#productId").val(obj.productId);
				$("#name").val(obj.name);
				$('#type').val(obj.type);
				$("#version").val(obj.version);
				$('#pdcNameModal').val(obj.productCategory.productCategoryId);
				$('#file').replaceWith('<input class="form-control" type="text" id="file" value="' + obj.fileName +'">');
// 				$('#file').attr('type', 'text');
// 				$("#file").val(obj.file);
				$("#description").val(obj.description);
				$("#statusModal").val(obj.status);
				
				$("#product_modal").modal();
			});
		}
		
		//download history modal
		function historyDetail(objectId) {
			handleGet("/api/product/historyDetail/" + objectId, "", function(result) {
				$('h4').text(objectId);
				
				var obj = result.object;
				//TODO: Son	
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
		

$('tb').each(function() {
	var length = 7;
	if($(this).text().length >= length) {
		$(this).text($(this).text().substr(0, length) + '...');
	}
});

function toPdTypeToStr(num) {
	if(num == 1) {
		return 'FILE';
	} else if(num == 2){
		return 'CONFIG';
	} else {
		return '';
	}
}
				
		
		
	</script>
</body>
</html>

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
										<label for="">product category</label> 
<!-- 										<input type="text" name="name" class="form-control" placeholder="productCategory-name"> -->
										<select class="form-control" id="pdcName" name="productCategoryId">
											<option value="0">선택</option>
										</select>
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">name</label> <input type="text" name="name" class="form-control" placeholder="name" >
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">version</label> <input type="text" name="version" class="form-control" placeholder="version">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">fileName</label> <input type="text" name="fileName" class="form-control" placeholder="fileName">
									</div>
								</div>
<!-- 								<div class="col-md-3"> -->
<!-- 									<div class="form-group"> -->
<!-- 										<label for="">status</label>  -->
<!-- 										<select class="form-control" id="status" name="status" style="width: 100px"> -->
<!-- 											<option >선택</option> -->
<%-- 											<sTag:selectTag name="status"/> --%>
<!-- 										</select> -->
<!-- 									</div> -->
<!-- 								</div> -->
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
								<span class="pull-right"><button type="button" class="btn btn-primary" id="addModalBtn">PRODUCT 추가</button></span>
								<table class="table table-bordered table-striped dataTable responsive" width="100%" id="listTable">
									<thead>
										<tr>
											<th>productId</th>
											<th>category</th>
											<th>name</th>
											<th>type</th>
											<th>version</th>
											<th>fileName</th>
											<th>description</th>
											<th>status</th>
											<th>addDate</th>
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
	
	<%@ include file="/WEB-INF/views/product/modal.jsp"%>
	<%@ include file="/WEB-INF/views/product/downloadModal.jsp"%>
	
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	
	<script>
		var sortColList = ['P.PRODUCTID','PC.NAME', 'P.NAME', 'P.VERSION', 'P.FILENAME', 'P.DESCRIPTION', 'P.STATUS', '', '', '', '', ''];
		var nullResult = false;
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			
			$("#productCategoryListMenu").addClass("deactive");
			$("#productListMenu").addClass("active");
			
			search();
			
			$("#addModalBtn").on('click', function(e) {
				$("#product_modal").on('hidden.bs.modal', function (e) { 
				    $("#product_modal").find('form')[0].reset() 
				}); //모달 청소
				$('h4').text('PRODUCT 추가');
				$('#modBtn').val('');
				$("#product_modal").modal();
			});
			
			$("#saveBtn").on('click', function(e) {
				nullCheck();
				if(nullResult) {
					if($('#modBtn').val() == 'mod') {
						update();
					}else{
						save();
					}
				}
				
			});
			
			//modal에 category불러오기
			var req = {
					start: 0,
					length: 999,
					orderColumn: "ADDDATE",
					orderDir: "DESC"
			}
			handleGet("/productCategory/list_with_count", req, function(result) {
				console.log(result);
				
				for(var i = 0; i < result.data.length; i++) {
					var productCategoryId = result.data[i].productCategoryId;
					var name = result.data[i].name;
					var status = result.data[i].status;
	
					if(status != 1) continue;
					var opt = "<option value='" + productCategoryId + "'>" + name + "</option>";
					
					$("#pdcName").append(opt); 
					$('#pdcNameModal').append(opt);
					$('#downPdcName').append(opt);
				}
			});
			
			//downloadModal 작성 후 확인
			$('#downSaveBtn').on('click', function() {
				if($('#downPurpose').val() == '') {
					alert('purpose를 입력하세요');
				} else if($('#downDescription').val() == '') {
					alert('description을 입력하세요');
				} else {
					filedownload();
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
// 		        pageLength: 25,
		        lengthMenu : [10, 25, 50] ,
		        ajax: {
		            url: "${ctxPath}/api/product/list_with_count",
		            
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
		                d.version = $('#searchForm input[name="version"]').val();
		                d.fileName = $('#searchForm input[name="fileName"]').val();
		                d.productCategoryId = $('#searchForm select[name="productCategoryId"]').val();
		                d.status = $('#searchForm select[name="status"]').val();
		                
		            }
		        },
		        order: [[0, "desc"]],
		        columns: [
		        	
		        	{ "data": function(data, type, row, meta ) {
		        		return data.productId;
		        	}},
		        	
		        	{ "data": "productCategory.name" },
		        	{ "data": "name" },
		        	{ "data": function(data, type, row, meta) {
		        		var num = data.type;
		        		if(num == 1) {
		        			return 'FILE';
		        		} else if(num == 2){
		        			return 'CONFIG';
		        		} else {
		        			return '';
		        		}
		        	}},
		        	{ "data": "version" },
		        	{ "data": "fileName" },
		        	{ "data": function(data, type, row, meta) {
		        		var des = data.description;
		        		if(des && des.length >20) {
		        			des = des.substring(0, 20) + "...";
		        		}
		        		return des;
		        	}},
		        	{ "data": "status" },
		        	{ "data": "addDate" }
		        	
		        ],
		        columnDefs: [
		        	{ orderable: false, targets: [8] }
		        	 ,{
				            "targets": 7,
				            "data": "status",
				            "render": function (data, type, row) {
				            	return toStatusStr(data);
				          	}
			        	}
	        	 ,{
			            "targets": 9,
			            "data": "productId",
			            "render": function (data, type, row) {
			            	return '<a href="#" onclick="javascript:downloadModal(\'' + data + '\');"><i class="fa fa-fw fa-file-o text-red"></i></a>'+
			            	'<a href="#" onclick="javascript:updModal(\'' + data + '\');"> <i class="fa fa-fw fa-pencil text-yellow"></i></a>' + 
			            	' <a href="#" onclick="javascript:delModal(\'' + data + '\');"><i class="fa fa-fw fa-close text-red"></i></a>';
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
		
		
		function save() {
			var form = $('#pdForm')[0];
			var formData = new FormData(form);
			formData.append('fileObj', $('#file')[0].files[0]);
			
			$.ajax({
				url : '${ctxPath}/api/product/',
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				success : function(result) {
					alert(result.message);
					location.reload();
					
				}
			});
		}
		
		function updModal(productId) {
			//업데이트 모달을 띄우고 기존 정보 입력
			$('#modBtn').val('mod');
			$('h4').text('PRODUCT 수정');
			
			handleGet("/product/" + productId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				$("#productId").val(obj.productId);
				$("#name").val(obj.name);
				$('#type').val(obj.type);
				$("#version").val(obj.version);
				$('#pdcNameModal').val(obj.productCategory.productCategoryId);
				$("#file").val(obj.file);
				$("#description").val(obj.description);
				$("#statusModal").val(obj.status);
				
				$("#product_modal").modal();
			});
		}
		
		//저장 클릭시 업데이트
		function update() {
			var productId = $("#productId").val();
			var form = $('#pdForm')[0];
			var formData = new FormData(form);
			formData.append('fileObj', $('#file')[0].files[0]);

			$.ajax({
				url : '${ctxPath}/api/product/' + productId,
				contentType: "application/x-www-form-urlencoded; charset=UTF-8",
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				success : function(result) {
					alert(result.message);
					location.reload();
					
				},
				error: function(xhr, status, error) {
					console.log(xhr);
				}
			});
		}
		
		
		function del() {
			var productId = $('#deleteIdOne').val();
			handleDelete("/product/" + productId, "", function(result) {
				alert(result.message);
				location.reload();
			}, function() {
				alert(result.message);
				location.reload();
			});
		}
		
		
		//downloadHistory 저장
		function downloadModal(objectId) {
			$("#downloadModal").on('hidden.bs.modal', function (e) { 
			    $("#downloadModal").find('form')[0].reset();
			    $('h4').text('DOWNLOAD HISTORY 작성');
			});
			
			//productId, product.name, userId 가져와서 모달에 뿌려
			handleGet("/product/" + objectId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				$("#downProductId").val(obj.productId);
				$('#downPdcName').val(obj.productCategory.productCategoryId);
				$("#downName").val(obj.name);
				$("#downType").val(obj.type);
				$("#downVersion").val(obj.version);
				$("#downFile").val(obj.fileName);
				
				$("#downloadModal").modal("show");
			});
		}
		
		
		//DownloadHistory저장 후 파일 다운로드
 		function filedownload() {
 			var obj = {
 				purpose: $('#downPurpose').val(),
 				description: $('#downDescription').val()
			};
 			obj.product = {};
 			obj.product.productId = $("#downProductId").val();
			
 			handlePost("/product/downloadHistory/" + $("#downProductId").val(), obj, function(result) {
 				console.log("download" + result);
 				location.href = '${ctxPath}/api/product/download/' + result.object.ticket;
 				$("#downloadModal").modal("hide");
 			}, function(result) {
 				console.log(result);
 			});
 			
 		}
		
		
		function nullCheck() {
			var name = $('#name').val();
			var version = $('#version').val();
			var file = $('#file').val();
			if(name == null || name == ''){
				alert('name cannot be null');
				nullResult = false;
			} else if(version == null || version == ''){
				alert('version cannot be null');
				nullResult = false;
			} else if(file == null || file == ''){
				alert('file cannot be null');
				nullResult = false;
			} else {
				nullResult = true;
			}
		}

	</script>
</body>
</html>
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
										<label for="">userId</label> <input type="text" name="userId" class="form-control" placeholder="id">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">name</label> <input type="text" name="name" class="form-control" placeholder="userName">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">dept</label> <input type="text" name="dept" class="form-control" placeholder="dept">
									</div>
								</div>
								<div class="col-md-3">
									<div class="form-group">
										<label for="">type</label> 
<!-- 										<input type="text" name="type" class="form-control" placeholder="type"> -->
										<select class="form-control" name="type">
											<option value="-1">선택</option>
											<option value="0">일반</option>
											<option value="1">관리자</option>
										</select>
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
								<span class="pull-right"><button type="button" class="btn btn-primary" id="addModalBtn">사용자 추가</button></span>
								<table class="table table-bordered table-striped dataTable responsive" width="100%" id="listTable">
									<thead>
										<tr>
											<th>#</th>
											<th>userId</th>
											<th>name</th>
											<th>dept</th>
											<th>type</th>
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
	
	<%@ include file="/WEB-INF/views/user/userModal.jsp"%>
	
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	
	<script>
		var sortColList = ['', 'USERID', 'NAME', 'DEPT', 'TYPE', 'DESCRIPTION', 'STATUS', 'ADDDATE' ];
		var userIdCheck = false;
		var idValidation = false;
		var passwordCheck = false;
		
		$(document).ready(function() {
			setApiUrl("${ctxPath}/api");
			
			$("#userListMenu").addClass("active");
			
			search();
			
			$("#addModalBtn").on('click', function(e) {
				$("#modal-default").on('hidden.bs.modal', function (e) { 
				    $("#modal-default").find('form')[0].reset();
				    
				}); //모달 청소

				$("#modal-default").modal();
			});
			
			$("#saveBtn").on('click', function(e) {
				if($('#modBtn').val() == 'mod') {
					update();
				} else {
					save();
				}
			});
			
			$('#userId').on('keyup', function() {
				userIdCheck = false;
				valCheck();
			});
			
			
			$("#idCheckBtn").on('click', function() {
				idCheck();
			});
			
			$('#password').on('change', function() {
				var passwordCheck = false;
				alert("password 확인을 해주세요");
			});
			
			$('#password-ch').on('change', function() {
				if(passwordCheck){
					var passwordCheck = false;
					alert("password 확인을 해주세요");
				}
			});
			
			$('#pwdCheckBtn').on('click', function() {
				pwCheck();
			});
			
		});//document 끝
		
		function idCheck() {
			var userId = $('#userId').val();
			
			if(userId == null || userId == ""){
				alert("userId를 입력하세요");
			} else if(!idValidation) {
				alert('userId는 영문+숫자만 가능하며 첫번째 문자는 영문이어야 합니다.(5자리 ~10자리)');
			} else {
				handleGet("/user/" + userId, "", function(result) {
					console.log("success");

					if(result.code == 404) {
						userIdCheck = true;
						alert("사용가능");
					} else {
						userIdCheck = false;
						alert("아이디 중복");
					}
				}, function(result) {
					console.log(result);
				});
			}
		}
		
		function valCheck() {
			var inputId = $('#userId').val();
			if(inputId == null || inputId == '') {
				alert('userId를 입력하세요');
			} else {
// 				var regex = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,}$/;
				var regex = /^[a-zA-Z]{1}[a-zA-Z0-9]{4,10}$/;
				if(!regex.test(inputId)) {
					idValidation = false;
					$('#valDiv').empty();
					$('#valDiv').append('영문+숫자만 가능하며 첫번째 문자는 영문이어야 합니다.(5자리 ~10자리)');
				} else {
					idValidation = true;
					$('#valDiv').empty();
				}
			}
		}
		
		function pwCheck() {
			var pwd = $('#password').val();
			var pwdCh = $('#password-ch').val();
			
			if(pwd == null || pwd =="" || pwdCh == null || pwdCh == "") {
				alert("비밀번호를 입력하세요");
			} else {
				if(pwdCh != pwd) {
					passwordCheck = false;
					alert('비밀번호가 일치하지 않습니다');
					$('#password-ch').focus();
				} else {
					passwordCheck = true;
					alert('비밀번호 일치 확인');
					$('#dept').focus();
				}
			
			}
		}
		
		function save() {
			if(!userIdCheck) {
				alert('userId 중복검사를 하세요1');
			} else if(!idValidation) {
				alert('userId는 영문+숫자만 가능하며 첫번째 문자는 영문이어야 합니다.(5자리 ~10자리)');
			} else if(!passwordCheck) {
				alert("비밀번호를 확인하세요");
			} else {
				var obj = makeObj();
				handlePost("/user/" + obj.userId, obj, function(result) {
					alert(result.message);
					location.reload();	
				}, function(){});
			}
		}
		
		function search() {
			$('#listTable').DataTable( {
				filter: false,
				//info: false,
				lengthChange: true,
		        serverSide: true,
		        bPaginate: true,
		        
		        responsive: true,
		        //lengthMenu : [[10, 25, 50, -1], [10, 25, 50, "all"]] ,
		        lengthMenu : [10, 25, 50] ,
		        ajax: {
		            url: "${ctxPath}/api/user/list_with_count",
		            
		            type: "get",
		            data: function ( d ) {
		            	/* sort field */
		            	for (var i = 0; i < d.order.length; i++) {
		        			columnIndex = d.order[i].column;
		        			d.orderDir = d.order[i].dir;
		        			d.orderColumn = sortColList[columnIndex];
		        	    }
		            	
		            	/* search field */
		                d.userId = $('#searchForm input[name="userId"]').val();
		                d.name = $('#searchForm input[name="name"]').val();
		                d.dept = $('#searchForm input[name="dept"]').val();
		                d.type = $('#searchForm select[name="type"]').val();
		            }
		        },
		        order: [[1, "desc"]],
		        columns: [
		        	{ "data": function(data, type, row, meta ) {
		        		return meta.row + 1;
		        	}},
		        	{ "data": "userId" },
		        	{ "data": "name" },
		        	{ "data": "dept" },
		        	{ "data": function (data, type, row, meta) {
		        		var num = data.type;
		        		if(num == 0) {
		        			return '일반';
		        		} else if(num == 1) {
		        			return '관리자';
		        		} else {
		        			return '';
		        		}
		        	}},
		        	{ "data": "description" },
		        	{ "data": "status" },
		        	{ "data": "addDate" }
		        	
		        ],
		        columnDefs: [
		        	{ orderable: false, targets: [0, 8] }
		        	 ,{
				            "targets": 6,
				            "data": "status",
				            "render": function (data, type, row) {
				            	return toStatusStr(data);
				          	}
			        	}
	        	 ,{
			            "targets": 8,
			            "data": "userId",
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
		
		function updModal(objectId) {
			//업데이트 모달을 띄우고 기존 정보 입력
			$('#modBtn').val('mod');
			$('h4').text('사용자 수정');
			
			handleGet("/user/" + objectId, "", function(result) {
				var obj = result.object;
				//TODO: Son	
				if($('#modBtn').val() == 'mod') {
					
					$("#userId").val(obj.userId);
					$("#userId").attr('readonly', true);
					$("#name").val(obj.name);
					$("#password").val(obj.password);//비번도 가져와야해
					$("#dept").val(obj.dept);
	 				$("#type").val(obj.type);
	 				$('#status').val(obj.status);
					$("#description").val(obj.description);
					
					$("#modal-default").modal();
				}
			});
		}
		
		function makeObj() {
			var obj = {
				userId: $("#userId").val(),
				name: $("#name").val(),
				password: $('#password').val(),
				dept: $("#dept").val(),
				type: $('#type').val(),
				status: $('#status').val(),
				description: $('#description').val()
			};
			
			return obj;
		}
		
	
		//저장 클릭시 업데이트
		function update() {
			var obj = makeObj();
			handlePatch("/user/" + obj.userId, obj, function(result) {
				alert(result.message);
				location.reload();
			});
		}
		
		
		function del() {
			var userId = $('#deleteIdOne').val();
			handleDelete("/user/" + userId, "", function(result) {
				alert(result.message);
				location.reload();
			});
		}
		
		
		
	

	</script>
</body>
</html>
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
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">${pageHeaderName} List</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
			<div class="row">
			
				<div class="error-page">
		        <h2 class="headline text-red"> 500</h2>
		
		        <div class="error-content">
		        <div class="row" style="height: 22px;">
					<div class="col-md-12" ></div>
				</div>
		          <h3><i class="fa fa-warning text-red"></i> Oops! Page not found.</h3>
		
		          <p>
		            We will work on fixing that right away.
		            <br>
         			Meanwhile, you may <a href="${ctxPath}/views/main/main">return to dashboard</a>.
		          </p>
		
		          <form class="search-form">
		            <!-- /.input-group -->
		          </form>
		        </div>
		        <!-- /.error-content -->
		      </div>
		     </div>
		      <!-- /.error-page -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
	</div>
	<%@ include file="/WEB-INF/views/inc/footer.jsp"%>
	
	<%@ include file="/WEB-INF/views/downloadHistory/historyModal.jsp"%>
	
	<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
	<!-- DataTables -->
	<script src="${ctxPath}/resources/js/plugins/datatables/jquery.dataTables.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/dataTables.bootstrap.min.js"></script>
	<script src="${ctxPath}/resources/js/plugins/datatables/extensions/Responsive/js/dataTables.responsive.js"></script>
	<script src="${ctxPath}/resources/js/plugins/daterangepicker/daterangepicker.js"></script>
	<!-- bootstrap datepicker -->
	<script src="${ctxPath}/resources/js/plugins/datepicker/bootstrap-datepicker.js"></script>
	
</body>
</html>
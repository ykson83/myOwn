 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <header class="main-header">
    <!-- Logo -->
    <a href="<%= request.getContextPath() %>" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>ProdManage</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Product</b>Manage</span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="${ctxPath}/resources/images/admin.jpg" class="user-image" alt="User Image">
              <span><sec:authentication property="principal.username"/></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="${ctxPath}/resources/images/admin.jpg" class="img-circle" alt="User Image">
                <p>
                  <%-- <sec:authentication property="principal.adminVO.name"/> 
                  <small>로그인시간: <sec:authentication property="principal.adminVO.lastLoginTime"/></small> --%>
                </p>
              </li>
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <!-- <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div> -->
                <div class="pull-right">
                  <a href="${ctxPath}/logout" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>

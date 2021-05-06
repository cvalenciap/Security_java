<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/bootstrap.css"> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/seguridad.css">


<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/jquery.dataTables.min.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/seguridad.js"></script>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
 <!--[if lt IE 9]>
   <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
 <![endif]-->

    <title>Simple Sidebar - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->

    <!-- Custom CSS -->
    <link href="css/simple-sidebar.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<style type="text/css">
body {
    overflow-x: hidden;
 }
 
 .footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  /* Set the fixed height of the footer here */
  height: 40px;
  background-color: #f5f5f5;
}

/* Toggle Styles */

#wrapper {
	
    padding-left: 0;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled {
    padding-left: 250px;
}

#sidebar-wrapper {
    z-index: 1000;
    position: fixed;
    left: 250px;
    width: 0;
    height: 100%;
    margin-left: -250px;
    overflow-y: auto;
    background: #f8f8f8;
    -webkit-transition: all 0.5s ease;
    -moz-transition: all 0.5s ease;
    -o-transition: all 0.5s ease;
    transition: all 0.5s ease;
}

#wrapper.toggled #sidebar-wrapper {
    width: 250px;
}

#page-content-wrapper {
    width: 100%;
    position: absolute;
    padding: 15px;
}

#wrapper.toggled #page-content-wrapper {
    position: absolute;
    margin-right: -250px;
}

/* Sidebar Styles */

.sidebar-nav {
    position: absolute;
    top: 0;
    width: 250px;
    margin: 0;
    padding: 0;
    list-style: none;
}

.sidebar-nav li {
    text-indent: 20px;
    line-height: 40px;
}

.sidebar-nav li a {
    display: block;
    text-decoration: none;
    color: #000;
}

.sidebar-nav li a:hover {
    text-decoration: none;
    color: #0668a4;
    background: rgba(36, 201, 232,0.2);
}

.sidebar-nav li a:active,
.sidebar-nav li a:focus {
    text-decoration: none;
}

.sidebar-nav > .sidebar-brand {
    height: 65px;
    font-size: 18px;
    line-height: 60px;
}

.sidebar-nav > .sidebar-brand a {
    color: #999999;
}

.sidebar-nav > .sidebar-brand a:hover {
    color: #fff;
    background: none;
}

@media(min-width:768px) {
    #wrapper {
        padding-left: 250px;
    }

    #wrapper.toggled {
        padding-left: 0;
    }

    #sidebar-wrapper {
        width: 250px;
    }

    #wrapper.toggled #sidebar-wrapper {
        width: 0;
    }

    #page-content-wrapper {
        padding: 20px;
        position: relative;
    }

    #wrapper.toggled #page-content-wrapper {
        position: relative;
        margin-right: 0;
    }
}

</style>
<body>
	<div id="divHeader" class="bt-header">
	<div class="row mtrbl0" style="background: #d9edf7;">
			<div class="col-sm-3"></div>
			<div class="col-sm-6 text-center">
				<p>Ultimo acceso ${serverTime}</p>
			</div>
			<div class="col-sm-3">
					<c:if test="${pageContext.request.userPrincipal.name != null}">
				        Bienvenido
				           <a href="${pageContext.request.contextPath}/accountInfo">
								${pageContext.request.userPrincipal.name} </a>
				         &nbsp;|&nbsp;
				           <a type="button" class="btn btn-default btn-xs c-bot01" href="${pageContext.request.contextPath}/logout">
				           		Cerrar <span class="glyphicon glyphicon-remove " aria-hidden="true"></span>				           		
				           	</a>
						</c:if>
						<c:if test="${pageContext.request.userPrincipal.name == null}">
							<a href="${pageContext.request.contextPath}/login">Login
							</a>
						</c:if>				
			</div>					
	</div>
</div>
<div class="navbar navbar-default">
	<div class="container-fluid">
		<div class="col-lg-12">
			<div class="clearfix content-heading">
				<a class="pull-left" href="${pageContext.request.contextPath}/menu" id="inicio" ><img src="${pageContext.request.contextPath}/resources/core/images/company_logo.gif"/></a>
				<h2>&nbsp;Control de Seguridad</h2>
			</div>
		</div>
	</div>
</div>
    <div id="wrapper">
    
        <!-- Sidebar -->
        <div id="sidebar-wrapper">
            <c:import url="/WEB-INF/views/jsp/fragments/menu.jsp"/>
        </div>
        <!-- /#sidebar-wrapper -->

        <!-- Page Content -->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Page</h1>
                        <p>Data contenedor de Pages.</p>
						<p>Data contenedor de Pages.</p>
						<p>Data contenedor de Pages.</p>
						<p>Data contenedor de Pages.</p>
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>
                        
                        <button class="btn btn-default"
											onclick="location.href='${verUrl}'">
											<i class="glyphicon glyphicon-eye-open"></i>
											</button>
											
						<button class="btn btn-primary"
											onclick="location.href='${verUrl}'">
											<i class="glyphicon glyphicon-floppy-save"></i>
											&nbsp;Grabar&nbsp;
											</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->

    </div>
    <!-- /#wrapper -->
	<c:import url="../fragments/footer.jsp"/>

    <!-- Menu Toggle Script -->
    
</body>

</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sed" uri="/WEB-INF/seguridad.tld"%>
<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, shrink-to-fit=no, initial-scale=1">
<jsp:include page="fragments/header.jsp" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/seguridad.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/standard.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/jquery-confirm.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/core/css/datatable/1.10.12/jquery.dataTables.min.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/datatable/1.10.12/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/seguridad.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/jquery-confirm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/tableHeadFixer.js"></script>


<style type="text/css">
.dataTables_filter {
	float: left !important;
}

th, td {
	font-size: 12px;
	white-space: nowrap;
}

span:hover {
	cursor: hand;
}
</style>

</head>
<body>
	<c:import url="../fragments/header.jsp" />
	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<c:import url="../fragments/menu.jsp" />
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1>Pagina de Error</h1>
						<h2>El usuario ${username} no tiene acceso</h2>
						<p>${exception.message}</p>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<c:import url="../fragments/loader.jsp" />
	<c:import url="../fragments/footer.jsp" />



</body>
</html>
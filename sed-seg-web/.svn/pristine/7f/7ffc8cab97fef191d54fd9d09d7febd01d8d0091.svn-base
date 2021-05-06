<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">

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
<body>

	<div class="container">

		<h1>Error 403</h1>
		<h2>El usuario ${username} no tiene acceso</h2>
		<p>${exception.message}</p>
		<!-- Exception: ${exception.message}.
		  	<c:forEach items="${exception.stackTrace}" var="stackTrace"> 
				${stackTrace} 
			</c:forEach>
	  	-->

	</div>

	<jsp:include page="fragments/footer.jsp" />

</body>
</html>
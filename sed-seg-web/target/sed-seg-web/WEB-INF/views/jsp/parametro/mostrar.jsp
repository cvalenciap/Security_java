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


<c:import url="../fragments/link.jsp" />
<style type="text/css">
input[type=text] {
	text-transform: uppercase;
}

textarea {
	text-transform: uppercase;
}
</style>
<script type="text/javascript">
	/* function alphaOnly(e) {
		var code;
		if (!e)
			var e = window.event;
		if (e.keyCode)
			code = e.keyCode;
		else if (e.which)
			code = e.which;
		if ((code >= 48) && (code <= 57)) {
			return false;
		}
		return true;
	} */
</script>
<style type="text/css">
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

						<c:if test="${not empty msg}">
							<div class="alert alert-${css} alert-dismissible" role="alert">
								<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<!-- <span aria-hidden="true">&times;</span> -->
								</button>
								<strong>${msg}</strong>
							</div>
						</c:if>



						<h3>
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;Información de Parametro
						</h3>

						<br />

						<spring:url value="/parametro" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="parametroForm" action="${userActionUrl}">



							<div class="row">
								<label class="col-sm-2 control-label">Cod. Param.(*)</label>
								<div class="col-sm-6 ">
									<p class="form-control">${parametroForm.codParametro}</p>
								</div>
								<div class="col-sm-4"></div>
							</div>
							<div class="row">
								<label class="col-sm-2 control-label">Código(*)</label>
								<div class="col-sm-6 ">
									<p class="form-control">${parametroForm.codigo}</p>
								</div>
								<div class="col-sm-4"></div>
							</div>

							<spring:bind path="descripcion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Descripción(*)</label>
									<div class="col-sm-6">
										<form:input path="descripcion" rows="2" maxlength="40"
											class="form-control" id="txtdescripcion" readonly="true"
											placeholder="Descripción" />
										<form:errors path="descripcion" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							<spring:bind path="valor">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Valor(*)</label>
									<div class="col-sm-6">
										<form:input path="valor" rows="2" maxlength="100"
											class="form-control" id="txtvalor" readonly="true"
											placeholder="Valor" />
										<form:errors path="valor" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>


							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
									</div>
								</div>
							</div>

						</form:form>

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
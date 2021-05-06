<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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

textarea{
    text-transform: uppercase;
}
</style>
</head>
<body>
	<c:import url="../fragments/header.jsp" />

	<div id="wrapper">
		<!-- Sidebar -->
		<!-- <div id="sidebar-wrapper">

		</div> -->
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

						<div class="panel-heading">
							<div class="panel-title">Restablecer contraseña</div>
						</div>
						<spring:url value="/olvido" var="userActionUrl" />
						<spring:url value="/" var="retornarUrl" />
						<div class="panel-body">
							<form:form class="form-horizontal" method="post"
								modelAttribute="claveForm" action="${userActionUrl}">


								<div class="form-group ${status.error ? 'has-error' : ''}">
									<spring:bind path="codUsuario">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Usuario(*)</label>
											<div class="col-sm-3">
												<form:input path="codUsuario" size="15" maxlength="15"
													class="form-control" id="descripcion"
													placeholder="Nombre de Usuario"
													 />
												<form:errors path="codUsuario" class="control-label" />
											</div>
											
											<button type="submit" class="btn btn-primary">
												<i class="glyphicon glyphicon-ok"></i> &nbsp;Enviar
											</button>
											<a class="btn btn-primary" href="${retornarUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										</div>
									</spring:bind>

								</div>
							</form:form>

						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<c:import url="../fragments/loader.jsp" />
	<c:import url="../fragments/footer.jsp" />

</body>
</html>
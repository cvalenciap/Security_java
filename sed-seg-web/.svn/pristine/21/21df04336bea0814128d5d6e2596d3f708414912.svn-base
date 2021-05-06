<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, shrink-to-fit=no, initial-scale=1">

<c:import url="../fragments/link.jsp" />

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

						<h2>Cambiar Clave</h2>
						<br />
						<spring:url value="/clave" var="userActionUrl" />
						<spring:url value="/menu" var="retornarUrl" />
						<c:if test="${usuarioBean.flagCambiarClave == 1}">				        	
				        	<spring:url value="/logout" var="retornarUrl" />
						</c:if>
						
						
						<form:form class="form-horizontal" method="post"
							modelAttribute="claveForm" action="${userActionUrl}">

							<spring:bind path="pass">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Clave actual(*)</label>
									<div class="col-sm-6">
										<form:password path="pass" class="form-control" id="pass"
											placeholder="Clave" />
										<form:errors path="pass" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							<spring:bind path="passNuevo">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nueva clave(*)</label>
									<div class="col-sm-6">
										<form:password path="passNuevo" class="form-control"
											id="passnuevo" placeholder="Clave nueva" />
										<form:errors path="passNuevo" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							<spring:bind path="passConfirmar">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Confirmar
										clave(*)</label>
									<div class="col-sm-6">
										<form:password path="passConfirmar" class="form-control"
											id="passconfirmar" placeholder="Clave confirmar" />
										<form:errors path="passConfirmar" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>
							<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="submit" class="btn btn-primary">
											<i class="glyphicon glyphicon-ok"></i> &nbsp;Enviar
										</button>
										<a class="btn btn-primary" href="${retornarUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
									</div>

								</div>
						</form:form>

					</div>
				</div>
			</div>
		</div>

	</div>

	<%-- <div class="container">
		<div class="row">
			<h2 >Cambiar Password</h1>
			<form>
				<br /> <label class="col-sm-2" >Password</label>
				<span class="col-sm-5"><input class="form-control"
					id="password" name="newPassword" type="password" value="" /></span>
				<div class="col-sm-12"></div>
				<br />
				<br /> <label class="col-sm-2" ">Confirmar</label>
				<span class="col-sm-5"><input class="form-control"
					id="matchPassword" type="password" value="" /></span>
				<div class="col-sm-12">
					<br />
					<br />
					<button type="submit" class="btn btn-primary">
					<i class="glyphicon glyphicon-ok"></i>
					&nbsp;Enviar</button>
				</div>
			</form>

		</div>
	</div>
	 --%>
	<c:import url="../fragments/loader.jsp" />
	<c:import url="../fragments/footer.jsp" />

</body>
</html>
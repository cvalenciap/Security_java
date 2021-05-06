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
span:hover {
	cursor: hand;
}

input[type=text] {
	text-transform: uppercase;
}

textarea {
	text-transform: uppercase;
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

						<c:choose>
							<c:when test="${moduloForm['new']}">
								<c:set var="titulo" value="Agregar Módulo" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Módulo" />
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${moduloForm['readOnly']}">
								<c:set var="readonly" value="true" />
							</c:when>
							<c:otherwise>
								<c:set var="readonly" value="false" />
							</c:otherwise>
						</c:choose>


						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							${titulo}
						</h3>

						<br />

						<spring:url value="/modulo" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="moduloForm" action="${userActionUrl}">

							<form:hidden path="codModulo" />

							<c:choose>
								<c:when test="${moduloForm['new']}">
									<spring:bind path="codSistema">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Sistema(*)</label>
											<div class="col-sm-6">
												<form:select id="selectSistema" path="codSistema"
													class="form-control">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${sistemas}" />
												</form:select>
												<form:errors path="codSistema" class="control-label" />
											</div>
											<div class="col-sm-4"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<form:hidden path="codSistema" />
									<form:hidden path="sistemaNombre" />
									<div class="row">
										<label class="col-sm-2 control-label">Sistema(*)</label>
										<div class="col-sm-6">
											<p class="form-control">${moduloForm.codSistema}-
												${moduloForm.sistemaNombre}</p>
										</div>
										<div class="col-sm-4"></div>
									</div>
									<div class="row">
										<label class="col-sm-2 control-label">Cod. Modulo(*)</label>
										<div class="col-sm-2">
											<p class="form-control">${moduloForm.codModulo}</p>
										</div>
										<div class="col-sm-8"></div>
									</div>
								</c:otherwise>
							</c:choose>

							<spring:bind path="descripcion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Descripción(*)</label>
									<div class="col-sm-6">
										<form:input path="descripcion" rows="2" maxlength="40"
											class="form-control" id="txtdescripcion"
											placeholder="Descripción"
											onkeypress="return alphaOnly(event);" readonly="${readonly}" />
										<form:errors path="descripcion" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							
							<c:choose>
								<c:when test="${moduloForm['new']}">
									<spring:bind path="estado">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Estado(*)</label>
									<div class="col-sm-2">
										<form:select path="estado" disabled="true" class="form-control">
											<form:options items="${estados}" />
										</form:select>
										<form:errors path="estado" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="estado">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Estado(*)</label>
									<div class="col-sm-2">
										<form:select path="estado" class="form-control">
											<form:options items="${estados}" />
										</form:select>
										<form:errors path="estado" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>
								</c:otherwise>
							</c:choose>

							

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<%-- <a class="btn btn-primary" href="${userActionUrl}">
										<i class="glyphicon glyphicon-remove-sign"></i>
										Cancelar</a> --%>
										<c:choose>
											<c:when test="${moduloForm['new']}">
												<button id="botonGrabar" type="submit"
													class="btn btn-primary">
													<i class="glyphicon glyphicon-floppy-disk"></i>
													&nbsp;Grabar
												</button>
											</c:when>
											<c:otherwise>
												<button id="botonActualizar" type="submit"
													class="btn btn-primary">
													<i class="glyphicon glyphicon-floppy-disk"></i>
													&nbsp;Actualizar
												</button>
											</c:otherwise>
										</c:choose>
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
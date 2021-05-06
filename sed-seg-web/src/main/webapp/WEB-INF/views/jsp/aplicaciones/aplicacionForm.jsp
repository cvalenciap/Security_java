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

<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
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
							<c:when test="${aplicacionForm['new']}">
								<c:set var="titulo" value="Agregar Sistema" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Sistema" />
							</c:otherwise>
						</c:choose>

						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							${titulo}
						</h3>
						<br />

						<spring:url value="/aplicaciones" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="aplicacionForm" action="${userActionUrl}">

							<form:hidden path="codSistema" />

							<c:choose>
								<c:when test="${aplicacionForm['new']}">
								</c:when>
								<c:otherwise>
									<div class="row">
										<label class="col-sm-2 control-label">Código(*)</label>
										<div class="col-sm-2 ">
											<p class="form-control">${aplicacionForm.codSistema}</p>
										</div>
										<div class="col-sm-8"></div>
									</div>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${aplicacionForm['readOnly']}">
									<c:set var="readonly" value="true" />
								</c:when>
								<c:otherwise>
									<c:set var="readonly" value="false" />
								</c:otherwise>
							</c:choose>

							<spring:bind path="descripcion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Nombre(*)</label>
									<div class="col-sm-7">
										<form:input path="descripcion" rows="2" maxlength="40"
											class="form-control" id="descripcion"
											placeholder="Descripción"
											onkeypress="return alphaOnly(event);" readonly="${readonly}" />
										<form:errors path="descripcion" class="control-label" />
									</div>
									<div class="col-sm-3"></div>
								</div>
							</spring:bind>

							<spring:bind path="abreviatura">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Abreviatura(*)</label>
									<div class="col-sm-3">
										<form:input path="abreviatura" type="text" maxlength="7"
											class="form-control" id="abreviatura"
											placeholder="Abreviatura" readonly="${readonly}"
											onkeypress="return alphaOnly(event);" />
										<form:errors path="abreviatura" class="control-label" />
									</div>
									<div class="col-sm-7"></div>
								</div>
							</spring:bind>




							<spring:bind path="programa">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Programa(*)</label>
									<div class="col-sm-7">
										<form:input path="programa" type="text" maxlength="50"
											class="form-control" id="programa"
											onkeypress="return alphaOnly(event);" placeholder="Programa"
											readonly="${readonly}" />
										<form:errors path="programa" class="control-label" />
									</div>
									<div class="col-sm-3"></div>
								</div>
							</spring:bind>

							<c:choose>
								<c:when test="${aplicacionForm['readOnly']}">

									<spring:bind path="nivelNombre">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Nivel(*)</label>
											<div class="col-sm-3">
												<form:input path="nivelNombre" type="text" maxlength="50"
													class="form-control" id="nivelNombre"
													placeholder="nivel Nombre" readonly="${readonly}" />
											</div>
											<div class="col-sm-7"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="flagNivel">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Nivel(*)</label>
											<div class="col-sm-3">
												<form:select path="flagNivel" class="form-control">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${niveles}" />
												</form:select>
												<form:errors path="flagNivel" class="control-label" />
											</div>
											<div class="col-sm-7"></div>

										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>

<!-- 							inicio adecuacion seguridad2 -->
							<spring:bind path="autenticacion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Autenticación(*)</label>
									<div class="col-sm-3">
										<label class="col-sm-6">
								            <form:radiobutton path="autenticacion" value="0" /> Seguridad
								        </label>
								        <label class="col-sm-6">
								            <form:radiobutton path="autenticacion" value="1" /> LDAP
								        </label>
									</div>
									<div class="col-sm-7"></div>
								</div>
							</spring:bind>
<!-- 							fin adecuacion seguridad2 -->

							<c:choose>
								<c:when test="${aplicacionForm['new']}">
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Estado(*)</label>
											<div class="col-sm-3">
												<form:select path="estado" class="form-control"
													disabled="true">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-7"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Estado(*)</label>
											<div class="col-sm-3">
												<form:select path="estado" class="form-control">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-7"></div>
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
										<c:choose>
											<c:when test="${aplicacionForm['new']}">
												<button type="submit" id="botonGrabar"
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

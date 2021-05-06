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
								</button>
								<strong>${msg}</strong>
							</div>
						</c:if>

						<c:choose>
							<c:when test="${contratistaForm['new']}">
								<c:set var="titulo" value="Agregar Contratista" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Contratista" />
							</c:otherwise>
						</c:choose>

						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							${titulo}
						</h3>
						<br />

						<spring:url value="/contratista" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="contratistaForm" action="${userActionUrl}">

							<form:hidden path="idContratista" />

							<c:choose>
								<c:when test="${contratistaForm['new']}">
								</c:when>
								<c:otherwise>
									<div class="row">
										<label class="col-sm-3 control-label">Código(*)</label>
										<div class="col-sm-8 ">
											<p class="form-control">${contratistaForm.idContratista}</p>
										</div>
										<div class="col-sm-1"></div>
									</div>
								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${contratistaForm['readOnly']}">
									<c:set var="readonly" value="true" />
								</c:when>
								<c:otherwise>
									<c:set var="readonly" value="false" />
								</c:otherwise>
							</c:choose>
							
							<spring:bind path="desRazon">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Razon Social(*)</label>
									<div class="col-sm-8">
										<form:input path="desRazon" rows="2" maxlength="200"
											class="form-control" id="desRazon"
											placeholder="RAZÓN SOCIAL" readonly="${readonly}" />
										<form:errors path="desRazon" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<spring:bind path="desRazonCorta">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Abreviatura(*)</label>
									<div class="col-sm-8">
										<form:input path="desRazonCorta" rows="2" maxlength="30"
											class="form-control" id="desRazonCorta"
											placeholder="RAZÓN SOCIAL ABREVIATURA" readonly="${readonly}" />
										<form:errors path="desRazonCorta" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<spring:bind path="direccion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Dirección(*)</label>
									<div class="col-sm-8">
										<form:input path="direccion" rows="2" maxlength="200"
											class="form-control" id="direccion"
											placeholder="DIRECCIÓN" readonly="${readonly}" />
										<form:errors path="direccion" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<c:choose>
									<c:when test="${contratistaForm['readOnly']}">
										<spring:bind path="codDocRazon">
											<label class="col-sm-3 control-label">Tipo y Nro.Documento(*)</label>
											<div class="col-sm-3">
												<form:input path="codDocRazon" type="text" maxlength="15"
													class="form-control" id="codDocRazon"
													placeholder="TIPO DOCUMENTO" readonly="${readonly}" />
											</div>
										</spring:bind>
									</c:when>
									<c:otherwise>
										<spring:bind path="codDocRazon">
											<label class="col-sm-3 control-label">Tipo y Nro.Documento(*)</label>
											<div class="col-sm-3">
												<form:select path="codDocRazon" class="form-control">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${tiposDocumento}" />
												</form:select>
												<form:errors path="codDocRazon" class="control-label" />
											</div>
										</spring:bind>
									</c:otherwise>
								</c:choose>
								<spring:bind path="nroDocRazon">
									<div class="col-sm-4">
										<form:input path="nroDocRazon" type="text" maxlength="30"
											class="form-control" id="nroDocRazon"
											onkeypress="return checkOnlyDigits(event);" placeholder="NRO. DOCUMENTO"
											readonly="${readonly}" />
										<form:errors path="nroDocRazon" class="control-label" />
									</div>
								</spring:bind>
								<div class="col-sm-2"></div>
							</div>
							
							<spring:bind path="desRepresentante">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Representante(*)</label>
									<div class="col-sm-8">
										<form:input path="desRepresentante" rows="2" maxlength="200"
											class="form-control" id="desRepresentante"
											placeholder="REPRESENTANTE"
											onkeypress="return alphaNumericSpaceOnly(event);" readonly="${readonly}" />
										<form:errors path="desRepresentante" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<c:choose>
									<c:when test="${contratistaForm['readOnly']}">
										<spring:bind path="codDocRepresentante">
											<label class="col-sm-3 control-label">Tipo y Nro.Documento(*)</label>
											<div class="col-sm-3">
												<form:input path="codDocRepresentante" type="text" maxlength="30"
													class="form-control" id="codDocRepresentante"
													placeholder="TIPO DOCUMENTO" readonly="${readonly}" />
											</div>
										</spring:bind>
									</c:when>
									<c:otherwise>
										<spring:bind path="codDocRepresentante">
											<label class="col-sm-3 control-label">Tipo y Nro.Documento(*)</label>
											<div class="col-sm-3">
												<form:select path="codDocRepresentante" class="form-control">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${tiposDocumento}" />
												</form:select>
												<form:errors path="codDocRepresentante" class="control-label" />
											</div>
										</spring:bind>
									</c:otherwise>
								</c:choose>
								<spring:bind path="nroDocRepresentante">
									<div class="col-sm-4">
										<form:input path="nroDocRepresentante" type="text" maxlength="50"
											class="form-control" id="nroDocRepresentante"
											onkeypress="return checkOnlyDigits(event);" placeholder="NRO. DOCUMENTO"
											readonly="${readonly}" />
										<form:errors path="nroDocRepresentante" class="control-label" />
									</div>
								</spring:bind>
								<div class="col-sm-2"></div>
							</div>
							
							<spring:bind path="correo">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Correo Electrónico(*)</label>
									<div class="col-sm-8">
										<form:input path="correo" rows="2" maxlength="100" 
											class="form-control" id="correo"
											placeholder="CORREO ELECTRÓNICO"
											onkeypress="return ValidEmail(event);" readonly="${readonly}" />
										<form:errors path="correo" class="control-label" />
										<span id="lblError" style="color: red"></span>
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<spring:bind path="telefono">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Teléfonos(*)</label>
									<div class="col-sm-8">
										<form:input path="telefono" rows="2" maxlength="100"
											class="form-control" id="telefono"
											placeholder="TELEFONOS"
											onkeypress="return checkOnlyDigits(event);" readonly="${readonly}" />
										<form:errors path="telefono" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>
							
							<c:choose>
								<c:when test="${contratistaForm['new']}">
									<spring:bind path="codEstado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Estado(*)</label>
											<div class="col-sm-3">
												<form:select path="codEstado" class="form-control"
													disabled="true">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="codEstado" class="control-label" />
											</div>
											<div class="col-sm-6"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="codEstado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Estado(*)</label>
											<div class="col-sm-3">
												<form:select path="codEstado" class="form-control">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="codEstado" class="control-label" />
											</div>
											<div class="col-sm-6"></div>
										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>
							
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<c:choose>
											<c:when test="${contratistaForm['new']}">
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
	
	<script  type="text/javascript">

	$("#correo").on("keyup", function() {
		var lblError = document.getElementById("lblError");
		lblError.innerHTML = "";
		if(!ValidEmail($("#correo"))){
			lblError.innerHTML = "Dirección de correo invalido.";
		}
	});

	</script>
</body>
</html>

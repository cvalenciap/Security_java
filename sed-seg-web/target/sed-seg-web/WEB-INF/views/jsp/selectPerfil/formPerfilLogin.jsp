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
	<c:import url="../fragments/headerSelectPerfil.jsp" />
<%-- 	<c:import url="../fragments/header.jsp" /> --%>

	<div id="wrapper">
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
						
						<h2>Seleccionar Perfil de Ingreso</h2>
						<br />
						<spring:url value="/selectPerfil" var="userActionUrl" />
						<spring:url value="/logout" var="retornarUrl" />						
						
						<form:form class="form-horizontal" method="post"
							modelAttribute="perfilForm" action="${userActionUrl}">
							
							<spring:bind path="codPerfil">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Perfiles(*)</label>
									<div class="col-sm-6">
										<form:select id="selectPerfil" path="codPerfil"
 											class="form-control"> --%>
 											<form:option value="-1" label="--- Select ---" /> 
 											<c:forEach var="perfil" items="${perfiles}">
 												<form:option id="${perfil.id}" value="${perfil.value}" 
 													label="${perfil.label}" /> 
 											</c:forEach> 
 										</form:select> 
 										<form:errors path="codSistema" class="control-label" /> 
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>
							
							<spring:bind path="descripcion">
								<form:input type="hidden" path="descripcion" id="descripcion" value="${perfil.value}"/>
							</spring:bind>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">
										<i class="glyphicon glyphicon-ok"></i> &nbsp;Aceptar
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
	<c:import url="../fragments/loader.jsp" />
	<c:import url="../fragments/footer.jsp" />

</body>
</html>
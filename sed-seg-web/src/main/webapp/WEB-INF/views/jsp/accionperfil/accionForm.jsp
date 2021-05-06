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

<script type="text/javascript">
</script>
<style type="text/css">
span:hover {
	cursor: pointer;
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
					</div>
					<div class="col-lg-7">
						<c:choose>
							<c:when test="${accionForm['new']}">
								<c:set var="titulo" value="Agregar Accion Perfil"/>
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Accion Perfil"/>
							</c:otherwise>
						</c:choose>
						
						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							${titulo}
						</h3>
						
						<br />

						<spring:url value="/accionform" var="userActionUrl" />

						<form:form class="form-horizontal" method="post"
							modelAttribute="accionForm" action="${userActionUrl}">

							<form:hidden path="codPerfilAccion" />
							
							<spring:bind path="codSistema">   
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Sistema</label>
									<div class="col-sm-9">
										<form:select id="selectSistema" path="codSistema"
											class="form-control">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${sistemas}" />
										</form:select>
										<form:errors path="codSistema" class="control-label" />
									</div>
									<!-- <div class="col-sm-1"></div> -->
								</div>
							</spring:bind>
									
							<spring:bind path="codModulo">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Modulo</label>
									<div class="col-sm-9">
										<form:select id="selectModulo" path="codModulo"
											class="form-control">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${modulos}" />
										</form:select>
										<form:errors path="codModulo" class="control-label" />
									</div>
									<!-- <div class="col-sm-1"></div> -->
								</div>
							</spring:bind>
							
							<spring:bind path="codPerfil">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Perfil</label>
									<div class="col-sm-7">
										<form:select id="selectPerfil" path="codPerfil"
											class="form-control">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${perfiles}" />
										</form:select>
										<form:errors path="codPerfil" class="control-label" />
									</div>
									<div class="col-sm-2"></div>
								</div>
							</spring:bind>
							
							<div class="row">
								<table
									class="table table-striped table-bordered table-condensed">
									<tr>
										<th></th>
										<th></th>
										<th></th>
										<th></th>
									</tr>
									<%-- <c:forEach var="service" items="${servicesList}">
										<tr id="${service.service_id}">
											<td><c:out value="${service.service_id}" /></td>
											<td><c:out value="${service.service_name}" /></td>
											<td><c:out value="${service.service_price}" /></td>
											<td><input type="checkbox" name="serviceBox"
												value="${service.service_id}" /></td>
										</tr>
									</c:forEach> --%>
								</table>

							</div>
							
							
																			
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}">
										<i class="glyphicon glyphicon-share-alt"></i>
										Retornar</a>
										<c:choose>
											<c:when test="${accionForm['new']}">
												<button id="botonGrabar" type="submit" class="btn btn-primary">
												<i class="glyphicon glyphicon-floppy-disk"></i>
												&nbsp;Grabar</button>
											</c:when>
											<c:otherwise>
												<button id="botonActualizar" type="submit" class="btn btn-primary">
												<i class="glyphicon glyphicon-floppy-disk"></i>
												&nbsp;Actualizar</button>
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
	
	<script type="text/javascript">
	</script>

</body>
</html>
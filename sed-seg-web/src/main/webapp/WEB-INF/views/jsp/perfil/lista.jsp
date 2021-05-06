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
.dataTables_filter {
	float: left !important;
}

.close-image img {
	display: block;
	height: 15px;
	width: 15px;
}
/* .dataTables_length {
     float: right !important;
} */
/* th {
	font-size: 13px;
}

td {
	font-size: 12px;
}
 */
th, td {
	font-size: 12px;
	white-space: nowrap;
}

span:hover {
	cursor: hand;
}

h3 {
	text-decoration: underline;
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
							Lista de Perfiles
							<sed:seguridad accion="/perfilCREAR">
								<button class="btn btn-primary pull-right"
									onclick="location.href='perfil/add'">
									<!--  <i class="glyphicon glyphicon-plus"></i>-->
									&nbsp;&nbsp;Agregar&nbsp;
								</button>
							</sed:seguridad>
						</h3>

						<spring:url value="${pageContext.request.contextPath}/perfil"
							var="userActionUrl" />

						<br />
						<table id="mytable"
							class="table table-striped table-bordered table-hover "
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>SISTEMA DESC.</th>
									<th>COD. PERF.</th>
									<th>DESCRIPCION</th>
									<th>ESTADO</th>
									<th>USUARIO ACT.</th>
									<th>FECHA ACT.</th>
									<th>ACCIONES</th>
								</tr>
							</thead>

							<c:forEach var="perfil" items="${perfiles}">
								<tr>
									<td>${perfil.sistemaNombre}</td>
									<td>${perfil.codPerfil}</td>
									<td>${perfil.descripcion}</td>
									<td>${perfil.estadoNombre}</td>
									<td>${perfil.usuarioModificacion}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy"
											value="${perfil.fechaModificacion}" /></td>
									<td><spring:url
											value="/perfil/${perfil.codPerfil},${perfil.codSistema}"
											var="verUrl" /> <spring:url
											value="/perfil/delete/${perfil.codPerfil},${perfil.codSistema}"
											var="deleteUrl" /> <spring:url
											value="/perfil/update/${perfil.codPerfil},${perfil.codSistema}"
											var="updateUrl" />
									    <sed:seguridad accion="/perfilVISUALIZAR">
										<button id="btnBuscar" class="btn btn-default close-image"
											onclick="location.href='${verUrl}'">
											<!-- <i class="glyphicon glyphicon-eye-open"></i> -->
											<img src="resources/core/images/buscar.png">
										</button>
										</sed:seguridad>
										<sed:seguridad accion="/perfilMODIFICAR">
											<button id="btnGrabar" class="btn btn-default close-image"
												onclick="location.href='${updateUrl}'">
												<img src="resources/core/images/edit-icon.png">
											</button>
										</sed:seguridad>
										<sed:seguridad accion="/perfilELIMINAR">
										 <a onclick="return onEliminar(this);" href="${deleteUrl}">
											<button class="close-image btn btn-default">
												<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
												<img src="resources/core/images/eliminar.png">
											</button>
										</a> 
										</sed:seguridad>
									<!-- <a id="btnDelete" class="btn btn-default close-image"
											href="#">
											<img src="resources/core/images/eliminar.png">
										</a> --> <!-- <span id="btnDelete" class="btn btn-default close-image">
											<img src="resources/core/images/eliminar.png">
										</span> -->
										</td>
								</tr>
							</c:forEach>
						</table>

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
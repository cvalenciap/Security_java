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
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var oTable = $("#mytable")
								.DataTable(
										{
											"dom" : '<"top"f><rt><"bottom"lip><"clear">',
											"language" : {
												"url" : "${pageContext.request.contextPath}/resources/core/languaje/spanish.json",
											},
											"lengthMenu" : [ [ 5, 10, 25, 50 ],
													[ 5, 10, 25, 50 ] ],
											"scrollY" : 500,
											"scrollX" : true,
											"paging" : true,
											"ordering" : true,
											"responsive" : true,
											stateSave: true											
										});

						$(window).bind('resize', function() {
							oTable.fnAdjustColumnSizing();
						});
					});
</script>
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
							&nbsp;&nbsp;&nbsp;&nbsp;Lista de Usuarios
							<sed:seguridad accion="/usuarioCREAR">
								<button class="btn btn-primary pull-right"
									onclick="location.href='usuario/add'">
									<i class="glyphicon glyphicon-plus"></i>
									&nbsp;&nbsp;Agregar&nbsp;
								</button>
							</sed:seguridad>
						</h3>

						<spring:url value="${pageContext.request.contextPath}/usuarios"
							var="userActionUrl" />

						<br />
						<table id="mytable"
							class="table table-striped table-bordered table-hover "
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>Sistema</th>
									<th>Usuario</th>
									<th>Descripción</th>
									<th>Ficha</th>
									<th>Area</th>
									<th>Estado</th>
									<th>Acciones</th>
								</tr>
							</thead>

							<c:forEach var="usuario" items="${usuarios}">
								<tr>
									<td>${usuario.sistemaNombre}</td>
									<td>${usuario.codUsuario}</td>
									<td>${usuario.descripcion}</td>
									<td>${usuario.codFicha}</td>
									<td>${usuario.codArea}</td>
									<td>${usuario.estadoNombre}</td>
									<%-- <td><c:choose>
											<c:when test="${usuario.estado=='1'}">Activo
											</c:when>
											<c:when test="${usuario.estado=='2'}">Bloqueado
											</c:when>
											<c:otherwise>No Activo
											</c:otherwise>
										</c:choose></td> --%>
									<td><spring:url value="/usuario/${usuario.codSistema},${usuario.codUsuario}" var="verUrl" /> 
										<spring:url value="/usuario/delete/${usuario.codSistema},${usuario.codUsuario}" var="deleteUrl" />
										<spring:url value="/usuario/update/${usuario.codSistema},${usuario.codUsuario}" var="updateUrl" /> 
										<spring:url value="/usuario/desbloquear/${usuario.codUsuario}" var="desbloquearUrl" />
										<spring:url value="/usuario/activar/${usuario.codUsuario}" var="activarUrl" />

										<sed:seguridad accion="/usuarioVISUALIZAR">
											<button class="close-image btn btn-default"
												onclick="location.href='${verUrl}'">
												<!-- <i class="glyphicon glyphicon-eye-open"></i> -->
												<img src="resources/core/images/buscar.png">
											</button>
										</sed:seguridad>
										<sed:seguridad accion="/usuarioMODIFICAR">
											<button class="close-image btn btn-default"
												onclick="location.href='${updateUrl}'">
												<img src="resources/core/images/edit-icon.png">
											</button> 
										</sed:seguridad>
										<sed:seguridad accion="/usuarioELIMINAR">
										<a id="botonEliminar" href="${deleteUrl}">
											<button class="close-image btn btn-default">
												<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
												<img src="resources/core/images/eliminar.png">
											</button>
										</a> 
										</sed:seguridad>
									<c:choose>
											<c:when test="${usuario.estado == 2}">

												<a id="botonDesbloquear" href="${desbloquearUrl}">
													<button class="close-image btn btn-default">
														<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
														<img src="resources/core/images/blocked.jpg">
													</button>
												</a>

											</c:when>
											<c:when test="${usuario.estado == 1}">
												<button class="close-image btn btn-default" disabled="true">
													<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
													<img src="resources/core/images/active.jpg">
												</button>
											</c:when>
											<c:when test="${usuario.estado == 0}">

												<a id="botonActivar" href="${activarUrl}">
													<button class="close-image btn btn-default">
														<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
														<img src="resources/core/images/disabled.png">
													</button>
												</a>


											</c:when>
										</c:choose></td>
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

	<!-- Menu Toggle Script -->
	

</body>
</html>
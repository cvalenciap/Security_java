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
<meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">

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

						<h3 style = "text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							Lista de Accion Perfil
							<button class="btn btn-primary pull-right"
								onclick="location.href='accionperfil/add'">
								<i class="glyphicon glyphicon-plus"></i>
								&nbsp;&nbsp;Agregar&nbsp;
							</button>
						</h3>

						<spring:url
							value="${pageContext.request.contextPath}/accionform"
							var="userActionUrl" />

						<br />
						<table id="mytable"
							class="table table-striped table-bordered table-hover "
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>SISTEMA</th>
									<th>MODULO</th>
									<th>PERFIL</th>
									<th>CODIGO</th>
									<th>COD. FORM</th>
									<th>FORMULARIO</th>
									<th>ACCIONES</th>
								</tr>
							</thead>
<%-- 
							<c:forEach var="accionform" items="${accionforms}">
								<tr>
									<td>${opcionform.sistemaNombre}</td>
									<td>${opcionform.moduloNombre}</td>
									<td>${opcionform.codFormulario}</td>
									<td>${opcionform.descripcion}</td>
									<td>${opcionform.estadoNombre}</td>
									<td>${opcionform.urlFormulario}</td>
									<td>${opcionform.usuarioModificacion}</td>
									<td><fmt:formatDate pattern="dd/MM/yyyy"
											value="${opcionform.fechaModificacion}" />
									</td>
									<td>
										<spring:url
											value="/opcionform/${opcionform.codSistema},${opcionform.codModulo},${opcionform.codFormulario}" var="verUrl" />
										<spring:url
											value="/opcionform/delete/${opcionform.codSistema},${opcionform.codModulo},${opcionform.codFormulario}" var="deleteUrl" /> 
										<spring:url
											value="/opcionform/update/${opcionform.codSistema},${opcionform.codModulo},${opcionform.codFormulario},0" var="updateUrl" />
										<spring:url
											value="/opcionform/update/${opcionform.codSistema},${opcionform.codModulo},${opcionform.codFormulario},1" var="updateTreeUrl" />
										
										<button id="close-image" class="btn btn-default"
											onclick="location.href='${verUrl}'">
											<!-- <i class="glyphicon glyphicon-eye-open"></i> -->
											<img src="resources/core/images/buscar.png">
										</button>
										<button id="close-image" class="btn btn-default"
											onclick="location.href='${updateUrl}'">
											<img src="resources/core/images/edit-icon.png">
										</button>
										<button id="close-image" class="btn btn-default"
											onclick="location.href='${updateTreeUrl}'">
											<img src="resources/core/images/tree.png">
										</button>
										<button id="close-image" class="btn btn-default"
											onclick="location.href='${deleteUrl}'">
											<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
											<img src="resources/core/images/eliminar.png">
										</button>
									</td>
								</tr>
							</c:forEach>
							
 --%>					
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
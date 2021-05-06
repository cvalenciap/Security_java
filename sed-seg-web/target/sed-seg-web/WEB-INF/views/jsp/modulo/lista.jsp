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

						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							Lista de Módulos
							<sed:seguridad accion="/moduloCREAR">
								<button class="btn btn-primary pull-right"
									onclick="location.href='modulo/add'">
									<!--  <i class="glyphicon glyphicon-plus"></i>-->
									&nbsp;&nbsp;Agregar&nbsp;
								</button>
							</sed:seguridad>
						</h3>

						<spring:url value="${pageContext.request.contextPath}/modulo"
							var="userActionUrl" />

						<br />
						<div class="table-wrapper">
							<table id="mytable"
								class="estandardTable table table-striped table-bordered table-hover "
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>SISTEMA</th>
										<th>COD.</th>
										<th>DESCRIPCION</th>
										<th>ESTADO</th>
										<th>USUARIO ACT.</th>
										<th>FECHA ACT.</th>
										<th>ACCIONES</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="modulo" items="${modulos}">
										<tr>
											<td>${modulo.sistemaNombre}</td>
											<td>${modulo.codModulo}</td>
											<td>${modulo.descripcion}</td>
											<td>${modulo.estadoNombre}</td>
											<td>${modulo.usuarioModificacion}</td>
											<td><span><fmt:formatDate pattern="yyyyMMdd"
														value="${modulo.fechaModificacion}" /></span>
											<fmt:formatDate pattern="dd/MM/yyyy"
													value="${modulo.fechaModificacion}" /></td>
											<td><spring:url
													value="/modulo/${modulo.codSistema},${modulo.codModulo}"
													var="verUrl" /> <spring:url
													value="/modulo/delete/${modulo.codSistema},${modulo.codModulo}"
													var="deleteUrl" /> <spring:url
													value="/modulo/update/${modulo.codSistema},${modulo.codModulo}"
													var="updateUrl" /> <sed:seguridad
													accion="/moduloVISUALIZAR">
													<button class="close-image btn btn-default"
														onclick="location.href='${verUrl}'">
														<!-- <i class="glyphicon glyphicon-eye-open"></i> -->
														<img src="resources/core/images/buscar.png">
													</button>
												</sed:seguridad> <sed:seguridad accion="/moduloMODIFICAR">
													<button class="close-image btn btn-default"
														onclick="location.href='${updateUrl}'">
														<img src="resources/core/images/edit-icon.png">
													</button>

												</sed:seguridad> <input type="hidden" value="${deleteUrl}" id="deleteUrl">
												<sed:seguridad accion="/moduloELIMINAR">
													<c:if test="${modulo.estado == 1}">
														<a href="${deleteUrl}" onclick="return onEliminar(this);">
															<button class="close-image btn btn-default">
																<!-- <i class="glyphicon glyphicon-floppy-remove"></i> -->
																<img src="resources/core/images/eliminar.png">
															</button>

														</a>
													</c:if>
												</sed:seguridad></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

						</div>
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
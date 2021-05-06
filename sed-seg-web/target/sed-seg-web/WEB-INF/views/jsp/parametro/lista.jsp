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

						<h3>
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;Lista de Parametros
							<!-- <button class="btn btn-primary pull-right"
								onclick="location.href='parametro/add'">
								<i class="glyphicon glyphicon-plus"></i>
								&nbsp;&nbsp;Agregar&nbsp;
							</button> -->
						</h3>

						<spring:url value="${pageContext.request.contextPath}/parametro"
							var="userActionUrl" />

						<br />
						<div class="table-wrapper">
							<table id="mytable"
								class="estandardTable table table-striped table-bordered table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<!-- <th>COD. PAR.</th> -->
										<th>CODIGO</th>
										<th>DESCRIPCION</th>
										<th>ESTADO</th>
										<th>VALOR</th>
										<th>USUARIO ACT.</th>
										<th>FECHA ACT.</th>
										<th>ACCIONES</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="parametro" items="${parametros}">
										<tr>
											<%-- <td>${parametro.codParametro}</td> --%>
											<td>${parametro.codigo}</td>
											<td>${parametro.descripcion}</td>
											<td>${parametro.nombreEstado}</td>
											<%-- <td>${modulo.estadoNombre}</td> --%>
											<td>${parametro.valor}</td>
											<td>${parametro.usuarioModificacion}</td>
											
											<td><span><fmt:formatDate pattern="yyyyMMdd"
														value="${parametro.fechaModificacion}" /></span>
											<fmt:formatDate pattern="dd/MM/yyyy"
													value="${parametro.fechaModificacion}" /></td>
											<td><spring:url
													value="/parametro/${parametro.codParametro},${parametro.codigo}"
													var="verUrl" /> <%-- <spring:url
											value="/parametro/delete/${parametro.codParametro},${parametro.codigo}" var="deleteUrl" /> --%>
												<spring:url
													value="/parametro/update/${parametro.codParametro},${parametro.codigo}"
													var="updateUrl" /> <sed:seguridad
													accion="/parametroVISUALIZAR">

													<button class="close-image btn btn-default"
														onclick="location.href='${verUrl}'">
														<!-- <i class="glyphicon glyphicon-eye-open"></i> -->
														<img src="resources/core/images/buscar.png">
													</button>
												</sed:seguridad> <sed:seguridad accion="/parametroMODIFICAR">
													<button class="close-image btn btn-default"
														onclick="location.href='${updateUrl}'">
														<img src="resources/core/images/edit-icon.png">
													</button>
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
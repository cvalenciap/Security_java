<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="divHeader" class="bt-header">
	<div class="row mtrbl0" style="background: #d9edf7;">

		<div class="col-sm-2"></div>
		<div class="col-sm-6 text-center">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<p>
					Último acceso
					<c:out value="${sessionScope.ultimoAcceso}" />
				</p>
			</c:if>
		</div>
		<div class="col-sm-4">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				        Bienvenido				        				         
								${pageContext.request.userPrincipal.name}
				         &nbsp;|&nbsp; ${sessionScope.descripcionPerfil}
				           <a id="logoutId" type="button"
					class="btn btn-default btn-xs c-bot01"
					href="${pageContext.request.contextPath}/logout"> Cerrar Sesión<span
					class="glyphicon glyphicon-remove " aria-hidden="true"></span>
				</a>
			</c:if>
			<c:if test="${pageContext.request.userPrincipal.name == null}">
				<a href="${pageContext.request.contextPath}/login">Login </a>
			</c:if>
		</div>
	</div>
</div>
<div class="navbar navbar-default">
	<div class="container-fluid">
		<div class="col-lg-12">
			<div class="clearfix content-heading">
				<a class="pull-left" href="${pageContext.request.contextPath}/menu"
					id="inicio"><img
					src="${pageContext.request.contextPath}/resources/core/images/company_logo.gif" /></a>
				<h2>&nbsp;Control de Seguridad</h2>
			</div>
		</div>
	</div>
</div>

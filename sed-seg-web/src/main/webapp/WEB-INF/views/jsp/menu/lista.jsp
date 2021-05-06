<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<body>

	<div class="container">
	
		<h1>Lista Formulario de Modulo</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Cod. Form.</th>
					<th>Descripción</th>
					<th>Cod. Padre</th>
					<th>Nivel Form.</th>
					<th>Order Form.</th>
					<th>Estado</th>
					<th>Url</th>
				</tr>
			</thead>

			<c:forEach var="formulario" items="${formularioMenu}">
				<tr>
					<td>${formulario.codFormulario}</td>
					<td>${formulario.descripcion}</td>
					<td>${formulario.codFormularioPadre}</td>
					<td>${formulario.nivelFormulario}</td>
					<td>${formulario.ordenFormulario}</td>
					<td>${formulario.estado}</td>
					<td>${formulario.urlFormulario}</td>

				</tr>
			</c:forEach>
		</table>

	</div>

</body>
</html>
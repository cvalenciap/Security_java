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

<script type="text/javascript">
	
</script>
<script type="text/javascript">
	function perfilesOpciones(codsistema, codperfil) {
		// Carga cabecera de acciones
		agregarTabla(codsistema);
		// Carga datos de las acciones
		agregarDatos(codsistema, codperfil);
	}
</script>

<script type="text/javascript">
	function SeleccionarFila() {

		var chk = $(this);
		var tr = chk.closest("tr");

		var id = chk.attr('id');

		//console.log(chk);

		//alert("Aqui!!");
	}
</script>

<script type="text/javascript">
	function agregarDatos(value1, value2) {
		if (value1 < 0) {
			return false;
		}

		$
				.ajax({
					type : "GET",
					timeout : 50000,
					beforeSend : function() {
						$('#spinner').show();
					},
					url : "${pageContext.request.contextPath}/perfil/getOpcionFormModuloSistema",
					data : {
						codsistema : value1,
						codperfil : value2
					},
					contentType : "application/json",
					success : function(data) {

						var table = $('#tblOpciones tbody');
						var source = builddataMenu(data);
						var space = '';
						buildUL(table, source, space);
					},
					error : function(xhr, thrownError) {
						//alert(xhr.status);
						//alert(xhr.responseText);
						//alert(thrownError);
					},
					complete : function() {
						$('#spinner').hide();

					}
				})
	}

	function agregarTabla(codigo) {

		if (codigo < 0) {
			return false;
		}

		$
				.ajax({
					type : "GET",
					timeout : 50000,
					beforeSend : function() {
						$('#spinner').show();
					},
					url : "${pageContext.request.contextPath}/perfil/getAccionFormModuloSistema",
					data : {
						id : codigo
					},
					contentType : "application/json",
					success : function(data) {

						var trtitle = '<tr bgcolor="#337ab7" class="custom hoverTable">'; <!-- #9dd7f2-->
						var thcolumna = '';
						for (i = 0; i < data.length; i++) {
							var item = data[i];

							if (i == 0) {
								thcolumna = "<th \"id="+ item['codigo'] + ">"
										+ item['descripcion'] + "</th>"
							} else {
								thcolumna = "<th style=\"text-align:center;\""+ "id="+ item['codigo'] + ">"
										+ item['descripcion'] + "</th>"
							}

							//trtitle = trtitle + "<th style=\"text-align:center;\""+ "id="+ item['codigo'] + ">" + item['descripcion'] + "</th>"
							trtitle = trtitle + thcolumna;
						}
						trtitle += '</tr>';

						var tableDiv = $("#divtbla");

						var tablestart = "<table id=\"tblOpciones\" class='table table-striped table-bordered table-hover'>";
						var tableend = "</table>";
						var tablehead = "<thead >" + trtitle + "</thead>";
						var tablebody = "<tbody></tbody>";
						var table = tablestart + tablehead + tablebody
								+ tableend;

						$("#divtbla")
								.append("<h4>Formularios y opciones </h4>");
						$("#divtbla").append(table);
						$("#divtbla").append("<br/>");

					},
					error : function(xhr, thrownError) {
						//alert(xhr.status);
						//alert(xhr.responseText);
						//alert(thrownError);
					},
					complete : function() {
						$('#spinner').hide();

					}
				})
	}

	var builddataMenu = function(data) {
		var source = [];
		var items = [];

		for (i = 0; i < data.length; i++) {
			var item = data[i];
			var label = item["descripcion"];
			var parentid = item["codFormularioPadre"];
			var id = item["codFormulario"];
			var acciones = item["accionFormulario"];

			//alert(item["descripcion"]);
			/* for (c = 0; c < acciones.length; c++) {
				var accion = acciones[c];
				alert(accion["codAccionFormulario"]);
			} */

			/*if (items[parentid]) {
				var item = {
					parentid : parentid,
					label : label,
					id : id,
					acciones : acciones
				};
				if (!items[parentid].items) {
					items[parentid].items = [];
				}
				items[parentid].items[items[parentid].items.length] = item;
				items[id] = item;
			} else {
				items[id] = {
					parentid : parentid,
					label : label,
					id : id,
					acciones : acciones
				};
				source[id] = items[id];
			}*/
			items[i] = {
				parentid : parentid,
				label : label,
				id : id,
				acciones : acciones
			};
			source[i] = items[i];
		}
		return source;
	}

	var buildUL = function(parent, items, space) {
		$
				.each(
						items,
						function() {
							var valor;
							var trow;
							if (this.label) {
								//alert(this.label);

								var listacciones = this.acciones;

								var row = '<tr>';
								var table = document
										.getElementById('tblOpciones');
								var tcol = '';
								var trow = '';
								var encontro = 0;
								var nomcolumna;
								var codigo = '';
								var codigoAccionForm = '';
								var accionExistePerfil = 0;
								row = row
										+ "<td id=\"" + this.id + "\" value=\"" + this.id + "\">"
										+ space + this.label + "</td>"

								for (c = 0; c < table.rows[0].cells.length; c++) {
									nomcolumna = table.rows[0].cells[c].innerHTML;

									//Primera columna es descripcion, no se considera
									if (c > 0) {
										//alert(nomcolumna);

										for (i = 0; i < listacciones.length; i++) {
											var accion = listacciones[i];
											/* alert( "codAccionFormulario: " + accion["codAccionFormulario"] 
												+ " codigo: " + accion["codigo"]
												+ " nombreAccion: " + accion["nombreAccion"]
												+ " estado: " + accion["estado"]
											); */
											if (nomcolumna == accion["nombreAccion"]) {

												if (accion["estado"] == 1) {
													encontro = 1;
													codigo = accion["codigo"];
													codigoAccionForm = accion["codAccionFormulario"];
													accionExistePerfil = accion["accionPerfil"];
													//alert("encontro!!");
													break;
												}

											}
										}

										if (encontro > 0) {

											if (accionExistePerfil) {
												tcol = "<td style=\"text-align:center;\" onclick=\"SeleccionarFila(this);\">"
														+ "<input type=\"checkbox\" id=\"" + codigo + "\" value=\"" + codigoAccionForm +"\" checked/>"
														+ "</td>";
											} else {
												tcol = "<td style=\"text-align:center;\" onclick=\"SeleccionarFila(this);\">"
														+ "<input type=\"checkbox\" id=\"" + codigo + "\" value=\"" + codigoAccionForm +"\" />"
														+ "</td>";
											}

										} else {
											tcol = "<td></td>";
										}
										//console.log(tcol);
										trow = trow + tcol;
										encontro = 0;
										codigo = '';
									}
								}
								row = row + trow + '</tr>';

								//var li = $("<td onclick=\"SeleccionarFila(this);\">" + this.label + "</td>");					
								//var tr = $("<tr><td>" + space + this.label + "</td></tr>");	
								//console.log(row);
								var tr = $(row);

								//li.appendTo(parent);					
								parent.append(tr);

								if (this.items && this.items.length > 0) {
									//var ul = $("<ul></ul>");
									//ul.appendTo(li)
									space = space + "&nbsp;&nbsp;";
									buildUL(parent, this.items, space);
								}

							}
						});
	}
</script>

<style type="text/css">
span:hover {
	cursor: hand;
}
</style>
</head>
<body onload="onloadFunction()">

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

						<c:choose>
							<c:when test="${perfilForm['new']}">
								<c:set var="titulo" value="Agregar Acciones al usuario ${codUsuario}" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Acciones al usuario ${codUsuario}" />
							</c:otherwise>
						</c:choose>

						<h3>
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;${titulo}
						</h3>

						<br />

						<spring:url value="/usuario/acciones/" var="userActionUrl" />

						<%-- <form:form class="form-horizontal" method="post"
							modelAttribute="perfilForm" action="${userActionUrl}"> --%>
						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="perfilForm" action="${userActionUrl}">

							<form:hidden path="codPerfil" />

							<spring:bind path="perfilAccion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<!-- <label class="col-sm-2 control-label">CheckAll</label> -->
									<div class="col-sm-12">
										<form:input path="perfilAccion" type="hidden"
											class="form-control" id="txtperfilaccion" />
									</div>
								</div>
							</spring:bind>


							<spring:bind path="codSistema">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sistema(*)</label>
									<div class="col-sm-6">
										<form:select id="selectSistema" name="selectSistema" path="codSistema"
											class="form-control">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${sistemas}" />
										</form:select>
										<form:errors path="codSistema" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>
							

							<spring:bind path="estado">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Estado(*)</label>
									<div class="col-sm-2">
										<form:select path="estado" class="form-control">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${estados}" />
										</form:select>
										<form:errors path="estado" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>

							<div id=divtbla class="row">
								<%-- <table id="tblOpciones"
									class="table table-striped table-bordered table-hover">
									<thead>
										<tr bgcolor=#9dd7f2>
											<th id="0">NOMBRE DEL FORMULARIO</th>
											<th id="16">CREAR</th>
											<th id="17">VISUALIZAR</th>
											<th id="18">MODIFICAR</th>
											<th id="19">ELIMINAR</th>
											<th id="20">IMPRIMIR</th>
											<th id="21">EXPORTAR</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="budget" items="${budgets}">
								            <tr>
								                <td>${budget.budgetName}</td>
								                <td>${budget.budgetAmount}</td>
								                <td>${budget.actual}</td>
								            </tr>       
								        </c:forEach>
									</tbody>
								</table> --%>
							</div>


							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<%-- <a class="btn btn-primary" href="${userActionUrl}">
										<i class="glyphicon glyphicon-remove-sign"></i>
										Cancelar</a> --%>
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<c:choose>
											<c:when test="${perfilForm['new']}">
												<button id="botonGrabar" class="btn btn-primary">
													<i class="glyphicon glyphicon-floppy-disk"></i>
													&nbsp;Grabar
												</button>
											</c:when>
											<c:otherwise>
												<button id="botonActualizar" class="btn btn-primary">
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

	<script type="text/javascript">
	
	function onloadFunction(){
		//alert($('#selectSistema option:selected').val());
		var valor = $('#selectSistema option:selected').val();
		if(valor != undefined) {
			perfilesOpciones(valor, 0);	
		}
		
	}
	
	
		function SeleccionarFila() {

			var acciones = [];

			$('#txtperfilaccion').val("");

			//alert("a recorrer la tabla");

			$('#tblOpciones tbody tr').each(function() {
				var $row = $(this);
				//alert($(this).attr('id'));
				//alert($(this).text());
				//trid = $(this).attr('id');
				$row.find('input[type=checkbox]:checked').each(function() {
					//console.log($(this).attr('class'));				    
					//alert($(this).val());
					acciones.push($(this).val());
				});
			});

			$('#txtperfilaccion').val(acciones);
		}

		$("#selectSistema").change(function() {
			var valor = $(this).val();			
			$("#divtbla").empty();
			$('#spinner').show();
			if (valor > 0) {
				perfilesOpciones(valor, 0);
			}

		});
		$('tblOpciones tr:not(:first-child)').mouseover(function () {
		    $(this).addClass('row_selected');
		}).mouseout(function () {
		    $(this).removeClass('row_selected');
		});
		
		
	</script>

	<script type="text/javascript">
		$("#formid").click(function(event) {
			SeleccionarFila();
		});
	</script>

</body>
</html>
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


$(document).ready(function(){
	//ocultarBotones();
	//agregarAtributos();
	
	//$(".tree ul").remove();
	
	var valSistema = $('#selectSistema').val();
	var valModulo  = $('#selectModulo').val();
	
	//alert(valSistema);
	
	if (valSistema>0 && valModulo>0){
		obtenerListaFormularioModulo(valSistema, valModulo);
	}
	
});

</script>
<style type="text/css">
span:hover {
	cursor: pointer;
}

ul {
	list-style: none;
}
.disable-click {
    pointer-events: none;
}
</style>
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath}"
		id="url">
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
							<c:when test="${opcionForm['new']}">
								<c:set var="titulo" value="Agregar Formulario" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Formulario" />
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${opcionForm['readOnly']}">
								<c:set var="readonly" value="true" />
							</c:when>
							<c:otherwise>
								<c:set var="readonly" value="false" />
							</c:otherwise>
						</c:choose>


						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							${titulo}
						</h3>

						<br />

						<spring:url value="/opcionform" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="opcionForm" action="${userActionUrl}">

							<%-- <form:hidden path="codFormulario" id="codformulario"/> --%>

							<input type="hidden" name="activarRoot" value="${opcionForm.activarRoot}" id="activarRoot">


							<c:choose>
								<c:when test="${opcionForm['new']}">
									<form:hidden path="codFormulario" id="codformulario" />

									<spring:bind path="codSistema">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Sistema(*)</label>
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
											<label class="col-sm-3 control-label">Modulo(*)</label>
											<div class="col-sm-9">
												<form:select id="selectModulo" path="codModulo"
													class="form-control">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${modulos}" />
												</form:select>
												<form:errors path="codModulo" class="control-label" />
											</div>
											<div class="col-sm-1"></div>
										</div>
									</spring:bind>



									<c:if test="${not empty selectCodSistema}">

										<script type="text/javascript">
											$('#selectSistema').val(${selectCodSistema});										
											obtenerListarModulo(${selectCodSistema});																															
											
											obtenerListaFormularioModulo(${selectCodSistema}, ${selectCodModulo});	
											//$('#selectModulo').val(${selectCodModulo});
								    	</script>
									</c:if>
								</c:when>

								<c:otherwise>
									<form:hidden path="codSistema" />
									<form:hidden path="codModulo" />

									<form:hidden path="sistemaNombre" />
									<form:hidden path="moduloNombre" />

									<div class="row">
										<label class="col-sm-3 control-label">Sistema</label>
										<div class="col-sm-9">
											<p class="form-control">${opcionForm.codSistema} ${opcionForm.sistemaNombre}</p>
										</div>
										<!-- <div class="col-sm-4"></div> -->
									</div>

									<div class="row">
										<label class="col-sm-3 control-label">Modulo</label>
										<div class="col-sm-8">
											<p class="form-control">${opcionForm.codModulo} ${opcionForm.moduloNombre}</p>
										</div>
										<div class="col-sm-1"></div>
									</div>

									<%-- <div class="row">
										<label class="col-sm-3 control-label">Cod. Formulario</label>
										<div class="col-sm-3"> 
											<p id="codFormulario2" class="form-control">${opcionForm.codFormulario}</p>
										</div>
										<div class="col-sm-6"></div>
									</div> --%>
									<spring:bind path="codFormulario">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Cod. Formulario</label>
											<div class="col-sm-3">
												<form:input path="codFormulario" readonly="true"
													class="form-control" id="codFormulario" placeholder=""
													onkeypr-ess="return checkOnlyDigits(event);" />
												<form:errors path="codFormulario" class="control-label" />
											</div>
											<div class="col-sm-6"></div>
										</div>
									</spring:bind>



								</c:otherwise>
							</c:choose>

							<spring:bind path="descripcion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Descripci?n Formulario(*)</label>
									<div class="col-sm-8	">
										<form:input path="descripcion" maxlength="40"
											readonly="${readonly}" class="form-control exclude"
											id="descripcion" placeholder="DESCRIPCI?N"
											onkeypress="return alphaOnly(event);" />
										<form:errors path="descripcion" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>

							<spring:bind path="codFormularioPadre">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">C?digo Padre(*)</label>
									<div class="col-sm-9">
										<%-- <form:input path="codFormularioPadre" class="form-control"
											id="codFormularioPadre" placeholder="C?D PADRE"
											readonly="${readonly}"
											onkeypress="return checkOnlyDigits(event);" maxlength="10" /> --%>
										<form:select id="selectFormulario" path="codFormularioPadre" class="form-control">
											<form:option value="0" data-nivel="0" label="--- 0 - Es Menu Principal ---" />
											<form:options items="${formularios}" />
										</form:select>
										<form:errors path="codFormularioPadre" class="control-label" />
									</div>
									<div class="col-sm-6"></div>
								</div>
							</spring:bind>


							<c:if test="${opcionForm.activarRoot}">
								<script type="text/javascript">
		
									//alert("aqui en activar!!");
									obtenerListaFormularioModulo(${opcionForm.codSistema}, ${opcionForm.codModulo});
									
							    </script>
							</c:if>



							<spring:bind path="nivelFormulario">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Nivel Formulario(*)</label>
									<div class="col-sm-3">
										<form:input path="nivelFormulario" 
													class="form-control"
													id="nivelFormulario" 
													placeholder="NIVEL" 
													maxlength="2"
													readonly="true"/>
										<form:errors path="nivelFormulario" class="control-label" />
									</div>
									<div class="col-sm-6">
									</div>
								</div>
								<form:hidden path="nivelFormulario" id="nivelFormularioHidden"/>
							</spring:bind>

							<spring:bind path="urlFormulario">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Url Formulario(*)</label>
									<div class="col-sm-8">
										<form:input path="urlFormulario" maxlength="200"
											readonly="${readonly}" class="form-control"
											id="urlFormulario" onkeypress="return alphaOnlyUrl(event);"
											placeholder="URL" />
										<form:errors path="urlFormulario" class="control-label" />
									</div>
									<div class="col-sm-1"></div>
								</div>
							</spring:bind>

							<spring:bind path="ordenFormulario">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-3 control-label">Orden
										Formulario(*)</label>
									<div class="col-sm-3">
										<form:input path="ordenFormulario" class="form-control"
											id="ordenFormulario" placeholder="ORDEN" maxlength="2"
											readonly="${readonly}"
											onkeypress="return checkOnlyDigits(event);" />
										<form:errors path="ordenFormulario" class="control-label" />
									</div>
									<div class="col-sm-6"></div>
								</div>
							</spring:bind>


							<c:choose>
								<c:when test="${opcionForm['new']}">
									<form:hidden path="estado"  />
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Estado(*)</label>
											<div class="col-sm-4">
												<form:select path="estado" disabled="true"
													class="form-control" id="estado">
													<form:option value="-1" label="--- Select ---" />
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Estado(*)</label>
											<div class="col-sm-4">
												<form:select path="estado" class="form-control"
													id="estadoId">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>




							<c:choose>
								<c:when test="${opcionForm['readOnly']}">
									<form:hidden path="accion"/>
									<spring:bind path="accion">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Acciones(*)</label>
											<div class="col-sm-9" id="myDiv">
												<form:checkboxes path="accion" id="selectAccion" disabled="true"
													items="${acciones}" 
													element="label class='checkbox-inline'" itemLabel="text"
													itemValue="value" />
												<br />
												<form:errors path="accion" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="accion">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-3 control-label">Acciones(*)</label>
											<div class="col-sm-9" id="myDiv">
												<form:checkboxes path="accion" id="selectAccion"
													items="${acciones}" 
													element="label class='checkbox-inline'" itemLabel="text"
													itemValue="value" />
												<br />
												<form:errors path="accion" class="control-label" />
											</div>
											<div class="col-sm-5"></div>
										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>






							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<c:choose>
											<c:when test="${opcionForm['new']}">
												<button id="botonGrabar" type="submit"
													class="btn btn-primary">
													<i class="glyphicon glyphicon-floppy-disk"></i>
													&nbsp;Grabar
												</button>
											</c:when>
											<c:otherwise>
												<button id="botonActualizar" type="submit"
													class="btn btn-primary">
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

					<div class="col-lg-5">
						<div class="row">
							<div class="col-md-12">
								<br /> <br /> <br /> <br />
								<div
									style="overflow-y: scroll; overflow-x: scroll; height: 550px; width: 400px">
									<div class="tree well" style="width: 600px"></div>
								</div>



							</div>
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

	<script type="text/javascript">

		$('document').ready(function(){
			// setear nivel
			/* var nivelFormularioPadre = $("#selectFormulario").find(':selected').attr('data-nivel');
			console.log(nivelFormularioPadre);
			if (nivelFormularioPadre == undefined) {
				nivelFormularioPadre = 0;
			} */

			if (${!opcionForm.activarRoot}) {
				$("#nivelFormulario").val(1);
				$("#nivelFormularioHidden").val(1);
			}
			
			// llenar listado de formularios
			var codFormularioPadre = ${opcionForm.codFormularioPadre == null ? '0' : opcionForm.codFormularioPadre};
			var codSistema = ${opcionForm.codSistema == null ? '-1' : opcionForm.codSistema};
			var codModulo = ${opcionForm.codModulo == null ? '-1' : opcionForm.codModulo};
			var codForm = ${opcionForm.codFormulario == null ? '-1' : opcionForm.codFormulario};
			obtenerListarFormularioActualizando(codSistema, codModulo, codForm, codFormularioPadre);

		});

		// Seleccionando otro valor de #selectSistema
		$("#selectSistema").change(function() {
			var valor = $(this).val();
			// Llenar 'select' Modulo
			obtenerListarModulo(valor);
		});

		// Seleccionado otro valo de #selectModulo
		$("#selectModulo").change(function() {
			var valor1 =  $("#selectSistema").val();
			var codFormulario = $("#codFormulario").val();
			var valor2 = $(this).val();
			//$("#noderoot li").remove();
			//alert("Modulo!!!");
			var codigo = $("#codformulario").val();
			//alert($("#codformulario").serialize());
			//alert(codigo);
			obtenerListaFormularioModulo(valor1, valor2);
			// Llenar 'select' formularios padres
			if (codFormulario == null) {
				codFormulario = 0;
			}
			console.log(codFormulario);
			obtenerListarFormulario(valor1, valor2, codFormulario);
		});

		$("#selectFormulario").change(function() {
			var nivelFormularioPadre = $(this).find(':selected').attr('data-nivel');
			$("#nivelFormulario").val(parseInt(nivelFormularioPadre) + 1);
			$("#nivelFormularioHidden").val(parseInt(nivelFormularioPadre) + 1);
		});
		
		
		var builddata = function (data) {
		    var source = [];
		    var items = [];
		    
		    for (i = 0; i < data.length; i++) {
		        var item = data[i];
		        var label = item["descripcion"];
		        var parentid = item["codFormularioPadre"];
		        var id = item["codFormulario"];
		        var node = item["codSistema"];
		        var child = item["codModulo"];

		        if (items[parentid]) {
		            var item = { parentid: parentid, label: label, id: id, node: node, child: child, item: item };
		            if (!items[parentid].items) {
		                items[parentid].items = [];
		            }
		            items[parentid].items[items[parentid].items.length] = item;
		            items[id] = item;
		        } else {
		            items[id] = { parentid: parentid, label: label, id: id, node: node, child: child, item: item };
		            source[id] = items[id];
		        }
		    }
		    return source;
		}
		
		var buildUL = function (parent, items) {
			var codigo = $("#codformulario").val();
			//alert("costruyendo root");
			//alert(codigo);
			
		    $.each(items, function () {
		    	var valor;
		        if (this.label) {
		        	if (undefined == this.id){
		        		valor = '';
		        	} else {
		        		valor = this.id;
		        	}

		        	if (codigo == ""){
		        		var li = $("<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/document.gif\"/></span>"
		            			+ "<a href=\"javascript:void(0)\" data-id=\"" + valor + "\">" + " [" + this.parentid + "-" + valor + "] " + this.label + "</a>"
		            			+ "</li>");
		        	} else {
		        		
		        		var li = $("<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/document.gif\"/></span>"
		            			+ "<a href=\"javascript:void(0)\" onClick=\"buscarForm('" + this.node + "','" + this.child + "','" + valor + "');\" data-id=\"" + valor + "\">" + " [" + this.parentid + "-" + valor + "] " + this.label + "</a>"
		            			+ "</li>");	
		        		
		        		/*var li = $("<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/document.gif\"/></span>"
		            			+ "<a href=\"javascript:void(0)\" data-id=\"" + valor + "\">" + " [" + this.parentid + "-" + valor + "] " + this.label + "</a>"
		            			+ "</li>");	*/
					}		        		           
					
		            li.appendTo(parent);
		            if (this.items && this.items.length > 0) {
		                var ul = $("<ul></ul>");
		                ul.appendTo(li);
		                buildUL(ul, this.items);
		            }	
		        }
		    });
		}
		
		/* $('.buscarDatos').on("click",function() {
		    var datoid =  $(this).data("id");
		    //var username = $(this).data("username");
		    alert (datoid);
		}); */
		
		
		
		function buscarForm(value1, value2, id){
		   // alert(value1);
		   //alert(value2);
		   //alert(id);
		   
		   
			var url = "${pageContext.request.contextPath}/opcionform/update/" + value1 + "," + value2 + "," + id + ", 1";
			$('#spinner').show();
			$(location).attr('href',url);
		   
			/* $.ajax({
				type : "GET",
				timeout: 50000,
				beforeSend : function() {
					$('#spinner').show();
				},
				url : "${pageContext.request.contextPath}/opcionform/getFormModuloSistema",
				data : {value1 : value1, value2 : value2, value3 : id},
				contentType : "application/json",
				success : function(data) {
					limpiarDatos();
					
					
					
					$('#descripcion').val(data['descripcion']);
					$('#urlFormulario').val(data['urlFormulario']);
					$('#codFormulario').val(data['codFormulario']);
					$('#codFormularioPadre').val(data['codFormularioPadre']);
					$('#nivelFormulario').val(data['nivelFormulario']);
					$('#ordenFormulario').val(data['ordenFormulario']);
					$("#estado").val(data['estado']);
					//alert(data['estado']);
					//$("#estadoId").val(data['estado']);
					document.getElementById('estadoId').value = data['estado'];
					
					if(data['estado'] == 0){
						$('#descripcion').attr('readonly', true);							
						$('#urlFormulario').attr('readonly', true);
						$('#codFormulario').attr('readonly', true);
						$('#codFormularioPadre').attr('readonly', true);
						$('#nivelFormulario').attr('readonly', true);
						$('#ordenFormulario').attr('readonly', true);
						
						//$('#selectAccion').attr('readonly', true);	
						$("#myDiv").css("visibility", "hidden");
					} else {
						$('#descripcion').attr('readonly', false);							
						$('#urlFormulario').attr('readonly', false);
						$('#codFormulario').attr('readonly', false);
						$('#codFormularioPadre').attr('readonly', false);
						$('#nivelFormulario').attr('readonly', false);
						$('#ordenFormulario').attr('readonly', false);
						//$('#selectAccion').attr('readonly', false);
						$("#myDiv").css("visibility", "visible");
					}
					
					alert(data['accionFormulario']);
					var items = data['accionFormulario'];
					var ind=0;
					
					$('#formulario input[type=checkbox]').each(function(){
				    	 this.checked=0;
					});
					
					for(var i = 0; i < items.length; i++){
						item = items[i];
						
						//alert (item["nombreAccion"]);
						var accion = [];
						
						if (item["codAccionFormulario"] > 0 && item["estado"] == 1){
							
							accion.push(item["codigo"]);
							
						     $('#formulario input[type=checkbox]').each(function(){
						    	 //this.checked=0;
						    	 
						    	 if (item["codigo"] == $(this).val()){
						    		 this.checked=1;
						    	 }
						         console.log($(this).val());
						     });
						     
						}
						//$('#selectAccion').append(accion);						
						//$('#selectAccion').items(accion);
					 }
					
					//visualizarBotones();
				},
				error : function(xhr, thrownError) {
					alert(xhr.status);
					//alert(xhr.responseText);
					alert(thrownError);
				},
				complete : function() {
					$('#spinner').hide();
				}
			}); 
		};*/
		
		}
		
		function limpiarDatos() {
			$('#descripcion').val('');
			$('#urlFormulario').val('');
			$('#codFormulario').val('');
			$('#codFormularioPadre').text('');
			$('#nivelFormulario').val('');
			$('#ordenFormulario').val('');
		}
		
		/*
		function ocultarBotones() {
			$('#btnupdatenode').hide();
			$('#btndeletenode').hide();
			$('#btnaddchild').hide();
			$('#btncancel').hide();
		}
		
		function visualizarBotones() {
			$('#btnaddnode').hide();
			$('#btnupdatenode').show();
			$('#btndeletenode').show();
			$('#btnaddchild').show();
			//$('#btncancel').show();
		}
		
		function visualizarBotonesChild() {
			$('#btnaddnode').show();
			$('#btncancel').show();
		} */
		
		//$(function () {
		function agregarAtributos() {	
			//alert("En funci?n TreeView");
			
		    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Contraer este nodo');
		    $('.tree li.parent_li > span').on('click', function (e) {
		    	
		    	var children = $(this).parent('li.parent_li').find(' > ul > li');
		        if (children.is(":visible")) {
		            children.hide('fast');
		            $(this).attr('title', 'Expandir este nodo').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
		        } else {
		            children.show('fast');
		            $(this).attr('title', 'Contraer este nodo').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
		        }
		        e.stopPropagation();
		    });
		}
		
	</script>

</body>
</html>
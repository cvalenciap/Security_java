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
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	 -->
<meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">
<style type="text/css">
input[type=text] {
    text-transform: uppercase;
}

textarea{
    text-transform: uppercase;
}
</style>
<c:import url="../fragments/link.jsp" />

<!-- inicio adecuacion seguridad2 -->
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Scripts/multi-select-master/css/multi-select.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/multi-select-master/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.js"></script>

<script type="text/javascript">
 /*<![CDATA[*/ 
	var sistemasAsociados = ${sistemasAsociados};
	var perfilesAsociados = ${perfilesAsociados};
 /*]]>*/
</script>

<script type="text/javascript">
$(document).ready(function() {
	inicializarVariables();
	cargarComponentes();
});

function inicializarVariables() {
	console.log("visual usuario");
	var selectPerfilesAsociados = null;
	var selectSistemaAsociado = null;
}

function cargarComponentes(){
	selectPerfilesAsociados = $('#selectPerfilesAsociados');
	selectSistemaAsociado = $('#selectSistemaAsociado');
	ltaPerfilesAsociados = perfilesAsociados;

	actualizarPerfiles();	

	/*eval tipo usuario*/
	if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_INTERNO){
		$("#divExterno").hide()
	} else if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_EXTERNO){
		$("#divExterno").show()
	}	
}

function actualizarPerfiles(){
	sistemaId = selectSistemaAsociado.val();
	ltaPerfilesActual = [];

	for(var i=0; i<ltaPerfilesAsociados.length; i++){
		if(ltaPerfilesAsociados[i].codSistema == sistemaId){
			ltaPerfilesActual.push(ltaPerfilesAsociados[i]);
		}
	}
	selectPerfilesAsociados.empty();
	agregarListaItem(selectPerfilesAsociados, ltaPerfilesActual, "codPerfil", "descripcion");
	selectPerfilesAsociados.attr("disabled", "disabled");
	
 	selectPerfilesAsociados.multiSelect();
 	selectPerfilesAsociados.multiSelect({ keepOrder: true });
 	selectPerfilesAsociados.multiSelect('refresh');
}
</script>
<!-- fin adecuacion seguridad2 -->

<script type="text/javascript">
function buscarTrabajador(valor){

	setTimeout(function() { 
		$('#spinner').show();
		
		
		if (valor == 0 || null == valor){
			$('#spinner').hide();
// 			inicio adecuacion seguridad2
			$('#trabajador-foto').attr('src', "${pageContext.request.contextPath}/resources/core/images/00000.png");
// 			fin adecuacion seguridad2 
			return false;
		}		
		
		var rutafoto= "/resources/core/images/fotos";
		$('#trabajador-datos').val('');
    	$('#trabajador-dni').val(''); 
    	$('#trabajador-area').val('');
    	$('#trabajador-equipo').val('');
    	$('#trabajador-estado').val('');
    	$('#trabajador-codigo').val('');
    	$('#trabajador-estado').val('');
		
		$.ajax({
	        type: "GET",
	        beforeSend: function(){
	        	$('#spinner').show();
	          },
	        url: "${pageContext.request.contextPath}/usuario/getFichaTrabajador",
	        data : { id : valor}, 
	        contentType: "application/json",
	        success: function(data){
	        	
	        	if(data == '') {
	        		//alert('No existe información para la ficha ingresada.');
	        		$.alert({
	        			title: 'Aviso!',
                        content: 'No existe información para la ficha ingresada.',
                        icon: 'fa fa-rocket',
                        animation: 'zoom',
                        closeAnimation: 'zoom',
                        buttons: {
                            okay: {
                                text: 'Aceptar',
                                btnClass: 'btn-blue'
                            }
                        }
	        		});
	        		$('#spinner').hide(); 
	        	} else {
	        		descripcion=data['vApePaterno'] + " " + data['vApeMaterno'] + " " + data['vNombres'];		        	
		        	$('#trabajador-datos').val(descripcion);
		        	$('#trabajador-dni').val(data['vDni']);
		        	$('#trabajador-area').val(data['nCodArea']);
		        	$('#trabajador-codigo').val(data['nCodTrabajador']);
		        	$('#trabajador-email').val(data['vDirelectronica']);
		        	$('#trabajador-equipo').val(data['descArea']);
		        	$('#trabajador-estado').val(data['estadoTrabajador']);
		     	
		        	url =  data['data'];
		        	
		        	if(url == undefined ){
		        		$('#trabajador-foto').attr('src', "${pageContext.request.contextPath}/resources/core/images/00000.png");	        		
		        	} else {
		        		
		        		$('#trabajador-foto').attr('src', "data:image/png;base64," + data['data']);	
		        	}
		        	
		        	$('#spinner').hide(); 
		        	
	        	}
	        	
	        	
	        	
	        	       	
	        },
	        error:function(xhr, thrownError){
	           //alert(xhr.status);
	           //alert(xhr.responseText);
	           //alert(thrownError);
	        },
	        complete: function(){
	        	$('#spinner').hide();
	        }
	   })
		
	
	}, 500);

	$('#spinner').hide(); 
	
}
	</script>
<style type="text/css">
#file::-webkit-file-upload-button {
	border: 1px solid black;
	border-radius: 5px;
	background-color: #337ab7;
	color: white;
}

span:hover {
	cursor: hand;
}
</style>

</head>
<body onload="buscarTrabajador(${usuarioForm.codFicha});">

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



						<h3>
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;Visualizar Usuario
						</h3>

						<br />

						<spring:url value="/usuario" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="usuarioForm"
							action="${userActionUrl}?${_csrf.parameterName}=${_csrf.token}"
							enctype="multipart/form-data">		
							
<!-- 							inicio adecuacion seguridad2 -->							
<%-- 							<spring:bind path="codSistema"> --%>
<%-- 								<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 									<label class="col-sm-2 control-label">Sistema(*)</label> -->
<!-- 									<div class="col-sm-6"> -->
<%-- 										<form:select id="selectSistema" path="codSistema" --%>
<%-- 											class="form-control" disabled="true"> --%>
<%-- 											<form:option value="-1" label="--- Select ---" /> --%>
<%-- 											<form:options items="${sistemas}" /> --%>
<%-- 											<c:forEach var="sistema" items="${sistemas}"> --%>
<%-- 												<form:option id="${sistema.id}" value="${sistema.value}" --%>
<%-- 													label="${sistema.label}" /> --%>
<%-- 											</c:forEach> --%>
<%-- 										</form:select> --%>
<!-- 									</div> -->
<!-- 									<div class="col-sm-4"></div> -->
<!-- 								</div> -->
<%-- 							</spring:bind>						 --%>

							<spring:bind path="sistemasAsociados">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sistemas Asociados</label>
									<div class="col-sm-6">
										<form:select id="selectSistemaAsociado" path="codSistema"
											class="form-control" onchange="actualizarPerfiles();">
											<c:forEach var="sistema" items="${usuarioForm.sistemasAsociados}">
												<form:option id="${sistema.codSistema}" value="${sistema.codSistema}"
													label="${sistema.codSistema} - ${sistema.descripcion}" />
											</c:forEach>
										</form:select>
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>
							
							<spring:bind path="perfilesAsociados">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Perfiles Asociados</label>
									<div class="col-sm-6">
										<select multiple="multiple" id="selectPerfilesAsociados" name="selectPerfilesAsociados[]">
										</select>
									</div>
								</div>
							</spring:bind>						

<%-- 							<spring:bind path="codPerfil"> --%>
<%-- 								<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 									<label class="col-sm-2 control-label">Perfil(*)</label> -->
<!-- 									<div class="col-sm-6"> -->
<%-- 										<form:select id="selectPerfil" path="codPerfil" --%>
<%-- 											disabled="true" class="form-control"> --%>
<%-- 											<form:options items="${perfiles}" multiple="true" /> --%>
<%-- 										</form:select> --%>
<%-- 											<form:select id="selectPerfil" path="perfil" items="${perfiles}" 
<%-- 											multiple="true" class="form-control"  --%>
<%-- 											size="6"/>	--%>
<%-- 										<form:errors path="codPerfil" class="control-label" /> --%>
<%-- 										<form:errors path="codPerfil" class="control-label" /> --%>
<!-- 									</div> -->
<!-- 									<div class="col-sm-4"></div> -->
<!-- 								</div> -->
<%-- 							</spring:bind> --%>
<!-- 							fin adecuacion seguridad2  -->


							<spring:bind path="codFicha">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Ficha</label>
									<div class="col-sm-10">
										<div class="form-horizontal">
											<form:input path="codFicha" type="numeric" maxlength="5"
												readonly="true" class="form-control " id="txtcodficha"
												placeholder="Ficha"
												style="width:155px;max-width:155px;display:inline-block"
												onkeypress="return checkOnlyDigits(event);" />

											<form:errors path="codFicha" class="control-label" />
										</div>
									</div>
								</div>
							</spring:bind>


							<c:choose>
								<c:when test="${usuarioForm['new']}">
									<%-- <spring:bind path="codFicha">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Ficha</label>
											<div class="col-sm-10">
												<div class="form-horizontal">
													<form:input path="codFicha" type="numeric" maxlength="5"
														class="form-control " id="txtcodficha"
														placeholder="Ficha"
														style="width:155px;max-width:155px;display:inline-block" 
														onkeypress="return checkOnlyDigits(event);"/>
													<span class="btn btn-primary glyphicon glyphicon-search" id="search-ficha"
															style="margin-left: -8px; margin-top: -5px; min-height: 36px;"></span>
													<form:errors path="codFicha" class="control-label" />
												</div>
											</div>
										</div>
									</spring:bind> --%>
								</c:when>
								<c:otherwise>
									<%-- <form:hidden path="codSistema" />
									<form:hidden path="sistemaNombre" /> --%>
									<%-- <form:hidden path="codPerfil" />
									<form:hidden path="perfilNombre" /> --%>
									<form:hidden path="codFicha" />
									<form:hidden path="codUsuario" />
									<%-- <form:hidden path="codArea"/>
									<form:hidden path="descripcion"/> --%>

									<script type="text/javascript">
									    	buscarTrabajador(${usuarioForm.codFicha});
									 </script>

								

								</c:otherwise>
							</c:choose>

							<div class="form-group">
								<div class="col-sm-2"></div>
								<div class="col-sm-10">
									<div class="col-sm-2">
										<div>
											<img id="trabajador-foto"
												
												src="${pageContext.request.contextPath}/resources/core/images/00000.png"
												height="160px" width="150px" />
										</div>

									</div>
									<div class="col-sm-8">
										
										<div class="form-horizontal">
											<label class="col-sm-2 control-label">Trabajador</label>
											<div class="col-sm-10">
												<input id="trabajador-datos" type="text"
													class="form-control" readonly />
											</div>
										</div>
										

										<div class="form-horizontal">
											<label class="col-sm-2 control-label">DNI</label>
											<div class="col-sm-10">
												<input id="trabajador-dni" type="text" class="form-control"
													readonly />
											</div>
										</div>
										<div class="form-horizontal">
											<label class="col-sm-2 control-label">Area</label>
											<div class="col-sm-10">
												<input id="trabajador-area" type="text" class="form-control"
													readonly />
											</div>
										</div>
										<!-- <div class="form-horizontal">
											<label class="col-sm-2 control-label">Estado</label>
											<div class="col-sm-10">
												<input id="trabajador-estado" type="text" class="form-control" disabled/>
											</div>
										</div> -->
										<div class="form-horizontal">
											<label class="col-sm-2 control-label">Código</label>
											<div class="col-sm-10">
												<input id="trabajador-codigo" type="text"
													class="form-control" readonly />
											</div>
										</div>
										<div class="form-horizontal">
											<label class="col-sm-2 control-label">Email</label>
											<div class="col-sm-10">
												<input id="trabajador-email" type="text"
													class="form-control" readonly />
											</div>
										</div>
									</div>
								</div>
							</div>
							
<!-- 							Inicio REQ 213 -->
							<spring:bind path="codTipo">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Tipo(*)</label>
									<div class="col-sm-2">
										<form:select path="codTipo" disabled="true" class="form-control">
											<form:options items="${tipos}" />
										</form:select>
										<form:errors path="codTipo" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>
							
<!-- 							inicio opcional externo -->
							<div id="divExterno" style="display: none;">
								<div class="form-group">
									<div class="col-sm-2" ></div>
									<div class="col-sm-10" ><div style="font-style:italic; font-weight:bold;">*Campos para usuarios externos</div></div>
								</div>
								<div class="form-group">								
									<div class="col-sm-12">
										<div class="col-sm-10">
											<!-- combo empresas -->
											<spring:bind path="codEmpresa">
												<div class="form-horizontal ${status.error ? 'has-error' : ''}">
													<label class="col-sm-2 control-label">Empresa(*)</label>
													<div class="col-sm-10">
														<form:select path="codEmpresa" class="form-control" disabled="true">
															<form:option value="-1" label="--- Select ---" />
															<c:forEach var="contratista" items="${listaContratistas}">
																<form:option id="${contratista.idContratista}" value="${contratista.idContratista}"
																	label="${contratista.desRazon}" /> 
															</c:forEach> 
														</form:select>
														<form:errors path="codEmpresa" class="control-label" />
													</div>
												</div>
											</spring:bind>
											<!-- input trabajador externo -->
											<spring:bind path="nombreExterno">
												<div class="form-horizontal ${status.error ? 'has-error' : ''}">
													<label class="col-sm-2 control-label">Trabajador(*)</label>
													<div class="col-sm-10">
														<form:input path="nombreExterno" class="form-control" readonly="true"
															maxlength="50" onkeypress="return alphaSpaceOnly(event);"
															id="txtNombreExterno" placeholder="Trabajador Externo" />
														<form:errors path="nombreExterno" class="control-label" />
													</div>
												</div>
											</spring:bind>
											<!-- input DNI externo -->
											<spring:bind path="dniExterno">
												<div class="form-horizontal ${status.error ? 'has-error' : ''}">
													<label class="col-sm-2 control-label">DNI(*)</label>
													<div class="col-sm-10">
														<form:input path="dniExterno" class="form-control txtDNIExterno"
															maxlength="8"	readonly="true"
															id="txtDNIExterno" placeholder="DNI Externo" />
														<form:errors path="dniExterno" class="control-label" />
													</div>
												</div>
											</spring:bind>
											<!-- input Email externo -->
											<spring:bind path="emailExterno">
												<div class="form-horizontal ${status.error ? 'has-error' : ''}">
													<label class="col-sm-2 control-label">Email(*)</label>
													<div class="col-sm-10">
														<form:input path="emailExterno" class="form-control"
															maxlength="30"	readonly="true"
															id="txtEmailExterno" placeholder="Email Externo" />
														<form:errors path="emailExterno" class="control-label" />
														<span id="lblError" style="color: red"></span>
													</div>
												</div>
											</spring:bind>
										</div>
									</div>
									<div class="col-sm-2"></div>
								</div>
							</div>
							<spring:bind path="flagBloqueo">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Bloquear Usuario?</label>
									<div class="col-sm-2">
										<form:select path="flagBloqueo" class="form-control" disabled="true">
											<form:options items="${bloqueos}" />
										</form:select>
										<form:errors path="flagBloqueo" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>
<!-- 							fin opcional externo -->
<!-- 							Fin REQ 213 -->
							
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label class="col-sm-2 control-label">Usuario(*)</label>
								<div class="col-sm-2">
									<form:input path="idUsuario" class="form-control"
										maxlength="15" id="txtusuario" placeholder="Usuario"
										disabled="true"  />
									<form:errors path="idUsuario" class="control-label" />
								</div>
								<div class="col-sm-8"></div>
							</div>


							<spring:bind path="sustentacion">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sustentación(*)</label>
									<div class="col-sm-6">
										<form:textarea path="sustentacion" rows="5"
											class="form-control" id="txtsustentacion"
											placeholder="Sustentación" readonly="true"
											 />
										<form:errors path="sustentacion" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							<spring:bind path="estado">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Estado(*)</label>
									<div class="col-sm-2">
										<form:select path="estado" class="form-control"
											disabled="true">
											<form:option value="-1" label="--- Select ---" />
											<form:options items="${estados}" />
										</form:select>
										<form:errors path="estado" class="control-label" />
									</div>
									<div class="col-sm-8"></div>
								</div>
							</spring:bind>

							<spring:bind path="doc">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Documento(*)</label>
									<div class="col-sm-6">
										<form:input path="doc" class="form-control" id="txtdoc"
											placeholder="Documento" readonly="true"
											 />
										<form:errors path="doc" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>										
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
		function obtenerListaPerfilSistema(codSistema) {
			
			$.ajax({
		        type: "GET",
		        timeout: 50000,
		        beforeSend: function(){
		        	$('#spinner').show();
		          },
		        url: "${pageContext.request.contextPath}/usuario/getPerfilSistema",
		        data : { id : codSistema}, 
		        contentType: "application/json",
		        success: function(data){
		        	$('#selectPerfil').empty();  		        	
		        	var newOption = "<option value=" + "-1" + ">" + "--- Select ---" + "</option>";
				     $('#selectPerfil').append(newOption);
					for(var i = 0; i < data.length; i++){
					     var newOption = "<option value=" + data[i].value + ">" + data[i].value + " - " + data[i].text + "</option>";
					     $('#selectPerfil').append(newOption);
					 }
					$('#txtusuario').append("hola");
					 
		        },
		        error:function(xhr, thrownError){
		           //alert(xhr.status);
		           //alert(xhr.responseText);
		           //alert(thrownError);
		        },
		        complete: function(){
		        	$('#spinner').hide();
		        }
		   });			
		}
		
		$("#selectSistema").change(function(){
			var valor = $(this).val();
			//alert($("#selectSistema").serialize());
			var abv  = $(this).children(":selected").attr("id");
			
			obtenerListaPerfilSistema(valor);
			
			$('#txtusuario').val(abv);
		});
		
		/* $("#selectPerfil").change(function(){
			//alert($("#selectPerfil").serialize());
		}); */
		
		$("#search-ficha").click(function(e) {
			var valor= $("#txtcodficha").val();			
			var usuario = $('#txtusuario').val();
			buscarTrabajador(valor);	
			$('#txtusuario').val(usuario + valor);
		});
			
	</script>

</body>
</html>
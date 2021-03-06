<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sed" uri="/WEB-INF/seguridad.tld"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, shrink-to-fit=no, initial-scale=1">

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
	var sistemasTotal = ${sistemasTotal};
	var modo = ${modo};
 /*]]>*/
</script>

<script type="text/javascript">
$(document).ready(function() {
	console.log("prueba form");
    if($("#auxiliar").val() == 'true'){
   	 $("#file").attr('class','disable-click' );
    } else {
   	 $("#file").attr('class','');
    }
	
   	$("#file").change(function() {    
    	var file = this.value.substr(this.value.lastIndexOf('\\') + 1);       
       	document.getElementById('txtdoc').value = file;	
       
   	});
	inicializarVariables();
	cargarComponentes();
});

var ltaSistemasFinal = null;
var ltaAsociacionesFinal = null;

function inicializarVariables() {
	console.log("new/update usuario");
	var selectPerfilesAsociados = null;
	var selectSistemasAsociados = null;
	var cboSistemasElegidos = null;
}

function cargarComponentes(){
	selectPerfilesAsociados = $('#selectPerfilesAsociados');
	selectSistemasAsociados = $('#selectSistemasAsociados');
	cboSistemasElegidos = $('#cboSistemasElegidos');
	ltaPerfilesAsociados = perfilesAsociados;
	ltaAsociacionesFinal = ltaPerfilesAsociados;
	ltaSistemasAsociados = sistemasAsociados;
	for(var i=0; i<ltaSistemasAsociados.length; i++){
		ltaSistemasAsociados[i].valorLabel = ltaSistemasAsociados[i].codSistema + "-" + ltaSistemasAsociados[i].abreviatura 
	}
	ltaSistemasFinal = ltaSistemasAsociados;
	
	for(var i=0; i<sistemasTotal.length; i++){
		sistemasTotal[i].valorLabel = sistemasTotal[i].codSistema + "-" + sistemasTotal[i].abreviatura;
	}
	ltaSistemas = sistemasTotal;

	generarSistemas();
	actualizarPerfiles();
	acoplarFormulario();

	/*eval tipo usuario*/
	if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_INTERNO){
		$("#divExterno").hide()
	} else if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_EXTERNO){
		$("#divExterno").show()
	}

	formatearInputUsuario();
}

function generarSistemas(){
	if(modo == ConstantsServices.NUEVO){
		selectSistemasAsociados.empty();
		agregarListaItem(selectSistemasAsociados, ltaSistemas, "codSistema", "valorLabel");
		selectSistemasAsociados.multiSelect('destroy');
		selectSistemasAsociados.multiSelect({
			afterSelect: function(values){
			  construirCbSistemas(ConstantsServices.AGREGAR_SISTEMA, values);
			},
			afterDeselect: function(values){
			  eliminarPerfilesAsociados(values);	
			  construirCbSistemas(ConstantsServices.ELIMINAR_SISTEMA, values);
			}
				
		});
		selectSistemasAsociados.multiSelect('refresh');
	}else if(modo == ConstantsServices.EDITAR){
		selectSistemasAsociados.empty()
		agregarListaItem(selectSistemasAsociados, ltaSistemas, "codSistema", "valorLabel");
		var select = [];
		for(var i=0; i<ltaSistemasAsociados.length; i++){
			for(var j=0; j<ltaSistemas.length; j++){
				if(ltaSistemas[j].codSistema == ltaSistemasAsociados[i].codSistema){
					select.push(String(ltaSistemas[j].codSistema));
				}
			}
		}	
		selectSistemasAsociados.multiSelect('destroy');
		selectSistemasAsociados.multiSelect({
			afterSelect: function(values){
			  construirCbSistemas(ConstantsServices.AGREGAR_SISTEMA, values);
			},
			afterDeselect: function(values){
			  eliminarPerfilesAsociados(values);	
			  construirCbSistemas(ConstantsServices.ELIMINAR_SISTEMA, values);
			}
				
		});
		selectSistemasAsociados.multiSelect('select', select);
		selectSistemasAsociados.multiSelect('refresh');
		
	}
}

function eliminarPerfilesAsociados(arrayCodSistema){
	for(var j=0; j<arrayCodSistema.length; j++){
		for(var i=ltaAsociacionesFinal.length -1; i>=0; i--){
			if(ltaAsociacionesFinal[i].codSistema == parseInt(arrayCodSistema[j])){
				ltaAsociacionesFinal.splice(i, 1);
			}
		}
	}
	acoplarFormulario();
}

function construirCbSistemas(opcion, arrayValores){
	if(opcion == ConstantsServices.AGREGAR_SISTEMA){
		for(var i=0; i<sistemasTotal.length; i++){
			for(var j=0; j<arrayValores.length; j++){
				if(sistemasTotal[i].codSistema == arrayValores[j]){
					var flag = true;
					for(var k=0; k<ltaSistemasFinal.length; k++){
						if(ltaSistemasFinal[k].codSistema == sistemasTotal[i].codSistema){
							flag = false;
						}
					}
					if(flag){
						ltaSistemasFinal.push(sistemasTotal[i]);
					}
				}
			}	
		}
	}else{
		for(var j=0; j<arrayValores.length; j++){
			for(var i=0; i<ltaSistemasFinal.length; i++){
				if(ltaSistemasFinal[i].codSistema == arrayValores[j]){
					ltaSistemasFinal.splice(i,1);
				}
			}
		}
		
	} 
	if(ltaSistemasFinal != null){
		cboSistemasElegidos.empty();
		agregarListaItem(cboSistemasElegidos, ltaSistemasFinal, "codSistema", "valorLabel");
	}else{
		cboSistemasElegidos.empty();
	}	
	actualizarPerfiles();
}

function actualizarPerfiles(){
	var codSistema = cboSistemasElegidos.val();
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
        	generarPerfiles(data, codSistema);
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

function generarPerfiles(lista, codSistema){
	for(var i=0; i<lista.length; i++){
		lista[i].valorLabel = lista[i].value + "-" + lista[i].text;
	}
// 	if(modo == ConstantsServices.NUEVO){
// 		selectPerfilesAsociados.empty()
// 		agregarListaItem(selectPerfilesAsociados, lista, "value", "valorLabel");
		
// 		selectPerfilesAsociados.multiSelect('destroy');
// 		selectPerfilesAsociados.multiSelect({
// 			afterSelect: function(values){
// 				agregarPerfilAsociado(values);
// 			},
// 			afterDeselect: function(values){
// 				eliminarPerfilAsociado(values);
// 			}
				
// 		});
// 		selectPerfilesAsociados.multiSelect('select', select);
// 		selectPerfilesAsociados.multiSelect('refresh');
// 	}else if(modo == ConstantsServices.EDITAR){
// 	if(modo == ConstantsServices.EDITAR){		
		selectPerfilesAsociados.empty()
		agregarListaItem(selectPerfilesAsociados, lista, "value", "valorLabel");
		var select = [];
		for(var i=0; i<ltaAsociacionesFinal.length; i++){
			for(var j=0; j<lista.length; j++){
				if(ltaAsociacionesFinal[i].codSistema == codSistema && lista[j].value == ltaAsociacionesFinal[i].codPerfil){
					select.push(String(ltaAsociacionesFinal[i].codPerfil));
				}
			}
		}
		selectPerfilesAsociados.multiSelect('destroy');
		selectPerfilesAsociados.multiSelect({
			afterSelect: function(values){
				agregarPerfilAsociado(values);
			},
			afterDeselect: function(values){
				eliminarPerfilAsociado(values);
			}
				
		});
		selectPerfilesAsociados.multiSelect('select', select);
		selectPerfilesAsociados.multiSelect('refresh');
// 	}
}

function agregarPerfilAsociado(arrayCodPerfil){
	var codSistema = cboSistemasElegidos.val();
	for(var j=0; j<arrayCodPerfil.length; j++){
		var flag = true;
		for(var i=0; i<ltaAsociacionesFinal.length; i++){
			if(ltaAsociacionesFinal[i].codSistema == parseInt(codSistema) && ltaAsociacionesFinal[i].codPerfil == parseInt(arrayCodPerfil[j])){
				flag = false;
			}
		}
		if(flag){
			var objetoPerfil = {};
			objetoPerfil.codPerfil = parseInt(arrayCodPerfil[j]);
			objetoPerfil.codSistema = parseInt(codSistema);
			ltaAsociacionesFinal.push(objetoPerfil);
		}
	}
	acoplarFormulario();
}

function eliminarPerfilAsociado(arrayCodPerfil){
	var codSistema = cboSistemasElegidos.val();
	for(var j=0; j<arrayCodPerfil.length; j++){
		var flag = -100;
		for(var i=0; i<ltaAsociacionesFinal.length; i++){
			if(ltaAsociacionesFinal[i].codSistema == parseInt(codSistema) && ltaAsociacionesFinal[i].codPerfil == parseInt(arrayCodPerfil[j])){
				flag = i;
			}
		}
		if(flag != -100){
			ltaAsociacionesFinal.splice(flag, 1);
		}
	}
	acoplarFormulario();
}

function acoplarFormulario(){
	$("#perfilesAsociados").val(JSON.stringify(ltaPerfilesAsociados));
}

function cambiarTipoUsuario(){
	if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_INTERNO){
		$("#divExterno").hide()
	} else if(Number($("#codTipo").val()) == ConstantsServices.TIPO_USUARIO_EXTERNO){
		$("#divExterno").show()
	}
}

function formatearInputUsuario(){
	ponerClassConjunto("inputNumerico","txtDNIExterno",8,0,0);
}

</script>
<!-- fin adecuacion seguridad2 -->

<style type="text/css">
input[type=text] {
	text-transform: uppercase;
}

textarea {
	text-transform: uppercase;
}

.disable-click {
	pointer-events: none;
}
</style>
<script type="text/javascript">
// inicio adecuacion seguridad2
// $("document").ready(function(){
// 	console.log("prueba form");
// 	 //alert($("#auxiliar").val());
//      //
//      if($("#auxiliar").val() == 'true'){
//     	 $("#file").attr('class','disable-click' );
//     	 //alert(1);
//      } else {
//     	 //alert(2);
//     	 $("#file").attr('class','');
//      }
	
//     $("#file").change(function() {    
//         var file = this.value.substr(this.value.lastIndexOf('\\') + 1);       
//         document.getElementById('txtdoc').value = file;	
        
//     });
// });
// fin adecuacion seguridad2

function buscarTrabajador(valor){
	
	//$('#codFicha\\.errors').html('');
	//$('#codFicha\\.errors').empty();
	
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
		$('#trabajador-datos').empty();
    	$('#trabajador-dni').empty(); 
    	$('#trabajador-area').empty();
    	$('#trabajador-equipo').empty();
    	$('#trabajador-estado').empty();
    	$('#trabajador-codigo').empty();
    	$('#trabajador-estado').empty();
    	
    	//$('#codFicha.errors').empty();
						
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
	        		//alert('No existe informaci?n para la ficha ingresada.');
	        		
	        		
	        		$('#trabajador-datos').val('');
		        	$('#descripcion').val('');		        	
		        	$('#trabajador-dni').val('');
		        	$('#trabajador-area').val('');
		        	$('#codArea').val('');	        	
		        	$('#trabajador-codigo').val('');
		        	$('#trabajador-email').val('');
		        	$('#emailTrabajador').val('');
		        	$('#trabajador-equipo').val('');
		        	$('#trabajador-estado').val('');
		        	
	        		$.alert({
	        			title: 'Aviso!',
                        content: 'No existe informaci?n para la ficha ingresada.',
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
		        	$('#descripcion').val(descripcion);		        	
		        	$('#trabajador-dni').val(data['vDni']);
		        	$('#trabajador-area').val(data['nCodArea']);
		        	$('#codArea').val(data['nCodArea']);		        	
		        	$('#trabajador-codigo').val(data['nCodTrabajador']);
		        	$('#trabajador-email').val(data['vDirelectronica']);
		        	$('#emailTrabajador').val(data['vDirelectronica']);
		        	$('#trabajador-equipo').val(data['descArea']);
		        	$('#trabajador-estado').val(data['estadoTrabajador']);
		     		
		        	url =  data['data'];
		        	
		        	if(url == undefined){
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
	<input type="hidden" value="${rutaDocumentos}" id="rutadoc">
	<input type="hidden" value="${rutaImagenes}" id="rutafotos">
	<input type="hidden" value="${pageContext.request.contextPath}"
		id="url">
	<c:import url="../fragments/header.jsp" />

	<c:choose>
		<c:when test="${usuarioForm['readOnly']}">
			<c:set var="readonly" value="true" />
			<c:set var="readonly1" value="disabled=\"disabled\"" />
		</c:when>
		<c:otherwise>
			<c:set var="readonly" value="false" />
			<c:set var="readonly1" value="" />
		</c:otherwise>
	</c:choose>

	<input type="hidden" value="${readonly}" id="auxiliar">
	
<!-- 	inicio adecuacion seguridad2 -->
<!-- 	fin adecuaicon seguridad2 -->

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
							<c:when test="${empty  usuarioForm.codUsuario}">
								<c:set var="titulo" value="Agregar Usuario" />
							</c:when>
							<c:otherwise>
								<c:set var="titulo" value="Actualizar Usuario" />
							</c:otherwise>
						</c:choose>

						<h3>
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							&nbsp;&nbsp;&nbsp;&nbsp;${titulo}
						</h3>

						<br />

						<spring:url value="/usuario" var="userActionUrl" />

						<form:form class="form-horizontal" method="post" id="formid"
							modelAttribute="usuarioForm"
							action="${userActionUrl}?${_csrf.parameterName}=${_csrf.token}"
							enctype="multipart/form-data">		
							
<!-- 							inico adecuacion seguridad2 -->
<%-- 							<form:hidden path="sistemasAsociadosJSON" /> --%>
							<spring:bind path="sistemasPerfilesAsociadosJSON">
								<form:input type="hidden" path="sistemasPerfilesAsociadosJSON" id="perfilesAsociados"/>
							</spring:bind>
<!-- 							fin adecuacion seguridad2 -->
							
							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<div class="form-horizontal">
<%-- 										<spring:bind path="codSistema"> --%>
<%-- 											<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 												<label class="col-sm-2 control-label">Sistema(*)</label> -->
<!-- 												<div class="col-sm-6"> -->
<%-- 													<form:select id="selectSistema" path="codSistema" --%>
<%-- 														class="form-control"> --%>
<%-- 														<form:option value="-1" label="--- Select ---" /> --%>
<%-- 														<form:options items="${sistemas}" /> --%>
<%-- 														<c:forEach var="sistema" items="${sistemas}"> --%>
<%-- 															<form:option id="${sistema.id}" value="${sistema.value}" --%>
<%-- 																label="${sistema.label}" /> --%>
<%-- 														</c:forEach> --%>
<%-- 													</form:select> --%>
<%-- 													<form:errors path="codSistema" class="control-label" /> --%>
<!-- 												</div> -->
<!-- 												<div class="col-sm-4"></div> -->
<!-- 											</div> -->
<%-- 										</spring:bind> --%>
										<spring:bind path="sistemasAsociados">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-sm-2 control-label">Sistemas Asociados(*)</label>
												<div class="col-sm-6">
													<select multiple="multiple" id="selectSistemasAsociados" name="selectSistemasAsociados[]">
													</select>
												</div>
											</div>
										</spring:bind>
										<spring:bind path="sistemasAsociados">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-sm-2 control-label">Sistema para Perfiles</label>
												<div class="col-sm-6">
													<select class="form-control" id="cboSistemasElegidos" onchange="actualizarPerfiles()">
													</select>
												</div>
											</div>
										</spring:bind>
									</div>
								</c:when>
								<c:otherwise>

									<c:choose>
										<c:when test="${readonly}">
											<form:hidden path="codSistema" />
											<form:hidden path="sistemaNombre" />
											<div class="form-horizontal">
												<div class="row">
													<label class="col-sm-2 control-label">Sistema(*)</label>
													<div class="col-sm-6">
														<p class="form-control">${usuarioForm.codSistema}-
															${usuarioForm.sistemaNombre}</p>
													</div>
													<div class="col-sm-4"></div>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-horizontal">
<%-- 												<form:hidden path="codSistema" /> --%>
<%-- 												<spring:bind path="codSistema"> --%>
<%-- 													<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 														<label class="col-sm-2 control-label">Sistema(*)</label> -->
<!-- 														<div class="col-sm-6"> -->
<%-- 															<form:select id="selectSistema" path="codSistema" --%>
<%-- 																class="form-control" disabled="true"> --%>
<%-- 																<form:option value="-1" label="--- Select ---" /> --%>
<%-- 																<form:options items="${sistemas}" /> --%>
<%-- 																<c:forEach var="sistema" items="${sistemas}"> --%>
<%-- 																	<form:option id="${sistema.id}" --%>
<%-- 																		value="${sistema.value}" label="${sistema.label}" /> --%>
<%-- 																</c:forEach> --%>
<%-- 															</form:select> --%>
<%-- 															<form:errors path="codSistema" class="control-label" /> --%>
<!-- 														</div> -->
<!-- 														<div class="col-sm-4"></div> -->
<!-- 													</div> -->
<%-- 												</spring:bind> --%>
												<spring:bind path="sistemasAsociados">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label class="col-sm-2 control-label">Sistemas Asociados(*)</label>
														<div class="col-sm-6">
															<select multiple="multiple" id="selectSistemasAsociados" name="selectSistemasAsociados[]">
															</select>
														</div>
													</div>
												</spring:bind>
												<spring:bind path="sistemasAsociados">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label class="col-sm-2 control-label">Sistema para Perfiles</label>
														<div class="col-sm-6">
															<select class="form-control" id="cboSistemasElegidos" onchange="actualizarPerfiles()">
															</select>
														</div>
													</div>
												</spring:bind>
											</div>
										</c:otherwise>

									</c:choose>


								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<div class="form-horizontal">
<%-- 										<spring:bind path="codPerfil"> --%>
<%-- 											<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 												<label class="col-sm-2 control-label">Perfil(*)</label> -->
<!-- 												<div class="col-sm-6"> -->
<%-- 													<form:select id="selectPerfil" path="codPerfil" --%>
<%-- 														class="form-control"> --%>
<%-- 														<form:options items="${perfiles}" multiple="true" /> --%>
<%-- 													</form:select> --%>
<%-- 														<form:select id="selectPerfil" path="perfil" items="${perfiles}" 
<%-- 											multiple="true" class="form-control"  --%>
<%-- 											size="6"/>	--%>
<%-- 													<form:errors path="codPerfil" class="control-label" /> --%>
<%-- 													<form:errors path="codPerfil" class="control-label" /> --%>
<!-- 												</div> -->
<!-- 												<div class="col-sm-4"></div> -->
<!-- 											</div> -->
<%-- 										</spring:bind> --%>
										<spring:bind path="perfilesAsociados">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-sm-2 control-label">Perfiles Asociados(*)</label>
												<div class="col-sm-6">
													<select multiple="multiple" id="selectPerfilesAsociados" name="selectPerfilesAsociados[]">
													</select>
												</div>
											</div>
										</spring:bind>
									</div>
								</c:when>
								<c:otherwise>

									<c:choose>
										<c:when test="${readonly}">
											<form:hidden path="codPerfil" />

											<div class="form-horizontal">
												<div class="row">
													<label class="col-sm-2 control-label">Perfil(*)</label>
													<div class="col-sm-6">
														<p class="form-control">${usuarioForm.codPerfil}-
															${usuarioForm.perfilNombre}</p>
													</div>
													<div class="col-sm-4"></div>
												</div>
											</div>
										</c:when>
										<c:otherwise>
											<div class="form-horizontal">
<%-- 												<spring:bind path="codPerfil"> --%>
<%-- 													<div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<!-- 														<label class="col-sm-2 control-label">Perfil(*)</label> -->
<!-- 														<div class="col-sm-6"> -->
<%-- 															<form:select id="selectPerfil" path="codPerfil" --%>
<%-- 																class="form-control"> --%>
<%-- 																<form:options items="${perfiles}" multiple="true" /> --%>
<%-- 															</form:select> --%>
<%-- 																<form:select id="selectPerfil" path="perfil" items="${perfiles}" 
<%-- 											multiple="true" class="form-control"  --%>
<%-- 											size="6"/>	--%>
<%-- 															<form:errors path="codPerfil" class="control-label" /> --%>
<%-- 															<form:errors path="codPerfil" class="control-label" /> --%>
<!-- 														</div> -->
<!-- 														<div class="col-sm-4"></div> -->
<!-- 													</div> -->
<%-- 												</spring:bind> --%>
												<spring:bind path="perfilesAsociados">
													<div class="form-group ${status.error ? 'has-error' : ''}">
														<label class="col-sm-2 control-label">Perfiles Asociados(*)</label>
														<div class="col-sm-6">
															<select multiple="multiple" id="selectPerfilesAsociados" name="selectPerfilesAsociados[]">
															</select>
														</div>
													</div>
												</spring:bind>
											</div>
										</c:otherwise>

									</c:choose>


								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<div class="form-horizontal">
										<spring:bind path="codFicha">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-sm-2 control-label">Ficha(*)</label>
												<div class="col-sm-10">
													<div class="form-horizontal">
														<form:input path="codFicha" type="numeric" maxlength="5"
															class="form-control " id="txtcodficha"
															placeholder="Ficha" readonly="false"
															style="width:155px;max-width:155px;display:inline-block"
															onkeypress="return checkOnlyDigits(event);" />
														<span class="btn btn-primary glyphicon glyphicon-search"
															id="search-ficha"
															style="margin-left: -8px; margin-top: -5px; min-height: 36px;"></span>
														<form:errors path="codFicha" class="control-label" />
													</div>
												</div>
											</div>
										</spring:bind>
									</div>
								</c:when>
								<c:otherwise>
									<div class="form-horizontal">
										<spring:bind path="codFicha">
											<div class="form-group ${status.error ? 'has-error' : ''}">
												<label class="col-sm-2 control-label">Ficha(*)</label>
												<div class="col-sm-10">
													<div class="form-horizontal">
														<form:input path="codFicha" type="numeric" maxlength="5"
															class="form-control " id="txtcodficha"
															placeholder="Ficha" readonly="true"
															style="width:155px;max-width:155px;display:inline-block"
															onkeypress="return checkOnlyDigits(event);" />
														<c:choose>
															<c:when test="${not readonly}">
																<span class="btn btn-primary glyphicon glyphicon-search"
																	id="search-ficha"
																	style="margin-left: -8px; margin-top: -5px; min-height: 36px;"></span>
															</c:when>
															<c:otherwise>
																<span class="btn btn-primary glyphicon glyphicon-search"
																	id="search-fichano"
																	style="margin-left: -8px; margin-top: -5px; min-height: 36px;"></span>
															</c:otherwise>
														</c:choose>
														<form:errors path="codFicha" class="control-label" />
													</div>
												</div>
											</div>
										</spring:bind>
									</div>
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${usuarioForm['new']}">
								</c:when>
								<c:otherwise>
									<form:hidden path="codFicha" />
									<form:hidden path="codUsuario" />
									<script type="text/javascript">
									    	buscarTrabajador(${usuarioForm.codFicha});
									 </script>

								</c:otherwise>
							</c:choose>
							
							<c:choose>
								<c:when test="${usuarioForm['new']}">
									<div class="form-group">
										<div class="col-sm-2" ></div>
										<div class="col-sm-10" ><div style="font-style:italic; font-weight:bold;">*Debe seleccionar un trabajador que tenga email</div></div>
									</div>
								</c:when>
								<c:otherwise>									
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
											<form:hidden path="descripcion" />
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
											<form:hidden path="codArea" id="codArea" />
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
											<label class="col-sm-2 control-label">C?digo</label>
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
										<form:hidden path="emailTrabajador" />										
									</div>
								</div>
							</div>
							
							<!-- Inicio REQ 213 -->
							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<spring:bind path="codTipo">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Tipo(*)</label>
											<div class="col-sm-2">
												<form:select path="codTipo" class="form-control" onchange="cambiarTipoUsuario()">
													<form:options items="${tipos}" />
												</form:select>
												<form:errors path="codTipo" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
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
								</c:otherwise>
							</c:choose>
							
							<!-- inicio opcional externo -->
							<div id="divExterno" style="display: none;">
								<div class="form-group">
									<div class="col-sm-2" ></div>
									<div class="col-sm-10" ><div style="font-style:italic; font-weight:bold;">*Campos obligatorio para usuarios externos</div></div>
								</div>
								<div class="form-group">								
									<div class="col-sm-12">
										<div class="col-sm-10">
											<!-- combo empresas -->
											<c:choose>
												<c:when test="${empty  usuarioForm.codUsuario}">
													<spring:bind path="codEmpresa">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Empresa(*)</label>
															<div class="col-sm-10">
																<form:select path="codEmpresa" class="form-control">
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
												</c:when>
												<c:otherwise>
													<spring:bind path="codEmpresa">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Empresa(*)</label>
															<div class="col-sm-10">
																<form:select path="codEmpresa" class="form-control">
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
												</c:otherwise>
											</c:choose>
											<!-- input trabajador externo -->
											<c:choose>
												<c:when test="${usuarioForm['new']}">
													<spring:bind path="nombreExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Trabajador(*)</label>
															<div class="col-sm-10">
																<form:input path="nombreExterno" class="form-control"
																	maxlength="50" onkeypress="return alphaSpaceOnly(event);"
																	id="txtNombreExterno" placeholder="Trabajador Externo" />
																<form:errors path="nombreExterno" class="control-label" />
															</div>
														</div>
													</spring:bind>
												</c:when>
												<c:otherwise>
													<spring:bind path="nombreExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Trabajador(*)</label>
															<div class="col-sm-10">
																<form:input path="nombreExterno" class="form-control"
																	maxlength="50" onkeypress="return alphaSpaceOnly(event);"
																	id="txtNombreExterno" placeholder="Trabajador Externo" />
																<form:errors path="nombreExterno" class="control-label" />
															</div>
														</div>
													</spring:bind>
												</c:otherwise>
											</c:choose>
											<!-- input DNI externo -->
											<c:choose>
												<c:when test="${usuarioForm['new']}">
													<spring:bind path="dniExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">DNI(*)</label>
															<div class="col-sm-10">
																<form:input path="dniExterno" class="form-control txtDNIExterno"
																	maxlength="8"
																	id="txtDNIExterno" placeholder="DNI Externo" />
																<form:errors path="dniExterno" class="control-label" />
															</div>
														</div>
													</spring:bind>
												</c:when>
												<c:otherwise>
													<spring:bind path="dniExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">DNI(*)</label>
															<div class="col-sm-10">
																<form:input path="dniExterno" class="form-control txtDNIExterno"
																	maxlength="8"
																	id="txtDNIExterno" placeholder="DNI Externo" />
																<form:errors path="dniExterno" class="control-label" />
															</div>
														</div>
													</spring:bind>
												</c:otherwise>
											</c:choose>
											<!-- input Email externo -->
											<c:choose>
												<c:when test="${usuarioForm['new']}">
													<spring:bind path="emailExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Email(*)</label>
															<div class="col-sm-10">
																<form:input path="emailExterno" class="form-control"
																	maxlength="30"
																	id="txtEmailExterno" placeholder="Email Externo" />
																<form:errors path="emailExterno" class="control-label" />
																<span id="lblError" style="color: red"></span>
															</div>
														</div>
													</spring:bind>
												</c:when>
												<c:otherwise>
													<spring:bind path="emailExterno">
														<div class="form-horizontal ${status.error ? 'has-error' : ''}">
															<label class="col-sm-2 control-label">Email(*)</label>
															<div class="col-sm-10">
																<form:input path="emailExterno" class="form-control"
																	maxlength="30"
																	id="txtEmailExterno" placeholder="Email Externo" />
																<form:errors path="emailExterno" class="control-label" />
																<span id="lblError" style="color: red"></span>
															</div>
														</div>
													</spring:bind>
												</c:otherwise>
											</c:choose>	
										</div>
									</div>
									<div class="col-sm-2"></div>
								</div>
							</div>
							<!-- fin opcional externo -->
							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<spring:bind path="flagBloqueo">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Bloquear Usuario?</label>
											<div class="col-sm-2">
												<form:select path="flagBloqueo" class="form-control">
													<form:options items="${bloqueos}" />
												</form:select>
												<form:errors path="flagBloqueo" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="flagBloqueo">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Bloquear Usuario?</label>
											<div class="col-sm-2">
												<form:select path="flagBloqueo" class="form-control">
													<form:options items="${bloqueos}" />
												</form:select>
												<form:errors path="flagBloqueo" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>
							
							<!-- Fin REQ 213 -->

							<c:choose>
								<c:when test="${usuarioForm['new']}">
									<spring:bind path="idUsuario">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Usuario(*)</label>
											<div class="col-sm-2">
												<form:input path="idUsuario" class="form-control"
													maxlength="15" onkeypress="return alphaOnlyNumeric(event);"
													id="txtusuario" placeholder="Usuario" />
												<form:errors path="idUsuario" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<form:hidden path="idUsuario" />
									<div class="row">
										<label class="col-sm-2 control-label">Usuario(*)</label>
										<div class="col-sm-2">
											<p class="form-control">${usuarioForm.idUsuario}</p>
										</div>
										<div class="col-sm-8"></div>
									</div>
									<!-- <script type="text/javascript">
										buscarTrabajador(${codFicha});					
							    	</script> -->
								</c:otherwise>
							</c:choose>


							<c:choose>
								<c:when test="${usuarioForm['new']}">
									<spring:bind path="sustentacion">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Sustentaci?n(*)</label>
											<div class="col-sm-6">
												<form:textarea maxlength="500" path="sustentacion" rows="5"
													class="form-control" id="txtsustentacion"
													placeholder="Sustentaci?n" />
												<form:errors path="sustentacion" class="control-label" />
											</div>
											<div class="col-sm-4"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="sustentacion">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Sustentaci?n(*)</label>
											<div class="col-sm-6">
												<form:textarea maxlength="500" readonly="${readonly}"
													onkeypress="return alphaOnly(event);" path="sustentacion"
													rows="5" class="form-control" id="txtsustentacion"
													placeholder="Sustentaci?n" />
												<form:errors path="sustentacion" class="control-label" />
											</div>
											<div class="col-sm-4"></div>
										</div>
									</spring:bind>

								</c:otherwise>
							</c:choose>

							<c:choose>
								<c:when test="${empty  usuarioForm.codUsuario}">
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Estado(*)</label>
											<div class="col-sm-2">
												<form:select path="estado" disabled="true"
													class="form-control">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:when>
								<c:otherwise>
									<spring:bind path="estado">
										<div class="form-group ${status.error ? 'has-error' : ''}">
											<label class="col-sm-2 control-label">Estado(*)</label>
											<div class="col-sm-2">
												<form:select path="estado" class="form-control">
													<form:options items="${estados}" />
												</form:select>
												<form:errors path="estado" class="control-label" />
											</div>
											<div class="col-sm-8"></div>
										</div>
									</spring:bind>
								</c:otherwise>
							</c:choose>




							<spring:bind path="doc">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Documento(*)</label>
									<div class="col-sm-6">
										<form:input path="doc" class="form-control" id="txtdoc"
											placeholder="Documento" readonly="true" />
										<form:errors path="doc" class="control-label" />
									</div>
									<div class="col-sm-4"></div>
								</div>
							</spring:bind>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="file" name="file" id="file" size="2048"
										class="upload-file" accept=".pdf,.doc,.docx" />


								</div>
							</div>
							<br />
							<br />





							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${userActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<c:choose>
											<c:when test="${usuarioForm['new']}">
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
				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
		<input type="hidden" id="abreviaturaSistema" />
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
			$('#abreviaturaSistema').val(abv);
			var valor= $("#txtcodficha").val();
			if(abv == undefined){
				abv = '';				
			}
			
			$('#txtusuario').val(abv + valor);
		});
		
		/* $("#selectPerfil").change(function(){
			//alert($("#selectPerfil").serialize());
		}); */
		
		$("#search-ficha").click(function(e) {
			var valor= $("#txtcodficha").val();
			var descripcion="";
			var sistema = $('#selectSistema').val();
			var abv  = $('#selectSistema').children(":selected").attr("id");
			if(abv == undefined){
				abv = '';
			}
			buscarTrabajador(valor);				
			$('#txtusuario').val(abv + valor);
		});

		$("#txtEmailExterno").on("keyup", function() {
			var lblError = document.getElementById("lblError");
			lblError.innerHTML = "";
			if(!ValidEmail($("#txtEmailExterno"))){
				lblError.innerHTML = "Direcci?n de correo invalido.";
			}
		});

		$('#formid').on('submit', function() {
		    $('#codTipo').prop('disabled', false);
		});
		
	</script>

</body>
</html>
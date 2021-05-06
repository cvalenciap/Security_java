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

<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Scripts/multi-select-master/css/multi-select.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/multi-select-master/js/jquery.multi-select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.js"></script>
<script type="text/javascript">
 /*<![CDATA[*/ 
	var sistemasTotal = ${sistemasTotal};
 /*]]>*/
</script>
<script type="text/javascript">
$(document).ready(function() {
	inicializarVariables();
	cargarComponentes();
});

function inicializarVariables() {
	console.log("asociaciones LDAP");
	var selectPerfilesAsociados = null;
	var selectSistemasAsociados = null;
	var cboSistemasElegidos = null;
	var txtUsuarioLDAP = null;

	var ltaSistemasFinal = null;
	var ltaAsociacionesFinal = null;
	var modo = null;
}

function cargarComponentes(){
	selectPerfilesAsociados = $('#selectPerfilesAsociados');
	selectSistemasAsociados = $('#selectSistemasAsociados');
	cboSistemasElegidos = $('#cboSistemasElegidos');
	txtUsuarioLDAP = $("#txtUsuarioLDAP");

	for(var i=0; i<sistemasTotal.length; i++){
		sistemasTotal[i].valorLabel = sistemasTotal[i].codSistema + "-" + sistemasTotal[i].abreviatura;
	}
	ltaSistemas = sistemasTotal;
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
}

function busquedaUsuarioLDAP(){
	if(txtUsuarioLDAP.val() == ""){
		mostrarMensaje("Usuario vacio.", ConstantsServices.MENSAJE_DANGER);
	}else{
		$.ajax({
			type: "GET",
	        timeout: 50000,
	        beforeSend: function(){
	        	$('#spinner').show();
	          },
	        url: "${pageContext.request.contextPath}/asociacionLDAP/getUsuarioLDAP",
	        data : { 
		        codUsuarioLDAP : txtUsuarioLDAP.val()
		    }, 
	        contentType: "json",
	        complete: function(){
	        	$('#spinner').hide();
	        }
	   	}).done(function(respuestaBean){
	   		respuestaBean = JSON.parse(respuestaBean);
	   		if(respuestaBean.estadoRespuesta ==  ConstantsServices.OK ){
	   			mostrarMensaje(respuestaBean.mensajeRespuesta, ConstantsServices.MENSAJE_SUCCESS);
	   			modo = respuestaBean.parametros.modo;
	   			agregarInformacionLDAP(respuestaBean.parametros.perfilesAsociados, respuestaBean.parametros.sistemasAsociados, respuestaBean.parametros.usuarioLDAP);
	        } else {
	        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstantsServices.MENSAJE_DANGER);
	        	$("#validacionBusqueda").hide();
	        }
		}).fail(function(jqXHR, textStatus, errorThrown) {
	    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstantsServices.MENSAJE_SESION_EXPIRADA);
	    });
	}
}

function agregarInformacionLDAP(perfilesAsociados, sistemasAsociados, usuarioLDAP){
	$("#validacionBusqueda").show();
	
	ltaPerfilesAsociados = perfilesAsociados;
	ltaAsociacionesFinal = ltaPerfilesAsociados;
	ltaSistemasAsociados = sistemasAsociados;
	for(var i=0; i<ltaSistemasAsociados.length; i++){
		ltaSistemasAsociados[i].valorLabel = ltaSistemasAsociados[i].codSistema + "-" + ltaSistemasAsociados[i].abreviatura 
	}
	ltaSistemasFinal = ltaSistemasAsociados;
	
	generarSistemas();
	actualizarPerfiles();
}

function grabarAsociacionesLDAP(){
	if(ltaPerfilesAsociados.length == ConstantsServices.EMPTY_NUMBER){
		mostrarMensaje("Seleccione asociaciones para grabar.", ConstantsServices.MENSAJE_DANGER);
		$('#spinner').hide();
	}else{
		generarModalConfirmacion();
	}
}

function grabarDataAsociacion(){
	$.ajax({
		type: "GET",
        timeout: 50000,
        beforeSend: function(){
        	$('#spinner').show();
          },
        url: "${pageContext.request.contextPath}/asociacionLDAP/grabarUsuarioLDAP",
        data : { 
	        codUsuarioLDAP : txtUsuarioLDAP.val(),
	        ltaPerfilesAsociados : JSON.stringify(ltaPerfilesAsociados)
	    }, 
        contentType: "json",
        complete: function(){
        	$('#spinner').hide();
        }
   	}).done(function(respuestaBean){
   		respuestaBean = JSON.parse(respuestaBean);
   		if(respuestaBean.estadoRespuesta ==  ConstantsServices.OK ){
   			mostrarMensaje(respuestaBean.mensajeRespuesta, ConstantsServices.MENSAJE_SUCCESS);
        } else {
        	mostrarMensaje(respuestaBean.mensajeRespuesta, ConstantsServices.MENSAJE_DANGER);
        }
	}).fail(function(jqXHR, textStatus, errorThrown) {
    	validarFinDeSesion(jqXHR, textStatus, errorThrown, ConstantsServices.MENSAJE_SESION_EXPIRADA);
    });
}

</script>

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
<body>
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
						
						<div id="divMensajeInformacion"></div>
						<h3 style="text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							Asociación de Usuarios LDAP
						</h3>
						<br/>
						
						<spring:url value="/menu" var="asociacionLDAPActionUrl" />
						
						<div class="form-horizontal">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label class="col-sm-2 control-label">Usuario LDAP(*)</label>
								<div class="col-sm-10">
									<div class="form-horizontal">
										<input maxlength="15"
											class="form-control" type="text" id="txtUsuarioLDAP"
											placeholder="Usuario LDAP"
											style="width:155px;max-width:155px;display:inline-block"
											onkeypress="return alphaOnlyNumeric(event);" />											
										<span class="btn btn-primary glyphicon glyphicon-search"
											id="search-usuario"
											style="margin-left: -8px; margin-top: -5px; min-height: 36px;" onclick="busquedaUsuarioLDAP()"></span>
									</div>
								</div>
							</div>
						</div>
						
						<div id="validacionBusqueda" hidden>	
							<div class="form-horizontal">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sistemas Asociados(*)</label>
									<div class="col-sm-6">
										<select multiple="multiple" id="selectSistemasAsociados" name="selectSistemasAsociados[]">
										</select>
									</div>
								</div>
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Sistema para Perfiles</label>
									<div class="col-sm-6">
										<select class="form-control" id="cboSistemasElegidos" onchange="actualizarPerfiles()">
										</select>
									</div>
								</div>
							</div>
							<div class="form-horizontal">
								<div class="form-group ${status.error ? 'has-error' : ''}">
									<label class="col-sm-2 control-label">Perfiles Asociados(*)</label>
									<div class="col-sm-6">
										<select multiple="multiple" id="selectPerfilesAsociados" name="selectPerfilesAsociados[]">
										</select>
									</div>
								</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<div class="text-right btn-toolbar">
										<a class="btn btn-primary" href="${asociacionLDAPActionUrl}"> <i
											class="glyphicon glyphicon-share-alt"></i> Retornar
										</a>
										<span id="botonGrabarAsociaciones" onclick="grabarAsociacionesLDAP()"
											class="btn btn-primary">
											<i class="glyphicon glyphicon-floppy-disk"></i>
											&nbsp;Grabar
										</span>
									</div>
								</div>
							</div>
							
						</div>
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
</body>
</html>
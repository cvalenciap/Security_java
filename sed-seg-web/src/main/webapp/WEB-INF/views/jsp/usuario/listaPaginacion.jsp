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

<style type="text/css">
.close-image img {
	display: block;
	height: 15px;
	width: 15px;
}
span:hover {
	cursor: pointer;
}

</style>

<script type="text/javascript">
$(document).ready(function() {	
	$("#chkCodigo").attr('checked', 'checked');
	
	$("#cpSearch").keypress(function(e) {
	    if(e.which == 13) {
	    	$('#mytable1').DataTable().ajax.reload();
	    }
	});
	var table = $("#mytable1").DataTable({
		"serverSide": true,
		"searching": false,
		"stateSave" : true,
		"ordering" : false,		
		"fnDrawCallback": function (oSettings) {
	        
	       	 $("#mytable1 tbody tr").on('mouseenter', function(event) {
	  			$("#mytable1 tbody tr").removeClass('row_selected');
	  			$(this).addClass('row_selected');
	  		});
	       	$('#spinner').hide();
    	},
		//"displayLength": 5,
		"ajax": {
        	"url" : "${pageContext.request.contextPath}/usuario/getUsuarioListado",
        	"data": function ( d ) {
            	d.cpTipo = $('input[name=chkBusqueda]:checked').val();
            	d.cpSearch = $('#cpSearch').val();
            }
        },
        "dom" : '<"top"f><rt><"bottom"lip><"clear">',
		"language" : {
			"url" : "${pageContext.request.contextPath}/resources/core/languaje/spanish.json",
		},
		"lengthMenu" : [ [10, 20, 30, 40, 50, 100],[10, 20, 30, 40, 50, 100] ],
		"processing": false		
        
	});	
	 /*
	 $(document).ready(function() {
		    var table = $('#mytable1').dataTable();
		    table.on('click', 'th', function() {
		        var info = table.fnSettings().aaSorting;
		        var idx = info[0][0];
		        //alert(idx);
		    });
		});*/
	 
	 $('#mytable1 tbody').on('click', '.btn-update', function (e) {
			var data = table.row( $(this).parents('tr') ).data();
// 			inicio adecuacion seguridad2
// 			var url = "${pageContext.request.contextPath}/usuario/update/" + data[0] + "," + data[1];
			var url = "${pageContext.request.contextPath}/usuario/update/" + data[0];
// 			fin adecuacion seguridad2			
			$('#spinner').show();
			$(location).attr('href',url);
	  	});
	 
	 $('#mytable1 tbody').on('click', '.btn-delete', function (e) {
			var data = table.row( $(this).parents('tr') ).data();
// 			inicio adecuacion seguridad2
// 			var url = "${pageContext.request.contextPath}/usuario/delete/" + data[0] + "," + data[1];
			var url = "${pageContext.request.contextPath}/usuario/delete/" + data[0];
// 			fin adecuacion seguridad2
			//alert(url);
			$.confirm({
				title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
				useBootstrap : true,
				content : '\u00BFDesea eliminar la informaci\u00F3n\?',
				icon : 'fa fa-spinner fa-spin',
				animation : 'scale',
				closeAnimation : 'scale',
				opacity : 0.5,
				autoClose : false,
				buttons : {
					'confirm' : {
						text : 'Aceptar',
						btnClass : 'btn btn-primary',
						action : function() {							
							$('#spinner').show();
							$(location).attr('href',url);
						}
					},
					cancelar : function() {
						$('#spinner').hide();
					},
				}
			});
			return false;
			
	  	});
	 
	 
	 $('#mytable1 tbody').on('click', '.btn-blocked', function (e) {
			var data = table.row( $(this).parents('tr') ).data();
// 			inicio adecuacion seguridad2
			var url = "${pageContext.request.contextPath}/usuario/desbloquear/" + data[1] + "," + data[0];
// 			var url = "${pageContext.request.contextPath}/usuario/desbloquear/" + data[0];
// 			fin adecuacion seguridad2	
		    
			$.confirm({
				title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
				useBootstrap : true,
				content : '\u00BFDesea desbloquear el usuario\?',
				icon : 'fa fa-spinner fa-spin',
				animation : 'scale',
				closeAnimation : 'scale',
				opacity : 0.5,
				autoClose : false,
				buttons : {
					'confirm' : {
						text : 'Aceptar',
						btnClass : 'btn btn-primary',
						action : function() {
							$('#spinner').show();
							$(location).attr('href',url);
						}
					},
					cancelar : function() {
						$('#spinner').hide();
					},
				}
			});
			return false;
			
			
	  	});
	 
	 $('#mytable1 tbody').on('click', '.btn-disabled', function (e) {
			var data = table.row( $(this).parents('tr') ).data();
// 			inicio adecuacion seguridad2
// 			var url = "${pageContext.request.contextPath}/usuario/activar/" + data[1] + "," + data[0];
			var url = "${pageContext.request.contextPath}/usuario/activar/" + data[0];
// 			fin adecuacion seguridad2	
		    
		
			
			$.confirm({
				title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
				useBootstrap : true,
				content : '\u00BFDesea activar el usuario\?',
				icon : 'fa fa-spinner fa-spin',
				animation : 'scale',
				closeAnimation : 'scale',
				opacity : 0.5,
				autoClose : false,
				buttons : {
					'confirm' : {
						text : 'Aceptar',
						btnClass : 'btn btn-primary',
						action : function() {
							$('#spinner').show();
							$(location).attr('href',url);
						}
					},
					cancelar : function() {
						$('#spinner').hide();
					},
				}
			});
			return false;
			
	  	});
	 
	 	
   	$('#mytable1 tbody').on('click', '.btn-view', function (e) {
     	var data = table.row( $(this).parents('tr') ).data();
//      	inicio adecuacion seguridad2
//      	var url = "${pageContext.request.contextPath}/usuario/" + data[0] + "," + data[1];
     	var url = "${pageContext.request.contextPath}/usuario/" + data[0];
//      	fin adecuacion seguridad2     	
     	console.log(url);
     	$(location).attr('href',url);
  	});
   	
	$('#mytable1 tbody').on('click', '.btn-acciones', function (e) {
     	var data = table.row( $(this).parents('tr') ).data();
     	//alert(data[1]);
     	var url = "${pageContext.request.contextPath}/usuario/acciones/" + data[1];     	
     	console.log(url);
     	$(location).attr('href',url);
  	});

	
// 	inicio adecuacion seguridad2
	$('#mytable1 tbody').on('click', '.btn-detail', function (e) {
     	var data = table.row( $(this).parents('tr') ).data();
		redireccionModal('./cargarVentanaVerModalDetalleUsuario', {usuarioBean:JSON.stringify(data)}, $('#verModalDetalleUsuario'));
  	});

// 	fin adecuacion seguridad2

});

</script>
</head>
<body>
	<c:import url="../fragments/header.jsp" />
	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<c:import url="../fragments/menu.jsp" />
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- 
		<script type="text/javascript">
			listarDatos();
		</script> 
		-->
		<!-- <input type="button" value="Refrescar" id="MyButton" > -->

		<!-- Page Content -->
		<div id="page-content-wrapper">
			<div class="container-fluid">
				<div class="row"></div>
				<br />
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
							Lista de Usuarios
							<sed:seguridad accion="/usuarioCREAR">
							<button class="btn btn-primary pull-right"
								onclick="location.href='usuario/add'">
								<!--  <i class="glyphicon glyphicon-plus"></i>-->
								&nbsp;&nbsp;Agregar&nbsp;
							</button>
							</sed:seguridad>
						</h3>

						<div class="col-md-12 col-sm-12 col-xs-12 form-group no-padding">
							<h4>Buscar</h4>
						</div>
						
						<div class="col-md-12 no-padding">
							<div class="col-md-2 col-sm-3 col-xs-2 no-padding form-group-sm">
								<input type="radio" name="chkBusqueda" id="chkCodigo" value="C"/>
								<label for="chkCodigo" class="control-label label-check" >Usuario</label>
							</div>
							<div class="col-md-2 col-sm-3 col-xs-3 no-padding">	
								<input type="radio" name="chkBusqueda" id="chkDescri" value="D"/>
								<label for="chkDescri" class="control-label label-check" >Descripci&oacute;n</label>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-5 no-padding form-group">
							
								<input type="text" id="cpSearch"  
									   maxlength="80" class="form-control"/>
							</div>
						</div>
						
						<spring:url value="${pageContext.request.contextPath}/accionform"
							var="userActionUrl" />

						<br />
						<div class="table-wrapper">
						

						
						<table id="mytable1"
							class="estandardTable table table-striped table-bordered table-hover "
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>USUARIO</th>
									<th>DESCRIPCION</th>
									<th>FICHA</th>
									<th>AREA</th>
									<th>ESTADO</th>
									<th>TIPO</th>
									<th>AREA DESCRIPCI?N</th>
									<th>AREA ABREVIATURA</th>
									<th>AREA TRABAJADOR</th>
									<th>ESTADO TRABAJADOR</th>
									<th>CORREO</th>
									<th>ASOCIACION</th>
									<th>ACCIONES</th>
								</tr>
							</thead>

							<%-- <c:forEach var="test" items="${pagetest}">
								<tr>
									<td>${test.codPerfilAccion}</td>
									<td>${test.codPerfil}</td>
									<td>${test.codSistema}</td>
									<td>${test.codAccionFormulario}</td>									
								</tr>
							</c:forEach> --%>
						</table>
<script type="text/javascript">
/* $(function(){
  $('#mytable1').tablesorter(); 
}); */
</script>
</div>
					</div>
				</div>
			</div>
<!-- 			inicio adecuacion seguridad2 -->
			<div class="modal fade" id="verModalDetalleUsuario" data-keyboard="false" data-backdrop="static" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"></div>
<!-- 			fin adecuacion seguridad2 -->
			
		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<c:import url="../fragments/loader.jsp" />
	<c:import url="../fragments/footer.jsp" />



</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

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
<meta name="viewport" content="width=device-width, shrink-to-fit=no, initial-scale=1">

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
	$("#inputSearch").keypress(function(e) {
	    if(e.which == 13) {
	    	$('#mytable1').DataTable().ajax.reload();
	    }
	});
	//alert("DOM...");
	//listar();
	 var table = $("#mytable1").DataTable({
		"serverSide": true,
		"searching": false,
		"stateSave" : true,
		"ordering" : false,
		"initComplete": function(settings, json) {
//             $('#mytable1_filter input').unbind();
//             $('#mytable1_filter input').bind('keyup', function(e) {
//                 if(e.keyCode == 13) {
//                 	$('#spinner').show();
//                 	table.search( this.value.toUpperCase() ).draw();                	
//                 }                
//             }); 
            $("#mytable1 tbody tr").on('mouseenter', function(event) {
    			$("#mytable1 tbody tr").removeClass('row_selected');
    			$(this).addClass('row_selected');
    		});
        },
        "fnDrawCallback": function (oSettings) {
	        	 $("#mytable1 tbody tr").on('mouseenter', function(event) {
	     			$("#mytable1 tbody tr").removeClass('row_selected');
	     			$(this).addClass('row_selected');
	     		});
	        	 $('#spinner').hide();
        	},
		//"displayLength": 5,
		"ajax": {
        	"url" : "${pageContext.request.contextPath}/opcionform/getFormularioListado",
        	"data": function ( d ) {
            	d.tipoBusqueda = $('input[name=typeSearch]:checked').val();
            	d.valorBusqueda = $('#inputSearch').val();
            }
        },
//         "dom" : '<"top"f><rt><"bottom"lip><"clear">',
		"dom" : '<"top"f><rt><"bottom"lip><"clear">',
		"language" : {
			"url" : "${pageContext.request.contextPath}/resources/core/languaje/spanish.json",
		},
		"lengthMenu" : [ [10, 20, 30, 40, 50, 100],[10, 20, 30, 40, 50, 100] ],
		"processing": false
         
	});	
	 	
	 $("#mytable1 tbody tr").on('mouseenter', function(event) {
			$("#mytable1 tbody tr").removeClass('row_selected');
			$(this).addClass('row_selected');
		});
	 
   	$('#mytable1 tbody').on('click', '.btn-view', function (e) {
     	var data = table.row( $(this).parents('tr') ).data();
     	var url = "${pageContext.request.contextPath}/opcionform/" + data[0] + "," + data[2] + "," + data[4];
     	$('#spinner').show();     	
     	$(location).attr('href',url);
  	});

	$('#mytable1 tbody').on('click', '.btn-update1', function (e) {
		var data = table.row( $(this).parents('tr') ).data();
		var url = "${pageContext.request.contextPath}/opcionform/update/" + data[0] + "," + data[2] + "," + data[4] + ", 0";
		
		$('#spinner').show();
		$(location).attr('href',url);
  	});
	

	$('#mytable1 tbody').on('click', '.btn-update2', function (e) {
		var data = table.row( $(this).parents('tr') ).data();
		var url = "${pageContext.request.contextPath}/opcionform/update/" + data[0] + "," + data[2] + "," + data[4] + ", 1";
		$('#spinner').show();
		$(location).attr('href',url);
  	});
	

	
	$('#mytable1 tbody').on('click', '.btn-delete', function (e) {
		var data = table.row( $(this).parents('tr') ).data();
		var url = "${pageContext.request.contextPath}/opcionform/delete/" + data[0] + "," + data[2] + "," + data[4];
	    
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
				<div class="row">
					
				</div>
				<br/>
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

						<h3 style = "text-decoration: underline">
							<span class="glyphicon glyphicon-tasks" id="menu-toggle"></span>
							Lista de Formularios 
							<sed:seguridad accion="/opcionformCREAR">
							<button class="btn btn-primary pull-right"
								onclick="location.href='opcionform/add'">
								<!-- <i class="glyphicon glyphicon-plus"></i>-->
								&nbsp;&nbsp;Agregar&nbsp;
							</button> 
							</sed:seguridad>
						</h3>
						
						<div class="col-md-12" style="padding-top: 10px;">
							<h4>Buscar</h4>
						</div>
						
						<div class="col-md-12" style="margin-bottom: 20px;">
							<div class="col-md-3">
								&nbsp;&nbsp; 
								<label><input type="radio" checked="checked" name="typeSearch" value="C">Sistema</label>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<label><input type="radio" name="typeSearch" value="D">Descripci&oacute;n</label>
							</div>
							<div class="col-md-4">
								<input type="text" id="inputSearch"  
									maxlength="40" class="form-control"/>
							</div>
						</div>

						<spring:url value="${pageContext.request.contextPath}/opcionform"
							var="userActionUrl" />

						<br />
						<div class="table-wrapper">
						<table id="mytable1"
							class="estandardTable table table-striped table-bordered table-hover "
							cellspacing="0" width="100%">
							<thead>
								<tr>
									<th>COD. SISTEMA.</th>									 
									<th>SISTEMA</th>
									<th>COD. MODULO.</th>
									<th>MODULO</th>
									<th>COD.</th>
									<th>DESCRIPCION</th>
									<th>ESTADO</th>
									<th>URL FORMULARIO</th>
									<th>USUARIO ACT.</th>
									<th>FECHA ACT.</th>
									<th>ACCIONES</th>
								</tr>
							</thead>
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
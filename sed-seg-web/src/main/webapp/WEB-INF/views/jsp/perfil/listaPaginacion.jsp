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
/*
$(document).ready(function() {
    var table = $('#mytable1').dataTable();
    table.on('click', 'th', function() {
        var info = table.fnSettings().aaSorting;
        var idx = info[0][0];
        alert(idx);
    });
});*/

	$(document).ready(function() {
	//alert("DOM...");
	//listar();
	$("#chkCodigo").attr('checked', 'checked');

	$("#cpSearch").keypress(function(e) {
    if(e.which == 13) {
    	$('#mytable1').DataTable().ajax.reload();
    }
	});
		 var table = $("#mytable1").DataTable({
		"serverSide": true,
		"searching": false,
		"fixedHeader":true,
		"ordering" : false,
// 		cambio busqueda por dos campos codigoperfil y descripcion de perfil
// 		"initComplete": function(settings, json) {
//             $('#mytable1_filter input').unbind();
//             $('#mytable1_filter input').bind('keyup', function(e) {
//                 if(e.keyCode == 13) {
//                 	$('#spinner').hide();
//                 	table.search( this.value.toUpperCase() ).draw();
//                  	table.codigo( this.value.toUpperCase() ).draw();
//                 }
//             }); 
//             $("#mytable1 tbody tr").on('mouseenter', function(event) {
//     			$("#mytable1 tbody tr").removeClass('row_selected');
//     			$(this).addClass('row_selected');
//     		});
//         }
		 "fnDrawCallback": function (oSettings) {
        	        	        
	       	 $("#mytable1 tbody tr").on('mouseenter', function(event) {
	  			$("#mytable1 tbody tr").removeClass('row_selected');
	  			$(this).addClass('row_selected');
	  		});
	       	$('#spinner').hide();
     	},
		//"displayLength": 5,
		"ajax": {
        	"url" : "${pageContext.request.contextPath}/perfil/getPerfilListado",
        	"data": function ( d ) {
            	d.cpTipo = $('input[name=chkBusqueda]:checked').val();
            	d.cpSearch = $('#cpSearch').val();
            }
        },
        "dom" : '<"top"f><rt><"bottom"lip><"clear">',
		"language" : {
			"url" : "${pageContext.request.contextPath}/resources/core/languaje/spanish.json",
		},
		"lengthMenu" : [ [10, 20, 30, 40, 50, 100],
				[10, 20, 30, 40, 50, 100] ],
		"processing": false,
         "stateSave" : true
	});	
	 	   
   	$('#mytable1 tbody').on('click', '.btn-view', function (e) {
     	var data = table.row( $(this).parents('tr') ).data();
     	var url = "${pageContext.request.contextPath}/perfil/" + data[2] + "," + data[0];
     	$('#spinner').show();
     	console.log(url);
     	$(location).attr('href',url);
  	});

	$('#mytable1 tbody').on('click', '.btn-update', function (e) {
		var data = table.row( $(this).parents('tr') ).data();
		var url = "${pageContext.request.contextPath}/perfil/update/" + data[2] + "," + data[0];
		$('#spinner').show();
		$(location).attr('href',url);
  	});
	
	$('#mytable1 tbody').on('click', '.btn-delete', function (e) {
		var data = table.row( $(this).parents('tr') ).data();
		var url = "${pageContext.request.contextPath}/perfil/delete/" + data[2] + "," + data[0];
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
							Lista de Perfiles
							<sed:seguridad accion="/perfilCREAR">
								<button class="btn btn-primary pull-right"
									onclick="location.href='perfil/add'">
									<!--<i class="glyphicon glyphicon-plus"></i>-->
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
								<label for="chkCodigo" class="control-label label-check" >Sistema</label>
							</div>
							<div class="col-md-2 col-sm-3 col-xs-3 no-padding">	
								<input type="radio" name="chkBusqueda" id="chkDescri" value="D"/>
								<label for="chkDescri" class="control-label label-check" >Perfil</label>
							</div>
							<div class="col-md-3 col-sm-4 col-xs-5 no-padding form-group">
								<input type="text" id="cpSearch"  
									   maxlength="80" class="form-control"/>
							</div>
						</div>
						<spring:url value="${pageContext.request.contextPath}/perfil"
							var="userActionUrl" />

						<br />
						<div class="table-wrapper">
							<table id="mytable1"
								class="estandardTable table table-striped table-bordered table-hover "
								cellspacing="0" width="100%">
								<thead>
									<tr>
										<th>COD. SIS.</th>
										<th>SISTEMA</th>
										<th>COD. PERF.</th>
										<th>PERFIL</th>
										<th>ESTADO</th>
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
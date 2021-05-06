<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/html" href="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/filter/bootstrap-table-filter.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Content/bootstrap/css/bootstrap-select.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Scripts/bootstrap-jquery-ui/js/bootstrap.fileupload.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/bootstrap-table/bootstrap-table.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Scripts/bootstrap-jquery-ui/js/bootstrap-datepicker.css" />
<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/resources/Scripts/bootstrap-jquery-ui/js/summernote.css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Content/bootstrap-datatable/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Content/bootstrap-datatable/dataTables.fixedColumns.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Content/bootstrap-datatable/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/app/jquery.mCustomScrollbar.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/bootstrap-table.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/flat-json/bootstrap-table-flat-json.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/export/bootstrap-table-export.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/export/tableExport.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/locale/bootstrap-table-es-ES.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/filter/bootstrap-table-filter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-table/extensions/filter-control/bootstrap-table-filter-control.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/bootstrap-jquery-ui/js/bootstrap-select.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/bootstrap-jquery-ui/js/bootstrap.fileupload.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/Scripts/jquery/jquery.serializejson.js"></script>
<script type="text/javascript">
 <![CDATA[ 
	var codUsuario = "${codUsuario}";
	var usuarioBean = ${usuarioBean};
	var listaSistemas = ${listaSistemas};
	var listaPerfiles = ${listaPerfiles};
 ]]>
</script>

<script type="text/javascript">
$(document).ready(function() {
	inicializarVariables();
	cargarComponentes();
});

function inicializarVariables() {
	console.log("prueba modal");
	accordion();
	var divMantenimiento = null;
	var mensajeRespuesta = null;
	var estadoRespuesta = null;
	var txtCodigo = null;
	var txtNombre = null;
	var txtMensaje = null;
	var btnGrabar = null;
	var btnRegresar = null;
	var nombreUsuario = null;
}

function cargarComponentes(){
	divMantenimiento = $('#divMantenimiento');
	divMensajeInformacionModal = $('#divMensajeInformacionModal');
	tblResultadosSistemas = $('#tblResultadosSistemas');
	tblResultadosPerfiles = $('#tblResultadosPerfiles');
	nombreUsuario = $("#nombreUsuario");

	nombreUsuario.html(nombreUsuario.html() + usuarioBean.codUsuario);
	
	cargarGrillaResultadoSistemas(listaSistemas);
	cargarGrillaResultadoPerfiles(listaPerfiles);
}

function cargarGrillaResultadoSistemas(lista){
	tblResultadosSistemas.bootstrapTable({
		data : lista,
		exportDataType : 'all',
		pagination : true,
		pageSize : 5,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Sistemas Asociados'
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{	
			field : 'codSistema',
			title : 'Código',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'descripcion',
			title : 'Descripción',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'abreviatura',
			title : 'Abreviatura',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			formatter : verificarEstiloEstadoSistema	
		}]
	});
}

function cargarGrillaResultadoPerfiles(lista){
	tblResultadosPerfiles.bootstrapTable({
		data : lista,
		exportDataType : 'all',
		pagination : true,
		pageSize : 5,
		buttonsAlign : 'left',
		exportOptions: {
             fileName: 'Perfiles Asociados'
        },
		formatShowingRows : function(pageFrom, pageTo, totalRows) {
			return '';
		},
		formatRecordsPerPage : function(pageNumber) {
			return '';
		},			
		columns : [{	
			field : 'codPerfil',
			title : 'Código',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'descripcion',
			title : 'Descripción',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'sistemaAbreviatura',
			title : 'Sistema',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input'
		},{	
			field : 'estado',
			title : 'Estado',
			align : 'center',
			valign : 'bottom',
			sortable : false,
			searchable : true,
			filterControl: 'input',
			formatter : verificarEstiloEstadoPerfil
		}]
	});
}

function formatoNro(value, row, index) {
	return [
		(index + 1) + ''
    ].join('');			
}

function verificarEstiloEstadoPerfil(value, row, index) {	
	if(row != null){
		if(row.estado == ConstantsServices.ESTADO_ACTIVO || row.estado == ConstantsServices.ESTADO_ACTIVO_STR){
			return ['<i class="fa fa-check text-success"></i>'].join('');
		}else if(row.estado == ConstantsServices.ESTADO_INACTIVO || row.estado == ConstantsServices.ESTADO_INACTIVO_STR){
			return ['<i class="fa fa-close text-danger"></i>'].join('');
		}else if( row.estado == ConstantsServices.ESTADO_BLOQUEADO){
			return ['<i class="fa fa-ban text-warning"></i>'].join('');
		}else{
			return [];
		}
	}else{
		return [];
	}
}

function verificarEstiloEstadoSistema(value, row, index) {	
	if(row != null && row.estado == ConstantsServices.ESTADO_ACTIVO_SIS) {
		return ['<i class="fa fa-check text-success"></i>'].join('');
	}else if(row != null && row.estado == ConstantsServices.ESTADO_INACTIVO_SIS){
		return ['<i class="fa fa-close text-danger"></i>'].join('');
	}else {
		return [];
	}
}

</script>
</head>
<body class="skin-gym bar-white">
	<div class="modal-dialog modal-Blanks" role="document">   
		<div class="modal-content">  
		    <div class="modal-header">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">Relaciones de Usuario</h4>
	        </div>
			<div class="modal-body">
				<div class="panel-body">
					<div class="box-wrap-body">
						<div class="box-form" id="divMantenimiento">
							<div class = "row">
								<h3 class = "title" id="nombreUsuario"> &nbsp;&nbsp;Usuario:   </h3>
<%-- 								<h3 class="title" style="text-align: left !important;">${codUsuario}</h3> --%>
							</div>
							<div class = "row">
								<div class="col-md-4">
									&nbsp;
								</div>
							</div>
							<div class = "row">
								<div class="col-md-4">
									&nbsp;
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												<b class="accordeon_heading">Sistemas Asociados</b>
											</h3>
										</div>
										<div class="panel-body">
											<div>
												<div class="pull-left">
													<div id="divMensajeInformacionSistemas"></div>
												</div>
											</div>
											<div id="toolbarSistemas" style="margin-left: 80px; position: absolute; width: 300px;">
												<span id="lblValidacionSistemas" style="font-size: 13px;"></span>
											</div>
											<div class="box-form">
												<div class="row">
													<div class="col-md-12">
														<div>
															<div class="table-responsive">
																<table id="tblResultadosSistemas"
																	class="table table-striped table-bordered table-hover text-center text-middle"
																	data-flat="true" data-show-export="true"
																	data-toolbar="#toolbarSistemas" data-cache="false">
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h3 class="panel-title">
												<b class="accordeon_heading">Perfiles Asociados</b>
											</h3>
										</div>
										<div class="panel-body">
											<div>
												<div class="pull-left">
													<div id="divMensajeInformacionPerfiles"></div>
												</div>
											</div>
											<div id="toolbarBlanks"
												style="margin-left: 80px; position: absolute; width: 300px;">
												<span id="lblValidacionPerfiles" style="font-size: 13px;"></span>
											</div>
											<div class="box-form">
												<div class="row">
													<div class="col-md-12">
														<div>
															<div class="table-responsive">
																<table id="tblResultadosPerfiles"
																	class="table table-striped table-bordered table-hover text-center text-middle"
																	data-flat="true" data-show-export="true"
																	data-toolbar="#toolbarPerfiles" data-cache="false">
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>			
			<div class="modal-footer">
				<div class="pull-left">
					<div class="alert-custom">
						<div id="divMensajeInformacionModal" class="alert-custom"></div>
					</div>
				</div>
                <div class="pull-right">
                	 &nbsp;&nbsp;&nbsp;
					 <button id="btnRegresar" class="btn-s btn-clean" data-dismiss="modal">Aceptar</button>
                </div>
            </div>
		</div>
	</div>
</body>
</html>
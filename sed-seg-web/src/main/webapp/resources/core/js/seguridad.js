var table;
function obtenerListaFormularioModulo(codSistema, codModulo) {

	var url = document.getElementById('url').value;
	var divUl = $(".tree ul")

	divUl.remove();

	$.ajax({
		type : "GET",
		timeout : 50000,
		beforeSend : function() {
			// $('#spinner').show();
		},
		// url : "${request.contextPath}/menu/getFormularioModulo",
		url : url + "/menu/getFormularioModulo",
		data : {
			value1 : codSistema,
			value2 : codModulo
		},
		contentType : "application/json",
		success : function(data) {

			// alert('Lo justo!');

			// $("#noderoot").append("<li>Appended item</li>");

			var source = builddata(data);
			// var ul = $("#noderoot");
			var ulRoot = $("<ul></ul>");
			var liRoot = $("<li><span><img src=\"" + url
					+ "/resources/core/images/home.gif\"/></span>"
					+ "<a href=\"#\">" + " Root " + "</a>" + "</li>");

			var ul = $("<ul></ul>");

			buildUL(ul, source);

			ul.appendTo(liRoot);
			liRoot.appendTo(ulRoot);

			ulRoot.appendTo(".tree");

			agregarAtributos();

		},
		error : function(xhr, thrownError) {
			//alert(xhr.status);
			// alert(xhr.responseText);
			//alert(thrownError);
		},
		complete : function() {
			// $('#spinner').hide();
			$('#selectModulo').val(codModulo);
		}
	});
}

function obtenerListarModulo(valor) {
	$('#selectModulo').empty();
	$(".tree ul").remove();
	var url = document.getElementById('url').value
	$.ajax({
		type : "GET",
		timeout : 10000,
		beforeSend : function() {
			$('#spinner').show();
		},
		url : url + "/opcionform/getModuloSistema",
		data : {
			id : valor
		},
		contentType : "application/json",
		success : function(data) {

			var newOption = "<option value=" + "-1" + ">" + "--- Select ---"
					+ "</option>";
			$('#selectModulo').append(newOption);
			for (var i = 0; i < data.length; i++) {
				var newOption = "<option value=" + data[i].value + ">"
						+ data[i].value + " - " + data[i].text + "</option>";
				$('#selectModulo').append(newOption);
			}
		},
		error : function(xhr, thrownError) {
			//alert(xhr.status);
			// alert(xhr.responseText);
			//alert(thrownError);
		},
		complete : function() {
			$('#spinner').hide();
		}
	});
}

function obtenerListarFormulario(codSistema, codModulo, codFormulario) {
	$('#selectFormulario').empty();
	var url = document.getElementById('url').value
	
	$.ajax({
		type : "GET",
		timeout : 10000,
		beforeSend : function() {
			$('#spinner').show();
		},
		url : url + "/menu/getFormularioModulo",
		data : {
			value1 : codSistema,
			value2 : codModulo
		},
		contentType : "application/json",
		success : function(data) {
			var newOption = "<option value=" + "0" + " data-nivel=0>" + "--- 0 - Es Menu Principal ---"	+ "</option>";
			$('#selectFormulario').append(newOption);
			for (var i = 0; i < data.length; i++) {
				if (data[i].codFormulario != codFormulario) {
					var newOption = "<option value=" + data[i].codFormulario + " data-nivel=" + data[i].nivelFormulario + ">" + data[i].codFormulario+ " - " + data[i].descripcion + "</option>";
					$('#selectFormulario').append(newOption);
				}
			}
		},
		error : function(xhr, thrownError) {
			//alert(xhr.status);
			// alert(xhr.responseText);
			//alert(thrownError);
		},
		complete : function() {
			$('#spinner').hide();
		}
	});
}

function obtenerFormularioPadre(codSistema, codModulo, codFormularioPadre) {
	var url = document.getElementById('url').value
	var nombreFormularioPadre = "Es Menu Principal";
	$.ajax({
		type : "GET",
		timeout : 10000,
		beforeSend : function() {
			$('#spinner').show();
		},
		url : url + "/menu/getFormularioModulo",
		data : {
			value1 : codSistema,
			value2 : codModulo
		},
		async: false,
		contentType : "application/json",
		success : function(data) {
			
			for (var i = 0; i < data.length; i++) {
				if (data[i].codFormulario == codFormularioPadre) {
					nombreFormularioPadre = data[i].descripcion;
					break;
				}
			}
			
		},
		error : function(xhr, thrownError) {
			console.log(xhr.status);
			// alert(xhr.responseText);
			console.log(thrownError);
		},
		complete : function() {
			$('#spinner').hide();
		}
	});
	return nombreFormularioPadre;
}

function obtenerListarFormularioActualizando(codSistema, codModulo, codFormulario, codFormularioPadre) {
	$('#selectFormulario').empty();
	var url = document.getElementById('url').value
	
	$.ajax({
		type : "GET",
		timeout : 10000,
		beforeSend : function() {
			$('#spinner').show();
		},
		url : url + "/menu/getFormularioModulo",
		data : {
			value1 : codSistema,
			value2 : codModulo
		},
		contentType : "application/json",
		success : function(data) {
			
			//console.log("original");
			//console.log(data);
			
			//console.log("hijos");
			var hijos = obtenerHijos(data, codFormulario);
			//console.log(hijos);
			
			//console.log("no hijos");
			var nohijos = obtenerNohijos(data, hijos);
			//console.log(nohijos);
			
			data = nohijos;
			
			
			var newOption = "<option value=" + "0" + " data-nivel=0>" + "--- 0 - Es Menu Principal ---"	+ "</option>";
			$('#selectFormulario').append(newOption);
			for (var i = 0; i < data.length; i++) {
				if (data[i].codFormulario != codFormulario) {
					if (data[i].codFormulario == codFormularioPadre) {
						var newOption = "<option value=" + data[i].codFormulario + " selected='selected' data-nivel=" + data[i].nivelFormulario + ">" + data[i].codFormulario+ " - " + data[i].descripcion + "</option>";
						$('#selectFormulario').append(newOption);
					} else {
						var newOption = "<option value=" + data[i].codFormulario + " data-nivel=" + data[i].nivelFormulario + ">" + data[i].codFormulario+ " - " + data[i].descripcion + "</option>";
						$('#selectFormulario').append(newOption);
					}
					
				}
			}
		},
		error : function(xhr, thrownError) {
			//alert(xhr.status);
			// alert(xhr.responseText);
			//alert(thrownError);
		},
		complete : function() {
			$('#spinner').hide();
		}
	});
}

function obtenerNohijos(data, hijos){
	var noHijos = [];	
	var ind = 0;
	
	for (var i = 0; i < data.length; i++) {
		
		var temp = data[i];
		var sw = true;
		for (var j = 0; j < hijos.length; j++) {			
			if ( temp.codFormulario == hijos[j].codFormulario ) {
				sw = false; break;
			}
		}
		
		if (sw) { 
			noHijos[ind]= temp;
			ind++;
		}		
	}	
	return noHijos;
}

function obtenerHijos(data, codform){	
	var totalHijos = [];
	var inmediatos = [];
	var todo = [];
	var ind = 0;
	
	for (var i = 0; i < data.length; i++) {
		if (data[i].codFormularioPadre == codform) {
			inmediatos[ind] = data[i]; 
			ind++;
		}
	}
		
	var todo = todo.concat(inmediatos);
	
	
	if(inmediatos.length > 0) {		
		var defor = [];
		for (var j = 0; j < inmediatos.length; j++) {
			
			var codFormHijo = inmediatos[j].codFormulario;
			var hijos = obtenerHijos(data, codFormHijo);
			defor = defor.concat(hijos);			
		}
		
		return todo.concat(defor);		
	}
	else{
		return todo;
	}	
}

var builddata = function(data) {
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
			var item = {
				parentid : parentid,
				label : label,
				id : id,
				node : node,
				child : child,
				item : item
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
				node : node,
				child : child,
				item : item
			};
			source[id] = items[id];
		}
	}
	return source;
}

var buildUL = function(parent, items) {

	var codigo = $("#codformulario").val();

	// alert("costruyendo root");
	// alert(codigo);

	$
			.each(
					items,
					function() {
						var valor;
						if (this.label) {
							if (undefined == this.id) {
								valor = '';
							} else {
								valor = this.id;
							}

							if (codigo == "") {
								var li = $("<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/document.gif\"/></span>"
										+ "<a href=\"javascript:void(0)\" data-id=\""
										+ valor
										+ "\">"
										+ " ["
										+ this.parentid
										+ "-"
										+ valor
										+ "] "
										+ this.label
										+ "</a>" + "</li>");
							} else {
								var li = $("<li><span><img src=\"${pageContext.request.contextPath}/resources/core/images/document.gif\"/></span>"
										+ "<a href=\"javascript:void(0)\" onClick=\"buscarForm('"
										+ this.node
										+ "','"
										+ this.child
										+ "','"
										+ valor
										+ "');\" data-id=\""
										+ valor
										+ "\">"
										+ " ["
										+ this.parentid
										+ "-"
										+ valor
										+ "] "
										+ this.label
										+ "</a>" + "</li>");
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

function alphaOnly(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();

	if (character == "[" || character == "]") {
		return false;
	}
	return (/[^($%^&!-@#%*()_{}:;?\/><"',)]/.test(character));
}

function alphaOnlyNumeric(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();
	
	if (character == "[" || character == "]") {
		return false;
	}
	return (/[A-Za-z0-9_]/.test(character));
}

function alphaNumericSpaceOnly(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();
	
	if (character == "[" || character == "]") {
		return false;
	}
	return (/[A-Za-z0-9 _]/.test(character));
}

function alphaOnlyUrl(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();

	if (character == "[" || character == "]") {
		return false;
	}
	return (/[^($%^&!@#%*()_-{}:;?\><"',)]/.test(character));
}

function checkOnlyDigits(e) {
	e = e ? e : window.event;
	var charCode = e.which ? e.which : e.keyCode;
	
	
	if(charCode == 8) {
		return true;
	}
	
	if (charCode < 48 || charCode > 57) {
		return false;
	} else {
		return true;
	}
}

function post(path, params, method) {
	
	method = method || "post";

	var form = document.createElement("form");
	form.setAttribute("method", method);
	form.setAttribute("action", path);
	

	for ( var key in params) {
		if (params.hasOwnProperty(key)) {
			var hiddenField = document.createElement("input");
			hiddenField.setAttribute("type", "hidden");
			hiddenField.setAttribute("name", key);
			hiddenField.setAttribute("value", params[key]);
			form.appendChild(hiddenField);
		}
	}

	/*
	 * var hiddenField = document.createElement("input");
	 * hiddenField.setAttribute("type", "hidden");
	 * hiddenField.setAttribute("name", "${_csrf.parameterName}");
	 * hiddenField.setAttribute("value", "${_csrf.token");
	 * form.appendChild(hiddenField);
	 */

	document.body.appendChild(form);

	form.submit();
}

$(document).ready(function() {
	/* alert("1"); */
	$("#spinner").bind("ajaxSend", function() {
		$(this).show();
	}).bind("ajaxStop", function() {
		$(this).hide();
	}).bind("ajaxError", function() {
		$(this).hide();
	});
});

$(document).ready(function() {

	$('button').click(function() {
		/* alert("boton"); */
		$('#spinner').show();
		// $('#form').submit();
	});
	$('a').click(function() {
		$('#spinner').show();
		// $('#form').submit();
	});
	$('.launch').click(function() {
		$('#spinner').show();
		// $('#form').submit();
	});

});
/*
 * $(document).ready(function() {
 * 
 * $("a").on("click", "launch", function() {
 * alert($(this).find("span.t").text()); }); });
 */

$(document).ready(function() {
	$("#botonGrabar").click(function() {
		$.confirm({
			title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
			useBootstrap : true,
			content : '\u00BFDesea grabar la informaci\u00F3n\?',
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
						// $.alert('you clicked on <strong>cancel</strong>');
						$('#formid').submit();
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

$(document).ready(function() {
	$("#botonActualizar").click(function() {
		$.confirm({
			title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
			useBootstrap : true,
			content : '\u00BFDesea actualizar la informaci\u00F3n\?',
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
						// $.alert('you clicked on <strong>cancel</strong>');
						$('#formid').submit();
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

function onEliminar(t) {
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
					window.location.href = t;
				}
			},
			cancelar : function() {
				$('#spinner').hide();
			},
		}
	});
	return false;
}
/*
$(document).on("keydown", this, function(e) {

	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;

	if (code == 116) {
		// alert('F5 pressed!');
		inFormOrLink = true;
	}
});
*/
$(document).ready(function() {
	table = $("#mytable").DataTable({
		"dom" : '<"top"f><rt><"bottom"lip><"clear">',
		"language" : {
			"url" : "resources/core/languaje/spanish.json",
		},
		"initComplete" : function(settings, json) {
			$('#mytable_filter input').unbind();
			$('#mytable_filter input').bind('keyup', function(e) {
				if (e.keyCode == 13) {
					$('#spinner').show();
					table.search(this.value.toUpperCase()).draw();
				}
			});
		},
		"fnDrawCallback" : function(oSettings) {
			$('#spinner').hide();
		},
		"lengthMenu" : [ [ 10, 20, 30, 40, 50, 100 ], [ 10, 20, 30, 40, 50, 100 ] ],
		"paging" : true,
		"ordering" : true,
		"responsive" : true,
		"stateSave" : true,
		"bAutoWidth" : true
	});

	$("#mytable tbody tr").on('mouseenter', function(event) {
		$("#mytable tbody tr").removeClass('row_selected');
		$(this).addClass('row_selected');
	});		
});


$(document).keydown(function(e) {
    var doPrevent;
    if (e.keyCode == 8) {
        var d = e.srcElement || e.target;
        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') {
            doPrevent = d.readOnly || d.disabled;
        }
        else
            doPrevent = true;
    }
    else
        doPrevent = false;

    if (doPrevent)
        e.preventDefault();
});

//inicio adecuacion seguridad2
function redireccionModal(url, obj, element){
	$.ajax({
          url : url,
          type : "GET",
          async : false,
          data : obj
     }).success(function(data) {
          element.html(data);
          element.modal('toggle');
          
     }).fail(function(jqXHR, textStatus, errorThrown) {                	
     	  validarFinDeSesion(jqXHR, textStatus, errorThrown, '<%= ConstantsServices.MENSAJE_SESION_EXPIRADA %>');
     });
}

var validarFinDeSesion = function(jqXHR, textStatus, errorThrown, mensajeSesionExpirada, url){
    if(jqXHR.responseText != '' && jqXHR.responseText.indexOf(mensajeSesionExpirada) > -1){
        var url = jqXHR.responseText.split(",")[1];
        window.location.href = url;
   }else{
       alert('La aplicaci??n ha detectado un error inesperado al ejecutar la operaci??n seleccionada. Favor intentar nuevamente, en caso el error persista comun??quese con el ??rea responsable.');
   }
}

function accordion() {
	var text = '<span class="pull-right clickable accordion"><i class="glyphicon-alter glyphicon-chevron-up"></i></span>';
	$('.panel-heading').each(function(){		
	    $(this).html($(this).html() + text);
	});
	
	$('.panel-heading span.clickable').on("click", function (e) {
		if ($(this).hasClass('panel-collapsed')) {
		    // expand the panel
		    $(this).parents('.panel').find('.panel-body').slideDown();
		    $(this).removeClass('panel-collapsed');
		    $(this).find('i').removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
		}
		else {
		    // collapse the panel
		    $(this).parents('.panel').find('.panel-body').slideUp();
		    $(this).addClass('panel-collapsed');
		    $(this).find('i').removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
		}
	});
	
	$('.panel-heading span.clickable').click();
}

function agregarListaItem(element,lista, campoValue, campoText){
	  $.each(lista, function(i, item) {
		  element.append($("<option/>").attr("value",lista[i][campoValue] ).text(lista[i][campoText])); 
    });
}

function alphaOnlyNoSpace(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();

	if (character == "[" || character == "]") {
		return false;
	}
	return (/[^($%^&!-@#%*()_{}:;?\/><"', )]/.test(character));
}

function generarModalConfirmacion(){
	$.confirm({
		title : '<u>Mensaje de Confirmaci\u00F3n\n</u>',
		useBootstrap : true,
		content : '\u00BFDesea grabar la informaci\u00F3n\?',
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
					grabarDataAsociacion();
				}
			},
			cancelar : function() {
				$('#spinner').hide();
			},
		}
	});
	return false;
}

function mostrarMensaje(mensaje, estadoMensaje, idDiv){
	if(IsEmpty(idDiv)) {
		idDiv= "divMensajeInformacion";
	}
	if(IsEmpty(mensaje) && IsEmpty(estadoMensaje)) {
		$('#'+idDiv).html('');
	}else if(estadoMensaje == "success"){		
		mensajeHTML = '<div class="alert alert-success alert-dismissible" role="alert">';
	    mensajeHTML = mensajeHTML + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	    mensajeHTML = mensajeHTML + '<strong>';
	    mensajeHTML = mensajeHTML + mensaje;
	    mensajeHTML = mensajeHTML + '</strong>';
	    mensajeHTML = mensajeHTML + '</div>';
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	}else if (estadoMensaje == "danger") {		
		mensajeHTML = '<div class="alert alert-danger alert-dismissible" role="alert">';
	    mensajeHTML = mensajeHTML + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	    mensajeHTML = mensajeHTML + '<strong>';
	    mensajeHTML = mensajeHTML + mensaje;
	    mensajeHTML = mensajeHTML + '</strong>';
	    mensajeHTML = mensajeHTML + '</div>';
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	} 
	else if (estadoMensaje == "warning") {		
		mensajeHTML = '<div class="alert alert-warning alert-dismissible" role="alert">';
	    mensajeHTML = mensajeHTML + '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	    mensajeHTML = mensajeHTML + '<strong>';
	    mensajeHTML = mensajeHTML + mensaje;
	    mensajeHTML = mensajeHTML + '</strong>';
	    mensajeHTML = mensajeHTML + '</div>';
	    $('#'+idDiv+' div').remove();
	    $('#'+idDiv).append(mensajeHTML);
	    $('#divMensajeInformacion').parent().removeClass("alert-custom");
	    $('#divMensajeInformacion').removeClass("alert-custom");
	} 
}

function IsEmpty(cadena) {
	if(cadena == undefined || cadena == null || cadena == "" || (cadena.toString()).trim() == ""){
		return true;
	}
	return false;
}
//fin adecuacion seguridad2

function ponerClassConjunto(tipoInput,nombreClase,MaxDigito,maxPreDecimalPlaces, maxDecimalPlaces){
	var lista = document.getElementsByClassName(nombreClase);
	for(var  i = 0 ; i < lista.length ; i++){
		var identificador = lista[i].id;
		if(tipoInput == "inputDecimal"){
			inputDecimal(identificador, maxPreDecimalPlaces, maxDecimalPlaces);
		}else if(tipoInput == "inputNumerico"){
			inputNumerico(identificador, MaxDigito);
		}else if(tipoInput == "inputNumericoDefinido"){
			inputNumericoDefinido(identificador, MaxDigito, maxPreDecimalPlaces);
		}
	}	
}

function inputDecimal(elemento, maxPreDecimalPlaces, maxDecimalPlaces){	
	if(maxDecimalPlaces != null && maxDecimalPlaces != undefined) {
		maxPreDecimalPlaces = maxPreDecimalPlaces - maxDecimalPlaces ;
		$("#"+elemento).numeric({
		    allowMinus   : false, // Allow the - sign
		    allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
		    maxPreDecimalPlaces: maxPreDecimalPlaces, // The max number digits before the decimal point
		    maxDecimalPlaces: maxDecimalPlaces // The max number of decimal places
		});
	} else {
		$("#"+elemento).numeric({
		    allowMinus   : false, 
		    allowThouSep : false, 
		    maxPreDecimalPlaces: maxPreDecimalPlaces 
		});
	}
}

function inputNumerico(elemento, maxDigits){
	/*
	 Allow the - sign
	 allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
	 allowDecSep  : false, // // Allow the decimal separator, default is the fullstop eg 3.141
	 maxDigits: maxDigits // // The max number of digits
	 */
	if(maxDigits != null && maxDigits != undefined) {
		$("#"+elemento).numeric({
		    allowMinus   : true, 
		    allowThouSep : false,
		    allowDecSep  : false,
		    maxDigits: maxDigits 
		});
	} else {
		$("#"+elemento).numeric({
		    allowMinus   : true,
		    allowThouSep : false,
		    allowDecSep  : false
		});
	}
}

function inputNumericoDefinido(elemento, maxValue, minValue){
	/*
	 Allow the - sign
	 allowThouSep : false, // Allow the thousands separator, default is the comma eg 12,000
	 allowDecSep  : false, // // Allow the decimal separator, default is the fullstop eg 3.141
	 maxDigits: maxDigits // // The max number of digits
	 */
	$("#"+elemento).alphanum({
		disallow           : '0!@#$%^&*()+=[]\\\';,/{}|":<>?~`.- _??????????',
		allowNumeric       : true,
		maxLength          : 1,
	});
}

function ValidEmail(element) {
	// creamos nuestra regla con expresiones regulares.
	var filter = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
	// utilizamos test para comprobar si el parametro valor cumple la regla
	if ( filter.test($.trim(element.val()))) {
		return true;
	} else {
		return false;
	}
}

function alphaSpaceOnly(e) {
	var code;
	if (!e)
		var e = window.event;
	if (e.keyCode)
		code = e.keyCode;
	else if (e.which)
		code = e.which;
	var character = String.fromCharCode(code);

	character = character.toUpperCase();
	
	if (character == "[" || character == "]") {
		return false;
	}
	return (/[A-Za-z ]/.test(character));
}
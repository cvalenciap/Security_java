<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<script type="text/javascript">

// inicio adecuacion seguridad2
// function cargarMenuInicio(sistema, usuario) {
function cargarMenuInicio(sistema, usuario, perfil) {
// fin adecuacion seguridad2
	
	$.ajax({
		type : "GET",
		timeout: 50000,
		beforeSend : function() {
			$('#spinner').show();
		},
		//url : "${pageContext.request.contextPath}/perfil/getOpcionFormModuloInicio",
		url : "${pageContext.request.contextPath}/perfil/getOpcionModuloInicio",
// 		inicio adecuacion seguridad2
// 		data : {id : sistema, codigo : usuario},
		data : {id : sistema, codigo : usuario, perfil : perfil},
// 		fin adecuacion seguridad2
		contentType : "application/json",
		success : function(data) {		
			
			var ul = $("<ul class=\"sidebar-nav\"></ul>");
			
			var source = builddatamenu(data);			
			buildULMenu(ul, source);
			
			
			ul.appendTo(".menuinicio");
						
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

var builddatamenu = function (data) {
    var source = [];
    var items = [];
    
    for (i = 0; i < data.length; i++) {
        var item = data[i];
        //alert(item);
        var label = item["descripcion"];
        var parentid = item["codFormularioPadre"];
        var id = item["codFormulario"];
        var url = item["urlFormulario"];
        var sistema = item["codSistema"];
        var modulo = item["codModulo"];        
        //alert(sistema + ',' + modulo);
        /*
        if (items[parentid]) {
            var item = { parentid: parentid, label: label, id:id, url: url, item: item,sistema: sistema,modulo: modulo };
            if (!items[parentid].items) {
                items[parentid].items = [];
            }
            items[parentid].items[items[parentid].items.length] = item;
            items[id] = item;
        }
        else {
            items[id] = { parentid: parentid, label: label, id:id, url: url, item: item,sistema: sistema,modulo: modulo };
            source[id] = items[id];
        }*/
        items[i] = { parentid: parentid, label: label, id:id, url: url, item: item };
        source[i] = items[i];
    }
    return source;
}

var buildULMenu = function (parent, items) {
	
	 $.each(items, function () {
   	var valor;
       if (this.label) {
       	var li = $("<li class=\"classli\">"
       			+ "<a class=\"launch\" href=\"${pageContext.request.contextPath}" + this.url  +"\">" + this.label + "</a>"
       			+ "</li>");       		
           li.appendTo(parent);
           //alert(this.items);
           if (this.items && this.items.length > 0) {
               var ul = $("<ul></ul>");
               ul.appendTo(li);
               //buildUL(ul, this.items);
               buildULMenu(ul, this.items);
           }	
           
       }
   });
}    
</script>
<!-- inicio adecuacion usuario -->
<%-- <input type="hidden" id="sis_parametro" value="<c:out value="${sessionScope.abreviatura}"/>"> --%>
<input type="hidden" id="sis_parametro" value="<c:out value="${sessionScope.codSistema}"/>">
<input type="hidden" id="sis_perfil" value="<c:out value="${sessionScope.codPerfil}"/>">
<!-- fin adecuacion usuario -->
<input type="hidden" id="sis_usuario" value="<c:out value="${sessionScope.usuario}"/>">
<br/>
<div class="menuinicio">
	<script type="text/javascript">
		//var param = document.getElementById('sis_parametro').value;
		//var usuario = document.getElementById('sis_usuario').value;;
		
		//cargarMenuInicio(param, usuario);
// 		inicio adecuacion seguridad2
// 		cargarMenuInicio($("#sis_parametro").val(), $("#sis_usuario").val());
		cargarMenuInicio($("#sis_parametro").val(), $("#sis_usuario").val(), $("#sis_perfil").val());
// 		fin adecuacion seguridad2
	</script>
</div>



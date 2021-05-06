<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, shrink-to-fit=no, initial-scale=1">
<title>Sistema de Seguridad</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/core/js/jquery/1.11.1/jquery.min.js"></script>

<spring:url value="/resources/core/js/seguridad.js" var="coreJs" />

<script src="${coreJs}"></script>

<spring:url value="/resources/core/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapminCss" />
<%-- <spring:url value="/resources/core/css/standard.css" var="standardCss" /> --%>

<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${bootstrapminCss}" rel="stylesheet" />
<%-- <link href="${standardCss}" rel="stylesheet" />
 --%>
<style type="text/css">
.spinner {
	position: fixed;
	top: 50%;
	left: 50%;
	margin-left: -50px; /* half width of the spinner gif */
	margin-top: -50px; /* half height of the spinner gif */
	text-align: center;
	z-index: 1234;
	overflow: auto;
	width: 100px; /* width of the spinner gif */
	height: 102px; /*hight of the spinner gif +2px to fix IE8 issue */
}
.middleDiv {
	z-index:1100;
	position: fixed;
	width: 300px;
	height: 300px;
	left: 50%;
	top: 50%;
	margin-left: -100px; /* half of the width  */
	margin-top: -100px; /* half of the height */
}
.middleDiv:before {
	content: '';
	display: block;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.3);
}

input[type=text] {
    text-transform: uppercase;
}

textarea{
    text-transform: uppercase;
}

html {
	position: relative;
	min-height: 100%;
}

body {
	/* Margin bottom by footer height */
	margin-bottom: 40px;
}

.footer {
	position: absolute;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 40px;
	background-color: #f5f5f5;
}
</style>

</head>
<body>
	<c:import url="fragments/title.jsp" />

	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Iniciar sesión - ${ambiente}</div>
				</div>
				<div style="padding-top: 30px" class="panel-body">
					<c:if test="${not empty error}">
						<div id="msj-error"
							class="alert alert-danger col-sm-12 text-center">${error}</div>
					</c:if>
					<c:if test="${not empty msg}">
						<div id="msj-aviso"
							class="alert alert-success col-sm-12 text-center">${msg}</div>
					</c:if>

					<form id="loginform" class="form-horizontal" name="login"
						action="<c:url value='/login' />" method="POST">
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login-username"
								type="text" class="form-control" maxlength="15" size="15"
								 name="username" value=""
								placeholder="USUARIO" onKeyPress="enter(event);">
						</div>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								type="password" class="form-control" name="password" value="" autocomplete="off"
								onKeyPress="enter(event);" placeholder="CLAVE">
						</div>
						<!-- <div class="input-group">
							<div class="checkbox">
								<label> <input id="login-remember" type="checkbox"
									name="remember" value="1"> No cerrar sesión
								</label>
							</div>
						</div> -->
						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->
							<div class="col-sm-12 controls">
								<!-- <button id="submit" class="btn btn-success" type="submit">Iniciar
									sesión</button> 
								<a class="btn btn-primary" href="#" onClick="validarLogin()">
									Iniciar sesión</a>-->
								<button id="submit" class="btn btn-primary" type="submit" onClick="validarLogin()">Iniciar
									sesión</button>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 control">
								<spring:url value="/olvido" var="olvidoClaveUrl" />
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									He olvidado <a href="#"
										onclick="location.href='${olvidoClaveUrl}'"> mi contraseña!
									</a>
									<!-- <a href="#"
										onClick="$('#loginbox').hide(); $('#signupbox').show() ">
										mi contraseña! </a> -->
								</div>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
					</form>
				</div>
			</div>
		</div>
	</div>

	<div id="spinner" class="middleDiv" style="display: none;">
		<img id="img-spinner"
			src="${pageContext.request.contextPath}/resources/core/images/ajax-loader.gif"
			alt="Loading" />
	</div>

	<footer class="footer">
		<div class="row-fluid">
			<div class="col-sm-12">
				<p class="text-center">${copy}</p>
			</div>
		</div>
		<%-- <jsp:include page="fragments/footer.jsp" /> --%>
	</footer>


	<script>
		function validarLogin() {
			
			
			setTimeout(function() { 
				$('#spinner').show();
				$("#loginform").submit();
				//document.login.submit();				
			}, 100);
			
			
			
		}

		function restablecerClave() {
			$('#loginbox').hide();
			$('#signupbox').show();
			$('#spinner').hide();
		}

		$(document).keypress(function(e) {
			if (e.which == 13) {
				//alert('You pressed enter!');
				$('#spinner').show();
				$("#loginform").submit();
				//document.login.submit();
			}
		});

		function enter(e) {
			var code;
			if (!e)
				var e = window.event;
			if (e.keyCode)
				code = e.keyCode;
			else if (e.which)
				code = e.which;
			var character = String.fromCharCode(code);
			//alert(code);
			if (code == 13) {
				validarLogin();
				$('#spinner').show();
			}

			return false;
		}
	</script>

</body>
</html>
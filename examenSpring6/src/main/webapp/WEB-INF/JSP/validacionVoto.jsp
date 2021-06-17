<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>proceso de voto</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
</head>
<body>
	<header>
		<div class="container">
			<div class="row">
				<nav class="navbar navbar-expand-md navbar-dark">
					<div>
						<a href="#" class="navbar-brand"></a>
					</div>
					<!-- 
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Usuarios</a></li>
			</ul>-->
				</nav>

			</div>
		</div>
	</header>

	<br>

	<div class="container">
		<div class="row">
			<h2>Validación del proceso de voto</h2>
			<div class="col">
				<h3 color="red">"${eleccion.cargo}"&nbsp;"${eleccion.fechaInicio}"-"${eleccion.fechaFin}"</h3>
				<h3>"${estamento.descripcion}"</h3>

				<form action="validarVotante" method="post">
					
					<input type="hidden" class="form-control" name="eleccionCargo" required="required" value="${eleccion.cargo}">
					<input type="hidden" class="form-control" name="fechaInicio" required="required" value="${eleccion.fechaInicio}">
					<input type="hidden" class="form-control" name="fechaFin" required="required" value="${eleccion.fechaFin}}">
					<input type="hidden" class="form-control" name="id" required="required" value="${votante.id}">
					<input type="hidden" class="form-control" name="estamentoId" required="required" value="${estamento.id}">
					<input type="hidden" class="form-control" name="estamentoDescripcion" required="required" value="${estamento.descripcion}">
							
					<fieldset class="form-group">
						<label>Nombre:</label> <input type="text" class="form-control"
							name="nombre" required="required" maxlength="100"
							value="${votante.nombre}" readonly>
					</fieldset>

					<fieldset class="form-group">
						<label>Email</label> <input type="email" class="form-control"
							name="email" required="required" maxlength="50"
							value="${votante.email}" readonly>
					</fieldset>

					<br>
					<select class="form-select" aria-label="Default select example"name="documentoId">
						<option selected>Seleccione tipo de documento</option>
						<c:forEach var="i" items="${tipoDocumentos}">
							<option value="${i.id}">"${i.descripcion}"</option>
						</c:forEach>
					</select>
					<fieldset class="form-group">
						<label>Documento</label> <input type="text" class="form-control"
							name="documento" required="required" maxlength="20">
					</fieldset>
					<br>
					<button type="submit" class="btn btn-danger">Confirmo mis
						datos</button>
				</form>

				<div class="alert alert-warning" role="alert">Confirma tus datos y completa la información para continuar </div>
				<c:if test="${datosErroneos==true}">
					<div class="alert alert-danger" role="alert">Datos erróneos</div>
				</c:if>
			</div>
		</div>
	</div>

</body>
</html>
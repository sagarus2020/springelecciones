<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscripcion votante</title>
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
				<nav class="navbar navbar-expand-md navbar-dark bg-dark">
					<ul class="navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/inscripcionCandidato"
							class="nav-link">Inscripción de candidato</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>

	<br>

	<div class="container">
		<div class="row">
			<h2>Registro de población electoral</h2>

			<form action="insertarVotante" method="post">

				<select class="form-select" aria-label="Default select example"
					name="estamentoId">
					<option selected>Seleccione el estamento</option>
					<c:forEach var="i" items="${estamentos}">
						<option value="${i.id}">"${i.descripcion}"</option>
					</c:forEach>
				</select> <br> <select class="form-select"
					aria-label="Default select example" name="tipoDocumentoId">
					<option selected>Seleccione el tipo de documento</option>
					<c:forEach var="i" items="${tipodocumentos}">
						<option value="${i.id}">"${i.descripcion}"</option>
					</c:forEach>
				</select> <br>

				<fieldset class="form-group">
					<label>Documento</label> <input type="text" class="form-control"
						name="documento" required="required" maxlength="20">
				</fieldset>

				<br>

				<fieldset class="form-group">
					<label>Nombre</label> <input type="text" class="form-control"
						name="nombre" required="required" maxlength="100">
				</fieldset>

				<br>

				<fieldset class="form-group">
					<label>Email</label> <input type="text" class="form-control"
						name="email" required="required" maxlength="50">
				</fieldset>

				<br> <select class="form-select"
					aria-label="Default select example" name="eleccionId">
					<label>Elección</label>
					<option selected>Seleccione elección</option>
					<c:forEach var="i" items="${elecciones}">
						<option value="${i.id}">
							"${i.nombre}"&nbsp;"${i.fechaInicio}"-"${i.fechaFin}"</option>
					</c:forEach>
				</select> <br>

				<button type="submit" class="btn btn-danger">Registrar
					Votante</button>
			</form>

			<c:if test="${registrado == true }">
				<div class="alert alert-success" role="alert">El votante ha
					realizado su registro de forma correcta</div>
			</c:if>
		</div>
	</div>

</body>
</html>
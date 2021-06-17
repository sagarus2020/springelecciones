<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Selección de candidato</title>
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
					<!-- <ul class="navbar-nav">
						<li><a
							href="<%=request.getContextPath()%>/inscripcionCandidato"
							class="nav-link">Inscripción de candidato</a></li>
					</ul>-->
				</nav>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="row">
			<h2>Selección de candidato</h2>

			<h3 color="red">"${eleccion.cargo}"&nbsp;"${eleccion.fechaInicio}"&nbsp;"${eleccion.fechaFin}"</h3>
			<h3>"${estamento.descripcion}"</h3>

			<div class="alert alert-primary" role="alert">Debes seleccionar
				el candidato de tu preferencia y confirmar tu voto</div>

			<form action="registrarVoto" method="post">
				<input type="hidden" class="form-control" name="votanteId"
					required="required" value="${votante.id}"> <input
					type="hidden" class="form-control" name="estamentoId"
					required="required" value="${estamento.id}">
				<div class="row">
					<c:forEach var="i" items="${candidatos}">
						<div class="col-6">
							<div class="card" style="width: 18rem;">
								<div class="card-body">
									<h5 class="card-title">Numero: "${i.getNumero()}"</h5>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="candidatoId" id="flexRadioDefault1" value="${i.id}"> 
											<label class="form-check-label" for="flexRadioDefault1">
											Candidato:"${i.getNombre()}" 
											</label>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<button type="submit" class="btn btn-danger">Confirmo mi
						voto</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>
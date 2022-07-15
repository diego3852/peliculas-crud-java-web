<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../comunes/inicioHTML.jsp"/>

<jsp:include page="../comunes/inicioHead.jsp"/>
<title>Editando a ${peliculaAEditar.nombreCompleto}</title>
<jsp:include page="../comunes/finHead.jsp"/>

<jsp:include page="../comunes/nav.jsp"/>

<section class="py-3">
    <div class="container">
        <div class="row">
            <h1 class="h3">Editando pelicula</h1>
            <p class="lead">Estás a punto de editar a ${alumnoAEditar.nombreCompleto}. Asegurate de hacer click en "Confirmar cambios".</p>
        </div>
    </div>
    <div class="container px-4 mt-3">
        <div class="row align-items-center">
            <div class="col-md-5 col-lg-4" >
                <div class="card border p-4 rounded-3 bg-light">
                    <img class="card-img-top" src="${peliculaAEditar.foto}" alt="Foto de ${peliculaAEditar.nombreCompleto}" />
                    <div class="card-body pb-0">
                        <div class="text-center">
                            <h5 class="fw-bolder">${peliculaAEditar.nombreCompleto}</h5>
                            <ins>${peliculaAEditar.mail}</ins>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-7 col-lg-8" >
                <form id="formAgregarPelicula" action="${pageContext.request.contextPath}/app?accion=update&id=${peliculaAEditar.id}"
                      method="post" class="was-validated border p-4 rounded-3 bg-light">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" value="${peliculaAEditar.nombre}" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="genero" class="form-label">Genero</label>
                            <input type="text" class="form-control" id="genero" name="genero" value="${peliculaAEditar.genero}" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="fechaNac" class="form-label">Fecha de creacion</label>
                            <input type="date" class="form-control" id="fechaNac" name="fechaNac" value="${peliculaAEditar.fechaNacimiento}" required>
                        </div>

                        <div class="col-12 mb-3">
                            <label for="foto" class="form-label">
                                <c:choose>
                                    <c:when test="${yaTieneFoto}">Modificar</c:when>
                                    <c:otherwise>Agregar</c:otherwise>
                                </c:choose>
                                foto
                            </label>
                            <input type="file" class="form-control" id="inputFoto" name="foto">
                            <input type="hidden" id="fotoBase64" name="fotoBase64" value="${peliculaAEditar.foto}}">
                        </div>
                    </div>
                    <div class="row justify-content-end mt-2">
                        <div class="col-12">
                            <div class="float-end">
                                <a href="${pageContext.request.contextPath}/app" class="btn btn-secondary">Volver atrás</a>
                                <button type="submit" class="btn btn-warning">Confirmar cambios</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script src="scripts/fotobase64.js"></script>
<jsp:include page="../comunes/footer.jsp"/>
<jsp:include page="../comunes/finHTML.jsp"/>



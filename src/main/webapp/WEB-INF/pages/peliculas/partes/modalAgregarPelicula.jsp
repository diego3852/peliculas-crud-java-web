<div class="modal fade" id="modalAgregarPelicula" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Agregar pelicula</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form id="formAgregarPelicula" action="${pageContext.request.contextPath}/app?accion=add"
                  method="post" class="was-validated">
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <label for="nombre" class="form-label">Nombre</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="genero" class="form-label">Genero</label>
                            <input type="text" class="form-control" id="genero" name="genero" required>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <label for="fechaNac" class="form-label">Fecha de creacion</label>
                            <input type="date" class="form-control" id="fechaNac" name="fechaNac" required>
                        </div>
                        <div class="col-12 mb-3">
                            <label for="foto" class="form-label">Cargar foto</label>
                            <input type="file" class="form-control" id="inputFoto" name="foto">
                            <input type="hidden" id="fotoBase64" name="fotoBase64">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                    <button type="submit" class="btn btn-success">Agregar</button>
                </div>
            </form>
        </div>
    </div>
</div>
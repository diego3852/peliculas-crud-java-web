<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listaPeliculas}" var="pelicula">  
    <div class="col">
        <div class="card h-100">
            <!-- Foto -->
            <img class="card-img-top" src="${pelicula.foto}" alt="Foto de ${pelicula.nombreCompleto}" />
            <!-- Detalles -->
            <div class="card-body p-4">
                <div class="text-center">
                    <h5 class="fw-bolder">${pelicula.nombreCompleto}</h5>
                    <ins>${pelicula.genero}</ins> 
                </div>
            </div>
            <!-- Product actions-->
            <div class="card-footer border-top-0 bg-transparent">
                <div class="row justify-content-center">
                    <div class="col-6">
                        <a href="${pageContext.request.contextPath}/app?accion=edit&id=${pelicula.id}" class="btn btn-warning btn-block w-100">Editar</a>
                    </div>
                    <div class="col-6">
                        <a href="${pageContext.request.contextPath}/app?accion=remove&id=${pelicula.id}" class="btn btn-danger btn-block w-100">Borrar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>


s/comunes/finHTML.jsp"/>
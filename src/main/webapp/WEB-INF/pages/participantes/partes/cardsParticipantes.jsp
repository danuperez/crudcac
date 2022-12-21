<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:forEach items="${listaParticipantes}" var="participante">  
    <div class="col">
        <div class="card h-100">
            <jsp:include page="dataCardParticipante.jsp">
                <jsp:param name="fotoParticipante" value="${participante.foto}" />
                <jsp:param name="nombreCompletoParticipante" value="${participante.nombreCompleto}" />
            </jsp:include>
            <div class="card-footer">
                <div class="row justify-content-center">
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/tarea.jsp" class="btn bg-info w-100"><i class="bi bi-eye"></i></a>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=edit&id=${participante.id}" class="btn bg-warning w-100"><i class="bi bi-pencil"></i></a>
                    </div>
                    <div class="col-4">
                        <a href="${pageContext.request.contextPath}/app?accion=remove&id=${participante.id}" class="btn bg-danger text-light w-100"><i class="bi bi-trash3"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:forEach>



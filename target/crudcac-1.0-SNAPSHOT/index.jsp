<jsp:include page="WEB-INF/pages/comunes/inicioHTML.jsp">
    <jsp:param name="titulo" value="CaC CRUD" />
</jsp:include>

<jsp:include page="WEB-INF/pages/comunes/navbar.jsp" />

<div class="container">
    <div class="p-5 mb-4">
        <h1 class="display-5 fw-bold">WebApp Java con base de datos MySQL</h1>
        <p class="fs-4">Este es un proyecto CRUD (Create, Read, Update, Delete) con fines educativos para el curso Fullstack Java del programa "Codo a Codo".</p>
        <a class="btn btn-warning btn-lg" href="${pageContext.request.contextPath}/app">Ir a la app</a>
    </div>
</div>

<jsp:include page="WEB-INF/pages/comunes/footer.jsp"/>        
<jsp:include page="WEB-INF/pages/comunes/finHTML.jsp"/>




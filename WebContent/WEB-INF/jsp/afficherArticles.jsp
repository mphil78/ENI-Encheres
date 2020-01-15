<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="fr">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Afficher articles</title>
  </head>
  
   <body>
   <div class="container" > <!-- bg-primary"> -->
	    <nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	        <div class="container">
	            <a href="./">
				<img src="${pageContext.request.contextPath}/images/imageLogo.png" width="100px" alt="image">
				</a>
				<c:choose>
   					<c:when test="${!empty sessionScope.pseudo}">
   						<ul class="nav justify-content-center">
   							<li class="nav-item">
	                 			<a class="nav-link" href="./TraitementArticle">Vendre un article</a>   
	                 		</li>          	                
	                 		<li class="nav-item">
	                 			<a class="nav-link" href="./TraitementProfile?pseudoAAfficher=${sessionScope.pseudo}">Bonjour ${sessionScope.pseudo}</a>           	               
	                 		</li>
	                 		<li class="nav-item">
	                 			<a class="nav-link" href="./TraitementConnexion">Déconnexion</a>
	                 		</li>
	                 	</ul>
	       			</c:when>
   					<c:when test="${empty sessionScope.pseudo}">
   						<ul class="nav justify-content-center">
   							<li class="nav-item">	
   								<a class="nav-link" href="./Connexion">S'inscrire - Se connecter </a>
   							</li>	
   						</ul>
   					</c:when>
				</c:choose>
			</div>	
    	</nav>
    </div>
	
	<c:if test="${!empty requestScope.articles }">	
		<c:forEach items="${requestScope.articles}">
			<img src="" alt="image du produit">
			<div>${request.articleVendu.nom }</div>
			<div>${request.articleVendu.prixVente}</div>
			<div>${request.articleVendu.dateFinEncheres }</div>
			<div>Vendeur: ${request.articleVendu.vendeur }</div>
		</c:forEach>	
	</c:if>

	<!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
     <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>
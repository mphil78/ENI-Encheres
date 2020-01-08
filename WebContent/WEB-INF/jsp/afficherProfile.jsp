 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@page import="fr.eni.eniencheres.bo.Utilisateur"%>
 <%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.eniencheres.bo.Categorie"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
	<c:set var="utilisateur" value="${requestScope.utilisateurAAfficher}" scope="page"></c:set>
		<div class="container" > <!-- bg-primary"> -->
	    <nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	        <div class="container">
	            <a href="./TraitementAccueil">
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
	
		<div class="container">
	   		<div class="row h5">
	   			<div class="col-lg-8 offset-lg-3">
	  			
		 			<label for="pseudo">Pseudo : ${utilisateur.pseudo}</label> <br> 
					<label for="nom">Nom : ${utilisateur.nom}</label> <br>						 					
					<label for="prenom">Prénom : ${utilisateur.prenom}</label> <br>											
					<label for="email">Email : ${utilisateur.email}</label> <br>											
					<label for="telephone">Téléphone : ${utilisateur.telephone}</label> <br>
					<label for="rue">Rue : ${utilisateur.rue}</label> <br>
					<label for="codePostal">Code Postal : ${utilisateur.codePostal}</label> <br>								
					<label for="ville">Ville : ${utilisateur.ville}</label> <br>
				</div>
			</div>
		</div>
	
 		<c:choose>
 			<c:when test="${sessionScope.pseudo ne utilisateur.pseudo}">
	 			<div class="row">
		   			<div class="col-lg-8 offset-lg-6">
		   				<a href="./TraitementAccueil"><button type="button" class="btn btn-primary">Retour</button></a>
	 				</div>
	 			</div>
	 		</c:when>
 			
 			<c:when test="${sessionScope.pseudo eq utilisateur.pseudo}">
	 			<div class="row mt-5">
		   			<div class="col-lg-8 offset-lg-6">
		   				<a href="./TraitementProfile?pseudoAAfficher=${sessionScope.pseudo}&modifier=true">
		   					<button type="button" class="btn btn-primary">
		   						Modifier
		   					</button>
		   				</a>
					</div>
	 			</div>
 			</c:when>
 		</c:choose>
			
			 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
</body>
</html>
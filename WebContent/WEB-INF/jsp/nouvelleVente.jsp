<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniencheres.bo.Utilisateur"%>

    
<!DOCTYPE html>
<html lang="fr">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<title>Nouvelle Vente</title>
</head>

<body>
	<div class="container">  
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
		
	<c:set var="utilisateur" value="${requestScope.utilisateurAAfficher}" scope="page"></c:set>
	<c:set var="libelles" value="${requestScope.libelles}" scope="page"></c:set>
	<c:set var="vendeur" value="${requestScope.vendeur}" scope="page"></c:set>
		
	<div class="jumbotron">
		<form action="./TraitementArticle" method="post" enctype="multipart/form-data">
		
			<h2 class="text-center ">Nouvelle vente</h2><br>
			
			<div class="container">
				<div class="row">
					<div class="col-sm-4 ">
		   				<div class="border-left">
		   					<img height="auto" width="100%" src="./AfficherImage?idArticle=${article.noArticle}" alt="image du produit">
		   				</div>
					</div>
					
					<div class="col-sm-8 ">
							<div class="row ">
								<label class="col-4" for="article">Article : </label>
								<div class="col-8">
									<input class="form-control" type="text" name="nom" id="nom" required>
								</div>
							</div>
							
							<div class="row ">
								<label class="col-4" for="description">Description : </label>
								<div class="col-8">
									<textarea class="form-control" id="description" name="description" rows="4" cols="33" required></textarea>
								</div>	
							</div>
							
							<div class="row">
								<label class="col-4" for="categorie">Catégorie :</label>
								<div class="col-8">
									<select class="form-control" name="categorie" id="categorie">
										<option value="-1" selected></option>
										<c:forEach var="lib" items="${libelles}">
											<option value="${lib}"><c:out value="${lib}"/></option>
										</c:forEach>
									</select>
								</div>
							</div>
				
							<div class="row">
								<label class="col-4" for="photoArticle">Photo de l'article :</label>
								<div class="col-8">
									<input type="file" id="photoArticle" name="photoArticle" accept="image/png, image/jpeg">
								</div>
							</div>
						
							<div class="row">
								<label class="col-4" for="debutEnchere">Début de l'enchère :</label>
								<div class="col-8">
									<input class="form-control" type="date" id="debutEnchere" name="debutEnchere" >
								</div>
							</div>
							
							<div class="row">
								<label class="col-4" for="finEnchere">Fin de l'enchère :</label>
								<div class="col-8">
									<input class="form-control" type="date" id="finEnchere" name="finEnchere" >
								</div>
							</div>
							
							<div class="row">
								<!--  ATTENTION : UTILISER UN MAGIC NUMBER -->
								<label class="col-4" for="defPrix">Mise à prix :</label>
								<div class="col-8 ">
									<input class="form-control" type="number" id="defPrix" name="defPrix" min="1" step="1" required>
								</div>
							</div>
						
						<fieldset class="mt-3 border border-dark rounded">
							<legend class="w-auto border border-secondary rounded  mx-3 font-weight-bold">&nbsp; Retrait &nbsp;</legend>
								<div class="row mt-2">
									<label class="col-4 ml-5" for="rue">Rue : </label>
									<div class="col-3">
										<input type="text" name="rue" id="rue" value="${vendeur.rue}" required>
									</div>
								</div>
								<div class="row">
									<label class="col-4 ml-5" for="codePostal">Code Postal : </label>
									<div class="col-3">
										<input type="text" name="codePostal" id="codePostal" value="${vendeur.codePostal}" required><br>
									</div>	
								</div>
								<div class="row mb-3">
									<label class="col-4 ml-5" for="ville">Ville : </label>
									<div class="col-3">
										<input type="text" name="ville" id="ville" value="${vendeur.ville}" required><br>
									</div>	
								</div>
						</fieldset>
						
						<div class="row mt-5 ml-1">
		   					<button class="btn btn-primary mr-5" type="submit" name="enregistrer" id="enregistrer">Enregistrer</button>
							<a href="WebContent/WEB-INF/jsp/listeEncheres.jsp" >
								<button class="btn btn-primary mr-3" type="reset" name="annuler" id="annuler">Annuler</button>
							</a>		
							<c:if test="${!empty requestScope.noArticle}">   
								<button class="btn btn-primary" type="reset"  name="annuler" id="annuler">Annuler la vente</button>
							</c:if>
						</div>
						</div>
					</div>
				</div>
	
			
		   		
			
		</form>
	</div>
	</div>
	
  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>
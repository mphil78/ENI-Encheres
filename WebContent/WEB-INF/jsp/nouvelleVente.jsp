<%@page import="java.time.LocalDate"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniencheres.bo.Utilisateur"%>

    
<!DOCTYPE html>
<html lang="fr">

<head>
<meta charset="UTF-8">
<title>Nouvelle Vente</title>
</head>
<body>

	<c:set var="libelles" value="${requestScope.libelles}" scope="page"></c:set>
	<c:set var="vendeur" value="${requestScope.vendeur}" scope="page"></c:set>
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
	<form action="./TraitementArticle" method="post" enctype="multipart/form-data">
	
		<h2>ENI-Enchères</h2>
		<h2>Nouvelle vente</h2><br>
	
<!-- 	<span class="border-left"><img src="photoArticle" alt="Photo du produit" class="img-responsive"></span> -->
		<p>
			<label for="article">Article : </label>
			<input type="text" name="nom" id="nom" required><br>
		</p>
		
		<p>
			<label for="description">Description : </label>
			<textarea id="description" name="description" rows="5" cols="50" required></textarea>
		</p>
		
		<p>
			<label for="categorie">Catégorie</label>
			<select name="categorie" id="categorie">
					<c:forEach var="lib" items="${libelles}">
						<option value="${lib}"><c:out value="${lib}"/></option>
					</c:forEach>

			</select>
		</p>
		
		<p>
			<label for="photoArticle">Photo de l'article:</label>
			<input type="file" id="photoArticle" name="photoArticle" accept="image/png, image/jpeg">
		</p>
	
		<p>
			<label for="debutEnchere">Début de l'enchère</label>
			<input type="date" id="debutEnchere" name="debutEnchere" >
		</p>
	
		<p>
			<label for="finEnchere">Fin de l'enchère</label>
			<input type="date" id="finEnchere" name="finEnchere" >
		</p>
		
		<p>
			<!--  ATTENTION : UTILISER UN MAGIC NUMBER -->
			<label for="defPrix">Mise à prix :</label>
			<input type="number" id="defPrix" name="defPrix" min="1" step="1" required>
		</p>
	
		<fieldset>
			<legend>Retrait</legend>
			<label for="rue">Rue : </label>
			<input type="text" name="rue" id="rue" value="${vendeur.rue}" required><br>
			<label for="codePostal">Code Postal : </label>
			<input type="text" name="codePostal" id="codePostal" value="${vendeur.codePostal}" required><br>
			<label for="ville">Ville : </label>
			<input type="text" name="ville" id="ville" value="${vendeur.ville}" required><br>
		</fieldset>
	
		<button type="submit" name="enregistrer" id="enregistrer">Enregistrer</button>
		
		<a href="WebContent/WEB-INF/jsp/listeEncheres.jsp" ><button type="reset" name="annuler" id="annuler">Annuler</button></a>
	
		
			<c:if test="${!empty requestScope.noArticle}">   
				<button type="reset" name="annuler" id="annuler">Annuler la vente</button>
			</c:if>
	</form>

</body>
</html>
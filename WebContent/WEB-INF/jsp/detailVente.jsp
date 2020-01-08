<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.eniencheres.bo.Categorie"%>

<!doctype html>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
    crossorigin="anonymous">
    <title>Détail Vente</title>
</head>
<body>

<c:set var="artAffich" value="${requestScope.articleAAfficher}" scope="page"></c:set>
<c:set var="utilisateur" value="${requestScope.utilisateur}" scope="page"></c:set>
<c:set var="padding" value="1" scope="page"></c:set>

<div class="container ">
	<nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	    <div class="container">
	    	<a href="./TraitementAccueil">
				<img src="${pageContext.request.contextPath}/images/imageLogo.png" width="100px" alt="image">
			</a>
		</div>
	</nav>
	
	<div class="jumbotron">
	
		<div class="row">
		
			<div class="col-3">
				<div class="col-3  border border-dark">
					<img class="" src="" alt="image du produit">
				</div>
			</div>
			
			<div class="col-9">
				<h1 class="text-center mb-5 ">Détail vente</h1>
				
				<label for="nom"> ${artAffich.nomArticle} </label><br>
			
				<label for="description">Description : ${artAffich.description}</label><br>
					
				<label for="categorie">Categorie : ${artAffich.categorie}</label><br>
				
				<label for="meilleureOffre">Meilleure Offre : ${artAffich.prixVente}</label><br>
				
				<label for="miseAPrix">Mise à prix : ${artAffich.miseAPrix}</label><br>
			
				<label for="dateFinEncheres">Fin de l'enchères : ${artAffich.dateFinEncheres}</label><br>
			
				<label for="lieuRetrait">Retrait : ${artAffich.lieuRetrait}</label><br>
		
				<label for="vendeur">Vendeur : ${artAffich.vendeur.pseudo}</label><br>
				
		
			<c:choose>
		 			<c:when test="${sessionScope.pseudo ne artAffich.vendeur.pseudo}">
		 				<form action="./TraitementEnchere?idArticle=${artAffich.noArticle}" method="post">
						<%request.setAttribute("padding",1); %>
				
							<label for="maProposition">Ma Proposition : </label>
								<input type="number" name="maProposition" id="maProposition" min ="${artAffich.prixVente+padding}" value ="${artAffich.prixVente+padding}" step ="padding"><br>
							<button class="btn btn-primary btn-lg mt-5" type="submit" name="encherir" id="encherir">Enchérir</button>
						</form>
					</c:when>
			
					<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
						<a href="./TraitementAccueil"><button>Retour à l'accueil</button></a>
					</c:when>
			</c:choose>
			</div>
			</div>
		</div>		
	</div>		
</div>	
</body>
</html>
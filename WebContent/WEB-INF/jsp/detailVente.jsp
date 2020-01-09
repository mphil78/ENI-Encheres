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
<c:set var="gagnant" value="${requestScope.gagnant}" scope="page"></c:set>

<c:set var="padding" value="1" scope="page"></c:set>

<div class="container ">
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
	
	<div class="jumbotron">
	<c:choose>
		<c:when test="${empty gagnant}">
			<h1 class="text-center mb-5 ">Détail vente</h1>
		</c:when>
		<c:when test="${!empty gagnant}">
			<c:choose>
				<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
					<h1 class="text-center mb-5 ">${gagnant.pseudo} a remporté l'enchère</h1>
				</c:when>
				<c:when test="${sessionScope.pseudo ne artAffich.vendeur.pseudo}">
					<h1 class="text-center mb-5 ">Vous avez remporté la vente</h1>
				</c:when>
			</c:choose>
		</c:when>
	</c:choose>
	
		<div class="row">
		
			<div class="col-sm-4 border-dark mr-1">
				<img height="auto" width="100%" src="./AfficherImage?idArticle=${artAffich.noArticle}" alt="image du produit">
			</div>
			
			<div class="col-sm-7">
								
				<label class="text-uppercase font-weight-bolder" for="nom" > <u>${artAffich.nomArticle}</u> </label><br>
			
				<label for="description">Description : ${artAffich.description}</label><br>
				
				<c:if test="${empty gagnant}">	
					<label for="categorie">Categorie : ${artAffich.categorie.libelle}</label><br>
				</c:if>
				
				<c:choose>
					<c:when test="${empty gagnant}">
						<c:choose>
							<c:when test="${artAffich.vendeur.pseudo eq artAffich.acheteur.pseudo}">
									<label for="meilleureOffre">Meilleure Offre : Aucune enchère en cours</label><br>
							</c:when>
							<c:when test="${artAffich.vendeur.pseudo ne artAffich.acheteur.pseudo}">
									<label for="meilleureOffre">Meilleure Offre : ${artAffich.prixVente} par ${artAffich.acheteur.pseudo} </label><br>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${!empty gagnant}">
						<c:choose>
							<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
								<label for="meilleureOffre">Meilleure Offre : ${artAffich.prixVente} par </label> <a href= "./TraitementProfile?pseudoAAfficher=${artAffich.acheteur.pseudo}">${artAffich.acheteur.pseudo}</a><br>
							</c:when>
							<c:when test="${sessionScope.pseudo ne artAffich.vendeur.pseudo}">
								<label for="meilleureOffre">Meilleure Offre : ${artAffich.prixVente}</label><br>
							</c:when>
						</c:choose>
					</c:when>
				</c:choose>	
				
				<label for="miseAPrix">Mise à prix : ${artAffich.miseAPrix}</label><br>
			
				<c:choose>
					<c:when test="${empty gagnant}">
						<label for="dateFinEncheres">Fin de l'enchères : ${artAffich.dateFinEncheres}</label><br>
					</c:when>
					<c:when test="${!empty gagnant}">
						<c:if test="${artAffich.vendeur.pseudo eq artAffich.acheteur.pseudo}">
							<label for="dateFinEncheres">Fin de l'enchères : ${artAffich.dateFinEncheres}</label><br>
						</c:if>
					</c:when>
				</c:choose>
			
				<label for="lieuRetrait">Retrait : ${artAffich.lieuRetrait}</label><br>
		
				<label for="vendeur">Vendeur : ${artAffich.vendeur.pseudo}</label><br>
				
				<c:if test="${!empty gagnant}">
					<label for="telephone">Tel : ${artAffich.vendeur.telephone}</label>
				</c:if>
	<c:choose>
		<c:when test="${empty gagnant}">	
			<c:choose>
		 			<c:when test="${sessionScope.pseudo ne artAffich.vendeur.pseudo}">
		 				<form action="./TraitementEnchere?idArticle=${artAffich.noArticle}" method="post">
						
							<label for="maProposition">Ma Proposition : </label>
								<input type="number" name="maProposition" id="maProposition" min ="${artAffich.prixVente+padding}" value ="${artAffich.prixVente+padding}" step ="padding"><br>
							<div class="row">
								<div class="col-sm-6">
									<button class="btn btn-primary btn-block btn-lg mt-5" type="submit" name="encherir" id="encherir">Enchérir</button>
								</div>	
							</div>
						</form>
					</c:when>
			
					<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
						<div class="row">
							<div class="col-sm-6">
								<a href="./TraitementAccueil">
									<button class="btn btn-primary btn-block btn-lg mt-5">Retour à l'accueil</button>
								</a>
							</div>	
						</div>
					</c:when>
			</c:choose>
			</c:when>

			<c:when test="${!empty gagnant}">
				<c:choose>
					<c:when test="${sessionScope.pseudo ne artAffich.vendeur.pseudo}">
						<div class="row">
							<div class="col-sm-6">
								<a href="./TraitementAccueil">
									<button class="btn btn-primary btn-block btn-lg mt-5">Retour à l'accueil</button>
								</a>
							</div>
						</div>
						<c:choose>
							<c:when test="${artAffich.etatVente==0}">
								<div class="row">
									<div class="col-sm-6">
										<a href="./TraitementEnchere?idArticle=${artAffich.noArticle}&retirer=true">
											<button class="btn btn-primary btn-block btn-lg mt-5">Confirmer la réception</button>
										</a>
									</div>
								</div>
							</c:when>
							<c:when test="${artAffich.etatVente==1}">
								<div class="row">
										<div class="col-sm-6">
											<h5 style="color:green;">Vous avez confirmé la bonne réception de l'article.</h5>
										</div>
									</div>
							</c:when>
						</c:choose>
					</c:when>
					<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
						<div class="row">
							<div class="col-sm-6">
								<a href="./TraitementAccueil">
									<button class="btn btn-primary btn-block btn-lg mt-5">Retour à l'accueil</button>
								</a>
							</div>
						</div>
					<c:choose>
							<c:when test="${artAffich.etatVente==0}">
								<div class="row">
									<div class="col-sm-6">
										<h3 style="color:red;">L'article est en cours de livraison.</h3>
									</div>
								</div>
							</c:when>
							<c:when test="${artAffich.etatVente==1}">
								<div class="row">
										<div class="col-sm-6">
											<h3 style="color:green;">L'article a été livré.</h3>
										</div>
									</div>
							</c:when>
						</c:choose>
					</c:when>
			</c:choose>
		</c:when>
	</c:choose>
			</div>
		</div>
		</div>		
	</div>		
</body>
</html>
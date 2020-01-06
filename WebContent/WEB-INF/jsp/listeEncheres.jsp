<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.eniencheres.bo.Categorie"%>

<!doctype html>
<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <link href="<%=request.getContextPath() %>/css/style.css" rel="stylesheet" type="text/css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp"
    crossorigin="anonymous">
    <title>Liste Enchères</title>
  </head>
  <body>
  	<% List<String> libelles = (List<String>)request.getAttribute("libelles");	%>
  	<% List<ArticleVendu> articles = (List<ArticleVendu>)request.getAttribute("articles");	%>
  	<% List<Categorie> categories = (List<Categorie>)request.getAttribute("categories"); %>
    
    
    <!-- BARRE DE NAVIGATION -->
    <div class="container" > <!-- bg-primary"> -->
	    <nav class="navbar navbar-expand-sm navbar-light bg-light mb-3">
	        <div class="container">
	            <h5>Eni-Enchères</h5>
				<c:choose>
   					<c:when test="${!empty sessionScope.pseudo}">
   						<a class="nav-link" href="./NouvelleVente">Enchères </a>
	                 	<a class="nav-link" href="./TraitementArticle">Vendre un article</a>             	                
	                 	<a class="nav-link" href="./TraitementProfile?pseudoAAfficher=<%=session.getAttribute("pseudo") %>">Bonjour <%=session.getAttribute("pseudo") %></a>           	               
	                 	<a class="nav-link" href="./TraitementConnexion">Déconnexion</a>
	       			</c:when>
   					<c:when test="${empty sessionScope.pseudo}"><a class="nav-link" href="./Connexion">S'inscrire - Se connecter </a>
   					</c:when>
				</c:choose>
			</div>	
    	</nav>
	    
	    
	<!-- TITRE DE LA PAGE -->
	<h2 class="text-center mb-3 mt-5">Liste des enchères</h2>  
		
	
	<!-- DEBUT DU FORMULAIRE -->
	<form action="./TraitementAccueil" method="post">
		
		
		<!-- BARRE DE RECHERCHE -->
		<div class="mb-3">Filtres :</div>
		<div class="input-group input-focus col-sm-8 mb-3" >
	  		<div class="input-group-prepend">
	   		 	<span class="input-group-text bg-white"><i class="fa fa-search"></i></span>
	  		</div>
	  		<input type="search" placeholder="Le nom de l'article contient" class="form-control border-left-0" name="motCle">
		</div>
		
		
		<!-- CATEGORIES -->
		<div class="col-auto my-1">
			<label class="mr-sm-2" for="inlineFormCustomSelect">Catégorie :</label>
			<select name="categorie" class="custom-select mr-sm-2 col-sm-2" id="inlineFormCustomSelect">
		        <option value="-1" selected>Toutes</option>
		        <% for (Categorie cate : categories) { %>
		        	<option value="<%=cate.getNoCategorie()%>"><%=cate.getLibelle()%></option>
		        <%}%>
		     </select>
		</div>
		
		
		<!-- EN CAS DE SESSION CONNECTEE ON AFFICHE LE FORMULAIRE POUR SELECTIONNER LES ARTICLES -->
		<!-- RESTE DU CODE JAVA A CHANGER -->
		<div>
			<%
			if (session.getAttribute("pseudo") != null) {
			%>   
			<div class="form-check">
			
	  			<div class="row" >
	  				<div class="col">
			  			<input class="form-check-input" type="radio" name="achatVente" id="achats" value="0" checked >
			  			<label class="form-check-label" for="achats">Achats</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk1" value="encheresOuvertes" id="encheresOuvertes">
			  			<label class="form-check-label" for="defaultCheck1">enchères ouvertes</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk2" value="encheresCours" id="encheresCours">
			  			<label class="form-check-label" for="encheresCours">mes enchères en cours</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk3" value="ecnheresRemportees" id="ecnheresRemportees">
			  			<label class="form-check-label" for="ecnheresRemportees">mes enchères remportées</label>
			  		</div>
			  		
	  				<div class="col">
			  			<input class="form-check-input" type="radio" name="achatVente" id="mesVentes" value="1" >
			  			<label class="form-check-label" for="exampleRadios1">Mes ventes</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk1" value="ventesEncours" id="ventesEncours" >
			  			<label class="form-check-label" for="ventesEncours">mes ventes en cours</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk2" value="ventesNondebutees" id="ventesNondebutees">
			  			<label class="form-check-label" for="ventesNondebutees">ventes non débutées</label>
			  			<br>
			  			<input class="form-check-input" type="checkbox" name="chk3" value="ventesTerminees" id="ventesTerminees">
			  			<label class="form-check-label" for="ventesTerminees">ventes terminées</label>
	  				</div>
			  		
			  	</div>
			  	
			</div>
			
	</div> 
		<%
		}
		%>
		
		<!-- BOUTON RECHERCHER QUI FAIT LE SUBMIT -->
		<button type="submit" class="btn btn-primary btn-lg mt-3">Rechercher</button>
	</form>	
	
	
	<!-- AFFICHAGE DES ARTICLES -->

	<div class="row mt-5">	
		<c:if test="${!empty requestScope.articles }">	
			<c:forEach items="${requestScope.articles}" var="article">
				<div class="row mt-3">
					<div class="col-3  border border-dark">
						<img class="" src="" alt="image du produit">
					</div>
					<div class="col-7  border border-dark">
						<div class="nomproduit">${article.nomArticle }</div>
						<div>Prix: ${article.prixVente} points</div>
						<div>Fin de l'enchère: ${article.dateFinEncheres }</div>
						<div>Vendeur: <a href = "./TraitementProfile?pseudoAAfficher=${article.vendeur.pseudo}">${article.vendeur.pseudo}</a></div>
					</div>	
					<div class="col-2" ></div>
					<div class="row"></div>
				</div>				
			</c:forEach>	
		</c:if>
		
	</div>
	</div>
	

	
		
			
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
  </body>
</html>
<%@page import="fr.eni.eniencheres.bll.EncheresManager"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
    <%@page import="fr.eni.eniencheres.bo.Utilisateur"%>

    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail Vente</title>
</head>
<body>

<c:set var="artAffich" value="${requestScope.articleAAfficher}" scope="page"></c:set>
<c:set var="utilisateur" value="${requestScope.utilisateur}" scope="page"></c:set>
<c:set var="padding" value="1" scope="page"></c:set>

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
					<c:set var="meilleurOffre" value="${artAffich.prixVente}"></c:set>
					<c:set var="minOffre" value="${meilleurOffre+padding}"></c:set>
		
					<label for="maProposition">Ma Proposition : </label>
						<input type="number" name="maProposition" id="maProposition" min ="minOffre" value ="minOffre" step ="padding"><br>
					<button type="submit" name="encherir" id="encherir">Enchérir</button>
				</form>
			</c:when>
	
			<c:when test="${sessionScope.pseudo eq artAffich.vendeur.pseudo}">
				<a href="./TraitementAccueil"><button>Retour à l'accueil</button></a>
			</c:when>
	</c:choose>
			
			<c:set var ="now" value ="<%= new java.util.Date()%>" />
			<p>Date du jour : <fmt:formatDate type = "date" value ="${now}" /></p><br>
	
</body>
</html>
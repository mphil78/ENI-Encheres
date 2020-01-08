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
<% ArticleVendu articleAAfficher = (ArticleVendu)request.getAttribute("articleAAfficher");
   Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
%>
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
<!-- 		En attente de verification -->
<%-- 		<input type="text" name="lieuRetrait" id="lieuRetrait" value="<%= articleAAfficher.getLieuRetrait().toString()%>" ><br> --%>

		<label for="vendeur">Vendeur : ${artAffich.vendeur.pseudo}</label>
<%-- 		<input type="text" name="vendeur" id="vendeur" value="<%= articleAAfficher.getVendeur().getPseudo()%>"><br> --%>
	
	<!-- FAIRE EN SORTE QUE LA PROPOSITION NE S'AFFICHE PAS POUR LE VENDEUR -->
	<form action="./TraitementEnchere?idArticle=${artAffich.noArticle}" method="post">


			<label for="maProposition">Ma Proposition : </label>
			<c:set var="meilleurOffre" value="${artAffich.prixVente}"></c:set>
			<c:set var="minOffre" value="meilleurOffre+padding"></c:set>
			<c:set var ="now" value ="<%= new java.util.Date()%>" />
			<p>Date du jour : <fmt:formatDate type = "date" value ="${now}" /></p><br>
		<c:choose>
				<c:when test="${artAffich.dateFinEncheres} gt now">
					<a href="./TraitementAccueil"><button> Trop tard</button></a>
				</c:when>
				<c:when test="${artAffich.dateFinEncheres} lt now">
					<input type="number" name="maProposition" id="maProposition" min ="minOffre" value ="minOffre" step ="padding"><br>
					<!-- AFFICHER L'ERREUR erreurProposition SI ELLE EXISTE -->
					<!-- BOGDAAAAAAANNNNNNNN STP TU NOUS FAIS UN ONCLICK EN JAVASCRIPT SI L'ENCHERE EST TERMINEE AU MOMENT DU CLICK  -->
					<button type="submit" name="encherir" id="encherir">Enchérir</button>
				</c:when>
		</c:choose>
	</form>
	
	<!-- FAIRE UN BOUTON RETOUR POUR LE VENDEUR -->
	
</body>
</html>
<%@page import="fr.eni.eniencheres.bll.EncheresManager"%>
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
<% ArticleVendu articleAAfficher = (ArticleVendu)session.getAttribute("articleAAfficher");
   Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
%>



<p>
			<label for="description">Description : </label>
			<input type="text" name="description" id="description" value="<%= articleAAfficher.getDescription()%>"><br>
	</p>
	<p>
			<label for="categorie">Categorie : </label>
			<input type="text" name="categorie" id="categorie" value="<%= articleAAfficher.getCategorie()%>"><br>
	</p>
	<p>
			<label for="meilleureOffre">Meilleure Offre : </label>
			<input type="text" name="meilleureOffre" id="meilleureOffre" value="<%= String.valueOf(articleAAfficher.getPrixVente()) %>"><br>

	</p>
	<p>
			<label for="miseAPrix">Mise à prix : </label>
			<input type="text" name="miseAPrix" id="miseAPrix" value="<%= articleAAfficher.getMiseAPrix()%>"><br>
	</p>
	<p>
	<label for="dateFinEncheres">Fin de l'enchères</label>
	<input type="date" id="dateFinEncheres" name="dateFinEncheres" value="<%= articleAAfficher.getDateFinEncheres()%>">
	<p>
			<label for="lieuRetrait">Retrait : </label>

			<input type="text" name="lieuRetrait" id="lieuRetrait" value="<%= articleAAfficher.getLieuRetrait()%>" ><br>

			</p>
			<p>
			<label for="vendeur">Vendeur : </label>
			<input type="text" name="vendeur" id="vendeur" value="<%= articleAAfficher.getVendeur()%>"><br>
	</p>
	<p>
			<label for="maProposition">Ma Proposition : </label>
			<%
			int meilleureOffre = articleAAfficher.getPrixVente();
			int minOffre = meilleureOffre + EncheresManager.PADDING;
			%>
						
			<input type="number" name="maProposition" id="maProposition" min = "<%= minOffre %> value = "<%= utilisateur.getCredit()%>" step = "10"><br>
	</p>
		
			
	<p>
		<button type="submit" name="encherir" id="encherir">Enchérir</button>
	</p>
			
</p>
</body>
</html>
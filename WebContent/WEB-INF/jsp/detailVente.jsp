<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détail Vente</title>
</head>
<body>
<% Article articleAAfficher = (Article)session.getAttribute("articleAAfficher"); %>


<p>
			<label for="description">Description : </label>
			<input type="text" name="description" id="description" value="<%= Article.getDescription()%>"><br>
	</p>
	<p>
			<label for="categorie">Categorie : </label>
			<input type="text" name="categorie" id="categorie" value="<%= Article.getCategorie()%>"><br>
	</p>
	<p>
			<label for="meilleureOffre">Meilleure Offre : </label>
			<input type="text" name="meilleureOffre" id="meilleureOffre" value="<%= Article.getMeilleureOffre()%>"><br>
	</p>
	<p>
			<label for="miseAPrix">Mise à prix : </label>
			<input type="text" name="miseAPrix" id="miseAPrix" value="<%= Article.getMiseAPrix()%>"><br>
	</p>
	<p>
	<label for="dateFinEncheres">Fin de l'enchères</label>
	<input type="date" id="dateFinEncheres" name="dateFinEncheres" value="<%= Article.getdateFinEncheres()%>">
	<p>
			<label for="lieuRetrait">Retrait : </label>
			<input type="text" name="lieuRetrait" id="lieuRetrait" value="<%= Article.getLieuRetrait()%>" ><br>
			</p>
			<p>
			<label for="vendeur">Vendeur : </label>
			<input type="text" name="vendeur" id="vendeur" value="<%= Article.getVendeur()%>"><br>
	</p>
	<p>
			<label for="maProposition">Ma Proposition : </label>
			<%!
			int meilleureOffre = Article.getMeilleureOffre();
			int minOffre = meilleureOffre + 10;
			%>
						
			<input type="number" name="maProposition" id="maProposition" min = "<%= minOffre %> value = "<%= utilisateur.getCredit()%>" step = "10"><br>
	</p>
		
			
	<p>
		<button type="submit" name="encherir" id="encherir">Enchérir</button>
	</p>
			
</p>
</body>
</html>
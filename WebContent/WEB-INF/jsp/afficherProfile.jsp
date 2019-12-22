 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="fr.eni.eniencheres.bo.Utilisateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<% Utilisateur utilisateurAAfficher = (Utilisateur)request.getAttribute("utilisateurAAfficher"); %>

	<p>
			<label for="pseudo">Pseudo : <%= utilisateurAAfficher.getPseudo()%> </label> <br>
		
			<label for="nom">Nom : <%= utilisateurAAfficher.getNom()%></label> <br>
						 					
			<label for="prenom">Prénom : <%= utilisateurAAfficher.getPrenom()%></label> <br>
											
			<label for="email">Email : <%= utilisateurAAfficher.getEmail()%></label> <br>
											
			<label for="telephone">Téléphone : <%= utilisateurAAfficher.getTelephone()%></label> <br>
													
			<label for="rue">Rue : <%= utilisateurAAfficher.getRue()%></label> <br>
			
			<label for="codePostal">Code Postal : <%= utilisateurAAfficher.getCodePostal()%></label> <br>
									
			<label for="ville">Ville : <%=utilisateurAAfficher.getVille()%></label> <br>
	</p>
			<%
			if (((String)session.getAttribute("pseudo")).equals(utilisateurAAfficher.getPseudo())) {
			%>
			<a href="./TraitementProfile?pseudoAAfficher=<%=session.getAttribute("pseudo")%>&modifier=true"><button type="button" name="modifier" id="modifier">Modifier</button></a>
			<%
			}
			%>
</body>
</html>
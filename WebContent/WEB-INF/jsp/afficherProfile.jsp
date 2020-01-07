 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="fr.eni.eniencheres.bo.Utilisateur"%>
 <%@page import="fr.eni.eniencheres.bo.ArticleVendu"%>
<%@page import="fr.eni.eniencheres.bo.Categorie"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<% Utilisateur utilisateurAAfficher = (Utilisateur)request.getAttribute("utilisateurAAfficher"); %>
<div class="container">  
		   <nav class="navbar mb-5">
				<img src="${pageContext.request.contextPath}/images/imageLogo.png" width="100px" alt="image">
			</nav>
		</div>
	<p>
		<div class="container">
	   		<div class="row h5">
	   			<div class="col-lg-8 offset-lg-3">
	  			
 			<label for="pseudo">Pseudo : <%--<%= utilisateurAAfficher.getPseudo()%>--%> </label> <br> 
			<label for="nom">Nom : <%-- <%= utilisateurAAfficher.getNom()%> --%></label> <br>						 					
			<label for="prenom">Prénom : <%-- <%= utilisateurAAfficher.getPrenom()%> --%></label> <br>											
			<label for="email">Email : <%-- <%= utilisateurAAfficher.getEmail()%> --%></label> <br>											
			<label for="telephone">Téléphone :<%--  <%= utilisateurAAfficher.getTelephone()%> --%></label> <br>
			<label for="rue">Rue : <%-- <%= utilisateurAAfficher.getRue()%> --%></label> <br>
			<label for="codePostal">Code Postal : <%-- <%= utilisateurAAfficher.getCodePostal()%> --%></label> <br>								
			<label for="ville">Ville : <%-- <%=utilisateurAAfficher.getVille()%> --%></label> <br>
			</div>
			</div>
			</div>
	</p>
			<!-- VIRER LE CODE JAVA !!!!!! -->
 			<%-- <%
 			if (session.getAttribute("pseudo")==null||!(((String)session.getAttribute("pseudo")).equals(utilisateurAAfficher.getPseudo()))){
 			%> --%>
 			<div class="row">
	   			<div class="col-lg-8 offset-lg-6">
	   			<a href="./TraitementAccueil"><button type="button" class="btn btn-primary">Retour</button></a>
<!--  				<a href="./TraitementAccueil"><button type="button" id="modifier">Retour</button></a> -->
 			</div>
 			</div>
 			<%-- <%
 			} else {
 			%> --%>
 			<div class="row mt-5">
	   			<div class="col-lg-8 offset-lg-6">
	   			<a href="./TraitementProfile?pseudoAAfficher=<%=session.getAttribute("pseudo")%>&modifier=true"><button type="button" class="btn btn-primary">Modifier</button></a>
<%-- 				<a href="./TraitementProfile?pseudoAAfficher=<%=session.getAttribute("pseudo")%>&modifier=true"><button type="button" name="modifier" id="modifier">Modifier</button></a> --%>
			</div>
 			</div>
			<%-- <%
			}
			%> --%> 
			 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    
</body>
</html>
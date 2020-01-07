<%@page import="fr.eni.eniencheres.bo.Utilisateur"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création Compte</title>
</head>
<body>
<h1>ENI-Enchères</h1><br>
	<div>
		<h2>Mon Profil</h2>

		<c:set var="utilisateur" value="${requestScope.utilisateurAAfficher}" scope="page"></c:set>
			
		
			<c:choose>
				<c:when test="${empty sessionScope.pseudo}">
					<form action="./TraitementProfile?creation=true"method="post"></form>
				</c:when>
				<c:when test="${!empty sessionScope.pseudo}">
					<form action="./TraitementProfile?update=true" method="post"></form>
				</c:when>
			</c:choose>
		
		
			

<!-- 			oninput='password3.setCustomValidity(password3.value != password1.value ? "La confirmation du mot de passe actuel est érronée." : "")'
			oninput='password5.setCustomValidity(password5.value != password4.value ? "La confirmation du nouveau mot de passe est érronée." : "")'> -->
			
			
					
		
			<c:choose>
				<c:when test="${!empty sessionScope.pseudo}">
		  			
						<label for="pseudo">Pseudo : ${utilisateur.pseudo} </label>
					
				</c:when>
		
				<c:when test="${empty sessionScope.pseudo}">
					
						<label for="pseudo">Pseudo : </label>
							<input type="text" name="pseudo" id="pseudo" required>
					
				</c:when>	
				
			</c:choose>			
		
			<c:choose>
				<c:when test="${!empty sessionScope.pseudo}">
					
						<label for="nom">Nom : </label>
						<input type="text" name="nom" id="nom" value="${utilisateur.nom}" required><br>
					
						<label for="prenom">Prénom : </label>
						<input type="text" name="prenom" id="prenom" value="${utilisateur.prenom}" required>
					
						<label for="email">Email : </label>
						<input type="email" name="email" id="email" value="${utilisateur.email}"><br>
					
						<label for="telephone">Téléphone : </label>
						<input type="tel" name="telephone" id="telephone" value="${utilisateur.telephone}" required>
					
						<label for="rue">Rue : </label>
						<input type="text" name="rue" id="rue" value="${utilisateur.rue}" required><br>
					
						<label for="codePostal">Code Postal : </label>
						<input type="text" name="codePostal" id="codePostal" value="${utilisateur.codePostal}" required>
					
						<label for="ville">Ville : </label>
						<input type="text" name="ville" id="ville" value="${utilisateur.ville}" required><br>
					
				</c:when>
		
				<c:when test="${empty sessionScope.pseudo}">
				 		
							<label for="nom">Nom : </label>
							<input type="text" name="nom" id="nom"  required><br>
						
							<label for="prenom">Prénom : </label>
							<input type="text" name="prenom" id="prenom"  required>
						
							<label for="email">Email : </label>
							<input type="email" name="email" id="email" required><br>
						
							<label for="telephone">Téléphone : </label>
							<input type="text" name="telephone" id="telephone"  required>
						
							<label for="rue">Rue : </label>
							<input type="text" name="rue" id="rue"  required><br>
						
							<label for="codePostal">Code Postal : </label>
							<input type="text" name="codePostal" id="codePostal"  required>
						
							<label for="ville">Ville : </label>
							<input type="text" name="ville" id="ville"  required><br>
						
				</c:when>
			</c:choose>
			
		
			<c:choose>
				<c:when test="${!empty sessionScope.pseudo}">
						
							<label for="password3">Mot de Passe actuel: </label>
							<input type="password" name="password3" id="password3" required>
						
						
						
							<label for="password4">Nouveau mot de Passe: </label>
							<input type="password" name="password4" id="password4" required>
						
						
						
							<label for="password5">Confirmation : </label>
							<input type="password" name="password5" id="password5" required><br>
						   
						
							<label for="credit">Crédit : </label>
							<input type="text" name="credit" value="${utilisateur.credit}" disabled>
							
						
				</c:when>
			
		
				<c:when test="${empty sessionScope.pseudo}">
						
							<label for="password">Mot de Passe : </label>
							<input type="password" name="password" id="password" required>
						
						
						
							<label for="password2">Confirmation : </label>
							<input oninput='password2.setCustomValidity(password2.value != password.value ? "La confirmation du mot de passe est érronée." : "")' type="password" name="password2" id="password2" required><br>
						
				</c:when>
			</c:choose>	
			
		
			<c:choose>
				<c:when test="${!empty sessionScope.pseudo}">
					
						<button type="submit" name="enregistrer" id="enregistrer">Enregistrer</button>
							<a href="./TraitementProfile?pseudoASupprimer=${sessionScope.pseudo}&supprimer=true" ><button type="button" name="supprimer" id="supprimer">Supprimer mon compte</button></a>
					
				</c:when>
			
		
				<c:when test="${empty sessionScope.pseudo}">
					
						<button type="submit" name="creer" id="creer">Créer</button>
							<a href="./TraitementAccueil" ><button type="reset" name="annuler" id="annuler">Annuler</button></a>
					
				</c:when>
			</c:choose>
			
		
	</div>
</body>
</html>